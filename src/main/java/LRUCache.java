import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Stores the key vs. a reference to the DQNode
    private final Map<Integer, DQNode> map = new HashMap<>();

    private final DQNode headDummy = new DQNode(-1, -1);
    private final DQNode tailDummy = new DQNode(-1, -1);
    private final int capacity;
    private int dqSize;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        headDummy.next = tailDummy;
        tailDummy.prev = headDummy;
    }

    public int get(final int key) {
        final DQNode node = map.get(key);

        // Key doesn't exist in the map
        if (node == null) {
            return -1;
        }

        moveNodeToHead(node);
        return node.value;
    }

    public void put(final int key, final int value) {
        DQNode node = map.get(key);

        // Key doesn't exist in the map
        if (node == null) {
            dqSize++;
            trimIfNecessary();

            node = new DQNode(key, value);
            map.put(key, node);
            addNode(node);
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    private void addNode(final DQNode node) {
        node.prev = headDummy;
        node.next = headDummy.next;

        headDummy.next.prev = node;
        headDummy.next = node;
    }

    private void removeNode(final DQNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveNodeToHead(final DQNode node) {
        removeNode(node);
        addNode(node);
    }

    private void trimIfNecessary() {
        if (dqSize > capacity) {
            removeLastNode();
        }
    }

    private void removeLastNode() {
        if (tailDummy.prev != headDummy) {
            map.remove(tailDummy.prev.key);
            removeNode(tailDummy.prev);
        }
    }

    private static class DQNode {

        private int key;
        private int value;
        private DQNode next;
        private DQNode prev;

        private DQNode(final int key, final int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        final LRUCache lruCache2 = new LRUCache(2);
        lruCache2.put(1, 1);
        lruCache2.put(2, 2);
        System.out.println(lruCache2.get(1));
        lruCache2.put(3, 3);
        System.out.println(lruCache2.get(2));
        lruCache2.put(4, 4);
        System.out.println(lruCache2.get(1));
        System.out.println(lruCache2.get(3));
        System.out.println(lruCache2.get(4));
    }
}
