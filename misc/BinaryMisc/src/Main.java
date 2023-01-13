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
        char[] binaryValue5 = new char[]{'1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0'};
        char[] binaryValue6 = new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0'};
        System.out.println(convert2sCompToDecimal(binaryValue1));
        System.out.println(convert2sCompToDecimal(binaryValue2));
        System.out.println(convert2sCompToDecimal(binaryValue3));
        System.out.println(convert2sCompToDecimal(binaryValue4));
        System.out.println(convert2sCompToDecimal(binaryValue5));
        System.out.println(convert2sCompToDecimal(binaryValue6));

//        System.out.println("Convert this number 0 to binary");
//        System.out.println(Arrays.toString(convertDecimalTo2sComp(0)));
//        System.out.println("Convert this number 1 to binary");
//        System.out.println(Arrays.toString(convertDecimalTo2sComp(1)));
//        for (int i = 5; i < 7; i++) {
//            int initialDecimal = (int) Math.pow(i, i);
//            System.out.println("Convert this number " + initialDecimal + " to binary");
//            System.out.println(Arrays.toString(convertDecimalTo2sComp(initialDecimal)));
//        }

        // Uncomment this block of code to test exception thrown in convertDecimalTo2sComp.
//        int initialDecimal = (int) Math.pow(8, 8);
//        System.out.println("Convert this number " + initialDecimal + " to binary");
//        System.out.println(Arrays.toString(convertDecimalTo2sComp(initialDecimal)));



    }

    // TODO - deal with overflow.
    public static int convert2sCompToDecimal(char[] theBits) {
        int bitAmount = theBits.length;
        int decValue = 2;
        int newDecValue = 0;
        System.out.println(Arrays.toString(theBits));
        boolean isNegative = false;
        if (theBits[0] == '1') {
            isNegative = true;
        }
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
                // if char is 0 don't add converted value. If 1, do summation and power
                if (theBits[bitIndex] == '1') {
                    newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
                }

                // Convert to 2's comp and get negative decimal
            // Convert to negative by multiplying decimal by two and subtracting from initial decimal

                // Checks if the first bit is '1' and if it is then it takes 2's complement.

                if (theBits[0] == '1') {

                    // checks from right to left for a bit equalling '1'
                    // TODO - either figure out how to stop doing this check when '1' is found or use the second method
                    //        being flip all bits and add '1'.
                    int flipperIndex = bitIndex;

                        // Finds the first '1' closest to the right
                        if (theBits[bitIndex] == '1') {
                            while (flipperIndex != 0) {

                            // Checks for the next bit after the first '1' found.
                            if (theBits[flipperIndex - 1] == '1') {
                                flipperIndex--;
                                // Flips every 1 bit immediately after the first 1 to 0
                                theBits[flipperIndex] = '0';

                                // If the bit is '0'
                            } else {
                                flipperIndex--;
                                theBits[flipperIndex] = '1';
                            }
                        }
                    }

                    if (theBits[bitIndex] == '1') {
                        newDecValue = 0;
                        newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
                    }
                }
        }

        if (isNegative) {
            return - newDecValue;
        }
        return newDecValue;

    }

    // Consider using recursion. That  might fit best.
    public static char[] convertDecimalTo2sComp(int theDecimal) {


        // TODO ensure that overflow is impossible.
        // Idea- convert binary back to dec and if the dec does not equal the converted binary then it can't be represented in 16 bits.

        char[] arrayOfBits = new char[16];
        int bitAmount = arrayOfBits.length;
        int dividend = 0;


        // TODO - store the dividend at each step and reuse the next dividend
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            int value;

            if (bitIndex == bitAmount - 1) {
                value = theDecimal;
            } else {
                value = dividend;
            }

            if (value % 2 == 0){
                dividend = value / 2;
                arrayOfBits[bitIndex] = '0';

            } else if (value % 2 == 1){
                // Subtract 1 from theDecimal to ensure that the value that is used is rounded to the floor which integer division should already do in java.
                dividend = (value - 1) / 2;
                arrayOfBits[bitIndex] = '1';
            }

        }

        // TODO - check if this is where exception should be thrown.
        if (theDecimal != convert2sCompToDecimal(arrayOfBits)) {
            throw new IllegalArgumentException("Decimal converted to 2's complement is "
                    + theDecimal + ". Using 16 bit binary, the decimal converted back to decimal is recorded as "
                    + convert2sCompToDecimal(arrayOfBits) + ".\nThe initial decimal does not fit in 16 bits.");
        }



        return arrayOfBits;
        // TODO - deal with bit extention for 1s or 0s as necessary.
//        for (int bitIndex = 0; bitIndex < 16; bitIndex++) {
//            if (arrayOfBits[bitIndex].equals(null)) {
//                arrayOfBits[bitIndex] = 0;
//            }
//        }

    }
}