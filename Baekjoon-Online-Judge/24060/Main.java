import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int[] A;
    private static int[] sorted;
    private static int cumulativeCount;
    private static int answer;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        sorted = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        cumulativeCount = 0;
        answer = -1;

        merge_sort(0, N-1);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void merge_sort(int start, int end) {

        if (start < end) {
            int mid = (start + end) / 2;
            merge_sort(start, mid);
            merge_sort(mid+1, end);
            merge(start, mid, end);
        }
    }

    private static void merge(int start, int mid, int end) {

        int i = start;
        int j = mid+1;
        int idx = 0;

        while (i <= mid && j <= end) {

            if (A[i] < A[j]) {
                sorted[idx++] = A[i++];
            }
            else {
                sorted[idx++] = A[j++];
            }
        }

        // 남은 전반부를 전부 넣는다.
        while (i <= mid) {
            sorted[idx++] = A[i++];
        }

        // 남은 후반부를 전부 넣는다.
        while (j <= end) {
            sorted[idx++] = A[j++];
        }

        i = start;
        idx = 0;
        // A를 갱신하면서 저장횟수를 센다.
        while (i <= end) {
            cumulativeCount++;

            // 만약 K가 전체 저장 횟수보다 크면, answer이 갱신되지 않는다. (answer을 -1로 초기화)
            if (cumulativeCount == K) {
                answer = sorted[idx];
            }

            A[i++] = sorted[idx++];
        }
    }
}
