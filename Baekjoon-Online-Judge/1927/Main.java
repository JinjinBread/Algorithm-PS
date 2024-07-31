import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int cmd = Integer.parseInt(br.readLine());

            if (cmd == 0) {
                if (pq.isEmpty()) {
                    bw.write(String.valueOf(0));
                }
                else {
                    bw.write(String.valueOf(pq.poll()));
                }
                bw.newLine();
            }
            else {
                pq.add(cmd);
            }
        }

        bw.flush();
    }

}
