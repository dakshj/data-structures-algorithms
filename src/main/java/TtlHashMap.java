import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;

/**
 * A hash map that expires and removes items if they are older than a given
 * time-to-live.
 * <p>
 * The expiry is a passive process, items aren't removed until they are
 * retrieved and deemed to be expired by {@link #get(Object)}.
 */
public class TtlHashMap<K, V> implements Map<K, V> {

    private final HashMap<K, V> store = new HashMap<>();
    private final HashMap<K, Long> timestamps = new HashMap<>();
    private final long ttl;

    public TtlHashMap(final TimeUnit ttlUnit, final long ttlValue) {
        this.ttl = ttlUnit.toNanos(ttlValue);
    }

    @Override
    public V get(final Object key) {
        V value = this.store.get(key);

        if (value != null && expired(key)) {
            store.remove(key);
            timestamps.remove(key);
            return null;
        } else {
            return value;
        }
    }

    private boolean expired(final Object key) {
        return (System.nanoTime() - timestamps.get(key)) > this.ttl;
    }

    @Override
    public V put(final K key, final V value) {
        timestamps.put(key, System.nanoTime());
        return store.put(key, value);
    }

    @Override
    public int size() {
        clearExpired();
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return store.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return store.containsValue(value);
    }

    @Override
    public V remove(final Object key) {
        timestamps.remove(key);
        return store.remove(key);
    }

    @Override
    public void putAll(@NotNull final Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        timestamps.clear();
        store.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        clearExpired();
        return unmodifiableSet(store.keySet());
    }

    @NotNull
    @Override
    public Collection<V> values() {
        clearExpired();
        return unmodifiableCollection(store.values());
    }

    @NotNull
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        clearExpired();
        return unmodifiableSet(store.entrySet());
    }

    private void clearExpired() {
        for (K k : store.keySet()) {
            this.get(k);
        }
    }
}
