package sequencegenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tudor Onofrei
 */
public class WordChecker {
    static Set<String> wordContainer;
    
    static boolean isCreated = false;
    
    public static Set<String> getInstance(){
        if(!isCreated){
            wordContainer = getDictionary("E:\\Facultate\\_Master\\Java_Technologies\\JavaTechnologies\\Laboratory2\\src\\main\\webapp\\resources\\dictionary.txt");
        }
        return wordContainer;
    }
    
    private static Set<String> getDictionary(String newPathToDictionary){
        isCreated = true;
        wordContainer = new HashSet<>();
        try{
            FileReader reader = new FileReader(newPathToDictionary);
            BufferedReader specializedReader = new BufferedReader(reader);
            
            String wordInDictionary = specializedReader.readLine();
            while(wordInDictionary != null){
                wordContainer.add(wordInDictionary);
                wordInDictionary = specializedReader.readLine();
            }
            
            specializedReader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordChecker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wordContainer;
    }
    
    public static Set<String> filterList(Set<String> dictionary, Set<String> words){
        Set<String> filteredList = new HashSet<>();
        
        for(String word: words){
            if(dictionary.contains(word)){
                filteredList.add(word);
            }
        }
        return filteredList;
    }
}
