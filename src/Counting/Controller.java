package Counting;

import java.util.Arrays;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataGenerator data = new DataGenerator(100);
		int[] values = data.generateData();
		BloomFilter bloom = new BloomFilter(100, .001);
		System.out.println("size is: " + bloom.getFilterSize() + " and hasNumbers are: " + bloom.getHashNumbers());
		for(int val: values) {
			bloom.addValue(val);
		}
	}

}
