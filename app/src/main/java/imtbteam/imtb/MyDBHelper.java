package imtbteam.imtb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1; // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    private static final String DBName = "imtbData.db";  // DB name
    private static SQLiteDatabase database; // 資料庫物件，固定的欄位變數
    private static final String DATABASE_TABLE = "JOB";

    public MyDBHelper(Context context){
        super(context, DBName, null, DBVersion);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MyDBHelper(context, DBName,
                    null, DBVersion).getWritableDatabase();
        }

        return database;
    }

    //參數為 SQLiteDatabase 資料庫物件 db，並呼叫execSQL方法建立資料表
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
