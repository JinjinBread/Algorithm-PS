import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static List<Integer>[] adjLists;
    static List<Integer> maxComputerNum = new ArrayList<>();
    static int maxNum = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjLists = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjLists[from].add(to);
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            bfs(i);
        }

        for (int maxComputer: maxComputerNum) {
            bw.write(maxComputer + " ");
        }
        bw.flush();
    }

    static void bfs(int start) {

        int canHackComputerNum = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {

            int now = q.poll();
            canHackComputerNum++;

            for (int adjNode: adjLists[now]) {
                if (visited[adjNode]) {
                    continue;
                }

                visited[adjNode] = true;
                q.add(adjNode);
            }
        }

        if (canHackComputerNum > maxNum) {
            maxNum = canHackComputerNum;
            maxComputerNum.clear();
            maxComputerNum.add(start);
        }
        else if (canHackComputerNum == maxNum) {
            maxComputerNum.add(start);
        }
    }
}