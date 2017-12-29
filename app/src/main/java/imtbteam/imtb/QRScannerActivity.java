package imtbteam.imtb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScannerActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private Button btn_scan;
	private TextView txt_url;

	private SQLiteDatabase db;
	private MyDBHelper dbHelper;
	private String scanContent="";
	String x;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrscanner);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		assert drawer != null;
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		dbHelper = new MyDBHelper(this);
		db = dbHelper.getWritableDatabase(); // 打開資料庫

		this.btn_scan = (Button)findViewById(R.id.btn_scan);
		this.txt_url = (TextView) findViewById(R.id.txt_url);

		this.btn_scan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new IntentIntegrator( QRScannerActivity.this).initiateScan();
			}
		});
		String PStatus=updateStatus();
		if(PStatus=="drop"){
			ShowMsgDialog_Drop("恭喜您！","跳出儲蓄圈啦～！\n世界變得更寬廣了，還不加緊腳步邁向大老闆之路！","　爽啦！　");
		}
	}
	private void ShowMsgDialog_Drop(String title ,String Msg,String btnText)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle(title);
		MyAlertDialog.setMessage(Msg);
		MyAlertDialog.setCancelable(false);

		MyAlertDialog.setPositiveButton("　確定！　", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {


				Intent intent = new Intent();
				intent.setClass(QRScannerActivity.this, MainActivity.class);
				startActivity(intent);

			}
		});

		MyAlertDialog.show();
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent){

		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

		String content;
		String category;
		String query ;

		if(result!=null){
			scanContent = result.getContents(); // 取得CardID -> scanContent
			//txt_url.setText(scanContent);

			if (scanContent.equals("F01"))
			{
				Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_OTHER WHERE CardOtherNo ='F01'",null);
				cursor.moveToFirst();
				content = cursor.getString(0);
				Log.d("content:", content);

				ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
				cursor.close();

			}
			else if (scanContent.equals("F02"))
			{
				Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_OTHER WHERE CardOtherNo ='F02'",null);
				cursor.moveToFirst();
				content = cursor.getString(0);
				Log.d("content:", content);

				ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
				cursor.close();

			}
			else if (scanContent.equals("F03"))
			{
				Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_OTHER WHERE CardOtherNo ='F03'",null);
				cursor.moveToFirst();
				content = cursor.getString(0);
				Log.d("content:", content);

				ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
				cursor.close();

			}
			else{
				String temp=scanContent.replace("C","");
				int a=Integer.parseInt(temp);
				if(a > 9 && a < 13)
                {
                    System.out.println("執行機會命運");
                    Cursor cursor = db.rawQuery( "SELECT CardCategory, CardContent FROM CARD WHERE CardNo ='"+scanContent+"'",null);
                    cursor.moveToFirst();
                    category = cursor.getString(0);
                    content = cursor.getString(1);
                    Log.d("content:", content);

                    ShowMsgDialog_justshow(category,content,"　確定　");
                    cursor.close();
                }
				else if(a<=80){
					System.out.println("執行機會命運");
					Cursor cursor = db.rawQuery( "SELECT CardCategory, CardContent FROM CARD WHERE CardNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					category = cursor.getString(0);
					content = cursor.getString(1);
					Log.d("content:", content);

					ShowMsgDialog(category,content,"　確定　");
					cursor.close();
				}
				else if(a>80&&a<=100){
					System.out.println("投資理財");
					Cursor cursor = db.rawQuery( "SELECT CardContent, StockName, StockPrice, Dividend, PriceRange FROM CARD_INVESTMENT WHERE CardInvestNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					String stock = cursor.getString(1);
					String price = cursor.getString(2);
					String divi = cursor.getString(3);
					String range = cursor.getString(4);
					Log.d("content:",  content);

					ShowMsgDialog_Invest(content, stock, price, divi, range);
					cursor.close();
				}
				else if(a>100&&a<=115){
					System.out.println("市場變化");
					Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_MARKET WHERE CardMarNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					Log.d("content:", content);

					ShowMsgDialog("市場變化",content,"　確定　");
					cursor.close();
				}
				else if(a>115&&a<=145){
					System.out.println("大小訂單");
					Cursor cursor = db.rawQuery( "SELECT CardCategory, CardContent, FixedCost, VariableCost, Quantity, Price FROM CARD_ORDER WHERE CardOrderNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					category = cursor.getString(0);
					content = cursor.getString(1);
					String fc = cursor.getString(2);
					String vc = cursor.getString(3);
					String q = cursor.getString(4);
					String p = cursor.getString(5);
					Log.d("content:", content);

					ShowMsgDialog_Order(content,category,fc,vc,q,p);
					cursor.close();
				}
				else{
					ShowMsgDialog("哦，這是！","錯誤","Oh, no！");
				}
			}



			// 將 id 傳至下一 Activity
			/*Intent intent2 = new Intent();
			intent2.setClass(QRScannerActivity.this,AccountingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("id",scanContent.toString());
			intent2.putExtras(bundle);
			startActivity(intent2);*/


		}
		else{
            Toast.makeText(QRScannerActivity.this,"nothing",Toast.LENGTH_SHORT).show();
		}

	}
    private void ShowMsgDialog_justshow(String title ,String Msg,String btnText)
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

	private void ShowMsgDialog(String title ,String Msg,String btnText)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle(title);
		MyAlertDialog.setMessage(Msg);
		MyAlertDialog.setCancelable(false);

		MyAlertDialog.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
			    if (scanContent.equals("F01")){
                    x=Set_F01(scanContent);
                }
                else if (scanContent.equals("F02"))
                {
                    x=Set_F02(scanContent);
                }
                else {
                    x=Set_Card(scanContent);
                }

			}
		});

		MyAlertDialog.show();
	}

	private void ShowMsgDialog_Invest(String Msg, String stock, String price, String divi, String range)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle("投資理財");
		Msg = Msg + "\n\n股票名稱："+stock+"\n目前價格："+price+"\n股票股利："+divi+"\n價格範圍："+range+"\n";
		MyAlertDialog.setMessage(Msg);
		MyAlertDialog.setCancelable(false);

		MyAlertDialog.setPositiveButton("　好，我要進行交易！　", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {


				Intent intent = new Intent();
				intent.setClass(QRScannerActivity.this, InvestmentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("id",scanContent.toString());
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});

		MyAlertDialog.setNegativeButton("　略過　", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});

		MyAlertDialog.show();
	}

	private void ShowMsgDialog_Order(String Msg, String category, String fc , String vc, String q, String p)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle(category);
		Msg = Msg + "\n\n固定成本："+fc+"\n變動成本："+vc+"\n數量："+q+"\n價格："+p+"\n預估銷售額："+Integer.parseInt(p)*Integer.parseInt(q)+"\n";
		MyAlertDialog.setMessage(Msg);
		MyAlertDialog.setCancelable(false);

		MyAlertDialog.setPositiveButton("　接受　", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				x=Set_OrderCardV(scanContent);
			}
		});

		MyAlertDialog.setNegativeButton("　放棄　", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				x=Set_OrderCardN(scanContent);
			}
		});

		MyAlertDialog.show();
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
		getMenuInflater().inflate(R.menu.qrscanner, menu);
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

		if (id == R.id.nav_btn_home) {
			startActivity(new Intent(this, MainActivity.class));
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
	public String Set_F01(String CardNo){		//薪水

        /*抓出最後一筆玩家ID*/

		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("薪水查詢 - playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();

        /*抓出他的最新一筆月現金流*/
		int Salary;

		String query = "SELECT MonthCashFlow from SALARYRECORD WHERE PlayerID='"+id+"' ORDER BY SRID desc LIMIT 1";
		Cursor cursorinfo = db.rawQuery(query, null);


		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				Salary = cursorinfo.getInt(0);
				Log.d("月現金流:",Integer.toString( Salary));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();
		String sql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', 'n', 'n', '"+CardNo+"', 'n', "+id+", '收入', "+Salary+")";
		db.execSQL(sql);

		return "";


	}

	public String Set_F02(String CardNo){		//脫魯>>新增薪水表  >>新增現金紀錄

        /*抓出最後一筆玩家ID*/

		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("薪水查詢 - playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();

        /*抓出他的最新一筆月現金流*/
		int Salary;
		int Cost;
		int MonthCashFlow;

		String query = "SELECT Salary ,Cost from SALARYRECORD WHERE PlayerID='"+id+"' ORDER BY SRID desc LIMIT 1";
		Cursor cursorinfo = db.rawQuery(query, null);
		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				Salary = cursorinfo.getInt(0);
				Cost = cursorinfo.getInt(1);
				Log.d("薪水:",Integer.toString( Salary)+"月支出:"+Integer.toString( Cost));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();
		Cost=Cost+2000;
		MonthCashFlow=Salary-Cost;

		String SRsql = "INSERT INTO SALARYRECORD( PlayerID, Salary, Cost, MonthCashFlow) VALUES ( "+id+", "+Salary+", "+Cost+", "+MonthCashFlow+")";
		db.execSQL(SRsql);

		String CFsql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', 'n', 'n', '"+CardNo+"', 'n', "+id+", '脫魯', 0)";
		db.execSQL(CFsql);

		return "";

	}

	public String Set_F03(String CardNo){		//結婚-->先不做


		return "";

	}

	public String Set_Card(String CardNo){		//機會命運天使惡魔

		/*抓出最後一筆玩家ID*/

		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("新增 playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();

        /*抓出卡片金額*/
		int Amount;

		String query = "SELECT CashAmount FROM CARD where CardNo='"+CardNo+"'";
		Cursor cursorinfo = db.rawQuery(query, null);
		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				Amount = cursorinfo.getInt(0);
				Log.d("卡片金額:",Integer.toString( Amount));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();
		String sql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( '"+CardNo+"', 'n', 'n', 'n', 'n', "+id+", '暫時', "+Amount+")";
		db.execSQL(sql);

		return "";

	}

	public String Set_InvestmentCard(String CardNo){		//投資理財


		return "";

	}

	public String Set_OrderCardV(String CardNo){		//大小訂單確認
/*抓出最後一筆玩家ID*/

		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("新增 playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();
		/*抓出卡片金額*/
		int Amount;

		String query = "Select (Quantity*Price-FixedCost-VariableCost) as Amount " +
						"from CARD_ORDER " +
						"Where CardOrderNo='"+CardNo+"'";
		Cursor cursorinfo = db.rawQuery(query, null);
		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				Amount = cursorinfo.getInt(0);
				Log.d("卡片金額:",Integer.toString( Amount));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();
		//新增到現金流量表
		String sql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', 'n', 'n', 'n', '"+CardNo+"', "+id+", '暫時', "+Amount+")";
		db.execSQL(sql);
		return "";

	}		//還沒做跟市場變化相關的部分

	public String Set_OrderCardN(String CardNo){		//大小訂單放棄

		/*抓出最後一筆玩家ID*/
		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("新增 playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();
		/*抓出卡片金額*/
		int Amount;

		String query = "Select -FixedCost  " +
				"from CARD_ORDER " +
				"Where CardOrderNo='"+CardNo+"'";
		Cursor cursorinfo = db.rawQuery(query, null);
		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				Amount = cursorinfo.getInt(0);
				Log.d("卡片金額:",Integer.toString( Amount));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();
		//新增到現金流量表
		String sql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', 'n', 'n', 'n', '"+CardNo+"', "+id+", '暫時', "+Amount+")";
		db.execSQL(sql);
		return "";

	}		//還沒做跟市場變化相關的部分

	public String Set_MarketCard(String CardNo){		//市場變化-->抓ID(ok)>抓卡片資料(o)>寫入Player_Market表(o)>寫入現金紀錄(ok)

		/*抓出最後一筆玩家ID*/

		String id = "";

		Cursor cursorid = db.rawQuery("Select PlayerID from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Log.d("新增 playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}

		cursorid.close();

        /*抓出卡片資料*/
		String VC;
		double VR;

		String query = "Select VariationCategory,VariationRange from CARD_MARKET  where CardMarNo='"+CardNo+"'";
		Cursor cursorinfo = db.rawQuery(query, null);
		if (cursorinfo.getCount() != 0){
			do {
				cursorinfo.moveToFirst();
				VC= cursorinfo.getString(0);
				VR =cursorinfo.getDouble(1);
				Log.d("卡片分類:",VC+"卡片Range:"+String.valueOf(VR));

			} while (cursorinfo.moveToNext());
		}
		else{
			return "";
		}
		cursorinfo.close();

		//插入玩家_市場變化表
		String PMsql = "INSERT INTO PLAYER_MARKET(CardNo, PlayerID,VariationCategory, VariationRange, ) VALUES ('"+CardNo+"', '"+id+"', '"+VC+"', "+VR+")";
		db.execSQL(PMsql);

		//插入現金流量表
		String CFsql = "INSERT INTO CASHFLOW( CardNo, CardInvestNo, CardMarNo, CardOtherNo, CardOrderNo, PlayerID, CashCategory, Amount) VALUES ( 'n', 'n', '"+CardNo+"', 'n', 'n', "+id+", '暫時', '0')";
		db.execSQL(CFsql);

		return "";

	}

	public String updateStatus(){

		/*抓出最後一筆玩家ID*/
		String id = "";
		int Status;
		Cursor cursorid = db.rawQuery("Select PlayerID, Status  from PLAYER ORDER BY PlayerID desc limit 1 ", null);
		if (cursorid.getCount() != 0){
			do {
				cursorid.moveToLast();
				id = cursorid.getString(0);
				Status = cursorid.getInt(1);
				Log.d("新增 playerID:", id);
			} while (cursorid.moveToNext());
		}
		else {
			return "";
		}
		 /*抓出他的現金資料*/
		int idcash;

		String query = "Select SUM(Amount) from CASHFLOW Where PlayerID='"+id+"'";
		Cursor cursorinfo = db.rawQuery(query, null);

		if (cursorinfo.moveToNext()){
			cursorinfo.moveToFirst();
			do {
				idcash = cursorinfo.getInt(0);
				Log.d("現金 - cash:", String.valueOf(idcash));

			} while (cursorinfo.moveToNext());
		}
		else{
			System.out.print("沒有現金資料");
			return "";
		}

		cursorinfo.close();
		if(idcash>130000 && Status==1){
 			String Statussql="UPDATE PLAYER SET Status=2 WHERE PlayerID = "+id+";";
 			db.execSQL(Statussql);
			return "drop";
		}
		else{
			return "";
		}

	}
}
