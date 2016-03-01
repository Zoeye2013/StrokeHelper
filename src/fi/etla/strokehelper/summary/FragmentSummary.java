package fi.etla.strokehelper.summary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.SummaryIO;
import fi.etla.strokehelper.main.ItemDetailActivity;
import fi.etla.strokehelper.main.MainActivity;
import fi.etla.strokehelper.menu.MenusLevOne;

public class FragmentSummary extends Fragment {

	/** Colors */
	public static int grey;
	public static int green;
	public static int red;
	public static int yellow;
	public static int transparent;

	private View rootView;
	private TextView patIdText;
	private TextView docIdText;

	/** General Part */
	private TextView patAgeText;
	private SummaryGeneral generalPart;
	
	/** Finding part */
	private SummaryFindings findingPart;
	
	/** Anamnesis part */
	private SummaryAnamnesis anamnesisPart;
	private SummaryPresentCondition presentConditionPart;
	private SummaryRestSymptoms restSymptoms;
	
	private SummaryProcessRationale processRationalePart;
	
	private SummaryThrombolysis throbolysisPart;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentSummary() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_summary,
				container, false);

		initUIElements();
		loadUISatus();
		return rootView;
	}

	public void initUIElements() {
		grey = getResources().getColor(R.color.grey);
		green = getResources().getColor(R.color.green);
		red = getResources().getColor(R.color.red);
		yellow = getResources().getColor(R.color.yellow);
		transparent = Color.TRANSPARENT;

		patIdText = (TextView) rootView.findViewById(R.id.info_patient_id);
		docIdText = (TextView) rootView.findViewById(R.id.info_doctor_id);
		View tempView = (View) rootView.findViewById(R.id.view_summary_general);

		patAgeText = (TextView) tempView.findViewById(R.id.info_patient_age);
		generalPart = new SummaryGeneral(tempView, this);
		
		tempView = (View) rootView.findViewById(R.id.view_summary_finding);
		findingPart = new SummaryFindings(tempView, this);
		
		tempView = (View) rootView.findViewById(R.id.view_summary_anamnesis);
		anamnesisPart = new SummaryAnamnesis(tempView, this);
		
		tempView = (View) rootView.findViewById(R.id.view_summary_present_condition);
		presentConditionPart = new SummaryPresentCondition(tempView, this);
		
		tempView = (View)rootView.findViewById(R.id.view_summary_rest_symptoms);
		restSymptoms = new SummaryRestSymptoms(tempView, this);
		
		tempView = (View)rootView.findViewById(R.id.view_summary_process_rationale);
		processRationalePart = new SummaryProcessRationale(tempView, this);
		
		tempView = (View)rootView.findViewById(R.id.view_summary_thrombolysis);
		throbolysisPart = new SummaryThrombolysis(tempView, this);

	}

	public void loadUISatus() {
		if (AppViewModel.patientDAO.getPatientId() == -1) {

			rootView.setVisibility(View.GONE);
		} else {
			rootView.setVisibility(View.VISIBLE);
			setUserSelection();
		}
	}

	public void setUserSelection() {
		/** Patient & Doctor Info */
		String dateOfBirth = AppViewModel.patientDAO.getDateOfBirth();
		if (dateOfBirth != null && dateOfBirth.length() > 0) {
			patIdText.setText(dateOfBirth);
			patAgeText.setText(AppViewModel.patientDAO.getPatientAge() + " "
					+ getString(R.string.info_thrombolysis_patient_age));
		} else {
			patIdText.setText("");
			patAgeText.setText("");
		}

		String docUsername = AppViewModel.userDAO.getName();
		if (docUsername != null && docUsername.length() > 0) {
			docIdText.setText(docUsername);
		} else {
			docIdText.setText("");
		}

		/** General Part */
		generalPart.setUserSelection();
		
		/** Finding Part */
		findingPart.setUserSelection();
		
		/** Anamnesis Part */
		anamnesisPart.setUserSelection();
		
		presentConditionPart.setUserSelection();
		
		restSymptoms.setUserSelection();
		
		processRationalePart.setUserSelection();
		
		throbolysisPart.setUserSelection();
	}
}
