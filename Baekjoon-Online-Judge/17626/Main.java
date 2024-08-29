import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] minSquareNum = new int[n+1];

        minSquareNum[0] = 0;
        minSquareNum[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            minSquareNum[i] = minSquareNum[i - 1] + 1; // 초기화
            // i 이하의 제곱수 체크
            for (int j = 1; j * j <= i; j++) {
                // 제곱수는 항상 값이 1(4는 2^2, 9는 3^2, ...)이므로, 현재 값 i에서 제곱수를 뺀 값에 츼소 개수에 1을 더하면,
                // 현재 제곱수(j * j)의 최소 개수가 나온다.
                minSquareNum[i] = Math.min(minSquareNum[i], minSquareNum[i - (j * j)] + 1);
            }
        }

        bw.write(String.valueOf(minSquareNum[n]));
        bw.flush();
    }

}
