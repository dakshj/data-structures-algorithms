public class LinkedListPalindromeRecurse {

    public static void main(String[] args) {
        Node node = new Node(3);
        node.next = new Node(5);
        node.next.next = new Node(6);
        node.next.next.next = new Node(9);
        node.next.next.next.next = new Node(6);
        node.next.next.next.next.next = new Node(4);
        node.next.next.next.next.next.next = new Node(3);

        System.out.println(new LinkedListPalindromeRecurse().isPalindrome(node));
    }

    private boolean isPalindrome(Node node) {
        int length = length(node);

        Result res = isPalindrome(node, length);

        return res.palindrome;
    }

    private Result isPalindrome(Node node, int length) {
        if (length == 0) { // Even
            return new Result(true, node);
        } else if (length == 1) { // Odd
            return new Result(true, node.next);
        }

        Result res = isPalindrome(node.next, length - 2);
        if (!res.palindrome) {
            return res;
        }

        res.palindrome = node.data == res.node.data;
        res.node = res.node.next;

        return res;
    }

    private int length(Node n) {
        Node temp = n;
        int length = 0;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    private static class Result {
        boolean palindrome;
        Node node;

        Result(boolean palindrome, Node node) {
            this.palindrome = palindrome;
            this.node = node;
        }
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}
