/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameMusic;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author youssef
 */
public class GameMusic {

        Clip clip;
        AudioInputStream sound;

        public GameMusic(String soundFileName) {
            try {
                sound = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(soundFileName));
                clip = AudioSystem.getClip();
                clip.open(sound);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void play() {

            try {
                clip.loop(3);
            } catch (Exception e) {
            }
        }

        public void stop() {
            try {
                sound.close();
                clip.close();
                clip.stop();
            } catch (IOException e) {
            }
        }
    }
