import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*Filter f = new MagnitudeFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }*/
        Filter f1 = new MagnitudeFilter(3.5, 4.5, "Mag");
        ArrayList<QuakeEntry> a1 = filter(list, f1);
        Filter f2 = new DepthFilter(-55000.0, -20000.0, "Depth");
        ArrayList<QuakeEntry> a2 = filter(a1, f2);
        
        for(QuakeEntry q: a2)
        {
            System.out.println(q);
        }
        /*
        Location loc = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(loc, 1000000, "dist");
        ArrayList<QuakeEntry> a1 = filter(list, f1);
        Filter f2 = new PhraseFilter("end", "a", "phrazes");
        ArrayList<QuakeEntry> a2 = filter(a1, f2);
        
        for(QuakeEntry q: a2)
        {
            System.out.println(q);
        }*/
        
        System.out.println("number of quakes found: "+a2.size());
        
    }
    
    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        /*for(QuakeEntry q: list)
        {
            System.out.println(q);
        }*/
        System.out.println("read data for "+list.size()+" entries");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0, "Magnitude");
        Filter f2 = new DepthFilter(-180000.0, -30000.0, "Depth");
        Filter f3 = new PhraseFilter("any", "o", "Phrase");
        maf.AddFilter(f1);
        maf.AddFilter(f2);
        maf.AddFilter(f3);
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        
        for(QuakeEntry q: answer)
        {
            System.out.println(q);
        }
        
        System.out.println("The number of quakes found "+answer.size());
        System.out.println("Filters used are: "+maf.getName());
    }
    
    public void testMatchAllAFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" entries");
        
        MatchAllFilter maf = new MatchAllFilter();
        Location loc = new Location(55.7308, 9.1153); 
        Filter f1 = new MagnitudeFilter(0.0, 5.0, "Mag");
        Filter f2 = new DistanceFilter(loc, 3000000, "Dist");
        Filter f3 = new PhraseFilter("any", "e", "Phrazes");
        
        maf.AddFilter(f1);
        maf.AddFilter(f2);
        maf.AddFilter(f3);
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        
        for(QuakeEntry q: answer)
        {
            System.out.println(q);
        }
        
        System.out.println("quakes found "+answer.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
