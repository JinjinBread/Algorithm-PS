import java.util.*;

class Solution {

    
    public int[] solution(String s) {

        s = s.substring(0, s.length() - 2).replace("{", ""); // {{a,b,c},{a},{a,b -> a,b,c},a},a,b

        String[] str = s.split("},"); // 0: a,b,c   1: a   2: a,b
        int[] answer = new int[str.length];

        // 방법1. 길이를 기준으로 오름차순 정렬하고 이전에 나온 수와 중복되는 수가 있다면, 해당 수(들은)는 제거하고 중복되지 않은 수를 answer에 넣는다.
        // Set<String> set = new HashSet<>();
        // Arrays.sort(str, (o1, o2) -> Integer.compare(o1.length(), o2.length())); // 0: a   1: a,b   2: a,b,c
        // for (int i = 0; i < str.length; i++) {
        //     String[] num = str[i].split(",");
        //     for (String n: num) {
        //         if (!set.contains(n)) {
        //             answer[i] = Integer.parseInt(n);
        //             set.add(n);
        //         }
        //     }
        // }

        // 방법2.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            String[] num = str[i].split(",");
            for (int j = 0; j < num.length; j++) {
                int n = Integer.parseInt(num[j]);
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
        }

        // 튜플은 집합에서 가장 빈번히 나온 숫자 순으로 구성된다.
        // 많이 나온 순으로 정렬한 후(내림차순 정렬) key를 리턴한다.
        answer = map.entrySet().stream()
        .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
        .mapToInt(o -> o.getKey()).toArray();

        return answer;
    }
}