import java.util.*;

class Solution {
    
    // 오른쪽, 아래쪽 | 왼쪽, 위쪽
    int[] rw = { 1, 0, -1, 0 };
    int[] cw = { 0, 1, 0, -1 };
    
    class Location {
        int r;
        int c;
        public Location(int r, int c) { this.r = r; this.c = c; }
    }
    
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map = new int[n+1][m+1];
        
        for(int[] puddle: puddles) {
            map[puddle[1]][puddle[0]] = 1;
        }

        int answer = getPath(map, m, n);
        return answer;
    }
    
    public int getPath(int[][] map, int m, int n) {
 
        int[][] pathNum = new int[n+1][m+1];
        pathNum[1][1] = 1;
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // 시작점 건너뛰기
                if (i==1 && j==1) continue;
                // 물웅덩이
                if (map[i][j] == 1) continue;
                pathNum[i][j] = (pathNum[i-1][j] + pathNum[i][j-1]) % 1_000_000_007;
            }
        }
        
        return pathNum[n][m];
    }
}