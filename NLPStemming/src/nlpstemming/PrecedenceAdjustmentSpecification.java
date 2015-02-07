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
public class PrecedenceAdjustmentSpecification implements SpecificationInterface
{
    /**
     * @return boolean
     */
    @Override
    public boolean isSatisfiedBy(String value)
    {
        String[] regexRules = {
            "^be(.*)lah$",
            "^be(.*)an$",
            "^me(.*)i$",
            "^di(.*)i$",
            "^pe(.*)i$",
            "^ter(.*)i$"
        };

        for (String rule : regexRules) {
            if (tools.pregMatch(rule, value)) {
                return true;
            }
        }

        return false;
    }
}