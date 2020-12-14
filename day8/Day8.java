/**
 * Advent of Code Day 8
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {
    
    // progCounter: holds address of current instruction being executed.
    private int progCounter;
    // accumulator: holds the result of operations on the previous value in the accumulator.
    private int accumulator;
    private ArrayList<String> lines;

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day8.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();

            Day8 day = new Day8(lines);
            System.out.println("Part one results: " + day.partOne());
            System.out.println("Part two results: " + day.partTwo());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day8(ArrayList<String> lines) {
        this.lines = lines;
        this.progCounter = 0;
        this.accumulator = 0;
    }
    
    public void reset() {
        this.progCounter = 0;
        this.accumulator = 0;
    }
    
    public int partOne() {
        // Keep track of address spaces already visited, so we can check and find where program is looping.
        ArrayList<Integer> instrVisited = new ArrayList<Integer>();
        while (true) {
            // If instr is running the second time, return the value in the accumulator.
            if (instrVisited.contains(this.progCounter)) return this.accumulator;
            instrVisited.add(this.progCounter);
            
            String[] instr = this.lines.get(this.progCounter).split(" ");
            if (instr[0].equals("acc")) {
                // acc instruction: add the operand to the value in the accumulator.
                this.accumulator += Integer.parseInt(instr[1]);
                this.progCounter ++;
            } else if (instr[0].equals("jmp")) {
                // jmp instruction: add the operand to the value in the progCounter.
                this.progCounter += Integer.parseInt(instr[1]);
            } else {
                // Otherwise just increment the program counter.
                this.progCounter ++;
            }
            // If reached the end of the program without loops, return the value in the accumulator.
            if (progCounter == lines.size()) return this.accumulator;
        }
    }
    
    public int partTwo() {
        // Brute force option: I'm almost a week behind and I don't know many optimisations or maths :(
        // Loop through the program and replace each jmp or nop one at a time then run part one and analyse the return.
        // Reset program afterwards to repeat.
        for (int i = 0; i < this.lines.size(); i ++) {
            if (this.lines.get(i).contains("acc")) continue;
            String[] instr = this.lines.get(i).split(" ");
            
            // Swap nops for jmps and vice versa.
            if (instr[0].equals("jmp")) {
                this.lines.set(i, "nop " + instr[1]);
            } else {
                this.lines.set(i, "jmp " + instr[1]);
            }
            
            // Run the code.
            int value = partOne();

            instr = this.lines.get(i).split(" ");
            // Swap the values back afterwards.
            if (instr[0].equals("jmp")) {
                this.lines.set(i, "nop " + instr[1]);
            } else {
                this.lines.set(i, "jmp " + instr[1]);
            }
            
            // If reached end of program, return the value in the accumulator.
            if (this.progCounter == this.lines.size()) return value;
            // Reset the attributes.
            reset();
        }
        return 0;
    }
}