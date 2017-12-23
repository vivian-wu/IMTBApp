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

        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C001', '機會', '發票中獎 200 元', 200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C002', '機會', '發票中獎 500 元', 500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C003', '機會', '發票中獎 800 元', 800)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C004', '機會', '發票中獎 1,000 元', 1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C005', '機會', '收到小費  1,500 元', 1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C006', '機會', '收到小費  3,000 元', 3000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C007', '機會', '出差暫停 一次，但額外獲得 500 元', 500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C008', '機會', '兼差賺外快獲得 1,500 元', 1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C009', '機會', '地上撿到 100 元', 100)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C010', '機會', '不幸被狗咬，倒退 1 格', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C011', '機會', '感染流行性感冒，暫停 1 回', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C012', '機會', '被同事感染重感冒，暫停 2 回', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C013', '機會', '路上被狗追，前進 2 格', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C014', '機會', '參加二手拍賣，賺了3,000 元', 3000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C015', '機會', '參加馬拉松，得到獎金 1,000 元', 1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C016', '機會', '工作當月全勤，獲得 1,000 元', 1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C017', '機會', '颱風來襲，休息 1 回', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C018', '機會', '在路上租UBIKE，前進 5 格', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C019', '機會', '參加大胃王比賽，獲得 獎金 500 元，但同時得腸胃炎醫藥費 1,000 元', -500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C020', '機會', '國外發生大地震，捐贈 3,000 元', -3000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C021', '機會', '國外發生大海嘯，捐贈 3,500 元', -3500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C022', '機會', '參加資助兒童計畫，捐贈 700 元', -700)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C023', '機會', '收到生日紅包 2,000 元', 2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C024', '機會', '收到好友紅色炸彈損失 3,000 元', -3000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C025', '機會', '工作表現優良，老闆當月加薪 1,500 元', 1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C026', '機會', '額外兼差賺入 2,000 元', 2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C027', '機會', '遇到超級折扣日，花費 1,500 元', -1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C028', '機會', '獲得 業主全額贊助創業基金，並跳出「累積資本圈」', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C029', '機會', '上班認真主管加薪本月薪水 5 ％', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C030', '機會', '獲得年終獎金 一個月', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C031', '命運', '路上被狗咬，自己支付醫藥費 500 元', -500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C032', '命運', '出車禍醫藥費 1,500 元', -1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C033', '命運', '中樂透 20,000 元', 20000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C034', '命運', '刮刮樂中 100 元', 100)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C035', '命運', '手機掉到水溝，購買新手機 9,900 元', -9900)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C036', '命運', '同事結婚包紅包 1,200 元', -1200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C037', '命運', '下班後追星，演唱會門票 800 元', -800)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C038', '命運', '下班休閒活動，看球賽 200 元', -200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C039', '命運', '父母生日包紅包支付 6,000 元', -6000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C040', '命運', '上班途中遭搶劫 2,000 元', -2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C041', '命運', '颱風天放假，暫停 一次', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C042', '命運', '同事出車禍，探訪同事買水果禮盒 1,200 元', -1200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C043', '命運', '同事報樂透明牌，中獎 5,000 元', 5000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C044', '命運', '上班交通工具亂停車遭開單支付 800 元', -800)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C045', '命運', '交通工具拋錨支付修理費 1,000 元', -1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C046', '命運', '路上撿到 6,600 元', 6600)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C047', '命運', '出重大車禍，醫藥費 2,000 元並在家養傷暫停三次', -2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C048', '命運', '上班偷懶被主管抓到，主管扣本月薪水 5 ％', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C049', '命運', '上班認真主管加薪本月薪水 10 ％', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C050', '命運', '重感冒無法上班，損失月收入一成', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C051', '天使', '合作夥伴增資 3,000 元', 3000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C052', '天使', '募資獲得 30,000 元', 30000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C053', '天使', '意外獲得鼓勵獎金 2,000 元', 2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C054', '天使', '獲得企業贊助，當月收入增加  1,500 元', 1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C055', '天使', '發票兌獎獲得 1,000 元', 1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C056', '天使', '發票兌獎獲得 200 元', 200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C057', '天使', '路上撿到 1,000 元', 1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C058', '天使', '總統發放購物金，每位玩家可獲得 2,000 元', 2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C059', '天使', '向其他企業拉贊助，每位玩家必須支付 5,000 元給該玩家', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C060', '天使', '補助金，向每位玩家收取 500 元', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C061', '天使', '補助金，向每位玩家收取 1,000 元', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C062', '天使', '吸取距離最近玩家身上 10 ％現金', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C063', '天使', '吸取距離最近玩家身上 5 ％現金', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C064', '天使', '獲得 企業在助，每月收入增加  500 元', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C065', '天使', '增加  當月薪水 20 ％現金', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C066', '惡魔', '員工洩漏機密，造成公司損失 30,000 元', -30000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C067', '惡魔', '店裡機器毀損，支付維修費 1,500 元', -1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C068', '惡魔', '員工意外受傷需休養一個，因人手不足導致顧客流失，當月收入損失 2,000 元', -2000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C069', '惡魔', '遇到奧客棄單，損失 1,000 元', -1000)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C070', '惡魔', '例行員工聚餐損失 1,500 元', -1500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C071', '惡魔', '錢包遺失 500 元', -500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C072', '惡魔', '創業太勞累，醫藥費支付300 元', -300)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C073', '惡魔', '上班途中發生意外，損失 500 元', -500)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C074', '惡魔', '同事家有喜事，支付交際費 300 元', -300)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C075', '惡魔', '因熬夜加班勞累住院，支付資療費 1,200 元，並暫停 一次', -1200)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C076', '惡魔', '被政府課稅，本月營業額減少 500 元', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C077', '惡魔', '損失當月薪水 20 ％現金', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C078', '惡魔', '生財器具損壞，當月營業額損失 2 ％', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C079', '惡魔', '水電費 調漲，每位玩家每月支出多 500 元', 0)");
        db.execSQL("INSERT INTO CARD_CARD (CardNo, CardCategory, CardContent, CashAmount) values( 'C080', '惡魔', '颱風天，公司無法正常營業，所有玩家當月收入減少 200 元', 0)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_ORDER + " (" +
                CardOrderNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                FixedCost + " INTEGER NOT NULL, " +
                VariableCost + " INTEGER NOT NULL, "+
                Quantity + " INTEGER NOT NULL, " +
                Price + " INTEGER NOT NULL)");

        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C116', '小訂單', '今有一筆訂單，需生產 10 單位產品，總固定成本：1,500 元，總變動：2,000 元，每 單位商品售價：40 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1500, 2000, 10, 40)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C117', '小訂單', '今有一筆訂單，需生產 12 單位產品，總固定成本：1,000 元，總變動：2,000 元，每 單位商品售價：45 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1000, 2000, 12, 45)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C118', '小訂單', '今有一筆訂單，需生產 15 單位產品，總固定成本：1,700 元，總變動：1,800 元，每 單位商品售價：35 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1700, 1800, 15, 35)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C119', '小訂單', '今有一筆訂單，需生產 17 單位產品，總固定成本：2,000 元，總變動：2,000 元，每 單位商品售價：42 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 2000, 2000, 17, 42)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C120', '小訂單', '今有一筆訂單，需生產 5 單位產品，總固定成本：600 元，總變動：1,000 元，每 單位商品售價：10 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 600, 1000, 5, 10)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C121', '小訂單', '今有一筆訂單，需生產 6 單位產品，總固定成本：500 元，總變動：1,000 元，每 單位商品售價：12 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 500, 1000, 6, 12)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C122', '小訂單', '今有一筆訂單，需生產 4 單位產品，總固定成本：800 元，總變動：500 元，每 單位商品售價：13 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 800, 500, 4, 13)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C123', '小訂單', '今有一筆訂單，需生產 10 單位產品，總固定成本：1,200 元，總變動：550 元，每 單位商品售價：6 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1200, 550, 10, 6)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C124', '小訂單', '今有一筆訂單，需生產 10 單位產品，總固定成本：800 元，總變動：700 元，每 單位商品售價：15 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 800, 700, 10, 15)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C125', '小訂單', '今有一筆訂單，需生產 10 單位產品，總固定成本：1,500 元，總變動：500 元，每 單位商品售價：20 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1500, 500, 10, 20)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C126', '小訂單', '今有一筆訂單，需生產 12 單位產品，總固定成本：900 元，總變動：420 元，每 單位商品售價：11 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 900, 420, 12, 11)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C127', '小訂單', '今有一筆訂單，需生產 20 單位產品，總固定成本：1,300 元，總變動：1,300 元，每 單位商品售價：13 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1300, 1300, 20, 13)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C128', '小訂單', '今有一筆訂單，需生產 20 單位產品，總固定成本：1,000 元，總變動：3,000 元，每 單位商品售價：10 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1000, 3000, 20, 10)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C129', '小訂單', '今有一筆訂單，需生產 18 單位產品，總固定成本：2,000 元，總變動：2,000 元，每 單位商品售價：20 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 2000, 2000, 18, 20)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C130', '小訂單', '今有一筆訂單，需生產 21 單位產品，總固定成本：1,000 元，總變動：1,500 元，每 單位商品售價：12 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 1000, 1500, 21, 12)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C131', '大訂單', '今有一筆訂單，需生產 100 單位產品，總固定成本：15,000 元，總變動：20,000 元，每 單位商品售價：400 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 15000, 20000, 100, 400)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C132', '大訂單', '今有一筆訂單，需生產 125 單位產品，總固定成本：10,000 元，總變動：20,000 元，每 單位商品售價：450 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 10000, 20000, 125, 450)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C133', '大訂單', '今有一筆訂單，需生產 150 單位產品，總固定成本：17,000 元，總變動：18,000 元，每 單位商品售價：350 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 17000, 18000, 150, 350)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C134', '大訂單', '今有一筆訂單，需生產 175 單位產品，總固定成本：20,000 元，總變動：20,000 元，每 單位商品售價：420 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 20000, 20000, 175, 420)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C135', '大訂單', '今有一筆訂單，需生產 50 單位產品，總固定成本：6,000 元，總變動：10,000 元，每 單位商品售價：100 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 6000, 10000, 50, 100)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C136', '大訂單', '今有一筆訂單，需生產 60 單位產品，總固定成本：5,000 元，總變動：10,000 元，每 單位商品售價：120 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 5000, 10000, 60, 120)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C137', '大訂單', '今有一筆訂單，需生產 40 單位產品，總固定成本：8,000 元，總變動：5,000 元，每 單位商品售價：130 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 8000, 5000, 40, 130)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C138', '大訂單', '今有一筆訂單，需生產 100 單位產品，總固定成本：12,000 元，總變動：5,500 元，每 單位商品售價：60 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 12000, 5500, 100, 60)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C139', '大訂單', '今有一筆訂單，需生產 100 單位產品，總固定成本：8,000 元，總變動：7,000 元，每 單位商品售價：150 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 8000, 7000, 100, 150)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C140', '大訂單', '今有一筆訂單，需生產 100 單位產品，總固定成本：15,000 元，總變動：5,000 元，每 單位商品售價：200 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 15000, 5000, 100, 200)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C141', '大訂單', '今有一筆訂單，需生產 120 單位產品，總固定成本：9,000 元，總變動：4,200 元，每 單位商品售價：110 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 9000, 4200, 120, 110)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C142', '大訂單', '今有一筆訂單，需生產 200 單位產品，總固定成本：13,000 元，總變動：13,000 元，每 單位商品售價：130 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 13000, 13000, 200, 130)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C143', '大訂單', '今有一筆訂單，需生產 200 單位產品，總固定成本：10,000 元，總變動：30,000 元，每 單位商品售價：170 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。 ', 10000, 30000, 200, 170)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C144', '大訂單', '今有一筆訂單，需生產 180 單位產品，總固定成本：20,000 元，總變動：20,000 元，每 單位商品售價：200 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 20000, 20000, 180, 200)");
        db.execSQL("INSERT INTO CARD_ORDER (CardOrderNo, CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price) values( 'C145', '大訂單', '今有一筆訂單，需生產 210 單位產品，總固定成本：10,000 元，總變動：15,000 元，每 單位商品售價：120 元，玩家可選擇是否接此訂單。如放棄訂單，依舊須支付固定成本。', 10000, 15000, 210, 120)");


        db.execSQL("CREATE TABLE " + TABLE_CARD_INVESTMENT + " (" +
                CardInvestNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL, " +
                StockName + " TEXT NOT NULL, " +
                StockPrice + " INTEGER NOT NULL, "+
                Dividend + " INTEGER NOT NULL, " +
                PriceRange + " TEXT NOT NULL)");

        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C081', '投資理財', '市場不佳導致此家股票走跌，你可以以此價購買任意數量的 MIT 股票，其他人可以以此價出售該票。', 'MIT 股票', 10, 0, '10 ~ 30')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C082', '投資理財', '市場穩定導致此家股票上漲，你可以以此價購買任意數量的 MIT 股票，其他人可以以此價出售該股票。', 'MIT 股票', 25, 0, '10 ~ 30')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C083', '投資理財', '市場熱絡導致此家股票上漲，你可以以此價購買任意數量的 MIT 股票，其他人可以以此價出售該股票。', 'MIT 股票', 30, 0, '10 ~ 30')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C084', '投資理財', '市場穩定此家股票變回 20 元，你可以以此價購買任意數量的 MIT 股票，其他人可以以此價出售該股票。', 'MIT 股票', 20, 0, '10 ~ 30')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C085', '投資理財', '強勁的市場把基金價格推到最高價，你可以以此價購買任意數量的 Apple 共同基金，其他人可以以此價出售該股票。', 'Apple 共同基金', 50, 0, '5 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C086', '投資理財', '強勁的市場把基金價格推到高價，你可以以此價購買任意數量的 Apple 共同基金，其他人可以以此價出售該股票。', 'Apple 共同基金', 35, 0, '5 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C087', '投資理財', '低迷的市場使得基金價格跌落谷底，你可以以此價購買任意數量的 Apple 共同基金，其他人可以以此價出售該股票。', 'Apple 共同基金', 5, 0, '5 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C088', '投資理財', '穩動的市場使得基金價格上漲，你可以以此價購買任意數量的 Apple 共同基金，其他人可以以此價出售該股票。', 'Apple 共同基金', 20, 0, '5 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C089', '投資理財', '市場不佳導致此家股票走跌，你可以以此價購買任意數量的 Bread 股票，其他人可以以此價出售該股票。', 'Bread 股票', 20, 2, '15 ~ 60')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C090', '投資理財', '市場穩動導致此家股票上漲，你可以以此價購買任意數量的 Bread 股票，其他人可以以此價出售該股票。', 'Bread 股票', 45, 0, '15 ~ 60')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C091', '投資理財', '市場穩動導致此家股票上漲，你可以以此價購買任意數量的 Bread 股票，其他人可以以此價出售該股票。', 'Bread 股票', 30, 0, '15 ~ 60')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C092', '投資理財', '市場穩動導致此家股票上漲，你可以以此價購買任意數量的 Bread 股票，其他人可以以此價出售該股票。', 'Bread 股票', 50, 2, '15 ~ 60')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C093', '投資理財', '受食安風暴影響，此家股票股價下跌，你可以以此價購買任意數量的 Market 股票，其他人可以以此價出售該股票。', 'Market 股票', 10, 0, '10 ~ 40')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C094', '投資理財', '受經濟蕭條影響，此家股票股價下跌，你可以以此價購買任意數量的 Market 股票，其他人可以以此價出售該股票。', 'Market 股票', 15, 0, '10 ~ 40')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C095', '投資理財', '股票市場熱絡，此家股票股價上漲，你可以以此價購買任意數量的 Market 股票，其他人可以以此價出售該股票。', 'Market 股票', 20, 0, '10 ~ 40')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C096', '投資理財', '股票市場熱絡，此家股票股價上漲，你可以以此價購買任意數量的 Market 股票，其他人可以以此價出售該股票。', 'Market 股票', 30, 0, '10 ~ 40')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C097', '投資理財', '基金市場熱絡， Bee 基金價格上漲，你可以以此價購買任意數量的 Bee 基金，其他人可以以此價出售該基金。', 'Bee 基金', 40, 0, '20 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C098', '投資理財', '基金市場熱絡， Bee 基金價格來到 20 元，你可以以此價購買任意數量的 Bee 基金，其他人可以以此價出售該基金。', 'Bee 基金', 20, 0, '20 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C099', '投資理財', '強勁的市場把基金價格推到最高價，你可以以此價購買任意數量的 Bee 共同基金，其他人可以以此價出售該股票。', 'Bee 基金', 50, 0, '20 ~ 50')");
        db.execSQL("INSERT INTO CARD_INVESTMENT (CardInvestNo, CardCategory, CardContent, StockName, StockPrice, Dividend, PriceRange) values( 'C100', '投資理財', '基金市場熱絡， Bee 基金價格來到 30 元，你可以以此價購買任意數量的 Bee 基金，其他人可以以此價出售該基金。', 'Bee 基金', 30, 0, '20 ~ 50')");

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

        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C101', '市場變化', '新技術問世，需花費十萬元引進新技術，日後生產成本降低10%。', '成本', -0.1)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C102', '市場變化', '「一例一休」上路，工資成本上漲5%。', '成本', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C103', '市場變化', '政府提高稅收，生產成本增加5%。', '成本', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C104', '市場變化', '政府頒布新政策，稅收降低5%。', '成本', -0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C105', '市場變化', '原物料價格上漲，生產成本增加5%。', '成本', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C106', '市場變化', '本國頒布新政策，加收進口原物料關稅，生產成本增加3%。', '成本', 0.03)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C107', '市場變化', '政府補助產業，獲得3個月補助金，每個月5萬元。', '成本', 0)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C108', '市場變化', '全球發生金融風暴，消費市場疲軟不振，收入降低15%。', '收入', -0.15)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C109', '市場變化', '政府鼓勵外國遊客來台觀光，收入增加5%。', '收入', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C110', '市場變化', '消費者喜好轉變，產品銷量降低10%。', '銷量', -0.1)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C111', '市場變化', '政府簽訂經濟合作協定，產品銷量增加5%。', '銷量', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C112', '市場變化', '市場出現新銷售模式，需花費12萬來建立新銷售模式，日後產品銷量增加10%。', '銷量', 0.1)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C113', '市場變化', '消費市場熱絡，產品銷量增加5%。', '銷量', 0.05)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C114', '市場變化', '消費市場冷漠，產品銷量減少7%。', '銷量', -0.07)");
        db.execSQL("INSERT INTO CARD_MARKET (CardMarNo, CardCategory, CardContent, VariationCategory, VariationRange) values( 'C115', '市場變化', '政府為刺激消費發放消費券，產品銷量增加8%。', '銷量', 0.08)");


        db.execSQL("CREATE TABLE " + TABLE_PLAYER_MARKET + " (" +
                PMarID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardNo + " TEXT NOT NULL, " +
                PlayerID + " INTEGER NOT NULL, " +
                VariationCategory + " INTEGER NOT NULL, " +
                VariationRange + " REAL NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CARD_OTHER + " (" +
                CardOtherNo + " TEXT PRIMARY KEY , " +
                CardCategory + " TEXT NOT NULL, " +
                CardContent + " TEXT NOT NULL)");

        db.execSQL("INSERT INTO CARD_OTHER (CardOtherNo, CardCategory, CardContent) values( 'F01', '薪水', '獲得本月薪水～')");
        db.execSQL("INSERT INTO CARD_OTHER (CardOtherNo, CardCategory, CardContent) values( 'F02', '脫魯', '恭喜您獲得男(女)朋友！如重複走到每月開銷增加，一人限走三次。')");
        db.execSQL("INSERT INTO CARD_OTHER (CardOtherNo, CardCategory, CardContent) values( 'F03', '結婚', '恭喜您與您的伴侶結婚啦！其他玩家每人必須支付紅包 1200 元，如重複走到則重新結婚一次，費用需再支付一次，一人限走三次。')");

    }


    // 當資料庫是較舊版時呼叫execSQL刪除資料表，並呼叫onCreate重新建立資料庫
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
