/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming;


import java.util.ArrayList;

/**
 *
 * @author Irfan AFif
 */
public class Marks {
    ArrayList<String> input;
    //String [] frasa;
    String current;
    ArrayList<String> currentSentence = new ArrayList<String>();
    int n;
    char c;
    //constructor dgn input arraylist string (utk satu kalimat)  
    //disimpan di variabel (currentSentence)
    //panggil dengan 
    
    public Marks(ArrayList<String> in) { 
        input = in;
        
        for (int i=0;i<input.size();i++)
        {
            current = input.get(i);
            if (current.matches("[a-zA-Z]+"))    
                currentSentence.add(current);          
                    
            else
            {
                n = 0;
                String x = new String("");
                while (n<current.length())
                {
                    c = current.charAt(n);
                    if ( (Character.isDigit(c)) || (Character.isLetter(c))  )
                    {
                        x = x+c;
                    }
                    else
                    {
                        currentSentence.add(x);
                        currentSentence.add(""+c);
                        x = new String("");
                    }    
                    n = n+1;
                }
                if ((x.length()>0) && !(x.equals(" ")))
                    currentSentence.add(x);
            }    
               
                
        }


        
        
    }

    public ArrayList<String> getSentence() {
        return currentSentence;
    }
    
  
    


}
