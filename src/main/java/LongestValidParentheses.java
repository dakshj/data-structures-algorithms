import java.util.Stack;

class LongestValidParentheses {

    private int longestValidParentheses(final String s) {
        int maxLength = 0;
        int startIndex = 0;

        // Stores the indices of the characters, rather than the characters themselves
        final Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // When we encounter a ')'; BUT the stack is empty, it means that there is no
                // corresponding '(' for this current ')'. Thus, we make the startIndex to
                // the next element, which possibly CAN BE the start of a
                // valid sequence of parentheses.
                if (stack.isEmpty()) {
                    startIndex = i + 1;
                } else {
                    // When the stack is not empty, then we pop and discard the index of the
                    // previously pushed '(''s index.
                    stack.pop();

                    // Now, after discarding the corresponding '(' of the current ')'...
                    // If the stack is empty, then we know that this entire string
                    // from startIndex up to i is a valid parentheses string.
                    if (stack.isEmpty()) {
                        maxLength = Math.max(maxLength, i - startIndex + 1);
                    }

                    // If stack is not empty, then it means that there is a valid parentheses string
                    // from the NEXT ELEMENT of the index located at "stack.peek()"
                    // Thus, we set the current length to "i - stack.peek()"
                    // and compare it to "maxLength".
                    else {
                        maxLength = Math.max(maxLength, i - stack.peek());
                    }
                }
            }
        }

        return maxLength;
    }
}
