package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.databinding.ActivityChatBinding;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;



/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivity extends BaseActivity<ChatView, ChatActivityVM, ActivityChatBinding>
        implements ChatView{

    public void setImageChooserListener(ImageChooserListener imageChooserListener) {
        this.imageChooserListener = imageChooserListener;
    }

    private ImageChooserListener imageChooserListener;

    private ChatAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        adapter = new ChatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        getBinding().rViewChat.setAdapter(adapter);
        getBinding().rViewChat.setHasFixedSize(true);
        getBinding().rViewChat.setLayoutManager(linearLayoutManager);
        adapter.setScrollToBottomListener(position -> getBinding().rViewChat.postDelayed(() -> getBinding().rViewChat.smoothScrollToPosition(position), 400));
        getViewModel().setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    Uri resultPick = CropImage.getPickImageResultUri(this, intent);
                    if (resultPick != null) {
                        CropImage.activity(resultPick)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(this);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    Uri resultUri = result.getUri();
                    imageChooserListener.onChooseImage(resultUri.getPath());
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(getViewModel().isEditMessageEnabled.get()){
            getViewModel().disableEditMessage();
        } else {
            super.onBackPressed();
        }
    }

    @Nullable
    @Override
    public Class<ChatActivityVM> getViewModelClass() {
        return ChatActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_chat, BR.viewModel, this);
    }

}



