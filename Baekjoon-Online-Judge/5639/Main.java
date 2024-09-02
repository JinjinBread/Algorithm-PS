import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static class Node {
        int num;
        Node left;
        Node right;
        public Node(int num) { this.num = num; }
    }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {

            String str = br.readLine();

            if (str == null || str.isEmpty()) {
                break;
            }

            int nodeNum = Integer.parseInt(str);

            Node parent = root;
            Node now = root;

            while (now != null) {

                parent = now;

                if (now.num < nodeNum) {
                    now = now.right;
                }
                else {
                    now = now.left;
                }
            }

            if (parent.num > nodeNum) {
                parent.left = new Node(nodeNum);
            }
            else {
                parent.right = new Node(nodeNum);
            }
        }

        StringBuilder sb = postorder(root);
        bw.write(sb.toString());
        bw.flush();
    }

    // 좌, 우, 루트
    private static StringBuilder postorder(Node root) {

        StringBuilder sb = new StringBuilder();

        if (root == null)
            return sb;

        sb.append(postorder(root.left));
        sb.append(postorder(root.right));
        sb.append(root.num + "\n");

        return sb;
    }
}
