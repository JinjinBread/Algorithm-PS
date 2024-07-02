import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); //최대힙

        if (N == 1) {
            bw.write("0");
            bw.flush();
            return;
        }

        int dasom = Integer.parseInt(br.readLine());

        for (int i = 0; i < N - 1; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int count = 0;

        while (true) {
            int max = pq.poll();

            if (dasom > max)
                break;

            pq.add(--max);
            dasom++;
            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}