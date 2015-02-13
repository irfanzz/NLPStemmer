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
public class Removal implements RemovalInterface
{
    /**
     */
    protected VisitorInterface visitor;

    /**
     * @var string
     */
    protected String subject;

    /**
     * @var string
     */
    protected String  result;

    /**
     * @var string
     */
    protected String  removedPart;

    /**
     * @var string
     */
    protected String  affixType;

    /**
     * @param visitor
     * @param affixType
     * @param result
     * @param removedPart
     * @param subject
     */
    public Removal(
        VisitorInterface visitor,
        String subject,
        String result,
        String removedPart,
        String affixType
    ) {
        this.visitor = visitor;
        this.subject = subject;
        this.result  = result;
        this.removedPart = removedPart;
        this.affixType = affixType;
    }

    public VisitorInterface  getVisitor()
    {
        return this.visitor;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public String getResult()
    {
        return this.result;
    }

    public String getRemovedPart()
    {
        return this.removedPart;
    }

    public String getAffixType()
    {
        return this.affixType;
    }
}