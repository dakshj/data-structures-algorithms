public class BinarySearchFirstOccurrenceWithDuplicates {

    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 2, 2, 5, 5, 5, 7, 7, 11, 11, 11}, 7));
    }

    private static int search(int[] arr, int x) {
        return binarySearchFirstOccurrenceWithDuplicates(arr, x, 0, arr.length - 1);
    }

    private static int binarySearchFirstOccurrenceWithDuplicates(int[] arr, int x, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;

        // If {mid}th element is x, AND
        if (arr[mid] == x &&
                // Mid index is 0 (thus there is no element before it, OR
                // Previous element is less than x (thus ensuring that {mid}th element is the first
                // occurrence of x in arr (which is a sorted array).
                (mid == 0 || arr[mid - 1] < x)) {
            return mid;
        }

        if (x > arr[mid]) {
            return binarySearchFirstOccurrenceWithDuplicates(arr, x, mid + 1, hi);
        }

        // Called when:
        // x is less than {mid}th element, OR when
        // mid index is not the first occurrence of x in arr
        // (which is why this function did not return earlier above at #21.
        return binarySearchFirstOccurrenceWithDuplicates(arr, x, lo, mid - 1);
    }
}
