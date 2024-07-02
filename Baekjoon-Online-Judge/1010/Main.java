
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] dp = new int[30][30]; //모든 요소 0으로 초기화

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        //테스트 케이스
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            //StringTokenizer의 기본 구분자(delimiter)는 스페이스, 탭, 개행 등이 있다.
            st = new StringTokenizer(br.readLine());
            
            int westSite = Integer.parseInt(st.nextToken());
            int eastSite = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(combi(eastSite, westSite)));
            bw.newLine();
        }

        bw.flush();
    }

    private static int combi(int n, int r) {
        
        //이미 계산된 문제라면 결과 재사용
        if (dp[n][r] != 0)
            return dp[n][r];
        
        //nCn nC1 은 모두 1
        if ((n == r) || (r == 0))
            return dp[n][r] = 1;
        
        // n+1Cr+1 = nCr + nCr+1 
        // nCr = n-1Cr-1 + n-1Cr (위의 식에서 n과 r을 1씩 빼주기만 하면 됨)
        return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }
}