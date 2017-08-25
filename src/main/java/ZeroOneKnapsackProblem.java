import java.util.ArrayList;
import java.util.List;

public class ZeroOneKnapsackProblem {

    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 1));
        itemList.add(new Item(3, 4));
        itemList.add(new Item(4, 5));
        itemList.add(new Item(5, 7));

        System.out.println(get(7, itemList));
    }

    /**
     * @param totalWeight Maximum weight capacity of the knapsack
     * @param itemList    List of items which have value and weight properties
     * @return Maximum value contained within the knapsack
     */
    private static int get(int totalWeight, List<Item> itemList) {
        // "+ 1" so as to ease checking for valid index references while calling "i - 1"
        int[][] memo = new int[itemList.size() + 1][totalWeight + 1];

        // Iterate over all elements
        for (int i = 0; i < memo.length; i++) {
            for (int currentTotalWeight = 1; currentTotalWeight < memo[i].length; currentTotalWeight++) {
                // Keep all elements of 0th row and 0th column = 0
                if (i == 0 || currentTotalWeight == 0) continue;

                Item item = itemList.get(i - 1);

                // If current item's weight is less than current total weight of knapsack
                if (item.weight <= currentTotalWeight) {
                    // Store either
                    // {max of current item's value + best already-stored-value of REMAINING weight}
                    // OR
                    // {best already-stored-value up until the previous Item}
                    memo[i][currentTotalWeight] = Math.max(item.value + memo[i - 1][currentTotalWeight - item.weight],
                            memo[i - 1][currentTotalWeight]);
                } else {
                    // If current item's weight exceeds current total weight of knapsack
                    // Then simply store best already-stored-value up until the previous item
                    memo[i][currentTotalWeight] = memo[i - 1][currentTotalWeight];
                }
            }
        }

        // Last element is the max value a knapsack contains, for a given total weight
        return memo[itemList.size()][totalWeight];
    }

    private static class Item {
        private int weight;
        private int value;

        private Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
