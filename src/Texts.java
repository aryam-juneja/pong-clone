import java.awt.*;

public class Texts {
    public String text;
    public Font font;
    public double x, y;
    public double width, height;
    public Color color = Color.WHITE;

    public Texts(String text, Font font, double x, double y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = font.getSize()*text.length();
        this.height = font.getSize();
    }

    public Texts(int text, Font font, double x, double y) {
        this.font = font;
        this.text = "" + text;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, (float)x, (float)y);
    }
}
