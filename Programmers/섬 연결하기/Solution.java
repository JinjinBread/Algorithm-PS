import java.util.*;

class Solution {
    
    public int solution(int n, int[][] costs) {
        
        // 모든 섬이 서로 통행 가능하도록 만들기 위한 필요 최소 비용을 return
        // costs[i][0], costs[i][1]을 연결, costs[i][2] 해당 다리의 건설비용

        // 비용이 저렴한 순으로 정렬
        Arrays.sort(costs, ((o1, o2) -> Integer.compare(o1[2], o2[2])));

        int[] disjoint_set = new int[n];
        for (int i = 0; i < n; i++) {
            disjoint_set[i] = i;
        }

        int cost = 0; // 다리의 총 비용
        int edges = 0; // 지어진 다리의 개수

        for(int[] edge: costs) {

            //노드가 4개면 3개의 다리(간선)만 필요함
            if (edges == n - 1) {
                break;
            }

            // 사이클 방지 (루트 노드가 같은 노드 끼리 연결하면 사이클이 됨)
            if (find(disjoint_set, edge[0]) != find(disjoint_set, edge[1])) {
                union(disjoint_set, edge[0], edge[1]);
                cost += edge[2];
                edges++;
            }

        }
        return cost;
    }

    private int find(int[] disjoint_set, int node) {

        if (disjoint_set[node] == node)
            return node;

        return disjoint_set[node] = find(disjoint_set, disjoint_set[node]); // 경로 압축
    }
    
    private void union(int[] disjoint_set, int node1, int node2) {
        int root1 = find(disjoint_set, node1);
        int root2 = find(disjoint_set, node2);
        // root1을 루트 노드로
        disjoint_set[root2] = root1;
    }

}