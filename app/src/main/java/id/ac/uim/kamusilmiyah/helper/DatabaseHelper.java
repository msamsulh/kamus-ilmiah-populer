package id.ac.uim.kamusilmiyah.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "kamus_ilmiah.db";

    public static String TABLE_KAMUS = "kamus_ilmiah";

    public static String FIELD_ID = "id";
    public static String FIELD_WORD = "word";
    public static String FIELD_TRANSLATE = "translate";

    public static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_KAMUS = "create table " + TABLE_KAMUS + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_WORD + " text not null, " +
            FIELD_TRANSLATE + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAMUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KAMUS);
        onCreate(db);
    }
}
