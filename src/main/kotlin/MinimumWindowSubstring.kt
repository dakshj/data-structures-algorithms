private class MinimumWindowSubstring {

    fun get(s: String, t: String): String {
        require(t.length < s.length, { "Length of t cannot exceed length of s!" })

        /** Map of frequencies of all characters in [t] */
        val tMap = t.groupingBy { it }.eachCount().toMutableMap()

        /** Maintains the amount of characters in [t] currently matched in [s] */
        var matchedAmount = 0

        /** The current start index, used for trying out different window start indices */
        var currentStart = 0

        /** The start index of the stored optimal window */
        var savedWindowStart = 0
        var savedWindowLength = Int.MAX_VALUE

        /** After each for loop, even if the window contains all [t] chars, we will still decrement
         * the frequency of that char in tMap, but not increment matchedAmount.
         * This will help in the later while loop by allowing us to skip the first current
         * window char, *even if* it is one of the chars in [t].*/
        s.forEachIndexed { end, c ->
            /** Check for current char of [s] being matched with one of the multiple occurrences of
             * the same char in [t].
             * Update matchedAmount if found, and then decrement the frequency of that char in
             * tMap.
             * Thus, tMap will store only the number of chars pending to be matched. */
            if (tMap.containsKey(c)) {
                if (tMap[c]!! > 0) {
                    matchedAmount++
                }
                tMap[c] = tMap[c]!! - 1
            }

            /** Try to optimize the window length by reducing it to as minimum as possible.
             * After an iteration of this while loop, we still try to see if the window still
             * contains all the chars of [t] */
            while (matchedAmount == t.length) {
                /** The savedWindowStart and savedWindowLength values will be set the first and every time,
                 * whenever the current window still matches all the chars in [t],
                 * or more (be they different or the same chars, doesn't matter). */
                /** If the stored optimal window is not as optimal as the current window,
                 * then updated it */
                val currentWindowLength = end - currentStart + 1
                if (savedWindowLength > currentWindowLength) {
                    savedWindowStart = currentStart
                    savedWindowLength = currentWindowLength
                }

                /** Now we try to move the currentStart by one index forward */

                /** But first, we save the char pointed by currentStart in [s] */
                val firstChar = s[currentStart]

                /** Increment currentStart so as to try and optimize the window size */
                currentStart++

                /** However, if this iteration's firstChar is in tMap, */
                if (tMap.containsKey(firstChar)) {
                    /** then since we are skipping this first char, we need to restore the count
                     * of this to tMap */
                    tMap[firstChar] = tMap[firstChar]!! + 1

                    /** Count may still be <= 0 if the [s] window may have more no. of the same
                     * first chars than those present in [t]. Thus, we need to check and then only
                     * decrement matchedAmount.
                     * If the frequency was <=0, then it would mean that the current window
                     * still has >= the correct amount of firstChars in it, regardless of skipping
                     * of the current first index. */
                    if (tMap[firstChar]!! > 0) {
                        matchedAmount--
                    }
                }
            }
        }

        return if (savedWindowLength == Int.MAX_VALUE) ""
        else s.substring(savedWindowStart, savedWindowStart + savedWindowLength)
    }
}

fun main(args: Array<String>) {
    println(MinimumWindowSubstring().get(s = "fadfas", t = "sad"))
    println(MinimumWindowSubstring().get(s = "ADOBECODEBANC", t = "ABC"))
}
