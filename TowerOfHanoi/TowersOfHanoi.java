import ecs100.UI;

import java.util.Stack;

/**
 * TowersOfHanoi represents the classic Towers of Hanoi puzzle.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class TowersOfHanoi {
    private int      totalDisks;
    private Stack[]  tower        = new Stack[4];
    private int      totalTowers  = 3;
    private double   diskMaxSize  = 80.0;
    private double   stickGap     = 100.0;
    private int      numOfSticks  = 3;
    private double[] stickXPos    = new double[numOfSticks + 1];
    private double   stickLength  = 100.0;
    private double   stickWidth   = 10.0;
    private double   stickYPos    = 100;
    private double   platformYPos = 200.0;
    private double   diskHeight   = 10.0;
    private boolean  paused       = true;


    /**
     * Sets up the puzzle with the specified number of disks.
     *
     * @param disks the number of disks
     */
    public TowersOfHanoi(int disks) {
        UI.addButton("Next Step", this::doNextStep);
        totalDisks = disks;
        for (int a = 0; a <= totalTowers; a++) {        //initialize stacks
            tower[a] = new Stack<Integer>();
        }
        for (int i = 1; i < stickXPos.length; i++) {
            if (i == 1) {
                stickXPos[i] = stickGap / 2;
            } else {
                stickXPos[i] = stickXPos[i - 1] + stickGap;
            }
            System.out.println(stickXPos[i]);
        }
        addDisks();
        drawTower();
        drawDisks();
    }

    public void doNextStep() {
        paused = false;
    }

    /**
     * Add disks to the first tower.
     * For convenience, i starts from 1
     */
    public void addDisks() {
        for (int i = 1; i <= totalDisks; i++) {
            tower[1].push(i);
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
        drawDisks();
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
     *              <p>
     *              it will wait for the boolean paused
     *              if not then sleep for 100ms
     */
    private void moveOneDisk(int start, int end) {
        while (true) {
            if (paused) {
                UI.sleep(100);
            } else {
                UI.println("Move one disk from " + start + " to " + end);
                tower[end].push(tower[start].pop());
                UI.clearGraphics();
                drawDisks();
                drawTower();
                paused = true;
                break;
            }
        }
    }

    /**
     * Draw the tower(3 sticks and a platform)
     **/
    private void drawTower() {
        /*draw the tower itself*/
        UI.drawRect(0, 200, 300, 20);                   //draw the platform
        for (int i = 1; i < stickXPos.length; i++) {                           //draw sticks
            UI.drawRect(stickXPos[i], stickYPos, stickWidth, stickLength);
        }
    }

    /**
     * Draw disks
     **/
    private void drawDisks() {
        for (int i = 1; i < tower.length; i++) {
            Stack stack = tower[i];
            for (int index = 0; index < stack.size(); index++) {
                int num = (int) stack.get(index);
                double factor = 1 - (num - 1) / (double) (totalDisks + 1);
                UI.fillRect(stickXPos[i] - factor * diskMaxSize / 2.0 + stickWidth / 2, platformYPos - (index + 1) * diskHeight, factor * diskMaxSize, diskHeight);
            }
        }
    }
}		
