package fi.etla.strokehelper.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.R.id;
import fi.etla.strokehelper.authentification.DialogLoginActivity;
import fi.etla.strokehelper.common.CommonPatientInfo.CommonPatientInfoCallBack;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.common.TreatmentProgressBar.TreatProgressCallBack;
import fi.etla.strokehelper.common.TreatmentProgressBar;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.data.DoctorUserTableDAO;
import fi.etla.strokehelper.data.PatientTableDAO;
import fi.etla.strokehelper.editinginfo.DialogEditingTimes;
import fi.etla.strokehelper.main.FragmentMenusLevOne.MenusLevOneCallbacks;
import fi.etla.strokehelper.menu.MenusLevOne;
import fi.etla.strokehelper.patient.FragmentPatient;
import fi.etla.strokehelper.process.DialogInterruptProcess;
import fi.etla.strokehelper.process.FragmentAnamnesis;
import fi.etla.strokehelper.process.FragmentDoor;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.process.FragmentNihssReport;
import fi.etla.strokehelper.process.FragmentNihssTest;
import fi.etla.strokehelper.process.FragmentPresentConditions;
import fi.etla.strokehelper.process.FragmentProcessMenus;
import fi.etla.strokehelper.process.FragmentSymptoms;
import fi.etla.strokehelper.process.FragmentNihssReport.NihssCallback;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;
import fi.etla.strokehelper.summary.FragmentSummary;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision.JumpTabCallBack;
import fi.etla.strokehelper.thrombolysis.DialogFinishFollowUp;
import fi.etla.strokehelper.thrombolysis.DialogInterruptTreatment;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisMenus;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisMenus.ThrombolysisMenusCallbacks;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		MenusLevOneCallbacks, ProcessMenusCallbacks,
		ThrombolysisMenusCallbacks, NihssCallback, OnChronometerTickListener,
		UpdateTimerCallBack, OnItemSelectedListener, TreatProgressCallBack,
		CommonPatientInfoCallBack, JumpTabCallBack {

	public static final String TAG_CURRENT_PATIENT = "curr_patient";

	public static final int REQUEST_CODE_FINISH_FOLLOW_UP = 1;
	public static final int REQUEST_CODE_COMMOM_INTERRUPT = 2;
	public static final int REQUEST_CODE_INTERRUPT_TREAT = 3;
	public static final int REQUEST_CODE_INTERRUPT_PROCESS = 4;
	public final static int REQUEST_CODE_LAB_ITEMS = 10;
	public final static int REQUEST_CODE_DECISION_CONFIRM = 5;
	public final static int REQUEST_CODE_DIRECT_THROMBOLYSIS = 6;

	public final static int RESULT_INTERRUPT_TREAT_YES = 1;
	public final static int RESULT_FINISH_FOLLOW_UP = 2;
	public final static int RESULT_COMMOM_INTERRUPT_YES = 3;
	public final static int RESULT_INTERRUPT_PROCESS_YES = 4;

	/** Whether two-pane mode */
	private boolean mTwoPane;
	private FragmentMenusLevOne menuFragment;

	private PatientSpinnerAdapter patientsAdapter;
	private Cursor patientsCursor;

	public static AppViewModel appViewModel;

	/** Progress bar **/
	private TreatmentProgressBar treatProgressBar;

	/** Timer from onset time */
	private Chronometer onsetTimer;
	private boolean isLogOut;

	/** Fragments for tracking current attached fragment */
	private Fragment currFragment;
	private Fragment currHeadFragment;

	private Spinner selectPatient;
	private MenuItem interrProcessMenu;
	private MenuItem interrTreatMenu;
	private MenuItem editingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		onsetTimer = (Chronometer) findViewById(R.id.chronometer_timer);
		onsetTimer.setOnChronometerTickListener(this);

		/** Treatment progress Bar */
		treatProgressBar = new TreatmentProgressBar(
				(View) findViewById(R.id.status_bar), this);

		if (appViewModel == null) {
			appViewModel = new AppViewModel(MainActivity.this);
		}

		String currUser = getIntent().getStringExtra(
				DialogLoginActivity.TAG_CURRENT_USER);
		if (currUser.length() > 0)
			AppViewModel.userDAO.fetchDoctorUser(currUser);

		long currPatId = getIntent().getLongExtra(TAG_CURRENT_PATIENT, -1);
		if (currPatId != -1) {
			AppViewModel.patientDAO.setPatientId(currPatId);
			appViewModel.loadDataFromDB();
		}

		treatProgressBar.setLights();

		/** Two-pane mode */
		if (findViewById(R.id.item_detail_container) != null) {
			mTwoPane = true;
			menuFragment = ((FragmentMenusLevOne) getSupportFragmentManager()
					.findFragmentById(R.id.item_list));
			menuFragment.setActivateOnItemClick(true);

			/** Default select first item from the menu */
			if (savedInstanceState == null) {
				int position = 0;
				menuFragment.getListView().requestFocusFromTouch();
				menuFragment.getListView().setSelection(position);
				menuFragment.getListView().performItemClick(
						menuFragment.getListView().getAdapter()
								.getView(position, null, null), position,
						position);
			}
		}

		setTimer();

		isLogOut = false;
	}

	/** Load users data to Spinner */
	public void loadPatientsData(Spinner spinner) {

		patientsCursor = AppViewModel.patientDAO.fetchAllPatients();

		if (patientsCursor != null) {
			/** Spinner Adapter */
			patientsAdapter = new PatientSpinnerAdapter(this,
					R.layout.item_spinner, patientsCursor, false);
			// (MainActivity.this,
			// android.R.layout.simple_spinner_item, patientsCursor,
			// new String[] { DataBaseManage.COLUMN_DATE_OF_BIRTH },
			// new int[] { android.R.id.text1 }, 0);
			patientsAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(patientsAdapter);
		} else if (patientsCursor == null) {

		}
	}

	public void setTimer() {
		int onsetQuaid = AppViewModel.timeStampsDAO.getSympOnsetQuaId();
		long onsetTime = AppViewModel.timeStampsDAO.getSympOnsetTime();
		Time time = new Time();
		time.setToNow();

		if (AppViewModel.patientDAO.getPatientId() != -1
				&& onsetQuaid != R.id.radio_unknown && onsetQuaid != 0
				&& onsetTime > 0) {
			onsetTimer.setBase(onsetTime);
			onsetTimer.setVisibility(Chronometer.VISIBLE);
			onsetTimer.start();
		} else {
			onsetTimer.setVisibility(Chronometer.GONE);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_INTERRUPT_TREAT) {
			if (resultCode == RESULT_INTERRUPT_TREAT_YES) {
				jumpToTab(0);
			}
		} else if (requestCode == REQUEST_CODE_FINISH_FOLLOW_UP) {
			if (resultCode == RESULT_FINISH_FOLLOW_UP) {
				treatProgressBar.setLights();
				jumpToTab(0);
			}
		} else if (requestCode == REQUEST_CODE_COMMOM_INTERRUPT) {
			if (resultCode == RESULT_COMMOM_INTERRUPT_YES) {
				jumpToTab(0);
			}
		} else if (requestCode == REQUEST_CODE_INTERRUPT_PROCESS) {
			if (resultCode == RESULT_INTERRUPT_PROCESS_YES) {
				jumpToTab(0);
			}
		} 
		/** Any time stamps has been updated */
		else if (requestCode == DialogEditingTimes.EDITING_TIME_REQUEST){
			if(resultCode == DialogEditingTimes.EDITING_TIME_RESULT_YES){
				//If any timestamps have been modified, update the fragment 
				updateCurrentFrag();
				setTimer();
			}
		}
	}// onActivityResult

	@Override
	protected void onDestroy() {
		if (!isLogOut) {
			appViewModel.saveData();
			DataBaseManage.backupDbToSDCard();
			appViewModel.closeDataBase();
			appViewModel = null;
		}
		super.onDestroy();
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if (fragment.getId() == R.id.item_detail_container)
			currFragment = fragment;
		else if (fragment.getId() == R.id.item_top_fragment)
			currHeadFragment = fragment;
	}

	/** Update Current fragment when new patient is selected from the spinner */
	public void updateCurrentFrag() {
		// Fragment currFragment =
		// getSupportFragmentManager().findFragmentById(R.id.item_top_fragment);
		if (currFragment != null) {
			if (currFragment instanceof FragmentPatient) {
				// FragmentPatient fragPat = (FragmentPatient) currFragment;
				((FragmentPatient) currFragment).loadUISatus(false);
			} else if (currFragment instanceof FragmentDoor) {
				((FragmentDoor) currFragment).loadUISatus(false);
			} else if (currFragment instanceof FragmentSymptoms) {
				((FragmentSymptoms) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentNihssReport) {
				((FragmentNihssReport) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentNihssTest) {
				((FragmentNihssTest) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentLab) {
				((FragmentLab) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentPresentConditions) {
				((FragmentPresentConditions) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentAnamnesis) {
				((FragmentAnamnesis) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentThrombolysisDecision) {
				((FragmentThrombolysisDecision) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentThrombolysisFollowUp) {
				((FragmentThrombolysisFollowUp) currFragment).loadUISatus();
			} else if (currFragment instanceof FragmentSummary) {
				((FragmentSummary) currFragment).loadUISatus();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.action_menus, menu);
		View view = menu.findItem(R.id.option_select_patient).getActionView();
		selectPatient = (Spinner) view
				.findViewById(R.id.spinner_select_patient);
		loadPatientsData(selectPatient);
		selectPatient.post(new Runnable() {
			public void run() {
				selectPatient.setOnItemSelectedListener(MainActivity.this);
			}
		});

		interrProcessMenu = (MenuItem) menu
				.findItem(R.id.option_interrupt_process);
		interrTreatMenu = (MenuItem) menu
				.findItem(R.id.option_interrupt_treatment);

		editingMenu = (MenuItem) menu.findItem(R.id.option_editing_time);
		/** Updating visibility of editing menu */
		if (editingMenu != null) {

			if (AppViewModel.patientDAO.getPatientId() != -1)
				editingMenu.setVisible(true);
			else
				editingMenu.setVisible(false);
		}
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/** Log out, Back to Log in */
		case R.id.option_logout:
			DialogLoginActivity.accountPreferenceEditor.putString(
					DialogLoginActivity.TAG_LAST_LOG_IN_USER, "");
			setCurrPatient(-1);
			Intent loginIntent = new Intent(MainActivity.this,
					DialogLoginActivity.class);
			loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appViewModel.saveData();
			DataBaseManage.backupDbToSDCard();
			appViewModel.closeDataBase();
			appViewModel = null;
			isLogOut = true;
			MainActivity.this.startActivity(loginIntent);
			MainActivity.this.finish();
			break;
		case R.id.option_interrupt_treatment:
			new AlertDialog.Builder(MainActivity.this)
					.setTitle(R.string.title_confirm_interrupt_treat)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									Intent interruptIntent = new Intent(
											MainActivity.this,
											DialogInterruptTreatment.class);
									MainActivity.this.startActivityForResult(
											interruptIntent,
											REQUEST_CODE_INTERRUPT_TREAT);
									dialog.cancel();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
			break;
		case R.id.option_interrupt_process:
			new AlertDialog.Builder(MainActivity.this)
					.setTitle(R.string.title_confirm_interrupt_process)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									Intent interruptIntent = new Intent(
											MainActivity.this,
											DialogInterruptProcess.class);
									MainActivity.this.startActivityForResult(
											interruptIntent,
											REQUEST_CODE_INTERRUPT_PROCESS);
									dialog.cancel();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
			break;
		case R.id.option_editing_time:
			Intent intent = new Intent(this, DialogEditingTimes.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivityForResult(intent, DialogEditingTimes.EDITING_TIME_REQUEST);
			break;
		}
		return (super.onOptionsItemSelected(item));
	}

	/**
	 * Callback method from {@link FragmentMenusLevOne.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			/** Action bar menu */
			if (interrProcessMenu != null) {
				interrProcessMenu.setVisible(false);
				// interrProcessMenu
				// .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			}
			if (interrTreatMenu != null) {
				interrTreatMenu.setVisible(false);
				// interrTreatMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			}

			/** Main menu */
			MenusLevOne.MenuItem menu = MenusLevOne.MENU_MAP_LEVEL_ONE.get(id);
			if (menu.content.equals("Patient")) {
				FragmentPatient fragment = new FragmentPatient();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_top_fragment, new Fragment())
						.commit();

			} else if (menu.content.equals("Process")) {
				if (interrProcessMenu != null
						&& AppViewModel.patientDAO.getPatientId() != -1) {
					interrProcessMenu.setVisible(true);
					// interrProcessMenu
					// .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
				}
				FragmentProcessMenus fragment = new FragmentProcessMenus();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_top_fragment, fragment).commit();
				// ProcessFragment fragment = new ProcessFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, new Fragment())
						.commit();
			} else if (menu.content.equals("Summary")) {
				FragmentSummary fragment = new FragmentSummary();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_top_fragment, new Fragment())
						.commit();
				// ProcessFragment fragment = new ProcessFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			} else if (menu.content.equals("Thrombolysis")) {
				if (interrTreatMenu != null
						&& AppViewModel.patientDAO.getPatientId() != -1) {
					interrTreatMenu.setVisible(true);
					// interrTreatMenu
					// .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
				}
				FragmentThrombolysisMenus fragment = new FragmentThrombolysisMenus();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_top_fragment, fragment).commit();
				// ProcessFragment fragment = new ProcessFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, new Fragment())
						.commit();
			}

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			// detailIntent.putExtra(FragmentPatient.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public void onTabSelected(int id) {
		switch (id) {
		case R.id.tab_door:
			FragmentDoor doorFragment = new FragmentDoor();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, doorFragment).commit();
			break;
		case R.id.tab_anamnesis:
			FragmentAnamnesis anamnesisFragment = new FragmentAnamnesis();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, anamnesisFragment)
					.commit();
			break;
		case R.id.tab_present_condition:
			FragmentPresentConditions presentConditionFragment = new FragmentPresentConditions();
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.item_detail_container,
							presentConditionFragment).commit();
			break;
		case R.id.tab_symptoms:
			FragmentSymptoms symptomFragment = new FragmentSymptoms();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, symptomFragment)
					.commit();
			break;
		case R.id.tab_imaging_testing:
			FragmentLab labFragment = new FragmentLab();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, labFragment).commit();
			break;

		case R.id.tab_nihss:
			FragmentNihssReport nihssFragment = new FragmentNihssReport();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, nihssFragment)
					.commit();
			break;
		/** Thrombolysis Page */
		case R.id.tab_decision:
			FragmentThrombolysisDecision decisionFragment = new FragmentThrombolysisDecision();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, decisionFragment)
					.commit();
			break;
		case R.id.tab_follow_up:
			FragmentThrombolysisFollowUp followUpFragment = new FragmentThrombolysisFollowUp();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, followUpFragment)
					.commit();
			break;
		}
	}

	public static void setCurrPatient(long patId) {
		DialogLoginActivity.accountPreferenceEditor.putLong(
				TAG_CURRENT_PATIENT, patId);
		DialogLoginActivity.accountPreferenceEditor.commit();
	}

	@Override
	public void onNihssButtonClicked(int btnId, boolean isFollowUp) {
		switch (btnId) {
		case R.id.imgbtn_start_test:
		case R.id.imgbtn_check_test:
			FragmentNihssTest nihssTest = new FragmentNihssTest();
			nihssTest.setNihssType(0);
			nihssTest.setIsFollowUp(false);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, nihssTest).commit();
			break;
		case R.id.imgbtn_nihss_0h:
			FragmentNihssTest nihssTest0H = new FragmentNihssTest();
			nihssTest0H.setNihssType(0);
			nihssTest0H.setIsFollowUp(true);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, nihssTest0H).commit();
			break;
		case R.id.imgbtn_nihss_1h:
			FragmentNihssTest nihssTest1H = new FragmentNihssTest();
			nihssTest1H.setNihssType(1);
			nihssTest1H.setIsFollowUp(true);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, nihssTest1H).commit();
			break;
		case R.id.imgbtn_nihss_24h:
			FragmentNihssTest nihssTest24H = new FragmentNihssTest();
			nihssTest24H.setNihssType(24);
			nihssTest24H.setIsFollowUp(true);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, nihssTest24H).commit();
			break;
		case R.id.imgbtn_finish:
		case R.id.imgbtn_back:
			if (!isFollowUp) {
				FragmentNihssReport nihssFragment = new FragmentNihssReport();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, nihssFragment)
						.commit();
			} else if (isFollowUp) {
				FragmentThrombolysisFollowUp followUpFragment = new FragmentThrombolysisFollowUp();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, followUpFragment)
						.commit();
			}
			break;
		}
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
		Time time = new Time();
		time.setToNow();
		// Test
		long base = chronometer.getBase();
		long timerTime = time.toMillis(false) - base;
		double timeDiff = AppViewModel.milliToHour(timerTime);

		timerTime = timerTime / 1000;

		/** To set the format of numbers of the Timer */
		NumberFormat numberFormatter = new DecimalFormat("00");
		/** Timer value, in 'HH:MM:SS' format */
		String timerText = numberFormatter.format(timerTime / 3600) + ":"
				+ numberFormatter.format((timerTime % 3600) / 60) + ":"
				+ numberFormatter.format((timerTime % 3600) % 60);

		/** Show the value on the screen */
		chronometer.setFormat(timerText);

		int timerColor = getResources().getColor(R.color.dark_green);
		if (timeDiff > 0 && timeDiff < 2)
			timerColor = getResources().getColor(R.color.dark_green);
		else if (timeDiff >= 2 && timeDiff < 3.5)
			timerColor = getResources().getColor(R.color.green);
		else if (timeDiff >= 3.5 && timeDiff < 4.5)
			timerColor = getResources().getColor(R.color.yellow);
		else if (timeDiff >= 4.5)
			timerColor = getResources().getColor(R.color.red);

		chronometer.setBackgroundColor(timerColor);
	}

	@Override
	public void updateTimer(boolean isStop) {
		if (!isStop) {
			setTimer();
		} else {
			onsetTimer.stop();
			onsetTimer.setVisibility(Chronometer.GONE);
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		if (patientsCursor.moveToPosition(position)) {
			int selectPatId = patientsCursor.getInt(patientsCursor
					.getColumnIndexOrThrow(DataBaseManage.COLUMN_ID));

			if (selectPatId != -1) {
				// int menuPos = 0;
				// menuFragment.getListView().requestFocusFromTouch();
				// menuFragment.getListView().setSelection(menuPos);
				// menuFragment.getListView()
				// .performItemClick(
				// menuFragment.getListView().getAdapter()
				// .getView(menuPos, null, null), menuPos,
				// menuPos);
				if (AppViewModel.patientDAO.getPatientId() >= 0) {
					MainActivity.appViewModel.saveData();
				}
				MainActivity.appViewModel.initPatientRelateDAOs();
				MainActivity.setCurrPatient(selectPatId);
				AppViewModel.patientDAO.setPatientId(selectPatId);
				appViewModel.loadDataFromDB();
				updateCurrentFrag();
				setTimer();
				patientsCursor = AppViewModel.patientDAO.fetchAllPatients();
				patientsAdapter.changeCursor(patientsCursor);
				treatProgressBar.setLights();

				/** Updating visibility of editing menu */
				if (editingMenu != null) {

					if (AppViewModel.patientDAO.getPatientId() != -1)
						editingMenu.setVisible(true);
					else
						editingMenu.setVisible(false);
				}
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void updateProgressBar() {
		treatProgressBar.setLights();
	}

	@Override
	public void jumpToTab(int tabId) {
		switch (tabId) {
		/** Patient tab */
		case 0:
			menuFragment.getListView().requestFocusFromTouch();
			menuFragment.getListView().setSelection(tabId);
			menuFragment.getListView().performItemClick(
					menuFragment.getListView().getAdapter()
							.getView(tabId, null, null), tabId, tabId);
			break;
		case R.id.tab_symptoms:
			break;
		case R.id.tab_follow_up:
			if (currHeadFragment != null) {
				if (currHeadFragment instanceof FragmentThrombolysisMenus)
					((FragmentThrombolysisMenus) currHeadFragment)
							.jumpToFollowUp();
			}
			break;
		}
	}

	/** When new a patient, update the spinner for selecting patient */
	@Override
	public void onNewPatient() {
		Log.i("New Patient", "New Patient(MainPage)");
		selectPatient.setSelection(0);
		patientsCursor = AppViewModel.patientDAO.fetchAllPatients();
		patientsAdapter.changeCursor(patientsCursor);

		/** Updating visibility of editing menu */
		if (editingMenu != null) {
			editingMenu.setVisible(true);
		}
	}

	/** Adapter for the spinner shows all patient in list */
	private class PatientSpinnerAdapter extends ResourceCursorAdapter {
		private int itemlayout;
		private int dropdownLayout;
		private LayoutInflater inflater;

		public PatientSpinnerAdapter(Context context, int layout, Cursor c,
				boolean autoRequery) {
			super(context, layout, c, autoRequery);
			itemlayout = layout;
			dropdownLayout = R.layout.item_spinner_drop_down;

			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View newView(Context context, Cursor cur, ViewGroup parent) {
			return inflater.inflate(itemlayout, parent, false);
		}

		@Override
		public View newDropDownView(Context context, Cursor cursor,
				ViewGroup parent) {
			return inflater.inflate(dropdownLayout, parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cur) {

			TextView birthdayText = (TextView) view
					.findViewById(R.id.spinner_column_birth_date);
			TextView onsetTimeText = (TextView) view
					.findViewById(R.id.spinner_column_onset_time);
			TextView onHospitalDateText = (TextView) view
					.findViewById(R.id.spinner_column_on_hospital_date);
			View separator1 = (View) view.findViewById(R.id.separator_1);
			View separator2 = (View) view.findViewById(R.id.separator_2);

			if (cur.getInt(cur.getColumnIndexOrThrow(DataBaseManage.COLUMN_ID)) != -1) {
				separator1.setVisibility(View.VISIBLE);
				separator2.setVisibility(View.VISIBLE);
				String patId = cur
						.getString(cur
								.getColumnIndexOrThrow(DataBaseManage.COLUMN_DATE_OF_BIRTH));
				Time time = new Time();
				long onsetLong = cur
						.getLong(cur
								.getColumnIndexOrThrow(DataBaseManage.COLUMN_ONSET_TIME));
				// long lastFreeLong = cur
				// .getLong(cur
				// .getColumnIndexOrThrow(DataBaseManage.COLUMN_LAST_SYMPTOMS_FREE_TIME));
				long doorLong = cur
						.getLong(cur
								.getColumnIndexOrThrow(DataBaseManage.COLUMN_DOOR_TIME));

				birthdayText.setText("ID:" + patId);

				String timeStr = "";
				if (onsetLong > 0) {
					time.set(onsetLong);
					timeStr = time.hour + ":" + time.minute + "("
							+ time.monthDay + "." + (time.month + 1) + ")";
				}
				// else if (lastFreeLong > 0) {
				// time.set(lastFreeLong);
				// timeStr = time.hour + ":" + time.minute + "("
				// + time.monthDay + "." + (time.month + 1) + ")";
				// }
				onsetTimeText.setText(timeStr);

				if (doorLong > 0) {
					time.set(doorLong);
					timeStr = "Date." + time.monthDay + "." + (time.month + 1);
					onHospitalDateText.setText(timeStr);
				} else {
					onHospitalDateText.setText("");
				}

			} else {
				separator1.setVisibility(View.GONE);
				separator2.setVisibility(View.GONE);
				birthdayText
						.setText(cur.getString(cur
								.getColumnIndexOrThrow(DataBaseManage.COLUMN_DATE_OF_BIRTH)));
				onsetTimeText.setText("");
				onHospitalDateText.setText("");
			}

		}
	}

}
