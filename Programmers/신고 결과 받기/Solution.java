import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        // 유저 ID, 해당 유저를 신고한 유저(동일한 유저에 대한 신고 횟수는 1회로 처리 -> 중복 안된다 -> Set 사용!)
        Map<String, Set<String>> map = new HashMap<>();
        // 유저 ID, 받을 메일 개수 (id_list 순서대로 초기화하는 과정에 입력 순서를 유지하기 위해 LinkedHashMap을 사용)
        Map<String, Integer> result = new LinkedHashMap<>();
        
        // 초기화
        for (String userId: id_list) {
            map.put(userId, new HashSet<>());
            result.put(userId, 0);
        }
        
        for (String str: report) {
            String[] s = str.split(" ");
            String declarer = s[0]; //신고자
            String respondent = s[1]; //피신고자
            
            map.get(respondent).add(declarer);
        }
        
        
        
        for (Map.Entry<String, Set<String>> entry: map.entrySet()) {
            
            // 신고를 k번 이상 받았다면
            if (entry.getValue().size() >= k) {
                // 나를 신고한 사람의 받을 메일 개수를 1개 추가함
                for(String declarer: entry.getValue()) {
                    result.put(declarer, result.get(declarer) + 1);
                }
            }
            
        }
        
        return result.values().stream().mapToInt(Integer::intValue).toArray();
    }
}