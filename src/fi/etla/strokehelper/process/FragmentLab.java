package fi.etla.strokehelper.process;

import android.app.backup.RestoreObserver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.CommonLabImaging;
import fi.etla.strokehelper.common.CommonLabResults;
import fi.etla.strokehelper.common.CommonLabStatus;
import fi.etla.strokehelper.common.CommonLabStatus.CommonLabDoneCallBack;
import fi.etla.strokehelper.common.CommonPatientInfo;
import fi.etla.strokehelper.common.DialogCommonInterrupt;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.main.MainActivity;

public class FragmentLab extends Fragment implements OnClickListener,
		CommonLabDoneCallBack {
	private CommonPatientInfo patientInfoCommon;
	// private FragmentLabViewModel labViewModel;
	private View rootView;
	private CommonLabStatus labStatus;
	private CommonLabImaging labImaging;

	public final static int RESULT_RR_TREAT_YES = 1;
	public final static int RESULT_RR_TREAT_NO = 0;
	public final static int RESULT_B_GLUC_TREAT_YES = 2;
	public final static int RESULT_B_GLUC_TREAT_NO = 3;
	public final static int RESULT_B_GLUC_AFTER_YES = 4;
	public final static int RESULT_B_GLUC_AFTER_NO = 5;

	private CommonLabResults labResults;
	private View labView;

	private Button saveBtn;

	/** Mandatory empty constructor for the fragment manager to instantiate */
	public FragmentLab() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_detail_tab_lab,
				container, false);

		// /** Init ViewModel */
		// labViewModel = new FragmentLabViewModel(getActivity());
		initUIElements(rootView);
		loadUISatus();
		return rootView;
	}

	@Override
	public void onDestroy() {
		// labViewModel.saveData();
		// labViewModel.closeDataBase();
		super.onDestroy();
	}

	public void initUIElements(View rootView) {
		/** Patient info */
		View tempView = (View) rootView.findViewById(R.id.row_one);
		patientInfoCommon = new CommonPatientInfo(tempView, FragmentLab.this);

		saveBtn = (Button) rootView.findViewById(R.id.imgbtn_save);
		saveBtn.setOnClickListener(this);

		/** Lab info */
		labView = (View) rootView.findViewById(R.id.row_two);

		tempView = (View) rootView.findViewById(R.id.view_status);
		labStatus = new CommonLabStatus(tempView, FragmentLab.this);

		tempView = (View) rootView.findViewById(R.id.view_image);
		labImaging = new CommonLabImaging(tempView, FragmentLab.this);

		tempView = (View) rootView.findViewById(R.id.view_result);
		labResults = new CommonLabResults(tempView, FragmentLab.this);

	}

	public void loadUISatus() {
		patientInfoCommon.hideNewPatBtn();
		patientInfoCommon.loadPatientInfo();

		/** Initial UI status */
		if (AppViewModel.patientDAO.getPatientId() == -1) {

			labView.setVisibility(View.GONE);
			saveBtn.setVisibility(Button.GONE);
		}/** Load existing patient status */
		else {
			if (AppViewModel.labDAO.isLabInfoLoaded() == false)
				AppViewModel.labDAO.fetchLab();

			labView.setVisibility(View.VISIBLE);
			saveBtn.setVisibility(Button.VISIBLE);
			saveBtn.setEnabled(true);
			setUserSelection();
		}
		// saveBtn.setEnabled(false);
	}

	public void setUserSelection() {
		/** Status Part */
		labStatus.setUserSelection();

		/** Image Part */
		labImaging.setUserSelection();

		/** Results Part */
		labResults.setUserSelection();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == MainActivity.REQUEST_CODE_LAB_ITEMS) {
			if (resultCode == FragmentLab.RESULT_RR_TREAT_YES) {
				AppViewModel.labDAO.setRRTreatDecision(true);
				labStatus.updateRRAfterSeekbars(true, 0, 0);
				Toast toast = Toast.makeText(getActivity(),
						R.string.toast_rr_treat, Toast.LENGTH_LONG);
				toast.show();
			} else if (resultCode == FragmentLab.RESULT_RR_TREAT_NO) {
				AppViewModel.labDAO.setRRTreatDecision(false);
				labStatus.updateRRAfterSeekbars(false, 0, 0);
				Intent interruptIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_rr));
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_rr);
//				interruptIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interruptIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}else if (resultCode == FragmentLab.RESULT_B_GLUC_TREAT_YES) {
				AppViewModel.labDAO.setBGlucTreatDecision(true);
				labResults.updateBGlucAfter(true, 0);
				Toast toast = Toast.makeText(getActivity(),
						R.string.toast_b_gluc_treat, Toast.LENGTH_LONG);
				toast.show();
			} else if (resultCode == FragmentLab.RESULT_B_GLUC_TREAT_NO) {
				AppViewModel.labDAO.setBGlucTreatDecision(false);
				labResults.updateBGlucAfter(false, 0);
				Intent interruptIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.interruption_warning_title));
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_interr_b_gluc));
				interruptIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.reason_interr_bgluc);
//				interruptIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivityForResult(interruptIntent,
						MainActivity.REQUEST_CODE_COMMOM_INTERRUPT);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(saveBtn)) {
			Toast toast = Toast.makeText(getActivity(),
					R.string.toast_data_saved, Toast.LENGTH_LONG);
			toast.show();
			// rootView.requestFocus();
			// labViewModel.saveData();
			// saveBtn.setEnabled(false);
		}
	}

	@Override
	public void onLabDone(int labId) {
		switch (labId) {
		case R.id.imgbtn_done_rr:
			int rrSys = AppViewModel.labDAO.getRRSystolic();
			int rrDia = AppViewModel.labDAO.getRRDiastolic();

			if (rrSys >= 185 || rrDia >= 110) {
				Intent hypertensionIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				hypertensionIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.rr_hypertension_warning_title));
				hypertensionIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_warning_rr));
				hypertensionIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_warning_rr);
				FragmentLab.this.startActivityForResult(hypertensionIntent, MainActivity.REQUEST_CODE_LAB_ITEMS);
			}
			break;
		case R.id.imgbtn_done_b_gluc:
			float bGluc = AppViewModel.labDAO.getBGluc();

			if (bGluc < 2.799999) {
				Intent hypoglycemicIntent = new Intent(getActivity(),
						DialogCommonInterrupt.class);
				hypoglycemicIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_TITLE,
						getString(R.string.b_gluc_hypoglycemic_warning_title));
				hypoglycemicIntent.putExtra(
						DialogCommonInterrupt.TAG_WARNING_CONTENT,
						getString(R.string.info_warning_b_gluc));
				hypoglycemicIntent.putExtra(
						DialogCommonInterrupt.TAG_INTERRUPT_REASON,
						R.string.info_warning_b_gluc);
				FragmentLab.this.startActivityForResult(hypoglycemicIntent, MainActivity.REQUEST_CODE_LAB_ITEMS);
			}
			break;
		}
	}
}
