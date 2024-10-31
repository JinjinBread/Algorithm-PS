import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        1
     3  0  2
        4
        5
    */
    static int[] dice;
    static int[] side;
    static int[][] map;
    static int N;
    static int M;
    static int x;
    static int y;
    // (빈 값), 우, 좌, 상, 하
    static int[] rw = { 0, 0, 0, -1, 1 };
    static int[] cw = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기 == 행 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기 == 열 크기
        x = Integer.parseInt(st.nextToken()); // 주사위 행 위치
        y = Integer.parseInt(st.nextToken()); // 주사위 열 위치
        int K = Integer.parseInt(st.nextToken());

        dice = new int[6]; // 육면체
        side = new int[2];

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] cmd = new int[K];

        for (int i = 0; i < K; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        // 1. (주사위 먼저 움직인 후) 주사위 굴려서 칸 or 주사위 수 갱신
        // 2. 5번 위치(== 상단)에 있는 수 출력

        for (int i = 0; i < K; i++) {
            
            int ans = move(cmd[i]);

            if (ans != -1) {
                System.out.println(ans);
            }

        }


    }

    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    static int move(int cmd) {
        switch (cmd) {
            case 1:
                return moveRight();
            case 2:
                return moveLeft();
            case 3:
                return moveUp();
            case 4:
                return moveDown();
            default:
                return -1;
        }
    }

    /*
        1
     3  0  2
        4
        5
    */
    static int moveRight() {

        int r = x + rw[1];
        int c = y + cw[1];

        if (isValid(r, c)) {

            x = r;
            y = c;
            
            int temp = dice[2];
           
            dice[2] = dice[3];
            dice[3] = dice[0];
            dice[0] = temp;

            temp = dice[2];
            dice[2] = dice[5];
            dice[5] = temp;

            updateDice(r, c);
            return dice[5];
        }

        return -1;
    }

    static int moveLeft() {

        int r = x + rw[2];
        int c = y + cw[2];

        if (isValid(r, c)) {
            
            x = r;
            y = c;

            int temp = dice[3];
            
            dice[3] = dice[2];
            dice[2] = dice[0];
            dice[0] = temp;

            temp = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;

            updateDice(r, c);
            return dice[5];
        }
        
        return -1;
    }

    static int moveUp() {

        int r = x + rw[3];
        int c = y + cw[3];

        if (isValid(r, c)) {

            x = r;
            y = c;

            int temp = dice[0];

            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;

            updateDice(r, c);
            return dice[5];
        }

        return -1;
    }

    static int moveDown() {

        int r = x + rw[4];
        int c = y + cw[4];

        if (isValid(r, c)) {
            
            x = r;
            y = c;

            int temp = dice[0];
            
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;

            updateDice(r, c);
            return dice[5];
        }

        return -1;
    }

    static void updateDice(int r, int c) {

        if (map[r][c] == 0) {
            map[r][c] = dice[0];
        }
        else {
            dice[0] = map[r][c];
            map[r][c] = 0;
        }
    }

}
