import java.util.Comparator;

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
    public static int STRCount(String sequenceString, String STRString) {
        // Keep track of current & longest repetition
        int longestRepetition = 0;
        int currentRepetition = 0;

        // Convert to DNASequence
        DNASequence sequence = new DNASequence(sequenceString);
        DNASequence STR = new DNASequence(STRString);

        // Calculate STR Hash
        Hash STRHash = new Hash(STR.getSequence());


        // Calculate initial sequence hash
        SequenceHash sequenceHash = new SequenceHash(sequence.getSequence(), sequenceString.length());

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

    private static class DNASequence {
        private final byte[] sequence;
        private byte[] conversionMap;

        // Getters
        public byte[] getSequence() {
            return sequence;
        }

        // Tools
        private void fillConversionMap() {
            conversionMap = new byte[128];

            // Fill with values
            conversionMap['A'] = 0;
            conversionMap['C'] = 1;
            conversionMap['T'] = 2;
            conversionMap['G'] = 3;

            conversionMap['a'] = 0;
            conversionMap['c'] = 1;
            conversionMap['t'] = 2;
            conversionMap['g'] = 3;
        }

        // Constructor
        DNASequence(String DNAString) {
            // Configure conversion map (string to bytes)
            fillConversionMap();

            // Convert DNA Sequence
            sequence = new byte[DNAString.length()];

            for (int i = 0; i < sequence.length; i++) {
                sequence[i] = conversionMap[DNAString.charAt(i)];
            }
        }
    }

    private static class Hash{
        // Config
        protected final int P_VALUE = 1000000787;

        protected int hash;
        protected final byte[] value;

        // Getters
        public int getHash() {
            return hash;
        }

        // Hash Methods
        protected int calculateHash(int startIndex, int endIndex) {
            int sum = 0;

            for (int i = startIndex; i < endIndex; i++) {
                sum += (value[i] * (int) Math.pow(4, endIndex - i)) % P_VALUE;
            }

            return sum % P_VALUE;
        }


        // Constructor
        Hash(byte[] value) {
            this.value = value;
            hash = calculateHash(0, value.length);
        }
    }

    private static class SequenceHash extends Hash {
        private int currentPosition = 0;
        private final int STRLength;

        // Constructor
        SequenceHash(byte[] value, int STRLength) {
            super(value);
            this.STRLength = STRLength;
            hash = calculateHash(0, STRLength);
        }

        // Increment Hash (1 forward)
        public int incrementByOne() {
            // Subtract first value
            hash -= (int) (value[currentPosition] * Math.pow(4, STRLength));

            // Multiply by R (4)
            hash *= 4;

            // Add hash for last digit
            hash += value[currentPosition + STRLength];

            // Mod
            hash %= P_VALUE;

            // Increment position
            currentPosition += 1;

            return hash;
        }

        // Move forward STR length
        public int incrementBySTR() {
            // Increment position
            currentPosition += STRLength;

            // Update hash
            hash = calculateHash(currentPosition, currentPosition + STRLength);

            // Return new hash
            return hash;
        }
    }
}
