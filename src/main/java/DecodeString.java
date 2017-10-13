import java.util.Stack;

public class DecodeString {

    private static String decodeString(final String s) {
        final Stack<Integer> muls = new Stack<>();
        final Stack<StringBuilder> res = new Stack<>();

        res.push(new StringBuilder());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                final int start = i;
                while (Character.isDigit(s.charAt(i + 1))) {
                    i++;
                }
                muls.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (c == '[') {
                res.push(new StringBuilder());
            } else if (c == ']') {
                final StringBuilder curRes = res.pop();
                final StringBuilder curResMultiplied = new StringBuilder();
                final int curMul = muls.pop();
                for (int j = 0; j < curMul; j++) {
                    curResMultiplied.append(curRes);
                }
                res.push(res.pop().append(curResMultiplied));
            } else {
                res.push(res.pop().append(c));
            }
        }

        return res.pop().toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("a3[b2[c1[d]]]e"));
        System.out.println(decodeString("2[2[b]]"));
    }
}
