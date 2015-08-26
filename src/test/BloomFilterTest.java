package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import Counting.BloomFilter;
import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class BloomFilterTest {
	BloomFilter filter;
	
	private static final Object[] illegalParams() {
		return new Object[] {
			new Object[]{0, .25},
			new Object[]{1, -1},
			new Object[]{0, 0},
			new Object[]{100, -10.3},
			new Object[]{-1, .2}
		};
	}
	
	private static final Object[] validParams() {
		return new Object[] {
				new Object[] {100, .2},
				new Object[] {1000, .06},
				new Object[] {1011, .001},
		};
	}
	
	@Test
	@Parameters(method = "validParams")
	public void test(int size, double stderr) {
		filter = new BloomFilter(size, stderr);
		assertEquals(size, filter.maxCardinality);
	}

	
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "illegalParams")
	public void testIllegalArguments(int size, double stderr) {
		new BloomFilter(size, stderr);
	}
}
