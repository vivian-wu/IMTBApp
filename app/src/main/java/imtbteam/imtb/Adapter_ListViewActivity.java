package imtbteam.imtb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_ListViewActivity extends BaseAdapter{

	static final String TAG = "Adapter_ListViewActivity";

	private LayoutInflater mInflater;
	String[] adapter_id;
	String[] adapter_category;
	String[] adapter_content;
	String[] adapter_amount;
	TextView column1;
	TextView column2;
	TextView column3;
	TextView column4;

	public Adapter_ListViewActivity(Context context,
									int simple_list_item_single_choice,
									String[] cashid,
									String[] category,
									String[] cardcontent,
									String[] amount)
	{
		mInflater = LayoutInflater.from(context);
		adapter_id = cashid;
		adapter_category = category;
		adapter_content = cardcontent;
		adapter_amount = amount;
	}

	@Override
	public int getCount() {
		return adapter_id.length;
	}

	@Override
	public Object getItem(int position) {
		return adapter_id.length;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position,View convertView,ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.finance_row,null);
		column1 = (TextView)convertView.findViewById(R.id.item_id);
		column2 = (TextView)convertView.findViewById(R.id.item_category);
		column3 = (TextView)convertView.findViewById(R.id.item_content);
		column4 = (TextView)convertView.findViewById(R.id.item_amount);

		column1.setText(adapter_id[position].toString());
		column2.setText(adapter_category[position].toString());
		column3.setText(adapter_content[position].toString());
		column4.setText(adapter_amount[position].toString());

		return convertView;
	}
}
