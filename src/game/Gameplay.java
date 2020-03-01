package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	
	private static final long serialVersionUID = -4302210343950053122L;
	private int[] snakeXLength = new int[750];
	private int[] snakeYLength = new int[750];
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean gameOver = false;
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private int lengthOfSnake = 3;
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeBody;
	private ImageIcon titleImage;
	private int moves = 0;
	private int score = 0;
	private int[] enemyXpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] enemyYpos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	private ImageIcon enemyImage;
	private Random random = new Random();

	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);

	public Gameplay() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {

		if (moves == 0) {

			snakeXLength[2] = 50;
			snakeXLength[1] = 75;
			snakeXLength[0] = 100;

			snakeYLength[2] = 100;
			snakeYLength[1] = 100;
			snakeYLength[0] = 100;
		}

		// draw title image border
		g.setColor(Color.WHITE);
		g.drawRect(23, 10, 850, 55);

		// draw title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		// draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(25, 75, 851, 700);

		// draw background for gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);

		// draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 16));
		g.drawString("Score: " + score, 780, 30);

		//
		// g.setColor(Color.WHITE);
		// g.setFont(new Font("arial", Font.PLAIN, 12));
		// g.drawString("Length: " + lengthOfSnake, 780, 50);

		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

		for (int i = 0; i < lengthOfSnake; i++) {

			if (i == 0 && right) {

				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && left) {

				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && up) {

				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && down) {

				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			// adds body to snake head as length increases
			if (i != 0) {

				snakeBody = new ImageIcon("snakebody.png");
				snakeBody.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

		}

		enemyImage = new ImageIcon("enemy.png");

		if (enemyXpos[xPos] == snakeXLength[0] && enemyYpos[yPos] == snakeYLength[0]) {

			score += 10;
			lengthOfSnake++;
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}

		enemyImage.paintIcon(this, g, enemyXpos[xPos], enemyYpos[yPos]);

		// checking collision with body Game Over

		for (int i = 1; i < lengthOfSnake; i++) {

			if (snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0]) {
				right = false;
				left = false;
				down = false;
				up = false;
				gameOver = true;
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to start a new game", 280, 330);

			}

		}

		g.dispose();
	}

	public void actionPerformed(ActionEvent a) {

		timer.start();
		if (right) {

			for (int i = lengthOfSnake - 1; i >= 0; i--) {

				snakeYLength[i + 1] = snakeYLength[i];
			}

			// lengthOfSnake=2
			for (int i = lengthOfSnake; i >= 0; i--) {

				if (i == 0)
					snakeXLength[i] = snakeXLength[i] + 25;
				else
					snakeXLength[i] = snakeXLength[i - 1];

				// when crossing the boundary
				if (snakeXLength[i] > 850)
					snakeXLength[i] = 25;

				// snakeYLength[i + 1] = snakeYLength[i];

			}

			repaint();
		}

		if (left) {

			for (int i = lengthOfSnake - 1; i >= 0; i--) {

				snakeYLength[i + 1] = snakeYLength[i];

			}

			for (int i = lengthOfSnake; i >= 0; i--) {

				if (i == 0)
					snakeXLength[i] = snakeXLength[i] - 25;
				else
					snakeXLength[i] = snakeXLength[i - 1];

				// when crossing the boundary
				if (snakeXLength[i] < 25)
					snakeXLength[i] = 850;

				// snakeYLength[i + 1] = snakeYLength[i];

				repaint();
			}
		}

		if (down) {

			for (int i = lengthOfSnake - 1; i >= 0; i--) {

				snakeXLength[i + 1] = snakeXLength[i];

			}

			for (int i = lengthOfSnake; i >= 0; i--) {

				if (i == 0)
					snakeYLength[i] = snakeYLength[i] + 25;
				else
					snakeYLength[i] = snakeYLength[i - 1];

				// when crossing the boundary
				if (snakeYLength[i] > 625)
					snakeYLength[i] = 75;

				// snakeXLength[i + 1] = snakeXLength[i];

				repaint();
			}
		}

		if (up) {

			for (int i = lengthOfSnake - 1; i >= 0; i--) {

				snakeXLength[i + 1] = snakeXLength[i];

			}

			for (int i = lengthOfSnake; i >= 0; i--) {

				if (i == 0)
					snakeYLength[i] = snakeYLength[i] - 25;
				else
					snakeYLength[i] = snakeYLength[i - 1];

				// when crossing the boundary
				if (snakeYLength[i] < 75)
					snakeYLength[i] = 625;

				// snakeXLength[i + 1] = snakeXLength[i];

				repaint();
			}
		}
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			gameOver = false;
			moves = 0;
			lengthOfSnake = 3;
			score = 0;
			repaint();
		}

		if (!gameOver && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			if (!left) {
				right = true;
				left = false;
			}
			down = false;
			up = false;

		}

		if (!gameOver && e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			if (!right) {
				left = true;
				right = false;
			}
			down = false;
			up = false;

		}

		if (!gameOver && e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			if (!down) {
				up = true;
				down = false;
			}
			left = false;
			right = false;

		}

		if (!gameOver && e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			if (!up) {
				down = true;
				up = false;
			}
			left = false;
			right = false;

		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}
