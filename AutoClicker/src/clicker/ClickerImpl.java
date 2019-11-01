package clicker;

import java.awt.AWTException;
import java.awt.Robot;

public class ClickerImpl implements AutoClicker {
	
	 private Robot rob;
	 
	 public ClickerImpl(boolean debug) {
		 try {
			this.rob = debug?new TextRobot():new Robot();
		} catch (AWTException e) {
			System.out.print("Unable to initialize Robot.");
			e.printStackTrace();
		}
	 }

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		return "ClickerImpl done at "+ currentTimeMillis;
	}

	@Override
	public Robot rob() {
		return rob;
	}

}
