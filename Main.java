package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String testFileLocation = "C:\\Users\\gbagh\\OneDrive\\Desktop\\ML\\TestFile.txt";
    private static String outputFileLocation = "c:\\Users\\triewords";

    // will split the url and get the words
    private static List<String> parseUrl(String url){
        if (url.equals(""))
            return null;

        String [] words = url.split("/");
        if (words.length == 0)
            return null;

        List <String> urlWords = new ArrayList<String>();
        urlWords.add("http");
        for (int i = 1; i < words.length - 1; ++ i){
            if (words[i].equals(""))
                continue;

            urlWords.add(words[i]);
        }

        String lastWord = words[words.length - 1];
        for (int i = 0; i < lastWord.length(); ++ i){
            urlWords.add(lastWord.substring(i, i + 1));
        }

        return urlWords;
    }

    public static void main(String[] args) {

        //create the Trie class
        Trie trie = new Trie();

	    // write your code here
        BufferedReader reader;
        int cnt = 0;
        try {
            reader = new BufferedReader(new FileReader(testFileLocation));

            String line = reader.readLine();

            while (line != null) {
                // get the list of word by url
                List<String> wordsList = parseUrl(line);
                // add trie our non nul line
                if (wordsList != null) {
                    if (!trie.containsUrl(wordsList)){
                        trie.addWord(wordsList);
                        ++cnt;
                    }
                }

                // read next line
                line = reader.readLine();
            }
            reader.close();

            List<String> urls = trie.getWords(new ArrayList<String>());
            System.out.println("the count number of URL inserted in trie " + urls.size());
            System.out.println("Number of leaf Node = " + trie.getLeafs());
            System.out.println("Number of Character Node = " + trie.getCharacterNodes());
            System.out.println("Number of Word Node = " + trie.getWordNodes());


            double inBytes = getFileSize(testFileLocation);


            System.out.println("size of file in bytes = " + inBytes);

            List<String> trieNodeWords = trie.getNodeWords();
            writeTrieNodeInFile(trieNodeWords);


            double outFileSize = getFileSize(outputFileLocation);

            System.out.println("size of trie in bytes = " + outFileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static double getFileSize(String name){
        File file =new File(name);

        double bytes = 0;
        if (file.exists()){
            bytes = file.length();
        }

        return  bytes;
    }

    public static void writeTrieNodeInFile(List<String> trieNodeWords){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileLocation));
            for (String word : trieNodeWords){
                writer.write(word + '\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
