package biz.ideus.ideuslibexample.data.remote.socket;

import android.animation.ObjectAnimator;
import android.support.annotation.Nullable;

public enum ResponseBaseCommand {

    TEST_COMMAND ("test_command", ObjectAnimator.class);

    public String command;

    @SuppressWarnings("unchecked")
    public <T> T getResponseClass() {
        return (T) responseClass;
    }

    public Class responseClass;

    ResponseBaseCommand(String command, Class responseClass) {
        this.command = command;
        this.responseClass = responseClass;
    }

    @Nullable
    public static ResponseBaseCommand parse(String command) {
        for (ResponseBaseCommand responseBaseCommand: values()) {
            if (responseBaseCommand.command.contentEquals(command)) {
                return responseBaseCommand;
            }
        }
        return null;
    }
}
