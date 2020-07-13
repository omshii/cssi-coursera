
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 50, 65); 
    } 
    
    public void testGetFollows()
    {
        String st = "this is a test yes a test a really nice short test that is test yes the best";
        MarkovWord markovWord = new MarkovWord(3); 
        String source[] = {"is"};
        WordGram wg = new WordGram(source, 0, 1);
        markovWord.setTraining(st);
        markovWord.setRandom(50);
        //System.out.println(markovWord.getFollows(wg));
        System.out.println(markovWord.getRandomText(30));
    }
    
    public void testHashMap()
    {
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        String st = "this is a test yes this is really a test yes a test this is wow";
        runModel(markov, st, 50, 42);
    }
    
    public void compareMethods()
    {
        MarkovWord markov1 = new MarkovWord(3);
        EfficientMarkovWord markov2 = new EfficientMarkovWord(3);
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        long s1 = System.nanoTime();
        runModel(markov1, st, 50, 42);
        long e1 = System.nanoTime();
        long s2 = System.nanoTime();
        runModel(markov2, st, 50, 42);
        long e2 = System.nanoTime();
        System.out.println();
        System.out.println("E/M : "+(e2-s2)+"/"+(e1-s1));
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

}
