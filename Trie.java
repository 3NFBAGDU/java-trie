package com.company;

import java.util.ArrayList;
import java.util.List;

public class Trie
{
    private TrieNode root;

    /**
     * Constructor
     */
    public Trie()
    {
        root = new TrieNode();
    }

    /**
     * Adds a words to the Trie
     * @param words
     */
    public void addWord(List<String> words)
    {
        root.addWord(words);
    }


    /**
     * check is contains url
     * @param words
     */
    public boolean containsUrl(List<String> words){
        TrieNode lastNode = root;
        for (int i = 0; i < words.size(); i ++)
        {
            lastNode = lastNode.getNode(words.get(i));

            //If no node matches, then no words exist, return empty list
            if (lastNode == null) {
                return false;
            }
        }

        return lastNode.isUrl();
    }

    /**
     * Get the words in the Trie with the given
     * prefix of list
     * @param prefixWords
     * @return a List containing String objects containing the words in
     *         the Trie with the given prefix.
     */
    public List getWords(List<String> prefixWords)
    {
        //Find the node which represents the last letter of the prefix
        TrieNode lastNode = root;
        for (int i = 0; i < prefixWords.size(); i ++)
        {
            lastNode = lastNode.getNode(prefixWords.get(i));

            //If no node matches, then no words exist, return empty list
            if (lastNode == null) return new ArrayList();
        }

        //Return the words which eminate from the last node
        return lastNode.getWords();
    }

    /**
     * return the number of character nodes
     * @return
     */
    public int getCharacterNodes() {
        return root.getCharacterNodes();
    }

    /**
     * return the number of word nodes
     * @return
     */
    public int getWordNodes() {
        return root.getWordNodes();
    }

    /**
     *  will return the number of leaf nodes in trie
     * @return
     */
    public int getLeafs(){
        return root.getLeafs();
    }

    /**
     * this void will get the list of all trienode words
     * @return
     */
    public List<String> getNodeWords(){

        return root.getNodeWords();
    }

}