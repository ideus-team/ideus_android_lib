package biz.ideus.ideuslib.dialogs;

public class DialogModelBuilder {
    private int resDialogHeader;
    private int resDialogText;
    private int layoutId;
    private int colorTitle;
    private int resBtnText;
    private DialogType dialogType = DialogType.SHOW_DIALOG;

    public DialogModelBuilder setResDialogHeader(int resDialogHeader) {
        this.resDialogHeader = resDialogHeader;
        return this;
    }

    public DialogModelBuilder setResDialogText(int resDialogText) {
        this.resDialogText = resDialogText;
        return this;
    }

    public DialogModelBuilder setLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public DialogModelBuilder setColorTitle(int colorTitle) {
        this.colorTitle = colorTitle;
        return this;
    }

    public DialogModelBuilder setResBtnText(int resBtnText) {
        this.resBtnText = resBtnText;
        return this;
    }

    public DialogModelBuilder setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public DialogModel createDialogModel() {
        return new DialogModel(resDialogHeader, resDialogText, layoutId, colorTitle, resBtnText, dialogType);
    }
}