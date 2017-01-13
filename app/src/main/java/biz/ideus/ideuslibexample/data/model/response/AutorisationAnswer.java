package biz.ideus.ideuslibexample.data.model.response;


import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;

/**
 * Created by blackmamba on 14.12.16.
 */

public class AutorisationAnswer extends ServerAnswer<AutorisationEntity> {
    public AutorisationAnswer(AutorisationEntity data) {
        super(data);
    }
}
