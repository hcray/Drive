package com.daoliuhe.drive.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.daoliuhe.drive.bean.LineBean;

public class DbAdapter {

	public static final String KEY_ID 		 = "id";
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
			+" id integer primary key autoincrement, "
			+" lineName text not null,"
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
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
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
	
	public int insertLine(LineBean lineBean){
		Log.d(TAG,"insertLine()");
		ContentValues initialValues = new ContentValues();
		
		String lineName = lineBean.getLineName();
		if(null != lineName && !lineName.isEmpty()){
			initialValues.put(KEY_LINE_NAME, lineName);
		}
		
		Double turnRightLat = lineBean.getTurnRightLat();
		if(null != turnRightLat){
			initialValues.put(KEY_TURNRIGHT_LAT, turnRightLat);
		}
		
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLng){
			initialValues.put(KEY_TURNRIGHT_LNG, turnRightLng);
		}
		
		Double sidewalkLat = lineBean.getSidewalkLat();
		if(null != sidewalkLat){
			initialValues.put(KEY_SIDEWALK_LAT, sidewalkLat);
		}
		
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLng){
			initialValues.put(KEY_SIDEWALK_LNG, sidewalkLng);
		}
		
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		if(null != passSidewalkLat){
			initialValues.put(KEY_PASSSIDEWALK_LAT, passSidewalkLat);
		}
		
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLng){
			initialValues.put(KEY_PASSSIDEWALK_LNG, passSidewalkLng);
		}
		
		Double turnLeftLat = lineBean.getTurnLat();
		if(null != turnLeftLat){
			initialValues.put(KEY_TURNLEFT_LAT, turnLeftLat);
		}
		
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLng){
			initialValues.put(KEY_TURNLEFT_LNG, turnLeftLng);
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		if(null != aheadDirectLineLat){
			initialValues.put(KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
		}

		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLng){
			initialValues.put(KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
		}
		
		Double passBusStationLat = lineBean.getPassBusStationLat();
		if(null != passBusStationLat){
			initialValues.put(KEY_PASSBUSSTATION_LAT, passBusStationLat);
		}

		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLng){
			initialValues.put(KEY_PASSBUSSTATION_LNG, passBusStationLng);
		}
		
		Double directLineLat = lineBean.getDirectLineLat();
		if(null != directLineLat){
			initialValues.put(KEY_DIRECTLINE_LAT, directLineLat);
		}
		
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLng){
			initialValues.put(KEY_DIRECTLINE_LNG, directLineLng);
		}
		
		Double passSchoolLat = lineBean.getPassSchoolLat();
		if(null != passSchoolLat){
			initialValues.put(KEY_PASSSCHOOL_LAT, passSchoolLat);
		}
		
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLng){
			initialValues.put(KEY_PASSSCHOOL_LNG, passSchoolLng);
		}
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		if(null != changeLanesLat){
			initialValues.put(KEY_CHANGELANES_LAT, changeLanesLat);
		}

		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLng){
			initialValues.put(KEY_CHANGELANES_LNG, changeLanesLng);
		}

		Double slowdownLat = lineBean.getSlowdownLat();
		if(null != slowdownLat){
			initialValues.put(KEY_SLOWDOWN_LAT, slowdownLat);
		}
		
		Double slowdownLng = lineBean.getSlowdownLng();
		if(null != slowdownLng){
			initialValues.put(KEY_SLOWDOWN_LNG, slowdownLng);
		}
		
		Double speedLimitLat = lineBean.getSpeedLimitLat();
		if(null != speedLimitLat){
			initialValues.put(KEY_SPEEDLIMIT_LAT, speedLimitLat);
		}

		Double speedLimitLng = lineBean.getSpeedLimitLng();
		if(null != speedLimitLng){
			initialValues.put(KEY_SPEEDLIMIT_LNG, speedLimitLng);
		}

		Double passSchoolStationLat = lineBean.getPassSchoolStationLat();
		if(null != passSchoolStationLat){
			initialValues.put(KEY_PASSSCHOOLSTATION_LAT, passSchoolStationLat);
		}

		Double passSchoolStationLng = lineBean.getPassSchoolStationLng();
		if(null != passSchoolStationLng){
			initialValues.put(KEY_PASSSCHOOLSTATION_LNG, passSchoolStationLng);
		}
		
		Double turnLat = lineBean.getTurnLat();
		if(null != turnLat){
			initialValues.put(KEY_TURN_LAT, turnLat);
		}

		Double turnLng = lineBean.getTurnLng();
		if(null != turnLng){
			initialValues.put(KEY_TURN_LNG, turnLng);
		}

		Double pullOverLat = lineBean.getPullOverLat();
		if(null != pullOverLat){
			initialValues.put(KEY_PULLOVER_LAT, pullOverLat);
		}
		
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLng){
			initialValues.put(KEY_PULLOVER_LNG, pullOverLng);
		}
		
		Double backCarLat = lineBean.getBackCarLat();
		if(null != backCarLat){
			initialValues.put(KEY_BACKCAR_LAT, backCarLat);
		}
		
		Double backCarLng = lineBean.getBackCarLng();
		if(null != backCarLng){
			initialValues.put(KEY_BACKCAR_LNG, backCarLng);
		}

		long ret = mDb.insert(DATABASE_TABLE, null, initialValues);
		Cursor cursor = mDb.rawQuery("select last_insert_rowid() id", null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndexOrThrow("id");
		int id = cursor.getInt(columnIndex);

		if (ret > 0) {
			return id;
		} else {
			return 0;
		}
	}
	
	public boolean updateLine(LineBean lineBean){
		Log.d(TAG,"updateLine() lineBean: " + lineBean.toString());
		
		ContentValues initialValues = new ContentValues();
		
		String lineName = lineBean.getLineName();
		if(null != lineName && !lineName.isEmpty()){
			initialValues.put(KEY_LINE_NAME, lineName);
		}
		
		Double turnRightLat = lineBean.getTurnRightLat();
		if(null != turnRightLat){
			initialValues.put(KEY_TURNRIGHT_LAT, turnRightLat);
		}
		
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLng){
			initialValues.put(KEY_TURNRIGHT_LNG, turnRightLng);
		}
		
		Double sidewalkLat = lineBean.getSidewalkLat();
		if(null != sidewalkLat){
			initialValues.put(KEY_SIDEWALK_LAT, sidewalkLat);
		}
		
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLng){
			initialValues.put(KEY_SIDEWALK_LNG, sidewalkLng);
		}
		
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		if(null != passSidewalkLat){
			initialValues.put(KEY_PASSSIDEWALK_LAT, passSidewalkLat);
		}
		
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLng){
			initialValues.put(KEY_PASSSIDEWALK_LNG, passSidewalkLng);
		}
		
		Double turnLeftLat = lineBean.getTurnLat();
		if(null != turnLeftLat){
			initialValues.put(KEY_TURNLEFT_LAT, turnLeftLat);
		}
		
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLng){
			initialValues.put(KEY_TURNLEFT_LNG, turnLeftLng);
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		if(null != aheadDirectLineLat){
			initialValues.put(KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
		}

		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLng){
			initialValues.put(KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
		}
		
		Double passBusStationLat = lineBean.getPassBusStationLat();
		if(null != passBusStationLat){
			initialValues.put(KEY_PASSBUSSTATION_LAT, passBusStationLat);
		}

		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLng){
			initialValues.put(KEY_PASSBUSSTATION_LNG, passBusStationLng);
		}
		
		Double directLineLat = lineBean.getDirectLineLat();
		if(null != directLineLat){
			initialValues.put(KEY_DIRECTLINE_LAT, directLineLat);
		}
		
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLng){
			initialValues.put(KEY_DIRECTLINE_LNG, directLineLng);
		}
		
		Double passSchoolLat = lineBean.getPassSchoolLat();
		if(null != passSchoolLat){
			initialValues.put(KEY_PASSSCHOOL_LAT, passSchoolLat);
		}
		
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLng){
			initialValues.put(KEY_PASSSCHOOL_LNG, passSchoolLng);
		}
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		if(null != changeLanesLat){
			initialValues.put(KEY_CHANGELANES_LAT, changeLanesLat);
		}

		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLng){
			initialValues.put(KEY_CHANGELANES_LNG, changeLanesLng);
		}

		Double slowdownLat = lineBean.getSlowdownLat();
		if(null != slowdownLat){
			initialValues.put(KEY_SLOWDOWN_LAT, slowdownLat);
		}
		
		Double slowdownLng = lineBean.getSlowdownLng();
		if(null != slowdownLng){
			initialValues.put(KEY_SLOWDOWN_LNG, slowdownLng);
		}
		
		Double speedLimitLat = lineBean.getSpeedLimitLat();
		if(null != speedLimitLat){
			initialValues.put(KEY_SPEEDLIMIT_LAT, speedLimitLat);
		}

		Double speedLimitLng = lineBean.getSpeedLimitLng();
		if(null != speedLimitLng){
			initialValues.put(KEY_SPEEDLIMIT_LNG, speedLimitLng);
		}

		Double passSchoolStationLat = lineBean.getPassSchoolStationLat();
		if(null != passSchoolStationLat){
			initialValues.put(KEY_PASSSCHOOLSTATION_LAT, passSchoolStationLat);
		}

		Double passSchoolStationLng = lineBean.getPassSchoolStationLng();
		if(null != passSchoolStationLng){
			initialValues.put(KEY_PASSSCHOOLSTATION_LNG, passSchoolStationLng);
		}
		
		Double turnLat = lineBean.getTurnLat();
		if(null != turnLat){
			initialValues.put(KEY_TURN_LAT, turnLat);
		}

		Double turnLng = lineBean.getTurnLng();
		if(null != turnLng){
			initialValues.put(KEY_TURN_LNG, turnLng);
		}

		Double pullOverLat = lineBean.getPullOverLat();
		if(null != pullOverLat){
			initialValues.put(KEY_PULLOVER_LAT, pullOverLat);
		}
		
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLng){
			initialValues.put(KEY_PULLOVER_LNG, pullOverLng);
		}
		
		Double backCarLat = lineBean.getBackCarLat();
		if(null != backCarLat){
			initialValues.put(KEY_BACKCAR_LAT, backCarLat);
		}
		
		Double backCarLng = lineBean.getBackCarLng();
		if(null != backCarLng){
			initialValues.put(KEY_BACKCAR_LNG, backCarLng);
		}
		
		//id
		Integer id = lineBean.getId();
		
		Log.d(TAG,"updateLine() initialValues: " + initialValues.toString());
		
		return  mDb.update(DATABASE_TABLE, initialValues, KEY_ID + "=" + id, null) > 0;
	}
	
	/**
	 * 查询所有的线路
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<LineBean> selectAllLine() throws SQLException {
		Log.d(TAG,"selectAllLine()");
		//线路集合
		List<LineBean> list = new ArrayList<LineBean>();
		
		Cursor mCursor =
				mDb.query(DATABASE_TABLE, new String[] { KEY_ID,
				KEY_LINE_NAME, KEY_TURNRIGHT_LAT, KEY_TURNRIGHT_LNG,
				KEY_SIDEWALK_LAT, KEY_SIDEWALK_LNG, KEY_PASSSIDEWALK_LAT,
				KEY_PASSSIDEWALK_LNG, KEY_TURNLEFT_LAT, KEY_TURNLEFT_LNG,
				KEY_AHEADDIRECTLINE_LAT, KEY_AHEADDIRECTLINE_LNG,
				KEY_PASSBUSSTATION_LAT, KEY_PASSBUSSTATION_LNG,
				KEY_DIRECTLINE_LAT, KEY_DIRECTLINE_LNG, KEY_PASSSCHOOL_LAT,
				KEY_PASSSCHOOL_LNG, KEY_CHANGELANES_LAT, KEY_CHANGELANES_LNG,
				KEY_SLOWDOWN_LAT, KEY_SLOWDOWN_LNG, KEY_SPEEDLIMIT_LAT,
				KEY_SPEEDLIMIT_LNG, KEY_PASSSCHOOLSTATION_LAT,
				KEY_PASSSCHOOLSTATION_LNG, KEY_TURN_LAT, KEY_TURN_LNG,
				KEY_PULLOVER_LAT, KEY_PULLOVER_LNG, KEY_BACKCAR_LAT,
				KEY_BACKCAR_LNG }, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			
			LineBean lineBean = new LineBean();
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);  
			
			int turnRightLatIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LAT);
			int turnRightLngIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LNG);
			
			int sidewalkLatIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LAT);
			int sidewalkLngIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LNG);
			
			int passSidewalkLatIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LAT);
			int passSidewalkLngIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LNG);
			
			int turnLeftLatIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LAT); 
			int turnLeftLngIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LNG);
			
			int aheadDirectLineLatIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LAT);
			int aheadDirectLineLngIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LNG);
			
			int passBusStationLatIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LAT);
			int passBusStationLngIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LNG);
			
			int directLineLatIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LAT);
			int directLineLngIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LNG);
			
			int passSchoolLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LAT);
			int passSchoolLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LNG);
			
			int changeLanesLatIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LAT);
			int changeLanesLngIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LNG);
			
			int slowdownLatIndex = mCursor.getColumnIndex(KEY_SLOWDOWN_LAT);
			int slowdownLngIndex = mCursor.getColumnIndex(KEY_SLOWDOWN_LNG);
			
			int speedLimitLatIndex = mCursor.getColumnIndex(KEY_SPEEDLIMIT_LAT);
			int speedLimitLngIndex = mCursor.getColumnIndex(KEY_SPEEDLIMIT_LNG);
			
			int passSchoolStationLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOLSTATION_LAT);
			int passSchoolStationLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOLSTATION_LNG);
			
			int turnLatIndex = mCursor.getColumnIndex(KEY_TURN_LAT);
			int turnLngIndex = mCursor.getColumnIndex(KEY_TURN_LNG);
			
			int pullOverLatIndex = mCursor.getColumnIndex(KEY_PULLOVER_LAT);
			int pullOverLngIndex = mCursor.getColumnIndex(KEY_PULLOVER_LNG);
			
			int backCarLatIndex = mCursor.getColumnIndex(KEY_BACKCAR_LAT);
			int backCarLngIndex = mCursor.getColumnIndex(KEY_BACKCAR_LNG);
			
			Integer id = mCursor.getInt(idIndex);
			if(null != id){
				lineBean.setId(id);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
			
			Double turnRightLat = mCursor.getDouble(turnRightLatIndex);
			if(null != turnRightLat){
				lineBean.setTurnRightLat(turnRightLat);
			}
			
			Double turnRightLng = mCursor.getDouble(turnRightLngIndex);
			if(null != turnRightLng){
				lineBean.setTurnRightLng(turnRightLng);
			}
			
			Double sidewalkLat = mCursor.getDouble(sidewalkLatIndex);
			if(null != sidewalkLat){
				lineBean.setSidewalkLat(sidewalkLat);
			}
			
			Double sidewalkLng = mCursor.getDouble(sidewalkLngIndex);
			if(null != sidewalkLng){
				lineBean.setSidewalkLng(sidewalkLng);
			}
			
			Double passSidewalkLat = mCursor.getDouble(passSidewalkLatIndex);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			
			Double passSidewalkLng = mCursor.getDouble(passSidewalkLngIndex);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			
			Double turnLeftLat = mCursor.getDouble(turnLeftLatIndex);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			
			Double turnLeftLng = mCursor.getDouble(turnLeftLngIndex);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			
			Double aheadDirectLineLat = mCursor.getDouble(aheadDirectLineLatIndex);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}

			Double aheadDirectLineLng = mCursor.getDouble(aheadDirectLineLngIndex);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			
			Double passBusStationLat = mCursor.getDouble(passBusStationLatIndex);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}

			Double passBusStationLng = mCursor.getDouble(passBusStationLngIndex);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			
			Double directLineLat = mCursor.getDouble(directLineLatIndex);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			
			Double directLineLng = mCursor.getDouble(directLineLngIndex);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			
			Double passSchoolLat = mCursor.getDouble(passSchoolLatIndex);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			
			Double passSchoolLng = mCursor.getDouble(passSchoolLngIndex);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			
			Double changeLanesLat = mCursor.getDouble(changeLanesLatIndex);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}

			Double changeLanesLng = mCursor.getDouble(changeLanesLngIndex);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}

			Double slowdownLat = mCursor.getDouble(slowdownLatIndex);
			if(null != slowdownLat){
				lineBean.setSlowdownLat(slowdownLat);
			}
			
			Double slowdownLng = mCursor.getDouble(slowdownLngIndex);
			if(null != slowdownLng){
				lineBean.setSlowdownLng(slowdownLng);
			}
			
			Double speedLimitLat = mCursor.getDouble(speedLimitLatIndex);
			if(null != speedLimitLat){
				lineBean.setSpeedLimitLat(speedLimitLat);
			}

			Double speedLimitLng = mCursor.getDouble(speedLimitLngIndex);
			if(null != speedLimitLng){
				lineBean.setSpeedLimitLng(speedLimitLng);
			}

			Double passSchoolStationLat = mCursor.getDouble(passSchoolStationLatIndex);
			if(null != passSchoolStationLat){
				lineBean.setPassSchoolStationLat(passSchoolStationLat);
			}

			Double passSchoolStationLng = mCursor.getDouble(passSchoolStationLngIndex);
			if(null != passSchoolStationLng){
				lineBean.setPassSchoolStationLng(passSchoolStationLng);
			}
			
			Double turnLat = mCursor.getDouble(turnLatIndex);
			if(null != turnLat){
				lineBean.setTurnLat(turnLat);
			}

			Double turnLng = mCursor.getDouble(turnLngIndex);
			if(null != turnLng){
				lineBean.setTurnLng(turnLng);
			}

			Double pullOverLat = mCursor.getDouble(pullOverLatIndex);
			if(null != pullOverLat){
				lineBean.setPullOverLat(pullOverLat);
			}
			
			Double pullOverLng = mCursor.getDouble(pullOverLngIndex);
			if(null != pullOverLng){
				lineBean.setPullOverLng(pullOverLng);
			}
			
			Double backCarLat = mCursor.getDouble(backCarLatIndex);
			if(null != backCarLat){
				lineBean.setBackCarLat(backCarLat);
			}
			
			Double backCarLng = mCursor.getDouble(backCarLngIndex);
			if(null != backCarLng){
				lineBean.setBackCarLng(backCarLng);
			}
			
			list.add(lineBean);
			
		}
		mCursor.close();
		
		return list;
	}
	
	/**
	 * 根据id查询线路
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public LineBean selectLineBeanById(int id) throws SQLException {
		Log.d(TAG,"selectLineBeanById(int rowId)");
		Cursor mCursor =
				mDb.query(true, DATABASE_TABLE, new String[] { KEY_ID,
						KEY_LINE_NAME, KEY_TURNRIGHT_LAT, KEY_TURNRIGHT_LNG,
						KEY_SIDEWALK_LAT, KEY_SIDEWALK_LNG, KEY_PASSSIDEWALK_LAT,
						KEY_PASSSIDEWALK_LNG, KEY_TURNLEFT_LAT, KEY_TURNLEFT_LNG,
						KEY_AHEADDIRECTLINE_LAT, KEY_AHEADDIRECTLINE_LNG,
						KEY_PASSBUSSTATION_LAT, KEY_PASSBUSSTATION_LNG,
						KEY_DIRECTLINE_LAT, KEY_DIRECTLINE_LNG, KEY_PASSSCHOOL_LAT,
						KEY_PASSSCHOOL_LNG, KEY_CHANGELANES_LAT, KEY_CHANGELANES_LNG,
						KEY_SLOWDOWN_LAT, KEY_SLOWDOWN_LNG, KEY_SPEEDLIMIT_LAT,
						KEY_SPEEDLIMIT_LNG, KEY_PASSSCHOOLSTATION_LAT,
						KEY_PASSSCHOOLSTATION_LNG, KEY_TURN_LAT, KEY_TURN_LNG,
						KEY_PULLOVER_LAT, KEY_PULLOVER_LNG, KEY_BACKCAR_LAT,
						KEY_BACKCAR_LNG }, KEY_ID + "=" + id, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		LineBean lineBean = new LineBean();

		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);  
			
			int turnRightLatIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LAT);
			int turnRightLngIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LNG);
			
			int sidewalkLatIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LAT);
			int sidewalkLngIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LNG);
			
			int passSidewalkLatIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LAT);
			int passSidewalkLngIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LNG);
			
			int turnLeftLatIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LAT); 
			int turnLeftLngIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LNG);
			
			int aheadDirectLineLatIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LAT);
			int aheadDirectLineLngIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LNG);
			
			int passBusStationLatIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LAT);
			int passBusStationLngIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LNG);
			
			int directLineLatIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LAT);
			int directLineLngIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LNG);
			
			int passSchoolLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LAT);
			int passSchoolLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LNG);
			
			int changeLanesLatIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LAT);
			int changeLanesLngIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LNG);
			
			int slowdownLatIndex = mCursor.getColumnIndex(KEY_SLOWDOWN_LAT);
			int slowdownLngIndex = mCursor.getColumnIndex(KEY_SLOWDOWN_LNG);
			
			int speedLimitLatIndex = mCursor.getColumnIndex(KEY_SPEEDLIMIT_LAT);
			int speedLimitLngIndex = mCursor.getColumnIndex(KEY_SPEEDLIMIT_LNG);
			
			int passSchoolStationLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOLSTATION_LAT);
			int passSchoolStationLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOLSTATION_LNG);
			
			int turnLatIndex = mCursor.getColumnIndex(KEY_TURN_LAT);
			int turnLngIndex = mCursor.getColumnIndex(KEY_TURN_LNG);
			
			int pullOverLatIndex = mCursor.getColumnIndex(KEY_PULLOVER_LAT);
			int pullOverLngIndex = mCursor.getColumnIndex(KEY_PULLOVER_LNG);
			
			int backCarLatIndex = mCursor.getColumnIndex(KEY_BACKCAR_LAT);
			int backCarLngIndex = mCursor.getColumnIndex(KEY_BACKCAR_LNG);
			
			Integer retId = mCursor.getInt(idIndex);
			if(null != retId){
				lineBean.setId(id);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
			
			Double turnRightLat = mCursor.getDouble(turnRightLatIndex);
			if(null != turnRightLat){
				lineBean.setTurnRightLat(turnRightLat);
			}
			
			Double turnRightLng = mCursor.getDouble(turnRightLngIndex);
			if(null != turnRightLng){
				lineBean.setTurnRightLng(turnRightLng);
			}
			
			Double sidewalkLat = mCursor.getDouble(sidewalkLatIndex);
			if(null != sidewalkLat){
				lineBean.setSidewalkLat(sidewalkLat);
			}
			
			Double sidewalkLng = mCursor.getDouble(sidewalkLngIndex);
			if(null != sidewalkLng){
				lineBean.setSidewalkLng(sidewalkLng);
			}
			
			Double passSidewalkLat = mCursor.getDouble(passSidewalkLatIndex);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			
			Double passSidewalkLng = mCursor.getDouble(passSidewalkLngIndex);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			
			Double turnLeftLat = mCursor.getDouble(turnLeftLatIndex);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			
			Double turnLeftLng = mCursor.getDouble(turnLeftLngIndex);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			
			Double aheadDirectLineLat = mCursor.getDouble(aheadDirectLineLatIndex);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}
			
			Double aheadDirectLineLng = mCursor.getDouble(aheadDirectLineLngIndex);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			
			Double passBusStationLat = mCursor.getDouble(passBusStationLatIndex);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}
			
			Double passBusStationLng = mCursor.getDouble(passBusStationLngIndex);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			
			Double directLineLat = mCursor.getDouble(directLineLatIndex);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			
			Double directLineLng = mCursor.getDouble(directLineLngIndex);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			
			Double passSchoolLat = mCursor.getDouble(passSchoolLatIndex);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			
			Double passSchoolLng = mCursor.getDouble(passSchoolLngIndex);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			
			Double changeLanesLat = mCursor.getDouble(changeLanesLatIndex);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}
			
			Double changeLanesLng = mCursor.getDouble(changeLanesLngIndex);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}
			
			Double slowdownLat = mCursor.getDouble(slowdownLatIndex);
			if(null != slowdownLat){
				lineBean.setSlowdownLat(slowdownLat);
			}
			
			Double slowdownLng = mCursor.getDouble(slowdownLngIndex);
			if(null != slowdownLng){
				lineBean.setSlowdownLng(slowdownLng);
			}
			
			Double speedLimitLat = mCursor.getDouble(speedLimitLatIndex);
			if(null != speedLimitLat){
				lineBean.setSpeedLimitLat(speedLimitLat);
			}
			
			Double speedLimitLng = mCursor.getDouble(speedLimitLngIndex);
			if(null != speedLimitLng){
				lineBean.setSpeedLimitLng(speedLimitLng);
			}
			
			Double passSchoolStationLat = mCursor.getDouble(passSchoolStationLatIndex);
			if(null != passSchoolStationLat){
				lineBean.setPassSchoolStationLat(passSchoolStationLat);
			}
			
			Double passSchoolStationLng = mCursor.getDouble(passSchoolStationLngIndex);
			if(null != passSchoolStationLng){
				lineBean.setPassSchoolStationLng(passSchoolStationLng);
			}
			
			Double turnLat = mCursor.getDouble(turnLatIndex);
			if(null != turnLat){
				lineBean.setTurnLat(turnLat);
			}
			
			Double turnLng = mCursor.getDouble(turnLngIndex);
			if(null != turnLng){
				lineBean.setTurnLng(turnLng);
			}
			
			Double pullOverLat = mCursor.getDouble(pullOverLatIndex);
			if(null != pullOverLat){
				lineBean.setPullOverLat(pullOverLat);
			}
			
			Double pullOverLng = mCursor.getDouble(pullOverLngIndex);
			if(null != pullOverLng){
				lineBean.setPullOverLng(pullOverLng);
			}
			
			Double backCarLat = mCursor.getDouble(backCarLatIndex);
			if(null != backCarLat){
				lineBean.setBackCarLat(backCarLat);
			}
			
			Double backCarLng = mCursor.getDouble(backCarLngIndex);
			if(null != backCarLng){
				lineBean.setBackCarLng(backCarLng);
			}
		}
		mCursor.close();
		return lineBean;
	}
}
