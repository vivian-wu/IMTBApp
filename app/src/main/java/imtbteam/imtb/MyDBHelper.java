package imtbteam.imtb;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1; // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    private static final String DBName = "imtbData";  // DB name
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


    /*---------------  資料表 - 玩家資訊 START ---------------*/
    private static final String TABLE_PLAYER = "PLAYER";

    // 編號表格欄位名稱，固定不變
    public static final String PlayerID = "PlayerID ";

    // 其它表格欄位名稱
    public static final String PlayerName = "PlayerName";
        // public static final String JobNo = "JobNo";
    public static final String Status = "Status";
    /*---------------  資料表 - 玩家資訊 END  ---------------*/

    /*---------------  資料表 - 薪水紀錄 START ---------------*/
    private static final String TABLE_SALARYRECORD = "SALARYRECORD";

    // 編號表格欄位名稱，固定不變
    public static final String SRID = "SRID";

    // 其它表格欄位名稱
        //public static final String PlayerID = "PlayerID";
        //public static final String Salary = "Salary";
        //public static final String Cost = "Cost";
        //public static final String MonthCashFlow = "MonthCashFlow";
    /*---------------  資料表 - 薪水紀錄 END  ---------------*/

    /*---------------  資料表 - 現金紀錄 START ---------------*/
    private static final String TABLE_CF = "CASHFLOW";

    // 編號表格欄位名稱，固定不變
    public static final String CFID = "CFID ";

    // 其它表格欄位名稱
        //public static final String CardNo = "CardNo";
        //public static final String CardInvestNo = "CardInvestNo";
        //public static final String CardMarNo = "CardMarNo";
        //public static final String CardOrderNo = "CardOrderNo";
        //public static final String CardOtherNo = "CardOtherNo";
        //public static final String PlayerID = "PlayerID";
    public static final String CashCategory = "CashCategory";
    public static final String Amount = "Amount";
    /*---------------  資料表 - 現金紀錄 END  ---------------*/


    /*---------------  資料表 - 機會命運天使惡魔卡 START ---------------*/
    private static final String TABLE_CARD = "CARD";

    // 編號表格欄位名稱，固定不變
    public static final String CardNo = "CardNo";

    // 其它表格欄位名稱
    public static final String CardCategory = "CardCategory";
    public static final String CardContent = "CardContent";
    public static final String CashAmount = "CashAmount";
    /*---------------  資料表 - 機會命運天使惡魔卡 END  ---------------*/


    /*---------------  資料表 - 大小訂單卡 START ---------------*/
    private static final String TABLE_CARD_ORDER = "CARD_ORDER";

    // 編號表格欄位名稱，固定不變
    public static final String CardOrderNo = "CardOrderNo";

    // 其它表格欄位名稱
        // 重複的 public static final String CardCategory = "CardCategory";
        // 重複的 public static final String CardContent = "CardContent";
    public static final String FixedCost = "FixedCost";
    public static final String VariableCost = "VariableCost";
    public static final String Quantity = "Quantity";
    public static final String Price = "Price";
    /*---------------  資料表 - 大小訂單卡 END  ---------------*/


    /*---------------  資料表 - 投資理財卡 START ---------------*/
    private static final String TABLE_CARD_INVESTMENT = "CARD_INVESTMENT";

    // 編號表格欄位名稱，固定不變
    public static final String CardInvestNo = "CardInvestNo";

    // 其它表格欄位名稱
        // 重複的 public static final String CardCategory = "CardCategory";
        // 重複的 public static final String CardContent = "CardContent";
    public static final String StockName = "StockName";
    public static final String StockPrice = "StockPrice";
    public static final String Dividend = "Dividend";
    public static final String PriceRange = "PriceRange";
    /*---------------  資料表 - 投資理財卡 END  ---------------*/


    /*---------------  資料表 - 玩家的投資理財 START ---------------*/
    private static final String TABLE_PLAYER_INVESTMENT = "PLAYER_INVESTMENT";

    // 編號表格欄位名稱，固定不變
    public static final String PInvestID = "PInvestID ";

    // 其它表格欄位名稱
    // 重複的 public static final String CardCategory = "CardCategory";
    // public static final String CardMarNo = "CardNo";
        // public static final String PlayerID = "PlayerID";
        // 重複的 public static final String Quantity = "Quantity";
        // 重複的 public static final String Price = "Price";
        // public static final String Dividend = "Dividend";
    /*---------------  資料表 - 玩家的投資理財 END  ---------------*/


    /*---------------  資料表 - 市場變化卡 START ---------------*/
    private static final String TABLE_CARD_MARKET = "CARD_MARKET";

    // 編號表格欄位名稱，固定不變
    public static final String CardMarNo = "CardMarNo";

    // 其它表格欄位名稱
        // 重複的 public static final String CardCategory = "CardCategory";
        // 重複的 public static final String CardContent = "CardContent";
    public static final String VariationCategory = "VariationCategory";
    public static final String VariationRange = "VariationRange";
    /*---------------  資料表 - 市場變化卡 END  ---------------*/


    /*---------------  資料表 - 玩家的市場變化 START ---------------*/
    private static final String TABLE_PLAYER_MARKET = "PLAYER_MARKET";

    // 編號表格欄位名稱，固定不變
    public static final String PMarID = "PMarID";

    // 其它表格欄位名稱
        // 重複的 public static final String CardNo = "CardNo";
        // 重複的 public static final String CardCategory = "CardCategory";
        // 重複的 public static final String CardContent = "CardContent";
        // 重複的 public static final String VariationCategory = "VariationCategory";
        // 重複的 public static final String VariationRange = "VariationRange";
    /*---------------  資料表 - 玩家的市場變化 END  ---------------*/


    /*---------------  資料表 - 其他卡 START ---------------*/
    private static final String TABLE_CARD_OTHER = "CARD_OTHER";

    // 編號表格欄位名稱，固定不變
    public static final String CardOtherNo = "CardOtherNo";

    // 其它表格欄位名稱
        // 重複的 public static final String CardCategory = "CardCategory";
        // 重複的 public static final String CardContent = "CardContent";
    /*---------------  資料表 - 其他卡 END  ---------------*/


    // 建構子
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

        db.execSQL("CREATE TABLE " + TABLE_PLAYER + " (" +
                PlayerID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlayerName + " TEXT NOT NULL, " +
                JobNo + " TEXT NOT NULL, " +
                Status + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_SALARYRECORD + " (" +
                SRID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlayerID + " INTEGER NOT NULL, " +
                Salary + " INTEGER NOT NULL, " +
                Cost + " INTEGER  NOT NULL, " +
                MonthCashFlow + " INTEGER  NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CF + " (" +
                CFID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardNo + " TEXT NOT NULL, " +
                CardInvestNo + " TEXT NOT NULL, " +
                CardMarNo + " TEXT NOT NULL, " +
                CardOrderNo + " TEXT NOT NULL, "+
                CardOtherNo + " TEXT NOT NULL, " +
                PlayerID + " INTEGER NOT NULL, " +
                CashCategory + " TEXT NOT NULL, "+
                Amount + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD + " (" +
                CardNo + " TEXT PRIMARY KEY NOT NULL, " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                CashAmount + " INTEGER  NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_ORDER + " (" +
                CardOrderNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                FixedCost + " INTEGER NOT NULL, " +
                VariableCost + " INTEGER NOT NULL, "+
                Quantity + " INTEGER NOT NULL, " +
                Price + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_INVESTMENT + " (" +
                CardInvestNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                StockName + " TEXT NOT NULL, " +
                StockPrice + " INTEGER NOT NULL, "+
                Dividend + " INTEGER NOT NULL, " +
                PriceRange + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_PLAYER_INVESTMENT + " (" +
                PInvestID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardNo + " TEXT NOT NULL, " +
                PlayerID + " INTEGER NOT NULL, " +
                Quantity + " INTEGER NOT NULL, " +
                Price + " INTEGER NOT NULL, "+
                Dividend + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_MARKET + " (" +
                CardMarNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                VariationCategory + " INTEGER NOT NULL, " +
                VariationRange + " REAL NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_PLAYER_MARKET + " (" +
                PMarID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardNo + " TEXT NOT NULL, " +
                PlayerID + " INTEGER NOT NULL, " +
                VariationCategory + " INTEGER NOT NULL, " +
                VariationRange + " REAL NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_OTHER + " (" +
                CardOtherNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                VariationCategory + " INTEGER NOT NULL)");

    }


    // 當資料庫是較舊版時呼叫execSQL刪除資料表，並呼叫onCreate重新建立資料庫
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
