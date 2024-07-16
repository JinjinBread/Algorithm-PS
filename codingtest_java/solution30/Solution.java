
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new int[][] {{0, 0, 1}, {0, 1, 2}, {1, 1, 2}})));
        System.out.println(Arrays.toString(solution(4, new int[][] {{0, 0, 1}, {1, 1, 2}, {0, 1, 2}, {1, 0, 2}})));
    }


    private static Boolean[] solution(int k, int[][] operations) {

        // k는 노드의 개수 (0 <= k <= 1000)

        // operations[i][0]가 0이면 operations[i][1] 노드, operations[i][2] 노드에 대해 union연산 (루트 노드는 두 루트 노드 중 더 큰 노드로 선정)
        // operations[i][0]이 1이면 operations[i][1] 노드와 operations[i][2] 노드가 같은 집합에 속해 있으면 true, 아니면 false를 반환하는 equals 연산

        int[] disjoint_set = new int[k];
        // 초기 노드는 부모 노드(배열값)를 자신의 값으로 설정
        for (int i = 0; i < k; i++) {
            disjoint_set[i] = i;
        }

        // 결과값의 배열 개수는 equals 연산의 수 (== operations[i][0]이 1인 개수)
        List<Boolean> result = new ArrayList<>();
        for (int[] operation : operations) {
            // union 연산
            if (operation[0] == 0) {
                union(disjoint_set, operation[1], operation[2]);
            }
            // equals 연산
            else {
                // 두 노드의 루트 노드가 같으면 같은 집합에 속해 있다는 의미이므로 result에 true를 넣는다.
                if (find(disjoint_set, operation[1]) == find(disjoint_set, operation[2])) {
                    result.add(true);
                }
                else {
                    result.add(false);
                }
            }
        }
        
        return result.toArray(Boolean[]::new);
    }

    private static int find(int[] disjoint_set, int node) {

        int parent = disjoint_set[node];

        // 부모 노드가 자기 자신이라면 (== 루트 노드)
        if (parent == node) {
            return node;
        }

        // 아니면 자신의 부모 노드 확인(재귀적)
        return find(disjoint_set, parent);
    }

    private static void union(int[] disjoint_set, int x, int y) {

        // x의 루트 노드와 y의 루트 노드
        int xRootNode = find(disjoint_set, x);
        int yRootNode = find(disjoint_set, y);

        // xRootNode가 더 크면 xRootNode가 루트 노드가 됨
        if (xRootNode > yRootNode) {
            disjoint_set[yRootNode] = xRootNode;
            return;
        }

        disjoint_set[xRootNode] = yRootNode;
    }

}
