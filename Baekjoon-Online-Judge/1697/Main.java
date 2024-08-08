import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치

        // 1. 동생이 수빈이 보다 뒤에 있는 경우(K < N) (혹은 처음부터 같은 위치에 있는 경우도 해당 분기에 포함함)
        // 수빈이 위치에서 동생 위치를 뺀다. (순간이동은 앞으로만 갈 수 있으므로 걸어서 가는 방법밖에 없다.)
        // N - K

        if (K <= N) {
            bw.write(String.valueOf((N - K)));
            bw.flush();
            return;
        }

        // 2. 동생이 수빈이 보다 앞에 있는 경우(K > N)

        int[] dp = new int[100001];

        // 값 초기화 (앞에 있는 수 이용하므로)
        for (int i = 0; i < N; i++) {
            dp[i] = N - i;
        }

        for (int i = N+1; i <= K; i++) {

            int min;

            // 짝수 위치라면, 순간 이동한 값
            if (i % 2 == 0) {
                min = dp[i / 2] + 1;
            }
            // 홀수 위치라면, (양 옆의 짝수 위치에서 + 1을 한) 값
            // 아래에서 (왼쪽 위치에 + 1 한) 값과 비교하므로
            // 해당 분기문에선 (오른쪽 위치에 + 1 한) 값을 넣어둔다.
            else {
                min = dp[(i+1)/2] + 2; // +2는 (i+1)/2 위치에서 현재 오른쪽 위치로 순간이동하는 데 1초, 현재 (홀수)위치로 걸어오는 데 1초이므로 해주는 것
            }

            // 순차적으로 dp에 값이 넣어지므로 dp[(i-1)/2] + 2로 구하지 않아도 된다.
            dp[i] = Math.min(min, dp[i-1] + 1);
        }

        bw.write(String.valueOf(dp[K]));
        bw.flush();
    }
}
