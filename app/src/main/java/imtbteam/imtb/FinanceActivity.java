package imtbteam.imtb;

<<<<<<< HEAD
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
=======
>>>>>>> Adds-ListView-to-FinanceActivity
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FinanceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
     RadioGroup radioGroup1;
    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    private TextView textView;
=======
>>>>>>> Adds-ListView-to-FinanceActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
<<<<<<< HEAD
        RadioButton rbt_rev = (RadioButton) findViewById(R.id.rbt_rev);
        RadioButton rbt_exp = (RadioButton) findViewById(R.id.rbt_exp);
        textView = (TextView)findViewById(R.id.textView9);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 打開資料庫
        textView.setText(Get_CashData());
        db.close();
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(FinanceActivity.this, AccountingActivity.class));
            }
        });
*/
=======
>>>>>>> Adds-ListView-to-FinanceActivity

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.finance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public String Get_CashData(){

        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.getCount() != 0){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d("現金記錄 - playerID:", id);
            } while (cursorid.moveToNext());
        }
        else {
            return "";
        }

        cursorid.close();

        /*抓出他的現金資料*/
        String temp="";

        String query = "SELECT case  " +
                "when t1.CardNo!='n'  then t2.CardCategory " +
                "when t1.CardMarNo!='n' then t3.CardCategory " +
                "when t1.CardOrderNo!='n' then t4.CardCategory " +
                "when t1.CardInvestNo!='n' then t5.CardCategory " +
                "when t1.CardOtherNo!='n' then t6.CardCategory " +
                "END as Category, " +
                "case  " +
                "when t1.CardNo!='n'  then t2.CardContent " +
                "when t1.CardMarNo!='n' then t3.CardContent " +
                "when t1.CardOrderNo!='n' then '獲得訂單' " +
                "when t1.CardInvestNo!='n' AND t1.Amount<0 then '買進股票' " +
                "when t1.CardInvestNo!='n' AND t1.Amount>0 then '賣出股票' " +
                "when t1.CardOtherNo!='n' then t6.CardContent " +
                "END as Content " +
                ",t1.Amount " +
                "from CASHFLOW t1 " +
                "LEFT JOIN Card t2 on t1.CardNo=t2.CardNo " +
                "LEFT JOIN CARD_MARKET t3 on t1.CardMarNo=t3.CardMarNo " +
                "LEFT JOIN CARD_ORDER t4 on t1.CardOrderNo=t4.CardOrderNo " +
                "LEFT JOIN CARD_INVESTMENT t5 on t1.CardInvestNo=t5.CardInvestNo " +
                "LEFT JOIN CARD_OTHER t6 on t1.CardOtherNo=t6.CardOtherNo " +
                "WHERE t1.PlayerID='"+id+"'";
        Cursor cursorinfo = db.rawQuery(query, null);
        if (cursorinfo.getCount() != 0){
            cursorinfo.moveToFirst();
            do {
                temp = cursorinfo.getString(0);    //卡片類別
                temp = temp+cursorinfo.getString(1);     //卡片內容
                temp = temp+cursorinfo.getString(2);  //金額
                Log.d("現金紀錄:", temp);

            } while (cursorinfo.moveToNext());
        }
        else{
            return "";
        }
        cursorinfo.close();


        return temp;


    }

}
