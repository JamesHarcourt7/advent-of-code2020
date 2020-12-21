/**
 * Advent of Code Day 14
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day14.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();

            System.out.println("Part one results: " + partOne(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static long partOne(ArrayList<String> lines) {
        String mask = "";
        long address = 0;
        long value = 0;
        long newvalue = 0;
        // Store each value in memory in a hash map with the key as the address in memory.
        HashMap<Long, Long> memory = new HashMap<Long, Long>();
        
        for (String line : lines) {
            String[] splitline = line.split(" ");
            if (splitline[0].equals("mask")) {
                mask = splitline[2];
            } else {
                // Parse the line for the memory address and the given value.
                address = Long.parseLong(splitline[0].split("\\[|\\]")[1]);
                value = Long.parseLong(splitline[2]);
                newvalue = 0;
                // For each character in the mask: if its 1, add the corresponding power of 2 to the new value.
                // If its an X, add the corresponding power of two in the byte in the original value.
                for (int i = 0; i < mask.length(); i++) {
                    if (mask.charAt(i) == 'X') {
                        newvalue += value & ((long) Math.pow(2, 35 - i));
                    } else if (mask.charAt(i) == '1') {
                        newvalue += Math.pow(2, 35 - i);
                    }
                }
                // Place the new value in the memory address space- overwriting anything that was there previously. 
                memory.put(address, newvalue);
            }
        }
        // Get the sum of all values in the hashmap.
        long total = 0;
        for (long v : memory.values()) total += v;
        return total;
    }
}