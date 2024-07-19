import java.util.*;

class Solution {

    private static class Node {
        int r;
        int c;
        public Node(int r, int c) { this.r = r; this.c = c; }
    }
    
    private static String[] mapsArr;
    private static int N, M;
    private static int[] rw = { 0, 0, 1, -1 };
    private static int[] cw = { 1, -1, 0, 0 };
    
    public int solution(String[] maps) {
        
        Node start = new Node(-1, -1);
        Node lever = new Node(-1, -1);
        Node dest = new Node(-1, -1);

        mapsArr = maps;
        N = maps.length;
        M = maps[0].length();

        for (int i = 0; i < N; i++) {
            String map = maps[i];
            if (map.contains("S")) {
                start.r = i;
                start.c = map.indexOf("S");
            }
            if (map.contains("L")) {
                lever.r = i;
                lever.c = map.indexOf("L");
            }
            if (map.contains("E")) {
                dest.r = i;
                dest.c = map.indexOf("E");
            }
        }
        
        int startToLever = bfs(start, lever);
        int leverToDest = bfs(lever, dest);
        
        if (startToLever == -1 || leverToDest == -1)
            return -1;

        return startToLever + leverToDest;
    }

    private static int bfs(Node start, Node dest) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);

        int[][] minPath = new int[N][M];
        minPath[start.r][start.c] = 1;

        while(!queue.isEmpty()) {

            Node now = queue.poll();

            //동서남북
            for (int i = 0; i < 4; i++) {
                
                int r = now.r + rw[i];
                int c = now.c + cw[i];

                //범위를 벗어나면
                if (r < 0 || r >= N || c < 0 || c >= M)
                    continue;
                
                //벽으로 막혀있으면
                if (mapsArr[r].charAt(c) == 'X')
                    continue;

                //방문했으면
                if (minPath[r][c] != 0)
                    continue;

                queue.add(new Node(r, c));
                minPath[r][c] = minPath[now.r][now.c] + 1;
                
                if (r == dest.r && c == dest.c)
                    return minPath[r][c] - 1;
            }
        }

        return -1; // 도착지까지 도달하지 못했으면 -1을 반환
    }
}