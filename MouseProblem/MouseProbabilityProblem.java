package MouseProblem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MouseProbabilityProblem {

	public static void main(String[] args) { 
		
		List<int[]> connection = new ArrayList<>();
		connection.add(new int[] {1});
		connection.add(new int[] {0, 2, 3});
		connection.add(new int[] {1, 3, 4});
		connection.add(new int[] {1, 2, 4});
		connection.add(new int[] {2, 3, 5});
		connection.add(new int[] {});
		
		int prev = 0;
		int start = 1;
		List<Integer> exit = new ArrayList<>();
		
		Random ran = new Random();
		
		for (int i=0; i<1000000; i++) {
			int rn = ran.nextInt(2) + 1;
			int[] arr = connection.get(start);
			
			int optionA = -1;
			int optionB = -1;
			
			for(int j:arr) {
				if (j != prev) {
					if (optionA == -1) {
						optionA = j;
					}
					if (optionA != -1) {
						optionB = j;
					}
				}
			} 
			
			if (rn == 1) {
				prev = start;
				start = optionA;
			} else if (rn == 2) {
				prev = start;
				start = optionB;
			}
						
			if (start == 0 || start == 5) {
				exit.add(start);
				start = 1;
				prev = 0;
			}
		}
		
		double occurrences0 = Collections.frequency(exit, 0);
		double occurrences5 = Collections.frequency(exit, 5);

		double probA = occurrences0 / exit.size();
		double probB = occurrences5 / exit.size();

		System.out.println("Prob of A: " + probA);
		System.out.println("Prob of B: " + probB);

		
	}

}
