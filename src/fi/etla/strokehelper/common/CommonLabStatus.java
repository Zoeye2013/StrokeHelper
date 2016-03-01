package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonPatientInfo.CommonPatientInfoCallBack;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.DialogMRS6Confirm;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CommonLabStatus implements OnCheckedChangeListener,
		OnSeekBarChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener, OnClickListener {
	private View statusView;
	private Fragment appContext;

	private TextView statusMeasureInfoText;

	private View rr1View;
	private View rr2View;
	private View rrAfter1View;
	private View rrAfter2View;
	private TextView rrSystolicText;
	private TextView rrDiastolicText;
	private SeekBar rrSystolicSeek;
	private SeekBar rrDiastolicSeek;
	private ImageButton rrSysPlusBtn;
	private ImageButton rrSysMinusBtn;
	private ImageButton rrDiaPlusBtn;
	private ImageButton rrDiaMinusBtn;

	private TextView rrAftSystolicText;
	private TextView rrAftDiastolicText;
	private SeekBar rrAftSystolicSeek;
	private SeekBar rrAftDiastolicSeek;
	private ImageButton rrAftSysPlusBtn;
	private ImageButton rrAftSysMinusBtn;
	private ImageButton rrAftDiaPlusBtn;
	private ImageButton rrAftDiaMinusBtn;

	private RadioGroup modiRanScGroup;

	private RadioButton modiRanSc0Radio;
	private RadioButton modiRanSc1Radio;
	private RadioButton modiRanSc2Radio;
	private RadioButton modiRanSc3Radio;
	private RadioButton modiRanSc4Radio;
	private RadioButton modiRanSc5Radio;
	private RadioButton modiRanSc6Radio;

	private ImageButton rrHelpBtn;
	private ImageButton modiRanScHelpBtn;
	private Button rrDoneBtn;
	private Button rrAftDoneBtn;
	
	private TextView rrComple;
	private TextView rrAftComple;
	private TextView modiRanScComple;

	public interface CommonLabDoneCallBack {
		public void onLabDone(int labId);
	}

	private CommonLabDoneCallBack labDoneCallBack;

	public CommonLabStatus(View root, Fragment context) {
		statusView = root;
		appContext = context;
		if (appContext instanceof FragmentLab) {
			labDoneCallBack = (CommonLabDoneCallBack) appContext;
		}
		initUIElements();
	}

	public void initUIElements() {
		statusMeasureInfoText = (TextView) statusView
				.findViewById(R.id.info_status_measurements);

		rr1View = (View) statusView.findViewById(R.id.view_rr_1);
		rr2View = (View) statusView.findViewById(R.id.view_rr_2);
		rrAfter1View = (View) statusView.findViewById(R.id.view_rr_after_1);
		rrAfter2View = (View) statusView.findViewById(R.id.view_rr_after_2);
		if (appContext instanceof FragmentLab) {
			rrSystolicText = (TextView) statusView
					.findViewById(R.id.info_rr_systolic);
			rrDiastolicText = (TextView) statusView
					.findViewById(R.id.info_rr_diastolic);

			CustomizeSeekbar cusSeek = (CustomizeSeekbar) statusView
					.findViewById(R.id.seek_bar_rr_systolic);
			rrSystolicSeek = cusSeek.getSeekBar();
			rrSysPlusBtn = cusSeek.getPlusBtn();
			rrSysMinusBtn = cusSeek.getMinusBtn();

			cusSeek = (CustomizeSeekbar) statusView
					.findViewById(R.id.seek_bar_rr_diastolic);
			rrDiastolicSeek = cusSeek.getSeekBar();
			rrDiaPlusBtn = cusSeek.getPlusBtn();
			rrDiaMinusBtn = cusSeek.getMinusBtn();

			rrAftSystolicText = (TextView) statusView
					.findViewById(R.id.info_rr_after_treat_systolic);
			rrAftDiastolicText = (TextView) statusView
					.findViewById(R.id.info_rr_after_treat_diastolic);

			cusSeek = (CustomizeSeekbar) statusView
					.findViewById(R.id.seek_bar_rr_after_treat_systolic);
			rrAftSystolicSeek = cusSeek.getSeekBar();
			rrAftSysPlusBtn = cusSeek.getPlusBtn();
			rrAftSysMinusBtn = cusSeek.getMinusBtn();

			cusSeek = (CustomizeSeekbar) statusView
					.findViewById(R.id.seek_bar_rr_after_treat_diastolic);
			rrAftDiastolicSeek = cusSeek.getSeekBar();
			rrAftDiaPlusBtn = cusSeek.getPlusBtn();
			rrAftDiaMinusBtn = cusSeek.getMinusBtn();

			rrHelpBtn = (ImageButton) statusView
					.findViewById(R.id.imgbtn_help_rr);
			rrDoneBtn = (Button) statusView.findViewById(R.id.imgbtn_done_rr);
			rrAftDoneBtn = (Button) statusView
					.findViewById(R.id.imgbtn_done_rr_after);
			rrDoneBtn.setVisibility(Button.VISIBLE);
			rrAftDoneBtn.setVisibility(Button.VISIBLE);
			rrDoneBtn.setEnabled(false);
			rrAftDoneBtn.setEnabled(false);

			rrSystolicSeek.setMax(250);
			rrDiastolicSeek.setMax(150);
			rrAftSystolicSeek.setMax(250);
			rrAftDiastolicSeek.setMax(150);

			rrSystolicSeek.setOnSeekBarChangeListener(this);
			rrDiastolicSeek.setOnSeekBarChangeListener(this);
			rrAftSystolicSeek.setOnSeekBarChangeListener(this);
			rrAftDiastolicSeek.setOnSeekBarChangeListener(this);
			rrSysPlusBtn.setOnClickListener(this);
			rrSysMinusBtn.setOnClickListener(this);
			rrDiaPlusBtn.setOnClickListener(this);
			rrDiaMinusBtn.setOnClickListener(this);
			rrAftSysPlusBtn.setOnClickListener(this);
			rrAftSysMinusBtn.setOnClickListener(this);
			rrAftDiaPlusBtn.setOnClickListener(this);
			rrAftDiaMinusBtn.setOnClickListener(this);

			rrHelpBtn.setOnClickListener(this);
			rrDoneBtn.setOnClickListener(this);
			rrAftDoneBtn.setOnClickListener(this);
		}

		modiRanScGroup = (RadioGroup) statusView
				.findViewById(R.id.radio_group_modified_rankin_scale);
		modiRanSc0Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_0);
		modiRanSc1Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_1);
		modiRanSc2Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_2);
		modiRanSc3Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_3);
		modiRanSc4Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_4);
		modiRanSc5Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_5);
		modiRanSc6Radio = (RadioButton) statusView
				.findViewById(R.id.radio_modified_rankin_scale_6);

		modiRanScHelpBtn = (ImageButton) statusView
				.findViewById(R.id.imgbtn_help_modified_rankin_scale);
		
		rrComple = (TextView) statusView.findViewById(R.id.comple_rr);
		rrAftComple = (TextView) statusView.findViewById(R.id.comple_rr_after);
		modiRanScComple = (TextView) statusView.findViewById(R.id.comple_modified_rankin_scale);

		modiRanScGroup.setOnCheckedChangeListener(this);
		modiRanSc0Radio.setOnCheckedChangeListener(this);
		modiRanSc1Radio.setOnCheckedChangeListener(this);
		modiRanSc2Radio.setOnCheckedChangeListener(this);
		modiRanSc3Radio.setOnCheckedChangeListener(this);
		modiRanSc4Radio.setOnCheckedChangeListener(this);
		modiRanSc5Radio.setOnCheckedChangeListener(this);
		modiRanSc6Radio.setOnCheckedChangeListener(this);

		modiRanScHelpBtn.setOnClickListener(this);
		modiRanSc5Radio.setOnClickListener(this);
		modiRanSc6Radio.setOnClickListener(this);
	}

	public void setUserSelection() {
		/** Status measurement */
		if (appContext instanceof FragmentLab) {
			int rrSys = AppViewModel.labDAO.getRRSystolic();
			rrSystolicText.setText(String.valueOf(rrSys));
			rrSystolicSeek.setProgress(rrSys);

			int rrDia = AppViewModel.labDAO.getRRDiastolic();
			rrDiastolicText.setText(String.valueOf(rrDia));
			rrDiastolicSeek.setProgress(rrDia);

			updateRRSeekbars(rrSys, rrDia);
			boolean treatDecis = AppViewModel.labDAO.getRRTreatDecision();
			if (treatDecis == true) {
				int rrAftSys = AppViewModel.labDAO.getRRAftSystolic();
				int rrAftDia = AppViewModel.labDAO.getRRAftDiastolic();
				updateRRAfterSeekbars(true, rrAftSys, rrAftDia);
			} else if (treatDecis == false) {
				updateRRAfterSeekbars(false, 0, 0);
			}

			modiRanScGroup.check(AppViewModel.labDAO.getModiRankinScaleId());
			
		}
		if (appContext instanceof FragmentThrombolysisDecision) {
			statusMeasureInfoText.setVisibility(TextView.GONE);
			rr1View.setVisibility(View.GONE);
			rr2View.setVisibility(View.GONE);
			rrAfter1View.setVisibility(View.GONE);
			rrAfter2View.setVisibility(View.GONE);

			int checkedId = AppViewModel.labDAO.getModiRankinScaleId();
			modiRanSc0Radio.setVisibility(RadioButton.GONE);
			modiRanSc1Radio.setVisibility(RadioButton.GONE);
			modiRanSc2Radio.setVisibility(RadioButton.GONE);
			modiRanSc3Radio.setVisibility(RadioButton.GONE);
			modiRanSc4Radio.setVisibility(RadioButton.GONE);
			modiRanSc5Radio.setVisibility(RadioButton.GONE);
			modiRanSc6Radio.setVisibility(RadioButton.GONE);
			switch (checkedId) {
			case R.id.radio_modified_rankin_scale_0:
				modiRanSc0Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_1:
				modiRanSc1Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_2:
				modiRanSc2Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_3:
				modiRanSc3Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_4:
				modiRanSc4Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_5:
				modiRanSc5Radio.setVisibility(RadioButton.VISIBLE);
				break;
			case R.id.radio_modified_rankin_scale_6:
				modiRanSc6Radio.setVisibility(RadioButton.VISIBLE);
				break;
			}
			modiRanScGroup.check(checkedId);
		}
		completeRR();
		completeRRAfter();
		completeModifiesRankinScale();
	}

	public void updateRRSeekbars(int rrSys, int rrDia) {
		if (rrSys < 0)
			rrSys = rrSystolicSeek.getProgress();
		if (rrDia < 0)
			rrDia = rrDiastolicSeek.getProgress();

		/** Set color for RR systolic */
		if (rrSys > 0 && rrSys < 185) {
			rrSystolicSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else if (rrSys >= 185) {
			rrSystolicSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.red));
		} else {
			rrSystolicSeek.setBackgroundColor(Color.TRANSPARENT);
		}

		/** Set color for RR diatolic */
		if (rrDia > 0 && rrDia < 110) {
			rrDiastolicSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.green));
		} else if (rrDia >= 110) {
			rrDiastolicSeek.setBackgroundColor(appContext.getResources()
					.getColor(R.color.red));
		} else {
			rrDiastolicSeek.setBackgroundColor(Color.TRANSPARENT);
		}
	}

	public void updateRRAfterSeekbars(boolean isVisible, int rrAftSys,
			int rrAftDia) {
		/** Set RR after */
		if (isVisible) {
			if (rrAftSys < 0)
				rrAftSys = rrAftSystolicSeek.getProgress();
			if (rrAftDia < 0)
				rrAftDia = rrAftDiastolicSeek.getProgress();

			rrAfter1View.setVisibility(View.VISIBLE);
			rrAfter2View.setVisibility(View.VISIBLE);

			rrAftSystolicText.setText(String.valueOf(rrAftSys));
			rrAftSystolicSeek.setProgress(rrAftSys);
			rrAftDiastolicText.setText(String.valueOf(rrAftDia));
			rrAftDiastolicSeek.setProgress(rrAftDia);

			if (rrAftSys > 0 && rrAftSys < 185) {
				rrAftSystolicSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			} else if (rrAftSys >= 185) {
				rrAftSystolicSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.red));
			} else {
				rrAftSystolicSeek.setBackgroundColor(Color.TRANSPARENT);
			}

			if (rrAftDia > 0 && rrAftDia < 110) {
				rrAftDiastolicSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			} else if (rrAftDia >= 110) {
				rrAftDiastolicSeek.setBackgroundColor(appContext.getResources()
						.getColor(R.color.red));
			} else {
				rrAftDiastolicSeek.setBackgroundColor(Color.TRANSPARENT);
			}
		} else {
			rrAftSystolicText.setText("");
			rrAftDiastolicText.setText("");
			rrAftSystolicSeek.setProgress(0);
			rrAftDiastolicSeek.setProgress(0);
			AppViewModel.labDAO.setRRAftSystolic(0);
			AppViewModel.labDAO.setRRAftDiastolic(0);
			rrAfter1View.setVisibility(View.GONE);
			rrAfter2View.setVisibility(View.GONE);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (seekBar.equals(rrSystolicSeek)) {
			rrSystolicText.setText(String.valueOf(progress));
		} else if (seekBar.equals(rrDiastolicSeek)) {
			rrDiastolicText.setText(String.valueOf(progress));
		} else if (seekBar.equals(rrAftSystolicSeek)) {
			rrAftSystolicText.setText(String.valueOf(progress));
		} else if (seekBar.equals(rrAftDiastolicSeek)) {
			rrAftDiastolicText.setText(String.valueOf(progress));
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton comBtn, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			switch (comBtn.getId()) {
			case R.id.radio_modified_rankin_scale_0:
			case R.id.radio_modified_rankin_scale_1:
			case R.id.radio_modified_rankin_scale_2:
				bgColor = appContext.getResources().getColor(R.color.green);
				break;
			case R.id.radio_modified_rankin_scale_3:
			case R.id.radio_modified_rankin_scale_4:
				bgColor = appContext.getResources().getColor(R.color.yellow);
				break;
			case R.id.radio_modified_rankin_scale_5:
			case R.id.radio_modified_rankin_scale_6:
				bgColor = appContext.getResources().getColor(R.color.red);
			}
		}
		comBtn.setBackgroundColor(bgColor);
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int progress = seekBar.getProgress();
		if (seekBar.equals(rrSystolicSeek)) {
			AppViewModel.labDAO.setRRSystolic(progress);
			updateRRSeekbars(progress, -1);
			rrDoneBtn.setEnabled(true);
		} else if (seekBar.equals(rrDiastolicSeek)) {
			AppViewModel.labDAO.setRRDiastolic(progress);
			updateRRSeekbars(-1, progress);
			rrDoneBtn.setEnabled(true);
		} else if (seekBar.equals(rrAftSystolicSeek)) {
			AppViewModel.labDAO.setRRAftSystolic(progress);
			updateRRAfterSeekbars(true, progress, -1);
			rrAftDoneBtn.setEnabled(true);
		} else if (seekBar.equals(rrAftDiastolicSeek)) {
			AppViewModel.labDAO.setRRAftDiastolic(progress);
			updateRRAfterSeekbars(true, -1, progress);
			rrAftDoneBtn.setEnabled(true);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton r = null;
		switch (group.getId()) {
		case R.id.radio_group_modified_rankin_scale:
			r = (RadioButton) statusView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.labDAO.setModiRankinScaleId(checkedId);
				AppViewModel.labDAO.setModiRankinScale(r.getText().toString());
			}
			completeModifiesRankinScale();
			break;
		}
	}

	@Override
	public void onClick(View v) {
		int progress = 0;
		if (v.equals(rrSysPlusBtn)) {
			progress = rrSystolicSeek.getProgress() + 1;
			if (progress <= rrSystolicSeek.getMax()) {
				rrSystolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRSystolic(progress);
				updateRRSeekbars(progress, -1);
				rrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrSysMinusBtn)) {
			progress = rrSystolicSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				rrSystolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRSystolic(progress);
				updateRRSeekbars(progress, -1);
				rrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrDiaPlusBtn)) {
			progress = rrDiastolicSeek.getProgress() + 1;
			if (progress <= rrDiastolicSeek.getMax()) {
				rrDiastolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRDiastolic(progress);
				updateRRSeekbars(-1, progress);
				rrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrDiaMinusBtn)) {
			progress = rrDiastolicSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				rrDiastolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRDiastolic(progress);
				updateRRSeekbars(-1, progress);
				rrDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrAftSysPlusBtn)) {
			progress = rrAftSystolicSeek.getProgress() + 1;
			if (progress <= rrAftSystolicSeek.getMax()) {
				rrAftSystolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRAftSystolic(progress);
				updateRRAfterSeekbars(true, progress, -1);
				rrAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrAftSysMinusBtn)) {
			progress = rrAftSystolicSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				rrAftSystolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRAftSystolic(progress);
				updateRRAfterSeekbars(true, progress, -1);
				rrAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrAftDiaPlusBtn)) {
			progress = rrAftDiastolicSeek.getProgress() + 1;
			if (progress <= rrAftDiastolicSeek.getMax()) {
				rrAftDiastolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRAftDiastolic(progress);
				updateRRAfterSeekbars(true, -1, progress);
				rrAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrAftDiaMinusBtn)) {
			progress = rrAftDiastolicSeek.getProgress();
			if (progress > 0) {
				progress -= 1;
				rrAftDiastolicSeek.setProgress(progress);
				AppViewModel.labDAO.setRRAftDiastolic(progress);
				updateRRAfterSeekbars(true, -1, progress);
				rrAftDoneBtn.setEnabled(true);
			}
		} else if (v.equals(rrDoneBtn)) {
			rrDoneBtn.setEnabled(false);
			labDoneCallBack.onLabDone(R.id.imgbtn_done_rr);
			completeRR();
		} else if (v.equals(rrAftDoneBtn)) {
			rrAftDoneBtn.setEnabled(false);
			int rrAftSys = rrAftSystolicSeek.getProgress();
			int rrAftDia = rrAftDiastolicSeek.getProgress();
			completeRRAfter();

			if (rrAftSys >= 185 || rrAftDia >= 110) {
				Intent interrIntent = new Intent(appContext.getActivity(),
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								appContext
										.getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						appContext.getString(R.string.info_interr_rr_after));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_rr_after);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				appContext.getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if(v.equals(modiRanSc5Radio)){
			Intent interrIntent = new Intent(appContext.getActivity(),
					DialogCommonInterrupt.class);
			interrIntent
					.putExtra(
							DialogCommonInterrupt.TAG_WARNING_TITLE,
							appContext
									.getString(R.string.interruption_warning_title));
			interrIntent.putExtra(
					DialogCommonInterrupt.TAG_WARNING_CONTENT,
					appContext.getString(R.string.info_interr_mrs_5));
			interrIntent.putExtra(
					DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_mRS_5);
//			interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		}else if(v.equals(modiRanSc6Radio)){
			Intent interrIntent = new Intent(appContext.getActivity(),
					DialogMRS6Confirm.class);
			interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(interrIntent);
		}else {
			Intent intent = new Intent(appContext.getActivity(),
					DialogHelpInfo.class);
			switch (v.getId()) {
			case R.id.imgbtn_help_rr:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						appContext.getString(R.string.help_title_rr));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						appContext.getString(R.string.help_info_rr));
				break;
			case R.id.imgbtn_help_modified_rankin_scale:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, appContext
						.getString(R.string.info_lab_modified_rankin_scale));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, appContext
						.getString(R.string.help_info_modify_rankin_scale));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(intent);
		}
	}
	public void completeRR() {
		int rrSys = AppViewModel.labDAO.getRRSystolic();
		int rrDia = AppViewModel.labDAO.getRRDiastolic();
		if (rrSys > 0 && rrDia >0) {
			rrComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			rrComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
	
	public void completeRRAfter() {
		int rrAftSys = AppViewModel.labDAO.getRRAftSystolic();
		int rrAftDia = AppViewModel.labDAO.getRRAftDiastolic();
		if (rrAftSys > 0 && rrAftDia >0) {
			rrAftComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			rrAftComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
	
	public void completeModifiesRankinScale() {
		int mRS = AppViewModel.labDAO.getModiRankinScaleId();
		if (mRS > 0) {
			modiRanScComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			modiRanScComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
	
}
