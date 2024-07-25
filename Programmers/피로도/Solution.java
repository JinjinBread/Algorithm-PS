import java.util.*;

class Solution {
    
    private static int[][] Dungeons;
    private static boolean[] isVisited;
    private static int answer;
    
    public int solution(int k, int[][] dungeons) {
        
        Dungeons = dungeons;
        isVisited = new boolean[dungeons.length];
        answer = 0;
        
        backtrack(k, 0);
        
        return answer;
    }
    
    // 피로도, 현재 던전 번호, 탐험한 던전 수
    private static void backtrack(int k, int cnt) {
        
        for (int i = 0; i < Dungeons.length; i++) {
            // i 번째 던전을 탐험한 적 없고, 현재 피로도가 던전 탐험의 최소 필요 피로도 보다 크거나 같으면
            if (!isVisited[i] && k >= Dungeons[i][0]) {
                isVisited[i] = true;
                backtrack(k - Dungeons[i][1], cnt+1);
                answer = Math.max(answer, cnt+1); // +1을 해야 현재 던전까지 합하여 탐험한 던전 수를 제대로 셀 수 있다.
                isVisited[i] = false; // i 번째 던전 탐험을 취소(false를 하지 않으면 백트래킹으로 되돌아갔음에도 이미 탐험했다고 생각함)
            }
        }
    }
}