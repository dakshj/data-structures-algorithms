import java.util.Stack;

public class LongestAbsoluteFilePath {

    private int lengthLongestPath(final String input) {
        final Stack<Integer> stackOfPathLengthAtLevel = new Stack<>();
        int maxLength = 0;

        // Init length of root dir at Level 0 to 0; will be calculated below in the for loop
        stackOfPathLengthAtLevel.push(0);

        for (final String curr : input.split("\n")) {
            // Get the level of the current String based on the no. of '\t's
            // (Level of Root dir = Level 0)
            int level = 0;
            while (curr.charAt(level) == '\t') {
                level++;
            }

            // Go to correct parent in stack and discard previous stack entries of the
            // same or higher level
            // (since they have already been accounted for during maxLength calculation).
            // Example: If level is 1, then parent is at level 0 (root)
            // Thus, to make stack size = 1, we need to pop stack until next stack element is
            // of Level 0.
            while (stackOfPathLengthAtLevel.size() > level + 1) {
                stackOfPathLengthAtLevel.pop();
            }

            final int parentLength = stackOfPathLengthAtLevel.peek();

            int currAbsoluteLength =
                    // Absolute length of parent
                    stackOfPathLengthAtLevel.peek()
                            // Add a leading "/" only if there is a parent
                            // (i.e. root dir will have no leading "/")
                            + (parentLength != 0 ? 1 : 0)
                            // Length of current string (excluding leading '\t's)
                            + curr.length() - level;

            stackOfPathLengthAtLevel.push(currAbsoluteLength);

            // If curr is a file, i.e. if it has an extension thus will need to have a "."
            if (curr.contains(".")) {
                maxLength = Math.max(maxLength, currAbsoluteLength);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        final String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1" +
                "\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath(input));
    }
}
