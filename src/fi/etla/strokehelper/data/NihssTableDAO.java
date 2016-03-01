package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NihssTableDAO {

	/** DB Manage */
	private SQLiteDatabase sqliteDB;

	private Nihss nihssBaseline;
	private Nihss nihss1H;
	private Nihss nihss24H;

	private boolean isNihssBaseLoaded;
	private boolean isNihss1HLoaded;
	private boolean isNihss24HLoaded;

	public NihssTableDAO(SQLiteDatabase db) {
		sqliteDB = db;
		nihssBaseline = new Nihss();
		nihss1H = new Nihss();
		nihss24H = new Nihss();
		isNihssBaseLoaded = false;
		isNihss1HLoaded = false;
		isNihss24HLoaded = false;
	}

	public void newEmptyNIHSS() {
		/** NIHSS baseline */
		ContentValues initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_NIHSS_TYPE, 0);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		sqliteDB.insert(DataBaseManage.TABLE_NIHSS, null, initialValues);

		/** NIHSS 1H */
		initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_NIHSS_TYPE, 1);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());
		sqliteDB.insert(DataBaseManage.TABLE_NIHSS, null, initialValues);

		/** NIHSS 24H */
		initialValues = new ContentValues();
		initialValues.put(DataBaseManage.COLUMN_NIHSS_TYPE, 24);
		initialValues.put(DataBaseManage.COLUMN_DOCTOR_ID,
				AppViewModel.userDAO.getUserID());
		initialValues.put(DataBaseManage.COLUMN_PATIENT_ID,
				AppViewModel.patientDAO.getPatientId());

		isNihssBaseLoaded = true;
		isNihss1HLoaded = true;
		isNihss24HLoaded = true;
		sqliteDB.insert(DataBaseManage.TABLE_NIHSS, null, initialValues);
	}

	public int updateNihss(int nihssType) {
		Nihss nihss = null;
		if (nihssType == 0)
			nihss = nihssBaseline;
		else if (nihssType == 1)
			nihss = nihss1H;
		else if (nihssType == 24)
			nihss = nihss24H;

		ContentValues values = new ContentValues();
		if (nihss != null) {

			if (nihss.testTime != null && nihss.testTime.length() > 0)
				values.put(DataBaseManage.COLUMN_LOG_TIME, nihss.testTime);

			if (nihss.answer1A != null && nihss.answer1A.length() > 0)
				values.put(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL,
						nihss.answer1A);
			if (nihss.id1A > 0) {
				values.put(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_ID,
						nihss.id1A);
				values.put(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_POINT,
						nihss.point1A);
			}

			if (nihss.answer1B != null && nihss.answer1B.length() > 0)
				values.put(DataBaseManage.COLUMN_1B_LOC_QUESTIONS,
						nihss.answer1B);
			if (nihss.id1B > 0) {
				values.put(DataBaseManage.COLUMN_1B_LOC_QUESTIONS_ID,
						nihss.id1B);
				values.put(DataBaseManage.COLUMN_1B_LOC_QUESTIONS_POINT,
						nihss.point1B);
			}

			if (nihss.answer1C != null && nihss.answer1C.length() > 0)
				values.put(DataBaseManage.COLUMN_1C_LOC_COMMANDS,
						nihss.answer1C);
			if (nihss.id1C > 0) {
				values.put(DataBaseManage.COLUMN_1C_LOC_COMMANDS_ID, nihss.id1C);
				values.put(DataBaseManage.COLUMN_1C_LOC_COMMANDS_POINT,
						nihss.point1C);
			}

			if (nihss.answer2 != null && nihss.answer2.length() > 0)
				values.put(DataBaseManage.COLUMN_2_BEST_GAZE, nihss.answer2);
			if (nihss.id2 > 0) {
				values.put(DataBaseManage.COLUMN_2_BEST_GAZE_ID, nihss.id2);
				values.put(DataBaseManage.COLUMN_2_BEST_GAZE_POINT,
						nihss.point2);
			}

			if (nihss.answer3 != null && nihss.answer3.length() > 0)
				values.put(DataBaseManage.COLUMN_3_VISUAL, nihss.answer3);
			if (nihss.id3 > 0) {
				values.put(DataBaseManage.COLUMN_3_VISUAL_ID, nihss.id3);
				values.put(DataBaseManage.COLUMN_3_VISUAL_POINT, nihss.point3);
			}

			if (nihss.answer4 != null && nihss.answer4.length() > 0)
				values.put(DataBaseManage.COLUMN_4_FACIAL_PALSY, nihss.answer4);
			if (nihss.id1A > 0) {
				values.put(DataBaseManage.COLUMN_4_FACIAL_PALSY_ID, nihss.id4);
				values.put(DataBaseManage.COLUMN_4_FACIAL_PALSY_POINT,
						nihss.point4);
			}

			if (nihss.answer5A != null && nihss.answer5A.length() > 0)
				values.put(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT,
						nihss.answer5A);
			if (nihss.id5A > 0) {
				values.put(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_ID,
						nihss.id5A);
				values.put(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_POINT,
						nihss.point5A);
			}

			if (nihss.answer5B != null && nihss.answer5B.length() > 0)
				values.put(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT,
						nihss.answer5B);
			if (nihss.id5B > 0) {
				values.put(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_ID,
						nihss.id5B);
				values.put(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_POINT,
						nihss.point5B);
			}

			if (nihss.answer6A != null && nihss.answer6A.length() > 0)
				values.put(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT,
						nihss.answer6A);
			if (nihss.id6A > 0) {
				values.put(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_ID,
						nihss.id6A);
				values.put(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_POINT,
						nihss.point6A);
			}

			if (nihss.answer6B != null && nihss.answer6B.length() > 0)
				values.put(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT,
						nihss.answer6B);
			if (nihss.id6B > 0) {
				values.put(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_ID,
						nihss.id6B);
				values.put(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_POINT,
						nihss.point6B);
			}

			if (nihss.answer7 != null && nihss.answer7.length() > 0)
				values.put(DataBaseManage.COLUMN_7_LIMB_ATAXIA, nihss.answer7);
			if (nihss.id7 > 0) {
				values.put(DataBaseManage.COLUMN_7_LIMB_ATAXIA_ID, nihss.id7);
				values.put(DataBaseManage.COLUMN_7_LIMB_ATAXIA_POINT,
						nihss.point7);
			}

			if (nihss.answer8 != null && nihss.answer8.length() > 0)
				values.put(DataBaseManage.COLUMN_8_SENSORY, nihss.answer8);
			if (nihss.id8 > 0) {
				values.put(DataBaseManage.COLUMN_8_SENSORY_ID, nihss.id8);
				values.put(DataBaseManage.COLUMN_8_SENSORY_POINT, nihss.point8);
			}

			if (nihss.answer9 != null && nihss.answer9.length() > 0)
				values.put(DataBaseManage.COLUMN_9_BEST_LANGUAGE, nihss.answer9);
			if (nihss.id9 > 0) {
				values.put(DataBaseManage.COLUMN_9_BEST_LANGUAGE_ID, nihss.id9);
				values.put(DataBaseManage.COLUMN_9_BEST_LANGUAGE_POINT,
						nihss.point9);
			}

			if (nihss.answer10 != null && nihss.answer10.length() > 0)
				values.put(DataBaseManage.COLUMN_10_DYSARTHRIA, nihss.answer10);
			if (nihss.id10 > 0) {
				values.put(DataBaseManage.COLUMN_10_DYSARTHRIA_ID, nihss.id10);
				values.put(DataBaseManage.COLUMN_10_DYSARTHRIA_POINT,
						nihss.point10);
			}

			if (nihss.answer11 != null && nihss.answer11.length() > 0)
				values.put(DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION,
						nihss.answer11);
			if (nihss.id11 > 0) {
				values.put(DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_ID,
						nihss.id11);
				values.put(
						DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_POINT,
						nihss.point11);
			}

			nihss.calTotalScore();
			if (nihss.totalScore >= 0)
				values.put(DataBaseManage.COLUMN_TOTAL, nihss.totalScore);
		}

		// updating row
		if (values.size() > 0) {
			return sqliteDB
					.update(DataBaseManage.TABLE_NIHSS,
							values,
							DataBaseManage.COLUMN_PATIENT_ID + " = ?" + " AND "
									+ DataBaseManage.COLUMN_NIHSS_TYPE + " = ?",
							new String[] {
									String.valueOf(AppViewModel.patientDAO
											.getPatientId()),
									String.valueOf(nihssType) });
		}
		return 0;
	}

	public void fetchNihss(int nihssType) throws SQLException {
		Nihss nihss = null;
		if (nihssType == 0) {
			nihss = nihssBaseline;
			isNihssBaseLoaded = true;
		} else if (nihssType == 1) {
			nihss = nihss1H;
			isNihss1HLoaded = true;
		} else if (nihssType == 24) {
			nihss = nihss24H;
			isNihss24HLoaded = true;
		}

		Cursor cursor =

		sqliteDB.query(
				true,
				DataBaseManage.TABLE_NIHSS,
				new String[] { DataBaseManage.COLUMN_LOG_TIME,
						DataBaseManage.COLUMN_TOTAL,

						DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL,
						DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_ID,
						DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_POINT,

						DataBaseManage.COLUMN_1B_LOC_QUESTIONS,
						DataBaseManage.COLUMN_1B_LOC_QUESTIONS_ID,
						DataBaseManage.COLUMN_1B_LOC_QUESTIONS_POINT,

						DataBaseManage.COLUMN_1C_LOC_COMMANDS,
						DataBaseManage.COLUMN_1C_LOC_COMMANDS_ID,
						DataBaseManage.COLUMN_1C_LOC_COMMANDS_POINT,

						DataBaseManage.COLUMN_2_BEST_GAZE,
						DataBaseManage.COLUMN_2_BEST_GAZE_ID,
						DataBaseManage.COLUMN_2_BEST_GAZE_POINT,

						DataBaseManage.COLUMN_3_VISUAL,
						DataBaseManage.COLUMN_3_VISUAL_ID,
						DataBaseManage.COLUMN_3_VISUAL_POINT,

						DataBaseManage.COLUMN_4_FACIAL_PALSY,
						DataBaseManage.COLUMN_4_FACIAL_PALSY_ID,
						DataBaseManage.COLUMN_4_FACIAL_PALSY_POINT,

						DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT,
						DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_ID,
						DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_POINT,

						DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT,
						DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_ID,
						DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_POINT,

						DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT,
						DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_ID,
						DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_POINT,

						DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT,
						DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_ID,
						DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_POINT,

						DataBaseManage.COLUMN_7_LIMB_ATAXIA,
						DataBaseManage.COLUMN_7_LIMB_ATAXIA_ID,
						DataBaseManage.COLUMN_7_LIMB_ATAXIA_POINT,

						DataBaseManage.COLUMN_8_SENSORY,
						DataBaseManage.COLUMN_8_SENSORY_ID,
						DataBaseManage.COLUMN_8_SENSORY_POINT,

						DataBaseManage.COLUMN_9_BEST_LANGUAGE,
						DataBaseManage.COLUMN_9_BEST_LANGUAGE_ID,
						DataBaseManage.COLUMN_9_BEST_LANGUAGE_POINT,

						DataBaseManage.COLUMN_10_DYSARTHRIA,
						DataBaseManage.COLUMN_10_DYSARTHRIA_ID,
						DataBaseManage.COLUMN_10_DYSARTHRIA_POINT,

						DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION,
						DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_ID,
						DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_POINT, },
				DataBaseManage.COLUMN_PATIENT_ID + " = ?" + " AND "
						+ DataBaseManage.COLUMN_NIHSS_TYPE + " = ?",
				new String[] {
						String.valueOf(AppViewModel.patientDAO.getPatientId()),
						String.valueOf(nihssType) }, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();

			nihss.setTestTime(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_LOG_TIME)));

			nihss.setNihssTotal(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_TOTAL)));

			nihss.setAnswer1A(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL)));
			nihss.setId1A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_ID)));
			nihss.setPoint1A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1A_CONSCIOUSNESS_LEVEL_POINT)));

			nihss.setAnswer1B(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1B_LOC_QUESTIONS)));
			nihss.setId1B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1B_LOC_QUESTIONS_ID)));
			nihss.setPoint1B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1B_LOC_QUESTIONS_POINT)));

			nihss.setAnswer1C(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1C_LOC_COMMANDS)));
			nihss.setId1C(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1C_LOC_COMMANDS_ID)));
			nihss.setPoint1C(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_1C_LOC_COMMANDS_POINT)));

			nihss.setAnswer2(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_2_BEST_GAZE)));
			nihss.setId2(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_2_BEST_GAZE_ID)));
			nihss.setPoint2(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_2_BEST_GAZE_POINT)));

			nihss.setAnswer3(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_3_VISUAL)));
			nihss.setId3(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_3_VISUAL_ID)));
			nihss.setPoint3(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_3_VISUAL_POINT)));

			nihss.setAnswer4(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_4_FACIAL_PALSY)));
			nihss.setId4(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_4_FACIAL_PALSY_ID)));
			nihss.setPoint4(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_4_FACIAL_PALSY_POINT)));

			nihss.setAnswer5A(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT)));
			nihss.setId5A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_ID)));
			nihss.setPoint5A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5A_MOTOR_ARM_RIGHT_POINT)));

			nihss.setAnswer5B(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT)));
			nihss.setId5B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_ID)));
			nihss.setPoint5B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_5B_MOTOR_ARM_LEFT_POINT)));

			nihss.setAnswer6A(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT)));
			nihss.setId6A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_ID)));
			nihss.setPoint6A(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6A_MOTOR_LEG_RIGHT_POINT)));

			nihss.setAnswer6B(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT)));
			nihss.setId6B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_ID)));
			nihss.setPoint6B(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_6B_MOTOR_LEG_LEFT_POINT)));

			nihss.setAnswer7(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_7_LIMB_ATAXIA)));
			nihss.setId7(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_7_LIMB_ATAXIA_ID)));
			nihss.setPoint7(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_7_LIMB_ATAXIA_POINT)));

			nihss.setAnswer8(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_8_SENSORY)));
			nihss.setId8(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_8_SENSORY_ID)));
			nihss.setPoint8(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_8_SENSORY_POINT)));

			nihss.setAnswer9(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_9_BEST_LANGUAGE)));
			nihss.setId9(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_9_BEST_LANGUAGE_ID)));
			nihss.setPoint9(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_9_BEST_LANGUAGE_POINT)));

			nihss.setAnswer10(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_10_DYSARTHRIA)));
			nihss.setId10(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_10_DYSARTHRIA_ID)));
			nihss.setPoint10(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_10_DYSARTHRIA_POINT)));

			nihss.setAnswer11(cursor.getString(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION)));
			nihss.setId11(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_ID)));
			nihss.setPoint11(cursor.getInt(cursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_11_EXTINCTION_INATTENTION_POINT)));
		}
	}

	public Nihss getNihssBaseline() {
		return nihssBaseline;
	}

	public Nihss getNihss1H() {
		return nihss1H;
	}

	public Nihss getNihss24H() {
		return nihss24H;
	}

	public boolean isNihssLoaded(int type) {
		boolean loaded = false;
		switch (type) {
		case 0:
			loaded = isNihssBaseLoaded;
			break;
		case 1:
			loaded = isNihss1HLoaded;
			break;
		case 24:
			loaded = isNihss24HLoaded;
			break;
		}
		return loaded;
	}

	public boolean isNihssBaseComplete() {
		boolean isComplete = true;
		if (nihssBaseline.testTime == null
				|| (nihssBaseline.testTime != null && nihssBaseline.testTime
						.length() <= 0))
			isComplete = false;
		return isComplete;
	}

	public void summarizeContraindications() {
		if (nihssBaseline.testTime != null
				&& nihssBaseline.testTime.length() > 0) {
			if (nihssBaseline.totalScore >= 0 && nihssBaseline.totalScore <= 2)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_5));
			double timer = AppViewModel.milliToHour(AppViewModel.timeStampsDAO
					.getTimer());
			if (nihssBaseline.totalScore >= 26
					&& nihssBaseline.totalScore <= 42 && timer >= 3
					&& timer <= 4.5)
				AppViewModel.relaContras.add(AppViewModel.appActivity
						.getString(R.string.info_contrain_relative_6));
		}
	}

	public void summarizeIndications() {
		if (nihssBaseline.testTime != null
				&& nihssBaseline.testTime.length() > 0) {
			double timer = AppViewModel.milliToHour(AppViewModel.timeStampsDAO
					.getTimer());
			int totalScore = nihssBaseline.totalScore;

			if (timer < 3 && totalScore >= 3) {
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_indication_1)
						+ " "
						+ totalScore);
			} else if (timer >= 3 && timer <= 4.5
					&& nihssBaseline.totalScore >= 3 && totalScore <= 24) {
				AppViewModel.criticalIndications.add(AppViewModel.appActivity
						.getString(R.string.info_indication_2)
						+ " "
						+ totalScore);
			}
			
			/** Question 1A */
			int id = nihssBaseline.getId1A();
			if (id == R.id.radio_1a_1 || id == R.id.radio_1a_2
					|| id == R.id.radio_1a_3) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_1a)
						+ ": "
						+ nihssBaseline.getAnswer1A());
			}
			/** Question 1B */
			id = nihssBaseline.getId1B();
			if (id == R.id.radio_1b_1 || id == R.id.radio_1b_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_1b)
						+ ": "
						+ nihssBaseline.getAnswer1B());
			}
			/** Question 1C */
			id = nihssBaseline.getId1C();
			if (id == R.id.radio_1c_1 || id == R.id.radio_1c_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_1c)
						+ ": "
						+ nihssBaseline.getAnswer1C());
			}
			/** Question 2 */
			id = nihssBaseline.getId2();
			if (id == R.id.radio_2_1 || id == R.id.radio_2_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_2)
						+ ": "
						+ nihssBaseline.getAnswer2());
			}
			/** Question 3 */
			id = nihssBaseline.getId3();
			if (id == R.id.radio_3_1 || id == R.id.radio_3_2
					|| id == R.id.radio_3_3) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_3)
						+ ": "
						+ nihssBaseline.getAnswer3());
			}
			/** Question 4 */
			id = nihssBaseline.getId4();
			if (id == R.id.radio_4_1 || id == R.id.radio_4_2
					|| id == R.id.radio_4_3) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_4)
						+ ": "
						+ nihssBaseline.getAnswer4());
			}
			/** Question 5a */
			id = nihssBaseline.getId5A();
			if (id == R.id.radio_5a_1 || id == R.id.radio_5a_2
					|| id == R.id.radio_5a_3 || id == R.id.radio_5a_4) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_5a)
						+ ": "
						+ nihssBaseline.getAnswer5A());
			}
			/** Question 5b */
			id = nihssBaseline.getId5B();
			if (id == R.id.radio_5b_1 || id == R.id.radio_5b_2
					|| id == R.id.radio_5b_3 || id == R.id.radio_5b_4) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_5b)
						+ ": "
						+ nihssBaseline.getAnswer5B());
			}
			/** Question 6a */
			id = nihssBaseline.getId6A();
			if (id == R.id.radio_6a_1 || id == R.id.radio_6a_2
					|| id == R.id.radio_6a_3 || id == R.id.radio_6a_4) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_6a)
						+ ": "
						+ nihssBaseline.getAnswer6A());
			}
			/** Question 6b */
			id = nihssBaseline.getId6B();
			if (id == R.id.radio_6b_1 || id == R.id.radio_6b_2
					|| id == R.id.radio_6b_3 || id == R.id.radio_6b_4) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_6b)
						+ ": "
						+ nihssBaseline.getAnswer6B());
			}
			/** Question 7 */
			id = nihssBaseline.getId7();
			if (id == R.id.radio_7_1 || id == R.id.radio_7_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_7)
						+ ": "
						+ nihssBaseline.getAnswer7());
			}
			/** Question 8 */
			id = nihssBaseline.getId8();
			if (id == R.id.radio_8_1 || id == R.id.radio_8_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_8)
						+ ": "
						+ nihssBaseline.getAnswer8());
			}
			/** Question 9 */
			id = nihssBaseline.getId9();
			if (id == R.id.radio_9_1 || id == R.id.radio_9_2 || id == R.id.radio_9_3) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_9)
						+ ": "
						+ nihssBaseline.getAnswer9());
			}
			/** Question 10 */
			id = nihssBaseline.getId10();
			if (id == R.id.radio_10_1 || id == R.id.radio_10_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_10)
						+ ": "
						+ nihssBaseline.getAnswer10());
			}
			/** Question 11 */
			id = nihssBaseline.getId11();
			if (id == R.id.radio_11_1 || id == R.id.radio_11_2) {
				AppViewModel.supportiveIndications.add(AppViewModel.appActivity
						.getString(R.string.nihss_question_11)
						+ ": "
						+ nihssBaseline.getAnswer11());
			}
		}
	}

	public static class Nihss {
		private String testTime;

		private int totalScore;

		private String answer1A;
		private int id1A;
		private int point1A;

		private String answer1B;
		private int id1B;
		private int point1B;

		private String answer1C;
		private int id1C;
		private int point1C;

		private String answer2;
		private int id2;
		private int point2;

		private String answer3;
		private int id3;
		private int point3;

		private String answer4;
		private int id4;
		private int point4;

		private String answer5A;
		private int id5A;
		private int point5A;

		private String answer5B;
		private int id5B;
		private int point5B;

		private String answer6A;
		private int id6A;
		private int point6A;

		private String answer6B;
		private int id6B;
		private int point6B;

		private String answer7;
		private int id7;
		private int point7;

		private String answer8;
		private int id8;
		private int point8;

		private String answer9;
		private int id9;
		private int point9;

		private String answer10;
		private int id10;
		private int point10;

		private String answer11;
		private int id11;
		private int point11;

		public Nihss() {
			testTime = "";
			totalScore = 0;
			answer1A = "";
			id1A = -1;
			point1A = 0;
			answer1B = "";
			id1B = -1;
			point1B = 0;
			answer1C = "";
			id1C = -1;
			point1C = 0;
			answer2 = "";
			id2 = -1;
			point2 = 0;
			answer3 = "";
			id3 = -1;
			point3 = 0;
			answer4 = "";
			id4 = -1;
			point4 = 0;
			answer5A = "";
			id5A = -1;
			point5A = 0;
			answer5B = "";
			id5B = -1;
			point5B = 0;
			answer6A = "";
			id6A = -1;
			point6A = 0;
			answer6B = "";
			id6B = -1;
			point6B = 0;
			answer7 = "";
			id7 = -1;
			point7 = 0;
			answer8 = "";
			id8 = -1;
			point8 = 0;
			answer9 = "";
			id9 = -1;
			point9 = 0;
			answer10 = "";
			id10 = -1;
			point10 = 0;
			answer11 = "";
			id11 = -1;
			point11 = 0;
		}

		public void setTestTime(String time) {
			testTime = time;
		}

		public String getTestTime() {
			return testTime;
		}

		public void calTotalScore() {
			totalScore = 0;
			totalScore += point1A + point1B + point1C + point2 + point3
					+ point4 + point5A + point5B + point6A + point6B + point7
					+ point8 + point9 + point10 + point11;
		}

		public void setNihssTotal(int total) {
			totalScore = total;
		}

		public int getNihssTotal() {
			return totalScore;
		}

		public void setAnswer1A(String answer) {
			answer1A = answer;
		}

		public void setId1A(int id) {
			id1A = id;
		}

		public void setPoint1A(int point) {
			point1A = point;
		}

		public String getAnswer1A() {
			return answer1A;
		}

		public int getId1A() {
			return id1A;
		}

		public int getPoint1A() {
			return point1A;
		}

		public void setAnswer1B(String answer) {
			answer1B = answer;
		}

		public void setId1B(int id) {
			id1B = id;
		}

		public void setPoint1B(int point) {
			point1B = point;
		}

		public String getAnswer1B() {
			return answer1B;
		}

		public int getId1B() {
			return id1B;
		}

		public int getPoint1B() {
			return point1B;
		}

		public void setAnswer1C(String answer) {
			answer1C = answer;
		}

		public void setId1C(int id) {
			id1C = id;
		}

		public void setPoint1C(int point) {
			point1C = point;
		}

		public String getAnswer1C() {
			return answer1C;
		}

		public int getId1C() {
			return id1C;
		}

		public int getPoint1C() {
			return point1C;
		}

		public void setAnswer2(String answer) {
			answer2 = answer;
		}

		public void setId2(int id) {
			id2 = id;
		}

		public void setPoint2(int point) {
			point2 = point;
		}

		public String getAnswer2() {
			return answer2;
		}

		public int getId2() {
			return id2;
		}

		public int getPoint2() {
			return point2;
		}

		public void setAnswer3(String answer) {
			answer3 = answer;
		}

		public void setId3(int id) {
			id3 = id;
		}

		public void setPoint3(int point) {
			point3 = point;
		}

		public String getAnswer3() {
			return answer3;
		}

		public int getId3() {
			return id3;
		}

		public int getPoint3() {
			return point3;
		}

		public void setAnswer4(String answer) {
			answer4 = answer;
		}

		public void setId4(int id) {
			id4 = id;
		}

		public void setPoint4(int point) {
			point4 = point;
		}

		public String getAnswer4() {
			return answer4;
		}

		public int getId4() {
			return id4;
		}

		public int getPoint4() {
			return point4;
		}

		public void setAnswer5A(String answer) {
			answer5A = answer;
		}

		public void setId5A(int id) {
			id5A = id;
		}

		public void setPoint5A(int point) {
			point5A = point;
		}

		public String getAnswer5A() {
			return answer5A;
		}

		public int getId5A() {
			return id5A;
		}

		public int getPoint5A() {
			return point5A;
		}

		public void setAnswer5B(String answer) {
			answer5B = answer;
		}

		public void setId5B(int id) {
			id5B = id;
		}

		public void setPoint5B(int point) {
			point5B = point;
		}

		public String getAnswer5B() {
			return answer5B;
		}

		public int getId5B() {
			return id5B;
		}

		public int getPoint5B() {
			return point5B;
		}

		public void setAnswer6A(String answer) {
			answer6A = answer;
		}

		public void setId6A(int id) {
			id6A = id;
		}

		public void setPoint6A(int point) {
			point6A = point;
		}

		public String getAnswer6A() {
			return answer6A;
		}

		public int getId6A() {
			return id6A;
		}

		public int getPoint6A() {
			return point6A;
		}

		public void setAnswer6B(String answer) {
			answer6B = answer;
		}

		public void setId6B(int id) {
			id6B = id;
		}

		public void setPoint6B(int point) {
			point6B = point;
		}

		public String getAnswer6B() {
			return answer6B;
		}

		public int getId6B() {
			return id6B;
		}

		public int getPoint6B() {
			return point6B;
		}

		public void setAnswer7(String answer) {
			answer7 = answer;
		}

		public void setId7(int id) {
			id7 = id;
		}

		public void setPoint7(int point) {
			point7 = point;
		}

		public String getAnswer7() {
			return answer7;
		}

		public int getId7() {
			return id7;
		}

		public int getPoint7() {
			return point7;
		}

		public void setAnswer8(String answer) {
			answer8 = answer;
		}

		public void setId8(int id) {
			id8 = id;
		}

		public void setPoint8(int point) {
			point8 = point;
		}

		public String getAnswer8() {
			return answer8;
		}

		public int getId8() {
			return id8;
		}

		public int getPoint8() {
			return point8;
		}

		public void setAnswer9(String answer) {
			answer9 = answer;
		}

		public void setId9(int id) {
			id9 = id;
		}

		public void setPoint9(int point) {
			point9 = point;
		}

		public String getAnswer9() {
			return answer9;
		}

		public int getId9() {
			return id9;
		}

		public int getPoint9() {
			return point9;
		}

		public void setAnswer10(String answer) {
			answer10 = answer;
		}

		public void setId10(int id) {
			id10 = id;
		}

		public void setPoint10(int point) {
			point10 = point;
		}

		public String getAnswer10() {
			return answer10;
		}

		public int getId10() {
			return id10;
		}

		public int getPoint10() {
			return point10;
		}

		public void setAnswer11(String answer) {
			answer11 = answer;
		}

		public void setId11(int id) {
			id11 = id;
		}

		public void setPoint11(int point) {
			point11 = point;
		}

		public String getAnswer11() {
			return answer11;
		}

		public int getId11() {
			return id11;
		}

		public int getPoint11() {
			return point11;
		}

		public boolean isTestCompleted() {
			boolean complete = false;
			if (id1A > 0 && id1B > 0 && id1C > 0 && id2 > 0 && id3 > 0
					&& id4 > 0 && id5A > 0 && id5B > 0 && id6A > 0 && id6B > 0
					&& id7 > 0 && id8 > 0 && id9 > 0 && id10 > 0 && id11 > 0) {
				complete = true;
			}
			return complete;
		}
	}
}
