package ch.bbw.zork;public class CommandWords {	private static final String validCommands[] = {			"go", "back", "map", "quit", "help", "items", "take", "drop", "backpack", "merge"	};	public boolean isCommand(String aString) {		for (int i = 0; i < validCommands.length; i++) {			if (validCommands[i].equals(aString))				return true;		}		return false;	}	public String showAll() {		String outputStr = "";		for (int i = 0; i < validCommands.length; i++) {			outputStr += validCommands[i] + "  ";		}		return outputStr;	}}