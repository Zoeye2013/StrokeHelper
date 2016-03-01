package fi.etla.strokehelper.process;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonBirthAndOnset;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.CommonSymptoms;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;

public class FragmentSymptoms extends Fragment implements OnClickListener{
	private View onsetTimeView;
	private View symptomView;
	private Button saveBtn;

	private CommonPatientInfo patientInfoCommon;
	private CommonBirthAndOnset onsetCommon;
	private CommonSymptoms symptomsPart;

//	private FragmentSymptomsViewModel symptomsViewModel;
	private UpdateTimerCallBack updateTimerCallBack;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentSymptoms() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail_tab_symptoms,
				container, false);
//
//		/** Init ViewModel */
//		symptomsViewModel = new FragmentSymptomsViewModel(getActivity());

		initUIElements(rootView);
		loadUISatus();

		return rootView;
	}

	@Override
	public void onDestroy() {
		onsetCommon.getOnsetTimePickerData();
//		symptomsViewModel.saveData();
//		symptomsViewModel.closeDataBase();
		super.onDestroy();
	}

	public void initUIElements(View rootView) {
		/** Patient info */
		View tempView = (View) rootView.findViewById(R.id.row_one);
		patientInfoCommon = new CommonPatientInfo(tempView,
				FragmentSymptoms.this);
		patientInfoCommon.hideNewPatBtn();

		/** Date & Time picker */
		onsetTimeView = (View) rootView
				.findViewById(R.id.view_birthday_and_onset_time);
		onsetCommon = new CommonBirthAndOnset(onsetTimeView,
				FragmentSymptoms.this);

		/** Symptoms */
		symptomView = (View) rootView.findViewById(R.id.view_symptoms);
		symptomsPart = new CommonSymptoms(symptomView, FragmentSymptoms.this);

		/** Buttons */
		saveBtn = (Button) rootView.findViewById(R.id.imgbtn_save);
		saveBtn.setOnClickListener(this);
	}

	public void loadUISatus() {
		patientInfoCommon.loadPatientInfo();
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {
			onsetCommon.initUIStatus();
			symptomsPart.initUIStatus();

			onsetTimeView.setVisibility(View.GONE);
			symptomView.setVisibility(View.GONE);
			saveBtn.setVisibility(Button.GONE);
		}/** Load existing patient status */
		else {
			if(AppViewModel.symptomDAO.isSymptomInfoLoaded() == false)
				AppViewModel.symptomDAO.fetchSymptom();
			
			if(AppViewModel.timeStampsDAO.isTimeStampsInfoLoaded() == false)
				AppViewModel.timeStampsDAO.fetchTimestamp();
			
			onsetTimeView.setVisibility(View.VISIBLE);
			symptomView.setVisibility(View.VISIBLE);

			/** Load user inputs */
			onsetCommon.setOnsetTime();
			symptomsPart.setUserSelection();

			onsetCommon.showOnsetTimeOnly();
			symptomsPart.hideEleForSymptomPage();
			saveBtn.setVisibility(Button.VISIBLE);
			saveBtn.setEnabled(true);
		}
//		saveBtn.setEnabled(false);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(saveBtn)) {
			onsetCommon.getOnsetTimePickerData();
			updateTimerCallBack.updateTimer(false);
			Toast toast = Toast.makeText(getActivity(),
					R.string.toast_data_saved, Toast.LENGTH_LONG);
			toast.show();
//			saveBtn.setEnabled(false);
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
	}
}
