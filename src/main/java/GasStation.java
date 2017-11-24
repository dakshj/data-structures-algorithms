import java.util.List;

class GasStation {

    private int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
        int totalUnusedGas = 0;
        int startIndex = 0;

        // Tracks the the sum of the unused gas.
        // As soon as totalNonZeroUnusedGas goes below 0,
        // the next index is marked as the starting index.
        // Else, the currentUnusedGas is added to totalNonZeroUnusedGas.
        //
        // This helps in finding the correct startIndex.
        int totalNonZeroUnusedGas = 0;

        for (int i = 0; i < gas.size(); i++) {
            final int currentUnusedGas = gas.get(i) - cost.get(i);
            totalUnusedGas += currentUnusedGas;

            if (totalNonZeroUnusedGas >= 0) {
                totalNonZeroUnusedGas += currentUnusedGas;
            } else {
                totalNonZeroUnusedGas = currentUnusedGas;
                startIndex = i;
            }
        }

        // If totalUnusedGas is >= 0, then it means that even with multiple sub-trips where the
        // gas ran out, we will still be able to eventually reach the end, if we just started
        // at any other index.
        // It works because suppose the startIndex is x. Then sum(0,n-1) will be THE SAME AS
        // sum(x,n-1) + sum(0,x-1). Thus, if we have a total non-negative sum of unused gas,
        // Then it means that we will be able to reach from at least one index to itself again.
        return totalUnusedGas >= 0 ? startIndex : -1;
    }
}
