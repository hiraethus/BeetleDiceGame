package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;
import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.*;

public class RegularBeetle implements Beetle {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private Collection<IBodyPart> bodyParts = new ArrayList<>();
    public static final String ADDED_BODY_PART = "body part added";

	// beetlePartImages
	private BufferedImage background = null;

	/**
	 * Constructs a Beetle for the player with an array of null-type BodyParts.
	 */
	public RegularBeetle() {
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		// Loading BeetlePart images below...


	}

	@Override
	public Collection<IBodyPart> getBodyParts() {
		return bodyParts;
	}

	@Override
	public void addBodyPart(IBodyPart bodyPart) throws InvalidBodyPartSequence {
        if (bodyPart.canAppendToBeetle(this)) {
            this.bodyParts.add(bodyPart);
            this.pcs.firePropertyChange(ADDED_BODY_PART, null, bodyPart);
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



    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
