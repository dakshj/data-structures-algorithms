import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {

    private ListNode merge(final List<ListNode> listNodes) {
        final PriorityQueue<ListNode> pq =
                new PriorityQueue<>(Comparator.comparingInt(o -> o.data));

        // Add all non-null ListNode heads to the PriorityQueue
        listNodes.stream()
                .filter(Objects::nonNull)
                .forEach(pq::add);

        // Create a temporary head which will later be removed
        ListNode tempHead = new ListNode(Integer.MIN_VALUE);

        // Maintain a tail which will be used for appending all later values
        ListNode tail = tempHead;

        while (!pq.isEmpty()) {
            final ListNode smallest = pq.remove();

            // Append the PriorityQueue's smallest element to the tail
            tail.next = smallest;

            // Increment tail to point to end of merged list
            tail = tail.next;

            // Add next of the smallest ListNode ONLY IF IT IS NON-NULL
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        return tempHead.next;
    }

    private static class ListNode {
        private int data;

        private ListNode next;

        private ListNode(final int data) {
            this.data = data;
        }

        private ListNode next(final int nextData) {
            next = new ListNode(nextData);
            return next;
        }

        @Override
        public String toString() {
            return data + " ==> " + next;
        }
    }

    /**
     * Output:
     * 1 ==> 2 ==> 3 ==> 4 ==> 5 ==> 6 ==> 7 ==> 8 ==> 9 ==> 10 ==> 11 ==> 12 ==> 13 ==> 14 ==> null
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next(3).next(8).next(14);

        ListNode l2 = new ListNode(2);
        l2.next(5).next(6).next(9).next(12);

        ListNode l3 = new ListNode(4);
        l3.next(7).next(10).next(11).next(13);

        System.out.println(new MergeKSortedLinkedLists().merge(Arrays.asList(l1, l2, l3)));
    }
}
