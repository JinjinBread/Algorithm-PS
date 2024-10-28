import java.util.*;

class Solution {
    
    public int solution(int[] citations) {
        int answer = 0;
        
        // 오름차순 정렬
        Arrays.sort(citations);
        
        answer = binarySearch(citations);
        
        return answer;
    }
    
    static int binarySearch(int[] citations) {
        
        int low = 0;
        int high = citations[citations.length - 1];
        
        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (isGreaterThanOrEqualTo(citations, mid)) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return low - 1;
    }
    
    static boolean isGreaterThanOrEqualTo(int[] citations, int h) {
        // h번 이상 인용된 논문이 h편 이상인 경우 true, 아니면 false
        return (int) Arrays.stream(citations).filter(c -> c >= h).count() >= h ?
            true : false;
    }
}