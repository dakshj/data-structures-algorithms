package cloud_company_coding_challenge;

/**
 * Merge two strings with alternating chars
 */
class MergeStrings {

    static String mergeStrings(String a, String b) {
        if (a == null && b == null) return null;

        if (a == null || a.isEmpty()) return b;

        if (b == null || b.isEmpty()) return a;

        final StringBuilder merged = new StringBuilder();

        int ai = 0;
        int bi = 0;

        while (ai < a.length() || bi < b.length()) {
            if (ai < a.length()) {
                merged.append(a.charAt(ai++));
            }

            if (bi < b.length()) {
                merged.append(b.charAt(bi++));
            }
        }

        return merged.toString();
    }
}
