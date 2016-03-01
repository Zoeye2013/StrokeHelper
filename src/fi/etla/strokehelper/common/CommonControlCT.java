package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;

import fi.etla.strokehelper.data.AppViewModel;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CommonControlCT implements OnCheckedChangeListener {
	private View imageView;
	private Fragment appContext;

	private CheckBox noVisiSignRadio;
	private CheckBox hyperSignRadio;
	private CheckBox earlyInfarctRadio;
	private CheckBox bleedingRadio;
	private CheckBox ischeLessRadio;
	private CheckBox ischeMoreRadio;
	private CheckBox tumorRadio;
	private CheckBox abscessRadio;
	private CheckBox otherRadio;

	public CommonControlCT(View root, Fragment context) {
		imageView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		
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

		noVisiSignRadio.setOnCheckedChangeListener(this);
		hyperSignRadio.setOnCheckedChangeListener(this);
		earlyInfarctRadio.setOnCheckedChangeListener(this);
		bleedingRadio.setOnCheckedChangeListener(this);
		ischeLessRadio.setOnCheckedChangeListener(this);
		ischeMoreRadio.setOnCheckedChangeListener(this);
		tumorRadio.setOnCheckedChangeListener(this);
		abscessRadio.setOnCheckedChangeListener(this);
		otherRadio.setOnCheckedChangeListener(this);

	}

	public void setUserSelection() {
		/** Imaging */
		int tempInt = AppViewModel.thrombolysisDAO.getControlCtNoVisiSign();
		if (tempInt > 0)
			noVisiSignRadio.setChecked(true);
		else
			noVisiSignRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtHyperSign();
		if (tempInt > 0)
			hyperSignRadio.setChecked(true);
		else
			hyperSignRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtEarlyInfarct();
		if (tempInt > 0)
			earlyInfarctRadio.setChecked(true);
		else
			earlyInfarctRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtBleeding();
		if (tempInt > 0)
			bleedingRadio.setChecked(true);
		else
			bleedingRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtIscheLess();
		if (tempInt > 0)
			ischeLessRadio.setChecked(true);
		else
			ischeLessRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtIscheMore();
		if (tempInt > 0)
			ischeMoreRadio.setChecked(true);
		else
			ischeMoreRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtTumor();
		if (tempInt > 0)
			tumorRadio.setChecked(true);
		else
			tumorRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtAbscess();
		if (tempInt > 0)
			abscessRadio.setChecked(true);
		else
			abscessRadio.setChecked(false);

		tempInt = AppViewModel.thrombolysisDAO.getControlCtOther();
		if (tempInt > 0)
			otherRadio.setChecked(true);
		else
			otherRadio.setChecked(false);

	}
	
//	public void setControlCtViews(int type) {
//		switch(type){
//		case 15:
//			imageView.setVisibility(View.VISIBLE);
//			setUserSelection();
//			break;
//		}
//	}

	@Override
	public void onCheckedChanged(CompoundButton comBtn, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			switch (comBtn.getId()) {
			case R.id.radio_admission_ct_no_visible_sign:
			case R.id.radio_admission_ct_hyper_sign:
			case R.id.radio_admission_ct_early_infarct_sign:
			case R.id.radio_admission_ct_ischemia_less:
				bgColor = appContext.getResources().getColor(R.color.green);
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
				AppViewModel.thrombolysisDAO
						.setControlCtNoVisiSign(R.string.radio_admission_ct_no_visible_sign);
				break;
			case R.id.radio_admission_ct_hyper_sign:
				AppViewModel.thrombolysisDAO
						.setControlCtHyperSign(R.string.radio_admission_ct_hyper_sign);
				break;
			case R.id.radio_admission_ct_early_infarct_sign:
				AppViewModel.thrombolysisDAO
						.setControlCtEarlyInfarct(R.string.radio_admission_ct_early_infarct_sign);
				break;
			case R.id.radio_admission_ct_ischemia_less:
				AppViewModel.thrombolysisDAO
						.setControlCtIscheLess(R.string.radio_admission_ct_ischemia_less);
				break;
			case R.id.radio_admission_ct_ischemia_more:
				AppViewModel.thrombolysisDAO
						.setControlCtIscheMore(R.string.radio_admission_ct_ischemia_more);
				break;
			case R.id.radio_admission_ct_bleeding:
				AppViewModel.thrombolysisDAO
						.setControlCtBleeding(R.string.radio_admission_ct_bleeding);
				break;
			case R.id.radio_admission_ct_tumor:
				AppViewModel.thrombolysisDAO
						.setControlCtTumor(R.string.radio_admission_ct_tumor);
				break;
			case R.id.radio_admission_ct_abscess:
				AppViewModel.thrombolysisDAO
						.setControlCtAbscess(R.string.radio_admission_ct_abscess);
				break;
			case R.id.radio_admission_ct_other:
				AppViewModel.thrombolysisDAO
						.setControlCtOther(R.string.radio_admission_ct_other);
				break;
			}
		} else if (!isChecked) {
			switch (comBtn.getId()) {
			case R.id.radio_admission_ct_no_visible_sign:
				AppViewModel.thrombolysisDAO.setControlCtNoVisiSign(0);
				break;
			case R.id.radio_admission_ct_hyper_sign:
				AppViewModel.thrombolysisDAO.setControlCtHyperSign(0);
				break;
			case R.id.radio_admission_ct_early_infarct_sign:
				AppViewModel.thrombolysisDAO.setControlCtEarlyInfarct(0);
				break;
			case R.id.radio_admission_ct_ischemia_less:
				AppViewModel.thrombolysisDAO.setControlCtIscheLess(0);
				break;
			case R.id.radio_admission_ct_ischemia_more:
				AppViewModel.thrombolysisDAO.setControlCtIscheMore(0);
				break;
			case R.id.radio_admission_ct_bleeding:
				AppViewModel.thrombolysisDAO.setControlCtBleeding(0);
				break;
			case R.id.radio_admission_ct_tumor:
				AppViewModel.thrombolysisDAO.setControlCtTumor(0);
				break;
			case R.id.radio_admission_ct_abscess:
				AppViewModel.thrombolysisDAO.setControlCtAbscess(0);
				break;
			case R.id.radio_admission_ct_other:
				AppViewModel.thrombolysisDAO.setControlCtOther(0);
				break;
			}
		}
		comBtn.setBackgroundColor(bgColor);
	}
}
