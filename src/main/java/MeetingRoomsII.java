import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MeetingRoomsII {

    private static class Interval {
        int start;
        int end;

        private Interval() {
            start = 0;
            end = 0;
        }

        private Interval(final int s, final int e) {
            start = s;
            end = e;
        }
    }

    private enum Type {
        ARRIVE,
        DEPART
    }

    private static class Event {
        private Type type;
        private int date;

        private Event(final Type type, final int date) {
            this.type = type;
            this.date = date;
        }
    }

    private int minMeetingRooms(final Interval[] intervals) {
        final List<Event> list = new ArrayList<>();

        // Flatten the pair of values into a single list differentiated by Event types
        for (final Interval interval : intervals) {
            list.add(new Event(Type.ARRIVE, interval.start));
            list.add(new Event(Type.DEPART, interval.end));
        }

        // Sort list by dates
        list.sort(Comparator.comparing(o -> o.date));

        int count = 0;
        int max = 0;

        for (int i = 0; i < list.size(); i++) {
            final Event event = list.get(i);

            if (event.type == Type.ARRIVE) {
                count++;
            } else {
                count--;
            }

            // Only update max when we are done with TODAY, and the next item
            // in the list is a later day.
            // This is done so that if we have X departures and then X arrivals on the same day,
            // we will not consider the X arrivals as X extra meeting rooms required.
            // This is considered because the timings of the arrivals and departures
            // are not specified, thus having multiple entries for the same day.
            if (i == list.size() - 1 || (i < list.size() - 1 && list.get(i + 1).date != event.date)) {
                max = Math.max(max, count);
            }
        }

        return max;
    }
}
