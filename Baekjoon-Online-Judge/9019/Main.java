import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        StringBuilder sb = new StringBuilder();
        int num;
        public Node(int num) { this.num = num; }
    }

    static final boolean[] visited = new boolean[10000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bw.write(getMinCmd(A, B));
            bw.newLine();
            Arrays.fill(visited, false);
        }
        bw.flush();
    }

    static String getMinCmd(int A, int B) {

        String minCmd;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(A));

        while (true) {

            Node now = q.poll();

            if (now.num == B) {
                minCmd = now.sb.toString();
                break;
            }

            // D
            int resultD = operationD(now.num);

            if (!visited[resultD]) {
                visited[resultD] = true;
                Node nodeD = new Node(resultD);
                nodeD.sb.append(now.sb).append("D");
                q.add(nodeD);
            }

            // S
            int resultS = operationS(now.num);

            if (!visited[resultS]) {
                visited[resultS] = true;
                Node nodeS = new Node(resultS);
                nodeS.sb.append(now.sb).append("S");
                q.add(nodeS);
            }

            // L
            int resultL = operationL(now.num);

            if (!visited[resultL]) {
                visited[resultL] = true;
                Node nodeL = new Node(resultL);
                nodeL.sb.append(now.sb).append("L");
                q.add(nodeL);
            }

            // R
            int resultR = operationR(now.num);

            if (!visited[resultR]) {
                visited[resultR] = true;
                Node nodeR = new Node(resultR);
                nodeR.sb.append(now.sb).append("R");
                q.add(nodeR);
            }
        }

        return minCmd;
    }

    static int operationD(int n) {
        return (2 * n) % 10000;
    }

    static int operationS(int n) {
        return n == 0 ? 9999 : n-1;
    }

    static int operationL(int n) {
        return (n % 1000) * 10 + (n / 1000);
    }

    static int operationR(int n) {
        return (n % 10) * 1000 + (n / 10);
    }
}
