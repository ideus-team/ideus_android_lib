package biz.ideus.ideuslibexample.dialogs;

/**
 * Created by user on 15.12.2016.
 */
public class DialogCommand {
    private DialogCommandModel dialogCommandModel;
    private Object dialogIntent;


    public DialogCommandModel getDialogCommandModel() {
        return dialogCommandModel;
    }

    public DialogCommand(DialogCommandModel dialogCommandModel, Object dialogIntent) {
        this.dialogCommandModel = dialogCommandModel;
        this.dialogIntent = dialogIntent;
    }
}
