package com.daoliuhe.drive.ui;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.LocationManagerProxy;
import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.bean.LocationBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.CustomConstant.VoiceType;
import com.daoliuhe.drive.tools.DbAdapter;
import com.daoliuhe.drive.tools.Distance;
import com.daoliuhe.drive.tools.Utils;

/**
 * @author CYY
 * 
 */
public class LineActivity extends Activity /* implements OnLongClickListener */{

	// private static final int ACTIVITY_LOGIN = 0;
	private static final int ACTIVITY_ITEM_ADD = 1;

	private static final String TAG = "LineActivity";
	// 语音
	// private SoundPool soundPool;
	// 多媒体对象
	public static MediaPlayer mMediaPlayer = null;
	// 线路对象
	private LineBean lineBean;

	// GPS定位
	// private LocationManager lm;

	private LocationManagerProxy mLocationManagerProxy;

	public static final String GPSLOCATION_BROADCAST_ACTION = "com.daoliuhe.drive.broadcast";

	private PendingIntent mPendingIntent;

	// private Handler mHandler = new Handler() {
	//
	// };

	private TextView tvline;

	private Button btnLights1;
	private Button btnLights2;
	private Button btnLights3;
	private Button btnReset;
	private Button btnStart;

	private Button btnChangeLanes;
	private Button btnAheadDirectLine;
	private Button btnTurnLeft;
	private Button btnTurnRight;
	private Button btnSidewalk;

	private Button btnPassSchool;
	private Button btnPassBusStation;
	private Button btnPassSidewalk;
	private Button btnDirectLine;
	private Button btnEndDirectLine;

	private Button btnOvertake;
	private Button btnTurn;
	private Button btnPullOver;
	private Button btnPassing;
	private Button btnShiftGears;

	// 设置坐标点
	private Button btnLineParam;
	// 评分标准
	private Button btnScoring;
	// 返回
	private Button btnLineReturn;
	//数据库
	private DbAdapter dbAdapter;
	// 当前的经度
	//private Double curLongitude;
	// 当前的纬度
	//private Double curLatitude;
	// 当前的方位
	//private float curBearing;
	// 播报距离
	private Double distance;
	// 角度误差
	private float angleError;
	// 刷新频率
	private Double refresh;
	//是否弹出评分标准
	private boolean showFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);
		// 屏幕常亮
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		lineBean = new LineBean();

		SharedPreferences settings = getSharedPreferences(
				ParamActivity.SETTING_INFOS, 0);
		// 播报距离
		String distanceValue = settings.getString(CustomConstant.DISTANCE_KEY,
				CustomConstant.DISTANCE_VALUE);
		distance = Double.parseDouble(distanceValue);
		// 角度误差
		String angleErrorValue = settings.getString(
				CustomConstant.ANGLEERROR_KEY, CustomConstant.ANGLEERROR_VALUE);
		angleError = Float.parseFloat(angleErrorValue);
		// 刷新频率
		String refreshValue = settings.getString(CustomConstant.REFRESH_KEY,
				CustomConstant.REFRESH_VALUE);
		refresh = Double.parseDouble(refreshValue);
		//是否弹出评分标准
		showFlag = settings.getBoolean(CustomConstant.SHOW_KEY, true);
		// 数据库
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();

		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			Integer id = extras.getInt(DbAdapter.KEY_ID);
			if (null != id) {
				lineBean.setId(id);
			}
			String lineName = extras.getString(DbAdapter.KEY_LINE_NAME);
			if (null != lineName && !lineName.isEmpty()) {
				lineBean.setLineName(lineName);
				// 设置标题
				setTitle(lineName);
			}
		}

		if (mMediaPlayer != null) {
			mMediaPlayer.reset();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

		mMediaPlayer = new MediaPlayer();

		tvline = (TextView) this.findViewById(R.id.tv_line);
		tvline.setMovementMethod(ScrollingMovementMethod.getInstance());

		// 坐标点的录入
		btnLineParam = (Button) this.findViewById(R.id.btnLineParam);
		OnClickListener btnLineParamClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 坐标点
				passwordDialog(LineActivity.this);
				/*
				Intent intent = new Intent();
				intent.setClass(LineActivity.this, LocationListActivity.class);
				intent.putExtra(DbAdapter.KEY_ID, lineBean.getId());
				intent.putExtra(DbAdapter.KEY_LINE_NAME, lineBean.getLineName());
				startActivity(intent);
				*/
			}
		};
		btnLineParam.setOnClickListener(btnLineParamClick);

		// 评分标准
		btnScoring = (Button) this.findViewById(R.id.btnScoring);
		OnClickListener btnScoringClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 打开评分标准
				openScoring(0);
			}
		};
		btnScoring.setOnClickListener(btnScoringClick);
		// 返回
		btnLineReturn = (Button) this.findViewById(R.id.btnLineReturn);
		OnClickListener btnLineReturnClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 取消
				finish();
			}
		};
		btnLineReturn.setOnClickListener(btnLineReturnClick);

		// lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// // 判断GPS是否正常启动
		// if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		// Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
		// // 返回开启GPS导航设置界面
		// // Intent intent = new
		// // Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		// // startActivityForResult(intent,0);
		// // return;
		// }

		// 为获取地理位置信息时设置查询条件
		// String bestProvider = lm.getBestProvider(getCriteria(), true);
		// // 获取位置信息
		// // 如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
		// Location location = lm.getLastKnownLocation(bestProvider);
		// updateView(location);
		// // 监听状态
		// lm.addGpsStatusListener(listener);
		// 绑定监听，有4个参数
		// 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
		// 参数2，位置信息更新周期，单位毫秒
		// 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
		// 参数4，监听
		// 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新

		// 1秒更新一次，或最小位移变化超过1米更新一次；
		// 注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
		// lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
		// 1,locationListener);

		btnLights1 = (Button) this.findViewById(R.id.btnLights1);
		btnLights1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast01);
				// id,左右声道, 音量, 优先级, 是否循环(0为不循环，-1为循环),播放比率(从0.5到2，一般为1，表示正常播放)
				playMusic(v.getId());
			}
		});

		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast02);
				playMusic(v.getId());

			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast03);
				playMusic(v.getId());

			}
		});
		btnShiftGears = (Button) this.findViewById(R.id.btnShiftGears);
		btnShiftGears.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast04);
				playMusic(v.getId());
				openScoring(4);

			}
		});

		btnStart = (Button) this.findViewById(R.id.btnStart);
		// btnStart.setOnLongClickListener(this);
		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast05);
				playMusic(v.getId());
				openScoring(2);
			}
		});

		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
		// btnChangeLanes.setOnLongClickListener(this);
		btnChangeLanes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast06);
				playMusic(v.getId());
				openScoring(5);
			}
		});
		btnAheadDirectLine = (Button) this
				.findViewById(R.id.btnAheadDirectLine);
		// btnAheadDirectLine.setOnLongClickListener(this);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast07);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
		// btnTurnLeft.setOnLongClickListener(this);
		btnTurnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast08);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
		// btnTurnRight.setOnLongClickListener(this);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast09);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
		// btnSidewalk.setOnLongClickListener(this);
		btnSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast10);
				playMusic(v.getId());
				openScoring(7);
			}
		});

		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
		// btnPassSchool.setOnLongClickListener(this);
		btnPassSchool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast11);
				playMusic(v.getId());
				openScoring(8);
			}
		});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
		// btnPassBusStation.setOnLongClickListener(this);
		btnPassBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast12);
				playMusic(v.getId());
				openScoring(9);
			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
		// btnPassSidewalk.setOnLongClickListener(this);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast13);
				playMusic(v.getId());
				openScoring(7);
			}
		});
		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
		// btnDirectLine.setOnLongClickListener(this);
		btnDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast14);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnEndDirectLine = (Button) this.findViewById(R.id.btnEndDirectLine);
		// btnEndDirectLine.setOnLongClickListener(this);
		btnEndDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast15);
				playMusic(v.getId());
				openScoring(3);
			}
		});

		btnOvertake = (Button) this.findViewById(R.id.btnOvertake);
		// btnOvertake.setOnLongClickListener(this);
		btnOvertake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast16);
				playMusic(v.getId());
				openScoring(11);
			}
		});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
		// btnTurn.setOnLongClickListener(this);
		btnTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast17);
				playMusic(v.getId());
				openScoring(12);
			}
		});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
		// btnPullOver.setOnLongClickListener(this);
		btnPullOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast18);
				playMusic(v.getId());
				openScoring(13);
			}
		});
		btnPassing = (Button) this.findViewById(R.id.btnPassing);
		// btnPassing.setOnLongClickListener(this);
		btnPassing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText(R.string.toast19);
				playMusic(v.getId());
				openScoring(10);
			}
		});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tvline.setText("");
				playMusic(v.getId());

			}
		});

	}

	private BroadcastReceiver mGPSLocationReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// 接受广播
			if (intent.getAction().equals(GPSLOCATION_BROADCAST_ACTION)) {

				// 只进行一次定位，定位成功后移除定位请求
				// mLocationManagerProxy.removeUpdates(mPendingIntent);

				Bundle bundle = intent.getExtras();

				// 获取bundle里的数据
				Parcelable parcelable = bundle
						.getParcelable(LocationManagerProxy.KEY_LOCATION_CHANGED);

				Location location = (Location) parcelable;
				if (location == null) {
					return;
				}

				updateView(location);

				fireHandler(location);
			}
		}
	};

	private void init() {
		IntentFilter fliter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		fliter.addAction(GPSLOCATION_BROADCAST_ACTION);
		registerReceiver(mGPSLocationReceiver, fliter);
		Intent intent = new Intent(GPSLOCATION_BROADCAST_ACTION);
		mPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
				intent, 0);
		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		// 采用peddingIntent方式进行对定位调用
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次
		// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除

		mLocationManagerProxy.requestLocationUpdates(
				LocationManagerProxy.GPS_PROVIDER, refresh.longValue(),
				distance.floatValue() / 2, mPendingIntent);
	}

	/**
	 * 实时更新文本内容
	 * 
	 * @param location
	 */
	private void updateView(Location location) {
		if (location != null) {
			tvline.setText("location info\nLongitude：");
			tvline.append(String.valueOf(location.getLongitude()));
			tvline.append("\nLatitude：");
			tvline.append(String.valueOf(location.getLatitude()));
			tvline.append("\nBearing：");
			tvline.append(String.valueOf(location.getBearing()));
			tvline.append("\nSpeed：");
			tvline.append(String.valueOf(location.getSpeed()));
		} else {
			// 清空EditText对象
			tvline.setText("");
		}
	}

	/**
	 * 到达指定位置激活相关的操作
	 * 
	 * @param location
	 *            当前的位置
	 */
	private void fireHandler(Location location) {
		// 经度
		Double curLongitude = location.getLongitude();
		// 纬度
		Double curLatitude = location.getLatitude();
		// 方向
		float curBearing = location.getBearing();
		// 速度
		// float curSpeed = location.getSpeed();
		// 查询出路线中的所有的坐标点
		int lineId = lineBean.getId();
		List<LocationBean> locationList = dbAdapter.selectLocationByLineId(lineId);
		// 迭代保存的坐标点
		for (LocationBean locationBean : locationList) {
			// 经度
			Double longitude = locationBean.getLongitude();
			// 纬度
			Double latitude = locationBean.getLatitude();
			// 方位
			Float bearing = locationBean.getBearing();
			// 语音类型
			int voiceType = locationBean.getVoiceType();
			VoiceType curVoiceType = null;
			
			//获取枚举类型
			VoiceType[] values = VoiceType.values();
			for(VoiceType value : values){
				int valId = value.getId();
				if(voiceType == valId){
					curVoiceType = value;
					break;
				}
			}

			//判断是否符合触发条件
			if (null != latitude && null != longitude && 0 != latitude
					&& 0 != longitude) {
				if (Distance.GetDistance(curLongitude, curLatitude, longitude,
						latitude) < distance
						&& Distance.getDiff(curBearing, bearing) < angleError) {
					// tvline.setText(R.string.toast06);
					playMusic(curVoiceType);
					openScoring(curVoiceType);
				}
			}

		}
	}

	/**
	 * 后台播放声音
	 * 
	 * @param action
	 */
	public void playSound(int id) {
		Intent intent = new Intent();
		intent.putExtra("MSG", id);
		intent.setClass(LineActivity.this, SoundService.class);

		/* 启动service service要在AndroidManifest.xml注册如：<service></service> */
		startService(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ACTIVITY_ITEM_ADD && resultCode == RESULT_OK) {
			// 保存线路
			Bundle bundle = data.getExtras();
			// btnId
			int btnId = bundle.getInt(CustomConstant.BUTTONID);
			// 纬度
			Double lat = bundle.getDouble(CustomConstant.LATITUDE);
			// 经度
			Double lng = bundle.getDouble(CustomConstant.LONGITUDE);
			// 方位
			Float bearing = bundle.getFloat(CustomConstant.BEARING);

			Log.d(TAG, "onActivityResult() btnId: " + btnId + " lat: " + lat
					+ " lng:　" + lng + " bearing: " + bearing);

			switch (btnId) {
			case R.id.btnChangeLanes:
				// lineBean.setChangeLanesLat(lat);
				// lineBean.setChangeLanesLng(lng);
				// lineBean.setChangeLanesBr(bearing);
				break;
			case R.id.btnAheadDirectLine:
				// lineBean.setAheadDirectLineLat(lat);
				// lineBean.setAheadDirectLineLng(lng);
				// lineBean.setAheadDirectLineBr(bearing);
				break;
			case R.id.btnTurnLeft:
				// lineBean.setTurnLeftLat(lat);
				// lineBean.setTurnLeftLng(lng);
				// lineBean.setTurnLeftBr(bearing);
				break;
			case R.id.btnTurnRight:
				// lineBean.setTurnRightLat(lat);
				// lineBean.setTurnRightLng(lng);
				// lineBean.setTurnRightBr(bearing);
				break;
			case R.id.btnSidewalk:
				// lineBean.setSidewalkLat(lat);
				// lineBean.setSidewalkLng(lng);
				// lineBean.setSidewalkBr(bearing);
				break;
			case R.id.btnPassSchool:
				// lineBean.setPassSchoolLat(lat);
				// lineBean.setPassSchoolLng(lng);
				// lineBean.setPassSchoolBr(bearing);
				break;
			case R.id.btnPassBusStation:
				// lineBean.setPassBusStationLat(lat);
				// lineBean.setPassBusStationLng(lng);
				// lineBean.setPassBusStationBr(bearing);
				break;
			case R.id.btnPassSidewalk:
				// lineBean.setPassSidewalkLat(lat);
				// lineBean.setPassSidewalkLng(lng);
				// lineBean.setPassSidewalkBr(bearing);
				break;
			case R.id.btnDirectLine:
				// lineBean.setDirectLineLat(lat);
				// lineBean.setDirectLineLng(lng);
				// lineBean.setDirectLineBr(bearing);
				break;
			case R.id.btnEndDirectLine:
				// lineBean.setEndDirectLineLat(lat);
				// lineBean.setEndDirectLineLng(lng);
				// lineBean.setEndDirectLineBr(bearing);
				break;
			case R.id.btnOvertake:
				// lineBean.setOvertakeLat(lat);
				// lineBean.setOvertakeLng(lng);
				// lineBean.setOvertakeBr(bearing);
				break;
			case R.id.btnTurn:
				// lineBean.setTurnLat(lat);
				// lineBean.setTurnLng(lng);
				// lineBean.setTurnBr(bearing);
				break;
			case R.id.btnPullOver:
				// lineBean.setPullOverLat(lat);
				// lineBean.setPullOverLng(lng);
				// lineBean.setPullOverBr(bearing);
				break;
			case R.id.btnPassing:
				// lineBean.setPassingLat(lat);
				// lineBean.setPassingLng(lng);
				// lineBean.setPassingBr(bearing);
				break;
			}
			boolean ret = dbAdapter.updateLine(lineBean);
			Log.d(TAG,
					"updateLine ret: " + ret + " lineBean: "
							+ lineBean.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.line, menu);
		return true;
	}

	/*
	 * 暂停
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		// 如果媒体在播放，则暂停
		// if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
		// mMediaPlayer.pause();
		// }
		// 移除定位请求
		mLocationManagerProxy.removeUpdates(mPendingIntent);
		unregisterReceiver(mGPSLocationReceiver);
		// 销毁定位
		mLocationManagerProxy.destroy();
	}

	/*
	 * 恢复
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

		if (dbAdapter != null) {
			dbAdapter.close();
		}

		// lm.removeUpdates(locationListener);
	}

	/**
	 * 打开评分政策
	 * 
	 * @param id
	 */
	public void openScoring(VoiceType voiceType) {
		if(showFlag){
			Intent intent = new Intent();
			intent.setClass(LineActivity.this, ScoringActivity.class);
			intent.putExtra(CustomConstant.SELECT_ITEM, voiceType.getScoreId());
			startActivity(intent);
		}
	}
	
	/**
	 * 打开评分政策
	 * 
	 * @param id
	 */
	public void openScoring(int id) {
		if(showFlag){
			Intent intent = new Intent();
			intent.setClass(LineActivity.this, ScoringActivity.class);
			intent.putExtra(CustomConstant.SELECT_ITEM, id);
			startActivity(intent);
		}
	}
	
	/**
	 * 播放声音
	 * 
	 * @param id
	 */
	public void playMusic(VoiceType voiceType) {
		try {
			// 重置多媒体
			mMediaPlayer.reset();
			Uri uri = null;
			uri = Uri.parse("android.resource://" + getPackageName() + "/"+ voiceType.getCode());

			if (null != uri) {
				mMediaPlayer.setDataSource(this, uri);
				// 准备播放
				mMediaPlayer.prepare();
				// 开始播放
				mMediaPlayer.start();
			} else {
				mMediaPlayer.stop();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 播放声音
	 * 
	 * @param id
	 */
	public void playMusic(int id) {
		try {
			// 重置多媒体
			mMediaPlayer.reset();
			Uri uri = null;

			switch (id) {
			case R.id.btnLights1:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.light01);
				break;

			case R.id.btnLights2:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.light02);
				break;

			case R.id.btnLights3:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.light03);
				break;

			case R.id.btnShiftGears:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.shift_gears);
				break;

			case R.id.btnStart:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.start);
				break;

			case R.id.btnChangeLanes:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.change_lanes);
				break;

			case R.id.btnAheadDirectLine:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.ahead_direct_line);
				break;

			case R.id.btnTurnLeft:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.turn_left);
				break;

			case R.id.btnTurnRight:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.turn_right);
				break;

			case R.id.btnSidewalk:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.sidewalk);
				break;

			case R.id.btnPassSchool:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.pass_school);
				break;

			case R.id.btnPassBusStation:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.pass_bus_station);
				break;

			case R.id.btnPassSidewalk:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.pass_sidewalk);
				break;

			case R.id.btnDirectLine:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.direct_line);
				break;

			case R.id.btnEndDirectLine:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.end_direct_line);
				break;

			case R.id.btnOvertake:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.overtake);
				break;

			case R.id.btnTurn:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.turn);
				break;

			case R.id.btnPullOver:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.pull_over);
				break;

			case R.id.btnPassing:
				uri = Uri.parse("android.resource://" + getPackageName() + "/"
						+ R.raw.psssing);
				break;

			default:
				// mMediaPlayer.stop();
				break;
			}

			if (null != uri) {
				mMediaPlayer.setDataSource(this, uri);
				// 读取mp3文件
				// mMediaPlayer.create(this, R.raw.snd01);
				// mMediaPlayer.setDataSource(getResources().openRawResource(R.raw.snd01));
				// mMediaPlayer.setDataSource(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));
				// Uri uri =
				// Uri.parse(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));

				// mMediaPlayer.create(PlayerService.this,uri);
				// 准备播放
				mMediaPlayer.prepare();
				// 开始播放
				mMediaPlayer.start();
			} else {
				mMediaPlayer.stop();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 输入管理员密码的弹出框
	 * @param context
	 * @return
	 */
	private Dialog passwordDialog(Context context) {
		//密码输入框
		final EditText etPassword = new EditText(context);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//builder.setIcon(R.drawable.ic_dialog_info);
		builder.setTitle("请输入密码");
		builder.setView(etPassword);
		builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//setTitle("点击了对话框上的确定按钮");
				String inputPW = etPassword.getText().toString();
				String password = "3a79c605ba623458602d0b195e1c00b455f2dfbbf45664959d3f454f038ff321";
				//判断输入的密码
				if(inputPW != null && password.equalsIgnoreCase(Utils.encodeSHA256(inputPW))){
					Intent intent = new Intent();
					intent.setClass(LineActivity.this, LocationListActivity.class);
					intent.putExtra(DbAdapter.KEY_ID, lineBean.getId());
					intent.putExtra(DbAdapter.KEY_LINE_NAME, lineBean.getLineName());
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//setTitle("点击了对话框上的取消按钮");
			}
		});
		
		return builder.create();

	}

}
