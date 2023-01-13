import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // TODO - Make sure conversion in both directions accounts for the case 0
        // TODO - make sure to limit the amount of binary digits to 16
        // TODO - check to see if converting from or too 2's complement requires +/- signs to be dealt with.

        // Make a function to generate random binary numbers.
        char[] binaryValue1 = new char[]{'0', '0', '1', '0'};
        char[] binaryValue2 = new char[]{'0', '1', '1', '0'};
        char[] binaryValue3 = new char[]{'1', '1', '1', '0', '0', '1', '1', '1'};
        char[] binaryValue4 = new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'};
        char[] binaryValue5 = new char[]{'0', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0'};
        char[] binaryValue6 = new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0'};
        System.out.println("Convert " + Arrays.toString(binaryValue1) + " to | " + convert2sCompToDecimal(binaryValue1));
        System.out.println("Convert " + Arrays.toString(binaryValue2) + " to | " + convert2sCompToDecimal(binaryValue2));
        System.out.println("Convert " + Arrays.toString(binaryValue3) + " to | " + convert2sCompToDecimal(binaryValue3));
        System.out.println("Convert " + Arrays.toString(binaryValue4) + " to | " + convert2sCompToDecimal(binaryValue4));
        System.out.println("Convert " + Arrays.toString(binaryValue5) + " to | " + convert2sCompToDecimal(binaryValue5));
        System.out.println("Convert " + Arrays.toString(binaryValue6) + " to | " + convert2sCompToDecimal(binaryValue6));

        // Uncomment for conversion independent flipper test
//            int iterator = binaryValue5.length - 1;
//            int iterateNext = iterator;
//            char[] flippedBits = bitFlipper(binaryValue5, iterator, iterateNext);
//            System.out.println(flippedBits);
        // Uncomment this block of code to test exception thrown in convert2sCompToDecimal.
//        char[] binaryValue7 = new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0', '0'};
//        System.out.println(convert2sCompToDecimal(binaryValue7));


        System.out.print("Convert 0 to binary | ");
        System.out.println(Arrays.toString(convertDecimalTo2sComp(0)));
        System.out.print("Convert -1 to binary | ");
        System.out.println(Arrays.toString(convertDecimalTo2sComp(-1)));
        System.out.print("Convert -100 to binary | ");
        System.out.println(Arrays.toString(convertDecimalTo2sComp(-100)));
        System.out.print("Convert -50 to binary | ");
        System.out.println(Arrays.toString(convertDecimalTo2sComp(-50)));

        for (int i = 5; i < 6; i++) {
            int initialDecimal = (int) Math.pow(i, i);
            System.out.print("Convert this number " + initialDecimal + " to binary | ");
            System.out.println(Arrays.toString(convertDecimalTo2sComp(initialDecimal)));
        }

        System.out.print("Convert -32768 to binary | ");
        System.out.println(Arrays.toString(convertDecimalTo2sComp(-32768)));
        // Uncomment this block of code to test exception thrown in convertDecimalTo2sComp.
//        int initialDecimal = (int) Math.pow(8, 8);
//        System.out.println("Convert this number " + initialDecimal + " to binary");
//        System.out.println(Arrays.toString(convertDecimalTo2sComp(initialDecimal)));



    }

    public static int convert2sCompToDecimal(char[] theBits) {
        if (theBits.length > 16) {
            throw new IllegalArgumentException("Length of the array of bits is larger than 16. Length: " + theBits.length);
        }
        int bitAmount = theBits.length;
        int decValue = 2;
        int newDecValue = 0;
        char[] flippedBits = theBits;
        boolean isNegative = false;
        if (theBits[0] == '1') {
            isNegative = true;
        }
        if (isNegative) {
            int iterator = theBits.length - 1;
            int iterateNext = iterator;

            flippedBits = bitFlipper(theBits, iterator, iterateNext);
            theBits = flippedBits;
        }

        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
                // if char is 0 don't add converted value. If 1, do summation and power
                if (theBits[bitIndex] == '1') {
                    newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
                }
        }
        if (isNegative) {
            return - newDecValue;
        }
        return newDecValue;

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


    // Consider using recursion. That  might fit best.
    public static char[] convertDecimalTo2sComp(int theDecimal) {
        char[] theBits = new char[16];
        int bitAmount = theBits.length;
        int dividend = 0;
        int magnitudeOfTheDecimal = Math.abs(theDecimal);
        char[] flippedBits;

        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            int value;

            if (bitIndex == bitAmount - 1) {
                value = magnitudeOfTheDecimal;
            } else {
                value = dividend;
            }

            if (value % 2 == 0) {
                dividend = value / 2;
                theBits[bitIndex] = '0';

            } else if (value % 2 == 1) {
                // Subtract 1 from the decimal to ensure that the value that is used is rounded to the floor which integer division should already do in java.
                dividend = (value - 1) / 2;
                theBits[bitIndex] = '1';
            }
        }
        if (theDecimal < 0) {
            int iterator = theBits.length - 1;
            int iterateNext = iterator;

            flippedBits = bitFlipper(theBits, iterator, iterateNext);
            theBits = flippedBits;
        }
        System.out.println("The decimal" + theDecimal);

        return theBits;
        // TODO - deal with bit extention for 1s or 0s as necessary.
//        for (int bitIndex = 0; bitIndex < 16; bitIndex++) {
//            if (theBits[bitIndex].equals(null)) {
//                theBits[bitIndex] = 0;
//            }
//        }
    }
}
