import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    // The constructor will take Rect as input 
    // to know which Rectangles movments need to be controlled
    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public PlayerController(Rect rect) {
        this.rect = rect;
        this.keyListener = null;
    }

    public void update(double dt) {
        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN) || keyListener.isKeyPressed(KeyEvent.VK_S)) {
                moveDown(dt);
            } else if (keyListener.isKeyPressed(KeyEvent.VK_UP) || keyListener.isKeyPressed(KeyEvent.VK_W)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp(double dt) {
        if(rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT)
                this.rect.y -= (Constants.PADDLE_SPEED * dt);
    }

    public void moveDown(double dt) {
        if(rect.y + Constants.PADDLE_SPEED * dt + rect.height< Constants.SCREEN_HEIGHT)
                this.rect.y += (Constants.PADDLE_SPEED * dt);
    }
}
