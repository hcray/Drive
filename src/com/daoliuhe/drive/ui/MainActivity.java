package com.daoliuhe.drive.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.daoliuhe.drive.R;

public class MainActivity extends Activity {
	// 科目三
	private Button btnSubjectThree;
	// 科目三
	private Button btnSubjectThreeVideo;
	// 参数设置
	private Button btnParamSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		btnSubjectThreeVideo = (Button) this.findViewById(R.id.btnSubjectThreeVideo);
		btnSubjectThreeVideo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 视频播放
				String uri = "android.resource://" + getPackageName() + "/" + R.raw.s3;
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				intent.setDataAndType(Uri.parse(uri) ,"video/mp4");
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
