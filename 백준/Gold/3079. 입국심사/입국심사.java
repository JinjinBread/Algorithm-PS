import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static long[] times;

    public static void main(String[] args) throws IOException {
        /*
         * N(2) 개의 심사대
         * 1 심사대 - 7초
         * 2 심사대 - 10초
         * 
         * M(6) 명의 사람
         * 
         * 1번 심사대(1) + 2번 심사대(2) + 1번 심사대(3) + 2번 심사대(4) + 1번 심사대(5) + 1초(대기) + 1번 심사대(6)
         * 20초일 때 -> 2 심사대 비어 있음, 1 심사대 5번이 1초 후에 끝남
         * 6번이 2번 심사대에 들어간다면 총 30초가 걸림
         * 그러나 1번 심사대가 끝날 때까지 대기(1초) 후 1번 심사대에 들어간다면
         * 총 20초 + 1초 + 7초 -> 28초가 걸린다.
         * 
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        times = new long[N];

        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
        }

        long result = 0;

        long low = 1;
        long high = Arrays.stream(times).max().getAsLong() * M; // 최대 100경

        while (low <= high) {
            long mid = low + (high - low) / 2; // 총 걸리는 시간

            // 모든 인원(M)을 mid 시간 안에 심사 가능하다면
            // high를 줄인다.
            // 최소를 구해야 하므로
            // M과 같은 경우 high 를 줄여줌
            if (isPossibleAllCheckTime(mid)) {
                high = mid - 1;
            }
            // 모든 인원(M)을 mid 시간 안에 심사할 수 없다면
            // low 를 늘림
            else {
                low = mid + 1;
            }
        }

        result = low;
        System.out.println(result);
    }

    static boolean isPossibleAllCheckTime(long givenTime) {
        
        long count = 0; // 100경을 10만번 더하는 경우도 있다 -> oom 발생
        
        // givenTime 만큼의 시간이 주어졌을 때 time이 걸리는 심사대에 몇 명이 심사받을 수 있는지
        // 모든 심사대를 돌면서 심사받을 수 있는 전체 인원을 구함
        for(long time: times) {
            count += (givenTime / time);
            if (count >= M) { // 해당 조건을 달아서 M을 넘어가는 경우 반복문을 그만 돈다.
                return true;
            }
        }

        return false;
    }
}
