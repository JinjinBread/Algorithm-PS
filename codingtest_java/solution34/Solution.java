import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    private static List<Integer>[] adjLists;

    public static void main(String[] args) {
        int[] result = solution(new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}}, 1, 5);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(solution(new int[][] {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {5, 6}}, 1, 6)));
    }

    // 깊이 우선 탐색으로 모든 그래프의 노드를 순회하는 함수
    public static int[] solution(int[][] graph, int start, int n) {

        adjLists = new ArrayList[n]; // n + 1개의 공간을 만들어서 0번 index를 안 쓰는 방법도 있음
        
        for(int i = 0; i < n; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int[] node : graph) {
            adjLists[node[0] - 1].add(node[1] - 1);
        }

        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfs(start - 1, visited, answer);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void dfs(int start, boolean[] visited, List<Integer> answer) {

        visited[start] = true;
        answer.add(start + 1);

        // start노드와 인접해 있는 노드들
        List<Integer> adjNodes = adjLists[start];

        for (Integer adjNode : adjNodes) {
            if (!visited[adjNode]) {
                dfs(adjNode, visited, answer);
            }
        }

    }

}