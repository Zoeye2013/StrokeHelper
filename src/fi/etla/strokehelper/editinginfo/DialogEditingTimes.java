package fi.etla.strokehelper.editinginfo;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisFollowUp;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

public class DialogEditingTimes extends Activity implements OnClickListener {
	public static final int EDITING_TIME_REQUEST = 5;
	public static final int EDITING_TIME_RESULT_YES = 2;
	public static final String EDITING_TITLE = "EDITING_TITLE";
	
	private TextView birthdayText;
	private TextView onsetText;
	private TextView estimateArrivalText;
	private TextView doorText;
	private TextView ctBeginText;
	private TextView ctEndText;
	private TextView decisionText;
	private TextView bolusText;
	private TextView infusionBeginText;
	private TextView infusionEndText;
	private TextView followUpEndText;

	private Button birthdayBtn;
	private Button onsetBtn;
	private Button estimateArrivalBtn;
	private Button doorBtn;
	private Button ctBeginBtn;
	private Button ctEndBtn;
	private Button decisionBtn;
	private Button bolusBtn;
	private Button infusionBeginBtn;
	private Button infusionEndBtn;
	private Button followUpEndBtn;

	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_timestamps_list);
		setTitle(R.string.title_editing_timestamps);
		initElements();
		loadUI();
	}

	public void initElements() {
		birthdayText = (TextView) findViewById(R.id.info_birthday);
		onsetText = (TextView) findViewById(R.id.info_time_onset);
		estimateArrivalText = (TextView) findViewById(R.id.info_time_estimate_arrival);
		doorText = (TextView) findViewById(R.id.info_time_door);
		ctBeginText = (TextView) findViewById(R.id.info_time_ct_begin);
		ctEndText = (TextView) findViewById(R.id.info_time_ct_end);
		decisionText = (TextView) findViewById(R.id.info_time_thrombo_decision);
		bolusText = (TextView) findViewById(R.id.info_time_bolus);
		infusionBeginText = (TextView) findViewById(R.id.info_time_infusion_begin);
		infusionEndText = (TextView) findViewById(R.id.info_time_infusion_end);
		followUpEndText = (TextView) findViewById(R.id.info_time_follow_up);

		birthdayBtn = (Button) findViewById(R.id.btn_edit_birthday);
		onsetBtn = (Button) findViewById(R.id.btn_edit_onset_time);
		estimateArrivalBtn = (Button) findViewById(R.id.btn_edit_estimate_arrival_time);
		doorBtn = (Button) findViewById(R.id.btn_edit_patient_at_door_time);
		ctBeginBtn = (Button) findViewById(R.id.btn_edit_ct_begin_time);
		ctEndBtn = (Button) findViewById(R.id.btn_edit_ct_end_time);
		decisionBtn = (Button) findViewById(R.id.btn_edit_thrombo_decision_time);
		bolusBtn = (Button) findViewById(R.id.btn_edit_bolus_time);
		infusionBeginBtn = (Button) findViewById(R.id.btn_edit_infusion_begin_time);
		infusionEndBtn = (Button) findViewById(R.id.btn_edit_infusion_end_time);
		followUpEndBtn = (Button) findViewById(R.id.btn_edit_follow_up_end_time);

		backBtn = (Button) findViewById(R.id.imgbtn_back);

		birthdayBtn.setOnClickListener(this);
		onsetBtn.setOnClickListener(this);
		estimateArrivalBtn.setOnClickListener(this);
		doorBtn.setOnClickListener(this);
		ctBeginBtn.setOnClickListener(this);
		ctEndBtn.setOnClickListener(this);
		decisionBtn.setOnClickListener(this);
		bolusBtn.setOnClickListener(this);
		infusionBeginBtn.setOnClickListener(this);
		infusionEndBtn.setOnClickListener(this);
		followUpEndBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
			
		super.onDestroy();
	}

	public void loadUI() {
		String birthday = AppViewModel.patientDAO.getDateOfBirth();
		if (birthday != null && birthday.length() > 0){
			birthdayText.setText(birthday);
			birthdayBtn.setEnabled(true);
		}else
			birthdayBtn.setEnabled(false);

		long onset = AppViewModel.timeStampsDAO.getSympOnsetTime();
		if (onset > 0) {
			onsetText.setText(AppViewModel.timeToString(onset));
			onsetBtn.setEnabled(true);
		}else
			onsetBtn.setEnabled(false);
		
		long estimate = AppViewModel.timeStampsDAO.getEstiArriTime();
		if (estimate > 0) {
			estimateArrivalText.setText(AppViewModel.timeToString(estimate));
			estimateArrivalBtn.setEnabled(true);
		}else
			estimateArrivalBtn.setEnabled(false);
		
		long door = AppViewModel.timeStampsDAO.getDoorTime();
		if (door > 0) {
			doorText.setText(AppViewModel.timeToString(door));
			doorBtn.setEnabled(true);
		}else
			doorBtn.setEnabled(false);
		
		long ctBe = AppViewModel.timeStampsDAO.getCTBeginTime();
		if (ctBe > 0) {
			ctBeginText.setText(AppViewModel.timeToString(ctBe));
			ctBeginBtn.setEnabled(true);
		}else
			ctBeginBtn.setEnabled(false);
		
		long ctEnd = AppViewModel.timeStampsDAO.getCTStatementTime();
		if (ctEnd > 0) {
			ctEndText.setText(AppViewModel.timeToString(ctEnd));
			ctEndBtn.setEnabled(true);
		}else
			ctEndBtn.setEnabled(false);
		
		long decision = AppViewModel.timeStampsDAO.getDecisionTime();
		if (decision > 0) {
			decisionText.setText(AppViewModel.timeToString(decision));
			decisionBtn.setEnabled(true);
		}else
			decisionBtn.setEnabled(false);
		
		long bolus = AppViewModel.timeStampsDAO.getBolusTime();
		if (bolus > 0) {
			bolusText.setText(AppViewModel.timeToString(bolus));
			bolusBtn.setEnabled(true);
		}else
			bolusBtn.setEnabled(false);
		
		long infusionBe = AppViewModel.timeStampsDAO.getInfusionBeTime();
		if (infusionBe > 0) {
			infusionBeginText.setText(AppViewModel.timeToString(infusionBe));
			infusionBeginBtn.setEnabled(true);
		}else
			infusionBeginBtn.setEnabled(false);
		
		long infusionEnd = AppViewModel.timeStampsDAO.getInfusionEndTime();
		if (infusionEnd > 0) {
			infusionEndText.setText(AppViewModel.timeToString(infusionEnd));
			infusionEndBtn.setEnabled(true);
		}else
			infusionEndBtn.setEnabled(false);
		
		long followUpEnd = AppViewModel.timeStampsDAO.getFollowEndTime();
		if (followUpEnd > 0) {
			followUpEndText.setText(AppViewModel.timeToString(followUpEnd));
			followUpEndBtn.setEnabled(true);
		}else
			followUpEndBtn.setEnabled(false);
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** Birthday has been modified */
		if(requestCode == EDITING_TIME_REQUEST){
			if(resultCode == EDITING_TIME_RESULT_YES){
				loadUI();
				setResult(EDITING_TIME_RESULT_YES);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_edit_birthday:
			Intent birthIntent = new Intent(DialogEditingTimes.this, DialogEditingBirthday.class);
//			birthIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			DialogEditingTimes.this.startActivityForResult(birthIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_onset_time:
			Intent onsetIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			onsetIntent.putExtra(EDITING_TITLE, R.string.title_time_onset);
			DialogEditingTimes.this.startActivityForResult(onsetIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_estimate_arrival_time:
			Intent arriveIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			arriveIntent.putExtra(EDITING_TITLE, R.string.title_time_estimate_arrival);
			DialogEditingTimes.this.startActivityForResult(arriveIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_patient_at_door_time:
			Intent doorIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			doorIntent.putExtra(EDITING_TITLE, R.string.title_time_door);
			DialogEditingTimes.this.startActivityForResult(doorIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_ct_begin_time:
			Intent ctBeIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			ctBeIntent.putExtra(EDITING_TITLE, R.string.title_time_ct_begin);
			DialogEditingTimes.this.startActivityForResult(ctBeIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_ct_end_time:
			Intent ctEndIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			ctEndIntent.putExtra(EDITING_TITLE, R.string.title_time_ct_end);
			DialogEditingTimes.this.startActivityForResult(ctEndIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_thrombo_decision_time:
			Intent decisionIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			decisionIntent.putExtra(EDITING_TITLE, R.string.title_time_thrombo_decision);
			DialogEditingTimes.this.startActivityForResult(decisionIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_bolus_time:
			Intent bolusIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			bolusIntent.putExtra(EDITING_TITLE, R.string.title_time_bolus);
			DialogEditingTimes.this.startActivityForResult(bolusIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_infusion_begin_time:
			Intent infuBeIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			infuBeIntent.putExtra(EDITING_TITLE, R.string.title_time_infusion_begin);
			DialogEditingTimes.this.startActivityForResult(infuBeIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_infusion_end_time:
			Intent infuEndIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			infuEndIntent.putExtra(EDITING_TITLE, R.string.title_time_infusion_end);
			DialogEditingTimes.this.startActivityForResult(infuEndIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.btn_edit_follow_up_end_time:
			Intent foUpEndIntent = new Intent(DialogEditingTimes.this, DialogCommonEditingTimes.class);
			foUpEndIntent.putExtra(EDITING_TITLE, R.string.title_time_follow_up);
			DialogEditingTimes.this.startActivityForResult(foUpEndIntent, EDITING_TIME_REQUEST);
			break;
		case R.id.imgbtn_back:
			DialogEditingTimes.this.finish();
			break;
		}
	}
}
