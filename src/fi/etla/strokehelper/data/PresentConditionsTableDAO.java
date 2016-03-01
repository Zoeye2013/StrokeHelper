package fi.etla.strokehelper.data;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.main.MainActivity;

public class PresentConditionsTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private String activeBleed;
	private int activeBleedId;
	private String anticoaTreat;
	private int anticoaTreatId;
	private String anticoaHeparin;
	private int anticoaHeparinId;
	private String anticoaDabigatran;
	private int anticoaDabigatranId;
	private String anticoaApiksaban;
	private int anticoaApiksabanId;
	private String cardiovascular;
	private int cardiovascularId;
	private String obstetric;
	private int obstetricId;
	private String other;
	private int otherId;

	private int endocarditis;
	private int pericarditis;
	private int embolus;
	private int cardiOtherCri;
	private int cardiOtherNotCri;

	private int pregTrimester;
	private int placenta;
	private int obsteOtherRelatCri;
	private int obsteOtherNotCri;

	private boolean isPresentConInfoLoaded;

	public PresentConditionsTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		activeBleed = "";
		anticoaTreat = "";
		anticoaHeparin = "";
		anticoaDabigatran = "";
		anticoaApiksaban = "";
		cardiovascular = "";
		obstetric = "";
		other = "";

		activeBleedId = -1;
		anticoaTreatId = -1;
		anticoaHeparinId = -1;
		anticoaDabigatranId = -1;
		anticoaApiksabanId = -1;
		cardiovascularId = -1;
		obstetricId = -1;
		otherId = -1;

		endocarditis = -1;
		pericarditis = -1;
		embolus = -1;
		cardiOtherCri = -1;
		cardiOtherNotCri = -1;

		pregTrimester = -1;
		placenta = -1;
		obsteOtherRelatCri = -1;
		obsteOtherNotCri = -1;

		isPresentConInfoLoaded = false;
	}

	public long newEmptyPresentCondition(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isPresentConInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_PRESENT_CONDITIONS, null,
				initialValues);
	}

	public int updatePresentCondition() {
		ContentValues values = new ContentValues();

		if (activeBleed != null && activeBleed.length() > 0) {
			values.put(DataBaseManage.COLUMN_ACTIVE_BLEED, activeBleed);
		}
		if (activeBleedId > 0) {
			values.put(DataBaseManage.COLUMN_ACTIVE_BLEED_ID, activeBleedId);
		}

		if (anticoaTreat != null && anticoaTreat.length() > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_TREAT, anticoaTreat);
		}
		if (anticoaTreatId > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_TREAT_ID,
					anticoaTreatId);
		}

		if (anticoaHeparin != null && anticoaHeparin.length() > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_HEPARIN,
					anticoaHeparin);
		}
		if (anticoaHeparinId > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_HEPARIN_ID,
					anticoaHeparinId);
		}

		if (anticoaDabigatran != null && anticoaDabigatran.length() > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_DABIGATRAN,
					anticoaDabigatran);
		}
		if (anticoaDabigatranId > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_DABIGATRAN_ID,
					anticoaDabigatranId);
		}

		if (anticoaApiksaban != null && anticoaApiksaban.length() > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_APIKSABAN,
					anticoaApiksaban);
		}
		if (anticoaApiksabanId > 0) {
			values.put(DataBaseManage.COLUMN_ANTICOAGULANT_APIKSABAN_ID,
					anticoaApiksabanId);
		}

		if (cardiovascular != null && cardiovascular.length() > 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_INFECTIONS,
					cardiovascular);
		}
		if (cardiovascularId > 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_INFECTIONS_ID,
					cardiovascularId);
		}

		if (obstetric != null && obstetric.length() > 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC, obstetric);
		}
		if (obstetricId > 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC_ID, obstetricId);
		}

		if (other != null && other.length() > 0) {
			values.put(DataBaseManage.COLUMN_OTHER_PRESENT_CRITICAL_CONDITION,
					other);
		}
		if (otherId > 0) {
			values.put(
					DataBaseManage.COLUMN_OTHER_PRESENT_CRITICAL_CONDITION_ID,
					otherId);
		}

		if (endocarditis >= 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_ENDOCARDITIS,
					endocarditis);
		}
		if (pericarditis >= 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_PERICARDITIS,
					pericarditis);
		}
		if (embolus >= 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_EMBOLUS, embolus);
		}
		if (cardiOtherCri >= 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_CRITICAL,
					cardiOtherCri);
		}
		if (cardiOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_NOT_CRITICAL,
					cardiOtherNotCri);
		}

		if (pregTrimester >= 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC_PREGNANCY_TRIMESTER,
					pregTrimester);
		}
		if (placenta >= 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC_PLACENTA_PREVIA,
					placenta);
		}
		if (obsteOtherRelatCri >= 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC_OTHER_RELATIVE_CRITICAL,
					obsteOtherRelatCri);
		}
		if (obsteOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_OBSTETRIC_OTHER_NOT_CRITICAL,
					obsteOtherNotCri);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_PRESENT_CONDITIONS,
					values, DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	public void fetchPresentCondition() throws SQLException {

		Cursor cursor = sqliteDB.query(true,
				DataBaseManage.TABLE_PRESENT_CONDITIONS, null,
				DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);

		// sqliteDB.query(true, DataBaseManage.TABLE_PRESENT_CONDITIONS, new
		// String[] {
		// DataBaseManage.COLUMN_ACTIVE_BLEED_ID,
		// DataBaseManage.COLUMN_ANTICOAGULANT_TREAT_ID,
		// DataBaseManage.COLUMN_ANTICOAGULANT_HEPARIN_ID,
		// DataBaseManage.COLUMN_ANTICOAGULANT_DABIGATRAN_ID,
		// DataBaseManage.COLUMN_ANTICOAGULANT_APIKSABAN_ID,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_INFECTIONS_ID,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_ENDOCARDITIS,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_PERICARDITIS,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_EMBOLUS,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_CRITICAL,
		// DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_OBSTETRIC_ID,
		// DataBaseManage.COLUMN_OBSTETRIC_PREGNANCY_TRIMESTER,
		// DataBaseManage.COLUMN_OBSTETRIC_PLACENTA_PREVIA,
		// DataBaseManage.COLUMN_OBSTETRIC_OTHER_RELATIVE_CRITICAL,
		// DataBaseManage.COLUMN_OBSTETRIC_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_OTHER_PRESENT_CRITICAL_CONDITION_ID},
		// DataBaseManage.COLUMN_PATIENT_ID + "=" +
		// AppViewModel.patientDAO.getPatientId(), null, null, null, null,
		// null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setActiveBleedId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ACTIVE_BLEED_ID)));
			setActiveBleed(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ACTIVE_BLEED)));

			setAnticoaTreatId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_TREAT_ID)));
			setAnticoaTreat(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_TREAT)));

			setAnticoaHeparinId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_HEPARIN_ID)));
			setAnticoaDabigatranId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_DABIGATRAN_ID)));
			setAnticoaApiksabanId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ANTICOAGULANT_APIKSABAN_ID)));

			setCardiovascularId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_INFECTIONS_ID)));
			setCardiovascular(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_INFECTIONS)));

			setEndocarditis(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_ENDOCARDITIS)));
			setPericarditis(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_PERICARDITIS)));
			setEmbolus(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_EMBOLUS)));
			setCardiOtherCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_CRITICAL)));
			setCardiOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CARDIOVASCULAR_OTHER_NOT_CRITICAL)));

			setObstetricId(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC_ID)));
			setObstetric(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC)));

			setPregTrimester(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC_PREGNANCY_TRIMESTER)));
			setPlacenta(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC_PLACENTA_PREVIA)));
			setObsteOtherRelatCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC_OTHER_RELATIVE_CRITICAL)));
			setObsteOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OBSTETRIC_OTHER_NOT_CRITICAL)));

			setOtherId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OTHER_PRESENT_CRITICAL_CONDITION_ID)));
			setOther(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OTHER_PRESENT_CRITICAL_CONDITION)));
		}
		isPresentConInfoLoaded = true;
	}

	public boolean isPresentConInfoLoaded() {
		return isPresentConInfoLoaded;
	}

	public boolean isPresentConInfoComplete() {
		boolean isComplete = true;
		if (activeBleedId <= 0
				|| isAnticoagulantComplete() == false
				|| isCardioComplete() == false
				|| isObstetricComplete() == false
				|| AppViewModel.symptomDAO.getTermiDiseaseId() <= 0
				|| otherId <= 0)
			isComplete = false;
		return isComplete;
	}

	public boolean isAnticoagulantComplete() {
		boolean isComplete = true;
		if (AppViewModel.symptomDAO.getAnticoagularId() <= 0
				|| (AppViewModel.symptomDAO.getAnticoagularId() == R.id.radio_anticoagulant_yes && anticoaTreatId <= 0)
				|| (anticoaTreatId == R.id.radio_anticoagulant_treatment_heparin && anticoaHeparinId <= 0)
				|| (anticoaTreatId == R.id.radio_anticoagulant_treatment_dabigatran && anticoaDabigatranId <= 0)
				|| (anticoaTreatId == R.id.radio_anticoagulant_treatment_apiksaban && anticoaApiksabanId <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}

	public boolean isCardioComplete() {
		boolean isComplete = true;
		if (cardiovascularId <= 0
				|| (cardiovascularId == R.id.radio_cardiovascular_infections_yes
						&& endocarditis <= 0
						&& pericarditis <= 0
						&& embolus <= 0 && cardiOtherCri <= 0 && cardiOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}
	public boolean isObstetricComplete() {
		boolean isComplete = true;
		if (obstetricId <= 0
				|| (obstetricId == R.id.radio_obstetric_contraindications_yes_relative
						&& pregTrimester <= 0
						&& placenta <= 0
						&& obsteOtherRelatCri <= 0 && obsteOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}

	public void summarizeContraindications() {
		if (activeBleedId == R.id.radio_active_bleeding_yes)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_31));
		if (AppViewModel.symptomDAO.getAnticoagularId() == R.id.radio_anticoagulant_yes) {
			if (anticoaHeparinId == R.id.radio_heparin_yes)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_32));
			if (anticoaDabigatranId == R.id.radio_dabigatran_yes) {
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_33));

				int aptt = AppViewModel.labDAO.getAPTT();
				if (aptt >= 34 && aptt <= 60)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_34));
				int pTrombai = AppViewModel.labDAO.getPTrombai();
				if (pTrombai >= 26)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_35));
			}
			if (anticoaApiksabanId == R.id.radio_apiksaban_yes)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_36));
		}
		if (cardiovascularId == R.id.radio_cardiovascular_infections_yes) {
			if (endocarditis > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_37));
			if (pericarditis > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_38));
			if (embolus > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_39));
			if (cardiOtherCri > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_40));
		}
		if (obstetricId == R.id.radio_obstetric_contraindications_hellp)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_41));
		if (AppViewModel.symptomDAO.getTermiDiseaseId() == R.id.radio_terminal_disease_yes)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_42));
		if (otherId == R.id.radio_other_present_condition_yes)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_43));

		/** Relative */
		if (AppViewModel.symptomDAO.getAnticoagularId() == R.id.radio_anticoagulant_yes) {
			if (anticoaHeparinId == R.id.radio_heparin_no)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_14));
			if (anticoaDabigatranId == R.id.radio_dabigatran_no)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_15));
			if (anticoaApiksabanId == R.id.radio_apiksaban_no)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_16));
			if (anticoaTreatId == R.id.radio_anticoagulant_treatment_other)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_17));
		}
		if (obstetricId == R.id.radio_obstetric_contraindications_yes_relative) {
			if (pregTrimester > 0)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_18));
			if (placenta > 0)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_19));
			if (obsteOtherRelatCri > 0)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_20));
		}

	}

	/** For summary page, generate cardiovascular infections result list */
	public ArrayList<Integer> getCardioInfectionResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();

		if (endocarditis > 0)
			results.add(endocarditis);
		if (endocarditis > 0)
			results.add(endocarditis);
		if (embolus > 0)
			results.add(embolus);
		if (cardiOtherCri > 0)
			results.add(cardiOtherCri);
		if (cardiOtherNotCri > 0)
			results.add(cardiOtherNotCri);

		return results;
	}

	/** For summary page, generate obstetric result list */
	public ArrayList<Integer> getObstetricContrasResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();

		if (pregTrimester > 0)
			results.add(pregTrimester);
		if (placenta > 0)
			results.add(placenta);
		if (obsteOtherRelatCri > 0)
			results.add(obsteOtherRelatCri);
		if (obsteOtherNotCri > 0)
			results.add(obsteOtherNotCri);
		return results;
	}

	public void setEndocarditis(int id) {
		endocarditis = id;
	}

	public void setPericarditis(int in) {
		pericarditis = in;
	}

	public void setCardiOtherCri(int id) {
		cardiOtherCri = id;
	}

	public void setEmbolus(int id) {
		embolus = id;
	}

	public int getEndocarditis() {
		return endocarditis;
	}

	public int getCardiOtherCri() {
		return cardiOtherCri;
	}

	public int getPericarditis() {
		return pericarditis;
	}

	public int getEmbolus() {
		return embolus;
	}

	public void setActiveBleed(String str) {
		activeBleed = str;
	}

	public void setActiveBleedId(int id) {
		activeBleedId = id;
	}

	public String getActiveBleed() {
		return activeBleed;
	}

	public int getActiveBleedId() {
		return activeBleedId;
	}

	public void setAnticoaTreat(String str) {
		anticoaTreat = str;
	}

	public void setAnticoaTreatId(int id) {
		anticoaTreatId = id;
	}

	public String getAnticoaTreat() {
		return anticoaTreat;
	}

	public int getAnticoaTreatId() {
		return anticoaTreatId;
	}

	public void setAnticoaHeparin(String str) {
		anticoaHeparin = str;
	}

	public void setAnticoaHeparinId(int id) {
		anticoaHeparinId = id;
	}

	public String getAnticoaHeparin() {
		return anticoaHeparin;
	}

	public int getAnticoaHeparinId() {
		return anticoaHeparinId;
	}

	public void setAnticoaDabigatran(String str) {
		anticoaDabigatran = str;
	}

	public void setAnticoaDabigatranId(int id) {
		anticoaDabigatranId = id;
	}

	public String getAnticoaDabigatran() {
		return anticoaDabigatran;
	}

	public int getAnticoaDabigatranId() {
		return anticoaDabigatranId;
	}

	public void setAnticoaApiksaban(String str) {
		anticoaApiksaban = str;
	}

	public String getAnticoaApiksaban() {
		return anticoaApiksaban;
	}

	public void setAnticoaApiksabanId(int id) {
		anticoaApiksabanId = id;
	}

	public int getAnticoaApiksabanId() {
		return anticoaApiksabanId;
	}

	public void setCardiovascular(String str) {
		cardiovascular = str;
	}

	public String getCardiovascular() {
		return cardiovascular;
	}

	public void setCardiovascularId(int id) {
		cardiovascularId = id;
	}

	public int getCardiovascularId() {
		return cardiovascularId;
	}

	public void setObstetric(String str) {
		obstetric = str;
	}

	public String getObstetric() {
		return obstetric;
	}

	public void setObstetricId(int id) {
		obstetricId = id;
	}

	public int getObstetricId() {
		return obstetricId;
	}

	public void setOther(String str) {
		other = str;
	}

	public String getOther() {
		return other;
	}

	public void setOtherId(int id) {
		otherId = id;
	}

	public int getOtherId() {
		return otherId;
	}

	public void setCardiOtherNotCri(int id) {
		cardiOtherNotCri = id;
	}

	public int getCardiOtherNotCri() {
		return cardiOtherNotCri;
	}

	public void setPregTrimester(int id) {
		pregTrimester = id;
	}

	public int getPregTrimester() {
		return pregTrimester;
	}

	public void setPlacenta(int id) {
		placenta = id;
	}

	public int getPlacenta() {
		return placenta;
	}

	public void setObsteOtherRelatCri(int id) {
		obsteOtherRelatCri = id;
	}

	public int getObsteOtherRelatCri() {
		return obsteOtherRelatCri;
	}

	public void setObsteOtherNotCri(int id) {
		obsteOtherNotCri = id;
	}

	public int getObsteOtherNotCri() {
		return obsteOtherNotCri;
	}
}
