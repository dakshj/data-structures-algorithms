import java.util.Arrays;

public class SortedMerge {

    public static void main(String[] args) {
//        int[] a = {
//                1, 6, 9, 13, 15, 16, 23, 37, 41, 72,
//
//                // Buffer
//                0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//
//        int[] b = {2, 10, 17, 25, 31, 39, 58, 61, 70, 77};

        int[] a = {
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,

                // Buffer
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        sort(a, b);

        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] a, int[] b) {
        int indexA = a.length / 2 - 1;
        int indexB = b.length - 1;
        int indexMerged = a.length - 1;

        // Only check for B, since once we cross start of B, the rest of the elements are
        // already in A.
        while (indexB >= 0) {
            // When A's start is reached before B's start, indexA becomes -1
            // and causes an IndexOutOfBoundsException if not checked
            if (indexA >= 0 && a[indexA] > b[indexB]) {
                a[indexMerged--] = a[indexA--];
            } else {
                a[indexMerged--] = b[indexB--];
            }
        }
    }
}
