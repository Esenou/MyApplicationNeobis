package app.superesenou.ru.myneobisaplication.api_lang;

import app.superesenou.ru.myneobisaplication.api.ApiServerYandex;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientLang {

    private static final String BASE_URL="https://translate.yandex.net";


    //get Retrofit instance

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServerYandexLang getApiService(){
        return getRetrofitInstance().create(ApiServerYandexLang.class);
    }
}
