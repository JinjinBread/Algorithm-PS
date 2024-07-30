import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++) {
            int[] cmd = commands[i];
            
            int start = cmd[0];
            int end = cmd[1];
            int k = cmd[2];
            
            int[] temp = Arrays.copyOfRange(array, start - 1, end);
            
            Arrays.sort(temp);
            answer[i] = temp[k-1];
        }
        
        return answer;
    }
}