/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shapes;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author youssef
 */
public class ImageObject implements GameObject,Shapes{
    
    private static final int MAX_MSTATE = 11;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean visible;
	private int type;
        boolean horizontalOnly;
        boolean caughtByLeft;
        boolean caughtByRight;
	
	public ImageObject(int posX, int posY,boolean horizontal, String path){
		this(posX, posY, horizontal,path, 0);
	}
	
	public ImageObject(int posX, int posY,boolean horizontal, String path, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;
                horizontalOnly=horizontal;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
                    if(path.equalsIgnoreCase("/explosion0.gif")){
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[1] = ImageIO.read(getClass().getResourceAsStream("/explosion1.GIF"));
                        spriteImages[2] = ImageIO.read(getClass().getResourceAsStream("/explosion2.gif"));
                        spriteImages[3] = ImageIO.read(getClass().getResourceAsStream("/explosion3.gif"));
                        spriteImages[4] = ImageIO.read(getClass().getResourceAsStream("/explosion4.gif"));
                        spriteImages[5] = ImageIO.read(getClass().getResourceAsStream("/explosion5.gif"));
                        spriteImages[6] = ImageIO.read(getClass().getResourceAsStream("/explosion14.gif"));
                        spriteImages[7] = ImageIO.read(getClass().getResourceAsStream("/explosion15.gif"));
                        spriteImages[8] = ImageIO.read(getClass().getResourceAsStream("/explosion16.gif"));
                        spriteImages[9] = ImageIO.read(getClass().getResourceAsStream("/explosion17.gif"));
                        spriteImages[10] = ImageIO.read(getClass().getResourceAsStream("/explosion18.gif"));
                      
                    }else if(path.equalsIgnoreCase("/bomb.png"))
                    {
                        spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[1] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
                        spriteImages[2] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[3] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
                        spriteImages[4] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[5] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
                        spriteImages[6] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[7] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
                        spriteImages[8] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[9] = ImageIO.read(getClass().getResourceAsStream("/bomb1.png"));
                        spriteImages[10] = ImageIO.read(getClass().getResourceAsStream(path));

                    }else {
                        spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[1] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[2] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[3] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[4] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[5] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[6] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[7] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[8] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[9] = ImageIO.read(getClass().getResourceAsStream(path));
                        spriteImages[10] = ImageIO.read(getClass().getResourceAsStream(path));

                        
                    }
                        
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
            if(horizontalOnly) {
                return;
            }
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
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
	
	public void setVisible(boolean visible){
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
        this.caughtByLeft=x;
    }

    @Override
    public boolean isCaughtByRight() {
        return caughtByRight;
    }

    @Override
    public void setcaughtByRight(boolean x) {
        this.caughtByRight=x;
    }



	
	
}

