import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] adjLists;
    static boolean[] visited;
    static int[] edgeNum; // n 번 노드로 '들어오는' 간선의 수를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjLists = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        edgeNum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjLists[A].add(B);
            edgeNum[B]++;
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (edgeNum[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {

            int now = q.poll();
            bw.write(now + " ");

            List<Integer> adjNodes = adjLists[now];

            for (int adjNode : adjNodes) {
                edgeNum[adjNode]--; // 현재 노드(now)의 간선을 제거함
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && edgeNum[i] == 0) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

        bw.flush();
    }    
}
