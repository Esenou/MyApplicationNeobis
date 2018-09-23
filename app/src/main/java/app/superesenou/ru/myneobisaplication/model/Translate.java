package app.superesenou.ru.myneobisaplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Translate {


    private String lang;

    private List text;

    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

    public List getText() {
        return text;
    }

    public void setText(List text) {
        this.text = text;
    }
}