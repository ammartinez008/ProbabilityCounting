package test.java;

import Counting.CardinalityHashCounter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 9/18/15.
 */
@RunWith(JUnitParamsRunner.class)
public class CardinalityHashCounterTest {
    CardinalityHashCounter hash;

    @Before
    public void setup() {
        hash = new CardinalityHashCounter();
    }

    private static final Object[] addParams() {
        return new Object[] {1,1,2,3,4,5,1111,34,94,99,20};
    }

    @Test
    @Parameters(method = "addParams")
    public void testAdd(int value) {
        hash.add(value);
        assertTrue(hash.contains(value));
    }

    @Test
    public void testEmptyHash() {
        assertFalse(hash.contains(0));
        assertFalse(hash.contains(20));
    }

    @Test
    @Parameters(method = "addParams")
    public void testFalsePositives(int value) {
        hash.add(value);
        assertFalse(hash.contains(10));
    }
}
