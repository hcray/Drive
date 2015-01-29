package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.daoliuhe.drive.R;

public class VideoPlayerActivity extends Activity {
	//������
	private VideoView videoPlayer;  
	 
	MediaController mediaco;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ������ͷ��ȫ��
		requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_video_player);
		
		videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
		mediaco = new MediaController(this);
		// File file=new File("/mnt/sdcard/ͨ��¼��/1.mp4");
		// if(file.exists()){
		// VideoView��MediaController���й���
		// }
		videoPlayer.setVideoURI(Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.s3));
		// videoPlayer.setVideoPath(file.getAbsolutePath());
		videoPlayer.setMediaController(mediaco);
		mediaco.setMediaPlayer(videoPlayer);
		// ��VideiView��ȡ����
		videoPlayer.requestFocus();
		videoPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.video_player, menu);
		return true;
	}

}
