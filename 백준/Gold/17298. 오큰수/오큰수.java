import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] NGE = new int[N];
        
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = N-1; i >= 0; i--) {
            
            while (!dq.isEmpty() && dq.peekFirst() <= arr[i]) {
                dq.pollFirst();
            }

            NGE[i] = dq.isEmpty() ? -1 : dq.peekFirst();
            dq.addFirst(arr[i]);
        }

        for(int num: NGE) {
            bw.write(num + " ");
        }
        bw.flush();
    }
}
