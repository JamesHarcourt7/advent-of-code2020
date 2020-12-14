/**
 * Advent of Code Day 6                                                                              
 * @author James Harcourt                                                                            
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Day6 {

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day6.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Add blank line to the end, so that final group count gets calculated (part one).
            lines.add("");
            // Close the buffered reader once finished.
            br.close();

            System.out.println("Part one results: " + partOne(lines));
            System.out.println("Part two results: " + partTwo(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int partOne(ArrayList<String> lines) {
        // Keep track of the sum of counts.
        int sum = 0;
        // Keep track of questions answered yes to as a set- duplicates don't get added.
        HashSet<String> yesses =  new HashSet<String>();
        for (String line : lines) {
            if (line.length() == 0) {
                // Groups divided by empty lines.
                // At the end of group, the size of the set is the count of yes answers for the group.
                sum += yesses.size();
                yesses.clear();
            } else {
                // Add the letter of the question to the set. Duplicate letters aren't counted in the set.
                for (String c : line.split("")) yesses.add(c);
            }
        }
        return sum;
    }

    public static int partTwo(ArrayList<String> lines) {
        // Keep track of the sum of counts.
        int sum = 0;
        // Keep track of questions answered yes to as a set- duplicates don't get added.
        HashSet<String> yesses =  new HashSet<String>();
        Boolean first = true;
        for (String line : lines) {
            if (line.length() == 0) {
                // Groups divided by empty lines.
                // At the end of group, the size of the set is the count of yes answers for the group.
                sum += yesses.size();
                yesses.clear();
                // The next line must be the first person in the group.
                first = true;
            } else {
                if (first) {
                    // If person is the first, add all of their yes answered questions.
                    // Compare with others to remove values that don't appear.
                    // End result is the set of questions answered yes by everyone in group.
                    for (String c : line.split("")) yesses.add(c);
                    first = false;
                } else {
                    Iterator<String> yesIterator = yesses.iterator();
                    String toRemove = "";
                    while (yesIterator.hasNext()) {
                        String y = yesIterator.next();
                        if (!(line.contains(y))) {
                            toRemove += y;
                        }
                    }
                    // Avoid ConcurrentModificationException- remove all characters from toRemove string.
                    for (String c : toRemove.split("")) yesses.remove(c);
                }
            }
        }
        return sum;
    }
}                                                                                                    
                                                                                                     