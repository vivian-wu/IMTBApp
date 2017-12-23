package imtbteam.imtb;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.method.ScrollingMovementMethod;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class JobScannerActivity extends AppCompatActivity {

    private Button btn_scan;
    private Button btn_send;
    private TextView txt_job;
    private EditText txt_name;
    private String scan_JobNo;

    private SQLiteDatabase db;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getReadableDatabase(); // 打開資料庫

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scanner);

        this.txt_name = (EditText)findViewById(R.id.job_input_name);

        this.btn_scan = (Button)findViewById(R.id.job_btn_scan);
        this.btn_send = (Button)findViewById(R.id.job_btn_send);

        this.txt_job = (TextView) findViewById(R.id.job_text_scan);
        txt_job.setMovementMethod(ScrollingMovementMethod.getInstance());

        this.btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(JobScannerActivity.this).initiateScan();
            }
        });

        this.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (txt_name.getText().toString().length() != 0 && scan_JobNo != null ){

               String sql = "INSERT INTO PLAYER (PlayerName, JobNo, Status) values('" + txt_name.getText().toString() + "', '"+ scan_JobNo +"', 1)";

                db.execSQL(sql);

                //db.execSQL("INSERT INTO PLAYER (PlayerID, PlayerName, JobNo, Status) values('P01', '測試測試', 'J01', 1)");

                Toast.makeText(JobScannerActivity.this, "新增資料成功！", Toast.LENGTH_SHORT).show();


                /* ------------------------------測試 Start----------------------------*/
                /*
                Cursor c = db.rawQuery("SELECT * FROM PLAYER", null);
                if (c.getCount()==0){
                    txt_job.setText("NO DATA");
                }

                if (c.getCount()>0){    // 若有資料
                    String str="總共有 "+c.getCount()+"筆資料\n";
                    str+="-----\n";

                    c.moveToFirst();    // 移到第 1 筆資料
                    do{        // 逐筆讀出資料
                        str+="PlayerID:"+c.getString(0)+"\n";
                        str+="PlayerName:"+c.getString(1)+"\n";
                        str+="JobNo:"+c.getString(2)+"\n";
                        str+="Status:"+c.getString(3)+"\n";
                        str+="-----\n";
                    } while(c.moveToNext());    // 有一下筆就繼續迴圈

                    txt_job.setText(str);
                }
                */
                /* ------------------------------測試 End----------------------------*/

                db.close();        // 關閉資料庫

                Intent intent = new Intent();
                intent.setClass(JobScannerActivity.this, MainActivity.class);
                startActivity(intent);

            }
            else {

                Toast.makeText(JobScannerActivity.this, "請輸入您的名稱或掃描職業卡片！", Toast.LENGTH_SHORT).show();

            }


            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                if (resultCode == JobScannerActivity.RESULT_OK) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {

            /* ------------------------------資料庫語法 ----------------------------*/

            scan_JobNo = result.getContents(); // QRcode

            String sql = "SELECT * FROM JOB WHERE JobNo = '" + scan_JobNo + "'";
            Cursor c = db.rawQuery(sql, null);

            String str = "";

            c.moveToFirst();// 移到第 1 筆資料
            do {             // 逐筆讀出資料
                str += "您的職業是：" + c.getString(1) + "\n";
                str += "每月薪水：" + c.getString(2) + "\n";
                str += "每月成本：" + c.getString(3) + "\n";
                str += "月現金流：" + c.getString(4) + "\n";
                ;
            } while (c.moveToNext());    // 有一下筆就繼續迴圈

            txt_job.setText(str);


            /* ------------------------------測試 Start----------------------------*/

            /*
            Cursor c = db.rawQuery("SELECT * FROM JOB", null);// 查詢JOB資料表中的所有資料
            if (c.getCount()==0){
                txt_job.setText("NO DATA");
            }

            if (c.getCount()>0){    // 若有資料
                String str="總共有 "+c.getCount()+"筆資料\n";
                str+="-----\n";

                c.moveToFirst();    // 移到第 1 筆資料
                do{        // 逐筆讀出資料
                    str+="JobNo:"+c.getString(0)+"\n";
                    str+="Job:"+c.getString(1)+"\n";
                    str+="Salary:"+c.getString(2)+"\n";
                    str+="Cost:"+c.getString(3)+"\n";
                    str+="MonthCashFlow:"+c.getString(4)+"\n";
                    str+="-----\n";
                } while(c.moveToNext());    // 有一下筆就繼續迴圈

                txt_job.setText(str);
            }*/

            /* ------------------------------測試 End ----------------------------*/
        }
        else if(resultCode == JobScannerActivity.RESULT_CANCELED) {
            Log.e("SEARCH_EAN", "CANCEL");
        }
        else{
            Log.e("SEARCH_EAN", "IntentResult je NULL!");
        }

    }
        }
    }

}
