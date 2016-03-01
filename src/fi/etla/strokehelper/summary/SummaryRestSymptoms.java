package fi.etla.strokehelper.summary;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class SummaryRestSymptoms {

	private TableRow quickRecSympView;
	private TableRow mildSympView;
	private TableRow sugSavView;

	private TextView quickRecSympText;
	private TextView mildSympText;
	private TextView sugSavText;

	private View rootView;
	private Fragment appContext;

	public SummaryRestSymptoms(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TableRows */
		quickRecSympView = (TableRow) rootView
				.findViewById(R.id.row_symptom_quick_recover_symptoms);
		mildSympView = (TableRow) rootView
				.findViewById(R.id.row_symptom_mild_symptoms);
		sugSavView = (TableRow) rootView
				.findViewById(R.id.row_symptom_suggest_sav);

		/** TextViews */
		quickRecSympText = (TextView) rootView
				.findViewById(R.id.info_symptom_quick_recover_symptoms);
		mildSympText = (TextView) rootView
				.findViewById(R.id.info_symptom_mild_symptoms);
		sugSavText = (TextView) rootView
				.findViewById(R.id.info_symptom_suggest_sav);

	}

	public void setUserSelection() {
		
		String tempStr = AppViewModel.symptomDAO.getQuickRecovSymp();
		if (tempStr != null && tempStr.length() > 0) {
			quickRecSympText.setVisibility(TextView.VISIBLE);
			quickRecSympText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getQuickRecovSympId()) {
			case R.id.radio_quick_recover_symptoms_yes:
				quickRecSympText.setBackgroundColor(FragmentSummary.red);
				break;
			default:
				quickRecSympText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			quickRecSympView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			quickRecSympText.setVisibility(TextView.GONE);
			quickRecSympView.setBackgroundColor(FragmentSummary.grey);
		}
		
		tempStr = AppViewModel.symptomDAO.getMildSymp();
		if (tempStr != null && tempStr.length() > 0) {
			mildSympText.setVisibility(TextView.VISIBLE);
			mildSympText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getMildSympId()) {
			case R.id.radio_mild_symptoms_yes:
				mildSympText.setBackgroundColor(FragmentSummary.red);
				break;
			default:
				mildSympText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			mildSympView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			mildSympText.setVisibility(TextView.GONE);
			mildSympView.setBackgroundColor(FragmentSummary.grey);
		}
		
		tempStr = AppViewModel.symptomDAO.getSuggestSav();
		if (tempStr != null && tempStr.length() > 0) {
			sugSavText.setVisibility(TextView.VISIBLE);
			sugSavText.setText(tempStr);
			switch (AppViewModel.symptomDAO.getSuggestSavId()) {
			case R.id.radio_suggest_sav_yes:
				sugSavText.setBackgroundColor(FragmentSummary.red);
				break;
			default:
				sugSavText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			sugSavView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			sugSavText.setVisibility(TextView.GONE);
			sugSavView.setBackgroundColor(FragmentSummary.grey);
		}
	}
}
