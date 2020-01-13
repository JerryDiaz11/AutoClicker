package clicker;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;


public class PictureTaker extends AbstractClickerBehavior {

	private String fileLocation;
	private File file;
	
	public PictureTaker(int delay, String fileLocation, AutoClicker clicker) {
		super(delay,clicker);
		this.fileLocation = fileLocation;
		this.file = new File(this.fileLocation);
		this.file.getParentFile().mkdirs();
		try {
			this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(isTimeToExecute(currentTimeMillis)) {
			synchronized(clicker.rob()){
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture = clicker.rob().createScreenCapture(screenRect);
				try {
					if(this.file.canWrite()) {
						ImageIO.write(capture, "png", this.file);
						Application.log.log(Level.INFO,"Screenshot saved to "+file);
					} else {
						Application.log.log(Level.WARNING,"Unable to write to "+this.file + ". The file may be in use by another application.");
					}
					
				} catch (IIOException e) {
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return super.doClickBehavior(currentTimeMillis);
		
	}
	


}