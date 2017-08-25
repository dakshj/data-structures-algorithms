public class SearchListy {

    public static void main(String[] args) {
        Listy list = new Listy(new int[]{3, 4, 5, 10, 11, 15, 19, 23, 29, 33, 45});

        System.out.println(search(list, 11));
    }

    private static int search(Listy list, int x) {
        if (x < 0) return -1;

        int lim = 1;

        // Searching till "<" is more optimized than till "<=" since it gives a smaller part to search in
        while (list.elementAt(lim) != -1 && list.elementAt(lim) < x) {
            lim = lim * 2;
        }

        return binarySearch(list, x, lim / 2, lim);
    }

    private static int binarySearch(Listy list, int x, int left, int right) {
        int mid = left + (right - left) / 2;

        // Element found
        if (list.elementAt(mid) == x) return mid;

        // Element not found
        if (left > right) return -1;

        // Search left
        if (x < list.elementAt(mid) || list.elementAt(mid) == -1) {
            return binarySearch(list, x, left, mid - 1);
        }

        // Search right
        return binarySearch(list, x, mid + 1, right);
    }

    private static class Listy {

        private final int[] array;

        private Listy(int[] array) {
            this.array = array;
        }

        private int elementAt(int index) {
            if (index >= array.length) return -1;

            return array[index];
        }
    }
}
