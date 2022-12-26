/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Shapes;

import java.awt.Color;

/**
 *
 * @author youssef
 */
public interface Shapes {
    public Color getColor();
    public boolean isCaughtByLeft();
    public void setcaughtByLeft(boolean x);
    public boolean isCaughtByRight();
    public void setcaughtByRight(boolean x);
    public void setVisible(boolean x);
    
}
