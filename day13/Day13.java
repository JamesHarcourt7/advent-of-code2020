/**
 * Advent of Code Day 13
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day13.txt"));
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
    
    public static int partOne(ArrayList<String> lines) {
        int earliest = Integer.parseInt(lines.get(0));
        int departs = earliest;
        String[] busIDStrings = lines.get(1).split("[,x]+");
        int[] busIDs = new int[busIDStrings.length];
        for (int i = 0; i < busIDStrings.length; i ++) busIDs[i] = Integer.parseInt(busIDStrings[i]);
        while (true) {
            for (int id : busIDs) {
                if (departs % id == 0) {
                    return (departs - earliest) * id;
                }
            }
            departs ++;
        }
    }
}