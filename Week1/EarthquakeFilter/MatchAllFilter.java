
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter{

    private ArrayList<Filter> filter=new ArrayList<Filter>();
    
    void AddFilter(Filter fil)
    {
        filter.add(fil);
    }
    public boolean satisfies(QuakeEntry q)
    {
        for(Filter f: filter)
        {
            if(!f.satisfies(q))
                return false;
        }
        return true;
    }
    public String getName()
    {
        String name="";
        for(Filter f: filter)
        {
            name=name+f.getName()+" ";
        }
        return name;
    }
}
