import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점(노드)의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int V = Integer.parseInt(st.nextToken()); // 탐색 시작 노드

        List<Integer>[] edges = new ArrayList[N];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            edges[startNode - 1].add(endNode - 1); //0번 ~ N-1번 (== 1번 ~ N번)
            edges[endNode - 1].add(startNode - 1); // 무방향 간선
        }
        
        List<Integer> dfsResult = new ArrayList<>();
        List<Integer> bfsResult = new ArrayList<>();
        boolean[] visited = new boolean[N];

        dfs(edges, V - 1, visited, dfsResult);
        Arrays.fill(visited, false); // visited 초기화
        bfs(edges, V - 1, visited, bfsResult);

        String dfsRes = dfsResult.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(" "));
        String bfsRes = bfsResult.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(" "));

        bw.write(dfsRes);
        bw.newLine();
        bw.write(bfsRes);
        bw.flush();
    }

    private static void dfs(List<Integer>[] edges, int startNode, boolean[] visited, List<Integer> dfsResult) {

        List<Integer> curEdges = edges[startNode];
        curEdges.sort(Comparator.naturalOrder()); // 오름차순으로 정렬 (작은 번호의 노드 먼저)

        dfsResult.add(startNode + 1);
        visited[startNode] = true;

        for (Integer edge : curEdges) {
            if (!visited[edge])
                dfs(edges, edge, visited, dfsResult);
        }
    }

    private static void bfs(List<Integer>[] edges, int startNode, boolean[] visited, List<Integer> bfsResult) {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            bfsResult.add(node + 1);
            
            List<Integer> curEdges = edges[node];
            curEdges.sort(Comparator.naturalOrder()); // 오름차순으로 정렬 (작은 번호의 노드 먼저)

            for (Integer edge : curEdges) {
                if (!visited[edge]) {
                    queue.add(edge);
                    visited[edge] = true; // 중복방문 방지
                    // 예를 들어, 1번 노드에서 시작해서 해당 노드와 연결된 2, 3, 4번 노드를 큐에 넣었다고 가정.
                    // 3, 4번 노드와 연결되어 있는 2번 노드가 다음으로 꺼내지고 큐에 연결된 노드, 즉 3, 4번 노드를 큐에 넣으면
                    // 큐에는 3, 4, 3, 4가 들어가게 됨 (중복 방문함)
                    // 따라서 큐에 add할 때 visited를 true로 만들어야 한다.
                }

            }

        }

            
    }
}
