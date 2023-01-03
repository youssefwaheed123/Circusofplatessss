/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import eg.edu.alexu.csd.oop.game.GameEngine;

/**
 *
 * @author ram
 */
public class ResumeState implements State {
GameEngine.GameController gameController;

    public ResumeState(GameEngine.GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void changeState() {
     gameController.resume();
    }
    
}
