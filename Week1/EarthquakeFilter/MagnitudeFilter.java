
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{

    private double minMag, maxMag;
    private String name;
    public MagnitudeFilter(double min, double max, String n)
    {
        minMag=min;
        maxMag=max;
        name = n;
    }
    public boolean satisfies(QuakeEntry q)
    {
        if((q.getMagnitude()>=minMag)&&(q.getMagnitude()<=maxMag))
            return true;
        else
            return false;
    }
    public String getName()
    {
        return name;
    }
}
