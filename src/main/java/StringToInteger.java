class StringToInteger {

    private int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        // Skip leading whitespaces
        while (str.charAt(0) == ' ') {
            str = str.substring(1);
        }

        boolean positive = true;
        boolean isSignSet = false;

        int result = 0;

        for (final char c : str.toCharArray()) {
            if (c == '+' || c == '-') {
                // Wrong format if more than one sign is provided
                if (isSignSet) {
                    return 0;
                }

                isSignSet = true;
                positive = c == '+';
            } else if (Character.isDigit(c)) {
                final int digit = Character.getNumericValue(c);

                // Check for overflow
                // Check if "result * 10" will cause an overflow
                if (result > Integer.MAX_VALUE / 10 ||

                        // Check if "+ digit" will cause an overflow
                        // even though "result * 10" succeeds.
                        // "7" because Integer.MAX_VALUE ends in 7
                        result == Integer.MAX_VALUE / 10 && digit > 7) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = result * 10 + digit;
            } else {
                break;
            }
        }

        return positive ? result : -1 * result;
    }
}
