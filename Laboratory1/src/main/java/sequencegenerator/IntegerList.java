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
    
    public IntegerList(int end, int newList[]){
        this.list = new ArrayList<>();
        for(int i = 1; i<=end; i++){
            this.list.add(newList[i] - 1);
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
