import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int dest;
        int cost;
        public Node(int dest, int cost) { this.dest = dest; this.cost = cost; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 정점 수
            int d = Integer.parseInt(st.nextToken()); // 간선 수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 정점 번호

            List<Node>[] adjRLists = new ArrayList[n+1];

            for (int j = 1; j <= n; j++) {
                adjRLists[j] = new ArrayList<>();
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                // to 가 감염되면 s 초 후 from 이 감염되기 때문에
                // to 에서 from 으로 가게끔 인접리스트를 역전해야 한다.
                adjRLists[to].add(new Node(from, weight));
            }

            int[] minCost = new int[n+1];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            minCost[c] = 0; // 시작 정점의 최소 비용은 0으로 초기화 해놓는다.

            dijkstra(adjRLists, c, minCost);
            
            // 방문된 노드 중 max 비용 == 마지막 컴퓨터가 감염된 시간(== 가장 오래 걸린 시간)
            // 방문된 노드 == 해킹된 컴퓨터의 수
            int virusNum = 0;
            int time = 0;
            for (int j = 1; j <= n; j++) {
                
                if (minCost[j] != Integer.MAX_VALUE) {
                    virusNum++;
                    time = Math.max(time, minCost[j]);
                }
            }

            bw.write(virusNum + " " + time);
            bw.newLine();
        }
        bw.flush();
    }

    private static void dijkstra(List<Node>[] adjRLists, int startNode, int[] minCost) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        pq.add(new Node(startNode, 0));

        while(!pq.isEmpty()) {
            
            Node now = pq.poll();

            // 현재 노드에 이미 방문한 경우 혹은 현재 더하려는 비용보다 이미 더 작은 비용이 들어가 있는 경우
            if (minCost[now.dest] < now.cost) {
                continue;
            }

            List<Node> adjNodes = adjRLists[now.dest];

            for (Node adjNode: adjNodes) {

                // 현재 노드까지의 비용 + adjNode로 가는 데 드는 비용
                int cost = minCost[now.dest] + adjNode.cost;
                
                // 더 작은 값으로 갱신
                if (minCost[adjNode.dest] > cost) {
                    minCost[adjNode.dest] = cost;
                    pq.add(new Node(adjNode.dest, cost));
                }
            }
        }

    }
}
