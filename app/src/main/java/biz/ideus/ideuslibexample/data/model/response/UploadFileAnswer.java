package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;

/**
 * Created by blackmamba on 21.12.16.
 */

public class UploadFileAnswer extends ServerAnswer<UploadFileData> {
    public UploadFileAnswer(UploadFileData data) {
        super(data);
    }
}
