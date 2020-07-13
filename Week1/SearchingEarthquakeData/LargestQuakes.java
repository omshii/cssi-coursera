
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    
    void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        /*for(QuakeEntry q: list)
        {
            System.out.println(q);
        }
        int index=indexOfLargest(list);
        System.out.println("location ="+index+" "+list.get(index));*/
        
        ArrayList<QuakeEntry> answer = getLargest(list,50);
        for(QuakeEntry q: answer)
        {
            System.out.println(q);
        }
        System.out.println("read data for "+list.size()+" quakes");
        
    }
    
    int indexOfLargest(ArrayList<QuakeEntry> data)
    {
        double mag=data.get(0).getMagnitude();
        int ret=0;
        for(int i=1;i<data.size();i++)
        {
            if(data.get(i).getMagnitude()>mag)
            {
                mag=data.get(i).getMagnitude();
                ret=i;
            }
        }
        return ret;
    }
    
    ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        int ind;
        for(int i=0;i<howMany && i<quakeData.size();i++)
        {
            ind=indexOfLargest(copy);
            ret.add(copy.get(ind));
            copy.remove(ind);
        }
        return ret;
    }
}
