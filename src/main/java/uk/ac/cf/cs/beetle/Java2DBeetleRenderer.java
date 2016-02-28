package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Java2DBeetleRenderer implements BeetleRenderer {
    private final Graphics graphics;
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

    private int antennaCount = 0;
    private BufferedImage antenna1;
    private BufferedImage antenna2;


    public Java2DBeetleRenderer(Graphics g) {
        this.graphics = g;
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
            antenna1 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna1.png"));
            antenna2 = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/antenna2.png"));
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
    public void reset() {
        this.legCount = 0;
    }
}
