package codingtest_java.solution25;

import java.util.LinkedList;

public class Solution {


    public static void main(String[] args) {
        System.out.println(preorder(new int[]{1,2,3,4,5,6,7}, 0));;
    }

    public static String[] solution(int[] nodes) {

        // 마지막 공백을 지우기 위해 trim 사용
        return new String[] {
            preorder(nodes, 0).trim(),
            inorder(nodes, 0).trim(),
            postorder(nodes, 0).trim()
        };
    }

    // 부모 -> 좌 -> 우
    public static String preorder(int[] nodes, int idx) {

        if (idx >= nodes.length) {
            return "";
        }

        return nodes[idx] + " "
            + preorder(nodes, 2 * idx + 1) + " "
            + preorder(nodes, 2 * idx + 2) + " ";
    }

    // 좌 -> 부모 -> 우
    public static String inorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return preorder(nodes, 2 * idx + 1) + " "
            + nodes[idx] + " "
            + preorder(nodes, 2 * idx + 2) + " ";
    }

    // 좌 -> 우 -> 부모
    public static String postorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return preorder(nodes, 2 * idx + 1) + " "
            + preorder(nodes, 2 * idx + 2) + " "
            + nodes[idx] + " ";
    }
}
