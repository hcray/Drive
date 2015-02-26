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
	//����
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//ȡ��
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private static final int ACTIVITY_ADD = 1;
	
	private static final int ACTIVITY_ITEMVIEW = ACTIVITY_ADD + 1;
	
	private DbAdapter dbAdapter;
	
	//private RelativeLayout  layout;
	
	private List<LineBean> lineList;
	
	//���ذ�ť
	private Button btnSubjectReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		
		btnSubjectReturn = (Button) this.findViewById(R.id.btnSubjectReturn);
		OnClickListener btnSubjectReturnClick = new OnClickListener(){
				@Override
				public void onClick(View v) {
				//ȡ��
				finish();
			}
		};
		btnSubjectReturn.setOnClickListener(btnSubjectReturnClick);
		
		//layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
		//���ݿ�
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		//����list�б�
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
		case MENU_ADD_ID: // ����
			addLine();
			return true;
		case MENU_BACK_ID:// ����
			SubjectActivity.this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ADD && resultCode == RESULT_OK){
			//ˢ��·���б�
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
				Float changeLanesBr = lineList.get(position).getChangeLanesBr();
				Double aheadDirectLineLat = lineList.get(position).getAheadDirectLineLat();
				Double aheadDirectLineLng = lineList.get(position).getAheadDirectLineLng();
				Float aheadDirectLineBr = lineList.get(position).getAheadDirectLineBr();
				Double turnLeftLat = lineList.get(position).getTurnLat();
				Double turnLeftLng = lineList.get(position).getTurnLng();
				Float turnLeftBr = lineList.get(position).getTurnBr();
				Double turnRightLat = lineList.get(position).getTurnRightLat();
				Double turnRightLng = lineList.get(position).getTurnRightLng();
				Float turnRightBr = lineList.get(position).getTurnRightBr();
				Double sidewalkLat = lineList.get(position).getSidewalkLat();
				Double sidewalkLng = lineList.get(position).getSidewalkLng();
				Float sidewalkBr = lineList.get(position).getSidewalkBr();
				
				Double passSchoolLat = lineList.get(position).getPassSchoolLat();
				Double passSchoolLng = lineList.get(position).getPassSchoolLng();
				Float passSchoolBr = lineList.get(position).getPassSchoolBr();
				Double passBusStationLat = lineList.get(position).getPassBusStationLat();
				Double passBusStationLng = lineList.get(position).getPassBusStationLng();
				Float passBusStationBr = lineList.get(position).getPassBusStationBr();
				Double passSidewalkLat = lineList.get(position).getPassSidewalkLat();
				Double passSidewalkLng = lineList.get(position).getPassSidewalkLng();
				Float passSidewalkBr = lineList.get(position).getPassSidewalkBr();
				Double directLineLat = lineList.get(position).getDirectLineLat();
				Double directLineLng = lineList.get(position).getDirectLineLng();
				Float directLineBr = lineList.get(position).getDirectLineBr();
				Double endDirectLineLat = lineList.get(position).getEndDirectLineLat();
				Double endDirectLineLng = lineList.get(position).getEndDirectLineLng();
				Float endDirectLineBr = lineList.get(position).getEndDirectLineBr();
				
				Double overtakeLat = lineList.get(position).getOvertakeLat();
				Double overtakeLng = lineList.get(position).getOvertakeLng();
				Float overtakeBr = lineList.get(position).getOvertakeBr();
				Double turnLat = lineList.get(position).getTurnLat();
				Double turnLng = lineList.get(position).getTurnLng();
				Float turnBr = lineList.get(position).getTurnBr();
				Double pullOverLat = lineList.get(position).getPullOverLat();
				Double pullOverLng = lineList.get(position).getPullOverLng();
				Float pullOverBr = lineList.get(position).getPullOverBr();
				Double passingLat = lineList.get(position).getPassingLat();
				Double passingLng = lineList.get(position).getPassingLng();
				Float passingBr = lineList.get(position).getPassingBr();
				
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
				if(null != changeLanesBr){
					intent.putExtra(DbAdapter.KEY_CHANGELANES_BR, changeLanesBr);
				}
				if(null != aheadDirectLineLat){
					intent.putExtra(DbAdapter.KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
				}
				if(null != aheadDirectLineLng){
					intent.putExtra(DbAdapter.KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
				}
				if(null != aheadDirectLineBr){
					intent.putExtra(DbAdapter.KEY_AHEADDIRECTLINE_BR, aheadDirectLineBr);
				}
				if(null != turnLeftLat){
					intent.putExtra(DbAdapter.KEY_TURNLEFT_LAT, turnLeftLat);
				}
				if(null != turnLeftLng){
					intent.putExtra(DbAdapter.KEY_TURNLEFT_LNG, turnLeftLng);
				}
				if(null != turnLeftBr){
					intent.putExtra(DbAdapter.KEY_TURNLEFT_BR, turnLeftBr);
				}
				if(null != turnRightLat){
					intent.putExtra(DbAdapter.KEY_TURNRIGHT_LAT, turnRightLat);
				}
				if(null != turnRightLng){
					intent.putExtra(DbAdapter.KEY_TURNRIGHT_LNG, turnRightLng);
				}
				if(null != turnRightBr){
					intent.putExtra(DbAdapter.KEY_TURNRIGHT_BR, turnRightBr);
				}
				if(null != sidewalkLat){
					intent.putExtra(DbAdapter.KEY_SIDEWALK_LAT, sidewalkLat);
				}
				if(null != sidewalkLng){
					intent.putExtra(DbAdapter.KEY_SIDEWALK_LNG, sidewalkLng);
				}
				if(null != sidewalkBr){
					intent.putExtra(DbAdapter.KEY_SIDEWALK_BR, sidewalkBr);
				}
				if(null != passSchoolLat){
					intent.putExtra(DbAdapter.KEY_PASSSCHOOL_LAT, passSchoolLat);
				}
				if(null != passSchoolLng){
					intent.putExtra(DbAdapter.KEY_PASSSCHOOL_LNG, passSchoolLng);
				}
				if(null != passSchoolBr){
					intent.putExtra(DbAdapter.KEY_PASSSCHOOL_BR, passSchoolBr);
				}
				if(null != passBusStationLat){
					intent.putExtra(DbAdapter.KEY_PASSBUSSTATION_LAT, passBusStationLat);
				}
				if(null != passBusStationLng){
					intent.putExtra(DbAdapter.KEY_PASSBUSSTATION_LNG, passBusStationLng);
				}
				if(null != passBusStationBr){
					intent.putExtra(DbAdapter.KEY_PASSBUSSTATION_BR, passBusStationBr);
				}
				if(null != passSidewalkLat){
					intent.putExtra(DbAdapter.KEY_PASSSIDEWALK_LAT, passSidewalkLat);
				}
				if(null != passSidewalkLng){
					intent.putExtra(DbAdapter.KEY_PASSSIDEWALK_LNG, passSidewalkLng);
				}
				if(null != passSidewalkBr){
					intent.putExtra(DbAdapter.KEY_PASSSIDEWALK_BR, passSidewalkBr);
				}
				if(null != directLineLat){
					intent.putExtra(DbAdapter.KEY_DIRECTLINE_LAT, directLineLat);
				}
				if(null != directLineLng){
					intent.putExtra(DbAdapter.KEY_DIRECTLINE_LNG, directLineLng);
				}
				if(null != directLineBr){
					intent.putExtra(DbAdapter.KEY_DIRECTLINE_BR, directLineBr);
				}
				if(null != endDirectLineLat){
					intent.putExtra(DbAdapter.KEY_ENDDIRECTLINE_LAT, endDirectLineLat);
				}
				if(null != endDirectLineLng){
					intent.putExtra(DbAdapter.KEY_ENDDIRECTLINE_LNG, endDirectLineLng);
				}
				if(null != endDirectLineBr){
					intent.putExtra(DbAdapter.KEY_ENDDIRECTLINE_BR, endDirectLineBr);
				}
				if(null != overtakeLat){
					intent.putExtra(DbAdapter.KEY_OVERTAKE_LAT, overtakeLat);
				}
				if(null != overtakeLng){
					intent.putExtra(DbAdapter.KEY_OVERTAKE_LNG, overtakeLng);
				}
				if(null != overtakeBr){
					intent.putExtra(DbAdapter.KEY_OVERTAKE_BR, overtakeBr);
				}
				if(null != turnLat){
					intent.putExtra(DbAdapter.KEY_TURN_LAT, turnLat);
				}
				if(null != turnLng){
					intent.putExtra(DbAdapter.KEY_TURN_LNG, turnLng);
				}
				if(null != turnBr){
					intent.putExtra(DbAdapter.KEY_TURN_BR, turnBr);
				}
				if(null != pullOverLat){
					intent.putExtra(DbAdapter.KEY_PULLOVER_LAT, pullOverLat);
				}
				if(null != pullOverLng){
					intent.putExtra(DbAdapter.KEY_PULLOVER_LNG, pullOverLng);
				}
				if(null != pullOverBr){
					intent.putExtra(DbAdapter.KEY_PULLOVER_BR, pullOverBr);
				}
				if(null != passingLat){
					intent.putExtra(DbAdapter.KEY_PASSING_LAT, passingLat);
				}
				if(null != passingLng){
					intent.putExtra(DbAdapter.KEY_PASSING_LNG, passingLng);
				}
				if(null != passingBr){
					intent.putExtra(DbAdapter.KEY_PASSING_BR, passingBr);
				}
				startActivityForResult(intent, ACTIVITY_ITEMVIEW);
			}
        	
        };
        subjectList.setOnItemClickListener(listener);
	}
	
	/**
	 * ������·��¼
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
