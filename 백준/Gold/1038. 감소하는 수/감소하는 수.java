import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
     * 감소하는 수
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     * 10
     * 20, 21
     * 30, 31, 32
     * ...
     * 210
     * 310, 320, 321
     * 410, 420, 421, 430, 431, 432
     * ...
     * 910, 920, 921, 930, 931, 932, 940, 941, 942, 943, 950, ...
     * 3210
     * 
     */
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N < 10) {
            System.out.println(N);
            return;
        }

        long result = backtrack("10", 10);
        System.out.println(result);
    }

    static long backtrack(String num, int k) {
        
        if (num.equals("9876543210") && k < N) {
            return -1;
        }
        
        if (k == N) {
            return Long.parseLong(num);
        }

        int digit = num.length();
        int idx = -1;
        // 320
        for (int i = num.length() - 1; i > 0; i--) {
            int ch = num.charAt(i) - '0';
            int frontCh = num.charAt(i-1) - '0';
            
            if ((ch + 1 < frontCh)) {
                idx = i;
                break;
            }

        }

        StringBuilder sb = new StringBuilder();

        if (idx == -1) {

            int head = num.charAt(0) - '0';
            int start = digit;

            // 자릿수 늘려야 함
            if (head == 9) {
                for (int i = 0; i <= digit; i++) {
                    sb.append(start);
                    start--;
                }
            }
            else {
                // 가장 큰 자릿수의 값을 늘린다.
                sb.append(head + 1);
                start = digit - 2;
                for (int i = 1; i < digit; i++) {
                    sb.append(start);
                    start--;
                }
            }
        }
        else {
            
            for (int i = 0; i < idx; i++) {
                sb.append(num.charAt(i));
            }

            int increasedNum = (num.charAt(idx) - '0') + 1;
            sb.append(increasedNum);
            
            int minNum = digit - (idx + 1) - 1; // 남은 자릿수에 넣을 수 있는 가장 작은 수 -> 4210 의 다음 수는 4310 으로 1을 의미
            for (int i = idx + 1; i < num.length(); i++) {
                sb.append(minNum);
                minNum--;
            }

        }

        return backtrack(sb.toString(), k + 1);
    }
}
