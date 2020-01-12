package clicker;

import java.awt.MouseInfo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * @version 1.7
 * @author Austin
 *
 */

public class Application {

	static final Options options = new Options();
	
	
	static long startDelay = 1000;
	static long defaultDuration = TimeUnit.HOURS.toMillis(12);
	
	public static void main(String[] args) {
		
		System.out.println("Arguments: " + Arrays.deepToString(args));
		configureArgsOptions();
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine line = parser.parse(options, args);
			
			if(line.hasOption("preset")) {
				String fileName = line.getOptionValues("preset")[0];
				Map<String,String[]> result = null;
				try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
					result = lines.collect(Collectors.toMap(k -> k.split("\t")[0], v-> v.split("\t")[1].split(" ")));
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				
				String presetName = line.getOptionValues("preset")[1];
				
				System.out.println(String.format("Preset %s requetsed. The following arguments will be used:\n%s",
						presetName,
						Arrays.deepToString(result.get(presetName))));
				
				line = parser.parse(options, result.get(presetName)); 
				
				
			}
			
			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "java -jar clicker.jar [options] [Arg 1 [Arg2 ...]]\n"
						+ "Example 1: java -jar clicker.jar --keyPress (F5=15000)\n"
						+ "Example 2: java -jar clicker.jar --keyPress (F5=15000) -m1 3000\n\n"
						+ "Note: All delays are given in milliseconds (1/1000th of a second). So one second should be entered as '1000'.\n\n", options );
				return;
			}
			
			AutoClicker ac = new ClickerImpl(line.hasOption("debug"));
			
			
			
			if(line.hasOption("leftClick")) {
				int delay = getIntegerArg("leftClick",line);
				System.err.println(String.format("Using left click with %dms delay", delay));
				ac = new SimpleLeftClick(delay, ac);
			}
			if(line.hasOption("rightClick")) {
				int delay = getIntegerArg("rightClick",line);
				System.err.println(String.format("Using right click with %dms delay", delay));
				ac = new SimpleRightClick(delay, ac);
			}

			if (line.hasOption("eatFood")) {
				int delay = getIntegerArg("eatFood", line);
				System.err.println(String.format("Using eat food with %dms delay", delay));
				ac = new FoodEater(delay, ac);
			}
			if (line.hasOption("cycleInventory")) {
				int delay = getIntMultiArg("cycleInventory", line, 0);
				int numSlots = getIntMultiArg("cycleInventory", line, 1);
				System.err.println(String.format("Using cycle Inventory with %dms delay", delay));
				ac = new InventoryCycler(delay, numSlots, ac);
			}
			 
			if(line.hasOption("keyPress")) {
				String cmdLineOptions = line.getOptionValues("keyPress")[0];
				ac = KeyPresserBuilder.build(ac, cmdLineOptions, line.hasOption("debug"));
			}
			
			if(line.hasOption("dupe")) {
				ac = new Duper(ac);
			}
			
			long maxTimeInMillis = defaultDuration+startDelay;
			
			if(line.hasOption("maxTime")) {
				maxTimeInMillis = TimeUnit.HOURS.toMillis((long)getDoubleArg("maxTime",line));
			}
			
			System.out.println("Application will run for " + millisToTime(maxTimeInMillis));
			
			long startTime = System.currentTimeMillis();
			
			
			
			ac.rob().delay((int)startDelay); //wait for Alt+Tab
			
			while (true) {
				long duration =System.currentTimeMillis() - startTime; 
				if (duration > maxTimeInMillis) {
					terminate("Duration exceeded maximum time. Total runtime: " + millisToTime(duration));
					break;
				}
				
				if (mouseKillCondition()) {
					terminate("Execution terminated by user. Total runtime: "+ millisToTime(duration));
					break;
				}
				
				ac.doClickBehavior(System.currentTimeMillis());
				ac.rob().delay(10);
				
			}
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}
	
	private static void terminate (String reason) {
		JOptionPane.showMessageDialog(null, reason, "Execution Terminated", JOptionPane.INFORMATION_MESSAGE);
	}
	

	private static int getIntegerArg(String optString, CommandLine line) {
		try {
			int delay = Integer.parseInt(line.getOptionValue(optString));
			return delay;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal delay given for "+optString+" option.");
		}
		
	}
	
	private static double getDoubleArg(String optString, CommandLine line) {
		try {
			double delay = Double.parseDouble(line.getOptionValue(optString));
			return delay;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal delay given for "+optString+" option.");
		}
		
	}
	
	private static int getIntMultiArg(String optString, CommandLine line, int argIndex) {
		try {
			String value = line.getOptionValues(optString)[argIndex];
			int delay = Integer.parseInt(value);
			return delay;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal delay given for "+optString+" option.");
		}
		
	}


	private static void configureArgsOptions() {
		//options.addOption("i","immediate",false,"Immediately start all behaviors, i.e. don't use behavior delay at start.");
		options.addOption(new Option("d","debug",false,"Enables debug mode. No actions are takenby application. Instead, actions are printed to standard output."));
		options.addOption(new Option("h","help",false,"Lists and briefly describes each behavior currently implemented."));
		
		Option leftClick = new Option("m1","leftClick",true,"Left-clicks at regular intervals");
		leftClick.setArgName("delay");
		options.addOption(leftClick);
		
		Option rightClick = new Option("m2","rightClick",true,"Right-clicks at regular intervals");
		rightClick.setArgName("delay");
		options.addOption(rightClick);
		
		
		Option cycleInv = new Option("c", "cycleInventory", true,
				"Cycles numbers from 1-x, where 1<= x <=9 with a defined delay between each keypress.");
		cycleInv.setArgs(2);
		cycleInv.setArgName("delay> <numSlots");
		cycleInv.setValueSeparator(' ');
		options.addOption(cycleInv);

		Option eatFood = new Option("f", "eatFood", true,
				"Holds right mouse button for 2 seconds, then releases. Repeats at regular intervals");
		eatFood.setArgName("delay");
		options.addOption(eatFood);
		
		options.addOption(new Option("t","maxTime",true,"Sets the maximum number of hours to run the application (Default=2 hours)."));
		
		options.addOption(new Option("d","dupe",false,"perfect timing to piston-dupe item after button press."));
		
		Option keyPress = new Option ("k","keyPress",true,KeyPresserBuilder.getInstructions());
		keyPress.setArgs(1);
		keyPress.setArgName("key1=delay1=offset1,key2=...");
		options.addOption(keyPress);
		
		Option preset = new Option("p","preset",true,"Select presets options from a file.");
		preset.setArgs(2);
		preset.setArgName("fileName> <presetName");
		preset.setValueSeparator(' ');
		options.addOption(preset);
		
	}


	private static boolean mouseKillCondition() {
		return MouseInfo.getPointerInfo().getLocation().getX() < 5 && MouseInfo.getPointerInfo().getLocation().getY() < 5;
	}
	
	private static String millisToTime(long millis) {		return String.format("%d hours, %d min, %d sec", 
				TimeUnit.MILLISECONDS.toHours(millis),
			    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
			    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
			);
	}
}

