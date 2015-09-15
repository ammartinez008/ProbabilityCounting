package Utilities;

import java.util.BitSet;

public class HashingLibrary {
	private static final long HSTART = 0xBB40E64DA205B064L;
	private static final long HMULT = 7664345821815920749L;
	
	public int simpleHash(int value) {
		int val = 0;
		int prime = 31;
			
		return (value * prime);
		
	}
	
	public int simpleHash2(int value) {
		int val = 0;
		int prime = 17;
		int hash = 1;
		
		return (value * prime);
	}
	
	//third example of a hash function
	public int simpleHash3(int value, int salt) {
	  int constant = 17;
	  final int prime = 37;
	  value = (int) (constant * prime + value) + salt ;
	  return value;
	}
	
	public static final long[] lookupTable() {
		long[] byteTable = new long[256];
		  long h = 0x544B2FBACAAF1684L;
		  for (int i = 0; i < 256; i++) {
		    for (int j = 0; j < 31; j++) {
		      h ^= (h >>> 7);
		      h ^= (h << 11);
		      h ^= (h >>> 10);
		    }
		  //  h = maskValue(h);
		    byteTable[i] = h;
		  }
		  return byteTable;
	}
	
	private static final long[] lookupTable(int hashNumbers) {
		long[] byteTable = new long[256 * hashNumbers];
		long h = 0x544B2FBACAAF1684L;
		  
		  for (int i = 0; i < byteTable.length; i++) {
		    for (int j = 0; j < 31; j++) {
		      h ^= (h >>> 7);
		      h ^= (h << 11);
		      h ^= (h >>> 10);
		    }
		    byteTable[i] = h;
		  }
		  return byteTable;
	}
	
	public static long maskValue(long value) {
		value = value & ((1L << (int) value) - 1);
		return value;
	}
	public long strongHash64(byte[] data) {
		long hashVal = HSTART;
		final long hmult = HMULT;
		final long[] tableLookup = lookupTable();
		
		for(int i = 0; i < data.length; ++ i) {
			hashVal = (hashVal * hmult) ^ tableLookup[data[i] & 0xff];
			hashVal = (hashVal * hmult) ^ tableLookup[(data[i] >>> 8) & 0xff];
		}
		
		return hashVal;
	}
	
	public long strongHash64(byte[] data, int hashNums) {
		long hashVal = HSTART;
		final long hmult = HMULT;
		final long[] tableLookup = lookupTable(hashNums);
		int startIndx = 256 * hashNums;
		for(int i = 0; i < data.length; ++ i) {
			hashVal = (hashVal * hmult) ^ tableLookup[startIndx + (data[i] & 0xff)];
		}
		
		return (hashVal & 0xff);
	}
	
	
}
