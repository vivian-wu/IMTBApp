package imtbteam.imtb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AccountingActivity extends AppCompatActivity {

    private EditText edAmount;
    private RadioButton rev;
    private RadioButton exp;

    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    private String cardno_string;
    private int cardno_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);

        Bundle bundle = getIntent().getExtras(); // 利用 bundle 獲得掃到的 CardID
        cardno_string = bundle.getString("id");

        dbHelper = new MyDBHelper(this);   // 打開資料庫
        db = dbHelper.getWritableDatabase();

        edAmount = (EditText) findViewById(R.id.ed_amount);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
         exp = (RadioButton) findViewById(R.id.rbt_exp);
         rev = (RadioButton) findViewById(R.id.rbt_rev);

        cardno_int = Integer.parseInt(cardno_string.substring(1));

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
            Toast.makeText(AccountingActivity.this, "請輸入金額", Toast.LENGTH_SHORT).show();
        }

    }
}
