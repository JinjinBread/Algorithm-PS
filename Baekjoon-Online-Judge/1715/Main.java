
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.valueOf(br.readLine()));
        }

        int compareNum = 0;
        while (pq.size() != 1) {
            int bundle1 = pq.poll();
            int bundle2 = pq.poll();

            int twoBundleToOneBundle = bundle1 + bundle2;

            compareNum += twoBundleToOneBundle;
            pq.add(twoBundleToOneBundle);
        }

        System.out.println(compareNum);
    }
}
