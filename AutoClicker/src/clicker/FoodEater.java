package clicker;

import java.awt.event.InputEvent;


public class FoodEater extends ClickerBehavior {
	
	public FoodEater(int delay, AutoClicker clicker) {
		super(delay,clicker);
		
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			synchronized(clicker.rob()){
			clicker.rob().mousePress(InputEvent.BUTTON1_DOWN_MASK);
			clicker.rob().mousePress(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().delay(2000);
			clicker.rob().mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
