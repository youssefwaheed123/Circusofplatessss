/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import GameController.GameController;
import GameMusic.GameMusic;
import ObserverPattern.Observer;
import ObserverPattern.Subject;
import State.ResumeState;
import State.PauseState;
import Strategy.EasyStrategy;
import Strategy.ExpertStrategy;
import Strategy.IntermediateStrategy;
import Strategy.Strategy;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author youssef
 */
public class DifficultyMenu extends javax.swing.JFrame implements Subject,Observer{

    private ArrayList<Observer> observers = new ArrayList<>();
    private boolean VisualState;
    private ResumeState startState;
    private PauseState stopState;
    private GameMusic gameMusic;
    GameController gameController;

    /**
     * Creates new form DifficultyMenu
     */
    public DifficultyMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Select a difficulty");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        begginer = new javax.swing.JButton();
        intermediate = new javax.swing.JButton();
        expert = new javax.swing.JButton();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        begginer.setText("Beginner");
        begginer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                begginerActionPerformed(evt);
            }
        });

        intermediate.setText("Intermediate");
        intermediate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intermediateActionPerformed(evt);
            }
        });

        expert.setText("Expert");
        expert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(intermediate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(begginer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(begginer)
                .addGap(38, 38, 38)
                .addComponent(intermediate)
                .addGap(43, 43, 43)
                .addComponent(expert)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void expertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expertActionPerformed
        // TODO add your handling code here:
        gameMusic=new GameMusic("/music.wav");
        this.setVisible(false);
        Strategy gameStrategy = new ExpertStrategy();
        gameController = new GameController(() -> new CircusOfPlates(800, 600, gameStrategy));
        gameController.attach((Observer)this);
        gameController.start();
        gameMusic.play();
    }//GEN-LAST:event_expertActionPerformed

    private void intermediateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intermediateActionPerformed
        // TODO add your handling code here:
        gameMusic=new GameMusic("/music.wav");
        this.setVisible(false);
        Strategy gameStrategy = new IntermediateStrategy();
        gameController = new GameController(() -> new CircusOfPlates(800, 600, gameStrategy));
        gameController.attach((Observer)this);
        gameController.start();
        gameMusic.play();
        
    }//GEN-LAST:event_intermediateActionPerformed

    private void begginerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_begginerActionPerformed
        // TODO add your handling code here:
        gameMusic=new GameMusic("/music.wav");
        this.setVisible(false);
        Strategy gameStrategy = new EasyStrategy();
        gameController = new GameController(() -> new CircusOfPlates(800, 600, gameStrategy));
        gameController.attach((Observer)this);
        gameController.start();
        gameMusic.play();
        
    }//GEN-LAST:event_begginerActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.setVisualState(false);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DifficultyMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DifficultyMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DifficultyMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DifficultyMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DifficultyMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton begginer;
    private javax.swing.JButton expert;
    private javax.swing.JButton intermediate;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean getVisualState() {
        return VisualState;
    }

    @Override
    public void setVisualState(boolean state) {
        this.VisualState = state;
        this.setVisible(state);
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

    @Override
 
    public void update() {
        boolean state = gameController.getVisualState();
        if (state == false) {
           gameMusic.stop();
            this.setVisible(true);

        }
        if (state == true) {
            gameMusic.play();
            this.setVisible(false);

        } 
    }
 }


