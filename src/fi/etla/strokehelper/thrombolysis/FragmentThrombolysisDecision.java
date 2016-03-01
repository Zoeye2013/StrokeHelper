package fi.etla.strokehelper.thrombolysis;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonBirthAndOnset;
import fi.etla.strokehelper.common.CommonLabImaging;
import fi.etla.strokehelper.common.DialogContraindication;
import fi.etla.strokehelper.common.CommonLabResults;
import fi.etla.strokehelper.common.CommonLabStatus;
import fi.etla.strokehelper.common.CommonSymptoms;
import fi.etla.strokehelper.common.CommonBirthAndOnset.UpdateTimerCallBack;
import fi.etla.strokehelper.common.DialogCommonProcessRational;
import fi.etla.strokehelper.common.TreatmentProgressBar.TreatProgressCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentProcessMenus.ProcessMenusCallbacks;

public class FragmentThrombolysisDecision extends Fragment implements
		OnCheckedChangeListener, OnClickListener {
	private View rootView;

	private View thromboNotYetText;
	private Button directThromboBtn;
	private View readyToDecisionView;
	private TextView thromboDecisionText;

	private TextView patientAgeText;
	private CommonSymptoms generalSympPart;
	private CommonBirthAndOnset onsetPart;
	private TextView onsetTimeText;
	private CommonSymptoms patientSympPart;
	private CommonLabStatus labStatusPart;

	private View nihssView;
	private TextView nihssTotalScoreText;

	private CommonLabImaging labImagingPart;
	private CommonLabResults labResultPart;

	private TextView contraNonText;
	private Button contraBtn;

	private TextView continueProcessNonText;
	private Button continueProcessBtn;

	private RadioGroup decisionRadioGroup;
	private RadioButton decisionYesRadio;
	private RadioButton decisionNoRadio;

	private TreatProgressCallBack treatProgressCallBack;

	public static int RESULT_DIRECT_THROMBO_YES = 1;
	public static int RESULT_DIRECT_THROMBO_NO = 0;

	public interface JumpTabCallBack {
		public void jumpToTab(int tabId);
	}

	private JumpTabCallBack jumpTabCallBack;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentThrombolysisDecision() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_tab_decision,
				container, false);

		initUIElements(rootView);
		loadUISatus();
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements(View rootView) {

		/** Thrombolysis Ready or Not */
		thromboNotYetText = (View) rootView
				.findViewById(R.id.view_thrombolysis_not_yet);
		directThromboBtn = (Button) rootView
				.findViewById(R.id.imgbtn_direct_thrombolysis);
		readyToDecisionView = (View) rootView.findViewById(R.id.row_two);

		/** Decision Info */
		thromboDecisionText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_decision);

		/** General Info */
		patientAgeText = (TextView) rootView.findViewById(R.id.info_age);
		View tempView = (View) rootView
				.findViewById(R.id.view_general_symptoms);
		generalSympPart = new CommonSymptoms(tempView,
				FragmentThrombolysisDecision.this);
		tempView = (View) rootView.findViewById(R.id.view_general_onset_time);
		onsetPart = new CommonBirthAndOnset(tempView,
				FragmentThrombolysisDecision.this);
		onsetTimeText = (TextView) rootView
				.findViewById(R.id.info_timer_from_onset);

		/** Patient Info */
		tempView = (View) rootView.findViewById(R.id.view_patient_symptoms);
		patientSympPart = new CommonSymptoms(tempView,
				FragmentThrombolysisDecision.this);
		tempView = (View) rootView.findViewById(R.id.view_patient_status);
		labStatusPart = new CommonLabStatus(tempView,
				FragmentThrombolysisDecision.this);

		/** Findings */
		nihssView = (View) rootView.findViewById(R.id.view_finding_nihss);
		nihssTotalScoreText = (TextView) rootView
				.findViewById(R.id.info_point_total);

		tempView = (View) rootView.findViewById(R.id.view_finding_ct);
		labImagingPart = new CommonLabImaging(tempView,
				FragmentThrombolysisDecision.this);
		tempView = (View) rootView.findViewById(R.id.view_finding_results);
		labResultPart = new CommonLabResults(tempView,
				FragmentThrombolysisDecision.this);

		contraNonText = (TextView) rootView
				.findViewById(R.id.info_contraindications_none);
		contraBtn = (Button) rootView
				.findViewById(R.id.imgbtn_contraindication_detail);
		continueProcessNonText = (TextView) rootView
				.findViewById(R.id.info_rational_continue_process_none);
		continueProcessBtn = (Button) rootView
				.findViewById(R.id.imgbtn_rational_continue_process);
		contraBtn.setOnClickListener(this);
		continueProcessBtn.setOnClickListener(this);

		/** Thrombolysis decision */
		decisionRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_thrombolysis_decision);
		decisionRadioGroup.setOnCheckedChangeListener(this);

		decisionYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_thrombolysis_decision_yes);
		decisionNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_thrombolysis_decision_no);

		directThromboBtn.setOnClickListener(this);
		decisionYesRadio.setOnClickListener(this);
		decisionNoRadio.setOnClickListener(this);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MainActivity.REQUEST_CODE_DIRECT_THROMBOLYSIS) {
			if (resultCode == FragmentThrombolysisDecision.RESULT_DIRECT_THROMBO_YES) {
				treatProgressCallBack.updateProgressBar();
				jumpTabCallBack.jumpToTab(R.id.tab_follow_up);
			}
		} else if (requestCode == MainActivity.REQUEST_CODE_DECISION_CONFIRM) {
			boolean decision = data.getBooleanExtra(
					DialogConfirmDecision.TAG_THROMBO_DECISION, false);

			if (resultCode == DialogConfirmDecision.RESULT_CONFIRM_YES) {
				Time time = new Time();
				time.setToNow();
				if (decision) {
					AppViewModel.timeStampsDAO.setDecisionTime(time
							.toMillis(false));
					thromboDecisionText
							.setText(getString(R.string.info_thrombolysis_decision_yes)
									+ " "
									+ AppViewModel.timeToString(time
											.toMillis(false)));
					thromboDecisionText.setBackgroundColor(getResources()
							.getColor(R.color.bright_blue));
					jumpTabCallBack.jumpToTab(R.id.tab_follow_up);
					Toast toast = Toast.makeText(
							getActivity(),
							getString(R.string.info_thrombolysis_decision_yes)
									+ " "
									+ AppViewModel.timeToString(time
											.toMillis(false)) + "\n"
									+ getString(R.string.toast_begin_bolus),
							Toast.LENGTH_LONG);
					toast.show();
					treatProgressCallBack.updateProgressBar();
				} else if (!decision) {
					AppViewModel.timeStampsDAO.setDecisionTime(time
							.toMillis(false));
					thromboDecisionText
							.setText(getString(R.string.info_thrombolysis_decision_no)
									+ " "
									+ AppViewModel.timeToString(time
											.toMillis(false)));
					thromboDecisionText.setBackgroundColor(getResources()
							.getColor(R.color.yellow));
					treatProgressCallBack.updateProgressBar();
				}
			} else if (resultCode == DialogConfirmDecision.RESULT_CONFIRM_NO) {
				if (decision) {
					decisionYesRadio.setChecked(false);
					decisionYesRadio.setBackgroundColor(Color.TRANSPARENT);
				} else if (!decision) {
					decisionNoRadio.setChecked(false);
					decisionNoRadio.setBackgroundColor(Color.TRANSPARENT);
				}
				AppViewModel.timeStampsDAO.setDecisionTime(0);
				thromboDecisionText.setText("");
				thromboDecisionText.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}// onActivityResult

	public void loadUISatus() {
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {
			rootView.setVisibility(View.GONE);
		}/** Load existing patient status */
		else {
			rootView.setVisibility(View.VISIBLE);
			boolean isDirectThrombo = AppViewModel.thrombolysisDAO
					.getDirectThromboDecis();
			if (AppViewModel.isInfoComplete() || isDirectThrombo) {
				thromboNotYetText.setVisibility(View.GONE);
				readyToDecisionView.setVisibility(View.VISIBLE);
				AppViewModel.summarizeContraindications();
				if (AppViewModel.contras.size() <= 0
						&& AppViewModel.relaContras.size() <= 0) {
					contraNonText.setVisibility(TextView.VISIBLE);
					contraBtn.setVisibility(Button.GONE);
				} else {
					contraNonText.setVisibility(TextView.GONE);
					contraBtn.setVisibility(Button.VISIBLE);
				}

				AppViewModel.summarizeRationalContinueProcess();
				if (AppViewModel.continueProcess.size() <= 0) {
					continueProcessNonText.setVisibility(TextView.VISIBLE);
					continueProcessBtn.setVisibility(Button.GONE);
				} else {
					continueProcessNonText.setVisibility(TextView.GONE);
					continueProcessBtn.setVisibility(Button.VISIBLE);
				}
				setUserSelection();
			} else if (!AppViewModel.isInfoComplete() && !isDirectThrombo) {
				thromboNotYetText.setVisibility(View.VISIBLE);
				readyToDecisionView.setVisibility(View.GONE);
			}
		}
	}

	public void setUserSelection() {
		/** Decision */
		int decisionId = AppViewModel.thrombolysisDAO.getDecisionId();
		if (decisionId <= 0) {
			thromboDecisionText
					.setText(R.string.info_thrombolysis_decision_not_yet);
			thromboDecisionText.setBackgroundColor(getResources().getColor(
					R.color.yellow));
		} else if (decisionId > 0
				&& decisionId == R.id.radio_thrombolysis_decision_yes) {

			String timeStr = AppViewModel
					.timeToString(AppViewModel.timeStampsDAO.getDecisionTime());
			thromboDecisionText
					.setText(getString(R.string.info_thrombolysis_decision_yes)
							+ " " + timeStr);
			thromboDecisionText.setBackgroundColor(getResources().getColor(
					R.color.bright_blue));
		} else if (decisionId > 0
				&& decisionId == R.id.radio_thrombolysis_decision_no) {
			String timeStr = AppViewModel
					.timeToString(AppViewModel.timeStampsDAO.getDecisionTime());
			thromboDecisionText
					.setText(getString(R.string.info_thrombolysis_decision_no)
							+ " " + timeStr);
			thromboDecisionText.setBackgroundColor(getResources().getColor(
					R.color.yellow));
		}

		/** Symptom for general section */
		generalSympPart.setUserSelectionGeneralPart();
		patientAgeText.setText(AppViewModel.patientDAO.getPatientAge() + " "
				+ getString(R.string.info_thrombolysis_patient_age));

		/** Symptom for patient section */
		patientSympPart.setUserSelectionPatientPart();

		/** Onset time info */
		onsetPart.setOnsetTime();

		Time time = new Time();
		time.setToNow();
		long timeDiff = time.toMillis(false)
				- AppViewModel.timeStampsDAO.getSympOnsetTime();
		if (AppViewModel.timeStampsDAO.getSympOnsetQuaId() != R.id.radio_unknown) {
			onsetTimeText.setText(getString(R.string.info_thrombolysis_timer)
					+ " " + AppViewModel.milliToString(timeDiff));
		} else {
			onsetTimeText.setVisibility(TextView.GONE);
		}

		/** Lab Status info */
		labStatusPart.setUserSelection();

		/** Findings */
		/** NIHSS */
		double onsetTimeH = AppViewModel.milliToHour(timeDiff);
		int nihssTotal = AppViewModel.nihssDAO.getNihssBaseline()
				.getNihssTotal();
		if ((onsetTimeH < 3 && nihssTotal >= 3)
				|| (onsetTimeH >= 3 && onsetTimeH <= 4.5 && nihssTotal >= 3 && nihssTotal <= 24)) {
			nihssTotalScoreText.setText(String.valueOf(nihssTotal));
			nihssTotalScoreText.setBackgroundColor(getResources().getColor(
					R.color.green));
			nihssView.setVisibility(View.VISIBLE);
		} else {
			nihssView.setVisibility(View.GONE);
		}
		/** Admission CT */
		labImagingPart.setAdmiCT();
		labImagingPart.setPerfuAndAngioCT();
		labResultPart.setUserSelection();

		/** Decision */
		if (AppViewModel.thrombolysisDAO.getDecisionId() > 0) {

			decisionRadioGroup.check(AppViewModel.thrombolysisDAO
					.getDecisionId());
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (group.getId()) {
		case R.id.radio_group_thrombolysis_decision:
			decisionRadioGroup.check(checkedId);

			AppViewModel.thrombolysisDAO.setDecisionId(checkedId);
			switch (checkedId) {
			case R.id.radio_thrombolysis_decision_yes:
				decisionYesRadio.setBackgroundColor(getResources().getColor(
						R.color.bright_blue));
				decisionNoRadio.setBackgroundColor(Color.TRANSPARENT);
				AppViewModel.thrombolysisDAO
						.setDecision(getString(R.string.radio_yes));
				break;
			case R.id.radio_thrombolysis_decision_no:
				decisionNoRadio.setBackgroundColor(getResources().getColor(
						R.color.yellow));
				decisionYesRadio.setBackgroundColor(Color.TRANSPARENT);
				AppViewModel.thrombolysisDAO
						.setDecision(getString(R.string.radio_no));
				break;
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(directThromboBtn)) {
			Intent intent = new Intent(getActivity(),
					DialogDirectThrombolysis.class);
			FragmentThrombolysisDecision.this.startActivityForResult(intent,
					MainActivity.REQUEST_CODE_DIRECT_THROMBOLYSIS);
		} else {

			switch (v.getId()) {
			case R.id.imgbtn_contraindication_detail:
				Intent contraintent = new Intent(getActivity(),
						DialogContraindication.class);
				contraintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivity(contraintent);
				break;
			case R.id.imgbtn_rational_continue_process:
				Intent intent = new Intent(getActivity(),
						DialogCommonProcessRational.class);
				intent.putExtra(DialogCommonProcessRational.TAG_TYPE,
						DialogCommonProcessRational.TAG_TYPE_PROCESS_CONTINUE);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivity(intent);
				break;
			case R.id.radio_thrombolysis_decision_yes:
				Intent deciYesIntent = new Intent(getActivity(),
						DialogConfirmDecision.class);
				deciYesIntent.putExtra(
						DialogConfirmDecision.TAG_THROMBO_DECISION, true);
				FragmentThrombolysisDecision.this.startActivityForResult(
						deciYesIntent,
						MainActivity.REQUEST_CODE_DECISION_CONFIRM);

				break;
			case R.id.radio_thrombolysis_decision_no:
				Intent deciNoIntent = new Intent(getActivity(),
						DialogConfirmDecision.class);
				deciNoIntent.putExtra(
						DialogConfirmDecision.TAG_THROMBO_DECISION, false);
				FragmentThrombolysisDecision.this.startActivityForResult(
						deciNoIntent,
						MainActivity.REQUEST_CODE_DECISION_CONFIRM);
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
		treatProgressCallBack = (TreatProgressCallBack) activity;
		jumpTabCallBack = (JumpTabCallBack) activity;
	}
}
