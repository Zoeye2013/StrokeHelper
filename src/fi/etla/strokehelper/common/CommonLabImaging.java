package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;

import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentLab;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CommonLabImaging implements OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener,
		OnFocusChangeListener, OnClickListener {
	private View imageView;
	private Fragment appContext;

	private TextView imagingInfoText;
	private TextView admiCtInfoText;
	private TextView admiCtRecommendText;
	private CheckBox noVisiSignRadio;
	private CheckBox hyperSignRadio;
	private CheckBox earlyInfarctRadio;
	private CheckBox bleedingRadio;
	private CheckBox ischeLessRadio;
	private CheckBox ischeMoreRadio;
	private CheckBox tumorRadio;
	private CheckBox abscessRadio;
	private CheckBox otherRadio;

	private TextView perfuCtRecommendText;
	private TextView ctAngioRecommendText;
	private View perfuCtView;
	private View ctAngioView;
	private RadioGroup perfuCtGroup;
	private RadioGroup ctAngioGroup;
	private RadioGroup perfuCtDoneGroup;
	private RadioGroup ctAngioDoneGroup;

	private RadioButton perfuCtNotDoneRadio;
	private RadioButton ctAngioNotDoneRadio;

	private RadioButton perfuCtDone1Radio;
	private RadioButton perfuCtDone2Radio;
	private RadioButton perfuCtDone3Radio;

	private RadioButton ctAngioDone1Radio;
	private RadioButton ctAngioDone2Radio;
	private RadioButton ctAngioDone3Radio;
	private RadioButton ctAngioDone4Radio;

	private View perfuCtDoneView;
	private View perfuCtNotDoneView;
	private View ctAngioDoneView;
	private View ctAngioNotDoneView;

	private TextView perfuCtDoneInfoText;
	private TextView ctAngioDoneInfoText;

	private MyEditText perfuCtNotDoneEdit;
	private MyEditText ctAngioNotDoneEdit;

	private Button admiCtDoneBtn;
	
	private TextView adCtComple;
	private TextView perfuCtComple;
	private TextView ctAngioComple;

	public CommonLabImaging(View root, Fragment context) {
		imageView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		imagingInfoText = (TextView) imageView.findViewById(R.id.info_imaging);

		admiCtInfoText = (TextView) imageView
				.findViewById(R.id.info_lab_admission_ct);
		admiCtRecommendText = (TextView) imageView
				.findViewById(R.id.info_recommend_admission_ct);
		admiCtDoneBtn = (Button) imageView
				.findViewById(R.id.imgbtn_done_admission_ct);
		admiCtDoneBtn.setEnabled(false);

		noVisiSignRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_no_visible_sign);
		hyperSignRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_hyper_sign);
		earlyInfarctRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_early_infarct_sign);
		bleedingRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_bleeding);
		ischeLessRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_ischemia_less);
		ischeMoreRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_ischemia_more);
		tumorRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_tumor);
		abscessRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_abscess);
		otherRadio = (CheckBox) imageView
				.findViewById(R.id.radio_admission_ct_other);

		perfuCtRecommendText = (TextView) imageView
				.findViewById(R.id.info_recommend_perfusion_ct);
		ctAngioRecommendText = (TextView) imageView
				.findViewById(R.id.info_recommend_ct_angiography);
		perfuCtView = (View) imageView.findViewById(R.id.view_lab_perfusion_ct);
		ctAngioView = (View) imageView
				.findViewById(R.id.view_lab_ct_angiography);
		perfuCtGroup = (RadioGroup) imageView
				.findViewById(R.id.radio_group_persusion_ct);
		ctAngioGroup = (RadioGroup) imageView
				.findViewById(R.id.radio_group_ct_angiography);
		perfuCtDoneGroup = (RadioGroup) imageView
				.findViewById(R.id.radio_group_done_persusion_ct);
		ctAngioDoneGroup = (RadioGroup) imageView
				.findViewById(R.id.radio_group_done_ct_angiography);

		perfuCtNotDoneRadio = (RadioButton) imageView
				.findViewById(R.id.radio_persusion_ct_not_done);
		ctAngioNotDoneRadio = (RadioButton) imageView
				.findViewById(R.id.radio_ct_angiography_not_done);

		perfuCtDone1Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_persusion_ct_1);
		perfuCtDone2Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_persusion_ct_2);
		perfuCtDone3Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_persusion_ct_3);
		ctAngioDone1Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_ct_angiography_1);
		ctAngioDone2Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_ct_angiography_2);
		ctAngioDone3Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_ct_angiography_3);
		ctAngioDone4Radio = (RadioButton) imageView
				.findViewById(R.id.radio_done_ct_angiography_4);

		perfuCtDoneView = (View) imageView
				.findViewById(R.id.view_done_perfusion_ct);
		perfuCtNotDoneView = (View) imageView
				.findViewById(R.id.view_not_done_perfusion_ct);
		ctAngioDoneView = (View) imageView
				.findViewById(R.id.view_done_ct_angiography);
		ctAngioNotDoneView = (View) imageView
				.findViewById(R.id.view_not_done_ct_angiography);

		perfuCtDoneInfoText = (TextView) imageView
				.findViewById(R.id.info_lab_done_persusion_ct);
		ctAngioDoneInfoText = (TextView) imageView
				.findViewById(R.id.info_lab_done_ct_angiography);

		perfuCtNotDoneEdit = (MyEditText) imageView
				.findViewById(R.id.edit_text_not_done_persusion_ct);
		ctAngioNotDoneEdit = (MyEditText) imageView
				.findViewById(R.id.edit_text_not_done_ct_angiography);

		perfuCtComple = (TextView) imageView.findViewById(R.id.comple_perfusion_ct);
		ctAngioComple = (TextView) imageView.findViewById(R.id.comple_ct_angiography);

		perfuCtGroup.setOnCheckedChangeListener(this);
		perfuCtDoneGroup.setOnCheckedChangeListener(this);
		ctAngioGroup.setOnCheckedChangeListener(this);
		ctAngioDoneGroup.setOnCheckedChangeListener(this);

		perfuCtDone1Radio.setOnCheckedChangeListener(this);
		perfuCtDone2Radio.setOnCheckedChangeListener(this);
		perfuCtDone3Radio.setOnCheckedChangeListener(this);
		ctAngioDone1Radio.setOnCheckedChangeListener(this);
		ctAngioDone2Radio.setOnCheckedChangeListener(this);
		ctAngioDone3Radio.setOnCheckedChangeListener(this);
		ctAngioDone4Radio.setOnCheckedChangeListener(this);

		noVisiSignRadio.setOnCheckedChangeListener(this);
		hyperSignRadio.setOnCheckedChangeListener(this);
		earlyInfarctRadio.setOnCheckedChangeListener(this);
		bleedingRadio.setOnCheckedChangeListener(this);
		ischeLessRadio.setOnCheckedChangeListener(this);
		ischeMoreRadio.setOnCheckedChangeListener(this);
		tumorRadio.setOnCheckedChangeListener(this);
		abscessRadio.setOnCheckedChangeListener(this);
		otherRadio.setOnCheckedChangeListener(this);

		perfuCtNotDoneEdit.setOnFocusChangeListener(this);
		ctAngioNotDoneEdit.setOnFocusChangeListener(this);

		if (appContext instanceof FragmentLab) {
			admiCtDoneBtn.setOnClickListener(this);
			noVisiSignRadio.setOnClickListener(this);
			perfuCtNotDoneRadio.setOnClickListener(this);
			ctAngioNotDoneRadio.setOnClickListener(this);
			perfuCtDone3Radio.setOnClickListener(this);
			ctAngioDone4Radio.setOnClickListener(this);
		}
	}

	public void setUserSelection() {
		/** Admission CT */
		if (AppViewModel.symptomDAO.getQuickRecovSympId() == R.id.radio_quick_recover_symptoms_yes
				|| AppViewModel.symptomDAO.getMildSympId() == R.id.radio_mild_symptoms_yes
				|| AppViewModel.symptomDAO.getSuggestSavId() == R.id.radio_suggest_sav_yes) {
			admiCtRecommendText.setVisibility(TextView.VISIBLE);
			AppViewModel.labDAO.setIsAdminCtRecommend(true);
		} else {
			admiCtRecommendText.setVisibility(TextView.GONE);
			AppViewModel.labDAO.setIsAdminCtRecommend(false);
		}

		int tempInt = AppViewModel.labDAO.getAdmiCtNoVisiSign();
		if (tempInt > 0)
			noVisiSignRadio.setChecked(true);
		else
			noVisiSignRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtHyperSign();
		if (tempInt > 0)
			hyperSignRadio.setChecked(true);
		else
			hyperSignRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtEarlyInfarct();
		if (tempInt > 0)
			earlyInfarctRadio.setChecked(true);
		else
			earlyInfarctRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtBleeding();
		if (tempInt > 0)
			bleedingRadio.setChecked(true);
		else
			bleedingRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtIscheLess();
		if (tempInt > 0)
			ischeLessRadio.setChecked(true);
		else
			ischeLessRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtIscheMore();
		if (tempInt > 0)
			ischeMoreRadio.setChecked(true);
		else
			ischeMoreRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtTumor();
		if (tempInt > 0)
			tumorRadio.setChecked(true);
		else
			tumorRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtAbscess();
		if (tempInt > 0)
			abscessRadio.setChecked(true);
		else
			abscessRadio.setChecked(false);

		tempInt = AppViewModel.labDAO.getAdmiCtOther();
		if (tempInt > 0)
			otherRadio.setChecked(true);
		else
			otherRadio.setChecked(false);

		checkCtRecommendations();

		tempInt = AppViewModel.labDAO.getPerfusionCtId();
		if (tempInt > 0) {
			perfuCtGroup.check(tempInt);
			switch (tempInt) {
			case R.id.radio_persusion_ct_done:
				perfuCtNotDoneView.setVisibility(View.GONE);
				perfuCtDoneView.setVisibility(View.VISIBLE);
				int result = AppViewModel.labDAO.getPerfusionCtResultsId();
				perfuCtDoneGroup.check(result);
				break;
			case R.id.radio_persusion_ct_not_done:
				perfuCtDoneView.setVisibility(View.GONE);
				break;
			}
		} else {
			perfuCtNotDoneView.setVisibility(View.GONE);
			perfuCtDoneView.setVisibility(View.GONE);
		}

		tempInt = AppViewModel.labDAO.getCtAngiographyId();
		if (tempInt > 0) {
			ctAngioGroup.check(tempInt);
			switch (tempInt) {
			case R.id.radio_ct_angiography_done:
				ctAngioNotDoneView.setVisibility(View.GONE);
				ctAngioDoneView.setVisibility(View.VISIBLE);
				int result = AppViewModel.labDAO.getCtAngiographyResultsId();
				ctAngioDoneGroup.check(result);
				break;
			case R.id.radio_ct_angiography_not_done:
				ctAngioDoneView.setVisibility(View.GONE);
				break;
			}
		} else {
			ctAngioNotDoneView.setVisibility(View.GONE);
			ctAngioDoneView.setVisibility(View.GONE);
		}
		completeAdCt();
		completePerfuCt();
		completeCtAngio();
	}

	public void checkCtRecommendations() {
		/** Perfusion Ct Part */
		if (AppViewModel.symptomDAO.getBasilarisId() == R.id.radio_basilaris_main_suspicion
				|| (AppViewModel.labDAO.getAdmiCtNoVisiSign() > 0 && (AppViewModel.symptomDAO
						.getQuickRecovSympId() == R.id.radio_quick_recover_symptoms_yes || AppViewModel.symptomDAO
						.getMildSympId() == R.id.radio_mild_symptoms_yes))
				|| AppViewModel.labDAO.getAdmiCtHyperSign() > 0) {
			perfuCtRecommendText.setVisibility(TextView.VISIBLE);
			AppViewModel.labDAO.setIsPerfusionCtRecommend(true);
			if (AppViewModel.labDAO.getPerfusionCtId() == R.id.radio_persusion_ct_not_done) {
				perfuCtNotDoneView.setVisibility(View.VISIBLE);
				perfuCtNotDoneEdit.setText(AppViewModel.labDAO
						.getPerfusionCtWhy());
			}
		} else {
			perfuCtRecommendText.setVisibility(TextView.GONE);
			AppViewModel.labDAO.setIsPerfusionCtRecommend(false);
			perfuCtNotDoneView.setVisibility(View.GONE);
			perfuCtNotDoneEdit.setText("");
		}

		if (AppViewModel.symptomDAO.getBasilarisId() == R.id.radio_basilaris_main_suspicion
				|| (AppViewModel.labDAO.getAdmiCtNoVisiSign() > 0 && (AppViewModel.symptomDAO
						.getQuickRecovSympId() == R.id.radio_quick_recover_symptoms_yes
						|| AppViewModel.symptomDAO.getMildSympId() == R.id.radio_mild_symptoms_yes || AppViewModel.symptomDAO
						.getSuggestSavId() == R.id.radio_suggest_sav_yes))
				|| AppViewModel.labDAO.getAdmiCtHyperSign() > 0
				|| AppViewModel.nihssDAO.getNihssBaseline().getNihssTotal() > 10) {
			ctAngioRecommendText.setVisibility(TextView.VISIBLE);
			AppViewModel.labDAO.setIsCtAngiographyRecommend(true);
			if (AppViewModel.labDAO.getCtAngiographyId() == R.id.radio_ct_angiography_not_done) {
				ctAngioNotDoneView.setVisibility(View.VISIBLE);
				ctAngioNotDoneEdit.setText(AppViewModel.labDAO
						.getCtAngiographyWhy());
			}
		} else {
			ctAngioRecommendText.setVisibility(TextView.GONE);
			AppViewModel.labDAO.setIsCtAngiographyRecommend(false);
			ctAngioNotDoneView.setVisibility(View.GONE);
			ctAngioNotDoneEdit.setText("");
		}
	}

	/** For thrombolysis menu */
	public void setAdmiCT() {
		imagingInfoText.setVisibility(TextView.GONE);
		admiCtInfoText.setVisibility(TextView.GONE);
		admiCtDoneBtn.setVisibility(Button.GONE);
		tumorRadio.setVisibility(CheckBox.GONE);
		abscessRadio.setVisibility(CheckBox.GONE);
		otherRadio.setVisibility(CheckBox.GONE);
		bleedingRadio.setVisibility(CheckBox.GONE);
		ischeMoreRadio.setVisibility(CheckBox.GONE);

		int tempInt = AppViewModel.labDAO.getAdmiCtNoVisiSign();
		if (tempInt > 0) {
			noVisiSignRadio.setChecked(true);
			noVisiSignRadio.setVisibility(CheckBox.VISIBLE);
			admiCtInfoText.setVisibility(TextView.VISIBLE);
		} else {
			noVisiSignRadio.setChecked(false);
			noVisiSignRadio.setVisibility(CheckBox.GONE);
		}

		tempInt = AppViewModel.labDAO.getAdmiCtHyperSign();
		if (tempInt > 0) {
			hyperSignRadio.setChecked(true);
			hyperSignRadio.setVisibility(CheckBox.VISIBLE);
			admiCtInfoText.setVisibility(TextView.VISIBLE);
		} else {
			hyperSignRadio.setChecked(false);
			hyperSignRadio.setVisibility(CheckBox.GONE);
		}

		tempInt = AppViewModel.labDAO.getAdmiCtEarlyInfarct();
		if (tempInt > 0) {
			earlyInfarctRadio.setChecked(true);
			earlyInfarctRadio.setVisibility(CheckBox.VISIBLE);
			admiCtInfoText.setVisibility(TextView.VISIBLE);
		} else {
			earlyInfarctRadio.setChecked(false);
			earlyInfarctRadio.setVisibility(CheckBox.GONE);

		}

		tempInt = AppViewModel.labDAO.getAdmiCtIscheLess();
		if (tempInt > 0) {
			ischeLessRadio.setChecked(true);
			ischeLessRadio.setVisibility(CheckBox.VISIBLE);
			admiCtInfoText.setVisibility(TextView.VISIBLE);
		} else {
			ischeLessRadio.setChecked(false);
			ischeLessRadio.setVisibility(CheckBox.GONE);

		}
	}

	public void setPerfuAndAngioCT() {
		int tempInt = AppViewModel.labDAO.getPerfusionCtId();
		int result = AppViewModel.labDAO.getPerfusionCtResultsId();
		if (tempInt == R.id.radio_persusion_ct_done && result > 0
				&& result != R.id.radio_done_persusion_ct_3) {
			perfuCtView.setVisibility(View.VISIBLE);
			perfuCtGroup.setVisibility(RadioGroup.GONE);
			perfuCtNotDoneView.setVisibility(View.GONE);
			perfuCtDoneInfoText.setVisibility(TextView.GONE);

			perfuCtDone3Radio.setVisibility(RadioButton.GONE);

			switch (result) {
			case R.id.radio_done_persusion_ct_1:
				perfuCtDone1Radio.setChecked(true);
				perfuCtDone2Radio.setVisibility(RadioButton.GONE);
				break;
			case R.id.radio_done_persusion_ct_2:
				perfuCtDone2Radio.setChecked(true);
				perfuCtDone1Radio.setVisibility(RadioButton.GONE);
				break;
			}
		} else {
			perfuCtView.setVisibility(View.GONE);
			perfuCtDoneView.setVisibility(View.GONE);
			perfuCtNotDoneView.setVisibility(View.GONE);
		}

		tempInt = AppViewModel.labDAO.getCtAngiographyId();
		result = AppViewModel.labDAO.getCtAngiographyResultsId();
		if (tempInt == R.id.radio_ct_angiography_done && result > 0
				&& result != R.id.radio_done_ct_angiography_4) {
			ctAngioView.setVisibility(View.VISIBLE);
			ctAngioGroup.setVisibility(RadioGroup.GONE);
			ctAngioNotDoneView.setVisibility(View.GONE);
			ctAngioDoneInfoText.setVisibility(TextView.GONE);
			ctAngioDone4Radio.setVisibility(RadioButton.GONE);

			switch (result) {
			case R.id.radio_done_ct_angiography_1:
				ctAngioDone1Radio.setChecked(true);
				ctAngioDone2Radio.setVisibility(RadioButton.GONE);
				ctAngioDone3Radio.setVisibility(RadioButton.GONE);
				break;
			case R.id.radio_done_ct_angiography_2:
				ctAngioDone2Radio.setChecked(true);
				ctAngioDone1Radio.setVisibility(RadioButton.GONE);
				ctAngioDone3Radio.setVisibility(RadioButton.GONE);
				break;
			case R.id.radio_done_ct_angiography_3:
				ctAngioDone3Radio.setChecked(true);
				ctAngioDone1Radio.setVisibility(RadioButton.GONE);
				ctAngioDone2Radio.setVisibility(RadioButton.GONE);
				break;
			}
		} else {
			ctAngioView.setVisibility(View.GONE);
			ctAngioDoneView.setVisibility(View.GONE);
			ctAngioNotDoneView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton comBtn, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			switch (comBtn.getId()) {
			case R.id.radio_done_persusion_ct_1:
			case R.id.radio_done_persusion_ct_2:
			case R.id.radio_done_ct_angiography_1:
			case R.id.radio_done_ct_angiography_2:
			case R.id.radio_done_ct_angiography_3:
			case R.id.radio_admission_ct_no_visible_sign:
			case R.id.radio_admission_ct_hyper_sign:
			case R.id.radio_admission_ct_early_infarct_sign:
			case R.id.radio_admission_ct_ischemia_less:
				bgColor = appContext.getResources().getColor(R.color.green);
				break;
			case R.id.radio_done_persusion_ct_3:
			case R.id.radio_done_ct_angiography_4:
				bgColor = appContext.getResources().getColor(R.color.yellow);
				break;
			case R.id.radio_admission_ct_ischemia_more:
			case R.id.radio_admission_ct_bleeding:
			case R.id.radio_admission_ct_tumor:
			case R.id.radio_admission_ct_abscess:
			case R.id.radio_admission_ct_other:
				bgColor = appContext.getResources().getColor(R.color.red);
				break;
			}

			switch (comBtn.getId()) {
			case R.id.radio_admission_ct_no_visible_sign:
				AppViewModel.labDAO
						.setAdmiCtNoVisiSign(R.string.radio_admission_ct_no_visible_sign);
				checkCtRecommendations();
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_hyper_sign:
				AppViewModel.labDAO
						.setAdmiCtHyperSign(R.string.radio_admission_ct_hyper_sign);
				checkCtRecommendations();
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_early_infarct_sign:
				AppViewModel.labDAO
						.setAdmiCtEarlyInfarct(R.string.radio_admission_ct_early_infarct_sign);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_ischemia_less:
				AppViewModel.labDAO
						.setAdmiCtIscheLess(R.string.radio_admission_ct_ischemia_less);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_ischemia_more:
				AppViewModel.labDAO
						.setAdmiCtIscheMore(R.string.radio_admission_ct_ischemia_more);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_bleeding:
				AppViewModel.labDAO
						.setAdmiCtBleeding(R.string.radio_admission_ct_bleeding);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_tumor:
				AppViewModel.labDAO
						.setAdmiCtTumor(R.string.radio_admission_ct_tumor);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_abscess:
				AppViewModel.labDAO
						.setAdmiCtAbscess(R.string.radio_admission_ct_abscess);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_other:
				AppViewModel.labDAO
						.setAdmiCtOther(R.string.radio_admission_ct_other);
				admiCtDoneBtn.setEnabled(true);
				break;
			}
		} else if (!isChecked) {
			switch (comBtn.getId()) {
			case R.id.radio_admission_ct_no_visible_sign:
				AppViewModel.labDAO.setAdmiCtNoVisiSign(0);
				checkCtRecommendations();
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_hyper_sign:
				AppViewModel.labDAO.setAdmiCtHyperSign(0);
				checkCtRecommendations();
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_early_infarct_sign:
				AppViewModel.labDAO.setAdmiCtEarlyInfarct(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_ischemia_less:
				AppViewModel.labDAO.setAdmiCtIscheLess(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_ischemia_more:
				AppViewModel.labDAO.setAdmiCtIscheMore(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_bleeding:
				AppViewModel.labDAO.setAdmiCtBleeding(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_tumor:
				AppViewModel.labDAO.setAdmiCtTumor(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_abscess:
				AppViewModel.labDAO.setAdmiCtAbscess(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			case R.id.radio_admission_ct_other:
				AppViewModel.labDAO.setAdmiCtOther(0);
				admiCtDoneBtn.setEnabled(true);
				break;
			}
		}
		comBtn.setBackgroundColor(bgColor);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton r = null;
		r = (RadioButton) imageView.findViewById(checkedId);
		if (r != null) {
			switch (group.getId()) {
			case R.id.radio_group_persusion_ct:
				AppViewModel.labDAO.setPerfusionCtId(checkedId);
				AppViewModel.labDAO.setPerfusionCt(r.getText().toString());
				completePerfuCt();
				break;
			case R.id.radio_group_done_persusion_ct:

				AppViewModel.labDAO.setPerfusionCtResultsId(checkedId);
				AppViewModel.labDAO.setPerfusionCtResults(r.getText()
						.toString());
				completePerfuCt();
				break;
			case R.id.radio_group_ct_angiography:
				AppViewModel.labDAO.setCtAngiographyId(checkedId);
				AppViewModel.labDAO.setCtAngiography(r.getText().toString());
				completeCtAngio();
				break;
			case R.id.radio_group_done_ct_angiography:
				AppViewModel.labDAO.setCtAngiographyResultsId(checkedId);
				AppViewModel.labDAO.setCtAngiographyResults(r.getText()
						.toString());
				completeCtAngio();
				break;
			}
		}

		switch (checkedId) {
		case R.id.radio_persusion_ct_done:
			perfuCtDoneView.setVisibility(View.VISIBLE);
			perfuCtNotDoneView.setVisibility(View.GONE);
			perfuCtNotDoneEdit.setText("");
			break;
		case R.id.radio_persusion_ct_not_done:
			perfuCtDoneView.setVisibility(View.GONE);
			perfuCtDoneGroup.clearCheck();
			AppViewModel.labDAO.setPerfusionCtResultsId(0);
			AppViewModel.labDAO.setPerfusionCtResults("");
			if (AppViewModel.labDAO.getIsPerfusionCtRecommend())
				perfuCtNotDoneView.setVisibility(View.VISIBLE);
			else
				ctAngioNotDoneView.setVisibility(View.GONE);
			break;
		case R.id.radio_ct_angiography_done:
			ctAngioDoneView.setVisibility(View.VISIBLE);
			ctAngioNotDoneView.setVisibility(View.GONE);
			ctAngioNotDoneEdit.setText("");
			break;
		case R.id.radio_ct_angiography_not_done:
			ctAngioDoneView.setVisibility(View.GONE);
			ctAngioDoneGroup.clearCheck();
			AppViewModel.labDAO.setCtAngiographyResultsId(0);
			AppViewModel.labDAO.setCtAngiographyResults("");
			if (AppViewModel.labDAO.getIsCtAngiographyRecommend())
				ctAngioNotDoneView.setVisibility(View.VISIBLE);
			else
				ctAngioNotDoneView.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus) {
			String tempStr = "";
			switch (v.getId()) {
			case R.id.edit_text_not_done_persusion_ct:
				tempStr = perfuCtNotDoneEdit.getText().toString();
				AppViewModel.labDAO.setPerfusionCtWhy(tempStr);
				break;
			case R.id.edit_text_not_done_ct_angiography:
				tempStr = ctAngioNotDoneEdit.getText().toString();
				AppViewModel.labDAO.setCtAngiographyWhy(tempStr);
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (v.equals(admiCtDoneBtn)) {
			admiCtDoneBtn.setEnabled(false);
			completeAdCt();
			int isCheMore = AppViewModel.labDAO.getAdmiCtIscheMore();
			int bleeding = AppViewModel.labDAO.getAdmiCtBleeding();
			int tumor = AppViewModel.labDAO.getAdmiCtTumor();
			int abscess = AppViewModel.labDAO.getAdmiCtAbscess();
			int other = AppViewModel.labDAO.getAdmiCtOther();
			int noVisiSign = AppViewModel.labDAO.getAdmiCtNoVisiSign();
			int sav = AppViewModel.symptomDAO.getSuggestSavId();
			String contentStr = "";
			String reasonStr = "";

			/** Check Admission CT contraindications */
			if (isCheMore > 0 || bleeding > 0 || tumor > 0 || abscess > 0
					|| other > 0
					|| (noVisiSign > 0 && sav == R.id.radio_suggest_sav_yes)) {
				if (isCheMore > 0 || bleeding > 0 || tumor > 0 || abscess > 0
						|| other > 0) {
					contentStr += "- Admission CT shows:\n";
					if (isCheMore > 0) {
						contentStr += "        "
								+ appContext
										.getString(R.string.radio_admission_ct_ischemia_more)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.radio_admission_ct_ischemia_more)
								+ ", ";
					}
					if (bleeding > 0) {
						contentStr += "        "
								+ appContext
										.getString(R.string.radio_admission_ct_bleeding)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.radio_admission_ct_bleeding)
								+ ", ";
					}
					if (tumor > 0) {
						contentStr += "        "
								+ appContext
										.getString(R.string.radio_admission_ct_tumor)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.radio_admission_ct_tumor)
								+ ", ";
					}
					if (abscess > 0) {
						contentStr += "        "
								+ appContext
										.getString(R.string.radio_admission_ct_abscess)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.radio_admission_ct_abscess)
								+ ", ";
					}
					if (other > 0) {
						contentStr += "        "
								+ appContext
										.getString(R.string.radio_admission_ct_other)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.radio_admission_ct_other)
								+ ", ";
					}
				}
				if (noVisiSign > 0 && sav == R.id.radio_suggest_sav_yes) {
					contentStr += "- Patient has symptoms suggesting SAV when CT is normal.\n";
					reasonStr += appContext
							.getString(R.string.reason_interr_ct_sav);
				}
				contentStr += "\nWhich contraindicating thrombolysis. Do you want to interrupt the process?";
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT, contentStr);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_ct_sav);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
				// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (id == R.id.radio_admission_ct_no_visible_sign
				|| id == R.id.radio_persusion_ct_not_done
				|| id == R.id.radio_done_persusion_ct_3
				|| id == R.id.radio_ct_angiography_not_done
				|| id == R.id.radio_done_ct_angiography_4) {
			int quickRecov = AppViewModel.symptomDAO.getQuickRecovSympId();
			int mild = AppViewModel.symptomDAO.getMildSympId();
			int admiCtNoVisible = AppViewModel.labDAO.getAdmiCtNoVisiSign();
			int perCt = AppViewModel.labDAO.getPerfusionCtId();
			int perCtResult = AppViewModel.labDAO.getPerfusionCtResultsId();
			int ctAngio = AppViewModel.labDAO.getCtAngiographyId();
			int ctAngioResult = AppViewModel.labDAO.getCtAngiographyResultsId();

			if ((quickRecov == R.id.radio_quick_recover_symptoms_yes || mild == R.id.radio_mild_symptoms_yes)
					&& (admiCtNoVisible > 0
							&& (perCt == R.id.radio_persusion_ct_not_done || (perCt == R.id.radio_persusion_ct_done && perCtResult == R.id.radio_done_persusion_ct_3)) && (ctAngio == R.id.radio_ct_angiography_not_done || (ctAngio == R.id.radio_ct_angiography_done && ctAngioResult == R.id.radio_done_ct_angiography_4)))) {
				String contentStr = "";
				String reasonStr = "";

				if (quickRecov == R.id.radio_quick_recover_symptoms_yes) {
					if (perCtResult == R.id.radio_done_persusion_ct_3
							&& ctAngioResult == R.id.radio_done_ct_angiography_4) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_quick)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_quick)
								+ ", ";
					} else if (ctAngio == R.id.radio_ct_angiography_not_done
							&& perCtResult == R.id.radio_done_persusion_ct_3) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_angio_not_quick)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_angio_not_quick)
								+ ", ";
					} else if (perCt == R.id.radio_persusion_ct_not_done
							&& ctAngioResult == R.id.radio_done_ct_angiography_4) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_perfu_not_quick)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_perfu_not_quick)
								+ ", ";
					} else if (perCt == R.id.radio_persusion_ct_not_done
							&& ctAngio == R.id.radio_ct_angiography_not_done) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_angio_perfu_not_quick)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_angio_perfu_not_quick)
								+ ", ";
					}
				}
				if (mild == R.id.radio_mild_symptoms_yes) {
					if (perCtResult == R.id.radio_done_persusion_ct_3
							&& ctAngioResult == R.id.radio_done_ct_angiography_4) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_mild) + "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_mild)
								+ ", ";
					} else if (ctAngio == R.id.radio_ct_angiography_not_done
							&& perCtResult == R.id.radio_done_persusion_ct_3) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_angio_not_mild)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_angio_not_mild)
								+ ", ";
					} else if (perCt == R.id.radio_persusion_ct_not_done
							&& ctAngioResult == R.id.radio_done_ct_angiography_4) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_perfu_not_mild)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_perfu_not_mild)
								+ ", ";
					} else if (perCt == R.id.radio_persusion_ct_not_done
							&& ctAngio == R.id.radio_ct_angiography_not_done) {
						contentStr += appContext
								.getString(R.string.info_interr_ct_angio_perfu_not_mild)
								+ "\n";
						reasonStr += appContext
								.getString(R.string.reason_interr_ct_angio_perfu_not_mild)
								+ ", ";
					}
				}

				contentStr += "\nWhich contraindicating thrombolysis. Do you want to interrupt the process?";
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT, contentStr);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_ct_quick);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
				// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}

		}
	}
	public void completeAdCt() {
		int noVisiSign = AppViewModel.labDAO.getAdmiCtNoVisiSign();
		int hyper = AppViewModel.labDAO.getAdmiCtHyperSign();
		int infract = AppViewModel.labDAO.getAdmiCtEarlyInfarct();
		int bleeding = AppViewModel.labDAO.getAdmiCtBleeding();
		int isCheLess = AppViewModel.labDAO.getAdmiCtIscheLess();
		int isCheMore = AppViewModel.labDAO.getAdmiCtIscheMore();
		int tumor = AppViewModel.labDAO.getAdmiCtTumor();
		int abscess = AppViewModel.labDAO.getAdmiCtAbscess();
		int other = AppViewModel.labDAO.getAdmiCtOther();

		if (noVisiSign > 0 || hyper > 0 ||infract > 0 || bleeding > 0 ||isCheLess > 0 ||isCheMore > 0 ||  tumor > 0 || abscess > 0
				|| other > 0) {
			admiCtInfoText.setBackgroundColor(Color.TRANSPARENT);
		} else {
			admiCtInfoText.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
	public void completePerfuCt() {
		int perfusionCtId = AppViewModel.labDAO.getPerfusionCtId();
		int perfusionCtResultsId = AppViewModel.labDAO.getPerfusionCtResultsId();
		String perfusionCtNot = AppViewModel.labDAO.getPerfusionCtWhy();

		if (perfusionCtId <= 0 || (perfusionCtId == R.id.radio_persusion_ct_done && perfusionCtResultsId <= 0)){
//				|| (perfusionCtId == R.id.radio_persusion_ct_not_done && (perfusionCtNot == null || perfusionCtNot.length() <= 0))) {
			perfuCtComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		} else {
			perfuCtComple.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	public void completeCtAngio() {
		int ctAngioId = AppViewModel.labDAO.getCtAngiographyId();
		int ctAngioResultsId = AppViewModel.labDAO.getCtAngiographyResultsId();
		String ctAngioNot = AppViewModel.labDAO.getCtAngiographyWhy();

		if (ctAngioId <= 0 || (ctAngioId == R.id.radio_ct_angiography_done && ctAngioResultsId <= 0)){
//				|| (ctAngioId == R.id.radio_ct_angiography_not_done && (ctAngioNot == null || ctAngioNot.length() <= 0))) {
			ctAngioComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		} else {
			ctAngioComple.setBackgroundColor(Color.TRANSPARENT);
		}
	}
}
