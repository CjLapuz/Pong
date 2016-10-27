package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
/**
 * @author Jeremy
 */
public class Racquet implements PowerUpable{ 
	private static final int WITH = 75; 
	private static final int HEIGHT = 15;
        int Y;
        int speed;
        int player;
        int point = 0;
        int state = 0;
	int x = 150;  
	int xa = 0; 
	private Game game; 

	public Racquet(Game game, int player) {
            this.player = player;
                if (player == 1){
                    Y = 460;
                } else {
                    Y = 15;
                }
                this.speed = game.speed;
		this.game = game;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH) // can only move racket is within the width of the game board
			x = x + xa;
	}

	public void paint(Graphics2D g) {
            if (player == 1){
                g.setColor(Color.BLUE);
            } else if (player == 2){
                g.setColor(Color.RED);
            } else if (state == 1) {
                g.setColor(Color.yellow); 
            }
            g.fillRect(x, Y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {  // when the key is released the racquet stops moving 
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
            if (player == 1){
		if (e.getKeyCode() == KeyEvent.VK_LEFT) // move towards the negative x-axis when left key is pressed
			xa = -speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) // move towards the positive x-axis when right key is pressed
			xa = speed;
            } else {
                if (e.getKeyCode() == KeyEvent.VK_A) // move towards the negative x-axis when left key is pressed
			xa = -speed;
		if (e.getKeyCode() == KeyEvent.VK_D) // move towards the positive x-axis when right key is pressed
			xa = speed;
            }
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WITH, HEIGHT);  // returns the position of the racquet and its bounds 
	}

	public int getTopY() {
		return Y - HEIGHT; 
	}
        public int getPoint(){return point;}
        public void setPoint(int point){this.point = point;}
        public void score(){point++;}

    @Override
    public void powerUp() {
        if (player == 1) {
            JOptionPane.showMessageDialog(null, "BLUE has gained Ball Speed Boost!!\n"
                                                 +"Effective for the next two hits" );
        } else if (player == 2){
            JOptionPane.showMessageDialog(null, "RED has gained Ball Speed Boost!!\n"
                                                 +"Effective for the next two hits" );
        }
        state = 2;
    }
}
