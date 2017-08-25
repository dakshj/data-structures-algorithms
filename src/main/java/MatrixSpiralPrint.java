import java.util.ArrayList;
import java.util.List;

public class MatrixSpiralPrint {

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };

        System.out.println(print(mat));
    }

    private static List<Integer> print(int[][] mat) {
        List<Integer> list = new ArrayList<>();

        if (mat.length == 0 || mat[0].length == 0) return list;

        // Row Start Index
        int rS = 0;
        // Row End Index
        int rE = mat.length - 1;

        // Column Start Index
        int cS = 0;
        // Column End Index
        int cE = mat[0].length - 1;

        // Iterator
        int i;

        while (rS <= rE && cS <= cE) {
            // Top Row Left-to-Right
            for (i = cS; i <= cE; i++) {
                list.add(mat[rS][i]);
            }
            rS++;

            // Right Column Top-to-Bottom
            for (i = rS; i <= rE; i++) {
                list.add(mat[i][cE]);
            }
            cE--;

            // Required if condition! [TODO Figure out Why]
            if (rS <= rE) {
                // Bottom Row Right-to-Left
                for (i = cE; i >= cS; i--) {
                    list.add(mat[rE][i]);
                }
                rE--;
            }

            // Required if condition! [TODO Figure out Why]
            if (cS <= cE) {
                // Left Column Bottom-to-Top
                for (i = rE; i >= rS; i--) {
                    list.add(mat[i][cS]);
                }
                cS++;
            }
        }

        return list;
    }
}
