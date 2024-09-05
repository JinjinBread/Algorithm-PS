import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer>[] adjLists;
    private static Set<Integer> totalVisited;
    private static boolean[] visited;
    private static int nodeNum;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 노드의 개수

        adjLists = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) {
            adjLists[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            adjLists[from].add(to); // 단방향
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 집
        int T = Integer.parseInt(st.nextToken()); // 회사

        totalVisited = new HashSet<>();
        visited = new boolean[n+1];

        bfs(S, T); // 출근길
        Arrays.fill(visited, false);
        bfs(T, S); // 퇴근길

        bw.write(String.valueOf(nodeNum));
        bw.flush();
    }

    private static void bfs(int startNode, int destNode) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(startNode);

        while (!q.isEmpty()) {

            int now = q.poll();
            visited[now] = true;

            List<Integer> adjNodes = adjLists[now];
            for(int adjNode: adjNodes) {
                
                if (visited[adjNode] || (adjNode == destNode))
                    continue;

                // 출근길에 방문한 노드와 겹치면
                if (totalVisited.contains(adjNode)) {
                    nodeNum++;
                }

                totalVisited.add(adjNode);
                q.add(adjNode);
                visited[adjNode] = true;
            }
        }
    }
}