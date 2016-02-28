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
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BeetleJComponent extends JComponent implements Beetle {
    private BeetleRenderer beetleRenderer = null;
    private Vector<BodyPart> beetleBodyParts;
	public boolean textVisible = false; // REMOVE LATER!!

	private Collection<IBodyPart> bodyParts = new ArrayList<>();

	// beetlePartImages
	private BufferedImage background = null;
	private BufferedImage eye1 = null;
	private BufferedImage eye2 = null;
	private BufferedImage antenna1 = null;
	private BufferedImage antenna2 = null;
	private BufferedImage leg1 = null;
	private BufferedImage leg2 = null;
	private BufferedImage leg3 = null;
	private BufferedImage leg4 = null;
	private BufferedImage leg5 = null;
	private BufferedImage leg6 = null;

	/**
	 * Constructs a Beetle for the player with an array of null-type BodyParts.
	 */
	public BeetleJComponent() {

		this.setPreferredSize(new Dimension(200, 200));
		beetleBodyParts = new Vector<BodyPart>(13);
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		// Loading BeetlePart images below...
		try {
            background = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/background.png"));
            eye1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/eye1.png"));
            eye2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/eye2.png"));
            antenna1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna1.png"));
            antenna2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna2.png"));
            leg1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg1.png"));
            leg2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg2.png"));
            leg3 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg3.png"));
            leg4 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg4.png"));
            leg5 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg5.png"));
            leg6 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg6.png"));

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
	public void addBodyPart(BodyPart nextBodyPart) {

		// Using if statement, leads to other more bodypart specific add methods
        try {
            String nextBodyPartType = nextBodyPart.getType();
            if (nextBodyPartType.equals("head")) {
                System.out.println("Executing addHead method...");
                this.addBodyPart(new Head());
            } else if (nextBodyPartType.equals("body")) {
                System.out.println("Executing addBody method...");
                this.addBodyPart(new Body());
            } else if (nextBodyPartType.equals("leg")) {
                System.out.println("Executing addLeg method...");
                this.addLeg(nextBodyPart);
            } else if (nextBodyPartType.equals("antenna")) {
                System.out.println("Executing addAntenna method...");
                this.addAntenna(nextBodyPart);
            } else if (nextBodyPartType.equals("eye")) {
                System.out.println("Executing addEye method...");
                this.addEye(nextBodyPart);
            } else if (nextBodyPartType.equals("tail")) {
                System.out.println("Executing addTail method...");
                this.addBodyPart(new Tail());
            }
        } catch (InvalidBodyPartSequence invalidBodyPartSequence) {
            invalidBodyPartSequence.printStackTrace();
        }
	}

	/**
	 * Checks to see if a body part of a certain part is in the beetleBodyParts
	 * array.
	 * 
	 * @param bodyPartType
	 *            e.g. Body, Head, Leg etc.
	 * @return
	 */
	public boolean hasBodyPartOfType(String bodyPartType) {
		boolean hasBodyPartOfThisType = false;
		for (BodyPart currentBodyPart : beetleBodyParts) {
			if (currentBodyPart.getType().equals(bodyPartType)) {
				hasBodyPartOfThisType = true;
				break;
			}
		}
		return hasBodyPartOfThisType;
	}

	/**
	 * Checks to see how many of the BodyParts in the beetleBodyParts array are
	 * are of the type specified by the parameter bodyPartType
	 * 
	 * @param bodyPartType
	 *            e.g. Body, Head, Leg etc.
	 * @return
	 */
	public int numberOfBodyPartType(String bodyPartType) {
		int bodyPartCount = 0;
		for (BodyPart currentBodyPart : beetleBodyParts) {
			if (currentBodyPart.getType().equals(bodyPartType)) {
				bodyPartCount++;
			}
		}
		return bodyPartCount;
	}

	/**
	 * If the Beetle has a Body and it has less than 2 eyes (i.e. 0 or 1), this
	 * method adds nextBodyPart to the nextBodyPart to the beetleBodyParts array
	 * 
	 * @param nextBodyPart
	 *            e.g. Body, Head, Leg etc.
	 */
	private void addEye(BodyPart nextBodyPart) {
		if (this.hasBodyPartOfType("head")) {
			int numberOfEyes = this.numberOfBodyPartType("eye");
			if (numberOfEyes < 2) {
				beetleBodyParts.add(nextBodyPart);
			} else {
				// "You already have two eyes!"
			}
		} else {
			// "Your Beetle needs a Head first!"
		}
	}

	/**
	 * If the Beetle has a Body and it has less than 2 antennae (i.e. 0 or 1),
	 * this method adds nextBodyPart to the nextBodyPart to the beetleBodyParts
	 * array
	 * 
	 * @param nextBodyPart
	 *            e.g. Body, Head, Leg etc.
	 */
	private void addAntenna(BodyPart nextBodyPart) {
		if (this.hasBodyPartOfType("head")) {
			int numberOfAntennae = this.numberOfBodyPartType("antenna");
			if (numberOfAntennae < 2) {
				beetleBodyParts.add(nextBodyPart);
			} else {
				// "You already have two antennae!"
			}
		} else {
			// "Your Beetle needs a Head first!"
		}
	}

	/**
	 * If the Beetle has a Body and it has less than 2 antennae (i.e. 0 or 1),
	 * this method adds nextBodyPart to the nextBodyPart to the beetleBodyParts
	 * array
	 * 
	 * @param nextBodyPart
	 *            Body, Head, Leg etc.
	 */
	private void addLeg(BodyPart nextBodyPart) {
		if (this.hasBodyPartOfType("body")) {
			int numberOfLegs = this.numberOfBodyPartType("leg");
			if (numberOfLegs < 6) {
				beetleBodyParts.add(nextBodyPart);
			} else {
				// "Your Beetle already has 6 legs!"
			}
		} else {
			// "Your Beetle needs a Body first!"
		}
	}

	/**
	 * Removes last BodyPart added from array. For use with "Undo" option in
	 * game. Added bonus to add but non-essential.
	 */
	public void removeLastAddedBodyPart() {
		int mostRecentBodyPartIndex = beetleBodyParts.size() - 1;
		beetleBodyParts.removeElementAt(mostRecentBodyPartIndex);
	}

	/**
	 * Returns a String of all the BodyParts that the Beetle has stored in the
	 * beetleBodyParts array.
	 */
	public String toString() {
		String bodyPartList = "\n";
		for (BodyPart currentBodyPart : beetleBodyParts) {
			bodyPartList += currentBodyPart.getType() + "\n";
		}
		return "This Beetle has the following body parts:" + bodyPartList;
	}

	/**
	 * Returns the number of BodyParts that the Beetle possesses.
	 * May be used for a progress bar in the GUI (JSlider).
	 * @return
	 */
	public int getNumberOfBodyParts() {
		return beetleBodyParts.size();
	}
	
	

	/**
	 * Draws the beetle
	 */
	@Override
	public void paint(Graphics g) {
        beetleRenderer = new Java2DBeetleRenderer(g);
		int numberOfLegs = 0;
		int numberOfAntennae = 0;
		int numberOfEyes = 0;

		// Draw background
		g.setColor(Color.white);
		g.fillRect(0, 0, 195, 195);
		g.setColor(Color.black);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawRect(0, 0, 198, 198);
		
		// Loop through elements of array and count number of Legs, Antennae and
		// Eyes
		for (BodyPart bodyPartType : beetleBodyParts) {
			String currentBodyPartType = bodyPartType.getType();
			if (currentBodyPartType.equals("leg")) {
				numberOfLegs++;
			} else if (currentBodyPartType.equals("antenna")) {
				numberOfAntennae++;
			} else if (currentBodyPartType.equals("eye")) {
				numberOfEyes++;
			}
		}

        for (IBodyPart bp: bodyParts) {
            bp.accept(beetleRenderer);
        }

		// draws legs
		if (numberOfLegs == 0) {
			// Draw no legs
		} else if (numberOfLegs == 1) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs == 2) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg2, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs == 3) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg2, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg3, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs == 4) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg2, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg3, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg4, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs == 5) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg2, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg3, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg4, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg5, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs == 6) {
			g.drawImage(leg1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg2, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg3, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg4, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg5, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(leg6, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfLegs > 6) {
			// Throw TooManyBodyPartsException
		} else if (numberOfLegs < 0) {
			// Throw NegativeValueException
		}

		// draw antennae
		if (numberOfAntennae == 1) {
			g.drawImage(antenna1, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfAntennae == 2) {
			g.drawImage(antenna1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(antenna2, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfAntennae > 2) {
			// Throw TooManyBodyPartsException
		} else if (numberOfAntennae < 0) {
			// Throw NegativeValueException
		}

		// Draw eyes
		if (numberOfEyes == 1) {
			g.drawImage(eye1, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfEyes == 2) {
			g.drawImage(eye1, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(eye2, 0, 0, this.getWidth(), this.getHeight(), null);
		} else if (numberOfEyes > 2) {
			// Throw TooManyBodyPartsException
		} else if (numberOfEyes < 0) {
			// Throw NegativeValueException
		}
	}
}
