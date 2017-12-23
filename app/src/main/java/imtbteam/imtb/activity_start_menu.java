package imtbteam.imtb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class activity_start_menu extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);

		Button Game = (Button)findViewById(R.id.button7);
		Button Record = (Button)findViewById(R.id.button8);
		Game.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(activity_start_menu.this, MainActivity.class);
				startActivity(intent);

			}
		}
		);

		Record.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		ShowMsgDialog("遊戲紀錄啦");

									}
								}
		);
	}

	// 測試用 MSGbox
	private void ShowMsgDialog(String Msg)
	{
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle("哦，這是！");
		MyAlertDialog.setMessage(Msg);
		DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {
			//如果不做任何事情 就會直接關閉 對話方塊
			}
		};;
		MyAlertDialog.setNeutralButton("中間按鈕",OkClick );
		MyAlertDialog.show();
	}


}
