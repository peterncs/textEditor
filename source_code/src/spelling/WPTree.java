/**
 * 
 */
package spelling;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * 
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	private static final int THRESHOLD = 100; 
	
	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree () {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	//This constructor will be used by the grader code
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	// see method description in WordPath interface
	public List<String> findPath(String word1, String word2) 
	{
		if(!nw.dict.isWord(word2)) {
			return null;
		}
		
		List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		HashSet<String> visited = new HashSet<String>();
		
		root = new WPTreeNode(word1, null);
		WPTreeNode currNode = root;
		String currWord = currNode.getWord();
		queue.add(currNode);
		visited.add(currWord);
		
		while (queue.size() > 0 && !currWord.equals(word2)) {
			currNode = queue.remove(0);
			currWord = currNode.getWord();
			List<String> nwList = nw.distanceOne(currWord, true);
			for (String word: nwList) {
				if (!visited.contains(word)) {
					visited.add(word);
					WPTreeNode child = currNode.addChild(word);
					queue.add(child);
					if (word.equals(word2)){
						return child.buildPathToRoot();
					}
				}
			}
			//System.out.println(currNode.getWord());
			//System.out.println(printQueue(currNode.getChildren()));
		} 
		return null;
		
		/*
		while (queue.size() > 0 && !currWord.equals(word2)) {
			currNode = queue.remove(0);
			currWord = currNode.getWord();
			
			if (!visited.contains(currWord)) {
				visited.add(currWord);
				List<String> nwList = nw.distanceOne(currWord, true);
				for (String word: nwList) {
					queue.add(currNode.addChild(word));
				}
			}
			//System.out.println(currNode.getWord());
			//System.out.println(printQueue(currNode.getChildren()));
		}
		if (!currWord.equals(word2)) {
			return null;
		}
	    return currNode.buildPathToRoot();
	    */
	}
	
	// Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
	public static void main(String[] args) {
    	WPTree wpt = new WPTree();
    	System.out.println(wpt.findPath("broom", "mop"));
    }
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /** Construct a node with the word w and the parent p
     *  (pass a null parent to construct the root)  
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child of a node containing the String s
     *  precondition: The word is not already a child of this node
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get the list of children of the calling object
     *  (pass a null parent to construct the root)  
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Allows you to build a path from the root node to 
     *  the calling object
     * @return The list of strings starting at the root and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get the word for the calling object
     *
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /** toString method
    *
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }
}

