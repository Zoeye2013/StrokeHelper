package fi.etla.strokehelper.summary;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.common.DialogCommonProcessRational;
import fi.etla.strokehelper.data.AppViewModel;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;

public class SummaryProcessRationale implements OnClickListener {

	private TextView continueNoneText;
	private TextView interruptNoneText;

	private Button continueBtn;
	private Button interruptBtn;

	private View rootView;
	private Fragment appContext;

	public SummaryProcessRationale(View root, Fragment context) {
		rootView = root;
		appContext = context;
		initUIElements();
	}

	public void initUIElements() {
		/** TextViews */
		continueNoneText = (TextView) rootView
				.findViewById(R.id.info_rational_continue_process_none);
		interruptNoneText = (TextView) rootView
				.findViewById(R.id.info_interrupt_process_none);

		continueBtn = (Button) rootView
				.findViewById(R.id.imgbtn_rational_continue_process);
		interruptBtn = (Button) rootView
				.findViewById(R.id.imgbtn_interrupt_process);
		continueBtn.setOnClickListener(this);
		interruptBtn.setOnClickListener(this);

	}

	public void setUserSelection() {
		AppViewModel.summarizeRationalContinueProcess();
		if (AppViewModel.continueProcess.size() <= 0) {
			continueNoneText.setVisibility(TextView.VISIBLE);
			continueBtn.setVisibility(Button.GONE);
		} else {
			continueNoneText.setVisibility(TextView.GONE);
			continueBtn.setVisibility(Button.VISIBLE);
		}
		
		AppViewModel.summarizeInterruptProcess();
		if (AppViewModel.interruptProcess.size() <= 0) {
			interruptNoneText.setVisibility(TextView.VISIBLE);
			interruptBtn.setVisibility(Button.GONE);
		} else {
			interruptNoneText.setVisibility(TextView.GONE);
			interruptBtn.setVisibility(Button.VISIBLE);
		}

	}

	@Override
	public void onClick(View v) {
		if (v.equals(continueBtn)) {
			Intent intent = new Intent(appContext.getActivity(),
					DialogCommonProcessRational.class);
			intent.putExtra(DialogCommonProcessRational.TAG_TYPE, DialogCommonProcessRational.TAG_TYPE_PROCESS_CONTINUE);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(intent);
		}else if (v.equals(interruptBtn)) {
			Intent intent = new Intent(appContext.getActivity(),
					DialogCommonProcessRational.class);
			intent.putExtra(DialogCommonProcessRational.TAG_TYPE, DialogCommonProcessRational.TAG_TYPE_PROCESS_INTERRUPT);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			appContext.getActivity().startActivity(intent);
		}
	}
}
