/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming.Visitor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nlpstemming.Disambiguator.*;
import nlpstemming.tools;

/**
 *
 * @author Achank89
 */
public class VisitorProvider
{
    protected ArrayList<VisitorInterface> visitors = new ArrayList<>();

    protected ArrayList<VisitorInterface> suffixVisitors = new ArrayList<>();

    protected ArrayList<VisitorInterface> prefixVisitors = new ArrayList<>();


    public VisitorProvider()
    {
        this.initVisitors();
    }

    String replacer(char Pattern, String replace){
    
        
        switch(Pattern){
        
            case 'A' : 
                return replace.replaceFirst("A", "[a-z]");
                
                
            case 'B' : 
                return replace.replaceFirst("B", "[bcdfghjkpqstvxz]");
                
            case 'C' : 
                return replace.replaceFirst("C", "[bcdfghjklmnpqrstvwxyz]");
                
                
            case 'D' : 
                return replace.replaceFirst("D", "[bcdfghjklmnpqstvwxyz]");
                
            case 'E' : 
                return replace.replaceFirst("E", "[bcdfghjkmnpqstvwxyz]");
                
            case 'P' : 
                return replace.replaceFirst("P", ".*");
            
                
            case 'Q' : 
                return replace.replaceFirst("Q", "[^er]");
               
            case 'V' : 
                return replace.replaceFirst("V", "[aiueo]");
            
                
            case 'W' : 
                return replace.replaceFirst("W", "[aiuo]");
                
        
        }
    
        return replace;
    }
    
    protected void initVisitors()
    {
        
        this.visitors.add( new DontStemShortWord() );
        
        this.suffixVisitors.add( new RemoveInflectionalPossessivePronoun() ); // {ku|mu|nya}
        this.suffixVisitors.add( new RemoveInflectionalParticle() ); // {lah|kah|tah|pun}
        this.suffixVisitors.add( new RemoveDerivationalSuffix() ); // {i|kan|an}
      

        this.prefixVisitors.add( new RemovePlainPrefix() ); // {di|ke|se}
      
        /* TAMBAHKAN AMBIGIUITAS DISINI */
        
        
        try {
            String[] ambiguitaslist = tools.getRulesFromFile();
            
            String pattern_match = "[ABCDEPQVW]";
            
            String init = "";
            ArrayList<DisambiguatorInterface> ArryInt = new ArrayList<>();
            for(String ambigu : ambiguitaslist){
            
                
                String[] rules = ambigu.split("\t");
                String format = rules[0];
                String result = rules[1].split(" ")[0].trim().split("-")[1];
                
                format = format.replaceAll("\\}", "])");
                format = format.replaceAll("\\{", "([");
                format = format.replaceAll("\\|", "");
                
                if(!init.equals(format)){
                
                    if(ArryInt.size()>0){ 
                        this.prefixVisitors.add( new PrefixDisambiguator(
                            ArryInt
                           )
                        );
                    }
                    
                    ArryInt = new ArrayList<>();
                    // Masukkan arraylist keAmbiguitas
                
                }else{
                
                }
                
                init = format;
                
                Pattern p = Pattern.compile(pattern_match);
                Matcher m = p.matcher(format); // get a matcher object
                int count = 0;
                
                while(m.find())
                    count++;
                
                
                m = p.matcher(format);
                         
                if( count > 0){
                    
                    if( count == 1 && m.find()){
                        format = "^"+format.substring(0,m.start(0)) +"("+
                                this.replacer(m.group(0).charAt(0), m.group(0))+".*)$";
                    }else{
                        String _temp_pattern = "";
                        int lastend=0;
                        boolean _P_found = false;
                        while(m.find()){
                            
                            if(m.group().charAt(0) == 'P'){
                                _P_found = true;
                            }
                            // preg_match('/^per([bcdfghjklmnpqrstvwxyz])([a-z])er([aiueo])(.*)$/', $word, $matches);
                             _temp_pattern = _temp_pattern +  
                                     
                                     format.substring(lastend,m.start() ) +
                                     "("+ this.replacer(m.group().charAt(0), m.group()) +")";
                             
                             lastend = m.end();
                             
                        
                        }
                        if(!_P_found){
                            format = "^"+_temp_pattern+"(.*)$"; 
                        }else{
                            format = "^"+_temp_pattern+"$"; 
                        }
                        
                        
                    }
                    
                   
                    ArryInt.add(new DisambiguatorPrefixRuleCommon(result,format));
                           
                }else{
                 
                    format = "^"+format.replace(")", ".*)$");
                        
                    this.prefixVisitors.add( new PrefixDisambiguator(
                        new DisambiguatorPrefixRuleCommon(result,format)
                       )
                    );
                        
                }
                
                
                
             //   System.out.println(format);
              

               
            }
            
            
                this.prefixVisitors.add( new PrefixDisambiguator(
                    ArryInt
                   )
                );
                    
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DisambiguatorPrefixRuleCommon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<VisitorInterface> getVisitors()
    {
        return this.visitors;
    }

    public ArrayList<VisitorInterface> getSuffixVisitors()
    {
        return this.suffixVisitors;
    }

    public ArrayList<VisitorInterface> getPrefixVisitors()
    {
        return this.prefixVisitors;
    }
}