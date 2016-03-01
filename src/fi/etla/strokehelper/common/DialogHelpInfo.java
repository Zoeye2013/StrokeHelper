package fi.etla.strokehelper.common;

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
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DialogHelpInfo extends Activity implements OnClickListener {

	private TextView helpInfoText;
	private Button backBtn;

	public static String TAG_HELP_TITLE = "TAG_HELP_TITLE";
	public static String TAG_HELP_INFO = "TAG_HELP_INFO";
	private String titleStr;
	private String helpInfoStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_help_info);
		titleStr = getIntent().getStringExtra(TAG_HELP_TITLE);
		helpInfoStr = getIntent().getStringExtra(TAG_HELP_INFO);

		setTitle(titleStr);
		initUIElements();
		helpInfoText.setText(helpInfoStr);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {

		helpInfoText = (TextView) findViewById(R.id.info_help);
		backBtn = (Button) findViewById(R.id.imgbtn_back);
		backBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_back:
			DialogHelpInfo.this.finish();
			break;
		}
	}
}
