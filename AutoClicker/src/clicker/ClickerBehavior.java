package clicker;

import java.awt.Robot;

public abstract class ClickerBehavior implements AutoClicker {

	protected AutoClicker clicker;
	
	public ClickerBehavior(int delay,AutoClicker clicker) {
		this.clicker = clicker;
		this.delayInMiliseconds = delay;
	}
	
	private int delayInMiliseconds = 1000;
	private long timeOfLastBehaviorExecute = 0;
	
	@Override
	public String doClickBehavior(long currentTimeMillis) {
		return clicker.doClickBehavior(currentTimeMillis);
	}
	
	public int getDelayInMiliseconds() { return this.delayInMiliseconds;}
	public void setDelayInMiliseconds(int delay) { this.delayInMiliseconds = delay;}
	
	boolean isTimeToExecute(long currentTimeMillis) {
		if (currentTimeMillis - timeOfLastBehaviorExecute > delayInMiliseconds) {
			markTimeOfLastBehaviorExecute(currentTimeMillis);
			return true;
		}
		return false;
	}
	
	private void markTimeOfLastBehaviorExecute(long currentTimeMillis) {
		this.timeOfLastBehaviorExecute = currentTimeMillis;
	}
	

	@Override
	public Robot rob() {
		return this.clicker.rob();
	}
	

}
