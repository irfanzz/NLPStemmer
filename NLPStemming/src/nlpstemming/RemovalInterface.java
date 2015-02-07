/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

import nlpstemming.Visitor.VisitorInterface;

/**
 *
 * @author Achank89
 */
public interface RemovalInterface
{
    /**
     * @return Visitor\VisitorInterface
     */
    public VisitorInterface getVisitor();

    /**
     * @return string
     */
    public String getSubject();

    /**
     * @return string
     */
    public String getResult();

    /**
     * @return string
     */
    public String getRemovedPart();

    /**
     * @return string
     */
    public String getAffixType();
}
