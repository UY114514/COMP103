// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2017T1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * Tile
 * Represents a single tile.
 * Needs the name of the image file for drawing itself
 */

public class Tile{

    private String image;

    /** Construct a new Tile object */
    public Tile(){
        this.image = "tiles/"+ (char)(Math.random()*26+'A') + ".jpg";
    }

    /**
     * Draw the tile
     */
    public void draw(double x, double y){
        UI.drawImage(this.image, x, y);
    }

}
