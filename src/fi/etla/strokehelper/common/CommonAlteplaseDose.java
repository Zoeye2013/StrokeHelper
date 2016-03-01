package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CommonAlteplaseDose implements OnSeekBarChangeListener,
		OnClickListener {
	private View alteplaseView;
	private Fragment appContext;
	private View bolusView1;
	private View bolusView2;
	private View infusionView1;
	private View infusionView2;
	private View infusionView3;

	private TextView weightText;
	private SeekBar weightSeekBar;
	private ImageButton weightPlusBtn;
	private ImageButton weightMinusBtn;
	private TextView totalDoseText;

	private TextView bolusAmountText;
	private Button bolusBtn;
	private TextView bolusTimeText;

	private TextView infusionAmountText;
	private Button infusionBeginBtn;
	private TextView infusionBeginText;
	private Button infusionEndBtn;
	private TextView infusionEndText;

	public CommonAlteplaseDose(View root, Fragment context) {
		alteplaseView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		bolusView1 = (View) alteplaseView.findViewById(R.id.view_bolus_1);
		bolusView2 = (View) alteplaseView.findViewById(R.id.view_bolus_2);
		infusionView1 = (View) alteplaseView.findViewById(R.id.view_infusion_1);
		infusionView2 = (View) alteplaseView.findViewById(R.id.view_infusion_2);
		infusionView3 = (View) alteplaseView.findViewById(R.id.view_infusion_3);

		weightText = (TextView) alteplaseView
				.findViewById(R.id.info_patient_weight);
		CustomizeSeekbar cusSeek = (CustomizeSeekbar) alteplaseView
				.findViewById(R.id.seek_bar_patient_weight);
		weightSeekBar = cusSeek.getSeekBar();
		weightPlusBtn = cusSeek.getPlusBtn();
		weightMinusBtn = cusSeek.getMinusBtn();

		totalDoseText = (TextView) alteplaseView
				.findViewById(R.id.info_total_actilyse_dose);

		bolusAmountText = (TextView) alteplaseView
				.findViewById(R.id.info_thrombolysis_bolus);
		bolusBtn = (Button) alteplaseView.findViewById(R.id.imgbtn_bolus_begin);
		bolusTimeText = (TextView) alteplaseView
				.findViewById(R.id.info_bolus_begin);

		infusionAmountText = (TextView) alteplaseView
				.findViewById(R.id.info_thrombolysis_infusion);
		infusionBeginBtn = (Button) alteplaseView
				.findViewById(R.id.imgbtn_infusion_begin);
		infusionBeginText = (TextView) alteplaseView
				.findViewById(R.id.info_infusion_begin);
		infusionEndBtn = (Button) alteplaseView
				.findViewById(R.id.imgbtn_infusion_end);
		infusionEndText = (TextView) alteplaseView
				.findViewById(R.id.info_infusion_end);

		weightSeekBar.setMax(2000);
		weightSeekBar.setLayoutParams(new LayoutParams(400, 45));

		weightSeekBar.setOnSeekBarChangeListener(this);
		bolusBtn.setOnClickListener(this);
		infusionBeginBtn.setOnClickListener(this);
		infusionEndBtn.setOnClickListener(this);

		weightPlusBtn.setOnClickListener(this);
		weightMinusBtn.setOnClickListener(this);
	}

	public void setUserSelection() {
		/** Status measurement */
		if (appContext instanceof FragmentThrombolysisFollowUp) {
			double weight = AppViewModel.patientDAO.getWeight();
			if (weight > 0) {
				weightText.setText(String.valueOf(weight));
				weightSeekBar.setProgress((int) (weight * 10));
				double dose = AppViewModel.thrombolysisDAO.getTotalDose();
				totalDoseText.setText("(" + String.valueOf(dose) + " ml)");
				totalDoseText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.purple));

				setBolus();
			} else {
				weightText.setText("");
				totalDoseText.setText("");
				totalDoseText.setBackgroundColor(Color.TRANSPARENT);
				bolusView1.setVisibility(View.GONE);
				bolusView2.setVisibility(View.GONE);
				infusionView1.setVisibility(View.GONE);
				infusionView2.setVisibility(View.GONE);
				infusionView3.setVisibility(View.GONE);
			}
		}
	}

	public void setBolus() {

		if (AppViewModel.patientDAO.getWeight() > 0) {
			bolusView1.setVisibility(View.VISIBLE);
			bolusView2.setVisibility(View.VISIBLE);
			double dose = AppViewModel.thrombolysisDAO.getBolusDose();
			if (dose > 0) {
				bolusAmountText.setText(String.valueOf(dose) + " ml");
				bolusAmountText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.purple));
			} else {
				bolusAmountText.setText("");
				bolusAmountText.setBackgroundColor(Color.TRANSPARENT);
			}

			long time = AppViewModel.timeStampsDAO.getBolusTime();
			if (time > 0) {
				bolusBtn.setEnabled(false);
				bolusTimeText.setText(appContext
						.getString(R.string.info_thrombolysis_bolus_time)
						+ " "
						+ AppViewModel.timeToString(time));
				bolusTimeText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.bright_blue));
				setInfusionBegin();
			} else {
				bolusBtn.setEnabled(true);
				bolusTimeText.setText("");
				bolusTimeText.setBackgroundColor(Color.TRANSPARENT);
				infusionView1.setVisibility(View.GONE);
				infusionView2.setVisibility(View.GONE);
				infusionView3.setVisibility(View.GONE);
			}
		}
	}

	public void setInfusionBegin() {

		if (AppViewModel.timeStampsDAO.getBolusTime() > 0) {
			infusionView1.setVisibility(View.VISIBLE);
			infusionView2.setVisibility(View.VISIBLE);

			double dose = AppViewModel.thrombolysisDAO.getInfusionDose();
			if (dose > 0) {
				infusionAmountText.setText(String.valueOf(dose) + " ml");
				infusionAmountText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.purple));
			} else {
				infusionAmountText.setText("");
				infusionAmountText.setBackgroundColor(Color.TRANSPARENT);
			}

			long time = AppViewModel.timeStampsDAO.getInfusionBeTime();
			if (time > 0) {
				infusionBeginBtn.setEnabled(false);
				infusionBeginText
						.setText(appContext
								.getString(R.string.info_thrombolysis_infusion_time_begin)
								+ " " + AppViewModel.timeToString(time));
				infusionBeginText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.bright_blue));
				setInfusionEnd();
			} else {
				infusionView3.setVisibility(View.GONE);
				infusionBeginBtn.setEnabled(true);
				infusionBeginText.setText("");
				infusionBeginText.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}

	public void setInfusionEnd() {

		if (AppViewModel.timeStampsDAO.getInfusionBeTime() > 0) {
			infusionView3.setVisibility(View.VISIBLE);
			long time = AppViewModel.timeStampsDAO.getInfusionEndTime();
			if (time > 0) {
				infusionEndBtn.setEnabled(false);
				infusionEndText
						.setText(appContext
								.getString(R.string.info_thrombolysis_infusion_time_end)
								+ " " + AppViewModel.timeToString(time));
				infusionEndText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.bright_blue));
			} else {
				infusionEndBtn.setEnabled(true);
				infusionEndText.setText("");
				infusionEndText.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}

	public void calActilyse(double weight) {

		double totalDose = 0;
		if (weight > 0 && weight <= 100) {
			totalDose = (double) (Math.round(weight * 0.9 * 100)) / 100;
		}else if(weight > 100){
			totalDose = 90;
		}
		AppViewModel.thrombolysisDAO.setTotalDose(totalDose);
		double bolusDose = (double) (Math.round(totalDose * 0.1 * 100)) / 100;
		AppViewModel.thrombolysisDAO.setBolusDose(bolusDose);
		double infusionDose = (double) (Math
				.round((totalDose - bolusDose) * 100)) / 100;
		AppViewModel.thrombolysisDAO.setInfusionDose(infusionDose);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (seekBar.equals(weightSeekBar)) {
			double weight = (double) progress / 10;
			if (weight > 0) {
				weightText.setText(String.valueOf(weight) + " kg");
			} else
				weightText.setText("");
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int progress = seekBar.getProgress();
		double weight = (double) progress / 10;
		if (seekBar.equals(weightSeekBar)) {
			AppViewModel.patientDAO.setWeight(weight);
			calActilyse(weight);
			setUserSelection();
		}
	}

	@Override
	public void onClick(View v) {
		double weight = 0;
		int progress = 0;
		if (v.equals(weightPlusBtn)) {
			progress = weightSeekBar.getProgress() + 1;
			if (progress <= weightSeekBar.getMax()) {
				weightSeekBar.setProgress(progress);
				weight = (double) progress / 10;
				AppViewModel.patientDAO.setWeight(weight);
				calActilyse(weight);
				setUserSelection();
			}
		} else if (v.equals(weightMinusBtn)) {
			progress = weightSeekBar.getProgress();
			if (progress > 0) {
				progress -= 1;
				weightSeekBar.setProgress(progress);
				weight = (double) progress / 10;
				AppViewModel.patientDAO.setWeight(weight);
				calActilyse(weight);
				setUserSelection();
			}
		} else {
			Time time = new Time();
			time.setToNow();
			long milli = time.toMillis(false);
			switch (v.getId()) {
			case R.id.imgbtn_bolus_begin:
				AppViewModel.timeStampsDAO.setBolusTime(milli);
				setBolus();
//				FragmentThrombolysisFollowUp.startRRTimer();
				if (appContext instanceof FragmentThrombolysisFollowUp) {
					((FragmentThrombolysisFollowUp) appContext)
							.getTreatProgressCallBack().updateProgressBar();
				}
				break;
			case R.id.imgbtn_infusion_begin:
				AppViewModel.timeStampsDAO.setInfusionBeTime(milli);
				setInfusionBegin();
				if (appContext instanceof FragmentThrombolysisFollowUp) {
					((FragmentThrombolysisFollowUp) appContext)
							.getTreatProgressCallBack().updateProgressBar();
				}
				break;
			case R.id.imgbtn_infusion_end:
				AppViewModel.timeStampsDAO.setInfusionEndTime(milli);
				setInfusionEnd();
				if (appContext instanceof FragmentThrombolysisFollowUp) {
					((FragmentThrombolysisFollowUp) appContext)
							.getTreatProgressCallBack().updateProgressBar();
				}
				break;
			}
		}
	}
}
