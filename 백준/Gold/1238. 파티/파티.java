import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int N;
    static int X;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수, 마을 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        X = Integer.parseInt(st.nextToken()); // 파티가 열리는 마을


        adjLists = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjLists[start].add(new Node(dest, cost));
        }

        int[] fromPartyToHome = new int[N+1];
        fromPartyToHome = dijkstra(X);

        int longTime = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            int[] fromHomeToParty = dijkstra(i);
            longTime = Math.max(longTime, fromPartyToHome[i] + fromHomeToParty[X]);
        }

        System.out.println(longTime);
    }

    static int[] dijkstra(int start) {

        int[] min = new int[N+1];

        Arrays.fill(min, Integer.MAX_VALUE);
        min[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            // 이전에 계산된 최소 거리보다 더 큰 거리면 무시
            if (min[now.dest] < now.cost) {
                continue;
            }

            for (Node adjNode: adjLists[now.dest]) {

                if (min[adjNode.dest] > now.cost + adjNode.cost) {
                    min[adjNode.dest] = now.cost + adjNode.cost;
                    pq.add(new Node(adjNode.dest, min[adjNode.dest]));
                }
            }
        }

        return min;
    }

}
