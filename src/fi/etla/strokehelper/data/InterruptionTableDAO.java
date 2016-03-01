package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

public class InterruptionTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private long processInterrTime;
	private String processInterrReason;
	private boolean decisOnset;
	private String reasonOnset;
	private long timeOnset;

	private boolean decisTerIllness;
	private String reasonTerIllness;
	private long timeTerIllness;

	private boolean decisRR;
	private String reasonRR;
	private long timeRR;

	private boolean decisRRAft;
	private String reasonRRAft;
	private long timeRRAft;

	private boolean decisMRS;
	private String reasonMRS;
	private long timeMRS;

	private boolean decisAdminCt;
	private String reasonAdminCt;
	private long timeAdminCt;

	private boolean decisCTs;
	private String reasonCTs;
	private long timeCTs;

	private boolean decisQuickInr;
	private String reasonQuickInr;
	private long timeQuickInr;

	private boolean decisInr;
	private String reasonInr;
	private long timeInr;

	private boolean decisAptt;
	private String reasonAptt;
	private long timeAptt;

	private boolean decisBTrom;
	private String reasonBTrom;
	private long timeBTrom;

	private boolean decisBGluc;
	private String reasonBGluc;
	private long timeBGluc;

	private boolean decisBGlucAft;
	private String reasonBGlucAft;
	private long timeBGlucAft;

	private boolean decisActiveBleed;
	private String reasonActiveBleed;
	private long timeActiveBleed;

	private boolean decisAnticoagulant;
	private String reasonAnticoagulant;
	private long timeAnticoagulant;

	private boolean decisCardiovascular;
	private String reasonCardiovascular;
	private long timeCardiovascular;

	private boolean decisObstetric;
	private String reasonObstetric;
	private long timeObstetric;

	private boolean decisPresentOther;
	private String reasonPresentOther;
	private long timePresentOther;

	private boolean decisCerebrovascular;
	private String reasonCerebrovascular;
	private long timeCerebrovascular;

	private boolean decisDisease;
	private String reasonDisease;
	private long timeDisease;

	private boolean decisBleed;
	private String reasonBleed;
	private long timeBleed;

	private boolean decisOperation;
	private String reasonOperation;
	private long timeOperation;

	private boolean decisVessel;
	private String reasonVessel;
	private long timeVessel;

	private boolean isInterruptionInfoLoaded;

	public InterruptionTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		processInterrReason = "";
		processInterrTime = -1;
		decisOnset = false;
		reasonOnset = "";
		timeOnset = -1;
		decisTerIllness = false;
		reasonTerIllness = "";
		timeTerIllness = -1;
		decisRR = false;
		reasonRR = "";
		timeRR = -1;
		decisRRAft = false;
		reasonRRAft = "";
		timeRRAft = -1;
		decisMRS = false;
		reasonMRS = "";
		timeMRS = -1;
		decisAdminCt = false;
		reasonAdminCt = "";
		timeAdminCt = -1;
		decisCTs = false;
		reasonCTs = "";
		timeCTs = -1;
		decisQuickInr = false;
		reasonQuickInr = "";
		timeQuickInr = -1;
		decisInr = false;
		reasonInr = "";
		timeInr = -1;
		decisAptt = false;
		reasonAptt = "";
		timeAptt = -1;
		decisBTrom = false;
		reasonBTrom = "";
		timeBTrom = -1;
		decisBGluc = false;
		reasonBGluc = "";
		timeBGluc = -1;
		decisBGlucAft = false;
		reasonBGlucAft = "";
		timeBGlucAft = -1;
		decisActiveBleed = false;
		reasonActiveBleed = "";
		timeActiveBleed = -1;
		decisAnticoagulant = false;
		reasonAnticoagulant = "";
		timeAnticoagulant = -1;
		decisObstetric = false;
		reasonCardiovascular = "";
		timeCardiovascular = -1;
		decisCardiovascular = false;
		reasonObstetric = "";
		timeObstetric = -1;
		decisPresentOther = false;
		reasonPresentOther = "";
		timePresentOther = -1;
		decisCerebrovascular = false;
		reasonCerebrovascular = "";
		timeCerebrovascular = -1;
		decisDisease = false;
		reasonDisease = "";
		timeDisease = -1;
		decisBleed = false;
		reasonBleed = "";
		timeBleed = -1;
		decisOperation = false;
		reasonOperation = "";
		timeOperation = -1;
		decisVessel = false;
		reasonVessel = "";
		timeVessel = -1;

		isInterruptionInfoLoaded = false;
	}

	public SQLiteDatabase getSQLdb() {
		return sqliteDB;
	}

	public long newEmptyInterruption(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isInterruptionInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_INTERRUPTION, null,
				initialValues);
	}

	/** For patient page update */
	public int updateInterruption() {
		ContentValues values = new ContentValues();

		if (processInterrTime > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_PROCESS_TIME,
					processInterrTime);
			if (processInterrReason != null && processInterrReason.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_PROCESS_REASON,
						processInterrReason);
			}
		}

		if (timeOnset > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_ONSET, timeOnset);
			if (reasonOnset != null && reasonOnset.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_ONSET,
						reasonOnset);
			}
			if (decisOnset == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_ONSET, 1);
			else if (decisOnset == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_ONSET, 0);
		}

		if (timeTerIllness > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_TERMINAL_ILLNESS,
					timeTerIllness);
			if (reasonTerIllness != null && reasonTerIllness.length() > 0) {
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_REASON_TERMINAL_ILLNESS,
						reasonTerIllness);
			}
			if (decisTerIllness == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_TERMINAL_ILLNESS,
						1);
			else if (decisTerIllness == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_TERMINAL_ILLNESS,
						0);
		}

		if (timeRR > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_RR, timeRR);
			if (reasonRR != null && reasonRR.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_RR, reasonRR);
			}
			if (decisRR == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR, 1);
			else if (decisRR == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR, 0);
		}

		if (timeRRAft > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_RR_AFTER, timeRRAft);
			if (reasonRRAft != null && reasonRRAft.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_RR_AFTER,
						reasonRRAft);
			}
			if (decisRRAft == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR_AFTER, 1);
			else if (decisRRAft == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR_AFTER, 0);
		}

		if (timeMRS > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_mRS, timeMRS);
			if (reasonMRS != null && reasonMRS.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_mRS,
						reasonMRS);
			}
			if (decisMRS == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_mRS, 1);
			else if (decisMRS == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_mRS, 0);
		}

		if (timeAdminCt > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_ADMI_CT,
					timeAdminCt);
			if (reasonAdminCt != null && reasonAdminCt.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_ADMI_CT,
						reasonAdminCt);
			}
			if (decisAdminCt == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_ADMI_CT, 1);
			else if (decisAdminCt == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_ADMI_CT, 0);
		}
		if (timeCTs > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_CTs, timeCTs);
			if (reasonCTs != null && reasonCTs.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_CTs,
						reasonCTs);
			}
			if (decisCTs == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_CTs, 1);
			else if (decisCTs == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_CTs, 0);
		}

		if (timeQuickInr > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_QUICK_INR,
					timeQuickInr);
			if (reasonQuickInr != null && reasonQuickInr.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_QUICK_INR,
						reasonQuickInr);
			}
			if (decisQuickInr == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_QUICK_INR,
						1);
			else if (decisQuickInr == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_QUICK_INR,
						0);
		}
		if (timeInr > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_INR, timeInr);
			if (reasonInr != null && reasonInr.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_INR,
						reasonInr);
			}
			if (decisInr == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_INR, 1);
			else if (decisInr == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_INR, 0);
		}
		if (timeAptt > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_APTT, timeAptt);
			if (reasonAptt != null && reasonAptt.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_APTT,
						reasonAptt);
			}
			if (decisAptt == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_APTT, 1);
			else if (decisAptt == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_APTT, 0);
		}
		if (timeBTrom > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_B_TROM, timeBTrom);
			if (reasonBTrom != null && reasonBTrom.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_B_TROM,
						reasonBTrom);
			}
			if (decisBTrom == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_B_TROM, 1);
			else if (decisBTrom == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_B_TROM, 0);
		}
		if (timeBGluc > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_BGLUC, timeBGluc);
			if (reasonBGluc != null && reasonBGluc.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_BGLUC,
						reasonBGluc);
			}
			if (decisBGluc == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC, 1);
			else if (decisBGluc == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC, 0);
		}
		if (timeBGlucAft > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_BGLUC_AFTER,
					timeBGlucAft);
			if (reasonBGlucAft != null && reasonBGlucAft.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_BGLUC_AFTER,
						reasonBGlucAft);
			}
			if (decisBGlucAft == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC_AFTER, 1);
			else if (decisBGlucAft == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC_AFTER, 0);
		}
		if (timeActiveBleed > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_ACTIVE_BLEED,
					timeActiveBleed);
			if (reasonActiveBleed != null && reasonActiveBleed.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_ACTIVE_BLEED,
						reasonActiveBleed);
			}
			if (decisActiveBleed == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_ACTIVE_BLEED,
						1);
			else if (decisActiveBleed == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_ACTIVE_BLEED,
						0);
		}
		if (timeAnticoagulant > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_ANTICOAGULANT,
					timeAnticoagulant);
			if (reasonAnticoagulant != null && reasonAnticoagulant.length() > 0) {
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_REASON_ANTICOAGULANT,
						reasonAnticoagulant);
			}
			if (decisAnticoagulant == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_ANTICOAGULANT,
						1);
			else if (decisAnticoagulant == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_ANTICOAGULANT,
						0);
		}
		if (timeCardiovascular > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_CARDIOVASCULAR,
					timeCardiovascular);
			if (reasonCardiovascular != null
					&& reasonCardiovascular.length() > 0) {
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_REASON_CARDIOVASCULAR,
						reasonCardiovascular);
			}
			if (decisCardiovascular == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_CARDIOVASCULAR,
						1);
			else if (decisCardiovascular == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_CARDIOVASCULAR,
						0);
		}
		if (timeObstetric > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_OBSTETRIC,
					timeObstetric);
			if (reasonObstetric != null && reasonObstetric.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_OBSTETRIC,
						reasonObstetric);
			}
			if (decisObstetric == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_OBSTETRIC,
						1);
			else if (decisObstetric == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_OBSTETRIC,
						0);
		}
		if (timePresentOther > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_PRESENT_OTHER,
					timePresentOther);
			if (reasonPresentOther != null && reasonPresentOther.length() > 0) {
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_REASON_PRESENT_OTHER,
						reasonPresentOther);
			}
			if (decisPresentOther == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_PRESENT_OTHER,
						1);
			else if (decisPresentOther == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_PRESENT_OTHER,
						0);
		}
		if (timeCerebrovascular > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_CEREBROVASCULAR,
					timeCerebrovascular);
			if (reasonCerebrovascular != null
					&& reasonCerebrovascular.length() > 0) {
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_REASON_CEREBROVASCULAR,
						reasonCerebrovascular);
			}
			if (decisCerebrovascular == true)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_CEREBROVASCULAR,
						1);
			else if (decisCerebrovascular == false)
				values.put(
						DataBaseManage.COLUMN_INTERRUPT_DECISION_CEREBROVASCULAR,
						0);
		}
		if (timeDisease > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_DISEASE,
					timeDisease);
			if (reasonDisease != null && reasonDisease.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_DISEASE,
						reasonDisease);
			}
			if (decisDisease == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_DISEASE, 1);
			else if (decisDisease == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_DISEASE, 0);
		}
		if (timeBleed > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_BLEED, timeBleed);
			if (reasonBleed != null && reasonBleed.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_BLEED,
						reasonBleed);
			}
			if (decisBleed == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_BLEED, 1);
			else if (decisBleed == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_BLEED, 0);
		}
		if (timeOperation > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_OPERATION,
					timeOperation);
			if (reasonOperation != null && reasonOperation.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_OPERATION,
						reasonOperation);
			}
			if (decisOperation == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_OPERATION,
						1);
			else if (decisOperation == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_OPERATION,
						0);
		}
		if (timeVessel > 0) {
			values.put(DataBaseManage.COLUMN_INTERRUPT_TIME_VESSEL, timeVessel);
			if (reasonVessel != null && reasonVessel.length() > 0) {
				values.put(DataBaseManage.COLUMN_INTERRUPT_REASON_VESSEL,
						reasonVessel);
			}
			if (decisVessel == true)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_VESSEL, 1);
			else if (decisVessel == false)
				values.put(DataBaseManage.COLUMN_INTERRUPT_DECISION_VESSEL, 0);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_INTERRUPTION, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	/** For patient page */
	public void fetchInterruption() throws SQLException {
		Cursor cursor = sqliteDB.query(true, DataBaseManage.TABLE_INTERRUPTION,
				null, DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);

		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();

			setProcessInterrReason(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_PROCESS_REASON)));
			setProcessInterrTime(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_PROCESS_TIME)));

			setDecisOnset(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_ONSET)) == 1);
			setReasonOnset(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_ONSET)));
			setTimeOnset(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_ONSET)));

			setDecisTerIllness(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_TERMINAL_ILLNESS)) == 1);
			setReasonTerIllness(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_TERMINAL_ILLNESS)));
			setTimeTerIllness(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_TERMINAL_ILLNESS)));

			setDecisRR(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR)) == 1);
			setReasonRR(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_RR)));
			setTimeRR(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_RR)));

			setDecisRRAft(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_RR_AFTER)) == 1);
			setReasonRRAft(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_RR_AFTER)));
			setTimeRRAft(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_RR_AFTER)));

			setDecisMRS(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_mRS)) == 1);
			setReasonMRS(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_mRS)));
			setTimeMRS(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_mRS)));

			setDecisAdminCt(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_ADMI_CT)) == 1);
			setReasonAdminCt(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_ADMI_CT)));
			setTimeAdminCt(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_ADMI_CT)));

			setDecisCTs(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_CTs)) == 1);
			setReasonCTs(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_CTs)));
			setTimeCTs(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_CTs)));

			setDecisQuickInr(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_QUICK_INR)) == 1);
			setReasonQuickInr(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_QUICK_INR)));
			setTimeQuickInr(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_QUICK_INR)));

			setDecisInr(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_INR)) == 1);
			setReasonInr(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_INR)));
			setTimeInr(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_INR)));

			setDecisAptt(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_APTT)) == 1);
			setReasonAptt(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_APTT)));
			setTimeAptt(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_APTT)));

			setDecisBTrom(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_B_TROM)) == 1);
			setReasonBTrom(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_B_TROM)));
			setTimeBTrom(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_B_TROM)));

			setDecisBGluc(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC)) == 1);
			setReasonBGluc(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_BGLUC)));
			setTimeBGluc(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_BGLUC)));

			setDecisBGlucAft(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_BGLUC_AFTER)) == 1);
			setReasonBGlucAft(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_BGLUC_AFTER)));
			setTimeBGlucAft(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_BGLUC_AFTER)));

			setDecisActiveBleed(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_ACTIVE_BLEED)) == 1);
			setReasonActiveBleed(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_ACTIVE_BLEED)));
			setTimeActiveBleed(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_ACTIVE_BLEED)));

			setDecisAnticoagulant(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_ANTICOAGULANT)) == 1);
			setReasonAnticoagulant(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_ANTICOAGULANT)));
			setTimeAnticoagulant(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_ANTICOAGULANT)));

			setDecisCardiovascular(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_CARDIOVASCULAR)) == 1);
			setReasonCardiovascular(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_CARDIOVASCULAR)));
			setTimeCardiovascular(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_CARDIOVASCULAR)));

			setDecisObstetric(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_OBSTETRIC)) == 1);
			setReasonObstetric(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_OBSTETRIC)));
			setTimeObstetric(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_OBSTETRIC)));

			setDecisPresentOther(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_PRESENT_OTHER)) == 1);
			setReasonPresentOther(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_PRESENT_OTHER)));
			setTimePresentOther(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_PRESENT_OTHER)));

			setDecisCerebrovascular(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_CEREBROVASCULAR)) == 1);
			setReasonCerebrovascular(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_CEREBROVASCULAR)));
			setTimeCerebrovascular(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_CEREBROVASCULAR)));

			setDecisDisease(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_DISEASE)) == 1);
			setReasonDisease(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_DISEASE)));
			setTimeDisease(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_DISEASE)));

			setDecisBleed(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_BLEED)) == 1);
			setReasonBleed(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_BLEED)));
			setTimeBleed(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_BLEED)));

			setDecisOperation(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_OPERATION)) == 1);
			setReasonOperation(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_OPERATION)));
			setTimeOperation(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_OPERATION)));

			setDecisVessel(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_DECISION_VESSEL)) == 1);
			setReasonVessel(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_REASON_VESSEL)));
			setTimeVessel(cursor
					.getLong(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_INTERRUPT_TIME_VESSEL)));

		}
		isInterruptionInfoLoaded = true;
	}

	public boolean isInterruptionInfoLoaded() {
		return isInterruptionInfoLoaded;
	}

	/** Summarize Rational continue process despite contrary recommendation */
	public void summarizeRationalProcess() {
		if (!decisOnset && reasonOnset != null && reasonOnset.length() > 0)
			AppViewModel.continueProcess.add(reasonOnset);
		if (!decisRR && reasonRR != null && reasonRR.length() > 0)
			AppViewModel.continueProcess.add(reasonRR);
		if (!decisRRAft && reasonRRAft != null && reasonRRAft.length() > 0)
			AppViewModel.continueProcess.add(reasonRRAft);
		if (!decisMRS && reasonMRS != null && reasonMRS.length() > 0)
			AppViewModel.continueProcess.add(reasonMRS);
		if (!decisAdminCt && reasonAdminCt != null && reasonAdminCt.length() > 0)
			AppViewModel.continueProcess.add(reasonAdminCt);
		if (!decisCTs && reasonCTs != null && reasonCTs.length() > 0)
			AppViewModel.continueProcess.add(reasonCTs);
		if (!decisQuickInr
				&& reasonQuickInr != null && reasonQuickInr.length() > 0)
			AppViewModel.continueProcess.add(reasonQuickInr);
		if (!decisInr && reasonInr != null && reasonInr.length() > 0)
			AppViewModel.continueProcess.add(reasonInr);
		if (!decisAptt && reasonAptt != null && reasonAptt.length() > 0)
			AppViewModel.continueProcess.add(reasonAptt);
		if (!decisBTrom && reasonBTrom != null && reasonBTrom.length() > 0)
			AppViewModel.continueProcess.add(reasonBTrom);
		if (!decisBGluc && reasonBGluc != null && reasonBGluc.length() > 0)
			AppViewModel.continueProcess.add(reasonBGluc);
		if (!decisBGlucAft
				&& reasonBGlucAft != null && reasonBGlucAft.length() > 0)
			AppViewModel.continueProcess.add(reasonBGlucAft);
		if (!decisActiveBleed
				&& reasonActiveBleed != null && reasonActiveBleed.length() > 0)
			AppViewModel.continueProcess.add(reasonActiveBleed);
		if (!decisAnticoagulant
				&& reasonAnticoagulant != null && reasonAnticoagulant.length() > 0)
			AppViewModel.continueProcess.add(reasonAnticoagulant);
		if (!decisCardiovascular
				&& reasonCardiovascular != null && reasonCardiovascular.length() > 0)
			AppViewModel.continueProcess.add(reasonCardiovascular);
		if (!decisTerIllness
				&& reasonTerIllness != null && reasonTerIllness.length() > 0)
			AppViewModel.continueProcess.add(reasonTerIllness);
		if (!decisObstetric
				&& reasonObstetric != null && reasonObstetric.length() > 0)
			AppViewModel.continueProcess.add(reasonObstetric);
		if (!decisPresentOther
				&& reasonPresentOther != null && reasonPresentOther.length() > 0)
			AppViewModel.continueProcess.add(reasonPresentOther);
		if (!decisCerebrovascular
				&& reasonCerebrovascular != null && reasonCerebrovascular.length() > 0)
			AppViewModel.continueProcess.add(reasonCerebrovascular);
		if (!decisDisease && reasonDisease != null && reasonDisease.length() > 0)
			AppViewModel.continueProcess.add(reasonDisease);
		if (!decisBleed && reasonBleed != null && reasonBleed.length() > 0)
			AppViewModel.continueProcess.add(reasonBleed);
		if (!decisOperation
				&& reasonOperation != null && reasonOperation.length() > 0)
			AppViewModel.continueProcess.add(reasonOperation);
		if (!decisVessel && reasonVessel != null && reasonVessel.length() > 0)
			AppViewModel.continueProcess.add(reasonVessel);
	}
	/** Summarize interrupt process*/
	public void summarizeInterruptProcess() {
		if (decisOnset && reasonOnset != null && reasonOnset.length() > 0)
			AppViewModel.interruptProcess.add(reasonOnset);
		if (decisRR && reasonRR != null && reasonRR.length() > 0)
			AppViewModel.interruptProcess.add(reasonRR);
		if (decisRRAft && reasonRRAft != null && reasonRRAft.length() > 0)
			AppViewModel.interruptProcess.add(reasonRRAft);
		if (decisMRS && reasonMRS != null && reasonMRS.length() > 0)
			AppViewModel.interruptProcess.add(reasonMRS);
		if (decisAdminCt && reasonAdminCt != null && reasonAdminCt.length() > 0)
			AppViewModel.interruptProcess.add(reasonAdminCt);
		if (decisCTs && reasonCTs != null && reasonCTs.length() > 0)
			AppViewModel.interruptProcess.add(reasonCTs);
		if (decisQuickInr
				&& reasonQuickInr != null && reasonQuickInr.length() > 0)
			AppViewModel.interruptProcess.add(reasonQuickInr);
		if (decisInr && reasonInr != null && reasonInr.length() > 0)
			AppViewModel.interruptProcess.add(reasonInr);
		if (decisAptt && reasonAptt != null && reasonAptt.length() > 0)
			AppViewModel.interruptProcess.add(reasonAptt);
		if (decisBTrom && reasonBTrom != null && reasonBTrom.length() > 0)
			AppViewModel.interruptProcess.add(reasonBTrom);
		if (decisBGluc && reasonBGluc != null && reasonBGluc.length() > 0)
			AppViewModel.interruptProcess.add(reasonBGluc);
		if (decisBGlucAft
				&& reasonBGlucAft != null && reasonBGlucAft.length() > 0)
			AppViewModel.interruptProcess.add(reasonBGlucAft);
		if (decisActiveBleed
				&& reasonActiveBleed != null && reasonActiveBleed.length() > 0)
			AppViewModel.interruptProcess.add(reasonActiveBleed);
		if (decisAnticoagulant
				&& reasonAnticoagulant != null && reasonAnticoagulant.length() > 0)
			AppViewModel.interruptProcess.add(reasonAnticoagulant);
		if (decisCardiovascular
				&& reasonCardiovascular != null && reasonCardiovascular.length() > 0)
			AppViewModel.interruptProcess.add(reasonCardiovascular);
		if (decisTerIllness
				&& reasonTerIllness != null && reasonTerIllness.length() > 0)
			AppViewModel.interruptProcess.add(reasonTerIllness);
		if (decisObstetric
				&& reasonObstetric != null && reasonObstetric.length() > 0)
			AppViewModel.interruptProcess.add(reasonObstetric);
		if (decisPresentOther
				&& reasonPresentOther != null && reasonPresentOther.length() > 0)
			AppViewModel.interruptProcess.add(reasonPresentOther);
		if (decisCerebrovascular
				&& reasonCerebrovascular != null && reasonCerebrovascular.length() > 0)
			AppViewModel.interruptProcess.add(reasonCerebrovascular);
		if (decisDisease && reasonDisease != null && reasonDisease.length() > 0)
			AppViewModel.interruptProcess.add(reasonDisease);
		if (decisBleed && reasonBleed != null && reasonBleed.length() > 0)
			AppViewModel.interruptProcess.add(reasonBleed);
		if (decisOperation
				&& reasonOperation != null && reasonOperation.length() > 0)
			AppViewModel.interruptProcess.add(reasonOperation);
		if (decisVessel && reasonVessel != null && reasonVessel.length() > 0)
			AppViewModel.interruptProcess.add(reasonVessel);
	}

	public void setProcessInterrReason(String interr) {
		processInterrReason = interr;
	}

	public void setProcessInterrTime(long time) {
		processInterrTime = time;
	}

	public String getProcessInterrReason() {
		return processInterrReason;
	}

	public long getProcessInterrTime() {
		return processInterrTime;
	}

	public void setDecisOnset(boolean decision) {
		decisOnset = decision;
	}

	public boolean getDecisOnset() {
		return decisOnset;
	}

	public void setReasonOnset(String interr) {
		reasonOnset = interr;
	}

	public void setTimeOnset(long time) {
		timeOnset = time;
	}

	public String getReasonOnset() {
		return reasonOnset;
	}

	public long getTimeOnset() {
		return timeOnset;
	}

	public void setDecisTerIllness(boolean decision) {
		decisTerIllness = decision;
	}

	public boolean getDecisTerIllness() {
		return decisTerIllness;
	}

	public void setReasonTerIllness(String interr) {
		reasonTerIllness = interr;
	}

	public void setTimeTerIllness(long time) {
		timeTerIllness = time;
	}

	public String getReasonTerIllness() {
		return reasonTerIllness;
	}

	public long getTimeTerIllness() {
		return timeTerIllness;
	}

	public void setDecisRR(boolean decision) {
		decisRR = decision;
	}

	public boolean getDecisRR() {
		return decisRR;
	}

	public void setReasonRR(String interr) {
		reasonRR = interr;
	}

	public void setTimeRR(long time) {
		timeRR = time;
	}

	public String getReasonRR() {
		return reasonRR;
	}

	public long getTimeRR() {
		return timeRR;
	}

	public void setDecisRRAft(boolean decision) {
		decisRRAft = decision;
	}

	public boolean getDecisRRAft() {
		return decisRRAft;
	}

	public void setReasonRRAft(String interr) {
		reasonRRAft = interr;
	}

	public void setTimeRRAft(long time) {
		timeRRAft = time;
	}

	public String getReasonRRAft() {
		return reasonRRAft;
	}

	public long getTimeRRAft() {
		return timeRRAft;
	}

	public void setDecisMRS(boolean decision) {
		decisMRS = decision;
	}

	public boolean getDecisMRS() {
		return decisMRS;
	}

	public void setReasonMRS(String interr) {
		reasonMRS = interr;
	}

	public void setTimeMRS(long time) {
		timeMRS = time;
	}

	public String getReasonMRS() {
		return reasonMRS;
	}

	public long getTimeMRS() {
		return timeMRS;
	}

	public void setDecisAdminCt(boolean decision) {
		decisAdminCt = decision;
	}

	public boolean getDecisAdminCt() {
		return decisAdminCt;
	}

	public void setReasonAdminCt(String interr) {
		reasonAdminCt = interr;
	}

	public void setTimeAdminCt(long time) {
		timeAdminCt = time;
	}

	public String getReasonAdminCt() {
		return reasonAdminCt;
	}

	public long getTimeAdminCt() {
		return timeAdminCt;
	}

	public void setDecisCTs(boolean decision) {
		decisCTs = decision;
	}

	public boolean getDecisCTs() {
		return decisCTs;
	}

	public void setReasonCTs(String interr) {
		reasonCTs = interr;
	}

	public void setTimeCTs(long time) {
		timeCTs = time;
	}

	public String getReasonCTs() {
		return reasonCTs;
	}

	public long getTimeCTs() {
		return timeCTs;
	}

	public void setDecisQuickInr(boolean decision) {
		decisQuickInr = decision;
	}

	public boolean getDecisQuickInr() {
		return decisCTs;
	}

	public void setReasonQuickInr(String interr) {
		reasonQuickInr = interr;
	}

	public void setTimeQuickInr(long time) {
		timeQuickInr = time;
	}

	public String getReasonQuickInr() {
		return reasonQuickInr;
	}

	public long getTimeQuickInr() {
		return timeQuickInr;
	}

	public void setDecisInr(boolean decision) {
		decisInr = decision;
	}

	public boolean getDecisInr() {
		return decisInr;
	}

	public void setReasonInr(String interr) {
		reasonInr = interr;
	}

	public void setTimeInr(long time) {
		timeInr = time;
	}

	public String getReasonInr() {
		return reasonInr;
	}

	public long getTimeInr() {
		return timeInr;
	}

	public void setDecisAptt(boolean decision) {
		decisAptt = decision;
	}

	public boolean getDecisAptt() {
		return decisAptt;
	}

	public void setReasonAptt(String interr) {
		reasonAptt = interr;
	}

	public void setTimeAptt(long time) {
		timeAptt = time;
	}

	public String getReasonAptt() {
		return reasonAptt;
	}

	public long getTimeAptt() {
		return timeAptt;
	}

	public void setDecisBTrom(boolean decision) {
		decisBTrom = decision;
	}

	public boolean getDecisBTrom() {
		return decisBTrom;
	}

	public void setReasonBTrom(String interr) {
		reasonBTrom = interr;
	}

	public void setTimeBTrom(long time) {
		timeBTrom = time;
	}

	public String getReasonBTrom() {
		return reasonBTrom;
	}

	public long getTimeBTrom() {
		return timeBTrom;
	}

	public void setDecisBGluc(boolean decision) {
		decisBGluc = decision;
	}

	public boolean getDecisBGluc() {
		return decisBGluc;
	}

	public void setReasonBGluc(String interr) {
		reasonBGluc = interr;
	}

	public void setTimeBGluc(long time) {
		timeBGluc = time;
	}

	public String getReasonBGluc() {
		return reasonBGluc;
	}

	public long getTimeBGluc() {
		return timeBGluc;
	}

	public void setDecisBGlucAft(boolean decision) {
		decisBGlucAft = decision;
	}

	public boolean getDecisBGlucAft() {
		return decisBGlucAft;
	}

	public void setReasonBGlucAft(String interr) {
		reasonBGlucAft = interr;
	}

	public void setTimeBGlucAft(long time) {
		timeBGlucAft = time;
	}

	public String getReasonBGlucAft() {
		return reasonBGlucAft;
	}

	public long getTimeBGlucAft() {
		return timeBGlucAft;
	}

	public void setDecisActiveBleed(boolean decision) {
		decisActiveBleed = decision;
	}

	public boolean getDecisActiveBleed() {
		return decisActiveBleed;
	}

	public void setReasonActiveBleed(String interr) {
		reasonActiveBleed = interr;
	}

	public void setTimeActiveBleed(long time) {
		timeActiveBleed = time;
	}

	public String getReasonActiveBleed() {
		return reasonActiveBleed;
	}

	public long getTimeActiveBleed() {
		return timeActiveBleed;
	}

	public void setDecisAnticoagulant(boolean decision) {
		decisAnticoagulant = decision;
	}

	public boolean getDecisAnticoagulant() {
		return decisAnticoagulant;
	}

	public void setReasonAnticoagulant(String interr) {
		reasonAnticoagulant = interr;
	}

	public void setTimeAnticoagulant(long time) {
		timeAnticoagulant = time;
	}

	public String getReasonAnticoagulant() {
		return reasonAnticoagulant;
	}

	public long getTimeAnticoagulant() {
		return timeAnticoagulant;
	}

	public void setDecisCardiovascular(boolean decision) {
		decisCardiovascular = decision;
	}

	public boolean getDecisCardiovascular() {
		return decisCardiovascular;
	}

	public void setReasonCardiovascular(String interr) {
		reasonCardiovascular = interr;
	}

	public void setTimeCardiovascular(long time) {
		timeCardiovascular = time;
	}

	public String getReasonCardiovascular() {
		return reasonCardiovascular;
	}

	public long getTimeCardiovascular() {
		return timeCardiovascular;
	}

	public void setDecisObstetric(boolean decision) {
		decisObstetric = decision;
	}

	public boolean getDecisObstetric() {
		return decisObstetric;
	}

	public void setReasonObstetric(String interr) {
		reasonObstetric = interr;
	}

	public void setTimeObstetric(long time) {
		timeObstetric = time;
	}

	public String getReasonObstetric() {
		return reasonObstetric;
	}

	public long getTimeObstetric() {
		return timeObstetric;
	}

	public void setDecisPresentOther(boolean decision) {
		decisPresentOther = decision;
	}

	public boolean getDecisPresentOther() {
		return decisPresentOther;
	}

	public void setReasonPresentOther(String interr) {
		reasonPresentOther = interr;
	}

	public void setTimePresentOther(long time) {
		timePresentOther = time;
	}

	public String getReasonPresentOther() {
		return reasonPresentOther;
	}

	public long getTimePresentOther() {
		return timePresentOther;
	}

	public void setDecisCerebrovascular(boolean decision) {
		decisCerebrovascular = decision;
	}

	public boolean getDecisCerebrovascular() {
		return decisCerebrovascular;
	}

	public void setReasonCerebrovascular(String interr) {
		reasonCerebrovascular = interr;
	}

	public void setTimeCerebrovascular(long time) {
		timeCerebrovascular = time;
	}

	public String getReasonCerebrovascular() {
		return reasonCerebrovascular;
	}

	public long getTimeCerebrovascular() {
		return timeCerebrovascular;
	}

	public void setDecisDisease(boolean decision) {
		decisDisease = decision;
	}

	public boolean getDecisDisease() {
		return decisDisease;
	}

	public void setReasonDisease(String interr) {
		reasonDisease = interr;
	}

	public void setTimeDisease(long time) {
		timeDisease = time;
	}

	public String getReasonDisease() {
		return reasonDisease;
	}

	public long getTimeDisease() {
		return timeDisease;
	}

	public void setDecisBleed(boolean decision) {
		decisBleed = decision;
	}

	public boolean getDecisBleed() {
		return decisBleed;
	}

	public void setReasonBleed(String interr) {
		reasonBleed = interr;
	}

	public void setTimeBleed(long time) {
		timeBleed = time;
	}

	public String getReasonBleed() {
		return reasonBleed;
	}

	public long getTimeBleed() {
		return timeBleed;
	}

	public void setDecisOperation(boolean decision) {
		decisOperation = decision;
	}

	public boolean getDecisOperation() {
		return decisOperation;
	}

	public void setReasonOperation(String interr) {
		reasonOperation = interr;
	}

	public void setTimeOperation(long time) {
		timeOperation = time;
	}

	public String getReasonOperation() {
		return reasonOperation;
	}

	public long getTimeOperation() {
		return timeOperation;
	}

	public void setDecisVessel(boolean decision) {
		decisVessel = decision;
	}

	public boolean getDecisVessel() {
		return decisVessel;
	}

	public void setReasonVessel(String interr) {
		reasonVessel = interr;
	}

	public void setTimeVessel(long time) {
		timeVessel = time;
	}

	public String getReasonVessel() {
		return reasonVessel;
	}

	public long getTimeVessel() {
		return timeVessel;
	}
}
