package imtbteam.imtb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1; //版本
    private static final String DBName = "imtbData.db";  // Bb name
    private final static String TableName = "JobContent"; //Table name

    public MyDBHelper(Context context){
        super(context, DBName, null, DBVersion);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE  TABLE CashChanges" +
                "(CashChanges_id INTEGER PRIMARY KEY  NOT NULL , " +
                "cdate DATETIME NOT NULL , " +
                "Cardinfo VARCHAR, " +
                "numeric INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
