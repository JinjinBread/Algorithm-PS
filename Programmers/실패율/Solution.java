import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        
        //실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
        //입출력 1번 예
        // 1스테이지의 실패율 (1/8)
        // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 (즉 1 스테이지의 수)
        // 스테이지에 도달한 플레이어 수(즉 1보다 크거나 같은 스테이지 수)
        // 2스테이지 - 3/7, 3스테이지 - 1/2, 4스테이지 - 1/2, 5스테이지 - 0
        // 3 - 4 - 2 - 1 - 5
        List<Double> result = new ArrayList<>();
        
        Arrays.sort(stages);
        
        for (int i = 0; i < N; i++) {
            double challenger = 0.0;
            double passer = 0.0;
            for (int j = 0; j < stages.length; j++) {
                int stage = stages[j];
                
                //스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수
                if (stage == (i + 1))
                    challenger++;
                
                //스테이지에 도달한 플레이어의 수
                if (stage >= (i + 1))
                    passer++;
            }
            
            //스테이지에 도달한 유저가 없는 경우
            if (passer == 0) {
                result.add(0.0);
                continue;
            }
            
            result.add(challenger/passer);
        }
        
        int[] answer = new int[N];
        
        for(int i = 0; i < answer.length; i++) {
            
            double max = Collections.max(result);
            
            for(int j = 0; j < result.size(); j++) {
                if (result.get(j) == max) {
                    answer[i] = j + 1;
                    result.set(j, -1.0);
                    break;
                }
            }
        }
        
        return answer;
    }
}