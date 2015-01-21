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
	//保存按钮
	private Button btnParamSave;
	//取消按钮
	private Button btnParamCancel;

	//播报距离
	private EditText edtDistance;
	//角度误差
	private EditText edtAngleError;
	//刷新频率
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
		//播报距离
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, "20");
        //角度误差
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, "50");
        //刷新频率
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, "200");
        //设值
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
				
				//判断播报距离有没有输入
				if (edtDistanceValue == null || edtDistanceValue.equals("")
						|| edtDistanceValue.trim().equals("")
						|| Integer.parseInt(edtDistanceValue) < 0) {
					Toast.makeText(getApplicationContext(), "请输入有效的播报距离", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断角度误差有没有输入
				if (edtAngleErrorValue == null || edtAngleErrorValue.equals("")
						|| edtAngleErrorValue.trim().equals("")
						|| Integer.parseInt(edtAngleErrorValue) < 0){
					Toast.makeText(getApplicationContext(), "请输入有效的角度误差", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断刷新频率有没有输入
				if (edtRefreshValue == null || edtRefreshValue.equals("")
						|| edtRefreshValue.trim().equals("")
						|| Integer.parseInt(edtRefreshValue) < 0) {
					Toast.makeText(getApplicationContext(), "请输入有效的刷新频率", Toast.LENGTH_SHORT).show();
					return ;
				}
				settings.edit()
				.putString(CustomConstant.DISTANCE_KEY, edtDistanceValue)
				.putString(CustomConstant.ANGLEERROR_KEY, edtAngleErrorValue)
				.putString(CustomConstant.REFRESH_KEY, edtRefreshValue)
				.commit();
				//保存
				finish();
			}
		};
		btnParamSave.setOnClickListener(btnParamSaveClick);
		
		
        OnClickListener btnParamCancelClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//取消
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
