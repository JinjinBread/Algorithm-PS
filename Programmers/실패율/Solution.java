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

        int[] challenger = new int[N + 2]; //1 ~ N+1 스테이지 별 도전자를 구함

        for (int i = 0; i < stages.length; i++) {
            challenger[stages[i]]++;
        }

        double passer = stages.length; // 스테이지 도달한 플레이어 수

        Map<Integer, Double> failure = new HashMap<>();

        for (int i = 1; i <= N; i++) {

            //스테이지에 도달한 유저가 없는 경우
            if (challenger[i] == 0) {
                failure.put(i, 0.);
                continue;
            }

            failure.put(i, challenger[i] / passer);
            passer -= challenger[i];
        }
        
        //내림차순 정렬
        return failure.entrySet().stream()
        .sorted((m1, m2) -> Double.compare(m2.getValue(), m1.getValue()))
        .mapToInt(HashMap.Entry::getKey)
        .toArray();
    }
}