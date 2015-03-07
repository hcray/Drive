package com.daoliuhe.drive.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.adapter.ListViewActivityAdapter;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.bean.LocationBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;

public class LocationListActivity extends Activity {
	//list
	private ListView locationListView;
	//新增
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//取消
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private static final int ACTIVITY_ADD = 1;
	
	private static final int ACTIVITY_EDIT = ACTIVITY_ADD + 1;
	
	private DbAdapter dbAdapter;
	
	//private RelativeLayout  layout;
	
	private List<LocationBean> locationList;
	
	//返回按钮
	private Button btnLocationListReturn;
	//路线
	private LineBean lineBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_list);
		lineBean = new LineBean();
		
		//返回按钮
		btnLocationListReturn = (Button) this.findViewById(R.id.btnLocationListReturn);
		OnClickListener btnLocationListReturnClick = new OnClickListener(){
				@Override
				public void onClick(View v) {
				//取消
				finish();
			}
		};
		btnLocationListReturn.setOnClickListener(btnLocationListReturnClick);
		
		//layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
		//数据库
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			Integer id = extras.getInt(DbAdapter.KEY_ID);
			if(null != id){
				lineBean.setId(id);
			}
			String lineName = extras.getString(DbAdapter.KEY_LINE_NAME);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
		}
		
		//加载list列表
		renderListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.subject, menu);
		menu.add(0, MENU_ADD_ID, 0, R.string.menu_add_location);
		menu.add(0, MENU_BACK_ID, 0, R.string.menu_back);
		return true;
	}

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ADD_ID: // 新增
			addLocation();
			return true;
		case MENU_BACK_ID:// 返回
			LocationListActivity.this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ADD && resultCode == RESULT_OK){
			//刷新坐标点列表
			renderListView();
		}else if(requestCode == ACTIVITY_EDIT && resultCode == RESULT_OK){
			//刷新坐标点列表
			renderListView();
		}
	}
	
	private void renderListView(){
		locationListView=  (ListView)this.findViewById(R.id.locationList);
        int lineId = lineBean.getId();
		locationList = dbAdapter.selectLocationByLineId(lineId );
        List<String> viewList = new ArrayList<String>();
        for(LocationBean bean : locationList){
			viewList.add(bean.getId() + " 语音：" + LocationActivity.voiceType[bean.getVoiceType()] + " Longitude:"
					+ bean.getLongitude() + " Latitude:" + bean.getLatitude() + " Bearing:"
					+ bean.getBearing());
        }
        
        ListViewActivityAdapter adapter = new ListViewActivityAdapter(this, viewList);
        locationListView.setAdapter(adapter);
        
        OnItemClickListener listener =  new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				int id = locationList.get(position).getId();

				int voiceType = locationList.get(position).getVoiceType();
				
				Double longitude = locationList.get(position).getLongitude();
				
				Double latitude = locationList.get(position).getLatitude();
				
				float bearing = locationList.get(position).getBearing();
				
				Intent intent = new Intent();
				intent.setClass(LocationListActivity.this, LocationActivity.class);
				//坐标id
				intent.putExtra(DbAdapter.KEY_ID, id);
				//路线id
				intent.putExtra(DbAdapter.KEY_LINEID, lineBean.getId());

				intent.putExtra(CustomConstant.VOICETYPE, voiceType);
				
				intent.putExtra(CustomConstant.LONGITUDE, longitude);
				
				intent.putExtra(CustomConstant.LATITUDE, latitude);
				
				intent.putExtra(CustomConstant.BEARING, bearing);
				
				startActivityForResult(intent, ACTIVITY_EDIT);
			}
        	
        };
        locationListView.setOnItemClickListener(listener);
	}
	
	/**
	 * 增加坐标点
	 */
	private void addLocation(){
		Intent intent = new Intent(LocationListActivity.this, LocationActivity.class);
		intent.putExtra(DbAdapter.KEY_LINEID, lineBean.getId());
		startActivityForResult(intent, ACTIVITY_ADD);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbAdapter.close();
	}
	
	
}
