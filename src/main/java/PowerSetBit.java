import java.util.ArrayList;
import java.util.List;

/**
 * Created by daksh on 19-Oct-16.
 */
public class PowerSetBit {

    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>();
        set.add(3);
        set.add(1);
        set.add(4);

        System.out.println(getPowerSet(set));
    }

    private static List<List<Integer>> getPowerSet(List<Integer> set) {
        // Total amount of subsets in power set = 2^n
        // Thus, we set max by pushing a "1" to the appropriate 2^n position in the Integer's bits
        int max = 1 << set.size();

        List<List<Integer>> powerSet = new ArrayList<>();

        for (int i = 0; i < max; i++) {
            powerSet.add(getSetFromInt(i, set));
        }

        return powerSet;
    }

    private static List<Integer> getSetFromInt(int n, List<Integer> set) {
        // When n = 0, we return an empty subset list
        List<Integer> subSet = new ArrayList<>();

        // Maintains the current bit being referred to
        int index = 0;

        while (n > 0) {
            // Check if the right-most bit is a 1
            if ((n & 1) == 1) {
                subSet.add(set.get(index));
            }

            index++;

            // Shift n by 1 bit to the right, so that we can check the next bit's "Oneness"
            n = n >>> 1;
        }

        return subSet;
    }
}
