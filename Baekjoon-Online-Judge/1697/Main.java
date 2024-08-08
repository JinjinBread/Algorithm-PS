import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int MAX_RANGE = 100001;
    private static int[] visited = new int[MAX_RANGE];
    

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        // int K = Integer.parseInt(st.nextToken()); // 동생의 위치


        // ------ DP(Dynamic Programming, 동적 계획법)를 이용한 풀이

        // 1. 동생이 수빈이 보다 뒤에 있는 경우(K < N) (혹은 처음부터 같은 위치에 있는 경우도 해당 분기에 포함함)
        // 수빈이 위치에서 동생 위치를 뺀다. (순간이동은 앞으로만 갈 수 있으므로 걸어서 가는 방법밖에 없다.)
        // N - K
        // if (K <= N) {
        //     bw.write(String.valueOf((N - K)));
        //     bw.flush();
        //     return;
        // }

        // // 2. 동생이 수빈이 보다 앞에 있는 경우(K > N)
        // int[] dp = new int[100001];

        // // 값 초기화 (앞에 있는 수 이용하므로)
        // for (int i = 0; i < N; i++) {
        //     dp[i] = N - i;
        // }

        // for (int i = N+1; i <= K; i++) {

        //     int min;

        //     // 짝수 위치라면, 순간 이동한 값
        //     if (i % 2 == 0) {
        //         min = dp[i / 2] + 1;
        //     }
        //     // 홀수 위치라면, (양 옆의 짝수 위치에서 + 1을 한) 값
        //     // 아래에서 (왼쪽 위치에 + 1 한) 값과 비교하므로
        //     // 해당 분기문에선 (오른쪽 위치에 + 1 한) 값을 넣어둔다.
        //     else {
        //         min = dp[(i+1)/2] + 2; // +2는 (i+1)/2 위치에서 현재 오른쪽 위치로 순간이동하는 데 1초, 현재 (홀수)위치로 걸어오는 데 1초이므로 해주는 것
        //     }

        //     // 순차적으로 dp에 값이 넣어지므로 dp[(i-1)/2] + 2로 구하지 않아도 된다.
        //     dp[i] = Math.min(min, dp[i-1] + 1);
        // }

        // bw.write(String.valueOf(dp[K]));
        // bw.flush();


        // ------ BFS(너비 우선 탐색)를 이용한 풀이

        N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        K = Integer.parseInt(st.nextToken()); // 동생의 위치

        int fastTime = bfs();
        bw.write(String.valueOf(fastTime));
        bw.flush();
    }

    private static int bfs() {

        Queue<Integer> q = new ArrayDeque<>();

        q.add(N);
        visited[N] = 1; // visited 분별을 위해 0이 아닌 1로 설정. (나중에 -1을 해줘야함)

        while(!q.isEmpty()) {

            int now = q.poll();

            if (now == K) {
                return visited[K] - 1; // 방문 여부를 위해 1초부터 시작한 값을 빼줌
            }

            // 1. 왼쪽 위치
            // 2. 오른쪽 위치
            // 3. * 2(순간이동) 위치

            // 범위를 벗어나지 않고 방문한 적이 없다면
            if ((now - 1) >= 0 && visited[now - 1] == 0) {
                q.add(now - 1);
                visited[now - 1] = visited[now] + 1;
            }
            if ((now + 1) < MAX_RANGE && visited[now + 1] == 0) {
                q.add(now + 1);
                visited[now + 1] = visited[now] + 1;
            }
            if ((now * 2) < MAX_RANGE && visited[now * 2] == 0) {
                q.add(now * 2);
                visited[now * 2] = visited[now] + 1;
            }

        }

        return -1;
    }
}
