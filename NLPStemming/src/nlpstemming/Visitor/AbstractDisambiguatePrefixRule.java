/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Visitor;

import java.util.ArrayList;
import nlpstemming.ContextInterface;
import nlpstemming.Disambiguator.DisambiguatorInterface;
import nlpstemming.Removal;

/**
 *
 * @author Achank89
 */
public abstract class AbstractDisambiguatePrefixRule implements VisitorInterface
{
    protected ArrayList<DisambiguatorInterface> disambiguators = new ArrayList<>();
            
    @Override
    public void visit(ContextInterface context)
    {
        String result = null;

        for( DisambiguatorInterface disambiguator : this.disambiguators){
            result = disambiguator.disambiguate(context.getCurrentWord());

            if (context.getDictionary().contains(result)) {
                break;
            }
        }

        if (result == null) {
            return;
        }

         String removedPart = context.getCurrentWord().replaceFirst(result, "");

         Removal removal = new Removal(
                this,
                context.getCurrentWord(),
                result,
                removedPart,
                "DP"
            );
        context.addRemoval(removal);
        context.setCurrentWord(result);
    }

    public void addDisambiguators(ArrayList<DisambiguatorInterface>  disambiguators)
    {
        for(DisambiguatorInterface disambiguator  : disambiguators){
          this.addDisambiguator(disambiguator);
        }
    }

    public void addDisambiguator(DisambiguatorInterface disambiguator)
    {
        this.disambiguators.add(disambiguator);
    }
}
