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
import java.text.*;
import java.io.*;

/**
 * Earthquake
 * Describes a single earthquake, with information from the earthquake data
 * available from http://quakesearch.geonet.org.nz/
 * Has methods for computing the distance and time difference between two earthquakes
 */

public class Earthquake{

    //Constants needed to parse the date and time from the file and print it out
    public static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("HH:mm:ss' UTC, 'dd/MM/yyyy ");


    private String ID;
    private Date time;
    private String region;
    private double longitude, latitude;
    private double depth;
    private double magnitude;
    

    /** Construct a new EarthQuake object and initialise the interface */
    public Earthquake(String description){
        Scanner sc = new Scanner(description);
        this.ID = sc.next();
        try{ this.time = DATE_PARSER.parse(sc.next()); }catch(ParseException e){System.out.println(e);}
        this.longitude = sc.nextDouble();
        this.latitude = sc.nextDouble();
        this.magnitude = sc.nextDouble();
        this.depth = sc.nextDouble();
        this.region = sc.next();
    }

    /**
     * Distance from this earthquake to the other earthquake, in km
     * Formula from http://www.movable-type.co.uk/scripts/latlong.html
     */
    public double distanceTo(Earthquake other){
        double lat1 = this.latitude *Math.PI/180;
        double lat2 = other.latitude *Math.PI/180;
        double difLong = other.longitude*Math.PI/180 - this.longitude*Math.PI/180;

        return Math.acos( (Math.sin(lat1)*Math.sin(lat2)) +
                          (Math.cos(lat1)*Math.cos(lat2))* Math.cos(difLong) ) * 6371;
    }

    /**
     * Time difference between this earthquake and the other, in minutes
     */
    public double timeBetween(Earthquake other){
        return (other.time.getTime()-this.time.getTime())/60000; 
    }

    /** Get depth of the earthquake */
    public double getDepth(){
        return this.depth;
    }

    /** Get magnitude of the earthquake */
    public double getMagnitude(){
        return this.magnitude;
    }

    /** * Get earthquake ID */
    public String getID(){
        return this.ID;
    }

    /** * Get earthquake region */
    public String getRegion(){
        return this.region;
    }

    /** * Get time */
    public Date getTime(){
        return this.time;
    }

    /**
     * Returns a nicely formatted String describing the earthquake
     */
    public String toString(){
        return (this.ID +
                " at " + DATE_FORMATTER.format(this.time) +
                " mag:" + this.magnitude +
                " depth:" + this.depth +
                "at (" + this.longitude +","+ this.latitude +") " +
                "in " + this.region);
    }

}
