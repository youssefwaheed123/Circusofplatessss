/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameController;
import Main.StartMenu;
import ObserverPattern.Observer;
import ObserverPattern.Subject;
import State.ResumeState;
import State.State;
import State.PauseState;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author youssef
 */

    public final class GameController implements Subject{
    
    private final Supplier<World> gameSupplier;
    private JFrame gameFrame;
    private GameEngine.GameController gameController;
    PauseState pauseState;
    ResumeState resumeState;
    private ArrayList<Observer> observers = new ArrayList<>();
    private boolean VisualState;
    
    public GameController(Supplier<World> gameSupplier) {
        this.gameSupplier = gameSupplier;

    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  gameFrame.dispose();
                  setVisualState(true);
                  start();
            }
        });

        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseState.changeState();
                
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeState.changeState();
            }
        });
        
        return menuBar;
    }
    public void start() {
        
        JMenuBar menuBar = createMenuBar();
        World game = gameSupplier.get();
        this.gameController = GameEngine.start("Circus of plates", game, menuBar, Color.BLACK);
        pauseState=new PauseState(this.gameController);
        resumeState= new ResumeState(this.gameController);
        this.gameFrame = (JFrame) menuBar.getParent().getParent().getParent();        
        this.gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            
            
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int message= JOptionPane.showConfirmDialog(gameFrame, "Are you sure you want to close this game?",
                        "End Game?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (message == JOptionPane.YES_OPTION) {
                    setVisualState(false);
                    gameFrame.dispose();
                    gameFrame.setDefaultCloseOperation(WindowConstants. DO_NOTHING_ON_CLOSE);
                   
                }
                else {
                    gameFrame.setDefaultCloseOperation(WindowConstants. DO_NOTHING_ON_CLOSE);
                    
                }
               
            }
        });
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    public GameEngine.GameController getGameController() {
        return gameController;
    }

 @Override
    public boolean getVisualState() {
        return VisualState;
    }

    @Override
    public void setVisualState(boolean state) {
        this.VisualState = state;
        gameFrame.setVisible(state);
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

 

}
