import java.util.ArrayList;
import java.util.List;

public class TwinPrimeTest {

	public static void main(String[] args) {

		List<Integer> primeNumbers = new ArrayList<>();
		List<Integer> twinPrimeNumbers = new ArrayList<>();

		primeNumbers.add(2);

		int i=3;
		while(i<1000000) {
			boolean isPrime = false;
			for (int p:primeNumbers) {
				
				if (i % p == 0) {
					isPrime = false;
					break;
				} else {
					isPrime = true;
				}
			}

			if (isPrime) {
				primeNumbers.add(i);

				if (primeNumbers.size() >= 2) {
					int b = primeNumbers.get(primeNumbers.size() - 1);
					int a = primeNumbers.get(primeNumbers.size() - 2);
					int diff = b - a;

					if (diff == 2) {
						if (!twinPrimeNumbers.contains(a)) 
							twinPrimeNumbers.add(a);
						if (!twinPrimeNumbers.contains(b)) 
							twinPrimeNumbers.add(b);
					}
				}
			}
			i++;
		}

		System.out.println(twinPrimeNumbers);
	}	
}