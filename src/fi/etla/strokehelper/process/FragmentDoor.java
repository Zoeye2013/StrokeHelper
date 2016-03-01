package fi.etla.strokehelper.process;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.common.CommonPatientInfo.CommonPatientInfoCallBack;
import fi.etla.strokehelper.common.TreatmentProgressBar.TreatProgressCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;

public class FragmentDoor extends Fragment implements
		CommonPatientInfoCallBack, OnClickListener {

	private CommonPatientInfo patientInfoCommon;

	private Button patAtDoorBtn;
	private TextView alrAtDoorInfoText;

	private UpdateTimerCallBack updateTimerCallBack;
	private TreatProgressCallBack treatProgressCallBack;
	private CommonPatientInfoCallBack newPatientCallBack;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentDoor() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail_tab_door,
				container, false);

//		/** Init ViewModel */
//		doorViewModel = new FragmentDoorViewModel(getActivity());

		initUIElements(rootView);
		loadUISatus(false);

		return rootView;
	}

	public void initUIElements(View rootView) {
		/** Views */
		patientInfoCommon = new CommonPatientInfo(rootView, FragmentDoor.this);
		patAtDoorBtn = (Button) rootView
				.findViewById(R.id.imgbtn_patient_at_door);
		alrAtDoorInfoText = (TextView) rootView
				.findViewById(R.id.info_already_at_door);
		patAtDoorBtn.setOnClickListener(this);

		// DatePicker birthDate = (DatePicker) rootView
		// .findViewById(R.id.picker_birthday);
		// TimePicker onsetTime = (TimePicker)
		// rootView.findViewById(R.id.time_picker_onset_time);
		// birthDate.setCalendarViewShown(false);
		// onsetTime.setIs24HourView(true);
	}

	public void loadUISatus(boolean isNewUser) {
		patientInfoCommon.loadPatientInfo();
		/** Initial UI status */
		if (isNewUser) {
			alrAtDoorInfoText.setVisibility(TextView.INVISIBLE);
			patAtDoorBtn.setEnabled(true);
		}/** Load existing patient status */
		else {
			if (AppViewModel.patientDAO.getPatientId() != -1) {
				if(AppViewModel.timeStampsDAO.isTimeStampsInfoLoaded() == false)
					AppViewModel.timeStampsDAO.fetchTimestamp();
				/** Load patient */
				String doorTime = AppViewModel.timeToString(AppViewModel.timeStampsDAO.getDoorTime());
				String postDoorTime = AppViewModel.timeToString(AppViewModel.timeStampsDAO.getPostDoorTime());
				if (postDoorTime != null && postDoorTime.length() > 0) {
					alrAtDoorInfoText
							.setText(getString(R.string.info_door_already_post_door)
									+ "  " + postDoorTime);
					alrAtDoorInfoText.setVisibility(TextView.VISIBLE);
					patAtDoorBtn.setEnabled(false);
				} else if (doorTime != null && doorTime.length() > 0) {
					alrAtDoorInfoText
							.setText(getString(R.string.info_door_already_at_door)
									+ "  " + doorTime);
					alrAtDoorInfoText.setVisibility(TextView.VISIBLE);
					patAtDoorBtn.setEnabled(false);
				} else {
					alrAtDoorInfoText.setVisibility(TextView.INVISIBLE);
					patAtDoorBtn.setEnabled(true);
				}
			} else if (AppViewModel.patientDAO.getPatientId() == -1) {
				alrAtDoorInfoText.setVisibility(TextView.INVISIBLE);
				patAtDoorBtn.setEnabled(false);
			}
		}
	}

	@Override
	public void onNewPatient() {
		MainActivity.appViewModel.saveData();
		MainActivity.setCurrPatient(-1);
		newPatientCallBack.onNewPatient();
		MainActivity.appViewModel.initPatientRelateDAOs();
		MainActivity.appViewModel.newPatient();
		loadUISatus(true);
		updateTimerCallBack.updateTimer(true);
		
	}
	
	public TreatProgressCallBack getTreatProgressCallBack()
	{
		return treatProgressCallBack;
	}

	@Override
	public void onClick(View v) {
		if (v.equals(patAtDoorBtn)) {
			Time time = new Time();
			time.setToNow();
			AppViewModel.timeStampsDAO.setDoorTime(time.toMillis(false));
			String patType = AppViewModel.patientDAO.getPatientType();
			int patTypeId = AppViewModel.patientDAO.getPatientTypeId();
			if ((patType == null || patType.length() <= 0) && patTypeId <= 0) {
				AppViewModel.patientDAO.setPatientType(getString(R.string.radio_door));
				AppViewModel.patientDAO.setPatientTypeId(R.id.radio_door);
			}
			
			String timeStr = AppViewModel.timeToString(AppViewModel.timeStampsDAO.getDoorTime());
			alrAtDoorInfoText
					.setText(getString(R.string.info_door_already_at_door)
							+ "  " + timeStr);
			alrAtDoorInfoText.setVisibility(TextView.VISIBLE);
			patAtDoorBtn.setEnabled(false);
			treatProgressCallBack.updateProgressBar();
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
		newPatientCallBack = (CommonPatientInfoCallBack)activity;
	}
}
