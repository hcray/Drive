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
	//��Ŀ��·��
	private Button btnSubjectTwo;
	//��Ŀ����Ƶ
	private Button btnSubjectTwoVideo;
	// ��Ŀ��·��
	private Button btnSubjectThree;
	// ��Ŀ����Ƶ
	private Button btnSubjectThreeVideo;
	// ��������
	private Button btnParamSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��Ŀ��·��
		btnSubjectTwo = (Button) this.findViewById(R.id.btnSubjectTwo);
		btnSubjectTwo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "������...", Toast.LENGTH_SHORT).show();
			}
		});

		//��Ŀ����Ƶ
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
		
		// ��Ŀ��·��
		btnSubjectThree = (Button) this.findViewById(R.id.btnSubjectThree);
		btnSubjectThree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��Ŀ��·��
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SubjectActivity.class);
				startActivity(intent);
			}
		});

		// ��Ŀ����Ƶ
		btnSubjectThreeVideo = (Button) this.findViewById(R.id.btnSubjectThreeVideo);
		btnSubjectThreeVideo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��Ƶ����
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
				// ��������
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
