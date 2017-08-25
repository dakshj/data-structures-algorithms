public class MaxHeap {

    final int[] array;
    int length;

    public MaxHeap(int[] array, int length) {
        this.array = array;
        this.length = length;
    }

    /**
     * Complexity = O(n/2) = O(n)
     */
    void buildMaxHeap() {
        // Iterate from mid to 1 because
        // Elements from {N/2 + 1} to {N} are leaf nodes, thus they are single-element heaps which
        // don't require heapifying
        for (int i = length / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }

    /**
     * Heap starts from index ONE (1) !!!! [MEMORIZE]
     * <p>
     * Complexity = O(log n)
     */
    void maxHeapify(int index) {
        if (index < 1 || index > length) return;

        // Since a heap is stored row-wise in an array,
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        int largestIndex = index;

        // Compare left with top
        if (leftIndex <= length && array[leftIndex] > array[largestIndex]) {
            largestIndex = leftIndex;
        }

        // Compare right with top, OR compare right with left if above condition was executed
        if (rightIndex <= length && array[rightIndex] > array[largestIndex]) {
            largestIndex = rightIndex;
        }

        // If top is not largest element
        if (largestIndex != index) {
            swap(index, largestIndex);

            // Reiterate on position where top was swapped to
            maxHeapify(largestIndex);
        }
    }

    void swap(int indexA, int indexB) {
        if (indexA < 1 || indexA > length ||
                indexB < 1 || indexB > length) {
            return;
        }

        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public int peekMax() {
        if (length < 2) return Integer.MIN_VALUE;
        return array[1];
    }

    public int removeMax() {
        if (length < 2) return Integer.MIN_VALUE;

        int max = array[1];

        // Move last element to be first element
        array[1] = array[length];

        // Decrease length of heap elements because we have removed an element
        length--;

        maxHeapify(1);

        return max;
    }

    public void increaseValue(int index, int value) {
        if (index < 1 || index > length) return;

        array[index] = value;

        // Compare parent at {index / 2}, with child at {index}.
        // Where a parent needs to be GREATER THAN its child in MAX Heap
        // We only compare child when there is a valid parent.
        // Thus, for index = 1, there is no parent for this. Hence, we stop at index > 1
        while (index > 1 && array[index / 2] < array[index]) {
            // Bubble up the child to the parent's position
            swap(index / 2, index);

            // Set parent as child for the next iteration (which now has inserted value)
            index /= 2;
        }
    }

    public void insert(int value) {
        array[++length] = Integer.MIN_VALUE;
        increaseValue(length, value);
    }
}
