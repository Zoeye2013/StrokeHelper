package fi.etla.strokehelper.process;

import fi.etla.strokehelper.R;
import fi.etla.strokehelper.patient.FragmentPatient;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentProcessMenus extends Fragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private ProcessMenusCallbacks mCallbacks = processMenusCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	private RadioGroup tabGroup;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface ProcessMenusCallbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onTabSelected(int id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static ProcessMenusCallbacks processMenusCallbacks = new ProcessMenusCallbacks() {
		@Override
		public void onTabSelected(int id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public FragmentProcessMenus() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO: replace with a real list adapter.
		// setListAdapter(new ArrayAdapter<MenusLevOne.MenuItem>(getActivity(),
		// android.R.layout.simple_list_item_activated_1,
		// android.R.id.text1, MenusLevOne.MENUS_LEVEL_ONE));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail_process,
				container, false);
		tabGroup = (RadioGroup) rootView.findViewById(R.id.tabgroup);
		tabGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_door:

					break;
				case R.id.tab_anamnesis:
					int b = checkedId;
					break;
				case R.id.tab_present_condition:
					break;
				case R.id.tab_imaging_testing:
					break;
				case R.id.tab_symptoms:
					int i = checkedId;
					break;
				case R.id.tab_nihss:
					break;
				}
				mCallbacks.onTabSelected(checkedId);
			}
		});
		/** By default show door tab */
		tabGroup.check(R.id.tab_door);
		return rootView;
	}

	// @Override
	// public void onViewCreated(View view, Bundle savedInstanceState) {
	// super.onViewCreated(view, savedInstanceState);
	//
	// // Restore the previously serialized activated item position.
	// if (savedInstanceState != null
	// && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
	// setActivatedPosition(savedInstanceState
	// .getInt(STATE_ACTIVATED_POSITION));
	// }
	// }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof ProcessMenusCallbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (ProcessMenusCallbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = processMenusCallbacks;
	}

	// @Override
	// public void onListItemClick(ListView listView, View view, int position,
	// long id) {
	// super.onListItemClick(listView, view, position, id);
	//
	// // Notify the active callbacks interface (the activity, if the
	// // fragment is attached to one) that an item has been selected.
	// mCallbacks.onItemSelected(MenusLevOne.MENUS_LEVEL_ONE.get(position).id);
	// }

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// // When setting CHOICE_MODE_SINGLE, ListView will automatically
		// // give items the 'activated' state when touched.
		// getListView().setChoiceMode(
		// activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
		// : ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		// if (position == ListView.INVALID_POSITION) {
		// getListView().setItemChecked(mActivatedPosition, false);
		// } else {
		// getListView().setItemChecked(position, true);
		// }

		mActivatedPosition = position;
	}
}
