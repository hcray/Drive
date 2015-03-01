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
import com.daoliuhe.drive.tools.DbAdapter;

public class LocationActivity extends Activity {
	
	private EditText longitudeEdit;

	private EditText latitudeEdit;

	private EditText bearingEdit;
	
	private Button btnGetCurLocal;

	private Button btnLocalSave;

	private Button btnLocalCancel;
	
	private int lineId;

	private int id;
	
	private static final String TAG = "LocationActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			//·��id
			lineId = extras.getInt(DbAdapter.KEY_LINEID);
			//����id
			id = extras.getInt(DbAdapter.KEY_ID);
		}
		
		//����
		longitudeEdit = (EditText) this.findViewById(R.id.longitude_edit);
		//γ��
		latitudeEdit = (EditText) this.findViewById(R.id.latitude_edit);
		//��λ
		bearingEdit = (EditText) this.findViewById(R.id.bearing_edit);
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
				String bearingEditValue = bearingEdit.getText().toString();
				Log.d(TAG,"save onclick lat:"+latitudeEditValue + " lng:" + longitudeEditValue + " bearing: " + bearingEditValue);
				
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
				
				//�ж�γ�ȵ������Ƿ�Ϊ��
				if (bearingEditValue == null 
						|| bearingEditValue.equals("")
						|| bearingEditValue.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "��������Ч�ķ�λ", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				Intent mIntent = new Intent();
				//·��id
				mIntent.putExtra(DbAdapter.KEY_LINEID , lineId);
				//����id
				mIntent.putExtra(DbAdapter.KEY_ID , id);
				//����
				mIntent.putExtra(CustomConstant.LONGITUDE, Double.parseDouble(longitudeEditValue));
				//γ��
				mIntent.putExtra(CustomConstant.LATITUDE, Double.parseDouble(latitudeEditValue));
				//��λ
				mIntent.putExtra(CustomConstant.BEARING, Float.parseFloat(bearingEditValue));
				setResult(RESULT_OK, mIntent);
				finish();
			}});
		
		btnGetCurLocal.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
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
