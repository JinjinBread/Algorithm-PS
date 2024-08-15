import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    private static int[] arr;
    private static int[] oper;
    private static int N;
    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 0은 덧셈, 1은 뺄셈, 2는 곱셈, 3은 나눗셈
        oper = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
     
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(arr[0], 1);
        
        bw.write(String.valueOf(max));
        bw.newLine();
        bw.write(String.valueOf(min));
        bw.flush();
    }

    private static void dfs(int sum, int idx) {

        // 연산자 모두 사용(연산자는 총 N - 1개)
        if (idx == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }


        for (int i = 0; i < 4; i++) {
            
            if (oper[i] == 0) {
                continue;
            }

            oper[i]--;

            switch(i) {
                case 0:
                    dfs(sum + arr[idx], idx + 1);
                    break;
                case 1:
                    dfs(sum - arr[idx], idx + 1);
                    break;
                case 2:
                    dfs(sum * arr[idx], idx + 1);
                    break;
                case 3:
                    dfs(sum / arr[idx], idx + 1);
                    break;
            }

            oper[i]++;
        }

    }

}
