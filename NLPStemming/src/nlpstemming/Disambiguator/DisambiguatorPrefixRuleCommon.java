/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Disambiguator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nlpstemming.tools;

/**
 *
 * @author Achank89
 */
public class  DisambiguatorPrefixRuleCommon implements DisambiguatorInterface
{
    String match = "";
    String result_pattern = "";
    int total_match = 0;
    ArrayList<DisambiguatorComplementer> _wheres = new ArrayList<>();
    
   public  DisambiguatorPrefixRuleCommon(String result_pattern, String match){
    
       this.result_pattern = result_pattern;
        this.match= match;
        this.total_match=1;
        
    }
    
    
    public DisambiguatorPrefixRuleCommon(String result_pattern,String match, int total_match){
         this.result_pattern = result_pattern;
        this.match= match;
        this.total_match= total_match;
    }
    
    
    public DisambiguatorPrefixRuleCommon(String result_pattern,String match, int total_match, DisambiguatorComplementer[] in){
         this.result_pattern = result_pattern;
        this.match= match;
        this.total_match= total_match;
        this.InsertWhere(in);
    }
    
    public void InsertWhere(DisambiguatorComplementer[] in){
    
        this._wheres.addAll(Arrays.asList(in));
    
    }
    
    @Override
    public String disambiguate(String word)
    {

        
        if( tools.pregMatch(this.match, word) ){
        
            ArrayList<String> matches = tools.pregMatchExtract( this.match, word);
             
             
            for( DisambiguatorComplementer where:  this._wheres){
                 if( matches.size() > where.getPosition() && tools.pregMatch(where.getPattern(), matches.get(where.getPosition())) ){
                        return null;
                   }
            }

            String pattern_match = "[ABCDEPQVW]";
            
            Pattern p = Pattern.compile(pattern_match);
            Matcher m = p.matcher(this.result_pattern); // get a matcher object
            int count = 0;

            while(m.find())
                count++;


            m = p.matcher(this.result_pattern);
                
            String ret = "";

            
            int lastend=0;
            int i=0;
            if(matches.size() <= count && i<count){
                while(m.find()){
                    // Masih perlu diperbaiki untuk kasus peCerV	per-erV | where C!={r|w|y|l|m|n}, dimana semua pattern tidak digunakan
                      ret = ret +  

                      this.result_pattern.substring(lastend,m.start() ) +
                            matches.get(i);

                            lastend = m.end();
                     i++;
                }
            }else if(matches.size() > count){
                
                for(String s: matches){
                
                    if(m.find()){
                        ret = ret +  

                      this.result_pattern.substring(lastend,m.start() ) +
                            s;

                            lastend = m.end();
                    }else{
                        ret = ret + s;
                    }
                }
            }else{
            
            
            }

            return ret;
             
             
        }
        return null;
        
    }
}
