package biz.ideus.ideuslibexample.dialogs;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by user on 15.12.2016.
 */

@Parcel
public class DialogParams {
    private DialogModel dialogModel;
    private Integer dialogIntent;
    private boolean hasIntent;
    private String dialogHeader;
    private boolean hasHeader;
    private String dialogText;
    private boolean hasText;

    @ParcelConstructor
    public DialogParams(DialogModel dialogModel, Integer dialogIntent, boolean hasIntent, String dialogHeader, boolean hasHeader, String dialogText, boolean hasText) {
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
