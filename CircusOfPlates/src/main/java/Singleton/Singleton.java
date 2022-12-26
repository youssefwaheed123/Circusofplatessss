/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;

import Shapes.ClownStick;
import Shapes.ImageObject;
import Shapes.Rectangle;
import Shapes.Shapes;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author youssef
 */
public class Singleton {

    private static Shapes instance = null;
    public Singleton(Shapes shape) {

        instance=shape;
    }

    public static Shapes getInstance() {
      
         return instance;
    }

}
