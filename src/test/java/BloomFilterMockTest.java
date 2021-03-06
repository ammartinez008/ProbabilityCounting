package test.java;

import Counting.BloomFilter;
import Utilities.DataGenerator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterMockTest {
	BloomFilter bloom;
	DataGenerator dataGen;
	int[] testValues; 
	int[] uncontainedVals;

	@Before
	public void generateData() {
		bloom = new BloomFilter(1000,0.001);
		dataGen = new DataGenerator(300);
		testValues = dataGen.generateData();
	}

	
	//test adding, contains values in filter
	@Ignore
	@Test
	public void testAdd() {
		for(int i = 0; i < testValues.length; ++ i) {
			bloom.add(testValues[i]);
		}
		
		for(int val: testValues) {
			assertTrue(bloom.contains(val));
		}
		
	}
	
	@Test
	public void failAddingTests() {
		HashSet<Integer> values = new HashSet<Integer>();
		
		
		for(int i = 0; i < testValues.length / 2; ++ i) {
			values.add(testValues[i]);
		}
		
		for(int val : values) {
			//System.out.println("Adding: " + val);
			bloom.add(val);
		}
		
		System.out.println("size of set: " + values.size());
		for(int i = (testValues.length / 2) + 1 ; i < testValues.length;  i ++ ) {
			assertFalse("Testing: value: " + testValues[i] + " on index: " + i,
					bloom.contains(testValues[i]));
		}
	}
	
}
