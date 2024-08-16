import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static long A;
    private static long B;
    private static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        backtrack(A, 1);

        // 만들 수 없는 경우, min이 바뀌지 않음
        min = min == Integer.MAX_VALUE ? -1 : min;
        bw.write(String.valueOf(min));
        bw.flush();
    }

    private static void backtrack(long changeNum, int calcNum) {

        if (changeNum > B) {
            return;
        }

        if (changeNum == B) {
            min = Math.min(min, calcNum);
            return;
        }

        backtrack(changeNum * 2, calcNum + 1);
        backtrack(changeNum * 10 + 1, calcNum + 1);
    }
}
