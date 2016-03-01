package fi.etla.strokehelper.process;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.DialogCommonInterrupt;
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
import android.widget.RadioButton;
import android.widget.Toast;

public class DialogMRS6Confirm extends Activity implements OnClickListener{

	private RadioButton yesRadio;
	private RadioButton noRadio;

//	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_mrs6_confirm);
		setTitle(R.string.info_interr_mrs6_confirm);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		yesRadio = (RadioButton) findViewById(R.id.radio_mrs6_yes);
		noRadio = (RadioButton) findViewById(R.id.radio_mrs6_no);
		
//		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		yesRadio.setOnClickListener(this);
		noRadio.setOnClickListener(this);

//		saveBtn.setOnClickListener(this);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.radio_mrs6_yes:
			Intent interrIntent = new Intent(DialogMRS6Confirm.this,
					DialogCommonInterrupt.class);
			interrIntent
					.putExtra(
							DialogCommonInterrupt.TAG_WARNING_TITLE,
							getString(R.string.interruption_warning_title));
			interrIntent.putExtra(
					DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_mrs_6));
			interrIntent.putExtra(
					DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_mRS_6);
			interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(interrIntent);
			break;
		case R.id.radio_mrs6_no:
			Toast toast = Toast.makeText(DialogMRS6Confirm.this,
					R.string.toast_mrs6_correct_answer, Toast.LENGTH_LONG);
			toast.show();
			break;
		}
		DialogMRS6Confirm.this.finish(); 
	}
}
