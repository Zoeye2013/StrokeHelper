package fi.etla.strokehelper.data;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ThrombolysisTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private String decision;
	private int decisionId;

	private boolean directThromboDecis;
	private int directDataLater;
	private String directOther;
	
	private boolean isFollowUpFinish;
	private int followUpReady;
	private String followUpFinishOther;
	
	private double totalDose;
	private double bolusDose;
	private double infusionDose;

	private String rrFollowType;
	private int rrFollowTypeId;
	private int rrSystolic15min;
	private int rrDiastolic15min;
	private int rrSystolic30min;
	private int rrDiastolic30min;
	private int rrSystolic1h;
	private int rrDiastolic1h;
	private int rrSystolic2h;
	private int rrDiastolic2h;
	private int rrSystolic24h;
	private int rrDiastolic24h;

	private int controlCtNoVisiSign;
	private int controlCtHyperSign;
	private int controlCtEarlyInfarct;
	private int controlCtBleeding;
	private int controlCtIscheLess;
	private int controlCtIscheMore;
	private int controlCtTumor;
	private int controlCtAbscess;
	private int controlCtOther;

	private boolean isInterrupt;
	private int interrDeterioration;
	private int interrBleeding;
	private int interrAllergy;
	private int interrInfusionFail;
	private int interrAPTT;
	private int interrBTrom;
	private int interrRRUncontrol;
	private String interrOther;

	private boolean isThrombolysisInfoLoaded;

	public ThrombolysisTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
	}

	public void init() {
		decision = "";
		decisionId = -1;
		totalDose = -1;
		bolusDose = -1;
		infusionDose = -1;
		rrFollowType = "";
		rrFollowTypeId = -1;
		rrSystolic15min = 0;
		rrDiastolic15min = 0;
		rrSystolic30min = 0;
		rrDiastolic30min = 0;
		rrSystolic1h = 0;
		rrDiastolic1h = 0;
		rrSystolic2h = 0;
		rrDiastolic2h = 0;
		rrSystolic24h = 0;
		rrDiastolic24h = 0;

		controlCtNoVisiSign = -1;
		controlCtHyperSign = -1;
		controlCtEarlyInfarct = -1;
		controlCtBleeding = -1;
		controlCtIscheLess = -1;
		controlCtIscheMore = -1;
		controlCtTumor = -1;
		controlCtAbscess = -1;
		controlCtOther = -1;

		isInterrupt = false;
		interrDeterioration = -1;
		interrBleeding = -1;
		interrAllergy = -1;
		interrInfusionFail = -1;
		interrAPTT = -1;
		interrBTrom = -1;
		interrRRUncontrol = -1;
		interrOther = "";
		
		directThromboDecis = false;
		directDataLater = -1;
		directOther = "";
		
		isFollowUpFinish = false;
		followUpReady = -1;
		followUpFinishOther = "";

		isThrombolysisInfoLoaded = false;
	}

	public long newEmptyThrombolysis(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isThrombolysisInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_THROMBOLYSIS, null,
				initialValues);
	}

	public int updateThrombolysis() {
		ContentValues values = new ContentValues();
		if (decisionId > 0) {
			values.put(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_ID,
					decisionId);
		}
		if (decision != null && decision.length() > 0) {
			values.put(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION, decision);
		}
		
		if (directThromboDecis) {
			values.put(DataBaseManage.COLUMN_DIRECT_THROMBO_DECISION, 1);
		} else {
			values.put(DataBaseManage.COLUMN_DIRECT_THROMBO_DECISION, 0);
		}
		if (directDataLater >= 0) {
			values.put(DataBaseManage.COLUMN_DIRECT_THROMBO_REASON_LATER,
					directDataLater);
		}
		if (directOther != null && directOther.length() > 0) {
			values.put(DataBaseManage.COLUMN_DIRECT_THROMBO_REASON_OTHER, directOther);
		}
		
		if (isFollowUpFinish) {
			values.put(DataBaseManage.COLUMN_FINISH_FOLLOW_UP, 1);
		} else {
			values.put(DataBaseManage.COLUMN_FINISH_FOLLOW_UP, 0);
		}
		if (followUpReady >= 0) {
			values.put(DataBaseManage.COLUMN_FINISH_FOLLOW_UP_READY,
					followUpReady);
		}
		if (followUpFinishOther != null && followUpFinishOther.length() > 0) {
			values.put(DataBaseManage.COLUMN_FINISH_FOLLOW_UP_OTHER, followUpFinishOther);
		}

		if (totalDose > 0) {
			values.put(DataBaseManage.COLUMN_ACTILYSE_TOTAL_DOSE, totalDose);
		}
		if (bolusDose > 0) {
			values.put(DataBaseManage.COLUMN_ACTILYSE_BOLUS_DOSE, bolusDose);
		}
		if (infusionDose > 0) {
			values.put(DataBaseManage.COLUMN_ACTILYSE_INFUSION_DOSE,
					infusionDose);
		}

		if (rrFollowTypeId > 0) {
			values.put(DataBaseManage.COLUMN_RR_FOLLOW_UP_TYPE_ID,
					rrFollowTypeId);
		}
		if (rrFollowType != null && rrFollowType.length() > 0) {
			values.put(DataBaseManage.COLUMN_RR_FOLLOW_UP_TYPE, rrFollowType);
		}
		if (rrSystolic15min > 0) {
			values.put(DataBaseManage.COLUMN_RR_15_SYSTOLIC, rrSystolic15min);
		}
		if (rrDiastolic15min > 0) {
			values.put(DataBaseManage.COLUMN_RR_15_DIASTOLIC, rrDiastolic15min);
		}
		if (rrSystolic30min > 0) {
			values.put(DataBaseManage.COLUMN_RR_30_SYSTOLIC, rrSystolic30min);
		}
		if (rrDiastolic30min > 0) {
			values.put(DataBaseManage.COLUMN_RR_30_DIASTOLIC, rrDiastolic30min);
		}
		if (rrSystolic1h > 0) {
			values.put(DataBaseManage.COLUMN_RR_1h_SYSTOLIC, rrSystolic1h);
		}
		if (rrDiastolic1h > 0) {
			values.put(DataBaseManage.COLUMN_RR_1h_DIASTOLIC, rrDiastolic1h);
		}
		if (rrSystolic2h > 0) {
			values.put(DataBaseManage.COLUMN_RR_2h_SYSTOLIC, rrSystolic2h);
		}
		if (rrDiastolic2h > 0) {
			values.put(DataBaseManage.COLUMN_RR_2h_DIASTOLIC, rrDiastolic2h);
		}
		if (rrSystolic24h > 0) {
			values.put(DataBaseManage.COLUMN_RR_24h_SYSTOLIC, rrSystolic24h);
		}
		if (rrDiastolic24h > 0) {
			values.put(DataBaseManage.COLUMN_RR_24h_DIASTOLIC, rrDiastolic24h);
		}

		if (controlCtNoVisiSign >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_NO_VISILE_SIGN,
					controlCtNoVisiSign);
		}
		if (controlCtHyperSign >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_HYPER_SIGN,
					controlCtHyperSign);
		}
		if (controlCtEarlyInfarct >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_EARLY_INFARCT_SIGN,
					controlCtEarlyInfarct);
		}
		if (controlCtBleeding >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_BLEEDING,
					controlCtBleeding);
		}
		if (controlCtIscheLess >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_LESS,
					controlCtIscheLess);
		}
		if (controlCtIscheMore >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_MORE,
					controlCtIscheMore);
		}
		if (controlCtAbscess >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_ABSCESS,
					controlCtAbscess);
		}
		if (controlCtTumor >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_TUMOR, controlCtTumor);
		}
		if (controlCtOther >= 0) {
			values.put(DataBaseManage.COLUMN_CONTROL_CT_OTHER, controlCtOther);
		}

		if (isInterrupt) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TREATMENT, 1);
		} else {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TREATMENT, 0);
		}
		if (interrDeterioration >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_DETERIORATION,
					interrDeterioration);
		}
		if (interrBleeding >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_BLEEDING, interrBleeding);
		}
		if (interrAllergy >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_ALLERGY, interrAllergy);
		}
		if (interrInfusionFail >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_INFUSION_FAIL,
					interrInfusionFail);
		}
		if (interrAPTT >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_APPT_LONGER_THAN_60,
					interrAPTT);
		}
		if (interrBTrom >= 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_B_TROM_LESS_THAN_100,
					interrBTrom);
		}
		if (interrRRUncontrol >= 0) {
			values.put(
					DataBaseManage.COLUMN_INTERRUPT_BLOOD_PRESSURE_UNCONTROL,
					interrRRUncontrol);
		}
		if (interrOther != null && interrOther.length() > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_OTHER, interrOther);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_THROMBOLYSIS, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	public void fetchThrombolysis() throws SQLException {

		Cursor cursor =

		sqliteDB.query(true, DataBaseManage.TABLE_THROMBOLYSIS, null,
//				new String[] {
//				DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_ID,
//				DataBaseManage.COLUMN_FINISH_FOLLOW_UP,
//				DataBaseManage.COLUMN_FINISH_FOLLOW_UP_READY,
//				DataBaseManage.COLUMN_FINISH_FOLLOW_UP_OTHER,
//				DataBaseManage.COLUMN_ACTILYSE_TOTAL_DOSE,
//				DataBaseManage.COLUMN_ACTILYSE_BOLUS_DOSE,
//				DataBaseManage.COLUMN_ACTILYSE_INFUSION_DOSE,
//				DataBaseManage.COLUMN_RR_FOLLOW_UP_TYPE_ID,
//				DataBaseManage.COLUMN_RR_15_SYSTOLIC,
//				DataBaseManage.COLUMN_RR_15_DIASTOLIC,
//				DataBaseManage.COLUMN_RR_30_SYSTOLIC,
//				DataBaseManage.COLUMN_RR_30_DIASTOLIC,
//				DataBaseManage.COLUMN_RR_1h_SYSTOLIC,
//				DataBaseManage.COLUMN_RR_1h_DIASTOLIC,
//				DataBaseManage.COLUMN_RR_2h_SYSTOLIC,
//				DataBaseManage.COLUMN_RR_2h_DIASTOLIC,
//				DataBaseManage.COLUMN_RR_24h_SYSTOLIC,
//				DataBaseManage.COLUMN_RR_24h_DIASTOLIC,
//				DataBaseManage.COLUMN_CONTROL_CT_NO_VISILE_SIGN,
//				DataBaseManage.COLUMN_CONTROL_CT_HYPER_SIGN,
//				DataBaseManage.COLUMN_CONTROL_CT_EARLY_INFARCT_SIGN,
//				DataBaseManage.COLUMN_CONTROL_CT_BLEEDING,
//				DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_LESS,
//				DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_MORE,
//				DataBaseManage.COLUMN_CONTROL_CT_TUMOR,
//				DataBaseManage.COLUMN_CONTROL_CT_ABSCESS,
//				DataBaseManage.COLUMN_CONTROL_CT_OTHER,
//				DataBaseManage.COLUMN_INTERRUPT_TREATMENT,
//				DataBaseManage.COLUMN_INTERRUPT_DETERIORATION,
//				DataBaseManage.COLUMN_INTERRUPT_BLEEDING,
//				DataBaseManage.COLUMN_INTERRUPT_ALLERGY,
//				DataBaseManage.COLUMN_INTERRUPT_INFUSION_FAIL,
//				DataBaseManage.COLUMN_INTERRUPT_APPT_LONGER_THAN_60,
//				DataBaseManage.COLUMN_INTERRUPT_B_TROM_LESS_THAN_100,
//				DataBaseManage.COLUMN_INTERRUPT_BLOOD_PRESSURE_UNCONTROL,
//				DataBaseManage.COLUMN_INTERRUPT_OTHER },
				DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setDecisionId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION_ID)));
			setDecision(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_THROMBOLYSIS_DECISION)));

			setDirectThromboDecis(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DIRECT_THROMBO_DECISION)) == 1);
			setDirectDataLater(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DIRECT_THROMBO_REASON_LATER)));
			setDirectOther(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DIRECT_THROMBO_REASON_OTHER)));
			
			setIsFollowUpFinish(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FINISH_FOLLOW_UP)) == 1);
			setFollowUpReady(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FINISH_FOLLOW_UP_READY)));
			setFollowUpFinishOther(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_FINISH_FOLLOW_UP_OTHER)));
			
			setBolusDose(cursor
					.getDouble(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ACTILYSE_BOLUS_DOSE)));
			setInfusionDose(cursor
					.getDouble(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ACTILYSE_INFUSION_DOSE)));
			setTotalDose(cursor
					.getDouble(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_ACTILYSE_TOTAL_DOSE)));

			setRRFollowTypeId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_FOLLOW_UP_TYPE_ID)));
			setRRFollowType(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_FOLLOW_UP_TYPE)));

			setRRSystolic15min(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_15_SYSTOLIC)));
			setRRDiastolic15min(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_15_DIASTOLIC)));

			setRRSystolic30min(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_30_SYSTOLIC)));
			setRRDiastolic30min(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_30_DIASTOLIC)));

			setRRSystolic1h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_1h_SYSTOLIC)));
			setRRDiastolic1h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_1h_DIASTOLIC)));

			setRRSystolic2h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_2h_SYSTOLIC)));
			setRRDiastolic2h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_2h_DIASTOLIC)));

			setRRSystolic24h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_24h_SYSTOLIC)));
			setRRDiastolic24h(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_RR_24h_DIASTOLIC)));

			setControlCtNoVisiSign(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_NO_VISILE_SIGN)));
			setControlCtHyperSign(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_HYPER_SIGN)));
			setControlCtEarlyInfarct(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_EARLY_INFARCT_SIGN)));
			setControlCtBleeding(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_BLEEDING)));
			setControlCtIscheLess(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_LESS)));
			setControlCtIscheMore(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_ISCHEMIA_MORE)));
			setControlCtTumor(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_TUMOR)));
			setControlCtAbscess(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_ABSCESS)));
			setControlCtOther(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CONTROL_CT_OTHER)));

			setIsInterrupt(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TREATMENT)) == 1);
			setInterrDeterioration(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DETERIORATION)));
			setInterrBleeding(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_BLEEDING)));
			setInterrAllergy(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_ALLERGY)));
			setInterrInfusionFail(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_INFUSION_FAIL)));
			setInterrAPTT(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_APPT_LONGER_THAN_60)));
			setInterrBTrom(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_B_TROM_LESS_THAN_100)));
			setInterrRRUncontrol(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_BLOOD_PRESSURE_UNCONTROL)));
			setInterrOther(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_OTHER)));

		}
		isThrombolysisInfoLoaded = true;
	}

	public boolean isThrombolysisInfoLoaded() {
		return isThrombolysisInfoLoaded;
	}

	public boolean hasInterruptReasons() {
		boolean has = false;
		if (interrDeterioration > 0 || interrBleeding > 0 || interrAllergy > 0
				|| interrInfusionFail > 0 || interrAPTT > 0 || interrBTrom > 0
				|| interrRRUncontrol > 0
				|| (interrOther != null && interrOther.length() > 0)) {
			has = true;
		}
		return has;
	}
	
	public String getInterruptReasons(){
		String reason = "";
		if (interrDeterioration > 0)
			reason += AppViewModel.appActivity.getString(interrDeterioration) + ";\n";
		if (interrBleeding > 0)
			reason += AppViewModel.appActivity.getString(interrBleeding) + ";\n";
		if (interrAllergy > 0)
			reason += AppViewModel.appActivity.getString(interrAllergy) + ";\n";
		if (interrInfusionFail > 0)
			reason += AppViewModel.appActivity.getString(interrInfusionFail) + ";\n";
		if (interrAPTT > 0)
			reason += AppViewModel.appActivity.getString(interrAPTT) + ";\n";
		if (interrBTrom > 0)
			reason += AppViewModel.appActivity.getString(interrBTrom) + ";\n";
		if (interrRRUncontrol > 0)
			reason += AppViewModel.appActivity.getString(interrRRUncontrol) + ";\n";
		if (interrOther != null && interrOther.length() > 0){
			reason += interrOther;
		}
		return reason;
	}

	
	public boolean hasFinishFollowUpReasons(){
		boolean has = false;
		if (followUpReady > 0 || (followUpFinishOther != null && followUpFinishOther.length() > 0)) {
			has = true;
		}
		return has;
	}
	
	public String getFinishFollowUpReasons(){
		String reason = "";
		if (followUpReady > 0)
			reason += AppViewModel.appActivity.getString(followUpReady) + ";\n";
		if (followUpFinishOther != null && followUpFinishOther.length() > 0){
			reason += followUpFinishOther;
		}
		return reason;
	}
	
	public boolean hasDirectThromboReasons(){
		boolean has = false;
		if (directDataLater > 0 || (directOther != null && directOther.length() > 0)) {
			has = true;
		}
		return has;
	}
	
	/** For summary page, generate control ct result list */
	public ArrayList<Integer> getControlCtResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();

		if (controlCtNoVisiSign > 0)
			results.add(controlCtNoVisiSign);
		if (controlCtHyperSign > 0)
			results.add(controlCtHyperSign);
		if (controlCtEarlyInfarct > 0)
			results.add(controlCtEarlyInfarct);
		if (controlCtIscheLess > 0)
			results.add(controlCtIscheLess);
		if (controlCtBleeding > 0)
			results.add(controlCtBleeding);
		if (controlCtIscheMore > 0)
			results.add(controlCtIscheMore);
		if (controlCtTumor > 0)
			results.add(controlCtTumor);
		if (controlCtAbscess > 0)
			results.add(controlCtAbscess);
		if (controlCtOther > 0)
			results.add(controlCtOther);

		return results;
	}

	public void setDecision(String str) {
		decision = str;
	}

	public void setDecisionId(int id) {
		decisionId = id;
	}

	public String getDecision() {
		return decision;
	}

	public int getDecisionId() {
		return decisionId;
	}
	
	public void setDirectThromboDecis(boolean is) {
		directThromboDecis = is;
	}

	public boolean getDirectThromboDecis() {
		return directThromboDecis;
	}

	public void setDirectDataLater(int id) {
		directDataLater = id;
	}

	public int getDirectDataLater() {
		return directDataLater;
	}
	
	public void setDirectOther(String str) {
		directOther = str;
	}

	public String getDirectOther() {
		return directOther;
	}
	
	
	public void setIsFollowUpFinish(boolean is) {
		isFollowUpFinish = is;
	}

	public boolean getIsFollowUpFinish() {
		return isFollowUpFinish;
	}

	public void setFollowUpReady(int id) {
		followUpReady = id;
	}

	public int getFollowUpReady() {
		return followUpReady;
	}
	
	public void setFollowUpFinishOther(String str) {
		followUpFinishOther = str;
	}

	public String getFollowUpFinishOther() {
		return followUpFinishOther;
	}

	public void setTotalDose(double dou) {
		totalDose = dou;
	}

	public double getTotalDose() {
		return totalDose;
	}

	public void setBolusDose(double dou) {
		bolusDose = dou;
	}

	public double getBolusDose() {
		return bolusDose;
	}

	public void setInfusionDose(double dou) {
		infusionDose = dou;
	}

	public double getInfusionDose() {
		return infusionDose;
	}

	public void setRRFollowType(String str) {
		rrFollowType = str;
	}

	public void setRRFollowTypeId(int id) {
		rrFollowTypeId = id;
	}

	public String getRRFollowType() {
		return rrFollowType;
	}

	public int getRRFollowTypeId() {
		return rrFollowTypeId;
	}

	public void setRRSystolic15min(int in) {
		rrSystolic15min = in;
	}

	public void setRRDiastolic15min(int in) {
		rrDiastolic15min = in;
	}

	public int getRRSystolic15min() {
		return rrSystolic15min;
	}

	public int getRRDiastolic15min() {
		return rrDiastolic15min;
	}

	public void setRRSystolic30min(int in) {
		rrSystolic30min = in;
	}

	public void setRRDiastolic30min(int in) {
		rrDiastolic30min = in;
	}

	public int getRRSystolic30min() {
		return rrSystolic30min;
	}

	public int getRRDiastolic30min() {
		return rrDiastolic30min;
	}

	public void setRRSystolic1h(int in) {
		rrSystolic1h = in;
	}

	public void setRRDiastolic1h(int in) {
		rrDiastolic1h = in;
	}

	public int getRRSystolic1h() {
		return rrSystolic1h;
	}

	public int getRRDiastolic1h() {
		return rrDiastolic1h;
	}

	public void setRRSystolic2h(int in) {
		rrSystolic2h = in;
	}

	public void setRRDiastolic2h(int in) {
		rrDiastolic2h = in;
	}

	public int getRRSystolic2h() {
		return rrSystolic2h;
	}

	public int getRRDiastolic2h() {
		return rrDiastolic2h;
	}

	public void setRRSystolic24h(int in) {
		rrSystolic24h = in;
	}

	public void setRRDiastolic24h(int in) {
		rrDiastolic24h = in;
	}

	public int getRRSystolic24h() {
		return rrSystolic24h;
	}

	public int getRRDiastolic24h() {
		return rrDiastolic24h;
	}

	public void setControlCtNoVisiSign(int id) {
		controlCtNoVisiSign = id;
	}

	public int getControlCtNoVisiSign() {
		return controlCtNoVisiSign;
	}

	public void setControlCtHyperSign(int id) {
		controlCtHyperSign = id;
	}

	public int getControlCtHyperSign() {
		return controlCtHyperSign;
	}

	public void setControlCtEarlyInfarct(int id) {
		controlCtEarlyInfarct = id;
	}

	public int getControlCtEarlyInfarct() {
		return controlCtEarlyInfarct;
	}

	public void setControlCtBleeding(int id) {
		controlCtBleeding = id;
	}

	public int getControlCtBleeding() {
		return controlCtBleeding;
	}

	public void setControlCtIscheLess(int id) {
		controlCtIscheLess = id;
	}

	public int getControlCtIscheLess() {
		return controlCtIscheLess;
	}

	public void setControlCtIscheMore(int id) {
		controlCtIscheMore = id;
	}

	public int getControlCtIscheMore() {
		return controlCtIscheMore;
	}

	public void setControlCtTumor(int id) {
		controlCtTumor = id;
	}

	public int getControlCtTumor() {
		return controlCtTumor;
	}

	public void setControlCtAbscess(int id) {
		controlCtAbscess = id;
	}

	public int getControlCtAbscess() {
		return controlCtAbscess;
	}

	public void setControlCtOther(int id) {
		controlCtOther = id;
	}

	public int getControlCtOther() {
		return controlCtOther;
	}

	public void setIsInterrupt(boolean is) {
		isInterrupt = is;
	}

	public boolean getIsInterrupt() {
		return isInterrupt;
	}

	public void setInterrDeterioration(int id) {
		interrDeterioration = id;
	}

	public int getInterrDeterioration() {
		return interrDeterioration;
	}

	public void setInterrBleeding(int id) {
		interrBleeding = id;
	}

	public int getInterrBleeding() {
		return interrBleeding;
	}

	public void setInterrAllergy(int id) {
		interrAllergy = id;
	}

	public int getInterrAllergy() {
		return interrAllergy;
	}

	public void setInterrInfusionFail(int id) {
		interrInfusionFail = id;
	}

	public int getInterrInfusionFail() {
		return interrInfusionFail;
	}

	public void setInterrAPTT(int id) {
		interrAPTT = id;
	}

	public int getInterrAPTT() {
		return interrAPTT;
	}

	public void setInterrBTrom(int id) {
		interrBTrom = id;
	}

	public int getInterrBTrom() {
		return interrBTrom;
	}

	public void setInterrRRUncontrol(int id) {
		interrRRUncontrol = id;
	}

	public int getInterrRRUncontrol() {
		return interrRRUncontrol;
	}

	public void setInterrOther(String str) {
		interrOther = str;
	}

	public String getInterrOther() {
		return interrOther;
	}
}
