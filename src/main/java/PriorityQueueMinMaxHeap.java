import java.util.PriorityQueue;

public class PriorityQueueMinMaxHeap {

    /**
     * Output:
     * [1, 3, 4, 8, 7]
     * [8, 7, 1, 4, 3]
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(4);
        minHeap.add(8);
        minHeap.add(1);
        minHeap.add(7);
        minHeap.add(3);
        System.out.println(minHeap);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        maxHeap.add(4);
        maxHeap.add(8);
        maxHeap.add(1);
        maxHeap.add(7);
        maxHeap.add(3);
        System.out.println(maxHeap);
    }
}
