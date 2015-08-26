package Counting;

import java.util.Random;

public class DataGenerator {
	public int size;
	public int[] data;
	
	public DataGenerator(int size) {
		this.size = size;
		this.data = new int[size];
	}
	
	public int[] generateData() {
		Random rand = new Random();
		
		for(int i = 0; i < size; ++ i) {
			data[i] = rand.nextInt(1000 - 0);
		}
		
		return data;
	}


}
