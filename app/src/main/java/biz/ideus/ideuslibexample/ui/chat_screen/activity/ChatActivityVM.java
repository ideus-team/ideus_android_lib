package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
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

import biz.ideus.ideuslib.dialogs.RxBusCustomAction;
import biz.ideus.ideuslib.dialogs.RxBusShowDialog;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.data.DialogCommandModel;
import biz.ideus.ideuslibexample.data.DialogStore;
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
import biz.ideus.ideuslibexample.data.remote.socket_chat.SocketCommand;
import biz.ideus.ideuslibexample.data.remote.socket_chat.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket_chat.WebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model.SendMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model.UpdateMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket_chat.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.chat_screen.MessageViewModel;
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
import static biz.ideus.ideuslibexample.utils.Constants.KIND_IMAGE;
import static biz.ideus.ideuslibexample.utils.Constants.KIND_TEXT;


/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> implements ImageChooserListener, FileUploadProcessor.SuccessUploadListener, ChatAdapter.OnItemChatClickListener {
    private Context context;
    private String chatUserId = "";
    private PeopleEntity friendForChat = new PeopleEntity();
    private MessageViewModel messageVMForEdit;
    public List<MessageViewModel> messageList = new ArrayList<>();
    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<Boolean> isShowLinearProgress = new ObservableField<>();
    public ObservableField<Boolean> isEditMessageEnabled = new ObservableField<>();
    private ChatAdapter adapter;
    private WebSocketClient webSocketClient = WebSocketClient.getInstance();

    private FileUploadProcessor fileUploadProcessor;
    private String uploadedUrl = "";

    private Subscription rxBusNetworkSubscription;
    private Subscription rxEditDialogMessageSubscription;

    public void setAdapter(ChatAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnItemChatClickListener(this);
        adapter.notifyChatAdapter(messageList, friendForChat);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (arguments != null) {
            chatUserId = arguments.getString(Constants.CHAT_PEOPLE_ID);
        }
        isEditMessageEnabled.set(false);
        fileUploadProcessor = new FileUploadProcessor();
        fileUploadProcessor.setSuccessUploadListener(this);
        message.set("");


        webSocketClient.setMessageListener(new SocketMessageListener() {
            @Override
            public void onMessage(SocketMessageResponse response) {
                super.onMessage(response);

                if (adapter == null) {
                    return;
                }
                if (checkCurrentFriend(response.data.getMessageEntity().getUserId())) {
                    MessageEntity messageEntity = response.data.getMessageEntity();

                    requeryApi.storeMessage(messageEntity).subscribe(messageEntity1 -> {
                        switch (SocketCommand.getSocketCommandByValue(response.command)) {
                            case RECEIVE_MESSAGE:
                                setMessageModel(response, messageEntity1);
                                break;
                            case MESSAGE_SENT:
                                updatedMessageModel(response);
                                break;
                        }
                    });
                }
            }
        });

        startNetworkSubscription();
        rxEditDialogMessageSubscription = getSubscribtionEditDialogMessage();
        fetchMessages(chatUserId);
    }

    @Override
    public void onStart() {
        super.onStart();
        webSocketClient.connectHttpClient();
    }

    @Override
    public void onBindView(@NonNull ChatView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
        ((ChatActivity) context).setImageChooserListener(this);
    }

    private void updatedMessageModel(SocketMessageResponse response) {
        adapter.updateMessage(new MessageViewModel(response.data.getMessageEntity()));
        disableEditMessage();
    }

    private void setMessageModel(SocketMessageResponse response, MessageEntity messageEntity) {
        if (messageEntity.isUpdated()) {
            adapter.updateMessage(new MessageViewModel(response.data.getMessageEntity()));
        } else {
            adapter.setMessageToList(new MessageViewModel(response.data.getMessageEntity()));
        }

        if(!isEditMessageEnabled.get())
        message.set("");
    }

    private boolean checkCurrentFriend(String fromUserId) {
        return chatUserId.equals(fromUserId);
    }


    private void sendMessage(String message) {
        webSocketClient.sendMessage(new SendMessageRequest(chatUserId, message, KIND_TEXT));
    }

    private void updateMessage(String message, String messageId) {
        webSocketClient.sendMessage(new UpdateMessageRequest(message, messageId));
    }

    private void sendImage(String imageUrl) {
        webSocketClient.sendMessage(new SendMessageRequest(chatUserId, imageUrl, KIND_IMAGE));
    }

    public void onTextChangedMessage(CharSequence text, int start, int before, int count) {
        message.set(text.toString());
    }

    public void onSendClick(View view) {
        if (!message.get().isEmpty()) {
            if (isEditMessageEnabled.get()) {
                updateMessage(message.get(), messageVMForEdit.getIdent());
            } else {
                sendMessage(message.get());
            }
        }
    }

    public void selectImageClick(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((ChatActivity) context);
        ((ChatActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    private void fetchMessages(String peopleId) {
        requeryApi.getPeopleEntity(peopleId)
                .map(peopleEntity ->
                {
                    messageList = requeryApi.getMessageList(peopleEntity.getIdent());
                    return peopleEntity;
                })
                .subscribe(peopleEntity -> {
                    friendForChat = peopleEntity;
                    fetchMessagesFromServer(peopleId);
                });
    }


    public void startNetworkSubscription() {
        NetworkChangeReceiver.unsubscribe(rxBusNetworkSubscription);
        rxBusNetworkSubscription = RxBusNetworkConnected.getInstance().getEvents()
                .subscribe(new NetworkChangeSubscriber<Object>() {
                    @Override
                    public void complete() {
                        webSocketClient.connectHttpClient();
                        fetchMessages(chatUserId);
                    }
                });
    }

    private void fetchMessagesFromServer(String chatUserId) {
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(this, NetSubscriber.ProgressType.LINEAR);
        netApi.getUserMessages(new GetUserMessagesRequest().setIdFrom(chatUserId))
                .lift(new CheckError<>())
                .map(messagesResponse -> {
                    requeryApi.storeMessageList(messagesResponse.data.getMessageEntitiesList());
                    messageList = requeryApi.getMessageList(chatUserId);
                    return messagesResponse;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<MessagesResponse>(netSubscriberSettings) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        adapter.notifyChatAdapter(messageList, friendForChat);
                    }

                    @Override
                    public void onNext(MessagesResponse answer) {
                        adapter.notifyChatAdapter(messageList, friendForChat);
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


    private Subscription getSubscribtionEditDialogMessage() {
        return RxBusCustomAction.instanceOf().getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dialogCommand -> {
                    switch (DialogCommandModel.fromInt(dialogCommand.getDialogCommandModel())) {
                        case COPY_TEXT:
                            copyText(messageVMForEdit.getMessage());
                            break;
                        case EDIT:
                            if (messageVMForEdit.isOwner()) {
                                enableEditMessage();
                            } else {
                                Utils.toast(context, context.getString(R.string.edit_message_unfortunately));
                            }
                            break;
                        case DELETE:
                            break;
                        case DETAILS:
                            break;

                    }
                });
    }

    public void enableEditMessage() {
        isEditMessageEnabled.set(true);
        message.set(messageVMForEdit.getMessage());
    }

    public void disableEditMessage() {
        isEditMessageEnabled.set(false);
        message.set("");
        messageVMForEdit = null;
    }

    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Utils.toast(context, context.getString(R.string.copied_success));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxEditDialogMessageSubscription != null && !rxEditDialogMessageSubscription.isUnsubscribed()) {
            rxEditDialogMessageSubscription.unsubscribe();
        }
        adapter = null;
    }


    @Override
    public void onClickItem(MessageViewModel messageViewModel, ItemChatTag tag) {
        switch (tag) {
            case IMAGE:
                ((ChatActivity) context).addFragmentToBackStack(new ImageViewerFragment(messageViewModel.getMessage()), null, true, null);
                break;
            case TEXT:
                messageVMForEdit = messageViewModel;
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.EDIT_TEXT_DIALOG());
                break;
        }
    }
}
