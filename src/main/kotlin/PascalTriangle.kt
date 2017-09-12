private class PascalTriangle {
    fun generate(a: Int): ArrayList<ArrayList<Int>> {
        val ans = ArrayList<ArrayList<Int>>()

        if (a == 0) {
            return ans
        }

        for (i in 0 until a) {
            val temp = ArrayList<Int>()

            for (j in 0..i) {
                if (j == 0 || j == i) {
                    temp.add(1)
                    continue
                }

                temp.add(ans[i - 1][j] + ans[i - 1][j - 1])
            }

            ans.add(temp)
        }

        return ans
    }
}

fun main(args: Array<String>) {
    PascalTriangle().generate(8).forEach { println(it) }
}
