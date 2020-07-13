
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    
    private double minDepth, maxDepth;
    private String name;
    
    public DepthFilter(double min, double max, String n)
    {
        minDepth=min;
        maxDepth=max;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry q)
    {
        if((q.getDepth()>=minDepth)&&(q.getDepth()<=maxDepth))
            return true;
        else
            return false;
    }
    
    public String getName()
    {
        return name;
    }
}
