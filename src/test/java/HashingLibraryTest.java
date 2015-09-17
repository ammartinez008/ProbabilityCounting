package test.java;

import Utilities.HashingLibrary;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class HashingLibraryTest {

	public HashingLibrary hasher;
	
	@Before
	public void setUp() throws Exception {
		hasher = new HashingLibrary();
	}


	@Test
	public void test() {
		Long hashval;
		HashSet set = new HashSet();
		
		for(int i = 0; i < 3000; ++ i) {
			byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
			hashval = hasher.strongHash64(bytes);
			assertTrue(hashval instanceof Long);
			set.add(hashval);
		}
	//	System.out.println(set.size() + " unique values");
	}

	@Ignore
	@Test
	public void testLookUpTable() {
		long[] table = hasher.lookupTable();
		assertTrue(table.length == 256);
		System.out.println(Arrays.toString(table));
	}
	

	@Test
	public void simpleHash3Test() {
		Integer hashval;
		HashSet set = new HashSet();
		BitSet bset = new BitSet(1000);

		for(int i = 0; i < 30000; ++ i) {
			hashval = hasher.simpleHash3(i,i % 5);
			bset.set(hashval);
			set.add(hashval);
			assertTrue(hashval instanceof Integer);
			//System.out.println("hashval was genereated: " + hashval + " with hashcode: " + bset.hashCode());
		}
		
		System.out.println(set.size() + " unique values");
	}
}
