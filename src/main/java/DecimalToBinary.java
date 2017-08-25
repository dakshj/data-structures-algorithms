/**
 * Created by daksh on 09-Oct-16.
 */
public class DecimalToBinary {

    public static void main(String[] args) {
        System.out.println(convert(0.72));
    }

    private static String convert(double n) {
        if (n <= 0 || n >= 1) {
            return "ERROR";
        }

        StringBuilder bin = new StringBuilder();
        bin.append("0.");

        double r;

        while (n > 0) {
            if (bin.length() >= 32) {
                return "ERROR";
            }

            r = n * 2;

            if (r >= 1) {
                bin.append(1);
                n = r - 1;
            } else {
                bin.append(0);
                n = r;
            }
        }

        return bin.toString();
    }
}
