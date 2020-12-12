/**
 * Advent of Code Day 4
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {

    // Store the fields as a byte, as there are 8 fields that we check are present and valid.
    // For example, pid is one of the bits, either valid or not.
    byte fields;
    ArrayList<String> lines;

    public Day4(ArrayList<String> lines) {
        this.fields = 0;
        this.lines = lines;
    }

    public Boolean checkFields() {
        // If all fields are valid, the byte should be 11111111 (2) = 255 (10).
        Boolean val = Byte.toUnsignedInt(this.fields) == 255;
        this.fields = 0;
        return val;
    }

    public String getValue(String line, String key) {
        // Loop through each key:value pair in the line until find the right one.
        // Then find the index of the ':' and take the value part to return.
        for (String s : line.split(" ")) {
            if (s.contains(key)) {
                for (int i = 0; i < s.length(); i ++) {
                    if (s.charAt(i) == ':') return s.substring(i + 1);
                }
            }
        }
        return "";
    }

    public int partOne() {
        // Count how many passports are valid.
        int count = 0;
        for (String line : this.lines) {
            if (line.length() == 0) {
                if (checkFields()) count ++;
            } else {
                // Flip a bit if field is present by doing a bitwise OR operation.
                if (line.contains("byr")) this.fields |= 128;
                if (line.contains("iyr")) this.fields |= 64;
                if (line.contains("eyr")) this.fields |= 32;
                if (line.contains("hgt")) this.fields |= 16;
                if (line.contains("hcl")) this.fields |= 8;
                if (line.contains("ecl")) this.fields |= 4;
                if (line.contains("pid")) this.fields |= 2;
                this.fields |= 1; // do not require cid.
            }
        }
        return count;
    }

    public int partTwo() {
        // Count how many passports are valid.
        int count = 0;
        int value;
        for (String line : this.lines) {
            if (line.length() == 0) {
                if (checkFields()) count ++;
            } else {
                // Flip a bit if field is present by doing a bitwise OR operation.
                if (line.contains("byr")) {
                    // Convert to int and check value is in range.
                    value = Integer.parseInt(getValue(line, "byr"));
                    if (1920 <= value && value <= 2002) this.fields |= 128;
                }
                if (line.contains("iyr")) {
                    // Convert to int and check value is in range.
                    value = Integer.parseInt(getValue(line, "iyr"));
                    if (2010 <= value && value <= 2020) this.fields |= 64;
                };
                if (line.contains("eyr")) {
                    // Convert to int and check value is in range.
                    value = Integer.parseInt(getValue(line, "eyr"));
                    if (2020 <= value && value <= 2030) this.fields |= 32;
                }
                if (line.contains("hgt")) {
                    String height = getValue(line, "hgt");
                    // Separate value from unit.
                    String unit = height.substring(height.length() - 2);
                    if (unit.equals("cm")) {;
                        // Convert to int and check value is in range.
                        value = Integer.parseInt(height.substring(0, height.length() - 2));
                        if (150 <= value && value <= 193) this.fields |= 16;
                    } else if (unit.equals("in")) {
                        // Convert to int and check value is in range.
                        value = Integer.parseInt(height.substring(0, height.length() - 2));
                        if (59 <= value && value <= 76) this.fields |= 16;
                    }
                }
                if (line.contains("hcl")) {
                    // Match string to regex.
                    // '#' followed by 6 0-9 or a-f characters.
                    String colour = getValue(line, "hcl");
                    if (colour.matches("#[0-9a-f]{6}")) {
                        this.fields |= 8;
                    }
                }
                if (line.contains("ecl")) {
                    // Match string to regex.
                    // One of the 3 letter colour abbreviations.
                    String colour = getValue(line, "ecl");
                    if (colour.matches("amb|blu|brn|gry|grn|hzl|oth")) {
                        this.fields |= 4;
                    }
                }
                if (line.contains("pid")) {
                    // Match string to regex.
                    // 9 digits 0-9.
                    String id = getValue(line, "pid");
                    if (id.matches("[0-9]{9}")) {
                        this.fields |= 2;
                    }
                }
                this.fields |= 1; // do not require cid.
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Store each line in the file as an array list for easy access and reuse.
        ArrayList<String> lines = new ArrayList<String>();
        // Declare a Day4 object that will take the array list as parameters to its constructor.
        Day4 day;
        String line;
        try {
            // Create buffered reader to read puzzle input from file.
            BufferedReader br = new BufferedReader(new FileReader("day4.txt"));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Add a blank line to the end, else it will not count the last passport.
            lines.add("");
            day = new Day4(lines);
            System.out.println("Part one results: " + day.partOne());
            System.out.println("Part two results: " + day.partTwo());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
