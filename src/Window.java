//Provides a window setup
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
// import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne, ai, ballRect;
    public PlayerController playerController;
    public AIController aiController;
    public Ball ball;
    public Texts leftScoreTexts, rightScoreTexts;
    public int leftScore, rightScore;
    public boolean isRunning = true;

    
    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top + 10; // 10 for error correction
        g2 = (Graphics2D)this.getGraphics();

        playerOne = new Rect(Constants.HZ_PADDING, Constants.VT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rect(Constants.SCREEN_WIDTH-Constants.PADDLE_WIDTH-Constants.HZ_PADDING, Constants.VT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);

        ballRect = new Rect(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.PADDLE_COLOR);
        ball = new Ball(ballRect, playerOne, ai, leftScore, rightScore);

        
        aiController = new AIController(new PlayerController(ai), ballRect);

        // leftScore = 0;
        // rightScore = 0;

        leftScoreTexts = new Texts(leftScore , new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), 10, Constants.TEXT_Y_POS);
        rightScoreTexts = new Texts(rightScore , new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH-20, Constants.TEXT_Y_POS);
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

        playerController.update(dt);
        ball.update(dt);
        aiController.update(dt);
        
        leftScore = ball.leftScore;
        rightScore = ball.rightScore;
        leftScoreTexts.text = String.valueOf(leftScore);
        rightScoreTexts.text = String.valueOf(rightScore);

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Font font = new Font("Times New Roman", Font.PLAIN, 14);
        // Texts text = new Texts("Welcome!!!",font, 100, 1);
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
        leftScoreTexts.draw(g2);
        rightScoreTexts.draw(g2);
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
        }
        this.dispose();
        return;
    }
}
