package app.superesenou.ru.myneobisaplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import app.superesenou.ru.myneobisaplication.model.Translate;
public interface ApiServerYandex {

    @GET
    Call<Translate> getDate(@Url String name);
}
