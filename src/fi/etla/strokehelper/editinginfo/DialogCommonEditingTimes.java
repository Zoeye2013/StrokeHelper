package fi.etla.strokehelper.editinginfo;

import java.util.Calendar;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class DialogCommonEditingTimes extends Activity implements
		OnClickListener, OnDateChangedListener {

	private Button saveBtn;
	private Button cancelBtn;

	private NumberPicker hourPicker;
	private NumberPicker minPicker;
	private DatePicker datePicker;

	private int timeType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_common_editing_times);
		timeType = getIntent().getIntExtra(DialogEditingTimes.EDITING_TITLE, 0);
		initElements();
		loadUI();
	}

	public void initElements() {
		hourPicker = (NumberPicker) findViewById(R.id.picker_hour);
		minPicker = (NumberPicker) findViewById(R.id.picker_min);
		datePicker = (DatePicker) findViewById(R.id.picker_date);
		datePicker.setCalendarViewShown(false);

		Calendar cal = Calendar.getInstance();
		hourPicker.setMinValue(0);
		hourPicker.setMaxValue(23);
		minPicker.setMinValue(0);
		minPicker.setMaxValue(59);
		datePicker.setMaxDate(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);
		long twoMonth = 60 * 24 * 60 * 60;
		datePicker.setMinDate((cal.getTimeInMillis() - twoMonth * 1000));

		saveBtn = (Button) findViewById(R.id.imgbtn_save);
		cancelBtn = (Button) findViewById(R.id.imgbtn_cancel);

		saveBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	public void loadUI() {
		Time time = new Time();
		int hour = 0;
		int min = 0;
		int day = 0;
		int month = 0;
		int year = 0;
		switch (timeType) {
		case R.string.title_time_onset:
			setTitle(R.string.title_time_onset);
			time.set(AppViewModel.timeStampsDAO.getSympOnsetTime());
			break;
		case R.string.title_time_estimate_arrival:
			setTitle(R.string.title_time_estimate_arrival);
			time.set(AppViewModel.timeStampsDAO.getEstiArriTime());
			break;
		case R.string.title_time_door:
			setTitle(R.string.title_time_door);
			time.set(AppViewModel.timeStampsDAO.getDoorTime());
			break;
		case R.string.title_time_ct_begin:
			setTitle(R.string.title_time_ct_begin);
			time.set(AppViewModel.timeStampsDAO.getCTBeginTime());
			break;
		case R.string.title_time_ct_end:
			setTitle(R.string.title_time_ct_end);
			time.set(AppViewModel.timeStampsDAO.getCTStatementTime());
			break;
		case R.string.title_time_thrombo_decision:
			setTitle(R.string.title_time_thrombo_decision);
			time.set(AppViewModel.timeStampsDAO.getDecisionTime());
			break;
		case R.string.title_time_bolus:
			setTitle(R.string.title_time_bolus);
			time.set(AppViewModel.timeStampsDAO.getBolusTime());
			break;
		case R.string.title_time_infusion_begin:
			setTitle(R.string.title_time_infusion_begin);
			time.set(AppViewModel.timeStampsDAO.getInfusionBeTime());
			break;
		case R.string.title_time_infusion_end:
			setTitle(R.string.title_time_infusion_end);
			time.set(AppViewModel.timeStampsDAO.getInfusionEndTime());
			break;
		case R.string.title_time_follow_up:
			setTitle(R.string.title_time_follow_up);
			time.set(AppViewModel.timeStampsDAO.getFollowEndTime());
			break;
		}

		if (time.toMillis(false) > 0) {
			hour = time.hour;
			min = time.minute;
			day = time.monthDay;
			month = time.month;
			year = time.year;

			hourPicker.setValue(hour);
			minPicker.setValue(min);
			datePicker.init(year, month, day, this);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_save:
			int hour = hourPicker.getValue();
			int min = minPicker.getValue();
			int day = datePicker.getDayOfMonth();
			int month = datePicker.getMonth();
			int year = datePicker.getYear();
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day, hour, min, 0);
			switch (timeType) {
			case R.string.title_time_onset:
				AppViewModel.timeStampsDAO.setSympOnsetHour(hour);
				AppViewModel.timeStampsDAO.setSympOnsetMin(min);
				AppViewModel.timeStampsDAO.setSympOnsetTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_estimate_arrival:
				AppViewModel.timeStampsDAO.setEstiArriHour(hour);
				AppViewModel.timeStampsDAO.setEstiArriMin(min);
				AppViewModel.timeStampsDAO.setEstiArriTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_door:
				AppViewModel.timeStampsDAO.setDoorTime(cal.getTimeInMillis());
				break;
			case R.string.title_time_ct_begin:
				AppViewModel.timeStampsDAO
						.setCTBeginTime(cal.getTimeInMillis());
				break;
			case R.string.title_time_ct_end:
				AppViewModel.timeStampsDAO.setCTStatementTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_thrombo_decision:
				AppViewModel.timeStampsDAO.setDecisionTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_bolus:
				AppViewModel.timeStampsDAO.setBolusTime(cal.getTimeInMillis());
				break;
			case R.string.title_time_infusion_begin:
				AppViewModel.timeStampsDAO.setInfusionBeTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_infusion_end:
				AppViewModel.timeStampsDAO.setInfusionEndTime(cal
						.getTimeInMillis());
				break;
			case R.string.title_time_follow_up:
				AppViewModel.timeStampsDAO.setFollowEndTime(cal
						.getTimeInMillis());
				break;
			}
			Toast toast = Toast.makeText(this, R.string.toast_data_saved,
					Toast.LENGTH_LONG);
			toast.show();
			setResult(DialogEditingTimes.EDITING_TIME_RESULT_YES);
			DialogCommonEditingTimes.this.finish();
			break;
		case R.id.imgbtn_cancel:
			DialogCommonEditingTimes.this.finish();
			break;
		}
	}

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
	}
}
