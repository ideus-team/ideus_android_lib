package biz.ideus.ideuslibexample.data;

/**
 * Created by blackmamba on 02.12.16.
 */

public enum DialogCommandModel {
    NULL_VALUE(-1),
    COPY_TEXT(1), EDIT(2), DELETE(3), DETAILS(4), UPDATE_NOW(5), SKIP_UPDATE(6);

    private int _value;

    DialogCommandModel(int Value) {
        this._value = Value;
    }

    public int getValue() {
        return _value;
    }

    public static DialogCommandModel fromInt(int i) {
        for (DialogCommandModel b : DialogCommandModel .values()) {
            if (b.getValue() == i) { return b; }
        }
        return NULL_VALUE;
    }
}
