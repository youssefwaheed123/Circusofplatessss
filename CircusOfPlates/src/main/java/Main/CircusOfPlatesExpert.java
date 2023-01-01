/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Factory.Factory;
import Shapes.Bar;
import Shapes.Circle;
import Shapes.ClownStick;
import Shapes.ImageObject;
import Shapes.Plate;
import Shapes.Rectangle;
import Shapes.Shapes;
import Singleton.Clown;
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
public class CircusOfPlatesExpert implements World {

    private static int MAX_TIME = 1 * 90 * 1000;	// 90 seconds
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> caughtLeftShapes = new LinkedList<GameObject>();
    private final List<GameObject> caughtRightShapes = new LinkedList<GameObject>();
    ListIterator<GameObject> iterator = moving.listIterator();
    GameObject gameObject;
    private long FlagTime;
    private int livesRemaining;
    private int livesCounter;
    private Stack<GameObject> caughtLeft = new Stack();
    private Stack<GameObject> caughtRight = new Stack();
    private int leftIndex;
    private int rightIndex;
    private int heightOfCaughtRight;
    private int heightOfCaughtLeft;
    GameObject objectToIntersectRight;
    GameObject objectToIntersectLeft;
    Factory factory =new Factory();

    public CircusOfPlatesExpert(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        livesRemaining = 3;
        livesCounter = 0;
        FlagTime = startTime;
        //constant objects
        constant.add( factory.getInstance(0,0, 0, 0, false, Color.yellow, "/circusBackground.png", "ImageObject"));
        constant.add(factory.getInstance(30, 0, 0, 0, true, Color.yellow,"/heart.png", "ImageObject"));
        constant.add(factory.getInstance(60,0, 0, 0, false, Color.yellow, "/heart.png", "ImageObject"));
        constant.add(factory.getInstance(90,0, 0, 0, false, Color.yellow, "/heart.png", "ImageObject"));
        constant.add(factory.getInstance(30,2, 0, 0, true, Color.yellow, "/brokenHeart.png", "ImageObject"));
        constant.add(factory.getInstance(60,2, 0, 0, false, Color.yellow, "/brokenHeart.png", "ImageObject"));
        constant.add(factory.getInstance(90,2, 0, 0, false, Color.yellow, "/brokenHeart.png", "ImageObject"));
        ((ImageObject) constant.get(4)).setVisible(false);
        ((ImageObject) constant.get(5)).setVisible(false);
        ((ImageObject) constant.get(6)).setVisible(false);
        //controlable objects
        Clown clown = Clown.getInstance();
        control.add((GameObject) clown.createClown());
        control.add(factory.getInstance(300,430, 0, 0, true, Color.green,null, "ClownStick"));
        control.add(factory.getInstance(433,370, 0, 0, true, Color.yellow,null, "ClownStick"));
        control.add(factory.getInstance(260, 420, 80, 10, true, Color.BLACK, null, "Rectangle"));
        control.add(factory.getInstance(393, 360, 80, 10, true, Color.BLACK, null, "Rectangle"));
        objectToIntersectRight = control.get(4);
        objectToIntersectLeft = control.get(3);
        leftIndex = 0;
        rightIndex = 0;
        heightOfCaughtRight = control.get(4).getY();
        heightOfCaughtLeft = control.get(3).getY();

        // movable objects
//        for (int i = 0; i <10; i++) {
//            Random random = new Random();
//            moving.add(new Rectangle(random.nextInt(0, 150), random.nextInt(-800, -100), 50, 25, false, colors[random.nextInt(5)]));
//            moving.add(new Plate(random.nextInt(200, 350), random.nextInt(-600, -50), 70, false, colors[random.nextInt(5)]));
//            moving.add(new Rectangle(random.nextInt(400, 550), random.nextInt(-500, -70), 50, 25, false, colors[random.nextInt(5)]));
//            moving.add(new Plate(random.nextInt(600, 800), random.nextInt(-550, -60), 70, false, colors[random.nextInt(5)]));
//            //moving.add(new ImageObject(random.nextInt(0, 800), random.nextInt(-3000, -300), false, "/bomb.png"));
//        }
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        if (heightOfCaughtLeft <= 0 || heightOfCaughtRight <= 0 || objectToIntersectLeft instanceof ImageObject || objectToIntersectRight instanceof ImageObject) {
            livesRemaining = 0;
            return false;
        }

        //updating left stack
        //updating left stack
        if (caughtLeft.size() == 3) {

            score++;
            for (int i = 2; i >= 0; i--) {
                try {
                    control.remove(caughtLeft.get(i));
                    caughtLeftShapes.remove(caughtLeft.get(i));
                    if (caughtLeftShapes.isEmpty()) {
                        System.out.println("a7a");
                        objectToIntersectLeft = control.get(3);

                    } else {

                        objectToIntersectLeft = caughtLeftShapes.get(caughtLeftShapes.size() - 1);

                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }
            heightOfCaughtLeft = objectToIntersectLeft.getY();
            leftIndex = leftIndex - 3;
            caughtLeft.clear();
            try {
                if (!caughtLeftShapes.isEmpty()) {
                    if (caughtLeftShapes.size() == 1) {
                        caughtLeft.push(objectToIntersectLeft);
                    } else if (caughtLeftShapes.get(caughtLeftShapes.size() - 2) != null && ((Shapes) caughtLeftShapes.get(caughtLeftShapes.size() - 2)).getColor().equals(((Shapes) objectToIntersectLeft).getColor())) {
                        caughtLeft.push(objectToIntersectLeft);
                        caughtLeft.push(caughtLeftShapes.get(caughtLeftShapes.size() - 2));
                    } else {
                        caughtLeft.push(objectToIntersectLeft);
                    }

                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        //updating right stack
        if (caughtRight.size() == 3) {

            score++;
            for (int i = 2; i >= 0; i--) {
                control.remove(caughtRight.get(i));
                caughtRightShapes.remove(caughtRight.get(i));
                if (caughtRightShapes.isEmpty()) {
                    objectToIntersectRight = control.get(4);
                } else {
                    objectToIntersectRight = caughtRightShapes.get(caughtRightShapes.size() - 1);
                }
                heightOfCaughtRight = objectToIntersectRight.getY();
            }
            rightIndex = rightIndex - 3;
            caughtRight.clear();
            try {
                if (!caughtRightShapes.isEmpty()) {
                    if (caughtRightShapes.size() == 1) {
                        caughtRight.push(objectToIntersectRight);
                    } else if (caughtRightShapes.get(caughtRightShapes.size() - 2) != null && ((Shapes) caughtRightShapes.get(caughtRightShapes.size() - 2)).getColor().equals(((Shapes) objectToIntersectRight).getColor())) {
                        caughtRight.push(objectToIntersectRight);
                        caughtRight.push(caughtRightShapes.get(caughtRightShapes.size() - 2));

                    } else {
                        caughtRight.push(objectToIntersectRight);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        //updating height & caught objects
        if (caughtLeftShapes.size() > leftIndex || caughtRightShapes.size() > rightIndex) {
            if (caughtLeftShapes.size() > leftIndex) {
                {
                    if (caughtLeftShapes.isEmpty()) {
                        objectToIntersectLeft = control.get(3);
                    } else {
                        objectToIntersectLeft = caughtLeftShapes.get(caughtLeftShapes.size() - 1);
                    }
                    heightOfCaughtLeft = objectToIntersectLeft.getY();
                    leftIndex++;
                }
            } else if (caughtRightShapes.size() > rightIndex) {
                if (caughtRightShapes.isEmpty()) {
                    objectToIntersectRight = control.get(4);
                } else {
                    objectToIntersectRight = caughtRightShapes.get(caughtRightShapes.size() - 1);

                }
                heightOfCaughtRight = objectToIntersectRight.getY();
                rightIndex++;

            }
        }

        //setting the limits of the controlable objects 
        if (control.get(3).getX() <= 0) {

            control.get(0).setX(40);
            control.get(1).setX(40);
            control.get(2).setX(173);
            control.get(4).setX(133);
            for (int i = 5; i < control.size(); i++) {

                if (control.get(i) instanceof Rectangle) {
                    if (((Shapes) control.get(i)).isCaughtByLeft()) {
                        control.get(i).setX(control.get(3).getX() + 15);
                        control.get(i).setY(control.get(i).getY());
                    } else {
                        control.get(i).setX(control.get(4).getX() + 15);
                        control.get(i).setY(control.get(i).getY());
                    }
                } else {
                    if (((Plate) control.get(i)).isCaughtByLeft()) {
                        control.get(i).setX(control.get(3).getX() + 8);
                        control.get(i).setY(control.get(i).getY());
                    } else {
                        control.get(i).setX(control.get(4).getX() + 8);
                        control.get(i).setY(control.get(i).getY());
                    }
                }
            }

        } else if (control.get(4).getX() >= 720) {

            control.get(0).setX(627);
            control.get(1).setX(627);
            control.get(2).setX(760);
            control.get(3).setX(587);
            for (int i = 5; i < control.size(); i++) {

                if (control.get(i) instanceof Rectangle) {
                    if (((Shapes) control.get(i)).isCaughtByLeft()) {
                        control.get(i).setX(control.get(3).getX() + 15);
                        control.get(i).setY(control.get(i).getY());
                    } else {
                        control.get(i).setX(control.get(4).getX() + 15);
                        control.get(i).setY(control.get(i).getY());
                    }
                } else if (control.get(i) instanceof Plate) {
                    if (((Shapes) control.get(i)).isCaughtByLeft()) {
                        control.get(i).setX(control.get(3).getX() + 8);
                        control.get(i).setY(control.get(i).getY());
                    } else {
                        control.get(i).setX(control.get(4).getX() + 8);
                        control.get(i).setY(control.get(i).getY());
                    }
                }
            }
        }

        //updating lives remaining to end the game
        if (livesRemaining == 0) {
            livesCounter = 0;
            return false;
        } else if (livesCounter == 80) {
            ((ImageObject) constant.get(3)).setVisible(false);
            ((ImageObject) constant.get(6)).setVisible(true);
            livesRemaining = 2;
        } else if (livesCounter == 160) {
            ((ImageObject) constant.get(2)).setVisible(false);
            ((ImageObject) constant.get(5)).setVisible(true);
            livesRemaining = 1;
        } else if (livesCounter == 240) {
            ((ImageObject) constant.get(1)).setVisible(false);
            ((ImageObject) constant.get(4)).setVisible(true);
            livesRemaining = 0;

        }

        long diff = System.currentTimeMillis() - FlagTime;
        if (diff > 1500 && diff < 2000) {
            Random random = new Random();

            moving.add( factory.getInstance(random.nextInt(0, 150), random.nextInt(-100, -20), 50, 25, false, colors[random.nextInt(5)], null, "Rectangle"));
           
            moving.add( factory.getInstance(random.nextInt(200, 350), random.nextInt(-80, -20), 70,0, false, colors[random.nextInt(5)], null, "Plate"));
            
            moving.add(factory.getInstance(random.nextInt(400, 550), random.nextInt(-60, -20), 50,25, false, colors[random.nextInt(5)], null, "Rectangle"));
             
            moving.add(factory.getInstance(random.nextInt(600, 800), random.nextInt(-30, -20), 70,0, false, colors[random.nextInt(5)], null, "Plate"));
            moving.add(factory.getInstance(random.nextInt(0, 800), random.nextInt(-3000, -2000), 0, 0, false, Color.yellow, "/bomb.png", "ImageObject"));
            FlagTime = System.currentTimeMillis();

        }
        //regenerating shapes
        for (int i = 0; i < moving.size(); i++) {
            Random random = new Random();
            gameObject = moving.get(i);
            if (gameObject.getY() > 600) {
                gameObject.setX(gameObject.getX());
                if (gameObject instanceof ImageObject) {
                    gameObject.setY(random.nextInt(-1000, -500));
                } else {
                    gameObject.setY(random.nextInt(-200, 0));
                }

                livesCounter++;
            } else if (!((Shapes) gameObject).isCaughtByLeft() && !((Shapes) gameObject).isCaughtByRight()) {

                gameObject.setY(gameObject.getY() + 2);
            }

        }

        //catching the falling objects
        for (int i = 0; i < moving.size(); i++) {
            GameObject n = moving.get(i);
            if (intersect(objectToIntersectLeft, n) || intersect(objectToIntersectRight, n)) {
                if (n instanceof ImageObject) {
                    objectToIntersectLeft = n;
                    objectToIntersectRight = n;
                } else {
                    if (intersect(objectToIntersectLeft, n)) {
                        GameObject object = null;
                        if (n instanceof Plate) {

                            object = factory.getInstance(control.get(3).getX() + 8, heightOfCaughtLeft - n.getHeight(), n.getWidth(), 0, true, ((Shapes) n).getColor(), null, "Plate");
                        } else if (n instanceof Rectangle) {
                            object = factory.getInstance(control.get(3).getX() + 15, heightOfCaughtLeft - n.getHeight(), n.getWidth(), n.getHeight(), true, ((Shapes) n).getColor(), null, "Rectangle");
                        }

                        if (caughtLeft.isEmpty()) {
                            caughtLeft.push(object);
                        } else if (!caughtLeft.isEmpty()) {
                            if (((Shapes) caughtLeft.peek()).getColor().equals(((Shapes) object).getColor())) {
                                caughtLeft.push(object);
                            } else {

                                caughtLeft.clear();
                                caughtLeft.push(object);
                            }
                        }
                        control.add(object);
                        caughtLeftShapes.add(object);
                        moving.remove(n);
                        ((Shapes) object).setcaughtByLeft(true);
                        objectToIntersectLeft = object;
                    } else if (intersect(objectToIntersectRight, n)) {
                        GameObject object = null;
                        if (n instanceof Plate) {

                            object = factory.getInstance(control.get(4).getX() + 8, heightOfCaughtRight - n.getHeight(), n.getWidth(), 0, true, ((Shapes) n).getColor(), null, "Plate");
                        } else if (n instanceof Rectangle) {
                            object = factory.getInstance(control.get(4).getX() + 15, heightOfCaughtRight - n.getHeight(), n.getWidth(), n.getHeight(), true, ((Shapes) n).getColor(), null, "Rectangle");
                        }

                        if (caughtRight.isEmpty()) {
                            caughtRight.push(object);
                        } else if (!caughtRight.isEmpty()) {
                            if (((Shapes) caughtRight.peek()).getColor().equals(((Shapes) object).getColor())) {
                                caughtRight.push(object);
                            } else {

                                caughtRight.clear();
                                caughtRight.push(object);
                            }
                        }
                        control.add(object);
                        caughtRightShapes.add(object);
                        moving.remove(n);
                        ((Shapes) object).setcaughtByRight(true);
                        objectToIntersectRight = object;

                    }
                }
            }
        }

        return true;
    }

    @Override
    public int getSpeed() {
        return 20;
    }

    @Override
    public int getControlSpeed() {
        return 5;
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
