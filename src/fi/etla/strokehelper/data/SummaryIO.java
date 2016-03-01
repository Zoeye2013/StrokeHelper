package fi.etla.strokehelper.data;

import fi.etla.strokehelper.R;
import android.app.Activity;
import android.app.Notification.Style;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;

public class SummaryIO {
	private String summary;
	private Activity appContext;
	private Spanned spanned;

	public SummaryIO(Activity activity) {
		appContext = activity;
		summary = "";
	}

	public String getSummary() {
		return summary;
	}
	
	public Spanned getSummarySpanned(){
		return spanned;
	}

	public void generateSummary() {
		/** Colors */
		int grey = appContext.getResources().getColor(R.color.grey);
		int green = appContext.getResources().getColor(R.color.green);
		int red = appContext.getResources().getColor(R.color.red);
		int yellow = appContext.getResources().getColor(R.color.yellow);

		/** General Description */
		Spannable generalTitle1 = setTitle1Style(appContext
				.getString(R.string.info_summary_title_general) + "\n");
		Spannable ageTitle2 = setTitle2Style(AppViewModel.patientDAO
				.getPatientAge()
				+ appContext.getString(R.string.info_thrombolysis_patient_age)
				+ "\n");

		String temp = AppViewModel.symptomDAO.getSympSide();
		Spannable symSideTitle3 = new SpannableStringBuilder(
				appContext.getString(R.string.info_symptom_side) + " ");
		Spannable symSideContent = new SpannableStringBuilder(temp + "\n");

		if (temp != null && temp.length() > 0) {
			setContentStyle(symSideContent, green);
		} else {
			setTitle3Style(symSideTitle3, grey);
		}
		
		spanned = (Spanned) TextUtils.concat(generalTitle1,ageTitle2,symSideTitle3,symSideContent);

//		summary +=
//		// appContext.getString(R.string.info_summary_title_general)
//		// +AppViewModel.patientDAO.getPatientAge()
//		// + appContext.getString(R.string.info_thrombolysis_patient_age)
//		// + "\n"
////		+appContext.getString(R.string.info_symptom_side) + " "
////				+ 
//				AppViewModel.symptomDAO.getSympSide() + "\n"
//				+ appContext.getString(R.string.info_symptom_limb) + " "
//				+ AppViewModel.symptomDAO.getLimbSymp() + "\n"
//				+ appContext.getString(R.string.info_symptom_facial_palsy)
//				+ " " + AppViewModel.symptomDAO.getFacialPalsy() + "\n"
//				+ appContext.getString(R.string.info_symptom_speech_disorder)
//				+ " " + AppViewModel.symptomDAO.getSpeechDisorder() + "\n"
//				+ appContext.getString(R.string.info_symptom_basilaris) + " "
//				+ AppViewModel.symptomDAO.getBasilaris() + "\n"
//				+ appContext.getString(R.string.info_symptom_convulsion) + " "
//				+ AppViewModel.symptomDAO.getConvulsion() + "\n"
//				+ appContext.getString(R.string.info_summary_symptom_start)
//				+ " ";
//		String onsetTime = "";
//		switch (AppViewModel.timeStampsDAO.getSympOnsetQuaId()) {
//		case R.id.radio_known_onset:
//			onsetTime = AppViewModel.timeToString(AppViewModel.timeStampsDAO
//					.getSympOnsetTime());
//			break;
//		case R.id.radio_woke_up_symptom:
//			onsetTime = AppViewModel.timeToString(AppViewModel.timeStampsDAO
//					.getLastSympFreeTime());
//			break;
//		case R.id.radio_last_symptom_free:
//			onsetTime = AppViewModel.timeToString(AppViewModel.timeStampsDAO
//					.getLastSympFreeTime());
//			break;
//		}
//		summary += onsetTime + " ("
//				+ AppViewModel.timeStampsDAO.getSympOnsetQua() + ")\n";
	}

	public Spannable setTitle1Style(String text) {
		Spannable span = new SpannableStringBuilder(text);
		span.setSpan(new AbsoluteSizeSpan(27), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return span;
	}

	public Spannable setTitle2Style(String text) {
		Spannable span = new SpannableStringBuilder(text);
		span.setSpan(new AbsoluteSizeSpan(24), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return span;
	}

	public Spannable setTitle3Style(String text) {
		Spannable span = new SpannableStringBuilder(text);
		span.setSpan(new AbsoluteSizeSpan(21), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return span;
	}

	public void setTitle3Style(Spannable span, int color) {
		span.setSpan(new AbsoluteSizeSpan(14), 0, span.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new StyleSpan(Typeface.BOLD), 0, span.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new BackgroundColorSpan(color), 0, span.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	public void setContentStyle(Spannable span, int color) {
		span.setSpan(new AbsoluteSizeSpan(14), 0, span.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new BackgroundColorSpan(color), 0, span.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}
}
