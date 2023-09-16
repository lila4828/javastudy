import javax.swing.*;
import java.awt.*;

class Ball {
    private int x = 0;
    private int y = 0;
    private int size = 50;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private int red=0, green=0, blue=0;

    public Ball(int x, int y, int size, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillOval(x, y, size, size);
    }
    public void update() {
        x += xSpeed;
        y += ySpeed;
        if ((x + size) > MyPanel.BOARD_WIDTH - size || x < 0) {
            xSpeed = -xSpeed;
        }
        if ((y + size) > MyPanel.BOARD_HEIGHT - size || y < 0) {
            ySpeed = -ySpeed;
        }
    }
}

public class MyPanel extends JPanel {
    static final int BOARD_WIDTH = 600;
    static final int BOARD_HEIGHT = 300;
    private Ball ball1 = new Ball(200, 0, 50, 255, 0,255);
    private Ball ball2 = new Ball(100, 100, 50, 255, 255,0);
    private Ball ball3 = new Ball(0, 200, 50, 0, 255,255);
    public MyPanel() {
        this.setBackground(Color.BLACK);
        Runnable task = () -> {
            while (true) {
                ball1.update();
                ball2.update();
                ball3.update();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignore) {
                }
            }
        };
        new Thread(task).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball1.draw(g);
        ball2.draw(g);
        ball3.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(MyPanel.BOARD_WIDTH, MyPanel.BOARD_HEIGHT);
        frame.add(new MyPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
