/**
 * Advent of Code Day 7
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 {
    
    private HashMap<String, Bag> bags;

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day7.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();

            Day7 day = new Day7();
            System.out.println("Part one results: " + day.partOne(lines));
            System.out.println("Part two results: " + day.partTwo(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day7() {
        this.bags = new HashMap<String, Bag>();
    }
    
    public int partOne(ArrayList<String> lines) {
        // Create a directed graph using hashmap and bag objects.
        for (String line : lines) {
            // Loop through file contents and create instances of Bag for the hash map.
            // key : value --> String : Bag
            String[] words = line.split(" ");
            String colour = words[0] + words[1];
            this.bags.put(colour, new Bag(colour));
        }
        for (String line : lines) {
            // Loop through file contents and connect the bags together in a directed graph.
            String[] words = line.split(" ");
            String colour = words[0] + words[1];
            for (int i = 5; i < words.length; i += 4) {
                String new_colour = words[i] + words[i + 1];
                // Skip if new colour is "otherbags." as this occurs when bags don't contain other bags.
                if (!new_colour.equals("otherbags.")) {
                    // Add one bag to the other.
                    this.bags.get(new_colour).addBag(this.bags.get(colour), Integer.parseInt(words[i - 1]));
                }
            }
        }
        
        /* 
        The bag being held is the one that contains the link to the outside bag.
        This is so that we can traverse the graph backwards from "shinygold" so we
        don't have to compute redundant pathways - optimised.
         */
        
        // Return the cardinality of the set of colours that appear when traversing all possible paths from shiny gold.
        // Minus one because it occurred to me that shiny gold bags do not count as containing shiny gold bags.
        return this.bags.get("shinygold").getColourSet(new HashSet<String>()).size() - 1;
    }
    
    public int partTwo(ArrayList<String> lines) {
        // Loop through bags and clear each one of its connections.
        for (Bag b : this.bags.values()) b.clear();
        
        // Loop through anew and create connections again, just in the reverse direction.
        for (String line : lines) {
            // Loop through file contents and connect the bags together in a directed graph.
            String[] words = line.split(" ");
            String colour = words[0] + words[1];
            for (int i = 5; i < words.length; i += 4) {
                String new_colour = words[i] + words[i + 1];
                // Skip if new colour is "otherbags." as this occurs when bags don't contain other bags.
                if (!new_colour.equals("otherbags.")) {
                    // Add one bag to the other- but in the reverse to in part one.
                    this.bags.get(colour).addBag(this.bags.get(new_colour), Integer.parseInt(words[i - 1]));
                }
            }
        }
        
        // Return the number of bags accessed while moving down the paths through the graph.
        // Minus one because the initial bag does not count.
        return this.bags.get("shinygold").countBags() - 1;
    }
    
}
