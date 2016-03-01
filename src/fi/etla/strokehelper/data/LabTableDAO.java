package fi.etla.strokehelper.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LabTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private int rrSystolic;
	private int rrDiastolic;
	private boolean rrTreatDecision;
	private int rrAftSystolic;
	private int rrAftDiastolic;

	private String modiRankinScale;
	private int modiRankinScaleId;

	private int admiCtNoVisiSign;
	private int admiCtHyperSign;
	private int admiCtEarlyInfarct;
	private int admiCtBleeding;
	private int admiCtIscheLess;
	private int admiCtIscheMore;
	private int admiCtTumor;
	private int admiCtAbscess;
	private int admiCtOther;
	private boolean isAdminCtRecommend;

	private String perfusionCt;
	private int perfusionCtId;
	private String ctAngiography;
	private int ctAngiographyId;
	private String perfusionCtResults;
	private int perfusionCtResultsId;
	private String ctAngiographyResults;
	private int ctAngiographyResultsId;
	private boolean isPerfusionCtRecommend;
	private boolean isCtAngiographyRecommend;

	private String perfusionCtWhy;
	private String ctAngiographyWhy;

	private float quickInr;
	private float inr;
	private int aptt;
	private int pTrombai;
	private int bTrom;
	private float bGluc;
	private boolean bGlucTreatDecision;
	private float bGlucAfter;

	private boolean isLabInfoLoaded;

	public LabTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		rrSystolic = 0;
		rrDiastolic = 0;
		rrAftSystolic = 0;
		rrAftDiastolic = 0;
		rrTreatDecision = false;

		modiRankinScale = "";
		modiRankinScaleId = -1;

		admiCtNoVisiSign = -1;
		admiCtHyperSign = -1;
		admiCtEarlyInfarct = -1;
		admiCtBleeding = -1;
		admiCtIscheLess = -1;
		admiCtIscheMore = -1;
		admiCtTumor = -1;
		admiCtAbscess = -1;
		admiCtOther = -1;
		isAdminCtRecommend = false;

		perfusionCt = "";
		perfusionCtId = -1;
		ctAngiography = "";
		ctAngiographyId = -1;
		perfusionCtResults = "";
		perfusionCtResultsId = -1;
		ctAngiographyResults = "";
		ctAngiographyResultsId = -1;
		isPerfusionCtRecommend = false;
		isCtAngiographyRecommend = false;

		perfusionCtWhy = "";
		ctAngiographyWhy = "";

		quickInr = 0;
		inr = 0;
		aptt = 0;
		pTrombai = 0;
		bTrom = 0;
		bGluc = 0;
		bGlucAfter = 0;
		bGlucTreatDecision = false;

		isLabInfoLoaded = false;
	}

	public long newEmptyLab(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isLabInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_LAB, null, initialValues);
	}

	public int updateLab() {
		ContentValues values = new ContentValues();

		if (rrSystolic > 0) {
			values.put(DataBaseManage.COLUMN_RR_SYSTOLIC, rrSystolic);
		}
		if (rrDiastolic > 0) {
			values.put(DataBaseManage.COLUMN_RR_DIASTOLIC, rrDiastolic);
		}
		if (rrAftSystolic > 0) {
			values.put(DataBaseManage.COLUMN_RR_AFTER_SYSTOLIC, rrAftSystolic);
		}
		if (rrAftDiastolic > 0) {
			values.put(DataBaseManage.COLUMN_RR_AFTER_DIASTOLIC, rrAftDiastolic);
		}
		if (rrTreatDecision == true) {
			values.put(DataBaseManage.COLUMN_RR_TREAT_DECISION, 1);
		} else if (rrTreatDecision == false) {
			values.put(DataBaseManage.COLUMN_RR_TREAT_DECISION, 0);
		}

		if (modiRankinScaleId > 0) {
			values.put(DataBaseManage.COLUMN_MODIFIED_RANKIN_SCALE_ID,
					modiRankinScaleId);
		}
		if (modiRankinScale != null && modiRankinScale.length() > 0) {
			values.put(DataBaseManage.COLUMN_MODIFIED_RANKIN_SCALE,
					modiRankinScale);
		}

		if (isAdminCtRecommend) {
			values.put(DataBaseManage.COLUMN_IS_ADMIS_CT_RECOMMEND, 1);
		} else if (!isAdminCtRecommend) {
			values.put(DataBaseManage.COLUMN_IS_ADMIS_CT_RECOMMEND, 0);
		}
		if (admiCtNoVisiSign >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_NO_VISILE_SIGN,
					admiCtNoVisiSign);
		}
		if (admiCtHyperSign >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_HYPER_SIGN,
					admiCtHyperSign);
		}
		if (admiCtEarlyInfarct >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_EARLY_INFARCT_SIGN,
					admiCtEarlyInfarct);
		}
		if (admiCtBleeding >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_BLEEDING, admiCtBleeding);
		}
		if (admiCtIscheLess >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_LESS,
					admiCtIscheLess);
		}
		if (admiCtIscheMore >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_MORE,
					admiCtIscheMore);
		}
		if (admiCtAbscess >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_ABSCESS, admiCtAbscess);
		}
		if (admiCtTumor >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_TUMOR, admiCtTumor);
		}
		if (admiCtOther >= 0) {
			values.put(DataBaseManage.COLUMN_ADMIS_CT_OTHER, admiCtOther);
		}

		if (isPerfusionCtRecommend) {
			values.put(DataBaseManage.COLUMN_IS_PERFUSION_CT_RECOMMEND, 1);
		} else if (!isPerfusionCtRecommend) {
			values.put(DataBaseManage.COLUMN_IS_PERFUSION_CT_RECOMMEND, 0);
		}
		if (perfusionCtId > 0) {
			values.put(DataBaseManage.COLUMN_PERFUSION_CT_ID, perfusionCtId);
		}
		if (perfusionCt != null && perfusionCt.length() > 0) {
			values.put(DataBaseManage.COLUMN_PERFUSION_CT, perfusionCt);
		}

		if (perfusionCtResultsId > -1) {
			values.put(DataBaseManage.COLUMN_PERFUSION_CT_RESULTS_ID,
					perfusionCtResultsId);
		}
		if (perfusionCtResults != null) {
			values.put(DataBaseManage.COLUMN_PERFUSION_CT_RESULTS,
					perfusionCtResults);
		}

		if (perfusionCtWhy != null) {
			values.put(DataBaseManage.COLUMN_PERFUSION_CT_NOT_DONE_WHY,
					perfusionCtWhy);
		}

		if (isCtAngiographyRecommend) {
			values.put(DataBaseManage.COLUMN_IS_CT_ANGIOGRAPHY_RECOMMEND, 1);
		} else if (!isCtAngiographyRecommend) {
			values.put(DataBaseManage.COLUMN_IS_CT_ANGIOGRAPHY_RECOMMEND, 0);
		}
		if (ctAngiographyId > 0) {
			values.put(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_ID, ctAngiographyId);
		}
		if (ctAngiography != null && ctAngiography.length() > 0) {
			values.put(DataBaseManage.COLUMN_CT_ANGIOGRAPHY, ctAngiography);
		}

		if (ctAngiographyResultsId > -1) {
			values.put(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_RESULTS_ID,
					ctAngiographyResultsId);
		}
		if (ctAngiographyResults != null) {
			values.put(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_RESULTS,
					ctAngiographyResults);
		}
		if (ctAngiographyWhy != null) {
			values.put(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_NOT_DONE_WHY,
					ctAngiographyWhy);
		}

		if (quickInr > 0) {
			values.put(DataBaseManage.COLUMN_QUICK_INR, quickInr);
		}
		if (inr > 0) {
			values.put(DataBaseManage.COLUMN_INR, inr);
		}
		if (aptt > 0) {
			values.put(DataBaseManage.COLUMN_APTT, aptt);
		}
		if (pTrombai > 0) {
			values.put(DataBaseManage.COLUMN_P_TROMBAI, pTrombai);
		}
		if (bTrom > 0) {
			values.put(DataBaseManage.COLUMN_B_TROM, bTrom);
		}
		if (bGluc > 0) {
			values.put(DataBaseManage.COLUMN_B_GLUC, bGluc);
		}
		if (bGlucAfter > 0) {
			values.put(DataBaseManage.COLUMN_B_GLUC_AFTER_BALANCING, bGlucAfter);
		}
		if (bGlucTreatDecision == true) {
			values.put(DataBaseManage.COLUMN_B_GLUC_TREAT_DECISION, 1);
		} else if (bGlucTreatDecision == false) {
			values.put(DataBaseManage.COLUMN_B_GLUC_TREAT_DECISION, 0);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_LAB, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	public void fetchLab() throws SQLException {

		Cursor cursor = sqliteDB.query(true, DataBaseManage.TABLE_LAB, null,
				DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);

		// sqliteDB.query(true, DataBaseManage.TABLE_LAB, new String[] {
		// DataBaseManage.COLUMN_RR_SYSTOLIC,
		// DataBaseManage.COLUMN_RR_DIASTOLIC,
		// DataBaseManage.COLUMN_RR_AFTER_SYSTOLIC,
		// DataBaseManage.COLUMN_RR_AFTER_DIASTOLIC,
		// DataBaseManage.COLUMN_MODIFIED_RANKIN_SCALE_ID,
		// DataBaseManage.COLUMN_IS_ADMIS_CT_RECOMMEND,
		// DataBaseManage.COLUMN_ADMIS_CT_NO_VISILE_SIGN,
		// DataBaseManage.COLUMN_ADMIS_CT_HYPER_SIGN,
		// DataBaseManage.COLUMN_ADMIS_CT_EARLY_INFARCT_SIGN,
		// DataBaseManage.COLUMN_ADMIS_CT_BLEEDING,
		// DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_LESS,
		// DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_MORE,
		// DataBaseManage.COLUMN_ADMIS_CT_TUMOR,
		// DataBaseManage.COLUMN_ADMIS_CT_ABSCESS,
		// DataBaseManage.COLUMN_ADMIS_CT_OTHER,
		// DataBaseManage.COLUMN_IS_PERFUSION_CT_RECOMMEND,
		// DataBaseManage.COLUMN_PERFUSION_CT_ID,
		// DataBaseManage.COLUMN_PERFUSION_CT_RESULTS_ID,
		// DataBaseManage.COLUMN_IS_CT_ANGIOGRAPHY_RECOMMEND,
		// DataBaseManage.COLUMN_CT_ANGIOGRAPHY_ID,
		// DataBaseManage.COLUMN_CT_ANGIOGRAPHY_RESULTS_ID,
		// DataBaseManage.COLUMN_PERFUSION_CT_NOT_DONE_WHY,
		// DataBaseManage.COLUMN_CT_ANGIOGRAPHY_NOT_DONE_WHY,
		// DataBaseManage.COLUMN_QUICK_INR, DataBaseManage.COLUMN_INR,
		// DataBaseManage.COLUMN_APTT, DataBaseManage.COLUMN_P_TROMBAI,
		// DataBaseManage.COLUMN_B_TROM, DataBaseManage.COLUMN_B_GLUC,
		// DataBaseManage.COLUMN_B_GLUC_AFTER_BALANCING },
		// DataBaseManage.COLUMN_PATIENT_ID + "="
		// + AppViewModel.patientDAO.getPatientId(), null, null,
		// null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();

			setRRSystolic(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_SYSTOLIC)));
			setRRDiastolic(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_DIASTOLIC)));
			setRRAftSystolic(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_AFTER_SYSTOLIC)));
			setRRAftDiastolic(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_AFTER_DIASTOLIC)));
			setRRTreatDecision(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_TREAT_DECISION)) == 1);

			setModiRankinScaleId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_MODIFIED_RANKIN_SCALE_ID)));
			setModiRankinScale(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_MODIFIED_RANKIN_SCALE)));

			setAdmiCtNoVisiSign(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_NO_VISILE_SIGN)));
			setAdmiCtHyperSign(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_HYPER_SIGN)));
			setAdmiCtEarlyInfarct(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_EARLY_INFARCT_SIGN)));
			setAdmiCtBleeding(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_BLEEDING)));
			setAdmiCtIscheLess(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_LESS)));
			setAdmiCtIscheMore(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_ISCHEMIA_MORE)));
			setAdmiCtTumor(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_TUMOR)));
			setAdmiCtAbscess(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_ABSCESS)));
			setAdmiCtOther(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ADMIS_CT_OTHER)));

			setPerfusionCtId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_PERFUSION_CT_ID)));
			setPerfusionCt(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_PERFUSION_CT)));

			setPerfusionCtResultsId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_PERFUSION_CT_RESULTS_ID)));
			setPerfusionCtResults(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_PERFUSION_CT_RESULTS)));

			setCtAngiographyId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_ID)));
			setCtAngiography(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_ANGIOGRAPHY)));

			setCtAngiographyResultsId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_RESULTS_ID)));
			setCtAngiographyResults(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_RESULTS)));

			setPerfusionCtWhy(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_PERFUSION_CT_NOT_DONE_WHY)));
			setCtAngiographyWhy(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CT_ANGIOGRAPHY_NOT_DONE_WHY)));

			setQuickInr(cursor.getFloat(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_QUICK_INR)));
			setInr(cursor.getFloat(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_INR)));
			setAPTT(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_APTT)));
			setPTrombai(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_P_TROMBAI)));
			setBTrom(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_B_TROM)));
			setBGluc(cursor.getFloat(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_B_GLUC)));
			setBGlucTreatDecision(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_B_GLUC_TREAT_DECISION)) == 1);
			setBGlucAfter(cursor
					.getFloat(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_B_GLUC_AFTER_BALANCING)));
		}
		isLabInfoLoaded = true;
	}

	public boolean isLabInfoLoaded() {
		return isLabInfoLoaded;
	}

	public boolean isLabInfoComplete() {
		boolean isComplete = true;
		if (modiRankinScaleId <= 0
				|| rrSystolic <= 0
				|| rrDiastolic <= 0
				|| (admiCtNoVisiSign <= 0 && admiCtHyperSign <= 0
						&& admiCtEarlyInfarct <= 0 && admiCtBleeding <= 0
						&& admiCtIscheLess <= 0 && admiCtIscheMore <= 0
						&& admiCtTumor <= 0 && admiCtAbscess <= 0 && admiCtOther <= 0)
				|| perfusionCtId <= 0
				|| ctAngiographyId <= 0
				|| (perfusionCtId == R.id.radio_persusion_ct_done && perfusionCtResultsId <= 0)
				|| (ctAngiographyId == R.id.radio_ct_angiography_done && ctAngiographyResultsId <= 0)
				|| quickInr <= 0
				|| (quickInr >= 1.5 && quickInr <= 1.7 && inr <= 0)
				|| aptt <= 0 || pTrombai <= 0 || bTrom <= 0 || bGluc <= 0
				|| (bGluc < 2.8 && bGlucAfter <= 0))
			isComplete = false;
		return isComplete;
	}

	public void summarizeContraindications() {
		if (rrTreatDecision == false
				&& (rrSystolic >= 185 || rrDiastolic >= 110))
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_3));
		if (rrTreatDecision == true
				&& (rrAftSystolic >= 185 || rrAftDiastolic >= 110))
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_4));
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_5)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_5));
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_6)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_6));
		if (admiCtIscheMore > 0)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_7));
		if (admiCtBleeding > 0)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_8));
		if (admiCtTumor > 0)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_9));
		if (admiCtAbscess > 0)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_10));
		if (admiCtOther > 0)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_11));
		if (admiCtNoVisiSign > 0
				&& AppViewModel.symptomDAO.getSuggestSavId() == R.id.radio_suggest_sav_yes)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_12));
		if (AppViewModel.interruptionDAO.getDecisCTs() && admiCtNoVisiSign > 0) {
			if (AppViewModel.symptomDAO.getQuickRecovSympId() == R.id.radio_quick_recover_symptoms_yes) {
				if (perfusionCtResultsId == R.id.radio_done_persusion_ct_3
						&& ctAngiographyResultsId == R.id.radio_done_ct_angiography_4)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_13));
				if (perfusionCtResultsId == R.id.radio_done_persusion_ct_3
						&& ctAngiographyId == R.id.radio_ct_angiography_not_done)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_15));
				if (perfusionCtId == R.id.radio_persusion_ct_not_done
						&& ctAngiographyResultsId == R.id.radio_done_ct_angiography_4)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_17));
				if (perfusionCtId == R.id.radio_persusion_ct_not_done
						&& ctAngiographyId == R.id.radio_ct_angiography_not_done)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_19));
			}
			if (AppViewModel.symptomDAO.getMildSympId() == R.id.radio_mild_symptoms_yes) {
				if (perfusionCtResultsId == R.id.radio_done_persusion_ct_3
						&& ctAngiographyResultsId == R.id.radio_done_ct_angiography_4)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_14));
				if (perfusionCtResultsId == R.id.radio_done_persusion_ct_3
						&& ctAngiographyId == R.id.radio_ct_angiography_not_done)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_16));
				if (perfusionCtId == R.id.radio_persusion_ct_not_done
						&& ctAngiographyResultsId == R.id.radio_done_ct_angiography_4)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_18));
				if (perfusionCtId == R.id.radio_persusion_ct_not_done
						&& ctAngiographyId == R.id.radio_ct_angiography_not_done)
					AppViewModel.contras.add(AppViewModel.appActivity
							.getString(R.string.info_contraindications_20));
			}
		}

		if (quickInr > 1.7)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_21)
					+ " ("
					+ quickInr + ")");
		if (inr > 1.7)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_22)
					+ " ("
					+ inr
					+ ")");
		if (aptt > 60)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_23)
					+ " ("
					+ aptt + ")");
		if (aptt >= 34 && aptt <= 60)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_24)
					+ " ("
					+ aptt + ")");
		if (pTrombai >= 26)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_25)
					+ " ("
					+ pTrombai + ")");
		if (bTrom > 0 && bTrom < 100)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_26)
					+ " ("
					+ bTrom + ")");
		if (bGlucTreatDecision == false && bGluc > 0 && bGluc < 2.799999)
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_27));
		
		if (bGlucTreatDecision == true
				&& (bGlucAfter >0 && bGlucAfter < 2.799999))
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_29));
		
		if (bGlucTreatDecision == true && AppViewModel.interruptionDAO.getReasonBGlucAft().contains(AppViewModel.appActivity.getString(R.string.reason_interr_bgluc_after_disappear))){
			AppViewModel.contras.add(AppViewModel.appActivity
					.getString(R.string.info_contraindications_28));
		}

		/** Relative */
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_3)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_8));
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_4)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_9));
		if (perfusionCtId == R.id.radio_persusion_ct_done
				&& perfusionCtResultsId == R.id.radio_done_persusion_ct_3) {
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_10));
		}
		if (ctAngiographyId == R.id.radio_ct_angiography_done
				&& ctAngiographyResultsId == R.id.radio_done_ct_angiography_4) {
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_11));
		}

		if (quickInr >= 1.5 && quickInr <= 1.7)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_12)
					+ " ("
					+ quickInr + ")");
		if (bGluc > 10 || bGlucAfter > 10)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_13)
					+ " ("
					+ bGluc + ")");
	}
	
	public void summarizeIndications() {
		if (admiCtNoVisiSign > 0)
			AppViewModel.criticalIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_admission_ct) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_admission_ct_no_visible_sign));
		if (admiCtHyperSign > 0)
			AppViewModel.criticalIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_admission_ct) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_admission_ct_hyper_sign));
		if (admiCtEarlyInfarct> 0)
			AppViewModel.criticalIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_admission_ct) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_admission_ct_early_infarct_sign));
		if (admiCtIscheLess > 0)
			AppViewModel.criticalIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_admission_ct) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_admission_ct_ischemia_less));
		
		if (perfusionCtId == R.id.radio_persusion_ct_done){
			switch(perfusionCtResultsId){
			case R.id.radio_done_persusion_ct_1:
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_lab_perfusion_ct) + " " + 
						AppViewModel.appActivity
						.getString(R.string.radio_done_perfusion_ct_1));
				break;
			case R.id.radio_done_persusion_ct_2:
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_lab_perfusion_ct) + " " + 
						AppViewModel.appActivity
						.getString(R.string.radio_done_perfusion_ct_2));
				break;
			}
		}
		
		if (ctAngiographyId == R.id.radio_ct_angiography_done){
			switch(ctAngiographyResultsId){
			case R.id.radio_done_ct_angiography_1:
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_lab_ct_angiography) + " " + 
						AppViewModel.appActivity
						.getString(R.string.radio_done_ct_angiography_1));
				break;
			case R.id.radio_done_ct_angiography_2:
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_lab_ct_angiography) + " " + 
						AppViewModel.appActivity
						.getString(R.string.radio_done_ct_angiography_2));
				break;
			case R.id.radio_done_ct_angiography_3:
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_lab_ct_angiography) + " " + 
						AppViewModel.appActivity
						.getString(R.string.radio_done_ct_angiography_3));
				break;
			}
		}
		
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_0)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_modified_rankin_scale) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_modified_rankin_scale_0));
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_1)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_modified_rankin_scale) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_modified_rankin_scale_1));
		if (modiRankinScaleId == R.id.radio_modified_rankin_scale_2)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_modified_rankin_scale) + " " + 
					AppViewModel.appActivity
					.getString(R.string.radio_modified_rankin_scale_2));
		
		if (quickInr > 0 && quickInr <= 1.7)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_quick_inr)
					+ " ² 1.7");
		if (inr > 0 && inr <= 1.7)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_inr)
					+ " ² 1.7");
		if (aptt > 0 && aptt <= 60)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_aptt)
					+ " ² 60");
		
		if (bTrom >= 100)
			AppViewModel.supportiveIndications.add(AppViewModel.appActivity
					.getString(R.string.info_lab_b_trom)
					+ " ³ 100");
	}

	/** For summary page, generate admission ct result list */
	public ArrayList<Integer> getAdmissionCtResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();

		if (admiCtNoVisiSign > 0)
			results.add(admiCtNoVisiSign);
		if (admiCtHyperSign > 0)
			results.add(admiCtHyperSign);
		if (admiCtEarlyInfarct > 0)
			results.add(admiCtEarlyInfarct);
		if (admiCtIscheLess > 0)
			results.add(admiCtIscheLess);
		if (admiCtBleeding > 0)
			results.add(admiCtBleeding);
		if (admiCtIscheMore > 0)
			results.add(admiCtIscheMore);
		if (admiCtTumor > 0)
			results.add(admiCtTumor);
		if (admiCtAbscess > 0)
			results.add(admiCtAbscess);
		if (admiCtOther > 0)
			results.add(admiCtOther);

		return results;
	}

	/** For summary page, generate perfusion ct result string */
	public String getPerfusionCtResultForSummary() {
		String result = null;
		if (perfusionCtId > 0) {
			switch (perfusionCtId) {
			case R.id.radio_persusion_ct_not_done:
				result = AppViewModel.appActivity
						.getString(R.string.info_lab_ct_not_done);
				break;
			case R.id.radio_persusion_ct_done:
				if (perfusionCtResultsId > 0) {
					result = AppViewModel.appActivity
							.getString(R.string.info_lab_ct_done);
					result += " ("
							+ AppViewModel.labDAO.getPerfusionCtResults() + ")";
				}
				break;
			}
		}
		return result;
	}

	/** For summary page, generate ct angiography result string */
	public String getCtAngiographyResultForSummary() {
		String result = null;
		if (ctAngiographyId > 0) {
			switch (ctAngiographyId) {
			case R.id.radio_ct_angiography_not_done:
				result = AppViewModel.appActivity
						.getString(R.string.info_lab_ct_not_done);
				break;
			case R.id.radio_ct_angiography_done:
				if (ctAngiographyResultsId > 0) {
					result = AppViewModel.appActivity
							.getString(R.string.info_lab_ct_done);
					result += " ("
							+ AppViewModel.labDAO.getCtAngiographyResults()
							+ ")";
				}
				break;
			}
		}
		return result;
	}

	public void setRRSystolic(int id) {
		rrSystolic = id;
	}

	public void setRRDiastolic(int in) {
		rrDiastolic = in;
	}

	public void setRRAftDiastolic(int id) {
		rrAftDiastolic = id;
	}

	public void setRRAftSystolic(int id) {
		rrAftSystolic = id;
	}

	public void setRRTreatDecision(boolean decis) {
		rrTreatDecision = decis;
	}

	public int getRRSystolic() {
		return rrSystolic;
	}

	public int getRRAftDiastolic() {
		return rrAftDiastolic;
	}

	public int getRRDiastolic() {
		return rrDiastolic;
	}

	public int getRRAftSystolic() {
		return rrAftSystolic;
	}

	public boolean getRRTreatDecision() {
		return rrTreatDecision;
	}

	public void setModiRankinScale(String str) {
		modiRankinScale = str;
	}

	public void setModiRankinScaleId(int id) {
		modiRankinScaleId = id;
	}

	public String getModiRankinScale() {
		return modiRankinScale;
	}

	public int getModiRankinScaleId() {
		return modiRankinScaleId;
	}

	public void setPerfusionCt(String str) {
		perfusionCt = str;
	}

	public void setPerfusionCtId(int id) {
		perfusionCtId = id;
	}

	public String getPerfusionCt() {
		return perfusionCt;
	}

	public int getPerfusionCtId() {
		return perfusionCtId;
	}

	public void setCtAngiography(String str) {
		ctAngiography = str;
	}

	public void setCtAngiographyId(int id) {
		ctAngiographyId = id;
	}

	public String getCtAngiography() {
		return ctAngiography;
	}

	public int getCtAngiographyId() {
		return ctAngiographyId;
	}

	public void setPerfusionCtResults(String str) {
		perfusionCtResults = str;
	}

	public void setPerfusionCtResultsId(int id) {
		perfusionCtResultsId = id;
	}

	public String getPerfusionCtResults() {
		return perfusionCtResults;
	}

	public int getPerfusionCtResultsId() {
		return perfusionCtResultsId;
	}

	public void setCtAngiographyResults(String str) {
		ctAngiographyResults = str;
	}

	public void setCtAngiographyResultsId(int id) {
		ctAngiographyResultsId = id;
	}

	public String getCtAngiographyResults() {
		return ctAngiographyResults;
	}

	public int getCtAngiographyResultsId() {
		return ctAngiographyResultsId;
	}

	public void setPerfusionCtWhy(String str) {
		perfusionCtWhy = str;
	}

	public String getPerfusionCtWhy() {
		return perfusionCtWhy;
	}

	public void setCtAngiographyWhy(String str) {
		ctAngiographyWhy = str;
	}

	public String getCtAngiographyWhy() {
		return ctAngiographyWhy;
	}

	public void setIsPerfusionCtRecommend(boolean is) {
		isPerfusionCtRecommend = is;
	}

	public boolean getIsPerfusionCtRecommend() {
		return isPerfusionCtRecommend;
	}

	public void setIsCtAngiographyRecommend(boolean is) {
		isCtAngiographyRecommend = is;
	}

	public boolean getIsCtAngiographyRecommend() {
		return isCtAngiographyRecommend;
	}

	public void setAdmiCtNoVisiSign(int id) {
		admiCtNoVisiSign = id;
	}

	public int getAdmiCtNoVisiSign() {
		return admiCtNoVisiSign;
	}

	public void setAdmiCtHyperSign(int id) {
		admiCtHyperSign = id;
	}

	public int getAdmiCtHyperSign() {
		return admiCtHyperSign;
	}

	public void setAdmiCtEarlyInfarct(int id) {
		admiCtEarlyInfarct = id;
	}

	public int getAdmiCtEarlyInfarct() {
		return admiCtEarlyInfarct;
	}

	public void setAdmiCtBleeding(int id) {
		admiCtBleeding = id;
	}

	public int getAdmiCtBleeding() {
		return admiCtBleeding;
	}

	public void setAdmiCtIscheLess(int id) {
		admiCtIscheLess = id;
	}

	public int getAdmiCtIscheLess() {
		return admiCtIscheLess;
	}

	public void setAdmiCtIscheMore(int id) {
		admiCtIscheMore = id;
	}

	public int getAdmiCtIscheMore() {
		return admiCtIscheMore;
	}

	public void setAdmiCtTumor(int id) {
		admiCtTumor = id;
	}

	public int getAdmiCtTumor() {
		return admiCtTumor;
	}

	public void setAdmiCtAbscess(int id) {
		admiCtAbscess = id;
	}

	public int getAdmiCtAbscess() {
		return admiCtAbscess;
	}

	public void setAdmiCtOther(int id) {
		admiCtOther = id;
	}

	public int getAdmiCtOther() {
		return admiCtOther;
	}

	public void setIsAdminCtRecommend(boolean is) {
		isAdminCtRecommend = is;
	}

	public boolean getIsAdminCtRecommend() {
		return isAdminCtRecommend;
	}

	public void setQuickInr(float f) {
		quickInr = f;
	}

	public void setInr(float f) {
		inr = f;
	}

	public void setAPTT(int in) {
		aptt = in;
	}

	public void setPTrombai(int in) {
		pTrombai = in;
	}

	public void setBTrom(int in) {
		bTrom = in;
	}

	public void setBGluc(float f) {
		bGluc = f;
	}

	public void setBGlucAfter(float f) {
		bGlucAfter = f;
	}

	public void setBGlucTreatDecision(boolean decis) {
		bGlucTreatDecision = decis;
	}

	public float getQuickInr() {
		return quickInr;
	}

	public float getInr() {
		return inr;
	}

	public int getAPTT() {
		return aptt;
	}

	public int getPTrombai() {
		return pTrombai;
	}

	public int getBTrom() {
		return bTrom;
	}

	public float getBGluc() {
		return bGluc;
	}

	public float getBGlucAfter() {
		return bGlucAfter;
	}

	public boolean getBGlucTreatDecision() {
		return bGlucTreatDecision;
	}

}
