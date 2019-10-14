package clicker;

import java.awt.MouseInfo;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @version 1.0
 * @author Austin
 *
 */

public class Application {

	static final Options options = new Options();
	static final int secs = 1000;
	static final int mins = 60 * secs;
	static final int hours = 60 * mins;
	
	
	static int startDelay = 1000;
	
	public static void main(String[] args) {
		configureArgsOptions();
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "ant", options );
				return;
			}
			
			AutoClicker ac = new ClickerImpl(line.hasOption("debug"));
			
			if(line.hasOption("leftClick")) {
				int delay = getDelay("leftClick",line);
				System.err.println(String.format("Using left click with %dms delay", delay));
				ac = new SimpleLeftClick(delay, ac);
			}
			if(line.hasOption("rightClick")) {
				int delay = getDelay("rightClick",line);
				System.err.println(String.format("Using right click with %dms delay", delay));
				ac = new SimpleRightClick(delay, ac);
			}
			if(line.hasOption("eatFood")) {
				int delay = getDelay("eatFood",line);
				System.err.println(String.format("Using eat food with %dms delay", delay));
				ac = new FoodEater(delay, ac);
			}
			if(line.hasOption("cycleInventory")) {
				int delay = getDelay("cycleInventory",line);
				System.err.println(String.format("Using cycle Inventory with %dms delay", delay));
				ac = new InventoryCycler(delay, ac);
			}
			
			long startTime = System.currentTimeMillis();
			long maxTimeInMillis = 2*hours+startDelay;
			
			
			ac.rob().delay(startDelay); //wait for Alt+Tab
			
			while (true) {
				if (System.currentTimeMillis() - startTime > maxTimeInMillis) break;
				
				if (mouseKillCondition()) break;
				
				ac.doClickBehavior(System.currentTimeMillis());
				ac.rob().delay(10);
				
			}
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}
	

	private static int getDelay(String optString, CommandLine line) {
		try {
			int delay = Integer.parseInt(line.getOptionValue(optString));
			return delay;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal delay given for "+optString+" option.");
		}
		
	}


	private static void configureArgsOptions() {

		options.addOption("i","immediate",false,"Immediately start all behaviors, i.e. don't use behavior delay at start.");
		options.addOption(new Option("d","debug",false,"Enables debug mode. No actions are takenby application. Instead, actions are printed to standard output."));
		options.addOption(new Option("h","help",false,"Lists and briefly describes each behavior currently implemented."));
		
		Option leftClick = new Option("m1","leftClick",true,"Left-clicks at regular intervals");
		leftClick.setArgName("delay");
		options.addOption(leftClick);
		
		Option rightClick = new Option("m2","rightClick",true,"Right-clicks at regular intervals");
		rightClick.setArgName("delay");
		options.addOption(rightClick);
		
		Option cycleInv = new Option("c","cycleInventory",true,"Cycles numbers from 1-9 with a defined delay between each");
		cycleInv.setArgName("delay");
		options.addOption(cycleInv);
		
		Option eatFood = new Option("f", "eatFood",true,"Holds right mouse button for 2 seconds, then releases. Repeats at regular intervals");
		eatFood.setArgName("delay");
		options.addOption(eatFood);
		
	}


	private static boolean mouseKillCondition() {
		return MouseInfo.getPointerInfo().getLocation().getX() < 5 && MouseInfo.getPointerInfo().getLocation().getY() < 5;
	}
}

