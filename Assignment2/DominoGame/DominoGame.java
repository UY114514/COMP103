// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2017T1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.awt.Color;


/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to six dominoes.
 *  They can reorder the dominoes in their hand, they can place dominoes
 *  from their hand onto the table, and they can pick up more dominoes from a bag
 *  to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real dominoes games. 
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, random domino.
 *  Dominos have a draw(double x, double y) method that will draw the
 *  Domino on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array that can hold 6 Dominos. 
 *    table: an arrayList of the Dominos that have been placed on the table.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGame{
    public static final int NUM_HAND = 6;    // Number of dominos in hand

    // Fields
    private Domino[] hand;            // the hand (fixed size array of Dominos)
    private ArrayList<Domino> table;  // the table (variable sized list of Dominos)

    private int selectedPos = 0;      //  selected position in the hand.

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    public static final int HAND_LEFT = 60; // x-position of the leftmost Domino in the hand
    public static final int HAND_TOP = 5;   // y-Position of all the Dominos in the hand 
    public static final int DOMINO_SPACING = 54; 
    //spacing is the distance from left side of Domino to left side of next domino
    public static final int DOMINO_HEIGHT = 100; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 120;   

    /**  Constructor:
     * Initialise the hand field to have an array that will hold NUM_HAND Dominos
     * Initialise the table field to have an ArrayList of Dominos,
     * set up the GUI (buttons and mouse)
     *  restart the game
     */
    public DominoGame(){
        /*# YOUR CODE HERE */
        hand = new Domino[NUM_HAND];
        table = new ArrayList<>();
        UI.addButton("Pickup", this::doPickup);
        UI.addButton("Place", this::doPlaceDomino);
        UI.addButton("Flip", this::doFlipDomino);
        UI.addButton("Left", this::doMoveLeft);
        UI.addButton("Right", this::doMoveRight);
        JButton restart = UI.addButton("Restart", this::doRestart);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::doMouse);


        this.doRestart();
        this.redraw();
    }

    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have no dominos
     */
    public void doRestart(){
        /*# YOUR CODE HERE */
        this.table.clear();
        Arrays.fill(hand, null);
        this.redraw();
    }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random domino and put it into the first empty position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void doPickup() {
        /*# YOUR CODE HERE */
        for (int i = 0; i < this.hand.length; i++) {
            if (this.hand[i] == null) {
                this.hand[i] = new Domino();
                break;
            }

        }
        this.redraw();
    }

    /**
     * Draws the outline of the hand,
     * draws all the Dominos in the hand,
     * highlights the selected position in some way
     */
    public void drawHand(){
        /*# YOUR CODE HERE */
//        Show dominos in the hand
        for (int i = 0; i < this.hand.length; i++) {
            if (this.hand[i] != null) {
                this.hand[i].draw(HAND_LEFT + i * DOMINO_SPACING, 5);
            }
        }

//        Show selectedPos
        if (this.hand.length > 0) {
            UI.setColor(Color.green);
            UI.drawRect(HAND_LEFT + selectedPos * DOMINO_SPACING, 5, 50, 100);
        }

    }

    /**
     * Move domino from selected position on hand (if there is domino there) to the table
     * The selectedPos field contains the index of the selected domino.
     */
    public void doPlaceDomino() {
        /*# YOUR CODE HERE */
        if (this.hand[selectedPos] != null) {
            this.table.add(this.hand[selectedPos]);
            this.hand[selectedPos] = null;
        } else {
            UI.println("You have no Domino at there!");
            UI.setColor(Color.red);
            UI.drawRect(HAND_LEFT + selectedPos * DOMINO_SPACING, 5, 50, 100);
            UI.sleep(100);
            UI.setColor(Color.green);
            UI.drawRect(HAND_LEFT + selectedPos * DOMINO_SPACING, 5, 50, 100);
            UI.setColor(Color.red);
            UI.sleep(100);
            UI.drawRect(HAND_LEFT + selectedPos * DOMINO_SPACING, 5, 50, 100);
            UI.setColor(Color.green);
        }


        this.redraw();
    }

    /**
     * Draws the list of Dominos on the table, 10 to a row
     * Note, has to wrap around to a new row when it gets to the
     * edge of the table
     */
    public void drawTable(){
        /*# YOUR CODE HERE */
        int i = 0;
        int row = 0;
        for (Domino d : table) {
            d.draw(TABLE_LEFT + i * DOMINO_SPACING, TABLE_TOP + row * (100 + 5));
            i++;
            if (i == 10) {
                i = 0;
                row++;
            }

        }

    }

    /**
     * If there is a domino at the selected position in the hand, 
     * flip it over.
     */
    public void doFlipDomino(){
        /*# YOUR CODE HERE */

        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the domino 
     */
    public void doMoveLeft(){
        /*# YOUR CODE HERE */

        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the domino 
     */
    public void doMoveRight(){
        /*# YOUR CODE HERE */

        this.redraw();
    }

    /**
     * If the table is empty, only a double (top and bottom the same) can be suggested.
     * If the table is not empty, see if one domino has a number that matches one of the 
     *    numbers of the last domino on the table.
     */
    public void doSuggestDomino(){
        /*# YOUR CODE HERE */

    }

    /** ---------- The code below is already written for you ---------- **/

    /** Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table */
    public void doMouse(String action, double x, double y){
        if (action.equals("released")){
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
    }

    /**
     *  Redraw the table and the hand.
     *  To work with the code above, this needs to use the constants:
     *   - DOMINO_SPACING, HAND_HEIGHT, HAND_LEFT, HAND_TOP, TABLE_LEFT, TABLE_TOP
     *   See the descriptions where these fields are defined.
     *  Needs to clear the graphics pane,
     *  then draw the hand with all its dominos, 
     *  then outline the selected position on the hand
     *  then draw the rows of dominos on the table.
     */
    public void redraw(){
        UI.clearGraphics();
        this.drawHand();
        this.drawTable();
    }

    public static void main(String[] args){
        DominoGame obj = new DominoGame();
    }   

}
