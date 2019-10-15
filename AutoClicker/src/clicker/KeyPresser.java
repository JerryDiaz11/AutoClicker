package clicker;

import java.awt.event.InputEvent;

public class KeyPresser extends AbstractClickerBehavior {

	private int keyCode;
	private String keyString;
	private KeyCodeMap map = new KeyCodeMap();
	
	public KeyPresser(String keyString, int delay, AutoClicker clicker) {
		super(delay,clicker);
		this.keyString = keyString;
		this.keyCode = map.getCode(keyString);
		System.err.println(String.format("Using key presser to press %s with %dms delay", keyString, delay));
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			clicker.rob().keyPress(this.keyCode);
			clicker.rob().delay(10);
			clicker.rob().keyRelease(this.keyCode);
			clicker.rob().delay(10);
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}