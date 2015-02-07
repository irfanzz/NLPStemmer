/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

import nlpstemming.Dictionary.DictionaryInterface;
import java.util.ArrayList;
import nlpstemming.Visitor.VisitorProvider;

/**
 *
 * @author Achank89
 */
public class Stemmer implements StemmerInterface
{
    /**
     * The dictionary containing root words
     *
     * @var \Sastrawi\Dictionary\DictionaryInterface
     */
    protected DictionaryInterface dictionary;

    /**
     * Visitor provider
     *
     * @var \Sastrawi\Stemmer\Context\Visitor\VisitorProvider
     */
    protected VisitorProvider visitorProvider;

    public Stemmer(DictionaryInterface dictionary)
    {
        this.dictionary = dictionary;
        this.visitorProvider = new VisitorProvider();
    }

    
    String normalizeText(String text2normalize){
        
        text2normalize = text2normalize.toLowerCase();

        
        text2normalize = text2normalize.replaceAll("[^a-z0-9 -]", " ");
        text2normalize = text2normalize.replaceAll("( +)", " ");
                
        return text2normalize.trim();
    } 
    
    @Override
    public String stem(String normalizedText){
    
        String[] words = normalizedText.toLowerCase().split(" ");
        
        ArrayList<String> stems = new ArrayList<>() ;
        
        for (String word : words) {
              stems.add(this.stemWord(word));
        }
        
        String Return = "";

        Return = stems.stream().map((s) -> s + " ").reduce(Return, String::concat);

        return Return;
        
    }
    
    String stemWord(String word){
    
        if (this.isPlural(word)) {
            return this.stemPluralWord(word);
        } else {
            return this.stemSingularWord(word);
        }
    }
    
    boolean isPlural(String word){
    
        return word.contains("-");
    }
    
    String stemSingularWord(String word){
    
        word = word.toLowerCase();
        
        switch (word) {
            case "belajar":
                return "ajar";
            case "pelajar":
                return "ajar";
        }
        
        Context context = new Context(word, this.dictionary, this.visitorProvider);
        context.execute();

        return context.getResult();
        
    }
    
    String stemPluralWord(String plural){
     
        ArrayList<String> words = tools.pregMatchExtract("^(.*)-(.*)", plural);

        if (words.size() < 2 ) {
            return plural;
        }

        String rootWord1 = this.stemSingularWord(words.get(0));
        String rootWord2 = this.stemSingularWord(words.get(1));

        if (rootWord1.equals(rootWord2) ) {
            return rootWord1;
        } else {
            return plural;
        }
       
    }
    
}
