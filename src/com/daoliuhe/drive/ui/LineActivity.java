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
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.LocationManagerProxy;
import com.daoliuhe.drive.R;
import com.daoliuhe.drive.R.id;
import com.daoliuhe.drive.R.layout;
import com.daoliuhe.drive.R.raw;
import com.daoliuhe.drive.R.string;
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
	// ����
	//private SoundPool soundPool;
	//��ý�����
	public static MediaPlayer mMediaPlayer = null; 
	//��·����
	private LineBean lineBean;
	
	// GPS��λ
	//private LocationManager lm;
	
	private LocationManagerProxy mLocationManagerProxy;

	public static final String GPSLOCATION_BROADCAST_ACTION = "com.daoliuhe.drive.broadcast";

	private PendingIntent mPendingIntent;

	private Handler mHandler = new Handler() {

	};

	private TextView tvline;

	private Button btnLights1;
	private Button btnLights2;
	private Button btnLights3;
	private Button btnLights4;
	private Button btnTurnRight;

	private Button btnSidewalk;
	private Button btnPassSidewalk;
	private Button btnTurnLeft;
	private Button btnAheadDirectLine;
	private Button btnPassBusStation;

	private Button btnDirectLine;
	private Button btnPassSchool;
	private Button btnChangeLanes;
	private Button btnSlowdown;
	private Button btnSpeedLimit;

	private Button btnPassSchoolStation;
	private Button btnTurn;
	private Button btnPullOver;
	private Button btnBackCar;
	private Button btnReset;
	
	private DbAdapter dbAdapter;
	//��ǰ�ľ���
	private Double curLongitude;
	//��ǰ��γ��
	private Double curLatitude;
	//��������
    private Double distance;
    //�Ƕ����
    private Double angleError;
    //ˢ��Ƶ��
    private Double refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);
		//��Ļ����
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
		lineBean = new LineBean();
		
        SharedPreferences settings = getSharedPreferences(ParamActivity.SETTING_INFOS, 0);
		//��������
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, "20");
        distance = Double.parseDouble(distanceValue);
        //�Ƕ����
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, "50");
        angleError = Double.parseDouble(angleErrorValue);
        //ˢ��Ƶ��
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, "200");
        refresh = Double.parseDouble(refreshValue);

        //���ݿ�
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
				//���ñ���
				setTitle(lineName);
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
			Double passSidewalkLat = extras.getDouble(DbAdapter.KEY_PASSSIDEWALK_LAT);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			Double passSidewalkLng = extras.getDouble(DbAdapter.KEY_PASSSIDEWALK_LNG);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			Double turnLeftLat = extras.getDouble(DbAdapter.KEY_TURNLEFT_LAT);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			Double turnLeftLng = extras.getDouble(DbAdapter.KEY_TURNLEFT_LNG);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			Double aheadDirectLineLat = extras.getDouble(DbAdapter.KEY_AHEADDIRECTLINE_LAT);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}
			Double aheadDirectLineLng = extras.getDouble(DbAdapter.KEY_AHEADDIRECTLINE_LNG);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			Double passBusStationLat = extras.getDouble(DbAdapter.KEY_PASSBUSSTATION_LAT);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}
			Double passBusStationLng = extras.getDouble(DbAdapter.KEY_PASSBUSSTATION_LNG);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			Double directLineLat = extras.getDouble(DbAdapter.KEY_DIRECTLINE_LAT);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			Double directLineLng = extras.getDouble(DbAdapter.KEY_DIRECTLINE_LNG);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			Double passSchoolLat = extras.getDouble(DbAdapter.KEY_PASSSCHOOL_LAT);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			Double passSchoolLng = extras.getDouble(DbAdapter.KEY_PASSSCHOOL_LNG);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			Double changeLanesLat = extras.getDouble(DbAdapter.KEY_CHANGELANES_LAT);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}
			Double changeLanesLng = extras.getDouble(DbAdapter.KEY_CHANGELANES_LNG);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}
			Double slowdownLat = extras.getDouble(DbAdapter.KEY_SLOWDOWN_LAT);
			if(null != slowdownLat){
				lineBean.setSlowdownLat(slowdownLat);
			}
			Double slowdownLng = extras.getDouble(DbAdapter.KEY_SLOWDOWN_LNG);
			if(null != slowdownLng){
				lineBean.setSlowdownLng(slowdownLng);
			}
			Double speedLimitLat = extras.getDouble(DbAdapter.KEY_SPEEDLIMIT_LAT);
			if(null != speedLimitLat){
				lineBean.setSpeedLimitLat(speedLimitLat);
			}
			Double speedLimitLng = extras.getDouble(DbAdapter.KEY_SPEEDLIMIT_LNG);
			if(null != speedLimitLng){
				lineBean.setSpeedLimitLng(speedLimitLng);
			}
			Double passSchoolStationLat = extras.getDouble(DbAdapter.KEY_PASSSCHOOLSTATION_LAT);
			if(null != passSchoolStationLat){
				lineBean.setPassSchoolStationLat(passSchoolStationLat);
			}
			Double passSchoolStationLng = extras.getDouble(DbAdapter.KEY_PASSSCHOOLSTATION_LNG);
			if(null != passSchoolStationLng){
				lineBean.setPassSchoolStationLng(passSchoolStationLng);
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
			Double backCarLat = extras.getDouble(DbAdapter.KEY_BACKCAR_LAT);
			if(null != backCarLat){
				lineBean.setBackCarLat(backCarLat);
			}
			Double backCarLng = extras.getDouble(DbAdapter.KEY_BACKCAR_LNG);
			if(null != backCarLng){
				lineBean.setBackCarLng(backCarLng);
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
		
//		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		// �ж�GPS�Ƿ���������
//		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//			Toast.makeText(this, "�뿪��GPS����...", Toast.LENGTH_SHORT).show();
//			// ���ؿ���GPS�������ý���
//			// Intent intent = new
//			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			// startActivityForResult(intent,0);
//			// return;
//		}

		// Ϊ��ȡ����λ����Ϣʱ���ò�ѯ����
//		String bestProvider = lm.getBestProvider(getCriteria(), true);
//		// ��ȡλ����Ϣ
//		// ��������ò�ѯҪ��getLastKnownLocation�������˵Ĳ���ΪLocationManager.GPS_PROVIDER
//		Location location = lm.getLastKnownLocation(bestProvider);
//		updateView(location);
//		// ����״̬
//		lm.addGpsStatusListener(listener);
		// �󶨼�������4������
		// ����1���豸����GPS_PROVIDER��NETWORK_PROVIDER����
		// ����2��λ����Ϣ�������ڣ���λ����
		// ����3��λ�ñ仯��С���룺��λ�þ���仯������ֵʱ��������λ����Ϣ
		// ����4������
		// ��ע������2��3���������3��Ϊ0�����Բ���3Ϊ׼������3Ϊ0����ͨ��ʱ������ʱ���£�����Ϊ0������ʱˢ��

		// 1�����һ�Σ�����Сλ�Ʊ仯����1�׸���һ�Σ�
		// ע�⣺�˴�����׼ȷ�ȷǳ��ͣ��Ƽ���service��������һ��Thread����run��sleep(10000);Ȼ��ִ��handler.sendMessage(),����λ��
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,locationListener);

		btnLights1 = (Button) this.findViewById(R.id.btnLights1);
		btnLights1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast01);
				// id,��������, ����, ���ȼ�, �Ƿ�ѭ��(0Ϊ��ѭ����-1Ϊѭ��),���ű���(��0.5��2��һ��Ϊ1����ʾ��������)
				playMusic(1);
			}
		});
		
		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast02);
				playMusic(2);

			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast03);
				playMusic(3);

			}
		});
		btnLights4 = (Button) this.findViewById(R.id.btnLights4);
		btnLights4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast04);
				playMusic(4);

			}
		});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
		btnTurnRight.setOnLongClickListener(this);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast05);
				playMusic(5);

			}
		});

		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
		btnSidewalk.setOnLongClickListener(this);
		btnSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast06);
				playMusic(6);

			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
		btnPassSidewalk.setOnLongClickListener(this);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast07);
				playMusic(7);

			}
		});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
		btnTurnLeft.setOnLongClickListener(this);
		btnTurnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast08);
				playMusic(8);

			}
		});
		btnAheadDirectLine = (Button) this.findViewById(R.id.btnAheadDirectLine);
		btnAheadDirectLine.setOnLongClickListener(this);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast09);
				playMusic(9);

			}
		});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
		btnPassBusStation.setOnLongClickListener(this);
		btnPassBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast10);
				playMusic(10);

			}
		});

		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
		btnDirectLine.setOnLongClickListener(this);
		btnDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast11);
				playMusic(11);

			}
		});
		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
		btnPassSchool.setOnLongClickListener(this);
		btnPassSchool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast12);
				playMusic(12);

			}
		});
		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
		btnChangeLanes.setOnLongClickListener(this);
		btnChangeLanes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast13);
				playMusic(13);

			}
		});
		btnSlowdown = (Button) this.findViewById(R.id.btnSlowdown);
		btnSlowdown.setOnLongClickListener(this);
		btnSlowdown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast14);
				playMusic(14);

			}
		});
		btnSpeedLimit = (Button) this.findViewById(R.id.btnSpeedLimit);
		btnSpeedLimit.setOnLongClickListener(this);
		btnSpeedLimit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast15);
				playMusic(15);

			}
		});

		btnPassSchoolStation = (Button) this.findViewById(R.id.btnPassSchoolStation);
		btnPassSchoolStation.setOnLongClickListener(this);
		btnPassSchoolStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast16);
				playMusic(16);

			}
		});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
		btnTurn.setOnLongClickListener(this);
		btnTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast17);
				playMusic(17);

			}
		});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
		btnPullOver.setOnLongClickListener(this);
		btnPullOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast18);
				playMusic(18);

			}
		});
		btnBackCar = (Button) this.findViewById(R.id.btnBackCar);
		btnBackCar.setOnLongClickListener(this);
		btnBackCar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast19);
				playMusic(19);

			}
		});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText("");
				playMusic(20);

			}
		});

	}
	
	
	private BroadcastReceiver mGPSLocationReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// ���ܹ㲥
			if (intent.getAction().equals(GPSLOCATION_BROADCAST_ACTION)) {

				// ֻ����һ�ζ�λ����λ�ɹ����Ƴ���λ����
				//mLocationManagerProxy.removeUpdates(mPendingIntent);

				Bundle bundle = intent.getExtras();

				// ��ȡbundle�������
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
				
				
//				// ��λ�ɹ��ص���Ϣ�����������Ϣ
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
		// ����peddingIntent��ʽ���жԶ�λ����
		// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
		// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����removeUpdates()������ȡ����λ����
		// �ڶ�λ�������ں��ʵ��������ڵ���destroy()����
		// ����������ʱ��Ϊ-1����λֻ��һ��
		// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������removeUpdates()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�

		mLocationManagerProxy.requestLocationUpdates(
				LocationManagerProxy.GPS_PROVIDER, refresh.longValue(), distance.floatValue(),
				mPendingIntent);
		// ���һֱ��λʧ����2min��ֹͣ��λ
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
		//��ȡ���µ�bean
		lineBean = dbAdapter.selectLineBeanById(lineBean.getId());
		//��ת������¼��ҳ��
		Intent intent = new Intent();
		intent.setClass(LineActivity.this, LocationActivity.class);
		int viewId = v.getId();
		switch(viewId){
			case R.id.btnTurnRight:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnRightLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnRightLng());
				break;
			case R.id.btnSidewalk:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getSidewalkLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getSidewalkLng());
				break;
			case R.id.btnPassSidewalk:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSidewalkLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSidewalkLng());
				break;
			case R.id.btnTurnLeft:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLeftLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLeftLng());
				break;
			case R.id.btnAheadDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getAheadDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getAheadDirectLineLng());
				break;
			case R.id.btnPassBusStation:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassBusStationLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassBusStationLng());
				break;
			case R.id.btnDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getDirectLineLng());
				break;
			case R.id.btnPassSchool:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSchoolLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSchoolLng());
				break;
			case R.id.btnChangeLanes:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getChangeLanesLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getChangeLanesLng());
				break;
			case R.id.btnSlowdown:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getSlowdownLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getSlowdownLng());
				break;
			case R.id.btnSpeedLimit:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getSpeedLimitLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getSpeedLimitLng());
				break;
			case R.id.btnPassSchoolStation:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSchoolStationLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSchoolStationLng());
				break;
			case R.id.btnTurn:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLng());
				break;
			case R.id.btnPullOver:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPullOverLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPullOverLng());
				break;
			case R.id.btnBackCar:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getBackCarLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getBackCarLng());
				break;
		}
		//�ĸ�button������
		intent.putExtra(CustomConstant.BUTTONID, viewId);
		//��ǰ�ľ�γ��
		intent.putExtra(CustomConstant.CUR_LATITUDE, curLatitude);
		intent.putExtra(CustomConstant.CUR_LONGITUDE, curLongitude);
		startActivityForResult(intent, ACTIVITY_ITEM_ADD);
		return true;
	}
	
//	// λ�ü���
//	private LocationListener locationListener = new LocationListener() {
//		/**
//		 * λ����Ϣ�仯ʱ����
//		 */
//		public void onLocationChanged(Location location) {
//			updateView(location);
//			Log.i(TAG, "ʱ�䣺" + location.getTime());
//			Log.i(TAG, "���ȣ�" + location.getLongitude());
//			Log.i(TAG, "γ�ȣ�" + location.getLatitude());
//			Log.i(TAG, "���Σ�" + location.getAltitude());
//			//��ǰ�ľ�γ��
//			curLongitude = location.getLongitude();
//			curLatitude = location.getLatitude();
//			fireHandle(curLongitude, curLatitude);
//		}
//
//		/**
//		 * GPS״̬�仯ʱ����
//		 */
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//			switch (status) {
//			// GPS״̬Ϊ�ɼ�ʱ
//			case LocationProvider.AVAILABLE:
//				Log.i(TAG, "��ǰGPS״̬Ϊ�ɼ�״̬");
//				break;
//			// GPS״̬Ϊ��������ʱ
//			case LocationProvider.OUT_OF_SERVICE:
//				Log.i(TAG, "��ǰGPS״̬Ϊ��������״̬");
//				break;
//			// GPS״̬Ϊ��ͣ����ʱ
//			case LocationProvider.TEMPORARILY_UNAVAILABLE:
//				Log.i(TAG, "��ǰGPS״̬Ϊ��ͣ����״̬");
//				break;
//			}
//		}
//
//		/**
//		 * GPS����ʱ����
//		 */
//		public void onProviderEnabled(String provider) {
//			Location location = lm.getLastKnownLocation(provider);
//			updateView(location);
//		}
//
//		/**
//		 * GPS����ʱ����
//		 */
//		public void onProviderDisabled(String provider) {
//			updateView(null);
//		}
//	};

	// ״̬����
//	GpsStatus.Listener listener = new GpsStatus.Listener() {
//		public void onGpsStatusChanged(int event) {
//			switch (event) {
//			// ��һ�ζ�λ
//			case GpsStatus.GPS_EVENT_FIRST_FIX:
//				Log.i(TAG, "��һ�ζ�λ");
//				break;
//			// ����״̬�ı�
//			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//				Log.i(TAG, "����״̬�ı�");
//				// ��ȡ��ǰ״̬
//				GpsStatus gpsStatus = lm.getGpsStatus(null);
//				// ��ȡ���ǿ�����Ĭ�����ֵ
//				int maxSatellites = gpsStatus.getMaxSatellites();
//				// ����һ��������������������
//				Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
//				int count = 0;
//				while (iters.hasNext() && count <= maxSatellites) {
//					GpsSatellite s = iters.next();
//					s.getElevation();
//					count++;
//				}
//				System.out.println("��������" + count + "������");
//				break;
//			// ��λ����
//			case GpsStatus.GPS_EVENT_STARTED:
//				Log.i(TAG, "��λ����");
//				break;
//			// ��λ����
//			case GpsStatus.GPS_EVENT_STOPPED:
//				Log.i(TAG, "��λ����");
//				break;
//			}
//		};
//	};

	/**
	 * ʵʱ�����ı�����
	 * 
	 * @param location
	 */
	private void updateView(Location location) {
		if (location != null) {
			tvline.setText("�豸λ����Ϣ\n���ȣ�");
			tvline.append(String.valueOf(location.getLongitude()));
			tvline.append("\nγ�ȣ�");
			tvline.append(String.valueOf(location.getLatitude()));
			tvline.append("\n����");
			tvline.append(String.valueOf(location.getBearing()));
			tvline.append("\n�ٶȣ�");
			tvline.append(String.valueOf(location.getSpeed()));
		} else {
			// ���EditText����
			tvline.setText("");
		}
	}
	
	
	/**
	 * ����ָ��λ�ü�����صĲ���
	 * @param location ��ǰ��λ��
	 */
	private void fireHandle(Location location){
		//����
		Double curLongitude = location.getLongitude();
		//γ��
		Double curLatitude = location.getLatitude();
		//����
		float curBearing = location.getBearing();
		//�ٶ�
		float curSpeed = location.getSpeed();
		
		Double turnRightLat = lineBean.getTurnLeftLat();
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLat && null != turnRightLng && turnRightLat != 0 && turnRightLng != 0){
			if(Distance.GetDistance(curLongitude, curLatitude, turnRightLng, turnRightLat) < distance){
				tvline.setText(R.string.toast05);
				playMusic(5);
			}
			
		}
		
		Double sidewalkLat = lineBean.getSidewalkLat();
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLat && null != sidewalkLng && 0 != sidewalkLat && 0 != sidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, sidewalkLng, sidewalkLat) < distance){
				tvline.setText(R.string.toast06);
				playMusic(6);
			}
		}
		
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLat && null != passSidewalkLng && 0 != passSidewalkLat && 0 != passSidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSidewalkLng, passSidewalkLat) < distance){
				tvline.setText(R.string.toast07);
				playMusic(7);
			}
		}
		
		Double turnLeftLat = lineBean.getTurnLat();
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLat && null != turnLeftLng && 0 != turnLeftLat && 0 != turnLeftLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLeftLng, turnLeftLat) < distance){
				tvline.setText(R.string.toast08);
				playMusic(8);
			}
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLat && null != aheadDirectLineLng && 0 != aheadDirectLineLat && 0 != aheadDirectLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, aheadDirectLineLng, aheadDirectLineLat) < distance){
				tvline.setText(R.string.toast09);
				playMusic(9);
			}
		}
		
		Double passBusStationLat = lineBean.getPassBusStationLat();
		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLat && null != passBusStationLng && 0 != passBusStationLat && 0 != passBusStationLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passBusStationLng, passBusStationLat) < distance){
				tvline.setText(R.string.toast10);
				playMusic(10);
			}
		}
		
		Double directLineLat = lineBean.getDirectLineLat();
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLat && null != directLineLng && 0 != directLineLat && 0 != directLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, directLineLng, directLineLat) < distance){
				tvline.setText(R.string.toast11);
				playMusic(11);
			}
		}
		
		Double passSchoolLat = lineBean.getPassSchoolLat();
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLat && null != passSchoolLng && 0 != passSchoolLat && 0 != passSchoolLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSchoolLng, passSchoolLat) < distance){
				tvline.setText(R.string.toast12);
				playMusic(12);
			}
		}
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLat && null != changeLanesLng && 0 != changeLanesLat && 0 != changeLanesLng){
			if(Distance.GetDistance(curLongitude, curLatitude, changeLanesLng, changeLanesLat) < distance){
				tvline.setText(R.string.toast13);
				playMusic(13);
			}
		}

		Double slowdownLat = lineBean.getSlowdownLat();
		Double slowdownLng = lineBean.getSlowdownLng();
		if(null != slowdownLat && null != slowdownLng && 0 != slowdownLat && 0 != slowdownLng){
			if(Distance.GetDistance(curLongitude, curLatitude, slowdownLng, slowdownLat) < distance){
				tvline.setText(R.string.toast14);
				playMusic(14);
			}
		}
		
		Double speedLimitLat = lineBean.getSpeedLimitLat();
		Double speedLimitLng = lineBean.getSpeedLimitLng();
		if(null != speedLimitLat && null != speedLimitLng && 0 != speedLimitLat && 0 != speedLimitLng){
			if(Distance.GetDistance(curLongitude, curLatitude, speedLimitLng, speedLimitLat) < distance){
				tvline.setText(R.string.toast15);
				playMusic(15);
			}
		}

		Double passSchoolStationLat = lineBean.getPassSchoolStationLat();
		Double passSchoolStationLng = lineBean.getPassSchoolStationLng();
		if(null != passSchoolStationLat && null != passSchoolStationLng && 0 != passSchoolStationLat && 0 != passSchoolStationLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSchoolStationLng, passSchoolStationLat) < distance){
				tvline.setText(R.string.toast16);
				playMusic(16);
			}
		}
		
		Double turnLat = lineBean.getTurnLat();
		Double turnLng = lineBean.getTurnLng();
		if(null != turnLat && null != turnLng && 0 != turnLat && 0 != turnLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLng, turnLat) < distance){
				tvline.setText(R.string.toast17);
				playMusic(17);
			}
		}

		Double pullOverLat = lineBean.getPullOverLat();
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLat && null != pullOverLng && 0 != pullOverLat && 0 != pullOverLng){
			if(Distance.GetDistance(curLongitude, curLatitude, pullOverLng, pullOverLat) < distance){
				tvline.setText(R.string.toast18);
				playMusic(18);
			}
		}
		
		Double backCarLat = lineBean.getBackCarLat();
		Double backCarLng = lineBean.getBackCarLng();
		if(null != backCarLat && null != backCarLng && 0 != backCarLat && 0 != backCarLng){
			if(Distance.GetDistance(curLongitude, curLatitude, backCarLng, backCarLat) < distance){
				tvline.setText(R.string.toast19);
				playMusic(19);
			}
		}
	}

//	/**
//	 * ���ز�ѯ����
//	 * 
//	 * @return
//	 */
//	private Criteria getCriteria() {
//		Criteria criteria = new Criteria();
//		// ���ö�λ��ȷ�� Criteria.ACCURACY_COARSE�Ƚϴ��ԣ�Criteria.ACCURACY_FINE��ȽϾ�ϸ
//		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		// �����Ƿ�Ҫ���ٶ�
//		criteria.setSpeedRequired(false);
//		// �����Ƿ�������Ӫ���շ�
//		criteria.setCostAllowed(false);
//		// �����Ƿ���Ҫ��λ��Ϣ
//		criteria.setBearingRequired(false);
//		// �����Ƿ���Ҫ������Ϣ
//		criteria.setAltitudeRequired(false);
//		// ���öԵ�Դ������
//		criteria.setPowerRequirement(Criteria.POWER_LOW);
//		return criteria;
//	}
	

	/**
	 * ��̨��������
	 * @param action
	 */
	public void playSound(int id) {
		Intent intent = new Intent();
		intent.putExtra("MSG", id);
		intent.setClass(LineActivity.this, SoundService.class);

		/* ����service serviceҪ��AndroidManifest.xmlע���磺<service></service> */
		startService(intent);
	}
   
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ITEM_ADD && resultCode == RESULT_OK){
			//������·
			Bundle bundle = data.getExtras();
			//btnId
			int btnId = bundle.getInt(CustomConstant.BUTTONID);
			//γ��
			Double lat = bundle.getDouble(CustomConstant.LATITUDE);
			//����
			Double lng = bundle.getDouble(CustomConstant.LONGITUDE);
			
			Log.d(TAG, "onActivityResult() btnId: " + btnId + " lat: " + lat + " lng:��" + lng);
			
			switch (btnId) {
			case R.id.btnTurnRight:
				lineBean.setTurnRightLat(lat);
				lineBean.setTurnRightLng(lng);
				break;
			case R.id.btnSidewalk:
				lineBean.setSidewalkLat(lat);
				lineBean.setSidewalkLng(lng);
				break;
			case R.id.btnPassSidewalk:
				lineBean.setPassSidewalkLat(lat);
				lineBean.setPassSidewalkLng(lng);
				break;
			case R.id.btnTurnLeft:
				lineBean.setTurnLeftLat(lat);
				lineBean.setTurnLeftLng(lng);
				break;
			case R.id.btnAheadDirectLine:
				lineBean.setAheadDirectLineLat(lat);
				lineBean.setAheadDirectLineLng(lng);
				break;
			case R.id.btnPassBusStation:
				lineBean.setPassBusStationLat(lat);
				lineBean.setPassBusStationLng(lng);
				break;
			case R.id.btnDirectLine:
				lineBean.setDirectLineLat(lat);
				lineBean.setDirectLineLng(lng);
				break;
			case R.id.btnPassSchool:
				lineBean.setPassSchoolLat(lat);
				lineBean.setPassSchoolLng(lng);
				break;
			case R.id.btnChangeLanes:
				lineBean.setChangeLanesLat(lat);
				lineBean.setChangeLanesLng(lng);
				break;
			case R.id.btnSlowdown:
				lineBean.setSlowdownLat(lat);
				lineBean.setSlowdownLng(lng);
				break;
			case R.id.btnSpeedLimit:
				lineBean.setSpeedLimitLat(lat);
				lineBean.setSpeedLimitLng(lng);
				break;
			case R.id.btnPassSchoolStation:
				lineBean.setPassSchoolStationLat(lat);
				lineBean.setPassSchoolStationLng(lng);
				break;
			case R.id.btnTurn:
				lineBean.setTurnLat(lat);
				lineBean.setTurnLng(lng);
				break;
			case R.id.btnPullOver:
				lineBean.setPullOverLat(lat);
				lineBean.setPullOverLng(lng);
				break;
			case R.id.btnBackCar:
				lineBean.setBackCarLat(lat);
				lineBean.setBackCarLng(lng);
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
	 * ��ͣ
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		//���ý���ڲ��ţ�����ͣ
		if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
			mMediaPlayer.pause();
		}
		// �Ƴ���λ����
		mLocationManagerProxy.removeUpdates(mPendingIntent);
		unregisterReceiver(mGPSLocationReceiver);
		// ���ٶ�λ
		mLocationManagerProxy.destroy();
	}

	/* 
	 * �ָ�
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
	 * ��������
	 * @param id
	 */
	public void playMusic(int id) {  
        try {
        	// ���ö�ý��  
        	mMediaPlayer.reset();
        	Uri uri = null;
        	
        	switch(id){
				case 1: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd01);
					break;
				
				case 2: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd02);
					break;
				
				case 3: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd03);
					break;
				
				case 4: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd04);
					break;
				
				case 5: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd05);
					break;
				
				case 6: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd06);
					break;
				
				case 7: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd07);
					break;
				
				case 8: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd08);
					break;
				
				case 9: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd09);
					break;
				
				case 10: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd10);
					break;
				
				case 11: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd11);
					break;
				
				case 12: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd12);
					break;
				
				case 13: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd13);
					break;
				
				case 14: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd14);
					break;
				
				case 15:
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd15);
					break;
				
				case 16: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd16);
					break;
				
				case 17: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd17);
					break;
				
				case 18: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd18);
					break;
				
				case 19: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.snd19);
					break;
				
				default:
					//mMediaPlayer.stop();
					break;
        	}
        	
        	if(null != uri){
        		mMediaPlayer.setDataSource(this, uri);
        		// ��ȡmp3�ļ�   
        		//mMediaPlayer.create(this, R.raw.snd01);
        		//mMediaPlayer.setDataSource(getResources().openRawResource(R.raw.snd01));
        		//mMediaPlayer.setDataSource(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));  
        		//Uri uri = Uri.parse(MUSIC_PATH+TestMediaPlayer.mMusicList.get(TestMediaPlayer.currentListItme));  
        		
        		//mMediaPlayer.create(PlayerService.this,uri);  
        		// ׼������   
        		mMediaPlayer.prepare();
        		// ��ʼ����   
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