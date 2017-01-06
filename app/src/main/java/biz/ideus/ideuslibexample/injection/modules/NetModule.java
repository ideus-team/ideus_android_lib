package biz.ideus.ideuslibexample.injection.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslibexample.BuildConfig;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import biz.ideus.ideuslibexample.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
@Module
public class NetModule {

    public static final String URL = "http://46.101.254.89/";
    public static final String API_VERSION = "api/v1/";
    //http://46.101.254.89/api/v1/user/login

    @Provides
    @PerApplication
    static Gson provideGson() {
        return new GsonBuilder()
                // Custom type adapters for models are not needed when using Gson, but this
                // type adapter is a good example if you want to write one yourself.
//                .registerTypeAdapter(Country.class, CountryTypeAdapter.INSTANCE)
                // These type adapters for RealmLists are needed, since RealmString and RealmStringMapEntry
                // wrappers are not recognized by Gson in the default configuration.
//                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>(){}.getType(), RealmStringListTypeAdapter.INSTANCE)
//                .registerTypeAdapter(new TypeToken<RealmList<RealmStringMapEntry>>(){}.getType(), RealmStringMapEntryListTypeAdapter.INSTANCE)
                .create();
    }

    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @PerApplication
    static NetApi provideCountryApi(Gson gson, OkHttpClient okHttpClient) {
        OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient defaultHttpClient = httpClientBuilder.addInterceptor(
                chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("lang", "en")
                            .addHeader("Device-Platform", "android")
                            .addHeader("Application-Name", "IdeusLibExample")
                            .addHeader("Api-Token", (Hawk.get(Constants.USER_TOKEN))).build();
                    return chain.proceed(request);
                }).build();


        return new Retrofit.Builder()
                .baseUrl(URL + API_VERSION)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(defaultHttpClient)
                .build().create(NetApi.class);
    }
}
