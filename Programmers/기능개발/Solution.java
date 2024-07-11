import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayDeque<Integer> answer = new ArrayDeque<>();
        
        int n = progresses.length;
        int[] deployDate = new int[n];
        
        for (int i = 0; i < n; i++) {
            //(100.0 - 작업 진도) / 작업 속도 [올림] <- 배포 예상일
            // 100이 아니라 100.으로 해야 한다.
            // 100으로 하면, 100 - progresses[i] 의 결과가 int로 나온다.
            // 이를 speeds[i], 즉 int로 나누면 결괏값이 int가 나와 소수점이 버려져 예상하지 않은 결과값이 나온다.
            deployDate[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        
        int max = deployDate[0];
        int deployNum = 0; // 배포할 기능의 개수
        
        for (int i = 0; i < n; i++) {
            
            //후 기능 개발이 완료되었음
            if (deployDate[i] <= max) {
                deployNum++;
            }
            //아직 후 기능 개발이 완료되지 않았음
            else {
                answer.add(deployNum);
                max = deployDate[i];
                deployNum = 1;
            }
        }
        
        answer.add(deployNum);
       
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}