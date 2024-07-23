import java.util.ArrayList;
import java.util.List;

public class Solution {
    
    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(2));
        System.out.println(solution(7));
    }


    private static List<List<Integer>> result;
    private static int n;
    
    private static void backtrack(int sum, List<Integer> selectedNums, int start) {
        // 합이 10이 되면 해당 조합을 저장 후 백트래킹
         if (sum == 10) {
            result.add(selectedNums);
            return;
         }
         
         for (int i = start; i <= n; i++) {
            if (sum + i <= 10) {
                List<Integer> list = new ArrayList<>(selectedNums);
                list.add(i);
                backtrack(sum + i, list, i + 1);
            }
         }

    }

    private static List<List<Integer>> solution(int N) {
        
        n = N;
        result = new ArrayList<>();

        backtrack(0, new ArrayList<>(), 1);
        return result;
    }
}
