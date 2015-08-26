package Counting;

import java.util.BitSet;

public class BloomFilter {

	public int filterSize;
	public double stdError;
	public int maxCardinality;
	public int hashNumbers;
	public BitSet filter;
	
	public BloomFilter(int size, double stdError) {
		if(size < 1 || stdError < 0) {
			throw new IllegalArgumentException("size: " + size + ", stdError: " + stdError);
		}
		
		this.maxCardinality = size;
		this.stdError = stdError;
		this.filterSize = generateFilterSize(size, stdError);
		this.hashNumbers = generateHashNumbers(filterSize, size);
		this.filter = new BitSet();
	}
	
	public int generateFilterSize(int size, double stdError) {
		int filterSize = 0;
		double stdErrLog = Math.log(stdError);
		double ln2 = ln2();
		filterSize = (int) (-1 * ((size * stdErrLog) / ln2));
		
		return filterSize;
	}
	
	public double ln2() {
		double val = 0;
		
		val = Math.log(2);
		return val * val;
	}
	
	public int generateHashNumbers(int FilterSize, int size) {
		double ln2 = ln2();
		return (int) ((filterSize/size) * ln2);
	}

	public int getFilterSize() {
		return filterSize;
	}

	public void setFilterSize(int filterSize) {
		this.filterSize = filterSize;
	}

	public int getHashNumbers() {
		return hashNumbers;
	}

	public void setHashNumbers(int hashNumbers) {
		this.hashNumbers = hashNumbers;
	}
	
	public int hash1(int value) {
		int val = 0;
		int prime = 31;
		
		
		return (value * prime);
	}
	
	public int hash2(int value) {
		int val = 0;
		int prime = 17;
		int hash = 1;
		
		
		return (value * prime);
	}
	
	public int hashValue(int value, int hashIndex) {
		return (hash1(value) + hashIndex*hash2(value)) % this.filterSize;
	}
	
	public void addValue(int val) {
		int hashCode = 0;
		
		for(int i = 0; i < this.hashNumbers; ++ i) {
			hashCode = hashValue(val, i);
			filter.set(hashCode);
			System.out.println(hashCode);
		}
		
	}
}
