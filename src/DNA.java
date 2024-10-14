/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Alexandre Haddad-Delaveau
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

        // Log selected sequence + STR
        System.out.println("Sequence: " + sequence);
        System.out.println("STR: " + STR);

        // Loop through each character in the sequence
        for (int position = 0; position < sequence.length(); position++) {
            // Get current letter
            char letter = sequence.charAt(position);

            // Log character
            System.out.print(letter);
            if (letter == STR.charAt(STRPosition)) {

                // Increase STR Position
                STRPosition++;

                // Check for STR cycle
                if (STRPosition >= STR.length()) {
                    currentRepeats++;
                    STRPosition = 0;

                    // Log increment
                    System.out.println(" - STR found! Current repeats: " + currentRepeats);
                }
            } else {
                // Recovery from repeated letters
                boolean falseDetection = false;
                for (int i = 0; i < STRPosition; i++) {
                    if (sequence.startsWith(STR, position - i)) {
                        falseDetection = true;
                        break;
                    }
                }

                if (!falseDetection) {
                    STRPosition = 0;

                    // Update repetitions
                    if (currentRepeats > greatestRepeats) {
                        greatestRepeats = currentRepeats;
                    }

                    // Reset current repetitions
                    currentRepeats = 0;
                }

            }
        }

        return Math.max(currentRepeats, greatestRepeats);
    }
}
