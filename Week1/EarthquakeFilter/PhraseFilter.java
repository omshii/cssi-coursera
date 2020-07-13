
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{

    private String where, phrase, name;
    
    public PhraseFilter(String s, String p, String n)
    {
        where=s;
        phrase=p;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry q)
    {
        if(where.equalsIgnoreCase("start"))
        {
            if(q.getInfo().startsWith(phrase))
                return true;
            else
                return false;
        }
        else if(where.equalsIgnoreCase("any"))
        {
            if(q.getInfo().contains(phrase))
                return true;
            else
                return false;
        }
        else
        {
            if(q.getInfo().endsWith(phrase))
                return true;
            else
                return false;
        }
    }
    
    public String getName()
    {
        return name;
    }
}
