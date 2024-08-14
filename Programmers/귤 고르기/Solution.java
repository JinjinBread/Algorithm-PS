import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }

        List<Integer> list = map.values().stream().collect(Collectors.toList());
        list.sort(Comparator.reverseOrder());

        int tangerineNum = 0; // 현재 담은 귤의 개수
        int n = 0; // 귤 종류의 개수

        // 개수가 많은 종류부터 순서대로
        for (Integer num : list) {
            // 다 담았으면
            if (k <= tangerineNum) {
                break;
            }

            tangerineNum += num;
            n++;
        }

        return n;
    }
}