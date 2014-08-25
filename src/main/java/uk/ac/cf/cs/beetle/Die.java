package uk.ac.cf.cs.beetle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Die extends JComponent {
	private Random dieNumberGenerator;
	private int throwVal;
	private BufferedImage face1;
	private BufferedImage face2;
	private BufferedImage face3;
	private BufferedImage face4;
	private BufferedImage face5;
	private BufferedImage face6;

	public Die() {
		this.dieNumberGenerator = new Random();

		// Loading BeetlePart images below...
		try {
			face1 = ImageIO.read(new File(
			"DiceFaceImages/face1.png"));

			face2 = ImageIO.read(new File(
			"DiceFaceImages/face2.png"));

			face3 = ImageIO.read(new File(
			"DiceFaceImages/face3.png"));

			face4 = ImageIO.read(new File(
			"DiceFaceImages/face4.png"));

			face5 = ImageIO.read(new File(
			"DiceFaceImages/face5.png"));

			face6 = ImageIO.read(new File(
			"DiceFaceImages/face6.png"));

		} catch (IOException e) {
		}

	}

	public int generateRandomNumber() {
		this.throwVal = this.dieNumberGenerator.nextInt(6) + 1;
		return this.throwVal;
	}

	/* Draw die */
	@Override
	public void paintComponent(Graphics g) {
		int xLength = 100;
		int yLength = 100;
		g.setColor(Color.white);
		g.fillRect(0, 0, xLength, yLength);
		g.setColor(Color.black);
		g.drawRect(0, 0, xLength-2, yLength-2);

		// SEE leepoint.net for "RollDice" tutorial
		switch (throwVal) {
		case 1:
			g.drawImage(face1, 0, 0, xLength, yLength, null);
			break;
		case 2:
			g.drawImage(face2, 0, 0, xLength, yLength, null);
			break;
		case 3:
			g.drawImage(face3, 0, 0, xLength, yLength, null);
			break;
		case 4:
			g.drawImage(face4, 0, 0, xLength, yLength, null);
			break;

		case 5:
			g.drawImage(face5, 0, 0, xLength, yLength, null);
			break;
		case 6:
			g.drawImage(face6, 0, 0, xLength, xLength, null);
			break;
		default:
			break;
		}
		g.drawRect(0, 0, xLength-2, yLength-2);
	}
}
