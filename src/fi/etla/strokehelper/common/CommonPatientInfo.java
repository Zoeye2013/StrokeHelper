package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.patient.FragmentPatient;
import fi.etla.strokehelper.process.FragmentDoor;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class CommonPatientInfo implements OnClickListener {

	private TextView patTypeText;
	private TextView patIdText;
	private TextView patAgeText;
	private Button newPatBtn;
//	private Button interrProcesBtn;

	private CommonPatientInfoCallBack callBack;

	public interface CommonPatientInfoCallBack {
		/**
		 * Callback for when new patient buttom is clicked
		 */
		public void onNewPatient();
	}

	private View rootView;
	private Fragment appContext;

	public CommonPatientInfo(View root, Fragment context) {
		rootView = root;
		appContext = context;
		if (context instanceof CommonPatientInfoCallBack) {
			callBack = (CommonPatientInfoCallBack) appContext;
		}
		initUIElements();
	}

	public void initUIElements() {
		patTypeText = (TextView) rootView.findViewById(R.id.info_patient_type);
		patIdText = (TextView) rootView.findViewById(R.id.info_patient_id);
		patAgeText = (TextView) rootView.findViewById(R.id.info_patient_age);
		newPatBtn = (Button) rootView.findViewById(R.id.imgbtn_new_patient);
//		interrProcesBtn = (Button) rootView.findViewById(R.id.imgbtn_interr_process);
		newPatBtn.setOnClickListener(this);
//		interrProcesBtn.setOnClickListener(this);

		patTypeText.setText("");
		patIdText.setText("");
		patAgeText.setText("");
	}

	public void loadPatientInfo() {
		if (AppViewModel.patientDAO.getPatientId() != -1) {

			if (AppViewModel.patientDAO.getPatientTypeId() > 0) {
				switch (AppViewModel.patientDAO.getPatientTypeId()) {
				case R.id.radio_pre_door:
					patTypeText.setText(R.string.info_patient_type_predoor);
					break;
				case R.id.radio_door:
					patTypeText.setText(R.string.info_patient_type_door);
					break;
				case R.id.radio_post_door:
					patTypeText.setText(R.string.info_patient_type_post_door);
					break;
				}
			}else
				patTypeText.setText("");
			
			String dateOfBirth = AppViewModel.patientDAO.getDateOfBirth();
			if (dateOfBirth != null && dateOfBirth.length() > 0) {
				patIdText.setText(AppViewModel.patientDAO.getDateOfBirth());
				patAgeText.setText(appContext.getString(R.string.info_patient_age)
						+ " "
						+ String.valueOf(AppViewModel.patientDAO.getPatientAge()));
			} else {
				patIdText.setText("");
				patAgeText.setText("");
			}
		} 

		

	}

	public void hideNewPatBtn() {
		newPatBtn.setVisibility(Button.GONE);
//		interrProcesBtn.setVisibility(Button.VISIBLE);
	}

	@Override
	public void onClick(View view) {
		if (view.equals(newPatBtn)) {
			callBack.onNewPatient();
			loadPatientInfo();
			if( appContext instanceof FragmentPatient){
				((FragmentPatient) appContext).getTreatProgressCallBack().updateProgressBar();
			}else if( appContext instanceof FragmentDoor){
				((FragmentDoor) appContext).getTreatProgressCallBack().updateProgressBar();
			}
		}
	}
}
