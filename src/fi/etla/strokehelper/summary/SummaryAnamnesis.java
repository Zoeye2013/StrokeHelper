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

public class SummaryAnamnesis {

	private TableRow cerebroIncidentView;
	private TableRow diseaseView;
	private TableRow bleedingView;
	private TableRow operationView;
	private TableRow vesselView;
	private TableRow myocarInfarctView;

	private LinearLayout cerebroIncidentList;
	private LinearLayout diseaseList;
	private LinearLayout bleedingList;
	private LinearLayout operationList;
	private LinearLayout vesselList;

	private TextView cerebroIncidentText;
	private TextView diseaseText;
	private TextView bleedingText;
	private TextView operationText;
	private TextView vesselText;
	private TextView myocarInfarctText;

	private View rootView;
	private Fragment appContext;

	public SummaryAnamnesis(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TextViews */
		cerebroIncidentView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_cerebrovascular_incidents);
		diseaseView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_disease_bleeding_tendency);
		bleedingView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_significant_bleeding);
		operationView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_operation);
		vesselView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_blood_vessel_conditions);
		myocarInfarctView = (TableRow) rootView
				.findViewById(R.id.row_anamnesis_acute_myocardial_infarction);

		/** LinearLayouts */
		cerebroIncidentList = (LinearLayout) rootView
				.findViewById(R.id.list_anamnesis_cerebrovascular_incidents);
		diseaseList = (LinearLayout) rootView
				.findViewById(R.id.list_anamnesis_disease_bleeding_tendency);
		bleedingList = (LinearLayout) rootView
				.findViewById(R.id.list_anamnesis_significant_bleeding);
		operationList = (LinearLayout) rootView
				.findViewById(R.id.list_anamnesis_operation);
		vesselList = (LinearLayout) rootView
				.findViewById(R.id.list_anamnesis_blood_vessel_conditions);

		cerebroIncidentText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_cerebrovascular_incidents);
		diseaseText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_disease_bleeding_tendency);
		bleedingText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_significant_bleeding);
		operationText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_operation);
		vesselText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_blood_vessel_conditions);
		myocarInfarctText = (TextView) rootView
				.findViewById(R.id.info_anamnesis_acute_myocardial_infarction);
	}

	public void setUserSelection() {

		String tempStr = AppViewModel.anamnesisDAO.getCerebroIncident();
		if (tempStr != null && tempStr.length() > 0) {
			cerebroIncidentText.setVisibility(TextView.VISIBLE);
			cerebroIncidentText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getCerebroIncidentId()) {
			case R.id.radio_cerebrovascular_incidents_no:
			case R.id.radio_cerebrovascular_incidents_unknown:
				cerebroIncidentText.setBackgroundColor(FragmentSummary.green);
				cerebroIncidentList.setVisibility(LinearLayout.GONE);
				cerebroIncidentView
						.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_cerebrovascular_incidents_yes:
				cerebroIncidentText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.anamnesisDAO
						.getCerebroIncidentResults();
				if (tempResults.size() > 0) {
					cerebroIncidentList.removeAllViews();
					cerebroIncidentList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						cerebroIncidentList.addView(geneTextForList(resouceId,
								cerebroIncidentList));
					}
					cerebroIncidentView
							.setBackgroundColor(FragmentSummary.transparent);
				} else {
					cerebroIncidentText.setVisibility(TextView.GONE);
					cerebroIncidentList.setVisibility(TextView.GONE);
					cerebroIncidentView
							.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			cerebroIncidentText.setVisibility(TextView.GONE);
			cerebroIncidentView.setBackgroundColor(FragmentSummary.grey);
			cerebroIncidentList.setVisibility(LinearLayout.GONE);
		}

		tempStr = AppViewModel.anamnesisDAO.getDisease();
		if (tempStr != null && tempStr.length() > 0) {
			diseaseText.setVisibility(TextView.VISIBLE);
			diseaseText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getDiseaseId()) {
			case R.id.radio_disease_bleeding_tendency_no:
			case R.id.radio_disease_bleeding_tendency_unknown:
				diseaseText.setBackgroundColor(FragmentSummary.green);
				diseaseList.setVisibility(LinearLayout.GONE);
				diseaseView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_disease_bleeding_tendency_yes:
				diseaseText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.anamnesisDAO
						.getDiseaseBleedingTendencyResults();
				if (tempResults.size() > 0) {
					diseaseList.removeAllViews();
					diseaseList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						diseaseList.addView(geneTextForList(resouceId,
								diseaseList));
					}
					diseaseView.setBackgroundColor(FragmentSummary.transparent);
				} else {
					diseaseText.setVisibility(TextView.GONE);
					diseaseList.setVisibility(TextView.GONE);
					diseaseView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			diseaseText.setVisibility(TextView.GONE);
			diseaseView.setBackgroundColor(FragmentSummary.grey);
			diseaseList.setVisibility(LinearLayout.GONE);
		}

		tempStr = AppViewModel.anamnesisDAO.getBleeding();
		if (tempStr != null && tempStr.length() > 0) {
			bleedingText.setVisibility(TextView.VISIBLE);
			bleedingText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getBleedingId()) {
			case R.id.radio_significant_bleeding_no:
			case R.id.radio_significant_bleeding_unknown:
				bleedingText.setBackgroundColor(FragmentSummary.green);
				bleedingList.setVisibility(LinearLayout.GONE);
				bleedingView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_significant_bleeding_yes:
				bleedingText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.anamnesisDAO
						.getSignificantBleedingResults();
				if (tempResults.size() > 0) {
					bleedingList.removeAllViews();
					bleedingList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						bleedingList.addView(geneTextForList(resouceId,
								bleedingList));
					}
					bleedingView.setBackgroundColor(FragmentSummary.transparent);
				} else {
					bleedingText.setVisibility(TextView.GONE);
					bleedingList.setVisibility(TextView.GONE);
					bleedingView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			bleedingText.setVisibility(TextView.GONE);
			bleedingView.setBackgroundColor(FragmentSummary.grey);
			bleedingList.setVisibility(LinearLayout.GONE);
		}
		
		tempStr = AppViewModel.anamnesisDAO.getOperation();
		if (tempStr != null && tempStr.length() > 0) {
			operationText.setVisibility(TextView.VISIBLE);
			operationText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getOperationId()) {
			case R.id.radio_operation_no:
			case R.id.radio_operation_unknown:
				operationText.setBackgroundColor(FragmentSummary.green);
				operationList.setVisibility(LinearLayout.GONE);
				operationView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_operation_yes:
				operationText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.anamnesisDAO
						.getOperationResults();
				if (tempResults.size() > 0) {
					operationList.removeAllViews();
					operationList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						operationList.addView(geneTextForList(resouceId,
								operationList));
					}
					operationView.setBackgroundColor(FragmentSummary.transparent);
				} else {
					operationText.setVisibility(TextView.GONE);
					operationList.setVisibility(TextView.GONE);
					operationView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			operationText.setVisibility(TextView.GONE);
			operationView.setBackgroundColor(FragmentSummary.grey);
			operationList.setVisibility(LinearLayout.GONE);
		}
		
		tempStr = AppViewModel.anamnesisDAO.getVessel();
		if (tempStr != null && tempStr.length() > 0) {
			vesselText.setVisibility(TextView.VISIBLE);
			vesselText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getVesselId()) {
			case R.id.radio_blood_vessel_conditions_no:
			case R.id.radio_blood_vessel_conditions_unknown:
				vesselText.setBackgroundColor(FragmentSummary.green);
				vesselList.setVisibility(LinearLayout.GONE);
				vesselView.setBackgroundColor(FragmentSummary.transparent);
				break;
			case R.id.radio_blood_vessel_conditions_yes:
				vesselText.setBackgroundColor(FragmentSummary.red);
				ArrayList<Integer> tempResults = AppViewModel.anamnesisDAO
						.getVesselConditionResults();
				if (tempResults.size() > 0) {
					vesselList.removeAllViews();
					vesselList.setVisibility(TextView.VISIBLE);
					for (int resouceId : tempResults) {
						vesselList.addView(geneTextForList(resouceId,
								vesselList));
					}
					vesselView.setBackgroundColor(FragmentSummary.transparent);
				} else {
					vesselText.setVisibility(TextView.GONE);
					vesselList.setVisibility(TextView.GONE);
					vesselView.setBackgroundColor(FragmentSummary.grey);
				}
				break;
			}
		} else {
			vesselText.setVisibility(TextView.GONE);
			vesselView.setBackgroundColor(FragmentSummary.grey);
			vesselList.setVisibility(LinearLayout.GONE);
		}

		tempStr = AppViewModel.anamnesisDAO.getMyocarInfarction();
		if (tempStr != null && tempStr.length() > 0) {
			myocarInfarctText.setVisibility(TextView.VISIBLE);
			myocarInfarctText.setText(tempStr);
			switch (AppViewModel.anamnesisDAO.getMyocarInfarctionId()) {
			case R.id.radio_acute_myocardial_infarction_yes:
				myocarInfarctText.setBackgroundColor(FragmentSummary.yellow);
				break;
			default:
				myocarInfarctText.setBackgroundColor(FragmentSummary.green);
				break;
			}
			myocarInfarctView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			myocarInfarctText.setVisibility(TextView.GONE);
			myocarInfarctView.setBackgroundColor(FragmentSummary.grey);
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
		case R.string.radio_cerebrovascular_incidents_1:
		case R.string.radio_cerebrovascular_incidents_2:
		case R.string.radio_cerebrovascular_incidents_3:
		case R.string.radio_cerebrovascular_incidents_4:
		case R.string.radio_cerebrovascular_incidents_5:
			
		
		case R.string.radio_disease_bleeding_tendency_1:
		case R.string.radio_disease_bleeding_tendency_2:
		case R.string.radio_disease_bleeding_tendency_3:
		case R.string.radio_disease_bleeding_tendency_4:
			
		case R.string.radio_significant_bleeding_1:
		case R.string.radio_significant_bleeding_2:
		case R.string.radio_significant_bleeding_3:
		case R.string.radio_significant_bleeding_4:
		case R.string.radio_significant_bleeding_5:
			
		case R.string.radio_operation_1:
		case R.string.radio_operation_2:
		case R.string.radio_operation_3:
		case R.string.radio_operation_4:
		case R.string.radio_operation_5:
		case R.string.radio_operation_6:
			
		case R.string.radio_vessel_conditions_1:
		case R.string.radio_vessel_conditions_2:
		case R.string.radio_vessel_conditions_3:
		case R.string.radio_vessel_conditions_4:
			
		case R.string.radio_other_critical:
			
			text.setBackgroundColor(FragmentSummary.red);
			break;
		case R.string.radio_other_not_critical:
			text.setBackgroundColor(FragmentSummary.green);
			break;
			
		case R.string.radio_significant_bleeding_6:
			text.setBackgroundColor(FragmentSummary.yellow);
			break;
		}
		return text;
	}
}
