/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Irfan AFif
 */
public class Tokenizer {
    ArrayList<String> input;
    String [] frasa;
    String current, next;
    ArrayList<String> currentSentence = new ArrayList<String>();
    String filename, filename1;
    //constructor dgn input arraylist string (utk satu kalimat)  
    //disimpan di variabel (currentSentence)
    //panggil dengan 
    
    public Tokenizer(ArrayList<String> in) throws IOException{
        input = in;
        int i =0;
//        URL location = Tokenizer.class.getProtectionDomain().getCodeSource().getLocation();
//        filename1 = location.toString();
//        filename1 = filename1.substring(6, filename1.length());
        filename1 = "";
        
        while (i<=input.size()-2)
        {
            current = input.get(i).toLowerCase();
            next = input.get(i+1).toLowerCase();
            
            filename ="";
            
            if (current.startsWith("a") || current.startsWith("b") || current.startsWith("c"))
                filename = (filename1+"frasaAC.txt");
            else if (current.startsWith("d") || current.startsWith("e") || current.startsWith("f"))
                filename = (filename1+"frasaDI.txt");
            else if (current.startsWith("g") || current.startsWith("h") || current.startsWith("i"))
                filename = (filename1+"frasaDI.txt");
            else if (current.startsWith("j") || current.startsWith("k") || current.startsWith("l"))
                filename = (filename1+"frasaJL.txt");
            else if (current.startsWith("m") || current.startsWith("n") || current.startsWith("o"))
                filename = (filename1+"frasaMR.txt");
            else if (current.startsWith("p") || current.startsWith("q") || current.startsWith("r"))
                filename = (filename1+"frasaMR.txt");
            else if (current.startsWith("s") || current.startsWith("t") || current.startsWith("u")|| current.startsWith("v"))    
                filename = (filename1+"frasaSZ.txt");            
            else if (current.startsWith("w") || current.startsWith("x") || current.startsWith("y")|| current.startsWith("x"))    
                filename = (filename1+"frasaSZ.txt");
        
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
                        //kata terakhir tambahkan
                        if (i>=input.size()-2)
                            currentSentence.add(input.get(i+1).toLowerCase());                                                                
                        break;
                    }    
                    else    
                        j = j+1;
                }
            
                //jika sampai akhir tidak ada di kamus tambahkan 
                if (j>=frasa.length-2)
                {
                    currentSentence.add(current);  
                    //jika kata terakhir, tambahkan
                    if (i>=input.size()-2)
                        currentSentence.add(next);                
                }          
            }
            
            //jika tanda baca, tambahkan
            else
            {    
                currentSentence.add(current);
                //jika kata terakhir, tambahkan
                if (i>= input.size()-2)
                    currentSentence.add(next);                             
            }
            i=i+1;
            
        }//endwhile
              

        
        
    }

    public ArrayList<String> getSentence() {
        return currentSentence;
    }
    
    
    public static String readFile(String fn) throws IOException {
    String s="";
    try {
	File file = new File(fn);
        FileReader fileReader = new FileReader(file);
    	BufferedReader bufferedReader = new BufferedReader(fileReader);
	StringBuffer stringBuffer = new StringBuffer();
	String line;
	while ((line = bufferedReader.readLine()) != null) {
        	stringBuffer.append(line);
                stringBuffer.append(" ");
        	}
    	fileReader.close();
	s = stringBuffer.toString();
        } catch (IOException e) {
	e.printStackTrace();
    }
    
    return s;
   }
	
	
 
}
