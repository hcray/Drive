package com.daoliuhe.drive.tools;

import com.daoliuhe.drive.R;

/**
 * 自定义常量
 * 
 * @author CYY
 * 
 */
public class CustomConstant {

	/**
	 * 播报的语音类型
	 * @author 21829
	 * 
	 */
	public enum VoiceType {
		/**
		 * 直线行驶 
		 */
		aheadDirectLine(1, R.raw.ahead_direct_line),
		
		/**
		 * 
		 */
		changeLanes(2, R.raw.change_lanes),
		/**
		 * 
		 */
		directLine(3, R.raw.direct_line),
		/**
		 * 
		 */
		endDirectLine(4, R.raw.end_direct_line),
		/**
		 * 
		 */
		overtake(5, R.raw.overtake),
		/**
		 * 
		 */
		passBusStation(6, R.raw.pass_bus_station),
		/**
		 * 
		 */
		passSchool(7, R.raw.pass_school),
		/**
		 * 
		 */
		passSidewalk(8, R.raw.pass_sidewalk),
		/**
		 * 
		 */
		pullOver(9, R.raw.pull_over),
		/**
		 * 
		 */
		sidewalk(10, R.raw.sidewalk),
		/**
		 * 
		 */
		start(11, R.raw.start),
		/**
		 * 
		 */
		turn(12, R.raw.turn),
		/**
		 * 
		 */
		turnLeft(13, R.raw.turn_left),
		/**
		 * 
		 */
		turnRight(14, R.raw.turn_right);

		private int code;
		
		private int id;

		/**
		 * @param id 数据库存储id
		 * @param code 目录文件的路径R.raw.*
		 */
		private VoiceType(int id, int code) {
			this.id = id;
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}
		
		public int getId(){
			return id;
		}
	}

	/**
	 * 播报距离 key
	 */
	public static final String DISTANCE_KEY = "edtDistanceKey";
	/**
	 * 播报距离 value 默认值
	 */
	public static final String DISTANCE_VALUE = "10";

	/**
	 * 角度误差 key
	 */
	public static final String ANGLEERROR_KEY = "edtAngleErrorKey";
	/**
	 * 角度误差 value 默认值
	 */
	public static final String ANGLEERROR_VALUE = "50";
	
	/**
	 * 刷新频率 key
	 */
	public static final String REFRESH_KEY = "edtRefreshKey";
	/**
	 * 刷新频率 value 默认值
	 */
	public static final String REFRESH_VALUE = "200";

	/**
	 * 按钮的id
	 */
	public static final String BUTTONID = "buttonId";

	/**
	 * 经度
	 */
	public static final String LONGITUDE = "longitude";

	/**
	 * 纬度
	 */
	public static final String LATITUDE = "latitude";

	/**
	 * 方位
	 */
	public static final String BEARING = "bearing";

	/**
	 * 当前的经度
	 */
	public static final String CUR_LONGITUDE = "curLongitude";

	/**
	 * 当前的纬度
	 */
	public static final String CUR_LATITUDE = "curLatitude";

	/**
	 * 当前的方位
	 */
	public static final String CUR_BEARING = "curBearing";

	/**
	 * 当前选中的项目
	 */
	public static final String SELECT_ITEM = "selectItem";

	/**
	 * 视频播放的id
	 */
	public static final String VIDEO_ID = "videoId";
}
