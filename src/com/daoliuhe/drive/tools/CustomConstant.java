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
		 * ����ֱ����ʻ
		 */
		directLine(1, R.raw.direct_line),
		/**
		 * ����ֱ����ʻ
		 */
		endDirectLine(2, R.raw.end_direct_line),
		/**
		 * ǰ��·��ֱ�� 
		 */
		aheadDirectLine(3, R.raw.ahead_direct_line),
		/**
		 * ǰ��·����ת
		 */
		turnLeft(4, R.raw.turn_left),
		/**
		 * ǰ��·����ת
		 */
		turnRight(5, R.raw.turn_right),
		/**
		 * ͨ��ѧУ����
		 */
		passSchool(6, R.raw.pass_school),
		/**
		 * ͨ�����к��
		 */
		passSidewalk(7, R.raw.pass_sidewalk),
		/**
		 * ͨ����������վ
		 */
		passBusStation(8, R.raw.pass_bus_station),
		/**
		 * ǰ�����к��
		 */
		sidewalk(9, R.raw.sidewalk),
		/**
		 * ��������
		 */
		changeLanes(10, R.raw.change_lanes),
		/**
		 * �볬Խǰ������
		 */
		overtake(11, R.raw.overtake),
		/**
		 * ��������ᳵ�������ᳵ
		 */
		passing(12,R.raw.psssing),
		/**
		 * �Ӽ���λ����
		 */
		shiftGears(13,R.raw.shift_gears),
		/**
		 * �뿿��ͣ��
		 */
		pullOver(14, R.raw.pull_over),
		/**
		 * ǰ����ѡ����ʵص��ͷ
		 */
		turn(15, R.raw.turn),
		/**
		 * ���𲽣�������ɿ���
		 */
		start(16, R.raw.start);
		
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
