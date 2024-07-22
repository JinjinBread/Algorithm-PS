import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    
    // 오른쪽, 왼쪽, 아래, 위
    private static int[] rw = { 0, 0, 1, -1 };
    private static int[] cw = { 1, -1, 0, 0 };
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++) {
                map[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }

        int answer = 0;
        List<Integer> numOfHouse = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    answer++;
                    numOfHouse.add(dfs(i, j, 1));
                }
            }
        }

        Collections.sort(numOfHouse);
        
        bw.write(answer + "\n");
        for (int i = 0; i < numOfHouse.size(); i++) {
            if (i != 0)
                bw.newLine();
            bw.write(String.valueOf(numOfHouse.get(i)));    
        }
        bw.flush();
    }

    private static int dfs(int r, int c, int num) {

        map[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            
            int row = r + rw[i];
            int column = c + cw[i];

            // 범위를 벗어났으면
            if (row < 0 || column < 0 || row >= map.length || column >= map.length)
                continue;

            // 집이 없으면 or 이미 들렸으면
            if (map[row][column] == 0)
                continue;

            num = 1 + dfs(row, column, num);
        }

        return num;
    }

}
