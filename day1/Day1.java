/**
 * 01/12/20
 * @author James Harcourt
 * 
 * Day 1 Part 2 Advent of Code 2020 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Day1 {
	
	public static void main(String[] args) {
		// Use an ArrayList to store Integers from the input for ease of access later on.
		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		// Read from the input text file and store each number in numList.
		try {
			BufferedReader reader = new BufferedReader(new FileReader("day1.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				numList.add(Integer.parseInt(line));
			}
			
			// Close the reader once finished reading.
			if (reader != null) {
				reader.close();
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		// Perform each part on the input.
		int result1 = partOne(numList);
		if (result1 == -1) {
			System.out.println("Part 1: Could not find correct sum from input.");
		} else {
			System.out.println("Part 1: Answer = " + result1);
		}
		
		int result2 = partTwo(numList);
		if (result2 == -1) {
			System.out.println("Part 2: Could not find correct sum from input.");
		} else {
			System.out.println("Part 2: Answer = " + result2);
		}
	
	}
	
	public static int partOne(ArrayList<Integer> numList) {
		// Code for Day 1 Part 1
		// Find the products of 2 numbers in the input that sum to 2020.
		for (int n1 : numList) {
			for (int n2 : numList) {
				if (n1 + n2 == 2020) {
					return n1 * n2;
				}
			}
		}
		return -1;
	}
	
	public static int partTwo(ArrayList<Integer> numList) {
		// Code for Day 1 Part 2
		// Find the products of 3 numbers in the input that sum to 2020.
		
		for (int n1 : numList) {
			for (int n2 : numList) {
				for (int n3 : numList) {
					if (n1 + n2 + n3 == 2020) {
						return n1 * n2 * n3;
					}
				}
			}
		}
		return -1;
	}
}
