package clicker;

import java.awt.MouseInfo;

public class MoveMouse extends AbstractClickerBehavior {
	
	public MoveMouse(int delay, AutoClicker clicker) {
		super(delay,clicker);
		this.setDelayInMiliseconds(100);
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			System.out.println(MouseInfo.getPointerInfo().getLocation());
			
		}
		return super.doClickBehavior(currentTimeMillis);
	}


}
