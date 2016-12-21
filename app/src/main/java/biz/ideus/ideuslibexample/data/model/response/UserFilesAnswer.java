package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.UserFileData;

/**
 * Created by blackmamba on 21.12.16.
 */

public class UserFilesAnswer extends ServerAnswer<UserFileData> {
    public UserFilesAnswer(UserFileData data) {
        super(data);
    }
}
