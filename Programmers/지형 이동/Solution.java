import java.util.PriorityQueue;

class Solution {

    private static int[] rw = {-1, 1, 0, 0};
    private static int[] cw = {0, 0, -1, 1};

    private static class Node {
        int r;
        int c;
        int cost;
        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public int solution(int[][] land, int height) {
        
        int answer = 0;

        int n = land.length;
        boolean[][] visited = new boolean[n][n];
        // cost가 낮은 순으로 
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        pq.add(new Node(0, 0, 0));

        //bfs
        while(!pq.isEmpty()) {
            
            Node now = pq.poll();

            // 이미 방문한 칸은 무시
            if (visited[now.r][now.c]) {
                continue;
            }

            visited[now.r][now.c] = true;
            answer += now.cost;

            for (int i = 0; i < 4; i++) {
                
                int newR = now.r + rw[i];
                int newC = now.c + cw[i];

                // 범위를 벗어나면
                if (newR >= n || newC >= n || newR < 0 || newC < 0) {
                    continue;
                }

                int newCost = Math.abs(land[now.r][now.c] - land[newR][newC]);
                int cost = newCost > height ? newCost : 0;

                if (!visited[newR][newC]) {
                    pq.add(new Node(newR, newC, cost));
                }
            }
        }
        
        return answer;
    }
}