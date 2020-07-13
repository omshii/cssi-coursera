
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(int i=0;i<myWords.length; i++)
        {
            ret = ret.concat(myWords[i]);
            ret = ret.concat(" ");
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length())
            return false;
        for(int i=0;i<myWords.length;i++)
        {
            if(!myWords[i].equals(other.myWords[i]))
                return false;
        }
        // TODO: Complete this method
        return true;

    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        for(int i=0; i<myWords.length-1; i++)
        {
            out.myWords[i] = out.myWords[i+1];
        }
        out.myWords[myWords.length-1]=word;
        return out;
    }

    public int hashCode()
    {
        String hash = toString();
        return hash.hashCode();
    }
}