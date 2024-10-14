import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int STRPosition = 0;

        // Track repetitions
        int greatestRepeats = 0;
        int currentRepeats = 0;

        // Iterate through sequence
        for (int i = 0; i < sequence.length(); i++) {
            // Check if the current character matches the STR
            if (sequence.charAt(i) == STR.charAt(STRPosition)) {
                STRPosition++;

                // If we have finished STR, reset STRPosition and increment currentRepeats
                if (STRPosition == STR.length()) {
                    STRPosition = 0;
                    currentRepeats++;
                }
            } else {
                // Save the currentRepeats if it is greater than the greatestRepeats
                if (currentRepeats > greatestRepeats) {
                    greatestRepeats = currentRepeats;
                }

                // Reset currentRepeats and STRPosition
                currentRepeats = 0;
                STRPosition = 0;
            }
        }

        return greatestRepeats;
    }
//    private class Sequence {
//        byte[] sequence;
//
//        public Sequence(String sequence) {
//            ArrayList<Byte> sequenceList = new ArrayList<>();
//
//            // Convert to byte array list
//            for (char letter : sequence.toCharArray()) {
//                if (letter == 'a' || letter == 'A') {
//                    sequenceList.add((byte) 0);
//                } else if (letter == 'c' || letter == 'C') {
//                    sequenceList.add((byte) 1);
//                } else if (letter == 'g' || letter == 'G') {
//                    sequenceList.add((byte) 2);
//                } else if (letter == 't' || letter == 'T') {
//                    sequenceList.add((byte) 3);
//                }
//            }
//
//            // Convert arraylist to array
//            this.sequence = new byte[sequenceList.size()];
//            for (int i = 0; i < sequenceList.size(); i++) {
//                this.sequence[i] = sequenceList.get(i);
//            }
//        }
//    }
}
