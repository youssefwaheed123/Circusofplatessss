/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shapes;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author youssef
 */
public class Plate implements GameObject ,Shapes{

    public static final int SPRITE_HEIGHT = 10;
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private boolean horizontalOnly;
    private Color color;
    private boolean caughtByLeft;
    private boolean caughtByRight;

    public Plate(int posX, int posY, int width, boolean horizontalOnly, Color color) {
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.height = SPRITE_HEIGHT;
        this.color = color;
        this.horizontalOnly = horizontalOnly;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        spriteImages[0] = new BufferedImage(width, SPRITE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g2 = spriteImages[0].createGraphics();

        g2.setColor(color);
//        g2.setBackground(color);
        Polygon plate = new Polygon();
        plate.addPoint(0, 0);
        plate.addPoint(0 + width, 0);
        plate.addPoint((int) (0 + (0.25 * width)), 0 + 30);
        plate.addPoint((int) (0 + (0.75 * width)), 0 + 30);

        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setStroke(new BasicStroke(20));
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillPolygon(plate);

//		g2.drawLine(0, 0, width, 0);
        g2.dispose();

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (horizontalOnly) {
            return;
        }
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCaughtByLeft() {
        return caughtByLeft;
    }

    public void setcaughtByLeft(boolean caughtByLeft) {
        this.caughtByLeft = caughtByLeft;
    }

    public boolean isCaughtByRight() {
        return caughtByRight;
    }

    public void setcaughtByRight(boolean caughtByRight) {
        this.caughtByRight = caughtByRight;
    }


}
