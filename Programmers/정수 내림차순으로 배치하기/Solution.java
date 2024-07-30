class Solution {
    public long solution(long n) {

        int[] num = new int[10];
        
        while(n > 0) {
            num[(int) (n % 10)]++;
            n /= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < num[i]; j++) {
                sb.append(i);
            }
        }

        return Long.parseLong(sb.toString());
    }
}