import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] have;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        have = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            have[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(have); //오름차순 정렬

        int M = Integer.parseInt(br.readLine());
        int[] result = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int find = Integer.parseInt(st.nextToken());
            result[i] = getUpperBound(find) - getLowerBound(find);
        }

        for (int i = 0; i < result.length; i++) {
            if (i != 0)
                bw.write(" ");
            bw.write(String.valueOf(result[i]));
        }
        bw.flush();
    }

    private static int getLowerBound(int findNum) {

        int low = 0;
        int high = have.length;

        while (low < high) {

            int mid = low + ((high - low) / 2);

            // 중복 원소가 있다면, high를 줄임(하한)
            if (have[mid] >= findNum) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
            
        }

        return low;
    }

    private static int getUpperBound(int findNum) {

        int low = 0;
        int high = have.length;

        while (low < high) {

            int mid = low + ((high - low) / 2);

            if (have[mid] > findNum) {
                high = mid;
            }
            // 중복 원소가 있다면, low를 늘림(상한)
            else {
                low = mid + 1;
            }
            
        }

        return low;
    }
}
