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
	//保存按钮
	private Button btnParamSave;
	//恢复默认
	private Button btnParamRestore;
	//取消按钮
	private Button btnParamCancel;
	//返回
	private Button btnParamReturn;

	//播报距离
	private EditText edtDistance;
	//角度误差
	private EditText edtAngleError;
	//刷新频率
	private EditText edtRefresh;
	//是否弹出评分标准
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
		//播报距离
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, CustomConstant.DISTANCE_VALUE);
        //角度误差
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, CustomConstant.ANGLEERROR_VALUE);
        //刷新频率
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, CustomConstant.REFRESH_VALUE);
        //是否弹出评分标准
        boolean tgShowValue = settings.getBoolean(CustomConstant.SHOW_KEY, true);
        
        //设值
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
				
				//判断播报距离有没有输入
				if (edtDistanceValue == null || edtDistanceValue.equals("")
						|| edtDistanceValue.trim().equals("")
						|| Integer.parseInt(edtDistanceValue) < 0) {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.param_input_distance), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断角度误差有没有输入
				if (edtAngleErrorValue == null || edtAngleErrorValue.equals("")
						|| edtAngleErrorValue.trim().equals("")
						|| Integer.parseInt(edtAngleErrorValue) < 0){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.param_input_angleError), Toast.LENGTH_SHORT).show();
					return ;
				}
				
				//判断刷新频率有没有输入
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
				//保存
				finish();
			}
		};
		btnParamSave.setOnClickListener(btnParamSaveClick);
		
		btnParamRestore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
				//播报距离
		        String distanceValue = CustomConstant.DISTANCE_VALUE;
		        //角度误差
		        String angleErrorValue = CustomConstant.ANGLEERROR_VALUE;
		        //刷新频率
		        String refreshValue = CustomConstant.REFRESH_VALUE;
		        //是否弹出评分标准
		        boolean showValue = true;
		        //恢复页面的值
		        edtDistance.setText(distanceValue);
				edtAngleError.setText(angleErrorValue);
				edtRefresh.setText(refreshValue);
				toggleButtonShow.setChecked(showValue);
		        //保存
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
				//取消
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
