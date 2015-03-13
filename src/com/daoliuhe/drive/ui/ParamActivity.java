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
import android.widget.ToggleButton;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.tools.CustomConstant;

public class ParamActivity extends Activity {
	public static final String SETTING_INFOS = "SETTING_INFOS";
	//private static final int ACTIVITY_LOGIN = 0;
	//���水ť
	private Button btnParamSave;
	//�ָ�Ĭ��
	private Button btnParamRestore;
	//ȡ����ť
	private Button btnParamCancel;
	//����
	private Button btnParamReturn;

	//��������
	private EditText edtDistance;
	//�Ƕ����
	private EditText edtAngleError;
	//ˢ��Ƶ��
	private EditText edtRefresh;
	//�Ƿ񵯳����ֱ�׼
	private ToggleButton toggleButtonShow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_param);
		
		btnParamSave = (Button) this.findViewById(R.id.btnParamSave);
		btnParamRestore = (Button) this.findViewById(R.id.btnParamRestore);
		btnParamCancel = (Button) this.findViewById(R.id.btnParamCancel);
		btnParamReturn = (Button) this.findViewById(R.id.btnParamReturn);
		
		edtDistance = (EditText) this.findViewById(R.id.edtDistance);
		edtAngleError = (EditText) this.findViewById(R.id.edtAngleError);
		edtRefresh = (EditText) this.findViewById(R.id.edtRefresh);
		toggleButtonShow = (ToggleButton) this.findViewById(R.id.toggleButtonShow);
		
		
		// Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
		//��������
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, CustomConstant.DISTANCE_VALUE);
        //�Ƕ����
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, CustomConstant.ANGLEERROR_VALUE);
        //ˢ��Ƶ��
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, CustomConstant.REFRESH_VALUE);
        //�Ƿ񵯳����ֱ�׼
        boolean tgShowValue = settings.getBoolean(CustomConstant.SHOW_KEY, true);
        
        //��ֵ
        edtDistance.setText(distanceValue);
        edtAngleError.setText(angleErrorValue);
        edtRefresh.setText(refreshValue);
        toggleButtonShow.setChecked(tgShowValue);
		
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
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.param_input_distance), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//�жϽǶ������û������
				if (edtAngleErrorValue == null || edtAngleErrorValue.equals("")
						|| edtAngleErrorValue.trim().equals("")
						|| Integer.parseInt(edtAngleErrorValue) < 0){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.param_input_angleError), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//�ж�ˢ��Ƶ����û������
				if (edtRefreshValue == null || edtRefreshValue.equals("")
						|| edtRefreshValue.trim().equals("")
						|| Integer.parseInt(edtRefreshValue) < 0) {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.param_input_refresh), Toast.LENGTH_SHORT).show();
					return ;
				}
				settings.edit()
				.putString(CustomConstant.DISTANCE_KEY, edtDistanceValue)
				.putString(CustomConstant.ANGLEERROR_KEY, edtAngleErrorValue)
				.putString(CustomConstant.REFRESH_KEY, edtRefreshValue)
				.putBoolean(CustomConstant.SHOW_KEY, toggleButtonShow.isChecked())
				.commit();
				//����
				finish();
			}
		};
		btnParamSave.setOnClickListener(btnParamSaveClick);
		
		btnParamRestore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
				//��������
		        String distanceValue = CustomConstant.DISTANCE_VALUE;
		        //�Ƕ����
		        String angleErrorValue = CustomConstant.ANGLEERROR_VALUE;
		        //ˢ��Ƶ��
		        String refreshValue = CustomConstant.REFRESH_VALUE;
		        //�Ƿ񵯳����ֱ�׼
		        boolean showValue = true;
		        //�ָ�ҳ���ֵ
		        edtDistance.setText(distanceValue);
				edtAngleError.setText(angleErrorValue);
				edtRefresh.setText(refreshValue);
				toggleButtonShow.setChecked(showValue);
		        //����
				settings.edit()
				.putString(CustomConstant.DISTANCE_KEY, distanceValue)
				.putString(CustomConstant.ANGLEERROR_KEY, angleErrorValue)
				.putString(CustomConstant.REFRESH_KEY, refreshValue)
				.putBoolean(CustomConstant.SHOW_KEY, showValue)
				.commit();
				
			}
		});
		
		
        OnClickListener btnParamCancelClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//ȡ��
				finish();
			}
        };
        btnParamCancel.setOnClickListener(btnParamCancelClick);
        btnParamReturn.setOnClickListener(btnParamCancelClick);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.param, menu);
		return true;
	}

}
