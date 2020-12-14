/**
 * Advent of Code Day 7
 * Helper class
 * @author James Harcourt
 */
import java.util.HashSet;
import java.util.HashMap;

public class Bag {
    
    private String colour;
    private HashMap<Bag, Integer> containedBags;
    
    public Bag(String colour) {
        this.colour = colour;
        this.containedBags = new HashMap<Bag, Integer>();
    }
    
    public void addBag(Bag b, int q) {
        this.containedBags.put(b, q);
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public void clear() {
        this.containedBags.clear();
    }
    
    public HashSet<String> getColourSet(HashSet<String> colourSet) {
        // Add own colour to the path set, then call method on children.
        // Traverses path all the way up to "root" bag before returning set.
        // No duplicates when adding colour of bag to set.
        colourSet.add(getColour());
        for (Bag b : this.containedBags.keySet()) {
            colourSet = b.getColourSet(colourSet);
        }
        return colourSet;
    }
    
    public int countBags() {
        // Polymorphism -if no parameter given to countBags, assume it is 1.
        return countBags(1);
    }
    
    public int countBags(int count) {
        int temp = 0;
        for (Bag b: this.containedBags.keySet()) {
            // Call the method on the next bag in the graph- multiply by the quantity of that bag in this bag.
            temp += b.countBags(count) * this.containedBags.get(b);
        }
        count += temp;
        return count;
    }
    
}
