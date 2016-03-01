package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SymptomsTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private String sympSide;
	private int sympSideId;
	private String limbSymp;
	private int limbSympId;
	private String faciPal;
	private int faciPalId;
	private String speechDis;
	private int speechDisId;
	private String selfCare;
	private int selfCareId;
	private String termiDisea;
	private int termiDiseaId;
	private String convulsion;
	private int convulsionId;
	private String anticoagu;
	private int anticoaguId;
	private String basilaris;
	private int basilarisId;
	private String quickRecovSymp;
	private int quickRecovSympId;
	private String mildSymp;
	private int mildSympId;
	private String suggestSav;
	private int suggestSavId;

	private boolean isSymptomInfoLoaded;

	public SymptomsTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		sympSide = "";
		sympSideId = -1;
		limbSymp = "";
		limbSympId = -1;
		faciPal = "";
		faciPalId = -1;
		speechDis = "";
		speechDisId = -1;
		selfCare = "";
		selfCareId = -1;
		termiDisea = "";
		termiDiseaId = -1;
		convulsion = "";
		convulsionId = -1;
		anticoagu = "";
		anticoaguId = -1;
		basilaris = "";
		basilarisId = -1;
		quickRecovSymp = "";
		quickRecovSympId = -1;
		mildSymp = "";
		mildSympId = -1;
		suggestSav = "";
		suggestSavId = -1;

		isSymptomInfoLoaded = false;
	}

	public long newEmptySymptom(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isSymptomInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_SYMPTOMS, null,
				initialValues);
	}

	public int updateSymptom() {
		ContentValues values = new ContentValues();

		if (sympSide != null && sympSide.length() > 0)
			values.put(DataBaseManage.COLUMN_SYMPTOM_SIDE, sympSide);
		if (sympSideId > 0)
			values.put(DataBaseManage.COLUMN_SYMPTOM_SIDE_ID, sympSideId);

		if (limbSymp != null && limbSymp.length() > 0)
			values.put(DataBaseManage.COLUMN_LIMB_SYMPTOMS, limbSymp);
		if (limbSympId > 0)
			values.put(DataBaseManage.COLUMN_LIMB_SYMPTOMS_ID, limbSympId);

		if (faciPal != null && faciPal.length() > 0)
			values.put(DataBaseManage.COLUMN_FACIAL_PALSY_SYMPTOMS, faciPal);
		if (faciPalId > 0)
			values.put(DataBaseManage.COLUMN_FACIAL_PALSY_SYMPTOMS_ID,
					faciPalId);

		if (speechDis != null && speechDis.length() > 0)
			values.put(DataBaseManage.COLUMN_SPEECH_DISORDER, speechDis);
		if (speechDisId > 0)
			values.put(DataBaseManage.COLUMN_SPEECH_DISORDER_ID, speechDisId);

		if (selfCare != null && selfCare.length() > 0)
			values.put(DataBaseManage.COLUMN_CARE_FOR_SELF, selfCare);
		if (selfCareId > 0)
			values.put(DataBaseManage.COLUMN_CARE_FOR_SELF_ID, selfCareId);

		if (termiDisea != null && termiDisea.length() > 0)
			values.put(DataBaseManage.COLUMN_TERMINAL_DISEASE, termiDisea);
		if (termiDiseaId > 0)
			values.put(DataBaseManage.COLUMN_TERMINAL_DISEASE_ID, termiDiseaId);

		if (convulsion != null && convulsion.length() > 0)
			values.put(DataBaseManage.COLUMN_CONVULSIONS, convulsion);
		if (convulsionId > 0)
			values.put(DataBaseManage.COLUMN_CONVULSIONS_ID, convulsionId);

		if (anticoagu != null && anticoagu.length() > 0)
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT, anticoagu);
		if (anticoaguId > 0)
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_ID, anticoaguId);

		if (basilaris != null && basilaris.length() > 0)
			values.put(DataBaseManage.COLUMN_BASILARIS_SYMPTOMS, basilaris);
		if (basilarisId > 0)
			values.put(DataBaseManage.COLUMN_BASILARIS_SYMPTOMS_ID, basilarisId);

		if (quickRecovSymp != null && quickRecovSymp.length() > 0)
			values.put(DataBaseManage.COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS,
					quickRecovSymp);
		if (quickRecovSympId > 0)
			values.put(DataBaseManage.COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS_ID,
					quickRecovSympId);

		if (mildSymp != null && mildSymp.length() > 0)
			values.put(DataBaseManage.COLUMN_MILD_STROKE_SYMPTOMS, mildSymp);
		if (mildSympId > 0)
			values.put(DataBaseManage.COLUMN_MILD_STROKE_SYMPTOMS_ID,
					mildSympId);

		if (suggestSav != null && suggestSav.length() > 0)
			values.put(DataBaseManage.COLUMN_SYMPTOMS_SUGGEST_SAV, suggestSav);
		if (suggestSavId > 0)
			values.put(DataBaseManage.COLUMN_SYMPTOMS_SUGGEST_SAV_ID,
					suggestSavId);

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_SYMPTOMS, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	public void fetchSymptom() throws SQLException {

		Cursor cursor =
			sqliteDB.query(true, DataBaseManage.TABLE_SYMPTOMS, null,
					DataBaseManage.COLUMN_PATIENT_ID + "="
							+ AppViewModel.patientDAO.getPatientId(), null, null,
					null, null, null);

//		sqliteDB.query(true, DataBaseManage.TABLE_SYMPTOMS, new String[] {
//				DataBaseManage.COLUMN_SYMPTOM_SIDE_ID,
//				DataBaseManage.COLUMN_LIMB_SYMPTOMS_ID,
//				DataBaseManage.COLUMN_FACIAL_PALSY_SYMPTOMS_ID,
//				DataBaseManage.COLUMN_SPEECH_DISORDER_ID,
//				DataBaseManage.COLUMN_BASILARIS_SYMPTOMS_ID,
//				DataBaseManage.COLUMN_CONVULSIONS_ID,
//				DataBaseManage.COLUMN_CARE_FOR_SELF_ID,
//				DataBaseManage.COLUMN_ANTICOAGULANT_ID,
//				DataBaseManage.COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS_ID,
//				DataBaseManage.COLUMN_SYMPTOMS_SUGGEST_SAV_ID,
//				DataBaseManage.COLUMN_MILD_STROKE_SYMPTOMS_ID,
//				DataBaseManage.COLUMN_TERMINAL_DISEASE_ID },
//				DataBaseManage.COLUMN_PATIENT_ID + "="
//						+ AppViewModel.patientDAO.getPatientId(), null, null,
//				null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setSympSideId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SYMPTOM_SIDE_ID)));
			setSympSide(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SYMPTOM_SIDE)));
			
			setLimbSympId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_LIMB_SYMPTOMS_ID)));
			setLimbSymp(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_LIMB_SYMPTOMS)));
			
			setFacialPalsyId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FACIAL_PALSY_SYMPTOMS_ID)));
			setFacialPalsy(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FACIAL_PALSY_SYMPTOMS)));
			
			setSpeechDisorderId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SPEECH_DISORDER_ID)));
			setSpeechDisorder(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SPEECH_DISORDER)));
			
			setBasilarisId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BASILARIS_SYMPTOMS_ID)));
			setBasilaris(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BASILARIS_SYMPTOMS)));
			
			setConvulsionId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONVULSIONS_ID)));
			setConvulsion(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONVULSIONS)));
			
			setSelfCareId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARE_FOR_SELF_ID)));
			setSelfCare(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARE_FOR_SELF)));
			
			setAnticoagularId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_ID)));
			setAnticoagular(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT)));
			
			setQuickRecovSympId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS_ID)));
			setQuickRecovSymp(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS)));
			
			setMildSympId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_MILD_STROKE_SYMPTOMS_ID)));
			setMildSymp(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_MILD_STROKE_SYMPTOMS)));
			
			setSuggestSavId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SYMPTOMS_SUGGEST_SAV_ID)));
			setSuggestSav(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_SYMPTOMS_SUGGEST_SAV)));
			
			setTermiDiseaseId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_TERMINAL_DISEASE_ID)));
			setTermiDisease(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_TERMINAL_DISEASE)));
		}
		isSymptomInfoLoaded = true;
	}

	public boolean isSymptomInfoLoaded() {
		return isSymptomInfoLoaded;
	}

	public boolean isSymptomInfoComplete() {
		boolean isComplete = true;
		if (sympSideId <= 0 || limbSympId <= 0 || faciPalId <= 0
				|| speechDisId <= 0 || basilarisId <= 0 || termiDiseaId <= 0
				|| convulsionId <= 0 || quickRecovSympId <= 0
				|| suggestSavId <= 0 || mildSympId <= 0 || selfCareId <= 0
				|| anticoaguId <= 0)
			isComplete = false;
		return isComplete;
	}
	
	public void summarizeContraindications() {
		if (convulsionId == R.id.radio_convulsion_yes)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_1));
		if (quickRecovSympId == R.id.radio_quick_recover_symptoms_yes)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_2));
		if (suggestSavId == R.id.radio_suggest_sav_yes)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_3));
		if (mildSympId == R.id.radio_mild_symptoms_yes)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_4));
		if (selfCareId == R.id.radio_care_for_self_no)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_7));
	}
	
	public void summarizeIndications() {
		if (selfCareId == R.id.radio_care_for_self_yes)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_symptom_care_for_self) + " " + "Yes");
	}

	public void setSympSide(String str) {
		sympSide = str;
	}

	public void setSympSideId(int id) {
		sympSideId = id;
	}

	public String getSympSide() {
		return sympSide;
	}

	public int getSympSideId() {
		return sympSideId;
	}

	public void setLimbSymp(String str) {
		limbSymp = str;
	}

	public void setLimbSympId(int id) {
		limbSympId = id;
	}

	public String getLimbSymp() {
		return limbSymp;
	}

	public int getLimbSympId() {
		return limbSympId;
	}

	public void setFacialPalsy(String str) {
		faciPal = str;
	}

	public void setFacialPalsyId(int id) {
		faciPalId = id;
	}

	public String getFacialPalsy() {
		return faciPal;
	}

	public int getFacialPalsyId() {
		return faciPalId;
	}

	public void setSpeechDisorder(String str) {
		speechDis = str;
	}

	public void setSpeechDisorderId(int id) {
		speechDisId = id;
	}

	public String getSpeechDisorder() {
		return speechDis;
	}

	public int getSpeechDisorderId() {
		return speechDisId;
	}

	public void setSelfCare(String str) {
		selfCare = str;
	}

	public void setSelfCareId(int id) {
		selfCareId = id;
	}

	public String getSelfCare() {
		return selfCare;
	}

	public int getSelfCareId() {
		return selfCareId;
	}

	public void setTermiDisease(String str) {
		termiDisea = str;
	}

	public void setTermiDiseaseId(int id) {
		termiDiseaId = id;
	}

	public String getTermiDisease() {
		return termiDisea;
	}

	public int getTermiDiseaseId() {
		return termiDiseaId;
	}

	public void setConvulsion(String str) {
		convulsion = str;
	}

	public void setConvulsionId(int id) {
		convulsionId = id;
	}

	public String getConvulsion() {
		return convulsion;
	}

	public int getConvulsionId() {
		return convulsionId;
	}

	public void setAnticoagular(String str) {
		anticoagu = str;
	}

	public void setAnticoagularId(int id) {
		anticoaguId = id;
	}

	public String getAnticoagular() {
		return anticoagu;
	}

	public int getAnticoagularId() {
		return anticoaguId;
	}

	public void setBasilaris(String str) {
		basilaris = str;
	}

	public void setBasilarisId(int id) {
		basilarisId = id;
	}

	public String getBasilaris() {
		return basilaris;
	}

	public int getBasilarisId() {
		return basilarisId;
	}

	public void setMildSymp(String str) {
		mildSymp = str;
	}

	public void setMildSympId(int id) {
		mildSympId = id;
	}

	public String getMildSymp() {
		return mildSymp;
	}

	public int getMildSympId() {
		return mildSympId;
	}

	public void setSuggestSav(String str) {
		suggestSav = str;
	}

	public void setSuggestSavId(int id) {
		suggestSavId = id;
	}

	public String getSuggestSav() {
		return suggestSav;
	}

	public int getSuggestSavId() {
		return suggestSavId;
	}

	public void setQuickRecovSymp(String str) {
		quickRecovSymp = str;
	}

	public void setQuickRecovSympId(int id) {
		quickRecovSympId = id;
	}

	public String getQuickRecovSymp() {
		return quickRecovSymp;
	}

	public int getQuickRecovSympId() {
		return quickRecovSympId;
	}
}
