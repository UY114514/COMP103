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

/** Domino
 * Represents individual Dominos - rectangular tiles with two numbers
 * The constructor will return a new Domino with random numbers (0-6) on it
 * Methods:
 *   draw(double left, double top)  draws the domino at a position
 *   flip() the domino over.
 *   getTop() and getBottom() return the numbers on the top/bottom of the domino
 *   
 */

public class Domino{

    private int first;
    private int secnd;
    private boolean flipped = false;

    public static final int WIDTH = 50;  // the width of the domino 
    public static final int HEIGHT = WIDTH*2;  // the height of the domino 
    public static final int DIAM = WIDTH/6;  // the diameter of the spots

    /** Construct a new Domino object with a pair of random values on it */
    public Domino(){
        this.first = (int)(Math.random()*7); // between 0 and 6 inclusive
        this.secnd = (int)(Math.random()*7); // between 0 and 6 inclusive
    }

    /**
     * Turns the domino over
     */
    public void flip(){
        this.flipped = !this.flipped;
    }

    /**
     * Return the top number on the Domino
     */
    public int getTop(){
        if (this.flipped) {
            return this.secnd;
        }
        else {
            return this.first;
        }
    }

    /**
     * Return the bottom number on the Domino
     */
    public int getBottom(){
        if (this.flipped) {
            return this.first;
        }
        else {
            return this.secnd;
        }
    }

    /**
     * Draws the domino at the position x, y
     */
    public void draw(double x, double y){
        UI.setLineWidth(1);
        UI.setColor(Color.black);
        UI.fillRect(x, y, WIDTH, HEIGHT);
        UI.setColor(Color.red.darker());
        UI.drawRect(x+1, y+1, WIDTH-3, HEIGHT-3);
        UI.setLineWidth(2);
        UI.setColor(Color.gray);
        UI.drawLine(x+2,y+WIDTH, x+WIDTH-3,y+WIDTH);
        UI.setLineWidth(1);
        if (!flipped){
            this.drawNumber(this.first, x, y);
            this.drawNumber(this.secnd, x, y+WIDTH);
        }
        else {
            this.drawNumber(this.secnd, x, y);
            this.drawNumber(this.first, x, y+WIDTH);
        }
    }

    /**
     * Draw the number in the square at x,y, using white circles
     */
    public void drawNumber(int num, double x, double y){
        double xOff = x-DIAM/2;  // offset by radius of spots
        double yOff = y-DIAM/2;// offset by radius of spots
        double left  = xOff+WIDTH*0.25;
        double centr = xOff+WIDTH*0.5;
        double right = xOff+WIDTH*0.75;
        double top = yOff+WIDTH*0.25;
        double mid = yOff+WIDTH*0.5;
        double bot = yOff+WIDTH*0.75;
        UI.setColor(Color.white);
        if (num%2 == 1){   // 1, 3, 5
            UI.fillOval(centr, mid, DIAM, DIAM);
        }
        if (num>1){    // 2, 3, 4, 5, 6
            UI.fillOval(left, top, DIAM, DIAM);
            UI.fillOval(right, bot, DIAM, DIAM);
        }
        if (num>3){ //4, 5, 6
            UI.fillOval(left, bot, DIAM, DIAM);
            UI.fillOval(right, top, DIAM, DIAM);
        }
        if (num==6){
            UI.fillOval(left, mid, DIAM, DIAM);
            UI.fillOval(right, mid, DIAM, DIAM);
        }
    }

    /**
     * Method for testing the dominos
     * Creates 49 dominos, and draws them in a table,
     * and the flipped versions in a table beside it
     */
    public static void main(String[] arguments){
        UI.setImmediateRepaint(false);
        double flippedLeft = 8*(WIDTH+5);
        for (int row=0; row<=6; row++){
            for (int col=0; col<=6; col++){
                Domino dom = new Domino();
                double x = col*(WIDTH+5);
                double y = row*(HEIGHT+5);
                dom.draw(x,y);
                dom.flip();
                dom.draw(flippedLeft + x, y);
            }
        }
        UI.repaintGraphics();
    }

}
