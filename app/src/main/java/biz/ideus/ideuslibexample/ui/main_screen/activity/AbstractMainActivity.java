package biz.ideus.ideuslibexample.ui.main_screen.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;


public abstract class AbstractMainActivity extends BaseActivity<StartView, MainActivityVM, ActivityMainBinding>
        implements StartView {

    private ImageChooserListener imageChooserListener;


    public void setImageChooserListener(ImageChooserListener imageChooserListener) {
        this.imageChooserListener = imageChooserListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Log.d("LIFE", "MainActivity onCreate");
        new RelativeLayout(this, null, R.style.ButtonGreenStyle);
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

    @Nullable
    @Override
    public Class<MainActivityVM> getViewModelClass() {
        return MainActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(getLayout(), BR.viewModel, this);
    }

    abstract int getLayout();
}

