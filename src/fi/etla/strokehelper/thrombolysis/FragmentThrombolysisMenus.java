package fi.etla.strokehelper.thrombolysis;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.patient.FragmentPatient;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentThrombolysisMenus extends Fragment {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";


	private ThrombolysisMenusCallbacks mCallbacks = thrombolysisMenusCallbacks;


//	private int mActivatedPosition = ListView.INVALID_POSITION;

	private RadioGroup tabGroup;
	private RadioButton decisionTabRadio;
	private RadioButton followUpTabRadio;

	public interface ThrombolysisMenusCallbacks {
		public void onTabSelected(int id);
	}

	private static ThrombolysisMenusCallbacks thrombolysisMenusCallbacks = new ThrombolysisMenusCallbacks() {
		@Override
		public void onTabSelected(int id) {
		}
	};

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentThrombolysisMenus() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail_thrombolysis,
				container, false);
		tabGroup = (RadioGroup) rootView.findViewById(R.id.tabgroup);
		decisionTabRadio = (RadioButton) rootView.findViewById(R.id.tab_decision);
		followUpTabRadio = (RadioButton) rootView.findViewById(R.id.tab_follow_up);
		
		tabGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mCallbacks.onTabSelected(checkedId);
			}
		});
		
		if(AppViewModel.patientDAO.getPatientId() == -1){
			decisionTabRadio.setEnabled(false);
			followUpTabRadio.setEnabled(false);
		}else{
			/** By default show door tab */
			tabGroup.check(R.id.tab_decision);
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof ThrombolysisMenusCallbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (ThrombolysisMenusCallbacks) activity;
	}
	
	public void jumpToFollowUp(){
		tabGroup.check(R.id.tab_follow_up);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = thrombolysisMenusCallbacks;
	}
}
