import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.util.Pair;

class MaxGapBetweenIndices {

    public int maxGap(final List<Integer> list) {
        int maxGap = 0;

        // Create a list of values vs. their indices, so that when we sort the list by value,
        // the positions of those values is not lost
        final List<Pair<Integer, Integer>> indexedList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            indexedList.add(new Pair<>(list.get(i), i));
        }

        indexedList.sort(Comparator.comparingInt(Pair::getKey));

        // Init max index to last element's index
        int maxIndex = indexedList.get(indexedList.size() - 1).getValue();

        // Iter reverse from 2nd last to initial element
        for (int i = indexedList.size() - 2; i >= 0; i--) {
            // Update maxIndex
            maxIndex = Math.max(maxIndex, indexedList.get(i).getValue());

            // Calculate max gap between maxIndex and current value's index.
            //
            // Since the list has been sorted by value, and we are iterating in reverse,
            // we can be sure that the condition of
            // list[i] <= list[j] while finding j - i
            // is satisfied.
            maxGap = Math.max(maxGap, maxIndex - indexedList.get(i).getValue());
        }

        return maxGap;
    }
}
