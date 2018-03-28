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
 * Creates a rack of 7 Scrabble tiles.
 * Uses the Tile class.
 * User can select a tile using the mouse (written for you)
 * then shift it in the array one step to the left or to the right
 * or removing it from the array.
 * After every change to the array, it is necessary to call the redraw method,
 * (Unlike Ducks, Tiles do not automatically draw themselves or keep track
 * of where they are)
 */
public class TileExercise{
    public static final double LEFT = 20;
    public static final double TOP = 100;
    public static final double SIZE = 40;
    public static final int NUM_TILES = 7;

    private Tile[] tiles = new Tile[NUM_TILES];
    private int selectedIndex = 0;        // The index of the selected tile.

    /**
     * Constructor to set up an interface with buttons to call all the methods
     */
    public TileExercise(){
        UI.setMouseListener(this::doMouse);
        UI.addButton("Reset Tiles", this::doReset);
        UI.addButton("Move Left", this::doLeft);
        UI.addButton("Move Right", this::doRight);
        UI.addButton("Remove", this::doRemove);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Fill the array in the tiles field with new Tiles
     *  Note: the Tile constructor needs no arguments, and creates a
     *        random tile.
     * Redraw the tiles.
     */
    public void doReset(){
        /*# YOUR CODE HERE */

        this.redraw();
    }

    /**
     * Move the selected tile to the left 
     * Redraw the tiles.
     */
    public void doLeft(){
        /*# YOUR CODE HERE */

    }

    /**
     * Move the selected tile to the right 
     * Redraw the tiles.
     */
    public void doRight(){
        /*# YOUR CODE HERE */

    }

    /**
     * Remove the currently selected tile, leaving a null in its place
     * Redraw the tiles.
     */
    public void doRemove(){
        /*# YOUR CODE HERE */

    }

    //------------------------------------------------
    //   Methods for drawing and selecting the tiles
    //------------------------------------------------
    /**
     * If mouse is clicked on the rack, works out which tile
     * and stores the index of the selected tile
     */
    public void doMouse(String action, double x, double y){
        if ( action.equals("released") &&
        y >= TOP && y <= TOP+SIZE &&
        x >= LEFT && x <= LEFT+NUM_TILES*(SIZE+1) ){
            this.selectedIndex = (int)((x-LEFT)/(SIZE+1));
            this.redraw();
        }
    }

    /**
     * Draws all the tiles, in a rack.
     * highlights the currently selected tile.
     */
    public void redraw(){
        UI.eraseRect(LEFT-2, TOP-2, NUM_TILES*(SIZE+1)+3, SIZE+3);
        UI.setColor(Color.black);
        UI.drawRect(LEFT-2, TOP-2, NUM_TILES*(SIZE+1)+3, SIZE+3);
        UI.setColor(Color.red);
        UI.drawRect(LEFT+this.selectedIndex*(SIZE+1)-1, TOP-1, SIZE+1, SIZE+1);
        for (int i=0; i<this.tiles.length; i++){
            if (this.tiles[i]!=null){
                this.tiles[i].draw(LEFT + i*(SIZE+1), TOP);
            }
        }
    }

    public static void main(String[] arguments){
        new TileExercise();
    }        

}
