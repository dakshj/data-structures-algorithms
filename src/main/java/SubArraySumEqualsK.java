import java.util.HashMap;
import java.util.Map;

class SubArraySumEqualsK {

    private int subarraySum(final int[] nums, final int k) {
        int result = 0;

        // Stores cumulative sum vs. count of occurrences
        final Map<Integer, Integer> map = new HashMap<>();

        // TODO why do we init the map with this?
        map.put(0, 1);

        int cumulativeSum = 0;

        for (final int num : nums) {
            cumulativeSum += num;

            // Add the occurrences to the result
            // This means that we add the total no. of subarrays that are equal to cumulativeSum - k,
            // thus having multiple subarrays (which are the elements except the overlap
            // of the previous subarrays and the current subarray)
            result += map.getOrDefault(cumulativeSum - k, 0);

            // Increment count of occurrences
            map.put(cumulativeSum, map.getOrDefault(cumulativeSum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubArraySumEqualsK().subarraySum(new int[]{1, 2, 3, 4}, 3));
    }
}
