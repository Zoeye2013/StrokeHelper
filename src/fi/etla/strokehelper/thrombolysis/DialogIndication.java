package fi.etla.strokehelper.thrombolysis;

import java.util.ArrayList;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.ContraindicationTableDAO;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.data.DoctorUserTableDAO;
import fi.etla.strokehelper.data.PatientTableDAO;
import fi.etla.strokehelper.main.MainActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class DialogIndication extends Activity implements
		OnCheckedChangeListener, OnClickListener {

	/** UI elements */
	private RadioButton criticalRadio;
	private RadioButton supportiveRadio;

	private ListView criticalListView;
	private ArrayAdapter<String> criticalAdapter;

	private ListView supportiveListView;
	private ArrayAdapter<String> supportiveAdapter;
	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_thrombolysis_indications);
		setTitle(R.string.info_indications);
		initUIElements();
		loadIndicationsData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {
		criticalRadio = (RadioButton) findViewById(R.id.tab_critical);
		supportiveRadio = (RadioButton) findViewById(R.id.tab_supportive);
		criticalRadio.setOnCheckedChangeListener(this);
		supportiveRadio.setOnCheckedChangeListener(this);

		criticalListView = (ListView) findViewById(R.id.list_critical);
		supportiveListView = (ListView) findViewById(R.id.list_supportive);

		criticalRadio.setChecked(true);

		backBtn = (Button) findViewById(R.id.imgbtn_back);
		backBtn.setOnClickListener(this);
	}

	public void loadIndicationsData() {
		criticalAdapter = new ArrayAdapter<String>(DialogIndication.this,
				R.layout.item_list, AppViewModel.criticalIndications);
		criticalAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		criticalListView.setAdapter(criticalAdapter);

		supportiveAdapter = new ArrayAdapter<String>(
				DialogIndication.this,
				R.layout.item_list, AppViewModel.supportiveIndications);
		supportiveAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		supportiveListView.setAdapter(supportiveAdapter);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.tab_critical:
				criticalListView.setVisibility(ListView.VISIBLE);
				supportiveListView.setVisibility(ListView.GONE);
				break;
			case R.id.tab_supportive:
				supportiveListView.setVisibility(ListView.VISIBLE);
				criticalListView.setVisibility(ListView.GONE);
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(backBtn)) {
			DialogIndication.this.finish();
		}
	}
}
