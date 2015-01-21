package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.DbAdapter;

public class LineNameActivity extends Activity {
	
	//���水ť
	private Button btnLineNameSave;
	//ȡ����ť
	private Button btnLineNameCancel;

	//��������
	private EditText edtLineName;
	
	private DbAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line_name);
		//���ݿ�
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		btnLineNameSave = (Button) this.findViewById(R.id.btnLineNameSave);
		
		btnLineNameCancel = (Button) this.findViewById(R.id.btnLineNameCancel);
		
		edtLineName = (EditText) this.findViewById(R.id.edtLineName);
		
		
		btnLineNameSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String edtLineNameValue = edtLineName.getText().toString();
				//�ж�������û������
				if (edtLineNameValue == null || edtLineNameValue.isEmpty()) {
					Toast.makeText(getApplicationContext(), "��������Ч������", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//·��
				LineBean lineBean = new LineBean();
				lineBean.setLineName(edtLineNameValue);
				int id = dbAdapter.insertLine(lineBean);
				
				Intent mIntent = new Intent();
				mIntent.putExtra("id", id);
				mIntent.putExtra("name", edtLineNameValue);
				setResult(RESULT_OK, mIntent);
				finish();
				
			}
		});
		
		//ȡ��
		btnLineNameCancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.line_name, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(null != dbAdapter){
			dbAdapter.close();
		}
	}

}
