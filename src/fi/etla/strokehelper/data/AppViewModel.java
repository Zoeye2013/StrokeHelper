package fi.etla.strokehelper.data;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothClass.Device.Major;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AnamnesisTableDAO;
import fi.etla.strokehelper.data.ContraindicationTableDAO;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.data.LabTableDAO;
import fi.etla.strokehelper.data.NihssTableDAO;
import fi.etla.strokehelper.data.PatientTableDAO;
import fi.etla.strokehelper.data.PresentConditionsTableDAO;
import fi.etla.strokehelper.data.SymptomsTableDAO;
import fi.etla.strokehelper.data.ThrombolysisTableDAO;
import fi.etla.strokehelper.data.TimeStampsTableDAO;
import fi.etla.strokehelper.main.MainActivity;

public class AppViewModel {

	/** DB Manage */
	private DataBaseManage dbMag;
	private SQLiteDatabase sqliteDB;

	public static DoctorUserTableDAO userDAO;
	public static PatientTableDAO patientDAO;
	public static SymptomsTableDAO symptomDAO;
	public static TimeStampsTableDAO timeStampsDAO;

	public static NihssTableDAO nihssDAO;
	public static LabTableDAO labDAO;
	public static PresentConditionsTableDAO presentConditionsDAO;
	public static AnamnesisTableDAO anamnesisDAO;
	public static ThrombolysisTableDAO thrombolysisDAO;
	public static ContraindicationTableDAO contraDAO;
	public static InterruptionTableDAO interruptionDAO;

	/** Application Main Activity */
	public static Activity appActivity;

	/** Contraindications Lists */
	public static ArrayList<String> contras = new ArrayList<String>();
	public static ArrayList<String> relaContras = new ArrayList<String>();
	
	public static ArrayList<String> continueProcess = new ArrayList<String>();
	public static ArrayList<String> interruptProcess = new ArrayList<String>();
	
	public static ArrayList<String> criticalIndications = new ArrayList<String>();
	public static ArrayList<String> supportiveIndications = new ArrayList<String>();

	public AppViewModel(Activity activity) {
		appActivity = activity;

		/** Open Database connection */
		openDataBase();
		userDAO = new DoctorUserTableDAO(sqliteDB);
		initPatientRelateDAOs();

		// if (patientDAO.getPatientId() != -1) {
		// loadDataFromDB();
		// }
	}

	public void initPatientRelateDAOs() {
		patientDAO = new PatientTableDAO(sqliteDB);

		symptomDAO = new SymptomsTableDAO(sqliteDB);
		timeStampsDAO = new TimeStampsTableDAO(sqliteDB);
		nihssDAO = new NihssTableDAO(sqliteDB);
		labDAO = new LabTableDAO(sqliteDB);
		presentConditionsDAO = new PresentConditionsTableDAO(sqliteDB);
		anamnesisDAO = new AnamnesisTableDAO(sqliteDB);
		thrombolysisDAO = new ThrombolysisTableDAO(sqliteDB);
		contraDAO = new ContraindicationTableDAO(sqliteDB);
		interruptionDAO = new InterruptionTableDAO(sqliteDB);
	}

	public SQLiteDatabase getSQLdb() {
		return sqliteDB;
	}

	public void openDataBase() throws SQLException {
		dbMag = new DataBaseManage(appActivity);
		sqliteDB = dbMag.getWritableDatabase();
	}

	public void closeDataBase() {
		dbMag.close();
	}

	/** New patient is clicked */
	public void newPatient() {
		/** New patient */
		Time time = new Time();
		time.setToNow();
		String logTime = timeToString(time.toMillis(false));
		patientDAO.newEmptyPatient(logTime);
		MainActivity.setCurrPatient(patientDAO.getPatientId());

		/** New Timestamps */
		timeStampsDAO.newEmptyTimestamps(logTime);

		/** New pre-notification */
		symptomDAO.newEmptySymptom(logTime);

		/** New Nihss */
		nihssDAO.newEmptyNIHSS();

		/** New lab */
		labDAO.newEmptyLab(logTime);

		/** New Present Conditions */
		presentConditionsDAO.newEmptyPresentCondition(logTime);

		/** New Anamnesis */
		anamnesisDAO.newEmptyAnamnesis(logTime);

		/** New Thrombolysis */
		thrombolysisDAO.newEmptyThrombolysis(logTime);
		
		/** New Interruption */
		interruptionDAO.newEmptyInterruption(logTime);
	}

	/** Load info from DB */
	public void loadDataFromDB() {
		if (patientDAO.isPatientInfoLoaded() == false) {
			patientDAO.fetchPatient();
		}

		if (thrombolysisDAO.isThrombolysisInfoLoaded() == false) {
			thrombolysisDAO.fetchThrombolysis();
		}

		if (symptomDAO.isSymptomInfoLoaded() == false) {
			symptomDAO.fetchSymptom();
		}

		if (timeStampsDAO.isTimeStampsInfoLoaded() == false) {
			timeStampsDAO.fetchTimestamp();
		}

		if (nihssDAO.isNihssLoaded(0) == false) {
			nihssDAO.fetchNihss(0);
		}
		
		if (nihssDAO.isNihssLoaded(1) == false) {
			nihssDAO.fetchNihss(1);
		}
		
		if (nihssDAO.isNihssLoaded(24) == false) {
			nihssDAO.fetchNihss(24);
		}

		if (labDAO.isLabInfoLoaded() == false) {
			labDAO.fetchLab();
		}

		if (presentConditionsDAO.isPresentConInfoLoaded() == false) {
			presentConditionsDAO.fetchPresentCondition();
		}

		if (anamnesisDAO.isAnamnesisInfoLoaded() == false) {
			anamnesisDAO.fetchAnamnesis();
		}
		
		if (interruptionDAO.isInterruptionInfoLoaded() == false){
			interruptionDAO.fetchInterruption();
		}
	}

	public void saveData() {
		if (patientDAO.getPatientId() != -1) {
			patientDAO.updatePatient();
			thrombolysisDAO.updateThrombolysis();
			symptomDAO.updateSymptom();
			timeStampsDAO.updateTimeStamps();
			nihssDAO.updateNihss(0);
			nihssDAO.updateNihss(1);
			nihssDAO.updateNihss(24);
			labDAO.updateLab();
			presentConditionsDAO.updatePresentCondition();
			anamnesisDAO.updateAnamnesis();
			interruptionDAO.updateInterruption();
		}
	}

	public static boolean isInfoComplete() {
		boolean isInfoComplete = true;
		if (!patientDAO.isPatientInfoComplete()
				|| !symptomDAO.isSymptomInfoComplete()
				|| !timeStampsDAO.isTimeStampsInfoComplete()
				|| !nihssDAO.isNihssBaseComplete()
				|| !labDAO.isLabInfoComplete()
				|| !presentConditionsDAO.isPresentConInfoComplete()
				|| !anamnesisDAO.isAnamnesisInfoComplete())
			isInfoComplete = false;
		return isInfoComplete;
	}

	public static void summarizeContraindications() {
		contras.clear();
		relaContras.clear();

		timeStampsDAO.summarizeContraindications();
		nihssDAO.summarizeContraindications();
		labDAO.summarizeContraindications();
		presentConditionsDAO.summarizeContraindications();
		anamnesisDAO.summarizeContraindications();
		symptomDAO.summarizeContraindications();
	}
	
	public static void summarizeRationalContinueProcess(){
		continueProcess.clear();
		interruptionDAO.summarizeRationalProcess();
	}
	
	public static void summarizeInterruptProcess(){
		interruptProcess.clear();
		interruptionDAO.summarizeInterruptProcess();
	}
	
	public static void summarizeIndications(){
		criticalIndications.clear();
		supportiveIndications.clear();
		nihssDAO.summarizeIndications();
		labDAO.summarizeIndications();
		symptomDAO.summarizeIndications();
	}

	public static int calSEDAN() {
		int sedan = -1;
		float bGluc = labDAO.getBGluc();
		int admCtEarlyInfarct = labDAO.getAdmiCtEarlyInfarct();
		int admCtHyperSign = labDAO.getAdmiCtHyperSign();
		int age = patientDAO.getPatientAge();
		String nihssTestTime = nihssDAO.getNihssBaseline().getTestTime();
		int nihssTotal = nihssDAO.getNihssBaseline().getNihssTotal();
		if (bGluc > 0
				&& (labDAO.getAdmiCtNoVisiSign() > 0 || admCtHyperSign > 0
						|| admCtEarlyInfarct > 0
						|| labDAO.getAdmiCtBleeding() > 0
						|| labDAO.getAdmiCtIscheLess() > 0
						|| labDAO.getAdmiCtIscheMore() > 0
						|| labDAO.getAdmiCtTumor() > 0
						|| labDAO.getAdmiCtAbscess() > 0 || labDAO
						.getAdmiCtOther() > 0) && age > 0
				&& (nihssTestTime != null && nihssTestTime.length() > 0)) {
			sedan = 0;
			if (bGluc >= 8.1 && bGluc <= 12.0) {
				sedan += 1;
			} else if (bGluc > 12.0) {
				sedan += 2;
			}

			if (admCtHyperSign > 0) {
				sedan += 1;
			}
			if (admCtEarlyInfarct > 0) {
				sedan += 1;
			}

			if (age > 75) {
				sedan += 1;
			}

			if (nihssTotal >= 10) {
				sedan += 1;
			}
		}
		return sedan;
	}

	public static int calDRAGON() {
		int dragon = -1;
		float bGluc = labDAO.getBGluc();
		int admCtEarlyInfarct = labDAO.getAdmiCtEarlyInfarct();
		int admCtHyperSign = labDAO.getAdmiCtHyperSign();
		int age = patientDAO.getPatientAge();
		String nihssTestTime = nihssDAO.getNihssBaseline().getTestTime();
		int nihssTotal = nihssDAO.getNihssBaseline().getNihssTotal();
		int mRS = labDAO.getModiRankinScaleId();

		Time time = new Time();
		time.setToNow();
		long onsetTime = timeStampsDAO.getSympOnsetTime();
		if (bGluc > 0
				&& (labDAO.getAdmiCtNoVisiSign() > 0 || admCtHyperSign > 0
						|| admCtEarlyInfarct > 0
						|| labDAO.getAdmiCtBleeding() > 0
						|| labDAO.getAdmiCtIscheLess() > 0
						|| labDAO.getAdmiCtIscheMore() > 0
						|| labDAO.getAdmiCtTumor() > 0
						|| labDAO.getAdmiCtAbscess() > 0 || labDAO
						.getAdmiCtOther() > 0) && age > 0
				&& (nihssTestTime != null && nihssTestTime.length() > 0)
				&& mRS > 0 && onsetTime > 0) {
			dragon = 0;
			if (admCtHyperSign > 0) {
				dragon += 1;
			}
			if (admCtEarlyInfarct > 0) {
				dragon += 1;
			}

			switch (mRS) {
			case R.id.radio_modified_rankin_scale_1:
			case R.id.radio_modified_rankin_scale_2:
			case R.id.radio_modified_rankin_scale_3:
			case R.id.radio_modified_rankin_scale_4:
			case R.id.radio_modified_rankin_scale_5:
			case R.id.radio_modified_rankin_scale_6:
				dragon += 1;
				break;
			}
			
			if (age >= 80) {
				dragon += 2;
			}else if(age >= 65 && age <= 79){
				dragon += 1;
			}
			
			if (bGluc > 8) {
				dragon += 1;
			}

			double timer = AppViewModel.milliToHour(time.toMillis(false)
					- onsetTime);
			if(timer > 1.5){
				dragon += 1;
			}

			if (nihssTotal > 15) {
				dragon += 3;
			}else if (nihssTotal >= 10 && nihssTotal <= 15) {
				dragon += 2;
			}else if(nihssTotal >= 5 && nihssTotal <= 9){
				dragon += 1;
			}
		}
		return dragon;
	}

	/** DD/MM/YY HH:MM */
	public static String timeToString(long t) {
		String timeStr = "";
		if (t > 0) {
			Time time = new Time();
			time.set(t);
			timeStr = time.monthDay + "." + (time.month + 1) + "." + time.year
					+ " " + time.hour + ":" + time.minute;
		}
		return timeStr;
	}

	/** HH:MM:SS */
	public static String milliToString(long time) {
		String timeString = "";
		time = time / 1000;
		long hour = time / 3600;
		long minute = (time % 3600) / 60;
		long second = (time % 3600) % 60;

		if (hour >= 0) {
			timeString += hour + ":";
		}
		if (minute >= 0) {
			timeString += minute + ":";
		}
		if (second >= 0) {
			timeString += second;
		}

		return timeString;
	}

	/** Get onset time in hour (e.g. 1.5 h)*/
	public static double milliToHour(long time) {
		time = time / 1000;
		double timeH = (double) time / 3600;
		return timeH;
	}

}
