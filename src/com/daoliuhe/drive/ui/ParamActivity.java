package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.tools.CustomConstant;

public class ParamActivity extends Activity {
	public static final String SETTING_INFOS = "SETTING_INFOS";
	//private static final int ACTIVITY_LOGIN = 0;
	//���水ť
	private Button btnParamSave;
	//ȡ����ť
	private Button btnParamCancel;

	//��������
	private EditText edtDistance;
	//�Ƕ����
	private EditText edtAngleError;
	//ˢ��Ƶ��
	private EditText edtRefresh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_param);
		
		btnParamSave = (Button) this.findViewById(R.id.btnParamSave);
		btnParamCancel = (Button) this.findViewById(R.id.btnParamCancel);
		edtDistance = (EditText) this.findViewById(R.id.edtDistance);
		edtAngleError = (EditText) this.findViewById(R.id.edtAngleError);
		edtRefresh = (EditText) this.findViewById(R.id.edtRefresh);
		
		
		// Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
		//��������
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, "20");
        //�Ƕ����
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, "50");
        //ˢ��Ƶ��
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, "200");
        //��ֵ
        edtDistance.setText(distanceValue);
        edtAngleError.setText(angleErrorValue);
        edtRefresh.setText(refreshValue);
		
		OnClickListener btnParamSaveClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
			
				String edtDistanceValue = edtDistance.getText().toString();
				String edtAngleErrorValue = edtAngleError.getText().toString();
				String edtRefreshValue = edtRefresh.getText().toString();
				
				//�жϲ���������û������
				if (edtDistanceValue == null || edtDistanceValue.equals("")
						|| edtDistanceValue.trim().equals("")
						|| Integer.parseInt(edtDistanceValue) < 0) {
					Toast.makeText(getApplicationContext(), "��������Ч�Ĳ�������", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//�жϽǶ������û������
				if (edtAngleErrorValue == null || edtAngleErrorValue.equals("")
						|| edtAngleErrorValue.trim().equals("")
						|| Integer.parseInt(edtAngleErrorValue) < 0){
					Toast.makeText(getApplicationContext(), "��������Ч�ĽǶ����", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//�ж�ˢ��Ƶ����û������
				if (edtRefreshValue == null || edtRefreshValue.equals("")
						|| edtRefreshValue.trim().equals("")
						|| Integer.parseInt(edtRefreshValue) < 0) {
					Toast.makeText(getApplicationContext(), "��������Ч��ˢ��Ƶ��", Toast.LENGTH_SHORT).show();
					return ;
				}
				settings.edit()
				.putString(CustomConstant.DISTANCE_KEY, edtDistanceValue)
				.putString(CustomConstant.ANGLEERROR_KEY, edtAngleErrorValue)
				.putString(CustomConstant.REFRESH_KEY, edtRefreshValue)
				.commit();
				//����
				finish();
			}
		};
		btnParamSave.setOnClickListener(btnParamSaveClick);
		
		
        OnClickListener btnParamCancelClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//ȡ��
				finish();
			}
        };
        btnParamCancel.setOnClickListener(btnParamCancelClick);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.param, menu);
		return true;
	}

}
