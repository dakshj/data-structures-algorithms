import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{5, 3, -5, 0, 1, -1, -1, -7, 2}));
    }

    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Since 3 elements are required
        if (nums == null || nums.length < 3) return result;

        final int length = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        Arrays.sort(nums);

        for (int i = 0; i < length - 2; i++) {
            map.clear();

            // Either we are at the 0th element, or the current element is != previous
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < length; j++) {
                    List<Integer> points = new ArrayList<>();
                    if (map.containsKey(nums[j])) {
                        points.add(nums[j]);
                        points.addAll(map.get(nums[j]));

                        result.add(points);

                        // To ****avoid duplicates**** being added to the list,
                        // we increment j so that it is now at a position where the
                        // jth element != {j-1}th element
                        while (j < (length - 1) && nums[j] == nums[j + 1]) j++;
                    } else {
                        points.add(nums[i]);
                        points.add(nums[j]);
                        map.put(0 - (nums[i] + nums[j]), points);
                    }
                }
            }
        }

        return result;
    }
}
