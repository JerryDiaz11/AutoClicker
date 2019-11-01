package clicker;

import java.awt.Robot;

public interface AutoClicker {
	
	Robot rob();
	
	String doClickBehavior(long currentTimeMillis);

}
