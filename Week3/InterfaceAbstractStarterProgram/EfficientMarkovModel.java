
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
    
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    HashMap<String, ArrayList<String>> map = new HashMap<>();
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        ;
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-N);
        String key=myText.substring(index, index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0)
            {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
    
    public String toString()
    {
        String ret = "EfficientMarkovModel of order "+N;
        return ret;
    }
    
    public void buildMap()
    {
        //System.out.println("BUILDING MAP");
        //System.out.println();
        //System.out.println();
        String key;
        for(int i=0; i<=myText.length()-N; i++)
        {
            key = myText.substring(i, i+N);
            //System.out.print("i = "+i+'\t'+" Key ="+key);
            if(map.containsKey(key))
                continue;
            ArrayList<String> follows=new ArrayList<String>();
            int pos=0;
            //System.out.println("Entering while loop...");
            while(pos < myText.length())
            {
                int start = myText.indexOf(key, pos);
                if(start == -1)
                {    //System.out.println("Broke due to start");
                    break;}
                if (start + key.length() > myText.length() - 1)    
                {   
                    //System.out.println("Start + keyLength > TextLength - 1");
                    //System.out.println((start + key.length())+" "+(myText.length() - 1));
                    //System.out.println(myText);
                    //System.out.println(myText.le
                    break;
                }
                String next = myText.substring(start+key.length(),start+key.length()+1);
                //System.out.println("Pos = "+pos+'\t'+" Start = "+start+'\t'+" Next = "+next);
                follows.add(next);
                //pos = start+key.length();
                pos = start+1;
                //System.out.println("New pos ="+pos);
            }
            map.put(key, follows);
        }
        printHashMapInfo();
    }
    
    public ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        follows = map.get(key);
        return follows;
    }
    
    public void printHashMapInfo()
    {
        //System.out.println(map);
        System.out.println("Number of keys: "+map.size());
        int maxSize=0;
        for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet())  
        {
            if(entry.getValue().size()>maxSize)
                maxSize=entry.getValue().size();
        }
        System.out.println("Maximum size of ArrayList: "+maxSize);
        for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet())  
        {
            //if(entry.getValue().size()==maxSize)
                //System.out.println("Key: "+entry.getKey()+"       Value:"+entry.getValue());
        }
    }
}


