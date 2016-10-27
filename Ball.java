package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
/**
 * @author Jeremy
 */
public class Ball {
	private static final int DIAMETER = 30;
	
	int x = 180;
	int y = 235;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() throws InterruptedException{
		if (x + xa < 0) // if ball hits the left side of the frame
			xa = game.speed; 
		else if (x + xa > game.getWidth() - DIAMETER) // if ball hits the right side of the frame 
			xa = -game.speed;
		else if (y + ya < 0){ // if ball hits the top of the frame
			game.racquet1.score();
                        Sound.BACK.stop();
                        Sound.SCORE.play();
                        JOptionPane.showMessageDialog(game, "BLUE SCORES!!");
                        game.reset();
                        if (game.racquet1.getPoint() < 2 ){
                            ya = -game.speed;
                        } else if (game.racquet1.getPoint() == 2) {
                            game.racquet2.powerUp();
                            ya = -game.speed;
                        } else {
                            Sound.BACK.stop();
                            Sound.VICTORY2.play();
                            JOptionPane.showMessageDialog(game, "BLUE WINS!!");
                            game.gameOver();
                        }
                } else if (y + ya > game.getHeight() - DIAMETER){ 
			game.racquet2.score();
                        Sound.BACK.stop();
                        Sound.SCORE.play();
                        JOptionPane.showMessageDialog(game, "RED SCORES!!");
                        game.reset();
                        if (game.racquet2.getPoint() < 2){
                            ya = game.speed;
                        } else if (game.racquet2.getPoint() == 2) {
                            game.racquet1.powerUp(); 
                        }else {
                            Sound.BACK.stop();
                            Sound.VICTORY1.play();
                            JOptionPane.showMessageDialog(game, "RED WINS!!");
                            game.gameOver();
                        }
                } else if (collision1()){
                        Sound.BALL.play();
                        if (game.racquet1.state > 0){
                            game.speed = 10;
                        } else {
                            game.speed = 5;
                        }
                        game.racquet1.state--;
			ya = -game.speed;
                        
                } else if (collision2()){ // if ball collides with the racquet
                        Sound.BALL.play();
                        if (game.racquet2.state > 0){
                            game.speed = 10;
                        } else {
                            game.speed = 5;
                        }
                        game.racquet2.state--;
                        ya = game.speed; 
                }
                game.racquet1.speed = 4;
                game.racquet2.speed = 4;
		x = x + xa; 
		y = y + ya; 
	}

	private boolean collision1() {
		return game.racquet1.getBounds().intersects(getBounds()); // if ball collides with racquet1
	}
        private boolean collision2() {
		return game.racquet2.getBounds().intersects(getBounds()); // if ball collides with racquet2
	}
	public void paint(Graphics2D g) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER); // returns the position of the ball and its bounds
        }
}
