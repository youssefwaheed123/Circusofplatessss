/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameController;

import View.*;
import Factory.Factory;
import Iterator.ShapesListIterator;
import Model.BombObject;
import Model.ClownStick;
import Model.ImageObject;
import Model.Plate;
import Model.Rectangle;
import Model.Shapes;
import Singleton.Clown;
import State.MovingState;
import Strategy.Strategy;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author youssef
 */
public class CircusOfPlates implements World {

    private int score = 0;
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> caughtLeftShapes = new LinkedList<GameObject>();
    private final List<GameObject> caughtRightShapes = new LinkedList<GameObject>();
    ShapesListIterator movingIterator;
    ShapesListIterator ControlIterator;
    ShapesListIterator constantIterator;
    GameObject gameObject;
    private int livesRemaining;
    private int livesCounter;
    private Stack<GameObject> caughtLeft = new Stack();
    private Stack<GameObject> caughtRight = new Stack();
    private int heightOfCaughtRight;
    private int heightOfCaughtLeft;
    GameObject objectToIntersectRight;
    GameObject objectToIntersectLeft;
    Factory shapesFactory;
    Strategy gameStrategy;
    private Color[] colors;
    private int noOfColors;

    public CircusOfPlates(int screenWidth, int screenHeight, Strategy gameStrategy) {
        this.gameStrategy = gameStrategy;    // setting the level  
        colors = gameStrategy.getColors();   
        noOfColors = gameStrategy.getNoOfColors();
        shapesFactory = gameStrategy.getShapesFactory(); //creating factory
        width = screenWidth;
        height = screenHeight;
        livesRemaining = 3;
        livesCounter = 0;

        //creating constant objects
        constant.add(shapesFactory.getShapes(0, 0, 0, 0, false, Color.yellow, "/circusBackground.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(30, 0, 0, 0, true, Color.yellow, "/heart.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(60, 0, 0, 0, false, Color.yellow, "/heart.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(90, 0, 0, 0, false, Color.yellow, "/heart.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(30, 2, 0, 0, true, Color.yellow, "/brokenHeart.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(60, 2, 0, 0, false, Color.yellow, "/brokenHeart.png", "ImageObject"));
        constant.add(shapesFactory.getShapes(90, 2, 0, 0, false, Color.yellow, "/brokenHeart.png", "ImageObject"));
        ((ImageObject) constant.get(4)).setVisible(false);
        ((ImageObject) constant.get(5)).setVisible(false); //broken hearts
        ((ImageObject) constant.get(6)).setVisible(false);

        //controlable objects
        Clown clown = Clown.getInstance(); //singleton pattern
        control.add((GameObject) clown.createClown());
        control.add(shapesFactory.getShapes(300, 430, 0, 0, true, Color.green, null, "ClownStick"));
        control.add(shapesFactory.getShapes(433, 370, 0, 0, true, Color.yellow, null, "ClownStick"));
        control.add(shapesFactory.getShapes(260, 420, 80, 10, true, Color.BLACK, null, "Rectangle")); 
        control.add(shapesFactory.getShapes(393, 360, 80, 10, true, Color.BLACK, null, "Rectangle")); 
        objectToIntersectRight = control.get(4);  //clown's left rectangle
        objectToIntersectLeft = control.get(3);   //clown's right rectangle
        heightOfCaughtRight = control.get(4).getY();
        heightOfCaughtLeft = control.get(3).getY();

        //creating movable objects
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            moving.add(shapesFactory.getShapes(random.nextInt(0, 150), random.nextInt(-8000, -500), 50, 25, false, colors[random.nextInt(gameStrategy.getNoOfColors())], null, "Rectangle"));
            moving.add(shapesFactory.getShapes(random.nextInt(200, 350), random.nextInt(-6000, -500), 70, 0, false, colors[random.nextInt(gameStrategy.getNoOfColors())], null, "Plate"));
            moving.add(shapesFactory.getShapes(random.nextInt(400, 550), random.nextInt(-5000, -700), 50, 25, false, colors[random.nextInt(gameStrategy.getNoOfColors())], null, "Rectangle"));
            moving.add(shapesFactory.getShapes(random.nextInt(600, 800), random.nextInt(-5500, -600), 70, 0, false, colors[random.nextInt(gameStrategy.getNoOfColors())], null, "Plate"));

        }
        //creating bombs
        for(int j=0;j<20;j++) {
            GameObject bomb=gameStrategy.CreateBomb();
            if(bomb != null) {
            moving.add(bomb);
            }
        }
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
     

     
     //removing explosion 
        if (objectToIntersectLeft == control.get(3) || objectToIntersectRight == control.get(4)) { 

            if (constant.size() > 7) {
                for (int i = 7; i < constant.size(); i++) {
                    constant.remove(i);
                }
            }

        }
        
        //ending game
        if (heightOfCaughtLeft <= 0 || heightOfCaughtRight <= 0 || !checkLives() || objectToIntersectLeft instanceof BombObject) {
            ((ImageObject) constant.get(3)).setVisible(false);
            ((ImageObject) constant.get(6)).setVisible(true);        
            ((ImageObject) constant.get(2)).setVisible(false);
            ((ImageObject) constant.get(5)).setVisible(true);     
            ((ImageObject) constant.get(1)).setVisible(false);
            ((ImageObject) constant.get(4)).setVisible(true);
            livesRemaining = 0;
            return false;
        }

        updateStacks(); //updates left & right stacks 

        if (control.get(3).getX() <= 0) {

            setLeftLimits();

        } else if (control.get(4).getX() >= 720) {

            setRightLimits();

        }
        movingIterator = new ShapesListIterator((LinkedList<GameObject>) moving);
        while(movingIterator.hasNext()) {

            Random random = new Random();
            gameObject = movingIterator.next();
            MovingState movingState = new MovingState(gameObject);
            if (gameObject.getY() > 600) {
                respawn(gameObject); //regenerating shapes
                livesCounter++;
            } else if (!((Shapes) gameObject).isCaughtByLeft() && !((Shapes) gameObject).isCaughtByRight()) {
                  
               movingState.move(gameObject.getY() + gameStrategy.getSpeed());   //moving the falling objects
            }

        }
        movingIterator = new ShapesListIterator((LinkedList<GameObject>) moving);
        //catching the falling objects
        while(movingIterator.hasNext()){
            GameObject n = movingIterator.next();
            if (intersect(objectToIntersectLeft, n) || intersect(objectToIntersectRight, n)) {
                try {

                    if (n instanceof BombObject && intersect(objectToIntersectLeft, n)) {
                        constant.add(shapesFactory.getShapes(n.getX() - 50, n.getY() - 80, 0, 0, false, Color.yellow, "/explosion0.png", "Explosion"));
                        if (!gameStrategy.bombEndsGame()) {
                            for (int j = caughtLeftShapes.size() - 1; j >= 0; j--) {
                                control.remove(caughtLeftShapes.get(j));

                            }
                            objectToIntersectLeft = control.get(3);
                            moving.remove(n);
                            caughtLeftShapes.clear();
                            heightOfCaughtLeft = control.get(3).getY();

                        } else {
                            objectToIntersectLeft = n;
                        }

                    } else if (n instanceof BombObject && intersect(objectToIntersectRight, n)) {
                        constant.add(shapesFactory.getShapes(n.getX() - 50, n.getY() - 80, 0, 0, false, Color.yellow, "/explosion0.png", "Explosion"));
                        if (!gameStrategy.bombEndsGame()) {
                            for (int j = caughtRightShapes.size() - 1; j >= 0; j--) {
                                control.remove(caughtRightShapes.get(j));

                            }
                            objectToIntersectRight = control.get(4);
                            moving.remove(n);
                            caughtRightShapes.clear();
                            heightOfCaughtRight = control.get(4).getY();
                        } else {
                            objectToIntersectLeft = n;
                        }

                    } else {
                        if (intersect(objectToIntersectLeft, n)) {
                            GameObject object = null;
                            if (n instanceof Plate) {

                                object = shapesFactory.getShapes(control.get(3).getX() + 8, heightOfCaughtLeft - n.getHeight(), n.getWidth(), 0, true, ((Shapes) n).getColor(), null, "Plate");
                            } else if (n instanceof Rectangle) {
                                object = shapesFactory.getShapes(control.get(3).getX() + 15, heightOfCaughtLeft - n.getHeight(), n.getWidth(), n.getHeight(), true, ((Shapes) n).getColor(), null, "Rectangle");
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
                            heightOfCaughtLeft = object.getY();
                            moving.remove(n);
                            ((Shapes) object).setcaughtByLeft(true);
                            objectToIntersectLeft = object;
                        } else if (intersect(objectToIntersectRight, n)) {
                            GameObject object = null;
                            if (n instanceof Plate) {

                                object = shapesFactory.getShapes(control.get(4).getX() + 8, heightOfCaughtRight - n.getHeight(), n.getWidth(), 0, true, ((Shapes) n).getColor(), null, "Plate");
                            } else if (n instanceof Rectangle) {
                                object = shapesFactory.getShapes(control.get(4).getX() + 15, heightOfCaughtRight - n.getHeight(), n.getWidth(), n.getHeight(), true, ((Shapes) n).getColor(), null, "Rectangle");
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
                            heightOfCaughtRight = object.getY();
                            moving.remove(n);
                            ((Shapes) object).setcaughtByRight(true);
                            objectToIntersectRight = object;

                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public int getSpeed() {
        return 40;
    }

    @Override
    public int getControlSpeed() {
        return 20;
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

    public void updateStacks() {
        //updating left stack
        if (caughtLeft.size() == 3) {

            score++;
            for (int i = 2; i >= 0; i--) {
                try {
                    control.remove(caughtLeft.get(i));
                    caughtLeftShapes.remove(caughtLeft.get(i));
                    if (caughtLeftShapes.isEmpty()) {

                        objectToIntersectLeft = control.get(3);

                    } else {

                        objectToIntersectLeft = caughtLeftShapes.get(caughtLeftShapes.size() - 1);

                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }
            heightOfCaughtLeft = objectToIntersectLeft.getY();

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
    }

    public boolean checkLives() {
        if (livesRemaining == 0) {
            livesCounter = 0;
            return false;
        } else if (livesCounter == 100) {
            ((ImageObject) constant.get(3)).setVisible(false);
            ((ImageObject) constant.get(6)).setVisible(true);
            livesRemaining = 2;
        } else if (livesCounter == 200) {
            ((ImageObject) constant.get(2)).setVisible(false);
            ((ImageObject) constant.get(5)).setVisible(true);
            livesRemaining = 1;
        } else if (livesCounter == 300) {
            ((ImageObject) constant.get(1)).setVisible(false);
            ((ImageObject) constant.get(4)).setVisible(true);
            livesRemaining = 0;

        }
        return true;
    }

    public void respawn(GameObject gameObject) {
        Random random = new Random();
        gameObject.setX(gameObject.getX());
        if (gameObject instanceof BombObject) {
            gameObject.setY(random.nextInt(-1000, -500));
        } else {
            gameObject.setY(random.nextInt(-200, 0));
        }
    }

    public void setRightLimits() {
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

    public void setLeftLimits() {
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
    }

}
