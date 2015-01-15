package com.daoliuhe.drive;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.DbAdapter;

public class SubjectActivity extends Activity {
	// ·��1
	//private Button btnPath1;
	// ·��2
	//private Button btnPath2;
	// ·��3
	//private Button btnPath3;
	
	//����
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//ȡ��
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private final int ACTIVITY_ADD = 1;
	
	private DbAdapter dbAdapter;
	
	private RelativeLayout  layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		layout = (RelativeLayout ) this.findViewById(R.id.subject_relativeLayout);
		//���ݿ�
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		List<LineBean> list = dbAdapter.selectAllLine();
		for(LineBean listBean : list){
			Button button = new Button(this);
			button.setId(listBean.getId());
			button.setText(listBean.getLineName());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
//	        lp1.addRule(RelativeLayout.ALIGN_TOP);  
//	        lp1.setMargins(30, 50, 100, 100);//(int left, int top, int right, int bottom)  
	        params.leftMargin=30;  
	        params.topMargin = 100;
	        
	        button.setLayoutParams(params);
	        //lp1.height = lp1.WRAP_CONTENT;
			//����ť����layout���
			layout.addView(button);
		}
		
		Button button = new Button(this);
		button.setId(10000);
		button.setText("����1");
		layout.addView(button); 
		
		/*
		btnPath1 = (Button) this.findViewById(R.id.btnPath1);

		btnPath2 = (Button) this.findViewById(R.id.btnPath2);

		btnPath3 = (Button) this.findViewById(R.id.btnPath3);

		OnClickListener btnPath1Click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				startActivity(intent);
			}
		};
		btnPath1.setOnClickListener(btnPath1Click);

		OnClickListener btnPath2Click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				startActivity(intent);
			}
		};
		btnPath2.setOnClickListener(btnPath2Click);

		OnClickListener btnPath3Click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SubjectActivity.this, LineActivity.class);
				startActivity(intent);
			}
		};
		btnPath3.setOnClickListener(btnPath3Click);
		*/
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
			int retId = data.getIntExtra("id", -1);
			String retName = data.getStringExtra("name");
			if(retId != -1){
				Button button = new Button(this);
				button.setId(retId);
				button.setText(retName);
				//����ť����layout���
				layout.addView(button);   
			}
		}
	}
	
	
	
	
	/**
	 * ������·��¼
	 */
	private void addLine(){
		Intent intent = new Intent(SubjectActivity.this, LineNameActivity.class);
		startActivityForResult(intent, ACTIVITY_ADD);
	}
}
