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
import com.daoliuhe.drive.bean.LocationBean;

public class DbAdapter {

	private static final String TAG = "DbAdapter";

	public static final String KEY_ID 		 = "id";
	public static final String KEY_IME 		 = "ime";		//本机IME
	
	public static final String KEY_LINE_NAME = "lineName";	//路线名称
	public static final String KEY_COMMENT = "comment";		//备注

	public static final String KEY_VOICETYPE = "voiceType";	//语音类型
	public static final String KEY_LINEID = "lineId";		//路线id
	public static final String KEY_LATITUDE = "latitude";	//纬度
	public static final String KEY_LONGITUDE = "longitude";	//经度
	public static final String KEY_BEARING = "bearing";		//方位
	
	private static final String TABLE_LINE_CREATE = "create table t_lines ("
			+" id integer primary key, "
			+" lineName text not null,"
			+" comment text);";
	
	private static final String INSERT_LINE1 = "insert into t_lines (id,lineName,comment) values(1,'海滨1号线(1)','l1')";
	private static final String INSERT_LINE2 = "insert into t_lines (id,lineName,comment) values(2,'海滨1号线(2)','l2')";
	private static final String INSERT_LINE3 = "insert into t_lines (id,lineName,comment) values(3,'海滨2号线(1)','l3')";
	private static final String INSERT_LINE4 = "insert into t_lines (id,lineName,comment) values(4,'海滨2号线(2)','l4')";
	private static final String INSERT_LINE5 = "insert into t_lines (id,lineName,comment) values(5,'5号线','l5')";
	private static final String INSERT_LINE6 = "insert into t_lines (id,lineName,comment) values(6,'6号线','l6')";
	
	private static final String TABLE_LOCATION_CREATE = "create table t_locations ("
			+" id integer primary key autoincrement, "
			+" voiceType integer not null,"
			+" lineId integer not null,"
			+" latitude REAL not null, " 
			+" longitude REAL not null,"
			+" bearing REAL not null)";

	//路线1-1
	private static final String INSERT_LINE11_LOCATION1 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,1,120.821543,30.177781,181.07);";
	private static final String INSERT_LINE11_LOCATION2 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,2,120.821581,30.176933,178.07);";
	private static final String INSERT_LINE11_LOCATION3 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,4,120.821575,30.175501,192.02);";
	private static final String INSERT_LINE11_LOCATION4 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,4,120.823163,30.175381,231.01);";
	private static final String INSERT_LINE11_LOCATION5 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,6,120.823556,30.176125,9.09);";
	private static final String INSERT_LINE11_LOCATION6 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,5,120.823455,30.178751,37.17);";
	private static final String INSERT_LINE11_LOCATION7 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,13,120.827036,30.179314,86.05);";
	private static final String INSERT_LINE11_LOCATION8 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,12,120.828676,30.179434,90.83);";
	private static final String INSERT_LINE11_LOCATION9 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,3,120.831515,30.179575,83.83);";
	private static final String INSERT_LINE11_LOCATION10 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,4,120.836691,30.179723,93.95);";
	private static final String INSERT_LINE11_LOCATION11 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,8,120.837926,30.180398,347.96);";
	private static final String INSERT_LINE11_LOCATION12 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,11,120.837583,30.181409,338.46);";
	private static final String INSERT_LINE11_LOCATION13 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,10,120.821543,30.177781,340);";
	private static final String INSERT_LINE11_LOCATION14 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,3,120.836994,30.183226,352.88);";
	private static final String INSERT_LINE11_LOCATION15 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,14,120.836498,30.184795,354);";
	private static final String INSERT_LINE11_LOCATION16 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,3,120.835794,30.186965,352.07);";
	private static final String INSERT_LINE11_LOCATION17 ="insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (1,15,120.834963,30.189716,353.89);";

	//路线1-2
	private static final String INSERT_LINE12_LOCATION1 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,5,120.834959,30.189349,230.99);";
	private static final String INSERT_LINE12_LOCATION2 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,15,120.834233,30.188325,274.19);";
	private static final String INSERT_LINE12_LOCATION3 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,5,120.834058,30.188201,101.16);";
	private static final String INSERT_LINE12_LOCATION4 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,11,120.835918,30.186551,165.67);";
	private static final String INSERT_LINE12_LOCATION5 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,10,120.836173,30.1863785,166.21);";
	private static final String INSERT_LINE12_LOCATION6 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,7,120.836428,30.184655,166.75);";
	private static final String INSERT_LINE12_LOCATION7 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,14,120.836683,30.183960,165.75);";
	private static final String INSERT_LINE12_LOCATION8 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,8,120.837476,30.181465,184.4);";
	private static final String INSERT_LINE12_LOCATION9 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,5,120.837736,30.180599,190);";
	private static final String INSERT_LINE12_LOCATION10 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,1,120.837273,30.179746,277.35);";
	private static final String INSERT_LINE12_LOCATION11 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,2,120.835851,30.179640,255.64);";
	private static final String INSERT_LINE12_LOCATION12 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,3,120.83325,30.179516,258.18);";
	private static final String INSERT_LINE12_LOCATION13 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,13,120.829076,30.17938,250.71);";
	private static final String INSERT_LINE12_LOCATION14 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,12,120.827738,30.179358,242.16);";
	private static final String INSERT_LINE12_LOCATION15 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (2,4,120.824151,30.179151,251.92);";
	
	//路线2-1
	private static final String INSERT_LINE21_LOCATION1 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,1,120.821543,30.177781,181.07);";
	private static final String INSERT_LINE21_LOCATION2 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,2,120.821581,30.176933,178.07);";
	private static final String INSERT_LINE21_LOCATION3 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,4,120.821575,30.175501,192.02);";
	private static final String INSERT_LINE21_LOCATION4 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,4,120.823163,30.175381,231.01);";
	private static final String INSERT_LINE21_LOCATION5 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,6,120.823556,30.176125,9.09);";
	private static final String INSERT_LINE21_LOCATION6 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,5,120.823455,30.178751,37.17);";
	private static final String INSERT_LINE21_LOCATION7 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,13,120.827036,30.179314,86.05);";
	private static final String INSERT_LINE21_LOCATION8 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,12,120.828676,30.179434,90.83);";
	private static final String INSERT_LINE21_LOCATION9 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,3,120.831515,30.179575,83.83);";
	private static final String INSERT_LINE21_LOCATION10 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,5,120.83671,30.1796216,91.64);";
	private static final String INSERT_LINE21_LOCATION11 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,11,120.838206,30.178853,47.82);";
	private static final String INSERT_LINE21_LOCATION12 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,15,120.838583,30.178035,138.4);";
	private static final String INSERT_LINE21_LOCATION13 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,7,120.838391,30.179021,351.78);";
	private static final String INSERT_LINE21_LOCATION14 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,8,120.837926,30.180398,347.96);";
	private static final String INSERT_LINE21_LOCATION15 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,11,120.837583,30.181409,338.46);";
	private static final String INSERT_LINE21_LOCATION16 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,10,120.821543,30.177781,340);";
	private static final String INSERT_LINE21_LOCATION17 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,3,120.836994,30.183226,352.88);";
	private static final String INSERT_LINE21_LOCATION18 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (3,14,120.836498,30.184795,354);";
	
	//路线2-2
	private static final String INSERT_LINE22_LOCATION1 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,3,120.835794,30.186965,352.07);";
	private static final String INSERT_LINE22_LOCATION2 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,15,120.834963,30.189716,353.89);";
	private static final String INSERT_LINE22_LOCATION3 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,11,120.835918,30.186551,165.67);";
	private static final String INSERT_LINE22_LOCATION4 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,10,120.836173,30.1863785,166.21);";
	private static final String INSERT_LINE22_LOCATION5 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,7,120.836428,30.184655,166.75);";
	private static final String INSERT_LINE22_LOCATION6 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,14,120.836683,30.183960,165.75);";
	private static final String INSERT_LINE22_LOCATION7 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,8,120.837476,30.181465,184.4);";
	private static final String INSERT_LINE22_LOCATION8 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,5,120.837736,30.180599,190);";
	private static final String INSERT_LINE22_LOCATION9 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,1,120.837273,30.179746,277.35);";
	private static final String INSERT_LINE22_LOCATION10 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,2,120.835851,30.179640,255.64);";
	private static final String INSERT_LINE22_LOCATION11 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,3,120.83325,30.179516,258.18);";
	private static final String INSERT_LINE22_LOCATION12 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,13,120.829076,30.17938,250.71);";
	private static final String INSERT_LINE22_LOCATION13 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,12,120.827738,30.179358,242.16);";
	private static final String INSERT_LINE22_LOCATION14 = "insert into t_locations (lineId, voiceType, longitude, latitude, bearing) values (4,4,120.824151,30.179151,251.92);";

	private static final String DATABASE_NAME = "database";
	
	/**
	 * 路线表
	 */
	private static final String DATABASE_TABLE_LINES = "t_lines";
	
	/**
	 * 位置表
	 */
	private static final String DATABASE_TABLE_LOCATION = "t_locations";
	
	private static final int DATABASE_VERSION = 3;

	private final Context mCtx;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//创建表
			db.execSQL(TABLE_LINE_CREATE);
			db.execSQL(TABLE_LOCATION_CREATE);
			//初始化路线数据
			db.execSQL(INSERT_LINE1);
			db.execSQL(INSERT_LINE2);
			db.execSQL(INSERT_LINE3);
			db.execSQL(INSERT_LINE4);
			db.execSQL(INSERT_LINE5);
			db.execSQL(INSERT_LINE6);
			//初始化坐标数据
			db.execSQL(INSERT_LINE11_LOCATION1);
			db.execSQL(INSERT_LINE11_LOCATION2);
			db.execSQL(INSERT_LINE11_LOCATION3);
			db.execSQL(INSERT_LINE11_LOCATION4);
			db.execSQL(INSERT_LINE11_LOCATION5);
			db.execSQL(INSERT_LINE11_LOCATION6);
			db.execSQL(INSERT_LINE11_LOCATION7);
			db.execSQL(INSERT_LINE11_LOCATION8);
			db.execSQL(INSERT_LINE11_LOCATION9);
			db.execSQL(INSERT_LINE11_LOCATION10);
			db.execSQL(INSERT_LINE11_LOCATION11);
			db.execSQL(INSERT_LINE11_LOCATION12);
			db.execSQL(INSERT_LINE11_LOCATION13);
			db.execSQL(INSERT_LINE11_LOCATION14);
			db.execSQL(INSERT_LINE11_LOCATION15);
			db.execSQL(INSERT_LINE11_LOCATION16);
			db.execSQL(INSERT_LINE11_LOCATION17);
			
			db.execSQL(INSERT_LINE12_LOCATION1);
			db.execSQL(INSERT_LINE12_LOCATION2);
			db.execSQL(INSERT_LINE12_LOCATION3);
			db.execSQL(INSERT_LINE12_LOCATION4);
			db.execSQL(INSERT_LINE12_LOCATION5);
			db.execSQL(INSERT_LINE12_LOCATION6);
			db.execSQL(INSERT_LINE12_LOCATION7);
			db.execSQL(INSERT_LINE12_LOCATION8);
			db.execSQL(INSERT_LINE12_LOCATION9);
			db.execSQL(INSERT_LINE12_LOCATION10);
			db.execSQL(INSERT_LINE12_LOCATION11);
			db.execSQL(INSERT_LINE12_LOCATION12);
			db.execSQL(INSERT_LINE12_LOCATION13);
			db.execSQL(INSERT_LINE12_LOCATION14);
			db.execSQL(INSERT_LINE12_LOCATION15);
			
			db.execSQL(INSERT_LINE21_LOCATION1);
			db.execSQL(INSERT_LINE21_LOCATION2);
			db.execSQL(INSERT_LINE21_LOCATION3);
			db.execSQL(INSERT_LINE21_LOCATION4);
			db.execSQL(INSERT_LINE21_LOCATION5);
			db.execSQL(INSERT_LINE21_LOCATION6);
			db.execSQL(INSERT_LINE21_LOCATION7);
			db.execSQL(INSERT_LINE21_LOCATION8);
			db.execSQL(INSERT_LINE21_LOCATION9);
			db.execSQL(INSERT_LINE21_LOCATION10);
			db.execSQL(INSERT_LINE21_LOCATION11);
			db.execSQL(INSERT_LINE21_LOCATION12);
			db.execSQL(INSERT_LINE21_LOCATION13);
			db.execSQL(INSERT_LINE21_LOCATION14);
			db.execSQL(INSERT_LINE21_LOCATION15);
			db.execSQL(INSERT_LINE21_LOCATION16);
			db.execSQL(INSERT_LINE21_LOCATION17);
			db.execSQL(INSERT_LINE21_LOCATION18);
			
			db.execSQL(INSERT_LINE22_LOCATION1);
			db.execSQL(INSERT_LINE22_LOCATION2);
			db.execSQL(INSERT_LINE22_LOCATION3);
			db.execSQL(INSERT_LINE22_LOCATION4);
			db.execSQL(INSERT_LINE22_LOCATION5);
			db.execSQL(INSERT_LINE22_LOCATION6);
			db.execSQL(INSERT_LINE22_LOCATION7);
			db.execSQL(INSERT_LINE22_LOCATION8);
			db.execSQL(INSERT_LINE22_LOCATION9);
			db.execSQL(INSERT_LINE22_LOCATION10);
			db.execSQL(INSERT_LINE22_LOCATION11);
			db.execSQL(INSERT_LINE22_LOCATION12);
			db.execSQL(INSERT_LINE22_LOCATION13);
			db.execSQL(INSERT_LINE22_LOCATION14);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LINES);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LOCATION);
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
	
	/**
	 * 插入路线
	 * @param lineBean
	 * @return 插入记录的id,失败返回0
	 */
	public int insertLine(LineBean lineBean){
		Log.d(TAG,"insertLine()");
		ContentValues initialValues = new ContentValues();
		
		Integer id = lineBean.getId();
		if(null != id && id != 0){
			initialValues.put(KEY_ID, id);
		}
		
		String lineName = lineBean.getLineName();
		if(null != lineName && !lineName.isEmpty()){
			initialValues.put(KEY_LINE_NAME, lineName);
		}
		
		String comment = lineBean.getComment();
		if(null != comment && !comment.isEmpty()){
			initialValues.put(KEY_COMMENT, comment);
		}

		long ret = mDb.insert(DATABASE_TABLE_LINES, null, initialValues);
		Cursor cursor = mDb.rawQuery("select last_insert_rowid() id", null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndexOrThrow("id");
		int retId = cursor.getInt(columnIndex);

		if (ret > 0) {
			return retId;
		} else {
			return 0;
		}
	}
	
	/**
	 * 更新路线
	 * @param lineBean
	 * @return 是否成功
	 */
	public boolean updateLine(LineBean lineBean){
		Log.d(TAG,"updateLine() lineBean: " + lineBean.toString());
		
		ContentValues initialValues = new ContentValues();
		
		String lineName = lineBean.getLineName();
		if(null != lineName && !lineName.isEmpty()){
			initialValues.put(KEY_LINE_NAME, lineName);
		}
		
		String comment = lineBean.getComment();
		if(null != comment && !comment.isEmpty()){
			initialValues.put(KEY_COMMENT, comment);
		}
		//id
		Integer id = lineBean.getId();
		
		Log.d(TAG,"updateLine() initialValues: " + initialValues.toString());
		
		return  mDb.update(DATABASE_TABLE_LINES, initialValues, KEY_ID + "=" + id, null) > 0;
	}
	
	/**
	 * 查询所有的路线
	 * @param id
	 * @return 路线集合
	 * @throws SQLException
	 */
	public List<LineBean> selectAllLine() throws SQLException {
		Log.d(TAG,"selectAllLine()");
		//路线集合
		List<LineBean> list = new ArrayList<LineBean>();
		
		Cursor mCursor =
				mDb.query(DATABASE_TABLE_LINES, new String[] {
				KEY_ID, KEY_LINE_NAME, KEY_COMMENT
				}, null,
				null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			LineBean lineBean = new LineBean();
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);  
			int commentIndex = mCursor.getColumnIndex(KEY_COMMENT);  
			
			Integer id = mCursor.getInt(idIndex);
			if(null != id){
				lineBean.setId(id);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}

			String comment = mCursor.getString(commentIndex);
			if(null != comment && !comment.isEmpty()){
				lineBean.setComment(comment);
			}
			
			list.add(lineBean);
			
		}
		mCursor.close();
		
		return list;
	}
	
	/**
	 * 根据id查询路线
	 * @param id
	 * @return 路线集合
	 * @throws SQLException
	 */
	public LineBean selectLineBeanById(int id) throws SQLException {
		Log.d(TAG,"selectLineBeanById(int rowId)");
		Cursor mCursor =
				mDb.query(true, DATABASE_TABLE_LINES, new String[] {
						KEY_ID, KEY_LINE_NAME, KEY_COMMENT,
						}, KEY_ID + "=" + id, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		LineBean lineBean = null;

		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			lineBean = new LineBean();
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineNameIndex = mCursor.getColumnIndex(KEY_LINE_NAME);
			int commentIndex = mCursor.getColumnIndex(KEY_COMMENT);
			
			Integer retId = mCursor.getInt(idIndex);
			if(null != retId){
				lineBean.setId(retId);
			}
			
			String lineName = mCursor.getString(lineNameIndex);
			if(null != lineName && !lineName.isEmpty()){
				lineBean.setLineName(lineName);
			}
			
			String comment = mCursor.getString(commentIndex);
			if(null != comment && !comment.isEmpty()){
				lineBean.setComment(comment);
			}
		}
		mCursor.close();
		return lineBean;
	}
	
	/**
	 * 插入坐标
	 * @param locationBean
	 * @return 插入记录的id,失败返回0
	 */
	public int insertLocation(LocationBean locationBean){
		Log.d(TAG,"insertLocation()");
		ContentValues initialValues = new ContentValues();
		
		Integer lineId = locationBean.getLineId();
		if(null != lineId && lineId > 0){
			initialValues.put(KEY_LINEID, lineId);
		}
		
		Integer voiceType = locationBean.getVoiceType();
		if(null != voiceType){
			initialValues.put(KEY_VOICETYPE, voiceType);
		}

		Double longitude = locationBean.getLongitude();
		if(null != longitude){
			initialValues.put(KEY_LONGITUDE, longitude);
		}
		
		Double latitude = locationBean.getLatitude();
		if(null != latitude){
			initialValues.put(KEY_LATITUDE, latitude);
		}
		
		Float bearing = locationBean.getBearing();
		if(null != bearing){
			initialValues.put(KEY_BEARING, bearing);
		}
		
		long ret = mDb.insert(DATABASE_TABLE_LOCATION, null, initialValues);
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
	
	/**
	 * 更新坐标
	 * @param locationBean
	 * @return 是否成功
	 */
	public boolean updateLocation(LocationBean locationBean){
		Log.d(TAG,"updateLocation() lineBean: " + locationBean.toString());
		
		ContentValues initialValues = new ContentValues();
		
		Integer voiceType = locationBean.getVoiceType();
		if(null != voiceType){
			initialValues.put(KEY_VOICETYPE, voiceType);
		}

		Double longitude = locationBean.getLongitude();
		if(null != longitude){
			initialValues.put(KEY_LONGITUDE, longitude);
		}
		
		Double latitude = locationBean.getLatitude();
		if(null != latitude){
			initialValues.put(KEY_LATITUDE, latitude);
		}
		
		Float bearing = locationBean.getBearing();
		if(null != bearing){
			initialValues.put(KEY_BEARING, bearing);
		}
		
		//id
		Integer id = locationBean.getId();
		
		Log.d(TAG,"updateLocation() initialValues: " + initialValues.toString());
		
		return  mDb.update(DATABASE_TABLE_LOCATION, initialValues, KEY_ID + "=" + id, null) > 0;
	}
	
	/**
	 * 查询所有的路线
	 * @param id
	 * @return 路线集合
	 * @throws SQLException
	 */
	public List<LocationBean> selectAllLocation() throws SQLException {
		Log.d(TAG,"selectAllLocation()");
		//坐标集合
		List<LocationBean> list = new ArrayList<LocationBean>();
		
		Cursor mCursor =
				mDb.query(DATABASE_TABLE_LOCATION, new String[] {
						KEY_ID, KEY_LINEID, KEY_VOICETYPE, KEY_LONGITUDE, KEY_LATITUDE, KEY_BEARING
				}, null,
				null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			LocationBean locationBean = new LocationBean();
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineIdIndex = mCursor.getColumnIndex(KEY_LINEID);  
			int voiceTypeIndex = mCursor.getColumnIndex(KEY_VOICETYPE);  
			int longitudeIndex = mCursor.getColumnIndex(KEY_LONGITUDE);  
			int latitudeIndex = mCursor.getColumnIndex(KEY_LATITUDE);  
			int bearingIndex = mCursor.getColumnIndex(KEY_BEARING);  
			
			Integer id = mCursor.getInt(idIndex);
			if(null != id){
				locationBean.setId(id);
			}

			Integer lineId = mCursor.getInt(lineIdIndex);
			if(null != lineId){
				locationBean.setLineId(lineId);
			}
			
			Integer voiceType = mCursor.getInt(voiceTypeIndex);
			if(null != voiceType){
				locationBean.setVoiceType(voiceType);
			}
			
			Double longitude = mCursor.getDouble(longitudeIndex);
			if(null != longitude){
				locationBean.setLongitude(longitude);
			}

			Double latitude = mCursor.getDouble(latitudeIndex);
			if(null != latitude){
				locationBean.setLatitude(latitude);
			}

			Float bearing = mCursor.getFloat(bearingIndex);
			if(null != bearing){
				locationBean.setBearing(bearing);
			}
			
			list.add(locationBean);
		}
		mCursor.close();
		return list;
	}
	
	/**
	 * 根据id查询坐标
	 * @param id
	 * @return 坐标点
	 * @throws SQLException
	 */
	public LocationBean selectLocationById(int id) throws SQLException {
		Log.d(TAG,"selectLocationById(int rowId)");
		Cursor mCursor =
				mDb.query(true, DATABASE_TABLE_LOCATION, new String[] {
						KEY_ID, KEY_LINEID, KEY_VOICETYPE, KEY_LONGITUDE, KEY_LATITUDE, KEY_BEARING
				}, KEY_ID + "=" + id, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		LocationBean locationBean = new LocationBean();
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineIdIndex = mCursor.getColumnIndex(KEY_LINEID);  
			int voiceTypeIndex = mCursor.getColumnIndex(KEY_VOICETYPE);  
			int longitudeIndex = mCursor.getColumnIndex(KEY_LONGITUDE);  
			int latitudeIndex = mCursor.getColumnIndex(KEY_LATITUDE);  
			int bearingIndex = mCursor.getColumnIndex(KEY_BEARING);  
			
			Integer retId = mCursor.getInt(idIndex);
			if(null != retId){
				locationBean.setId(retId);
			}

			Integer lineId = mCursor.getInt(lineIdIndex);
			if(null != lineId){
				locationBean.setLineId(lineId);
			}
			
			Integer voiceType = mCursor.getInt(voiceTypeIndex);
			if(null != voiceType){
				locationBean.setVoiceType(voiceType);
			}
			
			Double longitude = mCursor.getDouble(longitudeIndex);
			if(null != longitude){
				locationBean.setLongitude(longitude);
			}

			Double latitude = mCursor.getDouble(latitudeIndex);
			if(null != latitude){
				locationBean.setLatitude(latitude);
			}

			Float bearing = mCursor.getFloat(bearingIndex);
			if(null != bearing){
				locationBean.setBearing(bearing);
			}
		}
		mCursor.close();
		return locationBean;
	}
	
	/**
	 * 根据路线id查询坐标
	 * @param id
	 * @return 坐标集合
	 * @throws SQLException
	 */
	public List<LocationBean> selectLocationByLineId(int lineId) throws SQLException {
		Log.d(TAG,"selectLocationById(int rowId)");
		Cursor mCursor =
				mDb.query(true, DATABASE_TABLE_LOCATION, new String[] {
						KEY_ID, KEY_LINEID, KEY_VOICETYPE, KEY_LONGITUDE, KEY_LATITUDE, KEY_BEARING
				}, KEY_LINEID + "=" + lineId, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		//坐标集合
		List<LocationBean> list = new ArrayList<LocationBean>();
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			LocationBean locationBean = new LocationBean();
			
			int idIndex = mCursor.getColumnIndex(KEY_ID);
			int lineIdIndex = mCursor.getColumnIndex(KEY_LINEID);  
			int voiceTypeIndex = mCursor.getColumnIndex(KEY_VOICETYPE);  
			int longitudeIndex = mCursor.getColumnIndex(KEY_LONGITUDE);  
			int latitudeIndex = mCursor.getColumnIndex(KEY_LATITUDE);  
			int bearingIndex = mCursor.getColumnIndex(KEY_BEARING);  
			
			Integer retId = mCursor.getInt(idIndex);
			if(null != retId){
				locationBean.setId(retId);
			}
			
			Integer retLineId = mCursor.getInt(lineIdIndex);
			if(null != retLineId){
				locationBean.setLineId(retLineId);
			}
			
			Integer voiceType = mCursor.getInt(voiceTypeIndex);
			if(null != voiceType){
				locationBean.setVoiceType(voiceType);
			}
			
			Double longitude = mCursor.getDouble(longitudeIndex);
			if(null != longitude){
				locationBean.setLongitude(longitude);
			}
			
			Double latitude = mCursor.getDouble(latitudeIndex);
			if(null != latitude){
				locationBean.setLatitude(latitude);
			}
			
			Float bearing = mCursor.getFloat(bearingIndex);
			if(null != bearing){
				locationBean.setBearing(bearing);
			}
			
			list.add(locationBean);
		}
		mCursor.close();
		return list;
	}
}
