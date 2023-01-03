/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import Shapes.Rectangle;
import Shapes.Plate;
import Shapes.*;
import Shapes.Shapes;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;

/**
 *
 * @author omare
 */
public class Factory {

    public Factory() {
    }
    

    public GameObject getShapes(int posX,int posY,int width ,int height,boolean horizontalOnly,Color color,String path,String type){
        if(type==null)
            return null;
        else if(type.equalsIgnoreCase("Rectangle")) {
            return (GameObject)new Rectangle(posX,posY,width,height,horizontalOnly,color); }
        else if (type.equalsIgnoreCase("Plate")) {
            return (GameObject) new Plate(posX, posY, width, horizontalOnly, color);
        }
        else if (type.equalsIgnoreCase("ImageObject")) {
            return (GameObject) new ImageObject(posX, posY, horizontalOnly, path);
        }
        else if (type.equalsIgnoreCase("ClownStick")){
            
            return (GameObject) new ClownStick(posX, posY, horizontalOnly, color);
        }
        else if (type.equalsIgnoreCase("BombObject")) {
            return (GameObject) new BombObject(posX, posY, horizontalOnly, path);
        }
        else if (type.equalsIgnoreCase("Explosion")) {
            return (GameObject) new Explosion(posX, posY, horizontalOnly, path);
        }
        return null;    
    }
   
}
