import java.util.*;

class Solution {
    public boolean isPalindrome(int x) {
        
        // 팰린드롬: 왼쪽에서 오른쪽으로 읽는 것과 오른쪽에서 왼쪽으로 읽는 것이 같은 수
        StringBuilder xSb = new StringBuilder();
        StringBuilder xRevSb = new StringBuilder();
        xSb.append(x);
        xRevSb.append(x);
        xRevSb.reverse(); // StringBuilder 의 reverse 는 자신(xRevSb)의 값을 변경한다.

        if (xSb.toString().equals(xRevSb.toString())) { // == 는 객체 주소 비교, equals 는 문자열 값 비교
            return true;
        }

        return false;
    }
}