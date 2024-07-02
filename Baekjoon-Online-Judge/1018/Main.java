import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] board = new boolean[N][M];
        List<Integer> changeNumberList = new ArrayList<>();

        //Black이면 false, White면 true
        for (int i = 0; i < N; i++) {
            String color = br.readLine();
            for (int j = 0; j < M; j++) {
                char BW = color.charAt(j);
                if (BW == 'B') {
                    board[i][j] = false;
                    continue;
                }
                board[i][j] = true;
            }
        }

        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                int changeColorNum = 0;
                boolean color = board[i][j]; //첫 사각형 색깔

                for (int k = i; k < i + 8; k++) {
                    for (int l = j; l < j + 8; l++) {
                        
                        if (color != board[k][l]) {
                            changeColorNum++;
                        }

                        color = !color;
                    }
                    color = !color; //다음 줄로 넘어가기 전 색깔 바꾸기
                }
                changeNumberList.add(changeColorNum);
                changeNumberList.add(64 - changeColorNum); //첫 사각형 색깔과 반대되는 색깔일 때 변경되는 수
            }
        }

        Collections.sort(changeNumberList);
        
        bw.write(String.valueOf(changeNumberList.get(0)));
        bw.flush();
    }
}