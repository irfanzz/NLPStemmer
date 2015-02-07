/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Visitor;

import nlpstemming.ContextInterface;

/**
 *
 * @author Achank89
 */
public class DontStemShortWord implements VisitorInterface
{
    /**
     * @param string $word
     */
    protected boolean isShortWord(String word)
    {
        return (word.length() <= 3);
    }
    
    
    @Override
    public void visit(ContextInterface context)
    {
        if (this.isShortWord(context.getCurrentWord())) {
            context.stopProcess();
        }
    }


}
