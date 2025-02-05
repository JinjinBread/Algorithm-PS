import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] coord = new int[N];

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            coord[i] = x;
        }

        int[] sortedCoord = Arrays.copyOf(coord, N);
        Arrays.sort(sortedCoord);

        int num = 0;
        Map<Integer, Integer> compressedCoord = new HashMap<>(); // 압축한 좌표
        for (int i = 0; i < N; i++) {
            
            int x = sortedCoord[i];

            if (compressedCoord.containsKey(x)) { // 이미 압축한 좌표가 있으면 넘어감
                continue;
            }

            compressedCoord.put(x, num++);
        }

        for (int i = 0; i < N; i++) {
            bw.write(compressedCoord.get(coord[i]) + " ");
        }

        bw.flush();
    }

}