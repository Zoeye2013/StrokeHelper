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

public class SummaryFindings {

	private TableRow nihssTotalView;
	private TableRow rrView;
	private TableRow rrAfterView;
	private TextView admissionCtView;
	private TableRow perfusionCtView;
	private TableRow ctAngiographyView;
	private TableRow quickInrView;
	private TableRow inrView;
	private TableRow apttView;
	private TableRow pTrombaiView;
	private TableRow bTromView;
	private TableRow bGlucView;
	private TableRow bGlucAfterView;
	private TableRow sedanView;
	private TableRow dragonView;

	private TextView nihssTotalText;
	private TextView rrText;
	private TextView rrAfterText;
	private LinearLayout admissionCtList;
	private TextView perfusionCtText;
	private TextView ctAngiographyText;
	private TextView quickInrText;
	private TextView inrText;
	private TextView apttText;
	private TextView pTrombaiText;
	private TextView bTromText;
	private TextView bGlucText;
	private TextView bGlucAfterText;
	private TextView sedanText;
	private TextView dragonText;

	private View rootView;
	private Fragment appContext;

	public SummaryFindings(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TableRows */
		nihssTotalView = (TableRow) rootView
				.findViewById(R.id.row_nihss_total_point);
		rrView = (TableRow) rootView.findViewById(R.id.row_lab_rr);
		rrAfterView = (TableRow) rootView
				.findViewById(R.id.row_lab_rr_after_treat);
		admissionCtView = (TextView) rootView
				.findViewById(R.id.row_lab_admission_ct);
		perfusionCtView = (TableRow) rootView
				.findViewById(R.id.row_lab_perfusion_ct);
		ctAngiographyView = (TableRow) rootView
				.findViewById(R.id.row_lab_ct_angiography);
		quickInrView = (TableRow) rootView.findViewById(R.id.row_lab_quick_inr);
		inrView = (TableRow) rootView.findViewById(R.id.row_lab_inr);
		apttView = (TableRow) rootView.findViewById(R.id.row_lab_aptt);
		pTrombaiView = (TableRow) rootView.findViewById(R.id.row_lab_p_trombai);
		bTromView = (TableRow) rootView.findViewById(R.id.row_lab_b_trom);
		bGlucView = (TableRow) rootView.findViewById(R.id.row_lab_b_gluc);
		bGlucAfterView = (TableRow) rootView
				.findViewById(R.id.row_lab_b_gluc_after);
		sedanView = (TableRow) rootView
				.findViewById(R.id.row_summary_sedan_score);
		dragonView = (TableRow) rootView
				.findViewById(R.id.row_summary_dragon_score);

		/** TextViews */
		nihssTotalText = (TextView) rootView
				.findViewById(R.id.info_nihss_total_point);
		rrText = (TextView) rootView.findViewById(R.id.info_lab_rr);
		rrAfterText = (TextView) rootView
				.findViewById(R.id.info_lab_rr_after_treat);
		admissionCtList = (LinearLayout) rootView
				.findViewById(R.id.list_admission_ct);
		perfusionCtText = (TextView) rootView
				.findViewById(R.id.info_lab_perfusion_ct);
		ctAngiographyText = (TextView) rootView
				.findViewById(R.id.info_lab_ct_angiography);
		quickInrText = (TextView) rootView
				.findViewById(R.id.info_lab_quick_inr);
		inrText = (TextView) rootView.findViewById(R.id.info_lab_inr);
		apttText = (TextView) rootView.findViewById(R.id.info_lab_aptt);
		pTrombaiText = (TextView) rootView
				.findViewById(R.id.info_lab_p_trombai);
		bTromText = (TextView) rootView.findViewById(R.id.info_lab_b_trom);
		bGlucText = (TextView) rootView.findViewById(R.id.info_lab_b_gluc);
		bGlucAfterText = (TextView) rootView
				.findViewById(R.id.info_lab_b_gluc_after);
		sedanText = (TextView) rootView
				.findViewById(R.id.info_summary_sedan_score);
		dragonText = (TextView) rootView
				.findViewById(R.id.info_summary_dragon_score);

	}

	public void setUserSelection() {
		Time time = new Time();
		time.setToNow();
		long timeDiff = time.toMillis(false)
				- AppViewModel.timeStampsDAO.getSympOnsetTime();
		double onsetTimeH = AppViewModel.milliToHour(timeDiff);
		String testTime = AppViewModel.nihssDAO.getNihssBaseline()
				.getTestTime();
		int tempInt = AppViewModel.nihssDAO.getNihssBaseline().getNihssTotal();
		if (testTime != null && testTime.length() > 0) {
			nihssTotalText.setVisibility(TextView.VISIBLE);
			nihssTotalText.setText(String.valueOf(tempInt));
			if (onsetTimeH < 3
					&& tempInt >= 3
					|| (onsetTimeH >= 3 && onsetTimeH <= 4.5 && tempInt >= 3 && tempInt <= 24)) {
				nihssTotalText.setBackgroundColor(FragmentSummary.green);
			} else {
				nihssTotalText.setBackgroundColor(FragmentSummary.yellow);
			}
			nihssTotalView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			nihssTotalText.setVisibility(TextView.GONE);
			nihssTotalView.setBackgroundColor(FragmentSummary.grey);
		}

		int rrSys = AppViewModel.labDAO.getRRSystolic();
		int rrDia = AppViewModel.labDAO.getRRDiastolic();
		if (rrSys > 0 && rrDia > 0) {
			rrText.setVisibility(TextView.VISIBLE);

			rrText.setText(rrSys + " / " + rrDia, BufferType.SPANNABLE);
			Spannable span = (Spannable) rrText.getText();
			int startIn = 0;
			int endIn = 0;
			endIn = String.valueOf(rrSys).length();
			if (rrSys > 0 && rrSys <= 185) {
				span.setSpan(new BackgroundColorSpan(FragmentSummary.green),
						startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else if (rrSys > 185) {
				span.setSpan(new BackgroundColorSpan(FragmentSummary.red),
						startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}

			startIn = rrText.getText().toString()
					.indexOf(String.valueOf(rrDia));
			endIn = startIn + String.valueOf(rrDia).length();
			if (rrDia > 0 && rrDia <= 110) {
				span.setSpan(new BackgroundColorSpan(FragmentSummary.green),
						startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else if (rrDia > 110) {
				span.setSpan(new BackgroundColorSpan(FragmentSummary.red),
						startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			rrView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			rrText.setVisibility(TextView.GONE);
			rrView.setBackgroundColor(FragmentSummary.grey);
			rrAfterView.setVisibility(TableRow.GONE);
		}

		if (rrSys > 185 || rrDia > 110) {
			rrSys = AppViewModel.labDAO.getRRAftSystolic();
			rrDia = AppViewModel.labDAO.getRRAftDiastolic();
			if (rrSys > 0 && rrDia > 0) {
				rrAfterView.setVisibility(TableRow.VISIBLE);

				rrAfterText
						.setText(rrSys + " / " + rrDia, BufferType.SPANNABLE);
				Spannable span = (Spannable) rrAfterText.getText();
				int startIn = 0;
				int endIn = 0;
				endIn = String.valueOf(rrSys).length();
				if (rrSys > 0 && rrSys <= 185) {
					span.setSpan(
							new BackgroundColorSpan(FragmentSummary.green),
							startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (rrSys > 185) {
					span.setSpan(new BackgroundColorSpan(FragmentSummary.red),
							startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}

				startIn = rrAfterText.getText().toString()
						.indexOf(String.valueOf(rrDia));
				endIn = startIn + String.valueOf(rrDia).length();
				if (rrDia > 0 && rrDia <= 110) {
					span.setSpan(
							new BackgroundColorSpan(FragmentSummary.green),
							startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (rrDia > 110) {
					span.setSpan(new BackgroundColorSpan(FragmentSummary.red),
							startIn, endIn, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
				rrAfterView.setBackgroundColor(FragmentSummary.transparent);
			} else {
				rrAfterText.setVisibility(TextView.GONE);
				rrAfterView.setBackgroundColor(FragmentSummary.grey);
			}
		} else {
			rrAfterView.setVisibility(TableRow.GONE);
		}

		ArrayList<Integer> admissionCtResults = AppViewModel.labDAO
				.getAdmissionCtResults();
		if (admissionCtResults.size() > 0) {
			admissionCtList.removeAllViews();
			admissionCtList.setVisibility(TextView.VISIBLE);
			for (int resouceId : admissionCtResults) {
				admissionCtList.addView(geneTextForList(resouceId,
						admissionCtList));
			}

			admissionCtView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			admissionCtList.setVisibility(TextView.GONE);
			admissionCtView.setBackgroundColor(FragmentSummary.grey);
		}

		String tempStr = AppViewModel.labDAO.getPerfusionCtResultForSummary();
		if (tempStr != null && tempStr.length() > 0) {
			perfusionCtText.setVisibility(TextView.VISIBLE);
			perfusionCtText.setText(tempStr);

			if (tempStr.contains(appContext
					.getString(R.string.radio_done_perfusion_ct_1))
					|| tempStr.contains(appContext
							.getString(R.string.radio_done_perfusion_ct_2))) {
				perfusionCtText.setBackgroundColor(FragmentSummary.green);
			} else if (tempStr.contains(appContext
					.getString(R.string.radio_done_perfusion_ct_3))) {
				perfusionCtText.setBackgroundColor(FragmentSummary.yellow);
			}
			perfusionCtView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			perfusionCtText.setVisibility(TextView.GONE);
			perfusionCtView.setBackgroundColor(FragmentSummary.grey);
		}

		tempStr = AppViewModel.labDAO.getCtAngiographyResultForSummary();
		if (tempStr != null && tempStr.length() > 0) {
			ctAngiographyText.setVisibility(TextView.VISIBLE);
			ctAngiographyText.setText(tempStr);

			if (tempStr.contains(appContext
					.getString(R.string.radio_done_ct_angiography_1))
					|| tempStr.contains(appContext
							.getString(R.string.radio_done_ct_angiography_2))
					|| tempStr.contains(appContext
							.getString(R.string.radio_done_ct_angiography_3))) {
				ctAngiographyText.setBackgroundColor(FragmentSummary.green);
			} else if (tempStr.contains(appContext
					.getString(R.string.radio_done_ct_angiography_4))) {
				ctAngiographyText.setBackgroundColor(FragmentSummary.yellow);
			}
			ctAngiographyView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			ctAngiographyText.setVisibility(TextView.GONE);
			ctAngiographyView.setBackgroundColor(FragmentSummary.grey);
		}

		float tempF = AppViewModel.labDAO.getQuickInr();
		if (tempF > 0) {
			quickInrText.setVisibility(TextView.VISIBLE);
			quickInrText.setText(String.valueOf(tempF));
			if (tempF < 1.5) {
				quickInrText.setBackgroundColor(FragmentSummary.green);
			} else if (tempF > 1.7) {
				quickInrText.setBackgroundColor(FragmentSummary.red);
			} else if (tempF >= 1.5 && tempF <= 1.7) {
				quickInrText.setBackgroundColor(FragmentSummary.yellow);
			}
			quickInrView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			quickInrText.setVisibility(TextView.GONE);
			quickInrView.setBackgroundColor(FragmentSummary.grey);
		}

		if (tempF >= 1.5 && tempF <= 1.7) {
			inrView.setVisibility(TextView.VISIBLE);
			tempF = AppViewModel.labDAO.getInr();
			if (tempF > 0) {
				inrText.setVisibility(TextView.VISIBLE);
				inrText.setText(String.valueOf(tempF));
				if (tempF <= 1.7) {
					inrText.setBackgroundColor(FragmentSummary.green);
				} else if (tempF > 1.7) {
					inrText.setBackgroundColor(FragmentSummary.red);
				}
				inrView.setBackgroundColor(FragmentSummary.transparent);
			} else {
				inrText.setVisibility(TextView.GONE);
				inrView.setBackgroundColor(FragmentSummary.grey);
			}

		} else {
			inrView.setVisibility(TextView.GONE);
		}

		tempInt = AppViewModel.labDAO.getAPTT();
		if (tempInt > 0) {
			apttText.setVisibility(TextView.VISIBLE);
			apttText.setText(String.valueOf(tempInt));
			if (tempInt < 34) {
				apttText.setBackgroundColor(FragmentSummary.green);
			} else if (tempInt >= 34 && tempInt <= 60) {
				apttText.setBackgroundColor(FragmentSummary.yellow);
			} else if (tempInt > 60) {
				apttText.setBackgroundColor(FragmentSummary.red);
			}
			apttView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			apttText.setVisibility(TextView.GONE);
			apttView.setBackgroundColor(FragmentSummary.grey);
		}

		tempInt = AppViewModel.labDAO.getPTrombai();
		if (tempInt > 0) {
			pTrombaiText.setVisibility(TextView.VISIBLE);
			pTrombaiText.setText(String.valueOf(tempInt));
			if (tempInt < 17 || tempInt > 25) {
				pTrombaiText.setBackgroundColor(FragmentSummary.yellow);
			} else if (tempInt >= 17 && tempInt <= 25) {
				pTrombaiText.setBackgroundColor(FragmentSummary.green);
			}
			pTrombaiView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			pTrombaiText.setVisibility(TextView.GONE);
			pTrombaiView.setBackgroundColor(FragmentSummary.grey);
		}

		tempInt = AppViewModel.labDAO.getBTrom();
		if (tempInt > 0) {
			bTromText.setVisibility(TextView.VISIBLE);
			bTromText.setText(String.valueOf(tempInt));
			if (tempInt < 100) {
				bTromText.setBackgroundColor(FragmentSummary.red);
			} else if (tempInt >= 100) {
				bTromText.setBackgroundColor(FragmentSummary.green);
			}
			bTromView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			bTromText.setVisibility(TextView.GONE);
			bTromView.setBackgroundColor(FragmentSummary.grey);
		}

		tempF = AppViewModel.labDAO.getBGluc();
		if (tempF > 0) {
			bGlucText.setVisibility(TextView.VISIBLE);
			bGlucText.setText(String.valueOf(tempF));
			if (tempF < 2.8 || tempF > 10) {
				bGlucText.setBackgroundColor(FragmentSummary.yellow);
			} else if (tempF >= 2.8 && tempF <= 10) {
				bGlucText.setBackgroundColor(FragmentSummary.green);
			}
			bGlucView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			bGlucText.setVisibility(TextView.GONE);
			bGlucView.setBackgroundColor(FragmentSummary.grey);
		}

		if (tempF > 0 && tempF < 2.8) {
			bGlucAfterView.setVisibility(TextView.VISIBLE);
			tempF = AppViewModel.labDAO.getBGlucAfter();
			if (tempF > 0) {
				bGlucAfterText.setVisibility(TextView.VISIBLE);
				bGlucAfterText.setText(String.valueOf(tempF));
				if (tempF < 2.8 || tempF > 10) {
					bGlucAfterText.setBackgroundColor(FragmentSummary.yellow);
				} else if (tempF >= 2.8 && tempF <= 10) {
					bGlucAfterText.setBackgroundColor(FragmentSummary.green);
				}
				bGlucAfterView.setBackgroundColor(FragmentSummary.transparent);
			} else {
				bGlucAfterText.setVisibility(TextView.GONE);
				bGlucAfterView.setBackgroundColor(FragmentSummary.grey);
			}

		} else {
			bGlucAfterView.setVisibility(TextView.GONE);
		}

		tempInt = AppViewModel.calSEDAN();
		if (tempInt >= 0) {
			sedanText.setVisibility(TextView.VISIBLE);
			if (tempInt >= 0 && tempInt <= 1) {
				sedanText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_sedan_1) + ")");
				sedanText.setBackgroundColor(FragmentSummary.green);
			} else if (tempInt >= 2 && tempInt <= 3) {
				sedanText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_sedan_2) + ")");
				sedanText.setBackgroundColor(FragmentSummary.yellow);
			} else if (tempInt >= 4) {
				sedanText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_sedan_3) + ")");
				sedanText.setBackgroundColor(FragmentSummary.red);
			}
			sedanView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			sedanText.setVisibility(TextView.GONE);
			sedanView.setBackgroundColor(FragmentSummary.grey);
		}
		
		tempInt = AppViewModel.calDRAGON();
		if (tempInt >= 0) {
			dragonText.setVisibility(TextView.VISIBLE);
			if (tempInt >= 0 && tempInt <= 3) {
				dragonText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_dragon_1) + ")");
				dragonText.setBackgroundColor(FragmentSummary.green);
			} else if (tempInt >= 4  &&  tempInt <= 7) {
				dragonText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_dragon_2) + ")");
				dragonText.setBackgroundColor(FragmentSummary.yellow);
			} else if (tempInt >= 8  && tempInt <= 10) {
				dragonText.setText(tempInt + " (" + appContext.getString(R.string.info_summary_dragon_3) + ")");
				dragonText.setBackgroundColor(FragmentSummary.red);
			}
			dragonView.setBackgroundColor(FragmentSummary.transparent);
		} else {
			dragonText.setVisibility(TextView.GONE);
			dragonView.setBackgroundColor(FragmentSummary.grey);
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
