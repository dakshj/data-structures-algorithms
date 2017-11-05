import java.util.HashSet;
import java.util.Set;

class NextClosestTime {

    public String nextClosestTime(String time) {
        if (time == null || time.isEmpty() || time.length() != 5) return null;

        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));

        final Set<Integer> set = new HashSet<>();
        set.add(hours / 10);
        set.add(hours % 10);
        set.add(minutes / 10);
        set.add(minutes % 10);

        while (true) {
            // Increment time by one minute
            minutes++;

            if (minutes == 60) {
                minutes = 0;
                hours++;

                if (hours == 24) {
                    hours = 0;
                }
            }

            // Check if all digits are in the set
            if (set.contains(hours / 10) && set.contains(hours % 10) &&
                    set.contains(minutes / 10) && set.contains(minutes % 10)) {
                return buildFormattedTimeString(hours, minutes);
            }
        }
    }

    private String buildFormattedTimeString(final int hours, final int minutes) {
        final StringBuilder sb = new StringBuilder();
        if (hours < 10) sb.append(0);
        sb.append(hours).append(':');
        if (minutes < 10) sb.append(0);
        return sb.append(minutes).toString();
    }
}
