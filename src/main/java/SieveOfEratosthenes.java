/**
 * Created by daksh on 09-Oct-16.
 */
public class SieveOfEratosthenes {

    public static void main(String[] args) {
        new SieveOfEratosthenes().printPrimes(100);
    }

    private void printPrimes(int n) {
        boolean[] primes = new boolean[n + 1];

        for (int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }

        primes[0] = false;
        primes[1] = false;

        int lim = (int) Math.sqrt(n);
        for (int i = 2; i <= lim; i++) {
            if (primes[i]) {
                for (int j = i * i; j < primes.length; j += i) {
                    primes[j] = false;
                }
            }
        }

        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                System.out.println(i);
            }
        }
    }
}
