class StocksMaxProfit {

    private int maxProfitOneTransaction(final int[] prices) {
        if (prices.length == 0) return 0;

        int minPrice = Integer.MAX_VALUE;
        int maxGain = 0;

        for (final int price : prices) {
            minPrice = Math.min(minPrice, price);

            maxGain = Math.max(maxGain, price - minPrice);
        }

        return maxGain;
    }

    private int maxProfitAnyTransactions(final int[] prices) {
        if (prices.length == 0) return 0;

        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Add a greedy pair-wise transaction profit to the total profit,
            // only if it is a profit, and not a loss.
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }

        return profit;
    }

    private int maxProfitTwoTransactions(final int[] prices) {
        final int n = prices.length;
        if (n <= 1) return 0;

        // Initialized to last element, because we will be skipping
        // the last element in the below iteration
        int maxPrice = prices[n - 1];

        int[] gains = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);

            // Max of previous iteration where i  was i+1;
            // and difference of max stock price and current stock price
            gains[i] = Math.max(gains[i + 1], maxPrice - prices[i]);
        }

        // Initialized to first element, because we will be skipping
        // the first element in the below iteration
        int minPrice = prices[0];

        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);

            // Max of previous iteration where i was i-1;
            // and calculation of previously stored ith gain {from above for loop}
            // summed with difference between current stock price and min stock price
            gains[i] = Math.max(gains[i - 1], gains[i] + (prices[i] - minPrice));
        }

        return gains[n - 1];
    }
}
