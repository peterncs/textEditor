package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if (sourceText == null) {
			throw new NullPointerException("Cannot train null input");
		}
		
		if (wordList.size()>0) { return; } // Comment to keep training
		
		String[] words = sourceText.split(" +");
		starter = words[0];
		String prevWord = starter;
		int i;
		for (i = 1; i < words.length; i++) {
			if (!AddExistWord(prevWord, words[i])) {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(words[i]);
				wordList.add(newNode);
			}
			prevWord = words[i];
		}
		
		// Handle last word
		if (!AddExistWord(prevWord, starter)) {
			ListNode newNode = new ListNode(prevWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		}
	}
	
	private boolean AddExistWord (String prevWord, String currWord) {
		for (ListNode n : wordList)
		{
			if (n.getWord().equals(prevWord)) {
				n.addNextWord(currWord);
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (numWords == 0) {
			return "";
		}
		
		String currWord = starter;
		String nextWord = "";
		StringBuilder output = new StringBuilder();
		output.append(starter);
		
		for (int i = 0; i < numWords-1; i++) {
			for (ListNode n: wordList) {
				if (n.getWord().equals(currWord)) {
					nextWord = n.getRandomNextWord(rnGenerator);
					break;
				}
			}
			output.append(" ");
			output.append(nextWord);
			currWord = nextWord;
		}
		return output.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		
		
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		String textString4 = "You say yes, I say no,";
		System.out.println(textString);
		gen.train(textString);
		//System.out.println(gen);
		//gen.train(textString4);
		//System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		//System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String textString3 = "hi hi hi hi hi hi hi hello hi hi hi hi hi hello hi";
		System.out.println(textString3);
		gen.retrain(textString3);
		System.out.println(gen.generateText(5));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


