import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<Integer>[] adjLists;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 연결 요소의 개수?
        // 몇 개의 그래프(네트워크)가 존재하는지?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjLists = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            adjLists[start].add(dest);
            adjLists[dest].add(start);
        }

        int ccNum = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                ccNum++;
            }
        }

        bw.write(String.valueOf(ccNum));
        bw.flush();
    }

    private static void dfs(int start) {
        
        visited[start] = true;
        List<Integer> edges = adjLists[start];

        for (Integer edge : edges) {
            if (!visited[edge])
                dfs(edge);
        }
    }
}
