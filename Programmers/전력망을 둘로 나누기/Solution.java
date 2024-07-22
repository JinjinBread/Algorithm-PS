import java.util.*;

class Solution {
    
    private static List<Integer>[] adjLists;
    private static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        
        // 두 전력망(트리)의 송전탑(노드)의 개수 차가 더 적은 걸로 변경
        
        adjLists = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            adjLists[i] = new ArrayList<>();
        }
        
        for (int[] wire: wires) {
            adjLists[wire[0]].add(wire[1]);
            adjLists[wire[1]].add(wire[0]);
        }
        
        
        visited = new boolean[n + 1];
        int answer = Integer.MAX_VALUE;
        
        for (int[] wire: wires) {
            
            // 전선 끊기
            adjLists[wire[0]].remove(Integer.valueOf(wire[1]));
            adjLists[wire[1]].remove(Integer.valueOf(wire[0]));
            
            answer = Math.min(answer, Math.abs(dfs(wire[0], 1) - dfs(wire[1], 1)));
            
            // 다시 복구
            adjLists[wire[0]].add(wire[1]);
            adjLists[wire[1]].add(wire[0]);
            Arrays.fill(visited, false);
        }
        
        return answer;
    }
    
    private static int dfs(int start, int nodeNum) {
        
        visited[start] = true;
        List<Integer> adjNodes = adjLists[start];
        
        for (Integer adjNode: adjNodes) {
            if (!visited[adjNode]) {
                nodeNum = 1 + dfs(adjNode, nodeNum);
            }
        }
        
        return nodeNum;
    }
}