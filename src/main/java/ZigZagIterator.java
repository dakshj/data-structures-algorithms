import java.util.List;

class ZigZagIterator {

    private final List<Integer> v1;
    private final List<Integer> v2;
    private int remaining;
    private boolean firstList = true;

    public ZigZagIterator(final List<Integer> v1, final List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        remaining = v1.size() + v2.size();
    }

    public int next() {
        int result;

        if (firstList) {
            if (!v1.isEmpty()) {
                result = v1.remove(0);
            } else {
                result = v2.remove(0);
            }
        } else {
            if (!v2.isEmpty()) {
                result = v2.remove(0);
            } else {
                result = v1.remove(0);
            }
        }

        remaining--;
        firstList = !firstList;

        return result;
    }

    public boolean hasNext() {
        return remaining != 0;
    }
}
