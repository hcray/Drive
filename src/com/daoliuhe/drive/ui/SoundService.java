package com.daoliuhe.drive.ui;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import com.daoliuhe.drive.R;

public class SoundService extends Service {
	//多媒体对象
	public static MediaPlayer mMediaPlayer = null; 
	
	// 用户操作  
    private int MSG;
	
	public SoundService() {
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if (mMediaPlayer != null) {
			mMediaPlayer.reset();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

		mMediaPlayer = new MediaPlayer();
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		MSG = intent.getIntExtra("MSG", 0);
		playMusic(MSG);
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void playMusic(int id) {  
        try {
        	// 重置多媒体  
        	mMediaPlayer.reset();
        	Uri uri = null;
        	
        	switch(MSG){
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

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
