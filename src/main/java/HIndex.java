public class HIndex {

    private int hIndex(int[] citations) {
        final int n = citations.length;

        // Stores the count of each citation,
        // where all possible h-indices possible are range of 1..citations.length (both inclusive)
        final int[] a = new int[n + 1];

        for (final int citation : citations) {
            // If a citation exceeds the no. of citations, then simply increment the count of the
            // last citation's amount, such that all citations > n are considered in it
            if (citation >= n) {
                a[n]++;
            }

            // If it does not exceed, the increment the count of the corresponding index
            else {
                a[citation]++;
            }
        }

        int total = 0;

        // Reverse iterate, because we want to get the largest citation
        // satisfying the constraint
        for (int i = n; i >= 0; i--) {
            // We also keep a reversed running total of the counts, since we are checking for
            // a[i] >= i; thus the count at a[i] should ALSO include the count at a[i+1]
            total += a[i];

            // A citation satisfies the constraint if its count up till now (i.e., total)
            // is >= its value
            if (total >= i) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new HIndex().hIndex(new int[]{3, 0, 6, 1, 5}));
    }
}
