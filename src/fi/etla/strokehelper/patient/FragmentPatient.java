package fi.etla.strokehelper.patient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonBirthAndOnset;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.common.CommonEstimateArrival;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.CommonSymptoms;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.common.CommonPatientInfo.CommonPatientInfoCallBack;
import fi.etla.strokehelper.common.TreatmentProgressBar.TreatProgressCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;

public class FragmentPatient extends Fragment implements
		CommonPatientInfoCallBack, OnCheckedChangeListener, OnClickListener {

	/** UI elements */
	private RadioGroup selectPatTypeView;
	private View detailTableView;

	private RadioButton preDoorRadio;
	private RadioButton doorRadio;
	private RadioButton postDoorRadio;

	private RadioGroup candidateRadioGroup;
	private RadioButton candidateYesRadio;
	private RadioButton candidateNoRadio;

	private TextView candidateComple;

	private Button saveBtn;

	private CommonPatientInfo patientInfoCommon;
	private CommonBirthAndOnset birthAndOnsetCommon;
	private CommonSymptoms symptomsPart;
	private View thromboCandidateView;
	private CommonEstimateArrival estimateArrivalCommon;

	private UpdateTimerCallBack updateTimerCallBack;
	private CommonPatientInfoCallBack newPatientCallBack;
	private TreatProgressCallBack treatProgressCallBack;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentPatient() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail_patient,
				container, false);

		initUIElements(rootView);
		if (AppViewModel.patientDAO.getPatientId() == -1) {
			loadUISatus(true);
		} else if (AppViewModel.patientDAO.getPatientId() != -1) {
			if (AppViewModel.patientDAO.isPatientInfoLoaded() == false)
				AppViewModel.patientDAO.fetchPatient();
			loadUISatus(false);
		}
		return rootView;
	}

	@Override
	public void onDestroy() {
		birthAndOnsetCommon.getBirthDatePickerData();
		birthAndOnsetCommon.getOnsetTimePickerData();
		estimateArrivalCommon.getEstiTimePickerData();
		super.onDestroy();
	}

	public void initUIElements(View rootView) {
		/** Views */
		selectPatTypeView = (RadioGroup) rootView.findViewById(R.id.row_two);
		detailTableView = (View) rootView.findViewById(R.id.row_three);

		/** Patient info */
		View tempParentView = (View) rootView.findViewById(R.id.row_one);
		patientInfoCommon = new CommonPatientInfo(tempParentView,
				FragmentPatient.this);

		/** Select type */
		preDoorRadio = (RadioButton) rootView.findViewById(R.id.radio_pre_door);
		doorRadio = (RadioButton) rootView.findViewById(R.id.radio_door);
		postDoorRadio = (RadioButton) rootView
				.findViewById(R.id.radio_post_door);
		preDoorRadio.setOnClickListener(this);
		doorRadio.setOnClickListener(this);
		postDoorRadio.setOnClickListener(this);
		preDoorRadio.setOnCheckedChangeListener(this);
		doorRadio.setOnCheckedChangeListener(this);
		postDoorRadio.setOnCheckedChangeListener(this);

		/** Date & Time picker */
		tempParentView = (View) rootView
				.findViewById(R.id.view_birthday_and_onset_time);
		birthAndOnsetCommon = new CommonBirthAndOnset(tempParentView,
				FragmentPatient.this);

		/** Symptoms */
		tempParentView = (View) rootView.findViewById(R.id.view_symptoms);
		symptomsPart = new CommonSymptoms(tempParentView, FragmentPatient.this);
		symptomsPart.hideEleForPatientPage();

		/** Thrombolysis candidate */
		thromboCandidateView = (View) rootView
				.findViewById(R.id.view_thrombolysis_candidate);
		candidateRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_thrombolysis_candidate);
		candidateNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_thrombolysis_candidate_no);
		candidateYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_thrombolysis_candidate_yes);
		candidateComple = (TextView) rootView
				.findViewById(R.id.comple_thrombo_candidate);
		candidateNoRadio.setOnCheckedChangeListener(this);
		candidateYesRadio.setOnCheckedChangeListener(this);
		candidateNoRadio.setOnClickListener(this);

		/** Estimate arrival time */
		tempParentView = (View) rootView
				.findViewById(R.id.view_estimate_arrival_time);
		estimateArrivalCommon = new CommonEstimateArrival(tempParentView,
				FragmentPatient.this);

		/** Buttons */
		saveBtn = (Button) rootView.findViewById(R.id.imgbtn_save);
		saveBtn.setOnClickListener(this);
	}

	/** Call back, when new patient button is clicked */
	@Override
	public void onNewPatient() {
		if (AppViewModel.patientDAO.getPatientId() >= 0) {
			birthAndOnsetCommon.getBirthDatePickerData();
			birthAndOnsetCommon.getOnsetTimePickerData();
			estimateArrivalCommon.getEstiTimePickerData();
			MainActivity.appViewModel.saveData();
			MainActivity.setCurrPatient(-1);
		}
		newPatientCallBack.onNewPatient();

		MainActivity.appViewModel.initPatientRelateDAOs();
		MainActivity.appViewModel.newPatient();
		loadUISatus(true);
		updateTimerCallBack.updateTimer(true);
		selectPatTypeView.setVisibility(RadioGroup.VISIBLE);
		saveBtn.setVisibility(Button.VISIBLE);
		saveBtn.setEnabled(true);
	}

	public void loadUISatus(boolean isNewUser) {
		patientInfoCommon.loadPatientInfo();

		/** Initial UI status */
		if (isNewUser) {
			selectPatTypeView.clearCheck();
			birthAndOnsetCommon.initUIStatus();
			symptomsPart.initUIStatus();
			candidateRadioGroup.clearCheck();
			estimateArrivalCommon.initUIStatus();

			selectPatTypeView.setVisibility(RadioGroup.GONE);
			detailTableView.setVisibility(View.GONE);
			saveBtn.setVisibility(Button.GONE);

		}/** Load existing patient status */
		else {
			selectPatTypeView.setVisibility(RadioGroup.VISIBLE);

			/** Load user inputs */
			selectPatTypeView.check(AppViewModel.patientDAO.getPatientTypeId());
			birthAndOnsetCommon.setDateOfBirth();
			birthAndOnsetCommon.setOnsetTime();
			candidateRadioGroup.check(AppViewModel.patientDAO.getCandidateId());
			symptomsPart.setUserSelection();
			estimateArrivalCommon.setEstiArriTime();

			detailTableView.setVisibility(View.VISIBLE);
			birthAndOnsetCommon.showBothBirDayAndOnset();
			symptomsPart.showSymptoms();
			thromboCandidateView.setVisibility(RadioGroup.VISIBLE);
			estimateArrivalCommon.showEstimateArrivalView();
			saveBtn.setEnabled(true);
		}
		completeCandidate();
	}

	public TreatProgressCallBack getTreatProgressCallBack() {
		return treatProgressCallBack;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			detailTableView.setVisibility(View.VISIBLE);
			if (buttonView.equals(candidateNoRadio)) {
				buttonView.setBackgroundColor(getResources().getColor(
						R.color.red));
				AppViewModel.patientDAO.setCandidate(candidateNoRadio.getText()
						.toString());
				AppViewModel.patientDAO
						.setCandidateId(candidateNoRadio.getId());
				completeCandidate();
			} else if (buttonView.equals(candidateYesRadio)) {
				buttonView.setBackgroundColor(getResources().getColor(
						R.color.green));
				AppViewModel.patientDAO.setCandidate(candidateYesRadio
						.getText().toString());
				AppViewModel.patientDAO.setCandidateId(candidateYesRadio
						.getId());
				completeCandidate();
			} else if (buttonView.equals(preDoorRadio)) {
				buttonView.setBackgroundColor(getResources().getColor(
						R.color.green));
				birthAndOnsetCommon.showBothBirDayAndOnset();
				symptomsPart.showSymptoms();
				thromboCandidateView.setVisibility(View.VISIBLE);
				estimateArrivalCommon.showEstimateArrivalView();

				AppViewModel.patientDAO.setPatientType(buttonView.getText()
						.toString());
				AppViewModel.patientDAO.setPatientTypeId(buttonView.getId());
			} else if (buttonView.equals(doorRadio)) {
				buttonView.setBackgroundColor(getResources().getColor(
						R.color.yellow));
				birthAndOnsetCommon.showBirthDayOnly();
				symptomsPart.hideSymptoms();
				thromboCandidateView.setVisibility(View.GONE);
				estimateArrivalCommon.hideEstimateArrivalView();

				AppViewModel.patientDAO.setPatientType(buttonView.getText()
						.toString());
				AppViewModel.patientDAO.setPatientTypeId(buttonView.getId());
			} else if (buttonView.equals(postDoorRadio)) {
				buttonView.setBackgroundColor(getResources().getColor(
						R.color.red));
				birthAndOnsetCommon.showBirthDayOnly();
				symptomsPart.hideSymptoms();
				thromboCandidateView.setVisibility(View.GONE);
				estimateArrivalCommon.hideEstimateArrivalView();

				AppViewModel.patientDAO.setPatientType(buttonView.getText()
						.toString());
				AppViewModel.patientDAO.setPatientTypeId(buttonView.getId());
			}
		} else if (isChecked == false) {
			buttonView.setBackgroundColor(Color.TRANSPARENT);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(candidateNoRadio)) {
			Intent intent = new Intent(getActivity(),DialogCandidateConfirm.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivity(intent);
		} else {
			Time time = new Time();
			time.setToNow();

			switch (v.getId()) {
			case R.id.imgbtn_save:
				birthAndOnsetCommon.getBirthDatePickerData();
				birthAndOnsetCommon.getOnsetTimePickerData();
				estimateArrivalCommon.getEstiTimePickerData();
				updateTimerCallBack.updateTimer(false);
				patientInfoCommon.loadPatientInfo();
				Toast toast = Toast.makeText(getActivity(),
						R.string.toast_data_saved, Toast.LENGTH_LONG);
				toast.show();
				// saveBtn.setEnabled(false);
				break;
			case R.id.radio_pre_door:
				AppViewModel.timeStampsDAO.setPreDoorTime(time.toMillis(false));
				treatProgressCallBack.updateProgressBar();
				break;
			case R.id.radio_door:
				AppViewModel.timeStampsDAO.setDoorTime(time.toMillis(false));
				treatProgressCallBack.updateProgressBar();
				break;
			case R.id.radio_post_door:
				AppViewModel.timeStampsDAO
						.setPostDoorTime(time.toMillis(false));
				treatProgressCallBack.updateProgressBar();
				break;

			}
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof ProcessMenusCallbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		updateTimerCallBack = (UpdateTimerCallBack) activity;
		treatProgressCallBack = (TreatProgressCallBack) activity;
		newPatientCallBack = (CommonPatientInfoCallBack) activity;
	}

	@Override
	public void onDetach() {

		super.onDetach();
	}

	public void completeCandidate() {
		int candidate = AppViewModel.patientDAO.getCandidateId();
		if (candidate > 0) {
			candidateComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			candidateComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

}
