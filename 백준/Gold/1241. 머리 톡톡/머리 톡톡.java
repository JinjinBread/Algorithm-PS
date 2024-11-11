import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

    static int[] students = new int[1000001];

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            students[number]++;
            q.add(number);
        }

        for (int i = 0; i < N; i++) {
            int now = q.poll();
            
            int toktokCnt = countFactor(now);
            bw.write(String.valueOf(toktokCnt));
            bw.newLine();
        }

        bw.flush();
    }

    static int countFactor(int number) {

        Set<Integer> factors = new HashSet<>();
        int count = 0;

        for (int i = 1; i <= Math.sqrt(number); i++) {
            if ((number % i) == 0) {
                factors.add(i);
                factors.add(number / i);
            }
        }

        for (int factor : factors) {
            count += students[factor];
        }

        return count - 1; // 자기 자신은 제외
    }
}