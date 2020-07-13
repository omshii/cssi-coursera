
/**
 * Write a description of TitleAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        String last1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" "));
        String last2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" "));
        int ret = last1.compareTo(last2);
        if(ret==0)
        {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return ret;
    }
}
