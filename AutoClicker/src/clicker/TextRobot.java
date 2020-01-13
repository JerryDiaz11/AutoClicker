package clicker;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Rather than executing the mouse and keyboard input events it's given, this robot prints a description of those requests to the standard output. 
 * @author Austin
 *
 */public class TextRobot extends Robot {
	 
	 
	private BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	private HashMap<String,Long> eventTimestamps = new HashMap<>();
	private long lastEventTime = 0;

	public TextRobot() throws AWTException {
		super();
		
	}
	
	@Override
	public synchronized BufferedImage createScreenCapture(Rectangle rect) {
		long now = System.currentTimeMillis();
		Long lastTimeStamp = eventTimestamps.getOrDefault("screenshot", now);
		eventTimestamps.put("screenshot", now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime) + "\t Screenshot taken. Delay: "+ delay);
		lastEventTime = now;
		return bi;
	}
	
	@Override
	public synchronized void keyPress(int keyCode) {
		long now = System.currentTimeMillis();
		String keyText = KeyEvent.getKeyText(keyCode)+" Press";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime) + "\t" + keyText +" pressed. Delay: "+ delay);
		lastEventTime = now;
    }
	
	@Override
	public synchronized void keyRelease(int keycode) {
		long now = System.currentTimeMillis();
		String keyText = KeyEvent.getKeyText(keycode)+" Release";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime)  + "\t" + keyText +" released. Delay: "+ delay);
		lastEventTime = now;
    }
	
	@Override
	public synchronized void mousePress(int button) {
		long now = System.currentTimeMillis();
		String keyText = InputEvent.getModifiersExText(button)+" Press";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime)  + "\t" + keyText +" pressed. Delay: "+ delay);
		lastEventTime = now;
	}
	
	@Override
	public synchronized void mouseRelease(int button) {
		long now = System.currentTimeMillis();
		
		String keyText = InputEvent.getModifiersExText(button)+" Release";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime)  + "\t" + keyText +" released. Delay: "+ delay);
		lastEventTime = now;
	}
	
	@Override
	public synchronized void mouseMove(int x, int y) {
		long now = System.currentTimeMillis();
		String keyText = "mouseMove";
		Long lastTimeStamp = eventTimestamps.getOrDefault(keyText, now);
		eventTimestamps.put(keyText, now);
		long delay = now - lastTimeStamp;
		Application.log.log(Level.FINEST,(now -lastEventTime)  + "\t" + keyText +" to ("+x+", "+y+"). Delay: "+ delay);
		lastEventTime = now;
	}
}
