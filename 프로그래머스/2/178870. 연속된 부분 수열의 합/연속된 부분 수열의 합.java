class Solution {
    public int[] solution(int[] sequence, int k) {
        
        // 비내림차순 -> 오름차순인데, 같은 수가 있는 경우
        // 합이 k 인 부분 수열을 선택
        // 1. 합이 k인 부분 수열이 여러 개인 경우
        // 1-1. 길이가 짧은 수열을 선택
        // 1-2. 앞쪽(시작 인덱스가 작은)에 나오는 수열을 선택
        // output: 부부 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return
        
        int lastIndex = sequence.length - 1;
        
        int[] answer = new int[2];
        int answerLength = Integer.MAX_VALUE;
        int p1 = 0;
        int p2 = 0;
        int sum = sequence[0];
        while (p1 != lastIndex && p2 != lastIndex) {
            
            if (sum == k) {
                int len = (p2 - p1) + 1;
                if (answerLength > len) {
                    answer[0] = p1;
                    answer[1] = p2;
                    answerLength = len;
                }
            }
            
            if (sum >= k) {
                sum -= sequence[p1];
                p1++;
            }
            else {
                p2++;
                sum += sequence[p2];
            }
        }
        
        for(int i = p1; i <= lastIndex; i++) {
            
            if (sum == k) {
                int len = (p2 - i) + 1;
                if (answerLength > len) {
                    answer[0] = i;
                    answer[1] = p2;
                    answerLength = len;
                }
            }
            
            sum -= sequence[i];
        }
        
        return answer;
    }
}