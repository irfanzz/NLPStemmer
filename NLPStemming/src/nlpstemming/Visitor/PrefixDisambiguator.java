/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import nlpstemming.Disambiguator.DisambiguatorInterface;

/**
 *
 * @author Achank89
 */
public class PrefixDisambiguator extends AbstractDisambiguatePrefixRule implements VisitorInterface
{
    public PrefixDisambiguator(ArrayList<DisambiguatorInterface> disambiguators)
    {
        this.addDisambiguators(disambiguators);
    }
    
    
    public PrefixDisambiguator(DisambiguatorInterface[] disambiguators)
    {
        this.addDisambiguators(
        new ArrayList<>(Arrays.asList(disambiguators))
        );
    }
    
    
    
    public PrefixDisambiguator(DisambiguatorInterface disambiguators)
    {
        this.addDisambiguator(
        disambiguators
        );
    }
}
