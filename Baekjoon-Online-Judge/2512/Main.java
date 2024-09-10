import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static int[] budget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        budget = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, budget[i]);
        }

        int totalBudget = Integer.parseInt(br.readLine());

        int totalNeededBudget = Arrays.stream(budget).sum();

        if (totalNeededBudget <= totalBudget) {
            bw.write(String.valueOf(max));
            bw.flush(); 
            return;
        }

        int maxBudget = getUpperLimit(totalBudget) - 1;
        bw.write(String.valueOf(maxBudget));
        bw.flush();
    }

    static int getUpperLimit(int totalBudget) {

        int low = 0;
        int high = totalBudget;

        while (low <= high) {

            int mid = (low + high) / 2;

            // mid 가 상한액이라고 추정했을 때, 전체 예산보다 크다면
            if (calculateUsedBudget(mid) > totalBudget) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return low;
    }

    static int calculateUsedBudget(int upperLimit) {
        int usedBudget = 0;
        for (int b : budget) {
            if (b < upperLimit) {
                usedBudget += b;
                continue;
            }
            usedBudget += upperLimit;
        }

        return usedBudget;
    }
}
