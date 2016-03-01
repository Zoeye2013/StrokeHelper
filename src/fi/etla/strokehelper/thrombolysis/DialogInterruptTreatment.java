package fi.etla.strokehelper.thrombolysis;

import java.util.ArrayList;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.MyEditText;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.ContraindicationTableDAO;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.data.DoctorUserTableDAO;
import fi.etla.strokehelper.data.PatientTableDAO;
import fi.etla.strokehelper.main.MainActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class DialogInterruptTreatment extends Activity implements
		OnCheckedChangeListener, OnClickListener{

	private CheckBox interrupt1Radio;
	private CheckBox interrupt2Radio;
	private CheckBox interrupt3Radio;
	private CheckBox interrupt4Radio;
	private CheckBox interrupt5Radio;
	private CheckBox interrupt6Radio;
	private CheckBox interrupt7Radio;
	private CheckBox interrupt8Radio;

	private MyEditText otherWhatEdit;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_interrup_treatment);
		setTitle(R.string.title_interrupt_treat_why);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		interrupt1Radio = (CheckBox) findViewById(R.id.radio_interrupt_1);
		interrupt2Radio = (CheckBox) findViewById(R.id.radio_interrupt_2);
		interrupt3Radio = (CheckBox) findViewById(R.id.radio_interrupt_3);
		interrupt4Radio = (CheckBox) findViewById(R.id.radio_interrupt_4);
		interrupt5Radio = (CheckBox) findViewById(R.id.radio_interrupt_5);
		interrupt6Radio = (CheckBox) findViewById(R.id.radio_interrupt_6);
		interrupt7Radio = (CheckBox) findViewById(R.id.radio_interrupt_7);
		interrupt8Radio = (CheckBox) findViewById(R.id.radio_interrupt_8);

		otherWhatEdit = (MyEditText) findViewById(R.id.edit_other_what);
		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		interrupt1Radio.setOnCheckedChangeListener(this);
		interrupt2Radio.setOnCheckedChangeListener(this);
		interrupt3Radio.setOnCheckedChangeListener(this);
		interrupt4Radio.setOnCheckedChangeListener(this);
		interrupt5Radio.setOnCheckedChangeListener(this);
		interrupt6Radio.setOnCheckedChangeListener(this);
		interrupt7Radio.setOnCheckedChangeListener(this);
		interrupt8Radio.setOnCheckedChangeListener(this);

		saveBtn.setOnClickListener(this);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			bgColor = getResources().getColor(R.color.bright_blue);
			switch(buttonView.getId()){
			case R.id.radio_interrupt_1:
				AppViewModel.thrombolysisDAO.setInterrDeterioration(R.string.radio_interrupt_treat_1);
				break;
			case R.id.radio_interrupt_2:
				AppViewModel.thrombolysisDAO.setInterrBleeding(R.string.radio_interrupt_treat_2);
				break;
			case R.id.radio_interrupt_3:
				AppViewModel.thrombolysisDAO.setInterrAllergy(R.string.radio_interrupt_treat_3);
				break;
			case R.id.radio_interrupt_4:
				AppViewModel.thrombolysisDAO.setInterrInfusionFail(R.string.radio_interrupt_treat_4);
				break;
			case R.id.radio_interrupt_5:
				AppViewModel.thrombolysisDAO.setInterrAPTT(R.string.radio_interrupt_treat_5);
				break;
			case R.id.radio_interrupt_6:
				AppViewModel.thrombolysisDAO.setInterrBTrom(R.string.radio_interrupt_treat_6);
				break;
			case R.id.radio_interrupt_7:
				AppViewModel.thrombolysisDAO.setInterrRRUncontrol(R.string.radio_interrupt_treat_7);
				break;
			case R.id.radio_interrupt_8:
				otherWhatEdit.setVisibility(EditText.VISIBLE);
				break;
			}
		}else{
			switch(buttonView.getId()){
			case R.id.radio_interrupt_1:
				AppViewModel.thrombolysisDAO.setInterrDeterioration(0);
				break;
			case R.id.radio_interrupt_2:
				AppViewModel.thrombolysisDAO.setInterrBleeding(0);
				break;
			case R.id.radio_interrupt_3:
				AppViewModel.thrombolysisDAO.setInterrAllergy(0);
				break;
			case R.id.radio_interrupt_4:
				AppViewModel.thrombolysisDAO.setInterrInfusionFail(0);
				break;
			case R.id.radio_interrupt_5:
				AppViewModel.thrombolysisDAO.setInterrAPTT(0);
				break;
			case R.id.radio_interrupt_6:
				AppViewModel.thrombolysisDAO.setInterrBTrom(0);
				break;
			case R.id.radio_interrupt_7:
				AppViewModel.thrombolysisDAO.setInterrRRUncontrol(0);
				break;
			case R.id.radio_interrupt_8:
				AppViewModel.thrombolysisDAO.setInterrOther("");
				otherWhatEdit.setText("");
				otherWhatEdit.setVisibility(EditText.GONE);
				break;
			}
		}
		buttonView.setBackgroundColor(bgColor);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_save:
			AppViewModel.thrombolysisDAO.setInterrOther(otherWhatEdit.getText().toString());
			
			if (interrupt8Radio.isChecked()
					&& (AppViewModel.thrombolysisDAO.getInterrOther() == null || AppViewModel.thrombolysisDAO
							.getInterrOther().length() <= 0)) {
				Toast toast = Toast.makeText(DialogInterruptTreatment.this,
						R.string.warning_interrupt_no_other, Toast.LENGTH_LONG);
				toast.show();
			}else if (!AppViewModel.thrombolysisDAO.hasInterruptReasons()) {
				Toast toast = Toast
						.makeText(DialogInterruptTreatment.this,
								R.string.warning_interrupt_no_reason,
								Toast.LENGTH_LONG);
				toast.show();
			}else {
				Time time = new Time();
				time.setToNow();
				AppViewModel.thrombolysisDAO.setIsInterrupt(true);
				AppViewModel.timeStampsDAO.setInterruptionTime(time.toMillis(false));
				Toast toast = Toast.makeText(DialogInterruptTreatment.this,
						R.string.toast_interrupted, Toast.LENGTH_LONG);
				toast.show();
				setResult(MainActivity.RESULT_INTERRUPT_TREAT_YES);
				DialogInterruptTreatment.this.finish();
			}
			break;
		}
	}
}
