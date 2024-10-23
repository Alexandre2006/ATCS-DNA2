import java.util.Arrays;

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
    public static int STRCount(String sequence, String STR) {
        // Keep track of current & longest repetition
        int longestRepetition = 0;
        int currentRepetition = 0;

        // Uppercase both strings
        sequence = sequence.toUpperCase();
        STR = STR.toUpperCase();

        // Calculate STR Hash
        Hash STRHash = new Hash(STR);

        // Calculate initial sequence hash
        SequenceHash sequenceHash = new SequenceHash(sequence, STR.length());

        // Count longest sequence!
        while (true) {
            try {
                // Match found!
                if (STRHash.getHash() == sequenceHash.getHash()) {
                    // Increment longest repetition
                    currentRepetition++;

                    // Move sequence hash forward
                    sequenceHash.incrementBySTR();
                } else { // No match found
                    // Reset counter
                    longestRepetition = Math.max(currentRepetition, longestRepetition);
                    currentRepetition = 0;

                    // Increment sequence hash by 1
                    sequenceHash.incrementByOne();
                }
            } catch (IndexOutOfBoundsException e) {
                // We have reached the end of the sequence!
                break;
            }
        }

        return Math.max(currentRepetition, longestRepetition);
    }

    private static class Hash{
        // Config
        protected static final int P_VALUE = 1000000787;
        protected static final int R = 85; // T - highest value expected - is the 85th character on the ASCII table

        protected long hash;
        protected final String value;

        // Getters
        public long getHash() {
            return hash;
        }

        // Hash Methods
        protected long calculateHash(int startIndex, int endIndex) {
            long sum = 0;

            for (int i = startIndex; i < endIndex; i++) {
                sum = (sum * R + value.charAt(i)) % P_VALUE;
            }

            return sum % P_VALUE;
        }

        protected long getPow(int pow) {
            if (pow == 0) {
                return 1;
            }

            long sum = R;
            for (int i = 1; i < pow; i++) {
                sum *= R;
                sum %= P_VALUE;
            }

            return sum;
        }

        // Constructor
        Hash(String value) {
            this.value = value;
            hash = calculateHash(0, value.length());
        }
    }

    private static class SequenceHash extends Hash {
        private int currentPosition = 0;
        private final int STRLength;
        private long[] precalculatedPowers;

        // Constructor
        SequenceHash(String value, int STRLength) {
            super(value);
            this.STRLength = STRLength;
            calculatePowers(STRLength);
            hash = calculateHash(0, STRLength);
        }

        // Powers
        private void calculatePowers(int maxPower) {
            precalculatedPowers = new long[maxPower + 1];

            for (int i = 0; i <= maxPower; i++) {
               // Power of 0 is always 1
                if (i == 0) {
                    precalculatedPowers[i] = 0;
                } else {
                    // Calculate power
                    precalculatedPowers[i] = R;

                    for (int x = 1; x < i; x++) {
                        precalculatedPowers[i] *= R;
                        precalculatedPowers[i] %= P_VALUE;
                    }
                }
            }
        }

        @Override
        protected long getPow(int pow) {
            return precalculatedPowers[pow];
        }

        // Increment Hash (1 forward)
        public void incrementByOne() {
            // Subtract first value (not sure if this is the most effective way to prevent negatives, but this is what worked for me)
            hash = (hash - (value.charAt(currentPosition) * getPow(STRLength - 1)) % P_VALUE + P_VALUE) % P_VALUE;

            // Multiply by R
            hash *= R;

            // Add hash for last digit
            hash += value.charAt(currentPosition + STRLength);

            // Mod
            hash = (hash + P_VALUE) % P_VALUE;

            // Increment position
            currentPosition += 1;
        }

        // Move forward STR length
        public void incrementBySTR() {
            // Increment position
            currentPosition += STRLength;

            // Update hash
            hash = calculateHash(currentPosition, currentPosition + STRLength);
        }
    }
}
