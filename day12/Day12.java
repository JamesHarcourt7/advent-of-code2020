/**
 * Advent of Code Day 12
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {

    private int x;
    private int y;
    private int xDir;
    private int yDir;
    private int wayX;
    private int wayY;
    private ArrayList<String> lines;

    public static void main(String[] args) {
        // Store contents of input file in array list for ease of access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            // Create buffered reader to read input file.
            BufferedReader br = new BufferedReader(new FileReader("day12.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Close the buffered reader once finished.
            br.close();

            Day12 day = new Day12(lines);
            System.out.println("Part one results: " + day.partOne());
            System.out.println("Part two results: " + day.partTwo());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day12(ArrayList<String> lines) {
        this.lines = lines;
        reset();
    }
    
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.xDir = 1;
        this.yDir = 0;
        this.wayX = 10;
        this.wayY = 1;
    }
    
    public int partOne() {
        char action;
        int value;
        for (String instr : this.lines) {
            // Get the action required to take at each line along with the distance/angle.
            action = instr.charAt(0);
            value = Integer.parseInt(instr.substring(1));
            
            if (action == 'N') {
                this.y += value;
            } else if (action == 'S') {
                this.y -= value;
            } else if (action == 'E') {
                this.x += value;
            } else if (action == 'W') {
                this.x -= value;
            } else if (action == 'F') {
                // Move forward in the current facing direction by value.
                this.x += this.xDir * value;
                this.y += this.yDir * value;
            } else if (action == 'L') {
                // Rotate the direction vector by value.
                double dx = this.xDir * Math.cos(Math.toRadians(value)) - this.yDir * Math.sin(Math.toRadians(value));
                double dy = this.xDir * Math.sin(Math.toRadians(value)) + this.yDir * Math.cos(Math.toRadians(value));
                this.xDir = (int) dx;
                this.yDir = (int) dy;
            } else {
                // Rotate the direction vector by negative value, as we want clockwise rotation
                double dx = this.xDir * Math.cos(Math.toRadians(value)) + this.yDir * Math.sin(Math.toRadians(value));
                double dy = -this.xDir * Math.sin(Math.toRadians(value)) + this.yDir * Math.cos(Math.toRadians(value));
                this.xDir = (int) dx;
                this.yDir = (int) dy;
            }
        }
        return Math.abs(this.x) + Math.abs(this.y);
    }
    
    public int partTwo() {
        reset();
        char action;
        int value;
        for (String instr : this.lines) {
            // Get the action required to take at each line along with the distance/angle.
            action = instr.charAt(0);
            value = Integer.parseInt(instr.substring(1));

            if (action == 'N') {
                this.wayY += value;
            } else if (action == 'S') {
                this.wayY -= value;
            } else if (action == 'E') {
                this.wayX += value;
            } else if (action == 'W') {
                this.wayX -= value;
            } else if (action == 'F') {
                // Move the ferry to the waypoint the amount of times given by value. 
                int dx = (this.wayX - this.x) * value;
                int dy = (this.wayY - this.y) * value;
                this.x += dx;
                this.y += dy;
                this.wayX += dx;
                this.wayY += dy;
                
            } else if (action == 'L') {
                // Rotate the direction vector by value.
                double dx = (this.wayX - this.x) * Math.cos(Math.toRadians(value)) - (this.wayY - this.y) * Math.sin(Math.toRadians(value));
                double dy = (this.wayX - this.x) * Math.sin(Math.toRadians(value)) + (this.wayY - this.y) * Math.cos(Math.toRadians(value));
                this.wayX = (int) (this.x + Math.round(dx));
                this.wayY = (int) (this.y + Math.round(dy));
            } else {
                // Rotate the direction vector by negative value, as we want clockwise rotation
                double dx = (this.wayX - this.x) * Math.cos(Math.toRadians(value)) + (this.wayY - this.y) * Math.sin(Math.toRadians(value));
                double dy = -(this.wayX - this.x) * Math.sin(Math.toRadians(value)) + (this.wayY - this.y) * Math.cos(Math.toRadians(value));
                this.wayX = (int) (this.x + Math.round(dx));
                this.wayY = (int) (this.y + Math.round(dy));
            }
            System.out.println();
        }
        return Math.abs(this.x) + Math.abs(this.y);
    }
}