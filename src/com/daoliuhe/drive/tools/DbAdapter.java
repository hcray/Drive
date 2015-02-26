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
	
	public static final String KEY_CHANGELANES_LAT = "changeLanesLat";
	public static final String KEY_CHANGELANES_LNG = "changeLanesLng";
	public static final String KEY_CHANGELANES_BR = "changeLanesBr";
	public static final String KEY_AHEADDIRECTLINE_LAT = "aheadDirectLineLat";
	public static final String KEY_AHEADDIRECTLINE_LNG = "aheadDirectLineLng";
	public static final String KEY_AHEADDIRECTLINE_BR = "aheadDirectLineBr";
	public static final String KEY_TURNLEFT_LAT = "turnLeftLat";
	public static final String KEY_TURNLEFT_LNG = "turnLeftLng";
	public static final String KEY_TURNLEFT_BR = "turnLeftBr";
	public static final String KEY_TURNRIGHT_LAT = "turnRightLat"; //纬度
	public static final String KEY_TURNRIGHT_LNG = "turnRightLng"; //经度
	public static final String KEY_TURNRIGHT_BR = "turnRightBr"; //经度
	public static final String KEY_SIDEWALK_LAT = "sidewalkLat";
	public static final String KEY_SIDEWALK_LNG = "sidewalkLng";
	public static final String KEY_SIDEWALK_BR = "sidewalkBr";
	
	public static final String KEY_PASSSCHOOL_LAT = "passSchoolLat";
	public static final String KEY_PASSSCHOOL_LNG = "passSchoolLng";
	public static final String KEY_PASSSCHOOL_BR = "passSchoolBr";
	public static final String KEY_PASSBUSSTATION_LAT = "passBusStationLat";
	public static final String KEY_PASSBUSSTATION_LNG = "passBusStationLng";
	public static final String KEY_PASSBUSSTATION_BR = "passBusStationBr";
	public static final String KEY_PASSSIDEWALK_LAT = "passSidewalkLat";
	public static final String KEY_PASSSIDEWALK_LNG = "passSidewalkLng";
	public static final String KEY_PASSSIDEWALK_BR = "passSidewalkBr";
	public static final String KEY_DIRECTLINE_LAT = "directLineLat";
	public static final String KEY_DIRECTLINE_LNG = "directLineLng";
	public static final String KEY_DIRECTLINE_BR = "directLineBr";
	public static final String KEY_ENDDIRECTLINE_LAT = "endDirectLineLat";
	public static final String KEY_ENDDIRECTLINE_LNG = "endDirectLineLng";
	public static final String KEY_ENDDIRECTLINE_BR = "endDirectLineBr";

	public static final String KEY_OVERTAKE_LAT = "overtakeLat";
	public static final String KEY_OVERTAKE_LNG = "overtakeLng";
	public static final String KEY_OVERTAKE_BR = "overtakeBr";
	public static final String KEY_TURN_LAT = "turnLat";
	public static final String KEY_TURN_LNG = "turnLng";
	public static final String KEY_TURN_BR = "turnBr";
	public static final String KEY_PULLOVER_LAT = "pullOverLat";
	public static final String KEY_PULLOVER_LNG = "pullOverLng";
	public static final String KEY_PULLOVER_BR = "pullOverBr";
	public static final String KEY_PASSING_LAT = "passingLat";
	public static final String KEY_PASSING_LNG = "passingLng";
	public static final String KEY_PASSING_BR = "passingBr";
	
	private static final String TAG = "DbAdapter";

	private static final String DATABASE_CREATE = "create table t_lines ("
			+" id integer primary key autoincrement, "
			+" lineName text not null,"
			+" changeLanesLat REAL, " 
			+" changeLanesLng REAL,"
			+" changeLanesBr REAL,"
			+" aheadDirectLineLat REAL, " 
			+" aheadDirectLineLng REAL,"
			+" aheadDirectLineBr REAL,"
			+" turnLeftLat REAL, " 
			+" turnLeftLng REAL,"
			+" turnLeftBr REAL,"
			+" turnRightLat REAL,"
			+" turnRightLng REAL,"
			+" turnRightBr REAL,"
			+" sidewalkLat REAL, " 
			+" sidewalkLng REAL,"
			+" sidewalkBr REAL,"
			
			+" passSchoolLat REAL, " 
			+" passSchoolLng REAL,"
			+" passSchoolBr REAL,"
			+" passBusStationLat REAL, " 
			+" passBusStationLng REAL,"
			+" passBusStationBr REAL,"
			+" passSidewalkLat REAL," 
			+" passSidewalkLng REAL,"
			+" passSidewalkBr REAL,"
			+" directLineLat REAL, " 
			+" directLineLng REAL,"
			+" directLineBr REAL,"
			+" endDirectLineLat REAL, " 
			+" endDirectLineLng REAL,"
			+" endDirectLineBr REAL,"
			
			+" overtakeLat REAL, " 
			+" overtakeLng REAL,"
			+" overtakeBr REAL,"
			+" turnLat REAL, " 
			+" turnLng REAL,"
			+" turnBr REAL,"
			+" pullOverLat REAL, " 
			+" pullOverLng REAL,"
			+" pullOverBr REAL,"
			+" passingLat REAL, " 
			+" passingLng REAL,"
			+" passingBr REAL);";

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
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		if(null != changeLanesLat){
			initialValues.put(KEY_CHANGELANES_LAT, changeLanesLat);
		}

		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLng){
			initialValues.put(KEY_CHANGELANES_LNG, changeLanesLng);
		}

		Float changeLanesBr = lineBean.getChangeLanesBr();
		if(null != changeLanesBr){
			initialValues.put(KEY_CHANGELANES_BR, changeLanesBr);
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		if(null != aheadDirectLineLat){
			initialValues.put(KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
		}

		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLng){
			initialValues.put(KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
		}
		
		Float aheadDirectLineBr = lineBean.getAheadDirectLineBr();
		if(null != aheadDirectLineBr){
			initialValues.put(KEY_AHEADDIRECTLINE_BR, aheadDirectLineBr);
		}
		
		Double turnLeftLat = lineBean.getTurnLeftLat();
		if(null != turnLeftLat){
			initialValues.put(KEY_TURNLEFT_LAT, turnLeftLat);
		}
		
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLng){
			initialValues.put(KEY_TURNLEFT_LNG, turnLeftLng);
		}
		
		Float turnLeftBr = lineBean.getTurnLeftBr();
		if(null != turnLeftBr){
			initialValues.put(KEY_TURNLEFT_BR, turnLeftBr);
		}
		
		Double turnRightLat = lineBean.getTurnRightLat();
		if(null != turnRightLat){
			initialValues.put(KEY_TURNRIGHT_LAT, turnRightLat);
		}
		
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLng){
			initialValues.put(KEY_TURNRIGHT_LNG, turnRightLng);
		}
		
		Float turnRightBr = lineBean.getTurnRightBr();
		if(null != turnRightBr){
			initialValues.put(KEY_TURNRIGHT_BR, turnRightBr);
		}
		
		Double sidewalkLat = lineBean.getSidewalkLat();
		if(null != sidewalkLat){
			initialValues.put(KEY_SIDEWALK_LAT, sidewalkLat);
		}
		
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLng){
			initialValues.put(KEY_SIDEWALK_LNG, sidewalkLng);
		}
		
		Float sidewalkBr = lineBean.getSidewalkBr();
		if(null != sidewalkBr){
			initialValues.put(KEY_SIDEWALK_BR, sidewalkBr);
		}
		
		Double passSchoolLat = lineBean.getPassSchoolLat();
		if(null != passSchoolLat){
			initialValues.put(KEY_PASSSCHOOL_LAT, passSchoolLat);
		}
		
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLng){
			initialValues.put(KEY_PASSSCHOOL_LNG, passSchoolLng);
		}
		
		Float passSchoolBr = lineBean.getPassSchoolBr();
		if(null != passSchoolBr){
			initialValues.put(KEY_PASSSCHOOL_BR, passSchoolBr);
		}
		
		Double passBusStationLat = lineBean.getPassBusStationLat();
		if(null != passBusStationLat){
			initialValues.put(KEY_PASSBUSSTATION_LAT, passBusStationLat);
		}
		
		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLng){
			initialValues.put(KEY_PASSBUSSTATION_LNG, passBusStationLng);
		}
		
		Float passBusStationBr = lineBean.getPassBusStationBr();
		if(null != passBusStationBr){
			initialValues.put(KEY_PASSBUSSTATION_BR, passBusStationBr);
		}
		
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		if(null != passSidewalkLat){
			initialValues.put(KEY_PASSSIDEWALK_LAT, passSidewalkLat);
		}
		
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLng){
			initialValues.put(KEY_PASSSIDEWALK_LNG, passSidewalkLng);
		}
		
		Float passSidewalkBr = lineBean.getPassSidewalkBr();
		if(null != passSidewalkBr){
			initialValues.put(KEY_PASSSIDEWALK_BR, passSidewalkBr);
		}
		
		Double directLineLat = lineBean.getDirectLineLat();
		if(null != directLineLat){
			initialValues.put(KEY_DIRECTLINE_LAT, directLineLat);
		}
		
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLng){
			initialValues.put(KEY_DIRECTLINE_LNG, directLineLng);
		}
		
		Float directLineBr = lineBean.getDirectLineBr();
		if(null != directLineBr){
			initialValues.put(KEY_DIRECTLINE_BR, directLineBr);
		}
		
		Double endDirectLineLat = lineBean.getEndDirectLineLat();
		if(null != endDirectLineLat){
			initialValues.put(KEY_ENDDIRECTLINE_LAT, endDirectLineLat);
		}
		
		Double endDirectLineLng = lineBean.getEndDirectLineLng();
		if(null != endDirectLineLng){
			initialValues.put(KEY_ENDDIRECTLINE_LNG, endDirectLineLng);
		}
		
		Float endDirectLineBr = lineBean.getEndDirectLineBr();
		if(null != endDirectLineBr){
			initialValues.put(KEY_ENDDIRECTLINE_BR, endDirectLineBr);
		}
	
		Double overtakeLat = lineBean.getOvertakeLat();
		if(null != overtakeLat){
			initialValues.put(KEY_OVERTAKE_LAT, overtakeLat);
		}
		
		Double overtakeLng = lineBean.getOvertakeLng();
		if(null != overtakeLng){
			initialValues.put(KEY_OVERTAKE_LNG, overtakeLng);
		}
		
		Float overtakeBr = lineBean.getOvertakeBr();
		if(null != overtakeBr){
			initialValues.put(KEY_OVERTAKE_BR, overtakeBr);
		}
		
		Double turnLat = lineBean.getTurnLat();
		if(null != turnLat){
			initialValues.put(KEY_TURN_LAT, turnLat);
		}
		
		Double turnLng = lineBean.getTurnLng();
		if(null != turnLng){
			initialValues.put(KEY_TURN_LNG, turnLng);
		}
		
		Float turnBr = lineBean.getTurnBr();
		if(null != turnBr){
			initialValues.put(KEY_TURN_BR, turnBr);
		}
		
		Double pullOverLat = lineBean.getPullOverLat();
		if(null != pullOverLat){
			initialValues.put(KEY_PULLOVER_LAT, pullOverLat);
		}
		
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLng){
			initialValues.put(KEY_PULLOVER_LNG, pullOverLng);
		}
		
		Float pullOverBr = lineBean.getPullOverBr();
		if(null != pullOverBr){
			initialValues.put(KEY_PULLOVER_BR, pullOverBr);
		}
		
		Double passingLat = lineBean.getPassingLat();
		if(null != passingLat){
			initialValues.put(KEY_PASSING_LAT, passingLat);
		}

		Double passingLng = lineBean.getPassingLng();
		if(null != passingLng){
			initialValues.put(KEY_PASSING_LNG, passingLng);
		}
		
		Float passingBr = lineBean.getPassingBr();
		if(null != passingBr){
			initialValues.put(KEY_PASSING_BR, passingBr);
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
		
		Double changeLanesLat = lineBean.getChangeLanesLat();
		if(null != changeLanesLat){
			initialValues.put(KEY_CHANGELANES_LAT, changeLanesLat);
		}

		Double changeLanesLng = lineBean.getChangeLanesLng();
		if(null != changeLanesLng){
			initialValues.put(KEY_CHANGELANES_LNG, changeLanesLng);
		}

		Float changeLanesBr = lineBean.getChangeLanesBr();
		if(null != changeLanesBr){
			initialValues.put(KEY_CHANGELANES_BR, changeLanesBr);
		}
		
		Double aheadDirectLineLat = lineBean.getAheadDirectLineLat();
		if(null != aheadDirectLineLat){
			initialValues.put(KEY_AHEADDIRECTLINE_LAT, aheadDirectLineLat);
		}

		Double aheadDirectLineLng = lineBean.getAheadDirectLineLng();
		if(null != aheadDirectLineLng){
			initialValues.put(KEY_AHEADDIRECTLINE_LNG, aheadDirectLineLng);
		}
		
		Float aheadDirectLineBr = lineBean.getAheadDirectLineBr();
		if(null != aheadDirectLineBr){
			initialValues.put(KEY_AHEADDIRECTLINE_BR, aheadDirectLineBr);
		}
		
		Double turnLeftLat = lineBean.getTurnLeftLat();
		if(null != turnLeftLat){
			initialValues.put(KEY_TURNLEFT_LAT, turnLeftLat);
		}
		
		Double turnLeftLng = lineBean.getTurnLeftLng();
		if(null != turnLeftLng){
			initialValues.put(KEY_TURNLEFT_LNG, turnLeftLng);
		}
		
		Float turnLeftBr = lineBean.getTurnLeftBr();
		if(null != turnLeftBr){
			initialValues.put(KEY_TURNLEFT_BR, turnLeftBr);
		}
		
		Double turnRightLat = lineBean.getTurnRightLat();
		if(null != turnRightLat){
			initialValues.put(KEY_TURNRIGHT_LAT, turnRightLat);
		}
		
		Double turnRightLng = lineBean.getTurnRightLng();
		if(null != turnRightLng){
			initialValues.put(KEY_TURNRIGHT_LNG, turnRightLng);
		}
		
		Float turnRightBr = lineBean.getTurnRightBr();
		if(null != turnRightBr){
			initialValues.put(KEY_TURNRIGHT_BR, turnRightBr);
		}
		
		Double sidewalkLat = lineBean.getSidewalkLat();
		if(null != sidewalkLat){
			initialValues.put(KEY_SIDEWALK_LAT, sidewalkLat);
		}
		
		Double sidewalkLng = lineBean.getSidewalkLng();
		if(null != sidewalkLng){
			initialValues.put(KEY_SIDEWALK_LNG, sidewalkLng);
		}
		
		Float sidewalkBr = lineBean.getSidewalkBr();
		if(null != sidewalkBr){
			initialValues.put(KEY_SIDEWALK_BR, sidewalkBr);
		}
		
		Double passSchoolLat = lineBean.getPassSchoolLat();
		if(null != passSchoolLat){
			initialValues.put(KEY_PASSSCHOOL_LAT, passSchoolLat);
		}
		
		Double passSchoolLng = lineBean.getPassSchoolLng();
		if(null != passSchoolLng){
			initialValues.put(KEY_PASSSCHOOL_LNG, passSchoolLng);
		}
		
		Float passSchoolBr = lineBean.getPassSchoolBr();
		if(null != passSchoolBr){
			initialValues.put(KEY_PASSSCHOOL_BR, passSchoolBr);
		}
		
		Double passBusStationLat = lineBean.getPassBusStationLat();
		if(null != passBusStationLat){
			initialValues.put(KEY_PASSBUSSTATION_LAT, passBusStationLat);
		}
		
		Double passBusStationLng = lineBean.getPassBusStationLng();
		if(null != passBusStationLng){
			initialValues.put(KEY_PASSBUSSTATION_LNG, passBusStationLng);
		}
		
		Float passBusStationBr = lineBean.getPassBusStationBr();
		if(null != passBusStationBr){
			initialValues.put(KEY_PASSBUSSTATION_BR, passBusStationBr);
		}
		
		Double passSidewalkLat = lineBean.getPassSidewalkLat();
		if(null != passSidewalkLat){
			initialValues.put(KEY_PASSSIDEWALK_LAT, passSidewalkLat);
		}
		
		Double passSidewalkLng = lineBean.getPassSidewalkLng();
		if(null != passSidewalkLng){
			initialValues.put(KEY_PASSSIDEWALK_LNG, passSidewalkLng);
		}
		
		Float passSidewalkBr = lineBean.getPassSidewalkBr();
		if(null != passSidewalkBr){
			initialValues.put(KEY_PASSSIDEWALK_BR, passSidewalkBr);
		}
		
		Double directLineLat = lineBean.getDirectLineLat();
		if(null != directLineLat){
			initialValues.put(KEY_DIRECTLINE_LAT, directLineLat);
		}
		
		Double directLineLng = lineBean.getDirectLineLng();
		if(null != directLineLng){
			initialValues.put(KEY_DIRECTLINE_LNG, directLineLng);
		}
		
		Float directLineBr = lineBean.getDirectLineBr();
		if(null != directLineBr){
			initialValues.put(KEY_DIRECTLINE_BR, directLineBr);
		}
		
		Double endDirectLineLat = lineBean.getEndDirectLineLat();
		if(null != endDirectLineLat){
			initialValues.put(KEY_ENDDIRECTLINE_LAT, endDirectLineLat);
		}
		
		Double endDirectLineLng = lineBean.getEndDirectLineLng();
		if(null != endDirectLineLng){
			initialValues.put(KEY_ENDDIRECTLINE_LNG, endDirectLineLng);
		}
		
		Float endDirectLineBr = lineBean.getEndDirectLineBr();
		if(null != endDirectLineBr){
			initialValues.put(KEY_ENDDIRECTLINE_BR, endDirectLineBr);
		}
	
		Double overtakeLat = lineBean.getOvertakeLat();
		if(null != overtakeLat){
			initialValues.put(KEY_OVERTAKE_LAT, overtakeLat);
		}
		
		Double overtakeLng = lineBean.getOvertakeLng();
		if(null != overtakeLng){
			initialValues.put(KEY_OVERTAKE_LNG, overtakeLng);
		}
		
		Float overtakeBr = lineBean.getOvertakeBr();
		if(null != overtakeBr){
			initialValues.put(KEY_OVERTAKE_BR, overtakeBr);
		}
		
		Double turnLat = lineBean.getTurnLat();
		if(null != turnLat){
			initialValues.put(KEY_TURN_LAT, turnLat);
		}
		
		Double turnLng = lineBean.getTurnLng();
		if(null != turnLng){
			initialValues.put(KEY_TURN_LNG, turnLng);
		}
		
		Float turnBr = lineBean.getTurnBr();
		if(null != turnBr){
			initialValues.put(KEY_TURN_BR, turnBr);
		}
		
		Double pullOverLat = lineBean.getPullOverLat();
		if(null != pullOverLat){
			initialValues.put(KEY_PULLOVER_LAT, pullOverLat);
		}
		
		Double pullOverLng = lineBean.getPullOverLng();
		if(null != pullOverLng){
			initialValues.put(KEY_PULLOVER_LNG, pullOverLng);
		}
		
		Float pullOverBr = lineBean.getPullOverBr();
		if(null != pullOverBr){
			initialValues.put(KEY_PULLOVER_BR, pullOverBr);
		}
		
		Double passingLat = lineBean.getPassingLat();
		if(null != passingLat){
			initialValues.put(KEY_PASSING_LAT, passingLat);
		}

		Double passingLng = lineBean.getPassingLng();
		if(null != passingLng){
			initialValues.put(KEY_PASSING_LNG, passingLng);
		}
		
		Float passingBr = lineBean.getPassingBr();
		if(null != passingBr){
			initialValues.put(KEY_PASSING_BR, passingBr);
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
				mDb.query(DATABASE_TABLE, new String[] {
				KEY_ID, KEY_LINE_NAME, 
				KEY_CHANGELANES_LAT, KEY_CHANGELANES_LNG, KEY_CHANGELANES_BR,
				KEY_AHEADDIRECTLINE_LAT, KEY_AHEADDIRECTLINE_LNG, KEY_AHEADDIRECTLINE_BR,
				KEY_TURNLEFT_LAT, KEY_TURNLEFT_LNG, KEY_TURNLEFT_BR,
				KEY_TURNRIGHT_LAT, KEY_TURNRIGHT_LNG, KEY_TURNRIGHT_BR, 
				KEY_SIDEWALK_LAT, KEY_SIDEWALK_LNG, KEY_SIDEWALK_BR,
				KEY_PASSSCHOOL_LAT, KEY_PASSSCHOOL_LNG, KEY_PASSSCHOOL_BR,  
				KEY_PASSBUSSTATION_LAT, KEY_PASSBUSSTATION_LNG, KEY_PASSBUSSTATION_BR,  
				KEY_PASSSIDEWALK_LAT, KEY_PASSSIDEWALK_LNG, KEY_PASSSIDEWALK_BR,  
				KEY_DIRECTLINE_LAT, KEY_DIRECTLINE_LNG, KEY_DIRECTLINE_BR,
				KEY_ENDDIRECTLINE_LAT, KEY_ENDDIRECTLINE_LNG, KEY_ENDDIRECTLINE_BR,  
				KEY_OVERTAKE_LAT, KEY_OVERTAKE_LNG, KEY_OVERTAKE_BR, 
				KEY_TURN_LAT, KEY_TURN_LNG, KEY_TURN_BR,  
				KEY_PULLOVER_LAT, KEY_PULLOVER_LNG, KEY_PULLOVER_BR,  
				KEY_PASSING_LAT, KEY_PASSING_LNG, KEY_PASSING_BR,}, null,
				null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			
			LineBean lineBean = new LineBean();
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);  
			
			int changeLanesLatIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LAT);
			int changeLanesLngIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LNG);
			int changeLanesBrIndex = mCursor.getColumnIndex(KEY_CHANGELANES_BR);
			
			int aheadDirectLineLatIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LAT);
			int aheadDirectLineLngIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LNG);
			int aheadDirectLineBrIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_BR);
			
			int turnLeftLatIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LAT); 
			int turnLeftLngIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LNG);
			int turnLeftBrIndex = mCursor.getColumnIndex(KEY_TURNLEFT_BR);
			
			int turnRightLatIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LAT);
			int turnRightLngIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LNG);
			int turnRightBrIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_BR);
			
			int sidewalkLatIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LAT);
			int sidewalkLngIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LNG);
			int sidewalkBrIndex = mCursor.getColumnIndex(KEY_SIDEWALK_BR);
			
			int passSchoolLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LAT);
			int passSchoolLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LNG);
			int passSchoolBrIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_BR);
			
			int passBusStationLatIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LAT);
			int passBusStationLngIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LNG);
			int passBusStationBrIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_BR);
			
			int passSidewalkLatIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LAT);
			int passSidewalkLngIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LNG);
			int passSidewalkBrIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_BR);
			
			int directLineLatIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LAT);
			int directLineLngIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LNG);
			int directLineBrIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_BR);
		
			int endDirectLineLatIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_LAT);
			int endDirectLineLngIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_LNG);
			int endDirectLineBrIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_BR);
			
			int overtakeLatIndex = mCursor.getColumnIndex(KEY_OVERTAKE_LAT);
			int overtakeLngIndex = mCursor.getColumnIndex(KEY_OVERTAKE_LNG);
			int overtakeBrIndex = mCursor.getColumnIndex(KEY_OVERTAKE_BR);
			
			int turnLatIndex = mCursor.getColumnIndex(KEY_TURN_LAT);
			int turnLngIndex = mCursor.getColumnIndex(KEY_TURN_LNG);
			int turnBrIndex = mCursor.getColumnIndex(KEY_TURN_BR);
			
			int pullOverLatIndex = mCursor.getColumnIndex(KEY_PULLOVER_LAT);
			int pullOverLngIndex = mCursor.getColumnIndex(KEY_PULLOVER_LNG);
			int pullOverBrIndex = mCursor.getColumnIndex(KEY_PULLOVER_BR);
			
			int passingLatIndex = mCursor.getColumnIndex(KEY_PASSING_LAT);
			int passingLngIndex = mCursor.getColumnIndex(KEY_PASSING_LNG);
			int passingBrIndex = mCursor.getColumnIndex(KEY_PASSING_BR);
			
			Integer id = mCursor.getInt(idIndex);
			if(null != id){
				lineBean.setId(id);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
			
			Double changeLanesLat = mCursor.getDouble(changeLanesLatIndex);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}

			Double changeLanesLng = mCursor.getDouble(changeLanesLngIndex);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}

			Float changeLanesBr = mCursor.getFloat(changeLanesBrIndex);
			if(null != changeLanesBr){
				lineBean.setChangeLanesBr(changeLanesBr);
			}
			
			Double aheadDirectLineLat = mCursor.getDouble(aheadDirectLineLatIndex);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}

			Double aheadDirectLineLng = mCursor.getDouble(aheadDirectLineLngIndex);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			
			Float aheadDirectLineBr = mCursor.getFloat(aheadDirectLineBrIndex);
			if(null != aheadDirectLineBr){
				lineBean.setAheadDirectLineBr(aheadDirectLineBr);
			}
			
			Double turnLeftLat = mCursor.getDouble(turnLeftLatIndex);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			
			Double turnLeftLng = mCursor.getDouble(turnLeftLngIndex);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			
			Float turnLeftBr = mCursor.getFloat(turnLeftBrIndex);
			if(null != turnLeftBr){
				lineBean.setTurnLeftBr(turnLeftBr);
			}
		
			Double turnRightLat = mCursor.getDouble(turnRightLatIndex);
			if(null != turnRightLat){
				lineBean.setTurnRightLat(turnRightLat);
			}
			
			Double turnRightLng = mCursor.getDouble(turnRightLngIndex);
			if(null != turnRightLng){
				lineBean.setTurnRightLng(turnRightLng);
			}
			
			Float turnRightBr = mCursor.getFloat(turnRightBrIndex);
			if(null != turnRightBr){
				lineBean.setTurnRightBr(turnRightBr);
			}
			
			Double sidewalkLat = mCursor.getDouble(sidewalkLatIndex);
			if(null != sidewalkLat){
				lineBean.setSidewalkLat(sidewalkLat);
			}
			
			Double sidewalkLng = mCursor.getDouble(sidewalkLngIndex);
			if(null != sidewalkLng){
				lineBean.setSidewalkLng(sidewalkLng);
			}
			
			Float sidewalkBr = mCursor.getFloat(sidewalkBrIndex);
			if(null != sidewalkBr){
				lineBean.setSidewalkBr(sidewalkBr);
			}
			
			Double passSchoolLat = mCursor.getDouble(passSchoolLatIndex);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			
			Double passSchoolLng = mCursor.getDouble(passSchoolLngIndex);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			
			Float passSchoolBr = mCursor.getFloat(passSchoolBrIndex);
			if(null != passSchoolBr){
				lineBean.setPassSchoolBr(passSchoolBr);
			}
			
			Double passBusStationLat = mCursor.getDouble(passBusStationLatIndex);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}

			Double passBusStationLng = mCursor.getDouble(passBusStationLngIndex);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			
			Float passBusStationBr = mCursor.getFloat(passBusStationBrIndex);
			if(null != passBusStationBr){
				lineBean.setPassBusStationBr(passBusStationBr);
			}
			
			Double passSidewalkLat = mCursor.getDouble(passSidewalkLatIndex);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			
			Double passSidewalkLng = mCursor.getDouble(passSidewalkLngIndex);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			
			Float passSidewalkBr = mCursor.getFloat(passSidewalkBrIndex);
			if(null != passSidewalkBr){
				lineBean.setPassSidewalkBr(passSidewalkBr);
			}
			
			Double directLineLat = mCursor.getDouble(directLineLatIndex);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			
			Double directLineLng = mCursor.getDouble(directLineLngIndex);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			
			Float directLineBr = mCursor.getFloat(directLineBrIndex);
			if(null != directLineBr){
				lineBean.setDirectLineBr(directLineBr);
			}
			
			Double endDirectLineLat = mCursor.getDouble(endDirectLineLatIndex);
			if(null != endDirectLineLat){
				lineBean.setEndDirectLineLat(endDirectLineLat);
			}
			
			Double endDirectLineLng = mCursor.getDouble(endDirectLineLngIndex);
			if(null != endDirectLineLng){
				lineBean.setEndDirectLineLng(endDirectLineLng);
			}
			
			Float endDirectLineBr = mCursor.getFloat(endDirectLineBrIndex);
			if(null != endDirectLineBr){
				lineBean.setEndDirectLineBr(endDirectLineBr);
			}
			
			Double overtakeLat = mCursor.getDouble(overtakeLatIndex);
			if(null != overtakeLat){
				lineBean.setOvertakeLat(overtakeLat);
			}
			
			Double overtakeLng = mCursor.getDouble(overtakeLngIndex);
			if(null != overtakeLng){
				lineBean.setOvertakeLng(overtakeLng);
			}
			
			Float overtakeBr = mCursor.getFloat(overtakeBrIndex);
			if(null != overtakeBr){
				lineBean.setOvertakeBr(overtakeBr);
			}
			
			Double turnLat = mCursor.getDouble(turnLatIndex);
			if(null != turnLat){
				lineBean.setTurnLat(turnLat);
			}

			Double turnLng = mCursor.getDouble(turnLngIndex);
			if(null != turnLng){
				lineBean.setTurnLng(turnLng);
			}
			
			Float turnBr = mCursor.getFloat(turnBrIndex);
			if(null != turnBr){
				lineBean.setTurnBr(turnBr);
			}

			Double pullOverLat = mCursor.getDouble(pullOverLatIndex);
			if(null != pullOverLat){
				lineBean.setPullOverLat(pullOverLat);
			}
			
			Double pullOverLng = mCursor.getDouble(pullOverLngIndex);
			if(null != pullOverLng){
				lineBean.setPullOverLng(pullOverLng);
			}
			
			Float pullOverBr = mCursor.getFloat(pullOverBrIndex);
			if(null != pullOverBr){
				lineBean.setPullOverBr(pullOverBr);
			}
			
			Double passingLat = mCursor.getDouble(passingLatIndex);
			if(null != passingLat){
				lineBean.setPassingLat(passingLat);
			}
			
			Double passingLng = mCursor.getDouble(passingLngIndex);
			if(null != passingLng){
				lineBean.setPassingLng(passingLng);
			}
			
			Float passingBr = mCursor.getFloat(passingBrIndex);
			if(null != passingBr){
				lineBean.setPassingBr(passingBr);
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
				mDb.query(true, DATABASE_TABLE, new String[] {
						KEY_ID, KEY_LINE_NAME, 
						KEY_CHANGELANES_LAT, KEY_CHANGELANES_LNG, KEY_CHANGELANES_BR,
						KEY_AHEADDIRECTLINE_LAT, KEY_AHEADDIRECTLINE_LNG, KEY_AHEADDIRECTLINE_BR,
						KEY_TURNLEFT_LAT, KEY_TURNLEFT_LNG, KEY_TURNLEFT_BR,
						KEY_TURNRIGHT_LAT, KEY_TURNRIGHT_LNG, KEY_TURNRIGHT_BR, 
						KEY_SIDEWALK_LAT, KEY_SIDEWALK_LNG, KEY_SIDEWALK_BR,
						KEY_PASSSCHOOL_LAT, KEY_PASSSCHOOL_LNG, KEY_PASSSCHOOL_BR,  
						KEY_PASSBUSSTATION_LAT, KEY_PASSBUSSTATION_LNG, KEY_PASSBUSSTATION_BR,  
						KEY_PASSSIDEWALK_LAT, KEY_PASSSIDEWALK_LNG, KEY_PASSSIDEWALK_BR,  
						KEY_DIRECTLINE_LAT, KEY_DIRECTLINE_LNG, KEY_DIRECTLINE_BR,
						KEY_ENDDIRECTLINE_LAT, KEY_ENDDIRECTLINE_LNG, KEY_ENDDIRECTLINE_BR,  
						KEY_OVERTAKE_LAT, KEY_OVERTAKE_LNG, KEY_OVERTAKE_BR, 
						KEY_TURN_LAT, KEY_TURN_LNG, KEY_TURN_BR,  
						KEY_PULLOVER_LAT, KEY_PULLOVER_LNG, KEY_PULLOVER_BR,  
						KEY_PASSING_LAT, KEY_PASSING_LNG, KEY_PASSING_BR,
						}, KEY_ID + "=" + id, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		LineBean lineBean = new LineBean();

		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);  
			
			int changeLanesLatIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LAT);
			int changeLanesLngIndex = mCursor.getColumnIndex(KEY_CHANGELANES_LNG);
			int changeLanesBrIndex = mCursor.getColumnIndex(KEY_CHANGELANES_BR);
			
			int aheadDirectLineLatIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LAT);
			int aheadDirectLineLngIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_LNG);
			int aheadDirectLineBrIndex = mCursor.getColumnIndex(KEY_AHEADDIRECTLINE_BR);
			
			int turnLeftLatIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LAT); 
			int turnLeftLngIndex = mCursor.getColumnIndex(KEY_TURNLEFT_LNG);
			int turnLeftBrIndex = mCursor.getColumnIndex(KEY_TURNLEFT_BR);
			
			int turnRightLatIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LAT);
			int turnRightLngIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_LNG);
			int turnRightBrIndex = mCursor.getColumnIndex(KEY_TURNRIGHT_BR);
			
			int sidewalkLatIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LAT);
			int sidewalkLngIndex = mCursor.getColumnIndex(KEY_SIDEWALK_LNG);
			int sidewalkBrIndex = mCursor.getColumnIndex(KEY_SIDEWALK_BR);
			
			int passSchoolLatIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LAT);
			int passSchoolLngIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_LNG);
			int passSchoolBrIndex = mCursor.getColumnIndex(KEY_PASSSCHOOL_BR);
			
			int passBusStationLatIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LAT);
			int passBusStationLngIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_LNG);
			int passBusStationBrIndex = mCursor.getColumnIndex(KEY_PASSBUSSTATION_BR);
			
			int passSidewalkLatIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LAT);
			int passSidewalkLngIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_LNG);
			int passSidewalkBrIndex = mCursor.getColumnIndex(KEY_PASSSIDEWALK_BR);
			
			int directLineLatIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LAT);
			int directLineLngIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_LNG);
			int directLineBrIndex = mCursor.getColumnIndex(KEY_DIRECTLINE_BR);
		
			int endDirectLineLatIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_LAT);
			int endDirectLineLngIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_LNG);
			int endDirectLineBrIndex = mCursor.getColumnIndex(KEY_ENDDIRECTLINE_BR);
			
			int overtakeLatIndex = mCursor.getColumnIndex(KEY_OVERTAKE_LAT);
			int overtakeLngIndex = mCursor.getColumnIndex(KEY_OVERTAKE_LNG);
			int overtakeBrIndex = mCursor.getColumnIndex(KEY_OVERTAKE_BR);
			
			int turnLatIndex = mCursor.getColumnIndex(KEY_TURN_LAT);
			int turnLngIndex = mCursor.getColumnIndex(KEY_TURN_LNG);
			int turnBrIndex = mCursor.getColumnIndex(KEY_TURN_BR);
			
			int pullOverLatIndex = mCursor.getColumnIndex(KEY_PULLOVER_LAT);
			int pullOverLngIndex = mCursor.getColumnIndex(KEY_PULLOVER_LNG);
			int pullOverBrIndex = mCursor.getColumnIndex(KEY_PULLOVER_BR);
			
			int passingLatIndex = mCursor.getColumnIndex(KEY_PASSING_LAT);
			int passingLngIndex = mCursor.getColumnIndex(KEY_PASSING_LNG);
			int passingBrIndex = mCursor.getColumnIndex(KEY_PASSING_BR);
			
			Integer retId = mCursor.getInt(idIndex);
			if(null != retId){
				lineBean.setId(id);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
			
			Double changeLanesLat = mCursor.getDouble(changeLanesLatIndex);
			if(null != changeLanesLat){
				lineBean.setChangeLanesLat(changeLanesLat);
			}

			Double changeLanesLng = mCursor.getDouble(changeLanesLngIndex);
			if(null != changeLanesLng){
				lineBean.setChangeLanesLng(changeLanesLng);
			}

			Float changeLanesBr = mCursor.getFloat(changeLanesBrIndex);
			if(null != changeLanesBr){
				lineBean.setChangeLanesBr(changeLanesBr);
			}
			
			Double aheadDirectLineLat = mCursor.getDouble(aheadDirectLineLatIndex);
			if(null != aheadDirectLineLat){
				lineBean.setAheadDirectLineLat(aheadDirectLineLat);
			}

			Double aheadDirectLineLng = mCursor.getDouble(aheadDirectLineLngIndex);
			if(null != aheadDirectLineLng){
				lineBean.setAheadDirectLineLng(aheadDirectLineLng);
			}
			
			Float aheadDirectLineBr = mCursor.getFloat(aheadDirectLineBrIndex);
			if(null != aheadDirectLineBr){
				lineBean.setAheadDirectLineBr(aheadDirectLineBr);
			}
			
			Double turnLeftLat = mCursor.getDouble(turnLeftLatIndex);
			if(null != turnLeftLat){
				lineBean.setTurnLeftLat(turnLeftLat);
			}
			
			Double turnLeftLng = mCursor.getDouble(turnLeftLngIndex);
			if(null != turnLeftLng){
				lineBean.setTurnLeftLng(turnLeftLng);
			}
			
			Float turnLeftBr = mCursor.getFloat(turnLeftBrIndex);
			if(null != turnLeftBr){
				lineBean.setTurnLeftBr(turnLeftBr);
			}
		
			Double turnRightLat = mCursor.getDouble(turnRightLatIndex);
			if(null != turnRightLat){
				lineBean.setTurnRightLat(turnRightLat);
			}
			
			Double turnRightLng = mCursor.getDouble(turnRightLngIndex);
			if(null != turnRightLng){
				lineBean.setTurnRightLng(turnRightLng);
			}
			
			Float turnRightBr = mCursor.getFloat(turnRightBrIndex);
			if(null != turnRightBr){
				lineBean.setTurnRightBr(turnRightBr);
			}
			
			Double sidewalkLat = mCursor.getDouble(sidewalkLatIndex);
			if(null != sidewalkLat){
				lineBean.setSidewalkLat(sidewalkLat);
			}
			
			Double sidewalkLng = mCursor.getDouble(sidewalkLngIndex);
			if(null != sidewalkLng){
				lineBean.setSidewalkLng(sidewalkLng);
			}
			
			Float sidewalkBr = mCursor.getFloat(sidewalkBrIndex);
			if(null != sidewalkBr){
				lineBean.setSidewalkBr(sidewalkBr);
			}
			
			Double passSchoolLat = mCursor.getDouble(passSchoolLatIndex);
			if(null != passSchoolLat){
				lineBean.setPassSchoolLat(passSchoolLat);
			}
			
			Double passSchoolLng = mCursor.getDouble(passSchoolLngIndex);
			if(null != passSchoolLng){
				lineBean.setPassSchoolLng(passSchoolLng);
			}
			
			Float passSchoolBr = mCursor.getFloat(passSchoolBrIndex);
			if(null != passSchoolBr){
				lineBean.setPassSchoolBr(passSchoolBr);
			}
			
			Double passBusStationLat = mCursor.getDouble(passBusStationLatIndex);
			if(null != passBusStationLat){
				lineBean.setPassBusStationLat(passBusStationLat);
			}

			Double passBusStationLng = mCursor.getDouble(passBusStationLngIndex);
			if(null != passBusStationLng){
				lineBean.setPassBusStationLng(passBusStationLng);
			}
			
			Float passBusStationBr = mCursor.getFloat(passBusStationBrIndex);
			if(null != passBusStationBr){
				lineBean.setPassBusStationBr(passBusStationBr);
			}
			
			Double passSidewalkLat = mCursor.getDouble(passSidewalkLatIndex);
			if(null != passSidewalkLat){
				lineBean.setPassSidewalkLat(passSidewalkLat);
			}
			
			Double passSidewalkLng = mCursor.getDouble(passSidewalkLngIndex);
			if(null != passSidewalkLng){
				lineBean.setPassSidewalkLng(passSidewalkLng);
			}
			
			Float passSidewalkBr = mCursor.getFloat(passSidewalkBrIndex);
			if(null != passSidewalkBr){
				lineBean.setPassSidewalkBr(passSidewalkBr);
			}
			
			Double directLineLat = mCursor.getDouble(directLineLatIndex);
			if(null != directLineLat){
				lineBean.setDirectLineLat(directLineLat);
			}
			
			Double directLineLng = mCursor.getDouble(directLineLngIndex);
			if(null != directLineLng){
				lineBean.setDirectLineLng(directLineLng);
			}
			
			Float directLineBr = mCursor.getFloat(directLineBrIndex);
			if(null != directLineBr){
				lineBean.setDirectLineBr(directLineBr);
			}
			
			Double endDirectLineLat = mCursor.getDouble(endDirectLineLatIndex);
			if(null != endDirectLineLat){
				lineBean.setEndDirectLineLat(endDirectLineLat);
			}
			
			Double endDirectLineLng = mCursor.getDouble(endDirectLineLngIndex);
			if(null != endDirectLineLng){
				lineBean.setEndDirectLineLng(endDirectLineLng);
			}
			
			Float endDirectLineBr = mCursor.getFloat(endDirectLineBrIndex);
			if(null != endDirectLineBr){
				lineBean.setEndDirectLineBr(endDirectLineBr);
			}
			
			Double overtakeLat = mCursor.getDouble(overtakeLatIndex);
			if(null != overtakeLat){
				lineBean.setOvertakeLat(overtakeLat);
			}
			
			Double overtakeLng = mCursor.getDouble(overtakeLngIndex);
			if(null != overtakeLng){
				lineBean.setOvertakeLng(overtakeLng);
			}
			
			Float overtakeBr = mCursor.getFloat(overtakeBrIndex);
			if(null != overtakeBr){
				lineBean.setOvertakeBr(overtakeBr);
			}
			
			Double turnLat = mCursor.getDouble(turnLatIndex);
			if(null != turnLat){
				lineBean.setTurnLat(turnLat);
			}

			Double turnLng = mCursor.getDouble(turnLngIndex);
			if(null != turnLng){
				lineBean.setTurnLng(turnLng);
			}
			
			Float turnBr = mCursor.getFloat(turnBrIndex);
			if(null != turnBr){
				lineBean.setTurnBr(turnBr);
			}

			Double pullOverLat = mCursor.getDouble(pullOverLatIndex);
			if(null != pullOverLat){
				lineBean.setPullOverLat(pullOverLat);
			}
			
			Double pullOverLng = mCursor.getDouble(pullOverLngIndex);
			if(null != pullOverLng){
				lineBean.setPullOverLng(pullOverLng);
			}
			
			Float pullOverBr = mCursor.getFloat(pullOverBrIndex);
			if(null != pullOverBr){
				lineBean.setPullOverBr(pullOverBr);
			}
			
			Double passingLat = mCursor.getDouble(passingLatIndex);
			if(null != passingLat){
				lineBean.setPassingLat(passingLat);
			}
			
			Double passingLng = mCursor.getDouble(passingLngIndex);
			if(null != passingLng){
				lineBean.setPassingLng(passingLng);
			}
			
			Float passingBr = mCursor.getFloat(passingBrIndex);
			if(null != passingBr){
				lineBean.setPassingBr(passingBr);
			}
		}
		mCursor.close();
		return lineBean;
	}
}
