package app.superesenou.ru.myneobisaplication.modelLang;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Lang {
    @SerializedName("dirs")
    private ArrayList<String > dirs;



    @SerializedName("langs")
    private LangText langs;

    public LangText getLangs() {
        return langs;
    }

    public void setLangs(LangText langs) {
        this.langs = langs;
    }



    public ArrayList<String> getDirs() {
        return dirs;
    }

    public void setDirs(ArrayList<String> dirs) {
        this.dirs = dirs;
    }



}
