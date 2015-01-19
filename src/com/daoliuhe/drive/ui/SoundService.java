package com.daoliuhe.drive.ui;

import java.io.IOException;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.R.raw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

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
