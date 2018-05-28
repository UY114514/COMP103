/**
 * SolveTowers uses recursion to solve the Towers of Hanoi puzzle.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class SolveTowers
{
    /**
     * Creates a TowersOfHanoi puzzle and solves it.
     */
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        int discs = 2;
        TowersOfHanoi towers = new TowersOfHanoi(discs);
        System.out.println("Number of discs: " + discs);
        System.out.println("----------------------");
        towers.solve();
    }
}