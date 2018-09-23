package app.superesenou.ru.myneobisaplication.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientTranslate {

    private static final String BASE_URL="https://translate.yandex.net";


    //get Retrofit instance

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

public static ApiServerYandex getApiService(){
        return getRetrofitInstance().create(ApiServerYandex.class);
}

}
