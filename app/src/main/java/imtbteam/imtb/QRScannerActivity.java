package imtbteam.imtb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

		if(result!=null){
			String scanContent = result.getContents();
			String scanFormat = result.getFormatName();
			txt_url.setText(scanFormat+" \n"+scanContent);


			switch (scanContent)
			{
				case "C010":
				case "C011":
				case "C012":
				case "C013":
				case "C017":
				case "C018":
					ShowMsgDialog_card("");
					break;

			}

			// 將 id 傳至下一 Activity
			Intent intent2 = new Intent();
			intent2.setClass(QRScannerActivity.this,AccountingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("id",scanContent.toString());
			intent2.putExtras(bundle);
			startActivity(intent2);


		}
		else{
            Toast.makeText(QRScannerActivity.this,"nothing",Toast.LENGTH_SHORT).show();
		}

	}

	private void ShowMsgDialog_card(String Msg)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle("哦，這是！");
		MyAlertDialog.setMessage(Msg);

		MyAlertDialog.setNegativeButton("中間按鈕", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});

		AlertDialog dialog = MyAlertDialog.create();
		dialog.show();
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
