package biz.ideus.ideuslibexample.data.remote.socket;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.AuthorizeChatRequestSocket;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.SocketRequestBuilder;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAutorisedResponse;
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

import static okhttp3.ws.WebSocket.TEXT;

public final class WebSocketClient implements WebSocketListener {
    private ExecutorService writeExecutor;
    private WebSocket myWebSocket = null;
    private Class responseType;
    private Object serverAnswer;
    private SocketMessageReceiver socketMessageReceiver;
    public static WebSocketClient instance = null;


    public static WebSocketClient getInstance() {
        if (instance == null) {
            instance = new WebSocketClient();
        }
        return instance;
    }

    public WebSocketClient() {
        createHttpClient();
        socketMessageReceiver = new SocketMessageReceiver();
    }

    private void createHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://46.101.254.89:8080")
                .build();
        WebSocketCall.create(client, request).enqueue(this);
        client.dispatcher().executorService().shutdown();
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        autorisationInWeb(webSocket);

    }

    public void sendMessage(Class responseType, RequestSocketParams requestParams) {
        this.responseType = responseType;
        send(requestParams);
    }

    private void send(RequestSocketParams requestParams) {
        writeExecutor = Executors.newSingleThreadExecutor();
        writeExecutor.execute(() -> {
            try {
                myWebSocket.sendMessage(RequestBody.create(TEXT, new SocketRequestBuilder(requestParams).createJsonRequest()));
//                myWebSocket.sendMessage(RequestBody.create(TEXT, "...World!"));
                //  myWebSocket.sendMessage(RequestBody.create(BINARY, ByteString.decodeHex("deadbeef")));

            } catch (IOException e) {
                System.err.println("Unable to send messages: " + e.getMessage());
            }
        });
    }

    private void autorisationInWeb(WebSocket webSocket) {
        responseType = SocketAutorisedResponse.class;
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
        String messageCommand = null;
        json = message.string();
        if (message.contentType() == TEXT && JSONUtils.isJSONValid(json)) {
            try {
                messageCommand = new JSONObject(json).get("command").toString();
                serverAnswer = new Gson().fromJson(json, responseType);
                socketMessageReceiver.dispatchRxBus(serverAnswer, messageCommand);
                writeExecutor.shutdown();
            } catch (Exception ex) {
                writeExecutor.shutdown();
            }
        }
    }

    @Override
    public void onPong(Buffer payload) {
        System.out.println("PONG: " + payload.readUtf8());
    }


    @Override
    public void onClose(int code, String reason) {
        System.out.println("CLOSE: " + code + " " + reason);

    }

    @Override
    public void onFailure(IOException e, Response response) {
        e.printStackTrace();
        System.out.println("socketFail: " + e);
        writeExecutor.shutdown();
    }

    public void closeWebSocket() {
        try {
            myWebSocket.close(1000, "Goodbye, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}