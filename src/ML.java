import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter /*implements MouseMotionListener*/ {
    private boolean isPressed = false;
    private double x = 0.0, y = 0.0;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public double getMouseX() {
        return this.x;
    }

    public double getMouseY() {
        return this.y;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isOnText(Texts text) {
        if (getMouseX() >= text.x && getMouseX() <= text.x + text.width &&
            getMouseY() >= text.y - text.height/2 && getMouseY() <= text.y + text.height/2)
            return true;
        return false;
    }
    
}
