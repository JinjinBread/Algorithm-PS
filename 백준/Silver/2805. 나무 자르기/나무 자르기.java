import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] trees;
    static int N;
    static int M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 나무 수
        M = Integer.parseInt(st.nextToken()); // 원하는 나무 길이

        trees = new int[N];
        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        System.out.println(binarySearch(max));

    }

    static int binarySearch(int high) {

        int low = 0;

        while (low < high) {

            int mid = low + (high - low) / 2;

            // 필요한 나무보다 적게 잘렸다면 -> 절단기 길이를 줄여야 함
            if (countTreeLength(mid) < M) {
                high = mid;
            }
            else {
                low = mid + 1;
            }

        }

        return low - 1;
    }

    static long countTreeLength(int sawLength) {

        long treeLength = 0;

        for (int tree : trees) {
            // 절단기 길이보다 트리가 더 높은 경우만 나무를 얻을 수 있음
            if (sawLength < tree) {
                treeLength += (tree - sawLength);
            }
        }

        return treeLength;
    }

}
