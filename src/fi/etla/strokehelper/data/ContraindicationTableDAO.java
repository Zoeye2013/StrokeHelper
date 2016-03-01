package fi.etla.strokehelper.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContraindicationTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	public ContraindicationTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
	}
	
	public void deleteContraindications(long patId) throws SQLException {
		sqliteDB.delete(DataBaseManage.TABLE_CONTRAINDICATION, DataBaseManage.COLUMN_PATIENT_ID + " = ?",
		            new String[] { String.valueOf(patId) });
	}
	
	public void updateContraindications(long patId, ArrayList<String> consList, ArrayList<String> relaConsList) {
		deleteContraindications(patId);
		ContentValues values;
		for(String cons: consList){
			values = new ContentValues();
			values.put(DataBaseManage.COLUMN_CONTRAINDICATION, cons);
			values.put(DataBaseManage.COLUMN_PATIENT_ID, patId);
			values.put(DataBaseManage.COLUMN_CONTRAINDICATION_TYPE, 0);
			sqliteDB.insert(DataBaseManage.TABLE_CONTRAINDICATION, null, values);
		}
		
		for(String relaCons: relaConsList){
			values = new ContentValues();
			values.put(DataBaseManage.COLUMN_CONTRAINDICATION, relaCons);
			values.put(DataBaseManage.COLUMN_PATIENT_ID, patId);
			values.put(DataBaseManage.COLUMN_CONTRAINDICATION_TYPE, 1);
			sqliteDB.insert(DataBaseManage.TABLE_CONTRAINDICATION, null, values);
		}
	}

	public Cursor fetchContraindications(long patId) throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_CONTRAINDICATION, new String[] {
				DataBaseManage.COLUMN_ID,
				DataBaseManage.COLUMN_CONTRAINDICATION,
				DataBaseManage.COLUMN_CONTRAINDICATION_TYPE},
				DataBaseManage.COLUMN_PATIENT_ID + " = ?" + " AND " + DataBaseManage.COLUMN_CONTRAINDICATION_TYPE + " = ?",
				new String[] { String.valueOf(patId), String.valueOf(0)}, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			return cursor;
		}
		return null;
	}
	
	public Cursor fetchRelativeContraindications(long patId) throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_CONTRAINDICATION, new String[] {
				DataBaseManage.COLUMN_ID,
				DataBaseManage.COLUMN_CONTRAINDICATION,
				DataBaseManage.COLUMN_CONTRAINDICATION_TYPE},
				DataBaseManage.COLUMN_PATIENT_ID + " = ?" + " AND " + DataBaseManage.COLUMN_CONTRAINDICATION_TYPE + " = ?",
				new String[] { String.valueOf(patId), String.valueOf(1)}, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			return cursor;
		}
		return null;
	}
}
