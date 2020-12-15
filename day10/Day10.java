/**
 * Advent of Code Day 10
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    private ArrayList<String> lines;

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day10.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();

            Day10 day = new Day10(lines);
            System.out.println("Part one results: " + day.partOne());
            System.out.println("Part two results: " + day.partTwo());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Day10(ArrayList<String> lines) {
        this.lines = lines;
    }
    
    public int partOne() {
        int diff;
        int diff1 = 0;
        int diff3 = 0;
        ArrayList<Integer> adapters = new ArrayList<Integer>();
        for (int i = 0; i < this.lines.size(); i ++) {
            adapters.add(Integer.parseInt(this.lines.get(i)));
        }
        Collections.sort(adapters);
        for (int i = 0; i < adapters.size(); i ++) {
            if (i == 0) {
                diff = adapters.get(0);
            } else {
                diff = adapters.get(i) - adapters.get(i - 1);
            }
            if (diff == 3) {
                diff3 ++;
            } else if (diff == 1) {
                diff1 ++;
            }
        }
        // Add one because device adapter is 3 jolts higher than largest.
        return diff1 * (diff3 + 1);
    }
    
    public int partTwo() {
        // Convert input array to integers and sort.
        ArrayList<Integer> input = new ArrayList<Integer>();
        for (int i = 0; i < this.lines.size(); i ++) {
            input.add(Integer.parseInt(this.lines.get(i)));
        }
        Collections.sort(input);
        int[] adapters = new int[input.size()];
        for (int i = 0; i < input.size(); i ++) adapters[i] = input.get(i);
        
        // Create the root adapter for the graph.
        // Then call createPaths on it, which adds all possible connections as children and calls 
        // createPaths on each of them to create all possible paths from power to any finishing point.
        Adapter power = new Adapter(0);
        power.createPaths(adapters);
        // Then with power linked up to all paths, we can count how many of these paths end at the power adapter.
        int end = adapters[adapters.length - 1];
        return power.countPaths(end);
    }
}
    