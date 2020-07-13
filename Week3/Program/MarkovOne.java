
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key)
    {
        int len = key.length();
        int i=0, pos;
        char ch;
        ArrayList<String> follows=new ArrayList<String>();
        while(i<myText.length()-len)
        {
            pos=myText.indexOf(key, i);
            if((pos==-1)||(pos==len-1))
                break;
            ch = myText.charAt(pos+len);
            follows.add(Character.toString(ch));
            i = i + pos + len;
        }
        return follows;
    }    
}

