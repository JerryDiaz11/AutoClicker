package clicker;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsBuilder {

	public OptionsBuilder() {	}
	
	public static final String OPT_DEBUG = "debug";
	public static final String OPT_HELP = "help";
	public static final String OPT_MOUSE1 = "leftClick";
	public static final String OPT_MOUSE2 = "rightClick";
	public static final String OPT_CYCLE_INVENTORY = "cycleInventory";
	public static final String OPT_EAT_FOOD = "eatFood";
	public static final String OPT_MAX_TIME = "maxTime";
	public static final String OPT_DUPE = "dupe";
	public static final String OPT_KEYPRESS = "keyPress";
	public static final String OPT_PRESET = "preset";
	public static final String OPT_SCREENSHOT = "screenshot";
	
	static Options configureArgsOptions() {
		
		Options options = new Options();
		//options.addOption("i","immediate",false,"Immediately start all behaviors, i.e. don't use behavior delay at start.");
		options.addOption(new Option("d",OPT_DEBUG,false,"Enables debug mode. No actions are takenby application. Instead, actions are printed to standard output."));
		options.addOption(new Option("h",OPT_HELP,false,"Lists and briefly describes each behavior currently implemented."));
		
		Option leftClick = new Option("m1",OPT_MOUSE1,true,"Left-clicks at regular intervals");
		leftClick.setArgName("delay");
		options.addOption(leftClick);
		
		Option rightClick = new Option("m2",OPT_MOUSE2,true,"Right-clicks at regular intervals");
		rightClick.setArgName("delay");
		options.addOption(rightClick);
		
		
		Option cycleInv = new Option("c", OPT_CYCLE_INVENTORY, true,
				"Cycles numbers from 1-x, where 1<= x <=9 with a defined delay between each keypress.");
		cycleInv.setArgs(2);
		cycleInv.setArgName("delay> <numSlots");
		cycleInv.setValueSeparator(' ');
		options.addOption(cycleInv);

		Option eatFood = new Option("f", OPT_EAT_FOOD, true,
				"Holds right mouse button for 2 seconds, then releases. Repeats at regular intervals");
		eatFood.setArgName("delay");
		options.addOption(eatFood);
		
		options.addOption(new Option("t",OPT_MAX_TIME,true,"Sets the maximum number of hours to run the application (Default=2 hours)."));
		
		options.addOption(new Option("d",OPT_DUPE,false,"perfect timing to piston-dupe item after button press."));
		
		Option keyPress = new Option ("k",OPT_KEYPRESS,true,KeyPresserBuilder.getInstructions());
		keyPress.setArgs(1);
		keyPress.setArgName("key1=delay1=offset1,key2=...");
		options.addOption(keyPress);
		
		Option preset = new Option("p",OPT_PRESET,true,"Select presets options from a file.");
		preset.setArgs(2);
		preset.setArgName("fileName> <presetName");
		preset.setValueSeparator(' ');
		options.addOption(preset);
		
		Option screenshot = new Option("s",OPT_SCREENSHOT,true,"Saves a screenshot to a file");
		preset.setArgs(2);
		preset.setArgName("delay> <destination");
		preset.setValueSeparator(' ');
		options.addOption(screenshot);
		
		return options;
		
	}

}
