import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 중간값을 더해야 기다리는 시간이 최소가 됨
        // 홀수 개? 중간 값 1개 -> 무조건 1개
        // 짝수 개? 중간 값 2개 -> 중간 값 사이의 개수

        int[] gap = new int[N];

        for (int i = 0; i < N; i++) {
            int T = -1 * (A[i] - B[i]); // 기다리는 시간을 0으로 만들어주는 수
            gap[i] = T;
        }

        //홀수면 
        if (N % 2 != 0) {
            bw.write(String.valueOf(1));
            bw.flush();
            return;
        }

        Arrays.sort(gap);
        int n1 = gap[N / 2 - 1];
        int n2 = gap[(N / 2)];
        int result = n2 - n1 + 1; // 짝수면 가운데 두 수 사잇 수
        bw.write(String.valueOf(result));
        bw.flush();
    }

}
