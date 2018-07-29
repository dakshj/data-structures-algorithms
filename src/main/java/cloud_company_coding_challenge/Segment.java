package cloud_company_coding_challenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * For all subarrays of size x, find the min in each subarray.
 * Then, find the max of all those mins.
 */
@SuppressWarnings("ConstantConditions")
class Segment {

    static int segment(final int x, final int[] arr) {
        if (arr == null) return Integer.MIN_VALUE;

        final List<Integer> sublist = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            sublist.add(arr[i]);
        }

        final List<Integer> mins = new ArrayList<>();

        for (int i = x - 1; i < arr.length; i++) {
            mins.add(sublist.stream().min(Comparator.naturalOrder()).get());
            sublist.remove(0);
            sublist.add(arr[i]);
        }

        return mins.stream().max(Comparator.naturalOrder()).get();
    }
}
