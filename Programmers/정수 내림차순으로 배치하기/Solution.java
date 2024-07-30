import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public long solution(long n) {

        String[] digit = String.valueOf(n).split("");

        Arrays.sort(digit, Comparator.reverseOrder());
        
        StringBuilder sb = new StringBuilder();
        for (String strDigit : digit) {
            sb.append(strDigit);
        }

        return Long.parseLong(sb.toString());
    }
}