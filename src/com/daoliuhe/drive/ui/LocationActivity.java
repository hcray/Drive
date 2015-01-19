package com.daoliuhe.drive.ui;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.R.id;
import com.daoliuhe.drive.R.layout;
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
	
	private Button btnGetCurLocal;

	private Button btnLocalSave;

	private Button btnLocalCancel;
	
	private int btnId;
	
	private Double lat;
	
	private Double lng;
	//��ǰ�ľ���
	private Double curLongitude;
	//��ǰ��γ��
	private Double curLatitude;
	
	private static final String TAG = "LocationActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			btnId = extras.getInt(CustomConstant.BUTTONID);
			//����ľ�γ��
			lat = extras.getDouble(CustomConstant.LATITUDE);
			lng = extras.getDouble(CustomConstant.LONGITUDE);
			//��ǰ�ľ�γ��
			curLongitude = extras.getDouble(CustomConstant.CUR_LONGITUDE);
			curLatitude = extras.getDouble(CustomConstant.CUR_LATITUDE);
			Log.d(TAG, "get from lineActivity btnId: " + btnId + " lat: " + lat + " lng: " + lng + " curLongitude:" + curLongitude + " curLatitude:" + curLatitude);
		}
		
		//����
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		longitudeEdit.setText(lng.toString());
		//γ��
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		latitudeEdit.setText(lat.toString());
		//��ȡ��ǰλ��
		btnGetCurLocal = (Button) this.findViewById(R.id.btnGetCurLocal);
		//���水ť
		btnLocalSave = (Button) this.findViewById(R.id.btnLocalSave);
		//ȡ����ť
		btnLocalCancel = (Button) this.findViewById(R.id.btnLocalCancel);
		
		btnLocalSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String longitudeEditValue = longitudeEdit.getText().toString();
				String latitudeEditValue = latitudeEdit.getText().toString();
				Log.d(TAG,"save onclick lat:"+latitudeEditValue + " lng:" + longitudeEditValue);
				
				//�жϾ��ȵ������Ƿ�Ϊ��
				if (longitudeEditValue == null 
						|| longitudeEditValue.equals("")
						|| longitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "��������Ч�ľ���ֵ", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//�ж�γ�ȵ������Ƿ�Ϊ��
				if (latitudeEditValue == null 
						|| latitudeEditValue.equals("")
						|| latitudeEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "��������Ч��γ��ֵ", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				Intent mIntent = new Intent();
				//��ťid
				mIntent.putExtra(CustomConstant.BUTTONID , btnId);
				//����
				mIntent.putExtra(CustomConstant.LONGITUDE, Double.parseDouble(longitudeEditValue));
				//γ��
				mIntent.putExtra(CustomConstant.LATITUDE, Double.parseDouble(latitudeEditValue));
				setResult(RESULT_OK, mIntent);
				finish();
			}});
		
		btnGetCurLocal.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//��ǰ����
				longitudeEdit.setText(curLongitude.toString());
				//��ǰγ��
				latitudeEdit.setText(curLatitude.toString());
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
