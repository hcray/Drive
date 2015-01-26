package com.daoliuhe.drive.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ListView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.adapter.ListViewActivityAdapter;

public class ScoringActivity extends Activity {
	private ListView leftListView;
	
	private ListView rightListView;
	
	private List<String> leftList = new ArrayList<String>();
	
	private List<String> rightList = new ArrayList<String>();
	
	//关闭页面标志
	private boolean closeFlag = true;
	
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
                //如果没有操作，则关闭
            	if(closeFlag){
                	finish();
                }
            }  
        }, 5000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoring, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(closeFlag){
			closeFlag = false;
		}
		return super.onTouchEvent(event);
	}

	private void initList(){
		
		String item1Name = this.getResources().getString(R.string.item1);
		String item2Name = this.getResources().getString(R.string.item2);
		String item3Name = this.getResources().getString(R.string.item3);
		String item4Name = this.getResources().getString(R.string.item4);
		String item5Name = this.getResources().getString(R.string.item5);
		String item6Name = this.getResources().getString(R.string.item6);
		String item7Name = this.getResources().getString(R.string.item7);
		String item8Name = this.getResources().getString(R.string.item8);
		String item9Name = this.getResources().getString(R.string.item9);
		String item10Name = this.getResources().getString(R.string.item10);
		String item11Name = this.getResources().getString(R.string.item11);
		String item12Name = this.getResources().getString(R.string.item12);
		String item13Name = this.getResources().getString(R.string.item13);
		
		leftList.add(item1Name );
		leftList.add(item2Name );
		leftList.add(item3Name );
		leftList.add(item4Name );
		leftList.add(item5Name );
		leftList.add(item6Name );
		leftList.add(item7Name );
		leftList.add(item8Name );
		leftList.add(item9Name );
		leftList.add(item10Name);
		leftList.add(item11Name);
		leftList.add(item12Name);
		leftList.add(item13Name);
		
		Resources res =getResources();
		String[] item1Detail = res.getStringArray(R.array.item1Detail);
		String[] item2Detail = res.getStringArray(R.array.item2Detail);
		String[] item3Detail = res.getStringArray(R.array.item3Detail);
		String[] item4Detail = res.getStringArray(R.array.item4Detail);
		String[] item5Detail = res.getStringArray(R.array.item5Detail);
		String[] item6Detail = res.getStringArray(R.array.item6Detail);
		String[] item7Detail = res.getStringArray(R.array.item7Detail);
		String[] item8Detail = res.getStringArray(R.array.item8Detail);
		String[] item9Detail = res.getStringArray(R.array.item9Detail);
		String[] item10Detail = res.getStringArray(R.array.item10Detail);
		String[] item11Detail = res.getStringArray(R.array.item11Detail);
		String[] item12Detail = res.getStringArray(R.array.item12Detail);
		String[] item13Detail = res.getStringArray(R.array.item13Detail);
		//添加项目明细
		rightList.addAll(Arrays.asList(item1Detail ));
		rightList.addAll(Arrays.asList(item2Detail ));
		rightList.addAll(Arrays.asList(item3Detail ));
		rightList.addAll(Arrays.asList(item4Detail ));
		rightList.addAll(Arrays.asList(item5Detail ));
		rightList.addAll(Arrays.asList(item6Detail ));
		rightList.addAll(Arrays.asList(item7Detail ));
		rightList.addAll(Arrays.asList(item8Detail ));
		rightList.addAll(Arrays.asList(item9Detail ));
		rightList.addAll(Arrays.asList(item10Detail));
		rightList.addAll(Arrays.asList(item11Detail));
		rightList.addAll(Arrays.asList(item12Detail));
		rightList.addAll(Arrays.asList(item13Detail));
	}

}
