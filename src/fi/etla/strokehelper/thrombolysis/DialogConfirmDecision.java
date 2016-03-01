package fi.etla.strokehelper.thrombolysis;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.DialogContraindication;
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

public class DialogConfirmDecision extends Activity implements OnClickListener {

	public static final String TAG_THROMBO_DECISION = "TAG_THROMBO_DECISION";
	public static final int RESULT_CONFIRM_YES = 1;
	public static final int RESULT_CONFIRM_NO = 2;
//	private TextView titleText;
	private TextView indicationsText;
	private TextView contrasText;
	private Button indicationsBtn;
	private Button contrasBtn;
	
	private Button yesBtn;
	private Button noBtn;
	
	private boolean decision;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_common_confirm_decision);
		decision = getIntent().getBooleanExtra(TAG_THROMBO_DECISION, false);
		initUIElements();
		loadUI();
	}

	public void initUIElements() {
//		titleText = (TextView) findViewById(R.id.info_title);
		indicationsText = (TextView) findViewById(R.id.info_indications);
		contrasText = (TextView) findViewById(R.id.info_contraindications);
		indicationsBtn = (Button) findViewById(R.id.imgbtn_indications);
		contrasBtn = (Button) findViewById(R.id.imgbtn_contraindications);
		yesBtn = (Button) findViewById(R.id.imgbtn_yes);
		noBtn = (Button) findViewById(R.id.imgbtn_no);
		indicationsBtn.setOnClickListener(this);
		contrasBtn.setOnClickListener(this);
		yesBtn.setOnClickListener(this);
		noBtn.setOnClickListener(this);
	}
	
	public void loadUI(){
		if(decision){
			setTitle(R.string.title_confirm_thrombolysis_decision_yes);
		}else if (!decision){
			setTitle(R.string.title_confirm_thrombolysis_decision_no);
		}
		
		AppViewModel.summarizeIndications();
		int indicasSize = AppViewModel.criticalIndications.size() + AppViewModel.supportiveIndications.size();
		int contrasSize = AppViewModel.contras.size() + AppViewModel.relaContras.size();
		
		if(indicasSize <= 0){
			indicationsText.setVisibility(TextView.VISIBLE);
			indicationsBtn.setVisibility(Button.GONE);
		}else if(indicasSize > 0){
			indicationsText.setVisibility(TextView.GONE);
			indicationsBtn.setVisibility(Button.VISIBLE);
		}
		
		if(contrasSize <= 0){
			contrasText.setVisibility(TextView.VISIBLE);
			contrasBtn.setVisibility(Button.GONE);
		}else if(contrasSize > 0){
			contrasText.setVisibility(TextView.GONE);
			contrasBtn.setVisibility(Button.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		if(v.equals(indicationsBtn)){
			Intent indicationsIntent = new Intent(DialogConfirmDecision.this,
					DialogIndication.class);
			indicationsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			DialogConfirmDecision.this.startActivity(indicationsIntent);
		}else if(v.equals(contrasBtn)){
			Intent contraintent = new Intent(DialogConfirmDecision.this,
					DialogContraindication.class);
			contraintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			DialogConfirmDecision.this.startActivity(contraintent);
		}else if(v.equals(yesBtn)){
			Intent data = new Intent();
			data.putExtra(TAG_THROMBO_DECISION, decision);
			setResult(RESULT_CONFIRM_YES,data);
			DialogConfirmDecision.this.finish();
		}else if(v.equals(noBtn)){
			Intent data = new Intent();
			data.putExtra(TAG_THROMBO_DECISION, decision);
			setResult(RESULT_CONFIRM_NO,data);
			DialogConfirmDecision.this.finish();
		}
	}
}
