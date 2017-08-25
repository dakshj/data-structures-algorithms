public class SparseSearch {

    public static void main(String[] args) {
        String[] a = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String x = "ball";

        System.out.println(search(a, x));
    }

    private static int search(String[] a, String x) {
        if (x == null || x.isEmpty()) return -1;

        return search(a, x, 0, a.length - 1);
    }

    private static int search(String[] a, String x, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        // If mid is an empty String, find the closest non-empty String
        if (a[mid].equals("")) {
            // Left iterator
            int p = mid - 1;

            //Right iterator
            int q = mid + 1;

            while (true) {
                // If both left and right iterators have crossed their range,
                // then all Strings are empty in the array.
                if (p < left && q > right) return -1;

                // Set mid to p (left iterator) if not empty, and break out!
                if (p >= left && !a[p].equals("")) {
                    mid = p;
                    break;
                }

                // Set mid to q (right iterator) if not empty, and break out!
                if (q <= right && !a[q].equals("")) {
                    mid = q;
                    break;
                }

                p--;
                q++;
            }
        }

        if (a[mid].equals(x)) return mid;

        // If mid String is more x, then Search Left!
        if (a[mid].compareTo(x) > 0) {
            return search(a, x, left, mid - 1);
        }

        // If mid String is less than x, then Search Right!
        return search(a, x, mid + 1, right);
    }
}
