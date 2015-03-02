package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;

public class SubjectActivity extends Activity {
	//list
//	private ListView subjectList;
	//����
//	private static final int MENU_ADD_ID = Menu.FIRST; 
	//ȡ��
//	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
//	private static final int ACTIVITY_ADD = 1;
	
//	private static final int ACTIVITY_ITEMVIEW = ACTIVITY_ADD + 1;
	
	private DbAdapter dbAdapter;
	
	//private RelativeLayout  layout;
	
//	private List<LineBean> lineList;
	
	private Button btnLine1;
	
	private Button btnLine2;
	
	private Button btnLine3;
	
	private Button btnLine4;
	
	//���ذ�ť
	private Button btnSubjectReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		// ���ݿ�
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		btnSubjectReturn = (Button) this.findViewById(R.id.btnSubjectReturn);
		OnClickListener btnSubjectReturnClick = new OnClickListener(){
				@Override
				public void onClick(View v) {
				//ȡ��
				finish();
			}
		};
		btnSubjectReturn.setOnClickListener(btnSubjectReturnClick);
		
		btnLine1 = (Button) this.findViewById(R.id.btnLine1);
		btnLine1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				LineBean lineBean = dbAdapter.selectLineBeanById(CustomConstant.S3LINE1ID);
				if(lineBean == null){
					lineBean = new LineBean();
					lineBean.setId(CustomConstant.S3LINE1ID);
					lineBean.setLineName(btnLine1.getText().toString());
					dbAdapter.insertLine(lineBean);
				}
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, CustomConstant.S3LINE1ID);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, btnLine1.getText());
				
				startActivity(intent);
			}
		});
		
		btnLine2 = (Button) this.findViewById(R.id.btnLine2);
		btnLine2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				LineBean lineBean = dbAdapter.selectLineBeanById(CustomConstant.S3LINE2ID);
				if(lineBean == null){
					lineBean = new LineBean();
					lineBean.setId(CustomConstant.S3LINE2ID);
					lineBean.setLineName(btnLine2.getText().toString());
					dbAdapter.insertLine(lineBean);
				}
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, CustomConstant.S3LINE2ID);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, btnLine2.getText());
				startActivity(intent);
			}
		});
		
		btnLine3 = (Button) this.findViewById(R.id.btnLine3);
		btnLine3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				LineBean lineBean = dbAdapter.selectLineBeanById(CustomConstant.S3LINE3ID);
				if(lineBean == null){
					lineBean = new LineBean();
					lineBean.setId(CustomConstant.S3LINE3ID);
					lineBean.setLineName(btnLine3.getText().toString());
					dbAdapter.insertLine(lineBean);
				}
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, CustomConstant.S3LINE3ID);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, btnLine3.getText());
				startActivity(intent);
			}
		});
		
		btnLine4 = (Button) this.findViewById(R.id.btnLine4);
		btnLine4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				LineBean lineBean = dbAdapter.selectLineBeanById(CustomConstant.S3LINE4ID);
				if(lineBean == null){
					lineBean = new LineBean();
					lineBean.setId(CustomConstant.S3LINE4ID);
					lineBean.setLineName(btnLine4.getText().toString());
					dbAdapter.insertLine(lineBean);
				}
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, CustomConstant.S3LINE4ID);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, btnLine4.getText());
				startActivity(intent);
			}
		});
		
		//layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
		//���ݿ�
		//dbAdapter = new DbAdapter(this);
		//dbAdapter.open();
		//����list�б�
		//renderListView();
	}

	/*
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
				//String comment = lineList.get(position).getComment();
				
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, id);
				intent.putExtra(DbAdapter.KEY_LINE_NAME, lineName);
				
				startActivityForResult(intent, ACTIVITY_ITEMVIEW);
			}
        	
        };
        subjectList.setOnItemClickListener(listener);
	}
	*/
	/**
	 * ������·��¼
	 */
//	private void addLine(){
//		Intent intent = new Intent(SubjectActivity.this, LineNameActivity.class);
//		startActivityForResult(intent, ACTIVITY_ADD);
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbAdapter.close();
	}
	
	
}
