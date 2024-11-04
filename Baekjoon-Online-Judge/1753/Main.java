import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int dest;
        int cost;
        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static List<Node>[] adjLists;
    static int[] minPath;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        adjLists = new ArrayList[V+1];
        minPath = new int[V+1];
        Arrays.fill(minPath, Integer.MAX_VALUE);

        for (int i = 1; i <= V; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjLists[u].add(new Node(v, w));
        }

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (minPath[i] == Integer.MAX_VALUE) {
                bw.write("INF");
            }
            else {
                bw.write(String.valueOf(minPath[i]));
            }
            bw.newLine();
        }
        bw.flush();
    }

    static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));
        minPath[start] = 0; // 초기 시작 노드를 0으로 설정

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            // 현재 처리 중인 노드의 비용이 기록된 최소 비용보다 큰 경우 (탐색 필요 x)
            if (minPath[now.dest] < now.cost) {
                continue;
            }

            List<Node> adjNodes = adjLists[now.dest];
            
            for (Node node: adjNodes) {
                // 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수 있으나,
                // 우선순위 큐에 들어가면 비용이 적은 순으로 정렬이 되기 때문에 따로 처리할 필요 없다.
                if (minPath[node.dest] < now.cost + node.cost) {
                    continue; 
                }

                minPath[node.dest] = now.cost + node.cost;
                pq.add(new Node(node.dest, minPath[node.dest]));
            }
        }

    }

}
