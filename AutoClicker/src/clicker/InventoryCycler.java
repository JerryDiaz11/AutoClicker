package clicker;

import java.awt.event.KeyEvent;


public class InventoryCycler extends AbstractClickerBehavior {
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
	int numSlots = 9;
	
	public InventoryCycler(int delay, int numSlots, AutoClicker clicker) {
		super(delay,clicker);
		if(numSlots < 1 || numSlots > 9) {
			throw new IllegalArgumentException("Number of slots, x, must be in the range: [1,9]. Value entered: "+ numSlots);
		}
		this.numSlots = numSlots;
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().keyPress(keys[nextInv]);
			clicker.rob().delay(100);
			clicker.rob().keyRelease(keys[nextInv++]);
			clicker.rob().delay(10);
			nextInv%=numSlots;
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
