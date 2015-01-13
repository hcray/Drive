package com.daoliuhe.drive;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LineActivity extends Activity implements OnLongClickListener{

	public static final String SETTING_INFOS = "LineActivity";
	// private static final int ACTIVITY_LOGIN = 0;
	private static final String TAG = "LineActivity";
	// ����
	private SoundPool soundPool;
	// GPS��λ
	private LocationManager lm;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);

		tvline = (TextView) this.findViewById(R.id.tv_line);

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// �ж�GPS�Ƿ���������
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "�뿪��GPS����...", Toast.LENGTH_SHORT).show();
			// ���ؿ���GPS�������ý���
			// Intent intent = new
			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			// startActivityForResult(intent,0);
			// return;
		}

		// Ϊ��ȡ����λ����Ϣʱ���ò�ѯ����
		String bestProvider = lm.getBestProvider(getCriteria(), true);
		// ��ȡλ����Ϣ
		// ��������ò�ѯҪ��getLastKnownLocation�������˵Ĳ���ΪLocationManager.GPS_PROVIDER
		Location location = lm.getLastKnownLocation(bestProvider);
		updateView(location);
		// ����״̬
		lm.addGpsStatusListener(listener);
		// �󶨼�������4������
		// ����1���豸����GPS_PROVIDER��NETWORK_PROVIDER����
		// ����2��λ����Ϣ�������ڣ���λ����
		// ����3��λ�ñ仯��С���룺��λ�þ���仯������ֵʱ��������λ����Ϣ
		// ����4������
		// ��ע������2��3���������3��Ϊ0�����Բ���3Ϊ׼������3Ϊ0����ͨ��ʱ������ʱ���£�����Ϊ0������ʱˢ��

		// 1�����һ�Σ�����Сλ�Ʊ仯����1�׸���һ�Σ�
		// ע�⣺�˴�����׼ȷ�ȷǳ��ͣ��Ƽ���service��������һ��Thread����run��sleep(10000);Ȼ��ִ��handler.sendMessage(),����λ��
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,
				locationListener);

		soundPool = new SoundPool(19, AudioManager.STREAM_SYSTEM, 5);
		/*
		soundPool.load(this, R.raw.snd01, 1);
		soundPool.load(this, R.raw.snd02, 1);
		soundPool.load(this, R.raw.snd03, 1);
		soundPool.load(this, R.raw.snd04, 1);
		soundPool.load(this, R.raw.snd05, 1);
		soundPool.load(this, R.raw.snd06, 1);
		soundPool.load(this, R.raw.snd07, 1);
		soundPool.load(this, R.raw.snd08, 1);
		soundPool.load(this, R.raw.snd09, 1);
		soundPool.load(this, R.raw.snd10, 1);
		soundPool.load(this, R.raw.snd11, 1);
		soundPool.load(this, R.raw.snd12, 1);
		soundPool.load(this, R.raw.snd13, 1);
		soundPool.load(this, R.raw.snd14, 1);
		soundPool.load(this, R.raw.snd15, 1);
		soundPool.load(this, R.raw.snd16, 1);
		soundPool.load(this, R.raw.snd17, 1);
		soundPool.load(this, R.raw.snd18, 1);
		soundPool.load(this, R.raw.snd19, 1);
		*/
		
		btnLights1 = (Button) this.findViewById(R.id.btnLights1);
		btnLights1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast01);
				// id,��������, ����, ���ȼ�, �Ƿ�ѭ��(0Ϊ��ѭ����-1Ϊѭ��),���ű���(��0.5��2��һ��Ϊ1����ʾ��������)
				soundPool.play(1, 1, 1, 0, 0, 1);
			}
		});
		btnLights1.setOnLongClickListener(this);
		
		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast02);
				soundPool.play(2, 1, 1, 0, 0, 1);

			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast03);
				soundPool.play(3, 1, 1, 0, 0, 1);

			}
		});
		btnLights4 = (Button) this.findViewById(R.id.btnLights4);
		btnLights4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast04);
				soundPool.play(4, 1, 1, 0, 0, 1);

			}
		});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast05);
				soundPool.play(5, 1, 1, 0, 0, 1);

			}
		});

		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
		btnSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast06);
				soundPool.play(6, 1, 1, 0, 0, 1);

			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast07);
				soundPool.play(7, 1, 1, 0, 0, 1);

			}
		});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
		btnTurnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast08);
				soundPool.play(8, 1, 1, 0, 0, 1);

			}
		});
		btnAheadDirectLine = (Button) this
				.findViewById(R.id.btnAheadDirectLine);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast09);
				soundPool.play(9, 1, 1, 0, 0, 1);

			}
		});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
		btnPassBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast10);
				soundPool.play(10, 1, 1, 0, 0, 1);

			}
		});

		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
		btnDirectLine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast11);
				soundPool.play(11, 1, 1, 0, 0, 1);

			}
		});
		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
		btnPassSchool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast12);
				soundPool.play(12, 1, 1, 0, 0, 1);

			}
		});
		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
		btnChangeLanes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast13);
				soundPool.play(13, 1, 1, 0, 0, 1);

			}
		});
		btnSlowdown = (Button) this.findViewById(R.id.btnSlowdown);
		btnSlowdown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast14);
				soundPool.play(14, 1, 1, 0, 0, 1);

			}
		});
		btnSpeedLimit = (Button) this.findViewById(R.id.btnSpeedLimit);
		btnSpeedLimit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast15);
				soundPool.play(15, 1, 1, 0, 0, 1);

			}
		});

		btnPassSchoolStation = (Button) this
				.findViewById(R.id.btnPassSchoolStation);
		btnPassSchoolStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast16);
				soundPool.play(16, 1, 1, 0, 0, 1);

			}
		});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
		btnTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast17);
				soundPool.play(17, 1, 1, 0, 0, 1);

			}
		});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
		btnPullOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast18);
				soundPool.play(18, 1, 1, 0, 0, 1);

			}
		});
		btnBackCar = (Button) this.findViewById(R.id.btnBackCar);
		btnBackCar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText(R.string.toast19);
				soundPool.play(19, 1, 1, 0, 0, 1);

			}
		});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvline.setText("");
				// TODO Auto-generated method stub

			}
		});

	}
	
	@Override
	public boolean onLongClick(View v) {
		Log.i(TAG, "view: " + v.getId());
		
		Intent intent = new Intent();
		intent.setClass(LineActivity.this, LocationActivity.class);
		
		//intent.putExtra(GPSDbAdapter.KEY_ROWID, id);
		startActivity(intent);
		//startActivityForResult(intent, ACTIVITY_ITEMVIEW);
		return true;
	}
	
	// λ�ü���
	private LocationListener locationListener = new LocationListener() {

		/**
		 * λ����Ϣ�仯ʱ����
		 */
		public void onLocationChanged(Location location) {
			updateView(location);
			Log.i(TAG, "ʱ�䣺" + location.getTime());
			Log.i(TAG, "���ȣ�" + location.getLongitude());
			Log.i(TAG, "γ�ȣ�" + location.getLatitude());
			Log.i(TAG, "���Σ�" + location.getAltitude());
		}

		/**
		 * GPS״̬�仯ʱ����
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			// GPS״̬Ϊ�ɼ�ʱ
			case LocationProvider.AVAILABLE:
				Log.i(TAG, "��ǰGPS״̬Ϊ�ɼ�״̬");
				break;
			// GPS״̬Ϊ��������ʱ
			case LocationProvider.OUT_OF_SERVICE:
				Log.i(TAG, "��ǰGPS״̬Ϊ��������״̬");
				break;
			// GPS״̬Ϊ��ͣ����ʱ
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				Log.i(TAG, "��ǰGPS״̬Ϊ��ͣ����״̬");
				break;
			}
		}

		/**
		 * GPS����ʱ����
		 */
		public void onProviderEnabled(String provider) {
			Location location = lm.getLastKnownLocation(provider);
			updateView(location);
		}

		/**
		 * GPS����ʱ����
		 */
		public void onProviderDisabled(String provider) {
			updateView(null);
		}

	};

	// ״̬����
	GpsStatus.Listener listener = new GpsStatus.Listener() {
		public void onGpsStatusChanged(int event) {
			switch (event) {
			// ��һ�ζ�λ
			case GpsStatus.GPS_EVENT_FIRST_FIX:
				Log.i(TAG, "��һ�ζ�λ");
				break;
			// ����״̬�ı�
			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				Log.i(TAG, "����״̬�ı�");
				// ��ȡ��ǰ״̬
				GpsStatus gpsStatus = lm.getGpsStatus(null);
				// ��ȡ���ǿ�����Ĭ�����ֵ
				int maxSatellites = gpsStatus.getMaxSatellites();
				// ����һ��������������������
				Iterator<GpsSatellite> iters = gpsStatus.getSatellites()
						.iterator();
				int count = 0;
				while (iters.hasNext() && count <= maxSatellites) {
					GpsSatellite s = iters.next();
					count++;
				}
				System.out.println("��������" + count + "������");
				break;
			// ��λ����
			case GpsStatus.GPS_EVENT_STARTED:
				Log.i(TAG, "��λ����");
				break;
			// ��λ����
			case GpsStatus.GPS_EVENT_STOPPED:
				Log.i(TAG, "��λ����");
				break;
			}
		};
	};

	/**
	 * ʵʱ�����ı�����
	 * 
	 * @param location
	 */
	private void updateView(Location location) {
		if (location != null) {
			tvline.setText("�豸λ����Ϣ\n\n���ȣ�");
			tvline.append(String.valueOf(location.getLongitude()));
			tvline.append("\nγ�ȣ�");
			tvline.append(String.valueOf(location.getLatitude()));
		} else {
			// ���EditText����
			tvline.setText("");
		}
	}

	/**
	 * ���ز�ѯ����
	 * 
	 * @return
	 */
	private Criteria getCriteria() {
		Criteria criteria = new Criteria();
		// ���ö�λ��ȷ�� Criteria.ACCURACY_COARSE�Ƚϴ��ԣ�Criteria.ACCURACY_FINE��ȽϾ�ϸ
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// �����Ƿ�Ҫ���ٶ�
		criteria.setSpeedRequired(false);
		// �����Ƿ�������Ӫ���շ�
		criteria.setCostAllowed(false);
		// �����Ƿ���Ҫ��λ��Ϣ
		criteria.setBearingRequired(false);
		// �����Ƿ���Ҫ������Ϣ
		criteria.setAltitudeRequired(false);
		// ���öԵ�Դ������
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		return criteria;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.line, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		soundPool.release();
		lm.removeUpdates(locationListener);
	}



}
