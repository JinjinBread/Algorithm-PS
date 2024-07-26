import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] trees;
    private static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(getHeight() - 1));
        bw.flush();
    }
    
    // 절단기의 높이의 최댓값. 중복 값 없음
    private static long getHeight() {

        long low = 0;
        long high = Arrays.stream(trees).max().getAsInt();

        while (low <= high) {

            long mid = (high + low) / 2;
            long num = 0;

            for (int tree : trees) {
                if (tree >= mid) {
                    num += (tree - mid);
                }
            }

            if (num < M) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }

        }

        return low;
    }

}
