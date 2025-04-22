import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        Set<Character> set = new HashSet<>();
        int longestLen = 0;

        if (s.length() < 2) {
            return s.length();
        }

        for(int i = 0; i < s.length() - 1; i++) {
            int len = 1;
            char startCh = s.charAt(i);
            set.clear();
            set.add(startCh);

            if ((s.length() - i + 1) <= longestLen) {
                return longestLen;
            }

            for(int j = i + 1; j < s.length(); j++) {
                char ch = s.charAt(j);

                if (set.contains(ch)) {
                    break;
                }

                set.add(ch);
                len++;
            }
            
            longestLen = longestLen < len ? len : longestLen;
        }
        return longestLen;
    }
}