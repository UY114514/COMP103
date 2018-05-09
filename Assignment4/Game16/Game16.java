// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2017T2, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

public class Game16 {
    Board16 board; // board representation
    private final int SIZE = 5; // number of position on the board
    private boolean hasMessageDisplayed = false; // managing "Game won" message 

    public Game16 () {
        UI.addButton("Restart", this::startGame);
        UI.addButton("Quit", UI::quit);

        UI.setKeyListener(this::doKey);
        UI.setDivider(0.5);

        UI.println("Move the tiles left with the a key, and right with the d key");
        UI.println("(put the mouse over the graphics pane first).\n");
        UI.println("Each time two tiles with the same number touch after a move,");
        UI.println("the two tiles merge into one containing the sum of the values.\n");
        UI.println("Produce the magic number of 16.");

        startGame();
    }

    /** Respond to key actions */
    public void doKey(String key) {
        if (key.equals("a")) move("Left");
        else if (key.equals("d")) move("Right");
    }

    private void startGame() {
        UI.clearGraphics();
        
        hasMessageDisplayed = false;
        
        board = new Board16 (SIZE);
        board.insertRandomTile();
        board.redraw();
    }

    private void move(String direction) {
        if (board == null) {
            UI.println("Board needs to be restarted.");
            return;
        }

        if (direction.equals("Left"))
            board.left();
        else if (direction.equals("Right"))
            board.right();
            
        board.redraw();

        // Only display the "WON" message once
        if (!hasMessageDisplayed && board.hasReachedTarget()) {
            hasMessageDisplayed = true;
            board.displayMessage("Game won!!!");
            UI.println("You can start a new game or carry on with this game.");
            
            return;
        }

        // Insert a new random tile
        UI.sleep(20);
        board.insertRandomTile();
        board.redraw();

        // Check if the game is over
        if (board.isGameOver()) {
            board.displayMessage("Game OVER!!!");
            board = null;  // flag that board needs to be restarted
        }
    }

    public static void main(String[] arguments){
        new Game16();
    }   
}
