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
import android.widget.TableRow;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class SummaryPresentCondition {

	private TableRow activeBleedView;
	private TableRow cardioInfectionView;
	private TableRow obstetricView;
	private TableRow terminalDiseaseView;
	private TableRow otherPresConditionView;
	private TableRow quickRecoverNoThromView;
	private TableRow savNormalCtView;
	private TableRow mildStrokeNoThromView;

	private LinearLayout cardioInfectionList;
	private LinearLayout obstetricList;

	private TextView activeBleedText;
	private TextView cardioInfectionText;
	private TextView obstetricText;
	private TextView terminalDiseaseText;
	private TextView otherPresConditionText;
	private TextView quickRecoverNoThromText;
	private TextView savNormalCtText;
	private TextView mildStrokeNoThromText;

	private View rootView;
	private Fragment appContext;

	public SummaryPresentCondition(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TextViews */
		activeBleedView = (TableRow) rootView
				.findViewById(R.id.row_present_active_bleeding);
		cardioInfectionView = (TableRow) rootView
				.findViewById(R.id.row_present_cardiovascular_infections);
		obstetricView = (TableRow) rootView
				.findViewById(R.id.row_present_obstetric_contraindications);
		terminalDiseaseView = (TableRow) rootView
				.findViewById(R.id.row_symptom_terminal_disease);
		otherPresConditionView = (TableRow) rootView
				.findViewById(R.id.row_other_present_condition);
		quickRecoverNoThromView = (TableRow) rootView
				.findViewById(R.id.row_summary_quick_recover_not_in_ct);
		savNormalCtView = (TableRow) rootView
				.findViewById(R.id.row_summary_suggest_sav_nomal_ct);
		mildStrokeNoThromView = (TableRow) rootView
				.findViewById(R.id.row_summary_mild_stroke_without_arterail_thrombosis);

		/** LinearLayouts */
		cardioInfectionList = (LinearLayout) rootView
				.findViewById(R.id.list_present_cardiovascular_infections);
		obstetricList = (LinearLayout) rootView
				.findViewById(R.id.list_present_obstetric_contraindications);

		activeBleedText = (TextView) rootView
				.findViewById(R.id.info_present_active_bleeding);
		cardioInfectionText = (TextView) rootView
				.findViewById(R.id.info_present_cardiovascular_infections);
		obstetricText = (TextView) rootView
				.findViewById(R.id.info_present_obstetric_contraindications);
		terminalDiseaseText = (TextView) rootView
				.findViewById(R.id.info_symptom_terminal_disease);
		otherPresConditionText = (TextView) rootView
				.findViewById(R.id.info_other_present_condition);
		quickRecoverNoThromText = (TextView) rootView
				.findViewById(R.id.info_summary_quick_recover_not_in_ct);
		savNormalCtText = (TextView) rootView
				.findViewById(R.id.info_summary_suggest_sav_nomal_ct);
		mildStrokeNoThromText = (TextView) rootView
				.findViewById(R.id.info_summary_mild_stroke_without_arterail_thrombosis);
	}

	public void setUserSelection() {

		String tempStr = AppViewModel.presentConditionsDAO.getActiveBleed();
		if (tempStr != null && tempStr.length() > 0) {
			activeBleedText.setVisibility(TextView.VISIBLE);
			activeBleedText.setText(tempStr);
			switch (AppViewModel.presentConditionsDAO.getActiveBleedId()) {
			case R.id.radio_active_bleeding_no:
			case R.id.radio_active_bleeding_unknown:
				activeBleedText.setBackgroundColor(FragmentSummary.green);
				break;
			case R.id.radio_active_bleeding_yes:
				activeBleedText.setBackgroundColor(FragmentSummary.red);
				break;
			}
			activeBleedView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			activeBleedText.setVisibility(TextView.GONE);
			activeBleedView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.presentConditionsDAO.getCardiovascular();
		if (tempStr != null && tempStr.length() > 0) {
			cardioInfectionText.setVisibility(TextView.VISIBLE);
			cardioInfectionText.setText(tempStr);
			switch (AppViewModel.presentConditionsDAO.getCardiovascularId()) {
			case R.id.radio_cardiovascular_infections_no:
			case R.id.radio_cardiovascular_infections_unknown:
				cardioInfectionText.setBackgroundColor(FragmentSummary.green);
				cardioInfectionList.setVisibility(LinearLayout.GONE);
				cardioInfectionView
						.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_cardiovascular_infections_yes:
				cardioInfectionText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.presentConditionsDAO
						.getCardioInfectionResults();
				if (tempResults.size() > 0) {
					cardioInfectionList.removeAllViews();
					cardioInfectionList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						cardioInfectionList.addView(geneTextForList(resouceId,
								cardioInfectionList));
					}
					cardioInfectionView
							.setBackgroundColor(FragmentSummary.transparent);
				} else {
					cardioInfectionText.setVisibility(TextView.GONE);
					cardioInfectionList.setVisibility(TextView.GONE);
					cardioInfectionView
							.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			cardioInfectionText.setVisibility(TextView.GONE);
			cardioInfectionView.setBackgroundColor(FragmentSummary.grey);
			cardioInfectionList.setVisibility(LinearLayout.GONE);
		}

		tempStr = AppViewModel.presentConditionsDAO.getObstetric();
		if (tempStr != null && tempStr.length() > 0) {
			obstetricText.setVisibility(TextView.VISIBLE);
			obstetricText.setText(tempStr);
			switch (AppViewModel.presentConditionsDAO.getObstetricId()) {
			case R.id.radio_obstetric_contraindications_no:
			case R.id.radio_obstetric_contraindications_unknown:
				obstetricText.setBackgroundColor(FragmentSummary.green);
				obstetricList.setVisibility(LinearLayout.GONE);
				obstetricView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_obstetric_contraindications_hellp:
				obstetricText.setBackgroundColor(FragmentSummary.red);
				obstetricList.setVisibility(LinearLayout.GONE);
				obstetricView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_obstetric_contraindications_yes_relative:
				obstetricText.setBackgroundColor(FragmentSummary.yellow);
				ArrayList<Integer> tempResults = AppViewModel.presentConditionsDAO
						.getObstetricContrasResults();
				if (tempResults.size() > 0) {
					obstetricList.removeAllViews();
					obstetricList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						obstetricList.addView(geneTextForList(resouceId,
								obstetricList));
					}
					obstetricView
							.setBackgroundColor(FragmentSummary.transparent);
				} else {
					obstetricText.setVisibility(TextView.GONE);
					obstetricList.setVisibility(TextView.GONE);
					obstetricView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			obstetricText.setVisibility(TextView.GONE);
			obstetricView.setBackgroundColor(FragmentSummary.grey);
			obstetricList.setVisibility(LinearLayout.GONE);
		}

		tempStr = AppViewModel.symptomDAO.getTermiDisease();
		if (tempStr != null && tempStr.length() > 0) {
			terminalDiseaseText.setVisibility(TextView.VISIBLE);
			terminalDiseaseText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getTermiDiseaseId()) {
			case R.id.radio_terminal_disease_no:
			case R.id.radio_terminal_disease_unknown:
				terminalDiseaseText.setBackgroundColor(FragmentSummary.green);
				break;
			case R.id.radio_terminal_disease_yes:
				terminalDiseaseText.setBackgroundColor(FragmentSummary.red);
				break;
			}
			terminalDiseaseView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			terminalDiseaseText.setVisibility(TextView.GONE);
			terminalDiseaseView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.presentConditionsDAO.getOther();
		if (tempStr != null && tempStr.length() > 0) {
			otherPresConditionText.setVisibility(TextView.VISIBLE);
			otherPresConditionText.setText(tempStr);
			switch (AppViewModel.presentConditionsDAO.getOtherId()) {
			case R.id.radio_other_present_condition_no:
				otherPresConditionText
						.setBackgroundColor(FragmentSummary.green);
				break;
			case R.id.radio_other_present_condition_yes:
				otherPresConditionText.setBackgroundColor(FragmentSummary.red);
				break;
			}
			otherPresConditionView
					.setBackgroundColor(FragmentSummary.transparent);
		} else {
			otherPresConditionText.setVisibility(TextView.GONE);
			otherPresConditionView.setBackgroundColor(FragmentSummary.grey);
		}

//		tempStr = AppViewModel.anamnesisDAO.getMyocarInfarction();
//		if (tempStr != null && tempStr.length() > 0) {
//			quickRecoverNoThromText.setVisibility(TextView.VISIBLE);
//			quickRecoverNoThromText.setText(tempStr);
//			switch (AppViewModel.anamnesisDAO.getMyocarInfarctionId()) {
//			case R.id.radio_acute_myocardial_infarction_yes:
//				quickRecoverNoThromText
//						.setBackgroundColor(FragmentSummary.yellow);
//				break;
//			default:
//				quickRecoverNoThromText
//						.setBackgroundColor(FragmentSummary.green);
//				break;
//			}
//			quickRecoverNoThromView
//					.setBackgroundColor(FragmentSummary.transparent);
//		} else {
//			quickRecoverNoThromText.setVisibility(TextView.GONE);
//			quickRecoverNoThromView.setBackgroundColor(FragmentSummary.grey);
//		}
	}

	public TextView geneTextForList(int resouceId, ViewGroup parent) {
		LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		lparams.setMargins(0, 5, 0, 5);
		TextView text = new TextView(appContext.getActivity());
		text.setLayoutParams(lparams);

		text.setText(appContext.getString(resouceId));
		switch (resouceId) {
		case R.string.radio_cardiovascular_infections_endocarditis:
		case R.string.radio_cardiovascular_infections_pericarditis:
		case R.string.radio_cardiovascular_infections_embolus:
		case R.string.radio_other_critical:

			text.setBackgroundColor(FragmentSummary.red);
			break;

		case R.string.radio_obstetric_contraindications_pregnancy:
		case R.string.radio_obstetric_contraindications_placenta:
		case R.string.radio_other_relative_critical:
			text.setBackgroundColor(FragmentSummary.yellow);
			break;
		case R.string.radio_other_not_critical:
			text.setBackgroundColor(FragmentSummary.green);
			break;
		}
		return text;
	}
}
