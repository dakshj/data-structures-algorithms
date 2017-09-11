private class PhoneKeypadCombinations {
    companion object {
        val digitsToLettersMap = mapOf(
                2 to listOf('A', 'B', 'C'),
                3 to listOf('D', 'E', 'F'),
                4 to listOf('G', 'H', 'I'),
                5 to listOf('J', 'K', 'L'),
                6 to listOf('M', 'N', 'O'),
                7 to listOf('P', 'Q', 'R', 'S'),
                8 to listOf('T', 'U', 'V'),
                9 to listOf('W', 'X', 'Y', 'Z')
        )
    }

    fun get(digits: String): List<String> {
        val list = mutableListOf<String>()
        get(digits.replace("1", ""), prefix = "", list = list)
        return list
    }

    private fun get(digits: String, prefix: String, list: MutableList<String>) {
        if (digits.isEmpty()) {
            list.add(prefix)
            return
        }

        digitsToLettersMap[Character.getNumericValue(digits[0])]!!.forEach {
            get(digits.substring(1 until digits.length), prefix + it, list)
        }
    }
}

fun main(args: Array<String>) {
    println(PhoneKeypadCombinations().get("112311"))
    println(PhoneKeypadCombinations().get("234"))
}
