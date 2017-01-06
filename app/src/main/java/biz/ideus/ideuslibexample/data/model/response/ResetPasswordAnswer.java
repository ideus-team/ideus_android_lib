package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;

/**
 * Created by blackmamba on 05.01.17.
 */

public class ResetPasswordAnswer extends ServerAnswer<UploadFileData> {
    public ResetPasswordAnswer(UploadFileData data) {
        super(data);
    }
}
