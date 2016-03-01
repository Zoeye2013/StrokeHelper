package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CommonRRFollowUp implements OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener, OnClickListener {
	private View rootView;
	private Fragment appContext;

	private RadioGroup rrFollowTypeGroup;
	private RadioButton rr185Radio;
	private RadioButton rr160Radio;
	private int rrUpLimit;
	private int rrLowerLimit;

	private LinearLayout rrsView;
	// private View rr0hView;
	private TextView rrSystolic0hText;
	private TextView rrDiastolic0hText;

	private RR rr15min;
	private RR rr30min;
	private RR rr1h;
	private RR rr2h;
	private RR rr24h;

	// private Chronometer bolusTimer;

	public CommonRRFollowUp(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		rrFollowTypeGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_rr_follow_type);
		rr185Radio = (RadioButton) rootView
				.findViewById(R.id.radio_rr_follow_type_185);
		rr160Radio = (RadioButton) rootView
				.findViewById(R.id.radio_rr_follow_type_160);
		rrFollowTypeGroup.setOnCheckedChangeListener(this);
		rr185Radio.setOnCheckedChangeListener(this);
		rr160Radio.setOnCheckedChangeListener(this);
		rr185Radio.setOnClickListener(this);
		rr160Radio.setOnClickListener(this);

		rrsView = (LinearLayout) rootView.findViewById(R.id.view_rrs);
		// rr0hView = (View) rootView.findViewById(R.id.view_rr_0h);
		rrSystolic0hText = (TextView) rootView
				.findViewById(R.id.info_rr_systolic_0h);
		rrDiastolic0hText = (TextView) rootView
				.findViewById(R.id.info_rr_diastolic_0h);

		rr15min = new RR(15);
		rr30min = new RR(30);
		rr1h = new RR(1);
		rr2h = new RR(2);
		rr24h = new RR(24);
	}

	public void setUserSelection() {
		int tempInt = AppViewModel.thrombolysisDAO.getRRFollowTypeId();
		if (tempInt > 0) {
			rrFollowTypeGroup.check(tempInt);
			rrsView.setVisibility(View.VISIBLE);
			setRRViews();
		} else {
			rrsView.setVisibility(View.GONE);
		}
	}

	public void setRRViews() {
		/** Set RR 0 min */
		int rrSystolic = 0;
		int rrDiastolic = 0;
		boolean rrTreatDeci = AppViewModel.labDAO.getRRTreatDecision();

		if (rrTreatDeci == true) {
			rrSystolic = AppViewModel.labDAO.getRRAftSystolic();
			rrDiastolic = AppViewModel.labDAO.getRRAftDiastolic();
		} else if (rrTreatDeci == false) {
			rrSystolic = AppViewModel.labDAO.getRRSystolic();
			rrDiastolic = AppViewModel.labDAO.getRRDiastolic();
		}

		if (rrSystolic > 0) {
			rrSystolic0hText.setText(String.valueOf(rrSystolic));
			if (rrSystolic < rrUpLimit)
				rrSystolic0hText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			else if (rrSystolic >= rrUpLimit)
				rrSystolic0hText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.red));
		} else{
			rrSystolic0hText.setBackgroundColor(appContext.getResources()
					.getColor(Color.TRANSPARENT));
			rrSystolic0hText.setText("");
		}

		if (rrDiastolic > 0) {
			rrDiastolic0hText.setText(String.valueOf(rrDiastolic));
			if (rrDiastolic < rrLowerLimit)
				rrDiastolic0hText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			else if (rrDiastolic >= rrLowerLimit)
				rrDiastolic0hText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.red));
		} else{
			rrDiastolic0hText.setBackgroundColor(appContext.getResources()
					.getColor(Color.TRANSPARENT));
			rrDiastolic0hText.setText("");
		}

		rr15min.setUserSelection();
		rr30min.setUserSelection();
		rr1h.setUserSelection();
		rr2h.setUserSelection();
		rr24h.setUserSelection();

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton r = (RadioButton) rootView.findViewById(checkedId);
		if (r != null) {
			AppViewModel.thrombolysisDAO.setRRFollowTypeId(checkedId);
			AppViewModel.thrombolysisDAO
					.setRRFollowType(r.getText().toString());
			switch (checkedId) {
			case R.id.radio_rr_follow_type_185:
				rrUpLimit = 185;
				rrLowerLimit = 110;
				break;
			case R.id.radio_rr_follow_type_160:
				rrUpLimit = 160;
				rrLowerLimit = 100;
				break;
			}
			rrsView.setVisibility(View.VISIBLE);
			setRRViews();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int color = Color.TRANSPARENT;
		if (isChecked) {
			color = appContext.getResources().getColor(R.color.bright_blue);
		}
		buttonView.setBackgroundColor(color);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.radio_rr_follow_type_185:
		// case R.id.radio_rr_follow_type_160:
		// // if (AppViewModel.thrombolysisDAO.getRRFollowTypeId() <= 0)
		// // FragmentThrombolysisFollowUp.startRRTimer();
		// break;
		}
	}

	private class RR implements OnClickListener, OnSeekBarChangeListener {
		private int rrType;
		private TextView rrSystolicText;
		private TextView rrDiastolicText;

		private CustomizeSeekbar rrSysSeek;
		private CustomizeSeekbar rrDiaSeek;
		private SeekBar rrSystolicSeek;
		private SeekBar rrDiastolicSeek;
		private ImageButton rrSysMinusBtn;
		private ImageButton rrSysPlusBtn;
		private ImageButton rrDiaMinusBtn;
		private ImageButton rrDiaPlusBtn;

		public RR(int type) {
			rrType = type;
			initUIElements();
		}

		public void initUIElements() {

			switch (rrType) {
			case 15:
				rrSystolicText = (TextView) rootView
						.findViewById(R.id.info_rr_systolic_15min);
				rrDiastolicText = (TextView) rootView
						.findViewById(R.id.info_rr_diastolic_15min);
				rrSysSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_systolic_15min);
				rrDiaSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_diastolic_15min);
				break;
			case 30:
				rrSystolicText = (TextView) rootView
						.findViewById(R.id.info_rr_systolic_30min);
				rrDiastolicText = (TextView) rootView
						.findViewById(R.id.info_rr_diastolic_30min);
				rrSysSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_systolic_30min);
				rrDiaSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_diastolic_30min);
				break;
			case 1:
				rrSystolicText = (TextView) rootView
						.findViewById(R.id.info_rr_systolic_1h);
				rrDiastolicText = (TextView) rootView
						.findViewById(R.id.info_rr_diastolic_1h);
				rrSysSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_systolic_1h);
				rrDiaSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_diastolic_1h);
				break;
			case 2:
				rrSystolicText = (TextView) rootView
						.findViewById(R.id.info_rr_systolic_2h);
				rrDiastolicText = (TextView) rootView
						.findViewById(R.id.info_rr_diastolic_2h);
				rrSysSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_systolic_2h);
				rrDiaSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_diastolic_2h);
				break;
			case 24:
				rrSystolicText = (TextView) rootView
						.findViewById(R.id.info_rr_systolic_24h);
				rrDiastolicText = (TextView) rootView
						.findViewById(R.id.info_rr_diastolic_24h);
				rrSysSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_systolic_24h);
				rrDiaSeek = (CustomizeSeekbar) rootView
						.findViewById(R.id.seek_bar_rr_diastolic_24h);
				break;
			}
			rrSystolicSeek = rrSysSeek.getSeekBar();
			rrSysMinusBtn = rrSysSeek.getMinusBtn();
			rrSysPlusBtn = rrSysSeek.getPlusBtn();
			rrDiastolicSeek = rrDiaSeek.getSeekBar();
			rrDiaMinusBtn = rrDiaSeek.getMinusBtn();
			rrDiaPlusBtn = rrDiaSeek.getPlusBtn();
			
			rrSystolicSeek.setMax(250);
			rrDiastolicSeek.setMax(150);

			rrSystolicSeek.setOnSeekBarChangeListener(this);
			rrDiastolicSeek.setOnSeekBarChangeListener(this);

			rrSysMinusBtn.setOnClickListener(this);
			rrSysPlusBtn.setOnClickListener(this);
			rrDiaMinusBtn.setOnClickListener(this);
			rrDiaPlusBtn.setOnClickListener(this);
		}

		public void setUserSelection() {
			int rrSystolic = 0;
			int rrDiastolic = 0;

			switch (rrType) {
			case 15:
				rrSystolic = AppViewModel.thrombolysisDAO.getRRSystolic15min();
				rrDiastolic = AppViewModel.thrombolysisDAO
						.getRRDiastolic15min();
				break;
			case 30:
				rrSystolic = AppViewModel.thrombolysisDAO.getRRSystolic30min();
				rrDiastolic = AppViewModel.thrombolysisDAO
						.getRRDiastolic30min();
				break;
			case 1:
				rrSystolic = AppViewModel.thrombolysisDAO.getRRSystolic1h();
				rrDiastolic = AppViewModel.thrombolysisDAO.getRRDiastolic1h();
				break;
			case 2:
				rrSystolic = AppViewModel.thrombolysisDAO.getRRSystolic2h();
				rrDiastolic = AppViewModel.thrombolysisDAO.getRRDiastolic2h();
				break;
			case 24:
				rrSystolic = AppViewModel.thrombolysisDAO.getRRSystolic24h();
				rrDiastolic = AppViewModel.thrombolysisDAO.getRRDiastolic24h();
				break;
			}

			if (rrSystolic > 0) {
				rrSystolicText.setText(String.valueOf(rrSystolic));
				rrSystolicSeek.setProgress(rrSystolic);
				if (rrSystolic < rrUpLimit)
					rrSystolicSeek.setBackgroundColor(appContext.getResources()
							.getColor(R.color.green));
				else if (rrSystolic >= rrUpLimit)
					rrSystolicSeek.setBackgroundColor(appContext.getResources()
							.getColor(R.color.red));
			} else {
				rrDiastolicText.setText("");
			}

			if (rrDiastolic > 0) {
				rrDiastolicText.setText(String.valueOf(rrDiastolic));
				rrDiastolicSeek.setProgress(rrDiastolic);
				if (rrDiastolic < rrLowerLimit)
					rrDiastolicSeek.setBackgroundColor(appContext
							.getResources().getColor(R.color.green));
				else if (rrSystolic >= rrLowerLimit)
					rrDiastolicSeek.setBackgroundColor(appContext
							.getResources().getColor(R.color.red));
			} else {
				rrDiastolicText.setText("");
			}
		}

		public void setRRSystolicText(int rrS) {
			rrSystolicText.setText(String.valueOf(rrS));
		}

		public void setRRSystolic(int rrS) {
			switch (rrType) {
			case 15:
				AppViewModel.thrombolysisDAO.setRRSystolic15min(rrS);
				break;
			case 30:
				AppViewModel.thrombolysisDAO.setRRSystolic30min(rrS);
				break;
			case 1:
				AppViewModel.thrombolysisDAO.setRRSystolic1h(rrS);
				break;
			case 2:
				AppViewModel.thrombolysisDAO.setRRSystolic2h(rrS);
				break;
			case 24:
				AppViewModel.thrombolysisDAO.setRRSystolic24h(rrS);
				break;
			}
		}

		public void setRRDiastolicText(int rrD) {
			rrDiastolicText.setText(String.valueOf(rrD));
		}

		public void setRRDiastolic(int rrD) {
			switch (rrType) {
			case 15:
				AppViewModel.thrombolysisDAO.setRRDiastolic15min(rrD);
				break;
			case 30:
				AppViewModel.thrombolysisDAO.setRRDiastolic30min(rrD);
				break;
			case 1:
				AppViewModel.thrombolysisDAO.setRRDiastolic1h(rrD);
				break;
			case 2:
				AppViewModel.thrombolysisDAO.setRRDiastolic2h(rrD);
				break;
			case 24:
				AppViewModel.thrombolysisDAO.setRRDiastolic24h(rrD);
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
					setRRSystolic(progress);
					setUserSelection();
				}
			} else if (v.equals(rrSysMinusBtn)) {
				progress = rrSystolicSeek.getProgress();
				if (progress > 0) {
					progress -= 1;
					rrSystolicSeek.setProgress(progress);
					setRRSystolic(progress);
					setUserSelection();
				}
			} else if (v.equals(rrDiaPlusBtn)) {
				progress = rrDiastolicSeek.getProgress() + 1;
				if (progress <= rrDiastolicSeek.getMax()) {
					rrDiastolicSeek.setProgress(progress);
					setRRDiastolic(progress);
					setUserSelection();
				}
			} else if (v.equals(rrDiaMinusBtn)) {
				progress = rrDiastolicSeek.getProgress();
				if (progress > 0) {
					progress -= 1;
					rrDiastolicSeek.setProgress(progress);
					setRRDiastolic(progress);
					setUserSelection();
				}
			}
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if(seekBar.equals(rrSystolicSeek)){
				setRRSystolicText(progress);
			}else if(seekBar.equals(rrDiastolicSeek)){
				setRRDiastolicText(progress);
			}
//			switch (seekBar.getId()) {
//			case R.id.seek_bar_rr_systolic_15min:
//				rr15min.setRRSystolicText(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_15min:
//				rr15min.setRRDiastolicText(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_30min:
//				rr30min.setRRSystolicText(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_30min:
//				rr30min.setRRDiastolicText(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_1h:
//				rr1h.setRRSystolicText(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_1h:
//				rr1h.setRRDiastolicText(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_2h:
//				rr2h.setRRSystolicText(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_2h:
//				rr2h.setRRDiastolicText(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_24h:
//				rr24h.setRRSystolicText(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_24h:
//				rr24h.setRRDiastolicText(progress);
//				break;
//			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			int progress = seekBar.getProgress();
			if(seekBar.equals(rrSystolicSeek)){
				setRRSystolic(progress);
				if (rrUpLimit > 0) {
					if (progress < rrUpLimit)
						seekBar.setBackgroundColor(appContext.getResources()
								.getColor(R.color.green));
					else if (progress >= rrUpLimit)
						seekBar.setBackgroundColor(appContext.getResources()
								.getColor(R.color.red));
				}
			}else if(seekBar.equals(rrDiastolicSeek)){
				setRRDiastolic(progress);
				if (rrLowerLimit > 0) {
					if (progress < rrLowerLimit)
						seekBar.setBackgroundColor(appContext.getResources()
								.getColor(R.color.green));
					else if (progress >= rrLowerLimit)
						seekBar.setBackgroundColor(appContext.getResources()
								.getColor(R.color.red));
				}
			}
//			switch (seekBar.getId()) {
//			case R.id.seek_bar_rr_systolic_15min:
//				AppViewModel.thrombolysisDAO.setRRSystolic15min(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_15min:
//				AppViewModel.thrombolysisDAO.setRRDiastolic15min(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_30min:
//				AppViewModel.thrombolysisDAO.setRRSystolic30min(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_30min:
//				AppViewModel.thrombolysisDAO.setRRDiastolic30min(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_1h:
//				AppViewModel.thrombolysisDAO.setRRSystolic1h(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_1h:
//				AppViewModel.thrombolysisDAO.setRRDiastolic1h(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_2h:
//				AppViewModel.thrombolysisDAO.setRRSystolic2h(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_2h:
//				AppViewModel.thrombolysisDAO.setRRDiastolic2h(progress);
//				break;
//			case R.id.seek_bar_rr_systolic_24h:
//				AppViewModel.thrombolysisDAO.setRRSystolic24h(progress);
//				break;
//			case R.id.seek_bar_rr_diastolic_24h:
//				AppViewModel.thrombolysisDAO.setRRDiastolic2h(progress);
//				break;
//			}

//			switch (seekBar.getId()) {
//			case R.id.seek_bar_rr_systolic_15min:
//			case R.id.seek_bar_rr_systolic_30min:
//			case R.id.seek_bar_rr_systolic_1h:
//			case R.id.seek_bar_rr_systolic_2h:
//			case R.id.seek_bar_rr_systolic_24h:
//				if (rrUpLimit > 0) {
//					if (progress < rrUpLimit)
//						seekBar.setBackgroundColor(appContext.getResources()
//								.getColor(R.color.green));
//					else if (progress >= rrUpLimit)
//						seekBar.setBackgroundColor(appContext.getResources()
//								.getColor(R.color.red));
//				}
//				break;
//			case R.id.seek_bar_rr_diastolic_15min:
//			case R.id.seek_bar_rr_diastolic_30min:
//			case R.id.seek_bar_rr_diastolic_1h:
//			case R.id.seek_bar_rr_diastolic_2h:
//			case R.id.seek_bar_rr_diastolic_24h:
//				if (rrLowerLimit > 0) {
//					if (progress < rrLowerLimit)
//						seekBar.setBackgroundColor(appContext.getResources()
//								.getColor(R.color.green));
//					else if (progress >= rrLowerLimit)
//						seekBar.setBackgroundColor(appContext.getResources()
//								.getColor(R.color.red));
//				}
//
//				break;
//			}
		}
	}
}
