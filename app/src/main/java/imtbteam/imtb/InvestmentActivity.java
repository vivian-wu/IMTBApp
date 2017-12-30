package imtbteam.imtb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

public class InvestmentActivity extends AppCompatActivity
       {

    private EditText edAmount;
    private RadioButton buy;
    private RadioButton sell;
    private Button add;
    private TextView text_stockname,text_price,text_div,text_pricerange,text_stock;
    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    private String cardno_string;
    private int cardno_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        Bundle bundle = getIntent().getExtras(); // 利用 bundle 獲得掃到的 CardID
        cardno_string = bundle.getString("id");

        dbHelper = new MyDBHelper(this);   // 打開資料庫
        db = dbHelper.getWritableDatabase();

        edAmount = (EditText) findViewById(R.id.inv_ed_amount);
        RadioGroup rg = (RadioGroup)findViewById(R.id.inv_radioGroup);
        buy = (RadioButton) findViewById(R.id.inv_rbt_buy);
        sell = (RadioButton) findViewById(R.id.inv_rbt_sell);
        add = (Button)  findViewById(R.id.inv_btn_add);
        text_stockname = (TextView) findViewById(R.id.inv_text_stockname);
        text_price = (TextView) findViewById(R.id.inv_text_price);
        text_div = (TextView) findViewById(R.id.inv_text_div);
        text_pricerange = (TextView) findViewById(R.id.inv_text_pricerange);
        text_stock = (TextView) findViewById(R.id.inv_text_stocks);
        String x=Set_StockContent(cardno_string);
        String OwnStock=Set_OwnStock(cardno_string);

      //  cardno_int = Integer.parseInt(cardno_string.substring(1));
        //沒有抽到卡片的股票就只能買進
        if(OwnStock=="x"){
            sell.setVisibility(View.INVISIBLE);
        }
        this.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SAmount =String.valueOf(edAmount.getText());
                String Status;
                int Amount=0;
                if(SAmount!= "0"){
                    Amount= Integer.parseInt(SAmount);
                }
                if(buy.isChecked()){    //買入股票
                    Status="";
                    Status=BuyStock(cardno_string,Amount);
                    if(Status=="y"){
                        ShowMsgDialog_Success("投資理財","購買成功！","　好耶！　");
                        db.close();
                    }

                    else if(Status=="x"){
                        ShowMsgDialog_Fail("投資理財","持有金額不足購買股票","　噢不！　");
                    }
                    else{
                        ShowMsgDialog_Fail("投資理財","輸入錯誤"+Status,"　確定　");
                    }
                }
                //賣出股票
                else if(sell.isChecked()){
                    Status="";
                    Status=SellStock(cardno_string,Amount);
                    if(Status=="y"){
                        ShowMsgDialog_Success("投資理財","成功賣出！","　好耶！　");
                        db.close();
                    }

                    else if(Status=="x"){
                        ShowMsgDialog_Fail("投資理財","持有股票數量不足，請重新輸入！","　噢不～　");
                    }
                    else{
                        ShowMsgDialog_Fail("投資理財","輸入錯誤"+Status,"　確定　");
                    }
                }

            }
        });
        //db.close();
    }
    private void ShowMsgDialog_Fail(String title ,String Msg,String btnText)
       {
           AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
           MyAlertDialog.setTitle(title);
           MyAlertDialog.setMessage(Msg);
           MyAlertDialog.setCancelable(false);

           MyAlertDialog.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

               }
           });

           MyAlertDialog.show();
       }

           private void ShowMsgDialog_Success(String title ,String Msg,String btnText)
           {
               AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
               MyAlertDialog.setTitle(title);
               MyAlertDialog.setMessage(Msg);
               MyAlertDialog.setCancelable(false);

               MyAlertDialog.setPositiveButton("　確定！　", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {


                       Intent intent = new Intent();
                       intent.setClass(InvestmentActivity.this, MainActivity.class);
                       startActivity(intent);

                   }
               });

               MyAlertDialog.show();
           }

    public void add(View v){

        //int cardno = Integer.parseInt(cardID.substring(2));

        if (cardno_int <10)
        {
            if (edAmount != null)
            {
                int amount = Integer.parseInt(edAmount.getText().toString());
            }

        }
        else
        {
            Toast.makeText(InvestmentActivity.this, "請輸入金額", Toast.LENGTH_SHORT).show();
        }

    }
    public String Set_StockContent(String CardNo){


        Cursor cursorStock = db.rawQuery("Select StockName,StockPrice,Dividend,PriceRange from CARD_INVESTMENT Where CardInvestNo='"+CardNo+"'", null);
        if (cursorStock.getCount() != 0){
            cursorStock.moveToLast();
            do {
                text_stockname.setText(cursorStock.getString(0));
                text_price.setText(cursorStock.getString(1));
                text_div.setText(cursorStock.getString(2));
                text_pricerange.setText(cursorStock.getString(3));
                Log.d("掃描股票資料:", CardNo);
            } while (cursorStock.moveToNext());
        }
        else {
            return "";
        }
        cursorStock.close();
        return"";
    }

    public String Set_OwnStock(String CardNo){


        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.getCount() != 0){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d(" playerID:", id);
            } while (cursorid.moveToNext());
        }
        else {
            return "";
        }
        cursorid.close();

        String StockName;
        int quantity;
        int total;
        double price;
        Cursor cursorStock = db.rawQuery("Select t2.StockName,Sum(Quantity*Price),SUM(Quantity) " +
                "From PLAYER_INVESTMENT t1 " +
                "INNER JOIN CARD_INVESTMENT t2 ON t1.CardNo=t2.CardInvestNo " +
                "WHERE PlayerID='"+id+"' " +
                "AND t2.StockName=( " +
                    "Select StockName " +
                    "From CARD_INVESTMENT " +
                    "WHERE CardInvestNo='"+CardNo+"' " +
                ")", null);
        cursorStock.moveToLast();
        if (cursorStock.getInt(2)!=0){  //有數量的時候
            do {
                StockName = cursorStock.getString(0);
                total=cursorStock.getInt(1);
                quantity=cursorStock.getInt(2);
                price=(double)total/(double)quantity;
                Log.d(" StockName:", StockName+"數量:"+quantity+"金額:"+price);
                String temp="　名稱　　　　價格　　數量\n"+StockName+"　 　　"+String.valueOf(price)+"　　　　"+String.valueOf(quantity);
                text_stock.setText(temp);

            } while (cursorStock.moveToNext());
        }
        else {
            text_stock.setText("　名稱　　　　價格　　數量");
            return "x";
        }
        cursorStock.close();


        return"y";
    }

    public String BuyStock(String CardNo,int quantity){
        //尋找玩家ID>玩家現在擁有的現金-->
        // 買進時候數量*價錢不可以>現金  a.成功買入(回到開始頁)(寫到現金流量表) b.失敗(重新輸入數量)


        String id = "";
        Cursor cursorid_buy = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid_buy.getCount() != 0){
            do {
                cursorid_buy.moveToLast();
                id = cursorid_buy.getString(0);
                Log.d(" playerID:", id);
            } while (cursorid_buy.moveToNext());
        }
        else {
            return "";
        }
        cursorid_buy.close();

        int cash ;

        Cursor cursorcash = db.rawQuery("Select Sum(Amount) from CASHFLOW where PlayerID='"+id+"'", null);
        if (cursorcash.getCount() != 0){
            do {
                cursorcash.moveToLast();
                cash = cursorcash.getInt(0);
                Log.d(" HasCash:", String.valueOf(cash));
            } while (cursorcash.moveToNext());
        }
        else {
            return "";
        }
        cursorcash.close();

        int price;
        int dividend;
        Cursor cursorStock = db.rawQuery("Select StockPrice,Dividend  from CARD_INVESTMENT WHERE CardInvestNo='"+CardNo+"'", null);
        cursorStock.moveToLast();
        if (cursorStock.getCount() != 0){
            do {
                price=cursorStock.getInt(0);
                dividend=cursorStock.getInt(1);
                Log.d(" StockPrice:", String.valueOf(price));

            } while (cursorStock.moveToNext());
        }
        else {
            return "";
        }
        cursorStock.close();

        int total=price*quantity;
        int nvtotal=total*-1;
        if(cash>=total){
            //新增到玩家擁有的股票
            String Playersql = "INSERT INTO PLAYER_INVESTMENT( CardNo, PlayerID, Quantity, Price, Dividend) VALUES ('"+CardNo+"', "+id+", "+quantity+", "+price+", "+dividend+")";
            db.execSQL(Playersql);
            //新增到現金流量表
            String Cashflowsql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', '"+CardNo+"', 'n', 'n', 'n', "+id+", '買進股票', "+nvtotal+")";
            db.execSQL(Cashflowsql);
            return  "y";
        }
        else{
            return "x";
        }

    }
           public String SellStock(String CardNo,int quantity){
               //尋找玩家ID>玩家現在擁有的股票張數-->
               // 賣出數量不可以大於擁有張數  a.成功賣出(回到開始頁)(寫到現金流量表) b.失敗(重新輸入數量)

               String id = "";
               Cursor cursorid_buy = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
               if (cursorid_buy.getCount() != 0){
                   do {
                       cursorid_buy.moveToLast();
                       id = cursorid_buy.getString(0);
                       Log.d(" playerID:", id);
                   } while (cursorid_buy.moveToNext());
               }
               else {
                   return "";
               }
               cursorid_buy.close();

               int ownquantity ;

               Cursor cursorquantity = db.rawQuery("Select Sum(t1.Quantity) " +
                       "From PLAYER_INVESTMENT t1 " +
                       "INNER JOIN CARD_INVESTMENT t2 ON t1.CardNo=t2.CardInvestNo " +
                       "WHERE t1.PlayerID='"+id+"' " +
                       "AND t2.StockName=(Select StockName from CARD_INVESTMENT WHERE CardInvestNo='"+CardNo+"')", null);
               if (cursorquantity.getCount() != 0){
                   cursorquantity.moveToLast();
                   do {
                       ownquantity = cursorquantity.getInt(0);
                       Log.d(" HasQuantity:", String.valueOf(ownquantity));
                   } while (cursorquantity.moveToNext());
               }
               else {
                   return "";
               }
               cursorquantity.close();
               if(quantity>ownquantity){
                   return "x";
               }
               else{
                   int price;
                   int dividend;
                   Cursor cursorStock = db.rawQuery("Select StockPrice,Dividend  from CARD_INVESTMENT WHERE CardInvestNo='"+CardNo+"'", null);
                   cursorStock.moveToLast();
                   if (cursorStock.getCount() != 0){
                       do {
                           price=cursorStock.getInt(0);
                           dividend=cursorStock.getInt(1);
                           Log.d(" StockPrice:", String.valueOf(price));

                       } while (cursorStock.moveToNext());
                   }
                   else {
                       return "";
                   }
                   cursorStock.close();

                   int total=price*quantity;
                    int navquantity=quantity*-1;

                   //新增賣出紀錄到玩家擁有的股票
                   String Playersql = "INSERT INTO PLAYER_INVESTMENT( CardNo, PlayerID, Quantity, Price, Dividend) VALUES ('"+CardNo+"', "+id+", "+navquantity+", "+price+", "+dividend+")";
                   db.execSQL(Playersql);
                   //新增到現金流量表
                   String Cashflowsql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', '"+CardNo+"', 'n', 'n', 'n', "+id+", '賣出股票', "+total+")";
                   db.execSQL(Cashflowsql);
                   return  "y";
               }

           }
}
