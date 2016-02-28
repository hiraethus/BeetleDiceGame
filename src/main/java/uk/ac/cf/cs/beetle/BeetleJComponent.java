package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;
import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BeetleJComponent extends JComponent implements Beetle {
	private Collection<IBodyPart> bodyParts = new ArrayList<>();

	// beetlePartImages
	private BufferedImage background = null;

	/**
	 * Constructs a Beetle for the player with an array of null-type BodyParts.
	 */
	public BeetleJComponent() {
		this.setPreferredSize(new Dimension(200, 200));
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		// Loading BeetlePart images below...
		try {
            background = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/background.png"));
		} catch (IOException e) {
		}

	}

	@Override
	public Collection<IBodyPart> getBodyParts() {
		return bodyParts;
	}

	@Override
	public void addBodyPart(IBodyPart bodyPart) throws InvalidBodyPartSequence {
        if (bodyPart.canAppendToBeetle(this)) {
            this.bodyParts.add(bodyPart);
        }
	}

	/**
	 * Checks the BodyPart type and executes the correct add method for that
	 * particular BodyPart.
	 * 
	 * @param nextBodyPart
	 */
	public void addBodyPart(BodyPart nextBodyPart) throws InvalidBodyPartSequence {
		// Using if statement, leads to other more bodypart specific add methods
        String nextBodyPartType = nextBodyPart.getType();
        if (nextBodyPartType.equals("head")) {
            System.out.println("Executing addHead method...");
            this.addBodyPart(new Head());
        } else if (nextBodyPartType.equals("body")) {
            System.out.println("Executing addBody method...");
            this.addBodyPart(new Body());
        } else if (nextBodyPartType.equals("leg")) {
            System.out.println("Executing addLeg method...");
            this.addBodyPart(new Leg());
        } else if (nextBodyPartType.equals("antenna")) {
            System.out.println("Executing addAntenna method...");
            this.addBodyPart(new Antenna());
        } else if (nextBodyPartType.equals("eye")) {
            System.out.println("Executing addEye method...");
            this.addBodyPart(new Eye());
        } else if (nextBodyPartType.equals("tail")) {
            System.out.println("Executing addTail method...");
            this.addBodyPart(new Tail());
        }
	}

	/**
	 * Returns the number of BodyParts that the Beetle possesses.
	 * May be used for a progress bar in the GUI (JSlider).
	 * @return
	 */
	public int getNumberOfBodyParts() {
		return bodyParts.size();
	}
	
	/**
	 * Draws the beetle
	 */
	@Override
	public void paint(Graphics g) {
        BeetleRenderer beetleRenderer = new Java2DBeetleRenderer(g);
        drawBackground(g);

        for (IBodyPart bp: bodyParts) {
            bp.accept(beetleRenderer);
        }
	}

    private void drawBackground(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 195, 195);
        g.setColor(Color.black);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
