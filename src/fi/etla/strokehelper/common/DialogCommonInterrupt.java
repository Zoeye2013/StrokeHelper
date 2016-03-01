package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.process.FragmentLab;
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

public class DialogCommonInterrupt extends Activity implements OnClickListener {

	private TextView warnContentText;
	private Button yesBtn;
	private Button noBtn;

	public static String TAG_WARNING_TITLE = "TAG_WARNING_TITLE";
	public static String TAG_WARNING_CONTENT = "TAG_WARNING_CONTENT";
	public static String TAG_INTERRUPT_REASON = "TAG_INTERRUPT_REASON";
	public static String TAG_INTERRUPT_REASON_STR = "TAG_INTERRUPT_REASON_STR";
	private String titleStr;
	private String warnContentStr;
	private int interrType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_common_interrup);
		titleStr = getIntent().getStringExtra(TAG_WARNING_TITLE);
		warnContentStr = getIntent().getStringExtra(TAG_WARNING_CONTENT);
		interrType = getIntent().getIntExtra(TAG_INTERRUPT_REASON, 0);

		setTitle(titleStr);
		initUIElements();
		warnContentText.setText(warnContentStr);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		warnContentText = (TextView) findViewById(R.id.info_warn_content);
		yesBtn = (Button) findViewById(R.id.imgbtn_yes);
		noBtn = (Button) findViewById(R.id.imgbtn_no);
		yesBtn.setOnClickListener(this);
		noBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (interrType == R.string.info_warning_rr) {
			switch (v.getId()) {
			case R.id.imgbtn_yes:
				setResult(FragmentLab.RESULT_RR_TREAT_YES);
				break;
			case R.id.imgbtn_no:
				setResult(FragmentLab.RESULT_RR_TREAT_NO);
				break;
			}
			DialogCommonInterrupt.this.finish();
		} else if(interrType == R.string.info_warning_b_gluc){
			switch (v.getId()) {
			case R.id.imgbtn_yes:
				setResult(FragmentLab.RESULT_B_GLUC_TREAT_YES);
				break;
			case R.id.imgbtn_no:
				setResult(FragmentLab.RESULT_B_GLUC_TREAT_NO);
				break;
			}
			DialogCommonInterrupt.this.finish();
		}else if(interrType == R.string.info_warning_b_gluc_after){
			switch (v.getId()) {
			case R.id.imgbtn_yes:
				Intent interrIntent = new Intent(DialogCommonInterrupt.this,
						DialogCommonInterrupt.class);
				interrIntent
						.putExtra(
								DialogCommonInterrupt.TAG_WARNING_TITLE,
								getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_b_gluc_after_disappear));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_bgluc_after_disappear);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
				break;
			}
			DialogCommonInterrupt.this.finish();
		}else {
			Time time = new Time();
			time.setToNow();
			String interrDes = "";
			boolean decision = false;
			Toast toast = new Toast(DialogCommonInterrupt.this);

			switch (v.getId()) {
			case R.id.imgbtn_yes:
				decision = true;
				interrDes = getString(R.string.interruption_to_do);
				toast = Toast.makeText(DialogCommonInterrupt.this,
						R.string.toast_process_interrupted, Toast.LENGTH_LONG);
				setResult(MainActivity.RESULT_COMMOM_INTERRUPT_YES);
				break;
			case R.id.imgbtn_no:
				decision = false;
				interrDes = getString(R.string.interruption_not_to);
				toast = Toast.makeText(DialogCommonInterrupt.this,
						R.string.toast_process_continue, Toast.LENGTH_LONG);
				break;
			}

			switch (interrType) {
			case R.string.reason_interr_onset:
				AppViewModel.interruptionDAO.setDecisOnset(decision);
				AppViewModel.interruptionDAO.setReasonOnset(interrDes + " "
						+ getString(interrType));
				AppViewModel.interruptionDAO.setTimeOnset(time.toMillis(false));
				break;
			case R.string.reason_interr_ter_illness:
				AppViewModel.interruptionDAO.setDecisTerIllness(decision);
				AppViewModel.interruptionDAO.setReasonTerIllness(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeTerIllness(time
						.toMillis(false));
				break;
			case R.string.reason_interr_rr:
				AppViewModel.interruptionDAO.setDecisRR(decision);
				AppViewModel.interruptionDAO.setReasonRR(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeRR(time
						.toMillis(false));
				break;
			case R.string.reason_interr_rr_after:
				AppViewModel.interruptionDAO.setDecisRRAft(decision);
				AppViewModel.interruptionDAO.setReasonRRAft(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeRRAft(time
						.toMillis(false));
				break;
			case R.string.reason_interr_mRS_5:
			case R.string.reason_interr_mRS_6:
				AppViewModel.interruptionDAO.setDecisMRS(decision);
				AppViewModel.interruptionDAO.setReasonMRS(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeMRS(time
						.toMillis(false));
				break;
			case R.string.reason_interr_ct_sav:
				String reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisAdminCt(decision);
				AppViewModel.interruptionDAO.setReasonAdminCt(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeAdminCt(time
						.toMillis(false));
				break;
			case R.string.reason_interr_ct_quick:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisCTs(decision);
				AppViewModel.interruptionDAO.setReasonCTs(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeCTs(time
						.toMillis(false));
				break;
			case R.string.reason_interr_quick_inr:
				AppViewModel.interruptionDAO.setDecisQuickInr(decision);
				AppViewModel.interruptionDAO.setReasonQuickInr(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeQuickInr(time
						.toMillis(false));
				break;
			case R.string.reason_interr_inr:
				AppViewModel.interruptionDAO.setDecisInr(decision);
				AppViewModel.interruptionDAO.setReasonInr(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeInr(time
						.toMillis(false));
				break;
			case R.string.reason_interr_aptt:
				AppViewModel.interruptionDAO.setDecisAptt(decision);
				AppViewModel.interruptionDAO.setReasonAptt(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeAptt(time
						.toMillis(false));
				break;
			case R.string.reason_interr_b_trom:
				AppViewModel.interruptionDAO.setDecisBTrom(decision);
				AppViewModel.interruptionDAO.setReasonBTrom(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeBTrom(time
						.toMillis(false));
				break;
			case R.string.reason_interr_bgluc:
				AppViewModel.interruptionDAO.setDecisBGluc(decision);
				AppViewModel.interruptionDAO.setReasonBGluc(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeBGluc(time
						.toMillis(false));
				break;
			case R.string.reason_interr_bgluc_after:
			case R.string.reason_interr_bgluc_after_disappear:
				AppViewModel.interruptionDAO.setDecisBGlucAft(decision);
				AppViewModel.interruptionDAO.setReasonBGlucAft(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeBGlucAft(time
						.toMillis(false));
				break;
			case R.string.reason_interr_active_bleed:
				AppViewModel.interruptionDAO.setDecisActiveBleed(decision);
				AppViewModel.interruptionDAO.setReasonActiveBleed(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeActiveBleed(time
						.toMillis(false));
				break;
			case R.string.info_symptom_anticoagulant:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisAnticoagulant(decision);
				AppViewModel.interruptionDAO.setReasonAnticoagulant(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeAnticoagulant(time
						.toMillis(false));
				break;
			case R.string.reason_interr_hellp:
				AppViewModel.interruptionDAO.setDecisObstetric(decision);
				AppViewModel.interruptionDAO.setReasonObstetric(interrDes
						+ " " + getString(interrType));
				AppViewModel.interruptionDAO.setTimeObstetric(time
						.toMillis(false));
				break;
			case R.string.info_interr_cardiovascular:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisCardiovascular(decision);
				AppViewModel.interruptionDAO.setReasonCardiovascular(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeCardiovascular(time
						.toMillis(false));
				break;
			case R.string.info_interr_cerebrovascular:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisCerebrovascular(decision);
				AppViewModel.interruptionDAO.setReasonCerebrovascular(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeCerebrovascular(time
						.toMillis(false));
				break;
			case R.string.info_interr_disease:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisDisease(decision);
				AppViewModel.interruptionDAO.setReasonDisease(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeDisease(time
						.toMillis(false));
				break;
			case R.string.info_interr_bleeding:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisBleed(decision);
				AppViewModel.interruptionDAO.setReasonBleed(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeBleed(time
						.toMillis(false));
				break;
			case R.string.info_interr_operation:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisOperation(decision);
				AppViewModel.interruptionDAO.setReasonOperation(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeOperation(time
						.toMillis(false));
				break;
			case R.string.info_interr_vessel:
				reason = getIntent().getStringExtra(TAG_INTERRUPT_REASON_STR);
				AppViewModel.interruptionDAO.setDecisVessel(decision);
				AppViewModel.interruptionDAO.setReasonVessel(interrDes
						+ " " + reason);
				AppViewModel.interruptionDAO.setTimeVessel(time
						.toMillis(false));
				break;
			}
			toast.show();
			DialogCommonInterrupt.this.finish();
		}
	}
}
