import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(new int[][] { {10, 19}, {7, 10}, {6, 10} }, 15));
        System.out.println(solution(new int[][] { {10, 60}, {20, 100}, {30, 120} }, 50));
    }

    private static class Stock {
        int weight;
        int value;
        double valuePerWeight;
        public Stock(int weight, int value) { this.weight = weight; this.value = value; this.valuePerWeight = (double) value / weight; }
    }

    private static double solution(int[][] items, int weight_limit) {

        Stock[] itemList = new Stock[items.length];

        for (int i = 0; i < items.length; i++) {
            itemList[i] = new Stock(items[i][0], items[i][1]);
        }
        
        // kg 당 가치가 높은 순으로 정렬한다.
        Arrays.sort(itemList, (o1, o2) -> Double.compare(o2.valuePerWeight, o1.valuePerWeight));

        int remain_weight = weight_limit;
        double total_value = 0;
        
        for (Stock stock : itemList) {
            
            if (stock.weight <= remain_weight) {
                total_value += stock.value;
                remain_weight -= stock.weight;
            }
            // 남은 무게 보다 짐 무게가 더 큰 경우
            else {
                total_value += (remain_weight * stock.valuePerWeight);
                break;
            }
        }

        return total_value;
    }
}
