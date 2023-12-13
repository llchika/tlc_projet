package src.utils;

public class Triplet<K, V> {
    private final K key;
    private final V value1;
    private final V value2;

    public Triplet(K key, V value1, V value2) {
        this.key=key;
        this.value1=value1;
        this.value2=value2;
    }

    public K getKey() {
        return key;
    }

    public V getValue1() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }
}
