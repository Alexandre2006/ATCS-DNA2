import java.util.LinkedList;
import java.util.Queue;

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
        return 0;
    }

    private class DNASequence {
        private byte[] sequence;
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

    private static class Hasher {
        // Config
        private int P_VALUE = 1000000787;

        private int hash;
        private byte[] value;

        // Getters
        public int getHash() {
            return hash;
        }

        // Hash Methods
        public void calculateHash(int startIndex, int endIndex) {
            int sum = 0;

            for (int i = startIndex; i < endIndex; i++) {
                sum += (value[i] * (int) Math.pow(4, endIndex - i)) % P_VALUE;
            }

            hash = sum % P_VALUE;
        }

        // Constructor
        Hasher(byte[] value) {
            this.value = value;
            calculateHash(0, value.length);
        }
    }

    private static class SequenceHasher extends Hasher {
        private int currentPosition = 0;
        private int STRLength;
        private byte[] original;

        // Constructor
        SequenceHasher(byte[] value, int STRLength) {
            super(value);
            this.STRLength = STRLength;
            original = value;
            super.calculateHash(0, STRLength);
        }

        // Increment Hash (1 forward)
        public int incrementByOne() {
            
        }
    }
}
