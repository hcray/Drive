package com.daoliuhe.drive.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoliuhe.drive.R;
import com.daoliuhe.drive.bean.LineBean;

public class ListViewActivityAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	
	private List<LineBean> lineList;
	
	static class ViewHolder {
		public TextView tvLineName;
	}
	
	public ListViewActivityAdapter(Context context, List<LineBean> lineList){
		this.mInflater = LayoutInflater.from(context);
		this.lineList = lineList;
	}
	
	@Override
	public int getCount() {
		return lineList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.atom_item, null);
			holder.tvLineName = (TextView) convertView.findViewById(R.id.tvLineName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvLineName.setText(lineList.get(position).getLineName());
		return convertView;
	}

}
