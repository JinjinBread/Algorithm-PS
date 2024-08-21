
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            for(int prime: goldbach(n)) {
                bw.write(String.valueOf(prime + " "));
            }
            bw.newLine();
        }

        bw.flush();
    }

    private static int[] goldbach(int n) {

        int[] partition = new int[2];
        int min = Integer.MAX_VALUE;

        for (int i = 2; i <= n/2; i++) {
            
            if (!isPrime(i) || !isPrime(n-i)) {
                continue;
            }

            // 4나 9 같은 수
            if (i * i == n) {
                partition[0] = i;
                partition[1] = i;
                break;
            }
            
            // 두 소수의 차이가 가장 작은 것을 출력
            if (min > ((n-i) - i)) {
                partition[0] = i;
                partition[1] = n-i;
                min = ((n-i) - i);
            }
        }

        return partition;
    }

    private static boolean isPrime(int num) {

        if (num == 2)
            return true;

        for (int i = 2; i <= Math.sqrt(num); i++) {

            if ((num % i) == 0) {
                return false;
            }
        }

        return true;
    }

}
