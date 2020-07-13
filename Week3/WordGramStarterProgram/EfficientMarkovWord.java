/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class EfficientMarkovWord implements IMarkovModel{

    String myText[];
    Random myRandom;
    int myOrder;
    HashMap<WordGram, ArrayList<String>> map = new HashMap<>();
    
    public EfficientMarkovWord(int num) {
        myOrder = num;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        /*String key=" ";
        for(int i=0;i<myOrder;i++)
        {
            key = key+myText[index+i]+" ";
            sb.append(key);
        }
        key = key.trim();*/
        //String[] source = key.split("\\s+");
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        //System.out.println(kGram);
        for(int k=0; k < numWords; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                //System.out.println("Breaking at wordGram = "+kGram);
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //System.out.println("Next = "+next);
            sb.append(next);
            sb.append(" ");
            //System.out.println("Sb = "+sb);
            //System.out.println("kGram = "+kGram);
            //System.out.println("Follows = "+follows);
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start)
    {
        int num = 1;
        int i;
        //System.out.println("//INDEX OF "+'\n'+"  words = "+words+"   target = "+target + "start = "+start);

        for(i=start; i<=words.length-target.length(); i++)
        {
            //System.out.println("i ="+i);
            if(words[i].equals(target.wordAt(0)))
            {
                int c=i+1;
                num =1;
                for(int j=1; j<target.length(); j++)
                {
                    //System.out.println("c ="+c);
                    //System.out.println("j ="+j);
                    if(!words[c].equals(target.wordAt(j)))
                    {
                        //System.out.println("Broke here words[c] = "+words[c]+"  wordAt ="+target.wordAt(j));
                        num = -1;
                        break;
                    }
                    c++;
                }
                if(num!=-1)
                {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void buildMap()
    {
        for(int i=0; i<=myText.length-myOrder; i++)
        {
            WordGram kGram = new WordGram(myText, i, myOrder);
            if(map.containsKey(kGram))
                continue;
            ArrayList<String> follows = new ArrayList<String>();
            int pos = 0;
            while(pos < myText.length)
            {
                int start = indexOf(myText, kGram, pos);
                if(start == -1)
                {       
                    //System.out.println("no index found for "+kGram);
                    break;
                }
                if(start >= myText.length - kGram.length())
                    break;
                String next = myText[start+kGram.length()];
                follows.add(next);
                pos = start+1;
            }
            map.put(kGram, follows);
        }
        printHashMapInfo();
    }
    
    public void printHashMapInfo()
    {
        //System.out.println(map);
        System.out.println("Number of keys: "+map.size());
        int maxSize=0;
        for (HashMap.Entry<WordGram, ArrayList<String>> entry : map.entrySet())  
        {
            if(entry.getValue().size()>maxSize)
                maxSize=entry.getValue().size();
        }
        System.out.println("Maximum size of ArrayList: "+maxSize);
        for (HashMap.Entry<WordGram, ArrayList<String>> entry : map.entrySet())  
        {
            //if(entry.getValue().size()==maxSize)
                //System.out.println("Key: "+entry.getKey()+"       Value:"+entry.getValue());
        }
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        follows = map.get(kGram);
        return follows;
        /*int pos=0;
        while(pos < myText.length)
        {
            int start = indexOf(myText, kGram, pos);
            if(start == -1)
            {    
                //System.out.println("no index found for "+kGram);
                break;
            }
            if(start >= myText.length - kGram.length())
                break;
            String next = myText[start+kGram.length()];
            follows.add(next);
            pos = start+kGram.length();
        }*/
        
    }
}