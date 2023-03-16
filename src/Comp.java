import java.util.Comparator;

public class Comp implements Comparator<Letter>{

    @Override
    public int compare(Letter o1, Letter o2) {
        if(o1.count > o2.count){
            return 1;
        }
        else if(o1.count < o2.count){
            return -1;
        }
        return 0;
    
    }
    
}
