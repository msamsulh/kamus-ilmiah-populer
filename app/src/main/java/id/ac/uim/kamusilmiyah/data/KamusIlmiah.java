package id.ac.uim.kamusilmiyah.data;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusIlmiah implements Parcelable {

    private int id;
    private String word;
    private String translate;

    public KamusIlmiah() {
    }

    public KamusIlmiah(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(translate);
    }

    protected KamusIlmiah(Parcel in) {
        id = in.readInt();
        word = in.readString();
        translate = in.readString();
    }

    public static final Creator<KamusIlmiah> CREATOR = new Creator<KamusIlmiah>() {
        @Override
        public KamusIlmiah createFromParcel(Parcel in) {
            return new KamusIlmiah(in);
        }

        @Override
        public KamusIlmiah[] newArray(int size) {
            return new KamusIlmiah[size];
        }
    };
}
