import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[] landInfo;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 땅의 세로 길이
        M = Integer.parseInt(st.nextToken()); // 땅의 가로 길이
        int B = Integer.parseInt(st.nextToken()); // 인벤토리에 있는 블록의 개수

        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;

        landInfo = new int[N * M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int landHeight = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, landHeight);
                minHeight = Math.min(minHeight, landHeight);
                landInfo[M * i + j] = landHeight;
            }
        }

        int time = Integer.MAX_VALUE;
        int height = Integer.MIN_VALUE;

        for (int i = minHeight; i <= maxHeight; i++) {
            
            int baseTime = 0;
            int inventory = B;

            for (int j = 0; j < N * M; j++) {
                
                int curLandHeight = landInfo[j];
                int diff = i - curLandHeight;

                // 높이 차이가 없는 블록이면 넘김
                if (diff == 0) {
                    continue;
                }

                // 차이가 음수이면 -> 블록을 제거해야 한다 (1번 작업)
                // ex) 기준 높이가 16, 현재 높이가 20이라 가정하면 -> -4. 이는 현재 높이에서 16높이로 갈라면 블록을 제거해야 한다.
                if (diff < 0) {
                    int digBlock = Math.abs(diff);
                    baseTime += digBlock * 2; // 한 블록 제거하는 데 2초가 걸린다.
                    inventory += digBlock; // 제거한 블록은 인벤토리로 들어온다.
                }
                // 차이가 양수이면 -> 블록을 쌓아 올려야 한다 (2번 작업)
                else {
                    // 인벤토리에 흙이 없으면 continue? (기준 높이(i)에 맞춰 '땅 고르기' 작업을 못한다는 의미 아닌가?) --> X 
                    // --> 15 17 16 (현재 인벤토리에는 흙이 없다고 가정)
                    // --> 기준 높이가 16일 때, (순서상의 이유로) 높이가 15인 흙은 인벤토리에 흙이 없어서 '땅 고르기' 작업을 못한다.
                    // --> 그러나 높이가 17인 흙을 거치고 나면 흙이 1개 생겨, 높이가 15인 흙에 블록을 쌓아 올릴 수 있다.
                    // --> 따라서 모든 땅을 훑은 후, 인벤토리에 있는 흙의 개수를 체크하는 것이 더 올바름

                    baseTime += diff;
                    inventory -= diff;
                }

            }

            // 인벤토리에 있는 흙이 음수면,
            // 기준 높이(i)로 '땅 고르기' 작업이 불가하다는 의미.
            if (inventory < 0) {
                continue;
            }

            // 기준 높이(i)로 '땅 고르기' 작업을 했을 때 걸린 시간 비교
            time = Math.min(time, baseTime);
            // '땅 고르기' 작업의 최소 시간이 갱신됐거나 같은 최소 시간인 경우
            if (time == baseTime) {
                // 더 높은 높이를 선택
                height = Math.max(height, i);
            }
        }

        bw.write(time + " " + height);
        bw.flush();
    }
}
