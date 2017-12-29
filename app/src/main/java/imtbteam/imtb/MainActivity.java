package imtbteam.imtb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private SQLiteDatabase db;
	private MyDBHelper dbHelper;

	private TextView name_Text;
	private TextView job_Text;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		assert drawer != null;
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		///////////////////////////// NAV 資訊 /////////////////////////////

		dbHelper = new MyDBHelper(this);   // 打開資料庫
		db = dbHelper.getWritableDatabase();

		job_Text = (TextView) findViewById(R.id.job_Text);
		name_Text = (TextView) findViewById(R.id.name_Text);

		String id = "";
		String name="" ;

		Cursor cursor = db.rawQuery("Select PlayerID,PlayerName from PLAYER",null);

		do {
			cursor.moveToLast();
			id = cursor.getString(0);
			name = cursor.getString(1);
			Log.d("playerID:", id +"name:"+name);
		}while(cursor.moveToNext());

		cursor.close();

		name_Text.setText(name);

		String job ="";

		String query ="Select t2.Job " +
				"from PLAYER t1 " +
				"INNER JOIN JOB t2 ON t1.JobNo=t2.JobNo " +
				"WHERE PlayerID='"+id+"'";

		Cursor cursorjob = db.rawQuery(query, null);

		do {
			cursorjob.moveToLast();
			job = cursorjob.getString(0);
			Log.d("JobName",job );
		}while(cursorjob.moveToNext());

		cursorjob.close();

		job_Text.setText(job);

		///////////////////////////// NAV 資訊 /////////////////////////////





		/////////////////////////////// 按鈕選單////////////////////////////

		/* 首頁 */
		findViewById(R.id.nav_btn_home).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* 畫面切換 */
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity.class);
				startActivity(intent);

				/*浮現的小訊息*/
				Toast.makeText(MainActivity.this, "切換至首頁，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();

				finish();
			}
		});

		/* 遊戲規則 */
		findViewById(R.id.nav_btn_rule).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, game_rule.class);
				startActivity(intent);

				Toast.makeText(MainActivity.this, "切換至遊戲規則，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();


			}
		});

		/*收支紀錄*/
		findViewById(R.id.nav_btn_record).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, FinanceActivity.class);
				startActivity(intent);

				Toast.makeText(MainActivity.this, "切換至紀錄收支，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();


			}
		});

		/* 掃描卡片 */
		findViewById(R.id.nav_btn_scanner).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, QRScannerActivity.class);
				startActivity(intent);

				Toast.makeText(MainActivity.this, "切換至掃描卡片，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();


			}
		});

		/* 資產現況 */
		findViewById(R.id.nav_btn_asset).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, assets_record.class);
				startActivity(intent);

				Toast.makeText(MainActivity.this, "切換至資產現況，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();


			}
		});


		/* 關於 */
		findViewById(R.id.nav_btn_about).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, about_us.class);
				startActivity(intent);

				Toast.makeText(MainActivity.this, "切換至關於，成功！", Toast.LENGTH_SHORT).show();
				onBackPressed();


			}
		});

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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id== R.id.action_settings)
		{
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ReportActivity.class);
			startActivity(intent);
			onBackPressed();
		}
		else if (id==R.id.action_job)
		{
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, JobScannerActivity.class);
			startActivity(intent);
			onBackPressed();
		}




		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_btn_home) {

		} else if (id == R.id.nav_btn_rule) {

		} else if (id == R.id.nav_btn_record) {

		} else if (id == R.id.nav_btn_scanner) {

		} else if (id == R.id.nav_btn_asset) {

		} else if (id == R.id.nav_btn_about) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
