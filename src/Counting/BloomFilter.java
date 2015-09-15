package Counting;

import java.nio.ByteBuffer;
import java.util.BitSet;

import Utilities.HashingLibrary;

public class BloomFilter {

	public int filterSize;
	public double stdError;
	public int maxCardinality;
	public int hashNumbers;
	public int hashMask;
	public BitSet filter;
	
	public HashingLibrary hasher;
	
	//constructor
	public BloomFilter(int size, double stdError) {
		if(size < 1 || stdError < 0) {
			throw new IllegalArgumentException("size: " + size + ", stdError: " + stdError);
		}
		
		this.maxCardinality = size;
		this.stdError = stdError;
		this.filterSize = generateFilterSize(size, stdError);
		this.hashMask = filterSize - 1;
		this.hashNumbers = generateHashNumbers(filterSize, size);
		this.filter = new BitSet(filterSize);
		this.hasher = new HashingLibrary();
	}
	
	//creates filter size based off std error and size given
	public int generateFilterSize(int size, double stdError) {
		int filterSize = 0;
		double stdErrLog = Math.log(stdError);
		double ln2 = ln2();
		filterSize = (int) (-1 * ((size * stdErrLog) / ln2));
		
		return filterSize;
	}
	
	// generates number of hash functions 
	// (number of bits that will be checked per added value)
	public int generateHashNumbers(int FilterSize, int size) {
		double ln2 = ln2();
		return (int) ((filterSize/size) * ln2);
	}
	
	//filter logic
	public void addValue(int val) {
		int hashCode = 0;
		
		for(int i = 0; i < hashNumbers; ++ i) {
			hashCode = hashValue(val, i + 1 );
			filter.set(hashCode);
		}
		
	}
	
	//check if a given value is in the filter
	public boolean contains(int val) {
		
		for(int i = 0; i < hashNumbers; ++ i) {
			int hashNum = hashValue(val,i + 1);
			if(!filter.get(hashNum))
				return false;
		}
		
		return true;
	}
	
	//return the cardinality
	public int getCardinality() {
	
		return this.maxCardinality;
	}
	
	//math functions
	public double ln2() {
		double val = 0;
		
		val = Math.log(2);
		return val * val;
	}
	

		
	// setters and getters
	public int hashValue(int value, int hashIndex) {
		//todo: need new hash method
		byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
		return Integer.valueOf((Long.toUnsignedString(hasher.strongHash64(bytes)))) % filterSize;
	}
	
	public int getFilterSize() {
		return filterSize;
	}

	public int getHashNumbers() {
		return hashNumbers;
	}
	
}
