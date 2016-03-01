package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.patient.FragmentPatient;
import fi.etla.strokehelper.process.FragmentSymptoms;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CommonSymptoms implements OnCheckedChangeListener, OnClickListener {
	private View symptomSideView;
	private View facialPalsyView;
	private View selfCareView;
	private View quickRecoverSympView;
	private View convulsionView;

	private View symptomLimbView;
	private View speechDisorderView;
	private View termiDiseaseView;
	private View mildSympView;
	private View suggestSAVView;
	private View anticoagulantView;
	private View basilarisView;

	private RadioGroup sympSideRadioGroup;
	private RadioGroup limbSympRadioGroup;
	private RadioGroup facialPalsyRadioGroup;
	private RadioGroup speechDisorderRadioGroup;
	private RadioGroup selfCaresRadioGroup;
	private RadioGroup termiDiseaseRadioGroup;
	private RadioGroup convulsionRadioGroup;
	private RadioGroup anticoagulantRadioGroup;
	private RadioGroup basilarisSympRadioGroup;
	private RadioGroup quickRecoverSympRadioGroup;
	private RadioGroup mildSympRadioGroup;
	private RadioGroup suggestSAVRadioGroup;

	private RadioButton sympSideRight;
	private RadioButton sympSideLeft;
	private RadioButton sympSideBoth;

	private RadioButton limbSympYes;
	private RadioButton limbSympNo;

	private RadioButton facialPalsyYes;
	private RadioButton facialPalsyNo;

	private RadioButton speechDisYes;
	private RadioButton speechDisNo;

	private RadioButton selfCaresYes;
	private RadioButton selfCaresNo;
	private RadioButton selfCaresUnknown;

	private RadioButton termiDiseaseYes;
	private RadioButton termiDiseaseNo;
	private RadioButton termiDiseaseUnknown;

	private RadioButton convulsionNo;
	private RadioButton convulsionYes;
	private RadioButton convulsionUnknown;

	private RadioButton anticoagulantYes;
	private RadioButton anticoagulantNo;
	private RadioButton anticoagulantUnknown;

	private RadioButton basilarisMainSusp;
	private RadioButton basilarisLessPossi;
	private RadioButton basilarisUnknown;

	private RadioButton quickRecoverSympYes;
	private RadioButton quickRecoverSympNo;
	private RadioButton mildSympYes;
	private RadioButton mildSympNo;
	private RadioButton suggestSAVYES;
	private RadioButton suggestSAVNo;

	private ImageButton convulsionInfoBtn;
	private ImageButton anticoaguInfoBtn;
	private ImageButton basilarisInfoBtn;
	private ImageButton mildSympInfoBtn;

	private View rootView;
	private Fragment appContext;

	private TextView sideComple;
	private TextView limbComple;
	private TextView facialPalsyComple;
	private TextView speechDisorderComple;
	private TextView selfCareComple;
	private TextView termiDiseaseComple;
	private TextView convulsionComple;
	private TextView anticoagulantComple;
	private TextView basilarisComple;
	private TextView quickRecoverComple;
	private TextView mildSympComple;
	private TextView suggestSAVComple;

	public CommonSymptoms(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
		// initUIStatus();
	}

	public void initUIElements() {
		/** Views */
		symptomSideView = (View) rootView.findViewById(R.id.view_symptom_side);
		facialPalsyView = (View) rootView.findViewById(R.id.view_facial_palsy);
		selfCareView = (View) rootView.findViewById(R.id.view_care_for_self);
		quickRecoverSympView = (View) rootView
				.findViewById(R.id.view_quick_recover_symptoms);
		convulsionView = (View) rootView.findViewById(R.id.view_convulsion);

		symptomLimbView = (View) rootView.findViewById(R.id.view_symptom_limb);
		speechDisorderView = (View) rootView
				.findViewById(R.id.view_speech_disorder);
		termiDiseaseView = (View) rootView
				.findViewById(R.id.view_terminal_disease);
		mildSympView = (View) rootView.findViewById(R.id.view_mild_symptoms);
		suggestSAVView = (View) rootView.findViewById(R.id.view_suggest_sav);
		anticoagulantView = (View) rootView
				.findViewById(R.id.view_anticoagulant);
		basilarisView = (View) rootView.findViewById(R.id.view_basilaris);

		facialPalsyComple = (TextView) rootView
				.findViewById(R.id.comple_facial_palsy);
		speechDisorderComple = (TextView) rootView
				.findViewById(R.id.comple_speech_disorder);
		selfCareComple = (TextView) rootView
				.findViewById(R.id.comple_care_for_self);
		termiDiseaseComple = (TextView) rootView
				.findViewById(R.id.comple_terminal_disease);
		convulsionComple = (TextView) rootView
				.findViewById(R.id.comple_convulsion);
		anticoagulantComple = (TextView) rootView
				.findViewById(R.id.comple_anticoagulant);
		basilarisComple = (TextView) rootView
				.findViewById(R.id.comple_basilaris);
		quickRecoverComple = (TextView) rootView
				.findViewById(R.id.comple_quick_recover_symptoms);
		mildSympComple = (TextView) rootView
				.findViewById(R.id.comple_mild_symptoms);
		suggestSAVComple = (TextView) rootView
				.findViewById(R.id.comple_suggest_sav);
		sideComple = (TextView) rootView.findViewById(R.id.comple_symptom_side);
		limbComple = (TextView) rootView.findViewById(R.id.comple_limb);

		/** Buttons */
		convulsionInfoBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_convulsion);
		anticoaguInfoBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_anticoagulant);
		basilarisInfoBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_basilaris);
		mildSympInfoBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_mild_symptoms);

		/** Radio groups */
		sympSideRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_symptom_side);
		limbSympRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_limb_symptoms);
		facialPalsyRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_facial_palsy);
		speechDisorderRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_speech_disorder);
		selfCaresRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_care_for_self);
		termiDiseaseRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_terminal_disease);
		convulsionRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_convulsion);
		anticoagulantRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_anticoagulant);
		basilarisSympRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_basilaris);
		quickRecoverSympRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_quick_recover_symptoms);
		mildSympRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_mild_symptoms);
		suggestSAVRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_suggest_sav);

		/** Radio Buttons */
		sympSideRight = (RadioButton) rootView
				.findViewById(R.id.radio_symptom_side_right);
		sympSideLeft = (RadioButton) rootView
				.findViewById(R.id.radio_symptom_side_left);
		sympSideBoth = (RadioButton) rootView
				.findViewById(R.id.radio_symptom_side_both);

		limbSympYes = (RadioButton) rootView
				.findViewById(R.id.radio_limb_symptoms_yes);
		limbSympNo = (RadioButton) rootView
				.findViewById(R.id.radio_limb_symptoms_no);

		facialPalsyYes = (RadioButton) rootView
				.findViewById(R.id.radio_facial_palsy_yes);
		facialPalsyNo = (RadioButton) rootView
				.findViewById(R.id.radio_facial_palsy_no);

		speechDisYes = (RadioButton) rootView
				.findViewById(R.id.radio_speech_disorder_yes);
		speechDisNo = (RadioButton) rootView
				.findViewById(R.id.radio_speech_disorder_no);

		selfCaresYes = (RadioButton) rootView
				.findViewById(R.id.radio_care_for_self_yes);
		selfCaresNo = (RadioButton) rootView
				.findViewById(R.id.radio_care_for_self_no);
		selfCaresUnknown = (RadioButton) rootView
				.findViewById(R.id.radio_care_for_self_unknown);

		termiDiseaseYes = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_yes);
		termiDiseaseNo = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_no);
		termiDiseaseUnknown = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_unknown);

		convulsionNo = (RadioButton) rootView
				.findViewById(R.id.radio_convulsion_no);
		convulsionYes = (RadioButton) rootView
				.findViewById(R.id.radio_convulsion_yes);
		convulsionUnknown = (RadioButton) rootView
				.findViewById(R.id.radio_convulsion_unknown);

		anticoagulantNo = (RadioButton) rootView
				.findViewById(R.id.radio_anticoagulant_no);
		anticoagulantYes = (RadioButton) rootView
				.findViewById(R.id.radio_anticoagulant_yes);
		anticoagulantUnknown = (RadioButton) rootView
				.findViewById(R.id.radio_anticoagulant_unknown);

		basilarisMainSusp = (RadioButton) rootView
				.findViewById(R.id.radio_basilaris_main_suspicion);
		basilarisLessPossi = (RadioButton) rootView
				.findViewById(R.id.radio_basilaris_less_probable);
		basilarisUnknown = (RadioButton) rootView
				.findViewById(R.id.radio_basilaris_unknown);

		quickRecoverSympYes = (RadioButton) rootView
				.findViewById(R.id.radio_quick_recover_symptoms_yes);
		quickRecoverSympNo = (RadioButton) rootView
				.findViewById(R.id.radio_quick_recover_symptoms_no);
		mildSympYes = (RadioButton) rootView
				.findViewById(R.id.radio_mild_symptoms_yes);
		mildSympNo = (RadioButton) rootView
				.findViewById(R.id.radio_mild_symptoms_no);
		suggestSAVYES = (RadioButton) rootView
				.findViewById(R.id.radio_suggest_sav_yes);
		suggestSAVNo = (RadioButton) rootView
				.findViewById(R.id.radio_suggest_sav_no);

		sympSideRight.setOnCheckedChangeListener(this);
		sympSideLeft.setOnCheckedChangeListener(this);
		sympSideBoth.setOnCheckedChangeListener(this);
		limbSympYes.setOnCheckedChangeListener(this);
		limbSympNo.setOnCheckedChangeListener(this);
		facialPalsyNo.setOnCheckedChangeListener(this);
		facialPalsyYes.setOnCheckedChangeListener(this);
		speechDisNo.setOnCheckedChangeListener(this);
		speechDisYes.setOnCheckedChangeListener(this);
		selfCaresNo.setOnCheckedChangeListener(this);
		selfCaresUnknown.setOnCheckedChangeListener(this);
		selfCaresYes.setOnCheckedChangeListener(this);
		termiDiseaseNo.setOnCheckedChangeListener(this);
		termiDiseaseUnknown.setOnCheckedChangeListener(this);
		termiDiseaseYes.setOnCheckedChangeListener(this);
		convulsionNo.setOnCheckedChangeListener(this);
		convulsionUnknown.setOnCheckedChangeListener(this);
		convulsionYes.setOnCheckedChangeListener(this);
		anticoagulantNo.setOnCheckedChangeListener(this);
		anticoagulantUnknown.setOnCheckedChangeListener(this);
		anticoagulantYes.setOnCheckedChangeListener(this);
		basilarisMainSusp.setOnCheckedChangeListener(this);
		basilarisLessPossi.setOnCheckedChangeListener(this);
		basilarisUnknown.setOnCheckedChangeListener(this);
		quickRecoverSympYes.setOnCheckedChangeListener(this);
		quickRecoverSympNo.setOnCheckedChangeListener(this);
		mildSympYes.setOnCheckedChangeListener(this);
		mildSympNo.setOnCheckedChangeListener(this);
		suggestSAVYES.setOnCheckedChangeListener(this);
		suggestSAVNo.setOnCheckedChangeListener(this);

		convulsionInfoBtn.setOnClickListener(this);
		basilarisInfoBtn.setOnClickListener(this);
		anticoaguInfoBtn.setOnClickListener(this);
		mildSympInfoBtn.setOnClickListener(this);

		termiDiseaseYes.setOnClickListener(this);
	}

	public void initUIStatus() {
		completeSymptoms();
		sympSideRadioGroup.clearCheck();
		limbSympRadioGroup.clearCheck();
		facialPalsyRadioGroup.clearCheck();
		speechDisorderRadioGroup.clearCheck();
		selfCaresRadioGroup.clearCheck();
		termiDiseaseRadioGroup.clearCheck();
		convulsionRadioGroup.clearCheck();
		anticoagulantRadioGroup.clearCheck();
		basilarisSympRadioGroup.clearCheck();
		quickRecoverSympRadioGroup.clearCheck();
		mildSympRadioGroup.clearCheck();
		suggestSAVRadioGroup.clearCheck();
	}

	public void showSymptoms() {
		rootView.setVisibility(View.VISIBLE);
	}

	public void hideSymptoms() {
		rootView.setVisibility(View.GONE);
	}

	public void hideEleForPatientPage() {
		quickRecoverSympView.setVisibility(RadioGroup.GONE);
		mildSympView.setVisibility(RadioGroup.GONE);
		suggestSAVView.setVisibility(RadioGroup.GONE);
	}

	public void hideEleForSymptomPage() {
		selfCareView.setVisibility(RadioGroup.GONE);
		termiDiseaseView.setVisibility(RadioGroup.GONE);
		anticoagulantView.setVisibility(RadioGroup.GONE);
	}

	public void setUserSelection() {
		completeSymptoms();
		if (appContext instanceof FragmentPatient) {
			sympSideRadioGroup.check(AppViewModel.symptomDAO.getSympSideId());
			limbSympRadioGroup.check(AppViewModel.symptomDAO.getLimbSympId());
			facialPalsyRadioGroup.check(AppViewModel.symptomDAO
					.getFacialPalsyId());
			speechDisorderRadioGroup.check(AppViewModel.symptomDAO
					.getSpeechDisorderId());
			selfCaresRadioGroup.check(AppViewModel.symptomDAO.getSelfCareId());
			termiDiseaseRadioGroup.check(AppViewModel.symptomDAO
					.getTermiDiseaseId());
			convulsionRadioGroup.check(AppViewModel.symptomDAO
					.getConvulsionId());
			anticoagulantRadioGroup.check(AppViewModel.symptomDAO
					.getAnticoagularId());
			basilarisSympRadioGroup.check(AppViewModel.symptomDAO
					.getBasilarisId());
		} else if (appContext instanceof FragmentSymptoms) {
			sympSideRadioGroup.check(AppViewModel.symptomDAO.getSympSideId());
			limbSympRadioGroup.check(AppViewModel.symptomDAO.getLimbSympId());
			facialPalsyRadioGroup.check(AppViewModel.symptomDAO
					.getFacialPalsyId());
			speechDisorderRadioGroup.check(AppViewModel.symptomDAO
					.getSpeechDisorderId());
			quickRecoverSympRadioGroup.check(AppViewModel.symptomDAO
					.getQuickRecovSympId());
			mildSympRadioGroup.check(AppViewModel.symptomDAO.getMildSympId());
			convulsionRadioGroup.check(AppViewModel.symptomDAO
					.getConvulsionId());
			suggestSAVRadioGroup.check(AppViewModel.symptomDAO
					.getSuggestSavId());
			basilarisSympRadioGroup.check(AppViewModel.symptomDAO
					.getBasilarisId());
		}
	}

	public void setUserSelectionGeneralPart() {
		selfCareView.setVisibility(View.GONE);
		termiDiseaseView.setVisibility(View.GONE);
		anticoagulantView.setVisibility(View.GONE);
		quickRecoverSympView.setVisibility(View.GONE);
		mildSympView.setVisibility(View.GONE);
		suggestSAVView.setVisibility(View.GONE);

		sympSideRadioGroup.check(AppViewModel.symptomDAO.getSympSideId());
		limbSympRadioGroup.check(AppViewModel.symptomDAO.getLimbSympId());
		facialPalsyRadioGroup.check(AppViewModel.symptomDAO.getFacialPalsyId());
		speechDisorderRadioGroup.check(AppViewModel.symptomDAO
				.getSpeechDisorderId());
		convulsionRadioGroup.check(AppViewModel.symptomDAO.getConvulsionId());
		basilarisSympRadioGroup.check(AppViewModel.symptomDAO.getBasilarisId());
	}

	public void setUserSelectionPatientPart() {
		termiDiseaseView.setVisibility(View.GONE);
		convulsionView.setVisibility(View.GONE);
		quickRecoverSympView.setVisibility(View.GONE);
		mildSympView.setVisibility(View.GONE);
		suggestSAVView.setVisibility(View.GONE);

		symptomSideView.setVisibility(View.GONE);
		symptomLimbView.setVisibility(View.GONE);
		facialPalsyView.setVisibility(View.GONE);
		speechDisorderView.setVisibility(View.GONE);
		basilarisView.setVisibility(View.GONE);

		selfCaresRadioGroup.check(AppViewModel.symptomDAO.getSelfCareId());
		anticoagulantRadioGroup.check(AppViewModel.symptomDAO
				.getAnticoagularId());
	}

	public void completeSymptoms() {
		completeSympSide();
		completeLimbSymp();
		completeFacialPalsy();
		completeSpeechDisorder();
		completeConvulsion();
		completeBasilaris();
		if (appContext instanceof FragmentPatient) {
			completeSelfCare();
			completeTermiDisease();
			completeAnticoagulant();
		} else if (appContext instanceof FragmentSymptoms) {
			completeQuickRecov();
			completeMildSymp();
			completeSuggestSav();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton comBtn, boolean isChecked) {
		int id = comBtn.getId();
		String str = comBtn.getText().toString();
		if (isChecked) {
			switch (id) {
			case R.id.radio_symptom_side_right:
			case R.id.radio_symptom_side_left:
			case R.id.radio_symptom_side_both:
			case R.id.radio_limb_symptoms_yes:
			case R.id.radio_limb_symptoms_no:
			case R.id.radio_facial_palsy_yes:
			case R.id.radio_facial_palsy_no:
			case R.id.radio_speech_disorder_yes:
			case R.id.radio_speech_disorder_no:
			case R.id.radio_care_for_self_yes:
			case R.id.radio_care_for_self_unknown:
			case R.id.radio_terminal_disease_no:
			case R.id.radio_terminal_disease_unknown:
			case R.id.radio_convulsion_no:
			case R.id.radio_convulsion_unknown:
			case R.id.radio_anticoagulant_no:
			case R.id.radio_anticoagulant_unknown:
			case R.id.radio_basilaris_main_suspicion:
			case R.id.radio_basilaris_less_probable:
			case R.id.radio_basilaris_unknown:
			case R.id.radio_quick_recover_symptoms_no:
			case R.id.radio_mild_symptoms_no:
			case R.id.radio_suggest_sav_no:
				comBtn.setBackgroundColor(appContext.getResources().getColor(
						R.color.green));
				break;

			case R.id.radio_convulsion_yes:
			case R.id.radio_anticoagulant_yes:
			case R.id.radio_care_for_self_no:
			case R.id.radio_quick_recover_symptoms_yes:
			case R.id.radio_mild_symptoms_yes:
			case R.id.radio_suggest_sav_yes:
				comBtn.setBackgroundColor(appContext.getResources().getColor(
						R.color.yellow));
				break;
			case R.id.radio_terminal_disease_yes:
				comBtn.setBackgroundColor(appContext.getResources().getColor(
						R.color.red));
				break;
			}

			switch (id) {
			case R.id.radio_symptom_side_right:
			case R.id.radio_symptom_side_left:
			case R.id.radio_symptom_side_both:
				AppViewModel.symptomDAO.setSympSide(str);
				AppViewModel.symptomDAO.setSympSideId(id);
				completeSympSide();
				break;
			case R.id.radio_limb_symptoms_yes:
			case R.id.radio_limb_symptoms_no:
				AppViewModel.symptomDAO.setLimbSymp(str);
				AppViewModel.symptomDAO.setLimbSympId(id);
				completeLimbSymp();
				break;
			case R.id.radio_facial_palsy_yes:
			case R.id.radio_facial_palsy_no:
				AppViewModel.symptomDAO.setFacialPalsy(str);
				AppViewModel.symptomDAO.setFacialPalsyId(id);
				completeFacialPalsy();
				break;
			case R.id.radio_speech_disorder_yes:
			case R.id.radio_speech_disorder_no:
				AppViewModel.symptomDAO.setSpeechDisorder(str);
				AppViewModel.symptomDAO.setSpeechDisorderId(id);
				completeSpeechDisorder();
				break;
			case R.id.radio_care_for_self_yes:
			case R.id.radio_care_for_self_no:
			case R.id.radio_care_for_self_unknown:
				AppViewModel.symptomDAO.setSelfCare(str);
				AppViewModel.symptomDAO.setSelfCareId(id);
				completeSelfCare();
				break;
			case R.id.radio_terminal_disease_yes:
			case R.id.radio_terminal_disease_no:
			case R.id.radio_terminal_disease_unknown:
				AppViewModel.symptomDAO.setTermiDisease(str);
				AppViewModel.symptomDAO.setTermiDiseaseId(id);
				completeTermiDisease();
				break;
			case R.id.radio_convulsion_yes:
			case R.id.radio_convulsion_no:
			case R.id.radio_convulsion_unknown:
				AppViewModel.symptomDAO.setConvulsion(str);
				AppViewModel.symptomDAO.setConvulsionId(id);
				completeConvulsion();
				break;
			case R.id.radio_anticoagulant_yes:
			case R.id.radio_anticoagulant_no:
			case R.id.radio_anticoagulant_unknown:
				AppViewModel.symptomDAO.setAnticoagular(str);
				AppViewModel.symptomDAO.setAnticoagularId(id);
				completeAnticoagulant();
				break;
			case R.id.radio_basilaris_main_suspicion:
			case R.id.radio_basilaris_less_probable:
			case R.id.radio_basilaris_unknown:
				AppViewModel.symptomDAO.setBasilaris(str);
				AppViewModel.symptomDAO.setBasilarisId(id);
				completeBasilaris();
				break;
			case R.id.radio_quick_recover_symptoms_no:
			case R.id.radio_quick_recover_symptoms_yes:
				AppViewModel.symptomDAO.setQuickRecovSymp(str);
				AppViewModel.symptomDAO.setQuickRecovSympId(id);
				completeQuickRecov();
				break;
			case R.id.radio_mild_symptoms_no:
			case R.id.radio_mild_symptoms_yes:
				AppViewModel.symptomDAO.setMildSymp(str);
				AppViewModel.symptomDAO.setMildSympId(id);
				completeMildSymp();
				break;
			case R.id.radio_suggest_sav_no:
			case R.id.radio_suggest_sav_yes:
				AppViewModel.symptomDAO.setSuggestSav(str);
				AppViewModel.symptomDAO.setSuggestSavId(id);
				completeSuggestSav();
				break;
			}
		} else if (!isChecked) {
			comBtn.setBackgroundColor(Color.TRANSPARENT);
		}
	}

	@Override
	public void onClick(View v) {

		if (v.equals(termiDiseaseYes)) {
			Intent interrIntent = new Intent(appContext.getActivity(),
					DialogCommonInterrupt.class);
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					appContext.getString(R.string.interruption_warning_title));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					appContext.getString(R.string.info_interr_ter_illness));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_ter_illness);
			// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else {
			Intent intent = new Intent(appContext.getActivity(),
					DialogHelpInfo.class);

			switch (v.getId()) {
			case R.id.imgbtn_help_convulsion:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.help_title_convulsion));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_convulsion));
				break;
			case R.id.imgbtn_help_basilaris:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.help_title_basilaris));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_basilaris));
				break;
			case R.id.imgbtn_help_anticoagulant:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.help_title_anticoagulant));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_anticoagulant));
				break;
			case R.id.imgbtn_help_mild_symptoms:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.help_title_mild_symptoms));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_mild_symptoms));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(intent);
		}
	}

	public void completeSympSide() {
		int sympSide = AppViewModel.symptomDAO.getSympSideId();
		if (sympSide > 0) {
			sideComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			sideComple.setBackgroundColor(appContext.getResources().getColor(
					R.color.grey));
		}
	}

	public void completeLimbSymp() {
		int limb = AppViewModel.symptomDAO.getLimbSympId();
		if (limb > 0) {
			limbComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			limbComple.setBackgroundColor(appContext.getResources().getColor(
					R.color.grey));
		}
	}

	public void completeFacialPalsy() {
		int facialPalsy = AppViewModel.symptomDAO.getFacialPalsyId();
		if (facialPalsy > 0) {
			facialPalsyComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			facialPalsyComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeSpeechDisorder() {
		int speechDis = AppViewModel.symptomDAO.getSpeechDisorderId();
		if (speechDis > 0) {
			speechDisorderComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			speechDisorderComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeSelfCare() {
		int selfcare = AppViewModel.symptomDAO.getSelfCareId();
		if (selfcare > 0) {
			selfCareComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			selfCareComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeTermiDisease() {
		int terDisease = AppViewModel.symptomDAO.getTermiDiseaseId();
		if (terDisease > 0) {
			termiDiseaseComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			termiDiseaseComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeConvulsion() {
		int convulsion = AppViewModel.symptomDAO.getConvulsionId();
		if (convulsion > 0) {
			convulsionComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			convulsionComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeAnticoagulant() {
		int anticoagulant = AppViewModel.symptomDAO.getAnticoagularId();
		if (anticoagulant <= 0) {
			anticoagulantComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		} else {
			anticoagulantComple.setBackgroundColor(Color.TRANSPARENT);
		}
	}

	public void completeBasilaris() {
		int basilaris = AppViewModel.symptomDAO.getBasilarisId();
		if (basilaris > 0) {
			basilarisComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			basilarisComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeQuickRecov() {
		int quickRecov = AppViewModel.symptomDAO.getQuickRecovSympId();
		if (quickRecov > 0) {
			quickRecoverComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			quickRecoverComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeMildSymp() {
		int mildSymp = AppViewModel.symptomDAO.getMildSympId();
		if (mildSymp > 0) {
			mildSympComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			mildSympComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}

	public void completeSuggestSav() {
		int suggestSav = AppViewModel.symptomDAO.getSuggestSavId();
		if (suggestSav > 0) {
			suggestSAVComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			suggestSAVComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
}
