package fi.etla.strokehelper.process;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.MyEditText;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.patient.DialogCandidateConfirm;
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

public class DialogOtherPreCondiInterr extends Activity implements OnClickListener {

	private Button yesBtn;
	private Button noBtn;

	private MyEditText otherEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_other_present_condition);

		setTitle(R.string.interruption_warning_title);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		otherEditText = (MyEditText) findViewById(R.id.edit_other_what);
		yesBtn = (Button) findViewById(R.id.imgbtn_yes);
		noBtn = (Button) findViewById(R.id.imgbtn_no);
		yesBtn.setOnClickListener(this);
		noBtn.setOnClickListener(this);
		
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onClick(View v) {
		String otherStr = otherEditText.getText().toString();
		
		if (otherStr == null || otherStr.length() <= 0) {
			Toast toast = Toast.makeText(DialogOtherPreCondiInterr.this,
					R.string.warning_not_present_other, Toast.LENGTH_LONG);
			toast.show();
		}else {
			Time time = new Time();
			time.setToNow();
			String interrDes = "";
			boolean decision = false;
			Toast toast = new Toast(DialogOtherPreCondiInterr.this);

			switch (v.getId()) {
			case R.id.imgbtn_yes:
				decision = true;
				interrDes = getString(R.string.interruption_to_do);
				toast = Toast.makeText(DialogOtherPreCondiInterr.this,
						R.string.toast_process_interrupted, Toast.LENGTH_LONG);
				setResult(MainActivity.RESULT_COMMOM_INTERRUPT_YES);
				break;
			case R.id.imgbtn_no:
				decision = false;
				interrDes = getString(R.string.interruption_not_to);
				toast = Toast.makeText(DialogOtherPreCondiInterr.this,
						R.string.toast_process_continue, Toast.LENGTH_LONG);
				break;
			}
			
			AppViewModel.interruptionDAO.setDecisPresentOther(decision);
			AppViewModel.interruptionDAO.setReasonPresentOther(interrDes + " "
					+ otherStr);
			AppViewModel.interruptionDAO.setTimePresentOther(time.toMillis(false));
			
			toast.show();
			DialogOtherPreCondiInterr.this.finish();
		}
	}
}
