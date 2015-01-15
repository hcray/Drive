package com.daoliuhe.drive.tools;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

	public static final String KEY_ROWID 		 = "id";
	public static final String KEY_IME 			 = "ime";//本机IME
	
	public static final String KEY_LINE_NAME = "lineName";//路线名称
	
	public static final String KEY_TURNRIGHT_LAT = "turnRightLat"; //纬度
	public static final String KEY_TURNRIGHT_LNG = "turnRightLng"; //经度
	
	public static final String KEY_SIDEWALK_LAT = "sidewalkLat";
	public static final String KEY_SIDEWALK_LNG = "sidewalkLng";
	
	public static final String KEY_PASSSIDEWALK_LAT = "passSidewalkLat";
	public static final String KEY_PASSSIDEWALK_LNG = "passSidewalkLng";
	
	public static final String KEY_TURNLEFT_LAT = "turnLeftLat";
	public static final String KEY_TURNLEFT_LNG = "turnLeftLng";
	
	public static final String KEY_AHEADDIRECTLINE_LAT = "aheadDirectLineLat";
	public static final String KEY_AHEADDIRECTLINE_LNG = "aheadDirectLineLng";
	
	public static final String KEY_PASSBUSSTATION_LAT = "passBusStationLat";
	public static final String KEY_PASSBUSSTATION_LNG = "passBusStationLng";
	
	public static final String KEY_DIRECTLINE_LAT = "directLineLat";
	public static final String KEY_DIRECTLINE_LNG = "directLineLng";
	
	public static final String KEY_PASSSCHOOL_LAT = "passSchoolLat";
	public static final String KEY_PASSSCHOOL_LNG = "passSchoolLng";
	
	public static final String KEY_CHANGELANES_LAT = "changeLanesLat";
	public static final String KEY_CHANGELANES_LNG = "changeLanesLng";
	
	public static final String KEY_SLOWDOWN_LAT = "slowdownLat";
	public static final String KEY_SLOWDOWN_LNG = "slowdownLng";
	
	public static final String KEY_SPEEDLIMIT_LAT = "speedLimitLat";
	public static final String KEY_SPEEDLIMIT_LNG = "speedLimitLng";
	
	public static final String KEY_PASSSCHOOLSTATION_LAT = "passSchoolStationLat";
	public static final String KEY_PASSSCHOOLSTATION_LNG = "passSchoolStationLng";
	
	public static final String KEY_TURN_LAT = "turnLat";
	public static final String KEY_TURN_LNG = "turnLng";
	
	public static final String KEY_PULLOVER_LAT = "pullOverLat";
	public static final String KEY_PULLOVER_LNG = "pullOverLng";
	
	public static final String KEY_BACKCAR_LAT = "backCarLat";
	public static final String KEY_BACKCAR_LNG = "backCarLng";
	
	private static final String TAG = "DbAdapter";

	private static final String DATABASE_CREATE = "create table t_lines ("
			+"id integer primary key autoincrement, "
			+"lineName text not null,"
			+" turnRightLat REAL,"
			+" turnRightLng REAL,"
			+" sidewalkLat REAL, " 
			+" sidewalkLng REAL,"
			+" passSidewalkLat REAL," 
			+" passSidewalkLng REAL,"
			+" turnLeftLat REAL, " 
			+" turnLeftLng REAL,"
			+" aheadDirectLineLat REAL, " 
			+" aheadDirectLineLng REAL,"
			+" passBusStationLat REAL, " 
			+" passBusStationLng REAL,"
			+" directLineLat REAL, " 
			+" directLineLng REAL,"
			+" passSchoolLat REAL, " 
			+" passSchoolLng REAL,"
			+" changeLanesLat REAL, " 
			+" changeLanesLng REAL,"
			+" slowdownLat REAL, " 
			+" slowdownLng REAL,"
			+" speedLimitLat REAL, " 
			+" speedLimitLng REAL,"
			+" passSchoolStationLat REAL, " 
			+" passSchoolStationLng REAL,"
			+" turnLat REAL, " 
			+" turnLng REAL,"
			+" pullOverLat REAL, " 
			+" pullOverLng REAL,"
			+" backCarLat REAL, " 
			+" backCarLng REAL);";

	private static final String DATABASE_NAME = "database";
	private static final String DATABASE_TABLE = "t_lines";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS t_lines");
			onCreate(db);
		}
	}

	/**
	 * 构造方法
	 * @param ctx
	 */
	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * 打开数据库
	 * @return
	 * @throws SQLException
	 */
	public DbAdapter open() throws SQLException {
		Log.d(TAG, "open()");
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		Log.d(TAG,"close()");
		mDbHelper.close();
	}

}
