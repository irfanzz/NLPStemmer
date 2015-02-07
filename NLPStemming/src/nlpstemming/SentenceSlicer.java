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
public class SentenceSlicer {
    String input;
    String[] arrayInput;
    ArrayList<ArrayList> listSentence;
    ArrayList<String> currentSentence;
    static final String[] endMark = new String[]{".\"", "!", "?", "?\"", "!\""};
    
    public SentenceSlicer(String in){
        input = in.replace("\n", " ");
        arrayInput = input.split(" ");
        listSentence = new ArrayList<>();
        currentSentence = new ArrayList<>();
        listSentence.add(currentSentence);
        
        for (int i = 0; i < arrayInput.length; i++) {
            String word = arrayInput[i];
            if (isEndingWithEndMark(word)){//
                processEndSentence(word, i);
            }else if (word.endsWith(".")){
                if (word.length() < 5){//jika katanya lebih dari 5 huruf maka dianggap sebagai end
                    if (word.startsWith("rp") || word.startsWith("Rp")){
                        currentSentence.add(word);
                    }else if (Character.isUpperCase(word.charAt(0))){
                        currentSentence.add(word);
                    }else{
                        processEndSentence(word, i);
                    }
                }else{
                    System.out.println("else");
                    processEndSentence(word, i);
                }
            }else{
                currentSentence.add(word);
            }
        }
    }

    public ArrayList<ArrayList> getListSentence() {
        return listSentence;
    }
    
    private boolean isEndingWithEndMark(String word){
        for (int i = 0; i < endMark.length; i++) {
            String string = endMark[i];
            if (word.endsWith(string)){
                return true;
            }
        }
        return false;
    }
    
    private void processEndSentence(String word, int i){
        if (i < arrayInput.length - 1){
            currentSentence.add(word);
            currentSentence = new ArrayList<>();
            listSentence.add(currentSentence);
        }else{
            currentSentence.add(word);
        }
    }
}
