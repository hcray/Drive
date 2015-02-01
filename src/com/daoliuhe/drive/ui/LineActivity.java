package com.daoliuhe.drive.ui;

import java.io.IOException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.LocationManagerProxy;
import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;
import com.daoliuhe.drive.tools.Distance;

/**
 * @author CYY
 *
 */
public class LineActivity extends Activity implements OnLongClickListener{

	// private static final int ACTIVITY_LOGIN = 0;
	private static final int ACTIVITY_ITEM_ADD = 1;
	
	private static final String TAG = "LineActivity";
	// 语音
	//private SoundPool soundPool;
	//多媒体对象
	public static MediaPlayer mMediaPlayer = null; 
	//线路对象
	private LineBean lineBean;
	
	// GPS定位
	//private LocationManager lm;
	
	private LocationManagerProxy mLocationManagerProxy;

	public static final String GPSLOCATION_BROADCAST_ACTION = "com.daoliuhe.drive.broadcast";

	private PendingIntent mPendingIntent;

	//private Handler mHandler = new Handler() {
	//
	//};

	private TextView tvline;

	private Button btnLights1;
	private Button btnLights2;
	private Button btnLights3;
	private Button btnLights4;
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
	private Button btnReset;

	private Button btnScoring;
	private Button btnLineReturn;
	
	private DbAdapter dbAdapter;
	//当前的经度
	private Double curLongitude;
	//当前的纬度
	private Double curLatitude;
	//播报距离
    private Double distance;
    //角度误差
    private Double angleError;
    //刷新频率
    private Double refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);
		//屏幕常亮
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
		lineBean = new LineBean();
		
        SharedPreferences settings = getSharedPreferences(ParamActivity.SETTING_INFOS, 0);
		//播报距离
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, "20");
        distance = Double.parseDouble(distanceValue);
        //角度误差
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, "50");
        angleError = Double.parseDouble(angleErrorValue);
        //刷新频率
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, "200");
        refresh = Double.parseDouble(refreshValue);

        //数据库
		dbAdapter = new DbAdapter(this);
		dbAdapter.open();
		
		Bundle extras = this.getIntent().getExtras();
		if (extras != null) {
			Integer id = extras.getInt(DbAdapter.KEY_ID);
			if(null != id){
				lineBean.setId(id);
			}
			String lineName = extras.getString(DbAdapter.KEY_LINE_NAME);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
				//设置标题
				setTitle(lineName);
			}
			Double changeLanesLat = extras.getDouble(DbAdapter.KEY_CHANGELANES_LAT);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}
			Double changeLanesLng = extras.getDouble(DbAdapter.KEY_CHANGELANES_LNG);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}
			Double aheadDirectLineLat = extras.getDouble(DbAdapter.KEY_AHEADDIRECTLINE_LAT);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}
			Double aheadDirectLineLng = extras.getDouble(DbAdapter.KEY_AHEADDIRECTLINE_LNG);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			Double turnLeftLat = extras.getDouble(DbAdapter.KEY_TURNLEFT_LAT);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			Double turnLeftLng = extras.getDouble(DbAdapter.KEY_TURNLEFT_LNG);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			Double turnRightLat = extras.getDouble(DbAdapter.KEY_TURNRIGHT_LAT);
			if(null != turnRightLat){
				lineBean.setTurnRightLat(turnRightLat);
			}
			Double turnRightLng = extras.getDouble(DbAdapter.KEY_TURNRIGHT_LNG);
			if(null != turnRightLng){
				lineBean.setTurnRightLng(turnRightLng);
			}
			Double sidewalkLat = extras.getDouble(DbAdapter.KEY_SIDEWALK_LAT);
			if(null != sidewalkLat){
				lineBean.setSidewalkLat(sidewalkLat);
			}
			Double sidewalkLng = extras.getDouble(DbAdapter.KEY_SIDEWALK_LNG);
			if(null != sidewalkLng){
				lineBean.setSidewalkLng(sidewalkLng);
			}
			Double passSchoolLat = extras.getDouble(DbAdapter.KEY_PASSSCHOOL_LAT);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			Double passSchoolLng = extras.getDouble(DbAdapter.KEY_PASSSCHOOL_LNG);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			Double passBusStationLat = extras.getDouble(DbAdapter.KEY_PASSBUSSTATION_LAT);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}
			Double passBusStationLng = extras.getDouble(DbAdapter.KEY_PASSBUSSTATION_LNG);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			Double passSidewalkLat = extras.getDouble(DbAdapter.KEY_PASSSIDEWALK_LAT);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			Double passSidewalkLng = extras.getDouble(DbAdapter.KEY_PASSSIDEWALK_LNG);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			Double directLineLat = extras.getDouble(DbAdapter.KEY_DIRECTLINE_LAT);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			Double directLineLng = extras.getDouble(DbAdapter.KEY_DIRECTLINE_LNG);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			Double endDirectLineLat = extras.getDouble(DbAdapter.KEY_ENDDIRECTLINE_LAT);
			if(null != endDirectLineLat){
				lineBean.setEndDirectLineLat(endDirectLineLat);
			}
			Double endDirectLineLng = extras.getDouble(DbAdapter.KEY_ENDDIRECTLINE_LNG);
			if(null != endDirectLineLng){
				lineBean.setEndDirectLineLng(endDirectLineLng);
			}
		
		
			Double overtakeLat = extras.getDouble(DbAdapter.KEY_OVERTAKE_LAT);
			if(null != overtakeLat){
				lineBean.setOvertakeLat(overtakeLat);
			}
			Double overtakeLng = extras.getDouble(DbAdapter.KEY_OVERTAKE_LNG);
			if(null != overtakeLng){
				lineBean.setOvertakeLng(overtakeLng);
			}
			Double turnLat = extras.getDouble(DbAdapter.KEY_TURN_LAT);
			if(null != turnLat){
				lineBean.setTurnLat(turnLat);
			}
			Double turnLng = extras.getDouble(DbAdapter.KEY_TURN_LNG);
			if(null != turnLng){
				lineBean.setTurnLng(turnLng);
			}
			Double pullOverLat = extras.getDouble(DbAdapter.KEY_PULLOVER_LAT);
			if(null != pullOverLat){
				lineBean.setPullOverLat(pullOverLat);
			}
			Double pullOverLng = extras.getDouble(DbAdapter.KEY_PULLOVER_LNG);
			if(null != pullOverLng){
				lineBean.setPullOverLng(pullOverLng);
			}
			Double passingLat = extras.getDouble(DbAdapter.KEY_PASSING_LAT);
			if(null != passingLat){
				lineBean.setPassingLat(passingLat);
			}
			Double passingLng = extras.getDouble(DbAdapter.KEY_PASSING_LNG);
			if(null != passingLng){
				lineBean.setPassingLng(passingLng);
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
		
		//评分标准
		btnScoring = (Button) this.findViewById(R.id.btnScoring);
		OnClickListener btnScoringClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//打开评分标准
				openScoring(0);
			}
		};
		btnScoring.setOnClickListener(btnScoringClick);
		//返回
		btnLineReturn = (Button) this.findViewById(R.id.btnLineReturn);
		OnClickListener btnLineReturnClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//取消
				finish();
			}
		};
		btnLineReturn.setOnClickListener(btnLineReturnClick);
		
		
//		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		// 判断GPS是否正常启动
//		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//			Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
//			// 返回开启GPS导航设置界面
//			// Intent intent = new
//			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			// startActivityForResult(intent,0);
//			// return;
//		}

		// 为获取地理位置信息时设置查询条件
//		String bestProvider = lm.getBestProvider(getCriteria(), true);
//		// 获取位置信息
//		// 如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
//		Location location = lm.getLastKnownLocation(bestProvider);
//		updateView(location);
//		// 监听状态
//		lm.addGpsStatusListener(listener);
		// 绑定监听，有4个参数
		// 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
		// 参数2，位置信息更新周期，单位毫秒
		// 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
		// 参数4，监听
		// 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新

		// 1秒更新一次，或最小位移变化超过1米更新一次；
		// 注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,locationListener);

		btnLights1 = (Button) this.findViewById(R.id.btnLights1);
		btnLights1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast01);
				// id,左右声道, 音量, 优先级, 是否循环(0为不循环，-1为循环),播放比率(从0.5到2，一般为1，表示正常播放)
				playMusic(v.getId());
			}
		});
		
		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast02);
				playMusic(v.getId());

			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast03);
				playMusic(v.getId());

			}
		});
		btnLights4 = (Button) this.findViewById(R.id.btnLights4);
		btnLights4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast04);
				playMusic(v.getId());

			}
		});
		
		
		btnStart = (Button) this.findViewById(R.id.btnStart);
		btnStart.setOnLongClickListener(this);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast05);
				playMusic(v.getId());
				openScoring(2);
			}
		});
		
		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
		btnChangeLanes.setOnLongClickListener(this);
		btnChangeLanes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast06);
				playMusic(v.getId());
				openScoring(5);
			}
		});
		btnAheadDirectLine = (Button) this.findViewById(R.id.btnAheadDirectLine);
		btnAheadDirectLine.setOnLongClickListener(this);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast07);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
		btnTurnLeft.setOnLongClickListener(this);
		btnTurnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast08);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
		btnTurnRight.setOnLongClickListener(this);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast09);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
		btnSidewalk.setOnLongClickListener(this);
		btnSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast10);
				playMusic(v.getId());
				openScoring(7);
			}
		});

		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
		btnPassSchool.setOnLongClickListener(this);
		btnPassSchool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast11);
				playMusic(v.getId());
				openScoring(8);
			}
		});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
		btnPassBusStation.setOnLongClickListener(this);
		btnPassBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast12);
				playMusic(v.getId());
				openScoring(9);
			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
		btnPassSidewalk.setOnLongClickListener(this);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast13);
				playMusic(v.getId());
				openScoring(7);
			}
		});
		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
		btnDirectLine.setOnLongClickListener(this);
		btnDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast14);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnEndDirectLine = (Button) this.findViewById(R.id.btnEndDirectLine);
		btnEndDirectLine.setOnLongClickListener(this);
		btnEndDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast15);
				playMusic(v.getId());
				openScoring(3);
			}
		});

		btnOvertake = (Button) this.findViewById(R.id.btnOvertake);
		btnOvertake.setOnLongClickListener(this);
		btnOvertake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast16);
				playMusic(v.getId());
				openScoring(11);
			}
		});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
		btnTurn.setOnLongClickListener(this);
		btnTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast17);
				playMusic(v.getId());
				openScoring(12);
			}
		});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
		btnPullOver.setOnLongClickListener(this);
		btnPullOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast18);
				playMusic(v.getId());
				openScoring(13);
			}
		});
		btnPassing = (Button) this.findViewById(R.id.btnPassing);
		btnPassing.setOnLongClickListener(this);
		btnPassing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast19);
				playMusic(v.getId());
				openScoring(10);
			}
		});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText("");
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
				//mLocationManagerProxy.removeUpdates(mPendingIntent);

				Bundle bundle = intent.getExtras();

				// 获取bundle里的数据
				Parcelable parcelable = bundle
						.getParcelable(LocationManagerProxy.KEY_LOCATION_CHANGED);

				Location location = (Location) parcelable;
				if (location == null) {
					return;
				}
				
				updateView(location);
				
				
//				curLongitude = location.getLongitude();
//				curLatitude = location.getLatitude();
//				float curBearing = location.getBearing();
//				float curSpeed = location.getSpeed();
				
				fireHandle(location);
				
				
//				// 定位成功回调信息，设置相关消息
//				mLocationLatlngTextView.setText(location.getLatitude() + "  "
//						+ location.getLongitude());
//				mLocationAccurancyTextView.setText(String.valueOf(location
//						.getAccuracy()));
//				mLocationMethodTextView.setText(location.getProvider());
//
//				SimpleDateFormat df = new SimpleDateFormat(
//						"yyyy-MM-dd HH:mm:ss");
//				Date date = new Date(location.getTime());
//
//				mLocationTimeTextView.setText(df.format(date));

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
				LocationManagerProxy.GPS_PROVIDER, refresh.longValue(), distance.floatValue(),
				mPendingIntent);
		// 如果一直定位失败则2min后停止定位
//		mHandler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				mLocationManagerProxy.removeUpdates(mPendingIntent);
//			}
//		}, 2 * 60 * 1000);

	}

	
	@Override
	public boolean onLongClick(View v) {
		Log.i(TAG, "view: " + v.getId());
		//获取最新的bean
		lineBean = dbAdapter.selectLineBeanById(lineBean.getId());
		//跳转至坐标录入页面
		Intent intent = new Intent();
		intent.setClass(LineActivity.this, LocationActivity.class);
		int viewId = v.getId();
		switch(viewId){
			case R.id.btnChangeLanes:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getChangeLanesLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getChangeLanesLng());
				break;
			case R.id.btnAheadDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getAheadDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getAheadDirectLineLng());
				break;
			case R.id.btnTurnLeft:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLeftLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLeftLng());
				break;
			case R.id.btnTurnRight:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnRightLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnRightLng());
				break;
			case R.id.btnPassSchool:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSchoolLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSchoolLng());
				break;
			case R.id.btnPassBusStation:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassBusStationLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassBusStationLng());
				break;
			case R.id.btnPassSidewalk:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSidewalkLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSidewalkLng());
				break;
			case R.id.btnDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getDirectLineLng());
				break;
			case R.id.btnEndDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getEndDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getEndDirectLineLng());
				break;
			case R.id.btnOvertake:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getOvertakeLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getOvertakeLng());
				break;
			case R.id.btnTurn:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLng());
				break;
			case R.id.btnPullOver:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPullOverLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPullOverLng());
				break;
			case R.id.btnPassing:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassingLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassingLng());
				break;
		}
		//哪个button的坐标
		intent.putExtra(CustomConstant.BUTTONID, viewId);
		//当前的经纬度
		intent.putExtra(CustomConstant.CUR_LATITUDE, curLatitude);
		intent.putExtra(CustomConstant.CUR_LONGITUDE, curLongitude);
		startActivityForResult(intent, ACTIVITY_ITEM_ADD);
		return true;
	}
	
//	// 位置监听
//	private LocationListener locationListener = new LocationListener() {
//		/**
//		 * 位置信息变化时触发
//		 */
//		public void onLocationChanged(Location location) {
//			updateView(location);
//			Log.i(TAG, "时间：" + location.getTime());
//			Log.i(TAG, "经度：" + location.getLongitude());
//			Log.i(TAG, "纬度：" + location.getLatitude());
//			Log.i(TAG, "海拔：" + location.getAltitude());
//			//当前的经纬度
//			curLongitude = location.getLongitude();
//			curLatitude = location.getLatitude();
//			fireHandle(curLongitude, curLatitude);
//		}
//
//		/**
//		 * GPS状态变化时触发
//		 */
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//			switch (status) {
//			// GPS状态为可见时
//			case LocationProvider.AVAILABLE:
//				Log.i(TAG, "当前GPS状态为可见状态");
//				break;
//			// GPS状态为服务区外时
//			case LocationProvider.OUT_OF_SERVICE:
//				Log.i(TAG, "当前GPS状态为服务区外状态");
//				break;
//			// GPS状态为暂停服务时
//			case LocationProvider.TEMPORARILY_UNAVAILABLE:
//				Log.i(TAG, "当前GPS状态为暂停服务状态");
//				break;
//			}
//		}
//
//		/**
//		 * GPS开启时触发
//		 */
//		public void onProviderEnabled(String provider) {
//			Location location = lm.getLastKnownLocation(provider);
//			updateView(location);
//		}
//
//		/**
//		 * GPS禁用时触发
//		 */
//		public void onProviderDisabled(String provider) {
//			updateView(null);
//		}
//	};

	// 状态监听
//	GpsStatus.Listener listener = new GpsStatus.Listener() {
//		public void onGpsStatusChanged(int event) {
//			switch (event) {
//			// 第一次定位
//			case GpsStatus.GPS_EVENT_FIRST_FIX:
//				Log.i(TAG, "第一次定位");
//				break;
//			// 卫星状态改变
//			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//				Log.i(TAG, "卫星状态改变");
//				// 获取当前状态
//				GpsStatus gpsStatus = lm.getGpsStatus(null);
//				// 获取卫星颗数的默认最大值
//				int maxSatellites = gpsStatus.getMaxSatellites();
//				// 创建一个迭代器保存所有卫星
//				Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
//				int count = 0;
//				while (iters.hasNext() && count <= maxSatellites) {
//					GpsSatellite s = iters.next();
//					s.getElevation();
//					count++;
//				}
//				System.out.println("搜索到：" + count + "颗卫星");
//				break;
//			// 定位启动
//			case GpsStatus.GPS_EVENT_STARTED:
//				Log.i(TAG, "定位启动");
//				break;
//			// 定位结束
//			case GpsStatus.GPS_EVENT_STOPPED:
//				Log.i(TAG, "定位结束");
//				break;
//			}
//		};
//	};

	/**
	 * 实时更新文本内容
	 * 
	 * @param location
	 */
	private void updateView(Location location) {
		if (location != null) {
			tvline.setText("设备位置信息\n经度：");
			tvline.append(String.valueOf(location.getLongitude()));
			tvline.append("\n纬度：");
			tvline.append(String.valueOf(location.getLatitude()));
			tvline.append("\n方向：");
			tvline.append(String.valueOf(location.getBearing()));
			tvline.append("\n速度：");
			tvline.append(String.valueOf(location.getSpeed()));
		} else {
			// 清空EditText对象
			tvline.setText("");
		}
	}
	
	
	/**
	 * 到达指定位置激活相关的操作
	 * @param location 当前的位置
	 */
	private void fireHandle(Location location){
		//经度
		Double curLongitude = location.getLongitude();
		//纬度
		Double curLatitude = location.getLatitude();
		//方向
		float curBearing = location.getBearing();
		//速度
		float curSpeed = location.getSpeed();
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLat && null != changeLanesLng && 0 != changeLanesLat && 0 != changeLanesLng){
			if(Distance.GetDistance(curLongitude, curLatitude, changeLanesLng, changeLanesLat) < distance){
				tvline.setText(R.string.toast06);
				playMusic(R.id.btnChangeLanes);
			}
		}
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLat && null != aheadDirectLineLng && 0 != aheadDirectLineLat && 0 != aheadDirectLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, aheadDirectLineLng, aheadDirectLineLat) < distance){
				tvline.setText(R.string.toast07);
				playMusic(R.id.btnAheadDirectLine);
			}
		}
		Double turnLeftLat = lineBean.getTurnLat();
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLat && null != turnLeftLng && 0 != turnLeftLat && 0 != turnLeftLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLeftLng, turnLeftLat) < distance){
				tvline.setText(R.string.toast08);
				playMusic(R.id.btnTurnLeft);
			}
		}
		Double turnRightLat = lineBean.getTurnRightLat();
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLat && null != turnRightLng && turnRightLat != 0 && turnRightLng != 0){
			if(Distance.GetDistance(curLongitude, curLatitude, turnRightLng, turnRightLat) < distance){
				tvline.setText(R.string.toast09);
				playMusic(R.id.btnTurnRight);
			}
		}
		Double sidewalkLat = lineBean.getSidewalkLat();
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLat && null != sidewalkLng && 0 != sidewalkLat && 0 != sidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, sidewalkLng, sidewalkLat) < distance){
				tvline.setText(R.string.toast10);
				playMusic(R.id.btnSidewalk);
			}
		}
		Double passSchoolLat = lineBean.getPassSchoolLat();
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLat && null != passSchoolLng && 0 != passSchoolLat && 0 != passSchoolLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSchoolLng, passSchoolLat) < distance){
				tvline.setText(R.string.toast11);
				playMusic(R.id.btnPassSchool);
			}
		}
		Double passBusStationLat = lineBean.getPassBusStationLat();
		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLat && null != passBusStationLng && 0 != passBusStationLat && 0 != passBusStationLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passBusStationLng, passBusStationLat) < distance){
				tvline.setText(R.string.toast12);
				playMusic(R.id.btnPassBusStation);
			}
		}
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLat && null != passSidewalkLng && 0 != passSidewalkLat && 0 != passSidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSidewalkLng, passSidewalkLat) < distance){
				tvline.setText(R.string.toast13);
				playMusic(R.id.btnPassSidewalk);
			}
		}
		Double directLineLat = lineBean.getDirectLineLat();
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLat && null != directLineLng && 0 != directLineLat && 0 != directLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, directLineLng, directLineLat) < distance){
				tvline.setText(R.string.toast14);
				playMusic(R.id.btnDirectLine);
			}
		}
		Double endDirectLineLat = lineBean.getEndDirectLineLat();
		Double endDirectLineLng = lineBean.getEndDirectLineLng();
		if(null != endDirectLineLat && null != endDirectLineLng && 0 != endDirectLineLat && 0 != endDirectLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, endDirectLineLng, endDirectLineLat) < distance){
				tvline.setText(R.string.toast15);
				playMusic(R.id.btnEndDirectLine);
			}
		}
		Double overtakeLat = lineBean.getOvertakeLat();
		Double overtakeLng = lineBean.getOvertakeLng();
		if(null != overtakeLat && null != overtakeLng && 0 != overtakeLat && 0 != overtakeLng){
			if(Distance.GetDistance(curLongitude, curLatitude, overtakeLng, overtakeLat) < distance){
				tvline.setText(R.string.toast16);
				playMusic(R.id.btnOvertake);
			}
		}
		Double turnLat = lineBean.getTurnLat();
		Double turnLng = lineBean.getTurnLng();
		if(null != turnLat && null != turnLng && 0 != turnLat && 0 != turnLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLng, turnLat) < distance){
				tvline.setText(R.string.toast17);
				playMusic(R.id.btnTurn);
			}
		}

		Double pullOverLat = lineBean.getPullOverLat();
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLat && null != pullOverLng && 0 != pullOverLat && 0 != pullOverLng){
			if(Distance.GetDistance(curLongitude, curLatitude, pullOverLng, pullOverLat) < distance){
				tvline.setText(R.string.toast18);
				playMusic(R.id.btnPullOver);
			}
		}
		
		Double passingLat = lineBean.getPassingLat();
		Double passingLng = lineBean.getPassingLng();
		if(null != passingLat && null != passingLng && 0 != passingLat && 0 != passingLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passingLng, passingLat) < distance){
				tvline.setText(R.string.toast19);
				playMusic(R.id.btnPassing);
			}
		}
	}

//	/**
//	 * 返回查询条件
//	 * 
//	 * @return
//	 */
//	private Criteria getCriteria() {
//		Criteria criteria = new Criteria();
//		// 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
//		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		// 设置是否要求速度
//		criteria.setSpeedRequired(false);
//		// 设置是否允许运营商收费
//		criteria.setCostAllowed(false);
//		// 设置是否需要方位信息
//		criteria.setBearingRequired(false);
//		// 设置是否需要海拔信息
//		criteria.setAltitudeRequired(false);
//		// 设置对电源的需求
//		criteria.setPowerRequirement(Criteria.POWER_LOW);
//		return criteria;
//	}
	

	/**
	 * 后台播放声音
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
		if(requestCode == ACTIVITY_ITEM_ADD && resultCode == RESULT_OK){
			//保存线路
			Bundle bundle = data.getExtras();
			//btnId
			int btnId = bundle.getInt(CustomConstant.BUTTONID);
			//纬度
			Double lat = bundle.getDouble(CustomConstant.LATITUDE);
			//经度
			Double lng = bundle.getDouble(CustomConstant.LONGITUDE);
			
			Log.d(TAG, "onActivityResult() btnId: " + btnId + " lat: " + lat + " lng:　" + lng);
			
			switch (btnId) {
			case R.id.btnChangeLanes:
				lineBean.setChangeLanesLat(lat);
				lineBean.setChangeLanesLng(lng);
				break;
			case R.id.btnAheadDirectLine:
				lineBean.setAheadDirectLineLat(lat);
				lineBean.setAheadDirectLineLng(lng);
				break;
			case R.id.btnTurnLeft:
				lineBean.setTurnLeftLat(lat);
				lineBean.setTurnLeftLng(lng);
				break;
			case R.id.btnTurnRight:
				lineBean.setTurnRightLat(lat);
				lineBean.setTurnRightLng(lng);
				break;
			case R.id.btnSidewalk:
				lineBean.setSidewalkLat(lat);
				lineBean.setSidewalkLng(lng);
				break;
			case R.id.btnPassSchool:
				lineBean.setPassSchoolLat(lat);
				lineBean.setPassSchoolLng(lng);
				break;
			case R.id.btnPassBusStation:
				lineBean.setPassBusStationLat(lat);
				lineBean.setPassBusStationLng(lng);
				break;
			case R.id.btnPassSidewalk:
				lineBean.setPassSidewalkLat(lat);
				lineBean.setPassSidewalkLng(lng);
				break;
			case R.id.btnDirectLine:
				lineBean.setDirectLineLat(lat);
				lineBean.setDirectLineLng(lng);
				break;
			case R.id.btnEndDirectLine:
				lineBean.setEndDirectLineLat(lat);
				lineBean.setEndDirectLineLng(lng);
				break;
			case R.id.btnOvertake:
				lineBean.setOvertakeLat(lat);
				lineBean.setOvertakeLng(lng);
				break;
			case R.id.btnTurn:
				lineBean.setTurnLat(lat);
				lineBean.setTurnLng(lng);
				break;
			case R.id.btnPullOver:
				lineBean.setPullOverLat(lat);
				lineBean.setPullOverLng(lng);
				break;
			case R.id.btnPassing:
				lineBean.setPassingLat(lat);
				lineBean.setPassingLng(lng);
				break;
			}
			boolean ret = dbAdapter.updateLine(lineBean);
			Log.d(TAG, "updateLine ret: " + ret + " lineBean: " + lineBean.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.line, menu);
		return true;
	}

	
	
	/* 
	 * 暂停
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		//如果媒体在播放，则暂停
		//if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
		//	mMediaPlayer.pause();
		//}
		// 移除定位请求
		mLocationManagerProxy.removeUpdates(mPendingIntent);
		unregisterReceiver(mGPSLocationReceiver);
		// 销毁定位
		mLocationManagerProxy.destroy();
	}

	/* 
	 * 恢复
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
		
		if(dbAdapter != null){
			dbAdapter.close();
		}
		
		//lm.removeUpdates(locationListener);
	}
	
	
	/**
	 * 打开评分政策
	 * @param id
	 */
	public void openScoring(int id){
		Intent intent = new Intent();
		intent.setClass(LineActivity.this, ScoringActivity.class);
		intent.putExtra(CustomConstant.SELECT_ITEM, id);
		startActivity(intent);
	}
	
	/**
	 * 播放声音
	 * @param id
	 */
	public void playMusic(int id) {  
        try {
        	// 重置多媒体  
        	mMediaPlayer.reset();
        	Uri uri = null;
        	
        	switch(id){
				case R.id.btnLights1: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light01);
					break;
				
				case R.id.btnLights2: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light02);
					break;
				
				case R.id.btnLights3: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light03);
					break;
				
				case R.id.btnLights4: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light04);
					break;
				
				case R.id.btnStart: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.start);
					break;
				
				case R.id.btnChangeLanes: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.change_lanes);
					break;
				
				case R.id.btnAheadDirectLine: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.ahead_direct_line);
					break;
				
				case R.id.btnTurnLeft: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.turn_left);
					break;
				
				case R.id.btnTurnRight: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.turn_right);
					break;
				
				case R.id.btnSidewalk: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.sidewalk);
					break;
				
				case R.id.btnPassSchool: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.pass_school);
					break;
				
				case R.id.btnPassBusStation: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.pass_bus_station);
					break;
				
				case R.id.btnPassSidewalk: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.pass_sidewalk);
					break;
				
				case R.id.btnDirectLine: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.direct_line);
					break;
				
				case R.id.btnEndDirectLine:
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.end_direct_line);
					break;
				
				case R.id.btnOvertake: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.overtake);
					break;
				
				case R.id.btnTurn: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.turn);
					break;
				
				case R.id.btnPullOver : 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.pull_over);
					break;
				
				default:
					//mMediaPlayer.stop();
					break;
        	}
        	
        	if(null != uri){
        		mMediaPlayer.setDataSource(this, uri);
        		// 读取mp3文件   
        		//mMediaPlayer.create(this, R.raw.snd01);
        		//mMediaPlayer.setDataSource(getResources().openRawResource(R.raw.snd01));
        		//mMediaPlayer.setDataSource(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));  
        		//Uri uri = Uri.parse(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));  
        		
        		//mMediaPlayer.create(PlayerService.this,uri);  
        		// 准备播放   
        		mMediaPlayer.prepare();
        		// 开始播放   
        		mMediaPlayer.start();  
        	}else{
        		mMediaPlayer.stop();
        	}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }  


}
