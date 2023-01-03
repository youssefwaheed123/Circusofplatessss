/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObserverPattern;


import View.StartMenu;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author youssef
 */
public interface Subject {

    public abstract void setVisualState(boolean state);

    public abstract boolean getVisualState();

    public void attach(Observer observer);

    public void notifyAllObservers();

}
