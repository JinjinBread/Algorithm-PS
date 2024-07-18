import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private static class Node {
        int dest; // 도착지 노드 번호
        int weight; // 가중치

        public Node(int dest, int weight) { this.dest = dest; this.weight = weight; }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
            solution(new int[][] {{0, 1, 9}, {0, 2, 3}, {1, 0, 5}, {2, 1, 1}}, 0, 3)));
        System.out.println(Arrays.toString(
            solution(new int[][] {{0, 1, 1}, {1, 2, 5}, {2, 3, 1}}, 0, 4)));
    }

    private static int[] solution(int[][] graph, int start, int n) {

        List<Node>[] adjLists = new ArrayList[n]; // 0번 노드부터 시작
        for (int i = 0; i < n; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjLists[edge[0]].add(new Node(edge[1], edge[2]));
        }

        int[] minCost = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(minCost, Integer.MAX_VALUE);
        
        // 우선순위가 낮은 순서대로 나오는 우선순위 큐
        // 가장 최소 비용을 선택하여 이동
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));
        // 시작 노드 초기화
        minCost[start] = 0;
        pq.add(new Node(start, 0));
        
        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            // 이미 방문했으면 건너 뛴다.
            if (visited[curNode.dest])
                continue;

            visited[curNode.dest] = true;

            for(Node adjNode: adjLists[curNode.dest]) {
                // '현재 노드의 최소 비용 + 현재 노드에서 인접 노드까지의 비용'이 인접 노드의 최소 비용보다 작으면
                // curNode.weight == minCost[curNode.dest]
                if(minCost[adjNode.dest] > curNode.weight + adjNode.weight) {
                    minCost[adjNode.dest] = curNode.weight + adjNode.weight; // 갱신
                    pq.add(new Node(adjNode.dest, minCost[adjNode.dest]));
                }
            }
        }

        return minCost;
    }
}
