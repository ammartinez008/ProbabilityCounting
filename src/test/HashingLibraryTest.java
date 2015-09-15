package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Utilities.HashingLibrary;

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
		
		for(int i = 0; i < 300; ++ i) {
			byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
			hashval = hasher.strongHash64(bytes);
			assertTrue(hashval instanceof Long);
			String h = Long.toUnsignedString(hashval);
			set.add(h);
			System.out.println(h + " is a legit hashvalue");
		}
		System.out.println(set.size() + " unique values");
	}

	@Ignore
	@Test
	public void testLookUpTable() {
		long[] table = hasher.lookupTable();
		assertTrue(table.length == 256);
		System.out.println(Arrays.toString(table));
	}
	
	@Ignore
	@Test
	public void simpleHash3Test() {
		Integer hashval;
		HashSet set = new HashSet();
		for(int i = 0; i < 300000; ++ i) {
			hashval = hasher.simpleHash3(i);
			set.add(hashval);
			assertTrue(hashval instanceof Integer);
		}
		
		System.out.println(set.size() + " unique values");
	}
}
