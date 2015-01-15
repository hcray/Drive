package com.daoliuhe.drive;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LocationActivity extends Activity {
	
	private EditText longitudeEdit;

	private EditText latitudeEdit;
	
	private Button btnLocalSave;

	private Button btnLocalCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		//����
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		//γ��
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		//���水ť
		Button btnLocalSave = (Button) this.findViewById(R.id.btnLocalSave);
		//ȡ����ť
		Button btnLocalCancel = (Button) this.findViewById(R.id.btnLocalCancel);
		
		btnLocalSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				
			}});
		
		btnLocalCancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

}
