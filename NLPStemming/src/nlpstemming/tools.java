/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Achank89
 */
public class tools {
    public static boolean pregMatch(String pattern, String content) {
        return content.matches(pattern);
    }
    public static Object[] removeElement(Object[] a, int del) {
        System.arraycopy(a,del+1,a,del,a.length-1-del);
        return a;
    }   
    
    public static ArrayList<String> pregMatchExtract(String pattern, String content){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content); // get a matcher object
   
        ArrayList<String> Ret = new ArrayList<>();
        
        if(m.find()){
            for(int i=0; i < m.groupCount();i++){
                Ret.add(m.group(i+1));        
            }
        }
        return Ret;
    }
    
    public static Object[] reverse(Object[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            Object temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
        return a;
    }   
    
    
    public static ArrayList<String> getWordsFromFile() throws FileNotFoundException
    {
        FileInputStream fis = new FileInputStream(new File("kata-dasar.txt"));
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
        ArrayList<String> list = new ArrayList<>();
        try{
            String line = null;
            while ((line = br.readLine()) != null) {
                    list.add(line);
            }
        }catch(Exception e){
            
        }
        return list;
    }
    
    
    public static ArrayList<String> getRulesFromFile() throws FileNotFoundException
    {
//        String content = new Scanner(new File("rule-ambiguitas.txt")).useDelimiter("\\Z").next();
//        return content.split("\r");
        FileInputStream fis = new FileInputStream(new File("rule-ambiguitas.txt"));
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
        ArrayList<String> list = new ArrayList<>();
        try{
            String line = null;
            while ((line = br.readLine()) != null) {
                    list.add(line);
            }
        }catch(Exception e){
            
        }
        return list;
    }
}
