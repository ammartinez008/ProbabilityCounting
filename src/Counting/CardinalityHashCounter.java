package Counting;

import java.util.HashSet;

public class CardinalityHashCounter implements CounterStrategy{
    HashSet set;

    public CardinalityHashCounter() {
        set = new HashSet();
    }


    public void add(int value) {
        set.add(value);
    }

    public boolean contains(int value) {
        return set.contains(value);
    }

    public int getCardinality() {
        return set.size();
    }


}
