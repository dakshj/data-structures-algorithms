public class LinkedListIntersectionByReference {

    public static void main(String[] args) {
        Node intersectionStart = new Node(7);
        intersectionStart.next = new Node(6);
        intersectionStart.next.next = new Node(3);

        Node a = new Node(1);
        a.next = new Node(2);
        a.next.next = new Node(3);
        a.next.next.next = new Node(4);
        a.next.next.next.next = new Node(5);
        a.next.next.next.next.next = intersectionStart;

        Node b = new Node(20);
        b.next = new Node(19);
        b.next.next = new Node(18);
        b.next.next.next = intersectionStart;

        System.out.println(new LinkedListIntersectionByReference().getIntersectingNode(a, b));
    }

    private Node getIntersectingNode(Node a, Node b) {
        Result aRes = getLengthAndLastNode(a);
        Result bRes = getLengthAndLastNode(b);

        if (aRes.last != bRes.last) {
            return null;
        }

        if (aRes.length != bRes.length) {
            int lengthDiff = Math.abs(aRes.length - bRes.length);

            if (bRes.length > aRes.length) {
                b = trimNodeBy(b, lengthDiff);
            } else {
                a = trimNodeBy(a, lengthDiff);
            }
        }

        while (a != b) {
            a = a.next;
            b = b.next;
        }

        return a;
    }

    private Node trimNodeBy(Node n, int lengthDiff) {
        if (n == null) return null;

        Node node = n;

        for (int i = 0; i < lengthDiff; i++) {
            node = node.next;

            if (node == null) break;
        }

        return node;
    }

    private Result getLengthAndLastNode(Node n) {
        if (n == null) return new Result(0, null);

        Node temp = n;
        int count = 1;

        while (temp.next != null) {
            count++;

            temp = temp.next;
        }

        return new Result(count, temp);
    }

    private static class Result {
        int length;
        Node last;

        Result(int length, Node last) {
            this.length = length;
            this.last = last;
        }
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
