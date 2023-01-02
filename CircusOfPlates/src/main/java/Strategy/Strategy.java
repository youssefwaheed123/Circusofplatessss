/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Strategy;

import Factory.Factory;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;

/**
 *
 * @author youssef
 */
public interface Strategy {
    public GameObject CreateBomb();
    public int getSpeed();
    public Color[] getColors();
    public boolean bombEndsGame();
    public Factory getShapesFactory();
    public int getNoOfColors();
}
