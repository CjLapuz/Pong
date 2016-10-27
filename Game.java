package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author Jeremy
 */
@SuppressWarnings("serial")
    public class Game extends JPanel {
        Ball ball = new Ball(this); 
        Racquet racquet1 = new Racquet(this, 1);
        Racquet racquet2 = new Racquet(this, 2);
        int speed = 1; 
        
    public Game() {
        addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37 || e.getKeyCode() == 39){
            racquet1.keyReleased(e);
        } else if (e.getKeyCode() == 65 || e.getKeyCode() == 68){
            racquet2.keyReleased(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37 || e.getKeyCode() == 39 || (racquet1.state == 1 && e.getKeyCode() == 13)){
            racquet1.keyPressed(e);
        } else if (e.getKeyCode() == 65 || e.getKeyCode() == 68 || (racquet2.state == 1 && e.getKeyCode() == 32)){
            racquet2.keyPressed(e);
        }
    }
    });
    setFocusable(true);
    Sound.BACK.loop();
    
    
}

private void move() throws InterruptedException {
    ball.move();
    racquet1.move();
    racquet2.move();
    }

@Override
public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                      RenderingHints.VALUE_ANTIALIAS_ON);
    this.setBackground(Color.BLACK);
    ball.paint(g2d);
    racquet1.paint(g2d);
    racquet2.paint(g2d);
    
    g2d.setColor(Color.BLUE);
    g2d.setFont(new Font("Verdana", Font.BOLD, 30));
    g2d.drawString(String.valueOf(racquet1.getPoint()), 10, 275);
    g2d.setColor(Color.yellow);
    g2d.drawLine(0, 245, 400, 245);
    g2d.setColor(Color.RED);
    g2d.setFont(new Font("Verdana", Font.BOLD, 30));
    g2d.drawString(String.valueOf(racquet2.getPoint()), 350, 235);
}

public void gameOver() throws InterruptedException{
    int choice = JOptionPane.showConfirmDialog(null, "Try Again?");
    if (choice == 0) {
        racquet1.setPoint(0);
        racquet2.setPoint(0);
        reset();
    } else {
        System.exit(ABORT);
    }
}
public void reset()throws InterruptedException{
    Sound.BACK.loop();
    Sound.VICTORY1.stop();
    Sound.VICTORY2.stop();
    speed = 1;
    ball.x = 180;
    ball.y = 235;
    ball.xa = speed;
    ball.ya = speed;
    racquet1.x = 150;
    racquet2.x = 150;
    racquet1.xa = 0;
    racquet2.xa = 0;
    racquet1.state = 0;
    racquet2.state = 0;
}
public static void main(String[] args) throws InterruptedException{
    Game game = new Game();
    JFrame frame = new JFrame("Mini Tennis");
    frame.add(game);
    frame.setSize(400, 530);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while (true) {
        game.move();
        game.repaint();
        Thread.sleep(10);
    }

}
}