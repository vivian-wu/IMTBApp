package imtbteam.imtb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
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

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 打開資料庫


        text_stock = (TextView)findViewById(R.id.asset_text_stock);
        text_stock.setMovementMethod(ScrollingMovementMethod.getInstance());

        String StockContent=Get_Stock();
        text_stock.setText(StockContent);

        Get_Salary();
        Get_Cash();

        db.close();        // 關閉資料庫

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

        text_salary = (TextView)findViewById(R.id.asset_text_salary);
        text_cost = (TextView)findViewById(R.id.asset_text_cost);
        text_mcf = (TextView)findViewById(R.id.asset_text_mcf);

        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.getCount()>0)
        {
            do {
                    cursorid.moveToLast();
                    id = cursorid.getString(0);
                    Log.d("薪水- playerID:", id);

            } while (cursorid.moveToNext());
        }
        else{
            return "";
        }

        cursorid.close();

        /*抓出他的職業資料*/

        String idsalary="";
        String idcost="";
        String idMonthCashflow="";

        String query = "Select Salary, Cost, MonthCashFlow from SALARYRECORD where PlayerID='"+id+"' ORDER BY SRID DESC LIMIT 1";
        Cursor cursorinfo = db.rawQuery(query, null);
        if (cursorinfo.getCount()>0){
            do {
                cursorinfo.moveToFirst();
                idsalary = cursorinfo.getString(0);
                idcost = cursorinfo.getString(1);
                idMonthCashflow = cursorinfo.getString(2);
                Log.d("薪水- salary:", idsalary + ", " + idcost);

            } while (cursorinfo.moveToNext());
        }
        else{
            System.out.print("沒有薪水資料");
            return "";
        }

        cursorinfo.close();

        text_salary.setText(idsalary);
        text_cost.setText(idcost);
        text_mcf .setText(idMonthCashflow);
        return "";

    }
    public String Get_Cash(){

        text_cash = (TextView)findViewById(R.id.asset_text_cash);

        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.getCount()>0){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d("現金 - playerID:", id);
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

        if (cursorinfo.moveToNext()){
            do {
                cursorinfo.moveToFirst();
                idcash = cursorinfo.getString(0);
                Log.d("現金 - cash:", idcash);

            } while (cursorinfo.moveToNext());
        }
        else{
            System.out.print("沒有現金資料");
            return "";
        }

        cursorinfo.close();

        text_cash.setText(idcash);
        return "";


    }
    public String Get_Stock(){

        /*抓出最後一筆玩家ID*/

        String id = "";

        Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
        if (cursorid.getCount() != 0){
            do {
                cursorid.moveToLast();
                id = cursorid.getString(0);
                Log.d("投資 - playerID:", id);
            } while (cursorid.moveToNext());
        }
        else {
            return "";
        }

        cursorid.close();

        /*抓出他的股票資料*/

        String temp="";
        int total;
        int quantity;
        double price;
        String query = "Select t2.StockName,Sum(t1.Price*t1.Quantity),Sum(t1.Quantity ) " +
                "From PLAYER_INVESTMENT t1   " +
                "INNER JOIN CARD_INVESTMENT t2 ON t1.CardNo=t2.CardInvestNo  " +
                "WHERE PlayerID='"+id+"'  " +
                "GROUP BY t2.StockName";
        Cursor cursorinfo = db.rawQuery(query, null);


        if (cursorinfo.getCount() != 0){
            cursorinfo.moveToFirst();
            do {
                total=cursorinfo.getInt(1);
                quantity=cursorinfo.getInt(2);
                price=(double)total/(double)quantity;
                temp = temp+cursorinfo.getString(0);    //名稱
                temp = temp+"　 　　"+String.valueOf(price);     //金額
                temp = temp+"　　　　"+cursorinfo.getString(2)+"\n";  //張數
                Log.d("現金 - 股票資料:", temp);

            } while (cursorinfo.moveToNext());
        }
        else{
            return "";
        }
        cursorinfo.close();


        return temp;


    }

}
