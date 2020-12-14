/**
 * Advent of Code Day 9
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {
        
    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day9.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();
            
            int index = partOne(lines);
            System.out.println("Part one results: " + lines.get(index));
            System.out.println("Part two results: " + partTwo(lines, index));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int partOne(ArrayList<String> lines) {
        // Once again, brute forcing to save the day.
        // Time complexity something like O(n**3)? ;_;
        
        for (int i = 25; i < lines.size(); i ++) {
            // Changed int to long as numbers became too big for int.
            long n = Long.parseLong(lines.get(i));
            Boolean valid = false;
            // Loop through numbers 1-25 twice to subtract from index in order to get all possible pairs from preamble.
            for (int o1 = 1; o1 < 26; o1++) {
                for (int o2 = 1; o2 < 26; o2++) {
                    if (o1 == o2) continue;
                    // If n == lines[i - o1] + lines[i - o2] ----> somewhere in previous 25 numbers, a pair sums to n.
                    if (n == Long.parseLong(lines.get(i - o1)) + Long.parseLong(lines.get(i - o2))) {
                        valid = true;
                        break;
                    }
                }
                if (valid) break;
            }
            // If a pair hasn't been found, n must break the rule so return it's index.
            if (!valid) return i;
        }
        return -1;
    }
    
    public static long partTwo(ArrayList<String> lines, int target) {
        // See Day8.partTwo method stub for concerns regarding the lack of optimisation. 
        
        for (int i = 1; i < target; i ++) {
            // only have to loop to i - 1, as j is the lower limit.
            for (int j = 0; j < i; j ++) {
                long total = 0;
                // sum all of the numbers in contiguous range j-i.
                for (int k = j; k < i + 1; k ++) {
                    total += Long.parseLong(lines.get(k));
                }
                if (total == Long.parseLong(lines.get(target))) {
                    // Get the highest and lowest values in contiguous set.
                    // Then return their sum.
                    long highest = Long.parseLong(lines.get(j));
                    long lowest = Long.parseLong(lines.get(j));
                    for (int k = j + 1; k < i + 1; k ++) {
                        long val = Long.parseLong(lines.get(k));
                        if (highest < val) highest = val;
                        if (lowest > val) lowest = val;
                    }
                    return highest + lowest;
                }
            }
        }
        // If no set found that sums to lines[target], return -1.
        return -1;
    }
    
}
