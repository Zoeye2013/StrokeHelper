package fi.etla.strokehelper.thrombolysis;

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

public class DialogFinishFollowUp extends Activity implements
		OnCheckedChangeListener, OnClickListener{

	private CheckBox finish1Radio;
	private CheckBox finish2Radio;

	private MyEditText otherWhatEdit;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_finish_follow_up);
		setTitle(R.string.title_finish_why);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		finish1Radio = (CheckBox) findViewById(R.id.radio_finish_follow_up_1);
		finish2Radio = (CheckBox) findViewById(R.id.radio_finish_follow_up_2);

		otherWhatEdit = (MyEditText) findViewById(R.id.edit_other_what);
		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		finish1Radio.setOnCheckedChangeListener(this);
		finish2Radio.setOnCheckedChangeListener(this);

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
			case R.id.radio_finish_follow_up_1:
				AppViewModel.thrombolysisDAO.setFollowUpReady(R.string.radio_finish_follow_up_1);
				break;
			case R.id.radio_finish_follow_up_2:
				otherWhatEdit.setVisibility(EditText.VISIBLE);
				break;
			}
		}else{
			switch(buttonView.getId()){
			case R.id.radio_finish_follow_up_1:
				AppViewModel.thrombolysisDAO.setInterrDeterioration(0);
				break;
			case R.id.radio_finish_follow_up_2:
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
			AppViewModel.thrombolysisDAO.setFollowUpFinishOther(otherWhatEdit.getText().toString());
			
			if (finish2Radio.isChecked()
					&& (AppViewModel.thrombolysisDAO.getFollowUpFinishOther() == null || AppViewModel.thrombolysisDAO
							.getFollowUpFinishOther().length() <= 0)) {
				Toast toast = Toast.makeText(DialogFinishFollowUp.this,
						R.string.warning_finish_no_other, Toast.LENGTH_LONG);
				toast.show();
			}else if (!AppViewModel.thrombolysisDAO.hasFinishFollowUpReasons()) {
				Toast toast = Toast
						.makeText(DialogFinishFollowUp.this,
								R.string.warning_finish_no_reason,
								Toast.LENGTH_LONG);
				toast.show();
			}else {
				Time time = new Time();
				time.setToNow();
				AppViewModel.thrombolysisDAO.setIsFollowUpFinish(true);
				AppViewModel.timeStampsDAO.setFollowEndTime(time.toMillis(false));
				Toast toast = Toast.makeText(DialogFinishFollowUp.this,
						R.string.toast_follow_up_finished, Toast.LENGTH_LONG);
				toast.show();
				setResult(MainActivity.RESULT_FINISH_FOLLOW_UP);
				DialogFinishFollowUp.this.finish();
			}
			break;
		}
	}
}
