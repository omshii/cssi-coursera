
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from)
        {
            int maxIdx = from;
            for (int i=from+1; i< quakeData.size(); i++) {
                if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                    maxIdx = i;
                }
            }
            return maxIdx;
        }
    
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in)
    {
       for (int i=0; i<70; i++) { //in.size()
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        } 
    }
    
    public ArrayList<QuakeEntry> onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        QuakeEntry q1, q2;
        for(int i=1; i<quakeData.size()-numSorted; i++)
        {
            if(quakeData.get(i-1).getMagnitude()>quakeData.get(i).getMagnitude())
            {
                q1 = quakeData.get(i-1);
                q2 = quakeData.get(i);
                quakeData.set(i-1, q2);
                quakeData.set(i, q1);
            }
        }
        return quakeData;
    }
    
    public void sortbyMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in)
    {
        System.out.println("read data for "+in.size()+" quakes");
        for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }
        for(int i=0; i<in.size()-1; i++)
        {
            in = onePassBubbleSort(in, i);
            System.out.println("after pass "+i);
            for (QuakeEntry qe: in) 
            { 
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes)
    {
        for(int i=0;i<quakes.size()-1; i++)
        {
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in)
    {
        int i;
        for(i=0; i<in.size()-1; i++)
        {
            if(checkInSortedOrder(in))
                break;
            in = onePassBubbleSort(in, i);
        }
        System.out.println("Passes required to sort: "+i);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in)
    {
        int i;
        for (i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in))
                break;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        System.out.println("Hello");
        System.out.println("Passes required to sort: "+i);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        
        //sortbyMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        //sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
            qe.getLocation().getLatitude(),
            qe.getLocation().getLongitude(),
            qe.getMagnitude(),
            qe.getInfo());
        }
    }
}
