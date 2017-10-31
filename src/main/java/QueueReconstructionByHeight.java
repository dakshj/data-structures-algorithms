import java.util.ArrayList;
import java.util.Arrays;

class QueueReconstructionByHeight {

    private static final int HEIGHT = 0;
    private static final int NUM_PEOPLE_BEFORE = 1;

    private int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0)
            return new int[0][0];

        Arrays.sort(people, (a, b) -> {
            // Sort two people of the same HEIGHT in ascending order of NUM_PEOPLE_BEFORE
            if (a[HEIGHT] == b[HEIGHT]) {
                return a[NUM_PEOPLE_BEFORE] - b[NUM_PEOPLE_BEFORE];
            }

            // Otherwise, sort two people by descending order of HEIGHT
            return b[HEIGHT] - a[HEIGHT];
        });

        ArrayList<int[]> result = new ArrayList<>();

        // Insert each person at the NUM_PEOPLE_BEFORE index because (from the sample test case):
        // When the height 7, then first {7,0} is inserted at 0, then {7,1} is inserted at 1.
        // List = {{7,0}}
        //
        // Next, {6,1} is inserted at 1. Thus, {7,0} is a valid person before {6,1}
        // List = {{7,0}, {6,1}}
        //
        // Next, {5,0} is inserted at 0. Now {7,0} is valid (behind {5,0}), and also
        // {6,1} is valid (behind {7,0}).
        // List = {{5,0}, {7,0}, {6,1}}
        //
        // Next, {5,2} is inserted at 2.
        // List = {{5,0}, {7,0}, {5,2}, {6,1}} ==> which is valid
        for (final int[] person : people) {
            result.add(person[NUM_PEOPLE_BEFORE], person);
        }

        // Convert List to int[][] array
        int[][] res = new int[people.length][2];
        int i = 0;
        for (int[] person : result) {
            res[i][0] = person[0];
            res[i++][1] = person[1];
        }

        return res;
    }

    public static void main(String[] args) {
        final int[][] result = new QueueReconstructionByHeight().reconstructQueue(new int[][]
                {
                        {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
                });

        System.out.println(Arrays.toString(result));
    }
}
