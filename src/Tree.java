
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Map.Entry;

// Creates Binary Tree to store, traverse, and print binary codes of text file.
public class Tree {
    
    private int count = 10; // Tree diagram shift space
    Letter root;
    int [] ar;
    Map <Character, String> codes = new HashMap <Character, String>(); // binary codes for each unique character

    public Tree(int total){
        
        ar = new int[total];
    }
    
    // Outputs binary codes to new file
    public void send(Scanner output) throws FileNotFoundException {
       
        String code = "";
        File test = new File("test.txt");
        PrintStream out = new PrintStream(test);
            
        while(output.hasNextLine()){
                
            String line = output.nextLine();
            Scanner words = new Scanner(line);
                
            while(words.hasNext()){
                    
                String word = words.next();
                    
                for(int i = 0; i < word.length(); i++){
                    
                    for (Entry<Character, String> entry : codes.entrySet()){
                        
                        if(word.charAt(i) == entry.getKey()){
                            
                            code += entry.getValue();
                        }
                    }
                }
            }
        }

        out.print(code);
    }

    // Adds Node to binary tree
    public void add( Queue <Letter> h){
        
        while(h.size() != 1){
            
            Letter first = h.poll();
            Letter second = h.poll();

            int total = first.count + second.count;

            Letter boot = new Letter(total); // New Node that is combination of previous two.
            boot.left = first;
            boot.right = second;
            
            h.add(boot);
        }

        root = h.poll(); // Stores Overall root of finished tree.
    }

    // Prints each unique character's binary code.
    public void remix(ArrayList<Character>binary){
        
        for(int i = 0; i < binary.size(); i++){
            
            char letter = binary.get(i);
            System.out.println(letter + ":");
            search(root, letter, new int[4], 0);            
        }
    }

    // Finds binary code for each unique character
    public void search(Letter temp, char letter, int[]bop, int count){
        
        String code = "";
        
        if(temp.left != null){
        
            bop[count] = 0;
            search(temp.left, letter, bop, count + 1);
        }
        
        if(temp.right != null){
        
            bop[count] = 1;
            search(temp.right, letter, bop, count + 1);
        }
        
        if(temp.letter == letter){
        
            for(int i = 0; i < count; i++){
        
                System.out.print(bop[i]);
                code += bop[i] + "";
            }
        
            codes.put(letter, code);
            System.out.println();
        }
    }
   
    // Builds diagram for binary tree
    public void traverse(Letter temp, int space){
        
        space += count;
        if(temp != null){
            
            traverse(temp.right, space);
            for(int i = 0; i < space; i++){
                System.out.print(" ");
            }
            System.out.println(temp.letter + " " + temp.count);

            traverse(temp.left, space);
        }
    }
}