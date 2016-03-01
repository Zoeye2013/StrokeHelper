package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonLabStatus.CommonLabDoneCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CommonLabResults implements OnSeekBarChangeListener,
		OnClickListener {
	private View resultView;
	private Fragment appContext;

	private TextView labResultInfoText;
	private View quickInrView;
	private View inrView;
	private View apttView;
	private View pTrombaiView;
	private View bTromView;
	private View bGlucView;
	private View bGlucAfterView;
	private TextView quickInrText;
	private SeekBar quickInrSeek;
	private ImageButton quickInrPlusBtn;
	private ImageButton quickInrMinusBtn;
	private TextView inrText;
	private SeekBar inrSeek;
	private ImageButton inrPlusBtn;
	private ImageButton inrMinusBtn;
	private TextView apttText;
	private SeekBar apttSeek;
	private ImageButton apttPlusBtn;
	private ImageButton apttMinusBtn;
	private TextView pTrombaiText;
	private SeekBar pTrombaiSeek;
	private ImageButton pTrombaiPlusBtn;
	private ImageButton pTrombaiMinusBtn;
	private TextView bTromText;
	private SeekBar bTromSeek;
	private ImageButton bTromPlusBtn;
	private ImageButton bTromMinusBtn;
	private TextView bGlucText;
	private SeekBar bGlucSeek;
	private ImageButton bGlucPlusBtn;
	private ImageButton bGlucMinusBtn;
	private TextView bGlucAfterText;
	private SeekBar bGlucAfterSeek;
	private ImageButton bGlucAftPlusBtn;
	private ImageButton bGlucAftMinusBtn;

	private ImageButton quickInrHelpBtn;
	private ImageButton apttHelpBtn;
	private ImageButton pTrombaiHelpBtn;
	private ImageButton bTromHelpBtn;
	private ImageButton bGlucHelpBtn;
	private ImageButton bGlucAfterHelpBtn;
	
	private Button quickInrDoneBtn;
	private Button inrDoneBtn;
	private Button apttDoneBtn;
	private Button bTromDoneBtn;
	private Button bGlucDoneBtn;
	private Button bGlucAftDoneBtn;

	private CommonLabDoneCallBack labDoneCallBack;
	
	public CommonLabResults(View root, Fragment context) {
		resultView = root;
		appContext = context;
		// callBack = (ResultsChangedCallBack) appContext;
		initUIElements();
		if (appContext instanceof FragmentLab) {
			labDoneCallBack = (CommonLabDoneCallBack) appContext;
		}
		// initUIStatus();
	}

	public void initUIElements() {
		labResultInfoText = (TextView) resultView
				.findViewById(R.id.info_laboratory_results);
		quickInrView = (View) resultView.findViewById(R.id.view_quick_inr);
		inrView = (View) resultView.findViewById(R.id.view_inr);
		apttView = (View) resultView.findViewById(R.id.view_aptt);
		pTrombaiView = (View) resultView.findViewById(R.id.view_p_trombai);
		bTromView = (View) resultView.findViewById(R.id.view_b_trom);
		bGlucView = (View) resultView.findViewById(R.id.view_b_gluc);
		bGlucAfterView = (View) resultView.findViewById(R.id.view_b_gluc_after);

		quickInrText = (TextView) resultView.findViewById(R.id.info_quick_inr);

		CustomizeSeekbar cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_quick_inr);
		quickInrSeek = cusSeek.getSeekBar();
		quickInrPlusBtn = cusSeek.getPlusBtn();
		quickInrMinusBtn = cusSeek.getMinusBtn();

		inrText = (TextView) resultView.findViewById(R.id.info_inr);
		cusSeek = (CustomizeSeekbar) resultView.findViewById(R.id.seek_bar_inr);
		inrSeek = cusSeek.getSeekBar();
		inrPlusBtn = cusSeek.getPlusBtn();
		inrMinusBtn = cusSeek.getMinusBtn();

		apttText = (TextView) resultView.findViewById(R.id.info_aptt);
		cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_aptt);
		apttSeek = cusSeek.getSeekBar();
		apttPlusBtn = cusSeek.getPlusBtn();
		apttMinusBtn = cusSeek.getMinusBtn();

		pTrombaiText = (TextView) resultView.findViewById(R.id.info_p_trombai);
		cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_p_trombai);
		pTrombaiSeek = cusSeek.getSeekBar();
		pTrombaiPlusBtn = cusSeek.getPlusBtn();
		pTrombaiMinusBtn = cusSeek.getMinusBtn();

		bTromText = (TextView) resultView.findViewById(R.id.info_b_trom);
		cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_b_trom);
		bTromSeek = cusSeek.getSeekBar();
		bTromPlusBtn = cusSeek.getPlusBtn();
		bTromMinusBtn = cusSeek.getMinusBtn();

		bGlucText = (TextView) resultView.findViewById(R.id.info_b_gluc);
		cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_b_gluc);
		bGlucSeek = cusSeek.getSeekBar();
		bGlucPlusBtn = cusSeek.getPlusBtn();
		bGlucMinusBtn = cusSeek.getMinusBtn();

		bGlucAfterText = (TextView) resultView
				.findViewById(R.id.info_b_gluc_after);
		cusSeek = (CustomizeSeekbar) resultView
				.findViewById(R.id.seek_bar_b_gluc_after);
		bGlucAfterSeek = cusSeek.getSeekBar();
		bGlucAftPlusBtn = cusSeek.getPlusBtn();
		bGlucAftMinusBtn = cusSeek.getMinusBtn();

		quickInrHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_quick_inr);
		apttHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_aptt);
		pTrombaiHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_p_trombai);
		bTromHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_b_trom);
		bGlucHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_b_gluc);
		bGlucAfterHelpBtn = (ImageButton) resultView
				.findViewById(R.id.imgbtn_help_b_gluc_after);
		
		quickInrDoneBtn = (Button) resultView.findViewById(R.id.imgbtn_done_quick_inr);
		inrDoneBtn = (Button) resultView
				.findViewById(R.id.imgbtn_done_inr);
		apttDoneBtn = (Button) resultView.findViewById(R.id.imgbtn_done_aptt);
		bTromDoneBtn = (Button) resultView
				.findViewById(R.id.imgbtn_done_b_trom);
		bGlucDoneBtn = (Button) resultView.findViewById(R.id.imgbtn_done_b_gluc);
		bGlucAftDoneBtn = (Button) resultView
				.findViewById(R.id.imgbtn_done_b_gluc_after);

		quickInrSeek.setMax(30);
		inrSeek.setMax(30);
		apttSeek.setMax(100);
		pTrombaiSeek.setMax(70);
		bTromSeek.setMax(200);
		bGlucSeek.setMax(130);
		bGlucAfterSeek.setMax(130);

		quickInrSeek.setOnSeekBarChangeListener(this);
		inrSeek.setOnSeekBarChangeListener(this);
		apttSeek.setOnSeekBarChangeListener(this);
		pTrombaiSeek.setOnSeekBarChangeListener(this);
		bTromSeek.setOnSeekBarChangeListener(this);
		bGlucSeek.setOnSeekBarChangeListener(this);
		bGlucAfterSeek.setOnSeekBarChangeListener(this);

		quickInrHelpBtn.setOnClickListener(this);
		apttHelpBtn.setOnClickListener(this);
		pTrombaiHelpBtn.setOnClickListener(this);
		bTromHelpBtn.setOnClickListener(this);
		bGlucHelpBtn.setOnClickListener(this);
		bGlucAfterHelpBtn.setOnClickListener(this);

		quickInrPlusBtn.setOnClickListener(this);
		quickInrMinusBtn.setOnClickListener(this);
		inrPlusBtn.setOnClickListener(this);
		inrMinusBtn.setOnClickListener(this);
		apttPlusBtn.setOnClickListener(this);
		apttMinusBtn.setOnClickListener(this);
		pTrombaiPlusBtn.setOnClickListener(this);
		pTrombaiMinusBtn.setOnClickListener(this);
		bTromPlusBtn.setOnClickListener(this);
		bTromMinusBtn.setOnClickListener(this);
		bGlucPlusBtn.setOnClickListener(this);
		bGlucMinusBtn.setOnClickListener(this);
		bGlucAftPlusBtn.setOnClickListener(this);
		bGlucAftMinusBtn.setOnClickListener(this);
		
		if(appContext instanceof FragmentLab){
			quickInrDoneBtn.setVisibility(Button.VISIBLE);
			inrDoneBtn.setVisibility(Button.VISIBLE);
			apttDoneBtn.setVisibility(Button.VISIBLE);
			bTromDoneBtn.setVisibility(Button.VISIBLE);
			bGlucDoneBtn.setVisibility(Button.VISIBLE);
			bGlucAftDoneBtn.setVisibility(Button.VISIBLE);
			
			quickInrDoneBtn.setEnabled(false);
			inrDoneBtn.setEnabled(false);
			apttDoneBtn.setEnabled(false);
			bTromDoneBtn.setEnabled(false);
			bGlucDoneBtn.setEnabled(false);
			bGlucAftDoneBtn.setEnabled(false);
			
			quickInrDoneBtn.setOnClickListener(this);
			inrDoneBtn.setOnClickListener(this);
			apttDoneBtn.setOnClickListener(this);
			bTromDoneBtn.setOnClickListener(this);
			bGlucDoneBtn.setOnClickListener(this);
			bGlucAftDoneBtn.setOnClickListener(this);
		}
	}

	public void setUserSelection() {
		if (appContext instanceof FragmentLab) {
			float quickInr = AppViewModel.labDAO.getQuickInr();
			float inr = AppViewModel.labDAO.getInr();
			quickInrText.setText(String.valueOf(quickInr));
			int tempInt = (int) (quickInr * 10);
			quickInrSeek.setProgress(tempInt);
			updateINRs(quickInr, inr);

			int aptt = AppViewModel.labDAO.getAPTT();
			apttText.setText(String.valueOf(aptt));
			apttSeek.setProgress(aptt);
			updateAPTT(aptt);

			int pTrombai = AppViewModel.labDAO.getPTrombai();
			pTrombaiText.setText(String.valueOf(pTrombai));
			pTrombaiSeek.setProgress(pTrombai);
			updatePTrombai(pTrombai);

			int bTrom = AppViewModel.labDAO.getBTrom();
			bTromText.setText(String.valueOf(bTrom));
			bTromSeek.setProgress(bTrom);
			updateBTrom(bTrom);

			float bGluc = AppViewModel.labDAO.getBGluc();
			bGlucText.setText(String.valueOf(bGluc));
			tempInt = (int) (bGluc * 10);
			bGlucSeek.setProgress(tempInt);
			updateBGlucs(bGluc);
			boolean treatDecis = AppViewModel.labDAO.getBGlucTreatDecision();
			if (treatDecis == true) {
				float bGlucAft = AppViewModel.labDAO.getBGlucAfter();
				updateBGlucAfter(true, bGlucAft);
			} else if (treatDecis == false) {
				updateBGlucAfter(false, 0);
			}
			
		} else if (appContext instanceof FragmentThrombolysisDecision) {
			labResultInfoText.setVisibility(TextView.GONE);
			pTrombaiView.setVisibility(View.GONE);
			bGlucView.setVisibility(View.GONE);
			bGlucAfterView.setVisibility(View.GONE);

			float tempF = AppViewModel.labDAO.getQuickInr();
			int tempInt;
			if (tempF > 0 && tempF <= 1.7) {
				quickInrView.setVisibility(View.VISIBLE);
				quickInrView.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
				quickInrText.setText(String.valueOf(tempF));
				tempInt = (int) (tempF * 10);
				quickInrSeek.setVisibility(SeekBar.GONE);
				quickInrPlusBtn.setVisibility(ImageButton.GONE);
				quickInrMinusBtn.setVisibility(ImageButton.GONE);
			} else {
				quickInrView.setVisibility(View.GONE);
			}

			float inr = AppViewModel.labDAO.getInr();
			if (tempF >= 1.5 && tempF <= 1.7 && inr > 0 && inr <= 1.7) {
				inrView.setVisibility(View.VISIBLE);
				inrView.setBackgroundColor(appContext.getResources().getColor(
						R.color.green));
				inrText.setText(String.valueOf(inr));
				tempInt = (int) (tempF * 10);
				inrSeek.setVisibility(SeekBar.GONE);
				inrPlusBtn.setVisibility(ImageButton.GONE);
				inrMinusBtn.setVisibility(ImageButton.GONE);
			} else {
				inrView.setVisibility(View.GONE);
			}

			tempInt = AppViewModel.labDAO.getAPTT();
			if (tempInt > 0 && tempInt <= 60) {
				apttView.setVisibility(View.VISIBLE);
				apttView.setBackgroundColor(appContext.getResources().getColor(
						R.color.green));
				apttText.setText(String.valueOf(tempInt));
				apttSeek.setVisibility(SeekBar.GONE);
				apttPlusBtn.setVisibility(ImageButton.GONE);
				apttMinusBtn.setVisibility(ImageButton.GONE);
			} else {
				apttView.setVisibility(View.GONE);
			}

			tempInt = AppViewModel.labDAO.getBTrom();
			if (tempInt > 0 && tempInt >= 100) {
				bTromView.setVisibility(View.VISIBLE);
				bTromView.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
				bTromText.setText(String.valueOf(tempInt));
				bTromSeek.setVisibility(SeekBar.GONE);
				bTromPlusBtn.setVisibility(ImageButton.GONE);
				bTromMinusBtn.setVisibility(ImageButton.GONE);
			} else {
				bTromView.setVisibility(View.GONE);
			}
		}
	}
	
	public void updateINRs(float quickInr, float inr){
		if(quickInr < 0)
			quickInr = AppViewModel.labDAO.getQuickInr();
		if(inr < 0)
			inr = AppViewModel.labDAO.getInr();
		
		/** Set quick inr color */
		if (quickInr > 0 && quickInr < 1.499999) {
			quickInrSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else if (quickInr > 1.700001) {
			quickInrSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.red));
		} else if (quickInr > 1.499999 && quickInr < 1.7000001) {
			quickInrSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.yellow));
		} else {
			quickInrSeek.setBackgroundColor(Color.TRANSPARENT);
		}
		
		/** Set inr */
		if (quickInr > 1.499999 && quickInr < 1.7000001) {

			inrView.setVisibility(View.VISIBLE);
			
			inrText.setText(String.valueOf(inr));
			int tempInt = (int) (inr * 10);
			inrSeek.setProgress(tempInt);
			if (inr > 0 && inr < 1.7000001) {
				inrSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			} else if (inr > 1.7000001) {
				inrSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.red));
			} else {
				inrSeek.setBackgroundColor(Color.TRANSPARENT);
			}
		} else {
			inrText.setText("");
			inrSeek.setProgress(0);
			AppViewModel.labDAO.setInr(0);
			inrView.setVisibility(View.GONE);
		}
	}
	
	public void updateAPTT(int aptt){
		if(aptt < 0)
			aptt = AppViewModel.labDAO.getAPTT();
		
		if (aptt > 0 && aptt < 34) {
			apttSeek.setBackgroundColor(appContext.getResources().getColor(
					R.color.green));
		} else if (aptt >= 34 && aptt <= 60) {
			apttSeek.setBackgroundColor(appContext.getResources().getColor(
					R.color.yellow));
		} else if (aptt > 60) {
			apttSeek.setBackgroundColor(appContext.getResources().getColor(
					R.color.red));
		} else {
			apttSeek.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void updatePTrombai(int pTrombai){
		if(pTrombai < 0)
			pTrombai = AppViewModel.labDAO.getPTrombai();
		
		if (pTrombai > 0 && (pTrombai < 17 || pTrombai > 25)) {
			pTrombaiSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.yellow));
		} else if (pTrombai >= 17 && pTrombai <= 25) {
			pTrombaiSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else {
			pTrombaiSeek.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void updateBTrom(int bTrom){
		if(bTrom < 0)
			bTrom = AppViewModel.labDAO.getBTrom();
		
		if (bTrom > 0 && bTrom < 100) {
			bTromSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.red));
		} else if (bTrom >= 100) {
			bTromSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else {
			bTromSeek.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void updateBGlucs(float bGluc){
		if(bGluc < 0)
			bGluc = AppViewModel.labDAO.getBGluc();
		
		if (bGluc > 0 && (bGluc < 2.799999 || bGluc > 10.000001)) {
			bGlucSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.yellow));
		} else if (bGluc > 2.799999 && bGluc < 10.000001) {
			bGlucSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else {
			bGlucSeek.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void updateBGlucAfter(boolean isVisible,float bGlucAft){
		if(isVisible){
			if(bGlucAft < 0)
				bGlucAft = AppViewModel.labDAO.getBGlucAfter();
			
			bGlucAfterView.setVisibility(View.VISIBLE);
			bGlucAfterText.setText(String.valueOf(bGlucAft));
			int tempInt = (int) (bGlucAft * 10);
			bGlucAfterSeek.setProgress(tempInt);
			if (bGlucAft > 0 && (bGlucAft < 2.799999 || bGlucAft > 10.000001)) {
				bGlucAfterSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.yellow));
			} else if (bGlucAft > 2.799999 && bGlucAft < 10.000001) {
				bGlucAfterSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			} else {
				bGlucAfterSeek.setBackgroundColor(Color.TRANSPARENT);
			}
		}else {
			bGlucAfterText.setText("");
			bGlucAfterSeek.setProgress(0);
			AppViewModel.labDAO.setBGlucAfter(0);
			bGlucAfterView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		float tempF = 0;
		if (seekBar.equals(quickInrSeek)) {
			tempF = (float) progress / 10;
			quickInrText.setText(String.valueOf(tempF));
		}else if (seekBar.equals(inrSeek)) {
			tempF = (float) progress / 10;
			inrText.setText(String.valueOf(tempF));
		}else if (seekBar.equals(apttSeek)) {
			apttText.setText(String.valueOf(progress));
		}else if (seekBar.equals(pTrombaiSeek)) {
			pTrombaiText.setText(String.valueOf(progress));
		}else if (seekBar.equals(bTromSeek)) {
			bTromText.setText(String.valueOf(progress));
		}else if (seekBar.equals(bGlucSeek)) {
			tempF = (float) progress / 10;
			bGlucText.setText(String.valueOf(tempF));
		}else if (seekBar.equals(bGlucAfterSeek)) {
			tempF = (float) progress / 10;
			bGlucAfterText.setText(String.valueOf(tempF));
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int bgColor = Color.TRANSPARENT;
		int progress = seekBar.getProgress();
		float tempF = 0;
		
		if (seekBar.equals(quickInrSeek)) {
			tempF = (float) progress / 10;
			AppViewModel.labDAO.setQuickInr(tempF);
			updateINRs(tempF, -1);
			quickInrDoneBtn.setEnabled(true);
		}else if (seekBar.equals(inrSeek)) {
			tempF = (float) progress / 10;
			AppViewModel.labDAO.setInr(tempF);
			updateINRs(-1, tempF);
			inrDoneBtn.setEnabled(true);
		}else if (seekBar.equals(apttSeek)) {
			AppViewModel.labDAO.setAPTT(progress);
			updateAPTT(progress);
			apttDoneBtn.setEnabled(true);
		}else if (seekBar.equals(pTrombaiSeek)) {
			AppViewModel.labDAO.setPTrombai(progress);
			updatePTrombai(progress);
		}else if (seekBar.equals(bTromSeek)) {
			AppViewModel.labDAO.setBTrom(progress);
			updateBTrom(progress);
			bTromDoneBtn.setEnabled(true);
		}else if (seekBar.equals(bGlucSeek)) {
			tempF = (float) progress / 10;
			AppViewModel.labDAO.setBGluc(tempF);
			updateBGlucs(tempF);
			bGlucDoneBtn.setEnabled(true);
		}else if (seekBar.equals(bGlucAfterSeek)) {
			tempF = (float) progress / 10;
			AppViewModel.labDAO.setBGlucAfter(tempF);
			updateBGlucAfter(true, tempF);
			bGlucAftDoneBtn.setEnabled(true);
		}
	}

	@Override
	public void onClick(View v) {
		int progress = 0;
		float tempF = 0;
		if (v.equals(quickInrPlusBtn)) {
			progress = quickInrSeek.getProgress() + 1;
			if (progress <= quickInrSeek.getMax()) {
				quickInrSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setQuickInr(tempF);
				updateINRs(tempF, -1);
				quickInrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(quickInrMinusBtn)) {
			progress = quickInrSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				quickInrSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setQuickInr(tempF);
				updateINRs(tempF, -1);
				quickInrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(inrPlusBtn)) {
			progress = inrSeek.getProgress() + 1;
			if (progress <= inrSeek.getMax()) {
				inrSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setInr(tempF);
				updateINRs(-1, tempF);
				inrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(inrMinusBtn)) {
			progress = inrSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				inrSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setInr(tempF);
				updateINRs(-1, tempF);
				inrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(apttPlusBtn)) {
			progress = apttSeek.getProgress() + 1;
			if (progress <= apttSeek.getMax()) {
				apttSeek.setProgress(progress);
				AppViewModel.labDAO.setAPTT(progress);
				updateAPTT(progress);
				apttDoneBtn.setEnabled(true);
			}
		} else if (v.equals(apttMinusBtn)) {
			progress = apttSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				apttSeek.setProgress(progress);
				AppViewModel.labDAO.setAPTT(progress);
				updateAPTT(progress);
				apttDoneBtn.setEnabled(true);
			}
		} else if (v.equals(pTrombaiPlusBtn)) {
			progress = pTrombaiSeek.getProgress() + 1;
			if (progress <= pTrombaiSeek.getMax()) {
				pTrombaiSeek.setProgress(progress);
				AppViewModel.labDAO.setPTrombai(progress);
				updatePTrombai(progress);
			}
		} else if (v.equals(pTrombaiMinusBtn)) {
			progress = pTrombaiSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				pTrombaiSeek.setProgress(progress);
				AppViewModel.labDAO.setPTrombai(progress);
				updatePTrombai(progress);
			}
		} else if (v.equals(bTromPlusBtn)) {
			progress = bTromSeek.getProgress() + 1;
			if (progress <= bTromSeek.getMax()) {
				bTromSeek.setProgress(progress);
				AppViewModel.labDAO.setBTrom(progress);
				updateBTrom(progress);
				bTromDoneBtn.setEnabled(true);
			}
		} else if (v.equals(bTromMinusBtn)) {
			progress = bTromSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				bTromSeek.setProgress(progress);
				AppViewModel.labDAO.setBTrom(progress);
				updateBTrom(progress);
				bTromDoneBtn.setEnabled(true);
			}
		} else if (v.equals(bGlucPlusBtn)) {
			progress = bGlucSeek.getProgress() + 1;
			if (progress <= bGlucSeek.getMax()) {
				bGlucSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setBGluc(tempF);
				updateBGlucs(tempF);
				bGlucDoneBtn.setEnabled(true);
			}
		} else if (v.equals(bGlucMinusBtn)) {
			progress = bGlucSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				bGlucSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setBGluc(tempF);
				updateBGlucs(tempF);
				bGlucDoneBtn.setEnabled(true);
			}
		} else if (v.equals(bGlucAftPlusBtn)) {
			progress = bGlucAfterSeek.getProgress() + 1;
			if (progress <= bGlucAfterSeek.getMax()) {
				bGlucAfterSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setBGlucAfter(tempF);
				updateBGlucAfter(true, tempF);
				bGlucAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(bGlucAftMinusBtn)) {
			progress = bGlucAfterSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				bGlucAfterSeek.setProgress(progress);
				tempF = (float) progress / 10;
				AppViewModel.labDAO.setBGlucAfter(tempF);
				updateBGlucAfter(true, tempF);
				bGlucAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(quickInrDoneBtn)) {
			quickInrDoneBtn.setEnabled(false);
			if (quickInrSeek.getProgress() > 17) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_quick_inr));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_quick_inr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		}else if (v.equals(inrDoneBtn)) {
			inrDoneBtn.setEnabled(false);
			if (inrSeek.getProgress() > 17) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_inr));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_inr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		}else if (v.equals(apttDoneBtn)) {
			apttDoneBtn.setEnabled(false);
			if (apttSeek.getProgress() > 60) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_aptt));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_aptt);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		}else if (v.equals(bTromDoneBtn)) {
			bTromDoneBtn.setEnabled(false);
			if (bTromSeek.getProgress() < 100) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_b_trom));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_b_trom);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		}else if (v.equals(bGlucDoneBtn)) {
			bGlucDoneBtn.setEnabled(false);
			labDoneCallBack.onLabDone(R.id.imgbtn_done_b_gluc);
		}else if (v.equals(bGlucAftDoneBtn)) {
			bGlucAftDoneBtn.setEnabled(false);
			int bGlucAfter = bGlucAfterSeek.getProgress();

			if (bGlucAfter >0 && bGlucAfter < 28) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_b_gluc_after));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_bgluc_after);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}else if(bGlucAfter >= 28 && bGlucAfter <= 100){
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.b_gluc_after_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_warning_b_gluc_after));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_warning_b_gluc_after);
				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivity(interrIntent);
			}
			
		}else {
			Intent intent = new Intent(appContext.getActivity(),
					DialogHelpInfo.class);

			switch (v.getId()) {
			case R.id.imgbtn_help_quick_inr:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_quick_inr));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_quick_inr));
				break;
			case R.id.imgbtn_help_aptt:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_aptt));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_aptt));
				break;
			case R.id.imgbtn_help_p_trombai:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_p_trombai));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_p_trombai));
				break;
			case R.id.imgbtn_help_b_trom:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_b_trom));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_b_trom));
				break;
			case R.id.imgbtn_help_b_gluc:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_b_gluc));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_b_gluc));
				break;
			case R.id.imgbtn_help_b_gluc_after:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.info_lab_b_gluc_after));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_b_gluc_after));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(intent);
		}
	}

}
