/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import nlpstemming.Dictionary.ArrayDictionary;

/**
 *
 * @author Achank89
 */
public class mainTest {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> words = tools.getWordsFromFile();
        System.out.println("lengt words " + words.size());
        ArrayDictionary dictionary = new ArrayDictionary(words);
        ArrayList<String> wordList = dictionary.getWords();
        System.out.println("dictionary length " + wordList.size());
        System.out.println("is same " + "aba".equals("aba"));
        System.out.println("word 0 " + wordList.get(0));
        System.out.println("word 0 " + wordList.get(0).equals("aba"));
        System.out.println("contains aba " + wordList.contains("aba"));
        System.out.println("contains aba " + dictionary.contains("aba"));
        Stemmer stemmer = new Stemmer(dictionary);

        System.out.println(stemmer.stem("berubah"));
    }
}
