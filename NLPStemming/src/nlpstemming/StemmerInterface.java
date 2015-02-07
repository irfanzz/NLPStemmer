/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

/**
 *
 * @author Achank89
 */
public  interface StemmerInterface
{
    /**
     * Stem a text to its common stem form
     *
     *
     * @param  string $text the text string to stem, e.g : memberdayakan pembangunan
     * @return string common stem form, e.g : daya bangun
     */
    public String stem(String string);
}
