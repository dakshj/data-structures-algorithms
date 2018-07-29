package cloud_company_coding_challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * First sort alphabetically. If in case names clash, use the roman numeral to sort those
 * clashing names
 */
class RoyalNames {

    static String[] getSortedList(String[] names) {
        if (names == null || names.length <= 1) return names;

        final RomanToInt romanToInt = new RomanToInt();

        final List<String> list = new ArrayList<>(Arrays.asList(names));

        list.sort((o1, o2) -> {
            final String[] name1 = o1.split(" ");
            final String[] name2 = o2.split(" ");

            if (!name1[0].equals(name2[0])) {
                return o1.compareTo(o2);
            } else {
                final int val1 = romanToInt.convertRomanNumeralToInteger(name1[1]);
                final int val2 = romanToInt.convertRomanNumeralToInteger(name2[1]);

                return Integer.compare(val1, val2);
            }
        });

        return list.toArray(new String[list.size()]);
    }

    private static class RomanToInt {
        private final Map<Character, Integer> map = new HashMap<>();

        private RomanToInt() {
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
            map.put('L', 50);
            map.put('C', 100);
            map.put('D', 500);
            map.put('M', 1000);
        }

        private int convertRomanNumeralToInteger(String roman) {
            int val = 0;

            for (int i = 0; i < roman.length(); i++) {
                int current = map.get(roman.charAt(i));

                int next = 0;
                if (i != roman.length() - 1) {
                    next = map.get(roman.charAt(i + 1));
                }

                if (next > current) {
                    current = next - current;
                    i++;
                }

                val += current;
            }

            return val;
        }
    }
}
