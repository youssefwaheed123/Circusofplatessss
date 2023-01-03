/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author youssef
 */
public class BombObject implements GameObject,Shapes{
    private static final int MAX_MSTATE = 2;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int type;
    boolean horizontalOnly;
    boolean caughtByLeft;
    boolean caughtByRight;

    public BombObject(int posX, int posY, boolean horizontal, String path) {
        this(posX, posY, horizontal, path, 0);
    }

    public BombObject(int posX, int posY, boolean horizontal, String path, int type) {
        this.x = posX;
        this.y = posY;
        this.type = type;
        this.visible = true;
        horizontalOnly = horizontal;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
           
                spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
                spriteImages[1] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
 

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isCaughtByLeft() {
        return caughtByLeft;
    }

    @Override
    public void setcaughtByLeft(boolean x) {
        this.caughtByLeft = x;
    }

    @Override
    public boolean isCaughtByRight() {
        return caughtByRight;
    }

    @Override
    public void setcaughtByRight(boolean x) {
        this.caughtByRight = x;
    }



 
}
