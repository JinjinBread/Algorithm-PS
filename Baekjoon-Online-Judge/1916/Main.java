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
    
    private static class Node {
        int dest;
        int cost;
        public Node(int dest, int cost) { this.dest = dest; this.cost = cost; }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 버스의 개수

        List<Node>[] adjLists = new ArrayList[N+1];
        
        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjLists[start].add(new Node(dest, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        int[] minCost = new int[N+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        minCost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost)); // 더 낮은 비용이 우선
        pq.add(new Node(start, 0)); // 시작 지점의 도착지는 시작 지점으로, 비용은 0으로 설정한다.

        while(!pq.isEmpty()) {

            Node now = pq.poll();

            // 이미 더 짧은 비용을 알고 있는 경우 탐색을 하지 않는다.
            if (minCost[now.dest] < now.cost) {
                continue;
            }

            List<Node> adjNodes = adjLists[now.dest];

            for (Node adjNode : adjNodes) {
                
                // 현재 인접 노드(adjNode)까지의 최소 비용을 저장하는 배열 minCost의 값보다 더 적은 비용이면, 값을 변경한다.
                if (minCost[adjNode.dest] > minCost[now.dest] + adjNode.cost) {
                    minCost[adjNode.dest] = minCost[now.dest] + adjNode.cost;
                    pq.add(adjNode);
                }
            }
        }

        bw.write(String.valueOf(minCost[dest]));
        bw.flush();
    }

}
