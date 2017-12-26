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

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent){

		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

		String content;
		String query ;

		if(result!=null){
			String scanContent = result.getContents(); // 取得CardID -> scanContent
			txt_url.setText(scanContent);

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
				if(a<=80){
					System.out.println("執行機會命運");
					Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD WHERE CardNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					Log.d("content:", content);

					ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
					cursor.close();
				}
				else if(a>80&&a<=100){
					System.out.println("投資理財");
					Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_INVESTMENT WHERE CardInvestNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					Log.d("content:", content);

					ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
					cursor.close();
				}
				else if(a>100&&a<=115){
					System.out.println("市場變化");
					Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_MARKET WHERE CardMarNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					Log.d("content:", content);

					ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
					cursor.close();
				}
				else if(a>115&&a<=145){
					System.out.println("大小訂單");
					Cursor cursor = db.rawQuery( "SELECT CardContent FROM CARD_ORDER WHERE CardOrderNo ='"+scanContent+"'",null);
					cursor.moveToFirst();
					content = cursor.getString(0);
					Log.d("content:", content);

					ShowMsgDialog("哦，這是！",content,"好耶！太棒了！");
					cursor.close();
				}
				else{
					ShowMsgDialog("哦，這是！","錯誤","好耶！太棒了！");
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

	private void ShowMsgDialog(String title ,String Msg,String btnText)
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
}
