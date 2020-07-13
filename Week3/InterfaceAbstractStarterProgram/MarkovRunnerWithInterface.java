
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkovZero() 
    { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovZero markov = new MarkovZero(); 
        markov.setTraining(st); 
        markov.setRandom(1024);
        for(int k=0; k < 3; k++) 
        {    
            String text = markov.getRandomText(500); 
            printOut(text); 
        } 
    }

    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 32);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 32);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 32);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 32);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void testHashMap()
    {
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(markov, st, 50, 531);
    }
    
    public void compareMethods()
    {
        EfficientMarkovModel Emarkov = new EfficientMarkovModel(2);
        MarkovModel Mmarkov = new MarkovModel(2);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        long MStartTime = System.nanoTime();
        runModel(Mmarkov, st, 1000, 42);
        long MEndTime = System.nanoTime();
        long EStartTime = System.nanoTime();
        runModel(Emarkov, st, 1000, 42);
        long EEndTime = System.nanoTime();
        System.out.println();
        System.out.println("Markov time: "+(MEndTime-MStartTime));
        System.out.println("EMarkov time: "+(EEndTime-EStartTime));
    }
}
