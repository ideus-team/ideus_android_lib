package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.LoginData;

/**
 * Created by user on 12.12.2016.
 */

public class LoginAnswer extends ServerAnswer<LoginData> {
    public LoginAnswer(LoginData data) {
        super(data);
    }
}
