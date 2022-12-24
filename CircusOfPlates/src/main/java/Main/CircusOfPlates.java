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
import java.util.Random;
import java.util.Stack;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author youssef
 */
public class CircusOfPlates implements World {

    private static int MAX_TIME = 1 * 90 * 1000;	// 90 seconds
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
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
    private Stack<GameObject> caughtLeft = new Stack();
    private Stack<GameObject> caughtRight = new Stack();

    public CircusOfPlates(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        livesRemaining = 3;
        livesCounter = 0;
        FlagTime = startTime;
        //constant objects
        constant.add(new ImageObject(0, 0, false, "/circusBackground.png"));
        constant.add(new ImageObject(30,0, true, "/heart.png"));
        constant.add(new ImageObject(60, 0, false, "/heart.png"));
        constant.add(new ImageObject(90, 0, false, "/heart.png"));


        //controlable objects
        control.add(new ImageObject(300, 410, true, "/clownn.png"));
        control.add(new ClownStick(300, 385, true, Color.GREEN));
        control.add(new ClownStick(433, 325, true, Color.RED));
        control.add(new Rectangle(260, 360, 80, 30, Color.BLACK));
        control.add(new Rectangle(393, 300, 80, 30, Color.BLACK));

    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;

        if (control.get(3).getX() <= 0) {
            
              control.get(0).setX(40);
              control.get(1).setX(40);
              control.get(2).setX(173);
              control.get(4).setX(133);
              
        } else if (control.get(4).getX() >= 720) {
            
            control.get(0).setX(627);
            control.get(1).setX(627);
            control.get(2).setX(760);
            control.get(3).setX(587);
            
        }

        if (livesRemaining == 0) {
            livesCounter = 0;
            return false;
        } else if (livesCounter == 30) {
            ((ImageObject)constant.get(3)).setVisible(false);
            livesRemaining = 2;
        } else if (livesCounter == 60) {
            ((ImageObject)constant.get(2)).setVisible(false);
            livesRemaining = 1;
        } else if (livesCounter == 90) {
            ((ImageObject)constant.get(1)).setVisible(false);
            livesRemaining = 0;
        }
        long diff = System.currentTimeMillis() - FlagTime;

        if (diff > 1500 && diff < 2000) {
            Random random = new Random();

            moving.add(new Rectangle(random.nextInt(0, 150), random.nextInt(-100, -20), 50, 25, colors[random.nextInt(5)]));
            moving.add(new Plate(random.nextInt(200, 350), random.nextInt(-80, -20), 70, 30, colors[random.nextInt(5)]));
            moving.add(new Rectangle(random.nextInt(400, 550), random.nextInt(-60, -20), 50, 25, colors[random.nextInt(5)]));
            moving.add(new Plate(random.nextInt(600, 800), random.nextInt(-30, -20), 70, 30, colors[random.nextInt(5)]));
            FlagTime = System.currentTimeMillis();

        }
        for (int i = 0; i < moving.size(); i++) {

            gameObject = moving.get(i);
            if (gameObject.getY() > 600) {
                moving.remove(gameObject);
                livesCounter++;
            }
            if (gameObject != null) {

                gameObject.setY(gameObject.getY() + 2);
            }
            
        }

        for (GameObject n : moving) {
            if (intersect(control.get(3), n) || intersect(control.get(4), n)) {

                if (intersect(control.get(3), n) && n.getY() < control.get(3).getY()) {
                    if (n instanceof Plate) {
                        n.setX(control.get(3).getX() + 8);
                    } else {
                        n.setX(control.get(3).getX() + 15);
                    }
                    n.setY(control.get(3).getY() - n.getHeight());

                }
                if (intersect(control.get(4), n)) {
                    if (n instanceof Plate) {
                        n.setX(control.get(4).getX() + 8);
                    } else {
                        n.setX(control.get(4).getX() + 15);
                    }
                    n.setY(control.get(4).getY() - n.getHeight());

                }

                 	// got score
            }
        }

        return !timeout;
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
        return "Please Use Arrows To Move     |      Location = " + control.get(0).getX() + "," + control.get(0).getY() + "      |     Score = " + score + "     |      Lives Remaining =" + livesRemaining+"     |      Time Remaining=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000)+" seconds";	// update status
    }

}
