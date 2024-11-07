import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        if (N <= 3) {
            System.out.println(1);
            return;
        }

        // 1. N 이하의 소수를 구한다.
        // 2. 투 포인터로 더하면서 N의 경우 수를 구한다.

        int[] prime = getPrime(N);

        int p1 = -1;
        int p2 = 0;
        boolean isNBigger = true;

        int sum = 0;
        int num = (N == prime[prime.length - 1]) ? 1 : 0; // 자기 자신이 소수이면 1로 시작

        while ((p1 != (prime.length - 1)) && (p2 != (prime.length - 1))) {

            // 누적합이 N 보다 작거나 같으면 (누적합 <= N)
            if (isNBigger) {
                sum += prime[p2];
            }
            else { // 누적합 > N
                sum -= prime[p1];
            }

            // 누적합이 N 보다 작거나 같으면 p2 오른쪽으로 이동
            if (sum <= N) {
                if (sum == N) {
                    num++;
                }

                if (p2 != prime.length - 1) {
                    p2++;
                }
                isNBigger = true;
            }
            // 누적합이 N 보다 크면 p1를 오른쪽으로 이동
            else {
                if (p1 != prime.length - 1) {
                    p1++;
                }
                isNBigger = false;
            }

        }

        System.out.println(num);


    }

    static int[] getPrime(int num) {

        List<Integer> prime = new ArrayList<>();

        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                prime.add(i);
            }
        }

        return prime.stream().mapToInt(Integer::intValue).toArray();
    }

    static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if ((num % i) == 0) {
                return false;
            }
        }

        return true;
    } 
}