import java.util.Arrays;

public class SmallestDifference {

    public static void main(String[] args) {
        int[] a = {1, 15, 3, 11, 2};
        int[] b = {23, 127, 19, 235, 8};
        System.out.println(Arrays.toString(get(a, b)));
    }

    private static int[] get(int[] a, int[] b) {
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        Arrays.sort(a);
        Arrays.sort(b);

        while (i < a.length && j < b.length) {
            int currentMin = Math.abs(a[i] - b[j]);
            if (currentMin < min) {
                min = currentMin;
                result = new int[]{a[i], b[j]};

                if (min == 0) break;
            }

            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}
