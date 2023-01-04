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
public class EasyStrategy implements Strategy {

    final static int noOfColors = 2;
    Factory shapesFactory = new Factory();
    Random random = new Random();

    @Override
    public GameObject CreateBomb() {
        return null;
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public Color[] getColors() {
        Color[] colors = {Color.RED, Color.BLUE};
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
