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
			BufferedReader br = new BufferedReader(new FileReader("day2.txt"));
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			System.out.println("Part 1 solution: " + part1(lines));
			System.out.println("Part 2 solution: " + part2(lines));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int part1(ArrayList<String> lines) {
		int counter = 0;
		int counter2;
		String[] line_split;
		int lower;
		int upper;
		char check;
		String password;
		for (String line : lines) {
			counter2 = 0;
			line_split = line.split(" |-");
			lower = Integer.parseInt(line_split[0]);
			upper = Integer.parseInt(line_split[1]);
			check = line_split[2].charAt(0);
			password = line_split[3];
			
			for (int i = 0; i < password.length(); i++) {
				if (password.charAt(i) == check) counter2 ++;
			}
			if (lower <= counter2 && counter2 <= upper) counter ++;
		}
		return counter;
	}
	
	public static int part2(ArrayList<String> lines) {
		int counter = 0;

		String[] line_split;
		int pos1;
		int pos2;
		char check;
		String password;
		for (String line : lines) {
			line_split = line.split(" |-");
			pos1 = Integer.parseInt(line_split[0]) - 1;
			pos2 = Integer.parseInt(line_split[1]) - 1;
			check = line_split[2].charAt(0);
			password = line_split[3];
			
			if (password.charAt(pos1) == check ^ password.charAt(pos2) == check) counter ++;
		}
		return counter;
	}
}
