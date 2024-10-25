import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    static long[] minCost;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 수(노드 수)
        int M = Integer.parseInt(st.nextToken()); // 노선 수(간선 수)
        int start = 1; // 1번 도시에서 출발

        adjLists = new ArrayList[N+1];
        minCost = new long[N+1];

        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[start] = 0;

        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작도시
            int B = Integer.parseInt(st.nextToken()); // 도착도시
            int C = Integer.parseInt(st.nextToken()); // 걸리는 시간
            adjLists[A].add(new Node(B, C));
        }

        bellmanFord();
    }

    static void bellmanFord() {

        // (N-1) 만큼 반복
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (minCost[j] == Long.MAX_VALUE) {
                    continue;
                }

                List<Node> adjNodes = adjLists[j];
                
                for (Node node : adjNodes) {
                    minCost[node.dest] = Math.min(minCost[node.dest], minCost[j] + node.cost); // dest 까지 가는 비용을 최소 비용으로 갱신
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            List<Node> adjNodes = adjLists[i];
            if (minCost[i] == Long.MAX_VALUE) {
                continue;
            }
            for (Node node : adjNodes) {
                if (minCost[node.dest] > minCost[i] + node.cost) { // 음수 사이클 존재
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (minCost[i] == Long.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(minCost[i]);
            }
        }
    }
}
