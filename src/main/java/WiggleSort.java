class WiggleSort {

    private void wiggleSort(int[] nums) {
        // Start from 2nd element in array
        for (int i = 1; i < nums.length; i++) {
            // If this is an odd index (odd indices are indices of peak elements)
            // (peak = an element having smaller neighboring elements)
            if (i % 2 == 1) {
                // If the previous element is larger than current (which is a wrong situation)
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i, i - 1);
                }
            }

            // Else if this is an even index and if the previous element is smaller than current
            // (which is also a wrong situation)
            else if (nums[i - 1] < nums[i]) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(final int[] nums, final int a, final int b) {
        final int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }
}
