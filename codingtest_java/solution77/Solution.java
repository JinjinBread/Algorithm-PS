import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 거스름돈 주기
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(123)));
        System.out.println(Arrays.toString(solution(350)));
    }

    private static int[] solution(int amount) {
        // 거스름돈 amount가 있을 때, 화폐 단위 [1, 10, 50, 100]을 최소한으로 사용한 화폐 리스트를 반환하라.

        int[] cash = { 100, 50, 10, 1 };
        List<Integer> change = new ArrayList<>();
        
        for (int coin : cash) {
            while (amount >= coin) {
                amount -= coin;
                change.add(coin);
            }
        }

        return change.stream().mapToInt(Integer::intValue).toArray();
    }

}
