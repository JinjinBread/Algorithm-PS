import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<Integer>[] adjLists;
    private static int[] kevinBaconNum;
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 유저 수
        int M = Integer.parseInt(st.nextToken()); // 관계 수

        kevinBaconNum = new int[N+1];
        visited = new boolean[N+1];

        adjLists = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjLists[A].add(B);
            adjLists[B].add(A);
        }

        for (int i = 1; i <= N; i++) {
            kevinBaconNum[i] = bfs(i);
            Arrays.fill(visited, false);
        }

        int answer = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = N; i >= 1; i--) {
            min = Math.min(min, kevinBaconNum[i]);
            // 케빈 베이컨의 수가 더 작은 사람이 갱신됐거나, 케빈 베이컨의 수가 같은 사람인 경우
            if (min == kevinBaconNum[i]) {
                answer = Math.min(answer, i);
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int root) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(root);
        visited[root] = true;
        int[] linkNum = new int[N + 1];

        while(!q.isEmpty()) {

            int now = q.poll();
            List<Integer> adjList = adjLists[now];

            for (int adjNode : adjList) {
                if (visited[adjNode])
                    continue;

                linkNum[adjNode] = linkNum[now] + 1;
                visited[adjNode] = true;
                q.add(adjNode);
            }
            
        }

        return Arrays.stream(linkNum).sum();
    }

}
