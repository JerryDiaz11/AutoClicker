package clicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Rather than executing the mouse and keyboard input events it's given, this robot prints a description of those requests to the standard output. 
 * @author Austin
 *
 */public class TextRobot extends Robot {
	
	private HashMap<String,Long> eventTimestamps = new HashMap<>();

	public TextRobot() throws AWTException {
		super();
		
	}
	
	@Override
	public synchronized void keyPress(int keyCode) {
		long now = System.currentTimeMillis();
		String keyText = KeyEvent.getKeyText(keyCode)+" Press";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		System.out.println(keyText +" pressed. Delay: "+ delay);
    }
	
	@Override
	public synchronized void keyRelease(int keycode) {
		long now = System.currentTimeMillis();
		String keyText = KeyEvent.getKeyText(keycode)+" Release";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		System.out.println(keyText +" released. Delay: "+ delay);
    }
	
	@Override
	public synchronized void mousePress(int button) {
		long now = System.currentTimeMillis();
		String keyText = InputEvent.getModifiersExText(button)+" Press";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		System.out.println(keyText +" pressed. Delay: "+ delay);
	}
	
	@Override
	public synchronized void mouseRelease(int button) {
		long now = System.currentTimeMillis();
		
		String keyText = InputEvent.getModifiersExText(button)+" Release";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		System.out.println(keyText +" released. Delay: "+ delay);
	}
	
	@Override
	public synchronized void mouseMove(int x, int y) {
		long now = System.currentTimeMillis();
		String keyText = "mouseMove";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		System.out.println(keyText +" to ("+x+", "+y+"). Delay: "+ delay);
	}
}
