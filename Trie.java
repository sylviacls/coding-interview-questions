import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * Example: Trie trie = new Trie(); 
 * trie.insert("apple"); 
 * trie.search("apple"); // returns
 * true trie.search("app"); // returns false 
 * trie.startsWith("app"); // returns true trie.insert("app"); 
 * trie.search("app"); // returns true
 * 
 * You may assume that all inputs are consist of lowercase letters a-z. All
 * inputs are guaranteed to be non-empty strings.
 */
public class Trie {
    /** Initialize your data structure here. */
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null) {
                return false;
            } 
            current = node;
        }
        return current.isEndOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null) {
                return false;
            } 
            current = node;
        }

        return true;
    }

    @Test
    public void validate() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("car");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertTrue(trie.search("app"));
        Assert.assertTrue(trie.search("car"));
        Assert.assertFalse(trie.search("ca"));
        Assert.assertTrue(trie.startsWith("ap"));
       Assert.assertTrue(trie.startsWith("ca"));

    }
}
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    public TrieNode() {
        this.isEndOfWord = false;
        children = new HashMap<Character, TrieNode>();
    }
}
 