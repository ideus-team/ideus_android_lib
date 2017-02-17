package biz.ideus.ideuslibexample.data.remote.socket;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.dialogs.RxBusShowDialog;
import biz.ideus.ideuslibexample.data.DialogStore;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.AuthorizeChatRequestSocket;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.SocketRequestBuilder;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.utils.JSONUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static okhttp3.ws.WebSocket.TEXT;

public abstract class AbsWebSocketClient implements WebSocketListener {

    private ExecutorService writeExecutor;
    private WebSocket myWebSocket = null;
    private boolean isConnect = false;


    private List<ResponseDataKeeperModel> responseListeners = new ArrayList<>();

    private void handleJson(SocketResponseListener responseListener, String json) throws JSONException {

        SocketBaseResponse socketBaseResponse = ((SocketBaseResponse) new Gson().fromJson(json, responseListener.getResponseClass()));
        Observable.just(socketBaseResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketBaseResponse1 -> {
                    if (socketBaseResponse1.hasValidCommand()) {
                        responseListener.onGotResponseData(socketBaseResponse1);
                    }
                });


    }

    /**
     * Method for adding response listeners. May multiply called.
     *
     * @param dependencyObject for link response listener with activity, or fragment, or view model. (Usually pass 'this')
     * @param socketResponseListener response listener (what to do when we got response from server)
     */
    public void addResponseListener(Object dependencyObject, SocketResponseListener<?> socketResponseListener) {
        responseListeners.add(new ResponseDataKeeperModel(dependencyObject, socketResponseListener));
    }

    /**
     * Warning! If you called 'addResponseListener(...)' you MUST called this method in 'onDestroy()' for correct work.
     *
     * @param dependencyObject as 'dependencyObject' in 'addResponseListener(...)' method. (Usually pass 'this')
     */
    public void removeResponseListener(Object dependencyObject) {
        if (responseListeners.isEmpty()) {
            return;
        }

        int i = 0;

        while (i < responseListeners.size()) {
            if (responseListeners.get(i).getLinkedObject().equals(dependencyObject)) {
                responseListeners.remove(responseListeners.get(i));
            } else {
                i++;
            }
        }
    }

    public AbsWebSocketClient() {
        connectHttpClient();
    }

    public void connectHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("ws://46.101.254.89:8020")
                .build();
        WebSocketCall.create(client, request).enqueue(this);
        client.dispatcher().executorService().shutdown();
    }

    private Subscription getPingSubscription() {
        return Observable.interval(1, TimeUnit.SECONDS).map(value -> {
            try {
                if (myWebSocket != null)
                    myWebSocket.sendPing(new Buffer().buffer().writeUtf8("Alright"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return value;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }
                });
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        myWebSocket = webSocket;
        isConnect = true;
        authorisationInWeb(webSocket);

    }

    public void sendMessage(RequestSocketParams requestParams) {
        if (myWebSocket != null && isConnect) {
            send(requestParams);
        } else {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.SOCKET_UNFORTUNATELY_DIALOG());
        }
    }

    private void send(RequestSocketParams requestParams) {
        writeExecutor = Executors.newSingleThreadExecutor();
        writeExecutor.execute(() -> {
            try {
                myWebSocket.sendMessage(RequestBody.create(TEXT, new SocketRequestBuilder(requestParams).createJsonRequest()));
            } catch (Exception e) {
                System.err.println("Unable to send messages: " + e.getMessage());
            }
        });
    }

    private void authorisationInWeb(WebSocket webSocket) {
        writeExecutor = Executors.newSingleThreadExecutor();
        writeExecutor.execute(() -> {
            try {
                myWebSocket = webSocket;
                myWebSocket.sendMessage(RequestBody.create(TEXT, new SocketRequestBuilder(new AuthorizeChatRequestSocket()).createJsonRequest()));
            } catch (IOException e) {
                System.err.println("Unable to send messages: " + e.getMessage());
            }
        });
    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {

        String json = null;

        try {
            json = message.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("json", json);

        if (message.contentType() == TEXT && JSONUtils.isJSONValid(json)) {

            for (ResponseDataKeeperModel responseDataKeeperModel : responseListeners) {
                try {
                    handleJson(responseDataKeeperModel.getSocketResponseListener(), json);
                    writeExecutor.shutdown();
                } catch (Exception ex) {
                    writeExecutor.shutdown();
                }
            }
        }

    }

    @Override
    public void onPong(Buffer payload) {
        //  System.out.println("PONG: " + payload.readUtf8());
    }


    @Override
    public void onClose(int code, String reason) {
        isConnect = false;
        System.out.println("CLOSE: " + code + " " + reason);

    }

    @Override
    public void onFailure(IOException e, Response response) {
        e.printStackTrace();
        System.out.println("socketFail: " + e);
        isConnect = false;
        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.SOCKET_UNFORTUNATELY_DIALOG());

        if (writeExecutor != null)
            writeExecutor.shutdown();
    }

    public void closeWebSocket() {
        if (myWebSocket != null && isConnect)
            new Thread(() -> {
                try {
                    myWebSocket.close(1000, "Goodbye, World!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
    }

    private class ResponseDataKeeperModel {

        ResponseDataKeeperModel(Object linkedObject, SocketResponseListener<?> socketResponseListener) {
            this.linkedObject = linkedObject;
            this.socketResponseListener = socketResponseListener;
        }

        Object linkedObject; // for keep link to father class. For identity when we want to remove listeners.
        SocketResponseListener<?> socketResponseListener;

        public SocketResponseListener<?> getSocketResponseListener() {
            return socketResponseListener;
        }

        public Object getLinkedObject() {
            return linkedObject;
        }
    }
}
