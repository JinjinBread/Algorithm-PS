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
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        // DP(다이나믹 프로그래밍, 동적 계획법)를 사용

        int[] dp = new int[N];
        Arrays.fill(dp, 1); // 모든 수를 1로 초기화 (최소 길이는 1이므로)

        // 변수 간의 관계식(점화식) 작성
        // 특정 수를 가장 마지막 원소라고 가정한 부분 수열의 길이를 저장
        // 맨 앞의 수부터 특정 수 보다 작으면 해당 수가 마지막 원소일 때의 부분 수열의 길이 + 1을 저장한다.
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) { // 특정 수 i번째의 수를 가장 마지막 원소라고 가정
                if (numArr[i] > numArr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 더 긴 부분 수열의 길이를 저장
            }
        }

        bw.write(String.valueOf(Arrays.stream(dp).max().getAsInt()));
        bw.flush();
    }
}
