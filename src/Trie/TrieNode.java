package Trie;

/**
 * Created by Chloe on 10/7/16.
 */
public class TrieNode implements ITrieNode {

    protected char letter;
    protected Boolean endOfWord;
    protected Boolean prefix;
    protected TrieNode[] children;

    public TrieNode(char letter) {
        this.letter = letter;
        endOfWord = false;
        prefix = false;
        children = new TrieNode[26];


    }

    private TrieNode getChildWithLetter(char let) {
        return children[let - 'a'];
    }

    public Boolean containsWord(String word) {
        // The current node's letter is the last letter
        // If the current node also marks the end of a word
        // the word is contained in the trie
        if (word.length() == 0 && this.endOfWord) {
            return true;
        }
        // This is the last letter in the word and the node
        // does not mark the end of the word so the word
        // is not contained in the trie
        if (word.length() == 0) {
            return false;
        }

        // There are still letters left in other word, but
        // the collection of letters so far is not a prefix
        // to any word, so the given word is not contained
        if (!this.prefix) {
            return false;
        }

        // The word may yet be in the trie, keep looking for
        // it
        char first = word.charAt(0);
        // only have lower case alphabetic characters in the trie
        if (first < 'a' || first > 'z') {
            return false; // throw exception here
        }
        String rest = word.substring(1, word.length());
        TrieNode next = getChildWithLetter(first);
        if (next != null) {
            return next.containsWord(rest);
        }
        // The next letter in the word is not a child of the
        // current node, so the given word is not in the trie
        else {
            return false;
        }

    }

    public Boolean isPrefix(String word) {
        // The letter in this Trie.TrieNode is the last letter in the potential prefix.
        if (word.length() == 0) {
            return this.prefix;
        }

        // If the first part of the potential prefix is not the prefix of a valid word,
        // the whole string is not the prefix of a valid word.
        if (!this.prefix) {
            return false;
        }
        char first = word.charAt(0);
        // only have lower case alphabetic characters in the trie
        if (first < 'a' || first > 'z') {
            return false; // throw exception here
        }
        String rest = word.substring(1, word.length());
        TrieNode next = getChildWithLetter(first);
        if (next != null) {
            return next.isPrefix(rest);
        }
        // The next letter in the word is not a child of the
        // current node, so the given word is not in the trie at all
        // so it cannot be a prefix
        else {
            return false;
        }

    }

    public void addWord(String word) {
        // We've added all the letters, mark this node (holding
        // the last letter as the end of a word
        if (word.length() == 0) {
            this.endOfWord = true;
            return;
        }

        // There are more letters to add
        char first = word.charAt(0);
        // can't add a word with special characters in it
        if (first < 'a' || first > 'z') {
            return; // throw exception here
        }
        String rest = word.substring(1, word.length());
        TrieNode next = getChildWithLetter(first);
        this.prefix = true;

        // The next letter is not already a child, so add it
        // to the current node's children
        if (null == next) {
            next = new TrieNode(first);
            this.children[first - 'a'] = next;
        }

        // Add the rest of the word to the trie
        next.addWord(rest);

    }


    @Override
    public String toString() {
        return "Trie.TrieNode{" +
                "letter=" + letter +
                ", endOfWord=" + endOfWord +
                ", prefix=" + prefix +
                ", children=\n" + this.pprintChildren() +
                '}';
    }

    private String pprintChildren() {
        // fix this
        String ret = "";
        for (int i = 0; i < 26; i++) {
            TrieNode child = this.children[i];
            if (child != null) {
                ret = ret + "\n    " + this.children[i].toString();
            }
        }
        return ret;
    }
}