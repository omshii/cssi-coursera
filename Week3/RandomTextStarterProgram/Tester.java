
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    
    public void testGetFollows()
    {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> prin = markov.getFollows("t");
        System.out.println(prin);
        prin = markov.getFollows("e");
        System.out.println(prin);
        prin = markov.getFollows("es");
        System.out.println(prin);
        prin = markov.getFollows(".");
        System.out.println(prin);
        prin = markov.getFollows("estg");
        System.out.println(prin);
    }

    public void testGetFollowesWithFile()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> prin = markov.getFollows("he");
        System.out.println(prin.size());
    }
}
