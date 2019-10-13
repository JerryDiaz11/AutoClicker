package clicker;

import java.awt.event.KeyEvent;


public class InventoryCycler extends ClickerBehavior {
	static final int[] keys = {
			KeyEvent.VK_1,
			KeyEvent.VK_2,
			KeyEvent.VK_3,
			KeyEvent.VK_4,
			KeyEvent.VK_5,
			KeyEvent.VK_6,
			KeyEvent.VK_7,
			KeyEvent.VK_8,
			KeyEvent.VK_9};
	int nextInv = 0;
	
	public InventoryCycler(int delay, AutoClicker clicker) {
		super(delay,clicker);
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().keyPress(keys[nextInv]);
			clicker.rob().delay(100);
			clicker.rob().keyRelease(keys[nextInv++]);
			clicker.rob().delay(10);
			nextInv%=9;
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
