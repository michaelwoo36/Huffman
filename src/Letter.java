public class Letter {
    
    char letter;
    int count;

    Letter left;
    Letter right;

    public Letter(char letter, int count){

        this.letter = letter;
        this.count = count;
    }

    // Constructor for combined Nodes
    public Letter(int count){
         this.count = count;
    }
}
