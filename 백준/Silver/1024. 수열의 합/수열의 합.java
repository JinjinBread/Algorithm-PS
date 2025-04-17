import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
     * 합이 N이면서
     * 길이는 L 이상인
     * 가장 짧은 연속된 음이 아닌 정수 리스트를 구하라 (0 가능)
     * 
     * ex) 18
     * 5 6 7
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());


        /*
         * 등차수열의 합 (공차가 1인)
         * 첫 항: a
         * 마지막 항: l
         * 
         * l = a + (L - 1)
         * a + (a + 1) + (a + 2) + ... + (a + (L - 2)) + (a + (L - 1)) = N 
         * L*a + L(L+1)/2 = N
         * L*a = N - ((L * (L + 1)) / 2)
         * 
         * 1부터 L까지의 합은 (L * (L + 1)) / 2
         */
        
         int start = N - (((L - 1) * L) / 2);
         int len = L;
         boolean exist = false;
         for (int i = L; i <= 100; i++) {
            
            /*
             * 길이 10 (== 0, 1, 2, 3, 4, 5, 6, 7, 8, 9) -> 45
             */
            start = N - (((i - 1) * i) / 2); // 0부터 시작하기 때문에 수열의 길이를 하나 줄여줌
            if (start < 0) {
                break;
            }

            // 나누어 떨어지는 경우
            if ((start % i) == 0) {
                start /= i; // 정수 리스트의 시작 수
                len = i;
                exist = true;
                break;
            }
         }

         StringBuilder sb = new StringBuilder();

         if (exist) {
            for (int i = 0; i < len; i++) {
                sb.append(start + i).append(" ");
            }
         }
         else {
            sb.append(-1);
         }

         System.out.println(sb.toString());
    }
}
