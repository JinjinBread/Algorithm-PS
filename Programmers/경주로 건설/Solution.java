import java.util.*;

class Solution {
    
    private static class Node {
        int r, c, direction, cost;
        public Node(int r, int c, int direction, int cost) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.cost = cost;
        }
    }
    
    // 상 좌 하 우
    private static int[] rw = { -1, 0, 1, 0 };
    private static int[] cw = { 0, -1, 0, 1 };
    private static int N;
    private static int[][][] visited;
    
    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
    
    private static boolean isBlocked(int[][] board, int r, int c) {
        return (r == 0 && c == 0) || !isValid(r, c) || board[r][c] == 1;
    }
    
    private static int calculateCost(int curDirection, int preDirection, int cost) {
        // 상 0 좌 1 하 2 우 3
        // 처음 시작 좌표(0, 0)이거나 같은 방향이면
        if (curDirection == -1 || (preDirection - curDirection) % 2 == 0) {
            return cost + 100;
        }
        return cost + 600;
    }
    
    private static boolean isShouldUpdate(int r, int c, int curDirection, int newCost) {
        // 방문하지 않았거나, 새 비용이 더 적은 경우 -> 갱신되어야 함
        return visited[r][c][curDirection] == 0 || visited[r][c][curDirection] > newCost;
    }
    
    public int solution(int[][] board) {
        
        // 출발지: 0, 0 도착지: N-1, N-1
        // 최소한의 코너, 최대한의 직선 도로
        // 코너란?
        // 1. 행으로(수직으로) 움직이고 있을 때 열이 변하는 것
        // 2. 열로(수평으로) 움직이고 있을 때 행이 변하는 것
        // 수직으로 움직이는지, 수평으로 움직이는지 어떻게 판단?
        // 이전 노드와 열이 같으면? -> 수직
        // 이전 노드와 행이 같으면? -> 수평
        
        // 칸마다 최소 비용?
        N = board.length;
        visited = new int[N][N][4];
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, -1, 0));
        
        int answer = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            Node now = q.poll();
            // 상 0 좌 1 하 2 우 3
            for (int i = 0; i < 4; i++) {
                
                int r = now.r + rw[i];
                int c = now.c + cw[i];
                
                if (isBlocked(board, r, c)) {
                    continue;
                }
                
                int newCost = calculateCost(now.direction, i, now.cost);
                
                if (r == (N - 1) && c == (N - 1)) {
                    answer = Math.min(answer, newCost);
                }
                else if (isShouldUpdate(r, c, i, newCost)) {
                    visited[r][c][i] = newCost;
                    q.add(new Node(r, c, i, newCost));
                }
            }
        }
        return answer;
    }
}