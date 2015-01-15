package com.daoliuhe.drive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SubjectActivity extends Activity {
	// 路线1
	private Button btnPath1;
	// 路线2
	private Button btnPath2;
	// 路线3
	private Button btnPath3;
	
	//新增
	private static final int MENU_ADD_ID = Menu.FIRST; 
	//取消
	private static final int MENU_BACK_ID = Menu.FIRST + 1;
	
	private final int ACTIVITY_ADD = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		
		

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
	
	
	/**
	 * 修改这条记录
	 */
	private void addLine(){
		Intent intent = new Intent(SubjectActivity.this, LineNameActivity.class);
		startActivityForResult(intent, ACTIVITY_ADD);
	}
}
