package sequencegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tudor Onofrei
 */

public class IntegerList {
    private List<Integer> list;
    
    public IntegerList(){
        this.list = new ArrayList<>();
    }
    
    public IntegerList(int end, int newListOfIndexes[]){
        this.list = new ArrayList<>();
        for(int i = 1; i<=end; i++){
            this.list.add(newListOfIndexes[i]);
        }
    }
    
    public void decrement(int value){
        for(int index = 0; index<this.list.size(); index++){
            this.list.set(index, this.list.get(index) - value);
        }
    }
    
    @Override
    public String toString(){
        StringBuilder stringIntegerList = new StringBuilder();
       
        for(Integer i: this.list){
            stringIntegerList.append(i);
            stringIntegerList.append(" ");
        }
        stringIntegerList.append("\n");
        
        return stringIntegerList.toString();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
