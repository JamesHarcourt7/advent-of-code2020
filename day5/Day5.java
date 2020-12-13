/**
 * Advent of Code Day 5
 * @author James Harcourt
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

 class Day5 {

     public static int partOne(ArrayList<String> lines) {
         int highestSeat = 0;
         for (String code : lines) {
             int seat = getSeat(code);
             if (seat > highestSeat) highestSeat = seat;
         }
         return highestSeat;
     }

     public static int getSeat(String code) {
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
         ArrayList<String> lines = new ArrayList<String>();
         String line;
         try {
             BufferedReader br = new BufferedReader(new FileReader("day5.txt"));
             while ((line = br.readLine()) != null) {
                 lines.add(line);
             }
             br.close();

             System.out.println("Part one results: " + partOne(lines));

         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
