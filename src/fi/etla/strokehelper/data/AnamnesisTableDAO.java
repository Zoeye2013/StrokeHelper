package fi.etla.strokehelper.data;

import java.util.ArrayList;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AnamnesisTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private String cerebroIncident;
	private int cerebroIncidentId;
	private String disease;
	private int diseaseId;
	private String bleeding;
	private int bleedingId;
	private String operation;
	private int operationId;
	private String vessel;
	private int vesselId;
	private String myocarInfarction;
	private int myocarInfarctionId;

	private int sah;
	private int intracHemorrhage;
	private int headTrauma;
	private int wideStroke;
	private int minorStroke;
	private int cerebroOtherCri;
	private int cerebroOtherNotCri;

	private int hepatopathy;
	private int retinopathy;
	private int pancreatitis;
	private int tumour;
	private int diseaseOtherCri;
	private int diseaseOtherNotCri;

	private int trauma;
	private int otherBleeding;
	private int otherGIBleeding;
	private int ulcus;
	private int varices;
	private int tractHemorrahge;
	private int bleedingOtherNotCri;

	private int surgicalOper;
	private int neurosurgicalOper;
	private int opthalmologicalOper;
	private int punction;
	private int massage;
	private int parturition;
	private int operationOtherCri;
	private int operationOtherNotCri;

	private int microangiopathy;
	private int avm;
	private int aneurysm;
	private int otherVesselAnomal;
	private int vesselOtherCri;
	private int vesselOtherNotCri;

	private boolean isAnamnesisInfoLoaded;

	public AnamnesisTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		init();
	}

	public void init() {
		cerebroIncident = "";
		cerebroIncidentId = -1;
		disease = "";
		diseaseId = -1;
		bleeding = "";
		bleedingId = -1;
		operation = "";
		operationId = -1;
		vessel = "";
		vesselId = -1;
		myocarInfarction = "";
		myocarInfarctionId = -1;

		sah = -1;
		intracHemorrhage = -1;
		headTrauma = -1;
		wideStroke = -1;
		minorStroke = -1;
		cerebroOtherCri = -1;
		cerebroOtherNotCri = -1;

		hepatopathy = -1;
		retinopathy = -1;
		pancreatitis = -1;
		tumour = -1;
		diseaseOtherCri = -1;
		diseaseOtherNotCri = -1;

		trauma = -1;
		otherBleeding = -1;
		otherGIBleeding = -1;
		ulcus = -1;
		varices = -1;
		tractHemorrahge = -1;
		bleedingOtherNotCri = -1;

		surgicalOper = -1;
		neurosurgicalOper = -1;
		opthalmologicalOper = -1;
		punction = -1;
		massage = -1;
		parturition = -1;
		operationOtherCri = -1;
		operationOtherNotCri = -1;

		microangiopathy = -1;
		avm = -1;
		aneurysm = -1;
		otherVesselAnomal = -1;
		vesselOtherCri = -1;
		vesselOtherNotCri = -1;

		isAnamnesisInfoLoaded = false;
	}

	public long newEmptyAnamnesis(String logTime) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_LOG_TIME, logTime);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		isAnamnesisInfoLoaded = true;
		return sqliteDB.insert(DataBaseManage.TABLE_ANAMNESIS, null,
				initialValues);
	}

	public int updateAnamnesis() {
		ContentValues values = new ContentValues();

		if (cerebroIncident != null && cerebroIncident.length() > 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_INCIDENTS,
					cerebroIncident);
		}
		if (cerebroIncidentId > 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_INCIDENTS_ID,
					cerebroIncidentId);
		}

		if (disease != null && disease.length() > 0) {
			values.put(DataBaseManage.COLUMN_DISEASE, disease);
		}
		if (diseaseId > 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_ID, diseaseId);
		}

		if (bleeding != null && bleeding.length() > 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING, bleeding);
		}
		if (bleedingId > 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_ID, bleedingId);
		}

		if (operation != null && operation.length() > 0) {
			values.put(DataBaseManage.COLUMN_OPERATION, operation);
		}
		if (operationId > 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_ID, operationId);
		}

		if (vessel != null && vessel.length() > 0) {
			values.put(DataBaseManage.COLUMN_VESSEL, vessel);
		}
		if (vesselId > 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_ID, vesselId);
		}

		if (myocarInfarction != null && myocarInfarction.length() > 0) {
			values.put(DataBaseManage.COLUMN_MYOCARDIAL, myocarInfarction);
		}
		if (myocarInfarctionId > 0) {
			values.put(DataBaseManage.COLUMN_MYOCARDIAL_ID, myocarInfarctionId);
		}

		if (sah >= 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_SAH, sah);
		}
		if (intracHemorrhage >= 0) {
			values.put(
					DataBaseManage.COLUMN_CEREBROVASCULAR_INTRACRANIAL_HEMORRHAGE,
					intracHemorrhage);
		}
		if (headTrauma >= 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_HEAD_TRAUMA,
					headTrauma);
		}
		if (wideStroke >= 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_WIDE_STROKE,
					wideStroke);
		}
		if (minorStroke >= 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_MINOR_STROKE,
					minorStroke);
		}
		if (cerebroOtherCri >= 0) {
			values.put(DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_CRITICAL,
					cerebroOtherCri);
		}
		if (cerebroOtherNotCri >= 0) {
			values.put(
					DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_NOT_CRITICAL,
					cerebroOtherNotCri);
		}

		if (hepatopathy >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_HEPATOPATHY, hepatopathy);
		}
		if (retinopathy >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_RETINOPATHY, retinopathy);
		}
		if (pancreatitis >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_PANCREATITIS, pancreatitis);
		}
		if (tumour >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_TUMOUR, tumour);
		}
		if (diseaseOtherCri >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_OTHER_CRITICAL,
					diseaseOtherCri);
		}
		if (diseaseOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_DISEASE_OTHER_NOT_CRITICAL,
					diseaseOtherNotCri);
		}

		if (trauma >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_TRAUMA, trauma);
		}
		if (ulcus >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_ULCUS, ulcus);
		}
		if (varices >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_VARICES, varices);
		}
		if (otherBleeding >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_OTHER, otherBleeding);
		}
		if (otherGIBleeding >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_OTHER_GI, otherGIBleeding);
		}
		if (tractHemorrahge >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_URINARY, tractHemorrahge);
		}
		if (bleedingOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_BLEEDING_OTHER_NOT_CRITICAL,
					bleedingOtherNotCri);
		}

		if (surgicalOper >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_SURGICAL, surgicalOper);
		}
		if (neurosurgicalOper >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_NEUROSURGICAL,
					neurosurgicalOper);
		}
		if (opthalmologicalOper >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_OPTHALMOLOGICAL,
					opthalmologicalOper);
		}
		if (punction >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_VESSEL_PUNCTION,
					punction);
		}
		if (massage >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_MASSAGE, massage);
		}
		if (parturition >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_PARTURITION, parturition);
		}
		if (operationOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_OTHER_NOT_CRITICAL,
					operationOtherNotCri);
		}
		if (operationOtherCri >= 0) {
			values.put(DataBaseManage.COLUMN_OPERATION_OTHER_CRITICAL,
					operationOtherCri);
		}

		if (microangiopathy >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_MICROANGIOPATHY,
					microangiopathy);
		}
		if (aneurysm >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_ANEURYSM, aneurysm);
		}
		if (avm >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_AVM, avm);
		}
		if (otherVesselAnomal >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_OTHER_ANOMAL,
					otherVesselAnomal);
		}
		if (vesselOtherCri >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_OTHER_CRITICAL,
					vesselOtherCri);
		}
		if (vesselOtherNotCri >= 0) {
			values.put(DataBaseManage.COLUMN_VESSEL_OTHER_NOT_CRITICAL,
					vesselOtherNotCri);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB.update(DataBaseManage.TABLE_ANAMNESIS, values,
					DataBaseManage.COLUMN_PATIENT_ID + " = ?",
					new String[] { String.valueOf(AppViewModel.patientDAO
							.getPatientId()) });
		}
		return 0;
	}

	public void fetchAnamnesis() throws SQLException {

		Cursor cursor = sqliteDB.query(true, DataBaseManage.TABLE_ANAMNESIS,
				null, DataBaseManage.COLUMN_PATIENT_ID + "="
						+ AppViewModel.patientDAO.getPatientId(), null, null,
				null, null, null);

		// sqliteDB.query(true, DataBaseManage.TABLE_ANAMNESIS, new String[] {
		// DataBaseManage.COLUMN_CEREBROVASCULAR_INCIDENTS_ID,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_INTRACRANIAL_HEMORRHAGE,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_SAH,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_WIDE_STROKE,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_MINOR_STROKE,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_HEAD_TRAUMA,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_CRITICAL,
		// DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_DISEASE_ID,
		// DataBaseManage.COLUMN_DISEASE_HEPATOPATHY,
		// DataBaseManage.COLUMN_DISEASE_RETINOPATHY,
		// DataBaseManage.COLUMN_DISEASE_PANCREATITIS,
		// DataBaseManage.COLUMN_DISEASE_TUMOUR,
		// DataBaseManage.COLUMN_DISEASE_OTHER_CRITICAL,
		// DataBaseManage.COLUMN_DISEASE_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_BLEEDING_ID,
		// DataBaseManage.COLUMN_BLEEDING_TRAUMA,
		// DataBaseManage.COLUMN_BLEEDING_ULCUS,
		// DataBaseManage.COLUMN_BLEEDING_VARICES,
		// DataBaseManage.COLUMN_BLEEDING_OTHER,
		// DataBaseManage.COLUMN_BLEEDING_OTHER_GI,
		// DataBaseManage.COLUMN_BLEEDING_URINARY,
		// DataBaseManage.COLUMN_BLEEDING_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_OPERATION_ID,
		// DataBaseManage.COLUMN_OPERATION_SURGICAL,
		// DataBaseManage.COLUMN_OPERATION_NEUROSURGICAL,
		// DataBaseManage.COLUMN_OPERATION_OPTHALMOLOGICAL,
		// DataBaseManage.COLUMN_OPERATION_VESSEL_PUNCTION,
		// DataBaseManage.COLUMN_OPERATION_MASSAGE,
		// DataBaseManage.COLUMN_OPERATION_PARTURITION,
		// DataBaseManage.COLUMN_OPERATION_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_OPERATION_OTHER_CRITICAL,
		// DataBaseManage.COLUMN_VESSEL_ID,
		// DataBaseManage.COLUMN_VESSEL_MICROANGIOPATHY,
		// DataBaseManage.COLUMN_VESSEL_ANEURYSM,
		// DataBaseManage.COLUMN_VESSEL_AVM,
		// DataBaseManage.COLUMN_VESSEL_OTHER_ANOMAL,
		// DataBaseManage.COLUMN_VESSEL_OTHER_CRITICAL,
		// DataBaseManage.COLUMN_VESSEL_OTHER_NOT_CRITICAL,
		// DataBaseManage.COLUMN_MYOCARDIAL_ID},
		// DataBaseManage.COLUMN_PATIENT_ID + "=" +
		// AppViewModel.patientDAO.getPatientId(), null, null, null, null,
		// null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			setCerebroIncidentId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_INCIDENTS_ID)));
			setCerebroIncident(cursor
					.getString(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_INCIDENTS)));

			setDiseaseId(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_ID)));
			setDisease(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE)));

			setBleedingId(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_ID)));
			setBleeding(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING)));

			setOperationId(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_ID)));
			setOperation(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION)));

			setVesselId(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_ID)));
			setVessel(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL)));

			setMyocarInfarctionId(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_MYOCARDIAL_ID)));
			setMyocarInfarction(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_MYOCARDIAL)));

			setSAH(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_SAH)));
			setIntracHemorrhage(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_INTRACRANIAL_HEMORRHAGE)));
			setHeadTrauma(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_HEAD_TRAUMA)));
			setWideStroke(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_WIDE_STROKE)));
			setMinorStroke(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_MINOR_STROKE)));
			setCerebroOtherCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_CRITICAL)));
			setCerebroOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_CEREBROVASCULAR_OTHER_NOT_CRITICAL)));

			setHepatopathy(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_HEPATOPATHY)));
			setRetinopathy(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_RETINOPATHY)));
			setPancreatitis(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_PANCREATITIS)));
			setTumour(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_TUMOUR)));
			setDiseaseOtherCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_OTHER_CRITICAL)));
			setDiseaseOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_DISEASE_OTHER_NOT_CRITICAL)));

			setTrauma(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_TRAUMA)));
			setUlcus(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_ULCUS)));
			setVarices(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_VARICES)));
			setOtherBleeding(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_OTHER)));
			setOtherGIBleeding(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_OTHER_GI)));
			setTractHemorrahge(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_URINARY)));
			setBleedingOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_BLEEDING_OTHER_NOT_CRITICAL)));

			setSurgicalOper(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_SURGICAL)));
			setNeurosurgicalOper(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_NEUROSURGICAL)));
			setOpthalmologicalOper(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_OPTHALMOLOGICAL)));
			setPunction(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_VESSEL_PUNCTION)));
			setMassage(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_MASSAGE)));
			setParturition(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_PARTURITION)));
			setOperationOtherCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_OTHER_CRITICAL)));
			setOperationOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_OPERATION_OTHER_NOT_CRITICAL)));

			setMicroangiopathy(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_MICROANGIOPATHY)));
			setAneurysm(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_ANEURYSM)));
			setAvm(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_AVM)));
			setOtherVesselAnomal(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_OTHER_ANOMAL)));
			setVesselOtherCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_OTHER_CRITICAL)));
			setVesselOtherNotCri(cursor
					.getInt(cursor
							.getColumnIndexOrThrow(DataBaseManage.COLUMN_VESSEL_OTHER_NOT_CRITICAL)));
		}
		isAnamnesisInfoLoaded = true;
	}

	public boolean isAnamnesisInfoLoaded() {
		return isAnamnesisInfoLoaded;
	}

	public boolean isAnamnesisInfoComplete() {
		boolean isComplete = true;
		if (isCerebrovascularComplete() == false
				|| isDiseaseComplete() == false
				|| isBleedingComplete() == false
				|| isOperationComplete() == false
				|| isVesselComplete() == false
				|| myocarInfarctionId <= 0)
			isComplete = false;
		return isComplete;
	}

	public boolean isCerebrovascularComplete() {
		boolean isComplete = true;
		if (cerebroIncidentId <= 0
				|| (cerebroIncidentId == R.id.radio_cerebrovascular_incidents_yes
						&& intracHemorrhage <= 0
						&& sah <= 0
						&& wideStroke <= 0
						&& minorStroke <= 0
						&& headTrauma <= 0
						&& cerebroOtherCri <= 0 && cerebroOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}

	public boolean isDiseaseComplete() {
		boolean isComplete = true;
		if (diseaseId <= 0
				|| (diseaseId == R.id.radio_disease_bleeding_tendency_yes
						&& hepatopathy <= 0 && retinopathy <= 0
						&& pancreatitis <= 0 && tumour <= 0
						&& diseaseOtherCri <= 0 && diseaseOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}
	public boolean isBleedingComplete() {
		boolean isComplete = true;
		if (bleedingId <= 0
				|| (bleedingId == R.id.radio_significant_bleeding_yes
						&& trauma <= 0 && ulcus <= 0 && varices <= 0
						&& otherGIBleeding <= 0 && otherBleeding <= 0
						&& tractHemorrahge <= 0 && bleedingOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}
	public boolean isOperationComplete() {
		boolean isComplete = true;
		if (operationId <= 0
				|| (operationId == R.id.radio_operation_yes
						&& surgicalOper <= 0 && neurosurgicalOper <= 0
						&& opthalmologicalOper <= 0 && parturition <= 0
						&& punction <= 0 && massage <= 0
						&& operationOtherCri <= 0 && operationOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}
	public boolean isVesselComplete() {
		boolean isComplete = true;
		if (vesselId <= 0
				|| (vesselId == R.id.radio_blood_vessel_conditions_yes
						&& microangiopathy <= 0 && avm <= 0 && aneurysm <= 0
						&& otherVesselAnomal <= 0 && vesselOtherCri <= 0 && vesselOtherNotCri <= 0)) {
			isComplete = false;
		}
		return isComplete;
	}

	public void summarizeContraindications() {
		if (cerebroIncidentId == R.id.radio_cerebrovascular_incidents_yes) {
			if (intracHemorrhage > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_44));
			if (sah > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_45));
			if (wideStroke > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_46));
			if (minorStroke > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_47));
			if (headTrauma > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_48));
			if (cerebroOtherCri > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_49));
		}
		if (diseaseId == R.id.radio_disease_bleeding_tendency_yes) {
			if (hepatopathy > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_50));
			if (retinopathy > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_51));
			if (pancreatitis > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_52));
			if (tumour > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_53));
			if (diseaseOtherCri > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_54));
		}
		if (bleedingId == R.id.radio_significant_bleeding_yes) {
			if (trauma > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_55));
			if (ulcus > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_56));
			if (varices > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_57));
			if (otherGIBleeding > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_58));
			if (otherBleeding > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_59));
		}
		if (operationId == R.id.radio_operation_yes) {
			if (surgicalOper > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_60));
			if (neurosurgicalOper > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_61));
			if (opthalmologicalOper > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_62));
			if (parturition > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_63));
			if (punction > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_64));
			if (massage > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_65));
			if (operationOtherCri > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_66));
		}
		if (vesselId == R.id.radio_blood_vessel_conditions_yes) {
			if (microangiopathy > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_67));
			if (avm > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_68));
			if (aneurysm > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_69));
			if (otherVesselAnomal > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_70));
			if (vesselOtherCri > 0)
				AppViewModel.contras.add(AppViewModel.appActivity
						.getString(R.string.info_contraindications_71));
		}

		/** Relative */
		if (bleedingId == R.id.radio_significant_bleeding_yes
				&& tractHemorrahge > 0)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_21));
		if (myocarInfarctionId == R.id.radio_acute_myocardial_infarction_yes)
			AppViewModel.relaContras.add(AppViewModel.appActivity
					.getString(R.string.info_contrain_relative_22));
	}

	/** For summary page, generate cerebrovascular incidents result list */
	public ArrayList<Integer> getCerebroIncidentResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();
		if (sah > 0)
			results.add(sah);
		if (intracHemorrhage > 0)
			results.add(intracHemorrhage);
		if (headTrauma > 0)
			results.add(headTrauma);
		if (wideStroke > 0)
			results.add(wideStroke);
		if (minorStroke > 0)
			results.add(minorStroke);
		if (cerebroOtherCri > 0)
			results.add(cerebroOtherCri);
		if (cerebroOtherNotCri > 0)
			results.add(cerebroOtherNotCri);

		return results;
	}

	/**
	 * For summary page, generate disease increasing bleeding tendency result
	 * list
	 */
	public ArrayList<Integer> getDiseaseBleedingTendencyResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();
		if (hepatopathy > 0)
			results.add(hepatopathy);
		if (retinopathy > 0)
			results.add(retinopathy);
		if (pancreatitis > 0)
			results.add(pancreatitis);
		if (tumour > 0)
			results.add(tumour);
		if (diseaseOtherCri > 0)
			results.add(diseaseOtherCri);
		if (diseaseOtherNotCri > 0)
			results.add(diseaseOtherNotCri);
		return results;
	}

	/** For summary page, generate significant bleeding result list */
	public ArrayList<Integer> getSignificantBleedingResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();
		if (trauma > 0)
			results.add(trauma);
		if (otherBleeding > 0)
			results.add(otherBleeding);
		if (otherGIBleeding > 0)
			results.add(otherGIBleeding);
		if (ulcus > 0)
			results.add(ulcus);
		if (varices > 0)
			results.add(varices);
		if (tractHemorrahge > 0)
			results.add(tractHemorrahge);
		if (bleedingOtherNotCri > 0)
			results.add(bleedingOtherNotCri);
		return results;
	}

	/** For summary page, generate bleeding risk elevating operation result list */
	public ArrayList<Integer> getOperationResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();
		if (surgicalOper > 0)
			results.add(surgicalOper);
		if (neurosurgicalOper > 0)
			results.add(neurosurgicalOper);
		if (opthalmologicalOper > 0)
			results.add(opthalmologicalOper);
		if (punction > 0)
			results.add(punction);
		if (massage > 0)
			results.add(massage);
		if (parturition > 0)
			results.add(parturition);
		if (operationOtherCri > 0)
			results.add(operationOtherCri);
		if (operationOtherNotCri > 0)
			results.add(operationOtherNotCri);
		return results;
	}

	/** For summary page, generate vessel condition result list */
	public ArrayList<Integer> getVesselConditionResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.clear();
		if (microangiopathy > 0)
			results.add(microangiopathy);
		if (avm > 0)
			results.add(avm);
		if (aneurysm > 0)
			results.add(aneurysm);
		if (otherVesselAnomal > 0)
			results.add(otherVesselAnomal);
		if (vesselOtherCri > 0)
			results.add(vesselOtherCri);
		if (vesselOtherNotCri > 0)
			results.add(vesselOtherNotCri);
		return results;
	}

	public void setMinorStroke(int id) {
		minorStroke = id;
	}

	public void setCerebroOtherCri(int in) {
		cerebroOtherCri = in;
	}

	public void setHepatopathy(int id) {
		hepatopathy = id;
	}

	public void setCerebroOtherNotCri(int id) {
		cerebroOtherNotCri = id;
	}

	public int getCerebroOtherNotCri() {
		return cerebroOtherNotCri;
	}

	public int getMinorStroke() {
		return minorStroke;
	}

	public int getHepatopathy() {
		return hepatopathy;
	}

	public int getCerebroOtherCri() {
		return cerebroOtherCri;
	}

	public void setCerebroIncident(String str) {
		cerebroIncident = str;
	}

	public void setCerebroIncidentId(int id) {
		cerebroIncidentId = id;
	}

	public String getCerebroIncident() {
		return cerebroIncident;
	}

	public int getCerebroIncidentId() {
		return cerebroIncidentId;
	}

	public void setDisease(String str) {
		disease = str;
	}

	public void setDiseaseId(int id) {
		diseaseId = id;
	}

	public String getDisease() {
		return disease;
	}

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setBleeding(String str) {
		bleeding = str;
	}

	public void setBleedingId(int id) {
		bleedingId = id;
	}

	public String getBleeding() {
		return bleeding;
	}

	public int getBleedingId() {
		return bleedingId;
	}

	public void setOperation(String str) {
		operation = str;
	}

	public void setOperationId(int id) {
		operationId = id;
	}

	public String getOperation() {
		return operation;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setVessel(String str) {
		vessel = str;
	}

	public void setVesselId(int id) {
		vesselId = id;
	}

	public String getVessel() {
		return vessel;
	}

	public int getVesselId() {
		return vesselId;
	}

	public void setMyocarInfarction(String str) {
		myocarInfarction = str;
	}

	public String getMyocarInfarction() {
		return myocarInfarction;
	}

	public void setMyocarInfarctionId(int id) {
		myocarInfarctionId = id;
	}

	public int getMyocarInfarctionId() {
		return myocarInfarctionId;
	}

	public void setSAH(int id) {
		sah = id;
	}

	public int getSAH() {
		return sah;
	}

	public void setIntracHemorrhage(int id) {
		intracHemorrhage = id;
	}

	public int getIntracHemorrhage() {
		return intracHemorrhage;
	}

	public void setHeadTrauma(int id) {
		headTrauma = id;
	}

	public int getHeadTrauma() {
		return headTrauma;
	}

	public void setWideStroke(int id) {
		wideStroke = id;
	}

	public int getWideStroke() {
		return wideStroke;
	}

	public void setRetinopathy(int id) {
		retinopathy = id;
	}

	public int getRetinopathy() {
		return retinopathy;
	}

	public void setPancreatitis(int id) {
		pancreatitis = id;
	}

	public int getPancreatitis() {
		return pancreatitis;
	}

	public void setTumour(int id) {
		tumour = id;
	}

	public int getTumour() {
		return tumour;
	}

	public void setDiseaseOtherCri(int id) {
		diseaseOtherCri = id;
	}

	public int getDiseaseOtherCri() {
		return diseaseOtherCri;
	}

	public void setDiseaseOtherNotCri(int id) {
		diseaseOtherNotCri = id;
	}

	public int getDiseaseOtherNotCri() {
		return diseaseOtherNotCri;
	}

	public void setTrauma(int id) {
		trauma = id;
	}

	public int getTrauma() {
		return trauma;
	}

	public void setOtherBleeding(int id) {
		otherBleeding = id;
	}

	public int getOtherBleeding() {
		return otherBleeding;
	}

	public void setOtherGIBleeding(int id) {
		otherGIBleeding = id;
	}

	public int getOtherGIBleeding() {
		return otherGIBleeding;
	}

	public void setUlcus(int id) {
		ulcus = id;
	}

	public int getUlcus() {
		return ulcus;
	}

	public void setVarices(int id) {
		varices = id;
	}

	public int getVarices() {
		return varices;
	}

	public void setTractHemorrahge(int id) {
		tractHemorrahge = id;
	}

	public int getTractHemorrahge() {
		return tractHemorrahge;
	}

	public void setBleedingOtherNotCri(int id) {
		bleedingOtherNotCri = id;
	}

	public int getBleedingOtherNotCri() {
		return bleedingOtherNotCri;
	}

	public void setSurgicalOper(int id) {
		surgicalOper = id;
	}

	public int getSurgicalOper() {
		return surgicalOper;
	}

	public void setNeurosurgicalOper(int id) {
		neurosurgicalOper = id;
	}

	public int getNeurosurgicalOper() {
		return neurosurgicalOper;
	}

	public void setOpthalmologicalOper(int id) {
		opthalmologicalOper = id;
	}

	public int getOpthalmologicalOper() {
		return opthalmologicalOper;
	}

	public void setPunction(int id) {
		punction = id;
	}

	public int getPunction() {
		return punction;
	}

	public void setMassage(int id) {
		massage = id;
	}

	public int getMassage() {
		return massage;
	}

	public void setParturition(int id) {
		parturition = id;
	}

	public int getParturition() {
		return parturition;
	}

	public void setOperationOtherCri(int id) {
		operationOtherCri = id;
	}

	public int getOperationOtherCri() {
		return operationOtherCri;
	}

	public void setOperationOtherNotCri(int id) {
		operationOtherNotCri = id;
	}

	public int getOperationOtherNotCri() {
		return operationOtherNotCri;
	}

	public void setMicroangiopathy(int id) {
		microangiopathy = id;
	}

	public int getMicroangiopathy() {
		return microangiopathy;
	}

	public void setAvm(int id) {
		avm = id;
	}

	public int getAvm() {
		return avm;
	}

	public void setAneurysm(int id) {
		aneurysm = id;
	}

	public int getAneurysm() {
		return aneurysm;
	}

	public void setOtherVesselAnomal(int id) {
		otherVesselAnomal = id;
	}

	public int getOtherVesselAnomal() {
		return otherVesselAnomal;
	}

	public void setVesselOtherCri(int id) {
		vesselOtherCri = id;
	}

	public int getVesselOtherCri() {
		return vesselOtherCri;
	}

	public void setVesselOtherNotCri(int id) {
		vesselOtherNotCri = id;
	}

	public int getVesselOtherNotCri() {
		return vesselOtherNotCri;
	}

}
