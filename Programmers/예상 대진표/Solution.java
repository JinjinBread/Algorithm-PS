class Solution {
    public int solution(int n, int a, int b) {
        
        int count = 0;
        
        // 번호에 + 1 을 한 후 / 2를 하면 다음 토너먼트 번호
        // ex) 4번 -> 2번
        // ex) 7번 -> 4번
        
        while (a != b) {
            count++;
            a = (a + 1) / 2;
            b = (b + 1) / 2;
        }
        
        
        return count;
    }
}