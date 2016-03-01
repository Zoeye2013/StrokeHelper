package fi.etla.strokehelper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DoctorUserTableDAO {
	
	private SQLiteDatabase sqliteDB;
	
	private int userId;
    private String username;
    private String password;

	public DoctorUserTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
		
	}
	
	public void init(){
		userId = -1;
		username = "";
		password = "";
	}

	/** Adding new doctor user */
	public long addDoctorUser() {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_USERNAME, username);
		return sqliteDB.insert(DataBaseManage.TABLE_DOCTOR_USERS, null,
				initialValues);
	}

	public void addDoctorUser(String username, String pwd) {
	}

	public Cursor fetchAllDoctorUsers() {

		return sqliteDB.query(DataBaseManage.TABLE_DOCTOR_USERS,
				new String[] { DataBaseManage.COLUMN_ID,
						DataBaseManage.COLUMN_DOCTOR_USERNAME }, null, null,
				null, null, DataBaseManage.COLUMN_DOCTOR_USERNAME + " DESC");
	}

	public void fetchDoctorUser(String username) throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_DOCTOR_USERS, new String[] {
				DataBaseManage.COLUMN_ID},
				DataBaseManage.COLUMN_DOCTOR_USERNAME+ "=" + "\"" + username + "\"", null, null,
				null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setUserID(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManage.COLUMN_ID)));
			setName(username);
		}
	}
	
	public int getUserID(){
        return userId;
    }
    public void setUserID(int id){
        userId = id;
    }

    public String getName(){
        return username;
    }
    public void setName(String name){
        username = name;
    }

    public String getPwd(){
        return password;
    }
    public void setPwd(String pwd){
        password = pwd;
    }
}
