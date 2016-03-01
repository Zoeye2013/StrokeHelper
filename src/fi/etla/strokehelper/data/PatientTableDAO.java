package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PatientTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;
	
	private String logTime;
	
	private String onHospitalDate;
	
	private long patId;
	private String patType;
	private int patTypeId;

	private boolean birDayUnLock;
	private String dateOfBirth;
	private int birDay;
	private int birMonth;
	private int birYear;
	private int age;

	private double weight;

	private String candidate;
	private int candidateId;
	private String whyNotCandidate;

	private boolean isPatientInfoLoaded;

	public PatientTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}
	
	public void init(){
		patId = -1;
		
		logTime = "";
		onHospitalDate = "";
		patType = "";
		patTypeId = -1;
		dateOfBirth = "";
		birDay = -1;
		birMonth = -1;
		birYear = -1;
		
		birDayUnLock = true;
		
		weight = -1;
		candidate = "";
		candidateId = -1;
		whyNotCandidate = "";
		
		isPatientInfoLoaded = false;
	}

	public void newEmptyPatient(String time) {
		logTime = time;
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		isPatientInfoLoaded = true;
		patId = sqliteDB.insert(DataBaseManage.TABLE_PATIENTS, null,
				initialValues);
	}

	/** For patient page */
	public int updatePatient()
	{
		ContentValues values = new ContentValues();
		if(patType!= null && patType.length() > 0)
			values.put(DataBaseManage.COLUMN_PATIENT_TYPE, patType);
		if(patTypeId > 0)
			values.put(DataBaseManage.COLUMN_PATIENT_TYPE_ID, patTypeId);
		
		if(onHospitalDate!= null && onHospitalDate.length() > 0)
			values.put(DataBaseManage.COLUMN_ON_HOSPITAL_DATE, onHospitalDate);
		
		if (birDayUnLock) {
			values.put(DataBaseManage.COLUMN_BIRTHDAY_LOCK, 1);
		} else if(!birDayUnLock){
			values.put(DataBaseManage.COLUMN_BIRTHDAY_LOCK, 0);
		}
		
		if(dateOfBirth!= null && dateOfBirth.length() > 0)
		values.put(DataBaseManage.COLUMN_DATE_OF_BIRTH, dateOfBirth);
		if(birDay > 0)
		values.put(DataBaseManage.COLUMN_BIRTH_DAY, birDay);
		if(birMonth >= 0)
		values.put(DataBaseManage.COLUMN_BIRTH_MONTH, birMonth);
		if(birYear > 0)
		values.put(DataBaseManage.COLUMN_BIRTH_YEAR, birYear);
		if(age > 0)
		values.put(DataBaseManage.COLUMN_AGE, age);
		if(weight > 0)
			values.put(DataBaseManage.COLUMN_WEIGHT, weight);
		
		if(candidate!= null && candidate.length() > 0)
		values.put(DataBaseManage.COLUMN_THROMBOLYSIS_CANDIDATE, candidate);
		if(candidateId > 0)
			values.put(DataBaseManage.COLUMN_THROMBOLYSIS_CANDIDATE_ID,
					candidateId);
		if(whyNotCandidate!= null && whyNotCandidate.length() > 0)
			values.put(DataBaseManage.COLUMN_THROMBOLYSIS_CANDIDATE, whyNotCandidate);
		

		// updating row
		if(values.size() > 0){
		return sqliteDB.update(DataBaseManage.TABLE_PATIENTS, values,
				DataBaseManage.COLUMN_ID + " = ?",
				new String[] { String.valueOf(patId) });
		}
		return 0;
	}

	public Cursor fetchAllPatients() {
		
		String SELECT_QUERY = "SELECT " + DataBaseManage.TABLE_PATIENTS + "." + DataBaseManage.COLUMN_ID + ", " + DataBaseManage.COLUMN_DATE_OF_BIRTH + ", "
			+ DataBaseManage.COLUMN_DOOR_TIME  + ", " + DataBaseManage.COLUMN_ONSET_TIME
//			+ ", " + DataBaseManage.COLUMN_LAST_SYMPTOMS_FREE_TIME 
			+ " FROM "
			+ DataBaseManage.TABLE_PATIENTS + " INNER JOIN " + DataBaseManage.TABLE_TIMESTAMPS + " ON "
			+ DataBaseManage.TABLE_PATIENTS + "." + DataBaseManage.COLUMN_ID + " = "
			+ DataBaseManage.TABLE_TIMESTAMPS + "." + DataBaseManage.COLUMN_PATIENT_ID 
			+ " GROUP BY " + DataBaseManage.TABLE_PATIENTS + "." + DataBaseManage.COLUMN_ID + " ORDER BY "
			+ DataBaseManage.TABLE_PATIENTS + "." + DataBaseManage.COLUMN_ID + " DESC";
		
		Cursor queryCursor = sqliteDB.rawQuery(SELECT_QUERY,null);
		MatrixCursor extras = new MatrixCursor(new String[] { DataBaseManage.COLUMN_ID, DataBaseManage.COLUMN_DATE_OF_BIRTH });
		extras.addRow(new String[] { "-1", "Select Patient" });
		Cursor[] cursors = { extras, queryCursor };
		Cursor extendedCursor = new MergeCursor(cursors);
		
		return extendedCursor;
//		return sqliteDB.rawQuery(SELECT_QUERY,null);
	}

	/** For patient page */
	public void fetchPatient() throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_PATIENTS, new String[] {
				DataBaseManage.COLUMN_PATIENT_TYPE_ID,
				DataBaseManage.COLUMN_BIRTHDAY_LOCK,
				DataBaseManage.COLUMN_DATE_OF_BIRTH,
				DataBaseManage.COLUMN_BIRTH_DAY,
				DataBaseManage.COLUMN_BIRTH_MONTH,
				DataBaseManage.COLUMN_BIRTH_YEAR,
				DataBaseManage.COLUMN_AGE,
				DataBaseManage.COLUMN_WEIGHT,
				DataBaseManage.COLUMN_THROMBOLYSIS_CANDIDATE_ID,
				DataBaseManage.COLUMN_WHY},
				DataBaseManage.COLUMN_ID + "=" + patId, null, null, null, null,
				null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			
			setPatientTypeId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_PATIENT_TYPE_ID)));
			
			setBirDayUnlock(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BIRTHDAY_LOCK)) == 1);
			
			setDateOfBirth(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DATE_OF_BIRTH)));
			setBirthDay(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BIRTH_DAY)));
			setBirthMonth(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BIRTH_MONTH)));
			setBirthYear(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BIRTH_YEAR)));
			setPatientAge(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_AGE)));
			setWeight(cursor.getDouble(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_WEIGHT)));
			setCandidateId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_THROMBOLYSIS_CANDIDATE_ID)));
			setWhyNotCandidate(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_WHY)));
		}
		isPatientInfoLoaded = true;
	}
	
	public boolean isPatientInfoLoaded(){
		return isPatientInfoLoaded;
	}
	
	public boolean isPatientInfoComplete(){
		boolean isComplete = true;
		if (age <= 0)
			isComplete = false;
		return isComplete;
	}
	
	public void setLogTime(String time) {
		logTime = time;
	}
	
	public String getLogTime(){
		return logTime;
	}
	
	public void setOnHospitalDate(String date) {
		onHospitalDate = date;
	}
	
	public String getOnHospitalDate(){
		return onHospitalDate;
	}
	
	public void setPatientId(long id) {
		patId = id;
	}
	public long getPatientId() {
		return patId;
	}
	
	public void setPatientType(String str) {
		patType = str;
	}

	public void setPatientTypeId(int id) {
		patTypeId = id;
	}

	public String getPatientType() {
		return patType;
	}

	public int getPatientTypeId() {
		return patTypeId;
	}
	
	public void setDateOfBirth(String str) {
		dateOfBirth = str;
	}

	public void setBirthDay(int in) {
		birDay = in;
	}

	public void setBirthMonth(int in) {
		birMonth = in;
	}

	public void setBirthYear(int in) {
		birYear = in;
	}

	public void setPatientAge(int in) {
		age = in;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public int getBirthDay() {
		return birDay;
	}

	public int getBirthMonth() {
		return birMonth;
	}

	public int getBirthYear() {
		return birYear;
	}

	public int getPatientAge() {
		return age;
	}
	
	public void setBirDayUnlock(boolean isUnlock){
		birDayUnLock = isUnlock;
	}
	
	public boolean getBirDayUnlock(){
		return birDayUnLock;
	}
	
	public void setWeight(double dou) {
		weight = dou;
	}

	public double getWeight() {
		return weight;
	}

	public void setCandidate(String str) {
		candidate = str;
	}

	public void setCandidateId(int id) {
		candidateId = id;
	}

	public String getCandidate() {
		return candidate;
	}

	public int getCandidateId() {
		return candidateId;
	}
	
	public void setWhyNotCandidate(String why){
		whyNotCandidate = why;
	}
	public String getWhyNotCandidate(){
		return whyNotCandidate;
	}
}
