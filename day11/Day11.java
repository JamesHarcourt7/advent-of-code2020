/**
 * Advent of Code Day 11
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day11 {

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day11.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();
            
            System.out.println("Part one results: " + partOne(lines));
            System.out.println("Part two results: " + partTwo(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int partOne(ArrayList<String> lines) {
        // Create a new String array that we can edit.
        String[] seating = new String[lines.size()];
        for (int n = 0; n < lines.size(); n ++) seating[n] = lines.get(n);
        // Create another array in which we can store the new round of simulation so we know when it stabilises.
        String[] newRound = new String[lines.size()];
        
        Boolean changed = true;
        while (changed) {
            changed = false;
            
            // Enact rules on each seat in the grid.
            for (int y = 0; y < seating.length; y ++) {
                String newRow = "";
                for (int x = 0; x < seating[0].length(); x ++) {
                    // Skip if no seat here.
                    if (seating[y].charAt(x) == '.') {
                        newRow += ".";
                        continue;
                    }
                    
                    // Count number of adjacent seats.
                    int count = 0;
                    
                    // Check to see if adjacent seats are occupied.
                    if (y != 0) {
                        if (x != 0 && seating[y - 1].charAt(x - 1) == '#') count++;
                        if (seating[y - 1].charAt(x) == '#') count++;
                        if (x != seating[0].length() -1 && seating[y - 1].charAt(x + 1) == '#') count++;
                    }
                    if (y != seating.length - 1) {
                        if (x != 0 && seating[y + 1].charAt(x - 1) == '#') count++;
                        if (seating[y + 1].charAt(x) == '#') count++;
                        if (x != seating[0].length() - 1 && seating[y + 1].charAt(x + 1) == '#') count++;
                    }
                    if (x != 0 && seating[y].charAt(x - 1) == '#') count ++;
                    if (x != seating[0].length() -1 && seating[y].charAt(x + 1) == '#') count ++;
                    
                    // Apply rules to seat.
                    
                    if (seating[y].charAt(x) == 'L') {
                        if (count == 0) {
                            newRow += "#";
                        } else {
                            newRow += "L";
                        }
                    } else {
                        if (count >= 4) {
                            newRow += "L";
                        } else {
                            newRow += "#";
                        }
                    }
                    
                }
                newRound[y] = newRow;
            }
            for (int i = 0; i < seating.length; i ++) {
                if (!seating[i].equals(newRound[i])) changed = true;
                seating[i] = newRound[i];
            }
            
        }
        
        // Count number of occupied seats.
        int occupied = 0;
        for (int y = 0; y < seating.length; y ++) {
            for (int x = 0; x < seating[0].length(); x ++) {
                if (seating[y].charAt(x) == '#') occupied ++;
            }
        }
        return occupied;
    }

    public static int partTwo(ArrayList<String> lines) {
        // Create a new String array that we can edit.
        String[] seating = new String[lines.size()];
        for (int n = 0; n < lines.size(); n ++) seating[n] = lines.get(n);
        // Create another array in which we can store the new round of simulation so we know when it stabilises.
        String[] newRound = new String[lines.size()];

        Boolean changed = true;
        while (changed) {
            changed = false;

            // Enact rules on each seat in the grid.
            for (int y = 0; y < seating.length; y ++) {
                String newRow = "";
                for (int x = 0; x < seating[0].length(); x ++) {
                    // Skip if no seat here.
                    if (seating[y].charAt(x) == '.') {
                        newRow += ".";
                        continue;
                    }

                    // Count number of adjacent seats.
                    int count = countVisible(seating, x, y);
                    
                    // Apply rules to seat.

                    if (seating[y].charAt(x) == 'L') {
                        if (count == 0) {
                            newRow += "#";
                        } else {
                            newRow += "L";
                        }
                    } else {
                        if (count >= 5) {
                            newRow += "L";
                        } else {
                            newRow += "#";
                        }
                    }

                }
                newRound[y] = newRow;
            }
            for (int i = 0; i < seating.length; i ++) {
                if (!seating[i].equals(newRound[i])) changed = true;
                seating[i] = newRound[i];
            }

        }

        // Count number of occupied seats.
        int occupied = 0;
        for (int y = 0; y < seating.length; y ++) {
            for (int x = 0; x < seating[0].length(); x ++) {
                if (seating[y].charAt(x) == '#') occupied ++;
            }
        }
        return occupied;
    }
    
    public static int countVisible(String[] seating, int x, int y) {
        // Count the number of occupied seats visible from the seat at (x, y).
        int count = 0;
        boolean foundSeat;
        // Loop through 3x3 adjacent spaces to current (x, y), ignoring (x, y) itself.
        int[] directions = new int[] {-1, 0, 1};
        for (int dy : directions) {
            for (int dx : directions) {
                if (dy == 0 && dx == 0) continue;
                // Until a seat is found for this direction, increase the depth which the direction is multiplied by.
                foundSeat = false;
                short depth = 1;
                while (!foundSeat) {
                    // If a seat is not found in this direction by the time the borders are reached, break the loop.
                    if (y + (dy * depth) >= seating.length || y + (dy * depth) < 0) break;
                    if (x + (dx * depth) >= seating[0].length() || x + (dx * depth) < 0) break;
                    // Get the character at the space being checked.
                    char c = seating[y + (dy * depth)].charAt(x + (dx * depth));
                    if (c == '.') {
                        // If not a seat, increase the depth.
                        depth ++;
                    } else {
                        // If the seat is occupied, increase the counter.
                        if (c == '#') count ++;
                        foundSeat = true;
                    }
                }
            }
        }
        return count;
    }
}