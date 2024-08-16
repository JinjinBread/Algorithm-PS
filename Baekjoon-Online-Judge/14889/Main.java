import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] capacity;
    private static boolean[] visited;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        capacity = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                capacity[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번이 들어가는 팀과 아닌 팀을 기준으로 능력치를 계산
        visited = new boolean[N];
        visited[0] = true;

        dfs(0, 1);
        bw.write(String.valueOf(min));
        bw.flush();
    }

    private static void dfs(int idx, int teamCnt) {
        
        if (teamCnt == (N/2)) {
            int gap = getTeamCapacityGap();
            min = Math.min(min, gap);
            return;
        }

        // 1-2-3-4 팀이나 2-1-3-4 팀이나 같은 팀임
        for (int i = idx + 1; i < N; i++) {

            visited[i] = true;
            dfs(i, teamCnt + 1);
            visited[i] = false;
        }

    }

    private static int getTeamCapacityGap() {

        int team1Capacity = 0;
        int team2Capacity = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i와 j가 같은 팀인 경우
                
                if (visited[i] && visited[j]) {
                    team1Capacity += (capacity[i][j] + capacity[j][i]);
                }
                else if (!visited[i] && !visited[j]) {
                    team2Capacity += (capacity[i][j] + capacity[j][i]);
                }
            }
        }

        return Math.abs(team1Capacity - team2Capacity);

    }
}
