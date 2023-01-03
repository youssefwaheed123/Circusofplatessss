/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author youssef
 */
public class ShapesListIterator implements Iterator<GameObject> {

    int index = 0;
    List<GameObject> x;

    public ShapesListIterator(List<GameObject> x) {
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
        
        return x.get(index);
        
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
}
