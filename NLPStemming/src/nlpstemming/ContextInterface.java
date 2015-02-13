package nlpstemming;

import nlpstemming.Dictionary.DictionaryInterface;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Achank89
 */
public interface ContextInterface {
    
     /**
     * @return String
     */
    public String getOriginalWord();

    /**
     * @return void
     */
    public void setCurrentWord(String word);

    /**
     * @return string
     */
    public String getCurrentWord();

    /**
     */
    public DictionaryInterface getDictionary();

    /**
     * @return void
     */
    public void stopProcess();

    /**
     * @return boolean
     */
    public boolean processIsStopped();

    /**
     * @return void
     */
    public void addRemoval(RemovalInterface removal);

    /**
     * @return RemovalInterface[]
     */
    public ArrayList<RemovalInterface> getRemovals();
}
