package fi.etla.strokehelper.process;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.DialogCommonInterrupt;
import fi.etla.strokehelper.common.DialogHelpInfo;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.ItemDetailActivity;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.menu.MenusLevOne;

public class FragmentPresentConditions extends Fragment implements
		OnCheckedChangeListener,
		android.widget.CompoundButton.OnCheckedChangeListener, OnClickListener {
	private CommonPatientInfo patientInfoCommon;
	private View rootView;
	private View presentConsitionsView;
	private View anticoagulantView;
	private View cardiovascularView;
	private View obstetricView;
	private View anticoaTreatView;
	private View anticoaHeparinView;
	private View anticoaDabigatranView;
	private View anticoaApiksabanView;

	private View cardiQuesView;
	private View obstetricQuesView;

	private Button saveBtn;

	private RadioGroup activeBleedGroup;

	private RadioGroup anticoagulantGroup;
	private RadioGroup anticoaTreatGroup;
	private RadioGroup anticoaHeparinGroup;
	private RadioGroup anticoaDabigatranGroup;
	private RadioGroup anticoaApiksabanGroup;

	private RadioGroup cardiovascularGroup;
	private RadioGroup obstetricGroup;
	private RadioGroup termDiseaseGroup;
	private RadioGroup otherGroup;

	private CheckBox endocarditisRadio;
	private CheckBox pericarditisRadio;
	private CheckBox embolusRadio;
	private CheckBox cardiOtherCriRadio;
	private CheckBox cardiOtherNotCriRadio;
	private CheckBox pregTrimesterRadio;
	private CheckBox placentaRadio;
	private CheckBox obsteOtherRelatCriRadio;
	private CheckBox obsteOtherNotCriRadio;

	private RadioButton activeBleedNoRadio;
	private RadioButton activeBleedYesRadio;
	private RadioButton activeBleedUnknownRadio;

	private RadioButton anticoaNoRadio;
	private RadioButton anticoaYesRadio;
	private RadioButton anticoaUnknownRadio;

	private RadioButton anticoaTreatHepaRadio;
	private RadioButton anticoaTreatDabigaRadio;
	private RadioButton anticoaTreatApiksaRadio;
	private RadioButton anticoaTreatOtherRadio;
	private RadioButton anticoaTreatUnknownRadio;

	private RadioButton anticoaTreatHepaYesRadio;
	private RadioButton anticoaTreatHepaNoRadio;
	private RadioButton anticoaTreatDabigaYesRadio;
	private RadioButton anticoaTreatDabigaNoRadio;
	private RadioButton anticoaTreatApiksaYesRadio;
	private RadioButton anticoaTreatApiksaNoRadio;

	private RadioButton cardioNoRadio;
	private RadioButton cardioYesRadio;
	private RadioButton cardioUnknownRadio;

	private RadioButton obstericNoRadio;
	private RadioButton obstericYesRelatRadio;
	private RadioButton obstericHELLPRadio;
	private RadioButton obstericUnknownRadio;

	private RadioButton termDiseaseNoRadio;
	private RadioButton termDiseaseYesRadio;
	private RadioButton termDiseaseUnknownRadio;

	private RadioButton otherPresCondiNoRadio;
	private RadioButton otherPresCondiYesRadio;

	private ImageButton anticoaHelpBtn;
	private ImageButton cardioHelpBtn;
	private ImageButton obstetricHelpBtn;

	private Button cardioDoneBtn;

	private TextView acuteBleedComple;
	private TextView cardioComple;
	private TextView obstetricComple;
	private TextView termiDiseaseComple;
	private TextView otherPresComple;
	private TextView anticoagulantComple;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentPresentConditions() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(
				R.layout.fragment_detail_tab_present_conditions, container,
				false);

		initUIElements(rootView);
		loadUISatus();
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements(View rootView) {
		/** Patient info */
		View tempView = (View) rootView.findViewById(R.id.row_one);
		patientInfoCommon = new CommonPatientInfo(tempView,
				FragmentPresentConditions.this);

		presentConsitionsView = (View) rootView.findViewById(R.id.row_two);

		saveBtn = (Button) rootView.findViewById(R.id.imgbtn_save);
		saveBtn.setOnClickListener(this);

		/** Active Bleeding info */
		activeBleedGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_active_bleeding);
		activeBleedNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_active_bleeding_no);
		activeBleedYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_active_bleeding_yes);
		activeBleedUnknownRadio = (RadioButton) rootView
				.findViewById(R.id.radio_active_bleeding_unknown);
		acuteBleedComple = (TextView) rootView
				.findViewById(R.id.comple_acute_bleeding);

		/** Anticoagulant Info */
		anticoagulantView = (View) rootView
				.findViewById(R.id.view_anticoagulant_medication);
		anticoaTreatView = (View) anticoagulantView
				.findViewById(R.id.view_question_anticoagulant_treatment);

		anticoaHeparinView = (View) anticoagulantView
				.findViewById(R.id.view_question_heparin);
		anticoaDabigatranView = (View) anticoagulantView
				.findViewById(R.id.view_question_dabigatran);
		anticoaApiksabanView = (View) anticoagulantView
				.findViewById(R.id.view_question_apiksaban);
		anticoagulantGroup = (RadioGroup) anticoagulantView
				.findViewById(R.id.radio_group_anticoagulant_medication);
		anticoaTreatGroup = (RadioGroup) anticoagulantView
				.findViewById(R.id.radio_group_anticoagulant_treatment);
		anticoaHeparinGroup = (RadioGroup) anticoagulantView
				.findViewById(R.id.radio_group_heparin);
		anticoaDabigatranGroup = (RadioGroup) anticoagulantView
				.findViewById(R.id.radio_group_dabigatran);
		anticoaApiksabanGroup = (RadioGroup) anticoagulantView
				.findViewById(R.id.radio_group_apiksaban);

		anticoaNoRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_no);
		anticoaYesRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_yes);
		anticoaUnknownRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_unknown);

		anticoaTreatHepaRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_treatment_heparin);
		anticoaTreatDabigaRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_treatment_dabigatran);
		anticoaTreatApiksaRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_treatment_apiksaban);
		anticoaTreatOtherRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_treatment_other);
		anticoaTreatUnknownRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_anticoagulant_treatment_unknown);

		anticoaTreatHepaYesRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_heparin_yes);
		anticoaTreatHepaNoRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_heparin_no);
		anticoaTreatDabigaYesRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_dabigatran_yes);
		anticoaTreatDabigaNoRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_dabigatran_no);
		anticoaTreatApiksaYesRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_apiksaban_yes);
		anticoaTreatApiksaNoRadio = (RadioButton) anticoagulantView
				.findViewById(R.id.radio_apiksaban_no);
		anticoagulantComple = (TextView) anticoagulantView
				.findViewById(R.id.comple_anticoagulant);

		/** Cartraindication Info */
		cardiovascularView = (View) rootView
				.findViewById(R.id.view_cardiovascular_infections);
		cardiQuesView = (View) cardiovascularView
				.findViewById(R.id.view_question_cardiovascular_infections);

		cardiovascularGroup = (RadioGroup) cardiovascularView
				.findViewById(R.id.radio_group_cardiovascular_infections);
		cardioNoRadio = (RadioButton) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_no);
		cardioYesRadio = (RadioButton) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_yes);
		cardioUnknownRadio = (RadioButton) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_unknown);

		endocarditisRadio = (CheckBox) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_endocarditis);
		pericarditisRadio = (CheckBox) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_pericarditis);
		embolusRadio = (CheckBox) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_embolus);
		cardiOtherCriRadio = (CheckBox) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_other_critical);
		cardiOtherNotCriRadio = (CheckBox) cardiovascularView
				.findViewById(R.id.radio_cardiovascular_infections_other_not_critical);
		cardioComple = (TextView) cardiovascularView
				.findViewById(R.id.comple_cardiovascular_infections);

		/** Obsteric Info */
		obstetricView = (View) rootView
				.findViewById(R.id.view_obstetric_contraindications);
		obstetricQuesView = (View) obstetricView
				.findViewById(R.id.view_question_obstetric_contraindications);

		obstetricGroup = (RadioGroup) obstetricView
				.findViewById(R.id.radio_group_obstetric_contraindications);
		obstericNoRadio = (RadioButton) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_no);
		obstericYesRelatRadio = (RadioButton) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_yes_relative);
		obstericHELLPRadio = (RadioButton) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_hellp);
		obstericUnknownRadio = (RadioButton) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_unknown);

		pregTrimesterRadio = (CheckBox) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_pregnancy);
		placentaRadio = (CheckBox) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_placenta);
		obsteOtherRelatCriRadio = (CheckBox) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_other_relative_critical);
		obsteOtherNotCriRadio = (CheckBox) obstetricView
				.findViewById(R.id.radio_obstetric_contraindications_other_not_critical);
		obstetricComple = (TextView) obstetricView
				.findViewById(R.id.comple_obstetric);

		/** Teminal Disease Info */
		termDiseaseGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_terminal_disease);
		termDiseaseNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_no);
		termDiseaseYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_yes);
		termDiseaseUnknownRadio = (RadioButton) rootView
				.findViewById(R.id.radio_terminal_disease_unknown);
		termiDiseaseComple = (TextView) rootView
				.findViewById(R.id.comple_terminal_disease);

		/** Other Present Conditions Info */
		otherGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_other_present_condition);
		otherPresCondiNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_other_present_condition_no);
		otherPresCondiYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_other_present_condition_yes);
		otherPresComple = (TextView) rootView
				.findViewById(R.id.comple_other_present_condition);

		anticoaHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_anticoagulant_medication);
		cardioHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_cardiovascular_infections);
		obstetricHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_obstetric_contraindications);

		cardioDoneBtn = (Button) rootView
				.findViewById(R.id.imgbtn_done_cardiovascular);
		cardioDoneBtn.setVisibility(Button.VISIBLE);
		cardioDoneBtn.setEnabled(false);

		/** Register Listeners */
		activeBleedGroup.setOnCheckedChangeListener(this);
		anticoagulantGroup.setOnCheckedChangeListener(this);
		cardiovascularGroup.setOnCheckedChangeListener(this);
		obstetricGroup.setOnCheckedChangeListener(this);
		termDiseaseGroup.setOnCheckedChangeListener(this);
		otherGroup.setOnCheckedChangeListener(this);

		anticoaTreatGroup.setOnCheckedChangeListener(this);
		anticoaHeparinGroup.setOnCheckedChangeListener(this);
		anticoaDabigatranGroup.setOnCheckedChangeListener(this);
		anticoaApiksabanGroup.setOnCheckedChangeListener(this);

		activeBleedNoRadio.setOnCheckedChangeListener(this);
		activeBleedYesRadio.setOnCheckedChangeListener(this);
		activeBleedUnknownRadio.setOnCheckedChangeListener(this);

		anticoaNoRadio.setOnCheckedChangeListener(this);
		anticoaYesRadio.setOnCheckedChangeListener(this);
		anticoaUnknownRadio.setOnCheckedChangeListener(this);

		anticoaTreatHepaRadio.setOnCheckedChangeListener(this);
		anticoaTreatDabigaRadio.setOnCheckedChangeListener(this);
		anticoaTreatApiksaRadio.setOnCheckedChangeListener(this);
		anticoaTreatOtherRadio.setOnCheckedChangeListener(this);
		anticoaTreatUnknownRadio.setOnCheckedChangeListener(this);

		anticoaTreatHepaYesRadio.setOnCheckedChangeListener(this);
		anticoaTreatHepaNoRadio.setOnCheckedChangeListener(this);
		anticoaTreatDabigaYesRadio.setOnCheckedChangeListener(this);
		anticoaTreatDabigaNoRadio.setOnCheckedChangeListener(this);
		anticoaTreatApiksaNoRadio.setOnCheckedChangeListener(this);
		anticoaTreatApiksaYesRadio.setOnCheckedChangeListener(this);

		cardioNoRadio.setOnCheckedChangeListener(this);
		cardioYesRadio.setOnCheckedChangeListener(this);
		cardioUnknownRadio.setOnCheckedChangeListener(this);

		obstericNoRadio.setOnCheckedChangeListener(this);
		obstericYesRelatRadio.setOnCheckedChangeListener(this);
		obstericHELLPRadio.setOnCheckedChangeListener(this);
		obstericUnknownRadio.setOnCheckedChangeListener(this);

		termDiseaseNoRadio.setOnCheckedChangeListener(this);
		termDiseaseYesRadio.setOnCheckedChangeListener(this);
		termDiseaseUnknownRadio.setOnCheckedChangeListener(this);

		otherPresCondiNoRadio.setOnCheckedChangeListener(this);
		otherPresCondiYesRadio.setOnCheckedChangeListener(this);

		endocarditisRadio.setOnCheckedChangeListener(this);
		pericarditisRadio.setOnCheckedChangeListener(this);
		embolusRadio.setOnCheckedChangeListener(this);
		cardiOtherCriRadio.setOnCheckedChangeListener(this);
		cardiOtherNotCriRadio.setOnCheckedChangeListener(this);
		pregTrimesterRadio.setOnCheckedChangeListener(this);
		placentaRadio.setOnCheckedChangeListener(this);
		obsteOtherRelatCriRadio.setOnCheckedChangeListener(this);
		obsteOtherNotCriRadio.setOnCheckedChangeListener(this);

		anticoaHelpBtn.setOnClickListener(this);
		cardioHelpBtn.setOnClickListener(this);
		obstetricHelpBtn.setOnClickListener(this);
		activeBleedYesRadio.setOnClickListener(this);
		anticoaTreatHepaYesRadio.setOnClickListener(this);
		anticoaTreatDabigaYesRadio.setOnClickListener(this);
		anticoaTreatApiksaYesRadio.setOnClickListener(this);
		anticoaTreatDabigaNoRadio.setOnClickListener(this);
		cardioDoneBtn.setOnClickListener(this);
		obstericHELLPRadio.setOnClickListener(this);
		termDiseaseYesRadio.setOnClickListener(this);
		otherPresCondiYesRadio.setOnClickListener(this);
	}

	public void loadUISatus() {
		patientInfoCommon.hideNewPatBtn();
		patientInfoCommon.loadPatientInfo();
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {

			presentConsitionsView.setVisibility(View.GONE);
		}/** Load existing patient status */
		else {
			if (AppViewModel.presentConditionsDAO.isPresentConInfoLoaded() == false)
				AppViewModel.presentConditionsDAO.fetchPresentCondition();
			if (AppViewModel.symptomDAO.isSymptomInfoLoaded() == false)
				AppViewModel.symptomDAO.fetchSymptom();

			presentConsitionsView.setVisibility(View.VISIBLE);
			setUserSelection();
		}
		saveBtn.setEnabled(false);
	}

	public void setUserSelection() {
		activeBleedGroup.check(AppViewModel.presentConditionsDAO
				.getActiveBleedId());
		termDiseaseGroup.check(AppViewModel.symptomDAO.getTermiDiseaseId());
		otherGroup.check(AppViewModel.presentConditionsDAO.getOtherId());

		int tempInt = AppViewModel.symptomDAO.getAnticoagularId();
		anticoagulantGroup.check(tempInt);
		setAnticoaSelection(tempInt, true);

		tempInt = AppViewModel.presentConditionsDAO.getCardiovascularId();
		cardiovascularGroup.check(tempInt);
		setCardioSelection(tempInt, true);

		tempInt = AppViewModel.presentConditionsDAO.getObstetricId();
		obstetricGroup.check(tempInt);
		setObstertricSelection(tempInt, true);

		completeAcuteBleed();
		completeAnticoagulant();
		completeCardiovascular();
		completeObstetric();
		completeTermiDisease();
		completeOtherPresentCondition();
	}

	public void setAnticoaSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_anticoagulant_yes:
			anticoaTreatView.setVisibility(View.VISIBLE);
			int tempId = AppViewModel.presentConditionsDAO.getAnticoaTreatId();
			if (isLoad) {
				anticoaTreatGroup.check(tempId);
			}
			setAnticoaQueSelection(tempId, isLoad);
			break;
		default:
			anticoaHeparinGroup.clearCheck();
			anticoaDabigatranGroup.clearCheck();
			anticoaApiksabanGroup.clearCheck();
			anticoaTreatGroup.clearCheck();
			AppViewModel.presentConditionsDAO.setAnticoaTreatId(0);
			AppViewModel.presentConditionsDAO.setAnticoaHeparinId(0);
			AppViewModel.presentConditionsDAO.setAnticoaDabigatranId(0);
			AppViewModel.presentConditionsDAO.setAnticoaApiksabanId(0);
			AppViewModel.presentConditionsDAO.setAnticoaTreat("");
			AppViewModel.presentConditionsDAO.setAnticoaHeparin("");
			AppViewModel.presentConditionsDAO.setAnticoaDabigatran("");
			AppViewModel.presentConditionsDAO.setAnticoaApiksaban("");
			anticoaTreatView.setVisibility(View.GONE);
			break;

		}
	}

	public void setAnticoaQueSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_anticoagulant_treatment_heparin:
			if (isLoad)
				anticoaHeparinGroup.check(AppViewModel.presentConditionsDAO
						.getAnticoaHeparinId());
			anticoaDabigatranGroup.clearCheck();
			anticoaApiksabanGroup.clearCheck();
			anticoaHeparinView.setVisibility(View.VISIBLE);
			anticoaDabigatranView.setVisibility(View.GONE);
			anticoaApiksabanView.setVisibility(View.GONE);
			break;
		case R.id.radio_anticoagulant_treatment_dabigatran:
			anticoaHeparinGroup.clearCheck();
			if (isLoad)
				anticoaDabigatranGroup.check(AppViewModel.presentConditionsDAO
						.getAnticoaDabigatranId());
			anticoaApiksabanGroup.clearCheck();
			anticoaHeparinView.setVisibility(View.GONE);
			anticoaDabigatranView.setVisibility(View.VISIBLE);
			anticoaApiksabanView.setVisibility(View.GONE);
			break;
		case R.id.radio_anticoagulant_treatment_apiksaban:
			anticoaHeparinGroup.clearCheck();
			anticoaDabigatranGroup.clearCheck();
			if (isLoad)
				anticoaApiksabanGroup.check(AppViewModel.presentConditionsDAO
						.getAnticoaApiksabanId());
			anticoaHeparinView.setVisibility(View.GONE);
			anticoaDabigatranView.setVisibility(View.GONE);
			anticoaApiksabanView.setVisibility(View.VISIBLE);
			break;
		default:
			anticoaHeparinGroup.clearCheck();
			anticoaDabigatranGroup.clearCheck();
			anticoaApiksabanGroup.clearCheck();
			anticoaHeparinView.setVisibility(View.GONE);
			anticoaDabigatranView.setVisibility(View.GONE);
			anticoaApiksabanView.setVisibility(View.GONE);
			break;

		}
	}

	public void setCardioSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_cardiovascular_infections_yes:
			cardiQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.presentConditionsDAO
						.getEndocarditis();
				if (tempInt > 0)
					endocarditisRadio.setChecked(true);
				else
					endocarditisRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO.getPericarditis();
				if (tempInt > 0)
					pericarditisRadio.setChecked(true);
				else
					pericarditisRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO.getEmbolus();
				if (tempInt > 0)
					embolusRadio.setChecked(true);
				else
					embolusRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO.getCardiOtherCri();
				if (tempInt > 0)
					cardiOtherCriRadio.setChecked(true);
				else
					cardiOtherCriRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO
						.getCardiOtherNotCri();
				if (tempInt > 0)
					cardiOtherNotCriRadio.setChecked(true);
				else
					cardiOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			endocarditisRadio.setChecked(false);
			pericarditisRadio.setChecked(false);
			embolusRadio.setChecked(false);
			cardiOtherCriRadio.setChecked(false);
			cardiOtherNotCriRadio.setChecked(false);

			cardiQuesView.setVisibility(View.GONE);
			break;

		}
	}

	public void setObstertricSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_obstetric_contraindications_yes_relative:
			obstetricQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.presentConditionsDAO
						.getPregTrimester();
				if (tempInt > 0)
					pregTrimesterRadio.setChecked(true);
				else
					pregTrimesterRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO.getPlacenta();
				if (tempInt > 0)
					placentaRadio.setChecked(true);
				else
					placentaRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO
						.getObsteOtherRelatCri();
				if (tempInt > 0)
					obsteOtherRelatCriRadio.setChecked(true);
				else
					obsteOtherRelatCriRadio.setChecked(false);

				tempInt = AppViewModel.presentConditionsDAO
						.getObsteOtherNotCri();
				if (tempInt > 0)
					obsteOtherNotCriRadio.setChecked(true);
				else
					obsteOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			pregTrimesterRadio.setChecked(false);
			placentaRadio.setChecked(false);
			obsteOtherRelatCriRadio.setChecked(false);
			obsteOtherNotCriRadio.setChecked(false);
			obstetricQuesView.setVisibility(View.GONE);
			break;

		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		saveBtn.setEnabled(true);
		RadioButton r = null;
		switch (group.getId()) {
		case R.id.radio_group_active_bleeding:
			r = (RadioButton) rootView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO.setActiveBleedId(checkedId);
				AppViewModel.presentConditionsDAO.setActiveBleed(r.getText()
						.toString());
			}
			completeAcuteBleed();
			break;
		case R.id.radio_group_anticoagulant_medication:
			r = (RadioButton) anticoagulantView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.symptomDAO.setAnticoagularId(checkedId);
				AppViewModel.symptomDAO.setAnticoagular(r.getText().toString());
				setAnticoaSelection(checkedId, false);
			}
			completeAnticoagulant();
			break;
		case R.id.radio_group_anticoagulant_treatment:
			r = (RadioButton) anticoagulantView.findViewById(checkedId);
			
			/** Clear old info */
			AppViewModel.presentConditionsDAO.setAnticoaHeparinId(0);
			AppViewModel.presentConditionsDAO.setAnticoaHeparin("");
			AppViewModel.presentConditionsDAO.setAnticoaDabigatranId(0);
			AppViewModel.presentConditionsDAO.setAnticoaDabigatran("");
			AppViewModel.presentConditionsDAO.setAnticoaApiksabanId(0);
			AppViewModel.presentConditionsDAO.setAnticoaApiksaban("");
			if (r != null) {
				AppViewModel.presentConditionsDAO.setAnticoaTreatId(checkedId);
				AppViewModel.presentConditionsDAO.setAnticoaTreat(r.getText()
						.toString());
				setAnticoaQueSelection(checkedId, false);
			}
			completeAnticoagulant();
			break;
		case R.id.radio_group_heparin:
			r = (RadioButton) anticoagulantView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO
						.setAnticoaHeparinId(checkedId);
				AppViewModel.presentConditionsDAO.setAnticoaHeparin(r.getText()
						.toString());
			}
			completeAnticoagulant();
			break;
		case R.id.radio_group_dabigatran:
			r = (RadioButton) anticoagulantView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO
						.setAnticoaDabigatranId(checkedId);
				AppViewModel.presentConditionsDAO.setAnticoaDabigatran(r
						.getText().toString());
			}
			completeAnticoagulant();
			break;
		case R.id.radio_group_apiksaban:
			r = (RadioButton) anticoagulantView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO
						.setAnticoaApiksabanId(checkedId);
				AppViewModel.presentConditionsDAO.setAnticoaApiksaban(r
						.getText().toString());
			}
			completeAnticoagulant();
			break;
		case R.id.radio_group_cardiovascular_infections:
			r = (RadioButton) cardiovascularView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO
						.setCardiovascularId(checkedId);
				AppViewModel.presentConditionsDAO.setCardiovascular(r.getText()
						.toString());
				setCardioSelection(checkedId, false);
			}
			completeCardiovascular();
			break;
		case R.id.radio_group_obstetric_contraindications:
			r = (RadioButton) obstetricView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO.setObstetricId(checkedId);
				AppViewModel.presentConditionsDAO.setObstetric(r.getText()
						.toString());
				setObstertricSelection(checkedId, false);
			}
			completeObstetric();
			break;
		case R.id.radio_group_terminal_disease:
			r = (RadioButton) rootView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.symptomDAO.setTermiDiseaseId(checkedId);
				AppViewModel.symptomDAO.setTermiDisease(r.getText().toString());
			}
			completeTermiDisease();
			break;
		case R.id.radio_group_other_present_condition:
			r = (RadioButton) rootView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.presentConditionsDAO.setOtherId(checkedId);
				AppViewModel.presentConditionsDAO.setOther(r.getText()
						.toString());
			}
			completeOtherPresentCondition();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		saveBtn.setEnabled(true);
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.radio_active_bleeding_no:
			case R.id.radio_active_bleeding_unknown:
			case R.id.radio_anticoagulant_no:
			case R.id.radio_anticoagulant_unknown:
			case R.id.radio_anticoagulant_treatment_unknown:
			case R.id.radio_cardiovascular_infections_no:
			case R.id.radio_cardiovascular_infections_unknown:
			case R.id.radio_cardiovascular_infections_other_not_critical:
			case R.id.radio_obstetric_contraindications_no:
			case R.id.radio_obstetric_contraindications_unknown:
			case R.id.radio_obstetric_contraindications_other_not_critical:
			case R.id.radio_terminal_disease_no:
			case R.id.radio_terminal_disease_unknown:
			case R.id.radio_other_present_condition_no:
			case R.id.radio_dabigatran_no:
			case R.id.radio_apiksaban_no:
				bgColor = getResources().getColor(R.color.green);
				break;
			case R.id.radio_anticoagulant_yes:
			case R.id.radio_anticoagulant_treatment_heparin:
			case R.id.radio_anticoagulant_treatment_dabigatran:
			case R.id.radio_anticoagulant_treatment_apiksaban:
			case R.id.radio_anticoagulant_treatment_other:
			case R.id.radio_heparin_no:
			case R.id.radio_obstetric_contraindications_yes_relative:
			case R.id.radio_obstetric_contraindications_pregnancy:
			case R.id.radio_obstetric_contraindications_placenta:
			case R.id.radio_obstetric_contraindications_other_relative_critical:
				bgColor = getResources().getColor(R.color.yellow);
				break;
			case R.id.radio_active_bleeding_yes:
			case R.id.radio_heparin_yes:
			case R.id.radio_cardiovascular_infections_yes:
			case R.id.radio_cardiovascular_infections_endocarditis:
			case R.id.radio_cardiovascular_infections_pericarditis:
			case R.id.radio_cardiovascular_infections_embolus:
			case R.id.radio_cardiovascular_infections_other_critical:
			case R.id.radio_obstetric_contraindications_hellp:
			case R.id.radio_terminal_disease_yes:
			case R.id.radio_other_present_condition_yes:
			case R.id.radio_dabigatran_yes:
			case R.id.radio_apiksaban_yes:
				bgColor = getResources().getColor(R.color.red);
				break;
			}
		}
		/** Checkboxs */
		switch (buttonView.getId()) {
		case R.id.radio_cardiovascular_infections_endocarditis:
			cardioDoneBtn.setEnabled(true);
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setEndocarditis(R.string.radio_cardiovascular_infections_endocarditis);
			} else
				AppViewModel.presentConditionsDAO.setEndocarditis(0);
			break;
		case R.id.radio_cardiovascular_infections_pericarditis:
			cardioDoneBtn.setEnabled(true);
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setPericarditis(R.string.radio_cardiovascular_infections_pericarditis);
			} else
				AppViewModel.presentConditionsDAO.setPericarditis(0);

			break;
		case R.id.radio_cardiovascular_infections_embolus:
			cardioDoneBtn.setEnabled(true);
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setEmbolus(R.string.radio_cardiovascular_infections_embolus);
			} else
				AppViewModel.presentConditionsDAO.setEmbolus(0);

			break;
		case R.id.radio_cardiovascular_infections_other_critical:
			cardioDoneBtn.setEnabled(true);
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setCardiOtherCri(R.string.radio_other_critical);
			} else
				AppViewModel.presentConditionsDAO.setCardiOtherCri(0);

			break;
		case R.id.radio_cardiovascular_infections_other_not_critical:
			cardioDoneBtn.setEnabled(true);
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setCardiOtherNotCri(R.string.radio_other_not_critical);
			} else
				AppViewModel.presentConditionsDAO.setCardiOtherNotCri(0);

			break;
		case R.id.radio_obstetric_contraindications_pregnancy:
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setPregTrimester(R.string.radio_obstetric_contraindications_pregnancy);
			} else
				AppViewModel.presentConditionsDAO.setPregTrimester(0);
			completeObstetric();
			break;
		case R.id.radio_obstetric_contraindications_placenta:
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setPlacenta(R.string.radio_obstetric_contraindications_placenta);
			} else
				AppViewModel.presentConditionsDAO.setPlacenta(0);
			completeObstetric();
			break;
		case R.id.radio_obstetric_contraindications_other_relative_critical:
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setObsteOtherRelatCri(R.string.radio_other_relative_critical);
			} else
				AppViewModel.presentConditionsDAO.setObsteOtherRelatCri(0);
			completeObstetric();
			break;
		case R.id.radio_obstetric_contraindications_other_not_critical:
			if (isChecked) {
				AppViewModel.presentConditionsDAO
						.setObsteOtherNotCri(R.string.radio_other_not_critical);
				bgColor = getResources().getColor(R.color.red);
			} else
				AppViewModel.presentConditionsDAO.setObsteOtherNotCri(0);
			completeObstetric();
			break;
		}
		buttonView.setBackgroundColor(bgColor);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(saveBtn)) {
			// AppViewModel.presentConditionsDAO.saveData();
			Toast toast = Toast.makeText(getActivity(),
					R.string.toast_data_saved, Toast.LENGTH_LONG);
			toast.show();
			saveBtn.setEnabled(false);
		} else if (v.equals(activeBleedYesRadio)) {
			Intent interrupIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.rr_hypertension_warning_title));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_active_bleed));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_active_bleed);
			getActivity().startActivityForResult(interrupIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(anticoaTreatHepaYesRadio)) {
			Intent interrupIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.rr_hypertension_warning_title));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_heparin));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.info_symptom_anticoagulant);
			interrupIntent.putExtra(
					DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
					getString(R.string.reason_interr_heparin));
			getActivity().startActivityForResult(interrupIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(anticoaTreatDabigaYesRadio)) {
			int aptt = AppViewModel.labDAO.getAPTT();
			int pTrombai = AppViewModel.labDAO.getPTrombai();
			String contentStr = "";
			String reasonStr = "";

			contentStr += getString(R.string.info_interr_dabigatran) + "\n";
			reasonStr += getString(R.string.reason_interr_dabigatran) + ", ";

			if ((aptt >= 30 && aptt <= 60) || pTrombai >= 26) {
				if (aptt >= 30 && aptt <= 60) {
					contentStr += getString(R.string.info_interr_dabigatran_aptt)
							+ "\n";
					reasonStr += getString(R.string.reason_interr_dabi_aptt)
							+ ", ";
				}
				if (pTrombai >= 26) {
					contentStr += getString(R.string.info_interr_dabigatran_p_trombai)
							+ "\n";
					reasonStr += getString(R.string.reason_interr_dabi_p_trom)
							+ ", ";
				}
				contentStr += "\nWhich contraindicating thrombolysis. Do you want to interrupt the process?";
			}
			Intent interrIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.interruption_warning_title));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					contentStr);
			interrIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.info_symptom_anticoagulant);
			interrIntent.putExtra(
					DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR, reasonStr);
			interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(anticoaTreatDabigaNoRadio)) {
			int aptt = AppViewModel.labDAO.getAPTT();
			int pTrombai = AppViewModel.labDAO.getPTrombai();

			if ((aptt >= 30 && aptt <= 60) || pTrombai >= 26) {
				String contentStr = "";
				String reasonStr = "";

				if (aptt >= 30 && aptt <= 60) {
					contentStr += getString(R.string.info_interr_dabigatran_aptt)
							+ "\n";
					reasonStr += getString(R.string.reason_interr_dabi_aptt)
							+ ", ";
				}
				if (pTrombai >= 26) {
					contentStr += getString(R.string.info_interr_dabigatran_p_trombai)
							+ "\n";
					reasonStr += getString(R.string.reason_interr_dabi_p_trom)
							+ ", ";
				}
				contentStr += "\nWhich contraindicating thrombolysis. Do you want to interrupt the process?";
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT, contentStr);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_symptom_anticoagulant);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
				// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(anticoaTreatApiksaYesRadio)) {
			Intent interrupIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.rr_hypertension_warning_title));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_apiksaban));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.info_symptom_anticoagulant);
			interrupIntent.putExtra(
					DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
					getString(R.string.reason_interr_apiksaban));
			getActivity().startActivityForResult(interrupIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(cardioDoneBtn)) {
			cardioDoneBtn.setEnabled(false);
			completeCardiovascular();
			int endocarditis = AppViewModel.presentConditionsDAO
					.getEndocarditis();
			int pericarditis = AppViewModel.presentConditionsDAO
					.getPericarditis();
			int embolus = AppViewModel.presentConditionsDAO.getEmbolus();
			int cardioOtherCritical = AppViewModel.presentConditionsDAO
					.getCardiOtherCri();
			String reasonStr = "";

			if (endocarditis > 0 || pericarditis > 0 || embolus > 0
					|| cardioOtherCritical > 0) {
				if (endocarditis > 0) {
					reasonStr += getString(R.string.radio_cardiovascular_infections_endocarditis)
							+ ", ";
				}
				if (pericarditis > 0) {
					reasonStr += getString(R.string.radio_cardiovascular_infections_pericarditis)
							+ ", ";
				}
				if (embolus > 0) {
					reasonStr += getString(R.string.radio_cardiovascular_infections_embolus)
							+ ", ";
				}
				if (cardioOtherCritical > 0) {
					reasonStr += "other critical cardiovascular infections, ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_cardiovascular));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_cardiovascular);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
				// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(obstericHELLPRadio)) {
			Intent interrupIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.rr_hypertension_warning_title));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_hellp));
			interrupIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_hellp);
			getActivity().startActivityForResult(interrupIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(termDiseaseYesRadio)) {
			Intent interrIntent = new Intent(getActivity(),
					DialogCommonInterrupt.class);
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
					getString(R.string.interruption_warning_title));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_CONTENT,
					getString(R.string.info_interr_ter_illness));
			interrIntent.putExtra(DialogCommonInterrupt.TAG_INTERRUPT_REASON,
					R.string.reason_interr_ter_illness);
			// interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		} else if (v.equals(otherPresCondiYesRadio)) {
			Intent interrIntent = new Intent(getActivity(),
					DialogOtherPreCondiInterr.class);
//			interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivityForResult(interrIntent,
					MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
		}else {
			Intent intent = new Intent(getActivity(), DialogHelpInfo.class);

			switch (v.getId()) {
			case R.id.imgbtn_help_anticoagulant_medication:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.help_title_anticoagulant));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_anticoagulant));
				break;
			case R.id.imgbtn_help_cardiovascular_infections:
				intent.putExtra(
						DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_present_cardiovascular_infections));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_cardiovascular));
				break;
			case R.id.imgbtn_help_obstetric_contraindications:
				intent.putExtra(
						DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_present_obstetric_contraindications));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_obstetri));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivity(intent);
		}
	}

	public void completeAcuteBleed() {
		int acuteBleed = AppViewModel.presentConditionsDAO.getActiveBleedId();
		if (acuteBleed > 0) {
			acuteBleedComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			acuteBleedComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

	public void completeAnticoagulant() {
		if (AppViewModel.presentConditionsDAO.isAnticoagulantComplete()) {
			anticoagulantComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			anticoagulantComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

	public void completeCardiovascular() {
		if (AppViewModel.presentConditionsDAO.isCardioComplete()) {
			cardioComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			cardioComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

	public void completeObstetric() {
		if (AppViewModel.presentConditionsDAO.isObstetricComplete()) {
			obstetricComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			obstetricComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

	public void completeTermiDisease() {
		int terDisease = AppViewModel.symptomDAO.getTermiDiseaseId();
		if (terDisease > 0) {
			termiDiseaseComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			termiDiseaseComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

	public void completeOtherPresentCondition() {
		int other = AppViewModel.presentConditionsDAO.getOtherId();
		if (other > 0) {
			otherPresComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			otherPresComple.setBackgroundColor(getResources().getColor(
					R.color.grey));
		}
	}

}
