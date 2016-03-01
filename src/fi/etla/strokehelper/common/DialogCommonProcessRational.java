package fi.etla.strokehelper.common;

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

public class DialogCommonProcessRational extends Activity implements
		OnClickListener {

	/** UI elements */

	private ListView listView;
	private ArrayAdapter<String> adapter;

	private Button backBtn;

	public static String TAG_TYPE = "TAG_TYPE";
	public static final int TAG_TYPE_PROCESS_CONTINUE = 1;
	public static final int TAG_TYPE_PROCESS_INTERRUPT = 2;

	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		type = getIntent().getIntExtra(TAG_TYPE, 0);
		setContentView(R.layout.dialog_thrombolysis_rational_continue);
		initUIElements();
		loadContraindicationsData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {
		listView = (ListView) findViewById(R.id.list_rational_continue_process);

		backBtn = (Button) findViewById(R.id.imgbtn_back);
		backBtn.setOnClickListener(this);
	}

	/** Load users data to Spinner */
	public void loadContraindicationsData() {

		switch (type) {
		case TAG_TYPE_PROCESS_CONTINUE:
			setTitle(R.string.title_process_continue);
			adapter = new ArrayAdapter<String>(
					DialogCommonProcessRational.this, R.layout.item_list,
					AppViewModel.continueProcess);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			listView.setAdapter(adapter);
			break;
		case TAG_TYPE_PROCESS_INTERRUPT:
			setTitle(R.string.title_process_interrupt);
			adapter = new ArrayAdapter<String>(
					DialogCommonProcessRational.this, R.layout.item_list,
					AppViewModel.interruptProcess);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			listView.setAdapter(adapter);
			break;
		}

	}

	@Override
	public void onClick(View v) {
		if (v.equals(backBtn)) {
			DialogCommonProcessRational.this.finish();
		}
	}
}
