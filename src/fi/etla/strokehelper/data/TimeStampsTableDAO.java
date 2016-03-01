package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

public class TimeStampsTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private long preDoorTime;
	private long doorTime;
	private long postDoorTime;

	private String sympOnsetQua;
	private int sympOnsetQuaId;

	private boolean onsetUnLock;
	private int sympOnsetHour;
	private int sympOnsetMin;
	private long sympOnsetTime;

	private boolean estiArriUnLock;
	private int estiArriHour;
	private int estiArriMin;
	private long estiArriTime;

	private long thromboDeciTime;

	private long bolusTime;
	private long infusionBeTime;
	private long infusionEndTime;

	private long followEndTime;
	private long interruptionTime;

	private long ctBeginTime;
	private long ctStatementTime;

	private boolean isTimeStampsInfoLoaded;

	public TimeStampsTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		preDoorTime = -1;
		doorTime = -1;
		postDoorTime = -1;

		sympOnsetQua = "";
		sympOnsetQuaId = -1;

		sympOnsetHour = -1;
		sympOnsetMin = -1;

		onsetUnLock = true;
		estiArriUnLock = true;

		estiArriHour = -1;
		estiArriMin = -1;

		bolusTime = -1;
		infusionBeTime = -1;
		infusionEndTime = -1;

		followEndTime = -1;
		interruptionTime = -1;

		ctBeginTime = -1;
		ctStatementTime = -1;

		isTimeStampsInfoLoaded = false;
	}

	public SQLiteDatabase getSQLdb() {
		return sqliteDB;
	}

	public long newEmptyTimestamps(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isTimeStampsInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_TIMESTAMPS, null,
				initialValues);
	}

	/** For patient page update */
	public int updateTimeStamps() {
		ContentValues values = new ContentValues();

		if (preDoorTime > 0)
			values.put(DataBaseManage.COLUMN_PREDOOR_TIME, preDoorTime);

		if (doorTime > 0)
			values.put(DataBaseManage.COLUMN_DOOR_TIME, doorTime);

		if (postDoorTime > 0)
			values.put(DataBaseManage.COLUMN_POSTDOOR_TIME, postDoorTime);

		if (sympOnsetQua != null && sympOnsetQua.length() > 0)
			values.put(DataBaseManage.COLUMN_ONSET_QUALITY, sympOnsetQua);
		if (sympOnsetQuaId > 0)
			values.put(DataBaseManage.COLUMN_ONSET_QUALITY_ID, sympOnsetQuaId);

		if (onsetUnLock) {
			values.put(DataBaseManage.COLUMN_ONSET_LOCK, 1);
		} else if (!onsetUnLock) {
			values.put(DataBaseManage.COLUMN_ONSET_LOCK, 0);
		}

		if (sympOnsetTime > 0)
			values.put(DataBaseManage.COLUMN_ONSET_TIME, sympOnsetTime);

		if (sympOnsetHour >= 0)
			values.put(DataBaseManage.COLUMN_ONSET_HOUR, sympOnsetHour);
		if (sympOnsetMin >= 0)
			values.put(DataBaseManage.COLUMN_ONSET_MIN, sympOnsetMin);

		if (estiArriUnLock) {
			values.put(DataBaseManage.COLUMN_ESTIMATE_LOCK, 1);
		} else if (!estiArriUnLock) {
			values.put(DataBaseManage.COLUMN_ESTIMATE_LOCK, 0);
		}

		if (estiArriTime > 0)
			values.put(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_TIME,
					estiArriTime);
		if (estiArriHour >= 0)
			values.put(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_HOUR,
					estiArriHour);
		if (estiArriMin >= 0)
			values.put(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_MIN, estiArriMin);

		if (thromboDeciTime > 0)
			values.put(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_TIME,
					thromboDeciTime);

		if (bolusTime > 0)
			values.put(DataBaseManage.COLUMN_BOLUS_TIME, bolusTime);

		if (infusionBeTime > 0)
			values.put(DataBaseManage.COLUMN_INFUSION_BEGIN_TIME,
					infusionBeTime);

		if (infusionEndTime > 0)
			values.put(DataBaseManage.COLUMN_INFUSION_END_TIME, infusionEndTime);

		if (followEndTime > 0)
			values.put(DataBaseManage.COLUMN_FOLLOWUP_END_TIME, followEndTime);

		if (interruptionTime > 0)
			values.put(DataBaseManage.COLUMN_INTERRUPT_TREAT_TIME,
					interruptionTime);

		if (ctBeginTime > 0)
			values.put(DataBaseManage.COLUMN_CT_BEGIN_TIME, ctBeginTime);

		if (ctStatementTime > 0)
			values.put(DataBaseManage.COLUMN_CT_STATEMENT_TIME, ctStatementTime);

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_TIMESTAMPS, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	/** For patient page */
	public void fetchTimestamp() throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_TIMESTAMPS, new String[] {
				DataBaseManage.COLUMN_ONSET_LOCK,
				DataBaseManage.COLUMN_ONSET_TIME,
				DataBaseManage.COLUMN_ONSET_HOUR,
				DataBaseManage.COLUMN_ONSET_MIN,
				DataBaseManage.COLUMN_ESTIMATE_LOCK,
				DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_TIME,
				DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_HOUR,
				DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_MIN,
				DataBaseManage.COLUMN_ONSET_QUALITY_ID,
				DataBaseManage.COLUMN_PREDOOR_TIME,
				DataBaseManage.COLUMN_DOOR_TIME,
				DataBaseManage.COLUMN_POSTDOOR_TIME,
				DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_TIME,
				DataBaseManage.COLUMN_BOLUS_TIME,
				DataBaseManage.COLUMN_INFUSION_BEGIN_TIME,
				DataBaseManage.COLUMN_INFUSION_END_TIME,
				DataBaseManage.COLUMN_FOLLOWUP_END_TIME,
				DataBaseManage.COLUMN_INTERRUPT_TREAT_TIME,
				DataBaseManage.COLUMN_CT_BEGIN_TIME,
				DataBaseManage.COLUMN_CT_STATEMENT_TIME },
				DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setOnsetUnlock(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_LOCK)) == 1);
			setSympOnsetTime(cursor.getLong(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_TIME)));
			setSympOnsetHour(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_HOUR)));
			setSympOnsetMin(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_MIN)));

			setEstiArriUnlock(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ESTIMATE_LOCK)) == 1);

			setEstiArriTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_TIME)));
			setEstiArriHour(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_HOUR)));
			setEstiArriMin(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ESTIMATE_ARRIVAL_MIN)));

			setSympOnsetQuaId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_QUALITY_ID)));

			setPreDoorTime(cursor.getLong(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_PREDOOR_TIME)));
			setDoorTime(cursor.getLong(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_DOOR_TIME)));
			setPostDoorTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_POSTDOOR_TIME)));

			setDecisionTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_TIME)));

			setBolusTime(cursor.getLong(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BOLUS_TIME)));
			setInfusionBeTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INFUSION_BEGIN_TIME)));
			setInfusionEndTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INFUSION_END_TIME)));

			setFollowEndTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FOLLOWUP_END_TIME)));

			setInterruptionTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TREAT_TIME)));

			setCTBeginTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_BEGIN_TIME)));

			setCTStatementTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_STATEMENT_TIME)));
		}
		isTimeStampsInfoLoaded = true;
	}

	public boolean isTimeStampsInfoLoaded() {
		return isTimeStampsInfoLoaded;
	}

	public boolean isTimeStampsInfoComplete() {
		boolean isComplete = true;
		if (sympOnsetQuaId <= 0
				|| (sympOnsetQuaId == R.id.radio_known_onset && sympOnsetTime <= 0))
			isComplete = false;
		return isComplete;
	}

	public void summarizeContraindications() {
		if (sympOnsetQuaId > 0) {
			if (sympOnsetQuaId == R.id.radio_unknown)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_1));
			double timer = AppViewModel.milliToHour(getTimer());
			if (sympOnsetQuaId != R.id.radio_unknown && sympOnsetQuaId != 0
					&& timer > 4.5)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_2));
		}
	}

	public void setPreDoorTime(long time) {
		preDoorTime = time;
	}

	public void setDoorTime(long time) {
		doorTime = time;
	}

	public void setPostDoorTime(long time) {
		postDoorTime = time;
	}

	public long getPreDoorTime() {
		return preDoorTime;
	}

	public long getDoorTime() {
		return doorTime;
	}

	public long getPostDoorTime() {
		return postDoorTime;
	}

	public void setSympOnsetQua(String str) {
		sympOnsetQua = str;
	}

	public void setSympOnsetQuaId(int id) {
		sympOnsetQuaId = id;
	}

	public String getSympOnsetQua() {
		return sympOnsetQua;
	}

	public int getSympOnsetQuaId() {
		return sympOnsetQuaId;
	}

	public void setSympOnsetHour(int in) {
		sympOnsetHour = in;
	}

	public void setSympOnsetMin(int in) {
		sympOnsetMin = in;
	}

	public int getSympOnsetHour() {
		return sympOnsetHour;
	}

	public int getSympOnsetMin() {
		return sympOnsetMin;
	}

	public void setSympOnsetTime(long t) {
		sympOnsetTime = t;
	}

	public long getSympOnsetTime() {
		return sympOnsetTime;
	}

	// public void setLastSympFreeHour(int in) {
	// lastSympFreeHour = in;
	// }
	//
	// public void setLastSympFreeMin(int in) {
	// lastSympFreeMin = in;
	// }
	//
	// public int getLastSympFreeHour() {
	// return lastSympFreeHour;
	// }
	//
	// public int getLastSympFreeMin() {
	// return lastSympFreeMin;
	// }
	//
	// public void setLastSympFreeTime(long t){
	// lastSympFreeTime = t;
	// }
	// public long getLastSympFreeTime(){
	// return lastSympFreeTime;
	// }

	public void setEstiArriHour(int in) {
		estiArriHour = in;
	}

	public void setEstiArriMin(int in) {
		estiArriMin = in;
	}

	public void setEstiArriTime(long time) {
		estiArriTime = time;
	}

	public long getEstiArriTime() {
		return estiArriTime;
	}

	public int getEstiArriHour() {
		return estiArriHour;
	}

	public int getEstiArriMin() {
		return estiArriMin;
	}

	public void setDecisionTime(long t) {
		thromboDeciTime = t;
	}

	public long getDecisionTime() {
		return thromboDeciTime;
	}

	public void setOnsetUnlock(boolean isUnlock) {
		onsetUnLock = isUnlock;
	}

	public boolean getOnsetUnlock() {
		return onsetUnLock;
	}

	public void setEstiArriUnlock(boolean isUnlock) {
		estiArriUnLock = isUnlock;
	}

	public boolean getEstiArriUnlock() {
		return estiArriUnLock;
	}

	// public void setSympOnsetDate(int date){
	// sympOnsetDate = date;
	// }

	// public int getSympOnsetDate(){
	// return sympOnsetDate;
	// }

	// public void setLastSympFreeDate(int date){
	// lastSympFreeDate = date;
	// }
	//
	// public int getLastSympFreeDate(){
	// return lastSympFreeDate;
	// }
	// public void setEstiArriDate(int date){
	// estiArriDate = date;
	// }
	//
	// public int getEstiArriDate(){
	// return estiArriDate;
	// }

	public void setBolusTime(long t) {
		bolusTime = t;
	}

	public long getBolusTime() {
		return bolusTime;
	}

	public void setInfusionBeTime(long t) {
		infusionBeTime = t;
	}

	public long getInfusionBeTime() {
		return infusionBeTime;
	}

	public void setInfusionEndTime(long t) {
		infusionEndTime = t;
	}

	public long getInfusionEndTime() {
		return infusionEndTime;
	}

	public void setFollowEndTime(long t) {
		followEndTime = t;
	}

	public long getFollowEndTime() {
		return followEndTime;
	}

	public void setInterruptionTime(long t) {
		interruptionTime = t;
	}

	public long getInterruptionTime() {
		return interruptionTime;
	}

	public void setCTBeginTime(long t) {
		ctBeginTime = t;
	}

	public long getCTBeginTime() {
		return ctBeginTime;
	}

	public void setCTStatementTime(long t) {
		ctStatementTime = t;
	}

	public long getCTStatementTime() {
		return ctStatementTime;
	}

	public long getTimer() {
		Time time = new Time();
		time.setToNow();
		long timeDiff = time.toMillis(false) - sympOnsetTime;
		return timeDiff;
	}
}
