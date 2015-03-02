package com.daoliuhe.drive.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.adapter.ListViewActivityAdapter;
import com.daoliuhe.drive.tools.CustomConstant;

public class ScoringActivity extends Activity {
	private static final String TAG = "ScoringActivity";

	private ListView leftListView;

	private ListView rightListView;

	private List<String> leftList = new ArrayList<String>();

	private List<String> rightList = new ArrayList<String>();

	private ListViewActivityAdapter adapterRight;

	// 关闭页面标志
	private boolean closeFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoring);

		Bundle extras = this.getIntent().getExtras();
		int videoPlayerId = 0;
		if (extras != null) {
			videoPlayerId = extras.getInt(CustomConstant.SELECT_ITEM);
		}

		initList(videoPlayerId);

		OnTouchListener touchListener = new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "onTouch() closeFlag: " + closeFlag);
				if (closeFlag) {
					closeFlag = false;
				}
				return false;
			}
		};

		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Log.d(TAG, "onItemClick() position: " + position);
				//int viewId = 0;

				TextView tv = (TextView)view.findViewById(R.id.tvItemLineName);
				String tvText = tv.getText().toString();

				clickLeftListItem(tvText);
			}
		};

		leftListView = (ListView) this.findViewById(R.id.leftList);

		leftListView.setOnTouchListener(touchListener);

		leftListView.setOnItemClickListener(itemClickListener);

		rightListView = (ListView) this.findViewById(R.id.rightList);

		rightListView.setOnTouchListener(touchListener);

		ListViewActivityAdapter adapterLeft = new ListViewActivityAdapter(this,
				leftList);
		leftListView.setAdapter(adapterLeft);

		adapterRight = new ListViewActivityAdapter(this, rightList);
		rightListView.setAdapter(adapterRight);

		// 5秒后关闭
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				// 如果没有操作，则关闭
				Log.d(TAG, "closeFlag: " + closeFlag);
				if (closeFlag) {
					finish();
				}
			}
		}, 3000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.scoring, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent() closeFlag: " + closeFlag);
		if (closeFlag) {
			closeFlag = false;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 初始化list
	 * 
	 * @param id
	 */
	private void initList(int id) {

		String item1Name = this.getResources().getString(R.string.item1);
		String item2Name = this.getResources().getString(R.string.item2);
		String item3Name = this.getResources().getString(R.string.item3);
		String item4Name = this.getResources().getString(R.string.item4);
		String item5Name = this.getResources().getString(R.string.item5);
		String item6Name = this.getResources().getString(R.string.item6);
		String item7Name = this.getResources().getString(R.string.item7);
		String item8Name = this.getResources().getString(R.string.item8);
		String item9Name = this.getResources().getString(R.string.item9);
		String item10Name = this.getResources().getString(R.string.item10);
		String item11Name = this.getResources().getString(R.string.item11);
		String item12Name = this.getResources().getString(R.string.item12);
		String item13Name = this.getResources().getString(R.string.item13);

		String[] item1Detail = this.getResources().getStringArray(
				R.array.item1Detail);
		String[] item2Detail = this.getResources().getStringArray(
				R.array.item2Detail);
		String[] item3Detail = this.getResources().getStringArray(
				R.array.item3Detail);
		String[] item4Detail = this.getResources().getStringArray(
				R.array.item4Detail);
		String[] item5Detail = this.getResources().getStringArray(
				R.array.item5Detail);
		String[] item6Detail = this.getResources().getStringArray(
				R.array.item6Detail);
		String[] item7Detail = this.getResources().getStringArray(
				R.array.item7Detail);
		String[] item8Detail = this.getResources().getStringArray(
				R.array.item8Detail);
		String[] item9Detail = this.getResources().getStringArray(
				R.array.item9Detail);
		String[] item10Detail = this.getResources().getStringArray(
				R.array.item10Detail);
		String[] item11Detail = this.getResources().getStringArray(
				R.array.item11Detail);
		String[] item12Detail = this.getResources().getStringArray(
				R.array.item12Detail);
		String[] item13Detail = this.getResources().getStringArray(
				R.array.item13Detail);

		switch (id) {
		case 1:
			leftList.add(item1Name);
			rightList.addAll(Arrays.asList(item1Detail));
			break;
		case 2:
			leftList.add(item2Name);
			rightList.addAll(Arrays.asList(item2Detail));
			break;
		case 3:
			leftList.add(item3Name);
			rightList.addAll(Arrays.asList(item3Detail));
			break;
		case 4:
			leftList.add(item4Name);
			rightList.addAll(Arrays.asList(item4Detail));
			break;
		case 5:
			leftList.add(item5Name);
			rightList.addAll(Arrays.asList(item5Detail));
			break;
		case 6:
			leftList.add(item6Name);
			rightList.addAll(Arrays.asList(item6Detail));
			break;
		case 7:
			leftList.add(item7Name);
			rightList.addAll(Arrays.asList(item7Detail));
			break;
		case 8:
			leftList.add(item8Name);
			rightList.addAll(Arrays.asList(item8Detail));
			break;
		case 9:
			leftList.add(item9Name);
			rightList.addAll(Arrays.asList(item9Detail));
			break;
		case 10:
			leftList.add(item10Name);
			rightList.addAll(Arrays.asList(item10Detail));
			break;
		case 11:
			leftList.add(item11Name);
			rightList.addAll(Arrays.asList(item11Detail));
			break;
		case 12:
			leftList.add(item12Name);
			rightList.addAll(Arrays.asList(item12Detail));
			break;
		case 13:
			leftList.add(item13Name);
			rightList.addAll(Arrays.asList(item13Detail));
			break;
		default:
			leftList.add(item1Name);
			leftList.add(item2Name);
			leftList.add(item3Name);
			leftList.add(item4Name);
			leftList.add(item5Name);
			leftList.add(item6Name);
			leftList.add(item7Name);
			leftList.add(item8Name);
			leftList.add(item9Name);
			leftList.add(item10Name);
			leftList.add(item11Name);
			leftList.add(item12Name);
			leftList.add(item13Name);
			// 添加项目明细
			rightList.addAll(Arrays.asList(item1Detail));
			rightList.addAll(Arrays.asList(item2Detail));
			rightList.addAll(Arrays.asList(item3Detail));
			rightList.addAll(Arrays.asList(item4Detail));
			rightList.addAll(Arrays.asList(item5Detail));
			rightList.addAll(Arrays.asList(item6Detail));
			rightList.addAll(Arrays.asList(item7Detail));
			rightList.addAll(Arrays.asList(item8Detail));
			rightList.addAll(Arrays.asList(item9Detail));
			rightList.addAll(Arrays.asList(item10Detail));
			rightList.addAll(Arrays.asList(item11Detail));
			rightList.addAll(Arrays.asList(item12Detail));
			rightList.addAll(Arrays.asList(item13Detail));
			break;
		}

	}

	/**
	 * 左边栏的点击事件
	 * 
	 * @param id
	 */
	private void clickLeftListItem(String tvText) {
		Log.d(TAG, "clickLeftListItem(), tvText: " + tvText);
		rightList = new ArrayList<String>();
		String item1Name = this.getResources().getString(R.string.item1);
		String item2Name = this.getResources().getString(R.string.item2);
		String item3Name = this.getResources().getString(R.string.item3);
		String item4Name = this.getResources().getString(R.string.item4);
		String item5Name = this.getResources().getString(R.string.item5);
		String item6Name = this.getResources().getString(R.string.item6);
		String item7Name = this.getResources().getString(R.string.item7);
		String item8Name = this.getResources().getString(R.string.item8);
		String item9Name = this.getResources().getString(R.string.item9);
		String item10Name = this.getResources().getString(R.string.item10);
		String item11Name = this.getResources().getString(R.string.item11);
		String item12Name = this.getResources().getString(R.string.item12);
		String item13Name = this.getResources().getString(R.string.item13);

		if (item1Name.equals(tvText)) {
			String[] item1Detail = this.getResources().getStringArray(
					R.array.item1Detail);
			rightList.addAll(Arrays.asList(item1Detail));

		} else if (item2Name.equals(tvText)) {
			String[] item2Detail = this.getResources().getStringArray(
					R.array.item2Detail);
			rightList.addAll(Arrays.asList(item2Detail));
		} else if (item3Name.equals(tvText)) {
			String[] item3Detail = this.getResources().getStringArray(
					R.array.item3Detail);
			rightList.addAll(Arrays.asList(item3Detail));
		} else if (item4Name.equals(tvText)) {
			String[] item4Detail = this.getResources().getStringArray(
					R.array.item4Detail);
			rightList.addAll(Arrays.asList(item4Detail));
		} else if (item5Name.equals(tvText)) {
			String[] item5Detail = this.getResources().getStringArray(
					R.array.item5Detail);
			rightList.addAll(Arrays.asList(item5Detail));
		} else if (item6Name.equals(tvText)) {
			String[] item6Detail = this.getResources().getStringArray(
					R.array.item6Detail);
			rightList.addAll(Arrays.asList(item6Detail));
		} else if (item7Name.equals(tvText)) {
			String[] item7Detail = this.getResources().getStringArray(
					R.array.item7Detail);
			rightList.addAll(Arrays.asList(item7Detail));
		} else if (item8Name.equals(tvText)) {
			String[] item8Detail = this.getResources().getStringArray(
					R.array.item8Detail);
			rightList.addAll(Arrays.asList(item8Detail));
		} else if (item9Name.equals(tvText)) {
			String[] item9Detail = this.getResources().getStringArray(
					R.array.item9Detail);
			rightList.addAll(Arrays.asList(item9Detail));
		} else if (item10Name.equals(tvText)) {
			String[] item10Detail = this.getResources().getStringArray(
					R.array.item10Detail);
			rightList.addAll(Arrays.asList(item10Detail));
		} else if (item11Name.equals(tvText)) {
			String[] item11Detail = this.getResources().getStringArray(
					R.array.item11Detail);
			rightList.addAll(Arrays.asList(item11Detail));
		} else if (item12Name.equals(tvText)) {
			String[] item12Detail = this.getResources().getStringArray(
					R.array.item12Detail);
			rightList.addAll(Arrays.asList(item12Detail));
		} else if (item13Name.equals(tvText)) {
			String[] item13Detail = this.getResources().getStringArray(
					R.array.item13Detail);
			rightList.addAll(Arrays.asList(item13Detail));
		}
		Log.d(TAG, "rightList: " + rightList);
		adapterRight = new ListViewActivityAdapter(this, rightList);
		rightListView.setAdapter(adapterRight);
		// 通知list内容已经改变
		adapterRight.notifyDataSetChanged();
	}

}
