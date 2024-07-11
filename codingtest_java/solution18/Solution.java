package codingtest_java.solution18;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    
    public static void main(String[] args) {
        
    }

    private static boolean solution(int[] arr, int target) {

        Set<Integer> set = new HashSet<>();

        for (int i : arr) {

            // (ex. target: 10, hashSet = {4, 2, 6, 9}
            //  -> i가 6 일 때 target - i는 4로, 해시셋에 있으므로 true를 리턴한다)
            if (set.contains(target - i)) {
                return true;
            }

            set.add(i);
        }

        return false;
    }

}
