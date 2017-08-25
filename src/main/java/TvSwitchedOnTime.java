import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.careercup.com/question?id=5707346257903616
 */
public class TvSwitchedOnTime {

    public static void main(String[] args) {
        int[][] array;

        array = new int[][]{{1, 4}, {2, 3}};
        System.out.println(getTvSwitchedOnTime(array)); // 3

        array = new int[][]{{4, 6}, {1, 2}};
        System.out.println(getTvSwitchedOnTime(array)); // 3

        array = new int[][]{{1, 4}, {6, 8}, {2, 4}, {7, 9}, {10, 15}};
        System.out.println(getTvSwitchedOnTime(array)); // 11
    }

    private static int getTvSwitchedOnTime(int[][] array) {
        List<Time> times = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                times.add(new Time(array[i][j], j == 0));
            }
        }

        Collections.sort(times, (o1, o2) -> o1.value - o2.value);

        int currentViewers = 0;
        int lastOnTime = 0;
        int totalViewingTime = 0;

        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);

            if (time.on) {
                currentViewers++;
            } else {
                currentViewers--;
            }

            if (currentViewers == 0) {
                totalViewingTime += time.value - lastOnTime;
                lastOnTime = 0;
            } else if (lastOnTime == 0) {
                lastOnTime = time.value;
            }
        }

        return totalViewingTime;
    }

    private static class Time {
        private int value;
        private boolean on;

        private Time(int value, boolean on) {
            this.value = value;
            this.on = on;
        }
    }
}
