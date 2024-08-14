import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        
        // 낮은 금액을 신청한 부서 먼저 지원
        Arrays.sort(d);
        
        int remainBudget = budget;
        
        for(int i = 0; i < d.length; i++) {
            
            // 남아있는 예산 보다 신청 금액이 더 많은 경우
            // 지원해줄 수 없으므로 종료
            if (d[i] > remainBudget) {
                break;
            }
            remainBudget -= d[i];
            answer++;
        }
        
        return answer;
    }
}