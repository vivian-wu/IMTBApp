package imtbteam.imtb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerActivity extends AppCompatActivity {

	private Button btn_scan;
	private TextView txt_url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner);

		this.btn_scan = (Button)findViewById(R.id.btn_scan);
		this.txt_url = (TextView) findViewById(R.id.txt_url);

		this.btn_scan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new IntentIntegrator( ScannerActivity.this).initiateScan();
			}
		});
	}


	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if(result!=null){
			String scanContent = result.getContents();
			String scanFormat = result.getFormatName();
			txt_url.setText(scanFormat+" \n"+scanContent);
		}else{
			Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_LONG).show();
		}

	}
}
