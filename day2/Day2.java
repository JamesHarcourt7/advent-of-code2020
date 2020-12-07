/**
 * Advent of Code Day 2
 * @author James Harcourt
 *
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {
	
	public static void main(String[] args) {
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		try {
			// Create buffered reader to read input file.
			BufferedReader br = new BufferedReader(new FileReader("day2.txt"));
			// Store lines in ArrayList for easy iteration and reuse.
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			System.out.println("Part 1 solution: " + part1(lines));
			System.out.println("Part 2 solution: " + part2(lines));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Method for part 1 of Day 2.
	public static int part1(ArrayList<String> lines) {
		// counter will keep track of # valid passwords.
		// counter2 will keep track of how many times check appears in password.
		int counter = 0;
		int counter2;
		String[] line_split;
		// Upper and lower limits for how many times check can appear in password to be considered valid.
		int lower;
		int upper;
		char check;
		String password;
		for (String line : lines) {
			counter2 = 0;
			// Split each line by spaces and hyphens.
			line_split = line.split(" |-");
			lower = Integer.parseInt(line_split[0]);
			upper = Integer.parseInt(line_split[1]);
			check = line_split[2].charAt(0);
			password = line_split[3];
			
			// Count number of times check appears.
			for (int i = 0; i < password.length(); i++) {
				if (password.charAt(i) == check) counter2 ++;
			}
			// Confirm password is valid.
			if (lower <= counter2 && counter2 <= upper) counter ++;
		}
		return counter;
	}
	
	// Method for part 2 of Day 2.
	public static int part2(ArrayList<String> lines) {
		// Keep track of # valid passwords.
		int counter = 0;

		String[] line_split;
		// The two positions being check for the appearance of check.
		int pos1;
		int pos2;
		char check;
		String password;
		for (String line : lines) {
			// Split the line by spaces and hyphens.
			line_split = line.split(" |-");
			// Indexing starts at 1 for the input, so subtract 1 to fit our indexing.
			pos1 = Integer.parseInt(line_split[0]) - 1;
			pos2 = Integer.parseInt(line_split[1]) - 1;
			check = line_split[2].charAt(0);
			password = line_split[3];
			
			// Use XOR- password is valid if check appears ONCE at either pos1 or pos2.
			if (password.charAt(pos1) == check ^ password.charAt(pos2) == check) counter ++;
		}
		return counter;
	}
}
