package biz.ideus.ideuslibexample.ui.chat_screen;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;

/**
 * Created by blackmamba on 01.02.17.
 */

public class MessageViewModel extends BaseObservable {
    private MessageEntity messageEntity;

    //  private String message;
    private String kind;
    private String userId;
    private String ident;
    private String timeMessage;
    private String dateMessage;
    private String friendPhoto;
    private boolean isOwner;
    private boolean isVisibleText;
    private boolean isVisibleImage;
    private boolean isVisibleDate;


    public ObservableField<Boolean> updatedObs = new ObservableField<>();
    public ObservableField<String> messageObs = new ObservableField<>();

    public MessageEntity getMessageEntity() {
        return messageEntity;
    }

    public void setMessageEntity(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;

    }

    @Bindable
    public String getKind() {
        return messageEntity.getKind();
    }

    @Bindable
    public String getUserId() {
        return messageEntity.getUserId();
    }

    @Bindable
    public String getIdent() {
        return messageEntity.getIdent();
    }

    @Bindable
    public String getTimeMessage() {
        return messageEntity.getTimeMessage();
    }

    @Bindable
    public String getDateMessage() {
        return messageEntity.getDateMessage();
    }

    @Bindable
    public boolean isOwner() {
        return messageEntity.isOwner();
    }

    @Bindable
    public String getFriendPhoto() {
        return friendPhoto;
    }

    @Bindable
    public boolean isVisibleText() {
        return getKind().equals("text");
    }

   
    public boolean isVisibleImage() {
        return getKind().equals("image");
    }

    public boolean isUpdated() {
        return messageEntity.isUpdated();
    }

    public String getMessage() {
        return messageObs.get();
    }

    @Bindable
    public boolean isVisibleDate() {
        return isVisibleDate;
    }

    public MessageViewModel setVisibleDate(boolean visibleDate) {
        isVisibleDate = visibleDate;
        return this;
    }

    public void setMessage(String message) {
        this.messageObs.set(message);
    }

    public void setUpdated(boolean isUpdated) {
        if(!isOwner())
        this.updatedObs.set(isUpdated);
    }

    public MessageViewModel setFriendPhoto(String friendPhoto) {
        this.friendPhoto = friendPhoto;
        return this;
    }

    public MessageViewModel(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
        messageObs.set(messageEntity.getMessage());
    }
}


