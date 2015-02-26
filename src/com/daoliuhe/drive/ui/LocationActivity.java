package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.tools.CustomConstant;

public class LocationActivity extends Activity {
	
	private EditText longitudeEdit;

	private EditText latitudeEdit;

	private EditText bearingEdit;
	
	private Button btnGetCurLocal;

	private Button btnLocalSave;

	private Button btnLocalCancel;
	
	private int btnId;
	
	private Double lat;
	
	private Double lng;

	private Float bearing;
	//当前的经度
	private Double curLongitude;
	//当前的纬度
	private Double curLatitude;

	//当前的纬度
	private Float curBearing;
	
	private static final String TAG = "LocationActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			btnId = extras.getInt(CustomConstant.BUTTONID);
			//保存的经纬度
			lat = extras.getDouble(CustomConstant.LATITUDE);
			lng = extras.getDouble(CustomConstant.LONGITUDE);
			bearing = extras.getFloat(CustomConstant.BEARING);
			
			//当前的经纬度
			curLongitude = extras.getDouble(CustomConstant.CUR_LONGITUDE);
			curLatitude = extras.getDouble(CustomConstant.CUR_LATITUDE);
			curBearing = extras.getFloat(CustomConstant.CUR_BEARING);
			Log.d(TAG, "get from lineActivity btnId: " + btnId 
					+ " lat: " + lat
					+ " lng: " + lng 
					+ " bearing: " + bearing 
					+ " curLongitude:" + curLongitude
					+ " curBearing:" + curBearing
					+ " curLatitude:" + curLatitude);
		}
		
		//经度
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		longitudeEdit.setText(lng.toString());
		//纬度
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		latitudeEdit.setText(lat.toString());
		//方位
		bearingEdit = (EditText) this.findViewById(R.id.bearing_edit);
		bearingEdit.setText(bearing.toString());
		//获取当前位置
		btnGetCurLocal = (Button) this.findViewById(R.id.btnGetCurLocal);
		//保存按钮
		btnLocalSave = (Button) this.findViewById(R.id.btnLocalSave);
		//取消按钮
		btnLocalCancel = (Button) this.findViewById(R.id.btnLocalCancel);
		
		btnLocalSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String longitudeEditValue = longitudeEdit.getText().toString();
				String latitudeEditValue = latitudeEdit.getText().toString();
				String bearingEditValue = bearingEdit.getText().toString();
				Log.d(TAG,"save onclick lat:"+latitudeEditValue + " lng:" + longitudeEditValue + " bearing: " + bearingEditValue);
				
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
				
				//判断纬度的输入是否为空
				if (bearingEditValue == null 
						|| bearingEditValue.equals("")
						|| bearingEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入有效的方位", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				Intent mIntent = new Intent();
				//按钮id
				mIntent.putExtra(CustomConstant.BUTTONID , btnId);
				//经度
				mIntent.putExtra(CustomConstant.LONGITUDE, Double.parseDouble(longitudeEditValue));
				//纬度
				mIntent.putExtra(CustomConstant.LATITUDE, Double.parseDouble(latitudeEditValue));
				//方位
				mIntent.putExtra(CustomConstant.BEARING, Float.parseFloat(bearingEditValue));
				setResult(RESULT_OK, mIntent);
				finish();
			}});
		
		btnGetCurLocal.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//当前经度
				longitudeEdit.setText(curLongitude.toString());
				//当前纬度
				latitudeEdit.setText(curLatitude.toString());
				//当前方位
				bearingEdit.setText(curBearing.toString());
			}
		});
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
