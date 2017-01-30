package biz.ideus.ideuslibexample.data.model.response;

import biz.ideus.ideuslibexample.data.model.response.data.MessagesData;

/**
 * Created by blackmamba on 30.01.17.
 */

public class MessagesResponse extends ServerAnswer<MessagesData> {
    public MessagesResponse(MessagesData data) {
        super(data);
    }
}
