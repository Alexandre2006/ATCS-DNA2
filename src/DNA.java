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

        // Loop through each character in the sequence
        for (char letter : sequence.toCharArray()) {
            if (letter == STR.charAt(STRPosition)) {
                // Increase STR Position
                STRPosition++;

                // Check for STR cycle
                if (STRPosition >= STR.length()) {
                    currentRepeats++;
                    STRPosition = 0;
                }
            } else {
                STRPosition = 0;

                // Update repetitions
                if (currentRepeats > greatestRepeats) {
                    greatestRepeats = currentRepeats;
                }

                // Reset current repetitions
                currentRepeats = 0;
            }
        }

        return Math.max(currentRepeats, greatestRepeats);
    }
}
