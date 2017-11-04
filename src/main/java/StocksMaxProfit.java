class StocksMaxProfit {

    private int maxProfitOneTransaction(final int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxGain = 0;

        for (final int price : prices) {
            minPrice = Math.min(minPrice, price);

            maxGain = Math.max(maxGain, price - minPrice);
        }

        return maxGain;
    }

    private int maxProfitAnyTransactions(final int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Add a greedy pair-wise transaction profit to the total profit,
            // only if it is a profit, and not a loss.
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }

        return profit;
    }
}
