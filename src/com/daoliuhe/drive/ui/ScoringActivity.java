package com.daoliuhe.drive.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ListView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.adapter.ListViewActivityAdapter;

public class ScoringActivity extends Activity {
	private ListView leftListView;
	
	private ListView rightListView;
	
	private List<String> leftList = new ArrayList<String>();
	
	private List<String> rightList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoring);

		initList();
		
		leftListView = (ListView) this.findViewById(R.id.leftList);
		
		rightListView = (ListView) this.findViewById(R.id.rightList);
		
		ListViewActivityAdapter adapterLeft = new ListViewActivityAdapter(this, leftList);
		leftListView.setAdapter(adapterLeft);
	
		ListViewActivityAdapter adapterRight = new ListViewActivityAdapter(this, rightList);
		rightListView.setAdapter(adapterRight);
		
		//5秒后关闭
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
	
	private void initList(){
		leftList.add("上车准备");
		leftList.add("起步");
		leftList.add("直线行驶");
		leftList.add("加减挡位");
		leftList.add("变更车道");
		leftList.add("靠边停车");
		leftList.add("通过路口");
		leftList.add("通过人行横道");
		leftList.add("通过学校区域");
		leftList.add("通过公交车站");
		leftList.add("会车");
		leftList.add("超车");
		leftList.add("掉头");
		
		rightList.add("");
	}

}
