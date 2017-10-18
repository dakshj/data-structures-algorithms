import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

    private int longestConsecutive(int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;

        for (final int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }

            final int leftSize = map.getOrDefault(num - 1, 0);
            final int rightSize = map.getOrDefault(num + 1, 0);

            final int currentSize = leftSize + 1 + rightSize;

            max = Math.max(max, currentSize);

            map.put(num, currentSize);

            // If leftSize and rightSize are 0, then the below two lines will have no effect

            // If not, then go to the current left and right boundary elements,
            // and update the currentSize
            map.put(num - leftSize, currentSize);
            map.put(num + rightSize, currentSize);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence()
                .longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
