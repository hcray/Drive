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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.LocationManagerProxy;
import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;
import com.daoliuhe.drive.tools.CustomConstant;
import com.daoliuhe.drive.tools.DbAdapter;

/**
 * @author CYY
 *
 */
public class LineActivity extends Activity /* implements OnLongClickListener*/{

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

	//private Handler mHandler = new Handler() {
	//
	//};

	private TextView tvline;

	private Button btnLights1;
	private Button btnLights2;
	private Button btnLights3;
	private Button btnShiftGears;
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
	//��ǰ�ľ���
	private Double curLongitude;
	//��ǰ��γ��
	private Double curLatitude;
	//��ǰ�ķ�λ
	private float curBearing;
	//��������
    private Double distance;
    //�Ƕ����
    private float angleError;
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
        String distanceValue =  settings.getString(CustomConstant.DISTANCE_KEY, CustomConstant.DISTANCE_VALUE);
        distance = Double.parseDouble(distanceValue);
        //�Ƕ����
        String angleErrorValue =  settings.getString(CustomConstant.ANGLEERROR_KEY, CustomConstant.ANGLEERROR_VALUE);
        angleError = Float.parseFloat(angleErrorValue);
        //ˢ��Ƶ��
        String refreshValue =  settings.getString(CustomConstant.REFRESH_KEY, CustomConstant.REFRESH_VALUE);
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
		}
		
		if (mMediaPlayer != null) {
			mMediaPlayer.reset();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

		mMediaPlayer = new MediaPlayer();

		tvline = (TextView) this.findViewById(R.id.tv_line);
		tvline.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		//���ֱ�׼
		btnScoring = (Button) this.findViewById(R.id.btnScoring);
		OnClickListener btnScoringClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//�����ֱ�׼
				openScoring(0);
			}
		};
		btnScoring.setOnClickListener(btnScoringClick);
		//����
		btnLineReturn = (Button) this.findViewById(R.id.btnLineReturn);
		OnClickListener btnLineReturnClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//ȡ��
				finish();
			}
		};
		btnLineReturn.setOnClickListener(btnLineReturnClick);
		
		
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
				//tvline.setText(R.string.toast01);
				// id,��������, ����, ���ȼ�, �Ƿ�ѭ��(0Ϊ��ѭ����-1Ϊѭ��),���ű���(��0.5��2��һ��Ϊ1����ʾ��������)
				playMusic(v.getId());
			}
		});
		
		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast02);
				playMusic(v.getId());

			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast03);
				playMusic(v.getId());

			}
		});
		btnShiftGears = (Button) this.findViewById(R.id.btnShiftGears);
		btnShiftGears.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast04);
				playMusic(v.getId());

			}
		});
		
		
		btnStart = (Button) this.findViewById(R.id.btnStart);
		//btnStart.setOnLongClickListener(this);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast05);
				playMusic(v.getId());
				openScoring(2);
			}
		});
		
		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
//		btnChangeLanes.setOnLongClickListener(this);
		btnChangeLanes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast06);
				playMusic(v.getId());
				openScoring(5);
			}
		});
		btnAheadDirectLine = (Button) this.findViewById(R.id.btnAheadDirectLine);
//		btnAheadDirectLine.setOnLongClickListener(this);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast07);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
//		btnTurnLeft.setOnLongClickListener(this);
		btnTurnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast08);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
//		btnTurnRight.setOnLongClickListener(this);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast09);
				playMusic(v.getId());
				openScoring(6);
			}
		});
		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
//		btnSidewalk.setOnLongClickListener(this);
		btnSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast10);
				playMusic(v.getId());
				openScoring(7);
			}
		});

		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
//		btnPassSchool.setOnLongClickListener(this);
		btnPassSchool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast11);
				playMusic(v.getId());
				openScoring(8);
			}
		});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
//		btnPassBusStation.setOnLongClickListener(this);
		btnPassBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast12);
				playMusic(v.getId());
				openScoring(9);
			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
//		btnPassSidewalk.setOnLongClickListener(this);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast13);
				playMusic(v.getId());
				openScoring(7);
			}
		});
		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
//		btnDirectLine.setOnLongClickListener(this);
		btnDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast14);
				playMusic(v.getId());
				openScoring(3);
			}
		});
		btnEndDirectLine = (Button) this.findViewById(R.id.btnEndDirectLine);
//		btnEndDirectLine.setOnLongClickListener(this);
		btnEndDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast15);
				playMusic(v.getId());
				openScoring(3);
			}
		});

		btnOvertake = (Button) this.findViewById(R.id.btnOvertake);
//		btnOvertake.setOnLongClickListener(this);
		btnOvertake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast16);
				playMusic(v.getId());
				openScoring(11);
			}
		});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
//		btnTurn.setOnLongClickListener(this);
		btnTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast17);
				playMusic(v.getId());
				openScoring(12);
			}
		});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
//		btnPullOver.setOnLongClickListener(this);
		btnPullOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast18);
				playMusic(v.getId());
				openScoring(13);
			}
		});
		btnPassing = (Button) this.findViewById(R.id.btnPassing);
//		btnPassing.setOnLongClickListener(this);
		btnPassing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText(R.string.toast19);
				playMusic(v.getId());
				openScoring(10);
			}
		});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//tvline.setText("");
				playMusic(v.getId());

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
				
				
				curLongitude = location.getLongitude();
				curLatitude = location.getLatitude();
				//���ض�λ��λ�����򣩣��Զ�Ϊ��λ������������˳ʱ��ĽǶȡ�
				curBearing = location.getBearing();
				//���ض�λ�ٶ� ����λ����/�룬�����λ�ò������ٶȣ��򷵻�0.0 ��
				//float curSpeed = location.getSpeed();
				
				fireHandler(location);
				
				
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
				LocationManagerProxy.GPS_PROVIDER, refresh.longValue(), distance.floatValue()/2,
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

	/*
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
			case R.id.btnChangeLanes:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getChangeLanesLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getChangeLanesLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getChangeLanesBr());
				break;
			case R.id.btnAheadDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getAheadDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getAheadDirectLineLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getAheadDirectLineBr());
				break;
			case R.id.btnTurnLeft:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLeftLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLeftLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getTurnLeftBr());
				break;
			case R.id.btnTurnRight:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnRightLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnRightLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getTurnRightBr());
				break;
			case R.id.btnSidewalk:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getSidewalkLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getSidewalkLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getSidewalkBr());
				break;
			case R.id.btnPassSchool:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSchoolLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSchoolLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getPassSchoolBr());
				break;
			case R.id.btnPassBusStation:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassBusStationLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassBusStationLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getPassBusStationBr());
				break;
			case R.id.btnPassSidewalk:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassSidewalkLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassSidewalkLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getPassSidewalkBr());
				break;
			case R.id.btnDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getDirectLineLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getDirectLineBr());
				break;
			case R.id.btnEndDirectLine:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getEndDirectLineLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getEndDirectLineLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getEndDirectLineBr());
				break;
			case R.id.btnOvertake:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getOvertakeLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getOvertakeLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getOvertakeBr());
				break;
			case R.id.btnTurn:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getTurnLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getTurnLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getTurnBr());
				break;
			case R.id.btnPullOver:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPullOverLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPullOverLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getPullOverBr());
				break;
			case R.id.btnPassing:
				intent.putExtra(CustomConstant.LATITUDE, lineBean.getPassingLat());
				intent.putExtra(CustomConstant.LONGITUDE, lineBean.getPassingLng());
				intent.putExtra(CustomConstant.BEARING, lineBean.getPassingBr());
				break;
		}
		//�ĸ�button������
		intent.putExtra(CustomConstant.BUTTONID, viewId);
		//��ǰ�ľ�γ��
		intent.putExtra(CustomConstant.CUR_LATITUDE, curLatitude);
		intent.putExtra(CustomConstant.CUR_LONGITUDE, curLongitude);
		intent.putExtra(CustomConstant.CUR_BEARING, curBearing);
		startActivityForResult(intent, ACTIVITY_ITEM_ADD);
		return true;
	}
	*/
	
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
			tvline.setText("location info\nLongitude��");
			tvline.append(String.valueOf(location.getLongitude()));
			tvline.append("\nLatitude��");
			tvline.append(String.valueOf(location.getLatitude()));
			tvline.append("\nBearing��");
			tvline.append(String.valueOf(location.getBearing()));
			tvline.append("\nSpeed��");
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
	private void fireHandler(Location location){
		//����
		Double curLongitude = location.getLongitude();
		//γ��
		Double curLatitude = location.getLatitude();
		//����
		float curBearing = location.getBearing();
		//�ٶ�
		//float curSpeed = location.getSpeed();
		
		/*		
 		Double changeLanesLat = lineBean.getChangeLanesLat();
		Double changeLanesLng = lineBean.getChangeLanesLng();
		Float changeLanesBr = lineBean.getChangeLanesBr();
		
		if(null != changeLanesLat && null != changeLanesLng && 0 != changeLanesLat && 0 != changeLanesLng){
			if (Distance.GetDistance(curLongitude, curLatitude, changeLanesLng,
					changeLanesLat) < distance
					&& Distance.getDiff(curBearing, changeLanesBr) < angleError) {
				//tvline.setText(R.string.toast06);
				playMusic(R.id.btnChangeLanes);
				openScoring(5);
			}
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		Float aheadDirectLineBr = lineBean.getAheadDirectLineBr();
		if(null != aheadDirectLineLat && null != aheadDirectLineLng && 0 != aheadDirectLineLat && 0 != aheadDirectLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, aheadDirectLineLng, aheadDirectLineLat) < distance 
					&& Distance.getDiff(curBearing, aheadDirectLineBr) < angleError){
				//tvline.setText(R.string.toast07);
				playMusic(R.id.btnAheadDirectLine);
				openScoring(3);
			}
		}
		
		Double turnLeftLat = lineBean.getTurnLat();
		Double turnLeftLng = lineBean.getTurnLeftLng();
		Float turnLeftBr = lineBean.getTurnLeftBr();
		if(null != turnLeftLat && null != turnLeftLng && 0 != turnLeftLat && 0 != turnLeftLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLeftLng, turnLeftLat) < distance
					&& Distance.getDiff(curBearing, turnLeftBr) < angleError){
				//tvline.setText(R.string.toast08);
				playMusic(R.id.btnTurnLeft);
				openScoring(6);
			}
		}
		Double turnRightLat = lineBean.getTurnRightLat();
		Double turnRightLng = lineBean.getTurnRightLng();
		Float turnRightBr = lineBean.getTurnRightBr();
		if(null != turnRightLat && null != turnRightLng && turnRightLat != 0 && turnRightLng != 0){
			if(Distance.GetDistance(curLongitude, curLatitude, turnRightLng, turnRightLat) < distance
					&& Distance.getDiff(curBearing, turnRightBr) < angleError){
				//tvline.setText(R.string.toast09);
				playMusic(R.id.btnTurnRight);
				openScoring(6);
			}
		}
		Double sidewalkLat = lineBean.getSidewalkLat();
		Double sidewalkLng = lineBean.getSidewalkLng();
		Float sidewalkBr = lineBean.getSidewalkBr();
		if(null != sidewalkLat && null != sidewalkLng && 0 != sidewalkLat && 0 != sidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, sidewalkLng, sidewalkLat) < distance
					&& Distance.getDiff(curBearing, sidewalkBr) < angleError){
				//tvline.setText(R.string.toast10);
				playMusic(R.id.btnSidewalk);
				openScoring(7);
			}
		}
		Double passSchoolLat = lineBean.getPassSchoolLat();
		Double passSchoolLng = lineBean.getPassSchoolLng();
		Float passSchoolBr = lineBean.getPassSchoolBr();
		if(null != passSchoolLat && null != passSchoolLng && 0 != passSchoolLat && 0 != passSchoolLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSchoolLng, passSchoolLat) < distance
					&& Distance.getDiff(curBearing, passSchoolBr) < angleError){
				//tvline.setText(R.string.toast11);
				playMusic(R.id.btnPassSchool);
				openScoring(8);
			}
		}
		Double passBusStationLat = lineBean.getPassBusStationLat();
		Double passBusStationLng = lineBean.getPassBusStationLng();
		Float passBusStationBr = lineBean.getPassBusStationBr();
		if(null != passBusStationLat && null != passBusStationLng && 0 != passBusStationLat && 0 != passBusStationLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passBusStationLng, passBusStationLat) < distance
					&& Distance.getDiff(curBearing, passBusStationBr) < angleError){
				//tvline.setText(R.string.toast12);
				playMusic(R.id.btnPassBusStation);
				openScoring(9);
			}
		}
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		Float passSidewalkBr = lineBean.getPassSidewalkBr();
		if(null != passSidewalkLat && null != passSidewalkLng && 0 != passSidewalkLat && 0 != passSidewalkLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passSidewalkLng, passSidewalkLat) < distance
					&& Distance.getDiff(curBearing, passSidewalkBr) < angleError){
				//tvline.setText(R.string.toast13);
				playMusic(R.id.btnPassSidewalk);
				openScoring(7);
			}
		}
		Double directLineLat = lineBean.getDirectLineLat();
		Double directLineLng = lineBean.getDirectLineLng();
		Float directLineBr = lineBean.getDirectLineBr();
		if(null != directLineLat && null != directLineLng && 0 != directLineLat && 0 != directLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, directLineLng, directLineLat) < distance
					&& Distance.getDiff(curBearing, directLineBr) < angleError){
				//tvline.setText(R.string.toast14);
				playMusic(R.id.btnDirectLine);
				openScoring(3);
			}
		}
		Double endDirectLineLat = lineBean.getEndDirectLineLat();
		Double endDirectLineLng = lineBean.getEndDirectLineLng();
		Float endDirectLineBr = lineBean.getEndDirectLineBr();
		if(null != endDirectLineLat && null != endDirectLineLng && 0 != endDirectLineLat && 0 != endDirectLineLng){
			if(Distance.GetDistance(curLongitude, curLatitude, endDirectLineLng, endDirectLineLat) < distance
					&& Distance.getDiff(curBearing, endDirectLineBr) < angleError){
				//tvline.setText(R.string.toast15);
				playMusic(R.id.btnEndDirectLine);
				openScoring(3);
			}
		}
		Double overtakeLat = lineBean.getOvertakeLat();
		Double overtakeLng = lineBean.getOvertakeLng();
		Float overtakeBr = lineBean.getOvertakeBr();
		if(null != overtakeLat && null != overtakeLng && 0 != overtakeLat && 0 != overtakeLng){
			if(Distance.GetDistance(curLongitude, curLatitude, overtakeLng, overtakeLat) < distance
					&& Distance.getDiff(curBearing, overtakeBr) < angleError){
				//tvline.setText(R.string.toast16);
				playMusic(R.id.btnOvertake);
				openScoring(11);
			}
		}
		Double turnLat = lineBean.getTurnLat();
		Double turnLng = lineBean.getTurnLng();
		Float turnBr = lineBean.getTurnBr();
		if(null != turnLat && null != turnLng && 0 != turnLat && 0 != turnLng){
			if(Distance.GetDistance(curLongitude, curLatitude, turnLng, turnLat) < distance
					&& Distance.getDiff(curBearing, turnBr) < angleError){
				//tvline.setText(R.string.toast17);
				playMusic(R.id.btnTurn);
				openScoring(12);
			}
		}

		Double pullOverLat = lineBean.getPullOverLat();
		Double pullOverLng = lineBean.getPullOverLng();
		Float pullOverBr = lineBean.getPullOverBr();
		if(null != pullOverLat && null != pullOverLng && 0 != pullOverLat && 0 != pullOverLng){
			if(Distance.GetDistance(curLongitude, curLatitude, pullOverLng, pullOverLat) < distance
					&& Distance.getDiff(curBearing, pullOverBr) < angleError){
				//tvline.setText(R.string.toast18);
				playMusic(R.id.btnPullOver);
				openScoring(13);
			}
		}
		
		Double passingLat = lineBean.getPassingLat();
		Double passingLng = lineBean.getPassingLng();
		Float passingBr = lineBean.getPassingBr();
		if(null != passingLat && null != passingLng && 0 != passingLat && 0 != passingLng){
			if(Distance.GetDistance(curLongitude, curLatitude, passingLng, passingLat) < distance
					&& Distance.getDiff(curBearing, passingBr) < angleError){
				//tvline.setText(R.string.toast19);
				playMusic(R.id.btnPassing);
				openScoring(10);
			}
		}
		*/
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
			//��λ
			Float bearing = bundle.getFloat(CustomConstant.BEARING);
			
			Log.d(TAG, "onActivityResult() btnId: " + btnId + " lat: " + lat + " lng:��" + lng + " bearing: " + bearing);
			
			switch (btnId) {
			case R.id.btnChangeLanes:
//				lineBean.setChangeLanesLat(lat);
//				lineBean.setChangeLanesLng(lng);
//				lineBean.setChangeLanesBr(bearing);
				break;
			case R.id.btnAheadDirectLine:
//				lineBean.setAheadDirectLineLat(lat);
//				lineBean.setAheadDirectLineLng(lng);
//				lineBean.setAheadDirectLineBr(bearing);
				break;
			case R.id.btnTurnLeft:
//				lineBean.setTurnLeftLat(lat);
//				lineBean.setTurnLeftLng(lng);
//				lineBean.setTurnLeftBr(bearing);
				break;
			case R.id.btnTurnRight:
//				lineBean.setTurnRightLat(lat);
//				lineBean.setTurnRightLng(lng);
//				lineBean.setTurnRightBr(bearing);
				break;
			case R.id.btnSidewalk:
//				lineBean.setSidewalkLat(lat);
//				lineBean.setSidewalkLng(lng);
//				lineBean.setSidewalkBr(bearing);
				break;
			case R.id.btnPassSchool:
//				lineBean.setPassSchoolLat(lat);
//				lineBean.setPassSchoolLng(lng);
//				lineBean.setPassSchoolBr(bearing);
				break;
			case R.id.btnPassBusStation:
//				lineBean.setPassBusStationLat(lat);
//				lineBean.setPassBusStationLng(lng);
//				lineBean.setPassBusStationBr(bearing);
				break;
			case R.id.btnPassSidewalk:
//				lineBean.setPassSidewalkLat(lat);
//				lineBean.setPassSidewalkLng(lng);
//				lineBean.setPassSidewalkBr(bearing);
				break;
			case R.id.btnDirectLine:
//				lineBean.setDirectLineLat(lat);
//				lineBean.setDirectLineLng(lng);
//				lineBean.setDirectLineBr(bearing);
				break;
			case R.id.btnEndDirectLine:
//				lineBean.setEndDirectLineLat(lat);
//				lineBean.setEndDirectLineLng(lng);
//				lineBean.setEndDirectLineBr(bearing);
				break;
			case R.id.btnOvertake:
//				lineBean.setOvertakeLat(lat);
//				lineBean.setOvertakeLng(lng);
//				lineBean.setOvertakeBr(bearing);
				break;
			case R.id.btnTurn:
//				lineBean.setTurnLat(lat);
//				lineBean.setTurnLng(lng);
//				lineBean.setTurnBr(bearing);
				break;
			case R.id.btnPullOver:
//				lineBean.setPullOverLat(lat);
//				lineBean.setPullOverLng(lng);
//				lineBean.setPullOverBr(bearing);
				break;
			case R.id.btnPassing:
//				lineBean.setPassingLat(lat);
//				lineBean.setPassingLng(lng);
//				lineBean.setPassingBr(bearing);
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
		//if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
		//	mMediaPlayer.pause();
		//}
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
	 * ����������
	 * @param id
	 */
	public void openScoring(int id){
		Intent intent = new Intent();
		intent.setClass(LineActivity.this, ScoringActivity.class);
		intent.putExtra(CustomConstant.SELECT_ITEM, id);
		startActivity(intent);
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
				case R.id.btnLights1: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light01);
					break;
				
				case R.id.btnLights2: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light02);
					break;
				
				case R.id.btnLights3: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.light03);
					break;
				
				case R.id.btnShiftGears: 
					uri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.shift_gears);
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
