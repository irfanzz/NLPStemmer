/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import sun.misc.IOUtils;

/**
 *
 * @author Irfan AFif
 */
public class Marks {
    ArrayList<String> input;
    //String [] frasa;
    String current;
    ArrayList<String> currentSentence;
    int n;
    char c;
    //constructor dgn input arraylist string (utk satu kalimat)  
    //disimpan di variabel (currentSentence)
    //panggil dengan 
    
    public Marks(ArrayList<String> in) throws IOException{ 
        input = in;
        
        for (int i=0;i<input.size();i++)
        {
            current = input.get(i);
            if (current.matches("[a-zA-Z0-9]"))
                currentSentence.add(current);          
                    
            else
            {
                n = 0;
                StringBuilder sb = new StringBuilder();
                while (n<current.length())
                {
                    c = current.charAt(n);
                    if ( (Character.isDigit(c)) || (Character.isLetter(c))  )
                    {
                        sb.append(c);
                    }
                    else
                    {
                        currentSentence.add(sb.toString());
                        sb.setLength(0);
                        currentSentence.add(""+c);
                    }    
                    n = n+1;
                }
            }    
                
                
        }


        
        
    }

    public ArrayList<String> getSentence() {
        return currentSentence;
    }
    
  
    
}
