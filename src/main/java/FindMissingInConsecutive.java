public class FindMissingInConsecutive {

    public static void main(String[] args) {
        System.out.println(search(new int[]{-6, -5, -4, -2, -1, 0}));
    }

    private static Integer search(int[] arr) {
        return binarySearch(arr, 0, arr.length - 1);
    }

    private static Integer binarySearch(int[] arr, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        // TODO Explain why we are searching for this
        int x = arr[0] + 1 + mid;

        // TODO Explain these 3 conditions
        if (arr[mid] == x &&
                mid - 1 >= 0 &&
                arr[mid - 1] == (arr[0] + mid - 1)) {
            return arr[0] + mid;
        }

        if (arr[mid] == arr[0] + mid) {
            return binarySearch(arr, mid + 1, hi);
        }

        return binarySearch(arr, lo, mid - 1);
    }
}
