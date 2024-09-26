import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int C;
    static int[] houses;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int minDist = getMinDistance() - 1;
        bw.write(String.valueOf(minDist));
        bw.flush();
    }

    static int getMinDistance() {

        int low = 1;
        int high = houses[N-1] - houses[0] + 1; // 가능한 최대 거리에 + 1을 한 값(탐색하는 값을 초과하는 값을 찾음)

        while (low < high) {

            // 이분 탐색으로 찾을 '가장 인접한 두 공유기 사이의 최대 거리'
            int mid = (low + high) / 2;

            if (countSetC(mid) < C) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }

    // 설치한 공유기의 수를 센다.
    static int countSetC(int stdDist) {

        // 가장 인접한 두 공유기 사이의 거리를 최대로 하기 위해선 첫 번째 집에 공유기를 설치해야 한다.
        int preHouse = houses[0];
        int usedC = 1; // 설치한 공유기의 수

        for (int i = 1; i < N; i++) {
            // 기준 거리 이상 떨어진 집이라면 공유기를 설치한다.
            if (houses[i] - preHouse >= stdDist) {
                usedC++;
                preHouse = houses[i];
            }

        }

        return usedC;
    }
}
