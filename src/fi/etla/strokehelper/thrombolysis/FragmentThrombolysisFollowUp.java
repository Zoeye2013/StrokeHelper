package fi.etla.strokehelper.thrombolysis;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonAlteplaseDose;
import fi.etla.strokehelper.common.CommonBirthAndOnset;
import fi.etla.strokehelper.common.CommonControlCT;
import fi.etla.strokehelper.common.CommonLabImaging;
import fi.etla.strokehelper.common.CommonNihss;
import fi.etla.strokehelper.common.CommonRRFollowUp;
import fi.etla.strokehelper.common.DialogContraindication;
import fi.etla.strokehelper.common.CommonLabResults;
import fi.etla.strokehelper.common.CommonLabStatus;
import fi.etla.strokehelper.common.CommonSymptoms;
import fi.etla.strokehelper.common.DialogHelpInfo;
import fi.etla.strokehelper.common.TreatmentProgressBar.TreatProgressCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentNihssReport.NihssCallback;

public class FragmentThrombolysisFollowUp extends Fragment implements
		OnClickListener {
	private View rootView;
	private TextView followUpNotYetText;
	private View readyToFollowUpView;

	private Button finishFollowUpBtn;

	private CommonAlteplaseDose alteplaseDosePart;
	private CommonNihss nihssBaseline;
	private CommonNihss nihss1H;
	private CommonNihss nihss24H;

	private Button nihssBtn0H;
	private Button nihssBtn1H;
	private Button nihssBtn24H;
	private ImageButton rrFollowInfoBtn;

	private CommonRRFollowUp rrFollowPart;
	// public static Chronometer bolusTimer;
//	private static Handler rrHandler;

	private CommonControlCT controlCtPart;

	private NihssCallback nihssCallback;

	private TreatProgressCallBack treatProgressCallBack;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentThrombolysisFollowUp() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_tab_follow_up,
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
		followUpNotYetText = (TextView) rootView
				.findViewById(R.id.info_follow_up_not_yet);
		readyToFollowUpView = (View) rootView.findViewById(R.id.row_two);

		/** Finish Follow up */
		finishFollowUpBtn = (Button) rootView
				.findViewById(R.id.imgbtn_finish_follow_up);
		finishFollowUpBtn.setOnClickListener(this);

		/** Alteplase Dose */
		View tempView = (View) rootView.findViewById(R.id.view_alteplase_dose);
		alteplaseDosePart = new CommonAlteplaseDose(tempView,
				FragmentThrombolysisFollowUp.this);

		/** NIHSS */
		tempView = (View) rootView.findViewById(R.id.view_nihss);
		nihssBaseline = new CommonNihss(tempView,
				FragmentThrombolysisFollowUp.this, 0);
		nihss1H = new CommonNihss(tempView, FragmentThrombolysisFollowUp.this,
				1);
		nihss24H = new CommonNihss(tempView, FragmentThrombolysisFollowUp.this,
				24);
		nihssBtn0H = (Button) tempView.findViewById(R.id.imgbtn_nihss_0h);
		nihssBtn1H = (Button) tempView.findViewById(R.id.imgbtn_nihss_1h);
		nihssBtn24H = (Button) tempView.findViewById(R.id.imgbtn_nihss_24h);
		nihssBtn0H.setOnClickListener(this);
		nihssBtn1H.setOnClickListener(this);
		nihssBtn24H.setOnClickListener(this);

		/** RR */
		tempView = (View) rootView.findViewById(R.id.view_rr);
		rrFollowPart = new CommonRRFollowUp(tempView,
				FragmentThrombolysisFollowUp.this);
//		rrHandler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				Log.i("RRTimer", String.valueOf(msg.what));
//				rrFollowPart.setRRViews(msg.what);
//				super.handleMessage(msg);
//			}
//		};
		rrFollowInfoBtn = (ImageButton) tempView.findViewById(R.id.imgbtn_help_rr_follow_type);
		rrFollowInfoBtn.setOnClickListener(this);

		/** Control CT */
		tempView = (View) rootView.findViewById(R.id.view_ct);
		controlCtPart = new CommonControlCT(tempView,
				FragmentThrombolysisFollowUp.this);
	}

	public void loadUISatus() {
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {
			rootView.setVisibility(View.GONE);
		}/** Load existing patient status */
		else {
			rootView.setVisibility(View.VISIBLE);
			if (AppViewModel.thrombolysisDAO.getDecisionId() == R.id.radio_thrombolysis_decision_yes) {
				followUpNotYetText.setVisibility(View.GONE);
				readyToFollowUpView.setVisibility(View.VISIBLE);
				setUserSelection();
			} else {
				followUpNotYetText.setVisibility(View.VISIBLE);
				readyToFollowUpView.setVisibility(View.GONE);
				if (AppViewModel.thrombolysisDAO.getDecisionId() == R.id.radio_thrombolysis_decision_no) {

					String timeStr = AppViewModel
							.timeToString(AppViewModel.timeStampsDAO
									.getDecisionTime());
					followUpNotYetText
							.setText(getString(R.string.info_thrombolysis_decision_no)
									+ " " + timeStr);
				} else {
					followUpNotYetText
							.setText(getString(R.string.info_thrombolysis_decision_not_yet));
				}
			}
		}
	}

	public void setUserSelection() {
		/** Alteplase Dose */
		alteplaseDosePart.setUserSelection();
		// startBolusTimer();

		/** NIHSS */
		if (AppViewModel.nihssDAO.isNihssLoaded(0) == false)
			AppViewModel.nihssDAO.fetchNihss(0);
		if (AppViewModel.nihssDAO.isNihssLoaded(1) == false)
			AppViewModel.nihssDAO.fetchNihss(1);
		if (AppViewModel.nihssDAO.isNihssLoaded(24) == false)
			AppViewModel.nihssDAO.fetchNihss(24);
		nihssBaseline.setDataToNihssReport(0);
		nihss1H.setDataToNihssReport(1);
		nihss24H.setDataToNihssReport(24);

		/** RR */
		rrFollowPart.setUserSelection();

//		startRRTimer();

		controlCtPart.setUserSelection();
	}

//	public static void startRRTimer() {
//		long tempLong = AppViewModel.timeStampsDAO.getBolusTime();
//		if (tempLong > 0
//				&& AppViewModel.thrombolysisDAO.getRRFollowTypeId() > 0) {
//			Time time = new Time();
//			time.setToNow();
//			long timeDiff = time.toMillis(false) - tempLong;
//
//			rrHandler.removeCallbacksAndMessages(null);
//
//			Message msg = new Message();
//			msg.what = 15;
//			long delay = 600000 - timeDiff;
//			if (delay > 0)
//				rrHandler.sendMessageDelayed(msg, delay);
//			else if (delay <= 0)
//				rrHandler.sendMessage(msg);
//
//			msg = new Message();
//			msg.what = 30;
//			delay = 1200000 - timeDiff;
//			if (delay > 0)
//				rrHandler.sendMessageDelayed(msg, delay);
//			else if (delay <= 0)
//				rrHandler.sendMessage(msg);
//
//			msg = new Message();
//			msg.what = 1;
//			delay = 2700000 - timeDiff;
//			if (delay > 0)
//				rrHandler.sendMessageDelayed(msg, delay);
//			else if (delay <= 0)
//				rrHandler.sendMessage(msg);
//
//			msg = new Message();
//			msg.what = 2;
//			delay = 6300000 - timeDiff;
//			if (delay > 0)
//				rrHandler.sendMessageDelayed(msg, delay);
//			else if (delay <= 0)
//				rrHandler.sendMessage(msg);
//
//			msg = new Message();
//			msg.what = 24;
//			delay = 8280000 - timeDiff;
//			if (delay > 0)
//				rrHandler.sendMessageDelayed(msg, delay);
//			else if (delay <= 0)
//				rrHandler.sendMessage(msg);
//
//		}
//	}

	public TreatProgressCallBack getTreatProgressCallBack() {
		return treatProgressCallBack;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

//		if (requestCode == MainActivity.REQUEST_CODE_FINISH_FOLLOW_UP) {
//			if (resultCode == MainActivity.RESULT_FINISH_FOLLOW_UP) {
//				treatProgressCallBack.updateProgressBar();
//			}
//		}
	}// onActivityResult

	@Override
	public void onClick(View v) {
		if (v.equals(nihssBtn0H) || v.equals(nihssBtn1H)
				|| v.equals(nihssBtn24H)) {
			nihssCallback.onNihssButtonClicked(v.getId(), false);
		} else if (v.equals(rrFollowInfoBtn)){
			Intent intent = new Intent(getActivity(),DialogHelpInfo.class);
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.help_title_rr_follow_up));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_rr_follow_up));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivity(intent);
		}else if (v.equals(finishFollowUpBtn)) {
			new AlertDialog.Builder(getActivity())
					.setTitle(R.string.title_confirm_to_finish_follow_up)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									Intent finishIntent = new Intent(
											getActivity(),
											DialogFinishFollowUp.class);
									getActivity().startActivityForResult(
											finishIntent,
											MainActivity.REQUEST_CODE_FINISH_FOLLOW_UP);
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
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof NihssCallback)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		nihssCallback = (NihssCallback) activity;
		treatProgressCallBack = (TreatProgressCallBack) activity;
	}

}
