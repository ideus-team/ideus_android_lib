package biz.ideus.ideuslibexample.injection.components;

import android.content.Context;
import android.content.res.Resources;

import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.injection.modules.AppModule;
import biz.ideus.ideuslibexample.injection.modules.DataModule;
import biz.ideus.ideuslibexample.injection.modules.NetModule;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import dagger.Component;

/**
 * Created by user on 10.11.2016.
 */

@PerApplication
@Component(modules={AppModule.class , NetModule.class, DataModule.class})
public interface AppComponent {
    @AppContext
    Context context();
    Resources resources();
    RequeryApi dataApi();
    NetApi netApi();
}
