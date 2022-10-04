package sequencegenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tudor Onofrei
 */
public class ArrangementGenerator {
    private boolean used[];
    private int solution[];
    private int permutationLength;
    private List<IntegerList> allPermutations;
    private Set<String> generatedWords;
    private int length;
    private String word;
    
    public ArrangementGenerator(String newWord, int newPermutationLength){
        this.length = newWord.length();
        this.permutationLength = newPermutationLength;
        this.used = new boolean[this.length + 2];
        this.solution = new int[this.permutationLength + 2];
        this.allPermutations = new ArrayList<>();
        this.word = newWord;
        this.generatedWords = new HashSet<>();
        
        if(this.permutationLength == 0){
            for(int currentPermutationLength = 1; currentPermutationLength<=this.length; currentPermutationLength++){
                this.reinitializeAll(currentPermutationLength);
                this.permutationLength = currentPermutationLength;
                this.generate(1);
            }
        }
        else{
            this.generate(1);
        }
    }
    
    public void reinitializeAll(int newPermutationLength){
        for(int position = 0; position<=this.length; position++){
            this.used[position] = false;
        }
        
        for(int position = 0; position<=this.permutationLength; position++){
            this.solution[position] = 0;
        }
        
        this.solution = null;
        this.solution = new int[permutationLength + 2];
    }
    
    public void generate(int currentPosition){
        int i;
        if(currentPosition > this.permutationLength){
            this.allPermutations.add(new IntegerList(this.permutationLength, this.solution));
        }
        else{
            for(i = 1; i<=this.length; i++){
                if(this.used[i] == false){
                    this.solution[currentPosition] = i;
                    this.used[i] = true;
                    this.generate(currentPosition +1);
                    this.used[i] = false;
                    this.solution[currentPosition] = 0;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder wordList = new StringBuilder();
        
        wordList.append("<p> Size: ");
        wordList.append(this.allPermutations.size());
        wordList.append("</p>");
        wordList.append("<p> \n-------------\n </p>");
        
        Set<String> words = this.getWords();
        
        wordList.append("<ol>");
        for(String word: words){
            wordList.append("<li>");
            wordList.append(word);
            wordList.append("</li>");
        }
        wordList.append("</ol>");
        
        return wordList.toString();
    }
    
    public void print(){
        System.out.println("Size: " + this.allPermutations.size());
        for(IntegerList l: this.allPermutations){
            System.out.print(l);
        }
    }
    
    public Set<String> getWords(){
        Set<String> wordList = new HashSet<>();
        
        for(IntegerList indexList: this.allPermutations){
            StringBuilder newWord = new StringBuilder();
            
            List<Integer> integerList = indexList.getList();
            for(Integer i: integerList){
                newWord.append(this.word.charAt(i));
            }
            
            wordList.add(newWord.toString());
        }
        
        return wordList;
    }
    
    public Set<String> filterWords(Set<String> dictionary){
        Set<String> wordList = getWords();
        
        int listLength = wordList.size();
        for(String word: wordList){
            if(!dictionary.contains(word)){
                wordList.remove(word);
            }
        }
        
        return wordList;
    }
}
