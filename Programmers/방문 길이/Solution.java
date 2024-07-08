import java.util.*;

class Solution {
    public int solution(String dirs) {
        
        
        Map<Character, int[]> move = new HashMap<>();
        
        move.put('U', new int[] {0, 1});
        move.put('D', new int[] {0, -1});
        move.put('R', new int[] {1, 0});
        move.put('L', new int[] {-1, 0});
        
        Set<String> history = new HashSet<>();
        
        int x = 0;
        int y = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            
            int[] coordinate = move.get(dirs.charAt(i));
            
            int xm = x + coordinate[0];
            int ym = y + coordinate[1];
            
            if (!isValidBound(xm, ym))
                continue;
            
            //양방향 (걸어본 길에는 방향성이 없으므로 나중에 2로 나눠줘야 함)
            history.add("start: " + x + ", " + y
                        + " end: " + xm + ", " + ym);
            history.add("start: " + xm + ", " + ym
                        + " end: " + x + ", " + y);
            
            x = xm;
            y = ym;
        }
        
        return history.size() / 2;
    }
    
    private boolean isValidBound(int x, int y) {
        return x < 6 && x > -6 && y < 6 && y > -6;
        
    }
}