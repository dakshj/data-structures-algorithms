/**
 * Created by daksh on 16-Oct-16.
 */
public class SumListsB {

    public static void main(String[] args) {
        SumListsB sum = new SumListsB();

        Node a = new Node(3);
        a.next = new Node(5);
        a.next.next = new Node(7);
        sum.printLinkedList(a);

        Node b = new Node(6);
        b.next = new Node(2);
        b.next.next = new Node(9);
        b.next.next.next = new Node(5);
        sum.printLinkedList(b);

        Node ans = sum.sum(a, b);
        sum.printLinkedList(ans);
    }

    private Node sum(Node a, Node b) {
        int aLen = length(a);
        int bLen = length(b);

        if (aLen < bLen) {
            a = pad(a, bLen - aLen);
        } else {
            b = pad(b, aLen - bLen);
        }

        SumRec sumRec = add2(a, b);

        Node ret = sumRec.node;

        if (sumRec.carry == 1) {
            ret = insertBefore(sumRec.node, 1);
        }

        return ret;
    }

    private SumRec add2(Node a, Node b) {
        if (a == null && b == null) return new SumRec();

        SumRec sumRecCurrent = new SumRec();
        SumRec sumRecPrev = add2(a.next, b.next);

        int total = sumRecPrev.carry;

        total += a.data;

        total += b.data;

        sumRecCurrent.node = new Node(total % 10);
        sumRecCurrent.node.next = sumRecPrev.node;
        sumRecCurrent.carry = total >= 10 ? 1 : 0;

        return sumRecCurrent;
    }

    private int length(Node n) {
        int length = 0;
        Node temp = n;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    private Node pad(Node n, int count) {
        Node head = n;

        for (int i = 0; i < count; i++) {
            head = insertBefore(head, 0);
        }

        return head;
    }

    private Node insertBefore(Node n, int data) {
        Node temp = new Node(data);
        temp.next = n;
        return temp;
    }

    private void printLinkedList(Node n) {
        Node temp = n;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class SumRec {
        int carry;
        Node node;
    }
}
