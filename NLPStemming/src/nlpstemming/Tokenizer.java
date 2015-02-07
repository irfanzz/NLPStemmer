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
public class Tokenizer {
    ArrayList<String> input;
    String [] frasa;
    String current, next;
    ArrayList<String> currentSentence;
    String filename;
    //constructor dgn input arraylist string (utk satu kalimat)  
    //disimpan di variabel (currentSentence)
    //panggil dengan 
    
    public Tokenizer(ArrayList<String> in) throws IOException{
        
        input = in;
        int i =0;
        filename ="";
        while (i<=input.size()-2)
        {
            current = input.get(i).toLowerCase();
            next = input.get(i+1).toLowerCase();
            
            if (current.startsWith("a") || current.startsWith("b") || current.startsWith("c"))
                filename = ("frasaAC.txt");
            else if (current.startsWith("d") || current.startsWith("e") || current.startsWith("f"))
                filename = ("frasaDI.txt");
            else if (current.startsWith("g") || current.startsWith("h") || current.startsWith("i"))
                filename = ("frasaDI.txt");
            else if (current.startsWith("j") || current.startsWith("k") || current.startsWith("l"))
                filename = ("frasaJL.txt");
            else if (current.startsWith("m") || current.startsWith("n") || current.startsWith("o"))
                filename = ("frasaMR.txt");
            else if (current.startsWith("p") || current.startsWith("q") || current.startsWith("r"))
                filename = ("frasaMR.txt");
            else if (current.startsWith("s") || current.startsWith("t") || current.startsWith("u")|| current.startsWith("v"))    
                filename = ("frasaSZ.txt");            
            else if (current.startsWith("w") || current.startsWith("x") || current.startsWith("y")|| current.startsWith("x"))    
                filename = ("frasaSZ.txt");
            
            if (filename.length() > 1)
            {    
                String everything =  readFile(filename); 
                frasa = everything.split(" ");
                
                int j = 0;
                while (j<=frasa.length-2)
                {
                    if ( (current.equals(frasa[j])) && (next.equals(frasa[j+1])) )
                    {
                        currentSentence.add(current+next);
                        i = i+1;
                        break;
                    }    
                    else    
                        j = j+2;
                }
            
                if (j>=frasa.length-2)          
                    currentSentence.add(current);
            }        
            else
                currentSentence.add(current);
            
            i=i+1;
        }//endwhile

        
        
    }

    public ArrayList<String> getSentence() {
        return currentSentence;
    }
    
    
    public static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}
    
}
