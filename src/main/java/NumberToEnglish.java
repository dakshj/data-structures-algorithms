import java.util.ArrayList;
import java.util.List;

public class NumberToEnglish {

    private static final String[] smalls =
            {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                    "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                    "Eighteen", "Nineteen"};

    private static final String[] tens =
            {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    // Added space before appropriate elements to reduce empty checks or String trims later on
    private static final String[] bigs =
            {"", " Thousand", " Million", " Billion"};

    private static final String hundred = "Hundred";

    private static final String negative = "Negative";

    public static void main(String[] args) {
        System.out.println(convert(123));
        System.out.println(convert(19323980));
    }

    private static String convert(int n) {
        if (n == 0) return smalls[n];

        if (n < 0) return negative + " " + convert(-n);

        // Used to determine "Thousand", "Million", etc.
        int chunkCount = 0;

        List<String> list = new ArrayList<>();

        while (n > 0) {
            // Get chunk of 3 digits
            if (n % 1000 > 0) {
                // Add to start of list (Also, can use a Stack instead)
                // On first iteration, chunkCount will be 0, thus bigs[0] = ""
                list.add(0, getChunkText(n % 1000) + bigs[chunkCount]);
            }

            n /= 1000;
            chunkCount++;
        }

        return joinList(list);
    }

    /**
     * Gets Text representation for a (max) 3-digit number.
     */
    private static String getChunkText(int n) {
        List<String> list = new ArrayList<>();

        // Add hundred's place
        if (n >= 100) {
            list.add(smalls[n / 100] + " " + hundred);
            n %= 100;
        }

        // Add ten's place
        if (n >= 20) {
            list.add(tens[n / 10]);
            n %= 10;
        }

        // Add unit's place (or less than 20)
        if (n > 0) {
            list.add(smalls[n]);
        }

        return joinList(list);
    }

    private static String joinList(List<String> list) {
        String result = "";

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);

            if (i < list.size() - 1) {
                result += " ";
            }
        }

        return result;
    }
}
