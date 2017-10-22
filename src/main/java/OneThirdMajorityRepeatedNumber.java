import java.util.Arrays;
import java.util.List;

public class OneThirdMajorityRepeatedNumber {

    private int repeatedNumber(final List<Integer> a) {
        int n1 = -1, n2 = -1, c1 = 0, c2 = 0;

        // Get best candidates for the two most repeated numbers
        for (final int cur : a) {
            if (cur == n1) {
                c1++;
            } else if (cur == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = cur;
                c1++;
            } else if (c2 == 0) {
                n2 = cur;
                c2++;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;

        // Recount n1 and n2
        for (final int cur : a) {
            if (cur == n1) {
                c1++;
            } else if (cur == n2) {
                c2++;
            }
        }

        // If either n1 or n2 are repeated more than 1/3rd times
        if (c1 > a.size() / 3) {
            return n1;
        } else if (c2 > a.size() / 3) {
            return n2;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new OneThirdMajorityRepeatedNumber()
                .repeatedNumber(Arrays.asList(1, 1, 1, 2, 2, 2, 3)));
    }
}
