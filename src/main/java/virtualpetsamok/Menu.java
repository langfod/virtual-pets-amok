/*
 * Menu:
 * class to implement a Menu Generator.
 * 
 * Author: David Langford
 * Date  : Feb 02, 2018
 * 
 * 
 * Attempt to create a 'generic' menu generator.
 * Should be able to create a menu with user definable columns. (one thing works right at least \o/ )
 * 
 * 
 * Should have breadcrumbs also but currently keto.
 * 
 * Currently the 'generic' concept is an abject failure. (see needed get* commands below)
 * 
 * invoking the class is requires a  String used as a title and an optional Type of Menu.
 * Current types are:
 *  "ordinal" 		 "1. Title" 
 *  "splitkeyname" "[Choice] Title" 
 *  
 *  Currently "splitkeyname" requires that the "Choice" and "Title" be sent as a comma separated String ***
 *  
 *  addItem(String, Runnable)  adds a menu item to the menu
 *  
 *  genMenuString([int], String) generates a string representation of the menu.
 *  
 *  To select menu items currently requires that of one two methods are used:
 *  
 *  getOrdinalMenuChoice(Scanner)
 *  This is for ordinal menu types and returns a <Runnable>
 *  
 *  getSplitKeyMenuChoice(Scanner)
 *  This is for "splitkeyname" types and return a Strings (the value entered).
 *  
 *  
 */
package virtualpetsamok;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private String title;
	private String menuType;
	private Map<String, Runnable> commandMap = new LinkedHashMap<>();

	public Menu(String title) {
		this(title, "ordinal");
	}

	public Menu(String title, String menuType) {
		this.title = title;
		this.menuType = menuType;
	}

	public void addItem(String name, Runnable command) {
		commandMap.put(name, command);
	}

	public String genMenuString(int columns) {
		return genMenuString(columns, "What would you like to do next?");

	}

	public String genMenuString(String message) {
		return genMenuString(1, message);
	}

	public String genMenuString() {
		return genMenuString(1, "What would you like to do next?");

	}

	public String genMenuString(int columns, String message) {
		StringBuffer sb = new StringBuffer();
		Formatter fm = new Formatter(sb);

		fm.format("%s->%n %s%n%n", title, message);

		int indexer = 1;
		switch (menuType) {

		case "splitkeyname":
			for (String commandName : commandMap.keySet()) {
				String[] labelArray = commandName.split(",");
				fm.format("%12s %20s%3s", "[" + labelArray[0] + "]", labelArray[1],
						indexer % columns == 0 ? "\n" : " | ");
				++indexer;
			}
			break;
		case "ordinal":
		default:
			for (String header : commandMap.keySet()) {
				fm.format("%2d. %-16s%3s", indexer, header, indexer % columns == 0 ? "\n" : " | ");
				++indexer;
			}
			break;
		}

		fm.close();
		return sb.toString();
	}

	public Runnable getOrdinalMenuChoice(Scanner input) {
		String errorMsg = "Please use the menu numbers.";
		Runnable outputCmd = () -> {
		};
		try {
			int userPick = Integer.parseInt(input.nextLine());
			if (userPick < 1 || userPick > commandMap.size()) {
				System.err.println(errorMsg);
			} else {
				outputCmd = (new ArrayList<Runnable>(commandMap.values())).get(userPick - 1);
			}
		} catch (NumberFormatException e) {
			System.err.println(errorMsg);
		}
		return outputCmd;
	}

	public String getSplitKeyMenuChoice(Scanner input) {
		String output = "";
		String userPick = input.nextLine();

		for (String commandKey : commandMap.keySet()) {
			String menuLabel = commandKey.split(",")[0];
			if (userPick.toLowerCase().equals(menuLabel.toLowerCase())) {
				output = menuLabel;
				break;
			}
		}
		return output;
	}

}
