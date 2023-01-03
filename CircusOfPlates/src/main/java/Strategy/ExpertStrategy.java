/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy;

import Factory.Factory;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author youssef
 */
public class ExpertStrategy implements Strategy{
    private static final int noOfColors=5;

    public  int getNoOfColors() {
        return noOfColors;
    }
    private Factory shapesFactory =new Factory();
    Random random= new Random();
    @Override
    public GameObject CreateBomb() {
        return shapesFactory.getShapes(random.nextInt(0, 800), random.nextInt(-3000, -300), 0, 0, false, Color.yellow, "/bomb.png", "BombObject");
    }

    @Override
    public int getSpeed() {
        return 6;
    }

    @Override
    public Color[] getColors() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
        return colors;
    }

    @Override
    public boolean bombEndsGame() {
        return true;
    }

    public Factory getShapesFactory() {
        return shapesFactory;
    }
    
}
