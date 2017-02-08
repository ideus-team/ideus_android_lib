package biz.ideus.ideuslibexample.data.remote.socket;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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


    private List<SocketResponseListener> responseListeners = new ArrayList<>();

    private void handleJson(String json) throws JSONException {

        for (SocketResponseListener responseListener: responseListeners) {

            SocketBaseResponse socketBaseResponse = ((SocketBaseResponse) new Gson().fromJson(json, responseListener.getResponseClass()));

            if (socketBaseResponse.hasValidCommand()) {
                responseListener.onGotResponseData(socketBaseResponse);
            }
        }
    }

    public void addResponseListener(SocketResponseListener<?> socketResponseListener) {
        responseListeners.add(socketResponseListener);
    }

    public void removeResponseListener(SocketResponseListener socketResponseListener) {
        responseListeners.remove(socketResponseListener);
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
            try {
                handleJson(json);

                /*biz.ideus.ideuslibexample.network.SocketCommand socketCommand = biz.ideus.ideuslibexample.network.SocketCommand.getSocketCommandByValue(messageCommand);
                Object serverAnswer = new Gson().fromJson(json, socketCommand.responseType);
                RxBusBoardSocketEvent.getInstance().setRxBusBoardSocketEvent(new BoardSocketMessageWrapper(serverAnswer, socketCommand));*/
//                if (messageListener != null) {
//                    messageListener.addToMessageSelector(new SocketMessageWrapper(serverAnswer, socketCommand));
//                }
                writeExecutor.shutdown();
            } catch (Exception ex) {
                writeExecutor.shutdown();
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

}
