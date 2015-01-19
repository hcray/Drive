package com.daoliuhe.drive.ui;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.R.layout;
import com.daoliuhe.drive.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ScoringActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoring);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoring, menu);
		return true;
	}

}
