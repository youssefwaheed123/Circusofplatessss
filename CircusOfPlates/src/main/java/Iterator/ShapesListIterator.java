/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author youssef
 */
public class ShapesListIterator implements Iterator{

    int index = 0;
    LinkedList<GameObject> x;

    public ShapesListIterator(LinkedList<GameObject> x) {
        this.x = x;
    }

    @Override
    public boolean hasNext() {
        if (index < x.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GameObject next() {
        
        GameObject gameObject =x.get(index);
        index++;
        return gameObject;
        
    }


}
