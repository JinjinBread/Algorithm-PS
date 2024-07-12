import java.util.*;

class Solution {
    
    // (키) 구성 메뉴 개수 (값) 메뉴 구성 별 주문 횟수
    private static Map<Integer, Map<String, Integer>> courseMap;
    
    public String[] solution(String[] orders, int[] course) {
        
        // 가장 많이 함께 주문한 단품메뉴들
        // 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
        // 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 후보에 포함
        // N개의 코스 메뉴 구성들 중 가장 많이 주문된 메뉴 구성만 채택
        // N개의 코스 메뉴 구성들 중 주문 횟수가 같으면 모두 채택
        
        courseMap = new HashMap<>();
        
        for (int i: course) {
            courseMap.put(i, new HashMap<>());
        }
        
        for (String s: orders) {
            char[] sCharArray = s.toCharArray();
            // ex) ABC CBD가 있을 때, BC와 CB를 다른 메뉴 조합으로 체크하지 않도록
            // CBD를 BCD로 바꾸는 과정
            Arrays.sort(sCharArray);
            combination(0, sCharArray, "");
        }
        
        List<String> result = new ArrayList<>();
        
        for (Map<String, Integer> menu: courseMap.values()) {
            
            // 코스 메뉴 개수에 따른 코스 메뉴 조합과 주문 횟수(menu)
            // 주문 횟수 중 가장 큰 값을 구하고 그 값이 2 이상이면서(메뉴 조합의 개수가 2개 이상이면서)
            // 그 값과 같은 값만 필터링을 해서 해당 조건에 맞는 key(메뉴 조합)만 result에 넣는다.
            menu.values().stream()
            .max(Comparator.comparingInt(o -> o)) //Comparator.naturalOrder()와 같음
            .ifPresent(max -> menu.entrySet().stream()
                                .filter(entry -> max.equals(entry.getValue()) && max > 1)
                                .forEach(entry -> result.add(entry.getKey())));
        }

        result.sort(Comparator.naturalOrder());
        return result.toArray(String[]::new);
    }
    
    public void combination(int idx, char[] order, String result) {
        
        if (courseMap.containsKey(result.length())) {
            Map<String, Integer> menu = courseMap.get(result.length());
            menu.put(result, menu.getOrDefault(result, 0) + 1);
        }
        
        for (int i = idx; i < order.length; i++) {
            combination(i + 1, order, result + order[i]);
        }
        
    }
}