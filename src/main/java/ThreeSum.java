import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by daksh on 08-Oct-16.
 */
public class ThreeSum {

    public static void main(String[] args) {
        new ThreeSum().printThreeSum(new int[]{5, 3, -5, 0, 1, -1, -1, -7, 2});
    }

    private void printThreeSum(int[] array) {
        Arrays.sort(array);

        ArrayList<ArrayList<Integer>> pointsList = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            map.clear();

            for (int j = i + 1; j < array.length; j++) {
                if (map.containsKey(array[j])) {
                    ArrayList<Integer> points = new ArrayList<>();
                    points.add(array[j]);
                    points.addAll(map.get(array[j]));

                    Collections.sort(points);

                    if (!pointsList.contains(points)) {
                        pointsList.add(points);
                    }
                } else {
                    ArrayList<Integer> points = new ArrayList<>();
                    points.add(array[i]);
                    points.add(array[j]);
                    map.put(-array[i] - array[j], points);
                }
            }
        }

        for (ArrayList<Integer> points : pointsList) {
            System.out.println(Arrays.toString(points.toArray()));
        }
    }
}
