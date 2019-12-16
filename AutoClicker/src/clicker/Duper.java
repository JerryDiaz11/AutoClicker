package clicker;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class Duper extends AbstractClickerBehavior {
	
	public Duper(AutoClicker clicker) {
		super(6500,clicker);
	}
	

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().delay(3000); //prepare to click
			
			clicker.rob().mousePress(InputEvent.BUTTON3_DOWN_MASK); //press button
			clicker.rob().delay(100);
			clicker.rob().mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			
			clicker.rob().delay(250); //wait for piston to move
			
			clicker.rob().mousePress(InputEvent.BUTTON3_DOWN_MASK); //open box
			clicker.rob().delay(100);
			clicker.rob().mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().keyPress(KeyEvent.VK_SHIFT);
			
			clicker.rob().delay(1130); //delay till just before piston retracts
			
			//take item
			
			clicker.rob().mousePress(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().delay(20);
			clicker.rob().mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			clicker.rob().keyRelease(KeyEvent.VK_SHIFT);
			
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
