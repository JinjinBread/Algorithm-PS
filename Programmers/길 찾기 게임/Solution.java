import java.util.*;

class Solution {
    
    private static class Node {
        
        int number;
        int x, y;
        Node left;
        Node right;
        
        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

    }
    
    public int[][] solution(int[][] nodeinfo) {
        
        // 같은 레벨의 노드 == 같은 y 좌표
        // 레벨이 올라갈 수록 (아래로 갈 수록) y 값은 작아진다. (루트노드가 제일 큰 y를 가짐)
        // x값에 대해 이진 탐색 트리로 구성돼있음
        
        // 노드 번호와 좌표가 들어있는 노드들을 저장하는 리스트
        Node[] nodes = new Node[nodeinfo.length];

        // 같은 레벨에 있는 노드

        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        // y를 기준으로 내림차순 정렬을 하고, 만약 y가 같다면 x를 기준으로 오름차순 정렬을 한다.
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y)
                return Integer.compare(o1.x, o2.x);
            return Integer.compare(o2.y, o1.y);
        });

        // 시작은 무조건 루트 노드
        Node root = nodes[0];

        // 노드의 자식을 설정
        for (int i = 1; i < nodes.length; i++) {

            Node parent = root;

            while(true) {
                // 부모 노드보다 x가 크면 오른쪽으로
                if (nodes[i].x > parent.x) {
                    if (parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    }
                    else {
                        parent = parent.right;
                    }
                }
                // 부모 노드보다 x가 작으면 왼쪽으로
                else {
                    // 부모 노드의 왼쪽 자식이 없다면, 현재 노드를 부모의 왼쪽 자식으로 넣고 다음 노드로 넘어감
                    if (parent.left == null) {
                        parent.left = nodes[i];
                        break;
                    }
                    // 부모 노드의 왼쪽 자식이 있다면, 그 노드로 내려감
                    else {
                        parent = parent.left;
                    }
                }
            }

        }

        List<Integer> preorderResult = new ArrayList<>();
        List<Integer> postorderResult = new ArrayList<>();

        preorder(root, preorderResult);
        postorder(root, postorderResult);

        int[][] answer = new int[2][nodeinfo.length];

        answer[0] = preorderResult.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postorderResult.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }

    // 전위순회
    public void preorder(Node root, List<Integer> result) {

        if (root == null) {
            return;
        }

        result.add(root.number);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    public void postorder(Node root, List<Integer> result) {

        if (root == null) {
            return;
        }

        preorder(root.left, result);
        preorder(root.right, result);
        result.add(root.number);

    }
}