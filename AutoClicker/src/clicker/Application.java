package clicker;

import java.awt.MouseInfo;

/**
 * @version 1.0
 * @author Austin
 *
 */

public class Application {


	public static void main(String[] args) {
        
		final int secs = 1000;
		final int mins = 60* secs;
		final int hours = 60*mins;
		
		
		int startDelay = 1000;
		
		AutoClicker ac = new InventoryCycler(70, new SimpleRightClick(1*secs, new ClickerImpl())); 
		long startTime = System.currentTimeMillis();
		long maxTimeInMillis = 2*hours+startDelay;
		
		
		ac.rob().delay(startDelay); //wait for Alt+Tab
		
		while (true) {
			if (System.currentTimeMillis() - startTime > maxTimeInMillis) break;
			
			if (mouseKillCondition()) break;
			
			ac.doClickBehavior(System.currentTimeMillis());
			ac.rob().delay(10);
			
		}

	}

	private static boolean mouseKillCondition() {
		return MouseInfo.getPointerInfo().getLocation().getX() < 5 && MouseInfo.getPointerInfo().getLocation().getY() < 5;
	}
}

