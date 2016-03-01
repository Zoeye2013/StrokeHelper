package fi.etla.strokehelper.process;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonNihss;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.data.AppViewModel;

public class FragmentNihssReport extends Fragment implements OnClickListener {
	private View nihssStateView;
	private View nihssReportView;
	private TextView nihssStateInfoText;
	private Button newTestBtn;
	private Button reviewTestBtn;

	private CommonNihss nihssBaseLinePart;

	private CommonPatientInfo patientInfoCommon;

	// private FragmentNihssViewModel nihssViewModel;

	/** Callback for when newNihssTest & reviewNihssTest */
	public interface NihssCallback {

		public void onNihssButtonClicked(int btnId, boolean isFollowUp);
	}

	private NihssCallback nihssCallback;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentNihssReport() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_detail_tab_nihss_report, container, false);

		/** Init ViewModel */
		initUIElements(rootView);
		loadUISatus();
		return rootView;
	}

	@Override
	public void onDestroy() {
		// nihssViewModel.saveData();
		// nihssViewModel.closeDataBase();
		super.onDestroy();
	}

	public void initUIElements(View rootView) {
		/** Patient info */
		View tempView = (View) rootView.findViewById(R.id.row_one);
		patientInfoCommon = new CommonPatientInfo(tempView,
				FragmentNihssReport.this);

		/** NIHSS state info */
		nihssStateView = (View) rootView.findViewById(R.id.row_two);
		nihssStateInfoText = (TextView) rootView
				.findViewById(R.id.info_nihss_state);
		newTestBtn = (Button) rootView.findViewById(R.id.imgbtn_start_test);
		reviewTestBtn = (Button) rootView.findViewById(R.id.imgbtn_check_test);
		newTestBtn.setOnClickListener(this);
		reviewTestBtn.setOnClickListener(this);

		/** NIHSS report */
		nihssReportView = (View) rootView.findViewById(R.id.row_three);
		nihssBaseLinePart = new CommonNihss(rootView, FragmentNihssReport.this,0);
	}

	public void loadUISatus() {
		patientInfoCommon.hideNewPatBtn();
		patientInfoCommon.loadPatientInfo();
		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {

			nihssStateView.setVisibility(View.GONE);
			nihssReportView.setVisibility(View.GONE);
		}/** Load existing patient status */
		else {
			if (AppViewModel.nihssDAO.isNihssLoaded(0) == false)
				AppViewModel.nihssDAO.fetchNihss(0);

			nihssStateView.setVisibility(View.VISIBLE);

			/** Haven't done NIHSS test yet */
			String testTime = AppViewModel.nihssDAO.getNihssBaseline().getTestTime();
			if (testTime != null
					&& testTime.length() > 0) {
				nihssStateInfoText
						.setText(getString(R.string.info_nihss_has_baseling_test)
								+ " " + testTime);
				nihssStateInfoText.setBackgroundColor(getResources().getColor(
						R.color.bright_blue));
				newTestBtn.setVisibility(Button.GONE);
				reviewTestBtn.setVisibility(Button.VISIBLE);
				nihssReportView.setVisibility(View.VISIBLE);

				nihssBaseLinePart.setDataToNihssReport(0);
			} else {
				nihssStateInfoText
						.setText(R.string.info_nihss_no_baseling_test);
				nihssStateInfoText.setBackgroundColor(getResources().getColor(
						R.color.yellow));
				newTestBtn.setVisibility(Button.VISIBLE);
				reviewTestBtn.setVisibility(Button.GONE);
				nihssReportView.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(newTestBtn) || v.equals(reviewTestBtn)) {
			nihssCallback.onNihssButtonClicked(v.getId(),false);
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
}
