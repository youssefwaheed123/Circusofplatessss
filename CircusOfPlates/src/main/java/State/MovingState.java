/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 *
 * @author youssef
 */
public class MovingState {

    protected GameObject gameObject;

    public MovingState(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void move(int y) {
        gameObject.setY(y);
    }
}
