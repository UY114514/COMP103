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
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

/**
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 * ID time longitude        latitude magnitude depth region
 * Data is from http://quakesearch.geonet.org.nz/
 * Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 * which may be useful for testing, since it is not as big as the full file.
 * <p>
 * Core:  two methods:
 * doLoadData
 * Loads the data from a file into a field containing an
 * ArrayList of Earthquake objects.
 * Hint : to make an Earthquake object, read one line from the file
 * and pass it as the argument to the Earthquake constructor
 * Make sure any previous values in the field are removed
 * doFindBigOnes
 * Lists all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 * Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 * <p>
 * Completion: one method:
 * doFindPairs
 * Compares each Earthquake in the list with every other Earthquake
 * and finds every pair of "close" earthquakes - earthquakes that
 * - are within 1km of each other, and
 * - have depths within 1km of each other, and
 * - are separated by at least 24 hours days
 * For each pair, prints
 * - their ID's,
 * - the distance between them
 * - the depth difference
 * - the number of days between them.
 * <p>
 * Challenge: one method
 * doFindFollowOns;
 * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 * For each such earthquake on this list
 * - finds a list of all the "follow-on" earthquakes:
 * later earthquakes within a distance of 10km and depth difference <= 10km.
 * - If there are at least two follow-on earthquakes, then it prints out
 * the full details of the big earthquake followed by
 * ID, magnitude and days since the big one for each follow-on earthquake
 * <p>
 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 * file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser {

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /**
     * Construct a new EarthquakeAnalyser object and initialise the interface
     */
    public EarthquakeAnalyser() {
        UI.initialise();
        UI.addButton("Load", this::doLoadData);
        UI.addButton("Big ones", this::doFindBigOnes);
        UI.addButton("Pairs", this::doFindPairs);
        UI.addButton("Follow-ons", this::doFindFollowOns);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only 
    }

    /*
     * Load data from the data file into the earthquakes field
     */
    public void doLoadData() {
//        String filename = UIFileChooser.open("Data File");
        /*For test*/
        String filename = "C:\\Users\\Yan\\Google Drive\\XMUT\\git_COMP103_Assignments\\COMP103\\Assignment2\\EarthquakeAnalyser\\earthquake-data.txt";
        /*# YOUR CODE HERE */
        this.earthquakes.clear();
        try (Scanner sc = new Scanner(new File(filename))) {
//            if (filename == null) {
//                UI.println("Didn't select the file");
//                return;
//            }
            while (sc.hasNextLine()) {
                this.earthquakes.add(new Earthquake(sc.nextLine()));
            }

        } catch (Exception e) {
            UI.println("Error:" + e);
        }


        UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
        UI.println("----------------------------");
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void doFindBigOnes() {
        UI.println("Earthquakes 5.5 and above");
        /*# YOUR CODE HERE */
        ArrayList<String> bigOnes = new ArrayList<String>();
        for (Earthquake e : earthquakes) {
            if (e.getMagnitude() >= 5.5) {
                bigOnes.add(e.toString());
            }
        }

        for (String s : bigOnes) {
            UI.println(s);
        }


        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other and within 1km depth from each other
     * and separated by at least 1 day;
     */
    public void doFindPairs() {
        UI.println("Close earthquakes");
        /*# YOUR CODE HERE */
        int i = 0, j = 1;
        while (i < earthquakes.size()) {
            while (j < earthquakes.size()) {
                if (earthquakes.get(i).distanceTo(earthquakes.get(j)) <= 1) {
                    if ((Math.abs(earthquakes.get(i).getDepth()) - Math.abs(earthquakes.get(j).getDepth())) <= 1.0) {
                        if ((earthquakes.get(i).timeBetween(earthquakes.get(j))) >= 86400) {
//                            UI.println("-------");
                            UI.println("ID: " + earthquakes.get(i).getID() + " & " + earthquakes.get(j).getID());
                            UI.println("Distance:" + earthquakes.get(i).distanceTo(earthquakes.get(j)));
                            UI.println("Depth Difference:" + (Math.abs(earthquakes.get(i).getDepth()) - Math.abs(earthquakes.get(j).getDepth())));
                            UI.println("Days Between:" + ((earthquakes.get(i).timeBetween(earthquakes.get(j)) / 86400)));
                            UI.println("-------");
                        }
                    }
                }
                j++;
            }
            j = i + 1;
            i++;
        }
        UI.println("----------------------------");
    }

    /**
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     * later earthquakes within a distance of 10km and depth difference <= 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     * the full details of the big earthquake followed by
     * ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void doFindFollowOns() {
        UI.println("Big earthquakes and their follow-on earthquakes");
        /*# YOUR CODE HERE */
        ArrayList<Earthquake> bigE = new ArrayList<Earthquake>();
        ArrayList<Earthquake> followOnes = new ArrayList<Earthquake>();
        DecimalFormat df = new DecimalFormat("0");
        for (Earthquake e : earthquakes) {
            if (e.getMagnitude() >= 6.0) {
                bigE.add(e);
            }
        }

        for (Earthquake e1 : bigE) {
            int i = 0;
            boolean doPrint = false;
            followOnes.clear();


//            System.out.println(e1.toString());
            for (Earthquake e2 : earthquakes) {
//                    System.out.println(e1.getID() + "--" + e2.getID() + " " + e1.timeBetween(e2));
                if (e1.timeBetween(e2) > 0) {
                    if (e1.distanceTo(e2) <= 10) {
                        if (Math.abs(e1.getDepth() - e2.getDepth()) > 10) {
                            followOnes.add(e2);
                            UI.sleep(1000);
                        }
                    }

                }

            }
            if (followOnes.size() >= 2) {
                UI.println("***********");
                UI.println(e1.toString());
//                doPrint = true;
                for (Earthquake f : followOnes) {
                    UI.println("    " + f.getID() + " [mag]=" + f.getMagnitude() + " [days later]=" + df.format(e1.timeBetween(f) / 86400));

                }
            }
        }


        UI.println("-------------------------------------");
    }

    public static void main(String[] arguments) {
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
    }

}
