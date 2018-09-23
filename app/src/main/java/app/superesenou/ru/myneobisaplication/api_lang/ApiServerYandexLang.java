package app.superesenou.ru.myneobisaplication.api_lang;

import app.superesenou.ru.myneobisaplication.modelLang.Lang;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiServerYandexLang {

    @GET
    Call<Lang> getDate(@Url String name);
}
