package imtbteam.imtb;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1; // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    private static final String DBName = "imtbData.db";  // DB name
    private static SQLiteDatabase database; // 資料庫物件，固定的欄位變數

    /*---------------  資料表 - 職業 START ---------------*/
         private static final String TABLE_JOB = "JOB";

         // 編號表格欄位名稱，固定不變
         public static final String JobNo = "JobNo";

         // 其它表格欄位名稱
         public static final String Job = "Job";
         public static final String Salary = "Salary";
         public static final String Cost = "Cost";
         public static final String MonthCashFlow = "MonthCashFlow";

    /*---------------  資料表 - 職業 END  ---------------*/


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
        db.execSQL("CREATE TABLE " + TABLE_JOB + " (" +
                        JobNo + " TEXT PRIMARY KEY NOT NULL, " +
                        Job + " TEXT NOT NULL, " +
                        Salary + " INTEGER NOT NULL, " +
                        Cost + " INTEGER  NOT NULL, " +
                        MonthCashFlow + " INTEGER  NOT NULL)");

        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J01', '警衛', 32300, 27455, 4845)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J02', '護士', 65300, 55505, 9795)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J03', '美髮師', 27000, 22950, 4045)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J04', '工程師', 60200, 51170, 9030)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J05', '小學老師', 48200, 40970, 7230)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J06', '律師', 58500, 49725, 8775)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J07', '秘書', 47200, 40120, 7080)");
        db.execSQL("INSERT INTO JOB (JobNo, Job, Salary, Cost, MonthCashFlow) values( 'J08', '卡車司機', 34800, 29580, 5220)");
    }


    // 當資料庫是較舊版時呼叫execSQL刪除資料表，並呼叫onCreate重新建立資料庫
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
