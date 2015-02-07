/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Disambiguator;

/**
 *
 * @author Achank89
 */
public interface DisambiguatorInterface {
     /**
     * @param word
     * @return string|null
     */
    public String disambiguate(String word);
}
