package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BeetleRenderer {
    private final Graphics graphics;
    private BufferedImage tail;
    private BufferedImage head;
    private BufferedImage body;

    public BeetleRenderer(Graphics g) {
        this.graphics = g;
        try {
            head = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/head.png"));
            body = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/body.png"));
            tail = ImageIO.read(ImageUtil.class.getResource("/BeetlePartImages/tail.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(Head h) {
        System.out.println("Draw head");
        graphics.drawImage(head, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);

    }

    public void visit(Body b) {
        System.out.println("Draw body");
        graphics.drawImage(body, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
    }

    public void visit(Tail t) {
        System.out.println("Draw tail");
        graphics.drawImage(tail, 0, 0, (int) graphics.getClipBounds().getWidth(), (int) graphics.getClipBounds().getHeight(), null);
    }
}
