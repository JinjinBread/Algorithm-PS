import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] adjLists = new ArrayList[N+1];
        int[] parent = new int[N+1];
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adjLists[node1].add(node2);
            adjLists[node2].add(node1);
        }

        Queue<Integer> q = new ArrayDeque<>();
        // 루트노드
        q.add(1);

        while (!q.isEmpty()) {

            int node = q.poll();
            visited[node] = true;
            
            List<Integer> adjList = adjLists[node];

            for (Integer adjNode : adjList) {
                if (!visited[adjNode]) {
                    parent[adjNode] = node;
                    q.add(adjNode);
                }
            }


        }

        for (int i = 2; i <= N; i++) {
            bw.write(String.valueOf(parent[i]));
            bw.newLine();
        }

        bw.flush();
    }
}
