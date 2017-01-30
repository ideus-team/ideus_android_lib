package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.data.model.request.GetUserMessagesRequest;
import biz.ideus.ideuslibexample.data.model.response.MessagesResponse;
import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;
import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeReceiver;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeSubscriber;
import biz.ideus.ideuslibexample.data.remote.socket_chat.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model.SendMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket_chat.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.image_viewer_fragment.ImageViewerFragment;
import biz.ideus.ideuslibexample.utils.Constants;
import biz.ideus.ideuslibexample.utils.FileUploadProcessor;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;
import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.webSocketClient;
import static biz.ideus.ideuslibexample.utils.Constants.KIND_IMAGE;
import static biz.ideus.ideuslibexample.utils.Constants.KIND_TEXT;


/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> implements ImageChooserListener, FileUploadProcessor.SuccessUploadListener, ChatAdapter.OnItemChatClickListener {
    private Context context;
    private String chatUserId = "";
    private PeopleEntity friendForChat = new PeopleEntity();
    public List<MessageEntity> messageList = new ArrayList<>();
    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<Boolean> isShowLinearProgress = new ObservableField<>();
    private ChatAdapter adapter;

    private FileUploadProcessor fileUploadProcessor;
    private String uploadedUrl = "";

    private Subscription rxBusNetworkSubscription;

    public void setAdapter(ChatAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnItemChatClickListener(this);
        adapter.initOldMessages(friendForChat);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (arguments != null) {
            chatUserId = arguments.getString(Constants.CHAT_PEOPLE_ID);
        }
        fileUploadProcessor = new FileUploadProcessor();
        fileUploadProcessor.setSuccessUploadListener(this);

        getPeopleById(chatUserId);
        webSocketClient.connectHttpClient();
        webSocketClient.setMessageListener(new SocketMessageListener() {
            @Override
            public void onMessage(SocketMessageResponse response) {
                super.onMessage(response);
                MessageEntity messageEntity = response.data.getMessageEntity();
                adapter.setMessageToList(messageEntity);
                message.set("");
            }
        });
        startNetworkSubscription();
        fetchUserMessages(chatUserId);
    }

    @Override
    public void onBindView(@NonNull ChatView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
        ((ChatActivity) context).setImageChooserListener(this);
    }

    private void sendMessage(String message) {
        webSocketClient.sendMessage(new SendMessageRequest(chatUserId, message, KIND_TEXT));
    }

    private void sendImage(String imageUrl) {
        webSocketClient.sendMessage(new SendMessageRequest(chatUserId, imageUrl, KIND_IMAGE));
    }

    public void onTextChangedMessage(CharSequence text, int start, int before, int count) {
        message.set(text.toString());
    }

    public void onSendClick(View view) {
        if (!message.get().isEmpty()) {
            sendMessage(message.get());
        }
    }

    public void selectImageClick(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((ChatActivity) context);
        ((ChatActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getPeopleById(String peopleId) {
        requeryApi.getPeopleEntity(peopleId)
                .subscribe(peopleEntity1 -> {
                    friendForChat = peopleEntity1;
                    adapter.initOldMessages(friendForChat);
                });
    }


    public void startNetworkSubscription() {
        NetworkChangeReceiver.unsubscribe(rxBusNetworkSubscription);
        rxBusNetworkSubscription = RxBusNetworkConnected.getInstance().getEvents()
                .subscribe(new NetworkChangeSubscriber<Object>() {
                    @Override
                    public void complete() {
                        Utils.toast(context, " Network Connected");
                        webSocketClient.connectHttpClient();
                    }
                });
    }

    private void fetchUserMessages(String chatUserId) {

        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(this, NetSubscriber.ProgressType.LINEAR);

        netApi.getUserMessages(new GetUserMessagesRequest(chatUserId))
                .lift(new CheckError<>())
                .map(messagesResponse -> {
                    requeryApi.storeMessageList(messagesResponse.data.getMessageEntitiesList());
                    return messagesResponse;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<MessagesResponse>(netSubscriberSettings) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onNext(MessagesResponse answer) {
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void setVisibilityLinearProgress(boolean isShowProgress) {
        isShowLinearProgress.set(isShowProgress);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.chat);
    }

    @Override
    public void onChooseImage(String imagePath) {
        fileUploadProcessor.addFilePath(imagePath);
    }

    @Override
    public void setUploadFileAnswer(UploadFileData uploadData) {
        uploadedUrl = uploadData.getFile();
        sendImage(uploadedUrl);
    }

    @Override
    public void onClickItem(int position, MessageEntity messageEntity, ItemChatTag tag) {
        switch (tag){
            case IMAGE:
                ((ChatActivity) context).addFragmentToBackStack(new ImageViewerFragment(messageEntity.getMessage()),null, true, null);
                break;
            case TEXT:
                break;
        }


    }
}

