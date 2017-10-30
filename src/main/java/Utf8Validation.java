class Utf8Validation {

    private boolean validUtf8(final int[] data) {
        if (data == null || data.length == 0) return false;

        for (int i = 0; i < data.length; i++) {
            final int n = data[i];

            // Base Case: Check if within 8 bits
            if (n > 255) {
                // 256 or more needs 9 bits to be represented
                return false;
            }

            int numBytes;

            // Check if the left-most bit is a 0
            if ((n & 128) == 0) {
                // Rule 1.
                numBytes = 1;
            }

            // n & 1110 0000 == 1100 0000
            else if ((n & 224) == 192) {
                numBytes = 2;
            }

            // n & 1111 0000 == 1110 0000
            else if ((n & 240) == 224) {
                numBytes = 3;
            }

            // n & 1111 1000 == 1111 0000
            else if ((n & 248) == 240) {
                numBytes = 4;
            }

            // Exceeds 4 bytes (Sure?)
            else {
                return false;
            }

            // Rule 2. Check if the next n-1 bytes start with 10 (binary)
            for (int j = 1; j < numBytes; j++) {
                // Exceeds amount of bytes provided in the data[] array
                if (i + j >= data.length) {
                    return false;
                }

                // n & 1100 0000 != 1000 0000
                if ((data[i + j] & 192) != 128) {
                    return false;
                }
            }

            // Directly go to next n-byte UTF-8 Char
            i = i + numBytes - 1;
        }

        return true;
    }

    public static void main(String[] args) {
        final Utf8Validation val = new Utf8Validation();

        System.out.println(val.validUtf8(new int[]{197, 130, 1}));
        System.out.println(val.validUtf8(new int[]{235, 140, 4}));
    }
}
