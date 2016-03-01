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

public class DialogContraindication extends Activity implements
		OnCheckedChangeListener, OnClickListener {

	/** UI elements */
	private RadioButton contrasRadio;
	private RadioButton relaContrasRadio;

	private ListView contrasListView;
	private ArrayAdapter<String> contrasAdapter;
	private Cursor contrasCursor;

	private ListView relaContrasListView;
	private ArrayAdapter<String> relaContrasAdapter;
	private Cursor relaContrasCursor;

	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_thrombolysis_contraindications);

		initUIElements();
		loadContraindicationsData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initUIElements() {
		contrasRadio = (RadioButton) findViewById(R.id.tab_contraindication);
		relaContrasRadio = (RadioButton) findViewById(R.id.tab_contraindication_relative);
		contrasRadio.setOnCheckedChangeListener(this);
		relaContrasRadio.setOnCheckedChangeListener(this);

		contrasListView = (ListView) findViewById(R.id.list_contraindications);
		relaContrasListView = (ListView) findViewById(R.id.list_contraindications_relative);

		contrasRadio.setChecked(true);

		backBtn = (Button) findViewById(R.id.imgbtn_back);
		backBtn.setOnClickListener(this);
	}

	/** Load users data to Spinner */
	public void loadContraindicationsData() {
		contrasAdapter = new ArrayAdapter<String>(DialogContraindication.this,
				R.layout.item_list, AppViewModel.contras);
		contrasAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		contrasListView.setAdapter(contrasAdapter);

		relaContrasAdapter = new ArrayAdapter<String>(
				DialogContraindication.this,
				R.layout.item_list, AppViewModel.relaContras);
		relaContrasAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		relaContrasListView.setAdapter(relaContrasAdapter);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.tab_contraindication:
				contrasListView.setVisibility(ListView.VISIBLE);
				relaContrasListView.setVisibility(ListView.GONE);
				break;
			case R.id.tab_contraindication_relative:
				relaContrasListView.setVisibility(ListView.VISIBLE);
				contrasListView.setVisibility(ListView.GONE);
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(backBtn)) {
			DialogContraindication.this.finish();
		}
	}
}
