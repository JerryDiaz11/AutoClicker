package clicker;

/**
 * This class reads the String of the command line for the KeyPresser option and
 * builds the specified KeyPresser. The {@code cmdLineOptions} value must follow
 * this format:
 * 
 * {@code (key1=delay1=offset1,key2=delay2=offset2,key3=delay3=offset3,...)}
 * 
 * Note: Offsets are optional. the following is a valid entry:
 * {@code (a=2000,b=2000=1000)} This entry will cause the application to
 * alternate between 'a' and 'b' every 1 second.
 * 
 * @author Austin
 *
 */
public class KeyPresserBuilder {

	public KeyPresserBuilder() {
	}

	public static AutoClicker build(AutoClicker ac, String cmdLineOptions) {
		
		String trimmedOpts = cmdLineOptions.substring(1, cmdLineOptions.length() - 1);
		
		
		String[] keyDelayPairs = trimmedOpts.split(",");

		for (String kdPair : keyDelayPairs) {
			String[] kdPairArr = kdPair.split("=");

			String keyString = kdPairArr[0];
			
			int delay;
			try {
				delay = Integer.parseInt(kdPairArr[1]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Illegal delay given for KeyPresser option key: " + kdPair + ".");
			}

			int offset = 0;
			if (kdPairArr.length > 2) {
				try {
					offset = Integer.parseInt(kdPairArr[2]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException(
							"Illegal offset given for KeyPresser option key: " + kdPair + ".");
				}
			}
			
			int duration = 0;
			if (kdPairArr.length > 3) {
				try {
					duration = Integer.parseInt(kdPairArr[3]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException(
							"Illegal duration given for KeyPresser option key: " + kdPair + ".");
				}
			}

			ac = new KeyPresser(keyString, delay, offset, duration, ac);
		}

		return ac;

	}

	public static String getInstructions() {
		return "Presses the specified keys at a regular interval with the specified delays and durations. Here "
				+ "'key' is the button to press, "
				+ "'delay' is the delay between presses, "
				+ "'offset' (optional) is the number of milliseconds to wait before the first key press. "
				+ "'duration' is the length of time to press the key. For example \"(a=2000,b=2000=1000=500)\" will alternate between pressing 'a' and 'b' with a 1-sec delay between each, and 'b' is held for a 1/2 second";

	}

}
