/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Shapes.Bar;
import Shapes.Circle;
import Shapes.ClownStick;
import Shapes.ImageObject;
import Shapes.Plate;
import Shapes.Rectangle;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author youssef
 */
public class CircusOfPlates implements World {

    private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    ListIterator<GameObject> iterator = moving.listIterator();
    GameObject gameObject;
    private long FlagTime;
    private int livesRemaining;
    private int livesCounter;

    public CircusOfPlates(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        livesRemaining = 3;
        livesCounter=0;
        FlagTime=startTime;
        //constant objects
            constant.add(new ImageObject(0, 0, false, "/circusBackground.png"));
            constant.add(new Bar(0, 55, 300, true, Color.PINK));
            constant.add(new Bar(0, 175, 130, true, Color.RED));
            constant.add(new Bar(500, 55, 300, true, Color.BLUE));
            constant.add(new Bar(670, 175, 130, true, Color.YELLOW));
        
        //controlable objects

            control.add(new ImageObject(300, 410, true, "/clownn.png"));
            control.add(new ClownStick(300,385,true,Color.GREEN));
            control.add(new ClownStick(433,325,true,Color.BLUE));
            control.add(new Rectangle(260,360, 80, 30, Color.BLACK));
            control.add(new Rectangle(393,300, 80, 30, Color.BLACK));
        
        //movable objects
//        for(int i=0;i<4;i++) {
//        moving.add(new Rectangle(-50, 30, 50, 25, new Color(((int) (Math.random() * 0x1000000)))));
//        moving.add(new Plate(800, 45, 70, 30, new Color(((int) (Math.random() * 0x1000000)))));
//        moving.add(new Rectangle(800, 150, 50, 25, new Color(((int) (Math.random() * 0x1000000)))));
//        moving.add(new Plate(-30, 165, 70, 30, new Color(((int) (Math.random() * 0x1000000)))));
//        }

    }

    private boolean intersect(GameObject o1, GameObject o2) {
        int delta = 10;
        return (Math.abs(o1.getX() - o2.getX()) <= delta) && (Math.abs(o1.getY() - o2.getY()) <= delta);
    }

    @Override
    public boolean refresh() {
        
 
        
        //timeRemaining -= System.currentTimeMillis();
        if (livesRemaining == 0) {
            livesCounter=0;
            return false;
        } else if (livesCounter == 70) {
            livesRemaining = 2;
        } else if (livesCounter== 120) {
            livesRemaining = 1;
        } else if (livesCounter == 170) {
            livesRemaining = 0;
        }
        long diff = System.currentTimeMillis() - FlagTime;

        if (diff > 1000 && diff < 1100) {

            moving.add(new Rectangle(-50, 30, 50, 25, new Color(((int) (Math.random() * 0x1000000)))));
            moving.add(new Plate(800, 45, 70, 30, new Color(((int) (Math.random() * 0x1000000)))));
            moving.add(new Rectangle(800, 150, 50, 25, new Color(((int) (Math.random() * 0x1000000)))));
            moving.add(new Plate(-30, 165, 70, 30, new Color(((int) (Math.random() * 0x1000000)))));
            FlagTime = System.currentTimeMillis();
        }
        for (int i = 0; i < moving.size(); i++) {

            gameObject = moving.get(i);

            if (gameObject != null) {

                if (gameObject.getY() > 600) {
                    livesCounter++;
                    moving.remove(i);
                }
                if (gameObject.getY() < 100) {
                    if (gameObject.getX() < 400) {
                        if (gameObject.getX() < 300) {
                            gameObject.setX(gameObject.getX() + 5);

                        } else {
                            gameObject.setY(gameObject.getY() + 5);

                        }
                    } else {
                        if (gameObject.getX() >= 460) {
                            gameObject.setX(gameObject.getX() - 5);
                        } else {
                            gameObject.setY(gameObject.getY() + 5);
                        }
                    }
                } else {
                    if (gameObject.getX() < 400) {
                        if (gameObject.getX() < 130) {
                            gameObject.setX(gameObject.getX() + 5);
                        } else {
                            gameObject.setY(gameObject.getY() + 5);
                        }
                    } else {
                        if (gameObject.getX() >= 630) {
                            gameObject.setX(gameObject.getX() - 5);
                        } else {
                            gameObject.setY(gameObject.getY() + 5);
                        }
                    }

                }
            }
        }
            if(control.get(3).getX()<=0 ) {
                GameObject clown=new ImageObject(40,control.get(0).getY(),true,"/clownn.png");
                GameObject leftStick = new ClownStick(40,control.get(1).getY(), true,Color.GREEN);
                GameObject rightStick = new ClownStick(173,control.get(2).getY(), true,Color.BLUE);
                GameObject leftrectangle = new Rectangle(control.get(3).getX(),control.get(3).getY(), control.get(3).getWidth(), control.get(3).getHeight(), Color.BLACK);
                GameObject rightrectangle = new Rectangle(133,control.get(4).getY(), control.get(4).getWidth(), control.get(4).getHeight(), Color.BLACK);
                control.clear();
                control.add(clown);
                control.add(leftStick);
                control.add(rightStick);
                control.add(leftrectangle);
                control.add(rightrectangle);
            }
            else if( control.get(4).getX()>=720) {
                GameObject clown=new ImageObject(627,control.get(0).getY(),true,"/clownn.png");
                GameObject leftStick = new ClownStick(627,control.get(1).getY(), true,Color.GREEN);
                GameObject rightStick = new ClownStick(760,control.get(2).getY(), true,Color.BLUE);
                GameObject leftrectangle = new Rectangle(587,control.get(3).getY(), control.get(3).getWidth(), control.get(3).getHeight(), Color.BLACK);
                GameObject rightrectangle = new Rectangle(control.get(4).getX(),control.get(4).getY(), control.get(4).getWidth(), control.get(4).getHeight(), Color.BLACK);
                control.clear();
                control.add(clown);
                control.add(leftStick);
                control.add(rightStick);
                control.add(leftrectangle);
                control.add(rightrectangle);
            }


        //		// randomly hide constant objects
//		for(GameObject n : constant)
//			if(n.isVisible() && Math.random() < 0.0002 )
//				((CrossObject)n).setVisible(false);
//		// change position of moving objects
//		boolean direction = false;
//		for(GameObject m : moving){
//			m.setX((direction ? m.getX() + (int)(3 * Math.random()) : getWidth() + m.getX() - (int)(3 * Math.random())) % getWidth());	// move object
//			direction = ! direction;
//		}
//		// check intersection with constant
//		for(GameObject c : control)
//			for(GameObject n : moving)
//				if(n.isVisible() && intersect(c, n)){
//					n.setY(n.getY());
//					score++; 	// got score
//				}
//		// check intersection with moving objects
//		for(GameObject c : control)
//			for(GameObject m : moving)
//				if(intersect(c, m))
//					return false;	// game over (lose)
//		// check if any constant object still visible
//		boolean foundVisible = false;
//		for(GameObject n : constant)
//			foundVisible |= n.isVisible();
//		if(!foundVisible)
//			return false; // game ends (win)
        return true;
    }

    @Override
    public int getSpeed() {
        return 20;
    }

    @Override
    public int getControlSpeed() {
        return 3;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Please Use Arrows To Move     |      Location = " + control.get(0).getX() + "," + control.get(0).getY() + "      |     Score = " + score + "     |      Lives Remaining =" + livesRemaining;	// update status
    }

}
