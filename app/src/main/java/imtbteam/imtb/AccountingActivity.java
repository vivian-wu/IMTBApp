package imtbteam.imtb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AccountingActivity extends AppCompatActivity {

    private EditText edAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);

        edAmount = (EditText) findViewById(R.id.ed_amount);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
    }

    public void add(View v){

    }
}
