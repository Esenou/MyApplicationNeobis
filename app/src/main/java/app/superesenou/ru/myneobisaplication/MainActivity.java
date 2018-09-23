package app.superesenou.ru.myneobisaplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.superesenou.ru.myneobisaplication.api.ApiServerYandex;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.text.Format;
import java.util.ArrayList;

import app.superesenou.ru.myneobisaplication.api.RetroClientTranslate;
import app.superesenou.ru.myneobisaplication.api_lang.ApiServerYandexLang;
import app.superesenou.ru.myneobisaplication.api_lang.RetroClientLang;
import app.superesenou.ru.myneobisaplication.model.Translate;
import app.superesenou.ru.myneobisaplication.modelLang.InternetConnection;
import app.superesenou.ru.myneobisaplication.modelLang.Lang;
import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    TextView txtOutput,textView;
    EditText txtInput;
    String TXT;
    String LANG="en-ru";
    Spinner spinner,spinnertwo;
    ArrayAdapter adapter;
    public ArrayList<String> LangName=new ArrayList<>();
    private View     parentView;
    public    ArrayList<String> mApps=new ArrayList<>(162);



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput=(EditText) findViewById(R.id.txtInput);
        txtOutput=(TextView)findViewById(R.id.btnOutput);
        spinner=(Spinner)findViewById(R.id.spinner);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("selected :"+LANG);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                      LANG=mApps.get(i);
                      textView.setText("selected :"+LANG);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });{
        }
        addLang();
    }


    public  void addLang(){
        if (InternetConnection.checkConnection(getApplicationContext())) {
            final ProgressDialog dialog;
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Wait");
            dialog.setMessage("Loading In Progress");
            dialog.show();
            ApiServerYandexLang api= RetroClientLang.getApiService();
            Call<Lang> call =api.getDate("/api/v1.5/tr.json/getLangs?ui=ru&key=trnsl.1.1.20180917T112749Z.15d5f544e53c6b45.3d17fa9b3d72decd54e6049b1aff68cc5b1cbbc4");
            call.enqueue(new Callback<Lang>() {
                @Override
                public void onResponse(Call<Lang> call, Response<Lang> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        mApps=response.body().getDirs();
                        //     LangName.add(1,response.body().getLangs().getAm());
                        //    LangName.add(2,response.body().getLangs().getAr());
                    }
                    else {
                        Snackbar.make(parentView,"", Snackbar.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<Lang> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Snackbar.make(parentView,"The Internet is not available", Snackbar.LENGTH_LONG).show();
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mApps);
        spinner.setAdapter(adapter);
    }
    public void onClickbtn(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                addLang();
                break;
            case R.id.btnRequest:
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("Wait");
                    dialog.setMessage("Loading In Progress");
                    dialog.show();
                    TXT= txtInput.getText().toString();
                    if(TXT.isEmpty()){
                        Toast.makeText(MainActivity.this,"Input text",Toast.LENGTH_LONG).show();
                    }
                    else {
                        ApiServerYandex apii = RetroClientTranslate.getApiService();
                        Call<Translate> calll = apii.getDate("/api/v1.5/tr.json/translate?lang=" + LANG + "&text=" + TXT + "&key=trnsl.1.1.20180917T112749Z.15d5f544e53c6b45.3d17fa9b3d72decd54e6049b1aff68cc5b1cbbc4");
                        calll.enqueue(new Callback<Translate>() {
                            @Override
                            public void onResponse(Call<Translate> call, Response<Translate> response) {
                                dialog.dismiss();
                                if (response.isSuccessful()) {
                                    String txt = String.valueOf(response.body().getText().get(0));
                                    txtOutput.setText(txt);
                                } else {
                                    Snackbar.make(parentView, "", Snackbar.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Translate> call, Throwable t) {
                                dialog.dismiss();
                            }
                        });
                    }
                } else {
                    Snackbar.make(parentView,"The Internet is not available", Snackbar.LENGTH_LONG).show();
                }

                break;
        }
    }
}
