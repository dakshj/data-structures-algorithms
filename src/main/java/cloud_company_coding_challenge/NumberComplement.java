package cloud_company_coding_challenge;

/**
 * Find 2s complement of a number
 */
class NumberComplement {

    static int numberComplement(final int n) {
        final int num_ones = (Integer.highestOneBit(n) << 1) - 1;
        return num_ones ^ n;
    }
}
