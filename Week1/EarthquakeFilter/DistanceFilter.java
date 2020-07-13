
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{

    private Location loc;
    private double maxDist;
    private String name;
    
    public DistanceFilter(Location l, double d, String n)
    {
        loc=l;
        maxDist=d;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry q)
    {
        if(q.getLocation().distanceTo(loc)<maxDist)
            return true;
        else
            return false;
    }
    
    public String getName()
    {
        return name;
    }
}
