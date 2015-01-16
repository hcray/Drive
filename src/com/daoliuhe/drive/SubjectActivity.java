package com.daoliuhe.drive;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.DbAdapter;

public class SubjectActivity extends Activity {
	// 路线1
	//private Button btnPath1;
	// 路线2
	//private Button btnPath2;
	// 路线3
	//private Button btnPath3;
	
	private ListView subjectList;
	//新增
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//取消
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private static final int ACTIVITY_ADD = 1;
	
	private static final int ACTIVITY_ITEMVIEW = ACTIVITY_ADD + 1;
	
	private DbAdapter dbAdapter;
	
	private RelativeLayout  layout;
	
	private List<LineBean> lineList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
		//数据库
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		//加载list列表
		renderListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.subject, menu);
		menu.add(0, MENU_ADD_ID, 0, R.string.menu_add);
		menu.add(0, MENU_BACK_ID, 0, R.string.menu_back);
		return true;
	}

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_ADD_ID: // 新增
			addLine();
			return true;
		case MENU_BACK_ID:// 返回
			SubjectActivity.this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ADD && resultCode == RESULT_OK){
			int retId = data.getIntExtra("id", -1);
			String retName = data.getStringExtra("name");
			if(retId != -1){
				Button button = new Button(this);
				button.setId(retId);
				button.setText(retName);
				//将按钮放入layout组件
				layout.addView(button);   
			}
		}
	}
	
	private void renderListView(){
		subjectList=  (ListView)this.findViewById(R.id.subjectList);
        lineList = dbAdapter.selectAllLine();
        MyAdapter adapter = new MyAdapter(this);
        subjectList.setAdapter(adapter);
        
        OnItemClickListener listener =  new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				int id = lineList.get(position).getId();
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, id);
				startActivityForResult(intent, ACTIVITY_ITEMVIEW);
			}
        	
        };
        subjectList.setOnItemClickListener(listener);
	}
	
	public final class ViewHolder {
		public TextView tvLineName;
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return lineList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.atom_item, null);
				holder.tvLineName = (TextView) convertView.findViewById(R.id.tvLineName);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tvLineName.setText(lineList.get(position).getLineName());
			return convertView;
		}
	}
	
	/**
	 * 增加线路记录
	 */
	private void addLine(){
		Intent intent = new Intent(SubjectActivity.this, LineNameActivity.class);
		startActivityForResult(intent, ACTIVITY_ADD);
	}
}
