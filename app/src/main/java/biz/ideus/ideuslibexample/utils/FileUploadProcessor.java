package biz.ideus.ideuslibexample.utils;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.data.model.response.UploadFileAnswer;
import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blackmamba on 21.12.16.
 */
public class FileUploadProcessor {
    private Subscription subscription;
    private List<String> filePathList = new ArrayList<>(), filesToRemove = new ArrayList<>();
    private List<Integer> fileIdsList = new ArrayList<>();
    private Map<String, Integer> filesMap = new HashMap<>();
    private ProcessorState state = ProcessorState.COMPLETED;
    private SuccessUploadListener successUploadListener;

    public void setSuccessUploadListener(SuccessUploadListener successUploadListener) {
        this.successUploadListener = successUploadListener;
    }

    private NetApi netApi = SampleApplication.getAppComponent().netApi();

    private Handler messageHandler = new Handler();


    private void setState(ProcessorState newState) {
        if (newState == ProcessorState.INPROGRESS && state != ProcessorState.INPROGRESS) {
            state = newState;
            startProcess();
        } else {
            state = newState;
        }
    }

    public void clearListIds() {
        fileIdsList.clear();
        filesMap.clear();
    }

    public ProcessorState getState() {
        return state;
    }

    public List<Integer> getFileIdsList() {
        fileIdsList.addAll(filesMap.values());
        return fileIdsList;
    }

    public void addFilePath(String filePath) {
        String file = filePath;
        if (filePath.startsWith("file://")) {
            file = filePath.replaceFirst("file://", "");
        }

        if (!filesMap.containsKey(file)) {
            filesMap.put(file, 0);
            setState(ProcessorState.INPROGRESS);
        }
    }

    public void removeFilePath(String filePath) {
        String file = filePath;
        if (filePath.startsWith("file://")) {
            file = filePath.replaceFirst("file://", "");
        }
        filesToRemove.add(file);
        if (state == ProcessorState.COMPLETED) {
            setState(ProcessorState.INPROGRESS);
            startProcess();
        }
    }

    public void startProcess() {
        Log.d("fileUpload", "startProcess");
        if (filesToRemove.size() > 0) {
            for (String fileRemove : filesToRemove) {
                if (filesMap.containsKey(fileRemove)) {
                    filesMap.remove(fileRemove);
                }
                filesToRemove.clear();
            }
        }

        if (filesMap.containsValue(0)) {
            Log.d("fileUpload", "filesMap.containsValue(0)");
            Set<String> files = filesMap.keySet();
            for (String file : files) {
                if (filesMap.get(file) == 0) {
                    processFile(file);
                    break;
                }
            }
        } else {
            Log.d("fileUpload", "else filesMap.containsValue(0)");
            setState(ProcessorState.COMPLETED);
        }
    }


    private void processFile(String fileName) {
        Log.d("file", "processFile");
        //       stopProcess();

        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.NONE);


        SampleApplication.netApi.uploadFile(Utils.createMultipartBody(fileName))
                .map(uploadFileAnswer -> {
                    storeResult(uploadFileAnswer, fileName);
                    return uploadFileAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<UploadFileAnswer>(netSubscriberSettings) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        Log.d("fileUpload", "COMPLETED");
                        messageHandler.postDelayed(() -> startProcess(), 1000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        setState(ProcessorState.ERROR);
                    }

                    @Override
                    public void onNext(UploadFileAnswer uploadFileAnswer) {
                        super.onNext(uploadFileAnswer);
                        if (successUploadListener != null)
                            successUploadListener.setUploadFileAnswer(uploadFileAnswer.data);
                    }
                });
    }

    private void storeResult(UploadFileAnswer uploadFileAnswer, String fileName) {
        // UploadedFileEntity uploadedFileEntity = uploadFileAnswer.getData().getUploadedFileEntity();
        if (filesMap.containsKey(fileName)) {
            filesMap.remove(fileName);
            filesMap.put(fileName, uploadFileAnswer.data.getId());
        }
    }

    public void stopProcess() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public interface SuccessUploadListener {
        void setUploadFileAnswer(UploadFileData fileUrl);
    }

    public enum ProcessorState {
        INPROGRESS,
        COMPLETED,
        ERROR
    }
}
