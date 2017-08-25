import java.util.HashMap;
import java.util.Map;

public class KMostFrequentWords {

    public static void main(String[] args) {
        String text = "Within the first billion years of Earth's history life appeared in the oceans " +
                "and began to affect the atmosphere and surface leading to the proliferation of aerobic " +
                "and anaerobic organisms Since then the combination of Earth's distance from the Sun " +
                "physical properties and geological history have allowed life to evolve and thrive Life " +
                "arose on Earth by 35 billion years ago though some geological evidence indicates that " +
                "life may have arisen as much as 41 billion years ago In the history of the Earth " +
                "biodiversity has gone through long periods of expansion occasionally punctuated by mass " +
                "extinction events Over 99% of all species of life that ever lived on Earth are extinct " +
                "Estimates of the number of species on Earth today vary widely most species have " +
                "not been described Over 73 billion humans live on Earth and depend on its biosphere " +
                "and minerals for their survival Humanity has developed diverse societies and cultures " +
                "politically the world is divided into about 200 sovereign states";

        int k = 4;

        new KMostFrequentWords().getKMostFrequentWords(text, k);
    }

    private void getKMostFrequentWords(String text, int k) {
        Logic logic = new Logic(k);

        String[] words = text.split(" ");

        for (String word : words) {
            logic.insert(word);
        }

        logic.display();
    }

    private class TrieNode {
        private boolean endOfString;
        private Map<Character, TrieNode> children = new HashMap<>();
        private int frequency = 0;
        private int minHeapIndex = -1;
    }

    private class MinHeap {
        private int size;
        private int capacity;
        private MinHeapNode[] nodes;
    }

    private class MinHeapNode {
        private String word;
        private int frequency;
        private TrieNode trieNode; // Link to Logic
    }

    private class Logic {

        private TrieNode root;
        private MinHeap minHeap;

        private Logic(int frequency) {
            root = new TrieNode();
            minHeap = new MinHeap();
            minHeap.nodes = new MinHeapNode[frequency];
            minHeap.capacity = frequency;
        }

        private void insert(String word) {
            if (word == null || word.trim().length() == 0) {
                return;
            }
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!current.children.containsKey(c)) {
                    TrieNode node = new TrieNode();
                    current.children.put(c, node);
                }

                current = current.children.get(c);
            }
            if (current.endOfString) {
                current.frequency++;
            } else {
                current.frequency = 1;
                current.endOfString = true;
            }
            insertInMinHeap(word, current);
        }

        /**
         * Inserts word into Min Heap using the below rules:
         * 1. If word already present, increment count in MinHeap and call minHeapify().
         * 2. If Min Heap is not full AND word is not present, then add trieNode to MinHeap and update its minHeapIndex, and call minHeapify().
         * 3. If MinHeap is full AND new word is not present AND new word's frequency is > minimum head ==> replace the top element (index=0) with new word and update the minHeapIndexOf both the words.
         */
        private void insertInMinHeap(String word, TrieNode trieNode) {
            if (trieNode.minHeapIndex != -1) {
                minHeap.nodes[trieNode.minHeapIndex].frequency++;
                minHeapify(trieNode.minHeapIndex);
            } else if (minHeap.size < minHeap.capacity) {
                ++minHeap.size;
                MinHeapNode node = new MinHeapNode();
                node.word = word;
                node.frequency = trieNode.frequency;
                node.trieNode = trieNode;
                node.trieNode.minHeapIndex = minHeap.size - 1;
                minHeap.nodes[minHeap.size - 1] = node;
                buildMinHeap();
            } else if (trieNode.frequency > minHeap.nodes[0].frequency) {
                minHeap.nodes[0].trieNode.minHeapIndex = -1;
                minHeap.nodes[0].trieNode = trieNode;
                minHeap.nodes[0].frequency = trieNode.frequency;
                minHeap.nodes[0].word = word;
                trieNode.minHeapIndex = 0;
                minHeapify(0);
            }
        }

        private void buildMinHeap() {
            for (int i = (minHeap.size - 1) / 2; i >= 0; i--) {
                minHeapify(i);
            }
        }

        private void minHeapify(int node) {
            int left = (node << 1) + 1;
            int right = (node << 1) + 2;
            int smallest = node;
            if (left < minHeap.size
                    && minHeap.nodes[smallest].frequency > minHeap.nodes[left].frequency) {
                smallest = left;
            }
            if (right < minHeap.size
                    && minHeap.nodes[smallest].frequency > minHeap.nodes[right].frequency) {
                smallest = right;
            }
            if (smallest != node) {
                int index = minHeap.nodes[smallest].trieNode.minHeapIndex;
                minHeap.nodes[smallest].trieNode.minHeapIndex = minHeap.nodes[node].trieNode.minHeapIndex;
                minHeap.nodes[node].trieNode.minHeapIndex = index;
                MinHeapNode temp = minHeap.nodes[smallest];
                minHeap.nodes[smallest] = minHeap.nodes[node];
                minHeap.nodes[node] = temp;
                minHeapify(smallest);
            }
        }

        private void display() {
            for (int i = minHeap.size - 1; i >= 0; i--) {
                System.out.println(minHeap.nodes[i].word +
                        " = " +
                        minHeap.nodes[i].frequency);
            }
        }
    }
}
