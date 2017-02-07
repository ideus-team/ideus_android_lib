package biz.ideus.ideuslib.dialogs;

/**
 * data model from onClick {@link CustomDialog}
 */
public class DialogCommand {
    /**
     * value from layout Tag
     */
    private Integer dialogCommandModel;
    /**
     * object that will be sent to callback handler for this dialog
     */
    private Object dialogIntent;


    public Integer getDialogCommandModel() {
        return dialogCommandModel;
    }

    public DialogCommand(Integer dialogCommandModel, Object dialogIntent) {
        this.dialogCommandModel = dialogCommandModel;
        this.dialogIntent = dialogIntent;
    }
}
