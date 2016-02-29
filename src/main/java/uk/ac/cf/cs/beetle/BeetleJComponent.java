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
