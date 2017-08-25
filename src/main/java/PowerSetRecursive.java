import java.util.ArrayList;
import java.util.List;

/**
 * Created by daksh on 19-Oct-16.
 */
public class PowerSetRecursive {

    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>();
        set.add(3);
        set.add(1);
        set.add(4);

        System.out.println(getPowerSet(set, 0));
    }

    private static List<List<Integer>> getPowerSet(List<Integer> set, int i) {
        List<List<Integer>> powerSet;

        // We process recursion from last element in set to first element.

        // Thus, base case is when we have exceeded the set's upper limit
        // Then we add an empty set to the power set
        if (set.size() == i) {
            powerSet = new ArrayList<>();
            powerSet.add(new ArrayList<>());
        } else {
            // Get previous (next in this case) power set recursively
            powerSet = getPowerSet(set, i + 1);

            // Get current integer to be added to all sets of the previous power set
            int currentInteger = set.get(i);

            List<List<Integer>> currentPowerSet = new ArrayList<>();

            // Iterate over all sets in the previous power set (so as to add the current item to all)
            for (List<Integer> integers : powerSet) {
                // Transfer current set of previous power set to a temp
                List<Integer> currentSubSet = new ArrayList<>(integers);

                // Add current integer to current set
                currentSubSet.add(currentInteger);

                // Add newly created set to current power set
                currentPowerSet.add(currentSubSet);
            }

            // Add all of current power set (with the current item inserted in all containing sets),
            // to the previous power set
            powerSet.addAll(currentPowerSet);
        }

        return powerSet;
    }
}
