/**
 * Advent of Code Day 5
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

 class Day5 {

     public static int partOne(ArrayList<String> lines) {
         // Keep track of the highest seat so far.
         int highestSeat = 0;
         for (String code : lines) {
             int seat = getSeat(code);
             if (seat > highestSeat) highestSeat = seat;
         }
         return highestSeat;
     }

     public static int partTwo(ArrayList<String> lines) {
         ArrayList<Integer> seats = new ArrayList<Integer>();
         for (String code : lines) {
            seats.add(getSeat(code));
         }
         // Put the seats into ascending order.
         // Our seat is that which is missing from the range seats[0] - seats[-1]
         Collections.sort(seats);
         int count = 0;
         for (int i = 0; i < seats.size(); i ++) {
             // Keep a counter to compare to the seat number.
             // If a number is missed from the order, that must be our seat.
             if (count == 0) count = seats.get(i);
             if (count != seats.get(i)) return count;
             count ++;
         }
         return 0;
     }

     public static int getSeat(String code) {
         // Looking at the seat codes, the first 7 characters are the row number in unsigned binary
         // while the last 3 characters are the column number in unsigned binary.
         // Finding the seat id is a matter of converting the strings to unsigned integers and doing
         // seat = (row * 8) + column.
         String bincode = "";
         for (int i = 0; i < 7; i ++) {
             bincode += code.substring(i, i + 1).equals("B") ? "1" : "0";
         }
         int row = Byte.toUnsignedInt(Byte.parseByte(bincode, 2));
         bincode = "";
         for (int i = 0; i < 3; i ++) {
             bincode += code.substring(7 + i, 7 + i + 1).equals("R") ? "1" : "0";
         }
         int column = Byte.toUnsignedInt(Byte.parseByte(bincode, 2));
         return (row * 8) + column;
     }

     public static void main(String[] args) {
         // Create array list to store the contents of the input file for easy access and reuse.
         ArrayList<String> lines = new ArrayList<String>();
         String line;
         try {
             // Create buffered reader to parse input file.
             BufferedReader br = new BufferedReader(new FileReader("day5.txt"));
             while ((line = br.readLine()) != null) {
                 lines.add(line);
             }
             br.close();

             System.out.println("Part one results: " + partOne(lines));
             System.out.println("Part two results: " + partTwo(lines));

         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
