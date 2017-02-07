package biz.ideus.ideuslib.dialogs;

public class DialogParamsBuilder {
    private DialogModel dialogModel;
    private Integer dialogIntent;
    private boolean hasIntent;
    private String dialogHeader;
    private boolean hasHeader;
    private String dialogText;
    private boolean hasText;

    public DialogParamsBuilder setDialogModel(DialogModel dialogModel) {
        this.dialogModel = dialogModel;
        return this;
    }

    public DialogParamsBuilder setDialogIntent(Integer dialogIntent) {
        this.dialogIntent = dialogIntent;
        this.hasIntent = dialogIntent != null;
        return this;
    }


    public DialogParamsBuilder setDialogHeader(String dialogHeader) {
        this.dialogHeader = dialogHeader;
        this.hasHeader = !dialogHeader.isEmpty();
        return this;
    }


    public DialogParamsBuilder setDialogText(String dialogText) {
        this.dialogText = dialogText;
        this.hasText = !dialogText.isEmpty();
        return this;
    }

    public DialogParams createDialogParams() {
        return new DialogParams(dialogModel, dialogIntent, hasIntent, dialogHeader, hasHeader, dialogText, hasText);
    }
}