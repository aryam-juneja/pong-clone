//Provides a window setup
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
// import java.awt.event.KeyEvent;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    Texts startGame, exitGame, title;
    public boolean isRunning = true;

    
    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        // Constants.TOOLBAR_HEIGHT = this.getInsets().top + 10; // 10 for error correction
        g2 = (Graphics2D)this.getGraphics();
        this.startGame = new Texts("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH/2 -120, Constants.SCREEN_HEIGHT/2);
        this.exitGame = new Texts("Exit Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH/2 - 100, Constants.SCREEN_HEIGHT/2 + 60);
        this.title = new Texts("Pong", new Font("Times New Roman", Font.PLAIN, 100), Constants.SCREEN_WIDTH/2 - 140, Constants.TOOLBAR_HEIGHT + 100);
    }

    public void update(double dt) {

        // This implements the double buffer method
        // makes everythig to be drawn off screen in one image
        // instead of having everythig being drawn on top of each element and 
        // then replaces them to genereate super smooth graphics
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        if (mouseListener.isOnText(startGame)) {
                startGame.color = new Color(150,150,150);

                if (mouseListener.isPressed()) {
                    Main.changeState(1);
                }
        } else {
            startGame.color = Color.WHITE;
        }

        if (mouseListener.isOnText(exitGame)) {
            exitGame.color = new Color(150,150,150);
        } else {
            exitGame.color = Color.WHITE;
        }

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        startGame.draw(g2);
        exitGame.draw(g2);
        title.draw(g2);
        // Font font = new Font("Times New Roman", Font.PLAIN, 14);
        // Texts text = new Texts("Welcome!!!",font, 100, 1);
    
    }

    public void stop() {
        isRunning = false;
    }

    public void run() {
        double lastFrameTime = 0.0;
        while(isRunning){
            double time = Time.getTime();
            double deltaTime = time-lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            //to limit the frame-rate 
            // try {
            //     Thread.sleep(30);
            // } catch(Exception e) {}
        }
        this.dispose();
        return;
    }
}
