import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    
    private static class Number {
        int original;
        int abs;
        public Number(int original) { this.original = original; this.abs = Math.abs(original); }
    }

    private static PriorityQueue<Number> pq;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.abs == o2.abs) {
                return Integer.compare(o1.original, o2.original);
            }
            return Integer.compare(o1.abs, o2.abs);
        });
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            manipulateQueue(n);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void manipulateQueue(int n) {

        switch (n) {
            case 0:
                // 비어있는 경우
                if (pq.isEmpty()) {
                    sb.append(0);
                }
                else {
                    Number number = pq.poll();
                    sb.append(number.original);
                }
                sb.append("\n");
                break;
            default:
                pq.add(new Number(n));
        }
    }

}
