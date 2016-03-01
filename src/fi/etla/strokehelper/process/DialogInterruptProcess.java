package fi.etla.strokehelper.process;

import java.util.ArrayList;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.DialogContraindication;
import fi.etla.strokehelper.common.MyEditText;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.ContraindicationTableDAO;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.data.DoctorUserTableDAO;
import fi.etla.strokehelper.data.PatientTableDAO;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.patient.DialogCandidateConfirm;
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

public class DialogInterruptProcess extends Activity implements
		OnCheckedChangeListener, OnClickListener{

	private CheckBox interrupt1Radio;
	private CheckBox interrupt2Radio;
	private CheckBox interrupt3Radio;
	private CheckBox interrupt4Radio;
	private CheckBox interrupt5Radio;
	
	private View otherThanStrokeView;
	private CheckBox other1Radio;
	private CheckBox other2Radio;
	private CheckBox other3Radio;
	private CheckBox other4Radio;
	private CheckBox other5Radio;
	private CheckBox other6Radio;
	private CheckBox other7Radio;
	private CheckBox other8Radio;

	private MyEditText otherWhatEdit;
	private Button detailBtn;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_interrup_process);
		setTitle(R.string.title_interrupt_process_why);
		initUIElements();
		AppViewModel.summarizeContraindications();
		if (AppViewModel.contras.size() <= 0
				&& AppViewModel.relaContras.size() <= 0) {
			detailBtn.setEnabled(false);
			interrupt4Radio.setEnabled(false);
		} else {
			detailBtn.setEnabled(true);
			interrupt4Radio.setEnabled(true);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		interrupt1Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_1);
		interrupt2Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_2);
		interrupt3Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_3);
		interrupt4Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_4);
		interrupt5Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_5);
		
		otherThanStrokeView = (View) findViewById(R.id.view_other_than_stroke);
		other1Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_1);
		other2Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_2);
		other3Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_3);
		other4Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_4);
		other5Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_5);
		other6Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_6);
		other7Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_7);
		other8Radio = (CheckBox) findViewById(R.id.radio_interrupt_process_other_8);

		otherWhatEdit = (MyEditText) findViewById(R.id.edit_other_what);
		detailBtn = (Button) findViewById(R.id.imgbtn_detail);
		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		interrupt1Radio.setOnCheckedChangeListener(this);
		interrupt2Radio.setOnCheckedChangeListener(this);
		interrupt3Radio.setOnCheckedChangeListener(this);
		interrupt4Radio.setOnCheckedChangeListener(this);
		interrupt5Radio.setOnCheckedChangeListener(this);
		other1Radio.setOnCheckedChangeListener(this);
		other2Radio.setOnCheckedChangeListener(this);
		other3Radio.setOnCheckedChangeListener(this);
		other4Radio.setOnCheckedChangeListener(this);
		other5Radio.setOnCheckedChangeListener(this);
		other6Radio.setOnCheckedChangeListener(this);
		other7Radio.setOnCheckedChangeListener(this);
		other8Radio.setOnCheckedChangeListener(this);

		saveBtn.setOnClickListener(this);
		detailBtn.setOnClickListener(this);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			bgColor = getResources().getColor(R.color.bright_blue);
			switch(buttonView.getId()){
			case R.id.radio_interrupt_process_5:
				otherThanStrokeView.setVisibility(View.VISIBLE);
				break;
			case R.id.radio_interrupt_process_other_8:
				otherWhatEdit.setVisibility(EditText.VISIBLE);
				break;
//			case R.id.radio_interrupt_2:
//				AppViewModel.thrombolysisDAO.setInterrBleeding(R.string.radio_interrupt_2);
//				break;
//			case R.id.radio_interrupt_3:
//				AppViewModel.thrombolysisDAO.setInterrAllergy(R.string.radio_interrupt_3);
//				break;
//			case R.id.radio_interrupt_4:
//				AppViewModel.thrombolysisDAO.setInterrInfusionFail(R.string.radio_interrupt_4);
//				break;
//			case R.id.radio_interrupt_5:
//				AppViewModel.thrombolysisDAO.setInterrAPTT(R.string.radio_interrupt_5);
//				break;
//			case R.id.radio_interrupt_6:
//				AppViewModel.thrombolysisDAO.setInterrBTrom(R.string.radio_interrupt_6);
//				break;
//			case R.id.radio_interrupt_7:
//				AppViewModel.thrombolysisDAO.setInterrRRUncontrol(R.string.radio_interrupt_7);
//				break;
//			
			}
		}else{
			switch(buttonView.getId()){
			case R.id.radio_interrupt_process_5:
				otherThanStrokeView.setVisibility(View.GONE);
				break;
			case R.id.radio_interrupt_process_other_8:
				otherWhatEdit.setVisibility(EditText.GONE);
				break;
//			case R.id.radio_interrupt_2:
//				AppViewModel.thrombolysisDAO.setInterrBleeding(0);
//				break;
//			case R.id.radio_interrupt_3:
//				AppViewModel.thrombolysisDAO.setInterrAllergy(0);
//				break;
//			case R.id.radio_interrupt_4:
//				AppViewModel.thrombolysisDAO.setInterrInfusionFail(0);
//				break;
//			case R.id.radio_interrupt_5:
//				AppViewModel.thrombolysisDAO.setInterrAPTT(0);
//				break;
//			case R.id.radio_interrupt_6:
//				AppViewModel.thrombolysisDAO.setInterrBTrom(0);
//				break;
//			case R.id.radio_interrupt_7:
//				AppViewModel.thrombolysisDAO.setInterrRRUncontrol(0);
//				break;
//			case R.id.radio_interrupt_8:
//				AppViewModel.thrombolysisDAO.setInterrOther("");
//				otherWhatEdit.setText("");
//				otherWhatEdit.setVisibility(EditText.GONE);
//				break;
			}
		}
		buttonView.setBackgroundColor(bgColor);
	}

	@Override
	public void onClick(View v) {
		if(v.equals(detailBtn)){
			Intent intent = new Intent(this,
					DialogContraindication.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}else if(v.equals(saveBtn)){
			String otherStr = otherWhatEdit.getText().toString();
			if (interrupt5Radio.isChecked() && other8Radio.isChecked()
					&& (otherStr == null || otherStr.length() <= 0)) {
				Toast toast = Toast.makeText(DialogInterruptProcess.this,
						R.string.warning_interrupt_no_other, Toast.LENGTH_LONG);
				toast.show();
			}else if (!(interrupt1Radio.isChecked()||interrupt2Radio.isChecked()||interrupt3Radio.isChecked()||
					interrupt4Radio.isChecked()||interrupt5Radio.isChecked()) || 
					(interrupt5Radio.isChecked() && !(other1Radio.isChecked() || other2Radio.isChecked()
							||other3Radio.isChecked()||other4Radio.isChecked()||other5Radio.isChecked()
							||other6Radio.isChecked()||other7Radio.isChecked()||other8Radio.isChecked()))) {
				Toast toast = Toast
						.makeText(DialogInterruptProcess.this,
								R.string.warning_direct_thrombo_no_reason,
								Toast.LENGTH_LONG);
				toast.show();
			}else {
				Time time = new Time();
				time.setToNow();
				String reason = "";
				if(interrupt1Radio.isChecked()){
					reason += getString(R.string.radio_interrupt_process_1) + "; ";
				}
				if(interrupt2Radio.isChecked()){
					reason += getString(R.string.radio_interrupt_process_2) + "; ";
				}
				if(interrupt3Radio.isChecked()){
					reason += getString(R.string.radio_interrupt_process_3) + "; ";
				}
				if(interrupt4Radio.isChecked()){
					reason += getString(R.string.radio_interrupt_process_4) + "; ";
				}
				if(interrupt5Radio.isChecked()){
					if(other1Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_1) + "; ";
					}
					if(other2Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_2) + "; ";
					}
					if(other3Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_3) + "; ";
					}
					if(other4Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_4) + "; ";
					}
					if(other5Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_5) + "; ";
					}
					if(other6Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_6) + "; ";
					}
					if(other7Radio.isChecked()){
						reason += getString(R.string.radio_interrupt_process_other_7) + "; ";
					}
					if(other8Radio.isChecked()){
						reason += otherStr;
					}
				}
				
				AppViewModel.interruptionDAO.setProcessInterrReason(reason);
				AppViewModel.interruptionDAO.setProcessInterrTime(time.toMillis(false));
				
				Toast toast = Toast.makeText(DialogInterruptProcess.this,
						R.string.toast_process_interrupted, Toast.LENGTH_LONG);
				toast.show();
				setResult(MainActivity.RESULT_INTERRUPT_PROCESS_YES);
				DialogInterruptProcess.this.finish();
			}
		}
//		switch (v.getId()) {
//		case R.id.imgbtn_save:
//			AppViewModel.thrombolysisDAO.setInterrOther(otherWhatEdit.getText().toString());
//			
//			
//		}
	}
}
