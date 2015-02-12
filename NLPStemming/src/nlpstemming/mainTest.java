/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

import java.io.FileNotFoundException;
import nlpstemming.Dictionary.ArrayDictionary;

/**
 *
 * @author Achank89
 */
public class mainTest {
    
    public static void main(String[] args) throws FileNotFoundException {
    
        String[] words = tools.getWordsFromFile();
        ArrayDictionary dictionary = new ArrayDictionary(words);
        Stemmer stemmer    = new Stemmer(dictionary);

       System.out.println(stemmer.stem("Apakah"));
    }
}
