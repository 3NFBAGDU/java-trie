package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode
{
    private TrieNode parent;
    private HashMap<String, TrieNode> children;
    private boolean isLeaf;     //Quick way to check if any children exist
    private boolean isUrl;     //Does this node represent the last character of a word
    private String word;     //The character this node represents

    /**
     * Constructor for top level root node.
     */
    public TrieNode()
    {
        children = new HashMap<>();
        isLeaf = true;
        isUrl = false;
    }

    /**
     * Constructor for child node.
     */
    public TrieNode(String word)
    {
        this();
        this.word = word;
    }

    /**
     * checks if node is leaf
     * @return
     */
    public boolean isLeaf(){
        return isLeaf;
    }

    /**
     * checks if node is url
     * @return
     */
    public boolean isUrl(){
        return isUrl;
    }

    /**
     * Adds a word to this node. This method is called recursively and
     * adds child nodes for each successive letter in the word, therefore
     * recursive calls will be made with partial words.
     * @param words the word to add
     */
    protected void addWord(List<String> words)
    {
        isLeaf = false;
        String word = words.get(0);

        if (!children.containsKey(word))
        {
            children.put(word, new TrieNode(word));
            children.get(word).parent = this;
        }

        if (words.size() > 1)
        {
            children.get(word).addWord(words.subList(1, words.size()));
        }
        else
        {
            children.get(word).isUrl = true;
        }
    }

    /**
     * Returns the child TrieNode representing the given char,
     * or null if no node exists.
     * @param word
     * @return
     */
    protected TrieNode getNode(String word)
    {
        return children.get(word);
    }

    /**
     * Returns a List of String objects which are lower in the
     * hierarchy that this node.
     * @return
     */
    protected List getWords()
    {
        //Create a list to return
        List list = new ArrayList();

        //If this node represents a word, add it
        if (isUrl)
        {
            list.add(toString());
        }

        //If any children
        if (!isLeaf)
        {
            //Add any words belonging to any children
            for (String word : children.keySet())
            {
                    list.addAll(children.get(word).getWords());

            }

        }

        return list;

    }

    /**
     * this void will return number of leafs
      * @return
     */
    public int getLeafs(){
        int cnt = 0;
        if (!isLeaf)
        {
            for (String word : children.keySet())
            {
               cnt += children.get(word).getLeafs();
            }

        }
        else{
            return 1;
        }
        return cnt;
    }


    /**
     * return the number of word nodes
     * @return
     */
    public int getWordNodes() {
        int cnt = 0;

        if (word != null && word.length() > 1){
            ++ cnt;
        }

        for (String word : children.keySet())
        {
            cnt += children.get(word).getWordNodes();
        }

        return cnt;
    }

    /**
     * return the number of character nodes
     * @return
     */
    public int getCharacterNodes(){
        int cnt = 0;

        if (word != null && word.length() == 1){
            ++ cnt;
        }

        for (String word : children.keySet())
        {
            cnt += children.get(word).getCharacterNodes();
        }

        return cnt;
    }

    /**
     * return the number of urls
     * @return
     */
    public List<String> getNodeWords(){
        List<String> words = new ArrayList<String>();
        if (word != null)
            words.add(word);

        if (!isLeaf)
        {
            for (String word : children.keySet())
            {
                words.addAll(children.get(word).getNodeWords());
            }

        }
        return words;
    }

    /**

     * Gets the String that this node represents.

     * For example, if this node represents the character t, whose parent

     * represents the charater a, whose parent represents the character

     * c, then the String would be "cat".

     * @return

     */

    public String toString()

    {

        if (parent == null)

        {

            return "";

        }

        else

        {

            return parent.toString() + ' ' +word;

        }

    }

}