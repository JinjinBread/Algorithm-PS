import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dq = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            dq.add(i);
        }
        
        while (!dq.isEmpty()) {

            int n = dq.pollFirst(); // == dq.poll();
            bw.write(n + " ");

            if (dq.size() == 0) {
                break;
            }

            // 음수인 경우
            if (arr[n] < 0) {
                moveLeft(Math.abs(arr[n]));
            }
            else {
                moveRight(arr[n]);
            }
        }

        bw.flush();
    }

    static void moveLeft(int n) {
        for (int i = 0; i < n; i++) {
            int temp = dq.pollLast();
            dq.addFirst(temp);
        }
    }

    static void moveRight(int n) {
        for (int i = 0; i < n - 1; i++) {
            int temp = dq.pollFirst();
            dq.addLast(temp); // == dq.add(temp)
        }
    }
}
