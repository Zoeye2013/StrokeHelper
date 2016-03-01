package fi.etla.strokehelper.summary;

import java.util.ArrayList;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.Time;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class SummaryThrombolysis {

	private TableRow decisionView;
	private LinearLayout thromboYesView;
	private TableRow patWeightView;
	private TableRow bolusView;
	private TableRow infusionView;
	private TableRow nihss0View;
	private TableRow nihss1View;
	private TableRow nihss24View;
	private TableRow rrFoUpLevelView;
	private TableRow rr0View;
	private TableRow rr15View;
	private TableRow rr30View;
	private TableRow rr1View;
	private TableRow rr2View;
	private TableRow rr24View;
	private TextView controlCtView;
	
	private LinearLayout interrView;
	private LinearLayout endFoUpView;
//	private TableRow interrTimeView;
//	private TableRow interrReasonView;
//	private TableRow endTimeView;
//	private TableRow endReasonView;

	private TextView decisionText;
	private TextView patWeightText;
	private TextView bolusText;
	private TextView bolusTimeText;
	private TextView infusionText;
	private TextView infusionBeginText;
	private TextView infusionEndText;
	private TextView nihss0Text;
	private TextView nihss1Text;
	private TextView nihss24Text;
	private TextView rrFoUpLevelText;
	private TextView rr0Text;
	private TextView rr15Text;
	private TextView rr30Text;
	private TextView rr1Text;
	private TextView rr2Text;
	private TextView rr24Text;
	private TextView interrTimeText;
	private TextView interrReasonText;
	private TextView endTimeText;
	private TextView endReasonText;
	private LinearLayout controlCtList;

	private View rootView;
	private Fragment appContext;

	public SummaryThrombolysis(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TableRows */
		decisionView = (TableRow) rootView
				.findViewById(R.id.row_thrombolysis_decision);
		thromboYesView = (LinearLayout) rootView
				.findViewById(R.id.view_throbolysis_yes);
		patWeightView = (TableRow) rootView
				.findViewById(R.id.row_patient_weight);
		bolusView = (TableRow) rootView
				.findViewById(R.id.row_thrombolysis_bolus);
		infusionView = (TableRow) rootView
				.findViewById(R.id.row_thrombolysis_infusion);
		nihss0View = (TableRow) rootView.findViewById(R.id.row_summary_nihss_0);
		nihss1View = (TableRow) rootView.findViewById(R.id.row_summary_nihss_1);
		nihss24View = (TableRow) rootView
				.findViewById(R.id.row_summary_nihss_24);
		rrFoUpLevelView = (TableRow) rootView
				.findViewById(R.id.row_summary_rr_follow_up_level);
		rr0View = (TableRow) rootView.findViewById(R.id.row_rr_0min);
		rr15View = (TableRow) rootView.findViewById(R.id.row_rr_15min);
		rr30View = (TableRow) rootView.findViewById(R.id.row_rr_30min);
		rr1View = (TableRow) rootView.findViewById(R.id.row_rr_1h);
		rr2View = (TableRow) rootView.findViewById(R.id.row_rr_2h);
		rr24View = (TableRow) rootView.findViewById(R.id.row_rr_24h);
		controlCtView = (TextView) rootView.findViewById(R.id.row_control_ct);
		interrView = (LinearLayout)rootView.findViewById(R.id.view_interruption_thrombolysis);
		endFoUpView = (LinearLayout)rootView.findViewById(R.id.view_end_follow_up);
//		interrTimeView = (TableRow) rootView
//				.findViewById(R.id.row_summary_interrupt_time);
//		interrReasonView = (TableRow) rootView
//				.findViewById(R.id.row_summary_interrupt_reason);
//		endTimeView = (TableRow) rootView
//				.findViewById(R.id.row_summary_end_time);
//		endReasonView = (TableRow) rootView
//				.findViewById(R.id.row_summary_end_reason);

		/** TextViews */
		decisionText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_decision);
		patWeightText = (TextView) rootView
				.findViewById(R.id.info_patient_weight);
		bolusText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_bolus);
		bolusTimeText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_bolus_time);
		infusionText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_infusion);
		infusionBeginText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_infusion_begin_time);

		infusionEndText = (TextView) rootView
				.findViewById(R.id.info_thrombolysis_infusion_end_time);
		nihss0Text = (TextView) rootView
				.findViewById(R.id.info_summary_nihss_0);
		nihss1Text = (TextView) rootView
				.findViewById(R.id.info_summary_nihss_1);
		nihss24Text = (TextView) rootView
				.findViewById(R.id.info_summary_nihss_24);
		rrFoUpLevelText = (TextView) rootView
				.findViewById(R.id.info_summary_rr_follow_up_level);
		rr0Text = (TextView) rootView.findViewById(R.id.info_rr_0min);
		rr15Text = (TextView) rootView.findViewById(R.id.info_rr_15min);
		rr30Text = (TextView) rootView.findViewById(R.id.info_rr_30min);
		rr1Text = (TextView) rootView.findViewById(R.id.info_rr_1h);
		rr2Text = (TextView) rootView.findViewById(R.id.info_rr_2h);
		rr24Text = (TextView) rootView.findViewById(R.id.info_rr_24h);
		interrTimeText = (TextView) rootView
				.findViewById(R.id.info_summary_interrupt_time);
		interrReasonText = (TextView) rootView
				.findViewById(R.id.info_summary_interrupt_reason);
		endTimeText = (TextView) rootView
				.findViewById(R.id.info_summary_end_time);
		endReasonText = (TextView) rootView
				.findViewById(R.id.info_summary_end_reason);

		controlCtList = (LinearLayout) rootView
				.findViewById(R.id.list_control_ct);
	}

	public void setUserSelection() {
		long decisionTime = AppViewModel.timeStampsDAO.getDecisionTime();
		int decision = AppViewModel.thrombolysisDAO.getDecisionId();
		if (decisionTime > 0) {
			decisionText.setVisibility(TextView.VISIBLE);
			decisionText.setText(AppViewModel.thrombolysisDAO.getDecision());

			if (decision == R.id.radio_thrombolysis_decision_yes)
				decisionText.setBackgroundColor(FragmentSummary.green);
			else if (decision == R.id.radio_thrombolysis_decision_no)
				decisionText.setBackgroundColor(FragmentSummary.yellow);
			decisionView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			decisionText.setVisibility(TextView.GONE);
			decisionView.setBackgroundColor(FragmentSummary.grey);
		}

		if (decision == R.id.radio_thrombolysis_decision_yes) {
			thromboYesView.setVisibility(LinearLayout.VISIBLE);
			setThrombolysis();
		} else
			thromboYesView.setVisibility(LinearLayout.GONE);
		
		
		/** Set Interruption of thrombolysis part */
		boolean isInterr = AppViewModel.thrombolysisDAO.getIsInterrupt();
		long interrT = AppViewModel.timeStampsDAO.getInterruptionTime();
		String interrReason = AppViewModel.thrombolysisDAO.getInterruptReasons();
		if (isInterr) {
			interrView.setVisibility(LinearLayout.VISIBLE);
			interrTimeText.setText(AppViewModel.timeToString(interrT));
			interrTimeText.setBackgroundColor(FragmentSummary.green);
			interrReasonText.setText(interrReason);
		} else {
			interrTimeText.setText("");
			interrReasonText.setText("");
			interrTimeText.setBackgroundColor(FragmentSummary.transparent);
			interrView.setVisibility(LinearLayout.GONE);
		}
	}

	/** Only when Thrombolysis decision is Yes */
	public void setThrombolysis() {
		double weight = AppViewModel.patientDAO.getWeight();
		if (weight > 0) {
			patWeightText.setVisibility(TextView.VISIBLE);
			patWeightText.setText(weight + " kg");
			patWeightText.setBackgroundColor(FragmentSummary.green);
			patWeightView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			patWeightText.setVisibility(TextView.GONE);
			patWeightView.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set Bolus part */
		double bolus = AppViewModel.thrombolysisDAO.getBolusDose();
		long bolusTime = AppViewModel.timeStampsDAO.getBolusTime();
		if (weight > 0 && bolus > 0) {
			bolusView.setVisibility(TableRow.VISIBLE);
			bolusText.setText(bolus + " ml");
			bolusText.setBackgroundColor(FragmentSummary.green);
			bolusView.setBackgroundColor(FragmentSummary.transparent);
			if (bolusTime > 0) {
				bolusTimeText.setVisibility(TableRow.VISIBLE);
				bolusTimeText.setText("Given at: "
						+ AppViewModel.timeToString(bolusTime));
				bolusTimeText.setBackgroundColor(FragmentSummary.green);
			} else {
				bolusTimeText.setText("");
				bolusTimeText.setVisibility(TableRow.INVISIBLE);
			}
		} else {
			bolusText.setVisibility(TextView.GONE);
			bolusView.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set Infusion part */
		double infusion = AppViewModel.thrombolysisDAO.getInfusionDose();
		long infusionBeT = AppViewModel.timeStampsDAO.getInfusionBeTime();
		long infusionEndT = AppViewModel.timeStampsDAO.getInfusionEndTime();
		if (weight > 0 && infusion > 0) {
			infusionView.setVisibility(TableRow.VISIBLE);
			infusionText.setText(infusion + " ml");
			infusionText.setBackgroundColor(FragmentSummary.green);
			infusionView.setBackgroundColor(FragmentSummary.transparent);
			if (infusionBeT > 0) {
				infusionBeginText.setVisibility(TableRow.VISIBLE);
				infusionBeginText.setText("Given at: "
						+ AppViewModel.timeToString(infusionBeT));
				infusionBeginText.setBackgroundColor(FragmentSummary.green);
			} else {
				infusionBeginText.setText("");
				infusionBeginText.setVisibility(TableRow.INVISIBLE);
			}

			if (infusionBeT > 0 && infusionEndT > 0) {
				infusionEndText.setVisibility(TableRow.VISIBLE);
				infusionEndText.setText("Infusion ended: "
						+ AppViewModel.timeToString(infusionEndT));
				infusionEndText.setBackgroundColor(FragmentSummary.green);
			} else {
				infusionEndText.setText("");
				infusionEndText.setVisibility(TableRow.INVISIBLE);
			}
		} else {
			infusionText.setVisibility(TextView.GONE);
			infusionView.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set NIHSS part */
		String test0Time = AppViewModel.nihssDAO.getNihssBaseline()
				.getTestTime();
		String test1Time = AppViewModel.nihssDAO.getNihss1H().getTestTime();
		String test24Time = AppViewModel.nihssDAO.getNihss24H().getTestTime();
		int nihss0 = AppViewModel.nihssDAO.getNihssBaseline().getNihssTotal();
		int nihss1 = AppViewModel.nihssDAO.getNihss1H().getNihssTotal();
		int nihss24 = AppViewModel.nihssDAO.getNihss24H().getNihssTotal();
		if (test0Time != null && test0Time.length() > 0) {
			nihss0Text.setVisibility(TextView.VISIBLE);
			nihss0Text.setText(String.valueOf(nihss0));
			nihss0Text.setBackgroundColor(FragmentSummary.green);
			nihss0View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			nihss0Text.setVisibility(TextView.GONE);
			nihss0View.setBackgroundColor(FragmentSummary.grey);
		}
		if (test1Time != null && test1Time.length() > 0) {
			nihss1Text.setVisibility(TextView.VISIBLE);
			nihss1Text.setText(String.valueOf(nihss1));
			nihss1Text.setBackgroundColor(FragmentSummary.green);
			nihss1View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			nihss1Text.setVisibility(TextView.GONE);
			nihss1View.setBackgroundColor(FragmentSummary.grey);
		}
		if (test24Time != null && test24Time.length() > 0) {
			nihss24Text.setVisibility(TextView.VISIBLE);
			nihss24Text.setText(String.valueOf(nihss24));
			nihss24Text.setBackgroundColor(FragmentSummary.green);
			nihss24View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			nihss24Text.setVisibility(TextView.GONE);
			nihss24View.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set RR follow-up level part */
		int rrFoUpLevel = AppViewModel.thrombolysisDAO.getRRFollowTypeId();
		String rrFoUpLevelStr = AppViewModel.thrombolysisDAO.getRRFollowType();
		if(rrFoUpLevel > 0){
				rrFoUpLevelText.setVisibility(TextView.VISIBLE);
				rrFoUpLevelText.setText(rrFoUpLevelStr);
				rrFoUpLevelText.setBackgroundColor(FragmentSummary.green);
				rrFoUpLevelView.setBackgroundColor(FragmentSummary.transparent);
			} else {
				rrFoUpLevelText.setVisibility(TextView.GONE);
				rrFoUpLevelView.setBackgroundColor(FragmentSummary.grey);
			}
		
		/** Set RR (0 min) part */
		int rrSys = 0;
		int rrDia = 0;
		boolean rrTreatDeci = AppViewModel.labDAO.getRRTreatDecision();

		if (rrTreatDeci == true) {
			rrSys = AppViewModel.labDAO.getRRAftSystolic();
			rrDia = AppViewModel.labDAO.getRRAftDiastolic();
		} else if (rrTreatDeci == false) {
			rrSys = AppViewModel.labDAO.getRRSystolic();
			rrDia = AppViewModel.labDAO.getRRDiastolic();
		}

		if (rrSys > 0 && rrDia > 0) {
			rr0Text.setVisibility(TextView.VISIBLE);
			rr0Text.setText(rrSys + " / " + rrDia + " mmHg");
			rr0Text.setBackgroundColor(FragmentSummary.green);
			rr0View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr0Text.setVisibility(TextView.GONE);
			rr0View.setBackgroundColor(FragmentSummary.grey);
		}
		/** Set RR (15 min) part */
		int rrSys15 = AppViewModel.thrombolysisDAO.getRRSystolic15min();
		int rrDia15 = AppViewModel.thrombolysisDAO.getRRDiastolic15min();
		if (rrSys15 > 0 && rrDia15 > 0) {
			rr15Text.setVisibility(TextView.VISIBLE);
			rr15Text.setText(rrSys15 + " / " + rrDia15 + " mmHg");
			rr15Text.setBackgroundColor(FragmentSummary.green);
			rr15View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr15Text.setVisibility(TextView.GONE);
			rr15View.setBackgroundColor(FragmentSummary.grey);
		}
		/** Set RR (30 min) part */
		int rrSys30 = AppViewModel.thrombolysisDAO.getRRSystolic30min();
		int rrDia30 = AppViewModel.thrombolysisDAO.getRRDiastolic30min();
		if (rrSys30 > 0 && rrDia30 > 0) {
			rr30Text.setVisibility(TextView.VISIBLE);
			rr30Text.setText(rrSys30 + " / " + rrDia30 + " mmHg");
			rr30Text.setBackgroundColor(FragmentSummary.green);
			rr30View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr30Text.setVisibility(TextView.GONE);
			rr30View.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set RR (1 h) part */
		int rrSys1 = AppViewModel.thrombolysisDAO.getRRSystolic1h();
		int rrDia1 = AppViewModel.thrombolysisDAO.getRRDiastolic1h();
		if (rrSys1 > 0 && rrDia1 > 0) {
			rr1Text.setVisibility(TextView.VISIBLE);
			rr1Text.setText(rrSys1 + " / " + rrDia1 + " mmHg");
			rr1Text.setBackgroundColor(FragmentSummary.green);
			rr1View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr1Text.setVisibility(TextView.GONE);
			rr1View.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set RR (2 h) part */
		int rrSys2 = AppViewModel.thrombolysisDAO.getRRSystolic2h();
		int rrDia2 = AppViewModel.thrombolysisDAO.getRRDiastolic2h();
		if (rrSys2 > 0 && rrDia2 > 0) {
			rr2Text.setVisibility(TextView.VISIBLE);
			rr2Text.setText(rrSys2 + " / " + rrDia2 + " mmHg");
			rr2Text.setBackgroundColor(FragmentSummary.green);
			rr2View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr2Text.setVisibility(TextView.GONE);
			rr2View.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set RR (24 h) part */
		int rrSys24 = AppViewModel.thrombolysisDAO.getRRSystolic24h();
		int rrDia24 = AppViewModel.thrombolysisDAO.getRRDiastolic24h();
		if (rrSys24 > 0 && rrDia24 > 0) {
			rr24Text.setVisibility(TextView.VISIBLE);
			rr24Text.setText(rrSys24 + " / " + rrDia24 + " mmHg");
			rr24Text.setBackgroundColor(FragmentSummary.green);
			rr24View.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rr24Text.setVisibility(TextView.GONE);
			rr24View.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set Control CT part */
		ArrayList<Integer> controlCtResults = AppViewModel.thrombolysisDAO
				.getControlCtResults();
		if (controlCtResults.size() > 0) {
			controlCtList.removeAllViews();
			controlCtList.setVisibility(TextView.VISIBLE);
			for (int resouceId : controlCtResults) {
				controlCtList.addView(geneTextForList(resouceId,
						controlCtList));
			}

			controlCtView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			controlCtList.setVisibility(TextView.GONE);
			controlCtView.setBackgroundColor(FragmentSummary.grey);
		}

		/** Set End follow-up part */
		boolean isFoUpEnd = AppViewModel.thrombolysisDAO.getIsFollowUpFinish();
		long foUpEndTime = AppViewModel.timeStampsDAO.getFollowEndTime();
		String foUpEndReason = AppViewModel.thrombolysisDAO.getFinishFollowUpReasons();
		if (isFoUpEnd) {
			endFoUpView.setVisibility(LinearLayout.VISIBLE);
			endTimeText.setText(AppViewModel.timeToString(foUpEndTime));
			endTimeText.setBackgroundColor(FragmentSummary.green);
			endReasonText.setText(foUpEndReason);
		} else {
			endTimeText.setText("");
			endReasonText.setText("");
			endTimeText.setBackgroundColor(FragmentSummary.transparent);
			endFoUpView.setVisibility(LinearLayout.GONE);
		}
	}

	public TextView geneTextForList(int resouceId, ViewGroup parent) {
		LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		lparams.setMargins(0, 5, 0, 5);
		TextView text = new TextView(appContext.getActivity());
		text.setLayoutParams(lparams);

		text.setText(appContext.getString(resouceId));
		switch (resouceId) {
		case R.string.radio_admission_ct_no_visible_sign:
		case R.string.radio_admission_ct_hyper_sign:
		case R.string.radio_admission_ct_early_infarct_sign:
		case R.string.radio_admission_ct_ischemia_less:
			text.setBackgroundColor(FragmentSummary.green);
			break;
		case R.string.radio_admission_ct_ischemia_more:
		case R.string.radio_admission_ct_bleeding:
		case R.string.radio_admission_ct_tumor:
		case R.string.radio_admission_ct_abscess:
		case R.string.radio_admission_ct_other:
			text.setBackgroundColor(FragmentSummary.red);
			break;
		}
		return text;
	}
}
