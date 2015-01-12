package com.daoliuhe.drive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	// ��Ŀ��
	private Button btnSubjectThree;
	// ��������
	private Button btnParamSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSubjectThree = (Button) this.findViewById(R.id.btnSubjectThree);
		btnSubjectThree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��Ŀ��·��
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SubjectActivity.class);
				startActivity(intent);
			}
		});

		btnParamSettings = (Button) this.findViewById(R.id.btnParamSettings);
		btnParamSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��������
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ParamActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}