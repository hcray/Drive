package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.tools.CustomConstant;

public class MainActivity extends Activity {
	//科目二路线
	private Button btnSubjectTwo;
	//科目二视频
	private Button btnSubjectTwoVideo;
	// 科目三路线
	private Button btnSubjectThree;
	// 科目三视频
	private Button btnSubjectThreeVideo;
	// 参数设置
	private Button btnParamSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//科目二路线
		btnSubjectTwo = (Button) this.findViewById(R.id.btnSubjectTwo);
		btnSubjectTwo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "建设中...", Toast.LENGTH_SHORT).show();
			}
		});

		//科目二视频
		btnSubjectTwoVideo = (Button) this.findViewById(R.id.btnSubjectTwoVideo);
		btnSubjectTwoVideo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, VideoPlayerActivity.class);
				intent.putExtra(CustomConstant.VIDEO_ID, R.raw.s2);
				startActivity(intent);
			}
		});
		
		// 科目三路线
		btnSubjectThree = (Button) this.findViewById(R.id.btnSubjectThree);
		btnSubjectThree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 科目三路线
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SubjectActivity.class);
				startActivity(intent);
			}
		});

		// 科目三视频
		btnSubjectThreeVideo = (Button) this.findViewById(R.id.btnSubjectThreeVideo);
		btnSubjectThreeVideo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 视频播放
//				String uri = "android.resource://" + getPackageName() + "/" + R.raw.s3;
//				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//				intent.setDataAndType(Uri.parse(uri) ,"video/mp4");
//				startActivity(intent);
				
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, VideoPlayerActivity.class);
				intent.putExtra(CustomConstant.VIDEO_ID, R.raw.s3);
				startActivity(intent);
			}
		});
		
		btnParamSettings = (Button) this.findViewById(R.id.btnParamSettings);
		btnParamSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 参数设置
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ParamActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
