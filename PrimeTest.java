// Test if given number is a prime number
// Testing initial commit function

import java.util.Scanner;

import java.util.Random;

import java.math.BigInteger;

public class PrimeTest {

    
    public static boolean isProbablePrime(long n, int iteration){

        if (n < 2){
            return false;
        }
        if (n == 2){
            return true;
        }
        if (n % 2 == 0){
            return false;
        }

        if (iteration < 1){
            throw new IllegalArgumentException("iteration must be at least 1");
        }

        long s = n -1;
        while (s % 2 == 0){
            s /= 2;
        }

        Random rand = new Random();
        for (int i = 0; i < iteration; i++){
            long r = Math.abs(rand.nextLong());
            long a = r % (n - 1) + 1, temp = s;
            long mod = modPower(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n -1){
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;

    }


    public static long modPower(long base, long exp, long mod){
        long result = 1;
        base = Math.floorMod(base, mod);
        while (exp > 0) { 
            if ((exp & 1) == 1) result = mulMod(result, base, mod);
            base = mulMod(base, base, mod);
            exp >>= 1;

        }
        return result;
    }

    public static long mulMod(long a, long b, long mod){
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

    public static void main(String[] args ){
        Scanner scan = new Scanner(System.in);
        System.out.println("Miller Rabin Primality Algorithm Test\n");

        System.out.println("Enter number\n");

        long num = scan.nextLong();

        System.out.println("\nEnter number of iterations");

        int k = scan.nextInt();
        if (k < 1){
            System.out.println("Number of iterations must be at least 1");
            return;
        }

        

        boolean prime = isProbablePrime(num, k);

        if (prime)

            System.out.println("\n"+ num +" is prime");

        else

            System.out.println("\n"+ num +" is composite");

    }





}
