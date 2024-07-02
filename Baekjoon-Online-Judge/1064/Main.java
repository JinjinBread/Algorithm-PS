import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {

        //주어진 세(A, B, C) 점(좌표)
        //적절한 한(D) 점을 찾아서 평행사변형을 만들어라.
        
        //평행사변형의 성질?
        //두 쌍의 대변의 길이가 각각 같다.

        //1. AB(두 쌍의 대변 중 한 쌍의 변)와 AC(두 쌍의 대변 중 다른 한 쌍의 변)을 더해서 * 2 한 값 (즉 둘레)
        //2. AB와 BC ...
        //3. AC와 BC ...

        //그러나 좌표가 일직선 상에 있으면 사각형을 만들 수 없으므로 -1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] spot = new int[3][2];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < spot.length; i++) {
            for (int j = 0; j < spot[0].length; j++) {
                spot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //AB와 AC의 기울기가 같다면 A, B, C가 한 직선 위에 있다는 의미이므로 평행사변형을 만들 수 없음
        if ((spot[0][1] - spot[1][1]) * (spot[0][0] - spot[2][0])
            == (spot[0][1] - spot[2][1]) * (spot[0][0] - spot[1][0])) {
                bw.write(String.valueOf(-1.0));
                bw.flush();
                return;
            }

        //spot[0] == A, spot[1] == B, spot[2] == C
        double AB = getDistance(spot[0], spot[1]);
        double AC = getDistance(spot[0], spot[2]);
        double BC = getDistance(spot[1], spot[2]);

        double maxRound = Math.max(2 * (AB + AC), Math.max(2 * (AB + BC), 2 * (AC + BC)));
        double minRound = Math.min(2 * (AB + AC), Math.min(2 * (AB + BC), 2 * (AC + BC)));

        bw.write(String.valueOf(maxRound - minRound));
        bw.flush();
    }

    static double getDistance(int[] spot1, int[] spot2) {
        return Math.sqrt(Math.pow(spot1[0] - spot2[0], 2) + Math.pow(spot1[1] - spot2[1], 2));
    }
}
