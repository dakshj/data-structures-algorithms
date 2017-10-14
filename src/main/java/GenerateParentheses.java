import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    private List<String> generateParentheses(final int n) {
        final List<String> result = new ArrayList<>();
        generateParentheses(n, result, "", 0, 0);
        return result;
    }

    private void generateParentheses(final int n, final List<String> result, final String temp,
            final int open, final int close) {
        // When temp has n opening and n closing parentheses
        if (temp.length() == n * 2) {
            result.add(temp);
            return;
        }

        // If more open braces can be added to temp
        if (open < n) {
            generateParentheses(n, result, temp + '(', open + 1, close);
        }

        // If we need to balance opening parentheses with closing parentheses
        // (i.e., in later recursions when "open+1" becomes greater than close)
        if (close < open) {
            generateParentheses(n, result, temp + ')', open, close + 1);
        }
    }

    public static void main(String[] args) {
        new GenerateParentheses().generateParentheses(3).forEach(System.out::println);
    }
}
