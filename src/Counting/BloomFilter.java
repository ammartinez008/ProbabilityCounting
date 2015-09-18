package Counting;

import Utilities.HashingLibrary;

import java.util.BitSet;

public class BloomFilter implements CounterStrategy {

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
		this.filter = new BitSet();
		this.hasher = new HashingLibrary();
	}
	
	//creates filter size based off std error and size given
	public int generateFilterSize(int size, double stdError) {
		int filterSize;
		double stdErrLog = Math.log(stdError);
		double ln2Squared = ln2() * ln2();
		filterSize = (int) (-1 * ((size * stdErrLog) / ln2Squared));
		
		return filterSize;
	}
	
	// generates number of hash functions 
	// (number of bits that will be checked per added value)
	public int generateHashNumbers(int filterSize, int size) {
		return (int) ((filterSize/size) * ln2());
	}
	
	//filter logic
	public void add(int val) {
		int hashCode;
		
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

	//math functions
	public double ln2() {
		return Math.log(2);
	}

	// setters and getters
	public int hashValue(int value, int hashIndex) {
		return hasher.simpleHash3(value, hashIndex);
	}
	
	public int getFilterSize() {
		return filterSize;
	}

	public int getHashNumbers() {
		return hashNumbers;
	}
	
}
