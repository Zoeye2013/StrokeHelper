package fi.etla.strokehelper.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;

public class CommonEstimateArrival implements OnTimeChangedListener,
		OnDateChangedListener, OnCheckedChangeListener {

	private View timePickerView;
	private View timeInfoView;

	// private TimePicker timePicker;
	private NumberPicker hourPicker;
	private NumberPicker minPicker;
	private DatePicker dayPicker;

	private TextView timeInfoText;

	private ImageButton editTimeBtn;

	private ToggleButton toggleBtn;

	private View rootView;
	private Fragment appContext;

	private TextView estArriTimeComple;

	public CommonEstimateArrival(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** Higher level layout */
		timePickerView = rootView
				.findViewById(R.id.part_estimate_arrival_time_picker);
		timeInfoView = rootView
				.findViewById(R.id.part_estimate_arrival_time_info);
		estArriTimeComple = (TextView) rootView
				.findViewById(R.id.comple_estimate_arrival_time);

		/** Pickers */
		hourPicker = (NumberPicker) rootView
				.findViewById(R.id.picker_estimate_arrival_time_hour);
		minPicker = (NumberPicker) rootView
				.findViewById(R.id.picker_estimate_arrival_time_min);
		dayPicker = (DatePicker) rootView
				.findViewById(R.id.picker_estimate_arrival_time_day);
		dayPicker.setCalendarViewShown(false);

		Calendar cal = Calendar.getInstance();
		hourPicker.setMinValue(0);
		hourPicker.setMaxValue(23);
		minPicker.setMinValue(0);
		minPicker.setMaxValue(59);
		dayPicker.setMaxDate(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);
		long twoMonth = 60 * 24 * 60 * 60;
		dayPicker.setMinDate((cal.getTimeInMillis() - twoMonth * 1000));

		hourPicker.setValue(cal.get(Calendar.HOUR_OF_DAY));
		minPicker.setValue(cal.get(Calendar.MINUTE));

		/** Info */
		timeInfoText = (TextView) rootView
				.findViewById(R.id.info_estimate_arrival_time);

		/** Buttons */
		editTimeBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_edit_estimate_arrival_time);
		toggleBtn = (ToggleButton) rootView
				.findViewById(R.id.togbtn_lock_estimate);
		toggleBtn.setOnCheckedChangeListener(this);

	}

	public void initUIStatus() {
		toggleBtn.setChecked(AppViewModel.timeStampsDAO.getEstiArriUnlock());
		Calendar cal = Calendar.getInstance();
		dayPicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), this);
		hourPicker.setValue(cal.get(Calendar.HOUR_OF_DAY));
		minPicker.setValue(cal.get(Calendar.MINUTE));
		completeEstiArriTime();
	}

	public void showEstimateArrivalView() {
		rootView.setVisibility(View.VISIBLE);
	}

	public void hideEstimateArrivalView() {
		rootView.setVisibility(View.GONE);
	}

	public void getEstiTimePickerData() {
		int hour = hourPicker.getValue();
		int min = minPicker.getValue();
		int day = dayPicker.getDayOfMonth();
		int month = dayPicker.getMonth();
		int year = dayPicker.getYear();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, 0);

		AppViewModel.timeStampsDAO.setEstiArriHour(hour);
		AppViewModel.timeStampsDAO.setEstiArriMin(min);
		AppViewModel.timeStampsDAO.setEstiArriTime(cal.getTimeInMillis());
		completeEstiArriTime();
	}

	public void setEstiArriTime() {
		completeEstiArriTime();
		hourPicker.setValue(AppViewModel.timeStampsDAO.getEstiArriHour());
		minPicker.setValue(AppViewModel.timeStampsDAO.getEstiArriMin());
		Time time = new Time();
		time.set(AppViewModel.timeStampsDAO.getEstiArriTime());
		dayPicker.init(time.year, time.month, time.monthDay, this);
		timeInfoText.setText(AppViewModel
				.timeToString(AppViewModel.timeStampsDAO.getEstiArriTime()));
		toggleBtn.setChecked(AppViewModel.timeStampsDAO.getEstiArriUnlock());
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

	}

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView.equals(toggleBtn)) {
			AppViewModel.timeStampsDAO.setEstiArriUnlock(isChecked);
			if (isChecked) {
				timePickerView.setVisibility(View.VISIBLE);
				timeInfoView.setVisibility(View.GONE);
			} else if (!isChecked) {
				getEstiTimePickerData();
				timeInfoText.setText(AppViewModel
						.timeToString(AppViewModel.timeStampsDAO
								.getEstiArriTime()));
				timePickerView.setVisibility(View.GONE);
				timeInfoView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void completeEstiArriTime() {
		long estiArri = AppViewModel.timeStampsDAO.getEstiArriTime();
		if (estiArri > 0) {
			estArriTimeComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			estArriTimeComple.setBackgroundColor(appContext.getResources()
					.getColor(R.color.grey));
		}
	}
}
