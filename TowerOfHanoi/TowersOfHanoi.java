import ecs100.UI;

import java.util.Stack;

/**
 * TowersOfHanoi represents the classic Towers of Hanoi puzzle.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class TowersOfHanoi {

    private int     totalDisks;
    private Stack[] tower = new Stack[4];




    /**
     * Sets up the puzzle with the specified number of disks.
     *
     * @param disks the number of disks
     */
    public TowersOfHanoi(int disks) {
        totalDisks = disks;
        for(int a = 0; a <= 3; a++)
        {
            tower[a] = new Stack<Integer>();
        }


        for (int i = 0; i <= totalDisks; i++) {
            tower[1].push(i);
            UI.println(tower[1]);
        }
    }

    /**
     * Performs the initial call to moveTower to solve the puzzle.
     * Moves the disks from tower 1 to tower 3 using tower 2.
     */
    public void solve() {
        moveTower(totalDisks, 1, 3, 2);
    }

    /**
     * Moves the specified number of disks from one tower to another
     * by moving a subtower of n-1 disks out of the way, moving one
     * disk, then moving the subtower back. Base case of 1 disk.
     *
     * @param numDisks the number of disks to move
     * @param start    the starting tower
     * @param end      the ending tower
     * @param temp     the temporary tower
     */
    private void moveTower(int numDisks, int start, int end, int temp) {
        drawTower();
        if (numDisks == 1)
            moveOneDisk(start, end);
        else {
            moveTower(numDisks - 1, start, temp, end);
            moveOneDisk(start, end);
            moveTower(numDisks - 1, temp, end, start);
        }
    }

    /**
     * Prints instructions to move one disk from the specified start
     * tower to the specified end tower.
     *
     * @param start the starting tower
     * @param end   the ending tower
     */
    private void moveOneDisk(int start, int end) {
        UI.println("Move one disk from " + start + " to " + end);
        tower[end].push(tower[start].pop());
        UI.println("T1: "+tower[1]);
        UI.println("T2: "+tower[2]);
        UI.println("T3: "+tower[3]);
    }


    private void drawTower() {
        /*draw the tower itself*/
        UI.drawRect(0, 200, 300, 20);
        UI.drawRect(50, 100, 10, 100);
        UI.drawRect(50, 100, 10, 100);
        UI.drawRect(150, 100, 10, 100);
        UI.drawRect(250, 100, 10, 100);
    }

    private void drawDisks() {

    }
}		
