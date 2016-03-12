package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Java2DBeetleRenderer extends JComponent implements BeetleRenderer, PropertyChangeListener {
    private Graphics graphics;
    private final Beetle beetle;
    private BufferedImage tail;
    private BufferedImage head;
    private BufferedImage body;

    private int legCount = 0;
    private BufferedImage leg1;
    private BufferedImage leg2;
    private BufferedImage leg3;
    private BufferedImage leg4;
    private BufferedImage leg5;
    private BufferedImage leg6;
    private BufferedImage background;

    private int antennaCount = 0;
    private BufferedImage antenna1;
    private BufferedImage antenna2;

    private int eyeCount = 0;
    private BufferedImage eye1;
    private BufferedImage eye2;


    public Java2DBeetleRenderer(Beetle b) {
        this.beetle = b;
        this.setPreferredSize(new Dimension(200, 200));
        b.addPropertyChangeListener(this);
        this.graphics = this.getGraphics();

        try {
            head = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/head.png"));
            body = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/body.png"));
            tail = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/tail.png"));
            leg1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg1.png"));
            leg2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg2.png"));
            leg3 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg3.png"));
            leg4 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg4.png"));
            leg5 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg5.png"));
            leg6 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/leg6.png"));
            eye1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/eye1.png"));
            eye2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/eye2.png"));
            antenna1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna1.png"));
            antenna2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna2.png"));
            background = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Head h) {
        System.out.println("Draw head");
        graphics.drawImage(head, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

    }

    @Override
    public void visit(Body b) {
        System.out.println("Draw body");
        graphics.drawImage(body, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
    }

    @Override
    public void visit(Tail t) {
        System.out.println("Draw tail");
        graphics.drawImage(tail, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
    }

    @Override
    public void visit(Leg leg) {
        legCount = legCount + 1;

        if (legCount == 1) {
            graphics.drawImage(leg1, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        } else if (legCount == 2) {
            graphics.drawImage(leg2, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

        } else if (legCount == 3) {
            graphics.drawImage(leg3, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

        } else if (legCount == 4) {
            graphics.drawImage(leg4, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

        } else if (legCount == 5) {
            graphics.drawImage(leg5, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

        } else if (legCount == 6) {
            graphics.drawImage(leg6, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        }
    }

    @Override
    public void visit(Antenna antenna) {
        antennaCount = antennaCount + 1;

        if (antennaCount == 1) {
            graphics.drawImage(antenna1, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        } else if (antennaCount == 2) {
            graphics.drawImage(antenna2, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        }

    }

    @Override
    public void visit(Eye eye) {
        eyeCount = eyeCount + 1;

        if (eyeCount == 1) {
            graphics.drawImage(eye1, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        } else if (eyeCount == 2) {
            graphics.drawImage(eye2, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
        }

    }

    @Override
    public void reset() {
        this.legCount = 0;
        this.antennaCount = 0;
        this.eyeCount = 0;
    }


    /**
     * Draws the beetle
     */
    @Override
    public void paint(Graphics g) {
        reset();
        graphics = g;
        drawBackground(g);

        for (IBodyPart bp: beetle.getBodyParts()) {
            bp.accept(this);
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 195, 195);
        g.setColor(Color.black);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
}
