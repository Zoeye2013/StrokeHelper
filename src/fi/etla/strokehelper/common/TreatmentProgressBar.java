package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;

import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.thrombolysis.DialogFinishFollowUp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TreatmentProgressBar implements OnClickListener {
	private View rootView;
	private Activity appContext;

	private TextView atDoor;
	private TextView ctBegin;
	private TextView ctStatements;
	private TextView thromboDecision;
	private TextView thromboBolus;
	private TextView thromboInfusionBegin;
	private TextView thromboInfusionEnd;
	private TextView endFollowUp;

	private final Animation animation;

	public interface TreatProgressCallBack {
		public void updateProgressBar();
	}

	public TreatmentProgressBar(View root, Activity context) {
		rootView = root;
		appContext = context;
		animation = new AlphaAnimation(1, 0);
		initUIElements();
	}

	public void initUIElements() {

		atDoor = (TextView) rootView.findViewById(R.id.light_door);
		ctBegin = (TextView) rootView.findViewById(R.id.light_ct_begin);
		ctStatements = (TextView) rootView
				.findViewById(R.id.light_ct_statements);
		thromboDecision = (TextView) rootView
				.findViewById(R.id.light_thrombolysis_decision);
		thromboBolus = (TextView) rootView
				.findViewById(R.id.light_thrombolysis_bolus);
		thromboInfusionBegin = (TextView) rootView
				.findViewById(R.id.light_thrombolysis_infusion_begin);
		thromboInfusionEnd = (TextView) rootView
				.findViewById(R.id.light_thrombolysis_infusion_end);
		endFollowUp = (TextView) rootView
				.findViewById(R.id.light_end_follow_up);

		animation.setDuration(1000);
		animation.setInterpolator(new LinearInterpolator());
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);

		atDoor.setOnClickListener(this);
		ctBegin.setOnClickListener(this);
		ctStatements.setOnClickListener(this);
		// thromboDecision.setOnClickListener(this);
		// thromboBolus.setOnClickListener(this);
		// thromboInfusionBegin.setOnClickListener(this);
		// thromboInfusionEnd.setOnClickListener(this);
		endFollowUp.setOnClickListener(this);
	}

	public void setLights() {
		if (AppViewModel.patientDAO.getPatientId() != -1) {
			rootView.setVisibility(View.VISIBLE);
			long doorTime = AppViewModel.timeStampsDAO.getDoorTime();
			long postDoorTime = AppViewModel.timeStampsDAO.getPostDoorTime();
			long ctBeginTime = AppViewModel.timeStampsDAO.getCTBeginTime();
			long ctStatementTime = AppViewModel.timeStampsDAO
					.getCTStatementTime();
			long thromDesiTime = AppViewModel.timeStampsDAO.getDecisionTime();
			long bolusTime = AppViewModel.timeStampsDAO.getBolusTime();
			long infusionBeginTime = AppViewModel.timeStampsDAO
					.getInfusionBeTime();
			long infusionEndTime = AppViewModel.timeStampsDAO
					.getInfusionEndTime();
			long endFollowUpTime = AppViewModel.timeStampsDAO
					.getFollowEndTime();

			/** Door light */
			if (doorTime > 0 || (doorTime <= 0 && postDoorTime > 0)) {
				atDoor.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_green, 0, 0);
				atDoor.setClickable(false);
				atDoor.clearAnimation();
			} else {
				atDoor.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_red, 0, 0);
				atDoor.setClickable(true);
				atDoor.startAnimation(animation);
			}

			/** CT begin light */
			if (doorTime <= 0 && postDoorTime <= 0) {
				ctBegin.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				ctBegin.setClickable(false);
				ctBegin.clearAnimation();
			} else {
				if (ctBeginTime > 0) {
					ctBegin.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_green, 0, 0);
					ctBegin.setClickable(false);
					ctBegin.clearAnimation();
				} else {
					ctBegin.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_red, 0, 0);
					ctBegin.setClickable(true);
					ctBegin.startAnimation(animation);
				}
			}

			/** CT Statements light */
			if ((doorTime <= 0 && postDoorTime <= 0) || ctBeginTime <= 0) {
				ctStatements.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				ctStatements.setClickable(false);
				ctStatements.clearAnimation();
			} else {
				if (ctStatementTime > 0) {
					ctStatements.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_green, 0, 0);
					ctStatements.setClickable(false);
					ctStatements.clearAnimation();
				} else {
					ctStatements.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_red, 0, 0);
					ctStatements.setClickable(true);
					ctStatements.startAnimation(animation);
				}
			}

			/** Thrombolysis decision light */
			thromboDecision.setClickable(false);
			if (doorTime <= 0 && postDoorTime <= 0) {
				thromboDecision.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				thromboDecision.clearAnimation();
			} else {
				if (thromDesiTime > 0) {
					thromboDecision.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_green, 0, 0);
					thromboDecision.clearAnimation();
				} else {
					thromboDecision.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_red, 0, 0);
					thromboDecision.startAnimation(animation);
				}
			}

			/** Bolus light */
			thromboBolus.setClickable(false);
			if ((doorTime <= 0 && postDoorTime <= 0) || thromDesiTime <= 0) {
				thromboBolus.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				// thromboBolus.setClickable(false);
				thromboBolus.clearAnimation();
			} else {
				if (bolusTime > 0) {
					thromboBolus.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_green, 0, 0);
					// thromboBolus.setClickable(false);
					thromboBolus.clearAnimation();
				} else {
					thromboBolus.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_red, 0, 0);
					// thromboBolus.setClickable(true);
					thromboBolus.startAnimation(animation);
				}
			}

			/** Infusion begin light */
			thromboInfusionBegin.setClickable(false);
			if ((doorTime <= 0 && postDoorTime <= 0) || thromDesiTime <= 0
					|| bolusTime <= 0) {
				thromboInfusionBegin.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				// thromboInfusionBegin.setClickable(false);
				thromboInfusionBegin.clearAnimation();
			} else {
				if (infusionBeginTime > 0) {
					thromboInfusionBegin
							.setCompoundDrawablesWithIntrinsicBounds(0,
									R.drawable.light_green, 0, 0);
					// thromboInfusionBegin.setClickable(false);
					thromboInfusionBegin.clearAnimation();
				} else {
					thromboInfusionBegin
							.setCompoundDrawablesWithIntrinsicBounds(0,
									R.drawable.light_red, 0, 0);
					// thromboInfusionBegin.setClickable(true);
					thromboInfusionBegin.startAnimation(animation);
				}
			}

			/** Infusion end light */
			thromboInfusionEnd.setClickable(false);
			if ((doorTime <= 0 && postDoorTime <= 0) || thromDesiTime <= 0
					|| bolusTime <= 0 || infusionBeginTime <= 0) {
				thromboInfusionEnd.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				// thromboInfusionEnd.setClickable(false);
				thromboInfusionEnd.clearAnimation();
			} else {
				if (infusionEndTime > 0) {
					thromboInfusionEnd.setCompoundDrawablesWithIntrinsicBounds(
							0, R.drawable.light_green, 0, 0);
					// thromboInfusionEnd.setClickable(false);
					thromboInfusionEnd.clearAnimation();
				} else {
					thromboInfusionEnd.setCompoundDrawablesWithIntrinsicBounds(
							0, R.drawable.light_red, 0, 0);
					// thromboInfusionEnd.setClickable(true);
					thromboInfusionEnd.startAnimation(animation);
				}
			}

			/** End follow-up light */
			if ((doorTime <= 0 && postDoorTime <= 0) || thromDesiTime <= 0) {
				endFollowUp.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.light_grey, 0, 0);
				endFollowUp.setClickable(false);
				endFollowUp.clearAnimation();
			} else {
				if (endFollowUpTime > 0) {
					endFollowUp.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_green, 0, 0);
					endFollowUp.setClickable(false);
					endFollowUp.clearAnimation();
				} else {
					endFollowUp.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.light_red, 0, 0);
					endFollowUp.setClickable(true);
					endFollowUp.startAnimation(animation);
				}
			}
		} else {
			atDoor.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			atDoor.setClickable(false);
			atDoor.clearAnimation();

			ctBegin.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			ctBegin.setClickable(false);
			ctBegin.clearAnimation();

			ctStatements.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			ctStatements.setClickable(false);
			ctStatements.clearAnimation();

			thromboDecision.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			thromboDecision.setClickable(false);
			thromboDecision.clearAnimation();

			thromboBolus.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			thromboBolus.setClickable(false);
			thromboBolus.clearAnimation();

			thromboInfusionBegin.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			thromboInfusionBegin.setClickable(false);
			thromboInfusionBegin.clearAnimation();

			thromboInfusionEnd.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			thromboInfusionEnd.setClickable(false);
			thromboInfusionEnd.clearAnimation();

			endFollowUp.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.light_grey, 0, 0);
			endFollowUp.setClickable(false);
			endFollowUp.clearAnimation();

			rootView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		Time time = new Time();
		time.setToNow();

		switch (v.getId()) {
		case R.id.light_door:
			AppViewModel.timeStampsDAO.setDoorTime(time.toMillis(false));
			String patType = AppViewModel.patientDAO.getPatientType();
			int patTypeId = AppViewModel.patientDAO.getPatientTypeId();
			if ((patType == null || patType.length() <= 0) && patTypeId <= 0) {
				AppViewModel.patientDAO.setPatientType(appContext
						.getString(R.string.radio_door));
				AppViewModel.patientDAO.setPatientTypeId(R.id.radio_door);
			}
			setLights();
			break;
		case R.id.light_ct_begin:
			AppViewModel.timeStampsDAO.setCTBeginTime(time.toMillis(false));
			setLights();
			ctRecommendation();
			break;
		case R.id.light_ct_statements:
			AppViewModel.timeStampsDAO.setCTStatementTime(time.toMillis(false));
			setLights();
			break;
		case R.id.light_end_follow_up:
			Intent finishIntent = new Intent(appContext,
					DialogFinishFollowUp.class);
			// finishIntent
			// .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.startActivityForResult(finishIntent, 1);
			// getActivity()
			// .startActivityF(finishIntent);
			break;
		// case R.id.light_thrombolysis_bolus:
		// break;
		// case R.id.light_thrombolysis_infusion_begin:
		// break;
		// case R.id.light_thrombolysis_infusion_end:
		// break;
		}
	}
	
	public void ctRecommendation(){
		int basilari = AppViewModel.symptomDAO.getBasilarisId();
		int quickRec = AppViewModel.symptomDAO.getQuickRecovSympId();
		int mildSym = AppViewModel.symptomDAO.getMildSympId();
		String recommenStr = "";
		
		if(basilari == R.id.radio_basilaris_main_suspicion){
			recommenStr += appContext.getString(R.string.recommend_info_ct_basilari) + "\n\n";
		}
		if(quickRec == R.id.radio_quick_recover_symptoms_yes){
			recommenStr += appContext.getString(R.string.recommend_info_ct_quick_recover) + "\n\n";
		}
		if(mildSym == R.id.radio_mild_symptoms_yes){
			recommenStr += appContext.getString(R.string.recommend_info_ct_mild_symptom) + "\n\n";
		}
		
		if(recommenStr.length() > 0){
			Intent intent = new Intent(appContext, DialogHelpInfo.class);
			intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
					appContext.getString(R.string.recommend_title_ct));
			intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
					recommenStr);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.startActivity(intent);
		}
	}
}
