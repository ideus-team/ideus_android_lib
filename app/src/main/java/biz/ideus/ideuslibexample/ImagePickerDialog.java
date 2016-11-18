package biz.ideus.ideuslibexample;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by blackmamba on 18.11.16.
 */

public class ImagePickerDialog {
        private Context context;

        public void setActivity(Context context) {
            this.context = context;
        }

        View.OnClickListener onPickGaleryClickListener, onPickPhotoClickListener;
        ContextThemeWrapper contextThemeWrapper;
        Dialog dialog;
        TextView pickImage, makePhoto;

        public ImagePickerDialog(Context context) {
            this.context = context;
            dialog = new Dialog(context, R.style.customDialog);
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.image_picker_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }


        public ImagePickerDialog setPickImage() {
            pickImage = (TextView) dialog.findViewById(R.id.tv_pickImage);
            return this;
        }

        public ImagePickerDialog setMakePhoto() {
            makePhoto = (TextView) dialog.findViewById(R.id.tv_make_photo);
            return this;
        }

        public ImagePickerDialog setOnPickImageClickListener(View.OnClickListener onPickGaleryClickListener) {
            this.onPickGaleryClickListener = onPickGaleryClickListener;
            return this;
        }

        public ImagePickerDialog setMakePhotoClickListener(View.OnClickListener onPickPhotoClickListener) {
            this.onPickPhotoClickListener = onPickPhotoClickListener;
            return this;
        }

        private void setListeners() {
            pickImage.setOnClickListener(onPickGaleryClickListener);
            makePhoto.setOnClickListener(onPickPhotoClickListener);
        }

        public void show() {
            setListeners();
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }

        public void hide() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

    }

