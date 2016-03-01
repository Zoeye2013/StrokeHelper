package fi.etla.strokehelper.patient;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.MyEditText;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class DialogCandidateConfirm extends Activity implements
		OnCheckedChangeListener, OnClickListener{

	private CheckBox timeOverRadio;
	private CheckBox timeUnknownRadio;
	private CheckBox icBleedingRadio;
	private CheckBox majorBleedingRadio;
	private CheckBox uncontrolBleedingRadio;
	private CheckBox hellpRadio;
	private CheckBox terminalIllRadio;
	private CheckBox notStrokeRadio;
	private CheckBox heparinRadio;
	private CheckBox myocartdialRadio;
	
	private CheckBox otherRadio;

	private MyEditText otherWhatEdit;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_candidate_confirm);
		setTitle(R.string.title_confirm_not_candidate);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		timeOverRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_time_over);
		timeUnknownRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_time_unknown);
		icBleedingRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_ic_bleeding);
		majorBleedingRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_major_bleeding);
		uncontrolBleedingRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_uncontrol_bleeding);
		hellpRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_hellp);
		terminalIllRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_terminal_ill);
		notStrokeRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_not_stroke);
		heparinRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_heparin);
		myocartdialRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_myocardial);
		otherRadio = (CheckBox) findViewById(R.id.radio_candidate_reason_other);

		otherWhatEdit = (MyEditText) findViewById(R.id.edit_other_what);
		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		timeOverRadio.setOnCheckedChangeListener(this);
		timeUnknownRadio.setOnCheckedChangeListener(this);
		icBleedingRadio.setOnCheckedChangeListener(this);
		majorBleedingRadio.setOnCheckedChangeListener(this);
		uncontrolBleedingRadio.setOnCheckedChangeListener(this);
		hellpRadio.setOnCheckedChangeListener(this);
		terminalIllRadio.setOnCheckedChangeListener(this);
		notStrokeRadio.setOnCheckedChangeListener(this);
		heparinRadio.setOnCheckedChangeListener(this);
		myocartdialRadio.setOnCheckedChangeListener(this);
		otherRadio.setOnCheckedChangeListener(this);

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
			case R.id.radio_candidate_reason_other:
				otherWhatEdit.setVisibility(EditText.VISIBLE);
				break;
			}
		}else{
			switch(buttonView.getId()){
			case R.id.radio_candidate_reason_other:
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
			String otherStr = otherWhatEdit.getText().toString();
			
			if (otherRadio.isChecked()
					&& (otherStr == null || otherStr.length() <= 0)) {
				Toast toast = Toast.makeText(DialogCandidateConfirm.this,
						R.string.warning_not_candidate_other, Toast.LENGTH_LONG);
				toast.show();
			}else if (!(timeOverRadio.isChecked()||timeUnknownRadio.isChecked()||icBleedingRadio.isChecked()||
					majorBleedingRadio.isChecked()||uncontrolBleedingRadio.isChecked()||
					hellpRadio.isChecked()||terminalIllRadio.isChecked()||notStrokeRadio.isChecked()||
					heparinRadio.isChecked()||myocartdialRadio.isChecked()||otherRadio.isChecked())) {
				Toast toast = Toast
						.makeText(DialogCandidateConfirm.this,
								R.string.warning_direct_thrombo_no_reason,
								Toast.LENGTH_LONG);
				toast.show();
			}else {
				String reason = "";
				if(timeOverRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_time_over) + "; ";
				}
				if(timeUnknownRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_time_unknown) + "; ";
				}
				if(icBleedingRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_ic_bleeding) + "; ";
				}
				if(majorBleedingRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_major_bleeding) + "; ";
				}
				if(uncontrolBleedingRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_uncontrol_bleeding) + "; ";
				}
				if(hellpRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_hellp) + "; ";
				}
				if(terminalIllRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_terminal_ill) + "; ";
				}
				if(notStrokeRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_not_stroke) + "; ";
				}
				if(heparinRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_heparin) + "; ";
				}
				if(myocartdialRadio.isChecked()){
					reason += getString(R.string.radio_candidate_reason_myocardial) + "; ";
				}
				if(otherRadio.isChecked()){
					reason += otherStr;
				}
				
				AppViewModel.patientDAO.setWhyNotCandidate(reason);
				Toast toast = Toast.makeText(DialogCandidateConfirm.this,
						R.string.toast_candidate_not, Toast.LENGTH_LONG);
				toast.show();
				DialogCandidateConfirm.this.finish();
			}
			break;
		}
	}
}
