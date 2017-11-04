class FirstMissingPositive {

    private int firstMissingPositive(final int[] arr) {
        int i = 0;

        while (i < arr.length) {
            // If value is at correct index, or if a value is unimportant
            // (i.e., negative, or > arr.length).
            // We skip values > arr.length because if a value is > arr.length, then it means
            // that there *has to be* a value which is +ve and lesser than that value.
            if (arr[i] - 1 == i || arr[i] <= 0 || arr[i] > arr.length) {
                i++;
            }

            // Else if the value is not at the correct index,
            // bring it to the correct index by swapping.
            // NOTE: Cannot simply check (arr[i] - 1 != i) because then this algo will
            // get infinitely stuck when the input arr is [1,1]
            // Thus, this condition basically **handles duplicates**!
            else if (arr[arr[i] - 1] != arr[i]) {
                swap(arr, arr[i] - 1, i);
            } else {
                i++;
            }
        }

        // Finally, go through array again, and stop when we find the first value which
        // is in a misplaced index
        i = 0;
        while (i < arr.length && arr[i] == i + 1) {
            i++;
        }

        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
