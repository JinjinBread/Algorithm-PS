import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class CCTV {
        int cctvType;
        int r;
        int c;
        public CCTV(int cctvType, int r, int c) { this.cctvType = cctvType; this.r = r; this.c = c; }
    }

    static int N; // 세로
    static int M; // 가로
    static CCTV[] allCCTV = new CCTV[8]; // CCTV의 최대 개수는 8개
    static int totalCCTVNum; 
    static int[][] office;
    static int min = Integer.MAX_VALUE; // 사각지대 수

    // 오른쪽, 아래, 왼쪽, 위
    static int[] rw = { 0, 1, 0, -1 };
    static int[] cw = { 1, 0, -1, 0 };

    static int[][][] move = { 
        {
            {0}, {1}, {2}, {3} // 1번 CCTV가 가능한 감시 방향 (rw와 cw의 idx 값 -> 0은 오른쪽, 1은 아래 ...)
        },
        {
            {0, 2}, {1, 3} // 2번 CCTV가 가능한 감시 방향 (좌·우, 상·하)
        },
        {
            {3, 0}, {0, 1}, {1, 2}, {2, 3} // 3번 CCTV가 가능한 감시 방향
        },
        {
            {2, 3, 0}, {3, 0, 1}, {0, 1, 2}, {1, 2, 3} // 4번 CCTV가 가능한 감시 방향
        },
        {
            {0, 1, 2, 3} // 5번 CCTV가 가능한 감시 방향
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info > 0 && info < 6) {
                    allCCTV[totalCCTVNum++] = new CCTV(info, i, j);
                }
                office[i][j] = info;
            }
        }

        int[][] tempOffice = copy2DArray(office);
        backtrack(0, tempOffice);

        bw.write(String.valueOf(min));
        bw.flush();
    }

    static void backtrack(int cctvNum, int[][] accumOffice) {

        if (cctvNum >= totalCCTVNum) {
            min = Math.min(min, countBlindSpot(accumOffice));
            return;
        }

        CCTV cctv = allCCTV[cctvNum];
        int cctvType = cctv.cctvType - 1;

        for (int i = 0; i < move[cctvType].length; i++) {
            
            // 감시가 가능한 곳을 표시하기 전의 맵을 저장해둠
            int[][] preOffice = copy2DArray(accumOffice);

            // 감시가 가능한 곳을 -1로 바꿈
            for (int j = 0; j < move[cctvType][i].length; j++) {
                
                int direction = move[cctvType][i][j];

                int r = cctv.r + rw[direction];
                int c = cctv.c + cw[direction];

                while (isValidLocation(r, c)) {

                    if (accumOffice[r][c] == 6)
                        break;
                    
                    if (accumOffice[r][c] == 0) {
                        accumOffice[r][c] = -1;
                    }
                    
                    r += rw[direction];
                    c += cw[direction];
                }
            }

            backtrack(cctvNum + 1, accumOffice);
            // 원복
            accumOffice = copy2DArray(preOffice);
        }
    }

    static int[][] copy2DArray(int[][] arr) {
        int[][] tempArr = new int[N][M];

        for (int i = 0; i < N; i++) {
            tempArr[i] = Arrays.copyOf(arr[i], M);
        }

        return tempArr;
    }

    static boolean isValidLocation(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    static int countBlindSpot(int[][] accumOffice) {

        int blindSpotNum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (accumOffice[i][j] == 0) {
                    blindSpotNum++;
                }
            }
        }

        return blindSpotNum;
    }
}