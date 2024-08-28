import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int[][] sticker;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // tc

        int[][][] stickers = new int[T][][];

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            stickers[i] = new int[2][n];

            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    stickers[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int i = 0; i < T; i++) {
            sticker = stickers[i];
            bw.write(String.valueOf(getMaxScore()));
            bw.newLine();
        }

        bw.flush();
    }

    private static int getMaxScore() {
        
        int column = sticker[0].length;

        if (column == 1) {
            return Math.max(sticker[0][0], sticker[1][0]);
        }

        // 열이 2 이상인 경우
        int[][] accumulatedScore = new int[2][column];

        accumulatedScore[0][0] = sticker[0][0];
        accumulatedScore[1][0] = sticker[1][0];
        accumulatedScore[0][1] = accumulatedScore[1][0] + sticker[0][1];
        accumulatedScore[1][1] = accumulatedScore[0][0] + sticker[1][1];

        for (int i = 2; i < column; i++) {
            for (int j = 0; j < 2; j++) {
                int r = j == 0 ? 1 : 0;
                int case1 = accumulatedScore[r][i - 1] + sticker[j][i];
                int case2 = Math.max(accumulatedScore[0][i - 2], accumulatedScore[1][i - 2]) + sticker[j][i];
                accumulatedScore[j][i] = Math.max(case1, case2);
            }
        }

        return Math.max(accumulatedScore[0][column - 1], accumulatedScore[1][column - 1]);
    }
}
