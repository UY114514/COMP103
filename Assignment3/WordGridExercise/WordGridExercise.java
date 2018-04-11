// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2017T1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * WordGridExercise
 * lets the user load, edit, and save a 2D table of words.
 * The table is represented by a 2D array of Strings, using null
 * to represent empty cells.
 */
public class WordGridExercise {
    // Fields
    private int selectedRow = 0;  // selected cell
    private int selectedCol = 0;

    private String currentWord = "-";

    private int left = 50;  // position of left top corner of table
    private int top = 20;
    private int wd = 60;   // size of cells
    private int ht = 20;

    private String[][] wordGrid = new String[10][8];

    /**
     * Construct a new WordGridExercise object and set up the GUI
     */
    public WordGridExercise() {
        UI.setMouseListener(this::doMouse);
        UI.addTextField("Word", this::doSetWord);
        UI.addButton("Set Entry", this::doSetEntry);
        UI.addButton("Delete Entry", this::doDeleteEntry);
        UI.addButton("Load Words", this::doLoadWords);
        UI.addButton("Save Words", this::doSaveWords);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0); // expand Graphic Pane area
        this.redisplay();
    }

    /**
     * Display the grid of words.
     * Draw a rectangle for every element,
     * but only draw the string if it is not null
     */
    public void redisplay() {
        UI.clearGraphics();
        UI.setColor(Color.black);
        /*# YOUR CODE HERE */

        //highlights the selected cell
        UI.setColor(Color.red);
        UI.drawRect(this.left + this.selectedCol * this.wd,
                this.top + this.selectedRow * this.ht,
                this.wd, this.ht);
    }

    /**
     * Load file into the array of words
     * File represented as a sparse array:
     * first line has size (number of rows and cols)
     * other lines have row, col, entry for each non-null element
     * Construct array of correct size, then read and add every entry.
     */
    public void loadSparse() {
        try {
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            /*# YOUR CODE HERE */

            sc.close();
        } catch (IOException e) {
            UI.println("Fail: " + e);
        }
    }

    /**
     * Saves the grid as a sparse array.
     * first line should have size (number of rows and cols)
     * other lines have row, col, entry for each non-null element
     */
    public void saveSparse() {
        String fname = UIFileChooser.save();
        if (fname == null) return;  // user didn't select a file.
        try {
            PrintStream out = new PrintStream(new File(fname));
            /*# YOUR CODE HERE */

            out.close();
        } catch (IOException e) {
            UI.println("Fail: " + e);
        }
    }

    /** ---------- The code below is already written for you ---------- **/

    /**
     * Respond to buttons events
     */
    public void doSetEntry() {
        this.wordGrid[this.selectedRow][this.selectedCol] = this.currentWord;
        this.redisplay();
    }

    public void doDeleteEntry() {
        this.wordGrid[this.selectedRow][this.selectedCol] = null;
        this.redisplay();
    }

    public void doLoadWords() {
        this.loadSparse();
        this.redisplay();
    }

    public void doSaveWords() {
        this.saveSparse();
        UI.println("Saved");
    }

    /**
     * Respond to textField entries
     */
    public void doSetWord(String value) {
        this.currentWord = value;
        this.redisplay();
    }

    /**
     * Respond to mouse events
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("released")) {
            int c = ((int) x - this.left) / this.wd;
            int r = ((int) y - this.top) / this.ht;
            if (r >= 0 && r < this.wordGrid.length && c >= 0 && c < this.wordGrid[0].length) {
                this.selectedRow = r;
                this.selectedCol = c;
                this.redisplay();
            }
        }
    }


    // Main
    public static void main(String[] arguments) {
        new WordGridExercise();
    }

}
