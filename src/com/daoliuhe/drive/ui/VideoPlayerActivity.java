package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.tools.CustomConstant;

public class VideoPlayerActivity extends Activity {
	//播放器
	private VideoView videoPlayer;  
	 
	MediaController mediaco;
	
	//返回按钮
	private Button btnEndPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//屏幕常亮
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//去掉标题头，全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_video_player);
		
		
		btnEndPlayer = (Button) findViewById(R.id.btnEndPlayer);
        OnClickListener btnEndPlayerClick = new OnClickListener(){
			@Override
			public void onClick(View v) {
				//取消
				finish();
			}
        };
        btnEndPlayer.setOnClickListener(btnEndPlayerClick);
		
		Bundle extras = this.getIntent().getExtras();
		
		videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
		mediaco = new MediaController(this);
		if (extras != null) {
			int videoPlayerId = extras.getInt(CustomConstant.VIDEO_ID);
			videoPlayer.setVideoURI(Uri.parse("android.resource://"+ getPackageName() + "/" + videoPlayerId));
		videoPlayer.setMediaController(mediaco);
		mediaco.setMediaPlayer(videoPlayer);
		// 让VideiView获取焦点
		videoPlayer.requestFocus();
		videoPlayer.start();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.video_player, menu);
		return true;
	}

}
