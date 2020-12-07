/**
 * Advent of Code Day 3
 * @author James Harcourt
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
	
	public static void main(String[] args) {
		// ArrayList used to store input file contents for ease of access and reuse.
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			// Create buffered reader to read input file and store contents in lines.
			BufferedReader br = new BufferedReader(new FileReader("day3.txt"));
			String line;
			while ((line = br.readLine()) != null) lines.add(line);
			
			// Call methods for parts 1 and 2 of challenge with the input data.
			System.out.println("Part 1 solutions: " + part1(lines, 3, 1));
			System.out.println("Part 2 solutions: " + part2(lines));
			
			// Close buffered reader.
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * part1
	 * Takes input data as ArrayList<String> lines, and slope vector as int dx and int dy.
	 * Returns int count as the challenge solution.
	 */
	public static int part1(ArrayList<String> lines, int dx, int dy) {
		// Keep track of number of trees hit and current position on grid.
		int count = 0;
		int x = 0;
		int y = 0;
		
		// Until reached the bottom of the grid.
		while (y < lines.size() - 1) {
			// Grid repeats itself in the x direction, so wrap around with modulus.
			x = (x + dx) % lines.get(0).length();
			y += dy;
			
			if (lines.get(y).charAt(x) == '#') count ++;
		}
		
		return count;
	}
	
	/*
	 * part2
	 * Takes input data as ArrayList<String> lines.
	 * Returns int product as challenge solution.
	 */
	public static int part2(ArrayList<String> lines) {
		// Keep track of trees hit and product for challenge solution.
		int count = 0;
		int product = 1;
		
		// Each of the slope vectors that need to be checked.
		int[] slopesx = new int[] {1, 3, 5, 7, 1};
		int[] slopesy = new int[] {1, 1, 1, 1, 2};
		
		// Is this cheating?
		// Reuse code for part1, finding the trees hit for each slope vector and multiply to find total product.
		for (int i = 0; i < 5; i++) product *= part1(lines, slopesx[i], slopesy[i]);
		
		return product;
	}
	

}
