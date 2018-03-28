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
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 *   ID time longitude        latitude magnitude depth region
 * Data is from http://quakesearch.geonet.org.nz/
 *  Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 *   which may be useful for testing, since it is not as big as the full file.
 * 
 * Core:  two methods:
 *   doLoadData
 *      Loads the data from a file into a field containing an
 *      ArrayList of Earthquake objects.
 *      Hint : to make an Earthquake object, read one line from the file
 *              and pass it as the argument to the Earthquake constructor
Make sure any previous values in the field are removed
 *   doFindBigOnes
 *      Lists all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 *      Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 *   
 * Completion: one method:
 *   doFindPairs
 *        Compares each Earthquake in the list with every other Earthquake
 *      and finds every pair of "close" earthquakes - earthquakes that
 *        - are within 1km of each other, and
 *        - have depths within 1km of each other, and
 *        - are separated by at least 24 hours days 
 *      For each pair, prints
 *        - their ID's,
 *        - the distance between them
 *        - the depth difference
 *        - the number of days between them.

 * Challenge: one method
 *  doFindFollowOns;
 *      Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 *      For each such earthquake on this list
 *       - finds a list of all the "follow-on" earthquakes:
 *         later earthquakes within a distance of 10km and depth difference <= 10km.
 *       - If there are at least two follow-on earthquakes, then it prints out
 *          the full details of the big earthquake followed by
 *          ID, magnitude and days since the big one for each follow-on earthquake
 *
 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 *   file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser{

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /** Construct a new EarthquakeAnalyser object and initialise the interface */
    public EarthquakeAnalyser(){
        UI.initialise();
        UI.addButton("Load", this::doLoadData);
        UI.addButton("Big ones",  this::doFindBigOnes);
        UI.addButton("Pairs", this::doFindPairs);
        UI.addButton("Follow-ons", this::doFindFollowOns);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only 
    }

    /*
     * Load data from the data file into the earthquakes field
     */
    public void doLoadData(){
        String filename = UIFileChooser.open("Data File");
        /*# YOUR CODE HERE */

        UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
        UI.println("----------------------------");
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void doFindBigOnes(){
        UI.println("Earthquakes 5.5 and above");
        /*# YOUR CODE HERE */

        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other and within 1km depth from each other
     * and separated by at least 1 day;
     */
    public void doFindPairs(){
        UI.println("Close earthquakes");
        /*# YOUR CODE HERE */

        UI.println("----------------------------");
    }

    /**
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6 
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     *   later earthquakes within a distance of 10km and depth difference <= 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     *     the full details of the big earthquake followed by
     *    ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void doFindFollowOns(){
        UI.println("Big earthquakes and their follow-on earthquakes");
        /*# YOUR CODE HERE */

        UI.println("-------------------------------------");
    }

    public static void main(String[] arguments){
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
    }        

}
