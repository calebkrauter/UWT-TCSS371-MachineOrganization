import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        char[] bits = {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1',};
        int iterator = bits.length - 1;
        int iterateNext = iterator;
        System.out.println(bitFlipper(bits, iterator, iterateNext));
    }

    private static char[] bitFlipper(char[] theBits, int iterator, int iterateNext) {
        // Base case
        if (iterateNext != 0) {
            iterateNext--;
            // find the first 1 from the right
            if (theBits[iterator] == '1') {
                // Check for '1' and flip bit
                if (theBits[iterateNext] == '1') {
                    theBits[iterateNext] = '0';

                    // Repeat a check for the next bit and flip it.
                    return bitFlipper(theBits, iterator, iterateNext);
                } else {
                    // Flip bit
                    theBits[iterateNext] = '1';
                    return bitFlipper(theBits, iterator, iterateNext);
                }
            } else {
                // Found a zero, iterate and look for the next 1
                iterator--; // i = 2
                return bitFlipper(theBits, iterator, iterateNext);
            }
        }
        return theBits;
    }
}