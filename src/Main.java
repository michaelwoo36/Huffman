// Michael Woo
// CS 145
// Final lab
// VS Code
// This program implements the Huffman Algorithm to compress a text file using binary.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main{

    public static void main(String[]args) throws FileNotFoundException{

        // Stores unique characters with frequency
        Map <Character, Integer> huff = new HashMap <Character, Integer>(); 
        
        // Input file you want
        Scanner in = new Scanner(new File("Huffman.txt")); 
        
        // Orders based on frequency
        Queue <Letter> huff2 = new PriorityQueue<>(new Comp());

        // List of unique characters
        ArrayList <Character> binary = new ArrayList<>();
        
        readFile(in, huff, binary);
        
        // Adds nodes for characters
        for (Entry<Character, Integer> entry : huff.entrySet()){
            huff2.add(new Letter(entry.getKey(), entry.getValue()));
        }

        Tree huffman = new Tree(binary.size());

        huffman.add(huff2);
        huffman.traverse(huffman.root, 0);
        huffman.remix(binary);
        huffman.send(new Scanner(new File("Huffman.txt")));
    }
    
    // Updates parameters with proper Characters and frequencies within the txt file.
    public static void readFile(Scanner in, Map <Character, Integer> huff, ArrayList<Character>binary){
        
        while(in.hasNextLine()){
            
            String line = in.nextLine();
            Scanner words = new Scanner(line);

            while(words.hasNext()){

                String word = words.next();

                for(int i = 0; i < word.length(); i++){
                 
                    char letter = word.charAt(i);
                 
                    if(!(binary.contains(letter))){
                 
                        binary.add(letter);
                    }
                 
                    if(huff.containsKey(letter)){
                    
                        int count = huff.get(letter);
                        huff.put(letter, count + 1);  
                    }
                 
                    else{
                 
                        huff.put(letter, 1);
                    }
                }
            }
        }
    }

}
