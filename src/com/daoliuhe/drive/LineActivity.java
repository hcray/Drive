package com.daoliuhe.drive;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class LineActivity extends Activity {
	public static final String SETTING_INFOS = "LineActivity";
	//private static final int ACTIVITY_LOGIN = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.line, menu);
		return true;
	}

}
