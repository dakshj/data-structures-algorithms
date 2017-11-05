class KEmptySlots {

    private int kEmptySlots(final int[] positions, final int separation) {
        final int[] days = new int[positions.length];

        // Swap meaning of positions[] array to hold blooming day as index vs. position as value
        // to days[] array which holds position as index vs. blooming day as value
        for (int i = 0; i < positions.length; i++) {
            days[positions[i] - 1] = i + 1;
        }

        int left = 0;
        int right = separation + 1;

        // Will try to minimize the result, in case there are multiple results
        int result = Integer.MAX_VALUE;

        for (int i = 0; right < days.length; i++) {
            if (days[i] < days[left] || days[i] <= days[right]) {
                if (i == right) {
                    // Found a valid subarray
                    result = Math.min(result, Math.max(days[left], days[right]));
                }

                left = i;
                right = separation + 1 + i;
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
