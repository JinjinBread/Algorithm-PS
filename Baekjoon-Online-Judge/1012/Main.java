import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        
        while (T != 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 배추밭의 가로 길이 (열)
            int N = Integer.parseInt(st.nextToken()); // 배추밭의 세로 길이 (행)
            int K = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치의 개수

            int[][] land = new int[N][M];
            int earthWormNum = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); //열
                int y = Integer.parseInt(st.nextToken()); //행
                
                land[y][x] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (land[i][j] == 1) {
                        findNeighbor(land, i, j);
                        earthWormNum++;
                    }
                }
            }

            bw.write(String.valueOf(earthWormNum));
            bw.newLine();
            T--;
        }

        bw.flush();
    }

    static void findNeighbor(int[][] land, int r, int c) {

        land[r][c] = 0;
        
        //위, 아래, 오른쪽, 왼쪽 순으로 탐색한다.
        if ((r+1) < land.length && land[r+1][c] == 1) {
            findNeighbor(land, r+1, c);
        }

        if ((r-1) > -1 && land[r-1][c] == 1) {
            findNeighbor(land, r-1, c);
        }

        if ((c+1) < land[0].length && land[r][c+1] == 1) {
            findNeighbor(land, r, c+1);
        }

        if ((c-1) > -1 && land[r][c-1] == 1) {
            findNeighbor(land, r, c-1);
        }

    }

}
