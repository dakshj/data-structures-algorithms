import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheShort<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCacheShort(final int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
