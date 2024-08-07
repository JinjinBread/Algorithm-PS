import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    private static int[][] map;
    private static int[][] visited;
    private static int[] rw = { -1, 1, 0, 0 };
    private static int[] cw = { 0, 0, -1, 1 };
    private static int N;
    private static int M;

    private static class Node {
        int r;
        int c;
        public Node(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(ch[j]);
            }
        }

        visited = new int[N][M];

        bfs(0,0);

        bw.write(String.valueOf(visited[N-1][M-1]));
        bw.flush();
    }

    private static void bfs(int startN, int startM) {

        Queue<Node> pq = new ArrayDeque<>();

        pq.add(new Node(startN, startM));

        while (!pq.isEmpty()) {
            
            Node now = pq.poll();
            visited[now.r][now.c] += map[now.r][now.c]; // 현재 문제에서 map[now.r][now.c]는 모두 1
            
            for (int j = 0; j < 4; j++) {

                int r = now.r + rw[j];
                int c = now.c + cw[j];

                // 범위를 벗어남
                if (r >= N || c >= M || r < 0 || c < 0) {
                    continue;
                }

                // 벽으로 막혀있음
                if (map[r][c] == 0) {
                    continue;
                }

                // 이미 방문함
                if (visited[r][c] != 0) {
                    continue;
                }

                pq.add(new Node(r, c));
                visited[r][c] = visited[now.r][now.c]; // 현재의 가중치 값으로 방문할 곳에 초기화함
            }
        }
    }
    
}
