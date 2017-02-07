package biz.ideus.ideuslib.dialogs;

/**
 * Created by user on 15.12.2016.
 */
public class DialogCommand {
    private Integer dialogCommandModel;
    private Object dialogIntent;


    public Integer getDialogCommandModel() {
        return dialogCommandModel;
    }

    public DialogCommand(Integer dialogCommandModel, Object dialogIntent) {
        this.dialogCommandModel = dialogCommandModel;
        this.dialogIntent = dialogIntent;
    }
}
