import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) 
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(where.equalsIgnoreCase("start"))
        {
            for(int i=0;i<quakeData.size();i++)
            {
                if(quakeData.get(i).getInfo().startsWith(phrase))
                {
                    answer.add(quakeData.get(i));
                }
            }
        }
        else if(where.equalsIgnoreCase("any"))
        {
            for(int i=0;i<quakeData.size();i++)
            {
                if(quakeData.get(i).getInfo().contains(phrase))
                {
                    answer.add(quakeData.get(i));
                }
            }
        }
        else
        {
            for(int i=0;i<quakeData.size();i++)
            {
                if(quakeData.get(i).getInfo().endsWith(phrase))
                {
                    answer.add(quakeData.get(i));
                }
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(int i=0;i<quakeData.size();i++)
        {
            double depth=quakeData.get(i).getDepth();
            if((depth>minDepth)&&(depth<maxDepth))
            {
                answer.add(quakeData.get(i));
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) 
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int i=0;i<quakeData.size();i++)
        {
            if(quakeData.get(i).getMagnitude()>magMin)
            {
                answer.add(quakeData.get(i));
            }
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int i=0;i<quakeData.size();i++)
        {
           if(quakeData.get(i).getLocation().distanceTo(from) < distMax)
           {
               answer.add(quakeData.get(i));
           }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() 
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList <QuakeEntry> result = filterByMagnitude(list, 5.0);
        for (QuakeEntry q : result) {
            System.out.println(q);
        }
        
        System.out.println("Found "+result.size()+" quakes that match that criteria");
        
        

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> result = filterByDistanceFrom(list, 1000000, city);
        
        for(QuakeEntry q:result)
        {
            System.out.println(q.getLocation().distanceTo(city)/1000+" "+q.getInfo());
        }
        System.out.println("Found "+result.size()+" quakes that match that criteria");
    }
    
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList <QuakeEntry> result = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry q : result) {
            System.out.println(q);
        }
        
        System.out.println("Found "+result.size()+" quakes that match that criteria");
        
    }
    
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList <QuakeEntry> result = filterByPhrase(list,"any","Can");
        for (QuakeEntry q : result) {
            System.out.println(q);
        }
        
        System.out.println("Found "+result.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
