package fi.etla.strokehelper.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenusLevOne {

	/**
	 * An array of menus.
	 */
	public static List<MenuItem> MENUS_LEVEL_ONE = new ArrayList<MenuItem>();

	/**
	 * A map of menus, by ID.
	 */
	public static Map<String, MenuItem> MENU_MAP_LEVEL_ONE = new HashMap<String, MenuItem>();

	/** Four menu */
	static {
		addItem(new MenuItem("1", "Patient"));
		addItem(new MenuItem("2", "Process"));
		addItem(new MenuItem("3", "Thrombolysis"));
		addItem(new MenuItem("4", "Summary"));
	}

	private static void addItem(MenuItem item) {
		MENUS_LEVEL_ONE.add(item);
		MENU_MAP_LEVEL_ONE.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class MenuItem {
		public String id;
		public String content;

		public MenuItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
