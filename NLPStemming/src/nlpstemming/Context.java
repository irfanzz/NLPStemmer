/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming;

import nlpstemming.Dictionary.DictionaryInterface;
import java.util.ArrayList;
import java.util.Collections;
import nlpstemming.Visitor.VisitorInterface;
import nlpstemming.Visitor.VisitorProvider;

/**
 *
 * @author Achank89
 */
public class Context implements ContextInterface {

    /**
     * @var string
     */
    protected String originalWord;

    /**
     * @var string
     */
    protected String currentWord;

    /**
     * @var boolean
     */
    protected Boolean processIsStopped = false;

    /**
     */
    protected ArrayList<RemovalInterface> removals = new ArrayList<>();

    /**
     */
    protected DictionaryInterface dictionary;

    /**
     */
    protected VisitorProvider visitorProvider;

    /**
     */
    protected ArrayList<VisitorInterface> visitors = new ArrayList<>();

    /**
     */
    protected ArrayList<VisitorInterface> suffixVisitors = new ArrayList<>();

    /**
     */
    protected ArrayList<VisitorInterface> prefixVisitors = new ArrayList<>();

    /**
     * @var string
     */
    protected String result;

    Context(String originalWord, DictionaryInterface dictionary, VisitorProvider visitorProvider) {

        this.originalWord = originalWord;
        this.currentWord = originalWord;
        this.dictionary = dictionary;
        this.visitorProvider = visitorProvider;

        this.initVisitors();

    }

    @Override
    public String getCurrentWord() {
        return this.currentWord;
    }

    void initVisitors() {
        this.visitors = this.visitorProvider.getVisitors();
        this.suffixVisitors = this.visitorProvider.getSuffixVisitors();
        this.prefixVisitors = this.visitorProvider.getPrefixVisitors();
    }

    void execute() {
        // step 1 - 5
        this.startStemmingProcess();

        // step 6
        if (this.dictionary.contains(this.getCurrentWord())) {
            this.result = this.getCurrentWord();
        } else {
            this.result = this.originalWord;
        }
    }

    void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }

    protected String acceptVisitors(ArrayList<VisitorInterface> visitors) {
        for (VisitorInterface visitor : visitors) {
            this.accept(visitor);

            if (this.getDictionary().contains(this.getCurrentWord())) {
                return this.getCurrentWord();
            }

            if (this.processIsStopped()) {
                return this.getCurrentWord();
            }
        }
        return "";
    }

    protected String acceptPrefixVisitors(ArrayList<VisitorInterface> visitors) {
        int removalCount = this.removals.size();
        for (VisitorInterface visitor : visitors) {
            this.accept(visitor);

            if (this.getDictionary().contains(this.getCurrentWord())) {
                return this.getCurrentWord();
            }

            if (this.processIsStopped()) {
                return this.getCurrentWord();
            }

            if (this.removals.size() > removalCount) {
                return null;
            }
        }

        return "";
    }

    protected void removePrefixes() {
        for (int i = 0; i < 3; i++) {
            this.acceptPrefixVisitors(this.prefixVisitors);
            if (this.dictionary.contains(this.getCurrentWord())) {
                return;
            }
        }
    }

    protected void removeSuffixes() {
        this.acceptVisitors(this.suffixVisitors);
    }

    /**
     * @return void
     */
    protected void startStemmingProcess() {
        // step 1
        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        this.acceptVisitors(this.visitors);

        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        PrecedenceAdjustmentSpecification csPrecedenceAdjustmentSpecification = new PrecedenceAdjustmentSpecification();

        /*
         * Confix Stripping
         * Try to remove prefix before suffix if the specification is met
         */
        if (csPrecedenceAdjustmentSpecification.isSatisfiedBy(this.getOriginalWord())) {
            // step 4, 5
            this.removePrefixes();
            if (this.dictionary.contains(this.getCurrentWord())) {
                return;
            }

            // step 2, 3
            this.removeSuffixes();
            if (this.dictionary.contains(this.getCurrentWord())) {
                return;
            } else {
                // if the trial is failed, restore the original word
                // and continue to normal rule precedence (suffix first, prefix afterwards)
                this.setCurrentWord(this.originalWord);
                this.removals = new ArrayList<>();
            }
        }

        // step 2, 3
        this.removeSuffixes();
        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        // step 4, 5
        this.removePrefixes();
        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        // ECS loop pengembalian akhiran
        this.loopPengembalianAkhiran();
    }

    public String getResult() {
        return this.result;
    }

    /**
     * Check wether the removed part is a suffix
     *
     * @return boolean
     */
    protected boolean isSuffixRemoval(RemovalInterface removal) {
        return removal.getAffixType().equalsIgnoreCase("DS")
                || removal.getAffixType().equalsIgnoreCase("PP")
                || removal.getAffixType().equalsIgnoreCase("P");
    }

    /**
     * Restore prefix to proceed with ECS loop pengembalian akhiran
     *
     * @return void
     */
    public void restorePrefix() {
        for (RemovalInterface removal : this.removals) {
            if (removal.getAffixType().equalsIgnoreCase("DP")) {
                // return the word before precoding (the subject of first prefix removal)
                this.setCurrentWord(removal.getSubject());
                break;
            }
        }

        for (int i = 0; i < this.removals.size(); i++) {
            RemovalInterface removal = removals.get(i);
            if (removal.getAffixType().equalsIgnoreCase("DP")) {

                this.removals.remove(i);
            }
        }
    }

    /**
     * ECS Loop Pengembalian Akhiran
     */
    public void loopPengembalianAkhiran() {
        // restore prefix to form [DP+[DP+[DP]]] + Root word
        this.restorePrefix();

        ArrayList<RemovalInterface> removals = this.removals;

        ArrayList<RemovalInterface> reversedRemovals = new ArrayList<RemovalInterface>(removals);
        Collections.reverse(reversedRemovals);
        String currentWord = this.getCurrentWord();

        for (RemovalInterface removal : reversedRemovals) {
            if (!this.isSuffixRemoval(removal)) {
                continue;
            }

            if (removal.getRemovedPart().equalsIgnoreCase("kan")) {
                this.setCurrentWord(removal.getResult() + 'k');

                // step 4, 5
                this.removePrefixes();
                if (this.dictionary.contains(this.getCurrentWord())) {
                    return;
                }

                this.setCurrentWord(removal.getResult() + "kan");
            } else {
                this.setCurrentWord(removal.getSubject());
            }

            // step 4, 5
            this.removePrefixes();
            if (this.dictionary.contains(this.getCurrentWord())) {
                return;
            }

            this.removals = removals;
            this.setCurrentWord(currentWord);
        }
    }

    @Override
    public String getOriginalWord() {
        return this.originalWord;
    }

    @Override
    public void setCurrentWord(String word) {
        this.currentWord = word;
    }

    @Override
    public DictionaryInterface getDictionary() {
        return this.dictionary;
    }

    @Override
    public void stopProcess() {
        this.processIsStopped = true;
    }

    @Override
    public boolean processIsStopped() {
        return this.processIsStopped;
    }

    @Override
    public void addRemoval(RemovalInterface removal) {
        this.removals.add(removal);
    }

    @Override
    public ArrayList<RemovalInterface> getRemovals() {
        return this.removals;
    }
}
