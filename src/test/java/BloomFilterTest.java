package test.java;

import Counting.BloomFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
				new Object[] {10000, .001},
				new Object[] {5000, .001},
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
	
	@Test
	@Parameters(method = "validParams")
	public void testFilterSize(int size, double stderr) {
		filter = new BloomFilter(size, stderr);
		assertTrue("Filter is too small: " + 
		filter.getFilterSize() + " for param: " + size, 
				filter.getFilterSize() > size);
				
	}

	//test natural log of two method
	@Test
	public void testNaturalLog() {
		filter = new BloomFilter(10,0.01);
		assertEquals(0.69314718056, filter.ln2(), 0.0002);

	}

	//test generating filter size
	@Test
	public void testCreatingSize() {
		filter = new BloomFilter(10, 0.01);
		assertTrue( filter.generateFilterSize(10, 0.01) > 0);
		assertEquals(143, filter.generateFilterSize(10, 0.001));
	}

	//test hash count value
	@Test
	public void testHashCreationVal() {
		filter = new BloomFilter(10, 0.001);
		assertEquals(9, filter.generateHashNumbers(143,10));
	}


}
