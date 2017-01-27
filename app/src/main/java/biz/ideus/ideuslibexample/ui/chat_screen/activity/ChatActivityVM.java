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
import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.remote.socket.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.SendMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Constants;
import biz.ideus.ideuslibexample.utils.FileUploadProcessor;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.webSocketClient;
import static biz.ideus.ideuslibexample.utils.Constants.KIND_IMAGE;
import static biz.ideus.ideuslibexample.utils.Constants.KIND_TEXT;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> implements ImageChooserListener, FileUploadProcessor.SuccessUploadListener {
    private Context context;
    private String chatUserId = "";
    private PeopleEntity friendForChat = new PeopleEntity();
    public List<SocketMessageResponse> messageList = new ArrayList<>();
    public ObservableField<String> message = new ObservableField<>();
    private ChatAdapter adapter;

    private FileUploadProcessor fileUploadProcessor;
    private String uploadedUrl = "";


    public void setAdapter(ChatAdapter adapter) {
        this.adapter = adapter;
        adapter.setMessageList(messageList);
        adapter.setFriendForChat(friendForChat);
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
                messageList.add(response);
                adapter.setMessageToList(response);
                message.set("");
            }
        });
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
                    adapter.setFriendForChat(friendForChat);
                });
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
}

