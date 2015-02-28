package com.daoliuhe.drive.tools;

import com.daoliuhe.drive.R;

/**
 * �Զ��峣��
 * 
 * @author CYY
 * 
 */
public class CustomConstant {

	/**
	 * ��������������
	 * @author 21829
	 * 
	 */
	public enum VoiceType {
		/**
		 * ֱ����ʻ 
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
		 * @param id ���ݿ�洢id
		 * @param code Ŀ¼�ļ���·��R.raw.*
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
	 * �������� key
	 */
	public static final String DISTANCE_KEY = "edtDistanceKey";
	/**
	 * �������� value Ĭ��ֵ
	 */
	public static final String DISTANCE_VALUE = "10";

	/**
	 * �Ƕ���� key
	 */
	public static final String ANGLEERROR_KEY = "edtAngleErrorKey";
	/**
	 * �Ƕ���� value Ĭ��ֵ
	 */
	public static final String ANGLEERROR_VALUE = "50";
	
	/**
	 * ˢ��Ƶ�� key
	 */
	public static final String REFRESH_KEY = "edtRefreshKey";
	/**
	 * ˢ��Ƶ�� value Ĭ��ֵ
	 */
	public static final String REFRESH_VALUE = "200";

	/**
	 * ��ť��id
	 */
	public static final String BUTTONID = "buttonId";

	/**
	 * ����
	 */
	public static final String LONGITUDE = "longitude";

	/**
	 * γ��
	 */
	public static final String LATITUDE = "latitude";

	/**
	 * ��λ
	 */
	public static final String BEARING = "bearing";

	/**
	 * ��ǰ�ľ���
	 */
	public static final String CUR_LONGITUDE = "curLongitude";

	/**
	 * ��ǰ��γ��
	 */
	public static final String CUR_LATITUDE = "curLatitude";

	/**
	 * ��ǰ�ķ�λ
	 */
	public static final String CUR_BEARING = "curBearing";

	/**
	 * ��ǰѡ�е���Ŀ
	 */
	public static final String SELECT_ITEM = "selectItem";

	/**
	 * ��Ƶ���ŵ�id
	 */
	public static final String VIDEO_ID = "videoId";
}
