package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.SignUpData;

/**
 * Created by blackmamba on 14.12.16.
 */

public class SignUpAnswer extends ServerAnswer<SignUpData> {
    public SignUpAnswer(SignUpData data) {
        super(data);
    }
}
