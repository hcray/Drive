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
import com.daoliuhe.drive.tools.DbAdapter;

public class SubjectActivity extends Activity {
	//list
	private ListView subjectList;
	//新增
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//取消
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private static final int ACTIVITY_ADD = 1;
	
	private static final int ACTIVITY_ITEMVIEW = ACTIVITY_ADD + 1;
	
	private DbAdapter dbAdapter;
	
	//private RelativeLayout  layout;
	
	private List<LineBean> lineList;
	
	//返回按钮
	private Button btnSubjectReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		
		btnSubjectReturn = (Button) this.findViewById(R.id.btnSubjectReturn);
		OnClickListener btnSubjectReturnClick = new OnClickListener(){
				@Override
				public void onClick(View v) {
				//取消
				finish();
			}
		};
		btnSubjectReturn.setOnClickListener(btnSubjectReturnClick);
		
		//layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
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
			//刷新路线列表
			renderListView();
		}
	}
	
	private void renderListView(){
		subjectList=  (ListView)this.findViewById(R.id.subjectList);
        lineList = dbAdapter.selectAllLine();
        List<String> viewList = new ArrayList<String>();
        for(LineBean bean : lineList){
        	viewList.add(bean.getLineName());
        }
        
        ListViewActivityAdapter adapter = new ListViewActivityAdapter(this, viewList);
        subjectList.setAdapter(adapter);
        
        OnItemClickListener listener =  new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				int id = lineList.get(position).getId();
				String lineName = lineList.get(position).getLineName();
				
				Double changeLanesLat = lineList.get(position).getChangeLanesLat();
				Double changeLanesLng = lineList.get(position).getChangeLanesLng();
				Double aheadDirectLineLat = lineList.get(position).getAheadDirectLineLat();
				Double aheadDirectLineLng = lineList.get(position).getAheadDirectLineLng();
				Double turnLeftLat = lineList.get(position).getTurnLat();
				Double turnLeftLng = lineList.get(position).getTurnLng();
				Double turnRightLat = lineList.get(position).getTurnRightLat();
				Double turnRightLng = lineList.get(position).getTurnRightLng();
				Double sidewalkLat = lineList.get(position).getSidewalkLat();
				Double sidewalkLng = lineList.get(position).getSidewalkLng();
				
				Double passSchoolLat = lineList.get(position).getPassSchoolLat();
				Double passSchoolLng = lineList.get(position).getPassSchoolLng();
				Double passBusStationLat = lineList.get(position).getPassBusStationLat();
				Double passBusStationLng = lineList.get(position).getPassBusStationLng();
				Double passSidewalkLat = lineList.get(position).getPassSidewalkLat();
				Double passSidewalkLng = lineList.get(position).getPassSidewalkLng();
				Double directLineLat = lineList.get(position).getDirectLineLat();
				Double directLineLng = lineList.get(position).getDirectLineLng();
				Double endDirectLineLat = lineList.get(position).getEndDirectLineLat();
				Double endDirectLineLng = lineList.get(position).getEndDirectLineLng();
				
				Double overtakeLat = lineList.get(position).getOvertakeLat();
				Double overtakeLng = lineList.get(position).getOvertakeLng();
				Double turnLat = lineList.get(position).getTurnLat();
				Double turnLng = lineList.get(position).getTurnLng();
				Double pullOverLat = lineList.get(position).getPullOverLat();
				Double pullOverLng = lineList.get(position).getPullOverLng();
				Double passingLat = lineList.get(position).getPassingLat();
				Double passingLng = lineList.get(position).getPassingLng();
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, id);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, lineName);
				
				if(null != changeLanesLat){
					intent.putExtra(DbAdapter.KEY_CHANGELANES_LAT, changeLanesLat);
				}
				if(null != changeLanesLng){
					intent.putExtra(DbAdapter.KEY_CHANGELANES_LNG, changeLanesLng);
				}
				if(null != aheadDirectLineLat){
					intent.putExtra(DbAdapter.KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
				}
				if(null != aheadDirectLineLng){
					intent.putExtra(DbAdapter.KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
				}
				if(null != turnLeftLat){
					intent.putExtra(DbAdapter.KEY_TURNLEFT_LAT, turnLeftLat);
				}
				if(null != turnLeftLng){
					intent.putExtra(DbAdapter.KEY_TURNLEFT_LNG, turnLeftLng);
				}
				if(null != turnRightLat){
					intent.putExtra(DbAdapter.KEY_TURNRIGHT_LAT, turnRightLat);
				}
				if(null != turnRightLng){
					intent.putExtra(DbAdapter.KEY_TURNRIGHT_LNG, turnRightLng);
				}
				if(null != sidewalkLat){
					intent.putExtra(DbAdapter.KEY_SIDEWALK_LAT, sidewalkLat);
				}
				if(null != sidewalkLng){
					intent.putExtra(DbAdapter.KEY_SIDEWALK_LNG, sidewalkLng);
				}
				if(null != passSchoolLat){
					intent.putExtra(DbAdapter.KEY_PASSSCHOOL_LAT, passSchoolLat);
				}
				if(null != passSchoolLng){
					intent.putExtra(DbAdapter.KEY_PASSSCHOOL_LNG, passSchoolLng);
				}
				if(null != passBusStationLat){
					intent.putExtra(DbAdapter.KEY_PASSBUSSTATION_LAT, passBusStationLat);
				}
				if(null != passBusStationLng){
					intent.putExtra(DbAdapter.KEY_PASSBUSSTATION_LNG, passBusStationLng);
				}
				if(null != passSidewalkLat){
					intent.putExtra(DbAdapter.KEY_PASSSIDEWALK_LAT, passSidewalkLat);
				}
				if(null != passSidewalkLng){
					intent.putExtra(DbAdapter.KEY_PASSSIDEWALK_LNG, passSidewalkLng);
				}
				if(null != directLineLat){
					intent.putExtra(DbAdapter.KEY_DIRECTLINE_LAT, directLineLat);
				}
				if(null != directLineLng){
					intent.putExtra(DbAdapter.KEY_DIRECTLINE_LNG, directLineLng);
				}
				if(null != endDirectLineLat){
					intent.putExtra(DbAdapter.KEY_ENDDIRECTLINE_LAT, endDirectLineLat);
				}
				if(null != endDirectLineLng){
					intent.putExtra(DbAdapter.KEY_ENDDIRECTLINE_LNG, endDirectLineLng);
				}
				if(null != overtakeLat){
					intent.putExtra(DbAdapter.KEY_OVERTAKE_LAT, overtakeLat);
				}
				if(null != overtakeLng){
					intent.putExtra(DbAdapter.KEY_OVERTAKE_LNG, overtakeLng);
				}
				if(null != turnLat){
					intent.putExtra(DbAdapter.KEY_TURN_LAT, turnLat);
				}
				if(null != turnLng){
					intent.putExtra(DbAdapter.KEY_TURN_LNG, turnLng);
				}
				if(null != pullOverLat){
					intent.putExtra(DbAdapter.KEY_PULLOVER_LAT, pullOverLat);
				}
				if(null != pullOverLng){
					intent.putExtra(DbAdapter.KEY_PULLOVER_LNG, pullOverLng);
				}
				if(null != passingLat){
					intent.putExtra(DbAdapter.KEY_PASSING_LAT, passingLat);
				}
				if(null != passingLng){
					intent.putExtra(DbAdapter.KEY_PASSING_LNG, passingLng);
				}
				startActivityForResult(intent, ACTIVITY_ITEMVIEW);
			}
        	
        };
        subjectList.setOnItemClickListener(listener);
	}
	
	/**
	 * 增加线路记录
	 */
	private void addLine(){
		Intent intent = new Intent(SubjectActivity.this, LineNameActivity.class);
		startActivityForResult(intent, ACTIVITY_ADD);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbAdapter.close();
	}
	
	
}
