import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * 이전 작동 시기에 k 광년을 이동한 경우
     * k-1, k, k+1 광년을 이동할 수 있다.
     * 
     * 예
     * x: 0, y: 3, k = 0
     * 1 (-1, 0, 1 -> k = 1)
     * 2 (0, 1, 2 -> k = 1)
     * 3
     * 
     * x: 1, y: 5, k = 0
     * 1 (-1, 0, 1 -> k = 1)
     * 2 (1) (0, 1, 2 -> k = 2)
     * 4 (2) (1, 2, 3 -> k = 1)
     * 5 (3) (0, 1, 2)
     * 
     * x: 45, y: 50
     * 46 (k = 1)
     * 48 (k = 2)
     * 49 (k = 1)
     * 50 (k = 1)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= tc; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            /*
             * x와 y 사이의 거리
                1: 1 (Full)
                2: 1 1
                3: 1 1 1
                4: 1 2 1 (Full)
                5: 1 2 1 1
                6: 1 2 2 1
                7: 1 2 2 1 1
                8: 1 2 2 2 1
                9: 1 2 3 2 1 (Full)
                10: 1 2 3 2 1 1
                11: 1 2 3 2 2 1
                12: 1 2 3 3 2 1
                13: 1 2 3 3 2 1 1
                14: 1 2 3 3 2 2 1
                15: 1 2 3 3 3 2 1
                16: 1 2 3 4 3 2 1 (Full)
                17: 1 2 3 4 3 2 1 1
                18: 1 2 3 4 3 2 2 1
                19: 1 2 3 4 3 3 2 1
                20: 1 2 3 4 4 3 2 1
                21: 1 2 3 4 4 3 2 1 1
             */

             int workingNum = countWorkingNum(y - x);
             System.out.println(workingNum);
        }

    }

    static int countWorkingNum(int distance) {

        int closeSqrtRoot = (int) Math.floor(Math.sqrt(distance));
        int workingNum = closeSqrtRoot * 2 - 1;
        int remainDistance = distance - (closeSqrtRoot * closeSqrtRoot);

        if (remainDistance != 0) {
            if (remainDistance <= closeSqrtRoot) {
                workingNum += 1;
            }
            else {
                workingNum += 2;
            }
        }

        return workingNum;
    }
}
