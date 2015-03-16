package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LocationBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;

public class LocationActivity extends Activity {
	
	private EditText longitudeEdit;

	private EditText latitudeEdit;

	private EditText bearingEdit;
	
	private Spinner spinnerVoiceType;
	
	//private Button btnGetCurLocal;

	private Button btnLocalSave;

	private Button btnLocalCancel;
	
	private int lineId;

	private int id;
	
	private static final String TAG = "LocationActivity";
	
	private DbAdapter dbAdapter;
	
	private ArrayAdapter<CharSequence> adapterVoiceType = null;

	private String[] voiceType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		voiceType = new String[]{ "",
				getResources().getString(R.string.location_voiceType_01), getResources().getString(R.string.location_voiceType_02),
				getResources().getString(R.string.location_voiceType_03), getResources().getString(R.string.location_voiceType_04),
				getResources().getString(R.string.location_voiceType_05), getResources().getString(R.string.location_voiceType_06),
				getResources().getString(R.string.location_voiceType_07), getResources().getString(R.string.location_voiceType_08),
				getResources().getString(R.string.location_voiceType_09), getResources().getString(R.string.location_voiceType_10),
				getResources().getString(R.string.location_voiceType_11), getResources().getString(R.string.location_voiceType_12),
				getResources().getString(R.string.location_voiceType_13), getResources().getString(R.string.location_voiceType_14),
				getResources().getString(R.string.location_voiceType_15) };
		
		//数据库
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		Bundle extras = this.getIntent().getExtras();
		
		//经度
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		//纬度
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		//方位
		bearingEdit = (EditText) this.findViewById(R.id.bearing_edit);
		//语音类型
		spinnerVoiceType = (Spinner) this.findViewById(R.id.spinnerVoiceType);
		//获取当前位置
		//btnGetCurLocal = (Button) this.findViewById(R.id.btnGetCurLocal);
		//保存按钮
		btnLocalSave = (Button) this.findViewById(R.id.btnLocalSave);
		//取消按钮
		btnLocalCancel = (Button) this.findViewById(R.id.btnLocalCancel);
		
		//将数据cityInfo填充到适配器adapterCity中
		adapterVoiceType = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, voiceType);
		//设置下拉框的数据适配器adapterCity
		this.spinnerVoiceType.setAdapter(adapterVoiceType);
		
		if (extras != null) {
			//路线id
			lineId = extras.getInt(DbAdapter.KEY_LINEID);
			//坐标id
			id = extras.getInt(DbAdapter.KEY_ID, 0);
			if(id > 0){
				Integer voiceType = extras.getInt(CustomConstant.VOICETYPE);
				
				spinnerVoiceType.setSelection(voiceType, true);
				
				Double longitude = extras.getDouble(CustomConstant.LONGITUDE);
				longitudeEdit.setText(longitude.toString());
				
				Double latitude = extras.getDouble(CustomConstant.LATITUDE);
				latitudeEdit.setText(latitude.toString());
				
				Float bearing = extras.getFloat(CustomConstant.BEARING);
				bearingEdit.setText(bearing.toString());
				
			}
		}
		
		
		
		btnLocalSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String longitudeEditValue = longitudeEdit.getText().toString();
				String latitudeEditValue = latitudeEdit.getText().toString();
				String bearingEditValue = bearingEdit.getText().toString();
				Log.d(TAG,"save onclick lat:"+latitudeEditValue + " lng:" + longitudeEditValue + " bearing: " + bearingEditValue);
				//选择的语音类型
				int pos = spinnerVoiceType.getSelectedItemPosition();
				
				//判断经度的输入是否为空
				if (longitudeEditValue == null 
						|| longitudeEditValue.equals("")
						|| longitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.location_input_longitude), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断纬度的输入是否为空
				if (latitudeEditValue == null 
						|| latitudeEditValue.equals("")
						|| latitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.location_input_latitude), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断纬度的输入是否为空
				if (bearingEditValue == null 
						|| bearingEditValue.equals("")
						|| bearingEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.location_input_bearing), Toast.LENGTH_SHORT).show();
					return ;
				}

				//判断语音的选择
				if(pos == 0){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.location_input_voiceType), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				LocationBean locationBean = new LocationBean();
				locationBean.setLineId(lineId);
				Double longitude = Double.parseDouble(longitudeEditValue);
				Double latitude = Double.parseDouble(latitudeEditValue);
				Float bearing = Float.parseFloat(bearingEditValue);
				
				locationBean.setLongitude(longitude);
				locationBean.setLatitude(latitude);
				locationBean.setBearing(bearing);
				locationBean.setVoiceType(pos);
				
				if(id == 0){
					//添加坐标
					dbAdapter.insertLocation(locationBean);
				}else{
					//更新坐标
					locationBean.setId(id);
					dbAdapter.updateLocation(locationBean);
				}
				
				Intent mIntent = new Intent();
				//路线id
				mIntent.putExtra(DbAdapter.KEY_LINEID , lineId);
				//坐标id
				mIntent.putExtra(DbAdapter.KEY_ID , id);
				//经度
				mIntent.putExtra(CustomConstant.LONGITUDE, Double.parseDouble(longitudeEditValue));
				//纬度
				mIntent.putExtra(CustomConstant.LATITUDE, Double.parseDouble(latitudeEditValue));
				//方位
				mIntent.putExtra(CustomConstant.BEARING, Float.parseFloat(bearingEditValue));
				setResult(RESULT_OK, mIntent);
				finish();
			}});
		/*
		btnGetCurLocal.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
			}
		});
		*/
		btnLocalCancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(null != dbAdapter){
			dbAdapter.close();
		}
	}
	
}
