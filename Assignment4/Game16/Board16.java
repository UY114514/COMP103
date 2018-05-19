// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2017T2, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

public class Board16 {

    private static final int LIMIT = 7; // to determine the ratio of 2s over 4s (the closer to 10 the more twos)
    private static final int TARGET = 16; // number value the player needs to reach

    private final int COLUMNS; // size of the board
    private int [] board;

    public Board16 (int boardSize) {
        COLUMNS = boardSize;
        board = new int [COLUMNS];
    }

    /** Return whether (at least) the magic target number has been achieved
     *  [CORE]
     */
    public boolean hasReachedTarget() {
        /*# YOUR CODE HERE */
        for (int i = 0; i < board.length; i++) {
            if (board[i] >= TARGET) {
                return true;
            }
        }
        return false;

    }

    /** Return whether the game is over (true) or not (false)
     *  If there is some space on the board left, the game is not over.
     *  If there is no space left, the game is still not over, if adjacent tiles hold the same value,
     *  as they could be compressed to fewer tiles by a player move.
     *  [CORE]
     */
    public boolean isGameOver() {
        /*# YOUR CODE HERE */

        if (this.numEmptyTiles() == 0) {            //If there is some space on the board left
            return false;
        } else {
            for (int i = 0; i < board.length; i++) {//If there is no space left
                int i1 = board[i];
                for (int j = i; j < board.length; j++) {
                    int i2 = board[j];
                    if (i1 == i2) {
                        return false;
                    }

                }

            }
        }





        return false;
    }

    /** Return the number of empty tiles
     *  An empty tile is one which holds the value 0
     *  [CORE]
     */
    private int numEmptyTiles() {
        /*# YOUR CODE HERE */
        Integer result = 0;
        for (int i : board) {
            if (i == 0) {
                result++;
            }
        }
        return result;
    }

    /** Insert a random number (either 2 or 4) at a random empty tile.
     *  Note that 7 out of 10 times the number should be 2.
     *  An empty tile is one which holds the value 0.
     *  [CORE]
     */
    public void insertRandomTile() {
        /*# YOUR CODE HERE */
        Double randomNum = Math.random() * 10;
        Integer result = 0;
        if (randomNum < 7) {
            result = 2;
        } else {
            result = 4;
        }

        Integer emptyTilesIndex[] = new Integer[numEmptyTiles()];         //marks empty tiles index
        int arrayIndex = 0;
        for (Integer i : board) {
            if (i == 0) {
                emptyTilesIndex[arrayIndex] = i;
                arrayIndex++;
            }
        }

        Integer randomIndex =(int)(Math.random() * (numEmptyTiles()-1));  //choose a random empty tile
        if (board != null) {
            board[emptyTilesIndex[randomIndex]] = result;

        }


    }

    /** Move the tiles left.
      Each time two tiles with the same number touch, the numbers are added and the two tiles merge on
      the left side. An empty tile is then added on the right hand side of the board.

      Examples:
        2 2 4 2 will give 4 4 2 0 (the first 2s merge into a 4. Then the remaining
          4 and 2 follow, and the board is completed on the right with a 0)

        4 4 2 2 will give 8 4 0 0 (4 and 4 merge into a 8, 2 and 2 merge into a 4,
          completing with zeros on the right)

        4 4 4 4 will give 8 8 0 0 (First two 4s merge together, the last two 4s merge together)

        1. Shift all non-empty tiles to the left as far as possible, making sure that all empty tiles are on the right.
        2. From left to right, merge any two tiles with the same number by adding them, discarding
          the second one, and adding an empty tile on the right of the board.
     * [COMPLETION]
     */
    public void left() {
        /*# YOUR CODE HERE */
        for (int i = board.length - 1; i >= 0; i--) {
            int i1 = board[i];

            if (i-->=0) {
                if (board[i] == board[i - 1]) {//check if board[i] equals to the tile on the left side

                }
            }

        }

    }

    /** Move the tiles right.
     * Each time 2 tiles with the same number touch, the numbers are added and the two tiles merge on
     * the right side. An empty tile is then added on the left hand side of the board.

     * Examples:
     *   2 2 4 2 will give 0 4 4 2 (2 and 4 remain unchanged, then the last leftmost 2s merge
     *     into a 4, completing with a zero on the left.)
     *
     *   4 4 2 2 will give 0 0 8 4 (2 and 2 merge into a 4, 4 and 4 merge into a 8)
     *   4 4 4 4 will give 0 0 8 8 (First two 4s merge together, the last two 4s merge together)
     *
     *   1. Shift all non-empty tiles to the right, making sure that all the empty tiles are on the left.
     *   2. From right to left, merge any two tiles with the same number by adding them, discarding
     *      the second one, and adding an empty tile on the left of the board.
     * [COMPLETION]
     */
    public void right() {
        /*# YOUR CODE HERE */

    }

    public String toString() {
        String tiles = "";

        for (int col = 0; col < COLUMNS; col++) {
            tiles += "  " + board[col];
        }
        return tiles;
    }

    // layout of the board
    private final int boardLeft = 80;    // left edge of the board
    private final int boardTop = 40;     // top edge of the board
    private final int tileSize = 50;     // width of tiles in the board

    public void displayMessage(String txt){
        // Clear up any eventual previous message first
        UI.setColor(Color.white);
        UI.fillRect(boardLeft, boardTop + tileSize * 2, 500, 200);

        // Display the message
        UI.setFontSize(40);
        UI.setColor(Color.red);
        UI.drawString(txt, boardLeft, boardTop + tileSize * 3);
    }

    public void redraw() {
        //UI.clearGraphics();
        for (int col = 0; col < COLUMNS; col++) {
            drawTile(col);
        }

        displayScore();
    }

    private void drawTile(int col) {
        double left = boardLeft+col*tileSize;
        double top = boardTop;

        // Fill the rectangle with a colour matching the value of the tile
        UI.setColor(getColor(board[col]));
        UI.fillRect(left,top,tileSize,tileSize);

        // Outline the rectangle
        UI.setColor(Color.black);
        UI.drawRect(left,top,tileSize,tileSize);

        // Display the number
        UI.setFontSize(20);
        if (board[col] == 0)
          return;

        if (board[col] >= 16)
          UI.setColor(Color.white);

        double x = left + tileSize * 0.3;
        double y = top + tileSize * 0.6;
        UI.drawString(""+board[col], x, y);
    }

    private Color getColor(int value) {
        switch (value) {
            case  0 : { return Color.white; }
            case  2 : { return Color.gray; }
            case  4 : { return Color.orange; }
            case  8 : { return Color.red; }
            case 16 : { return Color.cyan; }
            case 32 : { return Color.blue; }
            case 64 : { return Color.green; }
             default: {return Color.black;}
        }
    }

    private void displayScore() {
        double x = boardLeft + tileSize * COLUMNS /2.;
        double y = boardTop/2;
        double size = 30;

        // Clear up previous score first
        UI.setColor(Color.white);
        UI.fillRect(x-5, 0, size, size);

        int score = 0;
        for (int col = 0; col < COLUMNS; col++) {
            score += board[col];
        }
        UI.setFontSize(20);
        UI.setColor(Color.blue);
        UI.drawString(""+score, x, y);
    }
}
