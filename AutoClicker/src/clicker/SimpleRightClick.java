package clicker;

import java.awt.event.InputEvent;


public class SimpleRightClick extends ClickerBehavior {
	
	public SimpleRightClick(int delay, AutoClicker clicker) {
		super(delay,clicker);
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().mousePress(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().delay(10);
			clicker.rob().mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().delay(10);
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
