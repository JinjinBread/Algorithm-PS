import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        while(true) {

            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }
            
            int num = 0;
            for (int i = n + 1; i <= n * 2; i++) {
                if (isPrime(i)) {
                    num++;
                }
            }
            
            sb.append(num).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if ((n % i) == 0)
                return false;
        }
        return true;
    }
}
