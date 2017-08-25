import java.util.Arrays;

public class SearchRotatedArray {

    public static void main(String[] args) {
        print(new int[]{7, 8, 9, 10, 11, 3, 4, 5, 6}, 10);
        print(new int[]{7, 8, 9, 10, 11, 3, 4, 5, 6}, 6);
        print(new int[]{2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1}, 2);
    }

    private static void print(int[] a, int x) {
        System.out.println("Index of " + x + " in " + Arrays.toString(a) + " = " + search(a, x));
    }

    private static int search(int[] a, int x) {
        return search(a, x, 0, a.length - 1);
    }

    private static int search(int[] a, int x, int left, int right) {
        int mid = left + (right - left) / 2;

        if (x == a[mid]) return mid; // Found

        if (left > right) return -1; // Not found

        if (a[left] < a[mid]) { // Left half is sorted
            if (a[left] <= x && x < a[mid]) { // x is contained in left half
                return search(a, x, left, mid - 1);
            } else { // x is contained in right half
                return search(a, x, mid + 1, right);
            }
        } else if (a[left] > a[mid]) { // Right half is sorted
            if (a[mid] < x && x <= a[right]) { // x is contained in right half
                return search(a, x, mid + 1, right);
            } else { // x is contained in left half
                return search(a, x, left, mid - 1);
            }
        } else if (a[left] == a[mid]) { // Either left or right half have all duplicates
            // Since a[left] == a[mid],
            // and because x != a[mid] (Otherwise code would have exited at line 25),
            // x can only lie in the right half
            if (a[mid] != a[right]) {
                return search(a, x, mid + 1, right);
            } else {
                int result = search(a, x, left, mid - 1);

                return result == 1 ? search(a, x, mid + 1, right) : result;
            }
        }

        return -1;
    }
}
