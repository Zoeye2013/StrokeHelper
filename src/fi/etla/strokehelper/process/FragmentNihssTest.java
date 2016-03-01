package fi.etla.strokehelper.process;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.DialogHelpInfo;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.NihssTableDAO.Nihss;
import fi.etla.strokehelper.main.ItemDetailActivity;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.menu.MenusLevOne;
import fi.etla.strokehelper.process.FragmentNihssReport.NihssCallback;

public class FragmentNihssTest extends Fragment implements
		OnCheckedChangeListener,
		android.widget.CompoundButton.OnCheckedChangeListener, OnClickListener {
	private Button finishBtn;
	private Button backBtn;

	private NihssCallback nihssCallback;

	// private FragmentNihssViewModel AppViewModel.nihssDAO;

	private RadioGroup ques1aRadioGroup;
	private RadioGroup ques1bRadioGroup;
	private RadioGroup ques1cRadioGroup;
	private RadioGroup ques2RadioGroup;
	private RadioGroup ques3RadioGroup;
	private RadioGroup ques4RadioGroup;
	private RadioGroup ques5aRadioGroup;
	private RadioGroup ques5bRadioGroup;
	private RadioGroup ques6aRadioGroup;
	private RadioGroup ques6bRadioGroup;
	private RadioGroup ques7RadioGroup;
	private RadioGroup ques8RadioGroup;
	private RadioGroup ques9RadioGroup;
	private RadioGroup ques10RadioGroup;
	private RadioGroup ques11RadioGroup;

	private RadioButton ques1a0;
	private RadioButton ques1a1;
	private RadioButton ques1a2;
	private RadioButton ques1a3;

	private RadioButton ques1b0;
	private RadioButton ques1b1;
	private RadioButton ques1b2;

	private RadioButton ques1c0;
	private RadioButton ques1c1;
	private RadioButton ques1c2;

	private RadioButton ques2_0;
	private RadioButton ques2_1;
	private RadioButton ques2_2;

	private RadioButton ques3_0;
	private RadioButton ques3_1;
	private RadioButton ques3_2;
	private RadioButton ques3_3;

	private RadioButton ques4_0;
	private RadioButton ques4_1;
	private RadioButton ques4_2;
	private RadioButton ques4_3;

	private RadioButton ques5a0;
	private RadioButton ques5a1;
	private RadioButton ques5a2;
	private RadioButton ques5a3;
	private RadioButton ques5a4;
	private RadioButton ques5aUn;

	private RadioButton ques5b0;
	private RadioButton ques5b1;
	private RadioButton ques5b2;
	private RadioButton ques5b3;
	private RadioButton ques5b4;
	private RadioButton ques5bUn;

	private RadioButton ques6a0;
	private RadioButton ques6a1;
	private RadioButton ques6a2;
	private RadioButton ques6a3;
	private RadioButton ques6a4;
	private RadioButton ques6aUn;

	private RadioButton ques6b0;
	private RadioButton ques6b1;
	private RadioButton ques6b2;
	private RadioButton ques6b3;
	private RadioButton ques6b4;
	private RadioButton ques6bUn;

	private RadioButton ques7_0;
	private RadioButton ques7_1;
	private RadioButton ques7_2;
	private RadioButton ques7_Un;

	private RadioButton ques8_0;
	private RadioButton ques8_1;
	private RadioButton ques8_2;

	private RadioButton ques9_0;
	private RadioButton ques9_1;
	private RadioButton ques9_2;
	private RadioButton ques9_3;

	private RadioButton ques10_0;
	private RadioButton ques10_1;
	private RadioButton ques10_2;
	private RadioButton ques10_Un;

	private RadioButton ques11_0;
	private RadioButton ques11_1;
	private RadioButton ques11_2;
	
	private ImageButton ques1aHelpBtn;
	private ImageButton ques1bHelpBtn;
	private ImageButton ques1cHelpBtn;
	private ImageButton ques2HelpBtn;
	private ImageButton ques3HelpBtn;
	private ImageButton ques4HelpBtn;
	private ImageButton ques5aHelpBtn;
	private ImageButton ques6aHelpBtn;
	private ImageButton ques7HelpBtn;
	private ImageButton ques8HelpBtn;
	private ImageButton ques9HelpBtn;
	private ImageButton ques10HelpBtn;
	private ImageButton ques11HelpBtn;
	

	private View rootView;

	private int nihssType;
	private Nihss nihss;
	private boolean isFollowUp;
	
	private TextView comple1a;
	private TextView comple1b;
	private TextView comple1c;
	private TextView comple2;
	private TextView comple3;
	private TextView comple4;
	private TextView comple5;
	private TextView comple5a;
	private TextView comple5b;
	private TextView comple6;
	private TextView comple6a;
	private TextView comple6b;
	private TextView comple7;
	private TextView comple8;
	private TextView comple9;
	private TextView comple10;
	private TextView comple11;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentNihssTest() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_tab_nihss_test,
				container, false);
		
		initUIElements(rootView);
		loadUISatus();

		return rootView;
	}

	public void initUIElements(View rootView) {
		finishBtn = (Button) rootView.findViewById(R.id.imgbtn_finish);
		backBtn = (Button)rootView.findViewById(R.id.imgbtn_back);
		finishBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);

		/** Radio groups */
		ques1aRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_1a);
		ques1bRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_1b);
		ques1cRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_1c);
		ques2RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_2);
		ques3RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_3);
		ques4RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_4);
		ques5aRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_5a);
		ques5bRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_5b);
		ques6aRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_6a);
		ques6bRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_6b);
		ques7RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_7);
		ques8RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_8);
		ques9RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_9);
		ques10RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_10);
		ques11RadioGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_11);

		/** Radio Buttons */
		ques1a0 = (RadioButton) rootView.findViewById(R.id.radio_1a_0);
		ques1a1 = (RadioButton) rootView.findViewById(R.id.radio_1a_1);
		ques1a2 = (RadioButton) rootView.findViewById(R.id.radio_1a_2);
		ques1a3 = (RadioButton) rootView.findViewById(R.id.radio_1a_3);

		ques1b0 = (RadioButton) rootView.findViewById(R.id.radio_1b_0);
		ques1b1 = (RadioButton) rootView.findViewById(R.id.radio_1b_1);
		ques1b2 = (RadioButton) rootView.findViewById(R.id.radio_1b_2);

		ques1c0 = (RadioButton) rootView.findViewById(R.id.radio_1c_0);
		ques1c1 = (RadioButton) rootView.findViewById(R.id.radio_1c_1);
		ques1c2 = (RadioButton) rootView.findViewById(R.id.radio_1c_2);

		ques2_0 = (RadioButton) rootView.findViewById(R.id.radio_2_0);
		ques2_1 = (RadioButton) rootView.findViewById(R.id.radio_2_1);
		ques2_2 = (RadioButton) rootView.findViewById(R.id.radio_2_2);

		ques3_0 = (RadioButton) rootView.findViewById(R.id.radio_3_0);
		ques3_1 = (RadioButton) rootView.findViewById(R.id.radio_3_1);
		ques3_2 = (RadioButton) rootView.findViewById(R.id.radio_3_2);
		ques3_3 = (RadioButton) rootView.findViewById(R.id.radio_3_3);

		ques4_0 = (RadioButton) rootView.findViewById(R.id.radio_4_0);
		ques4_1 = (RadioButton) rootView.findViewById(R.id.radio_4_1);
		ques4_2 = (RadioButton) rootView.findViewById(R.id.radio_4_2);
		ques4_3 = (RadioButton) rootView.findViewById(R.id.radio_4_3);

		ques5a0 = (RadioButton) rootView.findViewById(R.id.radio_5a_0);
		ques5a1 = (RadioButton) rootView.findViewById(R.id.radio_5a_1);
		ques5a2 = (RadioButton) rootView.findViewById(R.id.radio_5a_2);
		ques5a3 = (RadioButton) rootView.findViewById(R.id.radio_5a_3);
		ques5a4 = (RadioButton) rootView.findViewById(R.id.radio_5a_4);
		ques5aUn = (RadioButton) rootView.findViewById(R.id.radio_5a_un);

		ques5b0 = (RadioButton) rootView.findViewById(R.id.radio_5b_0);
		ques5b1 = (RadioButton) rootView.findViewById(R.id.radio_5b_1);
		ques5b2 = (RadioButton) rootView.findViewById(R.id.radio_5b_2);
		ques5b3 = (RadioButton) rootView.findViewById(R.id.radio_5b_3);
		ques5b4 = (RadioButton) rootView.findViewById(R.id.radio_5b_4);
		ques5bUn = (RadioButton) rootView.findViewById(R.id.radio_5b_un);

		ques6a0 = (RadioButton) rootView.findViewById(R.id.radio_6a_0);
		ques6a1 = (RadioButton) rootView.findViewById(R.id.radio_6a_1);
		ques6a2 = (RadioButton) rootView.findViewById(R.id.radio_6a_2);
		ques6a3 = (RadioButton) rootView.findViewById(R.id.radio_6a_3);
		ques6a4 = (RadioButton) rootView.findViewById(R.id.radio_6a_4);
		ques6aUn = (RadioButton) rootView.findViewById(R.id.radio_6a_un);

		ques6b0 = (RadioButton) rootView.findViewById(R.id.radio_6b_0);
		ques6b1 = (RadioButton) rootView.findViewById(R.id.radio_6b_1);
		ques6b2 = (RadioButton) rootView.findViewById(R.id.radio_6b_2);
		ques6b3 = (RadioButton) rootView.findViewById(R.id.radio_6b_3);
		ques6b4 = (RadioButton) rootView.findViewById(R.id.radio_6b_4);
		ques6bUn = (RadioButton) rootView.findViewById(R.id.radio_6b_un);

		ques7_0 = (RadioButton) rootView.findViewById(R.id.radio_7_0);
		ques7_1 = (RadioButton) rootView.findViewById(R.id.radio_7_1);
		ques7_2 = (RadioButton) rootView.findViewById(R.id.radio_7_2);
		ques7_Un = (RadioButton) rootView.findViewById(R.id.radio_7_un);

		ques8_0 = (RadioButton) rootView.findViewById(R.id.radio_8_0);
		ques8_1 = (RadioButton) rootView.findViewById(R.id.radio_8_1);
		ques8_2 = (RadioButton) rootView.findViewById(R.id.radio_8_2);

		ques9_0 = (RadioButton) rootView.findViewById(R.id.radio_9_0);
		ques9_1 = (RadioButton) rootView.findViewById(R.id.radio_9_1);
		ques9_2 = (RadioButton) rootView.findViewById(R.id.radio_9_2);
		ques9_3 = (RadioButton) rootView.findViewById(R.id.radio_9_3);

		ques10_0 = (RadioButton) rootView.findViewById(R.id.radio_10_0);
		ques10_1 = (RadioButton) rootView.findViewById(R.id.radio_10_1);
		ques10_2 = (RadioButton) rootView.findViewById(R.id.radio_10_2);
		ques10_Un = (RadioButton) rootView.findViewById(R.id.radio_10_un);

		ques11_0 = (RadioButton) rootView.findViewById(R.id.radio_11_0);
		ques11_1 = (RadioButton) rootView.findViewById(R.id.radio_11_1);
		ques11_2 = (RadioButton) rootView.findViewById(R.id.radio_11_2);
		
		ques1aHelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_1a);
		ques1bHelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_1b);
		ques1cHelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_1c);
		ques2HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_2);
		ques3HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_3);
		ques4HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_4);
		ques5aHelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_5a);
		ques6aHelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_6a);
		ques7HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_7);
		ques8HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_8);
		ques9HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_9);
		ques10HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_10);
		ques11HelpBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_help_question_11);
		
		comple1a = (TextView) rootView.findViewById(R.id.comple_nihss_1a);
		comple1b = (TextView) rootView.findViewById(R.id.comple_nihss_1b);
		comple1c = (TextView) rootView.findViewById(R.id.comple_nihss_1c);
		comple2 = (TextView) rootView.findViewById(R.id.comple_nihss_2);
		comple3 = (TextView) rootView.findViewById(R.id.comple_nihss_3);
		comple4 = (TextView) rootView.findViewById(R.id.comple_nihss_4);
		comple5 = (TextView) rootView.findViewById(R.id.comple_nihss_5);
		comple5a = (TextView) rootView.findViewById(R.id.comple_nihss_5a);
		comple5b = (TextView) rootView.findViewById(R.id.comple_nihss_5b);
		comple6 = (TextView) rootView.findViewById(R.id.comple_nihss_6);
		comple6a = (TextView) rootView.findViewById(R.id.comple_nihss_6a);
		comple6b = (TextView) rootView.findViewById(R.id.comple_nihss_6b);
		comple7 = (TextView) rootView.findViewById(R.id.comple_nihss_7);
		comple8 = (TextView) rootView.findViewById(R.id.comple_nihss_8);
		comple9 = (TextView) rootView.findViewById(R.id.comple_nihss_9);
		comple10 = (TextView) rootView.findViewById(R.id.comple_nihss_10);
		comple11 = (TextView) rootView.findViewById(R.id.comple_nihss_11);

		ques1aRadioGroup.setOnCheckedChangeListener(this);
		ques1bRadioGroup.setOnCheckedChangeListener(this);
		ques1cRadioGroup.setOnCheckedChangeListener(this);
		ques2RadioGroup.setOnCheckedChangeListener(this);
		ques3RadioGroup.setOnCheckedChangeListener(this);
		ques4RadioGroup.setOnCheckedChangeListener(this);
		ques5aRadioGroup.setOnCheckedChangeListener(this);
		ques5bRadioGroup.setOnCheckedChangeListener(this);
		ques6aRadioGroup.setOnCheckedChangeListener(this);
		ques6bRadioGroup.setOnCheckedChangeListener(this);
		ques7RadioGroup.setOnCheckedChangeListener(this);
		ques8RadioGroup.setOnCheckedChangeListener(this);
		ques9RadioGroup.setOnCheckedChangeListener(this);
		ques10RadioGroup.setOnCheckedChangeListener(this);
		ques11RadioGroup.setOnCheckedChangeListener(this);

		ques1a0.setOnCheckedChangeListener(this);
		ques1a1.setOnCheckedChangeListener(this);
		ques1a2.setOnCheckedChangeListener(this);
		ques1a3.setOnCheckedChangeListener(this);

		ques1b0.setOnCheckedChangeListener(this);
		ques1b1.setOnCheckedChangeListener(this);
		ques1b2.setOnCheckedChangeListener(this);

		ques1c0.setOnCheckedChangeListener(this);
		ques1c1.setOnCheckedChangeListener(this);
		ques1c2.setOnCheckedChangeListener(this);

		ques2_0.setOnCheckedChangeListener(this);
		ques2_1.setOnCheckedChangeListener(this);
		ques2_2.setOnCheckedChangeListener(this);

		ques3_0.setOnCheckedChangeListener(this);
		ques3_1.setOnCheckedChangeListener(this);
		ques3_2.setOnCheckedChangeListener(this);
		ques3_3.setOnCheckedChangeListener(this);

		ques4_0.setOnCheckedChangeListener(this);
		ques4_1.setOnCheckedChangeListener(this);
		ques4_2.setOnCheckedChangeListener(this);
		ques4_3.setOnCheckedChangeListener(this);

		ques5a0.setOnCheckedChangeListener(this);
		ques5a1.setOnCheckedChangeListener(this);
		ques5a2.setOnCheckedChangeListener(this);
		ques5a3.setOnCheckedChangeListener(this);
		ques5a4.setOnCheckedChangeListener(this);
		ques5aUn.setOnCheckedChangeListener(this);

		ques5b0.setOnCheckedChangeListener(this);
		ques5b1.setOnCheckedChangeListener(this);
		ques5b2.setOnCheckedChangeListener(this);
		ques5b3.setOnCheckedChangeListener(this);
		ques5b4.setOnCheckedChangeListener(this);
		ques5bUn.setOnCheckedChangeListener(this);

		ques6a0.setOnCheckedChangeListener(this);
		ques6a1.setOnCheckedChangeListener(this);
		ques6a2.setOnCheckedChangeListener(this);
		ques6a3.setOnCheckedChangeListener(this);
		ques6a4.setOnCheckedChangeListener(this);
		ques6aUn.setOnCheckedChangeListener(this);

		ques6b0.setOnCheckedChangeListener(this);
		ques6b1.setOnCheckedChangeListener(this);
		ques6b2.setOnCheckedChangeListener(this);
		ques6b3.setOnCheckedChangeListener(this);
		ques6b4.setOnCheckedChangeListener(this);
		ques6bUn.setOnCheckedChangeListener(this);

		ques7_0.setOnCheckedChangeListener(this);
		ques7_1.setOnCheckedChangeListener(this);
		ques7_2.setOnCheckedChangeListener(this);
		ques7_Un.setOnCheckedChangeListener(this);

		ques8_0.setOnCheckedChangeListener(this);
		ques8_1.setOnCheckedChangeListener(this);
		ques8_2.setOnCheckedChangeListener(this);

		ques9_0.setOnCheckedChangeListener(this);
		ques9_1.setOnCheckedChangeListener(this);
		ques9_2.setOnCheckedChangeListener(this);
		ques9_3.setOnCheckedChangeListener(this);

		ques10_0.setOnCheckedChangeListener(this);
		ques10_1.setOnCheckedChangeListener(this);
		ques10_2.setOnCheckedChangeListener(this);
		ques10_Un.setOnCheckedChangeListener(this);

		ques11_0.setOnCheckedChangeListener(this);
		ques11_1.setOnCheckedChangeListener(this);
		ques11_2.setOnCheckedChangeListener(this);
		
		ques1aHelpBtn.setOnClickListener(this);
		ques1bHelpBtn.setOnClickListener(this);
		ques1cHelpBtn.setOnClickListener(this);
		ques2HelpBtn.setOnClickListener(this);
		ques3HelpBtn.setOnClickListener(this);
		ques4HelpBtn.setOnClickListener(this);
		ques5aHelpBtn.setOnClickListener(this);
		ques6aHelpBtn.setOnClickListener(this);
		ques7HelpBtn.setOnClickListener(this);
		ques8HelpBtn.setOnClickListener(this);
		ques9HelpBtn.setOnClickListener(this);
		ques10HelpBtn.setOnClickListener(this);
		ques11HelpBtn.setOnClickListener(this);

	}

	public void setNihssType(int type) {
		nihssType = type;
	}
	
	public void setIsFollowUp(boolean isFo){
		isFollowUp = isFo;
	}

	public void initUIStatus() {
		ques1aRadioGroup.clearCheck();
		ques1bRadioGroup.clearCheck();
		ques1cRadioGroup.clearCheck();
		ques2RadioGroup.clearCheck();
		ques3RadioGroup.clearCheck();
		ques4RadioGroup.clearCheck();
		ques5aRadioGroup.clearCheck();
		ques5bRadioGroup.clearCheck();
		ques6aRadioGroup.clearCheck();
		ques6bRadioGroup.clearCheck();
		ques7RadioGroup.clearCheck();
		ques8RadioGroup.clearCheck();
		ques9RadioGroup.clearCheck();
		ques10RadioGroup.clearCheck();
		ques11RadioGroup.clearCheck();
	}

	public void loadUISatus() {
		/** Haven't done NIHSS test yet */
//		if (nihss != null && nihss.getTestTime() != null
//				&& nihss.getTestTime().length() > 0) {

			if (AppViewModel.nihssDAO.isNihssLoaded(nihssType) == false)
				AppViewModel.nihssDAO.fetchNihss(nihssType);

			setUserSelection();
//		} else {
//			initUIStatus();
//		}
	}

	public void setUserSelection() {
		switch(nihssType){
		case 0:
			nihss = AppViewModel.nihssDAO.getNihssBaseline();
			break;
		case 1:
			nihss = AppViewModel.nihssDAO.getNihss1H();
			break;
		case 24:
			nihss = AppViewModel.nihssDAO.getNihss24H();
			break;
		}
		
		if (nihss != null) {
			complete1A();
			complete1B();
			complete1C();
			complete2();
			complete3();
			complete4();
			complete5();
			complete6();
			complete7();
			complete8();
			complete9();
			complete10();
			complete11();
			ques1aRadioGroup.check(nihss.getId1A());
			ques1bRadioGroup.check(nihss.getId1B());
			ques1cRadioGroup.check(nihss.getId1C());
			ques2RadioGroup.check(nihss.getId2());
			ques3RadioGroup.check(nihss.getId3());
			ques4RadioGroup.check(nihss.getId4());
			ques5aRadioGroup.check(nihss.getId5A());
			ques5bRadioGroup.check(nihss.getId5B());
			ques6aRadioGroup.check(nihss.getId6A());
			ques6bRadioGroup.check(nihss.getId6B());
			ques7RadioGroup.check(nihss.getId7());
			ques8RadioGroup.check(nihss.getId8());
			ques9RadioGroup.check(nihss.getId9());
			ques10RadioGroup.check(nihss.getId10());
			ques11RadioGroup.check(nihss.getId11());
		}
	}

	@Override
	public void onDestroy() {
		nihss.calTotalScore();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		if (v.equals(finishBtn)) {
			if (nihss.isTestCompleted()) {
				if (nihss.getTestTime() == null
						|| nihss.getTestTime().length() <= 0) {
					Time time = new Time();
					time.setToNow();
					nihss.setTestTime(AppViewModel
							.timeToString(time.toMillis(false)));
				}
				nihss.calTotalScore();
				nihssCallback.onNihssButtonClicked(v.getId(),isFollowUp);
			} else {
				Toast toast = Toast.makeText(getActivity(),
						R.string.warning_nihss_uncompleted, Toast.LENGTH_LONG);
				toast.show();
			}
		}else if (v.equals(backBtn)) {
			nihss.calTotalScore();
			nihssCallback.onNihssButtonClicked(v.getId(),isFollowUp);
		}
		else{
			Intent intent = new Intent(getActivity(),DialogHelpInfo.class);
			switch(v.getId()){
			case R.id.imgbtn_help_question_1a:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_1a));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_1a));
				break;
			case R.id.imgbtn_help_question_1b:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_1b));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_1b));
				break;
			case R.id.imgbtn_help_question_1c:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_1c));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_1c));
				break;
			case R.id.imgbtn_help_question_2:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_2));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_2));
				break;
			case R.id.imgbtn_help_question_3:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_3));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_3));
				break;
			case R.id.imgbtn_help_question_4:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_4));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_4));
				break;
			case R.id.imgbtn_help_question_5a:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_5a));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_5));
				break;
			case R.id.imgbtn_help_question_6a:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_6a));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_6));
				break;
			case R.id.imgbtn_help_question_7:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_7));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_7));
				break;
			case R.id.imgbtn_help_question_8:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_8));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_8));
				break;
			case R.id.imgbtn_help_question_9:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_9));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_9));
				break;
			case R.id.imgbtn_help_question_10:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_10));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_10));
				break;
			case R.id.imgbtn_help_question_11:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE, getString(R.string.nihss_question_11));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO, getString(R.string.help_info_question_11));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivity(intent);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId > 0) {
			RadioButton radioBtn = (RadioButton) rootView
					.findViewById(checkedId);
			String answer = radioBtn.getText().toString();
			int point = 0;
			switch (checkedId) {
			case R.id.radio_1a_1:
			case R.id.radio_1b_1:
			case R.id.radio_1c_1:
			case R.id.radio_2_1:
			case R.id.radio_3_1:
			case R.id.radio_4_1:
			case R.id.radio_5a_1:
			case R.id.radio_5b_1:
			case R.id.radio_6a_1:
			case R.id.radio_6b_1:
			case R.id.radio_7_1:
			case R.id.radio_8_1:
			case R.id.radio_9_1:
			case R.id.radio_10_1:
			case R.id.radio_11_1:
				point = 1;
				break;
			case R.id.radio_1a_2:
			case R.id.radio_1b_2:
			case R.id.radio_1c_2:
			case R.id.radio_2_2:
			case R.id.radio_3_2:
			case R.id.radio_4_2:
			case R.id.radio_5a_2:
			case R.id.radio_5b_2:
			case R.id.radio_6a_2:
			case R.id.radio_6b_2:
			case R.id.radio_7_2:
			case R.id.radio_8_2:
			case R.id.radio_9_2:
			case R.id.radio_10_2:
			case R.id.radio_11_2:
				point = 2;
				break;
			case R.id.radio_1a_3:
			case R.id.radio_3_3:
			case R.id.radio_4_3:
			case R.id.radio_5a_3:
			case R.id.radio_5b_3:
			case R.id.radio_6a_3:
			case R.id.radio_6b_3:
			case R.id.radio_9_3:
				point = 3;
				break;
			case R.id.radio_5a_4:
			case R.id.radio_5b_4:
			case R.id.radio_6a_4:
			case R.id.radio_6b_4:
				point = 4;
				break;

			}
			switch (group.getId()) {
			case R.id.radio_group_1a:
				nihss.setAnswer1A(answer);
				nihss.setId1A(checkedId);
				nihss.setPoint1A(point);
				complete1A();
				break;
			case R.id.radio_group_1b:
				nihss.setAnswer1B(answer);
				nihss.setId1B(checkedId);
				nihss.setPoint1B(point);
				complete1B();
				break;
			case R.id.radio_group_1c:
				nihss.setAnswer1C(answer);
				nihss.setId1C(checkedId);
				nihss.setPoint1C(point);
				complete1C();
				break;
			case R.id.radio_group_2:
				nihss.setAnswer2(answer);
				nihss.setId2(checkedId);
				nihss.setPoint2(point);
				complete2();
				break;
			case R.id.radio_group_3:
				nihss.setAnswer3(answer);
				nihss.setId3(checkedId);
				nihss.setPoint3(point);
				complete3();
				break;
			case R.id.radio_group_4:
				nihss.setAnswer4(answer);
				nihss.setId4(checkedId);
				nihss.setPoint4(point);
				complete4();
				break;
			case R.id.radio_group_5a:
				nihss.setAnswer5A(answer);
				nihss.setId5A(checkedId);
				nihss.setPoint5A(point);
				complete5();
				break;
			case R.id.radio_group_5b:
				nihss.setAnswer5B(answer);
				nihss.setId5B(checkedId);
				nihss.setPoint5B(point);
				complete5();
				break;
			case R.id.radio_group_6a:
				nihss.setAnswer6A(answer);
				nihss.setId6A(checkedId);
				nihss.setPoint6A(point);
				complete6();
				break;
			case R.id.radio_group_6b:
				nihss.setAnswer6B(answer);
				nihss.setId6B(checkedId);
				nihss.setPoint6B(point);
				complete6();
				break;
			case R.id.radio_group_7:
				nihss.setAnswer7(answer);
				nihss.setId7(checkedId);
				nihss.setPoint7(point);
				complete7();
				break;
			case R.id.radio_group_8:
				nihss.setAnswer8(answer);
				nihss.setId8(checkedId);
				nihss.setPoint8(point);
				complete8();
				break;
			case R.id.radio_group_9:
				nihss.setAnswer9(answer);
				nihss.setId9(checkedId);
				nihss.setPoint9(point);
				complete9();
				break;
			case R.id.radio_group_10:
				nihss.setAnswer10(answer);
				nihss.setId10(checkedId);
				nihss.setPoint10(point);
				complete10();
				break;
			case R.id.radio_group_11:
				nihss.setAnswer11(answer);
				nihss.setId11(checkedId);
				nihss.setPoint11(point);
				complete11();
				break;
			}
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof NihssCallback)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		nihssCallback = (NihssCallback) activity;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			buttonView.setBackgroundColor(getResources()
					.getColor(R.color.green));
		} else {
			buttonView.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void complete1A(){
		int answer =  nihss.getId1A();
		if(answer > 0){
			comple1a.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple1a.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete1B(){
		int answer =  nihss.getId1B();
		if(answer > 0){
			comple1b.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple1b.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete1C(){
		int answer =  nihss.getId1C();
		if(answer > 0){
			comple1c.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple1c.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete2(){
		int answer =  nihss.getId2();
		if(answer > 0){
			comple2.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple2.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete3(){
		int answer =  nihss.getId3();
		if(answer > 0){
			comple3.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple3.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete4(){
		int answer =  nihss.getId4();
		if(answer > 0){
			comple4.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple4.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete5(){
		int answer1 =  nihss.getId5A();
		int answer2 = nihss.getId5B();
		if(answer1 > 0 && answer2 > 0){
			comple5.setBackgroundColor(Color.TRANSPARENT);
		}else
		{
			comple5.setBackgroundColor(getResources().getColor(R.color.grey));
		}
		
		if(answer1 > 0){
			comple5a.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple5a.setBackgroundColor(getResources().getColor(R.color.grey));
		}
		
		if(answer2 > 0){
			comple5b.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple5b.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}

	public void complete6(){
		int answer1 =  nihss.getId6A();
		int answer2 = nihss.getId6B();
		if(answer1 > 0 && answer2 > 0){
			comple6.setBackgroundColor(Color.TRANSPARENT);
		}else
		{
			comple6.setBackgroundColor(getResources().getColor(R.color.grey));
		}
		
		if(answer1 > 0){
			comple6a.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple6a.setBackgroundColor(getResources().getColor(R.color.grey));
		}
		
		if(answer2 > 0){
			comple6b.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple6b.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete7(){
		int answer =  nihss.getId7();
		if(answer > 0){
			comple7.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple7.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete8(){
		int answer =  nihss.getId8();
		if(answer > 0){
			comple8.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple8.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete9(){
		int answer =  nihss.getId9();
		if(answer > 0){
			comple9.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple9.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete10(){
		int answer =  nihss.getId10();
		if(answer > 0){
			comple10.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple10.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	public void complete11(){
		int answer =  nihss.getId11();
		if(answer > 0){
			comple11.setBackgroundColor(Color.TRANSPARENT);
		}else{
			comple11.setBackgroundColor(getResources().getColor(R.color.grey));
		}
	}
	

}
