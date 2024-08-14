import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
    private static boolean[] col;
    private static boolean[] diagonal1; // 주대각선 (행과 열의 차가 같음)
    private static boolean[] diagonal2; // 반대각선 (행과 열의 합이 같음)
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        col = new boolean[N];
        diagonal1 = new boolean[N * 2]; // 널널하게 잡음
        diagonal2 = new boolean[N * 2];

        bw.write(String.valueOf(dfs(0)));
        bw.flush();
    }

    private static int dfs(int r) {

        int num = 0;

        // 마지막 행까지 도달한 경우
        if (r == N) {
            num++;
        }
        else {
            for (int i = 0; i < N; i++) {

                // 열(행은 다음 행으로 넘어가므로 중복될 일이 없음)과 대각선에 퀸이 존재하면 설치할 수 없음
                // + N을 해주는 이유는 (r-i) 값이 음수가 나오면 out of index가 되기 때문
                // 그렇다고 절댓값을 해줄 순 없다. (ex) 5,2 와 0,3의 절댓값은 3이지만, 둘은 주대각선에 있지 않음)
                if (col[i] || diagonal1[r-i + N] || diagonal2[r+i]) {
                    continue;
                }
    
                col[i] = true;
                diagonal1[r - i + N] = true;
                diagonal2[r+i] = true;
    
                num += dfs(r + 1);
    
                col[i] = false;
                diagonal1[r - i + N] = false;
                diagonal2[r+i] = false;
            }
        }
        
        return num;
    }

}
