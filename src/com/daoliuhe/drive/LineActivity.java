package com.daoliuhe.drive;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LineActivity extends Activity {
	public static final String SETTING_INFOS = "LineActivity";
	//private static final int ACTIVITY_LOGIN = 0;
	//语音
	private SoundPool soundPool;

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

		soundPool= new SoundPool(10,AudioManager.STREAM_SYSTEM,5);
		soundPool.load(this,R.raw.snd01,1);
		soundPool.load(this,R.raw.snd02,1);
		soundPool.load(this,R.raw.snd03,1);
		soundPool.load(this,R.raw.snd04,1);
		soundPool.load(this,R.raw.snd05,1);
		soundPool.load(this,R.raw.snd06,1);
		soundPool.load(this,R.raw.snd07,1);
		soundPool.load(this,R.raw.snd08,1);
		soundPool.load(this,R.raw.snd09,1);
		soundPool.load(this,R.raw.snd10,1);
		soundPool.load(this,R.raw.snd11,1);
		soundPool.load(this,R.raw.snd12,1);
		soundPool.load(this,R.raw.snd13,1);
		soundPool.load(this,R.raw.snd14,1);
		soundPool.load(this,R.raw.snd15,1);
		soundPool.load(this,R.raw.snd16,1);
		soundPool.load(this,R.raw.snd17,1);
		soundPool.load(this,R.raw.snd18,1);
		soundPool.load(this,R.raw.snd19,1);
		
		btnLights1 = (Button) this.findViewById(R.id.btnLights1);
		btnLights1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//id,左右声道, 音量, 优先级, 是否循环(0为不循环，-1为循环),播放比率(从0.5到2，一般为1，表示正常播放)
				soundPool.play(1, 1, 1, 0, 0, 1);
			}
			
		});
		btnLights2 = (Button) this.findViewById(R.id.btnLights2);
		btnLights2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(2, 1, 1, 0, 0, 1);
				
			}
		});
		btnLights3 = (Button) this.findViewById(R.id.btnLights3);
		btnLights3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				soundPool.play(3, 1, 1, 0, 0, 1);
				
			}});
		btnLights4 = (Button) this.findViewById(R.id.btnLights4);
		btnLights4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				soundPool.play(4, 1, 1, 0, 0, 1);
				
			}});
		btnTurnRight = (Button) this.findViewById(R.id.btnTurnRight);
		btnTurnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				soundPool.play(5, 1, 1, 0, 0, 1);
				
			}});
		
		btnSidewalk = (Button) this.findViewById(R.id.btnSidewalk);
		btnSidewalk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(6, 1, 1, 0, 0, 1);
				
			}
		});
		btnPassSidewalk = (Button) this.findViewById(R.id.btnPassSidewalk);
		btnPassSidewalk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				soundPool.play(7, 1, 1, 0, 0, 1);
				
			}});
		btnTurnLeft = (Button) this.findViewById(R.id.btnTurnLeft);
		btnTurnLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(8, 1, 1, 0, 0, 1);
				
			}});
		btnAheadDirectLine = (Button) this.findViewById(R.id.btnAheadDirectLine);
		btnAheadDirectLine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(9, 1, 1, 0, 0, 1);
				
			}});
		btnPassBusStation = (Button) this.findViewById(R.id.btnPassBusStation);
		btnPassBusStation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(10, 1, 1, 0, 0, 1);
				
			}});

		btnDirectLine = (Button) this.findViewById(R.id.btnDirectLine);
		btnDirectLine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(11, 1, 1, 0, 0, 1);
				
			}});
		btnPassSchool = (Button) this.findViewById(R.id.btnPassSchool);
		btnPassSchool.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(12, 1, 1, 0, 0, 1);
				
			}});
		btnChangeLanes = (Button) this.findViewById(R.id.btnChangeLanes);
		btnChangeLanes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(13, 1, 1, 0, 0, 1);
				
			}});
		btnSlowdown = (Button) this.findViewById(R.id.btnSlowdown);
		btnSlowdown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(14, 1, 1, 0, 0, 1);
				
			}});
		btnSpeedLimit = (Button) this.findViewById(R.id.btnSpeedLimit);
		btnSpeedLimit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(15, 1, 1, 0, 0, 1);
				
			}});

		btnPassSchoolStation = (Button) this.findViewById(R.id.btnPassSchoolStation);
		btnPassSchoolStation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(16, 1, 1, 0, 0, 1);
				
			}});
		btnTurn = (Button) this.findViewById(R.id.btnTurn);
		btnTurn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(17, 1, 1, 0, 0, 1);
				
			}});
		btnPullOver = (Button) this.findViewById(R.id.btnPullOver);
		btnPullOver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(18, 1, 1, 0, 0, 1);
				
			}});
		btnBackCar = (Button) this.findViewById(R.id.btnBackCar);
		btnBackCar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.play(19, 1, 1, 0, 0, 1);
				
			}});
		btnReset = (Button) this.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
		
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
	}

}
