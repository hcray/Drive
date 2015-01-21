package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ListView;

import com.daoliuhe.drive.R;

public class ScoringActivity extends Activity {
	private ListView leftList;
	
	private ListView rightList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoring);
		
		leftList = (ListView) this.findViewById(R.id.leftList);
		
		rightList = (ListView) this.findViewById(R.id.rightList);
		
		
		//5√Î∫Ûπÿ±’
		Handler handler = new Handler();  
        handler.postDelayed(new Runnable() {  
            public void run() {  
                finish();
            }  
        }, 5000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoring, menu);
		return true;
	}

}
