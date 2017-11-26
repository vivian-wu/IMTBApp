package imtbteam.imtb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AccountingActivity extends AppCompatActivity {

    private EditText edAmount;
    private RadioButton rev;
    private  RadioButton exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);

        edAmount = (EditText) findViewById(R.id.ed_amount);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
         exp = (RadioButton) findViewById(R.id.rbt_exp);
         rev = (RadioButton) findViewById(R.id.rbt_rev);



    }

    public void add(View v){

    }
}
