import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

    public String encode(final List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }

        return sb.toString();
    }

    public List<String> decode(final String s) {
        final List<String> result = new ArrayList<>();
        int startIndex = 0;

        while (startIndex < s.length()) {
            final int slash = s.indexOf('/', startIndex);
            final int size = Integer.parseInt(s.substring(startIndex, slash));

            result.add(s.substring(slash + 1, slash + size + 1));
            startIndex = slash + size + 1;
        }

        return result;
    }
}
