package imtbteam.imtb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InvestmentActivity extends AppCompatActivity
       {

    private EditText edAmount;
    private RadioButton buy;
    private RadioButton sell;
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
        text_stockname = (TextView) findViewById(R.id.inv_text_stockname);
        text_price = (TextView) findViewById(R.id.inv_text_price);
        text_div = (TextView) findViewById(R.id.inv_text_div);
        text_pricerange = (TextView) findViewById(R.id.inv_text_pricerange);
        text_stock = (TextView) findViewById(R.id.inv_text_stocks);
        String x=Set_StockContent(cardno_string);
        String OwnStock=Set_OwnStock(cardno_string);
        db.close();
      //  cardno_int = Integer.parseInt(cardno_string.substring(1));

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
}
