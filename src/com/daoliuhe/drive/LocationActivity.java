package com.daoliuhe.drive;

import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocationActivity extends Activity {
	
	private EditText longitudeEdit;

	private EditText latitudeEdit;
	
	private Button btnLocalSave;

	private Button btnLocalCancel;
	
	private int btnId;
	
	private Double lat;
	
	private Double lng;
	
	private static final String TAG = "LocationActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			btnId = extras.getInt(CustomConstant.BUTTONID);
			lat = extras.getDouble(CustomConstant.LATITUDE);
			lng = extras.getDouble(CustomConstant.LONGITUDE);
			
			Log.d(TAG, "get from lineActivity btnId: " + btnId + " lat: " + lat + " lng: " + lng);
			
			
		}
		
		//经度
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		longitudeEdit.setText(lng.toString());
		//纬度
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		latitudeEdit.setText(lat.toString());
		//保存按钮
		btnLocalSave = (Button) this.findViewById(R.id.btnLocalSave);
		//取消按钮
		btnLocalCancel = (Button) this.findViewById(R.id.btnLocalCancel);
		
		btnLocalSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String longitudeEditValue = longitudeEdit.getText().toString();
				String latitudeEditValue = latitudeEdit.getText().toString();
				Log.d(TAG,"save onclick lat:"+latitudeEditValue + " lng:" + longitudeEditValue);
				
				//判断经度的输入是否为空
				if (longitudeEditValue == null 
						|| longitudeEditValue.equals("")
						|| longitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入有效的经度值", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断纬度的输入是否为空
				if (latitudeEditValue == null 
						|| latitudeEditValue.equals("")
						|| latitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入有效的纬度值", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				Intent mIntent = new Intent();
				//按钮id
				mIntent.putExtra(CustomConstant.BUTTONID , btnId);
				//经度
				mIntent.putExtra(CustomConstant.LONGITUDE, Double.parseDouble(longitudeEditValue));
				//纬度
				mIntent.putExtra(CustomConstant.LATITUDE, Double.parseDouble(latitudeEditValue));
				setResult(RESULT_OK, mIntent);
				finish();
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
