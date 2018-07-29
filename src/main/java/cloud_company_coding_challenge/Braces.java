package cloud_company_coding_challenge;

import java.util.Stack;

/**
 * Check if () {} [] braces are balanced or not
 */
class Braces {

    static String[] braces(String[] values) {
        if (values == null) return null;

        final String[] result = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            if (isBalanced(values[i])) {
                result[i] = "YES";
            } else {
                result[i] = "NO";
            }
        }

        return result;
    }

    private static boolean isBalanced(final String line) {
        if (line == null || line.isEmpty()) return true;

        // Odd amount of braces are always unbalanced
        if (line.length() % 2 != 0) return false;

        final Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            if (isOpeningBrace(line.charAt(i))) {
                stack.push(line.charAt(i));
            } else {
                if (stack.isEmpty() || !matchesPartner(stack.pop(), line.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isOpeningBrace(final char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private static boolean matchesPartner(final char open, final char close) {
        //noinspection SimplifiableIfStatement
        if (open == ')' || open == '}' || open == ']') return false;

        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}
