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
		
		//5���ر�
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
		leftList.add("�ϳ�׼��");
		leftList.add("��");
		leftList.add("ֱ����ʻ");
		leftList.add("�Ӽ���λ");
		leftList.add("�������");
		leftList.add("����ͣ��");
		leftList.add("ͨ��·��");
		leftList.add("ͨ�����к��");
		leftList.add("ͨ��ѧУ����");
		leftList.add("ͨ��������վ");
		leftList.add("�ᳵ");
		leftList.add("����");
		leftList.add("��ͷ");
		
		rightList.add("");
	}

}
