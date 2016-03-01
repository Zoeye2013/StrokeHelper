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
import android.widget.TextView;
import android.widget.Toast;

public class DialogEditingBirthday extends Activity implements OnClickListener,
		OnDateChangedListener {

	private Button saveBtn;
	private Button cancelBtn;

	private DatePicker birDayPicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_editing_birthday);
		setTitle(R.string.title_editing_birthday);
		initElements();
		loadUI();
	}

	public void initElements() {
		birDayPicker = (DatePicker) findViewById(R.id.picker_birthday);

		saveBtn = (Button) findViewById(R.id.imgbtn_save);
		cancelBtn = (Button) findViewById(R.id.imgbtn_cancel);

		saveBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	}

	public void loadUI() {
		birDayPicker.init(AppViewModel.patientDAO.getBirthYear(),
				AppViewModel.patientDAO.getBirthMonth(),
				AppViewModel.patientDAO.getBirthDay(), this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_save:
			AppViewModel.patientDAO.setBirthDay(birDayPicker.getDayOfMonth());
			AppViewModel.patientDAO.setBirthMonth(birDayPicker.getMonth());
			AppViewModel.patientDAO.setBirthYear(birDayPicker.getYear());

			String birthDate = birDayPicker.getDayOfMonth() + "."
					+ (birDayPicker.getMonth() + 1) + "."
					+ birDayPicker.getYear();
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
			Toast toast = Toast.makeText(this,
					R.string.toast_data_saved, Toast.LENGTH_LONG);
			toast.show();
			setResult(DialogEditingTimes.EDITING_TIME_RESULT_YES);
			DialogEditingBirthday.this.finish();
			break;
		case R.id.imgbtn_cancel:
			DialogEditingBirthday.this.finish();
			break;
		}
	}

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
	}
}
