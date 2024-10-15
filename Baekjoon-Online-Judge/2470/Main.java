import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] liquid;
    static int N;
    static int liquid1;
    static int liquid2;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        liquid = new int[N];

        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        liquid = Arrays.stream(liquid).sorted().toArray();

        findLiquid();
        
        bw.write(liquid1 + " " + liquid2);

        bw.flush();
    }

    static void findLiquid() {
        
        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;

        while (left < right) {

            int mixed = liquid[left] + liquid[right];

            if (min > Math.abs(mixed)) {
                min = Math.abs(mixed);
                liquid1 = liquid[left];
                liquid2 = liquid[right];
            }
            // 음수면
            if (mixed < 0) {
                left = left + 1;
            }
            // 양수면
            else {
                right = right - 1;
            }
        }
    }
}
