package clicker;

import java.awt.event.InputEvent;


public class SimpleClick extends ClickerBehavior {
	
	public SimpleClick(int delay, AutoClicker clicker) {
		super(delay,clicker);
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().mousePress(InputEvent.BUTTON1_DOWN_MASK);
			clicker.rob().delay(10);
			clicker.rob().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			clicker.rob().delay(10);
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
