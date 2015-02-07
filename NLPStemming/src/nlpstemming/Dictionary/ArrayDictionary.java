/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Achank89
 */
public class ArrayDictionary implements DictionaryInterface
{
    /**
     * @var string[]
     */

    protected HashMap<String, Integer> words = new HashMap<>();
            
    
    public ArrayDictionary(ArrayList<String> words)
    {
        this.addWords(words);
    }
    
    
    public ArrayDictionary(String[] words)
    {
        this.addWords( new ArrayList<String>(Arrays.asList(words)) );
    }


    /**
     * {@inheritdoc}
     * @param word
     * @return 
     */
    @Override
    public boolean contains(String word)
    {
        return words.containsKey(word);
    }

    /**
     * {@inheritdoc}
     * @return 
     */
    public int count()
    {
        return this.words.size();
    }

    /**
     * Add multiple words to the dictionary
     *
     * @param words
     */
    public void addWords(ArrayList<String> words)
    {
        for (String string : words) {
            this.add(string);
        }
    }

    /**
     * Add a word to the dictionary
     *
     * @param word
     */
    public void add(String word)
    {
        if (word.isEmpty()) {
            return;
        }

        this.words.put(word, 1);
    }
}
