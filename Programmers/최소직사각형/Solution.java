import java.util.*;

class Solution {
    public int solution(int[][] sizes) {

        int l1 = Math.max(sizes[0][0], sizes[0][1]);
        int l2 = l1 == sizes[0][0] ? sizes[0][1] : sizes[0][0];
        for (int i=1; i<sizes.length; i++) {
            int greater = Math.max(sizes[i][0], sizes[i][1]);
            int less = Math.min(sizes[i][0], sizes[i][1]);
            l1 = Math.max(l1, greater);
            l2 = Math.max(l2, less);
        }
        
        
        return l1 * l2;
    }
}