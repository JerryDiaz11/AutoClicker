package clicker;

public class KeyPresser extends AbstractClickerBehavior {

	private int keyCode;
	private int offset;
	private boolean awaitingOffset = true;
	private long firstClickTry = 0;
	private KeyCodeMap map = new KeyCodeMap();
	private int duration;
	
	public KeyPresser(String keyString, int delay, int offset, int duration, AutoClicker clicker) {
		super(delay,clicker);
		this.keyCode = map.getCode(keyString);
		this.offset = offset;
		this.duration = duration;
		System.err.println(String.format("Using key presser to press %s with %dms delay %d offset and %d duration", keyString, delay, offset, duration));
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(awaitingOffset) {
			if(firstClickTry == 0) {
				firstClickTry = currentTimeMillis;
			}
			
			if(currentTimeMillis - firstClickTry > offset) {
				awaitingOffset = false;
			}
			
		}
		
		if(!awaitingOffset && isTimeToExecute(currentTimeMillis)) {
			clicker.rob().keyPress(this.keyCode);
			clicker.rob().delay(this.duration);
			clicker.rob().keyRelease(this.keyCode);
			clicker.rob().delay(10);
		} 
		
		
		return super.doClickBehavior(currentTimeMillis);
	}
	


}