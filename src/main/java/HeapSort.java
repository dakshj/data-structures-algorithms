import java.util.Arrays;

public class HeapSort extends MaxHeap {

    /**
     * Prints [8, 7, 5, 4, 3, 1]
     * <p>
     * Complexity = O(n log n)
     */
    public static void main(String[] args) {
        int[] array = new int[1000];
        array[1] = 4;
        array[2] = 3;
        array[3] = 7;
        array[4] = 1;
        array[5] = 8;
        array[6] = 5;

        int length = 6;

        new HeapSort(array, length).sort();

        System.out.println(Arrays.toString(array));
    }

    public HeapSort(int[] array, int length) {
        super(array, length);
    }

    public void sort() {
        buildMaxHeap();

        for (int i = length; i >= 1; i--) {
            swap(1, i);
            length--;
            maxHeapify(1);
        }
    }
}
