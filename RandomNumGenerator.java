/*
	@author Alvin Grissom 
*/

import java.util.concurrent.ThreadLocalRandom;
public class RandomNumGenerator {
    public static void main(String[] args) {
		long num = Long.parseLong(args[0]);
		long[] nums;

		//nums = getRandomNumbers(num);
		for(long i = 0; i < num; i++) {
		    System.out.print(ThreadLocalRandom.current().nextLong(0,num) + " ");
		}
		
		System.out.println();
    }

    public static int[] getRandomNumbers(int num) {
		int[] nums = new int[num];

		for(int i = 0; i < num; i++) {
		    nums[i] = ThreadLocalRandom.current().nextInt(0, num);
		}

		return nums;
    }
}