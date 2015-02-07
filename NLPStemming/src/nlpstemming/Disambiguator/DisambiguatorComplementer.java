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
public class DisambiguatorComplementer {
    
    String pattern = "";
    int position = 0;
    
    DisambiguatorComplementer(String pattern,int position){
        this.pattern = pattern;
        this.position= position;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
}
