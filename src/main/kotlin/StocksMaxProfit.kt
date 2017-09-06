private class StocksMaxProfit {

    fun getForOneTrade(stocks: List<Int>): Int {
        if (stocks.size <= 1) return 0

        var maxStock = Int.MIN_VALUE
        var maxGain = Int.MIN_VALUE

        stocks.asReversed()

        stocks.reversed().forEach {
            maxStock = maxOf(maxStock, it)
            maxGain = maxOf(maxGain, maxStock - it)
        }

        return maxGain
    }

    fun getForTwoTrades(stocks: List<Int>): Int {
        if (stocks.size <= 1) return 0

        val gains = mutableListOf<Int>()
        stocks.forEach { gains.add(0) }

        // Initialized to last element, because we will be skipping
        // the last element in the below iteration
        var maxStock = stocks.last()

        for (i in stocks.size - 2 downTo 0) {
            maxStock = maxOf(maxStock, stocks[i])
            // Max of previous iteration where i  was i+1;
            // and difference of max stock price and current stock price
            gains[i] = maxOf(gains[i + 1], maxStock - stocks[i])
        }

        // Initialized to first element, because we will be skipping
        // the first element in the below iteration
        var minStock = stocks.first()

        for (i in 1 until stocks.size) {
            minStock = minOf(minStock, stocks[i])
            // Max of previous iteration where i was i-1;
            // and calculation of previously stored ith gain {from above for loop}
            // summed with difference between current stock price and min stock price
            gains[i] = maxOf(gains[i - 1], gains[i] + (stocks[i] - minStock))
        }

        return gains.last()
    }

    fun getForKTrades(prices: List<Int>, k: Int): Int {
        TODO("not implemented")
    }
}

fun main(args: Array<String>) {
    val prices = listOf(2, 30, 15, 10, 8, 25, 80)
    val k = 3

    println("One Trade = ${StocksMaxProfit().getForOneTrade(prices)}")
    println("Two Trades = ${StocksMaxProfit().getForTwoTrades(prices)}")
    println("K=$k Trades = ${StocksMaxProfit().getForKTrades(prices, k)}")
}
