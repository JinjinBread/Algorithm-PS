import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static int N;
    private static StringBuilder sb;
    private static int[][] image;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        image = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) { // N == c.length
                image[i][j] = c[j] - '0';
            }
        }

        dfs(0, 0, N);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int startR, int startC, int size) {

        // size가 1이면 isSameColor의 값이 반드시 true가 나온다.

        // 모두 0 or 1로 이루어져 있다면
        if (isSameColor(startR, startC, size)) {
            sb.append(image[startR][startC]);
        }
        else {
            // 영역이 나눠지면 괄호를 연다.
            sb.append("(");

            int half_size = size / 2;
            dfs(startR, startC, half_size); // 왼쪽 위
            dfs(startR, startC + half_size, half_size); // 오른쪽 위
            dfs(startR + half_size, startC, half_size); // 왼쪽 아래
            dfs(startR + half_size, startC + half_size, half_size); // 오른쪽 아래
            // 영역이 끝나면 괄호를 닫는다.
            sb.append(")");
        }

    }

    private static boolean isSameColor(int startR, int startC, int size) {

        int color = image[startR][startC]; // 시작 점 기준

        for (int i = startR; i < startR + size; i++) {
            for (int j = startC; j < startC + size; j++) {
                if (image[i][j] != color)
                    return false;
            }
        }
        return true;
    }
}
