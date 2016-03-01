package fi.etla.strokehelper.process;

import org.apache.http.util.LangUtils;

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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.DialogCommonInterrupt;
import fi.etla.strokehelper.common.DialogHelpInfo;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.ItemDetailActivity;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.menu.MenusLevOne;

public class FragmentAnamnesis extends Fragment implements
		OnCheckedChangeListener,
		android.widget.CompoundButton.OnCheckedChangeListener, OnClickListener {
	private CommonPatientInfo patientInfoCommon;
	private View rootView;
	private View anamnesisView;
	private View cerebroIncidentView;
	private View diseaseView;
	private View bleedingView;
	private View operationView;
	private View vesselView;
	private View cerebroIncidentQuesView;
	private View diseaseQuesView;
	private View bleedingQuesView;
	private View operationQuesView;
	private View vesselQuesView;

	private Button saveBtn;

	private RadioGroup cerebroIncidentGroup;
	private RadioGroup diseaseGroup;
	private RadioGroup bleedingGroup;
	private RadioGroup operationGroup;
	private RadioGroup vesselGroup;
	private RadioGroup myocarInfarctionGroup;

	private RadioButton cerebroIncidentNoRadio;
	private RadioButton cerebroIncidentYesRadio;
	private RadioButton cerebroIncidentUnknownRadio;
	private RadioButton diseaseNoRadio;
	private RadioButton diseaseYesRadio;
	private RadioButton diseaseUnknownRadio;
	private RadioButton bleedingNoRadio;
	private RadioButton bleedingYesRadio;
	private RadioButton bleedingUnknownRadio;
	private RadioButton operationNoRadio;
	private RadioButton operationYesRadio;
	private RadioButton operationUnknownRadio;
	private RadioButton vesselNoRadio;
	private RadioButton vesselYesRadio;
	private RadioButton vesselUnknownRadio;
	private RadioButton myocarInfarctionNoRadio;
	private RadioButton myocarInfarctionYesRadio;
	private RadioButton myocarInfarctionUnknownRadio;

	private CheckBox sahRadio;
	private CheckBox intracHemorrhageRadio;
	private CheckBox headTraumaRadio;
	private CheckBox wideStrokeRadio;
	private CheckBox minorStrokeRadio;
	private CheckBox cerebroOtherCriRadio;
	private CheckBox cerebroOtherNotCriRadio;

	private CheckBox hepatopathyRadio;
	private CheckBox retinopathyRadio;
	private CheckBox pancreatitisRadio;
	private CheckBox tumourRadio;
	private CheckBox diseaseOtherCriRadio;
	private CheckBox diseaseOtherNotCriRadio;

	private CheckBox traumaRadio;
	private CheckBox otherBleedingRadio;
	private CheckBox otherGIBleedingRadio;
	private CheckBox ulcusRadio;
	private CheckBox varicesRadio;
	private CheckBox tractHemorrahgeRadio;
	private CheckBox bleedingOtherNotCriRadio;

	private CheckBox surgicalOperRadio;
	private CheckBox neurosurgicalOperRadio;
	private CheckBox opthalmologicalOperRadio;
	private CheckBox punctionRadio;
	private CheckBox massageRadio;
	private CheckBox parturitionRadio;
	private CheckBox operationOtherCriRadio;
	private CheckBox operationOtherNotCriRadio;

	private CheckBox microangiopathyRadio;
	private CheckBox avmRadio;
	private CheckBox aneurysmRadio;
	private CheckBox otherVesselAnomalRadio;
	private CheckBox vesselOtherCriRadio;
	private CheckBox vesselOtherNotCriRadio;

	private ImageButton cerebroHelpBtn;
	private ImageButton diseaseHelpBtn;
	private ImageButton bleedingHelpBtn;
	private ImageButton operationHelpBtn;
	private ImageButton vesselHelpBtn;

	private Button cerebroDoneBtn;
	private Button diseaseDoneBtn;
	private Button bleedingDoneBtn;
	private Button operationDoneBtn;
	private Button vesselDoneBtn;
	
	private TextView cerebroComple;
	private TextView diseaseComple;
	private TextView bleedingComple;
	private TextView operationComple;
	private TextView vesselComple;
	private TextView myocardialComple;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentAnamnesis() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_tab_anamnesis,
				container, false);

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
				FragmentAnamnesis.this);
		anamnesisView = (View) rootView.findViewById(R.id.row_two);

		saveBtn = (Button) rootView.findViewById(R.id.imgbtn_save);
		saveBtn.setOnClickListener(this);

		/** Cerebrovascular Incidents info */
		cerebroIncidentView = (View) rootView
				.findViewById(R.id.view_cerebrovascular_incidents);
		cerebroIncidentQuesView = (View) cerebroIncidentView
				.findViewById(R.id.view_quesion_cerebrovascular_incidents);
		cerebroIncidentGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_cerebrovascular_incidents);

		sahRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_1);
		intracHemorrhageRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_2);
		headTraumaRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_4);
		wideStrokeRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_3);
		minorStrokeRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_5);
		cerebroOtherCriRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_6);
		cerebroOtherNotCriRadio = (CheckBox) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_7);

		cerebroIncidentNoRadio = (RadioButton) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_no);
		cerebroIncidentYesRadio = (RadioButton) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_yes);
		cerebroIncidentUnknownRadio = (RadioButton) cerebroIncidentView
				.findViewById(R.id.radio_cerebrovascular_incidents_unknown);
		cerebroComple = (TextView) cerebroIncidentView.findViewById(R.id.comple_cerebrovascular_incidents);

		/** Disease increase bleeding tendency info */
		diseaseView = (View) rootView.findViewById(R.id.view_diseases);
		diseaseQuesView = (View) diseaseView
				.findViewById(R.id.view_quesion_disease_bleeding_tendency);
		diseaseGroup = (RadioGroup) diseaseView
				.findViewById(R.id.radio_group_disease_bleeding_tendency);

		hepatopathyRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_1);
		retinopathyRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_2);
		pancreatitisRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_3);
		tumourRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_4);
		diseaseOtherCriRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_5);
		diseaseOtherNotCriRadio = (CheckBox) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_6);

		diseaseNoRadio = (RadioButton) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_no);
		diseaseYesRadio = (RadioButton) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_yes);
		diseaseUnknownRadio = (RadioButton) diseaseView
				.findViewById(R.id.radio_disease_bleeding_tendency_unknown);
		diseaseComple = (TextView) diseaseView.findViewById(R.id.comple_disease_bleeding_tendency);

		/** Significant bleeding Info */
		bleedingView = (View) rootView.findViewById(R.id.view_bleeding);
		bleedingQuesView = (View) bleedingView
				.findViewById(R.id.view_quesion_significant_bleeding);
		bleedingGroup = (RadioGroup) bleedingView
				.findViewById(R.id.radio_group_significant_bleeding);

		traumaRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_1);
		otherBleedingRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_2);
		otherGIBleedingRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_4);
		ulcusRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_3);
		varicesRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_5);
		tractHemorrahgeRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_6);
		bleedingOtherNotCriRadio = (CheckBox) bleedingView
				.findViewById(R.id.radio_significant_bleeding_7);

		bleedingNoRadio = (RadioButton) bleedingView
				.findViewById(R.id.radio_significant_bleeding_no);
		bleedingYesRadio = (RadioButton) bleedingView
				.findViewById(R.id.radio_significant_bleeding_yes);
		bleedingUnknownRadio = (RadioButton) bleedingView
				.findViewById(R.id.radio_significant_bleeding_unknown);
		bleedingComple = (TextView) bleedingView.findViewById(R.id.comple_significant_bleeding);

		/** Operation Info */
		operationView = (View) rootView.findViewById(R.id.view_operation);
		operationQuesView = (View) operationView
				.findViewById(R.id.view_quesion_operation);
		operationGroup = (RadioGroup) operationView
				.findViewById(R.id.radio_group_operation);

		surgicalOperRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_1);
		neurosurgicalOperRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_2);
		opthalmologicalOperRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_3);
		punctionRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_4);
		massageRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_5);
		parturitionRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_6);
		operationOtherCriRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_7);
		operationOtherNotCriRadio = (CheckBox) operationView
				.findViewById(R.id.radio_operation_8);

		operationNoRadio = (RadioButton) operationView
				.findViewById(R.id.radio_operation_no);
		operationYesRadio = (RadioButton) operationView
				.findViewById(R.id.radio_operation_yes);
		operationUnknownRadio = (RadioButton) operationView
				.findViewById(R.id.radio_operation_unknown);
		operationComple = (TextView) operationView.findViewById(R.id.comple_operation);

		/** Vessel Conditions Info */
		vesselView = (View) rootView.findViewById(R.id.view_vessel);
		vesselQuesView = (View) vesselView
				.findViewById(R.id.view_quesion_vessel_conditions);
		vesselGroup = (RadioGroup) vesselView
				.findViewById(R.id.radio_group_blood_vessel_conditions);

		microangiopathyRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_1);
		avmRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_2);
		aneurysmRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_3);
		otherVesselAnomalRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_4);
		vesselOtherCriRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_5);
		vesselOtherNotCriRadio = (CheckBox) vesselView
				.findViewById(R.id.radio_vessel_conditions_6);

		vesselNoRadio = (RadioButton) vesselView
				.findViewById(R.id.radio_blood_vessel_conditions_no);
		vesselYesRadio = (RadioButton) vesselView
				.findViewById(R.id.radio_blood_vessel_conditions_yes);
		vesselUnknownRadio = (RadioButton) vesselView
				.findViewById(R.id.radio_blood_vessel_conditions_unknown);
		vesselComple = (TextView) vesselView.findViewById(R.id.comple_blood_vessel);

		/** Myocardial infarction info */
		myocarInfarctionGroup = (RadioGroup) rootView
				.findViewById(R.id.radio_group_acute_myocardial_infarction);

		myocarInfarctionNoRadio = (RadioButton) rootView
				.findViewById(R.id.radio_acute_myocardial_infarction_no);
		myocarInfarctionYesRadio = (RadioButton) rootView
				.findViewById(R.id.radio_acute_myocardial_infarction_yes);
		myocarInfarctionUnknownRadio = (RadioButton) rootView
				.findViewById(R.id.radio_acute_myocardial_infarction_unknown);
		myocardialComple = (TextView) rootView.findViewById(R.id.comple_acute_myocardial_infarction);

		cerebroHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_cerebrovascular_incidents);
		diseaseHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_disease_bleeding_tendency);
		bleedingHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_significant_bleeding);
		operationHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_operation);
		vesselHelpBtn = (ImageButton) rootView
				.findViewById(R.id.imgbtn_help_blood_vessel_conditions);

		cerebroDoneBtn = (Button) rootView
				.findViewById(R.id.imgbtn_done_cerebrovascular);
		diseaseDoneBtn = (Button) rootView
				.findViewById(R.id.imgbtn_done_disease);
		bleedingDoneBtn = (Button) rootView
				.findViewById(R.id.imgbtn_done_bleeding);
		operationDoneBtn = (Button) rootView
				.findViewById(R.id.imgbtn_done_operation);
		vesselDoneBtn = (Button) rootView.findViewById(R.id.imgbtn_done_vessel);
		cerebroDoneBtn.setEnabled(false);
		diseaseDoneBtn.setEnabled(false);
		bleedingDoneBtn.setEnabled(false);
		operationDoneBtn.setEnabled(false);
		vesselDoneBtn.setEnabled(false);

		/** Register Listeners */
		cerebroIncidentGroup.setOnCheckedChangeListener(this);
		myocarInfarctionGroup.setOnCheckedChangeListener(this);
		diseaseGroup.setOnCheckedChangeListener(this);
		bleedingGroup.setOnCheckedChangeListener(this);
		operationGroup.setOnCheckedChangeListener(this);
		vesselGroup.setOnCheckedChangeListener(this);
		pancreatitisRadio.setOnCheckedChangeListener(this);
		tumourRadio.setOnCheckedChangeListener(this);
		diseaseOtherCriRadio.setOnCheckedChangeListener(this);
		diseaseOtherNotCriRadio.setOnCheckedChangeListener(this);
		traumaRadio.setOnCheckedChangeListener(this);
		otherBleedingRadio.setOnCheckedChangeListener(this);
		otherGIBleedingRadio.setOnCheckedChangeListener(this);
		ulcusRadio.setOnCheckedChangeListener(this);
		varicesRadio.setOnCheckedChangeListener(this);
		tractHemorrahgeRadio.setOnCheckedChangeListener(this);
		bleedingOtherNotCriRadio.setOnCheckedChangeListener(this);
		surgicalOperRadio.setOnCheckedChangeListener(this);
		neurosurgicalOperRadio.setOnCheckedChangeListener(this);
		opthalmologicalOperRadio.setOnCheckedChangeListener(this);
		punctionRadio.setOnCheckedChangeListener(this);
		parturitionRadio.setOnCheckedChangeListener(this);
		massageRadio.setOnCheckedChangeListener(this);
		operationOtherCriRadio.setOnCheckedChangeListener(this);
		operationOtherNotCriRadio.setOnCheckedChangeListener(this);
		microangiopathyRadio.setOnCheckedChangeListener(this);
		avmRadio.setOnCheckedChangeListener(this);
		aneurysmRadio.setOnCheckedChangeListener(this);
		otherVesselAnomalRadio.setOnCheckedChangeListener(this);
		vesselOtherCriRadio.setOnCheckedChangeListener(this);
		vesselOtherNotCriRadio.setOnCheckedChangeListener(this);
		sahRadio.setOnCheckedChangeListener(this);
		intracHemorrhageRadio.setOnCheckedChangeListener(this);
		headTraumaRadio.setOnCheckedChangeListener(this);
		wideStrokeRadio.setOnCheckedChangeListener(this);
		minorStrokeRadio.setOnCheckedChangeListener(this);
		cerebroOtherCriRadio.setOnCheckedChangeListener(this);
		cerebroOtherNotCriRadio.setOnCheckedChangeListener(this);
		hepatopathyRadio.setOnCheckedChangeListener(this);
		retinopathyRadio.setOnCheckedChangeListener(this);

		cerebroIncidentNoRadio.setOnCheckedChangeListener(this);
		cerebroIncidentYesRadio.setOnCheckedChangeListener(this);
		cerebroIncidentUnknownRadio.setOnCheckedChangeListener(this);
		diseaseNoRadio.setOnCheckedChangeListener(this);
		diseaseYesRadio.setOnCheckedChangeListener(this);
		diseaseUnknownRadio.setOnCheckedChangeListener(this);
		bleedingNoRadio.setOnCheckedChangeListener(this);
		bleedingYesRadio.setOnCheckedChangeListener(this);
		bleedingUnknownRadio.setOnCheckedChangeListener(this);
		operationNoRadio.setOnCheckedChangeListener(this);
		operationYesRadio.setOnCheckedChangeListener(this);
		operationUnknownRadio.setOnCheckedChangeListener(this);
		vesselNoRadio.setOnCheckedChangeListener(this);
		vesselYesRadio.setOnCheckedChangeListener(this);
		vesselUnknownRadio.setOnCheckedChangeListener(this);
		myocarInfarctionNoRadio.setOnCheckedChangeListener(this);
		myocarInfarctionYesRadio.setOnCheckedChangeListener(this);
		myocarInfarctionUnknownRadio.setOnCheckedChangeListener(this);

		cerebroHelpBtn.setOnClickListener(this);
		diseaseHelpBtn.setOnClickListener(this);
		bleedingHelpBtn.setOnClickListener(this);
		operationHelpBtn.setOnClickListener(this);
		vesselHelpBtn.setOnClickListener(this);

		cerebroDoneBtn.setOnClickListener(this);
		diseaseDoneBtn.setOnClickListener(this);
		bleedingDoneBtn.setOnClickListener(this);
		operationDoneBtn.setOnClickListener(this);
		vesselDoneBtn.setOnClickListener(this);
	}

	public void loadUISatus() {
		patientInfoCommon.hideNewPatBtn();
		patientInfoCommon.loadPatientInfo();
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {

			anamnesisView.setVisibility(View.GONE);
		}/** Load existing patient status */
		else {
			if (AppViewModel.anamnesisDAO.isAnamnesisInfoLoaded() == false)
				AppViewModel.anamnesisDAO.fetchAnamnesis();

			anamnesisView.setVisibility(View.VISIBLE);
			setUserSelection();
		}
		saveBtn.setEnabled(false);
	}

	public void setUserSelection() {
		int tempInt = AppViewModel.anamnesisDAO.getCerebroIncidentId();
		cerebroIncidentGroup.check(tempInt);
		setCerebroSelection(tempInt, true);

		tempInt = AppViewModel.anamnesisDAO.getDiseaseId();
		diseaseGroup.check(tempInt);
		setDiseaseSelection(tempInt, true);

		tempInt = AppViewModel.anamnesisDAO.getBleedingId();
		bleedingGroup.check(tempInt);
		setBleedingSelection(tempInt, true);

		tempInt = AppViewModel.anamnesisDAO.getOperationId();
		operationGroup.check(tempInt);
		setOperationSelection(tempInt, true);

		tempInt = AppViewModel.anamnesisDAO.getVesselId();
		vesselGroup.check(tempInt);
		setVesselSelection(tempInt, true);

		myocarInfarctionGroup.check(AppViewModel.anamnesisDAO
				.getMyocarInfarctionId());
		
		completeCerebro();
		completeDisease();
		completeBleeding();
		completeOperation();
		completeVessel();
		completeMyocardial();
	}

	public void setCerebroSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_cerebrovascular_incidents_yes:
			cerebroIncidentQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.anamnesisDAO.getSAH();
				if (tempInt > 0)
					sahRadio.setChecked(true);
				else
					sahRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getIntracHemorrhage();
				if (tempInt > 0)
					intracHemorrhageRadio.setChecked(true);
				else
					intracHemorrhageRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getHeadTrauma();
				if (tempInt > 0)
					headTraumaRadio.setChecked(true);
				else
					headTraumaRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getWideStroke();
				if (tempInt > 0)
					wideStrokeRadio.setChecked(true);
				else
					wideStrokeRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getMinorStroke();
				if (tempInt > 0)
					minorStrokeRadio.setChecked(true);
				else
					minorStrokeRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getCerebroOtherCri();
				if (tempInt > 0)
					cerebroOtherCriRadio.setChecked(true);
				else
					cerebroOtherCriRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getCerebroOtherNotCri();
				if (tempInt > 0)
					cerebroOtherNotCriRadio.setChecked(true);
				else
					cerebroOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			sahRadio.setChecked(false);
			intracHemorrhageRadio.setChecked(false);
			headTraumaRadio.setChecked(false);
			wideStrokeRadio.setChecked(false);
			minorStrokeRadio.setChecked(false);
			cerebroOtherCriRadio.setChecked(false);
			cerebroOtherNotCriRadio.setChecked(false);
			cerebroIncidentQuesView.setVisibility(View.GONE);
			break;
		}
	}

	public void setDiseaseSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_disease_bleeding_tendency_yes:
			diseaseQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.anamnesisDAO.getHepatopathy();
				if (tempInt > 0)
					hepatopathyRadio.setChecked(true);
				else
					hepatopathyRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getRetinopathy();
				if (tempInt > 0)
					retinopathyRadio.setChecked(true);
				else
					retinopathyRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getPancreatitis();
				if (tempInt > 0)
					pancreatitisRadio.setChecked(true);
				else
					pancreatitisRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getTumour();
				if (tempInt > 0)
					tumourRadio.setChecked(true);
				else
					tumourRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getDiseaseOtherCri();
				if (tempInt > 0)
					diseaseOtherCriRadio.setChecked(true);
				else
					diseaseOtherCriRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getDiseaseOtherNotCri();
				if (tempInt > 0)
					diseaseOtherNotCriRadio.setChecked(true);
				else
					diseaseOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			hepatopathyRadio.setChecked(false);
			retinopathyRadio.setChecked(false);
			pancreatitisRadio.setChecked(false);
			tumourRadio.setChecked(false);
			diseaseOtherCriRadio.setChecked(false);
			diseaseOtherNotCriRadio.setChecked(false);
			diseaseQuesView.setVisibility(View.GONE);
			break;
		}
	}

	public void setBleedingSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_significant_bleeding_yes:
			bleedingQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.anamnesisDAO.getTrauma();
				if (tempInt > 0)
					traumaRadio.setChecked(true);
				else
					traumaRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOtherBleeding();
				if (tempInt > 0)
					otherBleedingRadio.setChecked(true);
				else
					otherBleedingRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getUlcus();
				if (tempInt > 0)
					ulcusRadio.setChecked(true);
				else
					ulcusRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOtherGIBleeding();
				if (tempInt > 0)
					otherGIBleedingRadio.setChecked(true);
				else
					otherGIBleedingRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getVarices();
				if (tempInt > 0)
					varicesRadio.setChecked(true);
				else
					varicesRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getTractHemorrahge();
				if (tempInt > 0)
					tractHemorrahgeRadio.setChecked(true);
				else
					tractHemorrahgeRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getBleedingOtherNotCri();
				if (tempInt > 0)
					bleedingOtherNotCriRadio.setChecked(true);
				else
					bleedingOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			traumaRadio.setChecked(false);
			otherBleedingRadio.setChecked(false);
			ulcusRadio.setChecked(false);
			otherGIBleedingRadio.setChecked(false);
			varicesRadio.setChecked(false);
			tractHemorrahgeRadio.setChecked(false);
			bleedingOtherNotCriRadio.setChecked(false);
			bleedingQuesView.setVisibility(View.GONE);
			break;
		}
	}

	public void setOperationSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_operation_yes:
			operationQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.anamnesisDAO.getSurgicalOper();
				if (tempInt > 0)
					surgicalOperRadio.setChecked(true);
				else
					surgicalOperRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getNeurosurgicalOper();
				if (tempInt > 0)
					neurosurgicalOperRadio.setChecked(true);
				else
					neurosurgicalOperRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOpthalmologicalOper();
				if (tempInt > 0)
					opthalmologicalOperRadio.setChecked(true);
				else
					opthalmologicalOperRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getPunction();
				if (tempInt > 0)
					punctionRadio.setChecked(true);
				else
					punctionRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getMassage();
				if (tempInt > 0)
					massageRadio.setChecked(true);
				else
					massageRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getParturition();
				if (tempInt > 0)
					parturitionRadio.setChecked(true);
				else
					parturitionRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOperationOtherCri();
				if (tempInt > 0)
					operationOtherCriRadio.setChecked(true);
				else
					operationOtherCriRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOperationOtherNotCri();
				if (tempInt > 0)
					operationOtherNotCriRadio.setChecked(true);
				else
					operationOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			surgicalOperRadio.setChecked(false);
			neurosurgicalOperRadio.setChecked(false);
			opthalmologicalOperRadio.setChecked(false);
			punctionRadio.setChecked(false);
			massageRadio.setChecked(false);
			parturitionRadio.setChecked(false);
			operationOtherCriRadio.setChecked(false);
			operationOtherNotCriRadio.setChecked(false);
			operationQuesView.setVisibility(View.GONE);
			break;
		}
	}

	public void setVesselSelection(int id, boolean isLoad) {
		switch (id) {
		case R.id.radio_blood_vessel_conditions_yes:
			vesselQuesView.setVisibility(View.VISIBLE);
			if (isLoad) {
				int tempInt = AppViewModel.anamnesisDAO.getMicroangiopathy();
				if (tempInt > 0)
					microangiopathyRadio.setChecked(true);
				else
					microangiopathyRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getAvm();
				if (tempInt > 0)
					avmRadio.setChecked(true);
				else
					avmRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getAneurysm();
				if (tempInt > 0)
					aneurysmRadio.setChecked(true);
				else
					aneurysmRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getOtherVesselAnomal();
				if (tempInt > 0)
					otherVesselAnomalRadio.setChecked(true);
				else
					otherVesselAnomalRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getVesselOtherCri();
				if (tempInt > 0)
					vesselOtherCriRadio.setChecked(true);
				else
					vesselOtherCriRadio.setChecked(false);

				tempInt = AppViewModel.anamnesisDAO.getVesselOtherNotCri();
				if (tempInt > 0)
					vesselOtherNotCriRadio.setChecked(true);
				else
					vesselOtherNotCriRadio.setChecked(false);
			}
			break;
		default:
			microangiopathyRadio.setChecked(false);
			avmRadio.setChecked(false);
			aneurysmRadio.setChecked(false);
			otherVesselAnomalRadio.setChecked(false);
			vesselOtherCriRadio.setChecked(false);
			vesselOtherNotCriRadio.setChecked(false);
			vesselQuesView.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(saveBtn)) {
			// AppViewModel.anamnesisDAO.saveData();
			Toast toast = Toast.makeText(getActivity(),
					R.string.toast_data_saved, Toast.LENGTH_LONG);
			toast.show();
			saveBtn.setEnabled(false);
		} else if (v.equals(cerebroDoneBtn)) {
			cerebroDoneBtn.setEnabled(false);
			completeCerebro();
			int sah = AppViewModel.anamnesisDAO.getSAH();
			int intraHemor = AppViewModel.anamnesisDAO.getIntracHemorrhage();
			int headTrauma = AppViewModel.anamnesisDAO.getHeadTrauma();
			int majorStrok = AppViewModel.anamnesisDAO.getWideStroke();
			int minorStrok = AppViewModel.anamnesisDAO.getMinorStroke();
			int otherCritical = AppViewModel.anamnesisDAO.getCerebroOtherCri();

			String reasonStr = "";
			if (sah > 0 || intraHemor > 0 || headTrauma > 0 || majorStrok > 0
					|| minorStrok > 0 || otherCritical > 0) {
				if (sah > 0) {
					reasonStr += getString(R.string.radio_cerebrovascular_incidents_1)
							+ ", ";
				}
				if (intraHemor > 0) {
					reasonStr += getString(R.string.radio_cerebrovascular_incidents_2)
							+ ", ";
				}
				if (headTrauma > 0) {
					reasonStr += getString(R.string.radio_cerebrovascular_incidents_4)
							+ ", ";
				}
				if (majorStrok > 0) {
					reasonStr += getString(R.string.radio_cerebrovascular_incidents_3)
							+ ", ";
				}
				if (minorStrok > 0) {
					reasonStr += getString(R.string.radio_cerebrovascular_incidents_5)
							+ ", ";
				}
				if (otherCritical > 0) {
					reasonStr += "other critical cerebrovascular incidents, ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_cerebrovascular));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_cerebrovascular);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(diseaseDoneBtn)) {
			diseaseDoneBtn.setEnabled(false);
			completeDisease();
			int hepatopathy = AppViewModel.anamnesisDAO.getHepatopathy();
			int retinopathy = AppViewModel.anamnesisDAO.getRetinopathy();
			int pancreatitis = AppViewModel.anamnesisDAO.getPancreatitis();
			int tumour = AppViewModel.anamnesisDAO.getTumour();
			int otherCritical = AppViewModel.anamnesisDAO.getDiseaseOtherCri();

			String reasonStr = "";
			if (hepatopathy > 0 || retinopathy > 0 || pancreatitis > 0
					|| tumour > 0 || otherCritical > 0) {
				if (hepatopathy > 0) {
					reasonStr += getString(R.string.radio_disease_bleeding_tendency_1)
							+ ", ";
				}
				if (retinopathy > 0) {
					reasonStr += getString(R.string.radio_disease_bleeding_tendency_2)
							+ ", ";
				}
				if (pancreatitis > 0) {
					reasonStr += getString(R.string.radio_disease_bleeding_tendency_3)
							+ ", ";
				}
				if (tumour > 0) {
					reasonStr += getString(R.string.radio_disease_bleeding_tendency_4)
							+ ", ";
				}
				if (otherCritical > 0) {
					reasonStr += "other critical diseases increasing tendency of bleeding, ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_disease));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_disease);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(bleedingDoneBtn)) {
			bleedingDoneBtn.setEnabled(false);
			completeBleeding();
			int trauma = AppViewModel.anamnesisDAO.getTrauma();
			int otherBleeding = AppViewModel.anamnesisDAO.getOtherBleeding();
			int ulcus = AppViewModel.anamnesisDAO.getUlcus();
			int otherGIBleeding = AppViewModel.anamnesisDAO
					.getOtherGIBleeding();
			int varices = AppViewModel.anamnesisDAO.getVarices();
			int tractHemorr = AppViewModel.anamnesisDAO.getTractHemorrahge();

			String reasonStr = "";
			if (trauma > 0 || otherBleeding > 0 || ulcus > 0
					|| otherGIBleeding > 0 || varices > 0 || tractHemorr > 0) {
				if (trauma > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_1)
							+ ", ";
				}
				if (otherBleeding > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_2)
							+ ", ";
				}
				if (ulcus > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_3)
							+ ", ";
				}
				if (otherGIBleeding > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_4)
							+ ", ";
				}
				if (varices > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_5)
							+ ", ";
				}
				if (tractHemorr > 0) {
					reasonStr += getString(R.string.radio_significant_bleeding_6)
							+ ", ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_bleeding));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_bleeding);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(operationDoneBtn)) {
			operationDoneBtn.setEnabled(false);
			completeOperation();
			int surgicalOper = AppViewModel.anamnesisDAO.getSurgicalOper();
			int neurosurgicalOper = AppViewModel.anamnesisDAO
					.getNeurosurgicalOper();
			int opthalmologicalOper = AppViewModel.anamnesisDAO
					.getOpthalmologicalOper();
			int punction = AppViewModel.anamnesisDAO.getPunction();
			int massage = AppViewModel.anamnesisDAO.getMassage();
			int parturition = AppViewModel.anamnesisDAO.getParturition();
			int otherCritical = AppViewModel.anamnesisDAO
					.getOperationOtherCri();

			String reasonStr = "";
			if (surgicalOper > 0 || neurosurgicalOper > 0
					|| opthalmologicalOper > 0 || punction > 0 || massage > 0
					|| parturition > 0 || otherCritical > 0) {
				if (surgicalOper > 0) {
					reasonStr += getString(R.string.radio_operation_1)
							+ ", ";
				}
				if (neurosurgicalOper > 0) {
					reasonStr += getString(R.string.radio_operation_2)
							+ ", ";
				}
				if (opthalmologicalOper > 0) {
					reasonStr += getString(R.string.radio_operation_3)
							+ ", ";
				}
				if (punction > 0) {
					reasonStr += getString(R.string.radio_operation_4)
							+ ", ";
				}
				if (massage > 0) {
					reasonStr += getString(R.string.radio_operation_5)
							+ ", ";
				}
				if (parturition > 0) {
					reasonStr += getString(R.string.radio_operation_6)
					+ ", ";
				}
				if (otherCritical > 0) {
					reasonStr += "other critical bleeding-ristk elevating operation, ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_operation));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_operation);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else if (v.equals(vesselDoneBtn)) {
			vesselDoneBtn.setEnabled(false);
			completeVessel();
			int microangiopathy = AppViewModel.anamnesisDAO.getMicroangiopathy();
			int avm = AppViewModel.anamnesisDAO.getAvm();
			int aneurysm = AppViewModel.anamnesisDAO.getAneurysm();
			int otherVesselAnomal = AppViewModel.anamnesisDAO.getOtherVesselAnomal();
			int otherCritical = AppViewModel.anamnesisDAO.getVesselOtherCri();

			String reasonStr = "";
			if (microangiopathy > 0 || avm > 0 || aneurysm > 0 || otherVesselAnomal > 0
					|| otherCritical > 0) {
				if (microangiopathy > 0) {
					reasonStr += getString(R.string.radio_vessel_conditions_1)
							+ ", ";
				}
				if (avm > 0) {
					reasonStr += getString(R.string.radio_vessel_conditions_2)
							+ ", ";
				}
				if (aneurysm > 0) {
					reasonStr += getString(R.string.radio_vessel_conditions_3)
							+ ", ";
				}
				if (otherVesselAnomal > 0) {
					reasonStr += getString(R.string.radio_vessel_conditions_4)
							+ ", ";
				}
				if (otherCritical > 0) {
					reasonStr += "other critical blood vessel conditions, ";
				}
				Intent interrIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interrIntent.putExtra(DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_vessel));
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_interr_vessel);
				interrIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON_STR,
						reasonStr);
//				interrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interrIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		} else {
			Intent intent = new Intent(getActivity(), DialogHelpInfo.class);
			switch (v.getId()) {
			case R.id.imgbtn_help_cerebrovascular_incidents:
				intent.putExtra(
						DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_anamnesis_cerebrovascular_incidents));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_cerebrovascular_incidents));
				break;
			case R.id.imgbtn_help_disease_bleeding_tendency:
				intent.putExtra(
						DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_anamnesis_disease_bleeding_tendency));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_disease_bleeding_tendency));
				break;
			case R.id.imgbtn_help_significant_bleeding:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_anamnesis_significant_bleeding));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_significant_bleeding));
				break;
			case R.id.imgbtn_help_operation:
				intent.putExtra(DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_anamnesis_operation));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_operation));
				break;
			case R.id.imgbtn_help_blood_vessel_conditions:
				intent.putExtra(
						DialogHelpInfo.TAG_HELP_TITLE,
						getString(R.string.info_anamnesis_blood_vessel_conditions));
				intent.putExtra(DialogHelpInfo.TAG_HELP_INFO,
						getString(R.string.help_info_blood_vessel_conditions));
				break;
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getActivity().startActivity(intent);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundBtn, boolean isChecked) {
		saveBtn.setEnabled(true);
		int bgColor = Color.TRANSPARENT;
		if (isChecked) {
			switch (compoundBtn.getId()) {
			case R.id.radio_cerebrovascular_incidents_no:
			case R.id.radio_cerebrovascular_incidents_unknown:
			case R.id.radio_cerebrovascular_incidents_7:
			case R.id.radio_disease_bleeding_tendency_no:
			case R.id.radio_disease_bleeding_tendency_unknown:
			case R.id.radio_disease_bleeding_tendency_6:
			case R.id.radio_significant_bleeding_no:
			case R.id.radio_significant_bleeding_unknown:
			case R.id.radio_significant_bleeding_7:
			case R.id.radio_operation_no:
			case R.id.radio_operation_unknown:
			case R.id.radio_operation_8:
			case R.id.radio_blood_vessel_conditions_no:
			case R.id.radio_blood_vessel_conditions_unknown:
			case R.id.radio_vessel_conditions_6:
			case R.id.radio_acute_myocardial_infarction_no:
			case R.id.radio_acute_myocardial_infarction_unknown:
				bgColor = getResources().getColor(R.color.green);
				break;
			case R.id.radio_significant_bleeding_6:
			case R.id.radio_acute_myocardial_infarction_yes:
				bgColor = getResources().getColor(R.color.yellow);
				break;
			case R.id.radio_cerebrovascular_incidents_yes:
			case R.id.radio_cerebrovascular_incidents_1:
			case R.id.radio_cerebrovascular_incidents_2:
			case R.id.radio_cerebrovascular_incidents_3:
			case R.id.radio_cerebrovascular_incidents_4:
			case R.id.radio_cerebrovascular_incidents_5:
			case R.id.radio_cerebrovascular_incidents_6:
			case R.id.radio_disease_bleeding_tendency_yes:
			case R.id.radio_disease_bleeding_tendency_1:
			case R.id.radio_disease_bleeding_tendency_2:
			case R.id.radio_disease_bleeding_tendency_3:
			case R.id.radio_disease_bleeding_tendency_4:
			case R.id.radio_disease_bleeding_tendency_5:
			case R.id.radio_significant_bleeding_yes:
			case R.id.radio_significant_bleeding_1:
			case R.id.radio_significant_bleeding_2:
			case R.id.radio_significant_bleeding_3:
			case R.id.radio_significant_bleeding_4:
			case R.id.radio_significant_bleeding_5:
			case R.id.radio_operation_yes:
			case R.id.radio_operation_1:
			case R.id.radio_operation_2:
			case R.id.radio_operation_3:
			case R.id.radio_operation_4:
			case R.id.radio_operation_5:
			case R.id.radio_operation_6:
			case R.id.radio_operation_7:
			case R.id.radio_blood_vessel_conditions_yes:
			case R.id.radio_vessel_conditions_1:
			case R.id.radio_vessel_conditions_2:
			case R.id.radio_vessel_conditions_3:
			case R.id.radio_vessel_conditions_4:
			case R.id.radio_vessel_conditions_5:
				bgColor = getResources().getColor(R.color.red);
				break;
			}

			switch (compoundBtn.getId()) {
			case R.id.radio_cerebrovascular_incidents_1:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setSAH(R.string.radio_cerebrovascular_incidents_1);
				break;
			case R.id.radio_cerebrovascular_incidents_2:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setIntracHemorrhage(R.string.radio_cerebrovascular_incidents_2);
				break;
			case R.id.radio_cerebrovascular_incidents_3:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setWideStroke(R.string.radio_cerebrovascular_incidents_3);
				break;
			case R.id.radio_cerebrovascular_incidents_4:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setHeadTrauma(R.string.radio_cerebrovascular_incidents_4);
				break;
			case R.id.radio_cerebrovascular_incidents_5:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setMinorStroke(R.string.radio_cerebrovascular_incidents_5);
				break;
			case R.id.radio_cerebrovascular_incidents_6:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setCerebroOtherCri(R.string.radio_other_critical);
				break;
			case R.id.radio_cerebrovascular_incidents_7:
				cerebroDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setCerebroOtherNotCri(R.string.radio_other_not_critical);
				break;

			case R.id.radio_disease_bleeding_tendency_1:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setHepatopathy(R.string.radio_disease_bleeding_tendency_1);
				break;
			case R.id.radio_disease_bleeding_tendency_2:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setRetinopathy(R.string.radio_disease_bleeding_tendency_3);
				break;
			case R.id.radio_disease_bleeding_tendency_3:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setPancreatitis(R.string.radio_disease_bleeding_tendency_3);
				break;
			case R.id.radio_disease_bleeding_tendency_4:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setTumour(R.string.radio_disease_bleeding_tendency_4);
				break;
			case R.id.radio_disease_bleeding_tendency_5:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setDiseaseOtherCri(R.string.radio_other_critical);
				break;
			case R.id.radio_disease_bleeding_tendency_6:
				diseaseDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setDiseaseOtherNotCri(R.string.radio_other_not_critical);
				break;
			case R.id.radio_significant_bleeding_1:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setTrauma(R.string.radio_significant_bleeding_1);
				break;
			case R.id.radio_significant_bleeding_2:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOtherBleeding(R.string.radio_significant_bleeding_2);
				break;
			case R.id.radio_significant_bleeding_3:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setUlcus(R.string.radio_significant_bleeding_3);
				break;
			case R.id.radio_significant_bleeding_4:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOtherGIBleeding(R.string.radio_significant_bleeding_4);
				break;
			case R.id.radio_significant_bleeding_5:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setVarices(R.string.radio_significant_bleeding_5);
				break;
			case R.id.radio_significant_bleeding_6:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setTractHemorrahge(R.string.radio_significant_bleeding_6);
				break;
			case R.id.radio_significant_bleeding_7:
				bleedingDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setBleedingOtherNotCri(R.string.radio_other_not_critical);
				break;

			case R.id.radio_operation_1:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setSurgicalOper(R.string.radio_operation_1);
				break;
			case R.id.radio_operation_2:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setNeurosurgicalOper(R.string.radio_operation_2);
				break;
			case R.id.radio_operation_3:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOpthalmologicalOper(R.string.radio_operation_3);
				break;
			case R.id.radio_operation_4:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setPunction(R.string.radio_operation_4);
				break;
			case R.id.radio_operation_5:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setMassage(R.string.radio_operation_5);
				break;
			case R.id.radio_operation_6:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setParturition(R.string.radio_operation_6);
				break;
			case R.id.radio_operation_7:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOperationOtherCri(R.string.radio_other_critical);
				break;
			case R.id.radio_operation_8:
				operationDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOperationOtherNotCri(R.string.radio_other_not_critical);
				break;

			case R.id.radio_vessel_conditions_1:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setMicroangiopathy(R.string.radio_vessel_conditions_1);
				break;
			case R.id.radio_vessel_conditions_2:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setAvm(R.string.radio_vessel_conditions_2);
				break;
			case R.id.radio_vessel_conditions_3:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setAneurysm(R.string.radio_vessel_conditions_3);
				break;
			case R.id.radio_vessel_conditions_4:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setOtherVesselAnomal(R.string.radio_vessel_conditions_4);
				break;
			case R.id.radio_vessel_conditions_5:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setVesselOtherCri(R.string.radio_other_critical);
				break;
			case R.id.radio_vessel_conditions_6:
				vesselDoneBtn.setEnabled(true);
				AppViewModel.anamnesisDAO
						.setVesselOtherNotCri(R.string.radio_other_not_critical);
				break;

			}
		} else if (!isChecked) {
			/** Checkboxs */
			switch (compoundBtn.getId()) {
			case R.id.radio_cerebrovascular_incidents_1:
				AppViewModel.anamnesisDAO.setSAH(0);
				break;
			case R.id.radio_cerebrovascular_incidents_2:
				AppViewModel.anamnesisDAO.setIntracHemorrhage(0);
				break;
			case R.id.radio_cerebrovascular_incidents_3:
				AppViewModel.anamnesisDAO.setWideStroke(0);
				break;
			case R.id.radio_cerebrovascular_incidents_4:
				AppViewModel.anamnesisDAO.setHeadTrauma(0);
				break;
			case R.id.radio_cerebrovascular_incidents_5:
				AppViewModel.anamnesisDAO.setMinorStroke(0);
				break;
			case R.id.radio_cerebrovascular_incidents_6:
				AppViewModel.anamnesisDAO.setCerebroOtherCri(0);
				break;
			case R.id.radio_cerebrovascular_incidents_7:
				AppViewModel.anamnesisDAO.setCerebroOtherNotCri(0);
				break;

			case R.id.radio_disease_bleeding_tendency_1:
				AppViewModel.anamnesisDAO.setHepatopathy(0);
				break;
			case R.id.radio_disease_bleeding_tendency_2:
				AppViewModel.anamnesisDAO.setRetinopathy(0);
				break;
			case R.id.radio_disease_bleeding_tendency_3:
				AppViewModel.anamnesisDAO.setPancreatitis(0);
				break;
			case R.id.radio_disease_bleeding_tendency_4:
				AppViewModel.anamnesisDAO.setTumour(0);
				break;
			case R.id.radio_disease_bleeding_tendency_5:
				AppViewModel.anamnesisDAO.setDiseaseOtherCri(0);
				break;
			case R.id.radio_disease_bleeding_tendency_6:
				AppViewModel.anamnesisDAO.setDiseaseOtherNotCri(0);
				break;

			case R.id.radio_significant_bleeding_1:
				AppViewModel.anamnesisDAO.setTrauma(0);
				break;
			case R.id.radio_significant_bleeding_2:
				AppViewModel.anamnesisDAO.setOtherBleeding(0);
				break;
			case R.id.radio_significant_bleeding_3:
				AppViewModel.anamnesisDAO.setUlcus(0);
				break;
			case R.id.radio_significant_bleeding_4:
				AppViewModel.anamnesisDAO.setOtherGIBleeding(0);
				break;
			case R.id.radio_significant_bleeding_5:
				AppViewModel.anamnesisDAO.setVarices(0);
				break;
			case R.id.radio_significant_bleeding_6:
				AppViewModel.anamnesisDAO.setTractHemorrahge(0);
				break;
			case R.id.radio_significant_bleeding_7:
				AppViewModel.anamnesisDAO.setBleedingOtherNotCri(0);
				break;

			case R.id.radio_operation_1:
				AppViewModel.anamnesisDAO.setSurgicalOper(0);
				break;
			case R.id.radio_operation_2:
				AppViewModel.anamnesisDAO.setNeurosurgicalOper(0);
				break;
			case R.id.radio_operation_3:
				AppViewModel.anamnesisDAO.setOpthalmologicalOper(0);
				break;
			case R.id.radio_operation_4:
				AppViewModel.anamnesisDAO.setPunction(0);
				break;
			case R.id.radio_operation_5:
				AppViewModel.anamnesisDAO.setMassage(0);
				break;
			case R.id.radio_operation_6:
				AppViewModel.anamnesisDAO.setParturition(0);
				break;
			case R.id.radio_operation_7:
				AppViewModel.anamnesisDAO.setOperationOtherCri(0);
				break;
			case R.id.radio_operation_8:
				AppViewModel.anamnesisDAO.setOperationOtherNotCri(0);
				break;

			case R.id.radio_vessel_conditions_1:
				AppViewModel.anamnesisDAO.setMicroangiopathy(0);
				break;
			case R.id.radio_vessel_conditions_2:
				AppViewModel.anamnesisDAO.setAvm(0);
				break;
			case R.id.radio_vessel_conditions_3:
				AppViewModel.anamnesisDAO.setAneurysm(0);
				break;
			case R.id.radio_vessel_conditions_4:
				AppViewModel.anamnesisDAO.setOtherVesselAnomal(0);
				break;
			case R.id.radio_vessel_conditions_5:
				AppViewModel.anamnesisDAO.setVesselOtherCri(0);
				break;
			case R.id.radio_vessel_conditions_6:
				AppViewModel.anamnesisDAO.setVesselOtherNotCri(0);
				break;
			}
		}
		compoundBtn.setBackgroundColor(bgColor);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		saveBtn.setEnabled(true);
		RadioButton r = null;
		switch (group.getId()) {
		case R.id.radio_group_cerebrovascular_incidents:
			r = (RadioButton) cerebroIncidentView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setCerebroIncidentId(checkedId);
				AppViewModel.anamnesisDAO.setCerebroIncident(r.getText()
						.toString());
				setCerebroSelection(checkedId, false);
			}
			completeCerebro();
			break;
		case R.id.radio_group_disease_bleeding_tendency:
			r = (RadioButton) diseaseView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setDiseaseId(checkedId);
				AppViewModel.anamnesisDAO.setDisease(r.getText().toString());
				setDiseaseSelection(checkedId, false);
			}
			completeDisease();
			break;
		case R.id.radio_group_significant_bleeding:
			r = (RadioButton) bleedingView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setBleedingId(checkedId);
				AppViewModel.anamnesisDAO.setBleeding(r.getText().toString());
				setBleedingSelection(checkedId, false);
			}
			completeBleeding();
			break;
		case R.id.radio_group_operation:
			r = (RadioButton) operationView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setOperationId(checkedId);
				AppViewModel.anamnesisDAO.setOperation(r.getText().toString());
				setOperationSelection(checkedId, false);
			}
			completeOperation();
			break;
		case R.id.radio_group_blood_vessel_conditions:
			r = (RadioButton) vesselView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setVesselId(checkedId);
				AppViewModel.anamnesisDAO.setVessel(r.getText().toString());
				setVesselSelection(checkedId, false);
			}
			completeVessel();
			break;
		case R.id.radio_group_acute_myocardial_infarction:
			r = (RadioButton) rootView.findViewById(checkedId);
			if (r != null) {
				AppViewModel.anamnesisDAO.setMyocarInfarctionId(checkedId);
				AppViewModel.anamnesisDAO.setMyocarInfarction(r.getText()
						.toString());
			}
			completeMyocardial();
			break;
		}
	}
	public void completeCerebro() {
		if (AppViewModel.anamnesisDAO.isCerebrovascularComplete()) {
			cerebroComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			cerebroComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
	
	public void completeDisease() {
		if (AppViewModel.anamnesisDAO.isDiseaseComplete()) {
			diseaseComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			diseaseComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
	public void completeBleeding() {
		if (AppViewModel.anamnesisDAO.isBleedingComplete()) {
			bleedingComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			bleedingComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
	public void completeOperation() {
		if (AppViewModel.anamnesisDAO.isOperationComplete()) {
			operationComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			operationComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
	public void completeVessel() {
		if (AppViewModel.anamnesisDAO.isVesselComplete()) {
			vesselComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			vesselComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
	public void completeMyocardial() {
		int mycardial = AppViewModel.anamnesisDAO.getMyocarInfarctionId();
		if (mycardial > 0) {
			myocardialComple.setBackgroundColor(Color.TRANSPARENT);
		} else {
			myocardialComple.setBackgroundColor(getResources()
					.getColor(R.color.grey));
		}
	}
}
