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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] lan = new int[K];

        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }

        long length = getLength(N, lan) - 1;
        bw.write(String.valueOf(length));
        bw.flush();
    }

    // '최대' 랜선의 길이 -> Upper bound에서 -1 을 뺀 값
    private static long getLength(int N, int[] lan) {

        long low = 1; // 랜선의 길이는 '자연수'
        long high = Arrays.stream(lan).max().getAsInt();

        while (low <= high) {

            long mid = (high + low) / 2;

            long num = 0;

            for (int lanLen : lan) {
                num += (lanLen / mid);
            }

            if (num < N) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return low;
    }
}
