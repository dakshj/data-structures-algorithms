import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GetFirstLastIndexOfTargetFromArray {
    public static void main(String[] args) {
        final GetFirstLastIndexOfTargetFromArray obj = new GetFirstLastIndexOfTargetFromArray();
        System.out.println(obj.get(new int[]{5, 7, 7, 8, 8, 10}, 5));
        System.out.println(obj.get(new int[]{5, 7, 7, 8, 8, 10}, 7));
        System.out.println(obj.get(new int[]{5, 7, 7, 8, 8, 10}, 8));
        System.out.println(obj.get(new int[]{5, 7, 7, 8, 8, 10}, 9));
        System.out.println(obj.get(new int[]{5, 7, 7, 8, 8, 10}, 10));
    }

    public List<Integer> get(final int[] arr, final int n) {
        final List<Integer> result = new ArrayList<>(Arrays.asList(-1, -1));

        if (arr.length == 0) return result;

        final int firstIndex = getFirstIndex(arr, n, 0, arr.length - 1);

        if (firstIndex == -1) return result;

        result.set(0, firstIndex);

        result.set(1, getLastIndex(arr, n, firstIndex, arr.length - 1));
        return result;
    }

    private int getFirstIndex(final int[] arr, final int n, final int lo, final int hi) {
        if (lo >= hi && arr[lo] != n) return -1;
        if (lo == hi && arr[lo] != n) return -1;

        final int mid = lo + (hi - lo) / 2;

        if (arr[mid] == n && (mid == 0 || arr[mid - 1] < n)) {
            return mid;
        } else if (arr[mid] < n) {
            return getFirstIndex(arr, n, mid + 1, hi);
        }

        return getFirstIndex(arr, n, lo, mid);
    }

    private int getLastIndex(final int[] arr, final int n, final int lo, final int hi) {
        if (lo >= hi && arr[lo] != n) return -1;
        if (lo == hi && arr[lo] != n) return -1;

        final int mid = lo + (hi - lo) / 2;

        if (arr[mid] == n && (mid == arr.length - 1 || arr[mid + 1] > n)) {
            return mid;
        } else if (arr[mid] < n) {
            return getLastIndex(arr, n, mid + 1, hi);
        }

        return getLastIndex(arr, n, lo, mid);
    }
}
