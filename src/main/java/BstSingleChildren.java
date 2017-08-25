public class BstSingleChildren {

    public static void main(String[] args) {
        System.out.println(check(new int[]{50, 48, 33, 31, 41, 37, 35, 52})); // 1. false
        System.out.println(check(new int[]{50, 48, 33, 31, 41, 37, 35})); // 2. false
        System.out.println(check(new int[]{50, 48, 33, 41, 37, 35})); // 3. true
        System.out.println(check(new int[]{50, 48})); // 4. true
        System.out.println(check(new int[]{50, 48, 33, 31, 49})); // 5. false
    }

    private static boolean check(int[] arr) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            // Every current element should be in range of max and min
            // (which may have been updated by previous iterations).
            if (arr[i] < min || arr[i] > max) return false;

            // Update current to min or max.
            // Every new current element will always be either a min or max, since this is a
            // BST array represented in preorder! (Draw a BST to confirm.)
            if (arr[i] < arr[i + 1]) {
                min = arr[i];
            } else {
                max = arr[i];
            }
        }

        // Check only the last element to be in range of min and max.
        // This works because if there is more than one child for any non-leaf node, that will not
        // be in range.
        // For e.g. in the 1st case, 52 is the last element, which is not
        // Then, in the 5th case, 49 will be the last element, and 31 is min, and 33 is max
        int last = arr[arr.length - 1];
        return last > min && last < max;
    }
}
