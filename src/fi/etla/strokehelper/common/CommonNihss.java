package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.NihssTableDAO.Nihss;
import fi.etla.strokehelper.process.FragmentLab;
import fi.etla.strokehelper.process.FragmentNihssReport;
import fi.etla.strokehelper.thrombolysis.FragmentThrombolysisDecision;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CommonNihss {
	private View rootView;
	private Fragment appContext;

	private TextView totalScoreText;
	private TextView answer1AText;
	private TextView point1AText;
	private TextView answer1BText;
	private TextView point1BText;
	private TextView answer1CText;
	private TextView point1CText;
	private TextView answer2Text;
	private TextView point2Text;
	private TextView answer3Text;
	private TextView point3Text;
	private TextView answer4Text;
	private TextView point4Text;
	private TextView answer5AText;
	private TextView point5AText;
	private TextView answer5BText;
	private TextView point5BText;
	private TextView answer6AText;
	private TextView point6AText;
	private TextView answer6BText;
	private TextView point6BText;
	private TextView answer7Text;
	private TextView point7Text;
	private TextView answer8Text;
	private TextView point8Text;
	private TextView answer9Text;
	private TextView point9Text;
	private TextView answer10Text;
	private TextView point10Text;
	private TextView answer11Text;
	private TextView point11Text;

	private int nihssType;
	private Nihss nihss;

	public CommonNihss(View root, Fragment context, int type) {
		rootView = root;
		appContext = context;
		nihssType = type;
		initUIElements();
	}

	public void initUIElements() {
		if (appContext instanceof FragmentNihssReport) {
			answer1AText = (TextView) rootView
					.findViewById(R.id.info_answer_1a);
			answer1BText = (TextView) rootView
					.findViewById(R.id.info_answer_1b);
			answer1CText = (TextView) rootView
					.findViewById(R.id.info_answer_1c);
			answer2Text = (TextView) rootView.findViewById(R.id.info_answer_2);
			answer3Text = (TextView) rootView.findViewById(R.id.info_answer_3);
			answer4Text = (TextView) rootView.findViewById(R.id.info_answer_4);
			answer5AText = (TextView) rootView
					.findViewById(R.id.info_answer_5a);
			answer5BText = (TextView) rootView
					.findViewById(R.id.info_answer_5b);
			answer6AText = (TextView) rootView
					.findViewById(R.id.info_answer_6a);
			answer6BText = (TextView) rootView
					.findViewById(R.id.info_answer_6b);
			answer7Text = (TextView) rootView.findViewById(R.id.info_answer_7);
			answer8Text = (TextView) rootView.findViewById(R.id.info_answer_8);
			answer9Text = (TextView) rootView.findViewById(R.id.info_answer_9);
			answer10Text = (TextView) rootView
					.findViewById(R.id.info_answer_10);
			answer11Text = (TextView) rootView
					.findViewById(R.id.info_answer_11);
		}
		if (nihssType == 0) {
			totalScoreText = (TextView) rootView
					.findViewById(R.id.info_point_total);
			point1AText = (TextView) rootView.findViewById(R.id.info_point_1a);
			point1BText = (TextView) rootView.findViewById(R.id.info_point_1b);
			point1CText = (TextView) rootView.findViewById(R.id.info_point_1c);
			point2Text = (TextView) rootView.findViewById(R.id.info_point_2);
			point3Text = (TextView) rootView.findViewById(R.id.info_point_3);
			point4Text = (TextView) rootView.findViewById(R.id.info_point_4);
			point5AText = (TextView) rootView.findViewById(R.id.info_point_5a);
			point5BText = (TextView) rootView.findViewById(R.id.info_point_5b);
			point6AText = (TextView) rootView.findViewById(R.id.info_point_6a);
			point6BText = (TextView) rootView.findViewById(R.id.info_point_6b);
			point7Text = (TextView) rootView.findViewById(R.id.info_point_7);
			point8Text = (TextView) rootView.findViewById(R.id.info_point_8);
			point9Text = (TextView) rootView.findViewById(R.id.info_point_9);
			point10Text = (TextView) rootView.findViewById(R.id.info_point_10);
			point11Text = (TextView) rootView.findViewById(R.id.info_point_11);
		} else if (nihssType == 1) {
			totalScoreText = (TextView) rootView
					.findViewById(R.id.info_point_total_1h);
			point1AText = (TextView) rootView
					.findViewById(R.id.info_point_1a_1h);
			point1BText = (TextView) rootView
					.findViewById(R.id.info_point_1b_1h);
			point1CText = (TextView) rootView
					.findViewById(R.id.info_point_1c_1h);
			point2Text = (TextView) rootView.findViewById(R.id.info_point_2_1h);
			point3Text = (TextView) rootView.findViewById(R.id.info_point_3_1h);
			point4Text = (TextView) rootView.findViewById(R.id.info_point_4_1h);
			point5AText = (TextView) rootView
					.findViewById(R.id.info_point_5a_1h);
			point5BText = (TextView) rootView
					.findViewById(R.id.info_point_5b_1h);
			point6AText = (TextView) rootView
					.findViewById(R.id.info_point_6a_1h);
			point6BText = (TextView) rootView
					.findViewById(R.id.info_point_6b_1h);
			point7Text = (TextView) rootView.findViewById(R.id.info_point_7_1h);
			point8Text = (TextView) rootView.findViewById(R.id.info_point_8_1h);
			point9Text = (TextView) rootView.findViewById(R.id.info_point_9_1h);
			point10Text = (TextView) rootView
					.findViewById(R.id.info_point_10_1h);
			point11Text = (TextView) rootView
					.findViewById(R.id.info_point_11_1h);
		} else if (nihssType == 24) {
			totalScoreText = (TextView) rootView
					.findViewById(R.id.info_point_total_24h);
			point1AText = (TextView) rootView
					.findViewById(R.id.info_point_1a_24h);
			point1BText = (TextView) rootView
					.findViewById(R.id.info_point_1b_24h);
			point1CText = (TextView) rootView
					.findViewById(R.id.info_point_1c_24h);
			point2Text = (TextView) rootView
					.findViewById(R.id.info_point_2_24h);
			point3Text = (TextView) rootView
					.findViewById(R.id.info_point_3_24h);
			point4Text = (TextView) rootView
					.findViewById(R.id.info_point_4_24h);
			point5AText = (TextView) rootView
					.findViewById(R.id.info_point_5a_24h);
			point5BText = (TextView) rootView
					.findViewById(R.id.info_point_5b_24h);
			point6AText = (TextView) rootView
					.findViewById(R.id.info_point_6a_24h);
			point6BText = (TextView) rootView
					.findViewById(R.id.info_point_6b_24h);
			point7Text = (TextView) rootView
					.findViewById(R.id.info_point_7_24h);
			point8Text = (TextView) rootView
					.findViewById(R.id.info_point_8_24h);
			point9Text = (TextView) rootView
					.findViewById(R.id.info_point_9_24h);
			point10Text = (TextView) rootView
					.findViewById(R.id.info_point_10_24h);
			point11Text = (TextView) rootView
					.findViewById(R.id.info_point_11_24h);
		}
	}

	public void setDataToNihssReport(int nihssType) {

		switch (nihssType) {
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

		if (appContext instanceof FragmentNihssReport) {
			answer1AText.setText(nihss.getAnswer1A());
			answer1BText.setText(nihss.getAnswer1B());
			answer1CText.setText(nihss.getAnswer1C());
			answer2Text.setText(nihss.getAnswer2());
			answer3Text.setText(nihss.getAnswer3());
			answer4Text.setText(nihss.getAnswer4());
			answer5AText.setText(nihss.getAnswer5A());
			answer5BText.setText(nihss.getAnswer5B());
			answer6AText.setText(nihss.getAnswer6A());
			answer6BText.setText(nihss.getAnswer6B());
			answer7Text.setText(nihss.getAnswer7());
			answer8Text.setText(nihss.getAnswer8());
			answer9Text.setText(nihss.getAnswer9());
			answer10Text.setText(nihss.getAnswer10());
			answer11Text.setText(nihss.getAnswer11());
			setNihssTotalScoreColor(nihss.getNihssTotal());
		}

		if (nihss.getTestTime() != null && nihss.getTestTime().length() > 0)
			totalScoreText.setText(String.valueOf(nihss.getNihssTotal()));
		else
			totalScoreText.setText(R.string.info_nihss_empty);

		if (nihss.getId1A() > 0)
			point1AText.setText(String.valueOf(nihss.getPoint1A()));
		else
			point1AText.setText(R.string.info_nihss_empty);

		if (nihss.getId1B() > 0)
			point1BText.setText(String.valueOf(nihss.getPoint1B()));
		else
			point1BText.setText(R.string.info_nihss_empty);

		if (nihss.getId1C() > 0)
			point1CText.setText(String.valueOf(nihss.getPoint1C()));
		else
			point1CText.setText(R.string.info_nihss_empty);

		if (nihss.getId2() > 0)
			point2Text.setText(String.valueOf(nihss.getPoint2()));
		else
			point2Text.setText(R.string.info_nihss_empty);

		if (nihss.getId3() > 0)
			point3Text.setText(String.valueOf(nihss.getPoint3()));
		else
			point3Text.setText(R.string.info_nihss_empty);

		if (nihss.getId4() > 0)
			point4Text.setText(String.valueOf(nihss.getPoint4()));
		else
			point4Text.setText(R.string.info_nihss_empty);

		if (nihss.getId5A() > 0)
			point5AText.setText(String.valueOf(nihss.getPoint5A()));
		else
			point5AText.setText(R.string.info_nihss_empty);

		if (nihss.getId5B() > 0)
			point5BText.setText(String.valueOf(nihss.getPoint5B()));
		else
			point5BText.setText(R.string.info_nihss_empty);

		if (nihss.getId6A() > 0)
			point6AText.setText(String.valueOf(nihss.getPoint6A()));
		else
			point6AText.setText(R.string.info_nihss_empty);

		if (nihss.getId6B() > 0)
			point6BText.setText(String.valueOf(nihss.getPoint6B()));
		else
			point6BText.setText(R.string.info_nihss_empty);

		if (nihss.getId7() > 0)
			point7Text.setText(String.valueOf(nihss.getPoint7()));
		else
			point7Text.setText(R.string.info_nihss_empty);

		if (nihss.getId8() > 0)
			point8Text.setText(String.valueOf(nihss.getPoint8()));
		else
			point8Text.setText(R.string.info_nihss_empty);

		if (nihss.getId9() > 0)
			point9Text.setText(String.valueOf(nihss.getPoint9()));
		else
			point9Text.setText(R.string.info_nihss_empty);

		if (nihss.getId10() > 0)
			point10Text.setText(String.valueOf(nihss.getPoint10()));
		else
			point10Text.setText(R.string.info_nihss_empty);

		if (nihss.getId11() > 0)
			point11Text.setText(String.valueOf(nihss.getPoint11()));
		else
			point11Text.setText(R.string.info_nihss_empty);
	}

	public void setNihssTotalScoreColor(int nihssTotal) {
		Time time = new Time();
		time.setToNow();
		long timeDiff = time.toMillis(false)
				- AppViewModel.timeStampsDAO.getSympOnsetTime();
		double onsetTimeH = AppViewModel.milliToHour(timeDiff);

		if (onsetTimeH > 0 && onsetTimeH <= 3) {
			if (nihssTotal >= 0 && nihssTotal <= 2)
				totalScoreText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.yellow));
			else if (nihssTotal >= 3 && nihssTotal <= 42)
				totalScoreText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
		} else if (onsetTimeH > 3 && onsetTimeH <= 4.5) {
			if (nihssTotal >= 0 && nihssTotal <= 2)
				totalScoreText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.yellow));
			else if (nihssTotal >= 3 && nihssTotal <= 24)
				totalScoreText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.green));
			else if (nihssTotal >= 25 && nihssTotal <= 42)
				totalScoreText.setBackgroundColor(appContext.getResources()
						.getColor(R.color.yellow));
		} else {
			totalScoreText.setBackgroundColor(Color.TRANSPARENT);
		}
	}

}
