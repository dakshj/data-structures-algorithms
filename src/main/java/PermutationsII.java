import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PermutationsII {

    public List<List<Integer>> uniquePermutations(final int[] nums) {
        final List<List<Integer>> finalResult = new ArrayList<>();

        if (nums == null || nums.length == 0) return finalResult;

        // Sort the array to bring duplicates together
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];

        final List<Integer> currentResult = new ArrayList<>();

        dfs(nums, visited, currentResult, finalResult);

        return finalResult;
    }

    private void dfs(final int[] nums, final boolean[] visited, final List<Integer> currentResult,
            final List<List<Integer>> finalResult) {
        if (currentResult.size() == nums.length) {
            // Add a copy of the list, because this same currentResult will be modified later on
            finalResult.add(new ArrayList<>(currentResult));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // If already visited
            if (visited[i]) continue;

            // Helps to skip duplicates as well as explore lesser recursion branches.
            // If same as previous number, and previous number is visited,
            // then we skip this. This works because:
            // the same {i-1}th number is NOT VISITED only in the case of this current for loop,
            // i.e. when we go for the next ITERATION. This means that if it were allowed,
            // it would result in exploration of same solutions as the previous iteration
            // BECAUSE the nos. are same, and this question specifically does not care about
            // the uniqueness of numbers per se, and treats same nos. as the same.
            //
            // However, when we go into the next dfs() recursion after setting {i-1} as true,
            // we will skip all those previous nos. in the above base case, since they're all true.
            // Now, only this i will be false in all repeated numbers from {i - whatever} to i.
            // Thus, we will get a single way of exploration of all the indices if the nos.
            // at those indices are same.
            //
            // {i-1} will only be false in the case of ITERATION, NOT recursion. Thus, for iteration,
            // we skip those {i}s, since the {i-1} iteration has taken care of all the recursion
            // calls previously.
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                System.out.println("here");
                continue;
            }

            visited[i] = true;

            currentResult.add(nums[i]);

            dfs(nums, visited, currentResult, finalResult);

            // Make it unvisited for later DFSes
            visited[i] = false;

            currentResult.remove(currentResult.size() - 1);
        }
    }
}
