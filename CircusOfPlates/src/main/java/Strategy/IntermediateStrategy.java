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
public class IntermediateStrategy implements Strategy{
    final static int noOfColors=4;
    Factory shapesFactory =new Factory();
    Random random= new Random();
    @Override
    public GameObject CreateBomb() {
        return shapesFactory.getInstance(random.nextInt(0, 800), random.nextInt(-3000, -300), 0, 0, false, Color.yellow, "/bomb.png", "ImageObject");
    }

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public Color[] getColors() {
        Color[] colors ={Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        return colors;
    }

    @Override
    public boolean bombEndsGame() {
        return false;
    }

    @Override
    public Factory getShapesFactory() {
        return shapesFactory;
    }

    @Override
    public int getNoOfColors() {
        return noOfColors;
    }
    
}
