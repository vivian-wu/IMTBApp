package imtbteam.imtb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class JobDAO {

    // 表格名稱
    public static final String TABLE_NAME = "JOB";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "JobNo";

    // 其它表格欄位名稱
    public static final String JobNo = "JobNo";
    public static final String Job = "Job";
    public static final String Salary = "Salary";
    public static final String Cost = "Cost";
    public static final String MonthCashFlow = "MonthCashFlow";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    JobNo + " TEXT PRIMARY KEY NOT NULL, " +
                    Job + " TEXT NOT NULL, " +
                    Salary + " INTEGER NOT NULL, " +
                    Cost + " INTEGER  NOT NULL, " +
                    MonthCashFlow + " INTEGER  NOT NULL)";


    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public JobDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }





}
