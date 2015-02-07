/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Visitor;

import nlpstemming.ContextInterface;
import nlpstemming.Removal;
/**
 *
 * @author Achank89
 */
public class RemoveInflectionalPossessivePronoun implements VisitorInterface
{
    @Override
    public void visit(ContextInterface context)
    {
        String result = this.remove(context.getCurrentWord());

        if (result != context.getCurrentWord()) {
            String removedPart = context.getCurrentWord().replaceFirst(result, "");
           

            Removal removal = new Removal(
                this,
                context.getCurrentWord(),
                result,
                removedPart,
                "PP"
            );

            context.addRemoval(removal);
            context.setCurrentWord(result);
        }
    }

    /**
     * Remove inflectional particle : ku|mu|nya
     * @param word
     * @return 
     */
    public String remove(String word)
    {
        return word.replaceFirst("(ku|mu|nya)$", "");
    }
}
