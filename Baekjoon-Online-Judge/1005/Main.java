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

    static int N, K;
    static int[] buildTime;
    static List<Integer>[] adjList;
    static int[] inEdge;
    static int[] minTime;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            buildTime = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                buildTime[j] = Integer.parseInt(st.nextToken());
            }

            adjList = new ArrayList[N+1];
            inEdge = new int[N+1]; // 노드에 들어오는 간선의 수

            for (int j = 1; j <= N; j++) {
                adjList[j] = new ArrayList<>();
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                adjList[X].add(Y);
                inEdge[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            minTime = new int[N+1];
            bfs();

            bw.write(String.valueOf(minTime[W]));
            bw.newLine();
        }

        bw.flush();
    }

    static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inEdge[i] == 0) {
                q.add(i);
                minTime[i] = buildTime[i];
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            List<Integer> adjNodes = adjList[node];

            for (int adjNode: adjNodes) {
                minTime[adjNode] = Math.max(minTime[adjNode], minTime[node] + buildTime[adjNode]);
                inEdge[adjNode]--;
                if (inEdge[adjNode] == 0) {
                    q.add(adjNode);
                }
            }
        }
    }

}
