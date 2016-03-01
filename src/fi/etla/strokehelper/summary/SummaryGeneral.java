package fi.etla.strokehelper.summary;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class SummaryGeneral {

	/** Colors */

	private TableRow symptomSideView;
	private TableRow symptomLimbView;
	private TableRow facialPalsyView;
	private TableRow speechDisorderView;
	private TableRow basilarisView;
	private TableRow convulsionView;
	private TableRow symptomOnsetView;
	private TableRow symptomTimerView;
	private TableRow selfCareView;
	private TableRow anticoagulantView;
	private TableRow modifyRankinView;
	private TableRow arriveTimeView;

	private TextView sympSideText;
	private TextView limbSympText;
	private TextView facialPalsyText;
	private TextView speechDisorderText;
	private TextView basilarisSympText;
	private TextView convulsionText;
	private TextView sympOnsetText;
	private TextView sympTimerText;
	private TextView selfCaresText;
	private TextView anticoagulantText;
	private TextView modifyRankinText;
	private TextView arriveTimeTypeText;
	private TextView arriveTimeText;

	private View rootView;
	private Fragment appContext;

	public SummaryGeneral(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TableRows */
		symptomSideView = (TableRow) rootView
				.findViewById(R.id.row_sympton_side);
		symptomLimbView = (TableRow) rootView
				.findViewById(R.id.row_symptom_limb);
		facialPalsyView = (TableRow) rootView
				.findViewById(R.id.row_symptom_facial_palsy);
		speechDisorderView = (TableRow) rootView
				.findViewById(R.id.row_symptom_speech_disorder);
		basilarisView = (TableRow) rootView
				.findViewById(R.id.row_symptom_basilaris);
		convulsionView = (TableRow) rootView
				.findViewById(R.id.row_symptom_convulsion);
		symptomOnsetView = (TableRow) rootView
				.findViewById(R.id.row_symptom_start);
		symptomTimerView = (TableRow) rootView
				.findViewById(R.id.row_thrombolysis_timer);
		selfCareView = (TableRow) rootView
				.findViewById(R.id.row_symptom_care_for_self);
		anticoagulantView = (TableRow) rootView
				.findViewById(R.id.row_symptom_anticoagulant);
		modifyRankinView = (TableRow) rootView
				.findViewById(R.id.row_lab_modified_rankin_scale);
		arriveTimeView = (TableRow) rootView
				.findViewById(R.id.row_arrive_hospital_time);

		/** TextViews */
		sympSideText = (TextView) rootView.findViewById(R.id.info_sympton_side);
		limbSympText = (TextView) rootView.findViewById(R.id.info_symptom_limb);
		facialPalsyText = (TextView) rootView
				.findViewById(R.id.info_symptom_facial_palsy);
		speechDisorderText = (TextView) rootView
				.findViewById(R.id.info_symptom_speech_disorder);
		basilarisSympText = (TextView) rootView
				.findViewById(R.id.info_symptom_basilaris);
		convulsionText = (TextView) rootView
				.findViewById(R.id.info_symptom_convulsion);
		sympOnsetText = (TextView) rootView
				.findViewById(R.id.info_symptom_start);
		sympTimerText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_timer);
		selfCaresText = (TextView) rootView
				.findViewById(R.id.info_symptom_care_for_self);
		anticoagulantText = (TextView) rootView
				.findViewById(R.id.info_symptom_anticoagulant);
		modifyRankinText = (TextView) rootView
				.findViewById(R.id.info_lab_modified_rankin_scale);
		arriveTimeTypeText = (TextView) rootView
				.findViewById(R.id.info_arrive_hospital_time_type);
		arriveTimeText = (TextView) rootView
				.findViewById(R.id.info_arrive_hospital_time);

	}

	public void setUserSelection() {
		String tempStr = AppViewModel.symptomDAO.getSympSide();
		if (tempStr != null && tempStr.length() > 0) {
			sympSideText.setVisibility(TextView.VISIBLE);
			sympSideText.setText(tempStr);
			sympSideText.setBackgroundColor(FragmentSummary.green);
			symptomSideView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			sympSideText.setVisibility(TextView.GONE);
			symptomSideView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getLimbSymp();
		if (tempStr != null && tempStr.length() > 0) {
			limbSympText.setVisibility(TextView.VISIBLE);
			limbSympText.setText(tempStr);
			limbSympText.setBackgroundColor(FragmentSummary.green);
			symptomLimbView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			limbSympText.setVisibility(TextView.GONE);
			symptomLimbView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getFacialPalsy();
		if (tempStr != null && tempStr.length() > 0) {
			facialPalsyText.setVisibility(TextView.VISIBLE);
			facialPalsyText.setText(tempStr);
			facialPalsyText.setBackgroundColor(FragmentSummary.green);
			facialPalsyView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			facialPalsyText.setVisibility(TextView.GONE);
			facialPalsyView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getSpeechDisorder();
		if (tempStr != null && tempStr.length() > 0) {
			speechDisorderText.setVisibility(TextView.VISIBLE);
			speechDisorderText.setText(tempStr);
			speechDisorderText.setBackgroundColor(FragmentSummary.green);
			speechDisorderView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			speechDisorderText.setVisibility(TextView.GONE);
			speechDisorderView.setBackgroundColor(FragmentSummary.grey);
		}
		
		tempStr = AppViewModel.symptomDAO.getBasilaris();
		if (tempStr != null && tempStr.length() > 0) {
			basilarisSympText.setVisibility(TextView.VISIBLE);
			basilarisSympText.setText(tempStr);
			basilarisSympText.setBackgroundColor(FragmentSummary.green);
			basilarisView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			basilarisSympText.setVisibility(TextView.GONE);
			basilarisView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getConvulsion();
		if (tempStr != null && tempStr.length() > 0) {
			convulsionText.setVisibility(TextView.VISIBLE);
			convulsionText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getConvulsionId()) {
			case R.id.radio_convulsion_yes:
				convulsionText.setBackgroundColor(FragmentSummary.yellow);
				break;
			default:
				convulsionText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			convulsionView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			convulsionText.setVisibility(TextView.GONE);
			convulsionView.setBackgroundColor(FragmentSummary.grey);
		}

		Time time = new Time();
		time.setToNow();
		tempStr = "";
		tempStr = AppViewModel.timeStampsDAO.getSympOnsetQua();
		int tempInt = AppViewModel.timeStampsDAO.getSympOnsetQuaId();
		long tempT = 0;
		if (tempInt > 0) {
			sympOnsetText.setVisibility(TextView.VISIBLE);
			switch (tempInt) {
			case R.id.radio_known_onset:
			case R.id.radio_last_symptom_free:
			case R.id.radio_woke_up_symptom:
				tempT = AppViewModel.timeStampsDAO.getSympOnsetTime();
				sympOnsetText.setBackgroundColor(FragmentSummary.green);
				sympOnsetText.setText(AppViewModel.timeToString(tempT) + " ("
						+ tempStr + ")");
				sympTimerText.setText(AppViewModel.milliToString(time
						.toMillis(false) - tempT));
				sympTimerText.setBackgroundColor(FragmentSummary.green);
				break;
			// case R.id.radio_last_symptom_free:
			// case R.id.radio_woke_up_symptom:
			// tempT = AppViewModel.timeStampsDAO.getLastSympFreeTime();
			// sympOnsetText.setBackgroundColor(FragmentSummary.green);
			// sympOnsetText.setText(AppViewModel.timeToString(tempT) + " ("
			// + tempStr + ")");
			// sympTimerText.setText(AppViewModel.milliToString(time
			// .toMillis(false) - tempT));
			// sympTimerText.setBackgroundColor(FragmentSummary.green);
			// break;
			case R.id.radio_unknown:
				sympOnsetText.setBackgroundColor(FragmentSummary.red);
				sympOnsetText.setText(" (" + tempStr + ")");
				symptomTimerView.setVisibility(TextView.GONE);
				break;
			}
		} else {
			sympOnsetText.setVisibility(TextView.GONE);
			symptomOnsetView.setBackgroundColor(FragmentSummary.grey);
			sympTimerText.setVisibility(TextView.GONE);
			symptomTimerView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getSelfCare();
		if (tempStr != null && tempStr.length() > 0) {
			selfCaresText.setVisibility(TextView.VISIBLE);
			selfCaresText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getSelfCareId()) {
			case R.id.radio_care_for_self_no:
				selfCaresText.setBackgroundColor(FragmentSummary.yellow);
				break;
			default:
				selfCaresText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			selfCareView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			selfCaresText.setVisibility(TextView.GONE);
			selfCareView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.symptomDAO.getAnticoagular();
		if (tempStr != null && tempStr.length() > 0) {
			anticoagulantText.setVisibility(TextView.VISIBLE);
			switch (AppViewModel.symptomDAO.getAnticoagularId()) {
			case R.id.radio_anticoagulant_yes:
				tempInt = AppViewModel.presentConditionsDAO.getAnticoaTreatId();
				if (tempInt > 0) {
					anticoagulantText.setText(tempStr
							+ ", "
							+ AppViewModel.presentConditionsDAO
									.getAnticoaTreat());
					anticoagulantText
							.setBackgroundColor(FragmentSummary.yellow);
					anticoagulantView
							.setBackgroundColor(FragmentSummary.transparent);
				} else {
					anticoagulantText.setVisibility(TextView.GONE);
					anticoagulantView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			default:
				anticoagulantText.setText(tempStr);
				anticoagulantText.setBackgroundColor(FragmentSummary.green);
				anticoagulantView
						.setBackgroundColor(FragmentSummary.transparent);
				break;
			}
		} else {
			anticoagulantText.setVisibility(TextView.GONE);
			anticoagulantView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.labDAO.getModiRankinScale();
		if (tempStr != null && tempStr.length() > 0) {
			modifyRankinText.setVisibility(TextView.VISIBLE);
			modifyRankinText.setText(tempStr);
			switch (AppViewModel.labDAO.getModiRankinScaleId()) {
			case R.id.radio_modified_rankin_scale_0:
			case R.id.radio_modified_rankin_scale_1:
			case R.id.radio_modified_rankin_scale_2:
				modifyRankinText.setBackgroundColor(FragmentSummary.green);
				break;
			case R.id.radio_modified_rankin_scale_3:
			case R.id.radio_modified_rankin_scale_4:
				modifyRankinText.setBackgroundColor(FragmentSummary.yellow);
				break;
			case R.id.radio_modified_rankin_scale_5:
			case R.id.radio_modified_rankin_scale_6:
				modifyRankinText.setBackgroundColor(FragmentSummary.red);
				break;
			}
			modifyRankinView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			modifyRankinText.setVisibility(TextView.GONE);
			modifyRankinView.setBackgroundColor(FragmentSummary.grey);
		}

		tempT = AppViewModel.timeStampsDAO.getDoorTime();
		switch (AppViewModel.patientDAO.getPatientTypeId()) {
		case R.id.radio_pre_door:
			if (tempT > 0) {
				arriveTimeTypeText.setText(appContext
						.getString(R.string.info_door_already_at_door));
				arriveTimeText.setText(AppViewModel.timeToString(tempT));
			} else {
				arriveTimeTypeText
						.setText(appContext
								.getString(R.string.info_symptom_estimate_arrival_time));
				tempT = AppViewModel.timeStampsDAO.getEstiArriTime();
				if (tempT > 0)
					arriveTimeText.setText(AppViewModel.timeToString(tempT));
			}
			break;
		case R.id.radio_door:
			arriveTimeTypeText.setText(appContext
					.getString(R.string.info_door_already_at_door));
			arriveTimeText.setText(AppViewModel.timeToString(tempT));
			break;
		case R.id.radio_post_door:
			arriveTimeTypeText.setText(appContext
					.getString(R.string.info_door_already_post_door));
			arriveTimeText
					.setText(AppViewModel
							.timeToString(AppViewModel.timeStampsDAO
									.getPostDoorTime()));
			break;
		}
		arriveTimeText.setBackgroundColor(FragmentSummary.green);

	}
}
