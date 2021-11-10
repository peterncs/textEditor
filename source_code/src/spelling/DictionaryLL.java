package spelling;

import java.util.LinkedList;

//import textgen.LLNode;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	private int size;
	
    public DictionaryLL()
    {
    	this.dict = new LinkedList<String>();
    	this.size = 0;
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	if (this.dict.contains(word.toLowerCase())) {
    		return false;
    	}
        this.dict.add(word.toLowerCase());
        this.size += 1;
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return this.size;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if (this.dict.contains(s.toLowerCase())) {
    		return true;
    	}
        return false;
    }
}