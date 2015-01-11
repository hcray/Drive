package com.daoliuhe.drive.tools;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

	public static final String KEY_ROWID 		 = "_id";
	public static final String KEY_IME 			 = "ime"; 		   //本机IME
	public static final String KEY_TURNRIGHT_LA = "turnRightLa"; //纬度
	public static final String KEY_TURNRIGHT_LG = "turnRightLg"; //经度
	
	public static final String KEY_SIDEWALK_LA = "sidewalkLa";
	public static final String KEY_SIDEWALK_LG = "sidewalkLg";
	
	public static final String KEY_PASSSIDEWALK_LA = "passSidewalkLa";
	public static final String KEY_PASSSIDEWALK_LG = "passSidewalkLg";
	
	public static final String KEY_TURNLEFT_LA = "turnLeftLa";
	public static final String KEY_TURNLEFT_LG = "turnLeftLg";
	
	public static final String KEY_AHEADDIRECTLINE_LA = "aheadDirectLineLa";
	public static final String KEY_AHEADDIRECTLINE_LG = "aheadDirectLineLg";
	
	public static final String KEY_PASSBUSSTATION_LA = "passBusStationLa";
	public static final String KEY_PASSBUSSTATION_LG = "passBusStationLg";
	
	public static final String KEY_DIRECTLINE_LA = "directLineLa";
	public static final String KEY_DIRECTLINE_LG = "directLineLg";
	
	public static final String KEY_PASSSCHOOL_LA = "passSchoolLa";
	public static final String KEY_PASSSCHOOL_LG = "passSchoolLg";
	
	public static final String KEY_CHANGELANES_LA = "changeLanesLa";
	public static final String KEY_CHANGELANES_LG = "changeLanesLg";
	
	public static final String KEY_SLOWDOWN_LA = "slowdownLa";
	public static final String KEY_SLOWDOWN_LG = "slowdownLg";
	
	public static final String KEY_SPEEDLIMIT_LA = "speedLimitLa";
	public static final String KEY_SPEEDLIMIT_LG = "speedLimitLg";
	
	public static final String KEY_PASSSCHOOLSTATION_LA = "passSchoolStationLa";
	public static final String KEY_PASSSCHOOLSTATION_LG = "passSchoolStationLg";
	
	public static final String KEY_TURN_LA = "turnLa";
	public static final String KEY_TURN_LG = "turnLg";
	
	public static final String KEY_PULLOVER_LA = "pullOverLa";
	public static final String KEY_PULLOVER_LG = "pullOverLg";
	
	public static final String KEY_BACKCAR_LA = "backCarLa";
	public static final String KEY_BACKCAR_LG = "backCarLg";
	
	private static final String TAG = "DbAdapter";

	private static final String DATABASE_CREATE = "create table t_lines ("
			+"_id integer primary key autoincrement, "
			+ "ime text not null,"
			+" turnRightLg REAL,"
			+" turnRightLa REAL,"
			+" sidewalkLa REAL, " 
			+" sidewalkLg REAL,"
			+" passSidewalkLa REAL, " 
			+" passSidewalkLg REAL,"
			+" turnLeftLa REAL, " 
			+" turnLeftLg REAL,"
			+" aheadDirectLineLa REAL, " 
			+" aheadDirectLineLg REAL,"
			+" passBusStationLa REAL, " 
			+" passBusStationLg REAL,"
			+" directLineLa REAL, " 
			+" directLineLg REAL,"
			+" passSchoolLa REAL, " 
			+" passSchoolLg REAL,"
			+" changeLanesLa REAL, " 
			+" changeLanesLg REAL,"
			+" slowdownLa REAL, " 
			+" slowdownLg REAL,"
			+" speedLimitLa REAL, " 
			+" speedLimitLg REAL,"
			+" passSchoolStationLa REAL, " 
			+" passSchoolStationLg REAL,"
			+" turnLa REAL, " 
			+" turnLg REAL,"
			+" pullOverLa REAL, " 
			+" pullOverLg REAL,"
			+" backCarLa REAL, " 
			+" backCarLg REAL);";

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
		Log.d(TAG, "GPSDbAdapter open()");
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
