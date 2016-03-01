package fi.etla.strokehelper.thrombolysis;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.MyEditText;
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
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class DialogDirectThrombolysis extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	private Button yesBtn;
	private Button noBtn;

	private View reasonView;
	private CheckBox reason1Radio;
	private CheckBox reason2Radio;

	private MyEditText otherWhatEdit;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_direct_thrombolysis);
		setTitle(R.string.title_confirm_direct_thrombolysis);
		initUIElements();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {
		yesBtn = (Button) findViewById(R.id.imgbtn_yes);
		noBtn = (Button) findViewById(R.id.imgbtn_no);

		reasonView = (View) findViewById(R.id.view_direct_thrombolysis_reasons);
		reasonView.setVisibility(View.GONE);
		reason1Radio = (CheckBox) findViewById(R.id.radio_direct_thrombo_reason_1);
		reason2Radio = (CheckBox) findViewById(R.id.radio_direct_thrombo_reason_2);

		otherWhatEdit = (MyEditText) findViewById(R.id.edit_other_what);
		saveBtn = (Button) findViewById(R.id.imgbtn_save);

		reason1Radio.setOnCheckedChangeListener(this);
		reason2Radio.setOnCheckedChangeListener(this);

		yesBtn.setOnClickListener(this);
		noBtn.setOnClickListener(this);
		saveBtn.setOnClickListener(this);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			bgColor = getResources().getColor(R.color.bright_blue);
			switch (buttonView.getId()) {
			case R.id.radio_direct_thrombo_reason_1:
				AppViewModel.thrombolysisDAO
						.setDirectDataLater(R.string.radio_direct_thrombo_reason_1);
				break;
			case R.id.radio_direct_thrombo_reason_2:
				otherWhatEdit.setVisibility(EditText.VISIBLE);
				break;
			}
		} else {
			switch (buttonView.getId()) {
			case R.id.radio_direct_thrombo_reason_1:
				AppViewModel.thrombolysisDAO.setDirectDataLater(0);
				break;
			case R.id.radio_direct_thrombo_reason_2:
				AppViewModel.thrombolysisDAO.setDirectOther("");
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
		case R.id.imgbtn_yes:
			yesBtn.setEnabled(false);
			noBtn.setEnabled(false);
			reasonView.setVisibility(View.VISIBLE);
			break;
		case R.id.imgbtn_no:
			setResult(FragmentThrombolysisDecision.RESULT_DIRECT_THROMBO_NO);
			DialogDirectThrombolysis.this.finish();
			break;
		case R.id.imgbtn_save:
			String otherReasonStr = otherWhatEdit.getText().toString();
			if (reason2Radio.isChecked()
					&& (otherReasonStr == null || otherReasonStr.length() <= 0)) {
				Toast toast = Toast.makeText(DialogDirectThrombolysis.this,
						R.string.warning_direct_thrombo_no_other,
						Toast.LENGTH_LONG);
				toast.show();
			} else if (!AppViewModel.thrombolysisDAO.hasDirectThromboReasons()) {
				Toast toast = Toast.makeText(DialogDirectThrombolysis.this,
						R.string.warning_direct_thrombo_no_reason,
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				Time time = new Time();
				time.setToNow();
				long timeLong = time.toMillis(false);
				AppViewModel.thrombolysisDAO.setDirectOther(otherReasonStr);
				AppViewModel.thrombolysisDAO.setDirectThromboDecis(true);
				AppViewModel.thrombolysisDAO
						.setDecisionId(R.id.radio_thrombolysis_decision_yes);
				AppViewModel.timeStampsDAO.setDecisionTime(timeLong);
				Toast toast = Toast.makeText(DialogDirectThrombolysis.this,
						getString(R.string.info_thrombolysis_decision_yes)
								+ " " + AppViewModel.timeToString(timeLong)
								+ "\n" + getString(R.string.toast_begin_bolus),
						Toast.LENGTH_LONG);
				toast.show();
				setResult(FragmentThrombolysisDecision.RESULT_DIRECT_THROMBO_YES);
				DialogDirectThrombolysis.this.finish();
			}
			break;
		}
	}
}
