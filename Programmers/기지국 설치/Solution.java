class Solution {
    public int solution(int n, int[] stations, int w) {

        int location = 1; // 현재 위치한 아파트 번호
        int stationsIdx = 0;
        int num = 0; // 설치해야 할 기지국의 수

        while (location <= n) {

            // 기지국 전파 범위 내에 도착한 경우
            if (stationsIdx < stations.length && location >= (stations[stationsIdx] - w)) {
                // 해당 전파 범위 내에서 벗어남
                location = stations[stationsIdx] + w + 1;
                stationsIdx++;
            }
            else {
                // 기지국 설치 후 다음 위치로 이동 (설치한 기지국 전파 범위를 벗어나는 다음 위치)
                location += 2 * w + 1;
                num++;
            }
        }

        return num;
    }
}