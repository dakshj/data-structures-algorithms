class PeakElement {

    private int findPeakElement(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(final int[] nums, final int start, final int end) {
        if (start == end) return start;

        final int mid1 = start + (end - start) / 2;
        final int mid2 = mid1 + 1;

        // If m1 is greater than m2, then go left (ending at m1)
        if (nums[mid1] > nums[mid2]) {
            return binarySearch(nums, start, mid1);
        }

        // If m1 is lesser than m2, then go right (starting at m2)
        return binarySearch(nums, mid2, end);
    }

    public static void main(String[] args) {
        System.out.println(new PeakElement().findPeakElement(new int[]{3, 1, 4, 2}));
        System.out.println(new PeakElement().findPeakElement(new int[]{3, 2, 3, 2, 3}));
    }
}
