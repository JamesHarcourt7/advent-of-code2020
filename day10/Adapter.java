/**
 * Advent of Code Day 10
 * @author James Harcourt
 */
import java.util.ArrayList;

public class Adapter {
    
    // Rated "joltage" of the adapter:
    private int jolts;
    private Adapter parent;
    private ArrayList<Adapter> children;
    
    public Adapter(int jolts) {
        this.jolts = jolts;
        this.parent = null;
        this.children = new ArrayList<Adapter>();
    }
    
    public int getJolts() {
        return this.jolts;
    }
    
    public ArrayList<Adapter> getChildren() {
        return this.children;
    }
    
    public void setParent(Adapter parent) {
        this.parent = parent;
    }
    
    public void addChild(Adapter child) {
        this.children.add(child);
    }
    
    public void createPaths(int[] adapters) {
        
        // Day 10, also known as- fantastic new ways of throttling your system's memory allocation :D
        // (You know the debugging is getting better when you start getting new types of memory errors ;-; )
        
        // Parameter adapters contains the adapter jolts still to add to the path.
        for (int i = 0; i < adapters.length; i ++) {
            // Only add child which has a jolt difference of 3 or less, yet more than 0.
            if (adapters[i] - getJolts() <= 3 && adapters[i] - getJolts() > 0) {
                Adapter a = new Adapter(adapters[i]);
                int[] n = new int[adapters.length];
                
                // Duplicate the array of values and remove the current index's value afterwards.
                for (int j = 0; j < adapters.length; j++) if (j != i) n[j] = adapters[j];
                
                // Set the new adapter's parent and add it to children.
                a.setParent(this);
                addChild(a);
                
                // Call createPaths on new adapter, passing the shortened array.
                a.createPaths(n);
            }
        }
    }
    
    public int countPaths(int endpoint) {
        return countPaths(0, endpoint);
    }
    
    public int countPaths(int count, int endpoint) {
        if (getChildren().size() > 0) {
            int temp = 0;
            for (Adapter a : getChildren()) {
                temp += a.countPaths(count, endpoint);
            }
            count += temp;
        } else {
            if (getJolts() == endpoint) count ++;
        }
        return count;
    }
    
}
