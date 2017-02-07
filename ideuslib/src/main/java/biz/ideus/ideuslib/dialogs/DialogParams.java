package biz.ideus.ideuslib.dialogs;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * wrapper for {@link DialogModel}, also used for callback
 */

@Parcel
public class DialogParams {
    public DialogModel dialogModel;
    public Integer dialogIntent;
    public boolean hasIntent;
    public String dialogHeader;
    public boolean hasHeader;
    public String dialogText;
    public boolean hasText;


    @ParcelConstructor
    public DialogParams(DialogModel dialogModel, Integer dialogIntent, boolean hasIntent
            , String dialogHeader, boolean hasHeader, String dialogText, boolean hasText) {
        this.dialogModel = dialogModel;
        this.dialogIntent = dialogIntent;
        this.hasIntent = hasIntent;
        this.dialogHeader = dialogHeader;
        this.hasHeader = hasHeader;
        this.dialogText = dialogText;
        this.hasText = hasText;
    }

    public DialogParams() {
    }


    public DialogModel getDialogModel() {
        return dialogModel;
    }


    public Object getDialogIntent() {
        return dialogIntent;
    }


    public String getDialogHeader() {
        return dialogHeader;
    }


    public String getDialogText() {
        return dialogText;
    }


    public boolean isHasIntent() {
        return hasIntent;
    }


    public boolean isHasHeader() {
        return hasHeader;
    }


    public boolean isHasText() {
        return hasText;
    }

}
