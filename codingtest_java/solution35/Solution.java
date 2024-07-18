import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Solution {

    private static List<Integer>[] adjLists;
    private static boolean[] visited;
    private static List<Integer> answer;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
            solution(new int[][] { {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4, 8}, {5, 8}, {6, 9}, {7, 9}}, 
            1, 9)));
        
        System.out.println(Arrays.toString(solution(new int[][] { {1, 3}, {3, 4}, {3, 5}, {5, 2}}, 1, 5)));
    }

    // 너비 우선 탐색으로 모든 그래프의 노드를 순회하는 함수
    private static int[] solution(int[][] graph, int start, int n) {

        adjLists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjLists[edge[0]].add(edge[1]);
        }

        visited = new boolean[n + 1];
        answer = new ArrayList<>();
        
        bfs(start);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void bfs(int start) {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {

            int curNode = queue.poll();
            answer.add(curNode);

            for(int adjNode: adjLists[curNode]) {
                if (!visited[adjNode]) {
                    queue.add(adjNode);
                    visited[adjNode] = true;
                }
            }
        }

    }
}