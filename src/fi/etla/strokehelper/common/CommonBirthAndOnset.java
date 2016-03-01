package fi.etla.strokehelper.common;

import java.lang.reflect.Field;
import java.util.Calendar;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.SymptomsTableDAO;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.patient.FragmentPatient;
import fi.etla.strokehelper.process.FragmentSymptoms;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import android.R.interpolator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.ToggleButton;

public class CommonBirthAndOnset implements OnCheckedChangeListener,
		OnDateChangedListener, OnTimeChangedListener, OnClickListener {

	private View birDayView;
	private View onsetTimeView;

	private View birDayPartPickerView;
	private View onsetTimePartPickerView;
	private View birDayPartInfoView;
	private View onsetTimePartInfoView;

	private DatePicker birDayPicker;
	private NumberPicker onsetHourPicker;
	private NumberPicker onsetMinPicker;
	// private NumberPicker onsetDayPicker;
	private DatePicker onsetDayPicker;

	private TextView birDayInfoText;
	private TextView onsetTimeInfoText;
	private TextView onsetTimeTypeInfoText;
	private TextView onsetTimeTypePickerText;

	private RadioGroup onsetRadioGroup;
	private RadioButton knowOnsetRadio;
	private RadioButton unknownRadio;
	private RadioButton lastSympFreeRadio;
	private RadioButton wokeUpSympRadio;

	private ImageButton editBirDayBtn;
	private ImageButton editOnsetTBtn;

	private ToggleButton birDayTogBtn;
	private ToggleButton onsetTogBtn;

	private View rootView;
	private Fragment appContext;

	private TextView birthdayComple;
	private TextView onsetComple;

	public interface UpdateTimerCallBack {
		/** isStop = true, to stop the timer and hide */
		public void updateTimer(boolean isStop);
	}

	public CommonBirthAndOnset(View root, Fragment context) {
		rootView = root;
		appContext = context;

		initUIElements();
	}

	public void initUIElements() {
		/** Higher level layout */
		birDayView = rootView.findViewById(R.id.part_birthday);
		onsetTimeView = rootView.findViewById(R.id.part_onset_time);

		onsetRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_onset);

		birDayPartPickerView = rootView.findViewById(R.id.part_birthday_picker);
		onsetTimePartPickerView = rootView
				.findViewById(R.id.part_onset_time_picker_b);
		birDayPartInfoView = rootView.findViewById(R.id.part_birthday_info);
		onsetTimePartInfoView = rootView
				.findViewById(R.id.part_onset_time_info);

		/** Pickers */
		birDayPicker = (DatePicker) rootView.findViewById(R.id.picker_birthday);
		onsetHourPicker = (NumberPicker) rootView
				.findViewById(R.id.time_picker_onset_time_hour);
		onsetMinPicker = (NumberPicker) rootView
				.findViewById(R.id.time_picker_onset_time_min);
		onsetDayPicker = (DatePicker) rootView
				.findViewById(R.id.time_picker_onset_time_day);
		birDayPicker.setCalendarViewShown(false);

		Calendar cal = Calendar.getInstance();
		onsetDayPicker.setCalendarViewShown(false);

		onsetHourPicker.setMinValue(0);
		onsetHourPicker.setMaxValue(23);
		onsetMinPicker.setMinValue(0);
		onsetMinPicker.setMaxValue(59);
		onsetHourPicker.setValue(cal.get(Calendar.HOUR_OF_DAY));
		onsetMinPicker.setValue(cal.get(Calendar.MINUTE));
		onsetDayPicker.setMaxDate(cal.getTimeInMillis());
		long twoMonth = 60 * 24 * 60 * 60;
		onsetDayPicker.setMinDate((cal.getTimeInMillis() - twoMonth * 1000));

		/** Info */
		birDayInfoText = (TextView) rootView.findViewById(R.id.info_birthday);
		onsetTimeInfoText = (TextView) rootView
				.findViewById(R.id.info_onset_time);
		onsetTimeTypeInfoText = (TextView) rootView
				.findViewById(R.id.info_onset_time_type);
		onsetTimeTypePickerText = (TextView) rootView
				.findViewById(R.id.info_picker_type);

		birthdayComple = (TextView) rootView.findViewById(R.id.comple_birthday);
		onsetComple = (TextView) rootView.findViewById(R.id.comple_onset_time);

		/** Buttons */
		editBirDayBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_edit_birthday);
		editOnsetTBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_edit_onset_time);
		birDayTogBtn = (ToggleButton) rootView
				.findViewById(R.id.togbtn_lock_birthday);
		onsetTogBtn = (ToggleButton) rootView
				.findViewById(R.id.togbtn_lock_onset);

		/** Onset Radios */
		knowOnsetRadio = (RadioButton) rootView
				.findViewById(R.id.radio_known_onset);
		unknownRadio = (RadioButton) rootView.findViewById(R.id.radio_unknown);
		wokeUpSympRadio = (RadioButton) rootView
				.findViewById(R.id.radio_woke_up_symptom);
		lastSympFreeRadio = (RadioButton) rootView
				.findViewById(R.id.radio_last_symptom_free);

		knowOnsetRadio.setOnCheckedChangeListener(this);
		unknownRadio.setOnCheckedChangeListener(this);
		wokeUpSympRadio.setOnCheckedChangeListener(this);
		lastSympFreeRadio.setOnCheckedChangeListener(this);
		knowOnsetRadio.setOnClickListener(this);
		unknownRadio.setOnClickListener(this);
		wokeUpSympRadio.setOnClickListener(this);
		lastSympFreeRadio.setOnClickListener(this);
		birDayTogBtn.setOnCheckedChangeListener(this);
		onsetTogBtn.setOnCheckedChangeListener(this);
		

		unknownRadio.setOnClickListener(this);
	}

	public void initUIStatus() {
		completeBirthday();
		completeOnsetTime();
		birDayTogBtn.setChecked(AppViewModel.patientDAO.getBirDayUnlock());
		birDayPicker.init(1960, 0, 1, this);
		knowOnsetRadio.setChecked(false);
		onsetTogBtn.setChecked(AppViewModel.timeStampsDAO.getOnsetUnlock());
		unknownRadio.setChecked(false);
		wokeUpSympRadio.setChecked(false);
		lastSympFreeRadio.setChecked(false);
		onsetTimeTypePickerText.setText(R.string.info_time_onset);
		onsetTimeInfoText.setText("");
		onsetTimeTypeInfoText.setText(R.string.info_time_onset);

		Calendar cal = Calendar.getInstance();
		onsetDayPicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), this);
		onsetHourPicker.setValue(cal.get(Calendar.HOUR_OF_DAY));
		onsetMinPicker.setValue(cal.get(Calendar.MINUTE));
		onsetTimePartPickerView.setVisibility(View.GONE);
		onsetTogBtn.setVisibility(ToggleButton.GONE);

	}

	public void showBirthDayOnly() {
		onsetTimeView.setVisibility(View.GONE);
		birDayView.setVisibility(View.VISIBLE);
	}

	public void showOnsetTimeOnly() {
		onsetTimeView.setVisibility(View.VISIBLE);
		birDayView.setVisibility(View.GONE);
	}

	public void showBothBirDayAndOnset() {
		onsetTimeView.setVisibility(View.VISIBLE);
		birDayView.setVisibility(View.VISIBLE);
	}

	public void getOnsetTimePickerData() {
		if (AppViewModel.timeStampsDAO.getSympOnsetQuaId() > 0) {
			int hour = onsetHourPicker.getValue();
			int min = onsetMinPicker.getValue();
			int day = onsetDayPicker.getDayOfMonth();
			int month = onsetDayPicker.getMonth();
			int year = onsetDayPicker.getYear();

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, min);
			cal.set(Calendar.SECOND, 0);

			AppViewModel.timeStampsDAO.setSympOnsetHour(hour);
			AppViewModel.timeStampsDAO.setSympOnsetMin(min);
			AppViewModel.timeStampsDAO.setSympOnsetTime(cal.getTimeInMillis());
		}
		completeOnsetTime();
	}

	public void getBirthDatePickerData() {
		AppViewModel.patientDAO.setBirthDay(birDayPicker.getDayOfMonth());
		AppViewModel.patientDAO.setBirthMonth(birDayPicker.getMonth());
		AppViewModel.patientDAO.setBirthYear(birDayPicker.getYear());

		String birthDate = birDayPicker.getDayOfMonth() + "."
				+ (birDayPicker.getMonth() + 1) + "." + birDayPicker.getYear();
		AppViewModel.patientDAO.setDateOfBirth(birthDate);

		Calendar dob = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		dob.set(birDayPicker.getYear(), birDayPicker.getMonth(),
				birDayPicker.getDayOfMonth());

		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		AppViewModel.patientDAO.setPatientAge(age);
		completeBirthday();
	}

	public void setDateOfBirth() {
		completeBirthday();
		birDayPicker.init(AppViewModel.patientDAO.getBirthYear(),
				AppViewModel.patientDAO.getBirthMonth(),
				AppViewModel.patientDAO.getBirthDay(), this);
		birDayInfoText.setText(AppViewModel.patientDAO.getDateOfBirth());
		birDayTogBtn.setChecked(AppViewModel.patientDAO.getBirDayUnlock());
	}

	public void setOnsetTime() {
		onsetRadioGroup.clearCheck();
		if (appContext instanceof FragmentThrombolysisDecision) {
			switch (AppViewModel.timeStampsDAO.getSympOnsetQuaId()) {
			case R.id.radio_unknown:
				unknownRadio.setChecked(true);
				onsetTimePartInfoView.setVisibility(View.GONE);
				break;
			case R.id.radio_known_onset:
				knowOnsetRadio.setChecked(true);
				onsetTimeInfoText.setText(AppViewModel
						.timeToString(AppViewModel.timeStampsDAO
								.getSympOnsetTime()));
				onsetTimePartInfoView.setVisibility(View.VISIBLE);
				break;
			case R.id.radio_woke_up_symptom:
				wokeUpSympRadio.setChecked(true);
				onsetTimeInfoText.setText(AppViewModel
						.timeToString(AppViewModel.timeStampsDAO
								.getSympOnsetTime()));
				onsetTimePartInfoView.setVisibility(View.VISIBLE);
				break;
			case R.id.radio_last_symptom_free:
				lastSympFreeRadio.setChecked(true);
				onsetTimeInfoText.setText(AppViewModel
						.timeToString(AppViewModel.timeStampsDAO
								.getSympOnsetTime()));
				onsetTimePartInfoView.setVisibility(View.VISIBLE);
				break;

			}
			showOnsetTimeOnly();
			onsetTimePartPickerView.setVisibility(View.GONE);
			onsetTogBtn.setVisibility(ToggleButton.GONE);
		} else {
			completeOnsetTime();
			onsetTimePartPickerView.setVisibility(View.GONE);
			onsetTogBtn.setVisibility(ToggleButton.GONE);
			onsetTimeTypeInfoText.setVisibility(TextView.GONE);
			onsetTimeInfoText.setVisibility(TextView.GONE);
			Time time = new Time();
			switch (AppViewModel.timeStampsDAO.getSympOnsetQuaId()) {
			// case 0:
			// onsetTimePartPickerView.setVisibility(View.GONE);
			// onsetTogBtn.setVisibility(ToggleButton.GONE);
			// break;
			case R.id.radio_unknown:
				unknownRadio.setChecked(true);
				// onsetTimePartPickerView.setVisibility(View.GONE);
				// onsetTogBtn.setVisibility(ToggleButton.GONE);
				break;
			case R.id.radio_known_onset:
				knowOnsetRadio.setChecked(true);
				if (AppViewModel.timeStampsDAO.getOnsetUnlock()) {
					onsetTimePartPickerView.setVisibility(View.VISIBLE);
					onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
				}else{
					onsetTimeTypeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setText(AppViewModel
							.timeToString(AppViewModel.timeStampsDAO
									.getSympOnsetTime()));
				}
				onsetHourPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetHour());
				onsetMinPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetMin());
				time.set(AppViewModel.timeStampsDAO.getSympOnsetTime());
				onsetDayPicker.init(time.year, time.month, time.monthDay, this);
				break;
			case R.id.radio_woke_up_symptom:
				wokeUpSympRadio.setChecked(true);
				if (AppViewModel.timeStampsDAO.getOnsetUnlock()) {
					onsetTimePartPickerView.setVisibility(View.VISIBLE);
					onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
				}else{
					onsetTimeTypeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setText(AppViewModel
							.timeToString(AppViewModel.timeStampsDAO
									.getSympOnsetTime()));
				}
				onsetHourPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetHour());
				onsetMinPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetMin());
				time.set(AppViewModel.timeStampsDAO.getSympOnsetTime());
				onsetDayPicker.init(time.year, time.month, time.monthDay, this);
				break;
			case R.id.radio_last_symptom_free:
				lastSympFreeRadio.setChecked(true);
				if (AppViewModel.timeStampsDAO.getOnsetUnlock()) {
					onsetTimePartPickerView.setVisibility(View.VISIBLE);
					onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
				}else{
					onsetTimeTypeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setVisibility(TextView.VISIBLE);
					onsetTimeInfoText.setText(AppViewModel
							.timeToString(AppViewModel.timeStampsDAO
									.getSympOnsetTime()));
				}
				onsetHourPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetHour());
				onsetMinPicker.setValue(AppViewModel.timeStampsDAO
						.getSympOnsetMin());
				time.set(AppViewModel.timeStampsDAO.getSympOnsetTime());
				onsetDayPicker.init(time.year, time.month, time.monthDay, this);
				break;
			}
			if (AppViewModel.timeStampsDAO.getSympOnsetQuaId() > 0) {
				onsetTogBtn.setChecked(AppViewModel.timeStampsDAO
						.getOnsetUnlock());
			}
		}
	}

	/** Set form item background, if uncompleted grey, otherwise transparent */
	public void completeBirthday() {
		if (appContext instanceof FragmentPatient) {
			String birthDay = AppViewModel.patientDAO.getDateOfBirth();
			if (birthDay == null || birthDay.length() <= 0) {
				birthdayComple.setBackgroundColor(appContext.getResources()
						.getColor(R.color.grey));
			} else {
				birthdayComple.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}

	/** Set form item background, if uncompleted grey, otherwise transparent */
	public void completeOnsetTime() {
		if (appContext instanceof FragmentPatient
				|| appContext instanceof FragmentSymptoms) {
			int onsetQuaId = AppViewModel.timeStampsDAO.getSympOnsetQuaId();
			if (onsetQuaId > 0) {
				onsetComple.setBackgroundColor(Color.TRANSPARENT);
			} else {
				onsetComple.setBackgroundColor(appContext.getResources()
						.getColor(R.color.grey));
			}
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			if (buttonView.equals(birDayTogBtn)) {
				AppViewModel.patientDAO.setBirDayUnlock(isChecked);
				birDayPartPickerView.setVisibility(View.VISIBLE);
				birDayPartInfoView.setVisibility(View.GONE);
			} else if (buttonView.equals(onsetTogBtn)) {
				AppViewModel.timeStampsDAO.setOnsetUnlock(isChecked);
				onsetTimePartPickerView.setVisibility(View.VISIBLE);
				onsetTimePartInfoView.setVisibility(View.GONE);
			} else {
				AppViewModel.timeStampsDAO.setSympOnsetQua(buttonView.getText()
						.toString());
				AppViewModel.timeStampsDAO
						.setSympOnsetQuaId(buttonView.getId());
				completeOnsetTime();
				switch (buttonView.getId()) {
				case R.id.radio_known_onset:
					onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
					onsetTimeTypePickerText.setText(R.string.info_time_onset);
					onsetTimeTypeInfoText.setText(R.string.info_time_onset);
					knowOnsetRadio.setBackgroundColor(appContext.getResources()
							.getColor(R.color.green));
					break;
				case R.id.radio_unknown:
					onsetTimePartPickerView.setVisibility(View.GONE);
					onsetTimePartInfoView.setVisibility(View.GONE);
					onsetTogBtn.setVisibility(ToggleButton.GONE);
					unknownRadio.setBackgroundColor(appContext.getResources()
							.getColor(R.color.red));
					break;
				case R.id.radio_last_symptom_free:
					onsetTimeTypePickerText
							.setText(R.string.info_time_last_symp_free);
					onsetTimeTypeInfoText
							.setText(R.string.info_time_last_symp_free);
					lastSympFreeRadio.setBackgroundColor(appContext
							.getResources().getColor(R.color.green));
					break;
				case R.id.radio_woke_up_symptom:
					onsetTimeTypePickerText
							.setText(R.string.info_time_last_symp_free);
					onsetTimeTypeInfoText
							.setText(R.string.info_time_last_symp_free);
					wokeUpSympRadio.setBackgroundColor(appContext
							.getResources().getColor(R.color.green));
					break;
				}
			}
		} else if (isChecked == false) {
			if (buttonView.equals(birDayTogBtn)) {
				AppViewModel.patientDAO.setBirDayUnlock(isChecked);
				getBirthDatePickerData();
				birDayInfoText
						.setText(AppViewModel.patientDAO.getDateOfBirth());
				birDayPartPickerView.setVisibility(View.GONE);
				birDayPartInfoView.setVisibility(View.VISIBLE);
			} else if (buttonView.equals(onsetTogBtn)) {
				AppViewModel.timeStampsDAO.setOnsetUnlock(isChecked);
				getOnsetTimePickerData();
				onsetTimeTypeInfoText.setVisibility(TextView.VISIBLE);
				onsetTimeInfoText.setVisibility(TextView.VISIBLE);
				onsetTimeInfoText.setText(AppViewModel
						.timeToString(AppViewModel.timeStampsDAO
								.getSympOnsetTime()));
				onsetTimePartPickerView.setVisibility(View.GONE);
				onsetTimePartInfoView.setVisibility(View.VISIBLE);
			} else {
				buttonView.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
	}

	@Override
	public void onClick(View v) {
		if (v.equals(unknownRadio)) {
			Intent interrIntent = new Intent(appContext.getActivity(),
					DialogCommonInterrupt.class);
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					appContext.getString(R.string.interruption_warning_title));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					appContext.getString(R.string.info_interr_onset));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_onset);
			// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if(v.equals(knowOnsetRadio)){
			onsetTimePartPickerView.setVisibility(View.VISIBLE);
			onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
			onsetTogBtn.setChecked(true);
		} else if(v.equals(lastSympFreeRadio)){
			onsetTimePartPickerView.setVisibility(View.VISIBLE);
			onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
			onsetTogBtn.setChecked(true);
		} else if(v.equals(wokeUpSympRadio)){
			onsetTimePartPickerView.setVisibility(View.VISIBLE);
			onsetTogBtn.setVisibility(ToggleButton.VISIBLE);
			onsetTogBtn.setChecked(true);
		}
	}
}
