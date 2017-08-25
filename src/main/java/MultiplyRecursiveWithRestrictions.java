/**
 * Created by daksh on 20-Oct-16.
 */
public class MultiplyRecursiveWithRestrictions {

    // Can't use the following operators:
    // * (Multiply)
    // / (Divide)
    // % (Modulus)

    public static void main(String[] args) {
        System.out.println(multiply(109, 37));
    }

    private static int multiply(int smaller, int larger) {
        // Swap
        if (smaller > larger) return multiply(larger, smaller);

        // 0 * x = 0
        if (smaller == 0) return 0;

        // 1 * x = x
        if (smaller == 1) return larger;

        int prod =
                // Call recursively to calculate {(smaller/2) * larger} till smaller reaches 0
                multiply(
                        // Half of smaller
                        smaller >>> 1, larger)
                        // Multiply by 2 (because this returns a prod of half of smaller
                        << 1;

        // Even
        if (smaller >>> 1 << 1 == smaller) {
            return prod;
        }
        // Odd
        else {
            return prod + larger;
        }
    }
}
