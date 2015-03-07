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
		 * 保持直线行驶
		 */
		directLine(1, 3, R.raw.direct_line),
		/**
		 * 结束直线行驶
		 */
		endDirectLine(2, 3, R.raw.end_direct_line),
		/**
		 * 前方路口直行 
		 */
		aheadDirectLine(3, 6, R.raw.ahead_direct_line),
		/**
		 * 前方路口左转
		 */
		turnLeft(4, 6, R.raw.turn_left),
		/**
		 * 前方路口右转
		 */
		turnRight(5, 6, R.raw.turn_right),
		/**
		 * 通过学校区域
		 */
		passSchool(6, 8, R.raw.pass_school),
		/**
		 * 通过人行横道
		 */
		passSidewalk(7, 7, R.raw.pass_sidewalk),
		/**
		 * 通过公共汽车站
		 */
		passBusStation(8, 9, R.raw.pass_bus_station),
		/**
		 * 前方人行横道
		 */
		sidewalk(9, 7, R.raw.sidewalk),
		/**
		 * 请变更车道
		 */
		changeLanes(10, 5, R.raw.change_lanes),
		/**
		 * 请超越前方车辆
		 */
		overtake(11, 11, R.raw.overtake),
		/**
		 * 与机动车会车，结束会车
		 */
		passing(12, 10, R.raw.psssing),
		/**
		 * 加减档位操作
		 */
		shiftGears(13, 4, R.raw.shift_gears),
		/**
		 * 请靠边停车
		 */
		pullOver(14, 13, R.raw.pull_over),
		/**
		 * 前方请选择合适地点掉头
		 */
		turn(15, 12, R.raw.turn),
		/**
		 * 请起步，继续完成考试
		 */
		start(16, 2, R.raw.start),
		
		/**
		 * 结束考试
		 */
		end(17, 2, R.raw.end);
		
		/**
		 * 目录文件的路径R.raw.*
		 */
		private int code;
		
		/**
		 * 数据库存储的语音类型id
		 */
		private int id;
		
		/**
		 * 评分项的id
		 */
		private int scoreId;

		/**
		 * @param id 数据库存储id
		 * @param scoreId 评分项的id
		 * @param code 目录文件的路径R.raw.*
		 */
		private VoiceType(int id, int scoreId, int code) {
			this.id = id;
			this.scoreId = scoreId;
			this.code = code;
		}

		/**
		 * @return  目录文件的路径,例如：R.raw.shift_gears
		 */
		public int getCode() {
			return this.code;
		}
		
		/**
		 * @return 数据库存储的语音类型id
		 */
		public int getId(){
			return id;
		}
		
		/**
		 * @return 评分项的id
		 */
		public int getScoreId(){
			return scoreId;
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
	 * 是否弹出评分标准 key
	 */
	public static final String SHOW_KEY = "tbShowKey";
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
	
	/**
	 * 位置
	 */
	public static final String POSITION = "position";
	
	/**
	 * 声音类型
	 */
	public static final String VOICETYPE = "voiceType";
	
	/**
	 * 线路1 id
	 */
	public static final int S3LINE1ID = 1;
	
	/**
	 * 线路2 id
	 */
	public static final int S3LINE2ID = 2;
	
	/**
	 * 线路3 id
	 */
	public static final int S3LINE3ID = 3;
	
	/**
	 * 线路4 id
	 */
	public static final int S3LINE4ID = 4;
	
	/**
	 * 线路5 id
	 */
	public static final int S3LINE5ID = 5;
	
	/**
	 * 线路6 id
	 */
	public static final int S3LINE6ID = 6;
}
