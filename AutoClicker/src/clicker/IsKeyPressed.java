package clicker;

public class IsKeyPressed {
    
	static volatile boolean escPressed = false;
    
    public static boolean isEscapePressed() {
        synchronized (IsKeyPressed.class) {
            return escPressed;
        }
    }

   
}
  