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

    static int V;
    static int[] nonAdjNode;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[V+1];
            nonAdjNode = new int[V+1];

            for (int j = 1; j <= V; j++) {
                adjList[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                adjList[v1].add(v2);
                adjList[v2].add(v1);
            }

            if (isBipartiteGraph()) {
                bw.write("YES");
            }
            else {
                bw.write("NO");
            }
            bw.newLine();
        }

        bw.flush();
    }

    static boolean isBipartiteGraph() {

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= V; i++) {

            if (nonAdjNode[i] == 0) { // 방문하지 않았다면
                nonAdjNode[i] = 1;
                q.add(i);


                while (!q.isEmpty()) {

                    int now = q.poll();
                    int flag = nonAdjNode[now] == 1 ? 2 : 1;
                    List<Integer> adjNodes = adjList[now];

                    for(int adjNode: adjNodes) {
                        if (nonAdjNode[adjNode] == 0) {
                            nonAdjNode[adjNode] = flag;
                            q.add(adjNode);
                        }
                        else if (nonAdjNode[adjNode] == nonAdjNode[now]) { // 현재 노드와 인접 노드가 같은 그룹(집합)이면
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
