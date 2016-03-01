package fi.etla.strokehelper.authentification;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.data.AppViewModel;
import fi.etla.strokehelper.data.DataBaseManage;
import fi.etla.strokehelper.main.MainActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DialogLoginActivity extends Activity implements OnClickListener {

	/** UI elements */
	private Spinner selectUserSpinner;
	private EditText usernameEdit;
	private TextView warningText;
	private Button loginBtn;
	private Button cancelBtn;

	/** Get accounts information from SharedPreferences */
	public static SharedPreferences accountPreference;
	public static SharedPreferences.Editor accountPreferenceEditor;

	/** Spinner adapter */
	private SimpleCursorAdapter usersAdapter;
	private Cursor usersCursor;
	
	/** Static Tags */
	public static final String TAG_SHARE_PREFERENCE = "Account";
	public static final String TAG_LAST_LOG_IN_USER = "last_log_in";
	public static final String TAG_CURRENT_USER = "username";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_login);
		setTitle(R.string.title_log_in);

		/** Get SharedPreference for accounts information */
		accountPreference = getSharedPreferences(
				TAG_SHARE_PREFERENCE, Context.MODE_PRIVATE);
		accountPreferenceEditor = accountPreference.edit();

		if(MainActivity.appViewModel == null){
			MainActivity.appViewModel = new AppViewModel(DialogLoginActivity.this);
		}

		initUIElements();
		checkWhetherLogOut();
		
	}
	
	

	@Override
	protected void onDestroy() {
//		dialogLoginViewModel.closeDataBase();
		super.onDestroy();
	}



	public void initUIElements() {

		selectUserSpinner = (Spinner) findViewById(R.id.spinner_select_username);
		usernameEdit = (EditText) findViewById(R.id.edit_username);
		warningText = (TextView) findViewById(R.id.waring_no_username);
		warningText.setVisibility(TextView.INVISIBLE);
		loginBtn = (Button) findViewById(R.id.btn_login);
		cancelBtn = (Button) findViewById(R.id.btn_cancel_login);
		loginBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	}

	/** Check if user logged out */
	public void checkWhetherLogOut() {
		String lastLogInUser = accountPreference.getString(
				TAG_LAST_LOG_IN_USER, "");
		long currPatient = accountPreference.getLong(MainActivity.TAG_CURRENT_PATIENT, -1);

		if (lastLogInUser.length() > 0) {
			
			Intent loginIntent = new Intent(DialogLoginActivity.this,
					MainActivity.class);
			loginIntent
					.putExtra(TAG_CURRENT_USER, lastLogInUser);
			if(currPatient >0)
			{
				loginIntent.putExtra(MainActivity.TAG_CURRENT_PATIENT, currPatient);
			}
			loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			DialogLoginActivity.this.startActivity(loginIntent);
			DialogLoginActivity.this.finish();
		}
		else{
			loadUsersData();
		}
	}
	
	/** Load users data to Spinner */
	public void loadUsersData(){
		/** Init DAO */
		
		usersCursor = AppViewModel.userDAO.fetchAllDoctorUsers();
		
		if(usersCursor != null){
		/** Spinner Adapter */
		usersAdapter = new SimpleCursorAdapter(DialogLoginActivity.this, android.R.layout.simple_spinner_item, usersCursor, new String[] { DataBaseManage.COLUMN_DOCTOR_USERNAME},
				new int[] {android.R.id.text1}, 0);
		usersAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selectUserSpinner.setAdapter(usersAdapter);}
		else if(usersCursor == null){
			
		}
	}

	/** OnClick listener for buttons */
	@Override
	public void onClick(View view) {
		/** Cancel login */
		if (view.equals(cancelBtn)) {
			DialogLoginActivity.this.finish();
		}
		/** Login */
		else if (view.equals(loginBtn)) {

			String tempEdit = usernameEdit.getText().toString();
			if (tempEdit.length() > 0) {
				AppViewModel.userDAO.setName(tempEdit);
			} else if (selectUserSpinner.getSelectedItem() != null) {
				int pos = selectUserSpinner.getSelectedItemPosition();
				usersCursor.moveToPosition(pos);
				String tempSpin = usersCursor.getString(usersCursor.getColumnIndexOrThrow(DataBaseManage.COLUMN_DOCTOR_USERNAME));
				if (tempSpin.length() > 0) {
					AppViewModel.userDAO.setName(tempSpin);
				}
			}

			if (AppViewModel.userDAO.getName() != null && AppViewModel.userDAO.getName().length() > 0) {
				AppViewModel.userDAO.addDoctorUser();
				warningText.setVisibility(TextView.INVISIBLE);
				accountPreferenceEditor.putString(
						TAG_LAST_LOG_IN_USER, AppViewModel.userDAO.getName());
				
				accountPreferenceEditor.commit();
				Intent loginIntent = new Intent(DialogLoginActivity.this,
						MainActivity.class);
				loginIntent.putExtra(TAG_CURRENT_USER, AppViewModel.userDAO.getName());
				loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				DialogLoginActivity.this.startActivity(loginIntent);
				DialogLoginActivity.this.finish();
			} else {
				warningText.setVisibility(TextView.VISIBLE);
			}
		}
	}
}
