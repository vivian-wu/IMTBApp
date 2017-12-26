package imtbteam.imtb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

public class assets_record extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView text_salary,text_cost,text_mcf,text_stock,text_cash;
    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final TextView text_salary = (TextView)findViewById(R.id.asset_text_salary);
        final TextView text_cost = (TextView)findViewById(R.id.asset_text_cost);
        final TextView text_mcf = (TextView)findViewById(R.id.asset_text_mcf);

        final TextView text_stock = (TextView)findViewById(R.id.asset_text_stock);
        text_stock.setMovementMethod(ScrollingMovementMethod.getInstance());

        String StockContent=Get_Stock();
        text_stock.setText(StockContent);

        String x=Get_Salary();
        Get_Cash();

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
        getMenuInflater().inflate(R.menu.assets_record, menu);
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

    public String Get_Salary(){

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 打開資料庫

        text_salary = (TextView)findViewById(R.id.asset_text_salary);
        text_cost = (TextView)findViewById(R.id.asset_text_cost);
        text_mcf = (TextView)findViewById(R.id.asset_text_mcf);

        String sql = "";


        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.moveToNext())
        {
            do {
                if(db.isReadOnly()){
                    cursorid.moveToLast();
                    id = cursorid.getString(0);
                    Log.d("playerID:", id);
                }

            } while (cursorid.moveToNext());
        }
        else{
            return"";
        }

        cursorid.close();

        /*抓出他的職業資料*/

        String idsalary="";
        String idcost="";
        String idMonthCashflow="";

        String query = "Select Salary,Cost,MonthCashFlow from SALARYRECORD where PlayerID='"+id+"' ORDER BY SRID DESC LIMIT 1";
        Cursor cursorinfo = db.rawQuery(query, null);
        if (cursorid.moveToNext()){
            do {
                cursorinfo.moveToFirst();
                idsalary = cursorinfo.getString(0);
                idcost = cursorinfo.getString(1);
                idMonthCashflow=cursorinfo.getString(2);
                Log.d("salary:", idsalary + ", " + idcost);

            } while (cursorinfo.moveToNext());
        }
        else{
            return "";
        }

        cursorinfo.close();

        db.close();        // 關閉資料庫
        text_salary.setText(idsalary);
        text_cost.setText(idcost);
        text_mcf .setText(idMonthCashflow);
        return "";

    }
    public String Get_Cash(){

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 打開資料庫

        text_cash = (TextView)findViewById(R.id.asset_text_cash);

        String sql = "";


        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.moveToNext()){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d("playerID:", id);
            } while (cursorid.moveToNext());
        }
        else {
            return "";
        }

        cursorid.close();

        /*抓出他的現金資料*/

        String idcash;

        String query = "Select SUM(Amount) from CASHFLOW Where PlayerID='"+id+"'";
        Cursor cursorinfo = db.rawQuery(query, null);
        if (cursorid.moveToNext()){
            do {
                cursorinfo.moveToFirst();
                idcash = cursorinfo.getString(0);
                Log.d("cash:", idcash);

            } while (cursorinfo.moveToNext());
        }
        else{
            return "";
        }

        cursorinfo.close();

        db.close();        // 關閉資料庫
        text_cash.setText(idcash);
        return "";


    }
    public String Get_Stock(){

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 打開資料庫

        String sql = "";

        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.moveToNext()){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d("playerID:", id);
            } while (cursorid.moveToNext());
        }
        else {
            return "";
        }

        cursorid.close();

        /*抓出他的股票資料*/

        String temp="";

        String query = "Select t1.StockName,t2.Price,t2.Quantity from CARD_INVESTMENT t1 INNER JOIN PLAYER_INVESTMENT t2 on t1.CardInvestNo=t2.CardNo Where t2.PlayerID='"+id+"'";
        Cursor cursorinfo = db.rawQuery(query, null);
        if (cursorid.moveToNext()){
            do {
                cursorinfo.moveToFirst();
                temp = temp+cursorinfo.getString(0);    //名稱
                temp = temp+"　 　　"+cursorinfo.getString(1);     //金額
                temp = temp+"　　　　"+cursorinfo.getString(2)+"\n";  //張數
                Log.d("股票資料:", temp);

            } while (cursorinfo.moveToNext());
        }
        else{
            return "";
        }

        cursorinfo.close();

        db.close();        // 關閉資料庫
        return temp;


    }

}
