import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int[] spot = new int[200001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = N-1; i >= 0; i--) {
            spot[i] = spot[i+1] + 1;
        }

        for(int i = N+1; i <= K; i++) {
            
            int min = Integer.MAX_VALUE;            

            // 1. 작은 spot에서 현재 spot으로 + 1초
            // 2. 큰 spot에서 현재 spot으로 + 1초
            // 3. (짝수인 경우) spot[현재spot / 2] + 0초의 값

            // 짝수 위치일 경우
            if ((i % 2) == 0) {
                min = spot[i/2];
            }
            else {
                min = spot[(i+1)/2] + 1;
            }

            spot[i] = Math.min(min, spot[(i-1)] + 1);
        }

        bw.write(String.valueOf(spot[K]));
        bw.flush();
    }

}
