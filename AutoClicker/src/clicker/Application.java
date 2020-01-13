package clicker;

import java.awt.MouseInfo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



/**
 * @version 1.8
 * @author Austin
 *
 */

public class Application {

	
	static long startDelay = 1000;
	static long defaultDuration = TimeUnit.HOURS.toMillis(12);
	static final Logger log = Logger.getLogger("Application");
	
	static {
		//The following code change the format of the logger output
	      log.setUseParentHandlers(false);
	      ConsoleHandler handler = new ConsoleHandler();
	      handler.setFormatter(new SimpleFormatter() {
	          private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

	          @Override
	          public synchronized String format(LogRecord lr) {
	        	  return String.format(format,
	                      new Date(lr.getMillis()),
	                      lr.getLevel().getLocalizedName(),
	                      lr.getMessage()
	              );
	          }
	      });
	      log.addHandler(handler);
	  }

	
	
	
	public static void main(String[] args) {
		
		log.info("Arguments: " + Arrays.deepToString(args));
		final Options options = OptionsBuilder.configureArgsOptions();
		log.fine("Options Built");
		
				
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine line = parser.parse(options, args);
			
			boolean debugMode = line.hasOption(OptionsBuilder.OPT_DEBUG);
			
			if(line.hasOption(OptionsBuilder.OPT_PRESET)) {
				String fileName = line.getOptionValues(OptionsBuilder.OPT_PRESET)[0];
				Map<String,String[]> result = null;
				try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
					result = lines.collect(Collectors.toMap(k -> k.split("\t")[0], v-> v.split("\t")[1].split(" ")));
				} catch (IOException e) {
					e.printStackTrace();
					return;
				} catch (ArrayIndexOutOfBoundsException e) {
					log.severe("ERROR: The preset is formatted incorrectly. Ensure you use a tab character after the preset name. Application terminated.");
					return;
				}
				
				String presetName = line.getOptionValues(OptionsBuilder.OPT_PRESET)[1];
				
				log.info(String.format("Preset %s requetsed. The following arguments will be used: %s",
						presetName,
						Arrays.deepToString(result.get(presetName))));
				
				line = parser.parse(options, result.get(presetName)); 
				
				
			}
			
			if (line.hasOption(OptionsBuilder.OPT_HELP)) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "java -jar clicker.jar [options] [Arg 1 [Arg2 ...]]\n"
						+ "Example 1: java -jar clicker.jar --keyPress (F5=15000)\n"
						+ "Example 2: java -jar clicker.jar --keyPress (F5=15000) -m1 3000\n\n"
						+ "Note: All delays are given in milliseconds (1/1000th of a second). So one second should be entered as '1000'.\n\n", options );
				return;
			}
			
			debugMode = debugMode || line.hasOption(OptionsBuilder.OPT_DEBUG);
			
			if(debugMode) {
				log.setLevel(Level.ALL);
			}
			
			AutoClicker ac = new ClickerImpl(debugMode);
			
			if(line.hasOption(OptionsBuilder.OPT_MOUSE1)) {
				int delay = getIntegerArg(OptionsBuilder.OPT_MOUSE1,line);
				System.err.println(String.format("Using left click with %dms delay", delay));
				ac = new SimpleLeftClick(delay, ac);
			}
			if(line.hasOption(OptionsBuilder.OPT_MOUSE2)) {
				int delay = getIntegerArg(OptionsBuilder.OPT_MOUSE2,line);
				System.err.println(String.format("Using right click with %dms delay", delay));
				ac = new SimpleRightClick(delay, ac);
			}

			if (line.hasOption(OptionsBuilder.OPT_EAT_FOOD)) {
				int delay = getIntegerArg(OptionsBuilder.OPT_EAT_FOOD, line);
				System.err.println(String.format("Using eat food with %dms delay", delay));
				ac = new FoodEater(delay, ac);
			}
			if (line.hasOption(OptionsBuilder.OPT_CYCLE_INVENTORY)) {
				int delay = getIntMultiArg(OptionsBuilder.OPT_CYCLE_INVENTORY, line, 0);
				int numSlots = getIntMultiArg(OptionsBuilder.OPT_CYCLE_INVENTORY, line, 1);
				System.err.println(String.format("Using cycle Inventory with %dms delay", delay));
				ac = new InventoryCycler(delay, numSlots, ac);
			}
			 
			if(line.hasOption(OptionsBuilder.OPT_KEYPRESS)) {
				String cmdLineOptions = line.getOptionValues(OptionsBuilder.OPT_KEYPRESS)[0];
				ac = KeyPresserBuilder.build(ac, cmdLineOptions, debugMode);
			}
			
			if(line.hasOption(OptionsBuilder.OPT_DUPE)) {
				ac = new Duper(ac);
			}
			
			if(line.hasOption(OptionsBuilder.OPT_SCREENSHOT)) {
				int delay = getIntMultiArg(OptionsBuilder.OPT_SCREENSHOT,line, 0);
				String filePath = line.getArgs()[0];
				
				ac = new PictureTaker(delay, filePath, ac);
			}
			
			long maxTimeInMillis = defaultDuration+startDelay;
			
			if(line.hasOption(OptionsBuilder.OPT_MAX_TIME)) {
				maxTimeInMillis = TimeUnit.HOURS.toMillis((long)getDoubleArg(OptionsBuilder.OPT_MAX_TIME,line));
			}
			
			log.info("Application will run for " + millisToTime(maxTimeInMillis));
			
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
		log.info(reason);
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

